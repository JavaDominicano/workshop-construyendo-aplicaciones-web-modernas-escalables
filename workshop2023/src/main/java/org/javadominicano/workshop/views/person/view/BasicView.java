package org.javadominicano.workshop.views.person.view;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.KeyModifier;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.grid.dataview.GridListDataView;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.data.renderer.LocalDateRenderer;
import com.vaadin.flow.data.renderer.Renderer;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.function.SerializableConsumer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.theme.lumo.LumoIcon;
import com.vaadin.flow.theme.lumo.LumoUtility;
import jakarta.annotation.security.PermitAll;
import org.javadominicano.workshop.data.entity.Person;
import org.javadominicano.workshop.data.service.PersonService;
import org.javadominicano.workshop.views.MainLayoutDrawer;
import org.javadominicano.workshop.views.person.form.BasicForm;

/**
 * @author me@fredpena.dev
 * @created 22/11/2023  - 10:57
 */

@PageTitle("Person detail")
@Route(value = "persons-basic", layout = MainLayoutDrawer.class)
@RouteAlias(value = "basic-view")
@PermitAll
@Uses(Icon.class)
public class BasicView extends Div {
    private Grid<Person> grid;
    private GridListDataView<Person> dataView;
    private final transient PersonService personService;
    private final transient BasicForm form;
    private final TextField filter = new TextField();
    private final Button newObject = new Button("New Person", VaadinIcon.PLUS.create());

    public BasicView(PersonService personService) {
        this.personService = personService;
        this.form = new BasicForm(personService);

        setSizeFull();
        addClassNames("list-view");

        VerticalLayout layout = new VerticalLayout(createTopBar(), createGrid());
        layout.setSizeFull();
        add(layout);
    }

    public HorizontalLayout createTopBar() {
        filter.setPlaceholder("Search...");
        // A shortcut to focus on the textField by pressing ctrl + F
        filter.addFocusShortcut(Key.KEY_F, KeyModifier.CONTROL);
        filter.setPrefixComponent(new Icon(VaadinIcon.SEARCH));
        filter.setValueChangeMode(ValueChangeMode.EAGER);
        filter.setClearButtonVisible(true);
        // Apply the filter to grid's data provider. TextField value is never
        filter.addValueChangeListener(event -> dataView.refreshAll());

        newObject.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        newObject.addClickListener(event -> {
            Dialog dialog = form.createDialog("New Person", null, this::refreshGrid);
            dialog.open();
        });

        final HorizontalLayout topLayout = new HorizontalLayout();
        topLayout.setWidth("100%");
        topLayout.add(filter);
        topLayout.add(newObject);
        topLayout.setVerticalComponentAlignment(FlexComponent.Alignment.START, filter);
        topLayout.expand(filter);
        return topLayout;
    }

    private void edit(Person r) {
        Dialog dialog = form.createDialog("Edit Person", r, this::refreshGrid);
        dialog.open();
    }

    private void refreshGrid() {
        grid.getDataProvider().refreshAll();
    }


    private Component createGrid() {
        grid = new Grid<>(Person.class, false);
        grid.addColumn(Person::getFirstName).setHeader("First name").setAutoWidth(true);
        grid.addColumn(Person::getLastName).setHeader("Last name").setAutoWidth(true);
        grid.addColumn(Person::getEmail).setHeader("Email").setAutoWidth(true);
        grid.addColumn(Person::getPhone).setHeader("Phone").setAutoWidth(true);
        grid.addColumn(new LocalDateRenderer<>(Person::getDateOfBirth, "MMM d, YYYY")).setHeader("Birth date").setAutoWidth(true);
        grid.addColumn(Person::getOccupation).setHeader("Occupation").setAutoWidth(true);
        grid.addColumn(Person::getRole).setHeader("Role").setAutoWidth(true);
        grid.addColumn(c -> c.isImportant() ? "Yes" : "No").setHeader("Is important").setAutoWidth(true);
        grid.addColumn(createActionButton(this::edit));


        grid.addThemeVariants(GridVariant.LUMO_ROW_STRIPES);
        grid.addClassNames(LumoUtility.Border.TOP, LumoUtility.BorderColor.CONTRAST_10);

        dataView = grid.setItems(personService.findAll().stream().sorted((s1, s2) -> s2.getCode().compareTo(s1.getCode())).toList());

        dataView.addFilter(person -> {
            String searchTerm = filter.getValue().trim();

            if (searchTerm.isEmpty()) return true;

            boolean matchesFirstName = matchesTerm(person.getFirstName(), searchTerm);
            boolean matchesLastName = matchesTerm(person.getLastName(), searchTerm);
            boolean matchesEmail = matchesTerm(person.getEmail(), searchTerm);
            boolean matchesPhone = matchesTerm(person.getPhone(), searchTerm);
            boolean matchesOccupation = matchesTerm(person.getOccupation(), searchTerm);
            boolean matchesRole = matchesTerm(person.getRole(), searchTerm);

            return matchesFirstName || matchesLastName || matchesEmail || matchesPhone || matchesOccupation || matchesRole;
        });

        return grid;
    }


    private static Renderer<Person> createActionButton(SerializableConsumer<Person> consumer) {
        return new ComponentRenderer<>(Button::new, (button, p) -> {
            button.addThemeVariants(ButtonVariant.LUMO_SMALL);
            button.addClickListener(e -> consumer.accept(p));
            button.setIcon(LumoIcon.EDIT.create());
        });
    }

    public static boolean matchesTerm(String value, String searchTerm) {
        if (value == null || value.isEmpty()) {
            return false;
        }

        return value.toLowerCase().contains(searchTerm.toLowerCase());
    }

}
