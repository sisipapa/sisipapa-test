package com.mathflat.sisipapa.domain;

import com.mathflat.sisipapa.enumtype.ScoolType;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class Students {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int age;

    @Column(nullable = false)
    private ScoolType scoolType;

    @Column(nullable = false)
    private String phoneNumber;

    @Builder
    public Students(String name, int age, ScoolType scoolType, String phoneNumber){
        this.name = name;
        this.age = age;
        this.scoolType = scoolType;
        this.phoneNumber = phoneNumber;
    }
}
