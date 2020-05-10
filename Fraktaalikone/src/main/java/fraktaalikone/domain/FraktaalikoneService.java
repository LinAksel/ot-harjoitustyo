/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fraktaalikone.domain;

import fraktaalikone.dao.FractalDao;
import java.util.ArrayList;
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
        fractalDao.update(fractal);
        if(!getFractalDataFromDB(data.get(0))){
            fractalDao.create(fractal);
        }
    }
    
    public Fractal getFractal() {
        return this.fractal;
    }
    
    public String getFractalName() {
        return fractal.getName();
    }
    
    public String getFractalColorName() {
        return fractal.getColorName();
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
    
    public List<String> getFractalNames() {
        return fractalDao.getFractalNames();
    }
    
    public void turnFractalX() {
        fractal.turnX();
    }
    
    public void turnFractalY() {
        fractal.turnY();
    }
    
    public void turnFractalZ() {
        fractal.turnZ();
    }
    
    public void shrinkFractal() {
        fractal.shrink();
    }
    
    public void stretchFractal() {
        fractal.stretch();
    }
    
    public void zoomFractalIn() {
        fractal.zoomIn();
    }
    
    public void zoomFractalOut() {
        fractal.zoomOut();
    }
    
    public void fractalChosenPoint(int point) {
        fractal.chosenPoint(point);
    }
    
    public void chosenFractalDots(int dots) {
        fractal.chosenDots(dots);
    }
    
    public void setFractalDivider(int divider) {
        fractal.setDivider(divider);
    }
    
    public void setFractalPointNumber(int number) {
        fractal.setPointNumber(number);
    }
    
    public void setFractalDimensions(int width, int height) {
        fractal.setDimensions(width, height);
    }
   
}
