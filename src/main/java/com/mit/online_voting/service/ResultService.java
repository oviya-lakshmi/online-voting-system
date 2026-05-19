package com.mit.online_voting.service;

import com.mit.online_voting.model.Candidate;
import com.mit.online_voting.repository.CandidateRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResultService {

    private final CandidateRepository candidateRepository;

    public ResultService(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    // Fetch all candidates sorted by vote count
    public List<Candidate> getAllResults() {
        return candidateRepository.findAll()
                .stream()
                .sorted((c1, c2) -> Integer.compare(c2.getVoteCount(), c1.getVoteCount()))
                .toList();
    }

    // Get top candidate (winner)
    public Candidate getWinner() {
        return candidateRepository.findAll()
                .stream()
                .max((c1, c2) -> Integer.compare(c1.getVoteCount(), c2.getVoteCount()))
                .orElse(null);
    }
}
