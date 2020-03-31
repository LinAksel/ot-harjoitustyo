/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fraktaalikone;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Random;
import javax.swing.JPanel;

/**
 *
 * @author linaksel
 */
public class Tila2D extends JPanel implements ActionListener{

    private double[][] lista;
    private BufferedImage canvas;
    
    public Tila2D(int width, int height) {
        canvas = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        fillCanvas(Color.BLUE);
        //drawRect(Color.RED, 0, 0, width/2, height/2);
    }
    
    public void fillCanvas(Color c) {
            int color = c.getRGB();
            lista = new double[5000][3];
            double ekax = 100; double ekay = 100;
            double tokax = 200; double tokay = 100;
            double kolmasx = 150; double kolmasy = (Math.sqrt(3)/2)*100+100;
            Random random = new Random();
            double kx = random.nextInt(200);
            double ky = random.nextInt(200);
            for(int i = 0; i < 5000; i++){
                int luku = random.nextInt(300000) % 3;
                if(luku == 0){
                    kx = (ekax + kx)/2;
                    ky = (ekay + ky)/2;
                    lista[i][0] = kx;
                    lista[i][1] = ky;
                }
                if(luku == 1){
                    kx = (tokax + kx)/2;
                    ky = (tokay + ky)/2;
                    lista[i][0] = kx;
                    lista[i][1] = ky;
                }
                if(luku == 2){
                    kx = (kolmasx + kx)/2;
                    ky = (kolmasy + ky)/2;
                    lista[i][0] = kx;
                    lista[i][1] = ky;
                }
                canvas.setRGB((int)kx, (int)ky, color);
            }
        repaint();
    }
    
    public Dimension getPreferredSize() {
        return new Dimension(canvas.getWidth(), canvas.getHeight());
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(canvas, null, null);
    }
    
    @Override
    public void actionPerformed(ActionEvent arg0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
