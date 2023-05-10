package com.mycompany.gui;
import com.codename1.charts.util.ColorUtil;
import com.codename1.components.MultiButton;
import com.codename1.ui.Button;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.entites.Services;
import static com.mycompany.myapp.MyApplication.theme;
import com.mycompany.services.ServicesService;
import java.util.ArrayList;

public class ServiceFormFront extends Form {

    public ServiceFormFront(Resources res) {
        setTitle("List de Service");
        setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        ServicesService ss=new ServicesService();
        ArrayList<Services> s=ss.affichageServices();
        for(Services b: s)
        {
        addService(b.getService_id(),b.getService_nom(), b.getService_description(),b.getService_image(), ColorUtil.BLUE);
            
        }
    
    }

    public void addService(int serviceId, String serviceName, String description, String imagePath, int color) {
        MultiButton serviceButton = new MultiButton(serviceName);
        serviceButton.setTextLine2(description);
        serviceButton.setIcon(createColorIcon(color)); // Créez une icône colorée avec la couleur spécifiée
        
        // Chargement de l'image à partir du chemin d'accès spécifié
        try {
            Image image = Image.createImage(Display.getInstance().getResourceAsStream(getClass(), "/" + imagePath));
            EncodedImage encodedImage = EncodedImage.createFromImage(image, false);
            serviceButton.setIcon(createScaledImage(encodedImage)); // Définir l'image à l'aide d'une image mise à l'échelle
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        serviceButton.addActionListener(e -> {
        new SousServiceFormFrons(theme,serviceId).show();

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

    public void showDemo(Resources res) {
        ServiceFormFront form = new ServiceFormFront(res);
        form.show();
    }
}
