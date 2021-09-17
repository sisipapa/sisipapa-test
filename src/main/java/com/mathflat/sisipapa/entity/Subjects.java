package com.mathflat.sisipapa.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
@Entity(name = "subjects")
public class Subjects {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column(nullable = false, length = 50)
    private String name;

    @OneToMany(mappedBy = "subjects")
    private List<Classes> scores = new ArrayList<>();

    @Builder
    public Subjects(Long idx, String name){
        this.idx = idx;
        this.name = name;
    }
}
