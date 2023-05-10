
package com.mycompany.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.entites.Engagment;
import com.mycompany.utils.Statics;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class EngagmentService {

    public static EngagmentService instance = null;

    public static boolean resultOk = true;

    //initilisation connection request 
    private ConnectionRequest req;

    public static EngagmentService getInstance() {
        if (instance == null) {
            instance = new EngagmentService();
        }
        return instance;
    }

    public EngagmentService() {
        req = new ConnectionRequest();

    }    
    
       public ArrayList<Engagment> AffichageEvenement(int idfree) {
        ArrayList<Engagment> result = new ArrayList<>();

        String url = Statics.BASE_URL + "/engagementfreelancerJson/"+idfree;
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

                        Engagment en = new Engagment();

                        float ide = Float.parseFloat(obj.get("ide").toString());
                        String Nom = obj.get("nom").toString();
                        String jour = obj.get("jour").toString();
                        String date = obj.get("date").toString();
                        float heure = Float.parseFloat(obj.get("heure").toString());
                        float idf = Float.parseFloat(obj.get("idf").toString());


                        en.setIdE((int)ide );
                        en.setNom(Nom);
                        en.setJour(jour);
                        en.setDate(date);
                        en.setHeure((int)heure);
                        en.setIdF((int)idf);

                        result.add(en);

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
