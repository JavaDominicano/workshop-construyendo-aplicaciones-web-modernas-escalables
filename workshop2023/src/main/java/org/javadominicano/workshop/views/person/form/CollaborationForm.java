package org.javadominicano.workshop.views.person.form;

import com.vaadin.collaborationengine.CollaborationBinder;
import com.vaadin.collaborationengine.UserInfo;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.server.VaadinSession;
import lombok.extern.slf4j.Slf4j;
import org.javadominicano.workshop.data.entity.Person;
import org.javadominicano.workshop.data.entity.User;
import org.javadominicano.workshop.data.service.PersonService;


/**
 * @author me@fredpena.dev
 * @created 22/11/2023  - 11:45
 */

@Slf4j
public class CollaborationForm extends AbstractForm {

    private final CollaborationBinder<Person> binder;

    public CollaborationForm(PersonService personService) {
        super(personService);

        var user = VaadinSession.getCurrent().getAttribute(User.class);

        var userInfo = new UserInfo(user.getUserId().toString(), user.getName());
        // Bind fields. This is where you'd define e.g. validation rules
        // Configure Form
        binder = new CollaborationBinder<>(Person.class, userInfo);

        // Bind fields. This where you'd define e.g. validation rules
        binder.bindInstanceFields(this);

    }

    public Dialog createDialog(String title, Person element, Runnable reload) {
        var topic = "";
        if (element != null && element.getCode() != null) {
            topic = "person/%s".formatted(element.getCode());
        }
        binder.setTopic(topic, () -> element);

        return create(title, element, reload);
    }


    @Override
    protected void updatedBinder() {
        var topic = "person/%s".formatted(element.getCode());
        binder.setTopic(topic, () -> element);
    }

    @Override
    protected void writeBean(Person bean) throws ValidationException {
        binder.writeBean(element);
    }
}
