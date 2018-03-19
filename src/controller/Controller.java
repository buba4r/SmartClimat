/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.Station;
import model.Modele;
import model.Data;
import model.DataClimate;
import model.Annee;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.animation.TranslateTransition;
import javafx.animation.TranslateTransitionBuilder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import javax.swing.JOptionPane;

/**
 * le Controleur de la vue FXML.fxml
 *
 * @author GHEMID
 */
public class Controller implements Initializable {

    @FXML
    private Label label;
    @FXML
    private TabPane tabpane;
    @FXML
    private Tab accueil_onglet, comparaison_onglet, visualisation_onglet, donnees_onglet, tableau_onglet, courbe_onglet;
    @FXML
    private ComboBox combo_jour_v, combo_mois_v, combo_annee_v, combo_station_v, combo_type_v;
    @FXML
    private ComboBox combo_jour_1_c, combo_mois_1_c, combo_annee_1_c, combo_station_1_c, combo_type_c;
    @FXML
    private ComboBox combo_jour_2_c, combo_mois_2_c, combo_annee_2_c, combo_station_2_c, combo_heure_tel;
    @FXML
    private ComboBox combo_jour_tel, combo_mois_tel, combo_annee_tel;
    @FXML
    private ImageView cloud;
    @FXML
    private ListView list_donnee;
    @FXML
    private TableView tableview;
    @FXML
    private DatePicker date;
    @FXML
    private LineChart courbe_c, courbe_v;
    @FXML
    private NumberAxis xAxis, xAxis2;
    @FXML
    private NumberAxis yAxis, yAxis2;
    @FXML
    private RadioButton en_ligne;
    @FXML
    private ProgressIndicator progressIndicator;
    @FXML
    private TextField ecart_type_1, ecart_type_2;

    //Initialisation
    Modele modele = new Modele();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        //animation cloud
        TranslateTransition translateTransition;
        Image cloudd = new Image("img/cloud_PNG16.png");
        cloud.setImage(cloudd);
        //strokeTransition = new StrokeTransition(Duration.millis(3000), , Color.RED, Color.BLUE);
        translateTransition = TranslateTransitionBuilder.create()
                .node(cloud)
                .duration(Duration.seconds(20.0))
                .byX(100f)
                .cycleCount(3000)
                .autoReverse(true)
                .build();
        translateTransition.play();

        en_ligne.setDisable(true);
        if (modele.netIsAvailable()) {
            en_ligne.setSelected(true);
        } else {
            en_ligne.setSelected(true);
        }

        //Initialisation des stations : 
        String[] villes = {"ABBEVILLE", "LILLE-LESQUIN", "PTE DE LA HAGUE", "CAEN-CARPIQUET", "ROUEN-BOOS", "REIMS-PRUNAY", "BREST-GUIPAVAS", "PLOUMANACH", "RENNES-ST JACQUES", "ALENCON", "ORLY", "TROYES-BARBEREY", "NANCY-OCHEY", "STRASBOURG-ENTZHEIM",
            "BELLE ILE-LE TALUT", "NANTES-BOUGUENAIS", "TOURS", "BOURGES", "DIJON-LONGVIC", "BALE-MULHOUSE", "PTE DE CHASSIRON",
            "POITIERS-BIARD", "LIMOGES-BELLEGARDE", "CLERMONT-FD", "LE PUY-LOUDES", "LYON-ST EXUPERY", "BORDEAUX-MERIGNAC",
            "GOURDON", "MILLAU", "MONTELIMAR", "EMBRUN", "MONT-DE-MARSAN", "TARBES-OSSUN", "ST GIRONS", "TOULOUSE-BLAGNAC",
            "MONTPELLIER", "MARIGNANE", "CAP CEPET", "NICE", "PERPIGNAN", "AJACCIO", "BASTIA", "GLORIEUSES", "JUAN DE NOVA",
            "EUROPA", "TROMELIN", "GILLOT-AEROPORT", "NOUVELLE AMSTERDAM", "CROZET", "KERGUELEN", "PAMANDZI", "ST-PIERRE",
            "LA DESIRADE METEO", "ST-BARTHELEMY METEO", "LE RAIZET AERO", "TRINITE-CARAVEL"};

        String[] idVilles = {"07005", "07015", "07020", "07027", "07037", "07072", "07110", "07117", "07130", "07139", "07149", "07168",
            "07181", "07190", "07207", "07222", "07240", "07255", "07280", "07299", "07314", "07335", "07434", "07460", "07471", "07481",
            "07510", "07535", "07558", "07577", "07591", "07607", "07621", "07627", "07630", "07643", "07650", "07661", "07690", "07747",
            "07761", "07790", "61968", "61976", "61980", "61996", "61998", "67005", "71805", "78897", "78925", "81401",
            "81405", "81408", "81415", "89642"};

        //correspondre les idvilles aux stations
        List<Annee> list_ann = new ArrayList<>();
        for (int i = 0; i < idVilles.length; i++) {
            modele.station.add(new Station(villes[i], idVilles[i], list_ann));
        }

