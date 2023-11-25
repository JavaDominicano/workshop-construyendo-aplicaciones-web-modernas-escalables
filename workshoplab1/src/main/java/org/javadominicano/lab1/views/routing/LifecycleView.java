package org.javadominicano.lab1.views.routing;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;
import lombok.extern.slf4j.Slf4j;

/**
 * @author me@fredpena.dev
 * @created 24/11/2023  - 17:47
 */
@Slf4j
@Route("lifecycle")
public class LifecycleView extends Div implements BeforeEnterObserver {


    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
        log.info("BeforeEnterEvent");
    }
}
