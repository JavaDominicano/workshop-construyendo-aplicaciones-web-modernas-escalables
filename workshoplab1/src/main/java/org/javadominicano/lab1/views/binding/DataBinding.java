package org.javadominicano.lab1.views.binding;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.router.Route;
import org.javadominicano.lab1.views.MainLayout;

/**
 * @author me@fredpena.dev
 * @created 24/11/2023  - 18:40
 */
@Route(value = "binding-overview", layout = MainLayout.class)
public class DataBinding extends VerticalLayout {

    public DataBinding() {
        // Data model
        BusinessPerson person = new BusinessPerson();

        // Two editors: the actual editor and read-only display
        PersonEditor editor1 = new PersonEditor(false);
        PersonEditor editor2 = new PersonEditor(true);

        // The editor and buttons
        VerticalLayout layout1 = new VerticalLayout();
        HorizontalLayout buttons = new HorizontalLayout();
        Button saveButton = new Button("Save");
        saveButton.addClickListener(click -> {
            try {
                editor1.getBinder().writeBean(person);
                editor2.getBinder().readBean(person);
            } catch (ValidationException e) {
            }
        });
        Button resetButton = new Button("Reset");
        resetButton.addClickListener(click -> {
            editor1.getBinder().readBean(person);
        });
        buttons.add(saveButton, resetButton);
        layout1.add(editor1, buttons);

        VerticalLayout layout2 = new VerticalLayout();
        layout2.add(editor2);

        // Show the two editors side-by-side
        HorizontalLayout hlayout = new HorizontalLayout();
        hlayout.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.START);
        hlayout.add(layout1, layout2);
        add(hlayout);
    }

}