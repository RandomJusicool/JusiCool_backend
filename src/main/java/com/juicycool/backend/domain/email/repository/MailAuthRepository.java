package com.juicycool.backend.domain.email.repository;


import com.juicycool.backend.domain.email.EmailAuthEntity;
import org.springframework.data.repository.CrudRepository;

public interface MailAuthRepository extends CrudRepository<EmailAuthEntity, String> {
}
