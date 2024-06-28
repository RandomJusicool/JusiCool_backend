package com.juicycool.backend.domain.email.repository;


import com.juicycool.backend.domain.email.MailAuthEntity;
import org.springframework.data.repository.CrudRepository;

public interface MailAuthRepository extends CrudRepository<MailAuthEntity, String> {
}