        //initialisation des comboBox
        //combo_critere_difference.getItems().addAll("Courbe", "Difference", "Écart-Type");
        combo_station_v.getItems().addAll("ABBEVILLE", "LILLE-LESQUIN", "PTE DE LA HAGUE", "CAEN-CARPIQUET", "ROUEN-BOOS", "REIMS-PRUNAY", "BREST-GUIPAVAS", "PLOUMANACH", "RENNES-ST JACQUES", "ALENCON", "ORLY", "TROYES-BARBEREY", "NANCY-OCHEY", "STRASBOURG-ENTZHEIM",
                "BELLE ILE-LE TALUT", "NANTES-BOUGUENAIS", "TOURS", "BOURGES", "DIJON-LONGVIC", "BALE-MULHOUSE", "PTE DE CHASSIRON",
                "POITIERS-BIARD", "LIMOGES-BELLEGARDE", "CLERMONT-FD", "LE PUY-LOUDES", "LYON-ST EXUPERY", "BORDEAUX-MERIGNAC",
                "GOURDON", "MILLAU", "MONTELIMAR", "EMBRUN", "MONT-DE-MARSAN", "TARBES-OSSUN", "ST GIRONS", "TOULOUSE-BLAGNAC",
                "MONTPELLIER", "MARIGNANE", "CAP CEPET", "NICE", "PERPIGNAN", "AJACCIO", "BASTIA", "GLORIEUSES", "JUAN DE NOVA",
                "EUROPA", "TROMELIN", "GILLOT-AEROPORT", "NOUVELLE AMSTERDAM", "CROZET", "KERGUELEN", "PAMANDZI", "ST-PIERRE",
                "LA DESIRADE METEO", "ST-BARTHELEMY METEO", "LE RAIZET AERO", "TRINITE-CARAVEL");
        combo_station_1_c.getItems().addAll("ABBEVILLE", "LILLE-LESQUIN", "PTE DE LA HAGUE", "CAEN-CARPIQUET", "ROUEN-BOOS", "REIMS-PRUNAY", "BREST-GUIPAVAS", "PLOUMANACH", "RENNES-ST JACQUES", "ALENCON", "ORLY", "TROYES-BARBEREY", "NANCY-OCHEY", "STRASBOURG-ENTZHEIM",
                "BELLE ILE-LE TALUT", "NANTES-BOUGUENAIS", "TOURS", "BOURGES", "DIJON-LONGVIC", "BALE-MULHOUSE", "PTE DE CHASSIRON",
                "POITIERS-BIARD", "LIMOGES-BELLEGARDE", "CLERMONT-FD", "LE PUY-LOUDES", "LYON-ST EXUPERY", "BORDEAUX-MERIGNAC",
                "GOURDON", "MILLAU", "MONTELIMAR", "EMBRUN", "MONT-DE-MARSAN", "TARBES-OSSUN", "ST GIRONS", "TOULOUSE-BLAGNAC",
                "MONTPELLIER", "MARIGNANE", "CAP CEPET", "NICE", "PERPIGNAN", "AJACCIO", "BASTIA", "GLORIEUSES", "JUAN DE NOVA",
                "EUROPA", "TROMELIN", "GILLOT-AEROPORT", "NOUVELLE AMSTERDAM", "CROZET", "KERGUELEN", "PAMANDZI", "ST-PIERRE",
                "LA DESIRADE METEO", "ST-BARTHELEMY METEO", "LE RAIZET AERO", "TRINITE-CARAVEL");

        combo_station_2_c.getItems().addAll("ABBEVILLE", "LILLE-LESQUIN", "PTE DE LA HAGUE", "CAEN-CARPIQUET", "ROUEN-BOOS", "REIMS-PRUNAY", "BREST-GUIPAVAS", "PLOUMANACH", "RENNES-ST JACQUES", "ALENCON", "ORLY", "TROYES-BARBEREY", "NANCY-OCHEY", "STRASBOURG-ENTZHEIM",
                "BELLE ILE-LE TALUT", "NANTES-BOUGUENAIS", "TOURS", "BOURGES", "DIJON-LONGVIC", "BALE-MULHOUSE", "PTE DE CHASSIRON",
                "POITIERS-BIARD", "LIMOGES-BELLEGARDE", "CLERMONT-FD", "LE PUY-LOUDES", "LYON-ST EXUPERY", "BORDEAUX-MERIGNAC",
                "GOURDON", "MILLAU", "MONTELIMAR", "EMBRUN", "MONT-DE-MARSAN", "TARBES-OSSUN", "ST GIRONS", "TOULOUSE-BLAGNAC",
                "MONTPELLIER", "MARIGNANE", "CAP CEPET", "NICE", "PERPIGNAN", "AJACCIO", "BASTIA", "GLORIEUSES", "JUAN DE NOVA",
                "EUROPA", "TROMELIN", "GILLOT-AEROPORT", "NOUVELLE AMSTERDAM", "CROZET", "KERGUELEN", "PAMANDZI", "ST-PIERRE",
                "LA DESIRADE METEO", "ST-BARTHELEMY METEO", "LE RAIZET AERO", "TRINITE-CARAVEL");

        //Initialisation des critères
        combo_type_v.getItems().addAll("Temp C", "Temp K", "Humidité", "Nébulosité");
        combo_type_c.getItems().addAll("Temp C", "Temp K", "Humidité", "Nébulosité");
        combo_type_c.getSelectionModel().select(0);

        //Initialisation  des jour
        for (int i = 0; i < 32; i++) {
            if (i == 0) {
                combo_jour_v.getItems().add("NULL");
                combo_jour_1_c.getItems().add("NULL");
                combo_jour_2_c.getItems().add("NULL");
                combo_jour_tel.getItems().add("NULL");
            } else if (i < 10 && i > 0) {
                combo_jour_v.getItems().add("0" + String.valueOf(i));
                combo_jour_1_c.getItems().add("0" + String.valueOf(i));
                combo_jour_2_c.getItems().add("0" + String.valueOf(i));
                combo_jour_tel.getItems().add("0" + String.valueOf(i));

            } else {
                combo_jour_v.getItems().add(String.valueOf(i));
                combo_jour_1_c.getItems().add(String.valueOf(i));
                combo_jour_2_c.getItems().add(String.valueOf(i));
                combo_jour_tel.getItems().add(String.valueOf(i));
            }
        }

        //Initialisation des mois
        for (int i = 0; i < 13; i++) {
            if (i == 0) {
                combo_mois_v.getItems().add("NULL");
                combo_mois_1_c.getItems().add("NULL");
                combo_mois_2_c.getItems().add("NULL");
                combo_mois_tel.getItems().add("NULL");
            } else if (i < 10 && i > 0) {
                combo_mois_v.getItems().add("0" + String.valueOf(i));
                combo_mois_1_c.getItems().add("0" + String.valueOf(i));
                combo_mois_2_c.getItems().add("0" + String.valueOf(i));
                combo_mois_tel.getItems().add("0" + String.valueOf(i));

            } else {
                combo_mois_v.getItems().add(String.valueOf(i));
                combo_mois_1_c.getItems().add(String.valueOf(i));
                combo_mois_2_c.getItems().add(String.valueOf(i));
                combo_mois_tel.getItems().add(String.valueOf(i));
            }
        }

        //Initialisation des années
        for (int i = 1996; i < 2018; i++) {
            combo_annee_v.getItems().add(String.valueOf(i));
            combo_annee_1_c.getItems().add(String.valueOf(i));
            combo_annee_2_c.getItems().add(String.valueOf(i));
            combo_annee_tel.getItems().add(String.valueOf(i));
        }

