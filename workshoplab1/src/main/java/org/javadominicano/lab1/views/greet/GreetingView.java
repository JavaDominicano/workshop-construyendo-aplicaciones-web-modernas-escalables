package org.javadominicano.lab1.views.greet;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.WildcardParameter;

/**
 * @author me@fredpena.dev
 * @created 24/11/2023  - 17:17
 */
@Route(value = "greet")
public class GreetingView extends Div implements HasUrlParameter<String> {

    //Defining Route Parameters
//    @Override
//    public void setParameter(BeforeEvent event, String parameter) {
//        setText(String.format("Hello, %s!", parameter));
//    }

    //Optional Route Parameters
//    @Override
//    public void setParameter(BeforeEvent event, @OptionalParameter String parameter) {
//        if (parameter == null) {
//            setText("Welcome anonymous.");
//        } else {
//            setText(String.format("Welcome %s.", parameter));
//        }
//    }

    //Wildcard Route Parameters
    @Override
    public void setParameter(BeforeEvent event, @WildcardParameter String parameter) {
        if (parameter.isEmpty()) {
            setText("Welcome anonymous.");
        } else {
            setText("Handling parameter %s.".formatted(parameter));
        }
    }
}