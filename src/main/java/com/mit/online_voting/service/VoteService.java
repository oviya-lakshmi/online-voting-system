package com.mit.online_voting.service;

import com.mit.online_voting.model.*;
import com.mit.online_voting.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class VoteService {
    
    private final VoteRepository voteRepository;
    private final CandidateRepository candidateRepository;
    private final UserRepository userRepository;
    
    public VoteService(VoteRepository voteRepository, 
                       CandidateRepository candidateRepository, 
                       UserRepository userRepository) {
        this.voteRepository = voteRepository;
        this.candidateRepository = candidateRepository;
        this.userRepository = userRepository;
    }
    
    public List<Candidate> getAllCandidates() {
        return candidateRepository.findAll();
    }
    
    public boolean hasUserVoted(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        return user.isPresent() && voteRepository.findByUser(user.get()).isPresent();
    }
    
    @Transactional
    public boolean castVote(Long userId, Long candidateId) {
        Optional<User> userOpt = userRepository.findById(userId);
        Optional<Candidate> candidateOpt = candidateRepository.findById(candidateId);
        
        if (userOpt.isEmpty() || candidateOpt.isEmpty()) {
            return false;
        }
        
        User user = userOpt.get();
        Candidate candidate = candidateOpt.get();
        
        // Check if user has already voted
        if (voteRepository.findByUser(user).isPresent()) {
            return false;
        }
        
        // Increment vote count
        candidate.setVoteCount(candidate.getVoteCount() + 1);
        candidateRepository.save(candidate);
        
        // Record the vote
        Vote vote = new Vote(user, candidate);
        voteRepository.save(vote);
        
        return true;
    }
    
    public List<Candidate> getResults() {
        return candidateRepository.findAll();
    }
}