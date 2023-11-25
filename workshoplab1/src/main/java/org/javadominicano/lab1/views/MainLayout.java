package org.javadominicano.lab1.views;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Header;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.sidenav.SideNav;
import com.vaadin.flow.component.sidenav.SideNavItem;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.theme.lumo.LumoUtility;
import org.javadominicano.lab1.views.about.AboutView;
import org.javadominicano.lab1.views.helloworld.HelloWorldView;
import org.javadominicano.lab1.views.todo.TodoView;
import org.vaadin.lineawesome.LineAwesomeIcon;

/**
 * The main view is a top-level placeholder for other views.
 */
public class MainLayout extends AppLayout {

    private H2 viewTitle;

    public MainLayout() {
        // Use the drawer for the menu
        setPrimarySection(Section.DRAWER);
        // Put the menu in the drawer
        addDrawerContent();
        // Make the nav bar a header
        addToNavbar(true, createHeader());
    }

    private void addDrawerContent() {
        H1 appName = new H1("Demo");
        appName.addClassNames(LumoUtility.FontSize.LARGE, LumoUtility.Margin.NONE);

        Image logo = new Image("icons/icon.png", "Java Dominicano Logo");
        logo.setWidth("40px");

        // Have a drawer header with an application logo
        HorizontalLayout logoLayout = new HorizontalLayout(logo, appName);
        logoLayout.setAlignItems(FlexComponent.Alignment.CENTER);

        Header header = new Header(logoLayout);

        Scroller scroller = new Scroller(createNavigation());

        addToDrawer(header, scroller);
    }

    private SideNav createNavigation() {
        SideNav nav = new SideNav();

        nav.addItem(new SideNavItem("Hello World", HelloWorldView.class, LineAwesomeIcon.GLOBE_SOLID.create()));
        nav.addItem(new SideNavItem("Todo", TodoView.class, LineAwesomeIcon.CHECK_CIRCLE.create()));
        nav.addItem(new SideNavItem("About", AboutView.class, LineAwesomeIcon.FILE.create()));

        return nav;
    }

    private Component createHeader() {
        // Configure the header
        HorizontalLayout layout = new HorizontalLayout();
        layout.getThemeList().set("dark", true);
        layout.setWidthFull();
        layout.setAlignItems(FlexComponent.Alignment.CENTER);
        layout.addClassNames(LumoUtility.Height.XLARGE);

        // Have the drawer toggle button on the left
        DrawerToggle toggle = new DrawerToggle();
        toggle.setAriaLabel("Menu toggle");
        layout.add(toggle);

        // Placeholder for the title of the current view.
        // The title will be set after navigation.
        viewTitle = new H2();
        viewTitle.addClassNames(LumoUtility.FontSize.LARGE, LumoUtility.Margin.NONE);
        viewTitle.getStyle().set("margin-inline-end", "auto");
        layout.add(viewTitle);

        // A user Avatar
        Avatar avatar = new Avatar("Java Dominicano");
        avatar.addClassNames(LumoUtility.Margin.Right.MEDIUM);
        layout.add(avatar);

        return layout;
    }

    @Override
    protected void afterNavigation() {
        super.afterNavigation();
        // Set the view title in the header
        viewTitle.setText(getCurrentPageTitle());
    }

    private String getCurrentPageTitle() {
        PageTitle title = getContent().getClass().getAnnotation(PageTitle.class);
        return title == null ? "" : title.value();
    }
}
