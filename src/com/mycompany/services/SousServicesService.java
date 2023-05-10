
package com.mycompany.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.entites.Sous_services;
import com.mycompany.utils.Statics;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.mycompany.entities.Utilisateur;


public class SousServicesService {
 
    public static SousServicesService instance = null;

    public static boolean resultOk = true;

    //initilisation connection request 
    private ConnectionRequest req;

    public static SousServicesService getInstance() {
        if (instance == null) {
            instance = new SousServicesService();
        }
        return instance;
    }

    public SousServicesService() {
        req = new ConnectionRequest();

    }

    
        public ArrayList<Sous_services> affichageServices(int id) {
        ArrayList<Sous_services> result = new ArrayList<>();

        String url = Statics.BASE_URL + "/sousservicesJson/"+id;
        req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp;
                jsonp = new JSONParser();

                try {
                    Map<String, Object> mapService = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));

                    List<Map<String, Object>> listOfMaps = (List<Map<String, Object>>) mapService.get("root");


                    for (Map<String, Object> obj : listOfMaps) {

                        Sous_services ser = new Sous_services();

                        float id = Float.parseFloat(obj.get("sousServiceId").toString());
                        String Nom = obj.get("sousServiceNom").toString();
                        String des = obj.get("sousServiceDescription").toString();
                        String img = obj.get("sousServiceImage").toString();
                        float idS = Float.parseFloat(obj.get("serviceId").toString());


                        ser.setSous_service_id((int)id );
                        ser.setSous_service_nom(Nom);
                        ser.setSous_service_description(des);
                        ser.setSous_service_image(img);
                        result.add(ser);

                    }

                } catch (Exception ex) {

                    ex.printStackTrace();
                }

            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);

        return result;

    }
public ArrayList<Utilisateur> affichageFreelancer(int idS) {
    ArrayList<Utilisateur> result = new ArrayList<>();

    String url = Statics.BASE_URL + "/FreelancerSJson/" + idS;
    req.setUrl(url);

    req.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
            JSONParser jsonp;
            jsonp = new JSONParser();

            try {
                Map<String, Object> mapService = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));

                List<Map<String, Object>> listOfMaps = (List<Map<String, Object>>) mapService.get("root");

                for (Map<String, Object> obj : listOfMaps) {
                    Utilisateur ser = new Utilisateur();

                    float id=Float.parseFloat(obj.get("id").toString());
                    String nom = obj.get("nom").toString();
                    String description = obj.get("description").toString();
                    String photo = obj.get("image").toString();
                    float num=Float.parseFloat(obj.get("num").toString());
boolean verified=Boolean.parseBoolean(obj.get("verified").toString());
                    float rate=Float.parseFloat(obj.get("rate").toString());

                    ser.setId((int)id);
                    ser.setNom(nom);
                    ser.setDescription(description);
                    ser.setPhoto(photo);
                    ser.setNumtel((int)num);
                    ser.setVerified(verified);
                    ser.setRate((int)rate);

                    
                    
              

                    result.add(ser);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    });

    NetworkManager.getInstance().addToQueueAndWait(req);

    return result;
}
    
        
        
}
