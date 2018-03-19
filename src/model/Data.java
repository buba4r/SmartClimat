/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javafx.beans.property.SimpleStringProperty;

/**
 * Classe necessaire pour le fonctionnement de TableView
 * @author GHEMID
 */
public class Data {

    private final SimpleStringProperty date;
    private final SimpleStringProperty station;
    private final SimpleStringProperty temp_k;
    private final SimpleStringProperty temp_c;
    private final SimpleStringProperty humidite;
    private final SimpleStringProperty nebulosite;

    public Data(String date, String station, String temp_k, String temp_c, String humidite, String nebulosite) {
        this.temp_c = new SimpleStringProperty(temp_c);
        this.temp_k = new SimpleStringProperty(temp_k);
        this.humidite = new SimpleStringProperty(humidite);
        this.nebulosite = new SimpleStringProperty(nebulosite);
        this.station = new SimpleStringProperty(station);
        this.date = new SimpleStringProperty(date);
    }

    /**
     * Getter station
     * @return retourne la station
     */
    public String getStation() {
        return station.get();
    }

    /**
     * getter date
     * @return retourne la date 
     */
    public String getDate() {
        return date.get();
    }

    /**
     * getter température en Kelvin
     * @return retourn la température en K
     */
    public String getTemp_k() {
        return temp_k.get();
    }

    /**
     * getter température en Celsuis
     * @return retourne la température en C
     */
    public String getTemp_c() {
        return temp_c.get();
    }

    /**
     * getter Humidité en %
     * @return retourne l'humidité en %
     */
    public String getHumidite() {
        return humidite.get();
    }

    /**
     *  getter nébulosité en %
     * @return retourne la nébulosité en %
     */
    public String getNebulosite() {
        return nebulosite.get();
    }

    /**
     * Setter station
     * @param stationd valeur de la station 
     */
    public void setStation(String stationd) {
        station.set(stationd);
    }

    /**
     * Setter température en K
     * @param Tk valeur de la température en Kelvin
     */
    public void setTemp_k(String Tk) {
        temp_k.set(Tk);
    }

    /**
     * Setter Date
     * @param datex valeur de la date
     */
    public void setDate(String datex) {
        date.set(datex);
    }

    /**
     * setter de la température 
     * @param Tc valeur de la témpérature en Celsus
     */
    public void setTemp_c(String Tc) {
        temp_c.set(Tc);
    }

    /**
     * Setter Humidité
     * @param h valeur de l'humidité en %
     */
    public void setHumidite(String h) {
        humidite.set(h);
    }

    /**
     * Setter de la nébulosité
     * @param nb valeur de la nébulosité en %
     */
    public void setNebulosite(String nb) {
        nebulosite.set(nb);
    }

}
