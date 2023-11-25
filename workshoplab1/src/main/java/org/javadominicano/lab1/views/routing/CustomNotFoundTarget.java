package org.javadominicano.lab1.views.routing;

import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.ErrorParameter;
import com.vaadin.flow.router.NotFoundException;
import com.vaadin.flow.router.ParentLayout;
import jakarta.servlet.http.HttpServletResponse;
import org.javadominicano.lab1.views.MainLayout;

/**
 * @author me@fredpena.dev
 * @created 24/11/2023  - 18:27
 */
@ParentLayout(MainLayout.class)
public class CustomNotFoundTarget extends RouteNotFoundError {

    @Override
    public int setErrorParameter(BeforeEnterEvent event, ErrorParameter<NotFoundException> parameter) {
        getElement().setText(
                "My custom not found class!");
        return HttpServletResponse.SC_NOT_FOUND;
    }
}