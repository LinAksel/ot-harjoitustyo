package fraktaalikone.ui;

import fraktaalikone.domain.FractalBuilder;
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
    
    //Alussa määriteltyjä asioita on liuta, sillä näitä ei ole tarkoituskaan pystyä muuttamaan konstruktorissa
    JButton rotate = new JButton("Pyöritä ja venytä");
    JSlider dotSlider = new JSlider(JSlider.VERTICAL,0,300,100);
    JSlider divideSlider = new JSlider(JSlider.VERTICAL, 2, 6, 2);
    JSlider pointSlider = new JSlider(JSlider.HORIZONTAL, 2, 20, 3);
    FractalBuilder fractal;
    JFrame frame  = new JFrame("Fraktaalikone");
    int width;
    int height;
    
    //Ikkunan rakentaja
    public void window(int width, int height) {
        this.width = width;
        this.height = height;
        
        sliderSetup(dotSlider, 5, 1, "dotSlider");
        sliderSetup(divideSlider, 1, 0, "divideSlider");
        sliderSetup(pointSlider, 1, 0, "pointSlider");
        dotSlider.setName("dotSlider");
        pointSlider.setName("pointSlider");
        fractal = new FractalBuilder(Color.BLUE, 100);
        rotate.addKeyListener(this);
        frame.getContentPane().add(fractal);
        frame.setVisible(true);
        frame.add(rotate, BorderLayout.NORTH);
        frame.add(pointSlider, BorderLayout.SOUTH);
        frame.add(dotSlider, BorderLayout.WEST);
        frame.add(divideSlider, BorderLayout.EAST);
        rotate.setFocusable(true);
        frame.setSize(width,height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    //Sliderin alustaja ikkunaan copypasten välttämiseen
    public void sliderSetup(JSlider slider, int major, int minor, String name) {
        if(minor > 0){
            slider.setMinorTickSpacing(minor);
        }
        slider.setMajorTickSpacing(major);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setName(name);
        slider.addChangeListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
    }

    //Kun rotate-nappi on valittuna, näppäinkuuntelija fraktaalille
    @Override
    public void keyPressed(KeyEvent ke) {
        int key = ke.getKeyCode();
        if(key == KeyEvent.VK_X){
            fractal.turnX();
        }
        if(key == KeyEvent.VK_Y){
            fractal.turnY();
        }
        if(key == KeyEvent.VK_Z){
            fractal.turnZ();
        }
        if(key == KeyEvent.VK_UP){
            fractal.stretch();
        }
        if(key == KeyEvent.VK_DOWN){
            fractal.shrink();
        }
        if(key == KeyEvent.VK_I){
            fractal.zoomIn();
        }
        if(key == KeyEvent.VK_O){
            fractal.zoomOut();
        }
        if(key == KeyEvent.VK_RIGHT){
            fractal.chosenPoint(1);
        }
        if(key == KeyEvent.VK_LEFT){
            fractal.chosenPoint(-1);
        }
    }

    @Override
    public void keyReleased(KeyEvent arg0) {
        
    }

    @Override
    public void keyTyped(KeyEvent arg0) {
        
    }

    //Sliderien kuuntelija
    @Override
    public void stateChanged(ChangeEvent e) {
        JSlider source = (JSlider) e.getSource();
        if (source.getName().equals("dotSlider")) {
            fractal.chosenDots(source.getValue());
        } else if (source.getName().equals("divideSlider")) {
            fractal.setDivider(source.getValue());
        } else if (source.getName().equals("pointSlider")) {
            fractal.setPointNumber(source.getValue());
        }
    }
}