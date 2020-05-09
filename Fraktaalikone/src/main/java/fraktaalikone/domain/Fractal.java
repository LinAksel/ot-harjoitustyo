/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fraktaalikone.domain;

import java.util.List;

/**
 *
 * @author linaksel
 */
public interface Fractal {
    
    List<String> getData();
    
    void setData(List<String> data);
    
    double[][] getPointList();
    
    int getRealPointNumber();
    
    String getName();
    
}
