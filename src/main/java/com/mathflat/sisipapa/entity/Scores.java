package com.mathflat.sisipapa.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "scores")
public class Scores {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column(nullable = false)
    private int score;

    @ManyToOne
    @JoinColumn(name = "student_idx")
    private Students students;

    @ManyToOne
    @JoinColumn(name = "subject_idx")
    private Subjects subjects;

}
