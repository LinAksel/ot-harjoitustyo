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
import static java.lang.Math.sin;
import java.util.Random;
import javax.swing.JPanel;

/**
 *
 * @author linaksel
 */
public class Fractal extends JPanel {

    private double[][] dotList;
    private double[][] points;
    private int pointNumber = 3;
    //Todelliselle kulmien määrälle tulee enemmän käyttöä kun tiedostohaku toteutuu viimeisellä viikolla!
    private int realPointNumber = 8;
    private int divider = 2;
    private int dots;
    private int chosen;
    Color color;

//Eri pistemäärän fraktaalit (ja 2D/3D) vihdoin yhdistetty samaan! Kulmapisteiden rajoittaminen 20 tuntuu vaatimusmäärittelyn mukaiselta "sopivalta rajalta" 
    
    public Fractal(Color color, int dots) {
        points = new double[20][3];
        this.dots = dots * 1000;
        
//Tähän tulee viimeisellä viikolla DAO-tiedostovaihtelua, jotta saadaa monimuotoista pisteidenhakua.
//        points[0][0] = 100;
//        points[0][1] = 100;
//        points[0][2] = 100;
//        points[1][0] = -100;
//        points[1][1] = -100;
//        points[1][2] = 100;
//        points[2][0] = -100;
//        points[2][1] = 100;
//        points[2][2] = -100;
//        points[3][0] = 100;
//        points[3][1] = -100;
//        points[3][2] = -100;

//Nyt aloituspisteet ovat kuution muodossa, mutta kolmen pisteen valinnalla alkuun aloitus on silti Sierpinskin kolmio!

        points[0][0] = 100;
        points[0][1] = 100;
        points[0][2] = -100;
        points[1][0] = -100;
        points[1][1] = 100;
        points[1][2] = -100;
        points[2][0] = 100;
        points[2][1] = -100;
        points[2][2] = -100;
        points[3][0] = -100;
        points[3][1] = -100;
        points[3][2] = -100;
        points[4][0] = 100;
        points[4][1] = 100;
        points[4][2] = 100;
        points[5][0] = -100;
        points[5][1] = 100;
        points[5][2] = 100;
        points[6][0] = 100;
        points[6][1] = -100;
        points[6][2] = 100;
        points[7][0] = -100;
        points[7][1] = -100;
        points[7][2] = 100;
        builder();
        this.color = color;
    }
    
    //Builder on vastuussa satunnaispisteiden piirrosta kulmapisteiden mukaan, se käyttää siis näitä pisteitä sekä jakajalukua, eli sitä, miten uusi piste asettuu
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
        if(modulo > realPointNumber){
            modulo = realPointNumber;
        }
        this.chosen = (this.chosen + newChosen) % modulo;
        if(this.chosen < 0) {
            this.chosen = modulo-1;
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
    
    //Piirtäjä
    public void paintComponent(Graphics g) {
        g.clearRect(0, 0, 1100, 1100);
        g.setColor(color);
        for (int i = 0; i < dots; i++) {
            g.fillRect((int) dotList[i][0] + 400, (int) dotList[i][1] + 400, 1, 1);
        }
        g.setColor(Color.RED);
        g.fillRect((int) dotScaler(0) + 400, (int) dotScaler(1) + 400, 6, 6);
    }
    
    //Hieman kryptinen metodi, tämä siis skaalaa väritetyn valitun kulmapisteen paikan oikein! Tämän matematiikkaa oli hauska pohtia
    public double dotScaler(int coord) {
        double place = points[chosen][coord];
        place = place/(divider-1);
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
    
    public Color getColor() {
        return color;
    }
    
    public int getChosen() {
        return chosen;
    }
    
    public int getDots() {
        return dots;
    }
    
    public double[][] getDotList() {
        return this.dotList;
    }
}
