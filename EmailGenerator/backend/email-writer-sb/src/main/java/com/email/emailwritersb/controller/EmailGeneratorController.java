package com.email.emailwritersb.controller;



import com.email.emailwritersb.model.EmailRequest;
import com.email.emailwritersb.service.EmailGeneratorService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/email")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class EmailGeneratorController {


    private final EmailGeneratorService service;

    @PostMapping("/generate")
    public ResponseEntity<String> generateEmail(@RequestBody EmailRequest emailRequest){
        String response = service.generateEmailReply(emailRequest);
        return ResponseEntity.ok(response);
    }

}
