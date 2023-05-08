package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;


public class HomeForm extends Form{

    public HomeForm() {
        
        setTitle("Home");
        setLayout(BoxLayout.y());
        
        add(new Label("Choose an option"));
        Button btnAddTask = new Button("Ajouter Offre");
        Button btnListTasks = new Button("Afficher Offre");
        
        btnAddTask.addActionListener(e-> new AddOffre(this).show());
        btnListTasks.addActionListener(e-> new ListOffr(this).show());
        addAll(btnAddTask,btnListTasks);
        
        
    }
    
    
}
