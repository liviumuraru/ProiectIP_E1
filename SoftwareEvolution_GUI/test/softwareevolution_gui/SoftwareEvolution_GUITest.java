/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwareevolution_gui;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author tda_m
 */
public class SoftwareEvolution_GUITest {
    
    public SoftwareEvolution_GUITest() {
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

    
    @Test
    public void testDisplayInfo1() {
        System.out.println("Test 1: Both null");
        ArrayList<String> tags = new ArrayList<>();
        ArrayList<String> selectedLangs = new ArrayList<>();
        
        SoftwareEvolution_GUI instance = new SoftwareEvolution_GUI();
        boolean expResult = false;
        boolean result = instance.displayInfo(tags, selectedLangs);
        assertEquals(expResult, result);
    }

    @Test
    public void testDisplayInfo2() {
        System.out.println("Test 2: only tags null");
        ArrayList<String> tags = new ArrayList<>();
        ArrayList<String> selectedLangs = new ArrayList<>();
        selectedLangs.add("lang1");
        
        SoftwareEvolution_GUI instance = new SoftwareEvolution_GUI();
        boolean expResult = false;
        boolean result = instance.displayInfo(tags, selectedLangs);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testDisplayInfo3() {
        System.out.println("Test 3: only languages null");
        ArrayList<String> tags = new ArrayList<>();
        ArrayList<String> selectedLangs = new ArrayList<>();
        tags.add("tag1");
        
        SoftwareEvolution_GUI instance = new SoftwareEvolution_GUI();
        boolean expResult = false;
        boolean result = instance.displayInfo(tags, selectedLangs);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testDisplayInfo4() {
        System.out.println("Test 4: Both not null");
        ArrayList<String> tags = new ArrayList<>();
        ArrayList<String> selectedLangs = new ArrayList<>();
        tags.add("tag1"); selectedLangs.add("lang1");
        
        SoftwareEvolution_GUI instance = new SoftwareEvolution_GUI();
        boolean expResult = true;
        boolean result = instance.displayInfo(tags, selectedLangs);
        assertEquals(expResult, result);    }
    
}
