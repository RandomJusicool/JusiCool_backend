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
public class UserUtil {

    private final UserRepository userRepository;

    public User getCurrentUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByEmail(email)
                .orElseThrow(NotFoundUserException::new);
    }

}
