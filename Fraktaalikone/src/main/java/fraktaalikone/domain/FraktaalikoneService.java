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
   
}
