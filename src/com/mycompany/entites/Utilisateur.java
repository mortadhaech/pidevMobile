package com.mycompany.entites;

public class Utilisateur {
      private int id, numtel;
    private String nom, prenom, adresse;
     private String mdp;
   
    private String role;
    private String photo;
    private String description;
    private int rate;
    private String profession;
    private boolean verified;
    private int ids;

    public Utilisateur(int id, int numtel, String nom, String prenom, String adresse, String mdp, String role, String photo, String description, int rate, String profession, boolean verified, int ids) {
        this.id = id;
        this.numtel = numtel;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.mdp = mdp;
        this.role = role;
        this.photo = photo;
        this.description = description;
        this.rate = rate;
        this.profession = profession;
        this.verified = verified;
        this.ids = ids;
    }
//mobile
    public Utilisateur(int id, String nom, String prenom, int numtel, String adresse, String description, String photo,
            int rate, boolean verified, String profession, String role, int ids) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.numtel = numtel;
        this.adresse = adresse;
        this.description = description;
        this.photo = photo;
        this.rate = rate;
        this.verified = verified;
        this.profession = profession;
        this.role = role;
        this.ids = ids;
    }
    //
    
    public Utilisateur(int numtel, String nom, String prenom, String adresse, String mdp, String role, String photo, String description, int rate, String profession, boolean verified, int ids) {
        this.numtel = numtel;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.mdp = mdp;
        this.role = role;
        this.photo = photo;
        this.description = description;
        this.rate = rate;
        this.profession = profession;
        this.verified = verified;
        this.ids = ids;
    }

    public Utilisateur(int id, int numtel, String nom, String prenom, String adresse, String mdp, String role) {
        this.id = id;
        this.numtel = numtel;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.mdp = mdp;
        this.role = role;
    }

    public Utilisateur(int numtel, String nom, String prenom, String adresse, String mdp, String role) {
        this.numtel = numtel;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.mdp = mdp;
        this.role = role;
    }

    public Utilisateur() {
    }
   

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public int getIds() {
        return ids;
    }

    public void setIds(int ids) {
        this.ids = ids;
    }
   
   
   
   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumtel() {
        return numtel;
    }

    public void setNumtel(int numtel) {
        this.numtel = numtel;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

   
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }
    public boolean getVerified() {
        return verified;
    }

   

    @Override
    public String toString() {
        return "Utilisateur{" + "numtel=" + numtel + ", nom=" + nom + ", prenom=" + prenom + ", adresse=" + adresse + '}';
    }
   
}