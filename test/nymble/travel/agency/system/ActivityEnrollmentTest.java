/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package nymble.travel.agency.system;

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
public class ActivityEnrollmentTest {
    
    public ActivityEnrollmentTest() {
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
     * Test of populatePackageComboBox method, of class ActivityEnrollment.
     */
    @Test
    public void testPopulatePackageComboBox() {
        System.out.println("populatePackageComboBox");
        ActivityEnrollment instance = new ActivityEnrollment();
        instance.populatePackageComboBox();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setupListeners method, of class ActivityEnrollment.
     */
    @Test
    public void testSetupListeners() {
        System.out.println("setupListeners");
        ActivityEnrollment instance = new ActivityEnrollment();
        instance.setupListeners();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of populateDestinationComboBox method, of class ActivityEnrollment.
     */
    @Test
    public void testPopulateDestinationComboBox() {
        System.out.println("populateDestinationComboBox");
        ActivityEnrollment instance = new ActivityEnrollment();
        instance.populateDestinationComboBox();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of populateActivityList method, of class ActivityEnrollment.
     */
    @Test
    public void testPopulateActivityList() {
        System.out.println("populateActivityList");
        ActivityEnrollment instance = new ActivityEnrollment();
        instance.populateActivityList();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of enrollPassenger method, of class ActivityEnrollment.
     */
    @Test
    public void testEnrollPassenger() {
        System.out.println("enrollPassenger");
        ActivityEnrollment instance = new ActivityEnrollment();
        instance.enrollPassenger();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of calculatePrice method, of class ActivityEnrollment.
     */
    @Test
    public void testCalculatePrice() {
        System.out.println("calculatePrice");
        String passengerType = "";
        double activityCost = 0.0;
        ActivityEnrollment instance = new ActivityEnrollment();
        double expResult = 0.0;
        double result = instance.calculatePrice(passengerType, activityCost);
        assertEquals(expResult, result, 0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of main method, of class ActivityEnrollment.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        ActivityEnrollment.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
