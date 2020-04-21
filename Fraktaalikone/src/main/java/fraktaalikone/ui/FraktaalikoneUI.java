package fraktaalikone.ui;

import fraktaalikone.domain.State2D;
import fraktaalikone.domain.State3D;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class FraktaalikoneUI implements ActionListener, KeyListener, ChangeListener{
    JButton choose2D;
    JButton choose3D;
    JButton rotate;
    JSlider slider3D;
    JSlider slider2D;
    int point3D = 0;
    boolean in3D = false;
    State3D threeD;
    State2D twoD;
    JFrame frame;
    int width;
    int height;
    
    public void window(int width, int height) {
        //Aloitusruutu
        this.width = width;
        this.height = height;
        frame = new JFrame("Fraktaalikone");
        choose2D = new JButton("2D");
        choose3D = new JButton("3D");
        rotate = new JButton("Pyöritä ja venytä");
        slider3D = new JSlider(JSlider.VERTICAL,0,300,100);
        slider3D.setMinorTickSpacing(1);
        slider3D.setMajorTickSpacing(5);
        slider3D.setPaintTicks(true);
        slider3D.setPaintLabels(true);
        slider2D = new JSlider(JSlider.VERTICAL,0,300,100);
        slider2D.setMinorTickSpacing(1);
        slider2D.setMajorTickSpacing(5);
        slider2D.setPaintTicks(true);
        slider2D.setPaintLabels(true);
        threeD = new State3D(Color.BLUE, point3D, 100);
        twoD = new State2D(Color.BLUE, 100);
        rotate.addKeyListener(this);
        frame.add(choose3D, BorderLayout.NORTH);
        frame.add(choose2D, BorderLayout.SOUTH);
        choose3D.addActionListener(this);
        choose2D.addActionListener(this);
        slider3D.addChangeListener(this);
        slider2D.addChangeListener(this);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == choose2D){
            frame.getContentPane().removeAll();
            frame.getContentPane().invalidate();
            frame.getContentPane().add(twoD);
            frame.setVisible(true);
            frame.add(choose3D, BorderLayout.EAST);
            frame.add(rotate, BorderLayout.NORTH);
            frame.add(slider2D, BorderLayout.WEST);
            frame.setSize(1100, 1100);
            in3D = false;
        }
        if(e.getSource() == choose3D){
            frame.getContentPane().removeAll();
            frame.getContentPane().invalidate();
            frame.getContentPane().add(threeD);
            frame.setVisible(true);
            frame.add(choose2D, BorderLayout.EAST);
            frame.add(rotate, BorderLayout.NORTH);
            frame.add(slider3D, BorderLayout.WEST);
            rotate.setFocusable(true);
            frame.setSize(1100,1100);
            in3D = true;
        }
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        int key = ke.getKeyCode();
        if(key == KeyEvent.VK_X){
            if(in3D){
                threeD.turnX();
            } else {
                twoD.turnX();
            }
        }
        if(key == KeyEvent.VK_Y){
            if(in3D){
                threeD.turnY();
            } else {
                twoD.turnY();
            }
        }
        if(key == KeyEvent.VK_Z){
            if(in3D){
                threeD.turnZ();
            } else {
                twoD.turnZ();
            }
        }
        if(key == KeyEvent.VK_UP && in3D){
            threeD.stretch(point3D);
        }
        if(key == KeyEvent.VK_DOWN && in3D){
            threeD.shrink(point3D);
        }
        if(key == KeyEvent.VK_I){
            if(in3D){
            threeD.zoomIn();
            } else {
                twoD.zoomIn();
            }
        }
        
        if(key == KeyEvent.VK_O){
            if(in3D){
            threeD.zoomOut();
            } else {
                twoD.zoomOut();
            }
        }
        if(key == KeyEvent.VK_RIGHT && in3D){
            point3D = (point3D+1)%4;
            threeD.chosenPoint(point3D);
        }
        if(key == KeyEvent.VK_LEFT && in3D){
            if(point3D == 0){
                point3D = 3;
            } else {
                point3D--;
            }
            threeD.chosenPoint(point3D);
        }
    }

    @Override
    public void keyReleased(KeyEvent arg0) {
        
    }

    @Override
    public void keyTyped(KeyEvent arg0) {
        
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        JSlider source = (JSlider) e.getSource();
         if (!source.getValueIsAdjusting()) {
             if(in3D){
                 
             threeD.chosenDots(source.getValue());
             } else {
                 twoD.chosenDots(source.getValue());
             }
         }
    }
}