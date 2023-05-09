/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.services;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.io.Storage;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.Resources;
import com.company.gui.ProfileForm;
import com.company.utils.Statics;
import java.util.Map;
import com.company.gui.SessionManager;
import com.company.gui.SignUpForm;
/**
 *
 * @author lordg
 */
public class UtilisateurService {
     public static UtilisateurService instance = null ;
    
    public static boolean resultOk = true;
    String json;

    //initilisation connection request 
    private ConnectionRequest req;
    
    
    public static UtilisateurService getInstance() {
        if(instance == null )
            instance = new UtilisateurService();
        return instance ;
    }
    
    
    
    public UtilisateurService() {
        req = new ConnectionRequest();
        
    }
    
    //Signup
    public void signup(TextField nom,TextField prenom,TextField password,TextField num,TextField email,TextField confirmPassword, ComboBox<String> roles , Resources res ) {
        
     
        
        String url = Statics.BASE_URL+"/user/signup?nom="+nom.getText().toString()+"&prenom="+prenom.getText().toString()+"&numero="+num.getText().toString()+"&email="+email.getText().toString()+
                "&password="+password.getText().toString();
        
        req.setUrl(url);
       
        //Control saisi
        if(nom.getText().equals(" ") &&prenom.getText().equals(" ") &&num.getText().equals(" ") && password.getText().equals(" ") && email.getText().equals(" ")) {
            
            Dialog.show("Erreur","Veuillez remplir les champs","OK",null);
            
        }
        
        //hethi wa9t tsir execution ta3 url 
        req.addResponseListener((e)-> {
         
            //njib data ly7atithom fi form 
            byte[]data = (byte[]) e.getMetaData();//lazm awl 7aja n7athrhom ke meta data ya3ni na5o id ta3 kol textField 
            String responseData = new String(data);//ba3dika na5o content 
            
            System.out.println("data ===>"+responseData);
        }
        );
        
        
        //ba3d execution ta3 requete ely heya url nestanaw response ta3 server.
        NetworkManager.getInstance().addToQueueAndWait(req);
        
            
        
    }
        public void signin(TextField username,TextField password, Resources rs ) {
        
        
        String url = Statics.BASE_URL+"/user/signin?username="+username.getText().toString()+"&password="+password.getText().toString();
        req = new ConnectionRequest(url, false); //false ya3ni url mazlt matba3thtich lel server
        req.setUrl(url);
        
        req.addResponseListener((e) ->{
            
            JSONParser j = new JSONParser();
            
            String json = new String(req.getResponseData()) + "";
            
            
            try {
            
            if(json.equals("passowrd not found")) {
                Dialog.show("Echec d'authentification","Username ou mot de passe éronné","OK",null);
            }
            else {
                System.out.println("data =="+json);
                
                Map<String,Object> user = j.parseJSON(new CharArrayReader(json.toCharArray()));
                
                
             
                //Session 
                float id = Float.parseFloat(user.get("id").toString());
                SessionManager.setId((int)id);//jibt id ta3 user ly3ml login w sajltha fi session ta3i
                
                SessionManager.setPassowrd(user.get("password").toString());
                SessionManager.setUserName(user.get("nom").toString());
                SessionManager.setEmail(user.get("username").toString());
                
                //photo 
                
                if(user.get("photo") != null)
                    SessionManager.setPhoto(user.get("photo").toString());
                
                
               // if(user.size() >0 ) // l9a user
                   // new ListReclamationForm(rs).show();//yemchi lel list reclamation
                    //new ProfileForm(rs).show();
                    
                    }
            
            }catch(Exception ex) {
                ex.printStackTrace();
            }
            
            
            
        });
    
         //ba3d execution ta3 requete ely heya url nestanaw response ta3 server.
        NetworkManager.getInstance().addToQueueAndWait(req);
    }
   //edit user
public  void EditUser (String username, String password, String email) {
String url = Statics. BASE_URL +"/user/editUser?username="+username+"&password="+password+"+&email="+email;
MultipartRequest req= new MultipartRequest();

req.setUrl(url);
req.setPost(true);
req. addArgument("id", String.valueOf(SessionManager.getId()));
req. addArgument("username", username);
req.addArgument("password", password);
req. addArgument("email", email);
System.out.println (email);
req. addResponseListener((response) -> {
byte[] data =(byte[]) response.getMetaData();
String s = new String (data);
System.out.println(s);
if (s.equals("success")) {
} else {
Dialog.show("Erreur", "Echec de modification", "Ok", null);
}
});
NetworkManager.getInstance().addToQueueAndWait(req);
}
public static void delete(){
    int id =SessionManager.getId();
    String url = Statics. BASE_URL +"/user/delete?id="+id;
MultipartRequest req= new MultipartRequest();

req.setUrl(url);
req.setPost(true);
req. addArgument("id", String.valueOf(id));
Dialog.show("compte", "compte supprimer", "Ok", null);
}
public void deleteUser(int id) {
        String url = Statics.BASE_URL + "/user/delete?id="+id;
        req.removeAllArguments();
        req.setUrl(url);
        req.setPost(false);
        req.setHttpMethod("DELETE");
        req.addArgument("id", String.valueOf(id));
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                if (req.getResponseCode() == 200) {
                   SessionManager.pref.clearAll();//nfargh sessionI
Storage.getInstance().clearStorage ();
Storage.getInstance().clearCache();
System.out.println (SessionManager.getUserName());
                }
                req.removeResponseListener(this);}
            
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        
    }
}
