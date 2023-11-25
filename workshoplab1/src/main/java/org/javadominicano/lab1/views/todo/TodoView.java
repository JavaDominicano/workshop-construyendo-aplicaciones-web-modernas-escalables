package org.javadominicano.lab1.views.todo;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.javadominicano.lab1.views.MainLayout;

/**
 * @author me@fredpena.dev
 * @created 24/11/2023  - 02:42
 */
@PageTitle("Todo")
@Route(value = "todo", layout = MainLayout.class)
public class TodoView extends VerticalLayout {

    public TodoView() {
        VerticalLayout todosList = new VerticalLayout();
        TextField taskField = new TextField();
        Button addButton = new Button("Add");
        addButton.addClickListener(click -> {
            Checkbox checkbox = new Checkbox(taskField.getValue());
            todosList.addComponentAtIndex(0, checkbox);
            taskField.clear();
        });
        addButton.addClickShortcut(Key.ENTER);

        add(new H1("Java Dominicana Todo"), new HorizontalLayout(taskField, addButton), todosList);
    }
}