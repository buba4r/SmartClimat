package model;

import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Classe qui représente une annéee 
 * @author GHEMID
 */
public class Annee {

    private String annee_value;
    private List<Mois> mois_list;

    /**
     * Constructeur de la classe Annee
     * @param annee_value la valeur de l'année
     * @param mois_list la liste des mois qui correspondent à l'année choisie 
     */
    public Annee(String annee_value, List<Mois> mois_list) {
       
        try{
        this.mois_list = new ArrayList<>();
        this.mois_list.addAll(mois_list);
        this.annee_value = annee_value;
        }
        catch(NullPointerException e) {}
    }

    /**
     * Calcule la moyenne des données climatiques de l'année
     * @return l'objet dataclimate qui contient les moyennes des différents types de données climatiques 
     */
    public DataClimate getMoyenneAnnee() {
        DataClimate dataclimate = new DataClimate("0", "0", "0", "0");
        float temp_k = 0, temp_c = 0;
        int humidite = 0, nebulosite = 0;
        for (int i = 0; i < getMois_list().size(); i++) {
            temp_k += Float.parseFloat(getMois_list().get(i).getMoyenneMois().getTemp_k());
            temp_c += Float.parseFloat(getMois_list().get(i).getMoyenneMois().getTemp_c());
            humidite += Integer.parseInt(getMois_list().get(i).getMoyenneMois().getNebulosite());
            nebulosite += Integer.parseInt(getMois_list().get(i).getMoyenneMois().getHumidite());
        }

        dataclimate.setTemp_k(String.valueOf(temp_k / getMois_list().size()));
        dataclimate.setTemp_c(String.valueOf(temp_c / getMois_list().size()));
        dataclimate.setNebulosite(String.valueOf(nebulosite / getMois_list().size()));
        dataclimate.setHumidite(String.valueOf(humidite / getMois_list().size()));

        return dataclimate;
    }

    /**
     * Getter valeur d'année
     * @return la valeur de l'année 
     */
    public String getAnnee_value() {
        return annee_value;
    }

    /**
     * Setter valeur d'année
     * @param annee_value la valeur de l'année
     */
    public void setAnnee_value(String annee_value) {
        this.annee_value = annee_value;
    }

    /**
     * Getter Récupérer la liste des mois associés à l'année
     * @return la liste des mois associés à cet année
     */
    public List<Mois> getMois_list() {
        return mois_list;
    }

    /**
     * Setter pour la liste des mois associés à l'année
     * @param mois_list la liste des mois associés à l'année
     */
    public void setMois_list(List<Mois> mois_list) {
        this.mois_list.addAll(mois_list);
    }
    
    /**
     * Setter pour la liste des mois associés à l'année
     * @param mois_list la liste des mois associés à l'année
     */
    public void AddMois(Mois mois) {
        this.mois_list.add(mois);
    }

}
