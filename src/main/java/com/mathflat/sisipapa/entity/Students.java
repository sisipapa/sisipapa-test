package com.mathflat.sisipapa.entity;

import com.mathflat.sisipapa.enumtype.SchoolType;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Data
@Entity(name = "students")
public class Students {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idx")
    private Long idx;

    @Column(nullable = false, length = 50, name = "name")
    private String name;

    @Column(nullable = false, name = "age")
    private int age;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10, name = "school_type")
    private SchoolType schoolType;

    @Column(nullable = false, length = 15, name = "phone_number", unique=true)
    private String phoneNumber;

    @OneToMany(mappedBy = "students")
    private List<Classes> scores = new ArrayList<>();

    @Builder
    public Students(Long idx, String name, int age, SchoolType schoolType, String phoneNumber){
        this.idx = idx;
        this.name = name;
        this.age = age;
        this.schoolType = schoolType;
        this.phoneNumber = phoneNumber;
    }

}
