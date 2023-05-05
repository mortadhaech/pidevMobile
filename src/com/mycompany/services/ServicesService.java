package com.mycompany.services;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.entites.Services;
import com.mycompany.utils.MyCnx;

public class ServicesService {
    //singleton 

    public static ServicesService instance = null;

    public static boolean resultOk = true;

    //initilisation connection request 
    private ConnectionRequest req;

    public static ServicesService getInstance() {
        if (instance == null) {
            instance = new ServicesService();
        }
        return instance;
    }

    public ServicesService() {
        req = new ConnectionRequest();

    }

    //ajout 
    public boolean ajoutService(Services services) {
        try {

            String url = MyCnx.BASE_URL + "/addserviceJson?serN=" + services.getService_nom() + "&serD=" + services.getService_description()
                    + "&serI=" + services.getService_image() + "&serNb=" + services.getNb_sous_services();

            req.setUrl(url);
            req.addResponseListener((e) -> {

                String str = new String(req.getResponseData());
                System.out.println("data == " + str);
            });

            NetworkManager.getInstance().addToQueueAndWait(req);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean modifierService(int id,Services services) {
        try {
        String url = MyCnx.BASE_URL + "/editJson/" + id + "?&serN=" + services.getService_nom()
                + "&serD=" + services.getService_description() + "&serNb=" + services.getNb_sous_services()
                + "&serI=" + services.getService_image();
        req.setUrl(url);

         req.addResponseListener((e) -> {

                String str = new String(req.getResponseData());
                System.out.println("data == " + str);
            });


        NetworkManager.getInstance().addToQueueAndWait(req);
             return true;
        } catch (Exception e) {
            return false;
        }
        
        

    }

    public Services findService(Services services) {
        String url = MyCnx.BASE_URL + "/findJson/" + services.getService_id();
        req.setHttpMethod("POST");
        req.setUrl(url);
        req.addArgument("serN", services.getService_nom());
        req.addArgument("serD", services.getService_description());
        req.addArgument("serNb", Integer.toString(services.getNb_sous_services()));
        req.addArgument("serI", services.getService_image());
        Services service = new Services(services.getService_id(), services.getService_nom(), services.getService_description(),
                 services.getService_image(), services.getNb_sous_services());

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOk = req.getResponseCode() == 200;
                req.removeResponseListener(this);
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);
        if (resultOk) {
            return service;
        } else {
            return null;
        }
    }
   public boolean deleteService(int id ) {
        String url = MyCnx.BASE_URL +"/deleteJson/"+id;
        
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                    
                    req.removeResponseCodeListener(this);
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        return  resultOk;
    }
}
