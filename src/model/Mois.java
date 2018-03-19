/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import model.Jour;
import model.DataClimate;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe qui représente un mois d'une donnée climatique
 * @author GHEMID
 */
public class Mois {
    
    private String mois_value;
    private List<Jour> list_jour;
   
    /**
     * Constructeur d'un mois
     * @param mois_value valeur du mois
     * @param list_jour la liste des jours associté à ce mois
     */
    public Mois(String mois_value, List<Jour> list_jour) {
        this.list_jour = new ArrayList<>();
        this.list_jour.addAll(list_jour);
        this.mois_value = mois_value;
       
    }
    
    /**
     * Methode qui retourne la moyenne du mois en faisant la moyenne des jours associés à ce mois
     * @return l'objet DataClimat qui contient la moyenne de chaque type de données climatique
     */
    public DataClimate getMoyenneMois()
    {
        DataClimate dataclimate = new DataClimate("0", "0", "0", "0");
        float temp_k = 0,temp_c = 0;
        int humidite = 0,nebulosite = 0;
        for(int i=0; i<getList_jour().size(); i++)
        {
            temp_k +=  Float.parseFloat(getList_jour().get(i).GetMoyenneJour().getTemp_k());
            temp_c +=  Float.parseFloat(getList_jour().get(i).GetMoyenneJour().getTemp_c());
            humidite += Integer.parseInt(getList_jour().get(i).GetMoyenneJour().getNebulosite());
            nebulosite += Integer.parseInt(getList_jour().get(i).GetMoyenneJour().getHumidite());
        }
        
        dataclimate.setTemp_k(String.valueOf(temp_k/getList_jour().size()));
        dataclimate.setTemp_c(String.valueOf(temp_c/getList_jour().size()));
        dataclimate.setNebulosite(String.valueOf(nebulosite/getList_jour().size()));
        dataclimate.setHumidite(String.valueOf(humidite/getList_jour().size()));
        
        return dataclimate;
    }

    /**
     * getter la valeur du mois
     * @return retourne la valeur du mois
     */
    public String getMois_value() {
        return mois_value;
    }

    /**
     * setter du mois
     * @param mois_value valeur du mois
     */
    public void setMois_value(String mois_value) {
        this.mois_value = mois_value;
    }

    /**
     * getter liste des jours associés à ce mois
     * @return liste des jours associés à ce mois
     */
    public List<Jour> getList_jour() {
        return list_jour;
    }

    /**
     * setter list des jour associés à ce mois
     * @param list_jour liste des jours associés à ce mois
     */
    public void setList_jour(List<Jour> list_jour) {
        this.list_jour.addAll(list_jour);
    }
    
    /**
     * setter list des jour associés à ce mois
     * @param list_jour liste des jours associés à ce mois
     */
    public void Addjour(Jour jour) {
        this.list_jour.add(jour);
    }
   
  
    
}
