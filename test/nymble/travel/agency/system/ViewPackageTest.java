/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package nymble.travel.agency.system;

import java.awt.event.ActionEvent;
import java.util.Vector;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Shishir
 */
public class ViewPackageTest {
    
    public ViewPackageTest() {
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
     * Test of fetchTravelPackagesFromDatabase method, of class ViewPackage.
     */
    @Test
    public void testFetchTravelPackagesFromDatabase() {
        System.out.println("fetchTravelPackagesFromDatabase");
        ViewPackage instance = new ViewPackage();
        Vector<Vector<Object>> expResult = null;
        Vector<Vector<Object>> result = instance.fetchTravelPackagesFromDatabase();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of fetchDestinationsForPackage method, of class ViewPackage.
     */
    @Test
    public void testFetchDestinationsForPackage() {
        System.out.println("fetchDestinationsForPackage");
        String packageName = "";
        ViewPackage instance = new ViewPackage();
        Vector<String> expResult = null;
        Vector<String> result = instance.fetchDestinationsForPackage(packageName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of actionPerformed method, of class ViewPackage.
     */
    @Test
    public void testActionPerformed() {
        System.out.println("actionPerformed");
        ActionEvent ae = null;
        ViewPackage instance = new ViewPackage();
        instance.actionPerformed(ae);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of main method, of class ViewPackage.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        ViewPackage.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
