/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fraktaalikone.domain;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
import static java.lang.Math.cos;
import static java.lang.Math.min;
import static java.lang.Math.sin;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.JPanel;

/**
 *
 * @author linaksel
 */
public class DotFractal extends JPanel implements Fractal{

    private double[][] dotList;
    private double[][] points;
    private int pointNumber = 3;
    private int realPointNumber;
    private String name;
    private int divider = 2;
    private int dots;
    private int chosen = 0;
    private int width;
    private int height;
    private Color color;
    private String colorString;

    public DotFractal(int dots, int width, int height) {
        points = new double[20][3];
        this.width = width;
        this.height = height;
        this.dots = dots * 1000;
    }
    
    private void builder() {
        dotList = new double[dots][3];
        Random random = new Random();
        double kx = random.nextInt(100);
        double ky = random.nextInt(100);
        double kz = random.nextInt(100);
        for (int i = 0; i < dots; i++) {
            int luku = random.nextInt(300000) % pointNumber;
            kx = (points[luku][0] + kx) / divider;
            ky = (points[luku][1] + ky) / divider;
            kz = (points[luku][2] + kz) / divider;
            dotList[i][0] = kx;
            dotList[i][1] = ky;
            dotList[i][2] = kz;
        }
    }
    
    //Note to self: Eri kääntöjä on hyvin vaikea saada yhteiseen metodiin listakäsittelyerojen takia vaikka näyttä copypastelta, mutta palaa tähän vielä (edelleen pohdinnan alla 28.4)
    
    public void turnX() {
        double[][] newDots = new double[dots][3]; 
        double[][] newPoints = new double[20][3];
        for (int i = 0; i < dots; i++) {
            newDots[i][0] = dotList[i][0];
            newDots[i][1] = dotList[i][1] * cos(0.1) + dotList[i][2] * (-sin(0.1));
            newDots[i][2] = dotList[i][1] * sin(0.1) + dotList[i][2] * cos(0.1);
        }
        for (int j = 0; j < 20; j++) {
            newPoints[j][0] = points[j][0];
            newPoints[j][1] = points[j][1] * cos(0.1) + points[j][2] * (-sin(0.1));
            newPoints[j][2] = points[j][1] * sin(0.1) + points[j][2] * cos(0.1);
        }
        this.dotList = newDots;
        this.points = newPoints;
        super.repaint();
        Toolkit.getDefaultToolkit().sync();
    }
    
    public void turnY() {
        double[][] newDots = new double[dots][3]; 
        double[][] newPoints = new double[20][3];
        for (int i = 0; i < dots; i++) {
            newDots[i][0] = dotList[i][0] * cos(0.1) + dotList[i][2] * sin(0.1);
            newDots[i][1] = dotList[i][1];
            newDots[i][2] = dotList[i][0] * (-sin(0.1)) + dotList[i][2] * cos(0.1);
        }
        for (int j = 0; j < 20; j++) {
            newPoints[j][0] = points[j][0] * cos(0.1) + points[j][2] * sin(0.1);
            newPoints[j][1] = points[j][1];
            newPoints[j][2] = points[j][0] * (-sin(0.1)) + points[j][2] * cos(0.1);
        }
        this.dotList = newDots;
        this.points = newPoints;
        super.repaint();
        Toolkit.getDefaultToolkit().sync();
    }
    
    public void turnZ() {
        double[][] newDots = new double[dots][3]; 
        double[][] newPoints = new double[20][3];
        for (int i = 0; i < dots; i++) {
            newDots[i][0] = dotList[i][0] * cos(0.1) + dotList[i][1] * (-sin(0.1));
            newDots[i][1] = dotList[i][0] * sin(0.1) + dotList[i][1] * cos(0.1);
            newDots[i][2] = dotList[i][2];
        }
        for (int j = 0; j < 20; j++) {
            newPoints[j][0] = points[j][0] * cos(0.1) + points[j][1] * (-sin(0.1));
            newPoints[j][1] = points[j][0] * sin(0.1) + points[j][1] * cos(0.1);
            newPoints[j][2] = points[j][2];
        }
        this.dotList = newDots;
        this.points = newPoints;
        super.repaint();
        Toolkit.getDefaultToolkit().sync();
    }
    
    public void stretch() {
        this.stretcher(1.1);
    }
    
    public void shrink() {
        this.stretcher(0.9);
    }
    
    public void zoomIn() {
        this.zoomer(1.01);
    }
    
    public void zoomOut() {
        this.zoomer(0.99);
    }
    
