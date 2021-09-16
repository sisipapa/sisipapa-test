package com.mathflat.sisipapa.repository;

import com.mathflat.sisipapa.domain.Students;
import com.mathflat.sisipapa.domain.Subjects;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectsRepository extends JpaRepository<Subjects, Long> {
}
