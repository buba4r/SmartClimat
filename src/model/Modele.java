/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.GZIPInputStream;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.stage.FileChooser;
import javax.swing.JOptionPane;
import controller.Controller;
import org.apache.commons.io.FileUtils;
import util.DownloadFileWithProxy;

/**
 * Classe qui sert d'interface entre les classes du domaines et le controlleur
 *
 * @author GHEMID
 */
public class Modele {

    public List<Station> station = new ArrayList<>();

    /**
     * ligne propre à l'affichage des données dans une tableView
     */
    public ObservableList<Data> data = FXCollections.observableArrayList();

    /**
     * liste propres à l'affichage des données
     */
    public List<String> donnes;

    /**
     * les stations associés à ce modèle
     */
    public ArrayList<Station> station_list;
    List<String> list_donnee = new ArrayList<>();
    private String nom_fichier;

    /**
     * Methode qui fait un balayage des fichier qui existent déjà dans le
     * repértoire local lancer au démmarage de l'application
     *
     * @return la liste données qui existent
     */
    public List<String> download_existing_files() {
        String mois = null, jour = null, heure = null;
        for (int i = 1996; i < 2018; i++) {
            for (int j = 1; j < 13; j++) {
                if (j < 10) {
                    mois = "0" + String.valueOf(j);
                } else {
                    mois = String.valueOf(j);
                }
                File f = new File("synop." + String.valueOf(i) + mois + ".csv.gz.txt");
                if (f.exists()) {
                    Save_data(f);
                }
            }
        }

        for (int i = 1996; i < 2018; i++) {
            for (int j = 1; j < 13; j++) {
                if (j < 10) {
                    mois = "0" + String.valueOf(j);
                } else {
                    mois = String.valueOf(j);
                }
                for (int k = 1; k < 32; k++) {
                    if (k < 10) {
                        jour = "0" + String.valueOf(k);
                    } else {
                        jour = String.valueOf(k);
                    }
                    for (int x = 0; x < 22; x++) {
                        if (x < 10) {
                            heure = "0" + String.valueOf(x);
                        } else {
                            heure = String.valueOf(x);
                        }

                        File f = new File("synop." + String.valueOf(i) + mois + jour + heure +".csv.txt");
                        if (f.exists()) {
                            Save_data(f);
                        }

                    }

                }

            }
        }

        return list_donnee;
    }

