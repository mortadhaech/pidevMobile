
package com.mycompany.gui;
import com.codename1.charts.util.ColorUtil;
import com.codename1.components.MultiButton;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.entites.Services;
import com.mycompany.entites.Sous_services;
import com.mycompany.entities.Utilisateur;
import static com.mycompany.myapp.MyApplication.theme;
import com.mycompany.services.EngagmentService;
import com.mycompany.services.ServicesService;
import com.mycompany.services.SousServicesService;
import java.util.ArrayList;
public class FreelancerFormFront extends Form{
    public FreelancerFormFront(Resources res,int sserviceId) {
  setTitle("Liste des Freelancers");
    setLayout(new BoxLayout(BoxLayout.Y_AXIS));
    SousServicesService ss = new SousServicesService();
    ArrayList<Utilisateur> s = ss.affichageFreelancer(sserviceId);
    for (Utilisateur k : s) {
        addService(k.getId(), k.getNom(), k.getDescription(), k.getPhoto(), ColorUtil.BLUE, k.getRate(), k.getNumtel());
    }
}

public void addService(int id, String serviceName, String description, String imagePath, int color, int rate, int num) {
    MultiButton serviceButton = new MultiButton(serviceName);
    serviceButton.setTextLine2(description);
    serviceButton.setIcon(createColorIcon(color));

    try {
        Image image = Image.createImage(Display.getInstance().getResourceAsStream(getClass(), "/" + imagePath));
        EncodedImage encodedImage = EncodedImage.createFromImage(image, false);
        serviceButton.setIcon(createScaledImage(encodedImage));
    } catch (Exception e) {
        e.printStackTrace();
    }

    serviceButton.addActionListener(e -> {
        new CalendarForm(theme, id).show();
    });

    // Append the desired information to the MultiButton's lines
    serviceButton.setTextLine3("Rate: " + rate);
    serviceButton.setTextLine4("Num: " + num);
    
    Button reclamerButton = new Button("pofil");
    reclamerButton.addActionListener(e -> {
 new ProfileForm (theme).show();

    });
reclamerButton.setWidth(30); // Définir la largeur préférée
reclamerButton.setHeight(10); // Définir la hauteur préférée
    // Create a container with horizontal layout
MultiButton.setSameWidth(this);
    Container buttonContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
    buttonContainer.add(serviceButton);
    buttonContainer.add(reclamerButton);
    add(buttonContainer);
    Label separator = new Label();
    separator.setUIID("Separator");
    add(separator);
}


    public EncodedImage createColorIcon(int color) {
        int size = Display.getInstance().convertToPixels(4);
        Image colorImage = Image.createImage(size, size, color);
        EncodedImage encodedImage = EncodedImage.createFromImage(colorImage, false);
        return encodedImage;
    }

    public EncodedImage createScaledImage(EncodedImage sourceImage) {
        int width = Display.getInstance().convertToPixels(10); // Définir la largeur souhaitée de l'image
        int height = Display.getInstance().convertToPixels(10); // Définir la hauteur souhaitée de l'image
        Image scaledImage = sourceImage.scaled(width, height);
        EncodedImage encodedImage = EncodedImage.createFromImage(scaledImage, false);
        return encodedImage;
    }

    public void showDemo(Resources res,int sserviceId ) {
        FreelancerFormFront form = new FreelancerFormFront(res,sserviceId);
        form.show();
    }
    
    
}