    private void zoomer(double multi) {
        for (int j = 0; j < 20; j++) {
            for (int i = 0; i < 3; i++) {
                points[j][i] = points[j][i] * multi;
            }
        }
        this.builder();
        super.repaint();
        Toolkit.getDefaultToolkit().sync();
    }
    
    private void stretcher(double multi) {
        for (int i = 0; i < 3; i++) {
            points[chosen][i] = points[chosen][i] * multi;
        }
        this.builder();
        super.repaint();
        Toolkit.getDefaultToolkit().sync();
    }
    
    //Kulmapisteen valitsija
    public void chosenPoint(int newChosen) {
        int modulo = pointNumber;
        if (modulo > realPointNumber) {
            modulo = realPointNumber;
        }
        //tutkii ylivuodon laskennallisen ja todellisen kulmapisteen välillä, ja pitää käyttäjän vain todellisissa
        this.chosen = (this.chosen + newChosen) % modulo;
        if (this.chosen < 0) {
            this.chosen = modulo - 1;
        }
        super.repaint();
        Toolkit.getDefaultToolkit().sync();
    }
    
    //Satunnaispisteiden määrä
    public void chosenDots(int dots) {
        this.dots = dots * 1000;
        builder();
        super.repaint();
        Toolkit.getDefaultToolkit().sync();
    }
    
    @Override
    public void paintComponent(Graphics g) {
        g.clearRect(0, 0, width, height);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, width, height);
        g.setColor(color);
        for (int i = 0; i < dots; i++) {
            g.fillRect((int) dotList[i][0] + width/2 - 180, (int) dotList[i][1] + height/2, 1, 1);
        }
        g.setColor(Color.RED);
        g.fillRect((int) dotScaler(0) + width/2 - 180, (int) dotScaler(1) + height/2, 6, 6);
    }
    
    //Hieman kryptinen metodi, tämä siis skaalaa värityspallon valitusta kulmasta oikein kaikilla valinnoilla!
    public double dotScaler(int coord) {
        double place = points[chosen][coord];
        place = place / (divider - 1);
        return place;
    }
    
    //Jakajaluvun settaaja
    public void setDivider(int divider) {
        this.divider = divider;
        builder();
        super.repaint();
        Toolkit.getDefaultToolkit().sync();
    }
    
    //Kulmapisteiden laskennallinen määrä satunnaislukujen arvontaa varten. Mikäli pohjassa on vähemmän/enemmän todellisia kulmapisteitä, syntyy mielenkiintoisia kuvioita!!
    public void setPointNumber(int number) {
        this.pointNumber = number;
        builder();
        super.repaint();
        Toolkit.getDefaultToolkit();
    }
    
    public void setDimensions(int width, int height){
        this.width = width;
        this.height = height;
        super.repaint();
        Toolkit.getDefaultToolkit().sync();
    }
    
    public Color getColor() {
        return color;
    }
    
    public int getChosen() {
        return chosen;
    }
    
    public int getDots() {
        return dots;
    }
    
    public int getPointNumber() {
        return pointNumber;
    }
    
    @Override
    public int getRealPointNumber() {
        return realPointNumber;
    }
    
    public String getColorName() {
        return colorString;
    }
    
    public double[][] getDotList() {
        return this.dotList;
    }
    
    @Override
    public String getName() {
        return this.name;
    }
    
    @Override
    public double[][] getPointList() {
        return this.points;
    }
    

    @Override
    public ArrayList<String> getData() {
        ArrayList<String> data = new ArrayList<>();
        data.add(name);
        data.add(Integer.toString(realPointNumber));
        data.add(colorString);
        String coords = "";
        for(int i = 0; i < 3; i++){
            coords = "";
            for(int j = 0; j < realPointNumber; j++){
                if(j == 0) {
                    coords = coords + points[j][i];
                } else {
                    coords = coords + "," + points[j][i];
                }
            }
            data.add(coords);
        }
        return data;
    }

    @Override
    public void setData(List<String> data) {
        name = data.get(0);
        realPointNumber = Integer.parseInt(data.get(1));
        color = Color.getColor(data.get(2));
        colorString = data.get(2);
        String[] xCoord = data.get(3).split(",");
        String[] yCoord = data.get(4).split(",");
        String[] zCoord = data.get(5).split(",");
        String[][] coords = { xCoord, yCoord, zCoord };
        points = new double[20][3];
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < min(20, xCoord.length); j++) {
                points[j][i] = Double.parseDouble(coords[i][j]);
            }
        }
        builder();
        super.repaint();
        Toolkit.getDefaultToolkit();
    }
    
}
