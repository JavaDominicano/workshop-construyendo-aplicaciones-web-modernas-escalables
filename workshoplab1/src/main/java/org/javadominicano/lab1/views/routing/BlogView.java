package org.javadominicano.lab1.views.routing;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;

/**
 * @author me@fredpena.dev
 * @created 24/11/2023  - 17:53
 */
@Slf4j
@Route("blog")
public class BlogView extends Div implements BeforeEnterObserver {
    private final Random random = new Random();

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        // implementation omitted
        Object record = getItem();

//        if (record == null) {
        boolean hasChanges = hasChanges();
        log.info("hasChanges: {}", hasChanges);
        if (hasChanges) {
            //event.rerouteTo(BlogMessageView.class);
            event.forwardTo(BlogMessageView.class);
        }
    }

    private Object getItem() {
        // no-op implementation
        return null;
    }

    private boolean hasChanges() {
        return random.nextBoolean();
    }
}