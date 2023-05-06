/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Reclamation;
import com.mycompany.myapp.services.serviceReclamation;
import java.util.ArrayList;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;

/**
 *
 * @author USER
 */
public class ListReclam extends Form {
    public ListReclam(Form previous) {
        setTitle("List Reclamation");
        setLayout(BoxLayout.y());

        /*SpanLabel sp = new SpanLabel();
        sp.setText(ServiceTask.getInstance().getAllTasks().toString());
        add(sp);
         */
        ArrayList<Reclamation> quizs = serviceReclamation.getInstance().getAllReclam();

        for (Reclamation t : quizs) {
            addElement(t);
        }

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

    }
    
    
    
    public void addElement(Reclamation reclam) {
        Button btn_up =new Button("update")  ;
        btn_up.addActionListener(e-> new EditReclam(this,reclam.getId()).show());
    Button btn_2 = new Button("Delete");
    btn_2.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent evt) {
            Dialog dlg = new Dialog("Deleting Reclamation");
            //dlg.show("Please wait...");
            boolean result = serviceReclamation.getInstance().suppReclamation(reclam);
            if (result) {
                dlg.dispose();
                Dialog.show("Success", "Reclamation deleted successfully", "OK", null);
                refreshList();
            } else {
                dlg.dispose();
                Dialog.show("Error", "Failed to delete Reclamation", "OK", null);
            }
        }
    });

    Button btn = new Button(reclam.getTitle());
    Label right = new Label("titre :"+reclam.getTitle());
    Label right2 = new Label("subject :"+reclam.getSubject());
    btn.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent evt) {
            // TODO: handle displaying Reclamation details
        }
    });

    addAll(right,right2, btn_2,btn_up);
}

private void refreshList() {
    removeAll();
    ArrayList<Reclamation> quizs = serviceReclamation.getInstance().getAllReclam();
    for (Reclamation t : quizs) {
        addElement(t);
    }
    revalidate();
}

}
