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
public class State2DTest {
    
    State2D test2D;
    
    @Before
    public void setUp(){
        test2D = new State2D(Color.RED);
    }
    
    @Test
    public void assignedColorIsSet(){
        assertTrue(test2D.getColor().equals(Color.RED));
    }
    
}
