package org.javadominicano.workshop.views.person;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.CheckboxGroup;
import com.vaadin.flow.component.combobox.MultiSelectComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.theme.lumo.LumoUtility;
import jakarta.persistence.criteria.*;
import jakarta.validation.constraints.NotNull;
import org.javadominicano.workshop.data.entity.Person;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author me@fredpena.dev
 * @created 22/11/2023  - 22:57
 */
public class Filters extends Div implements Specification<Person> {
    private final TextField name = new TextField("Person");
    private final TextField phone = new TextField("Contact");
    private final TextField occupation = new TextField("Occupation");
    private final DatePicker startDate = new DatePicker("Date of Birth");
    private final DatePicker endDate = new DatePicker();
    private final MultiSelectComboBox<String> roles = new MultiSelectComboBox<>("Role");
    private final CheckboxGroup<Boolean> importants = new CheckboxGroup<>("Important");

    public Filters(Runnable onSearch) {
        setWidthFull();
        addClassNames(LumoUtility.Padding.Horizontal.LARGE, LumoUtility.Padding.Vertical.MEDIUM,
                LumoUtility.BoxSizing.BORDER);
        name.setPlaceholder("First or last name");
        phone.setPlaceholder("Email or phone");


        roles.setItems("Worker", "Supervisor", "Manager", "External");

        importants.setItemLabelGenerator(l -> StringUtils.capitalize(l.toString()));
        importants.setItems(Boolean.TRUE, Boolean.FALSE);
        importants.addClassName("double-width");

        Arrays.asList(name, phone, occupation).forEach(c -> {
            c.setValueChangeMode(ValueChangeMode.EAGER);
            c.addValueChangeListener(e -> onSearch.run());
            c.setClearButtonVisible(true);
        });

        Arrays.asList(startDate, endDate).forEach(c -> {
            c.addValueChangeListener(e -> onSearch.run());
            c.setClearButtonVisible(true);
        });

        roles.addValueChangeListener(e -> onSearch.run());
        roles.setClearButtonVisible(true);
        importants.addValueChangeListener(e -> onSearch.run());

        Button resetBtn = new Button("Reset");
        resetBtn.addThemeVariants(ButtonVariant.LUMO_ERROR);
        resetBtn.addClickListener(e -> {
            name.clear();
            phone.clear();
            startDate.clear();
            endDate.clear();
            occupation.clear();
            roles.clear();
            importants.clear();
            onSearch.run();
        });


        importants.getStyle().set("margin-inline-end", "auto");

        VerticalLayout header = new VerticalLayout();
        header.addClassNames(LumoUtility.Margin.NONE, LumoUtility.Padding.NONE);

        HorizontalLayout layout = new HorizontalLayout(name, phone, createDateRangeFilter(), occupation, roles, importants, resetBtn);
        layout.addClassNames(LumoUtility.FlexWrap.WRAP, LumoUtility.Padding.MEDIUM, LumoUtility.AlignItems.BASELINE);

        header.add(layout);

        add(header);
    }

    private Component createDateRangeFilter() {
        startDate.setPlaceholder("From");

        endDate.setPlaceholder("To");

        // For screen readers
        startDate.setAriaLabel("From date");
        endDate.setAriaLabel("To date");

        FlexLayout dateRangeComponent = new FlexLayout(startDate, new Text(" – "), endDate);
        dateRangeComponent.setAlignItems(FlexComponent.Alignment.BASELINE);
        dateRangeComponent.addClassName(LumoUtility.Gap.XSMALL);

        return dateRangeComponent;
    }

    @Override
    public Predicate toPredicate(@NotNull Root<Person> root, @NotNull CriteriaQuery<?> query, @NotNull CriteriaBuilder builder) {
        Order order = builder.desc(root.get("code"));
        query.orderBy(order);


        List<Predicate> predicates = new ArrayList<>();

        if (!name.isEmpty()) {
            String lowerCaseFilter = name.getValue().toLowerCase();
            Predicate firstNameMatch = builder.like(builder.lower(root.get("firstName")),
                    lowerCaseFilter + "%");

            Predicate lastNameMatch = builder.like(builder.lower(root.get("lastName")),
                    lowerCaseFilter + "%");
            predicates.add(builder.or(firstNameMatch, lastNameMatch));
        }
        if (!phone.isEmpty()) {
            String ignore = "- ()";

            String lowerCaseFilter = ignoreCharacters(ignore, phone.getValue().toLowerCase());
            Predicate phoneMatch = builder.like(
                    ignoreCharacters(ignore, builder, builder.lower(root.get("phone"))),
                    "%" + lowerCaseFilter + "%");

            Predicate emailMatch = builder.like(builder.lower(root.get("email")),
                    lowerCaseFilter + "%");

            predicates.add(builder.or(phoneMatch, emailMatch));

        }
        if (startDate.getValue() != null) {
            predicates.add(builder.greaterThanOrEqualTo(root.get("dateOfBirth"),
                    builder.literal(startDate.getValue())));
        }
        if (endDate.getValue() != null) {
            predicates.add(builder.greaterThanOrEqualTo(builder.literal(endDate.getValue()),
                    root.get("dateOfBirth")));
        }

        if (!occupation.isEmpty()) {
            String lowerCaseFilter = occupation.getValue().toLowerCase();
            Predicate occupationMatch = builder.like(builder.lower(root.get("occupation")),
                    lowerCaseFilter + "%");
            predicates.add(occupationMatch);
        }

        if (!roles.isEmpty()) {
            List<Predicate> rolePredicates = new ArrayList<>();
            for (String role : roles.getValue()) {
                rolePredicates.add(builder.equal(builder.literal(role), root.get("role")));
            }
            predicates.add(builder.or(rolePredicates.toArray(Predicate[]::new)));
        }

        if (!importants.isEmpty()) {
            List<Predicate> rolePredicates = new ArrayList<>();
            for (Boolean important : importants.getValue()) {
                rolePredicates.add(builder.equal(builder.literal(important), root.get("important")));
            }
            predicates.add(builder.or(rolePredicates.toArray(Predicate[]::new)));
        }
        return builder.and(predicates.toArray(Predicate[]::new));
    }

    private String ignoreCharacters(String characters, String in) {
        String result = in;
        for (int i = 0; i < characters.length(); i++) {
            result = result.replace("" + characters.charAt(i), "");
        }
        return result;
    }

    private Expression<String> ignoreCharacters(String characters, CriteriaBuilder criteriaBuilder,
                                                Expression<String> inExpression) {
        Expression<String> expression = inExpression;
        for (int i = 0; i < characters.length(); i++) {
            expression = criteriaBuilder.function("replace", String.class, expression,
                    criteriaBuilder.literal(characters.charAt(i)), criteriaBuilder.literal(""));
        }
        return expression;
    }

}
