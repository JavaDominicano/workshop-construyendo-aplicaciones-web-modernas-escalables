package org.javadominicano.lab1.views.routing;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.*;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;

/**
 * @author me@fredpena.dev
 * @created 24/11/2023  - 17:26
 */
@Slf4j
@Route(value = "signup")
public class SignupView extends Div implements BeforeLeaveObserver, BeforeEnterObserver {

    private final Random random = new Random();

    public SignupView() {
        Button exit = new Button("Exit");
        exit.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        exit.addClickListener(event -> UI.getCurrent().navigate(LifecycleView.class));

        add(exit);
    }


    @Override
    public void beforeLeave(BeforeLeaveEvent event) {
        log.info("BeforeLeaveEvent");
        if (hasChanges()) {
            BeforeLeaveEvent.ContinueNavigationAction action = event.postpone();
            ConfirmDialog confirmDialog = new ConfirmDialog();
            confirmDialog.setText("Your form has changes! Are you sure you want to leave?");
            confirmDialog.setCancelable(true);
            confirmDialog.addConfirmListener(__ -> action.proceed());
            confirmDialog.addCancelListener(__ -> action.cancel());
            confirmDialog.open();
        }
    }


    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
        log.info("BeforeEnterEvent");
    }

    private boolean hasChanges() {
        return true;
        //return random.nextBoolean();
    }
}