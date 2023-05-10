
package com.mycompany.gui;
import com.codename1.charts.util.ColorUtil;
import com.codename1.components.MultiButton;
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
import static com.mycompany.myapp.MyApplication.theme;
import com.mycompany.services.ServicesService;
import com.mycompany.services.SousServicesService;
import java.util.ArrayList;
public class SousServiceFormFrons extends Form{
    public SousServiceFormFrons(Resources res,int serviceId) {

        setTitle("List de Sous Service");
        setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        SousServicesService ss=new SousServicesService();
        ArrayList<Sous_services> s=ss.affichageServices(serviceId);
        
        for(Sous_services b: s)
        {
            
        addService(b.getSous_service_id(),b.getSous_service_nom(), b.getSous_service_description(),b.getSous_service_image(), ColorUtil.BLUE);
            
        }
        
    }

    public void addService(int sserviceId ,String serviceName, String description, String imagePath, int color) {
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

        new FreelancerFormFront(theme,sserviceId).show();

        });

        add(serviceButton);
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
        SousServiceFormFrons form = new SousServiceFormFrons(res,sserviceId);
        form.show();
    }
    
    
}
