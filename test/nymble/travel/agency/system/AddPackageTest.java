/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package nymble.travel.agency.system;

import java.awt.event.ActionEvent;
import javax.swing.JTextField;
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
public class AddPackageTest {
    
    public AddPackageTest() {
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
     * Test of actionPerformed method, of class AddPackage.
     */
    @Test
    public void testActionPerformed() {
        System.out.println("actionPerformed");
        ActionEvent ae = null;
        AddPackage instance = new AddPackage();
        instance.actionPerformed(ae);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of generateDynamicFields method, of class AddPackage.
     */
    @Test
    public void testGenerateDynamicFields() {
        System.out.println("generateDynamicFields");
        AddPackage instance = new AddPackage();
        instance.generateDynamicFields();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findTextFieldByIndex method, of class AddPackage.
     */
    @Test
    public void testFindTextFieldByIndex() {
        System.out.println("findTextFieldByIndex");
        int index = 0;
        AddPackage instance = new AddPackage();
        JTextField expResult = null;
        JTextField result = instance.findTextFieldByIndex(index);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of main method, of class AddPackage.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        AddPackage.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
