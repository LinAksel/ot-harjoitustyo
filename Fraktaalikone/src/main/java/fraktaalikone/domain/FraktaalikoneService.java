/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fraktaalikone.domain;

import fraktaalikone.dao.FractalDao;
import java.util.List;

/**
 *
 * @author linaksel
 */
public class FraktaalikoneService {
    
    private FractalDao fractalDao;
    private Fractal fractal;
    
    public FraktaalikoneService(FractalDao fractalDao, Fractal fractal) {
        this.fractalDao = fractalDao;
        this.fractal = fractal;
    }
    
    public Boolean getFractalDataFromDB(String name) {
        List<String> data = fractalDao.getFractalData(name);
        if(data.isEmpty()){
            return false;
        }
        fractal.setData(data);
        return true;
    }
    
    public void addFractalToDB() {
        List<String> data = fractal.getData();
        if(!getFractalDataFromDB(data.get(0))){
            fractalDao.create(fractal);
        } else {
            fractalDao.update(fractal);
        }
    }
    
    public void addDataToDB(List<String> data) {
        this.fractal.setData(data);
        if(!getFractalDataFromDB(data.get(0))){
            fractalDao.create(this.fractal);
        } else {
            fractalDao.update(this.fractal);
        }
    }
    
    public double[] getSimpleDotList() {
        double[][] dotList = fractal.getPointList();
        double[] list = new double[60];
        int counter = 0;
        for(int i = 0; i < 20; i++){
            for(int j = 0; j < 3; j++){
                list[counter] = dotList[i][j];
                counter++;
            }
        }
        return list;
    }
    
    public String getRealPoints() {
        return Integer.toString(fractal.getRealPointNumber());
    }
    
    public double[] getSimpleDotListFromDB() {
        double[][] dotList = fractal.getPointList();
        double[] list = new double[60];
        int counter = 0;
        for(int i = 0; i < 20; i++){
            for(int j = 0; j < 3; j++){
                list[counter] = dotList[i][j];
                counter++;
            }
        }
        return list;
    }
   
}
