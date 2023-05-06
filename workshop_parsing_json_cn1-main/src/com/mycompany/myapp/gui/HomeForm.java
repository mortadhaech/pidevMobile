/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author bhk
 */
public class HomeForm extends Form{

    public HomeForm() {
        
        setTitle("Home");
        setLayout(BoxLayout.y());
        
        add(new Label("Choose an option"));
        Button btnAddTask = new Button("Add Task");
        Button btnListTasks = new Button("List Tasks");
        Button btnListTaskss = new Button("Back");
        
        btnAddTask.addActionListener(e-> new AddReclam(this).show());
        btnListTasks.addActionListener(e-> new ListReclam(this).show());
        btnListTaskss.addActionListener(e-> new ListReclamBack(this).show());
        addAll(btnAddTask,btnListTasks,btnListTaskss);
        
        
    }
    
    
}
