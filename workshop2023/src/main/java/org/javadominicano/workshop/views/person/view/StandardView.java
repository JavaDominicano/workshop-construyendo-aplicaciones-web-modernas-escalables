package org.javadominicano.workshop.views.person.view;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.data.renderer.LitRenderer;
import com.vaadin.flow.data.renderer.LocalDateRenderer;
import com.vaadin.flow.data.renderer.Renderer;
import com.vaadin.flow.function.SerializableConsumer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.spring.data.VaadinSpringDataHelpers;
import com.vaadin.flow.theme.lumo.LumoIcon;
import com.vaadin.flow.theme.lumo.LumoUtility;
import jakarta.annotation.security.PermitAll;
import org.javadominicano.workshop.data.entity.Person;
import org.javadominicano.workshop.data.service.PersonService;
import org.javadominicano.workshop.views.MainLayoutDrawer;
import org.javadominicano.workshop.views.person.Filters;
import org.javadominicano.workshop.views.person.form.BasicForm;
import org.springframework.data.domain.PageRequest;

import java.util.Random;

/**
 * @author me@fredpena.dev
 * @created 22/11/2023  - 10:57
 */

@PageTitle("Person detail")
@Route(value = "persons-standard", layout = MainLayoutDrawer.class)
@RouteAlias(value = "view-standard")
@PermitAll
@Uses(Icon.class)
public class StandardView extends Div {
    private Grid<Person> grid;
    private final Filters filters;
    private final transient PersonService personService;
    private final transient BasicForm form;
    private static final Random random = new Random();

    public StandardView(PersonService personService) {
        this.personService = personService;
        this.form = new BasicForm(personService);

        setSizeFull();
        addClassNames("list-view");

        Button addBtn = new Button("New Person", VaadinIcon.PLUS.create());
        addBtn.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        addBtn.addClickListener(event -> {
            Dialog dialog = form.createDialog("New Person", null, this::refreshGrid);
            dialog.open();
        });

        filters = new Filters(this::refreshGrid);
        VerticalLayout layout = new VerticalLayout(createMobileFilters(), filters, addBtn, createGrid());
        layout.setSizeFull();
        add(layout);
    }

    private HorizontalLayout createMobileFilters() {
        // Mobile version
        HorizontalLayout mobileFilters = new HorizontalLayout();
        mobileFilters.setWidthFull();
        mobileFilters.addClassNames(LumoUtility.Padding.MEDIUM, LumoUtility.BoxSizing.BORDER,
                LumoUtility.AlignItems.CENTER);
        mobileFilters.addClassName("mobile-filters");

        Icon mobileIcon = new Icon("lumo", "plus");
        Span filtersHeading = new Span("Filters");
        mobileFilters.add(mobileIcon, filtersHeading);
        mobileFilters.setFlexGrow(1, filtersHeading);
        mobileFilters.addClickListener(e -> {
            if (filters.getClassNames().contains("visible")) {
                filters.removeClassName("visible");
                mobileIcon.getElement().setAttribute("icon", "lumo:plus");
            } else {
                filters.addClassName("visible");
                mobileIcon.getElement().setAttribute("icon", "lumo:minus");
            }
        });
        return mobileFilters;
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
        grid.addColumn(createNameRenderer()).setHeader("Person").setAutoWidth(true);
        grid.addColumn(createContactRenderer()).setHeader("Contact").setAutoWidth(true);
        grid.addColumn(new LocalDateRenderer<>(Person::getDateOfBirth, "MMM d, YYYY")).setHeader("Birth date").setAutoWidth(true);
        grid.addColumn(Person::getOccupation).setHeader("Occupation").setAutoWidth(true);
        grid.addColumn(Person::getRole).setHeader("Role").setAutoWidth(true);
        grid.addComponentColumn(c -> createIcon(c.isImportant())).setHeader("Is important").setAutoWidth(true);
        grid.addColumn(createActionButton(this::edit));

        grid.setItems(query -> personService.list(
                PageRequest.of(query.getPage(), query.getPageSize(), VaadinSpringDataHelpers.toSpringDataSort(query)),
                filters).stream());
        grid.addThemeVariants(GridVariant.LUMO_ROW_STRIPES);
        grid.addClassNames(LumoUtility.Border.TOP, LumoUtility.BorderColor.CONTRAST_10);

        return grid;
    }


