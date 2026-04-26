package com.email.emailwriter.controller;

import com.email.emailwriter.dto.EmailRequestDTO;
import com.email.emailwriter.service.EmailGeneratorService;
import lombok.AllArgsConstructor;
import org.checkerframework.checker.units.qual.C;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/email")
@AllArgsConstructor
@CrossOrigin("*")
public class EmailGeneratorController {

    private final EmailGeneratorService emailGeneratorService;

    @PostMapping("/generate")
    public ResponseEntity<?> generateEmail (@RequestBody EmailRequestDTO emailRequestDTO){
        String response = emailGeneratorService.generateEmailReply(emailRequestDTO);
        return ResponseEntity.ok(response);


    }


}
