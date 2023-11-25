package org.javadominicano.workshop.views.person.form;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.theme.lumo.LumoUtility;
import jakarta.persistence.Column;
import lombok.extern.slf4j.Slf4j;
import org.javadominicano.workshop.componect.CustomNotification;
import org.javadominicano.workshop.data.entity.Person;
import org.javadominicano.workshop.data.service.PersonService;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


/**
 * @author me@fredpena.dev
 * @created 23/11/2023  - 19:15
 */

@Slf4j
public abstract class AbstractForm {

    private final TextField firstName = new TextField("First Name");
    private final TextField lastName = new TextField("Last Name");
    private final EmailField email = new EmailField("Email");
    private final TextField phone = new TextField("Phone Number");
    private final DatePicker dateOfBirth = new DatePicker("Birthday");
    private final TextField occupation = new TextField("Occupation");
    private final Checkbox important = new Checkbox("Is important?");
    protected final ComboBox<String> role = new ComboBox<>("Role");

    private final Button delete = new Button("Delete");
    private final Button cancel = new Button("Cancel");
    private final Button save = new Button("Save");
    private final PersonService personService;
    private final CustomNotification notification;
    protected Person element;
    private Runnable deleted;

    protected abstract void updatedBinder();

    protected abstract void writeBean(Person bean) throws ValidationException;

    protected AbstractForm(PersonService personService) {
        this.personService = personService;
        this.notification = new CustomNotification();

        delete.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_ERROR);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        role.setItems("Worker", "Supervisor", "Manager", "External");

        Map<String, Component> components = new HashMap<>();
        components.put("firstName", firstName);
        components.put("lastName", lastName);
        components.put("email", email);
        components.put("phone", phone);
        components.put("dateOfBirth", dateOfBirth);
        components.put("role", role);
        configureProperties(components, Person.class);


        save.addClickListener(this::saveOrUpdate);

        delete.addClickListener(this::delete);

    }

    private void saveOrUpdate(ClickEvent<Button> buttonClickEvent) {
        try {
            if (this.element == null) {
                this.element = new Person();
            }

            writeBean(this.element);

            var dialog = new ConfirmDialog();
            dialog.setHeader("Unsaved changes");
            dialog.setText("There are unsaved changes. Do you want to discard or save them?");

            dialog.setCancelable(true);
            dialog.addCancelListener(event -> log.debug("Canceled"));

            dialog.setRejectable(true);
            dialog.setRejectText("Discard");
            dialog.addRejectListener(event -> log.debug("Discarded"));

            dialog.setConfirmText("Save");
            dialog.addConfirmListener(event -> {
                personService.update(this.element);
                notification.notificationSuccess();
                updatedBinder();
            });

            dialog.open();


        } catch (ValidationException validationException) {
            notification.notificationError(validationException);
        }
    }

    private void delete(ClickEvent<Button> buttonClickEvent) {

        var dialog = new ConfirmDialog();
        dialog.setHeader("Deleting record");
        dialog.setText("You are deleting some record. Do you want to discard them or continue?");

        dialog.setCancelable(true);
        dialog.addCancelListener(event -> log.debug("Canceled"));

        dialog.setRejectable(true);
        dialog.setRejectText("Discard");
        dialog.addRejectListener(event -> log.debug("Discarded"));

        dialog.setConfirmText("Delete");
        dialog.setConfirmButtonTheme("error primary");
        dialog.addConfirmListener(event -> {
            personService.delete(this.element.getCode());
            notification.notificationError("The record was deleted");
            deleted.run();
        });

        dialog.open();

    }

    protected Dialog create(String title, Person element, Runnable reload) {
        this.element = element;
        delete.setVisible(element != null);

        var dialog = new Dialog();
        dialog.setMaxWidth("1024px");
        dialog.setCloseOnOutsideClick(true);
        dialog.setDraggable(true);
        dialog.setResizable(true);
        dialog.setOpened(true);
        dialog.setHeaderTitle(title);
        dialog.add(createFormLayout());
        dialog.getFooter().add(createButtonLayout());

        dialog.addDialogCloseActionListener(event -> {
            dialog.close();
            reload.run();
        });

        cancel.addClickListener(event -> {
            dialog.close();
            reload.run();
        });

        deleted = () -> {
            dialog.close();
            reload.run();
        };

        return dialog;
    }

    private Component createFormLayout() {
        var formLayout = new FormLayout();
        formLayout.addClassNames(LumoUtility.Padding.SMALL);

        formLayout.add(firstName, lastName, email, phone, dateOfBirth, occupation, role, important);
        return formLayout;
    }

    private Component createButtonLayout() {
        var deleteDiv = new Div();
        deleteDiv.getStyle().set("margin-inline-end", "auto");
        deleteDiv.add(delete);

        var buttonLayout = new HorizontalLayout();
        buttonLayout.setWidthFull();
        buttonLayout.addClassNames(LumoUtility.Padding.MEDIUM, LumoUtility.FlexWrap.WRAP, LumoUtility.JustifyContent.CENTER);
        buttonLayout.add(deleteDiv, cancel, save);
        buttonLayout.setAlignItems(FlexComponent.Alignment.END);
        return buttonLayout;
    }

    private void configureProperties(Map<String, Component> components, Class<?> clazz) {
        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(Column.class)) {
                final Optional<Component> component = components.entrySet().stream()
                        .filter(f -> f.getKey().equals(field.getName()))
                        .map(Map.Entry::getValue).findFirst();

                if (component.isPresent()) {
                    final Column column = field.getAnnotation(Column.class);

                    component.get().getElement().setProperty("maxlength", column.length());
                }
            }
        }
        components.forEach((key, value) -> value.getElement().setProperty("clearButtonVisible", true));
    }
}
