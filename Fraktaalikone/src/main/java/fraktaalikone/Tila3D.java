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
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import java.util.Random;
import javax.swing.JPanel;

/**
 *
 * @author linaksel
 */
public class Tila3D extends JPanel implements ActionListener{

    private double[][] lista;
    private BufferedImage canvas;
    
    public Tila3D(int width, int height) {
        canvas = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        fillCanvas(Color.BLUE);
        //drawRect(Color.RED, 0, 0, width/2, height/2);
    }
    
    public void fillCanvas(Color c) {
            int color = c.getRGB();
            lista = new double[5000][3];
            double ekax = 100; double ekay = 100; double ekaz = 100;
            double tokax = -100; double tokay = -100; double tokaz = 100;
            double kolmasx = -100; double kolmasy = 100; double kolmasz = -100;
            double neljasx = 100; double neljasy = -100; double neljasz = -100;
            Random random = new Random();
            double kx = random.nextInt(100)+500;
            double ky = random.nextInt(100)+500;
            double kz = random.nextInt(100);
            for(int i = 0; i < 5000; i++){
                int luku = random.nextInt(300000) % 4;
                if(luku == 0){
                    kx = (ekax + kx)/2;
                    ky = (ekay + ky)/2;
                    kz = (ekaz + kz)/2;
                    lista[i][0] = kx;
                    lista[i][1] = ky;
                    lista[i][2] = kz;
                }
                if(luku == 1){
                    kx = (tokax + kx)/2;
                    ky = (tokay + ky)/2;
                    kz = (tokaz + kz)/2;
                    lista[i][0] = kx;
                    lista[i][1] = ky;
                    lista[i][2] = kz;
                }
                if(luku == 2){
                    kx = (kolmasx + kx)/2;
                    ky = (kolmasy + ky)/2;
                    kz = (kolmasz + kz)/2;
                    lista[i][0] = kx;
                    lista[i][1] = ky;
                    lista[i][2] = kz;
                }
                if(luku == 3){
                    kx = (neljasx + kx)/2;
                    ky = (neljasy + ky)/2;
                    kz = (neljasz + kz)/2;
                    lista[i][0] = kx;
                    lista[i][1] = ky;
                    lista[i][2] = kz;
                }
                canvas.setRGB((int)kx+500, (int)ky+500, color);
            }
        repaint();
    }
    
    public void kaantoX(int leveys, int korkeus){
        canvas = new BufferedImage(leveys, korkeus, BufferedImage.TYPE_INT_ARGB);
        for(int i = 0; i < 5000; i++){
            lista[i][1] = (double) (lista[i][1]*cos(0.01) + lista[i][2]*(-sin(0.01)));
            lista[i][2] = (double) (lista[i][1]*sin(0.01) + lista[i][2]*cos(0.01));
            canvas.setRGB((int)lista[i][0]+500, (int)lista[i][1]+500, Color.BLUE.getRGB());
        }
        repaint();
        Toolkit.getDefaultToolkit().sync();
    }
    
    public Dimension getPreferredSize() {
        return new Dimension(canvas.getWidth(), canvas.getHeight());
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(canvas, null, null);
        Toolkit.getDefaultToolkit().sync();
    }
    
    @Override
    public void actionPerformed(ActionEvent arg0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
