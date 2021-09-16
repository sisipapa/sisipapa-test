package com.mathflat.sisipapa.controller;

import com.mathflat.sisipapa.entity.Students;
import com.mathflat.sisipapa.dto.ResponseDto;
import com.mathflat.sisipapa.dto.StudentDto;
import com.mathflat.sisipapa.repository.StudentsRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RequiredArgsConstructor
@RestController
public class StudentController {

    private final StudentsRepository studentRepository;

    private final JPAQueryFactory jpaQueryFactory;

    @PostMapping("/students")
    @Operation(summary = "학생등록",
            description = "학생 정보를 등록한다.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "학생 등록 성공", content = @Content(schema = @Schema(implementation = ResponseDto.class))),
                    @ApiResponse(responseCode = "400", description = "이미 존재하는 학생입니다. [${studentPhoneNumber}]", content = @Content(schema = @Schema(implementation = ResponseDto.class)))
            })
    public ResponseEntity<? extends ResponseDto> postStudents(@RequestBody StudentDto dto){

        Students findStudents = studentRepository.findByPhoneNumber(dto.getPhoneNumber()).orElse(null);

        if(Objects.nonNull(findStudents)){
            Map<String, String> errorMap = new HashMap<>();
            errorMap.put("code", "ALREADY_EXIST_STUDENT");
            errorMap.put("message", "이미 존재하는 학생입니다. [" + dto.getPhoneNumber() + "]");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDto(null, errorMap));
        }

        Students students = Students.builder()
                .name(dto.getName())
                .age(dto.getAge())
                .schoolType(dto.getSchoolType())
                .phoneNumber(dto.getPhoneNumber())
                .build();

        studentRepository.save(students);
        return ResponseEntity.status(201).body(new ResponseDto(null, null));

    }
}
