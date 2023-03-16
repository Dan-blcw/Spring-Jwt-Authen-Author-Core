package com.DanCreate.securityTesting.runDemo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth/demo-controller")
public class testController {
    @GetMapping
    public ResponseEntity<String> sayHello() {
        return ResponseEntity.ok("What's up? I'm Dan if it run that mean it work, this message from secured endpoint !");
    }

}
