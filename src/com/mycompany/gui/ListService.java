package com.mycompany.gui;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.entites.Services;
import com.mycompany.services.ServicesService;
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
        form.add(servicesContainer);
        form.show();
    }
}
