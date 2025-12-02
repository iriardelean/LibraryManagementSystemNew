package com.example.librarymanagementsystemnew.controller;

import com.example.librarymanagementsystemnew.model.Member;
import com.example.librarymanagementsystemnew.service.LibraryService;
import com.example.librarymanagementsystemnew.service.MemberService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;
    private final LibraryService libraryService;

    public MemberController(MemberService memberService, LibraryService libraryService) {
        this.memberService = memberService;
        this.libraryService = libraryService;
    }

    @GetMapping
    public String listMembers(Model model) {
        model.addAttribute("members", memberService.getAllMembers());
        return "member/index";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("member", new Member());
        model.addAttribute("libraries", libraryService.getAllLibraries()); // Populate Library Dropdown
        model.addAttribute("pageTitle", "Create New Member");
        return "member/form";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        Member m = memberService.getMemberById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid member Id:" + id));
        model.addAttribute("member", m);
        model.addAttribute("libraries", libraryService.getAllLibraries()); // Populate Library Dropdown
        model.addAttribute("pageTitle", "Edit Member");
        return "member/form";
    }

    @PostMapping
    public String saveMember(@Valid @ModelAttribute Member member, BindingResult result, Model model) {
        if (result.hasErrors()) {
            // Reload list on error
            model.addAttribute("libraries", libraryService.getAllLibraries());
            model.addAttribute("pageTitle", member.getId() == null ? "Create New Member" : "Edit Member");
            return "member/form";
        }

        if (member.getId() == null) {
            memberService.createMember(member);
        } else {
            memberService.updateMember(member);
        }
        return "redirect:/member";
    }

    @PostMapping("/{id}/delete")
    public String deleteMember(@PathVariable Long id) {
        memberService.deleteMember(id);
        return "redirect:/member";
    }

    @GetMapping("/{id}/details")
    public String showDetails(@PathVariable Long id, Model model) {
        Member member = memberService.getMemberById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid member Id:" + id));
        model.addAttribute("member", member);
        return "member/details";
    }
}