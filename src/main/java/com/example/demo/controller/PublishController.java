package com.example.demo.controller;

import com.example.demo.service.SQSEventPublisher;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PublishController {

    @Autowired
    private SQSEventPublisher sqsEventPublisher;

    @PostMapping("/sendMessage")
    public ResponseEntity sendMessage(@RequestBody JsonNode jsonNode) {
        sqsEventPublisher.publishEvent(jsonNode);
        return ResponseEntity.ok().build();
    }
}