/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fraktaalikone.dao;

import fraktaalikone.domain.Fractal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author linaksel
 */
public class DotFractalDao extends FractalDao {
    
    public DotFractalDao(String database) {
        super(database);
        String sql = "CREATE TABLE IF NOT EXISTS DotFractals (\n"
                + " id integer PRIMARY KEY,\n"
                + " name text NOT NULL,\n"
                + " realPoints text NOT NULL,\n"
                + " color text NOT NULL,\n"
                + " xCoordinates text NOT NULL,\n"
                + " yCoordinates text NOT NULL,\n"
                + " zCoordinates text NOT NULL\n"
                + ");";

        try {
            Statement statement = connection.createStatement();
            statement.execute(sql);
            statement.close();
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Fractal create(Fractal fractal) {
        String sql = "INSERT INTO"
                + " DotFractals(name,realPoints,color,xCoordinates,yCoordinates,zCoordinates)"
                + " VALUES(?,?,?,?,?,?)";
        
        List<String> data = fractal.getData();
        
        try {
            PreparedStatement prepared = connection.prepareStatement(sql);
            
            for(int i = 0; i < 6; i++){
                prepared.setString(i + 1, data.get(i));
            }

            prepared.executeUpdate();
            prepared.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return fractal;
    }

    @Override
    public ArrayList<String> getFractalNames() {
        
        String sql = "SELECT * FROM DotFractals;";
        
        ArrayList<String> names = new ArrayList<>();
        
        try {
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery(sql);

            while (results.next()) {
                names.add(results.getString("name"));
            }
            statement.close();
        
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return names;
    }

    @Override
    public List<String> getFractalData(String name) {
        
        String sql = "SELECT * FROM DotFractals WHERE name = ?";
        ArrayList<String> data = new ArrayList<>();

        try {
            PreparedStatement prepared = connection.prepareStatement(sql);
            prepared.setString(1, name);
            ResultSet results = prepared.executeQuery();
            for(int i = 2; i <= 7; i++){
                data.add(results.getString(i)); 
            }
            prepared.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return data;
    }

    @Override
    public Boolean update(Fractal fractal) {
        String sql = "UPDATE DotFractals SET realPoints = ? , "
                + "color = ? , "
                + "xCoordinates = ? , "
                + "yCoordinates = ? , "
                + "zCoordinates = ? "
                + "WHERE name = ?";

        try {
            PreparedStatement prepared = connection.prepareStatement(sql);
            for(int i = 0; i < 6; i++) {
                prepared.setString(i + 1, fractal.getData().get(i));
            }
            prepared.executeUpdate();
            prepared.close();
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public Boolean remove(Fractal fractal) {
        String sql = "DELETE FROM DotFractals WHERE name = ?";
        ArrayList<String> data = new ArrayList<>();

        try {
            connect();
            PreparedStatement prepared = connection.prepareStatement(sql);
            prepared.setString(1, fractal.getData().get(0));
            prepared.executeUpdate();
            prepared.close();
            connection.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }
    
}
