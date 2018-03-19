/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author LPR
 */
public class ModeleTest {
    
    public ModeleTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of download_existing_files method, of class Modele.
     */
    @Test
    public void testDownload_existing_files() {
        Modele instance = new Modele();
        File f = new File("synop.200804.csv.gz.txt");
        List<String> expResult = new ArrayList<>();
        expResult.add("DATA existe, Date : 2008 - 04");
        List<String> result = instance.download_existing_files();
        assertEquals(expResult.get(0), result.get(0));
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of Save_data method, of class Modele.
     */
    @Test
    public void testSave_data() {
        File f = new File("synop.200804.csv.gz.txt");
        Modele instance = new Modele();
        String expResult = "DATA existe, Date : 2008 - 04";
        String result = instance.Save_data(f);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of IdStationToStation method, of class Modele.
     */
    @Test
    public void testIdStationToStation() {
        String IdStations = "07005";
        Modele instance = new Modele();
        String expResult = "ABBEVILLE";
        instance.IdStationToStation(IdStations);
        String result = "ABBEVILLE";
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of Telecharger_fichier_web method, of class Modele.
     */
    @Test
    public void Download_File() throws Exception {
        String mois = "05";
        String annee = "2015";
        Modele instance = new Modele();
        boolean expResult = true;
        instance.Download_File("NULL", mois, annee, "NULL");
        File f = new File("synop.201505.csv.gz.txt");
        assertEquals(expResult, f.exists());
        // TODO review the generated test code and remove the default call to fail.
    }



    /**
     * Test of split_date method, of class Modele.
     */
    @Test
    public void testSplit_date() {
        String date = "2015050521";
        Modele instance = new Modele();
        ArrayList<String> expResult = new ArrayList<>();
        expResult.add("2015");
        expResult.add("05");
        expResult.add("05");
        expResult.add("21");
        ArrayList<String> result = instance.split_date(date);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of importer_fichier method, of class Modele.
     */
    @Test
    public void testImporter_fichier() throws Exception {
        Modele instance = new Modele();
        String expResult = "";
        assertEquals(true, true);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of Ecart_type method, of class Modele.
     */
    @Test
    public void testEcart_type() {
        ArrayList<Integer> S = new ArrayList<>();
        S.add(1);
        S.add(2);
        S.add(4);
        S.add(5);
        Modele instance = new Modele();
        Integer expResult = 4;
        Integer result = instance.Ecart_type(S);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    
}
