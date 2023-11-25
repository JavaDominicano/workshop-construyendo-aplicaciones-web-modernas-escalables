package org.javadominicano.lab1.views.binding;

import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;

/**
 * @author me@fredpena.dev
 * @created 24/11/2023  - 18:42
 */
public class PersonEditor extends FormLayout {
    private Binder<BusinessPerson> binder;

    public PersonEditor(boolean readOnly) {
        // For binding the form to the data model
        binder = new Binder<>(BusinessPerson.class);

        TextField nameField = new TextField();
        addFormItem(nameField, "Name");
        binder.forField(nameField)
                .withValidator(name -> name.length() > 3, "Name must contain at least three characters")
                .bind(BusinessPerson::getName, BusinessPerson::setName);
        nameField.setReadOnly(readOnly);

        TextField titleField = new TextField();
        addFormItem(titleField, "Title");
        binder.forField(titleField)
                .bind(BusinessPerson::getTitle, BusinessPerson::setTitle);
        titleField.setReadOnly(readOnly);
    }

    public Binder<BusinessPerson> getBinder() {
        return binder;
    }
}