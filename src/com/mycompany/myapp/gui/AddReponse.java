/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.l10n.SimpleDateFormat;
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
import com.mycompany.myapp.entities.Reponse;
import com.mycompany.myapp.entities.Utilisateur;
import com.mycompany.myapp.services.serviceReclamation;
import com.mycompany.myapp.services.serviceReponse;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import java.util.Date;
import java.util.List;



/**
 *
 * @author USER
 */
public class AddReponse extends Form{
    
    public AddReponse(Form previous,int id) {
        setTitle("Add a new Reponse");
        setLayout(BoxLayout.y());
        
//System.out.println(id+"kfkffffffffffff");
        TextField tfSubject = new TextField("","Subject");
        CheckBox cbStatus = new CheckBox("Status");
        Button btnValider = new Button("Add reponse");
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date currentDate = new Date();
        String formattedDate = dateFormat.format(currentDate);
        System.out.println(formattedDate);

        
        Utilisateur freelancer =new Utilisateur(81);
        Utilisateur user =new Utilisateur(126);
        
        btnValider.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent evt) {
        if ((tfSubject.getText().length()==0))
            Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
        else
        {
            try {
                List <Reclamation> listreclam = serviceReclamation.getInstance().getReclamById(id) ;
            for(Reclamation k :listreclam){
                //System.out.println(listreclam+"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
                
          sendSms(99492074 ,tfSubject.getText() ,k.getTitle(),k.getSubject(),k.getDate() ) ;  
        
            }
                Reponse t = new Reponse(id,tfSubject.getText().toString());
                
                if( serviceReponse.getInstance().addReclamtion(t)) {
                    Dialog.show("Success","Reponse added",new Command("OK"));
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

        
        addAll(tfSubject,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
                
    }
    
    public void sendSms(int numero ,String reponse ,String Title ,String subj ,Date date) {
		try {
    String ACCOUNT_SID="ACee1185405e903ad86144cfd70297e75a";
    String AUTH_TOKEN="305c1433a12b87fbd0571fe58362dbe5";
		
                Twilio.init(ACCOUNT_SID,AUTH_TOKEN) ;
                Message message = Message.creator(new PhoneNumber("+216"+numero),
        new PhoneNumber("+16203492559"), 
        "\n \n nous avons consulter votre reclmations que tu as fait le "+date 
                +" qui est comme suit \nTitre :" +Title +
                "\nSubject :" +subj + "\nnotre reponse :\n"
                +reponse).create();

        System.out.println(message.getSid());
//System.out.println(numero);

		} catch (Exception e) {
			System.out.println("Error SMS "+e);
                        Dialog.show("ERROR", "Something went wrong!", new Command("OK"));
			//return "Error "+e;
		}
	}
}