    /**
     * Methode qui sauvegarde les données dans des objets (RAM)
     *
     * @param file le fichier à sauvegarder
     * @return le nom de fichier pour l'afficher sur la rubrique des données
     */
    public String Save_data(File file) {
        String last_last_1 = null;
        String last_last_2 = null;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            final String fileName = file.toURI().toString();
            String fileNamelast = null;
            String[] fileNames = fileName.toString().split("/");
            String last_part = fileNames[fileNames.length-1];
            String[] parts = last_part.split("\\.");
            String last_last_part = parts[1];
            last_last_1 = last_last_part.substring(0, 4);
            last_last_2 = last_last_part.substring(4, 6);
            List<String> row = new ArrayList<>();
            String line;
            donnes = new ArrayList<>();
            boolean line_one = false;
            double val_proggress = 0.0;
            while ((line = br.readLine()) != null) {
                if (line_one) {
                    row.clear();
                    donnes.add(line);
                    int i = 0;
                    for (String retval : line.split(";")) {

                        if (i == 0 || i == 1 || i == 7 || i == 9 || i == 14) {
                            row.add(retval);

                        }

                        i++;
                    }

                    if (row.size() == 5) {
                        String c;
                        if (!row.get(2).equals("mq")) {
                            Float kelvin = Float.parseFloat(row.get(2));
                            Float celsius = kelvin - 273.15F;
                            c = Float.toString(celsius);
                        } else {
                            c = "0";
                        }
                        //conversion des erreurs : 
                        if (row.get(0).equals("mq")) {
                            row.set(0, "0");
                        }
                        if (row.get(1).equals("mq")) {
                            row.set(1, "0");
                        }
                        if (row.get(2).equals("mq")) {
                            row.set(2, "0");
                        }
                        if (row.get(3).equals("mq")) {
                            row.set(3, "0");
                        }
                        if (row.get(4).equals("mq")) {
                            row.set(4, "0");
                        }

                        add_or_create(row, c);

                    }
                }
                line_one = true;

            }

        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);

        }
        list_donnee.add("DATA existe, Date : " + last_last_1 + " - " + last_last_2);
        return "DATA existe, Date : " + last_last_1 + " - " + last_last_2;
    }

    /**
     * Methode qui retourne le nom de la station à partir de l'id de la station
     *
     * @param IdStations l'identificateur de la station
     * @return le nom de station qui correspond à l'id de la station passé en
     * paramètre
     */
    public String IdStationToStation(String IdStations) {

        String nomStation = null;
        for (int i = 0; i < station.size(); i++) {
            if (station.get(i).getIdStation().equals(IdStations)) {
                nomStation = station.get(i).getNomStation();
            }

        }

        return nomStation;
    }

    /**
     * Methode qui télécharger le fichier des données du site directement
     *
     * @param jour jour du fichier à telecharger
     * @param mois mois du fichier à telecharger
     * @param annee annee du fichier à telecharger
     * @param heure heure du fichier à telecharger
     * @return nom du fichier télécharger
     * @throws MalformedURLException
     * @throws IOException
     */
    public String Download_File(String jour, String mois, String annee, String heure) throws MalformedURLException, IOException {

        if (jour.equals("NULL") && heure.equals("NULL")) {

            URL url = new URL("https://donneespubliques.meteofrance.fr/donnees_libres/Txt/Synop/Archive/synop." + annee + mois + ".csv.gz");
            File file = new File("synop." + annee + mois + ".csv.gz");
            DownloadFileWithProxy.copyURLToFile(url, file);

            FileInputStream in = new FileInputStream("synop." + annee + mois + ".csv.gz");
            GZIPInputStream zipin = new GZIPInputStream(in);
            byte[] buffer = new byte[8192];
            FileOutputStream out = new FileOutputStream("synop." + annee + mois + ".csv.gz.txt");
            int length;
            while ((length = zipin.read(buffer, 0, 8192)) != -1) {
                out.write(buffer, 0, length);
            }
            out.close();
            zipin.close();

            File f = new File("synop." + annee + mois + ".csv.gz.txt");
            if (f.exists()) {
                nom_fichier = Save_data(f);
            } else {
                JOptionPane.showMessageDialog(null, "Fichier inexistant ou n'a pas pu être téléchargé !");
            }

        } else {
            if (annee.equals("2017")) {

                URL url = new URL("https://donneespubliques.meteofrance.fr/donnees_libres/Txt/Synop/Archive/synop." + annee + mois + jour + heure + ".csv.gz");
                File file = new File("synop." + annee + mois + jour + heure + ".csv");
                DownloadFileWithProxy.copyURLToFile(url, file);

                File f = new File("synop." + annee + mois + jour + heure + ".csv");
                if (f.exists()) {
                    nom_fichier = Save_data(f);
                } else {
                    JOptionPane.showMessageDialog(null, "Fichier inexistant ou n'a pas pu être téléchargé !");
                }

            } else {
                JOptionPane.showMessageDialog(null, "Seulement l'année 2017 disponible pour cette option !");
            }

        }

        return nom_fichier;
    }

    /**
     * Methode qui vérifie s'il y a une connexin internet
     *
     * @return vrai s'il y a une connexion, faux s'il y a pas de connexion
     */
    public static boolean netIsAvailable() {
        try {
            final URL url = new URL("http://www.google.com");
            final URLConnection conn = url.openConnection();
            conn.connect();
            return true;
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * Methode qui découpe la date en Année/Jour/Mois/Heure
     *
     * @param date la date entière en string de la forme 2016010206
     * @return une liste de date découpés s[0] = année, s[1] = mois, s[2] =
     * jour, s[4] = heure,
     */
    public ArrayList<String> split_date(String date) {
        ArrayList<String> s = new ArrayList<>();

        String annee, mois, jour, heure;

        annee = date.substring(0, 4);
        mois = date.substring(4, 6);
        jour = date.substring(6, 8);
        heure = date.substring(8, 10);

        s.add(annee);
        s.add(mois);
        s.add(jour);
        s.add(heure);

        return s;

    }

    /**
     * Methode qui importe un fichier de donnée de la machine local (sans
     * internet)
     *
     * @param event
     * @return le nom du fichier pour l'afficher dans la rubrique des données
     * @throws IOException
     * @throws InterruptedException
     */
    public String import_file(ActionEvent event) throws IOException, InterruptedException {

        FileChooser filechooser = new FileChooser();
        filechooser.setTitle("Ouvrir un fichier");
        File file = filechooser.showOpenDialog(null);
        String fileNamelast = null;
        if (file != null) {
            String[] extensions = {"txt", "csv", "gz"};
            for (String extension : extensions) {
                if (file.getName().toLowerCase().endsWith("." + extension)) {

                    if (extension.equals("gz")) {

                        final String fileName = file.toURI().toString();
                        
                        for (String retval : fileName.split("/")) {
                            fileNamelast = retval;
                        }

                        File filex = new File(fileNamelast + ".txt");
                        boolean success = file.renameTo(filex);
                        file = filex;

                    }
                    else
                    if( extension.equals("csv"))
                    {
                        final String fileName = file.toURI().toString();
                        
                        for (String retval : fileName.split("/")) {
                            fileNamelast = retval;
                        }

                        File destFile  = new File(fileNamelast + ".txt");
                        FileUtils.copyFile(file, destFile);
                    }
                    

                    nom_fichier = Save_data(file);
                    
                }
            }
        }

        return nom_fichier;
    }

    /**
     * méthode qui retourne l'écart type d'une liste d'entier passé en paramètre
     *
     * @param S list d'entier
     * @return l'écart type d'une liste d'entier passé en paramètre
     */
    public Integer Ecart_type(ArrayList<Integer> S) {

        int ecrt_val;

        Collections.sort(S);
        if ((S.size() % 2) != 0) {
            ecrt_val = (S.size() + 1) / 2;
            return S.get(ecrt_val);
        } else {
            return (S.get(S.size() / 2) + S.get((S.size() + 1) / 2)) / 2;
        }
    }

    /**
     * Methode qui sauvegarde les données dans des objets
     *
     * @param row Liste donnée à ajouter
     * @param c la température en C
     */
    public void add_or_create(List<String> row, String c) {
        station.stream().forEach((stationn) -> {
            ArrayList<String> data_composant = split_date(row.get(1));
            if (stationn.getNomStation().equals(IdStationToStation(row.get(0)))) {
                Annee year = null;
                Mois month = null;
                Jour day = null;
                Heure hour = null;
                for (Annee annee : stationn.getAnnee()) {
                    if (annee.getAnnee_value().equals(data_composant.get(0))) {
                        year = annee;
                    }
                }
                if (year == null) {
                    DataClimate dataclimaate = new DataClimate(row.get(2), c, row.get(3), row.get(4));
                    List<Heure> list_heure = new ArrayList<>();
                    list_heure.add(new Heure(data_composant.get(3), dataclimaate));
                    List<Jour> list_jour = new ArrayList<>();
                    list_jour.add(new Jour(data_composant.get(2), list_heure));
                    List<Mois> list_mois = new ArrayList<>();
                    list_mois.add(new Mois(data_composant.get(1), list_jour));
                    stationn.AddAnnee(new Annee(data_composant.get(0), list_mois));
                } else {
                    for (Mois mois : year.getMois_list()) {
                        if (mois.getMois_value().equals(data_composant.get(1))) {
                            month = mois;
                        }
                    }
                    if (month == null) {
                        DataClimate dataclimaate = new DataClimate(row.get(2), c, row.get(3), row.get(4));
                        List<Heure> list_heure = new ArrayList<>();
                        list_heure.add(new Heure(data_composant.get(3), dataclimaate));
                        List<Jour> list_jour = new ArrayList<>();
                        list_jour.add(new Jour(data_composant.get(2), list_heure));
                        year.AddMois(new Mois(data_composant.get(1), list_jour));
                    } else {

                        for (Jour jour : month.getList_jour()) {
                            if (jour.getJour_value().equals(data_composant.get(2))) {
                                day = jour;
                            }
                        }
                        if (day == null) {
                            DataClimate dataclimaate = new DataClimate(row.get(2), c, row.get(3), row.get(4));
                            List<Heure> list_heure = new ArrayList<>();
                            month.Addjour(new Jour(data_composant.get(2), list_heure));

                        } else {
                            for (Heure heure : day.getList_heure()) {
                                if (heure.getHeure_value().equals(data_composant.get(3))) {
                                    hour = heure;
                                }

                            }
                            if (hour == null) {
                                DataClimate dataclimaate = new DataClimate(row.get(2), c, row.get(3), row.get(4));
                                day.addHeure(new Heure(data_composant.get(3), dataclimaate));
                            } else {

                            }

                        }

                    }
                }
            }
        });
    }

}
