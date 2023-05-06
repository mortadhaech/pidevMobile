package com.mycompany.gui;

import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.mycompany.entites.Engagment;
import com.mycompany.services.EngagmentService;
import java.util.ArrayList;
import java.util.Date;

/**
 * GUI builder created Form
 *
 * @author shai
 */
public class CalendarForm extends BaseForm {

    public CalendarForm() {
        this(com.codename1.ui.util.Resources.getGlobalResources());
    }
    
    public CalendarForm(com.codename1.ui.util.Resources resourceObjectInstance) {
        initGuiBuilderComponents(resourceObjectInstance);
        setLayout(BoxLayout.y());
        setScrollableY(true);
        getContentPane().setScrollVisible(false);
        getToolbar().setUIID("Container");
        Button b = new Button(" ");
        b.setUIID("Container");
        getToolbar().setTitleComponent(b);
        getTitleArea().setUIID("Container");
        installSidemenu(resourceObjectInstance);
        gui_Calendar_1.setTwoDigitMode(true);
        
        Picker p = new Picker();
        b.addActionListener(e -> {
            p.pressed(); 
            p.released();
        });
        p.addActionListener(e -> {
            gui_Calendar_1.setCurrentDate(p.getDate());
            
            gui_Calendar_1.setSelectedDate(p.getDate());
            gui_Calendar_1.setDate(p.getDate());
        });
        p.setFormatter(new SimpleDateFormat("MMMM"));
        p.setDate(new Date());
        p.setUIID("CalendarDateTitle");
        Container cnt = BoxLayout.encloseY(
                p,
                new Label(resourceObjectInstance.getImage("calendar-separator.png"), "CenterLabel")
        );
        
        BorderLayout bl = (BorderLayout)gui_Calendar_1.getLayout();
        Component combos = bl.getNorth();
        gui_Calendar_1.replace(combos, cnt, null);
        
         EngagmentService e=new EngagmentService();
         ArrayList<Engagment> en=e.AffichageEvenement(58);
        
              for(Engagment e1: en)
         { Date dateC=null;
        add(createEntry(resourceObjectInstance,true, e1.getHeure()+"", "", "",e1.getNom()+"", e1.getDate()+"", "", ""));
              String dateString = e1.getDate();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        try {
           dateC = format.parse(dateString);
        } catch (ParseException e2) {
            e2.printStackTrace();
        }
        
            gui_Calendar_1.setSelectedDate(dateC);
        
          
         }
    }

    private Container createEntry(Resources res, boolean selected, String startTime, String endTime, String location, String title, String attendance, String... images) {
        Component time = new Label(startTime, "CalendarHourUnselected");
        if(selected) {
            time.setUIID("CalendarHourSelected");
        }
        
        Container circleBox = BoxLayout.encloseY(new Label(res.getImage("label_round-selected.png")),
                new Label("-", "OrangeLine"),
                new Label("-", "OrangeLine")
        );
        
        Container cnt = new Container(BoxLayout.x());
        for(String att : images) {
            cnt.add(res.getImage(att));
        }
        Container mainContent = BoxLayout.encloseY(
                BoxLayout.encloseX(
                        new Label(title, "SmallLabel"), 
                        new Label("-", "SmallThinLabel"), 
                        new Label(startTime, "SmallThinLabel"), 
                        new Label("-", "SmallThinLabel"),
                        new Label(endTime, "SmallThinLabel")),
                new Label(attendance, "TinyThinLabel"),
                cnt
        );
        
        Label redLabel = new Label("", "RedLabelRight");
        FontImage.setMaterialIcon(redLabel, FontImage.MATERIAL_LOCATION_ON);
        Container loc = BoxLayout.encloseY(
                redLabel,
                new Label("Location:", "TinyThinLabelRight"),
                new Label(location, "TinyBoldLabel")
        );
        
        mainContent= BorderLayout.center(mainContent).
                add(BorderLayout.WEST, circleBox);
        
        return BorderLayout.center(mainContent).
                add(BorderLayout.WEST, FlowLayout.encloseCenter(time)).
                add(BorderLayout.EAST, loc);
    }
    
    protected com.codename1.ui.Calendar gui_Calendar_1 = new com.codename1.ui.Calendar();


// <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
        setLayout(new com.codename1.ui.layouts.GridLayout(2, 1));
        setInlineStylesTheme(resourceObjectInstance);
                setInlineStylesTheme(resourceObjectInstance);
        setTitle("");
        setName("CalendarForm");
                gui_Calendar_1.setInlineStylesTheme(resourceObjectInstance);
        gui_Calendar_1.setName("Calendar_1");
        addComponent(gui_Calendar_1);
    }// </editor-fold>

//-- DON'T EDIT ABOVE THIS LINE!!!

    @Override
    protected boolean isCurrentCalendar() {
        return true;
    }

    @Override
    protected void initGlobalToolbar() {
        setToolbar(new Toolbar(true));
    }


    
}
