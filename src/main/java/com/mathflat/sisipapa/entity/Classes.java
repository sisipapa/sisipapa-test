package com.mathflat.sisipapa.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Data
@Entity(name = "classes")
public class Classes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column(nullable = true)
    private int score;

    @ManyToOne
    @JoinColumn(name = "student_idx")
    private Students students;

    @ManyToOne
    @JoinColumn(name = "subject_idx")
    private Subjects subjects;

    @Builder
    public Classes(int score, Students students, Subjects subjects){
        this.score = score;
        this.students = students;
        this.subjects = subjects;
    }
}
