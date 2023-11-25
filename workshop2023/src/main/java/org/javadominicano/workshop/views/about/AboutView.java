package org.javadominicano.workshop.views.about;

import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin;
import jakarta.annotation.security.PermitAll;
import org.javadominicano.workshop.views.MainLayoutDrawer;
import org.javadominicano.workshop.views.MainLayoutTabs;

@PageTitle("About")
@Route(value = "about", layout = MainLayoutDrawer.class)
@RouteAlias(value = "", layout = MainLayoutTabs.class)
@PermitAll
public class AboutView extends VerticalLayout {

    public AboutView() {
        setSpacing(false);

        Image img = new Image("images/java-dominicano.png", "java dominicano");
        img.setWidth("600px");
        add(img);

        H2 header = new H2("Java Dominicano");
        header.addClassNames(Margin.Top.XLARGE, Margin.Bottom.MEDIUM);
        add(header);
        add(new Paragraph("Comunidad de usuarios y desarrolladores Java de República Dominicana. 😎🌴"));

        setSizeFull();
        setJustifyContentMode(JustifyContentMode.CENTER);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        getStyle().set("text-align", "center");
    }

}
