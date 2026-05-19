package com.mit.online_voting.controller;

import com.mit.online_voting.model.Candidate;
import com.mit.online_voting.model.User;
import com.mit.online_voting.repository.UserRepository;
import com.mit.online_voting.service.VoteService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class VoteController {
    
    private final VoteService voteService;
    private final UserRepository userRepository;
    
    public VoteController(VoteService voteService, UserRepository userRepository) {
        this.voteService = voteService;
        this.userRepository = userRepository;
    }
    
    @GetMapping("/vote")
    public String showVotePage(Model model, Authentication authentication) {
        List<Candidate> candidates = voteService.getAllCandidates();
        model.addAttribute("candidates", candidates);
        
        // Check if user has already voted
        String username = authentication.getName();
        User user = userRepository.findByUsername(username).orElse(null);
        if (user != null) {
            boolean hasVoted = voteService.hasUserVoted(user.getId());
            model.addAttribute("hasVoted", hasVoted);
        }
        
        return "vote";
    }
    
    @PostMapping("/vote")
    public String castVote(@RequestParam Long candidateId, 
                           Authentication authentication,
                           Model model) {
        String username = authentication.getName();
        User user = userRepository.findByUsername(username).orElse(null);
        
        if (user == null) {
            model.addAttribute("error", "User not found");
            return "redirect:/login";
        }
        
        boolean success = voteService.castVote(user.getId(), candidateId);
        
        if (success) {
            model.addAttribute("success", "Vote cast successfully!");
            return "redirect:/results";
        } else {
            model.addAttribute("error", "You have already voted or invalid candidate");
            model.addAttribute("candidates", voteService.getAllCandidates());
            return "vote";
        }
    }
}