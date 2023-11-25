package org.javadominicano.lab1.views.routing;

import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;
import lombok.extern.slf4j.Slf4j;

/**
 * @author me@fredpena.dev
 * @created 24/11/2023  - 18:31
 */
@Slf4j
@Route("auth")
public class AuthenticationHandler implements BeforeEnterObserver {
    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        Class<?> target = event.getNavigationTarget();
        if (!currentUserMayEnter(target)) {
            event.rerouteToError(CustomAccessDeniedException.class);
        }
    }

    private boolean currentUserMayEnter(
            Class<?> target) {
        // implementation omitted
        return false;
    }

    private static class CustomAccessDeniedException extends RuntimeException {
    }
}
