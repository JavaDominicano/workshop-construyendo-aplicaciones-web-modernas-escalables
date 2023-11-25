package org.javadominicano.workshop.views.person.view;

import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import jakarta.annotation.security.PermitAll;
import org.javadominicano.workshop.data.entity.Person;
import org.javadominicano.workshop.data.service.PersonService;
import org.javadominicano.workshop.views.MainLayoutTabs;
import org.javadominicano.workshop.views.person.form.CollaborationForm;

/**
 * @author me@fredpena.dev
 * @created 22/11/2023  - 13:30
 */

@PageTitle("Person detail")
@Route(value = "persons-collaboration", layout = MainLayoutTabs.class)
@RouteAlias(value = "collaboration-view")
@PermitAll
@Uses(Icon.class)
public class CollaborationView extends AbstractView {

    private final transient CollaborationForm form;


    public CollaborationView(PersonService personService) {
        super(personService);
        this.form = new CollaborationForm(personService);

    }

    @Override
    protected Dialog createDialog(String title, Person element, Runnable reload) {
        return form.createDialog(title, element, reload);
    }


}
