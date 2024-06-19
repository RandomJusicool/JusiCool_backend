package com.juicycool.backend.domain.user.util;

import com.juicycool.backend.domain.user.User;
import com.juicycool.backend.domain.user.exception.NotFoundUserException;
import com.juicycool.backend.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class UserUtil {

    private final UserRepository userRepository;

    public User getCurrentUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        log.info(email);
        return userRepository.findByEmail(email)
                .orElseThrow(NotFoundUserException::new);
    }

}
