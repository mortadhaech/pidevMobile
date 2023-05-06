/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;

import com.mycompany.myapp.entities.Reclamation;
import static com.mycompany.myapp.services.serviceReclamation.instance;
import com.mycompany.myapp.utils.Statics;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 *
 * @author USER
 */
public class serviceReclamation {
    public ArrayList<Reclamation> reclam;
    
    public static serviceReclamation instance = null;
    public boolean resultOK;
    public ConnectionRequest req;
    
    private serviceReclamation() {
        req = new ConnectionRequest();
    }
    
            public static serviceReclamation getInstance() {
                if (instance == null) {
                    instance = new serviceReclamation();
                }
                return instance;
            }

            public boolean addReclamtion(Reclamation t) {
            try {
                String titre = t.getTitle();
            String subject =  t.getSubject() ;
                System.out.println(titre+" "+subject);
                            req.setPost(false);


            String url = Statics.BASE_URL + "ajouterreclamJsoon/81"+"?titre=" + titre + "&desc=" + subject;
            String urll =  "127.0.0.1:8000/ajouterreclamJsoon/81"+"?titre=" + titre + "&desc=" + subject;
            //System.out.println(url);
            req.setUrl(urll);
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

    
    
    public ArrayList<Reclamation> parseReclam(String jsonText) {
    try {
        reclam = new ArrayList<>();
        JSONParser j = new JSONParser();
        Map<String, Object> reclamListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
        System.out.println(reclamListJson);
        List<Map<String, Object>> list = (List<Map<String, Object>>) reclamListJson.get("root");
        System.out.println(list);
        for (Map<String, Object> obj : list) {
            Reclamation t = new Reclamation();
            //t.setId(Integer.parseInt(obj.get("id").toString()));
            float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int) id);
                if(obj.get("title")==null){
                t.setTitle("null");
                }else{
            t.setTitle(obj.get("title").toString());
                }
                if(obj.get("subject")==null){
                t.setSubject("null");
                }else{
            t.setSubject(obj.get("subject").toString());}
                
                if((Date)obj.get("date")==null){
                t.setDate(null);
                }else{
            t.setDate((Date)obj.get("date"));}
                if(obj.get("consulter")==null){
                t.setConsulter("null");
                }else{
            t.setConsulter(obj.get("consulter").toString());}
            reclam.add(t);
        }
    } catch (IOException ex) {
        ex.printStackTrace();
    }
    return reclam;
}

    
    
    public ArrayList<Reclamation> getAllReclam() {
    String url = Statics.BASE_URL + "showreclamuserJsONALL/";
    //req = new ConnectionRequest();
    req.setUrl(url);
    req.setPost(false);
    req.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
            reclam = parseReclam(new String(req.getResponseData()));
            req.removeResponseListener(this);
        }
    });
    NetworkManager.getInstance().addToQueueAndWait(req);
        return reclam;
    }

    
    public boolean suppReclamation(Reclamation t)
    {
        
         String url = Statics.BASE_URL + "supprimerreclamationJsoon/"+t.getId() ;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
             @Override
             public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);             }
         });
          NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
        
        
        
    }
}
