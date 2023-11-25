package org.javadominicano.lab1.views.routing;

import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.AfterNavigationObserver;
import com.vaadin.flow.router.Route;
import lombok.extern.slf4j.Slf4j;

/**
 * @author me@fredpena.dev
 * @created 24/11/2023  - 18:15
 */

@Slf4j
@Route(value = "side")
public class SideMenuView extends Div implements AfterNavigationObserver {
    Anchor blog = new Anchor("side", "Menu");

    public SideMenuView() {
        add(blog);
    }

    @Override
    public void afterNavigation(AfterNavigationEvent event) {
        log.info("afterNavigation");
        boolean active = event.getLocation()
                .getFirstSegment()
                .equals(blog.getHref());


        blog.getElement()
                .getClassList()
                .set("active", active);
    }
}