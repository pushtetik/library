package com.example.demo.service;

import com.example.demo.entity.AuditLog;
import com.example.demo.repository.AuditRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class AuditService {
    private final AuditRepository repository;
    public AuditService(AuditRepository repository) { this.repository = repository; }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void log(String action) {
        repository.save(new AuditLog( action, LocalDateTime.now()));
    }
}