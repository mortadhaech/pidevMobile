package com.mycompany.gui;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.entites.Services;
import static com.mycompany.myapp.MyApplication.theme;
import com.mycompany.services.ServicesService;
import com.mycompany.services.UtilisateurService;
import java.util.ArrayList;

public class ListService {
    private ArrayList<Services> services;

    public ListService() {
        // Récupération des services depuis la base de données
        services = ServicesService.getInstance().affichageServices();

        // Créer un container pour tous les containers de service
        Container servicesContainer = new Container();
        servicesContainer.setLayout(new BoxLayout(BoxLayout.Y_AXIS));

        // Ajouter chaque service au container
        for (Services service : services) {
            ServiceContainer serviceContainer = new ServiceContainer();
            serviceContainer.setServiceInfo(service);
            servicesContainer.add(serviceContainer.getC());
        }

        // Créer et afficher la forme avec le container de services
        Form form = new Form("Liste des services");
        Button next = new Button("Ajouter");
        Button profil = new Button("profil");
        form.add(profil);
        form.add(next);
        form.add(servicesContainer);
        form.show();
        next.addActionListener(e -> 
{
    GererService g=new GererService();
    g.AjouterServiceForm();
});
    
            profil.addActionListener(a -> 
{
   new ProfileForm (theme).show();
});
    }
}
