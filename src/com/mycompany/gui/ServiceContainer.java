/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.UIBuilder;
import com.mycompany.entites.Services;
import static com.mycompany.myapp.MyApplication.theme;
import com.mycompany.services.ServicesService;

public class ServiceContainer extends Container{
        private Container c;
    private Label nom;
    private Label des;
    private Label nbService;
    private String imgS;
    private Button btnSupp;
    private Button btnModifier;
        private Services service;

  public void setServiceInfo(Services service) {
        nom.setText(service.getService_nom());
        des.setText(service.getService_description());
        nbService.setText(Integer.toString(service.getNb_sous_services()));
    
  btnSupp.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent evt) {
        if (service != null) {
            ServicesService.getInstance().deleteService(service.getService_id());
            
            
            ListService listService = new ListService();
    
        }
    }
});
  
  
      btnModifier.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent evt) {
        if (service != null) {
          
            
            
            
            
            GererService g=new GererService();
           g.ModifierServiceForm(service);
        }
    }
});
  }
        public ServiceContainer() {
        super();
        UIBuilder uIBuilder = new UIBuilder();
        c = uIBuilder.createContainer(theme, "AfficherService");
        nom = (Label) uIBuilder.findByName("NomS", c);
        des = (Label) uIBuilder.findByName("desS", c);
        nbService = (Label) uIBuilder.findByName("nbS", c);
        imgS = "hello";
        btnSupp = (Button) uIBuilder.findByName("btnSup", c);
        btnModifier = (Button) uIBuilder.findByName("btnMod", c);

       
        
    
        
        
        }

  

    public Container getC() {
        return c;
    }



}
