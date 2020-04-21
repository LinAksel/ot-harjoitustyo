/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fraktaalikone.domain;

import java.awt.Color;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import java.util.Arrays;
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
        test2D = new State2D(Color.RED, 200);
    }
    
    @Test
    public void assignedColorIsSet(){
        assertTrue(test2D.getColor().equals(Color.RED));
    }
    
    @Test
    public void correctStartDots(){
        assertTrue(test2D.getDots() == 200000);
    }
    
    @Test
    public void dotsChangeCorrectly(){
        test2D.chosenDots(250);
        assertTrue(test2D.getDots() == 250000);
    }
    
    @Test
    public void turnXReturnsDifferentDots(){
        double[][] lista = test2D.getDotList();
        test2D.turnX();
        assertTrue(!Arrays.equals(lista,test2D.getDotList()));
    }
    
    @Test
    public void turnZReturnsDifferentDots(){
        double[][] lista = test2D.getDotList();
        test2D.turnZ();
        assertTrue(!Arrays.equals(lista,test2D.getDotList()));
    }
    
    @Test
    public void turnYReturnsDifferentDots(){
        double[][] lista = test2D.getDotList();
        test2D.turnY();
        assertTrue(!Arrays.equals(lista,test2D.getDotList()));
    }
    
}
