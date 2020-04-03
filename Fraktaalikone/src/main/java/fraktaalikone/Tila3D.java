/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fraktaalikone;

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
public class Tila3D extends JPanel {

    private double[][] lista;
    private double[][] karki;
    private int pisteet;
    Color color;
    
    public Tila3D(Color color) {
        karki = new double[4][3];
        pisteet = 100000;
        karki[0][0] = 100; karki[0][1] = 100; karki[0][2] = 100;
        karki[1][0] = -100; karki[1][1] = -100; karki[1][2] = 100;
        karki[2][0] = -100; karki[2][1] = 100; karki[2][2] = -100;
        karki[3][0] = 100; karki[3][1] = -100; karki[3][2] = -100;
        alustaja();
        this.color = color;
    }
    
    public void alustaja() {
            lista = new double[pisteet][3];
            Random random = new Random();
            double kx = random.nextInt(100);
            double ky = random.nextInt(100);
            double kz = random.nextInt(100);
            for(int i = 0; i < pisteet; i++){
                int luku = random.nextInt(300000) % 4;
                    kx = (karki[luku][0] + kx)/2;
                    ky = (karki[luku][1] + ky)/2;
                    kz = (karki[luku][2] + kz)/2;
                    lista[i][0] = kx;
                    lista[i][1] = ky;
                    lista[i][2] = kz;
            }
    }
    
    public void kaantoX(){
        double[][] palautus = new double[pisteet][3]; 
        double[][] uuskarki = new double[4][3];
        for(int i = 0; i < pisteet; i++){
            palautus[i][0] = lista[i][0];
            palautus[i][1] = lista[i][1]*cos(0.1) + lista[i][2]*(-sin(0.1));
            palautus[i][2] = lista[i][1]*sin(0.1) + lista[i][2]*cos(0.1);
        }
        for(int j = 0; j < 4; j++){
            uuskarki[j][0] = karki[j][0];
            uuskarki[j][1] = karki[j][1]*cos(0.1) + karki[j][2]*(-sin(0.1));
            uuskarki[j][2] = karki[j][1]*sin(0.1) + karki[j][2]*cos(0.1);
        }
        this.lista = palautus;
        this.karki = uuskarki;
        super.repaint();
        Toolkit.getDefaultToolkit().sync();
    }
    
    public void venyta(int numero){
        karki[numero][0] = karki[numero][0]*1.1;
        karki[numero][1] = karki[numero][1]*1.1;
        karki[numero][2] = karki[numero][2]*1.1;
        this.alustaja();
        super.repaint();
        Toolkit.getDefaultToolkit().sync();
    }
    
    public void kutista(int numero){
        karki[numero][0] = karki[numero][0]*0.9;
        karki[numero][1] = karki[numero][1]*0.9;
        karki[numero][2] = karki[numero][2]*0.9;
        this.alustaja();
        super.repaint();
        Toolkit.getDefaultToolkit().sync();
    }
    
    public void paintComponent(Graphics g) {
        g.clearRect(0, 0, 1100, 1100);
        g.setColor(color);
        for(int i = 0; i < pisteet; i++){
            g.fillRect((int)lista[i][0]+400, (int)lista[i][1]+400, 1, 1);
        }
    }
}
