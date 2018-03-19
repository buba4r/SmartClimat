/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import model.DataClimate;
import java.util.List;

/**
 * Classe qui représente l'heure de la données climatique
 * @author GHEMID
 */
public class Heure {
    
    private String heure_value;
    private DataClimate dataclimate;

    /**
     * Constructeur heure
     * @param heure_value la valeur de l'heure 
     * @param dataclimate les données climatique associé à cette heure
     */
    public Heure(String heure_value, DataClimate dataclimate) {
        this.heure_value = heure_value;
        this.dataclimate = dataclimate;
    }

    /**
     * getter heure
     * @return retourne la valeur de l'heure
     */
    public String getHeure_value() {
        return heure_value;
    }

    /**
     * setter heure
     * @param heure_value la valeur de l'heure
     */
    public void setHeure_value(String heure_value) {
        this.heure_value = heure_value;
    }

    /**
     * getter Données climatique associé à cette heure
     * @return données climatique assoié à cette heure
     */
    public DataClimate getDataclimate() {
        return dataclimate;
    }

    /**
     * setter données climatique associé à cette heure
     * @param dataclimate données climatique associé à cette heure
     */
    public void setDataclimate(DataClimate dataclimate) {
        this.dataclimate = dataclimate;
    }
    
    
    
}
