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
public class FractalBuilderTest {
    
    FractalBuilder fractal;
    
    @Before
    public void setUp(){
        fractal = new FractalBuilder(Color.GREEN, 10);
    }
    
    @Test
    public void assignedColorIsSet(){
        assertTrue(fractal.getColor().equals(Color.GREEN));
    }
    
    @Test
    public void startingPointIsRight(){
        assertTrue(fractal.getChosen() == 0);
    }
    
    @Test
    public void correctStartDots(){
        assertTrue(fractal.getDots() == 10000);
    }
    
    @Test
    public void dotsChangeCorrectly(){
        fractal.chosenDots(120);
        assertTrue(fractal.getDots() == 120000);
    }
    
    @Test
    public void turnXReturnDifferentDots(){
        double[][] lista = fractal.getDotList();
        fractal.turnX();
        assertTrue(!Arrays.equals(lista, fractal.getDotList()));
    }
    
    @Test
    public void turnZReturnDifferentDots(){
        double[][] lista = fractal.getDotList();
        fractal.turnZ();
        assertTrue(!Arrays.equals(lista, fractal.getDotList()));
    }
    
    @Test
    public void turnYReturnDifferentDots(){
        double[][] lista = fractal.getDotList();
        fractal.turnY();
        assertTrue(!Arrays.equals(lista, fractal.getDotList()));
    }
    
    @Test
    public void chosenOverflowActsCorrectly() {
        int real = fractal.getRealPointNumber();
        int number = fractal.getPointNumber();
        fractal.chosenPoint(real+(number-real));
        assertTrue(fractal.getChosen() == 0);
    }
    
}
