package com.mathflat.sisipapa.repository;

import com.mathflat.sisipapa.domain.Students;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentsRepository extends JpaRepository<Students, Long> {

    Optional<Students> findByPhoneNumber(String phoneNumber);
}
