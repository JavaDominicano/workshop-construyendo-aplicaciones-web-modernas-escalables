package org.javadominicano.workshop.views.person.view;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.data.renderer.LitRenderer;
import com.vaadin.flow.dom.Style;
import com.vaadin.flow.spring.data.VaadinSpringDataHelpers;
import com.vaadin.flow.theme.lumo.LumoUtility;
import org.javadominicano.workshop.data.entity.Person;
import org.javadominicano.workshop.data.service.PersonService;
import org.javadominicano.workshop.views.person.Filters;
import org.springframework.data.domain.PageRequest;
import org.vaadin.lineawesome.LineAwesomeIcon;

import java.time.format.DateTimeFormatter;
import java.util.Random;

/**
 * @author me@fredpena.dev
 * @created 23/11/2023  - 19:56
 */
public abstract class AbstractView extends Div {

    private Grid<Person> grid;
    private final Filters filters;
    private final transient PersonService personService;
    private static final Random random = new Random();

    protected abstract Dialog createDialog(String title, Person element, Runnable reload);

    protected AbstractView(PersonService personService) {
        this.personService = personService;
        addClassName("form-view");

        Button addBtn = new Button("New Person", VaadinIcon.PLUS.create());
        addBtn.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        addBtn.addClickListener(event -> {
            Dialog dialog = createDialog("New Person", null, this::refreshGrid);
            dialog.open();
        });

        filters = new Filters(this::refreshGrid);
        VerticalLayout layout = new VerticalLayout(createFilters(), filters, addBtn, createGrid());
        layout.setSizeFull();

        setSizeFull();
        add(layout);
    }

    private HorizontalLayout createFilters() {
        HorizontalLayout layoutFilters = new HorizontalLayout();
        layoutFilters.setWidthFull();
        layoutFilters.addClassNames(LumoUtility.Padding.MEDIUM, LumoUtility.BoxSizing.BORDER, LumoUtility.AlignItems.CENTER);

        filters.setVisible(false);

        Icon icon = new Icon("lumo", "plus");
        Span filtersHeading = new Span("Filters");
        layoutFilters.add(icon, filtersHeading);
        layoutFilters.setFlexGrow(1, filtersHeading);
        icon.addClickListener(event -> {
            filters.setVisible(!filters.isVisible());
            icon.getElement().setAttribute("icon", filters.isVisible() ? "lumo:minus" : "lumo:plus");
        });

        return layoutFilters;
    }


    private void refreshGrid() {
        grid.getDataProvider().refreshAll();
    }


    private Component createGrid() {
        grid = new Grid<>(Person.class, false);
        grid.setSelectionMode(Grid.SelectionMode.SINGLE);

        grid.addColumn(createRenderer()).setAutoWidth(true);

        grid.setItems(query -> personService.list(
                PageRequest.of(query.getPage(), query.getPageSize(), VaadinSpringDataHelpers.toSpringDataSort(query)),
                filters).stream());

        grid.asSingleSelect().addValueChangeListener(event -> {
            if (event.getValue() != null) {
                Dialog dialog = createDialog("Edit Person", event.getValue(), this::refreshGrid);
                dialog.open();
            }
        });

        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER, GridVariant.LUMO_NO_ROW_BORDERS);
        grid.addClassNames(LumoUtility.BoxShadow.XSMALL, LumoUtility.BorderRadius.MEDIUM);

        grid.setHeightFull();