    private static Renderer<Person> createActionButton(SerializableConsumer<Person> consumer) {
        return new ComponentRenderer<>(Button::new, (button, p) -> {
            button.addThemeVariants(ButtonVariant.LUMO_SMALL);
            button.addClickListener(e -> consumer.accept(p));
            button.setIcon(LumoIcon.EDIT.create());
        });
    }

    private static Icon createIcon(boolean isImportant) {
        Icon icon;
        if (isImportant) {
            icon = createIcon(VaadinIcon.CHECK, "Yes");
            icon.getElement().getThemeList().add("badge success");
        } else {
            icon = createIcon(VaadinIcon.CLOSE_SMALL, "No");
            icon.getElement().getThemeList().add("badge error");
        }
        return icon;
    }

    private static Icon createIcon(VaadinIcon vaadinIcon, String label) {
        Icon icon = vaadinIcon.create();
        icon.getStyle().set("padding", "var(--lumo-space-xs");
        // Accessible label
        icon.getElement().setAttribute("aria-label", label);
        // Tooltip
        icon.getElement().setAttribute("title", label);
        return icon;
    }

    private static Renderer<Person> createNameRenderer() {
        return LitRenderer.<Person>of(
                        "<vaadin-horizontal-layout style=\"align-items: center;\" theme=\"spacing\">"
                        + "<vaadin-avatar img=\"${item.pictureUrl}\" name=\"${item.lastName}\" alt=\"User avatar\"></vaadin-avatar>"
                        + "  <vaadin-vertical-layout style=\"line-height: var(--lumo-line-height-m);\">"
                        + "    <span style=\"font-size: var(--lumo-font-size-xs); color: var(--lumo-primary-text-color);\">"
                        + "      ${item.firstName}" + "    </span>"
                        + "    <span style=\"font-size: var(--lumo-font-size-m); \">"
                        + "      ${item.lastName}" + "    </span>"
                        + "  </vaadin-vertical-layout>"
                        + "</vaadin-horizontal-layout>")
                .withProperty("pictureUrl", p -> "avatars/pexels-%s.jpg".formatted(random()))
                .withProperty("firstName", Person::getFirstName)
                .withProperty("lastName", Person::getLastName);
    }

    private static Renderer<Person> createContactRenderer() {
        return LitRenderer.<Person>of(
                        "<vaadin-vertical-layout style=\"width: 100%; font-size: var(--lumo-font-size-s); line-height: var(--lumo-line-height-m);\">"
                        + " <a href=\"mailto:${item.email}\" style=\"display: ${item.emailDisplay}; align-items: center;\">"
                        + "     <vaadin-icon icon=\"vaadin:${item.emailIcon}\" style=\"margin-inline-end: var(--lumo-space-xs); width: var(--lumo-icon-size-s); height: var(--lumo-icon-size-s);\"></vaadin-icon>"
                        + "     <span>${item.email}</span>"
                        + " </a>"
                        + " "
                        + " <a href=\"tel:${item.phone}\" style=\"display: ${item.phoneDisplay}; align-items: center;\">"
                        + "     <vaadin-icon icon=\"vaadin:${item.phoneIcon}\" style=\"margin-inline-end: var(--lumo-space-xs); width: var(--lumo-icon-size-s); height: var(--lumo-icon-size-s);\"></vaadin-icon>"
                        + "     <span>${item.phone}</span>"
                        + " </a>"
                        + "</vaadin-vertical-layout>")
                .withProperty("email", r -> r.getEmail() != null ? r.getEmail() : "")
                .withProperty("emailIcon", r -> r.getEmail() != null ? "envelope" : "")
                .withProperty("emailDisplay", r -> r.getEmail() != null ? "flex" : "none")
                .withProperty("phone", r -> r.getPhone() != null ? r.getPhone() : "")
                .withProperty("phoneIcon", r -> r.getPhone() != null ? "phone" : "")
                .withProperty("phoneDisplay", r -> r.getPhone() != null ? "flex" : "none");
    }


    private static int random() {
        return random.nextInt(9) + 1;
    }

}
