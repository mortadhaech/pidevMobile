package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Offre;
import com.mycompany.myapp.entities.Reclamation;
import static com.mycompany.myapp.services.serviceReclamation.instance;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


public class serviceOffre {
    public ArrayList<Offre> offr;
    public Offre off ;
    
    public static serviceOffre instance = null;
    public boolean resultOK;
    public ConnectionRequest req;
    
    private serviceOffre() {
        req = new ConnectionRequest();
    }
    
    public static serviceOffre getInstance() {
                if (instance == null) {
                    instance = new serviceOffre();
                }
                return instance;
            }

    
    
    
    
    
    
    
    
    
    
    
            public boolean addOffre(Offre t) {
            try {
                String servicee = t.getServiceN();
                String sservicee = t.getSous_serviceN();
                String adressee = t.getOffre_adresse();
                String descriptionn = t.getOffre_description();
                String imagess = t.getOffre_image();
                
                //System.out.println(titre+" "+subject);
                            req.setPost(false);


            String url = Statics.BASE_URL + "ajoutOffreJ/1"+"?servicee=" + servicee + "&sservicee=" + sservicee+ "&adressee=" + adressee+ "&descriptionn=" + descriptionn+ "&imagess=" + imagess;
            
            req.setUrl(url);
            System.out.println(req.getUrl());

            req.addResponseListener((e) -> {

                String str = new String(req.getResponseData());
                //System.out.println("data == " + str);
            });
            //req.onRedirect(url);
            NetworkManager.getInstance().addToQueueAndWait(req);

            return true;
        } catch (Exception e) {
            return false;
        }
        }
            
            
            
            
            
            
            
            
            
            
            
            
    public ArrayList<Offre> parseReclam(String jsonText) {
    try {
        offr = new ArrayList<>();
        JSONParser j = new JSONParser();
        Map<String, Object> reclamListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
        System.out.println(reclamListJson);
        List<Map<String, Object>> list = (List<Map<String, Object>>) reclamListJson.get("root");
        System.out.println(list);
        for (Map<String, Object> obj : list) {
            Offre t = new Offre();
            //System.out.println(obj);
            //t.setId(Integer.parseInt(obj.get("id").toString()));
            float id = Float.parseFloat(obj.get("id").toString());
                t.setOffre_id((int) id);
                
                if(obj.get("adr")==null){
                t.setOffre_adresse("ff");
                }else{
            t.setOffre_adresse(obj.get("adr").toString());
                }
                
                
                if(obj.get("desc")==null){
                t.setOffre_description("null");
                }else{
            t.setOffre_description(obj.get("desc").toString());}
                
                
                if(obj.get("img")==null){
                t.setOffre_image("null");
                }else{
            t.setOffre_image(obj.get("img").toString());}
                
                
                if(obj.get("ser")==null){
                t.setServiceN("null");
                }else{
            t.setServiceN(obj.get("ser").toString());}
                
                
                if(obj.get("sser")==null){
                t.setSous_serviceN("null");
                }else{
            t.setSous_serviceN(obj.get("sser").toString());}
                
                /*
                if((Date)obj.get("dt")==null){
                t.setOffre_date(null);
                }else{
            t.setOffre_date((Date)obj.get("dt"));}
                */
            offr.add(t);
            
        }
    } catch (IOException ex) {
        ex.printStackTrace();
    }
    System.out.println(offr);
    return offr;
}
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
    public ArrayList<Offre> getAllOffre() {
    String url = Statics.BASE_URL + "offresJ/";
    req.setUrl(url);
    req.setPost(false);
    req.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
            offr = parseReclam(new String(req.getResponseData()));
            req.removeResponseListener(this);
        }
    });
    NetworkManager.getInstance().addToQueueAndWait(req);
        return offr;
    }

    
}
