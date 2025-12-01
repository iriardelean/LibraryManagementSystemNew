package com.example.librarymanagementsystemnew.controller;

import com.example.librarymanagementsystemnew.model.Member;
import com.example.librarymanagementsystemnew.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping
    public String listMembers(Model model) {
        model.addAttribute("members", memberService.getAllMembers());
        return "member/index";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("member", new Member());
        model.addAttribute("pageTitle", "Create New Member");
        return "member/form";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        Member m = memberService.getMemberById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid member Id:" + id));
        model.addAttribute("member", m);
        model.addAttribute("pageTitle", "Edit Member");
        return "member/form";
    }

    @PostMapping
    public String saveMember(@ModelAttribute Member member) {
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
}