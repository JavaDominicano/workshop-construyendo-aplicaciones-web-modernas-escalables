package org.javadominicano.workshop.security;

import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.spring.security.AuthenticationContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.javadominicano.workshop.data.entity.User;
import org.javadominicano.workshop.data.service.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;


@Slf4j
@Component
@RequiredArgsConstructor
public class AuthenticatedUser {

    private final UserRepository userRepository;
    private final AuthenticationContext authenticationContext;


    @Transactional
    public Optional<User> get() {
        return authenticationContext.getAuthenticatedUser(UserDetails.class)
                .map(auth -> {
                    final User user = userRepository.findByUsername(auth.getUsername());

                    user.setLastLoginTs(Instant.now());
                    userRepository.save(user);

                    VaadinSession.getCurrent().setAttribute(User.class, user);

                    return user;
                });
    }

    public void logout() {
        authenticationContext.logout();
    }

}
