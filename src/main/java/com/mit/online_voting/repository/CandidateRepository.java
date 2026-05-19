package com.mit.online_voting.repository;

import com.mit.online_voting.model.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateRepository extends JpaRepository<Candidate, Long> { }
