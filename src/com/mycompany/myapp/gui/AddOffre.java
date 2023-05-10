package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Reclamation;


import com.codename1.l10n.DateFormat;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.ComboBox;
import com.mycompany.myapp.entities.Offre;
import com.mycompany.myapp.entities.Services;
import com.mycompany.myapp.entities.Sous_services;
import com.mycompany.myapp.entities.Utilisateur;
import com.mycompany.myapp.services.serviceOffre;
import com.mycompany.myapp.services.serviceReclamation;
import java.util.Date;


public class AddOffre extends Form{
    
    public AddOffre(Form previous) {
        setTitle("Ajouter Offre");
        setLayout(BoxLayout.y());
        
        TextField servicee = new TextField("","Service");
        TextField sservicee = new TextField("","Sous service");
        
        //ComboBox<String> serviceCombo = new ComboBox<String>();
        //ComboBox<String> sserviceCombo = new ComboBox<String>();
        
        
        TextField adressee = new TextField("","Adresse");
        TextField descriptionn = new TextField("","Description");
        TextField imagess = new TextField("","Image");
        
        
        
        CheckBox cbStatus = new CheckBox("Status");
        Button btnValider = new Button("Ajouter");
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date currentDate = new Date();
        String formattedDate = dateFormat.format(currentDate);
        System.out.println(formattedDate);
        
        btnValider.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent evt) {
        if ((servicee.getText().length()==0) || (sservicee.getText().length()==0)|| (adressee.getText().length()==0)|| (descriptionn.getText().length()==0)|| (imagess.getText().length()==0))
            Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
        else
        {
            try {
                Offre t = new Offre(adressee.getText().toString(),descriptionn.getText().toString(),imagess.getText().toString(),servicee.getText().toString(),sservicee.getText().toString());
                
                if( serviceOffre.getInstance().addOffre(t)) {
                    Dialog.show("Success","Offre Ajouter",new Command("OK"));
                    System.out.println(t.toString());
                } else {
                    Dialog.show("ERROR", "Server error", new Command("OK"));
                }
            } catch (NumberFormatException e) {
                Dialog.show("ERROR", "Status must be a number", new Command("OK"));
            }

        }
    }
});

        
        addAll(servicee,sservicee,adressee,descriptionn,imagess,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
                
    }
}
