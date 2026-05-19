package com.mit.online_voting.controller;

import com.mit.online_voting.model.Candidate;
import com.mit.online_voting.service.ResultService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ResultController {

    private final ResultService resultService;

    public ResultController(ResultService resultService) {
        this.resultService = resultService;
    }

    // Display all results
    @GetMapping("/results")
    public String showResults(Model model) {
        List<Candidate> results = resultService.getAllResults();
        Candidate winner = resultService.getWinner();

        model.addAttribute("results", results);
        model.addAttribute("winner", winner);

        return "results"; // results.html
    }
}
