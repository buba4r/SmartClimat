/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 * classe qui représente une donnée climatique 
 * @author GHEMID
 */
public class DataClimate {

    private String temp_k;
    private String temp_c;
    private String humidite;
    private String nebulosite;

    /**
     * Constructeur Classe DataClimate
     * @param temp_k valeur de la température en Kelvin
     * @param temp_c valeur de la témpérature en Celsus
     * @param humidite valeur de l'huimidté en %
     * @param nebulosite valkeur de la nébulosité en %
     */
    public DataClimate(String temp_k, String temp_c, String humidite, String nebulosite) {
        this.temp_k = temp_k;
        this.temp_c = temp_c;
        this.humidite = humidite;
        this.nebulosite = nebulosite;
    }

    /**
     * getter temperature en kelvin
     * @return retourne la température en kelvin
     */
    public String getTemp_k() {
        return temp_k;
    }

    /**
     * setter température en Kelvin
     * @param temp_k valeur de la température en kelvin
     */
    public void setTemp_k(String temp_k) {
        this.temp_k = temp_k;
    }

    /**
     * getter température en celsus
     * @return retourne la température en Celsus
     */
    public String getTemp_c() {
        return temp_c;
    }

    /**
     * setter température en Celsus
     * @param temp_c valeur de la température en celsus
     */
    public void setTemp_c(String temp_c) {
        this.temp_c = temp_c;
    }

    /**
     * getter huimidité en %
     * @return retourne l'humidité en %
     */
    public String getHumidite() {
        return humidite;
    }

    /**
     * setter humidité en %
     * @param humidite valeur de l'humidité en %
     */
    public void setHumidite(String humidite) {
        this.humidite = humidite;
    }

    /**
     * getter nébulosité en %
     * @return retourne la nébulosité en %
     */
    public String getNebulosite() {
        return nebulosite;
    }

    /**
     * Setter nébulosité en %
     * @param nebulosite valeur de la nébulosité en %
     */
    public void setNebulosite(String nebulosite) {
        this.nebulosite = nebulosite;
    }
    
    
    
}
