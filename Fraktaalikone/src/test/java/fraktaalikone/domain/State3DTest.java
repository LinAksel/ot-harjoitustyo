/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fraktaalikone.domain;

import java.awt.Color;
import java.util.Arrays;
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
        test3D = new State3D(Color.GREEN, 2, 10);
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
    
    @Test
    public void correctStartDots(){
        assertTrue(test3D.getDots() == 10000);
    }
    
    @Test
    public void dotsChangeCorrectly(){
        test3D.chosenDots(120);
        assertTrue(test3D.getDots() == 120000);
    }
    
    @Test
    public void turnXReturnDifferentDots(){
        double[][] lista = test3D.getDotList();
        test3D.turnX();
        assertTrue(!Arrays.equals(lista, test3D.getDotList()));
    }
    
    @Test
    public void turnZReturnDifferentDots(){
        double[][] lista = test3D.getDotList();
        test3D.turnZ();
        assertTrue(!Arrays.equals(lista, test3D.getDotList()));
    }
    
    @Test
    public void turnYReturnDifferentDots(){
        double[][] lista = test3D.getDotList();
        test3D.turnY();
        assertTrue(!Arrays.equals(lista, test3D.getDotList()));
    }
    
}
