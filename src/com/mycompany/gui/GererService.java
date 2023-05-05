package com.mycompany.gui;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
import com.codename1.io.Log;
import com.mycompany.entites.Services;
import static com.mycompany.myapp.MyApplication.theme;
import com.mycompany.services.ServicesService;

import java.io.IOException;

public class GererService {

    public static void AjouterServiceForm() {
        UIBuilder uIBuilder = new UIBuilder();
        uIBuilder.registerCustomComponent("imgS", ImageViewer.class);

        Container c = uIBuilder.createContainer(theme, "AjouterService");
        Form f1 = (Form) uIBuilder.findByName("AjouterService", c);
        TextField nom = (TextField) uIBuilder.findByName("Nomserv", c);
        TextField des = (TextField) uIBuilder.findByName("Descserv", c);
        TextField nbService = (TextField) uIBuilder.findByName("nbServ", c);
        //ImageViewer imgS=(ImageViewer) uIBuilder.findByName("imgS", c);
        String imgS = "hello";
        Button btnAj = (Button) uIBuilder.findByName("btnajout", c);
        Button btnannuler = (Button) uIBuilder.findByName("btnAnnuler", c);

        f1.show();

        Services service = new Services();
        ServicesService servicesService = new ServicesService();
        btnAj.addPointerPressedListener(l -> {

            service.setService_nom(nom.getText());
            service.setService_description(des.getText());
            service.setService_image(imgS);
            service.setNb_sous_services(Integer.parseInt(nbService.getText()));

            if (servicesService.ajoutService(service)) {
                System.out.println("ajout succes");
                nom.setText("");
                des.setText("");
                nbService.setText("");

            }
        });
        Button btnAnnuler = new Button("Annuler");
        btnAnnuler.addActionListener(e -> {
            System.out.println("ajout annuler");

        });
    }

    public static void ModifierServiceForm(Services services) {
        UIBuilder uIBuilder = new UIBuilder();
        uIBuilder.registerCustomComponent("imgS", ImageViewer.class);

        Container c = uIBuilder.createContainer(theme, "ModifierService");
        Form f1 = (Form) uIBuilder.findByName("ModifierService", c);
        TextField nom = (TextField) uIBuilder.findByName("NomS", c);
        TextField des = (TextField) uIBuilder.findByName("desS", c);
        TextField nbService = (TextField) uIBuilder.findByName("NbS", c);
        //ImageViewer imgS=(ImageViewer) uIBuilder.findByName("imgS", c);
        String imgS = "hello";
        Button btnAj = (Button) uIBuilder.findByName("BtnModifier", c);
        Button btnAn = (Button) uIBuilder.findByName("btnA", c);
        int id = services.getService_id();
        nom.setText(services.getService_nom());
        des.setText(services.getService_description());
        nbService.setText(services.getNb_sous_services() + "");

        f1.show();

        Services service = new Services();
        ServicesService servicesService = new ServicesService();
        btnAj.addPointerPressedListener(l -> {

            service.setService_nom(nom.getText());
            service.setService_description(des.getText());
            service.setService_image(imgS);
            service.setNb_sous_services(Integer.parseInt(nbService.getText()));
            if (servicesService.modifierService(id, service)) {
                System.out.println("Modification succes");
                nom.setText("");
                des.setText("");
                nbService.setText("");

            }
        });
        btnAn.addActionListener(e -> {
            System.out.println("Modification annuler");

        });

    }

    
    
    
    public void init() {
        try {
            theme = Resources.openLayered("/theme");
        } catch (IOException ex) {
            Log.e(ex);
        }
    }

}
