/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fraktaalikone.dao;

import fraktaalikone.domain.Fractal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;


public abstract class FractalDao {
    
    protected String database;
    protected Connection connection;
    
    public FractalDao(String database) {
        this.database = database;
        connect();
    }
    
    protected void connect() {
        String url = "jdbc:sqlite:./" + database;

        try {
            connection = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public abstract Fractal create(Fractal fractal);
    
    public abstract List<String> getFractalNames();
    
    public abstract List<String> getFractalData(String name);
    
    public abstract void update(Fractal fractal);
    
    public abstract Boolean remove(Fractal fractal);
    
}
