package com.feroc;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.NativeButton;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of an HTML page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final GridLayout gridLayout = new GridLayout();
        gridLayout.addStyleName("outlined");
        gridLayout.setSizeFull();
        gridLayout.setStyleName("black");


        gridLayout.removeAllComponents();
        gridLayout.setRows(3);
        gridLayout.setColumns(3);



        for (Integer row = 0; row < gridLayout.getRows(); row++) {
            for (Integer col = 0; col < gridLayout.getColumns(); col++) {
                NativeButton btn = new NativeButton(row.toString() + " - " + col.toString());
                btn.setWidth("95%");
                btn.setHeight("95%");
                btn.setStyleName("tile");
                btn.addClickListener(event -> Notification.show("You clicked: " + btn.getCaption()));
                gridLayout.addComponent(btn, row, col);
                gridLayout.setRowExpandRatio(row, 0.0f);
                gridLayout.setColumnExpandRatio(col, 0.0f);
                gridLayout.setComponentAlignment(btn, Alignment.MIDDLE_CENTER);
            }
        }

        setContent(gridLayout);
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
