package com.mathflat.sisipapa.service;

import com.mathflat.sisipapa.dto.ResponseDto;
import com.mathflat.sisipapa.dto.StudentDto;
import com.mathflat.sisipapa.entity.Classes;
import com.mathflat.sisipapa.entity.Students;
import com.mathflat.sisipapa.entity.Subjects;
import com.mathflat.sisipapa.repository.ClassesRepository;
import com.mathflat.sisipapa.repository.StudentsRepository;
import com.mathflat.sisipapa.repository.SubjectsRepository;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.mathflat.sisipapa.entity.QStudents.students;

@RequiredArgsConstructor
@Service
public class StudentService {

    private final StudentsRepository studentRepository;

    private final SubjectsRepository subjectsRepository;

    private final ClassesRepository classesRepository;

    private final JPAQueryFactory jpaQueryFactory;

    public ResponseDto postStudents(StudentDto dto){
        // DB Column에 Unique Index를 걸어두었지만 Application 에서 중복여부 한번 더 체크
        Students findStudents = studentRepository.findByPhoneNumber(dto.getPhoneNumber()).orElse(null);
        if(Objects.nonNull(findStudents)){
            Map<String, String> errorMap = new HashMap<>();
            errorMap.put("code", "ALREADY_EXIST_STUDENT");
            errorMap.put("message", "이미 존재하는 학생입니다. [" + dto.getPhoneNumber() + "]");
            return ResponseDto.builder().error(errorMap).build();
        }

        // 클라이언트에게 전달받은 데이터 Students Entity 형식에 맞게 Set
        Students students = Students.builder()
                .name(dto.getName())
                .age(dto.getAge())
                .schoolType(dto.getSchoolType())
                .phoneNumber(dto.getPhoneNumber())
                .build();
        Students saveStudent = studentRepository.save(students);

        // 학생 추가시 추가한 학생은 등록되어 있는 모든 과목을 수강하여야만 합니다.
        // 1. 과목조회
        List<Subjects> subjects = subjectsRepository.findAll();
        // 2. 수강신청
        List<Classes> classes = new ArrayList<>();
        subjects.stream().forEach(findSubject -> {
            Classes newClass = Classes.builder()
                    .students(Students.builder().idx(saveStudent.getIdx()).build())
                    .subjects(Subjects.builder().idx(findSubject.getIdx()).build())
                    .build();
//            classes.add(newClass);
            classesRepository.save(newClass);
        });
//        Iterable<Classes> iterable = classes;
//        classesRepository.saveAll(iterable);

        return ResponseDto.builder().data(null).error(null).build();
    }

    /**
     * 학생 목록 조회
     * @return
     */
    public ResponseDto getStudents(){

        List<StudentDto> result = jpaQueryFactory
                .select(Projections.bean(StudentDto.class,
                        students.idx,
                        students.name,
                        students.age,
                        students.schoolType,
                        students.phoneNumber))
                .from(students)
                .fetch();
        Map<String,Object> dataMap = Map.of("students", result);

        return ResponseDto.builder().data(dataMap).error(null).build();
    }
}