        return grid;
    }


    private LitRenderer<Person> createRenderer() {
        return new ComponentRenderer<>(person -> {
            HorizontalLayout layout = new HorizontalLayout();
            layout.setWidthFull();
            layout.setAlignItems(FlexComponent.Alignment.START);
            layout.addClassNames(LumoUtility.BoxShadow.SMALL, LumoUtility.BorderRadius.MEDIUM, LumoUtility.Padding.NONE,
                    LumoUtility.Padding.Top.SMALL, LumoUtility.Margin.NONE, LumoUtility.Margin.Bottom.MEDIUM,
                    LumoUtility.Background.BASE);

            HorizontalLayout nameRenderer = new HorizontalLayout();
            nameRenderer.setAlignItems(FlexComponent.Alignment.CENTER);
            nameRenderer.addClassNames(LumoUtility.Padding.SMALL, LumoUtility.Margin.NONE);
            Avatar avatar = new Avatar(person.getFirstName(), "avatars/pexels-%s.jpg".formatted(random()));
            nameRenderer.add(avatar);

            Span name = new Span(person.getFirstName());
            name.addClassNames(LumoUtility.FontSize.XLARGE, LumoUtility.TextColor.HEADER, LumoUtility.FontWeight.BOLD);
            Span lastName = new Span(person.getLastName());
            lastName.addClassNames(LumoUtility.FontSize.LARGE, LumoUtility.TextColor.SECONDARY);

            VerticalLayout layoutNameAndLastName = new VerticalLayout(name, lastName);
            layoutNameAndLastName.setAlignItems(FlexComponent.Alignment.START);
            layoutNameAndLastName.addClassNames(LumoUtility.Padding.SMALL, LumoUtility.Margin.NONE);

            nameRenderer.add(layoutNameAndLastName);

            VerticalLayout firstColumn = new VerticalLayout(nameRenderer);
            firstColumn.getThemeList().add("spacing-xl");
            firstColumn.setAlignItems(FlexComponent.Alignment.START);
            firstColumn.addClassNames(LumoUtility.Padding.NONE, LumoUtility.Margin.NONE, LumoUtility.FlexWrap.WRAP);
            firstColumn.setMinWidth("250px");
            firstColumn.setMaxWidth("250px");


            VerticalLayout layoutEmailAndPhone = new VerticalLayout();
            layoutEmailAndPhone.setAlignItems(FlexComponent.Alignment.START);
            layoutEmailAndPhone.addClassNames(LumoUtility.Padding.SMALL, LumoUtility.Margin.NONE);

            Span spanEmail = new Span(person.getEmail());
            spanEmail.addClassNames(LumoUtility.Padding.Left.MEDIUM);
            Span email = new Span(VaadinIcon.ENVELOPE.create(), spanEmail);
            email.getStyle().setDisplay(Style.Display.FLEX);
            email.addClassNames(LumoUtility.AlignItems.CENTER);
            email.addClassNames(LumoUtility.TextColor.SECONDARY);

            Span spanPhone = new Span(person.getPhone());
            spanPhone.addClassNames(LumoUtility.Padding.Left.MEDIUM);
            Span phone = new Span(VaadinIcon.PHONE.create(), spanPhone);
            phone.getStyle().setDisplay(Style.Display.FLEX);
            phone.addClassNames(LumoUtility.AlignItems.CENTER);
            phone.addClassNames(LumoUtility.TextColor.SECONDARY);

            layoutEmailAndPhone.add(email, phone);

            VerticalLayout secondColumn = new VerticalLayout(layoutEmailAndPhone);
            secondColumn.getThemeList().add("spacing-xl");
            secondColumn.setAlignItems(FlexComponent.Alignment.START);
            secondColumn.addClassNames(LumoUtility.Padding.NONE, LumoUtility.Margin.NONE, LumoUtility.FlexWrap.WRAP);
            secondColumn.setMinWidth("300px");
            secondColumn.setMaxWidth("300px");

            Span spanLastDateOfBirth = new Span(DateTimeFormatter.ofPattern("MMM d, yyyy").format(person.getDateOfBirth()));
            spanLastDateOfBirth.addClassNames(LumoUtility.Padding.Left.MEDIUM);
            Span lastDateOfBirth = new Span(VaadinIcon.CALENDAR_USER.create(), spanLastDateOfBirth);
            lastDateOfBirth.addClassNames(LumoUtility.TextColor.SECONDARY);

            Span spanLastOccupation = new Span(person.getOccupation());
            spanLastOccupation.addClassNames(LumoUtility.Padding.Left.MEDIUM);
            Span lastOccupation = new Span(VaadinIcon.USER_CARD.create(), spanLastOccupation);
            lastOccupation.addClassNames(LumoUtility.TextColor.SECONDARY);

            Span spanLastRole = new Span(person.getRole());
            spanLastRole.addClassNames(LumoUtility.Padding.Left.MEDIUM);
            Span lastRole = new Span(LineAwesomeIcon.USER_COG_SOLID.create(), spanLastRole);
            lastRole.addClassNames(LumoUtility.TextColor.SECONDARY);

            VerticalLayout thirdColumn = new VerticalLayout(lastDateOfBirth, lastOccupation, lastRole);
            thirdColumn.setHeightFull();
            thirdColumn.addClassNames(LumoUtility.FlexWrap.WRAP);
            thirdColumn.setAlignItems(FlexComponent.Alignment.START);


            layout.add(createImportant(person.isImportant()), firstColumn, secondColumn, thirdColumn);

            return layout;
        });
    }

    private static Span createImportant(boolean isImportant) {
        Span span = new Span(isImportant ? "Important" : "No important");
        span.addClassNames(LumoUtility.Margin.Right.LARGE, LumoUtility.Margin.Top.MEDIUM, LumoUtility.Margin.Left.MEDIUM, LumoUtility.Padding.SMALL);
        span.setMinWidth("100px");
        if (isImportant) {
            span.getElement().getThemeList().add("badge success");
        } else {
            span.getElement().getThemeList().add("badge error");
        }
        return span;
    }

    private static int random() {
        return random.nextInt(9) + 1;
    }
}
