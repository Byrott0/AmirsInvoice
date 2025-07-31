package com.example.amirsinvoicer.Repository;

import com.example.amirsinvoicer.Model.InvalidToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvalidTokenRepository extends JpaRepository<InvalidToken, String> {
}