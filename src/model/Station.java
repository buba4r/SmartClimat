/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe qui représente une station d'une données climatique
 * @author GHEMID
 */
public class Station {

    private String idStation;
    private String nomStation;
    private List<Annee> annee;

    /**
     * Constructeur Station
     * @param nomStation nom du station
     * @param idStation l'iditenficateur de la station
     * @param annee l'année de la station associté à la donnée climatique
     */
    public Station(String nomStation, String idStation, List<Annee> annee) {
        this.annee = new ArrayList<>();
        this.annee.addAll(annee);
        this.nomStation = nomStation;
        this.idStation = idStation;
    }

    /**
     * Methode qui retourne la liste des données climatiques associées à une journée
     * @param annee l'année de la donnée climatique 
     * @param mois le mois de la donnée climatique
     * @param jour le jour de la donnée climatique
     * @return la liste des données climatique associées à une journée entrée en paramètre
     */
    public List<DataClimate> getDataJour(String annee, String mois, String jour) {
        List<DataClimate> dataclimate = new ArrayList<>();

        boolean found = false;
        for (int i = 0; i < getAnnee().size(); i++) {
            if (getAnnee().get(i).getAnnee_value().equals(annee)) {
                for (int j = 0; j < getAnnee().get(i).getMois_list().size(); j++) {
                    if (getAnnee().get(i).getMois_list().get(j).getMois_value().equals(mois)) {
                        for (int k = 0; k < getAnnee().get(i).getMois_list().get(j).getList_jour().size(); k++) {
                            if (getAnnee().get(i).getMois_list().get(j).getList_jour().get(k).getJour_value().equals(jour)) {
                                for (int l = 0; l < getAnnee().get(i).getMois_list().get(j).getList_jour().get(k).getList_heure().size(); l++) {
                                    dataclimate.add(getAnnee().get(i).getMois_list().get(j).getList_jour().get(k).getList_heure().get(l).getDataclimate());
                                    found = true;
                                }

                            }
                        }
                    }
                }
            }
        }

        if (!found) {
            //JOptionPane.showMessageDialog(null, "Il n'y a pas de données pour cette date.");
        }

        return dataclimate;
    }

   /**
     * Methode qui retourne la liste des données climatiques associées à un mois
     * @param annee l'année de la donnée climatique 
     * @param mois le mois de la donnée climatique
     * @return la liste des données climatique associées à un mois entrée en paramètre
     */
    public List<DataClimate> getDataMois(String annee, String mois) {

        List<DataClimate> dataclimate = new ArrayList<>();

        boolean found = false;
        for (int i = 0; i < getAnnee().size(); i++) {
            
            if (getAnnee().get(i).getAnnee_value().equals(annee)) {
                //if(i%8==0)
                for (int j = 0; j < getAnnee().get(i).getMois_list().size(); j++) {
                    if (getAnnee().get(i).getMois_list().get(j).getMois_value().equals(mois)) {
                        
                        for(int s=0; s<getAnnee().get(i).getMois_list().get(j).getList_jour().size(); s++)
                        {
                        dataclimate.add(getAnnee().get(i).getMois_list().get(j).getList_jour().get(s).GetMoyenneJour());
                        found = true;
                        }

                    }
                }
            }
        }

        if (!found) {
            //JOptionPane.showMessageDialog(null, "Il n'y a pas de données pour cette date.");
        }

        return dataclimate;

    }

    /**
     * Methode qui retourne la liste des données climatiques associées à une année
     * @param annee l'année de la donnée climatique 
     * @return la liste des données climatique associées à une annéee entrée en paramètre
     */
    public List<DataClimate> getDataAnnee(String annee) {

        List<DataClimate> dataclimate = new ArrayList<>();
        boolean found = false;
        for (int i = 0; i < getAnnee().size(); i++) {
            
            if (getAnnee().get(i).getAnnee_value().equals(annee)) {
                //if(i%16==0)
                for (int j = 0; j < getAnnee().get(i).getMois_list().size(); j++) {

                    dataclimate.add(getAnnee().get(i).getMois_list().get(j).getMoyenneMois());
                    found = true;

                }

            }
        }

        

        if (!found) {
            //JOptionPane.showMessageDialog(null, "Il n'y a pas de données pour cette date.");
        }

        return dataclimate;
    }
    
    /**
     * getter de l'identificateur de la station
     * @return l'id de la station
     */
    public String getIdStation() {
        return idStation;
    }

    /**
     * setter de l'identificateur de la statioon
     * @param idStation l'identificateur de la station
     */
    public void setIdStation(String idStation) {
        this.idStation = idStation;
    }

    /**
     * getter nom de la station
     * @return nom de la station
     */
    public String getNomStation() {
        return nomStation;
    }

    /**
     * setter du nom de la station
     * @param nomStation Nom de la station
     */
    public void setNomStation(String nomStation) {
        this.nomStation = nomStation;
    }

    /**
     * getter liste des années associées à cette station
     * @return liste des années associées à cette stations
     */
    public List<Annee> getAnnee() {
        return annee;
    }

    /**
     * setter de la liste des données associés à cette station
     * @param annee la liste des années associés à cette stations
     */
    public void setAnnee(List<Annee> annee) {
        //this.annee = new ArrayList<>();
        this.annee.addAll(annee);
    }
    
    /**
     * setter de la liste des données associés à cette station
     * @param annee la liste des années associés à cette stations
     */
    public void AddAnnee(Annee annee) {
        //this.annee = new ArrayList<>();
        this.annee.add(annee);
    }

}
