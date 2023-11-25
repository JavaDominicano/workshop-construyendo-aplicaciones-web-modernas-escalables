package org.javadominicano.workshop.views.person.form;

import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.javadominicano.workshop.data.entity.Person;
import org.javadominicano.workshop.data.service.PersonService;


/**
 * @author me@fredpena.dev
 * @created 22/11/2023  - 11:45
 */

@Slf4j
public class BasicForm extends AbstractForm {

    private final Binder<Person> binder;

    public BasicForm(PersonService personService) {
        super(personService);

        // Bind fields. This is where you'd define e.g. validation rules
        // Configure Form
        binder = new BeanValidationBinder<>(Person.class);

        // Bind fields. This where you'd define e.g. validation rules
        binder.bindInstanceFields(this);

    }

    public Dialog createDialog(String title, Person element, Runnable reload) {
        binder.setBean(element);

        return create(title, element, reload);
    }

    @Override
    protected void updatedBinder() {
        binder.setBean(element);
    }

    @Override
    protected void writeBean(Person bean) throws ValidationException {
        binder.writeBean(element);

    }


}
