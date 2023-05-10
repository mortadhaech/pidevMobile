/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;
import com.codename1.capture.Capture;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.ScaleImageLabel;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkManager;
import com.codename1.io.Storage;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.services.UtilisateurService;
import com.mycompany.utils.Statics;
import java.io.IOException;

/**
 * The user profile form
 *
 * @author Shai Almog
 */
public class ProfileForm extends BaseForm1 {

    public ProfileForm(Resources res) {
        super(BoxLayout.y());
Toolbar tb= new Toolbar (true);
setToolbar(tb);
//tb.setTitle ("Mon Profile");
getContentPane().setScrollVisible(true);
//super.addSideMenu(res);
Image img= res.getImage("Logo.png");
/*if (img.getHeight () > Display.getInstance().getDisplayHeight() / 3) {
img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 3);
}*/
ScaleImageLabel s1= new ScaleImageLabel (img);
s1.setUIID("BottomPad");
s1.setBackgroundType (Style. BACKGROUND_IMAGE_SCALED_FILL);
Button modiff = new Button("Modifier");
Button picture= new Button("Photo");
Button deconnexion= new Button("logout");
Button supprimer= new Button("supprimer compte");
//Image pan=rea.cerImage("profile=nic.ing").
//Image pap = res.getImage ("profile-pic.jpg");
//pp.setIcon (pap):
add(LayeredLayout.encloseIn(
s1,
BorderLayout.south(
GridLayout.encloseIn (3,
FlowLayout.encloseCenter(
     )
))    
));
String us = SessionManager.getUserName ();
System.out.println (us);


TextField password= new TextField (SessionManager.getPassowrd(), "password", 20, TextField. PASSWORD);

password.setUIID("TextFieldBlack");
addStringValue("Password", password);
TextField email = new TextField (SessionManager.getEmail(), "email", 20, TextField. EMAILADDR);
email.setUIID("TextFieldBlack");
addStringValue("email", email);
TextField username =new TextField(us);
username.setUIID("TextFieldBlack");
addStringValue("Username", username);

//modification
picture.setUIID("Update");
modiff.setUIID("Edit");
deconnexion.setUIID("logout");
supprimer.setUIID("supprimer");
addStringValue("", picture);
addStringValue("", modiff);
addStringValue("", deconnexion);
addStringValue("", supprimer);
TextField path =new TextField("");
picture.addActionListener (e-> {
String i = Capture.capturePhoto(Display.getInstance().getDisplayWidth(), -1);
 
if (i != null) {
Image im;
try {
im = Image.createImage(i);
    //khallaha commentair
/*im =im.scaled(res.getImage("photo-profile.jpg").getWidth(),
res.getImage("photo-profile.jpg").getHeight ());*/
//neh ilcommentaire
System.out.println (1);
path.setText(i);
}catch(IOException ex) {
System.out.println("Could not load image!");
}
}

});
//ajoutertaw
modiff.addActionListener((edit)-> {
InfiniteProgress ip = new InfiniteProgress();
final Dialog ipDig = ip.showInifiniteBlocking();
UtilisateurService.getInstance().EditUser(username.getText (), password.getText(), email.getText());
SessionManager.setUserName(username.getText());
SessionManager.setPassowrd(password.getText());
SessionManager.setEmail(email.getText());
SessionManager.setPhoto (username.getText ()+".jpg");
Dialog.show("Succès", "Modifications des coordonnées avec succès", "Ok", null);
ipDig.dispose();
refreshTheme ();
    });
deconnexion.addActionListener(e-> {
     new SignInForm (res).show();
SessionManager.pref.clearAll();//nfargh sessionI
Storage.getInstance().clearStorage ();
Storage.getInstance().clearCache();
System.out.println (SessionManager.getUserName());
;
    });
supprimer.addActionListener((supp)-> {

UtilisateurService.getInstance().deleteUser(SessionManager.getId());


 new SignUpForm (res).show();

    });
    }
    
    private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel")).
                add(BorderLayout.CENTER, v));
        add(createLineSeparator(0xeeeeee));
    }
}