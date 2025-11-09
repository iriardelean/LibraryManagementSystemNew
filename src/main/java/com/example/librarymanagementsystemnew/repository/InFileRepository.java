package com.example.librarymanagementsystemnew.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

/**
 * Generisches Repository, das Daten in einer JSON-Datei im Verzeichnis 'src/main/resources/data' speichert.
 * Implementiert das CrudRepository-Interface und ersetzt das InMemoryRepository.
 *
 * @param <T>  Der Entitätstyp
 * @param <ID> Der Typ des Primärschlüssels (ID)
 */
public class InFileRepository<T, ID> implements CrudRepository<T, ID> {

    protected final Map<ID, T> store = new ConcurrentHashMap<>();
    private final Function<T, ID> idExtractor;
    private final Path filePath;
    private final ObjectMapper objectMapper;
    private final TypeReference<List<T>> typeReference;

    public InFileRepository(String dataFileName, Function<T, ID> idExtractor, TypeReference<List<T>> typeReference) {
        if (idExtractor == null) throw new IllegalArgumentException("idExtractor cannot be null");
        this.idExtractor = idExtractor;
        this.typeReference = typeReference;

        // ObjectMapper für JSON-Verarbeitung konfigurieren
        this.objectMapper = new ObjectMapper();
        this.objectMapper.enable(SerializationFeature.INDENT_OUTPUT); // Schöne JSON-Formatierung
        this.objectMapper.registerModule(new JavaTimeModule()); // Unterstützung für LocalDate etc.

        try {
            // Pfad zur JSON-Datei im 'resources/data'-Ordner
            File dataDir = ResourceUtils.getFile("classpath:data");
            this.filePath = Paths.get(dataDir.getAbsolutePath(), dataFileName);
        } catch (IOException e) {
            System.err.println("Fehler beim Auflösen des Datenverzeichnisses 'resources/data'");
            throw new RuntimeException("Konnte Datenverzeichnis nicht finden", e);
        }

        loadData();
    }

    /**
     * Lädt die Daten aus der JSON-Datei in den In-Memory-Speicher (die 'store'-Map).
     * Wird beim Start aufgerufen.
     */
    private synchronized void loadData() {
        if (!Files.exists(filePath)) {
            System.out.println("Datendatei nicht gefunden, wird erstellt: " + filePath);
            saveData(); // Erstellt eine leere Datei, falls sie nicht existiert
            return;
        }

        try {
            byte[] bytes = Files.readAllBytes(filePath);
            if (bytes.length == 0) {
                System.out.println("Datendatei ist leer: " + filePath);
                return;
            }

            List<T> entities = objectMapper.readValue(bytes, typeReference);
            store.clear();
            for (T entity : entities) {
                store.put(idExtractor.apply(entity), entity);
            }
            System.out.println("Erfolgreich " + entities.size() + " Einträge geladen aus: " + filePath.getFileName());
        } catch (IOException e) {
            System.err.println("Fehler beim Laden der Daten aus: " + filePath);
            e.printStackTrace();
            throw new RuntimeException("Fehler beim Laden der Datendatei", e);
        }
    }

    /**
     * Speichert den aktuellen In-Memory-Speicher ('store') in die JSON-Datei.
     * Wird nach 'save' und 'delete' aufgerufen.
     */
    private synchronized void saveData() {
        try {
            // Sicherstellen, dass das Verzeichnis existiert
            Files.createDirectories(filePath.getParent());
            // Alle Werte aus der Map in die JSON-Datei schreiben
            objectMapper.writeValue(filePath.toFile(), new ArrayList<>(store.values()));
        } catch (IOException e) {
            System.err.println("Fehler beim Speichern der Daten in: " + filePath);
            e.printStackTrace();
            throw new RuntimeException("Fehler beim Speichern der Datendatei", e);
        }
    }

    @Override
    public T save(T entity) {
        if (entity == null) throw new IllegalArgumentException("entity cannot be null");
        ID id = idExtractor.apply(entity);
        if (id == null) throw new IllegalArgumentException("entity id cannot be null");

        store.put(id, entity);
        System.out.println("Gespeichert: " + entity + " in " + filePath.getFileName());
        saveData(); // Nach jeder Änderung speichern
        return entity;
    }

    @Override
    public Optional<T> findById(ID id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<T> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public void delete(ID id) {
        T removed = store.remove(id);
        if (removed != null) {
            System.out.println("Gelöscht: " + removed + " aus " + filePath.getFileName());
            saveData(); // Nach jeder Änderung speichern
        } else {
            System.out.println("Item mit ID " + id + " nicht gefunden in " + filePath.getFileName());
        }
    }
}