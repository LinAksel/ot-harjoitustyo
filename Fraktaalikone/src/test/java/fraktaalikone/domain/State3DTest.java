/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fraktaalikone.domain;

import java.awt.Color;
import static junit.framework.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author linaksel
 */
public class State3DTest {
    
    @Test
    public void assignedColorIsSet(){
        State3D test3D = new State3D(Color.GREEN, 2);
        assertTrue(test3D.getColor().equals(Color.GREEN));
    }
    
    @Test
    public void startingPointIsRight(){
        State3D test3D = new State3D(Color.BLUE, 3);
        assertTrue(test3D.getChosen() == 3);
    }
    
}