        //Initialisation des heures
        String heure = null;
        int i = 0;
        while (i < 22) {
            if (i == 0) {
                heure = "NULL";
            } else if (i < 10 && i > 0) {
                heure = "0" + String.valueOf(i);
            } else {
                heure = String.valueOf(i);
            }
            combo_heure_tel.getItems().add(heure);

            i += 3;
        }

        combo_annee_1_c.getSelectionModel().select(0);
        combo_mois_1_c.getSelectionModel().select(0);
        combo_jour_1_c.getSelectionModel().select(0);
        combo_annee_2_c.getSelectionModel().select(0);
        combo_mois_2_c.getSelectionModel().select(0);
        combo_jour_2_c.getSelectionModel().select(0);
        combo_jour_v.getSelectionModel().select(0);
        combo_mois_v.getSelectionModel().select(0);
        combo_annee_v.getSelectionModel().select(0);
        combo_station_1_c.getSelectionModel().select(0);
        combo_station_2_c.getSelectionModel().select(0);
        combo_station_v.getSelectionModel().select(0);
        combo_type_v.getSelectionModel().select(0);
        combo_heure_tel.getSelectionModel().select(0);
        combo_jour_tel.getSelectionModel().select(0);
        combo_mois_tel.getSelectionModel().select(0);
        combo_annee_tel.getSelectionModel().select(0);

        if (tableau_onglet.isSelected()) {
            combo_type_v.setDisable(true);
        }

        //Listners
        tableau_onglet.setOnSelectionChanged((event) -> {
            combo_type_v.setDisable(true);
            visualiser();
        });

        courbe_onglet.setOnSelectionChanged((event) -> {
            combo_type_v.setDisable(false);
            visualiser();

        });

        combo_mois_v.setOnAction((event) -> {
            if (combo_mois_v.getSelectionModel().getSelectedItem().toString().equals("NULL")) {
                combo_jour_v.getSelectionModel().select(0);
                combo_jour_v.setDisable(true);
            } else {
                combo_jour_v.setDisable(false);
            }
        });

        combo_mois_1_c.setOnAction((event) -> {

            combo_mois_2_c.getSelectionModel().select(combo_mois_1_c.getSelectionModel().getSelectedIndex());
            combo_mois_2_c.setDisable(true);
            comparer();
        });

        combo_jour_1_c.setOnAction((event) -> {

            combo_jour_2_c.getSelectionModel().select(combo_jour_1_c.getSelectionModel().getSelectedIndex());
            combo_jour_2_c.setDisable(true);
            comparer();
        });

        combo_annee_1_c.setOnAction((event) -> {
            comparer();
        });

        combo_annee_2_c.setOnAction((event) -> {
            comparer();
        });

        combo_annee_v.setOnAction((event) -> {
            visualiser();
        });
        combo_mois_v.setOnAction((event) -> {
            visualiser();
        });
        combo_jour_v.setOnAction((event) -> {
            visualiser();
        });
        combo_type_v.setOnAction((event) -> {
            visualiser();
        });
        combo_station_v.setOnAction((event) -> {
            visualiser();
        });

        combo_type_c.setOnAction((event) -> {
            comparer();
        });
        combo_station_1_c.setOnAction((event) -> {
            comparer();
        });
        combo_station_2_c.setOnAction((event) -> {
            comparer();
        });

