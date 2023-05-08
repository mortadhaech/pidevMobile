package com.mycompany.myapp.gui;


import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Reclamation;
import com.mycompany.myapp.services.serviceReclamation;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Offre;
import com.mycompany.myapp.services.serviceOffre;
import java.util.Date;



public class EditOffr extends Form {
    Offre r=new Offre() ;
     public EditOffr(Form previous ,int id) {
        
            setTitle("Modifier Offre");
            setLayout(BoxLayout.y());
                 r = serviceOffre.getInstance().getOffr(id);
          TextField servicee = new TextField(r.getServiceN(),"Service");
          TextField sservicee = new TextField(r.getSous_serviceN(),"Sous service");
          TextField adressee = new TextField(r.getOffre_adresse(),"Adresse");
          TextField descriptionn = new TextField(r.getOffre_description(),"Description");
          TextField imagess = new TextField(r.getOffre_image(),"Image");
        
        Button btnValider = new Button("ENREGISTRER");
        
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
                        
                        Offre t = new Offre(id,adressee.getText().toString(),descriptionn.getText().toString(),imagess.getText().toString(),servicee.getText().toString(),sservicee.getText().toString());
                        if( serviceOffre.getInstance().uppOffre(t))
                        {
                           Dialog.show("Success","Connection accepted",new Command("OK"));
                        }else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                    
                }
                
                
            }
        });
        
        
        
        addAll(servicee,sservicee,adressee,descriptionn,imagess,btnValider)   ; 
        
                getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());

        
        
        
    }
    
}
