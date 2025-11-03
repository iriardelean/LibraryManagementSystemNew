package com.example.librarymanagementsystemnew.controller;

import com.example.librarymanagementsystemnew.model.Member;
import com.example.librarymanagementsystemnew.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping
    public String listMembers(Model model) {
        model.addAttribute("members", memberService.findAll());
        return "member/index";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("member", new Member());
        return "member/form";
    }

    @PostMapping
    public String createMember(@ModelAttribute Member member) {
        member.setId(UUID.randomUUID().toString());
        memberService.create(member);
        return "redirect:/member";
    }

    @PostMapping("/{id}/delete")
    public String deleteMember(@PathVariable String id) {
        memberService.delete(id);
        return "redirect:/member";
    }
}