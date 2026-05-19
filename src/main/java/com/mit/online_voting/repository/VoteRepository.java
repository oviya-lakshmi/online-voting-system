package com.mit.online_voting.repository;

import com.mit.online_voting.model.Vote;
import com.mit.online_voting.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface VoteRepository extends JpaRepository<Vote, Long> {
    Optional<Vote> findByUser(User user);
}
