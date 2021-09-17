package com.mathflat.sisipapa.controller;

import com.mathflat.sisipapa.entity.Classes;
import com.mathflat.sisipapa.entity.Students;
import com.mathflat.sisipapa.dto.ResponseDto;
import com.mathflat.sisipapa.dto.StudentDto;
import com.mathflat.sisipapa.entity.Subjects;
import com.mathflat.sisipapa.repository.ClassesRepository;
import com.mathflat.sisipapa.repository.StudentsRepository;
import com.mathflat.sisipapa.repository.SubjectsRepository;
import com.mathflat.sisipapa.service.StudentService;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RequiredArgsConstructor
@RestController
public class StudentController {

    private final StudentService studentService;

    @PostMapping("/students")
    @Operation(summary = "학생등록",
            description = "학생 정보를 등록한다.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "학생 등록 성공", content = @Content(schema = @Schema(implementation = ResponseDto.class))),
                    @ApiResponse(responseCode = "400", description = "이미 존재하는 학생입니다. [${studentPhoneNumber}]", content = @Content(schema = @Schema(implementation = ResponseDto.class)))
            })
    public ResponseEntity<? extends ResponseDto> postStudents(@RequestBody StudentDto dto){
        ResponseDto result = studentService.postStudents(dto);
        return ResponseEntity.status(201).body(result);
    }

    @GetMapping("/students")
    @Operation(summary = "학생조회",
            description = "학생 목록을 조회한다.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "학생 조회 성공", content = @Content(schema = @Schema(implementation = ResponseDto.class))),
            })
    public ResponseEntity<? extends ResponseDto> getStudents(){
        ResponseDto result = studentService.getStudents();
        return ResponseEntity.status(200).body(result);
    }
}
