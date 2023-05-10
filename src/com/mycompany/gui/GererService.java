package com.mycompany.gui;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
import com.codename1.io.Log;
import com.codename1.ui.Component;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.mycompany.entites.Services;
import static com.mycompany.myapp.MyApplication.theme;
import com.mycompany.services.ServicesService;
import java.io.IOException;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkManager;
import com.codename1.io.Util;
import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.Resources;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.UITimer;
import java.io.InputStream;
public class GererService {
public static String  img;
private static  void uploadImage(String imagePath) {
        try {
            MultipartRequest request = new MultipartRequest() {
                @Override
                protected void readResponse(InputStream input) throws IOException {
                    
                    String response = Util.readToString(input);
                    System.out.println("Réponse du serveur : " + response);
                }
            };


            request.addData("file", imagePath, "image/png");

            // Envoyer la requête au serveur
            NetworkManager.getInstance().addToQueueAndWait(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void AjouterServiceForm() {
        
        
        UIBuilder uIBuilder = new UIBuilder();
        uIBuilder.registerCustomComponent("imgS", ImageViewer.class);

        
ImageViewer imgViewer = new ImageViewer();
try {
    Image image = Image.createImage("/load.png");
          
    imgViewer.setImage(image);
} catch (IOException ex) {
    ex.printStackTrace();
}
 
         
        
        Container c = uIBuilder.createContainer(theme, "AjouterService");

        Form f1 = (Form) uIBuilder.findByName("AjouterService", c);
        TextField nom = (TextField) uIBuilder.findByName("Nomserv", c);
        TextField des = (TextField) uIBuilder.findByName("Descserv", c);
        TextField nbService = (TextField) uIBuilder.findByName("nbServ", c);
        //ImageViewer imgS=(ImageViewer) uIBuilder.findByName("imgS", c);
        String   imgS;
        Button btnAj = (Button) uIBuilder.findByName("btnajout", c);
        Button btnannuler = (Button) uIBuilder.findByName("btnAnnuler", c);
        
Container mainContainer = new Container(new BorderLayout());
 Button myButton = new Button("upload image");
        
        myButton.addActionListener(new ActionListener() {
           

            @Override
            public void actionPerformed(ActionEvent evt) {

           
                    Display.getInstance().openGallery(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent evt) {
                            if (evt != null && evt.getSource() != null) {
                                final String imagePath = (String) evt.getSource();
                                try {
                                    Image image = Image.createImage(imagePath);
                                    imgViewer.setImage(image);
                                    System.out.println(imagePath);
                                                                        img=imagePath;
                                                                        
                                                                        

                                } catch (IOException e) {

                                    e.printStackTrace();
                                }
                                
                            }
                        }
                    }, Display.GALLERY_IMAGE);
                }
            
        });
        
    

    mainContainer.addComponent(BorderLayout.NORTH, imgViewer);
    
    mainContainer.addComponent(BorderLayout.SOUTH, myButton);

            mainContainer.addComponent(BorderLayout.CENTER, c);

    Form form = new Form();
    form.setLayout(new BorderLayout());
    form.addComponent(BorderLayout.CENTER, mainContainer);
    form.show();

        Services service = new Services();
        ServicesService servicesService = new ServicesService();
        btnAj.addPointerPressedListener(l -> {

          String nomText = nom.getText();
            String desText = des.getText();
            String nbServiceText = nbService.getText();

            if (nomText.isEmpty() || desText.isEmpty() || nbServiceText.isEmpty()) {
                Dialog.show("Erreur", "Veuillez remplir tous les champs", "OK", null);
                return;
            }

     if (img==null ) {
                Dialog.show("Erreur", "selectionner une image", "OK", null);
                return;
            }

            // Vérification du format du nombre de services
            int nbServiceValue;
            try {
                nbServiceValue = Integer.parseInt(nbServiceText);
                if (nbServiceValue < 0) {
                    Dialog.show("Erreur", "Le nombre de services doit etre positif", "OK", null);
                    return;
                }
            } catch (NumberFormatException e) {
                Dialog.show("Erreur", "Le nombre de services doit être un entier valide", "OK", null);
                return;
            }
    
            service.setService_nom(nomText);
    service.setService_description(desText);
    
    service.setService_image(img);
    service.setNb_sous_services(nbServiceValue);

    if (servicesService.ajoutService(service)) {
             Dialog.show("Succes", "Ajouter  Succes", "OK", null);
        nom.setText("");
        des.setText("");
        nbService.setText("");
    }
        });
        btnannuler.addActionListener(e -> {
new ListService();
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
        Button btnMo = (Button) uIBuilder.findByName("BtnModifier", c);
        Button btnAn = (Button) uIBuilder.findByName("btnA", c);
        int id = services.getService_id();
        nom.setText(services.getService_nom());
        des.setText(services.getService_description());
        nbService.setText(services.getNb_sous_services() + "");

        f1.show();

        Services service = new Services();
        ServicesService servicesService = new ServicesService();
        
        btnMo.addPointerPressedListener(l -> {

          String nomText = nom.getText();
            String desText = des.getText();
            String nbServiceText = nbService.getText();

            if (nomText.isEmpty() || desText.isEmpty() || nbServiceText.isEmpty()) {
                Dialog.show("Erreur", "Veuillez remplir tous les champs", "OK", null);
                return;
            }

    

            // Vérification du format du nombre de services
            int nbServiceValue;
            try {
                nbServiceValue = Integer.parseInt(nbServiceText);
                if (nbServiceValue <= 0) {
                    Dialog.show("Erreur", "Le nombre de services doit être supérieur à zéro", "OK", null);
                    return;
                }
            } catch (NumberFormatException e) {
                Dialog.show("Erreur", "Le nombre de services doit être un entier valide", "OK", null);
                return;
            }
            service.setService_nom(nom.getText());
            service.setService_description(des.getText());
            service.setService_image(imgS);
            service.setNb_sous_services(Integer.parseInt(nbService.getText()));
            if (servicesService.modifierService(id, service)) {
             Dialog.show("Succes", "Modification Succes", "OK", null);

                des.setText("");
                nbService.setText("");
         new ListService();

            }
        });
        btnAn.addActionListener(e -> {
new ListService();
        });

    }

  

    
    
    
    public void init() {
        try {
            theme = Resources.openLayered("/theme-");
        } catch (IOException ex) {
            Log.e(ex);
        }
    }

}
