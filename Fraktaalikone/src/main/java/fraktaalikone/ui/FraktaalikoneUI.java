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

public class FraktaalikoneUI implements ActionListener, KeyListener{
    JButton choose2D;
    JButton choose3D;
    JButton rotate2D;
    JButton rotate3D;
    JButton parameters3D;
    private int point = 0;
    JFrame frame;
    State3D threeD;
    State2D twoD;
    int width;
    int height;
    boolean in3D = false;
    
    public void window(int width, int height) {
        //Aloitusruutu
        this.width = width;
        this.height = height;
        frame = new JFrame("Fraktaalikone");
        choose2D = new JButton("2D");
        choose3D = new JButton("3D");
        rotate3D = new JButton("Pyöritä ja venytä");
        rotate2D = new JButton("Pyöritä");
        parameters3D = new JButton("Säädä");
        frame.add(choose3D, BorderLayout.NORTH);
        frame.add(choose2D, BorderLayout.SOUTH);
        choose3D.addActionListener(this);
        choose2D.addActionListener(this);
        rotate3D.addKeyListener(this);
        rotate2D.addKeyListener(this);
        parameters3D.addActionListener(this);
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
            twoD = new State2D(Color.BLUE);
            frame.getContentPane().add(twoD);
            frame.setVisible(true);
            frame.add(choose3D, BorderLayout.WEST);
            frame.add(rotate2D, BorderLayout.NORTH);
            frame.setSize(1100, 1100);
            in3D = false;
        }
        if(e.getSource() == choose3D){
            frame.getContentPane().removeAll();
            frame.getContentPane().invalidate();
            threeD = new State3D(Color.BLUE, point);
            frame.getContentPane().add(threeD);
            frame.setVisible(true);
            frame.add(choose2D, BorderLayout.WEST);
            frame.add(rotate3D, BorderLayout.NORTH);
            frame.add(parameters3D, BorderLayout.EAST);
            rotate3D.setFocusable(true);
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
        if(key == KeyEvent.VK_UP){
            threeD.stretch(point);
        }
        if(key == KeyEvent.VK_DOWN){
            threeD.shrink(point);
        }
        if(key == KeyEvent.VK_RIGHT){
            point = (point+1)%4;
            threeD.chosenPoint(point);
        }
        if(key == KeyEvent.VK_LEFT){
            if(point == 0){
                point = 3;
            } else {
                point--;
            }
            threeD.chosenPoint(point);
        }
    }

    @Override
    public void keyReleased(KeyEvent arg0) {
        
    }

    @Override
    public void keyTyped(KeyEvent arg0) {
        
    }
}