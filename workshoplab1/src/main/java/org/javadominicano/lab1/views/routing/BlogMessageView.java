package org.javadominicano.lab1.views.routing;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.Route;

/**
 * @author me@fredpena.dev
 * @created 24/11/2023  - 17:53
 */
@Route("blog-message")
public class BlogMessageView extends Div {
    public BlogMessageView() {
//        setText("No items found.");
        setText("No permission");
    }
}
