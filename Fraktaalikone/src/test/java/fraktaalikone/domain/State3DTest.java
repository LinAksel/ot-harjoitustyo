/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fraktaalikone.domain;

import java.awt.Color;
import static junit.framework.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author linaksel
 */
public class State3DTest {
    
    State3D test3D;
    
    @Before
    public void setUp(){
        test3D = new State3D(Color.GREEN, 2);
    }
    
    @Test
    public void assignedColorIsSet(){
        assertTrue(test3D.getColor().equals(Color.GREEN));
    }
    
    @Test
    public void startingPointIsRight(){
        assertTrue(test3D.getChosen() == 2);
    }
    
    @Test
    public void pointCorrectAfterChange(){
        test3D.chosenPoint(3);
        assertTrue(test3D.getChosen() == 3);
    }
    
}