        //télécharger les fichier existants dans repertoire root 
        List<String> noms_de_fichiers = new ArrayList<>();
        noms_de_fichiers = modele.download_existing_files();
        list_donnee.getItems().addAll(noms_de_fichiers);
        //stage.hide();
        //Setting loading page

    }

    @FXML
    void disable_combo(ActionEvent event) {
        combo_type_v.setDisable(true);
    }

    @FXML
    private void handleButtonAction(ActionEvent event) {
        label.setText("Hello World!");
    }

    @FXML
    private void select_accueil() {
        tabpane.getSelectionModel().select(accueil_onglet);
    }

    @FXML
    private void select_visualiser() {
        tabpane.getSelectionModel().select(visualisation_onglet);
    }

    @FXML
    private void select_comparer() {
        tabpane.getSelectionModel().select(comparaison_onglet);
    }

    @FXML
    private void select_donnees() {
        tabpane.getSelectionModel().select(donnees_onglet);
    }

    //Ajouter un fichier
    @FXML
    private void import_file(ActionEvent event) throws IOException, InterruptedException {

        List<String> list_donnee_d = new ArrayList<>();
        String nom_fichier = modele.import_file(event);
        list_donnee.getItems().add(nom_fichier);
    }

    /**
     * Methode pour le bouton telecharger fichier du web
     *
     * @throws MalformedURLException
     * @throws IOException
     */
    @FXML
    public void download_file_web() throws MalformedURLException, IOException {

        if (!combo_heure_tel.getSelectionModel().isEmpty() || !combo_jour_tel.getSelectionModel().isEmpty() || !combo_mois_tel.getSelectionModel().isEmpty() || !combo_annee_tel.getSelectionModel().isEmpty()) {

            float value = 0;
            while (value <= 1) {
                progressIndicator.setProgress(value);
                value += 0.001;
            }

            String jour = combo_jour_tel.getSelectionModel().getSelectedItem().toString();
            String mois = combo_mois_tel.getSelectionModel().getSelectedItem().toString();
            String annee = combo_annee_tel.getSelectionModel().getSelectedItem().toString();
            String heure = combo_heure_tel.getSelectionModel().getSelectedItem().toString();

            String nom_fichier = modele.Download_File(jour, mois, annee, heure);
            list_donnee.getItems().add(nom_fichier);

        } else {
            JOptionPane.showMessageDialog(null, "Veuillez choisir une date !");
        }
    }

    /**
     * methode qui telecharger un fichier du web mais de l'onglet "visualisation
     * de l'évolution du climat
     *
     * @throws IOException
     */
    @FXML
    public void download_file_web_beta() throws IOException {

        String jour = combo_jour_v.getSelectionModel().getSelectedItem().toString();
        String mois = combo_mois_v.getSelectionModel().getSelectedItem().toString();
        String annee = combo_annee_v.getSelectionModel().getSelectedItem().toString();

        String nom_fichier = modele.Download_File("NULL", mois, annee, "NULL");
        list_donnee.getItems().add(nom_fichier);
        visualiser();
    }

    /**
     * Methode visualiser pour le tableau ou la courbe
     */
    @FXML
    public void visualiser() {

        if (tableau_onglet.isSelected()) {
            visualiser_tableau();
        } else {
            visualiser_courbe();
        }

    }

    /**
     * methode visualiser tableau
     */
    public void visualiser_tableau() {

        tableview.getItems().clear();
        tableview.getColumns().clear();

        String jour = combo_jour_v.getSelectionModel().getSelectedItem().toString();
        String mois = combo_mois_v.getSelectionModel().getSelectedItem().toString();
        String annee = combo_annee_v.getSelectionModel().getSelectedItem().toString();
        String nomStation = combo_station_v.getSelectionModel().getSelectedItem().toString();
        boolean found = false;
        if (!combo_jour_v.getSelectionModel().getSelectedItem().toString().equals("NULL") && !combo_mois_v.getSelectionModel().getSelectedItem().toString().equals("NULL")) {

            for (Station stationn : modele.station) {
                if (stationn.getNomStation().equals(nomStation)) {
                    List<DataClimate> list_dataclimate = stationn.getDataJour(annee, mois, jour);
                    int heure = 0;

                    for (int l = 0; l < list_dataclimate.size(); l++) {

                        String hr = null;
                        if (heure < 10) {
                            hr = "0" + String.valueOf(heure);
                        } else {
                            hr = String.valueOf(heure);
                        }

                        String temp_k = list_dataclimate.get(l).getTemp_k();
                        String temp_c = list_dataclimate.get(l).getTemp_c();
                        String humidite = list_dataclimate.get(l).getHumidite();
                        String nebulosite = list_dataclimate.get(l).getNebulosite();

                        modele.data.add(new Data(jour + " " + mois + " " + annee + " h" + hr, nomStation, temp_k, temp_c, humidite, nebulosite));

                        heure += 3;
                        if (heure > 21) {
                            heure = 0;
                        }
                        found = true;
                    }

                }
            }
        } else if (combo_jour_v.getSelectionModel().getSelectedItem().toString().equals("NULL") && !combo_mois_v.getSelectionModel().getSelectedItem().toString().equals("NULL")) {
            for (Station stationn : modele.station) {
                if (stationn.getNomStation().equals(nomStation)) {
                    List<DataClimate> list_dataclimate = stationn.getDataMois(annee, mois);
                  
                    int jourx = 1;
                    for (int l = 0; l < list_dataclimate.size(); l++) {
                        String jr = null;
                        if (jourx < 10) {
                            jr = "0" + String.valueOf(jourx);
                        } else {
                            jr = String.valueOf(jourx);
                        }

                        String temp_k = list_dataclimate.get(l).getTemp_k();
                        String temp_c = list_dataclimate.get(l).getTemp_c();
                        String humidite = list_dataclimate.get(l).getHumidite();
                        String nebulosite = list_dataclimate.get(l).getNebulosite();

                        modele.data.add(new Data(jr + " " + mois + " " + annee, nomStation, temp_k, temp_c, humidite, nebulosite));

                            jourx += 1;
                       
                        
                        found = true;
                    }

                }
            }
        } else if (combo_jour_v.getSelectionModel().getSelectedItem().toString().equals("NULL") && combo_mois_v.getSelectionModel().getSelectedItem().toString().equals("NULL")) {
            for (Station stationn : modele.station) {
                if (stationn.getNomStation().equals(nomStation)) {
                    List<DataClimate> list_dataclimate = stationn.getDataAnnee(annee);
                    
                    int moixs = 1;
                    for (int l = 0; l < list_dataclimate.size(); l++) {

                        String ms = null;
                        if (moixs < 10) {
                            ms = "0" + String.valueOf(moixs);
                        } else {
                            ms = String.valueOf(moixs);
                        }

                        String temp_k = list_dataclimate.get(l).getTemp_k();
                        String temp_c = list_dataclimate.get(l).getTemp_c();
                        String humidite = list_dataclimate.get(l).getHumidite();
                        String nebulosite = list_dataclimate.get(l).getNebulosite();

                        modele.data.add(new Data(ms +" "+annee, nomStation, temp_k, temp_c, humidite, nebulosite));

                        moixs ++;
                        
                        found = true;
                    }

                }
            }
        }

        if (found) {

            TableColumn c00 = new TableColumn("Date");
            c00.setMinWidth(150);
            c00.setCellValueFactory(new PropertyValueFactory<Data, String>("date"));

            TableColumn c0 = new TableColumn("Station");
            c0.setMinWidth(150);
            c0.setCellValueFactory(new PropertyValueFactory<Data, String>("station"));

            TableColumn c = new TableColumn("Température K");
            c.setMinWidth(150);
            c.setCellValueFactory(new PropertyValueFactory<Data, String>("temp_k"));

            TableColumn c1 = new TableColumn("Température C");
            c1.setMinWidth(150);
            c1.setCellValueFactory(new PropertyValueFactory<Data, String>("temp_c"));

            TableColumn c2 = new TableColumn("Humidité %");
            c2.setMinWidth(150);
            c2.setCellValueFactory(new PropertyValueFactory<Data, String>("humidite"));

            TableColumn c3 = new TableColumn("Nebulosité %");
            c3.setMinWidth(150);
            c3.setCellValueFactory(new PropertyValueFactory<Data, String>("nebulosite"));

            tableview.setItems(modele.data);
            tableview.getColumns().addAll(c0, c00, c, c1, c2, c3);

        } else {

        }

    }

    /**
     * methode visualiser courbe
     */
    public void visualiser_courbe() {
        //Temp C", "Temp K", "Humidité", "Nébulosité

        String nomStation = combo_station_v.getSelectionModel().getSelectedItem().toString();
        String jour = combo_jour_v.getSelectionModel().getSelectedItem().toString();
        String mois = combo_mois_v.getSelectionModel().getSelectedItem().toString();
        String annee = combo_annee_v.getSelectionModel().getSelectedItem().toString();

        // Cas ou le jour et le mois sont pas NULL
        if (!combo_jour_v.getSelectionModel().getSelectedItem().toString().equals("NULL") && !combo_mois_v.getSelectionModel().getSelectedItem().toString().equals("NULL")) {
            if (combo_type_v.getSelectionModel().getSelectedItem().toString().equals("Temp C")) {

                for (Station stationn : modele.station) {
                    if (stationn.getNomStation().equals(nomStation)) {
                        Courbe_jour(stationn.getDataJour(annee, mois, jour), "Température C", nomStation, "Temp C");
                    }
                }

            } else if (combo_type_v.getSelectionModel().getSelectedItem().toString().equals("Temp K")) {

                for (Station stationn : modele.station) {
                    if (stationn.getNomStation().equals(nomStation)) {
                        Courbe_jour(stationn.getDataJour(annee, mois, jour), "Température K", nomStation, "Temp K");
                    }
                }

            } else if (combo_type_v.getSelectionModel().getSelectedItem().toString().equals("Humidité")) {

                for (Station stationn : modele.station) {
                    if (stationn.getNomStation().equals(nomStation)) {
                        Courbe_jour(stationn.getDataJour(annee, mois, jour), "Humidité %", nomStation, "Humidité");
                    }
                }

            } else if (combo_type_v.getSelectionModel().getSelectedItem().toString().equals("Nébulosité")) {

                for (Station stationn : modele.station) {
                    if (stationn.getNomStation().equals(nomStation)) {
                        Courbe_jour(stationn.getDataJour(annee, mois, jour), "Nébulosité %", nomStation, "Nébulosité");
                    }
                }

            }
        } else // Cas ou le jour est NULL
        if (combo_jour_v.getSelectionModel().getSelectedItem().toString().equals("NULL") && !combo_mois_v.getSelectionModel().getSelectedItem().toString().equals("NULL")) {
            if (combo_type_v.getSelectionModel().getSelectedItem().toString().equals("Temp C")) {
                for (Station stationn : modele.station) {
                    if (stationn.getNomStation().equals(nomStation)) {
                        Courbe_mois(stationn.getDataMois(annee, mois), "Température C", nomStation, "Temp C");
                    }
                }

            } else if (combo_type_v.getSelectionModel().getSelectedItem().toString().equals("Temp K")) {

                for (Station stationn : modele.station) {
                    if (stationn.getNomStation().equals(nomStation)) {
                        Courbe_mois(stationn.getDataMois(annee, mois), "Température K", nomStation, "Temp K");
                    }
                }

            } else if (combo_type_v.getSelectionModel().getSelectedItem().toString().equals("Humidité")) {

                for (Station stationn : modele.station) {
                    if (stationn.getNomStation().equals(nomStation)) {
                        Courbe_mois(stationn.getDataMois(annee, mois), "Humidité %", nomStation, "Humidité");
                    }
                }

            } else if (combo_type_v.getSelectionModel().getSelectedItem().toString().equals("Nébulosité")) {

                for (Station stationn : modele.station) {
                    if (stationn.getNomStation().equals(nomStation)) {
                        Courbe_mois(stationn.getDataMois(annee, mois), "Nébulosité %", nomStation, "Nébulosité");
                    }
                }

            }
        } else if (combo_jour_v.getSelectionModel().getSelectedItem().toString().equals("NULL") && combo_mois_v.getSelectionModel().getSelectedItem().toString().equals("NULL")) {
            if (combo_type_v.getSelectionModel().getSelectedItem().toString().equals("Temp C")) {

                for (Station stationn : modele.station) {
                    if (stationn.getNomStation().equals(nomStation)) {
                        Courbe_annee(stationn.getDataAnnee(annee), "Température C", nomStation, "Temp C");
                    }
                }

            } else if (combo_type_v.getSelectionModel().getSelectedItem().toString().equals("Temp K")) {

                for (Station stationn : modele.station) {
                    if (stationn.getNomStation().equals(nomStation)) {
                        Courbe_annee(stationn.getDataAnnee(annee), "Température K", nomStation, "Temp K");
                    }
                }

            } else if (combo_type_v.getSelectionModel().getSelectedItem().toString().equals("Humidité")) {

                for (Station stationn : modele.station) {
                    if (stationn.getNomStation().equals(nomStation)) {
                        Courbe_annee(stationn.getDataAnnee(annee), "Humidité %", nomStation, "Humidité");
                    }
                }

            } else if (combo_type_v.getSelectionModel().getSelectedItem().toString().equals("Nébulosité")) {

                for (Station stationn : modele.station) {
                    if (stationn.getNomStation().equals(nomStation)) {
                        Courbe_annee(stationn.getDataAnnee(annee), "Nébulosité %", nomStation, "Nébulosité");
                    }
                }

            }
        }
    }

    /**
     * methode comparer pour le bouton comparer
     */
    @FXML
    public void comparer() {
        courbe_c.getData().clear();
        String nomStation1 = combo_station_1_c.getSelectionModel().getSelectedItem().toString();
        String nomStation2 = combo_station_2_c.getSelectionModel().getSelectedItem().toString();
        String jour1 = combo_jour_1_c.getSelectionModel().getSelectedItem().toString();
        String jour2 = combo_jour_2_c.getSelectionModel().getSelectedItem().toString();
        String mois1 = combo_mois_1_c.getSelectionModel().getSelectedItem().toString();
        String mois2 = combo_mois_2_c.getSelectionModel().getSelectedItem().toString();

        String annee1 = combo_annee_1_c.getSelectionModel().getSelectedItem().toString();
        String annee2 = combo_annee_2_c.getSelectionModel().getSelectedItem().toString();

        // Cas ou le jour et le mois sont pas NULL
        if (!combo_jour_1_c.getSelectionModel().getSelectedItem().toString().equals("NULL") && !combo_mois_1_c.getSelectionModel().getSelectedItem().toString().equals("NULL")) {
            if (combo_type_c.getSelectionModel().getSelectedItem().toString().equals("Temp C")) {

                for (Station stationn : modele.station) {
                    if (stationn.getNomStation().equals(nomStation1)) {
                        Courbe_jour_c(stationn.getDataJour(annee1, mois1, jour1), "Température C", nomStation1, "Temp C");
                        visualiser_ecart_type(stationn.getDataJour(annee1, mois1, jour1), "Temp C", true);
                    }
                    if (stationn.getNomStation().equals(nomStation2)) {
                        Courbe_jour_c(stationn.getDataJour(annee2, mois2, jour2), "Température C", nomStation2, "Temp C");
                        visualiser_ecart_type(stationn.getDataJour(annee2, mois2, jour2), "Temp C", false);
                    }
                }

            } else if (combo_type_c.getSelectionModel().getSelectedItem().toString().equals("Temp K")) {

                for (Station stationn : modele.station) {
                    if (stationn.getNomStation().equals(nomStation1)) {
                        Courbe_jour_c(stationn.getDataJour(annee1, mois1, jour1), "Température K", nomStation1, "Temp K");
                        visualiser_ecart_type(stationn.getDataJour(annee1, mois1, jour1), "Temp K", true);
                    }
                    if (stationn.getNomStation().equals(nomStation2)) {
                        Courbe_jour_c(stationn.getDataJour(annee2, mois2, jour2), "Température K", nomStation2, "Temp K");
                        visualiser_ecart_type(stationn.getDataJour(annee2, mois2, jour2), "Temp K", false);
                    }
                }

            } else if (combo_type_c.getSelectionModel().getSelectedItem().toString().equals("Humidité")) {

                for (Station stationn : modele.station) {
                    if (stationn.getNomStation().equals(nomStation1)) {
                        Courbe_jour_c(stationn.getDataJour(annee1, mois1, jour1), "Humidité %", nomStation1, "Humidité");
                        visualiser_ecart_type(stationn.getDataJour(annee1, mois1, jour1), "Humidité", true);
                    }
                    if (stationn.getNomStation().equals(nomStation2)) {
                        Courbe_jour_c(stationn.getDataJour(annee2, mois2, jour2), "Humidité %", nomStation2, "Humidité");
                        visualiser_ecart_type(stationn.getDataJour(annee2, mois2, jour2), "Humidité", false);
                    }
                }

            } else if (combo_type_c.getSelectionModel().getSelectedItem().toString().equals("Nébulosité")) {

                for (Station stationn : modele.station) {
                    if (stationn.getNomStation().equals(nomStation1)) {
                        Courbe_jour_c(stationn.getDataJour(annee1, mois1, jour1), "Nébulosité %", nomStation1, "Nébulosité");
                        visualiser_ecart_type(stationn.getDataJour(annee1, mois1, jour1), "Nébulosité", true);
                    }
                    if (stationn.getNomStation().equals(nomStation2)) {
                        Courbe_jour_c(stationn.getDataJour(annee2, mois2, jour2), "Nébulosité", nomStation2, "Nébulosité");
                        visualiser_ecart_type(stationn.getDataJour(annee2, mois2, jour2), "Nébulosité", false);
                    }
                }

            }
        } else // Cas ou le jour est NULL
        if (combo_jour_1_c.getSelectionModel().getSelectedItem().toString().equals("NULL") && !combo_mois_1_c.getSelectionModel().getSelectedItem().toString().equals("NULL")) {
            if (combo_type_c.getSelectionModel().getSelectedItem().toString().equals("Temp C")) {
                for (Station stationn : modele.station) {
                    if (stationn.getNomStation().equals(nomStation1)) {
                        Courbe_mois_c(stationn.getDataMois(annee1, mois1), "Température C", nomStation1, "Temp C");
                        visualiser_ecart_type(stationn.getDataMois(annee1, mois1), "Temp C", true);
                    }
                    if (stationn.getNomStation().equals(nomStation2)) {
                        Courbe_mois_c(stationn.getDataMois(annee2, mois2), "Température C", nomStation2, "Temp C");
                        visualiser_ecart_type(stationn.getDataMois(annee2, mois2), "Temp C", false);
                    }
                }

            } else if (combo_type_c.getSelectionModel().getSelectedItem().toString().equals("Temp K")) {

                for (Station stationn : modele.station) {
                    if (stationn.getNomStation().equals(nomStation1)) {
                        Courbe_mois_c(stationn.getDataMois(annee1, mois1), "Température K", nomStation1, "Temp K");
                        visualiser_ecart_type(stationn.getDataMois(annee1, mois1), "Temp K", true);
                    }
                    if (stationn.getNomStation().equals(nomStation2)) {
                        Courbe_mois_c(stationn.getDataMois(annee2, mois2), "Température K", nomStation2, "Temp K");
                        visualiser_ecart_type(stationn.getDataMois(annee2, mois2), "Temp K", false);
                    }
                }

            } else if (combo_type_c.getSelectionModel().getSelectedItem().toString().equals("Humidité")) {

                for (Station stationn : modele.station) {
                    if (stationn.getNomStation().equals(nomStation1)) {
                        Courbe_mois_c(stationn.getDataMois(annee1, mois1), "Humidité %", nomStation1, "Humidité");
                        visualiser_ecart_type(stationn.getDataMois(annee1, mois1), "Humidité", true);
                    }
                    if (stationn.getNomStation().equals(nomStation2)) {
                        Courbe_mois_c(stationn.getDataMois(annee2, mois2), "Humidité %", nomStation2, "Humidité");
                        visualiser_ecart_type(stationn.getDataMois(annee2, mois2), "Humidité", false);
                    }
                }

            } else if (combo_type_c.getSelectionModel().getSelectedItem().toString().equals("Nébulosité")) {

                for (Station stationn : modele.station) {
                    if (stationn.getNomStation().equals(nomStation1)) {
                        Courbe_mois_c(stationn.getDataMois(annee1, mois1), "Nébulosité %", nomStation1, "Nébulosité");
                        visualiser_ecart_type(stationn.getDataMois(annee1, mois1), "Nébulosité", true);
                    }
                    if (stationn.getNomStation().equals(nomStation2)) {
                        Courbe_mois_c(stationn.getDataMois(annee2, mois2), "Nébulosité %", nomStation2, "Nébulosité");
                        visualiser_ecart_type(stationn.getDataMois(annee2, mois2), "Nébulosité", false);
                    }
                }

            }
        } else if (combo_jour_1_c.getSelectionModel().getSelectedItem().toString().equals("NULL") && combo_mois_1_c.getSelectionModel().getSelectedItem().toString().equals("NULL")) {
            if (combo_type_c.getSelectionModel().getSelectedItem().toString().equals("Temp C")) {

                for (Station stationn : modele.station) {
                    if (stationn.getNomStation().equals(nomStation1)) {
                        Courbe_annee_c(stationn.getDataAnnee(annee1), "Température C", nomStation1, "Temp C");
                        visualiser_ecart_type(stationn.getDataAnnee(annee1), "Temp C", true);
                    }
                    if (stationn.getNomStation().equals(nomStation2)) {
                        Courbe_annee_c(stationn.getDataAnnee(annee2), "Température C", nomStation2, "Temp C");
                        visualiser_ecart_type(stationn.getDataAnnee(annee2), "Temp C", false);
                    }
                }

            } else if (combo_type_c.getSelectionModel().getSelectedItem().toString().equals("Temp K")) {

                for (Station stationn : modele.station) {
                    if (stationn.getNomStation().equals(nomStation1)) {
                        Courbe_annee_c(stationn.getDataAnnee(annee1), "Température K", nomStation1, "Temp K");
                        visualiser_ecart_type(stationn.getDataAnnee(annee1), "Temp K", true);
                    }
                    if (stationn.getNomStation().equals(nomStation2)) {
                        Courbe_annee_c(stationn.getDataAnnee(annee2), "Température K", nomStation2, "Temp K");
                        visualiser_ecart_type(stationn.getDataAnnee(annee2), "Temp K", false);
                    }
                }

            } else if (combo_type_c.getSelectionModel().getSelectedItem().toString().equals("Humidité")) {

                for (Station stationn : modele.station) {
                    if (stationn.getNomStation().equals(nomStation1)) {
                        Courbe_annee_c(stationn.getDataAnnee(annee1), "Humidité %", nomStation1, "Humidité");
                        visualiser_ecart_type(stationn.getDataAnnee(annee1), "Humidité", true);
                    }
                    if (stationn.getNomStation().equals(nomStation2)) {
                        Courbe_annee_c(stationn.getDataAnnee(annee2), "Humidité %", nomStation2, "Humidité");
                        visualiser_ecart_type(stationn.getDataAnnee(annee2), "Humidité", false);
                    }
                }

            } else if (combo_type_c.getSelectionModel().getSelectedItem().toString().equals("Nébulosité")) {

                for (Station stationn : modele.station) {
                    if (stationn.getNomStation().equals(nomStation1)) {
                        Courbe_annee_c(stationn.getDataAnnee(annee1), "Nébulosité %", nomStation1, "Nébulosité");
                        visualiser_ecart_type(stationn.getDataAnnee(annee1), "Nébulosité", true);
                    }
                    if (stationn.getNomStation().equals(nomStation2)) {
                        Courbe_annee_c(stationn.getDataAnnee(annee2), "Nébulosité %", nomStation2, "Nébulosité");
                        visualiser_ecart_type(stationn.getDataAnnee(annee2), "Nébulosité", false);
                    }
                }

            }
        }
    }

    /**
     * Methode pour afficher la courbe d'un jour
     *
     * @param data
     * @param titre
     * @param station
     * @param type_donnee
     */
    public void Courbe_jour(List<DataClimate> data, String titre, String station, String type_donnee) {
        courbe_v.getData().clear();
        XYChart.Series series = new XYChart.Series();
        xAxis.setLabel("Heure");
        yAxis.setLabel(titre);
        courbe_v.setTitle("Evolution " + titre);
        series.setName(station);
        int heure = 0;
        for (int i = 0; i < data.size(); i++) {
            if (type_donnee.equals("Temp C")) {
                series.getData().add(new XYChart.Data(heure, Float.parseFloat(data.get(i).getTemp_c())));
            } else if (type_donnee.equals("Temp K")) {
                series.getData().add(new XYChart.Data(heure, Float.parseFloat(data.get(i).getTemp_k())));
            } else if (type_donnee.equals("Humidité")) {
                series.getData().add(new XYChart.Data(heure, Integer.parseInt(data.get(i).getHumidite())));
            } else if (type_donnee.equals("Nébulosité")) {
                series.getData().add(new XYChart.Data(heure, Integer.parseInt(data.get(i).getNebulosite())));
            }

            heure = heure + 3;
        }
        courbe_v.getData().add(series);
    }

    /**
     * Methode pour afficher la courbe d'un mois
     *
     * @param data
     * @param titre
     * @param station
     * @param type_donnee
     */
    public void Courbe_mois(List<DataClimate> data, String titre, String station, String type_donnee) {
        courbe_v.getData().clear();
        XYChart.Series series = new XYChart.Series();
        xAxis.setLabel("Jour");
        yAxis.setLabel(titre);
        courbe_v.setTitle("Evolution " + titre);
        series.setName(station);
        int heure = 0;
        for (int i = 0; i < data.size(); i++) {
            if (type_donnee.equals("Temp C")) {
                series.getData().add(new XYChart.Data(heure, Float.parseFloat(data.get(i).getTemp_c())));
            } else if (type_donnee.equals("Temp K")) {
                series.getData().add(new XYChart.Data(heure, Float.parseFloat(data.get(i).getTemp_k())));
            } else if (type_donnee.equals("Humidité")) {
                series.getData().add(new XYChart.Data(heure, Integer.parseInt(data.get(i).getHumidite())));
            } else if (type_donnee.equals("Nébulosité")) {
                series.getData().add(new XYChart.Data(heure, Integer.parseInt(data.get(i).getNebulosite())));
            }
            if (heure < 31) {
                heure++;
            }
        }
        courbe_v.getData().add(series);
    }

    /**
     * Methode pour affichger la courbe d'une année
     *
     * @param data
     * @param titre
     * @param station
     * @param type_donnee
     */
    public void Courbe_annee(List<DataClimate> data, String titre, String station, String type_donnee) {
        courbe_v.getData().clear();
        XYChart.Series series = new XYChart.Series();
        xAxis.setLabel("Année");
        yAxis.setLabel(titre);
        courbe_v.setTitle("Evolution " + titre);
        series.setName(station);
        int heure = 0;
        for (int i = 0; i < data.size(); i++) {
            if (type_donnee.equals("Temp C")) {
                series.getData().add(new XYChart.Data(heure, Float.parseFloat(data.get(i).getTemp_c())));
            } else if (type_donnee.equals("Temp K")) {
                series.getData().add(new XYChart.Data(heure, Float.parseFloat(data.get(i).getTemp_k())));
            } else if (type_donnee.equals("Humidité")) {
                series.getData().add(new XYChart.Data(heure, Integer.parseInt(data.get(i).getHumidite())));
            } else if (type_donnee.equals("Nébulosité")) {
                series.getData().add(new XYChart.Data(heure, Integer.parseInt(data.get(i).getNebulosite())));
            }
            if (heure < 12) {
                heure++;
            } else {
                break;
            }
        }
        courbe_v.getData().add(series);
    }

    /**
     * methode pour afficher une courbe d'un jour pour la comparaison
     *
     * @param data
     * @param titre
     * @param station
     * @param type_donnee
     */
    public void Courbe_jour_c(List<DataClimate> data, String titre, String station, String type_donnee) {
        //courbe_c.getData().clear();
        XYChart.Series series = new XYChart.Series();
        xAxis2.setLabel("Heure");
        yAxis2.setLabel(titre);
        courbe_c.setTitle("Evolution " + titre);
        series.setName(station);
        int heure = 0;
        for (int i = 0; i < data.size(); i++) {
            if (type_donnee.equals("Temp C")) {
                series.getData().add(new XYChart.Data(heure, Float.parseFloat(data.get(i).getTemp_c())));
            } else if (type_donnee.equals("Temp K")) {
                series.getData().add(new XYChart.Data(heure, Float.parseFloat(data.get(i).getTemp_k())));
            } else if (type_donnee.equals("Humidité")) {
                series.getData().add(new XYChart.Data(heure, Integer.parseInt(data.get(i).getHumidite())));
            } else if (type_donnee.equals("Nébulosité")) {
                series.getData().add(new XYChart.Data(heure, Integer.parseInt(data.get(i).getNebulosite())));
            }

            heure = heure + 3;
        }
        courbe_c.getData().add(series);
    }

    /**
     * methode pour afficher la courbe d'un mois pour la comparaison
     *
     * @param data
     * @param titre
     * @param station
     * @param type_donnee
     */
    public void Courbe_mois_c(List<DataClimate> data, String titre, String station, String type_donnee) {

        //System.out.println("Taille : " + data.size());
        //courbe_c.getData().clear();
        XYChart.Series series = new XYChart.Series();
        xAxis2.setLabel("Jour");
        yAxis2.setLabel(titre);
        courbe_c.setTitle("Evolution " + titre);
        series.setName(station);
        int heure = 0;
        for (int i = 0; i < data.size(); i++) {
            if (type_donnee.equals("Temp C")) {
                series.getData().add(new XYChart.Data(heure, Float.parseFloat(data.get(i).getTemp_c())));
            } else if (type_donnee.equals("Temp K")) {
                series.getData().add(new XYChart.Data(heure, Float.parseFloat(data.get(i).getTemp_k())));
            } else if (type_donnee.equals("Humidité")) {
                series.getData().add(new XYChart.Data(heure, Integer.parseInt(data.get(i).getHumidite())));
            } else if (type_donnee.equals("Nébulosité")) {
                series.getData().add(new XYChart.Data(heure, Integer.parseInt(data.get(i).getNebulosite())));
            }
            if (heure < 31) {
                heure++;
            }
        }
        courbe_c.getData().add(series);
    }

    /**
     * methode pour afficher la courbe d'une année pour la comparaison
     *
     * @param data
     * @param titre
     * @param station
     * @param type_donnee
     */
    public void Courbe_annee_c(List<DataClimate> data, String titre, String station, String type_donnee) {

        //System.out.println("Taille : " + data.size());
        //courbe_c.getData().clear();
        XYChart.Series series = new XYChart.Series();
        xAxis2.setLabel("Année");
        yAxis2.setLabel(titre);
        courbe_c.setTitle("Evolution " + titre);
        series.setName(station);
        int heure = 0;
        for (int i = 0; i < data.size(); i++) {
            if (type_donnee.equals("Temp C")) {
                series.getData().add(new XYChart.Data(heure, Float.parseFloat(data.get(i).getTemp_c())));
            } else if (type_donnee.equals("Temp K")) {
                series.getData().add(new XYChart.Data(heure, Float.parseFloat(data.get(i).getTemp_k())));
            } else if (type_donnee.equals("Humidité")) {
                series.getData().add(new XYChart.Data(heure, Integer.parseInt(data.get(i).getHumidite())));
            } else if (type_donnee.equals("Nébulosité")) {
                series.getData().add(new XYChart.Data(heure, Integer.parseInt(data.get(i).getNebulosite())));
            }
            if (heure < 12) {
                heure++;
            } else {
                break;
            }
        }
        courbe_c.getData().add(series);
    }

    /**
     * Methode qui affiche l'écart type
     *
     * @param data
     * @param type_donnee
     */
    public void visualiser_ecart_type(List<DataClimate> data, String type_donnee, boolean ecart_1_2) {
        if (!data.isEmpty()) {
            ArrayList<Integer> temp_c = new ArrayList<>();
            ArrayList<Integer> temp_k = new ArrayList<>();
            ArrayList<Integer> humidite = new ArrayList<>();
            ArrayList<Integer> nebulosite = new ArrayList<>();
            for (int i = 0; i < data.size(); i++) {
                if (type_donnee.equals("Temp C")) {
                    float x = Float.valueOf(data.get(i).getTemp_c());
                    int val = Math.round(x);
                    temp_c.add(val);
                } else if (type_donnee.equals("Temp K")) {
                    float x = Float.valueOf(data.get(i).getTemp_k());
                    int val = Math.round(x);
                    temp_k.add(val);
                } else if (type_donnee.equals("Humidité")) {
                    float x = Float.valueOf(data.get(i).getHumidite());
                    int val = Math.round(x);
                    humidite.add(val);
                } else if (type_donnee.equals("Nébulosité")) {
                    float x = Float.valueOf(data.get(i).getNebulosite());
                    int val = Math.round(x);
                    nebulosite.add(val);
                }

            }

            if (type_donnee.equals("Temp C")) {
                try {
                    if (ecart_1_2) {
                        ecart_type_1.setText(String.valueOf(modele.Ecart_type(temp_c)));
                    } else {
                        ecart_type_2.setText(String.valueOf(modele.Ecart_type(temp_c)));
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                }
            } else if (type_donnee.equals("Temp K")) {
                try {
                    if (ecart_1_2) {
                        ecart_type_1.setText(String.valueOf(modele.Ecart_type(temp_k)));
                    } else {
                        ecart_type_2.setText(String.valueOf(modele.Ecart_type(temp_k)));
                    }
                } catch (IndexOutOfBoundsException e) {
                }
            } else if (type_donnee.equals("Humidité")) {
                try {
                    if (ecart_1_2) {
                        ecart_type_1.setText(String.valueOf(modele.Ecart_type(humidite)));
                    } else {
                        ecart_type_1.setText(String.valueOf(modele.Ecart_type(humidite)));
                    }
                } catch (IndexOutOfBoundsException e) {
                }
            } else if (type_donnee.equals("Nébulosité")) {
                try {
                    if (ecart_1_2) {
                        ecart_type_1.setText(String.valueOf(modele.Ecart_type(nebulosite)));
                    } else {
                        ecart_type_2.setText(String.valueOf(modele.Ecart_type(nebulosite)));
                    }
                } catch (IndexOutOfBoundsException e) {
                }
            }

        }
    }
}
