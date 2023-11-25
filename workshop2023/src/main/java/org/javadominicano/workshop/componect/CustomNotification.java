package org.javadominicano.workshop.componect;

import com.vaadin.flow.component.HasLabel;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.data.binder.ValidationException;
import org.springframework.stereotype.Component;

/**
 * @author me@fredpena.dev
 * @created 22/11/2023  - 11:02
 */

@Component
public class CustomNotification {

    private void notification(Notification notification, String msg) {
        notification.setDuration(5000);
        notification.setPosition(Notification.Position.TOP_END);

        final Div text = new Div(new Text(msg));

        final Button closeButton = new Button(new Icon("lumo", "cross"));
        closeButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY_INLINE);
        closeButton.getElement().setAttribute("aria-label", "Close");
        closeButton.addClickListener(event -> notification.close());

        final HorizontalLayout layout = new HorizontalLayout(text, closeButton);
        layout.setAlignItems(FlexComponent.Alignment.CENTER);

        notification.add(layout);
        notification.open();
    }

    public void notificationError() {
        notificationError("There was an error in the transaction.");
    }

    public void notificationError(String msg) {
        final Notification notification = new Notification();
        notification.addThemeVariants(NotificationVariant.LUMO_ERROR);

        notification(notification, msg);
    }

    public void notificationError(ValidationException validationException) {
        notificationError(null, validationException);
    }

    public void notificationError(String msg, ValidationException validationException) {
        final Notification notification = new Notification();
        notification.addThemeVariants(NotificationVariant.LUMO_ERROR);

        if (msg != null) {
            notification(notification, msg);
        }

        validationException.getFieldValidationErrors().forEach(err ->
                err.getMessage().ifPresent(msg2 -> {
                    String label = ((HasLabel) err.getBinding().getField()).getLabel();

                    notificationError(label + " -> " + msg2);
                })
        );

    }

    public void notificationSuccess() {
        notificationSuccess("The transaction was successful.");
    }

    public void notificationSuccess(String msg) {
        final Notification notification = new Notification();
        notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);

        notification(notification, msg);
    }

    public void notificationWarning(String msg) {
        final Notification notification = new Notification();
        notification.addThemeVariants(NotificationVariant.LUMO_PRIMARY);

        notification(notification, msg);
    }
}
