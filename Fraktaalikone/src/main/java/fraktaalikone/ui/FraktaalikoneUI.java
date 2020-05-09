package fraktaalikone.ui;

import fraktaalikone.dao.DotFractalDao;
import fraktaalikone.domain.DotFractal;
import fraktaalikone.domain.FraktaalikoneService;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class FraktaalikoneUI implements ActionListener, KeyListener, ChangeListener, ComponentListener {
    
    //Alussa määriteltyjä asioita on liuta, sillä näitä ei ole tarkoituskaan pystyä muuttamaan konstruktorissa
    JButton rotate = new JButton("Pyöritä ja venytä");
    FraktaalikoneService service;
    JButton load = new JButton("Lataa");
    JButton save = new JButton("Tallenna");
    DotFractalDao database = new DotFractalDao("testi.db");
    JSlider dotSlider = new JSlider(JSlider.VERTICAL,0,300,100);
    JSlider divideSlider = new JSlider(JSlider.VERTICAL, 2, 6, 2);
    JSlider pointSlider = new JSlider(JSlider.VERTICAL, 2, 20, 3);
    JPanel boxPanel = new JPanel();
    JPanel sliderPanel = new JPanel();
    JPanel settingsPanel = new JPanel();
    GridLayout settingsLayout = new GridLayout(0,3);
    DefaultComboBoxModel model;
    JComboBox fractalBox;
    JComboBox fileBox;
    DotFractal fractal;
    JFrame frame  = new JFrame("Fraktaalikone");
    String[] nimilista;
    int width;
    int height;
    
    //Ikkunan rakentaja
    public void window(int width, int height) {
        this.width = width;
        this.height = height;
     
        settingsPanel.setLayout(settingsLayout);
        for(int i = 0; i < 40; i++){
            settingsPanel.add(new JButton("" + i));
        }
        
        sliderSetup(dotSlider, 5, 1, "dotSlider");
        sliderSetup(divideSlider, 1, 0, "divideSlider");
        sliderSetup(pointSlider, 1, 0, "pointSlider");
        dotSlider.setName("dotSlider");
        pointSlider.setName("pointSlider");
        ArrayList<String> nimet = database.getFractalNames();
        nimilista = new String[nimet.size()]; 
        nimilista = nimet.toArray(nimilista);
        model = new DefaultComboBoxModel(nimilista);
        fractalBox = new JComboBox(model);
        fractalBox.addActionListener(this);
        boxPanel.add(fractalBox);
        sliderPanel.add(pointSlider);
        sliderPanel.add(dotSlider);
        sliderPanel.add(divideSlider);
        sliderPanel.setBackground(Color.black);
        boxPanel.setBackground(Color.black);
        fractal = new DotFractal(100, width, height);
        service = new FraktaalikoneService(database, fractal);
        service.getFractalDataFromDB("Nelipiste3D");
        rotate.addKeyListener(this);
        frame.getContentPane().add(fractal);
        frame.setVisible(true);
        frame.add(settingsPanel, BorderLayout.EAST);
        frame.add(boxPanel, BorderLayout.NORTH);
        frame.add(sliderPanel, BorderLayout.WEST);
        frame.add(rotate, BorderLayout.SOUTH);
        rotate.setFocusable(true);
        frame.addComponentListener(this);
        frame.setSize(width,height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    //Sliderin alustaja ikkunaan copypasten välttämiseen
    public void sliderSetup(JSlider slider, int major, int minor, String name) {
        if(minor > 0){
            slider.setMinorTickSpacing(minor);
        }
        slider.setBackground(Color.BLACK);
        slider.setMajorTickSpacing(major);
        slider.setForeground(Color.BLUE);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setPreferredSize(new Dimension(60, height-100));
        slider.setName(name);
        slider.addChangeListener(this);
    }
    
    
    //Kuuntelee 
    @Override
    public void actionPerformed(ActionEvent e) {
        //System.out.println(fractalBox.getSelectedItem());
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
        if(key == KeyEvent.VK_A){
            ArrayList<String> nimet = database.getFractalNames();
            model.removeAllElements();
            model.addAll(nimet);
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

    @Override
    public void componentResized(ComponentEvent arg0) {
        this.width = frame.getWidth();
        this.height = frame.getHeight();
        fractal.setDimensions(width, height);
        dotSlider.setPreferredSize(new Dimension(60, height-100));
        divideSlider.setPreferredSize(new Dimension(60, height-100));
        pointSlider.setPreferredSize(new Dimension(60, height-100));
    }

    @Override
    public void componentMoved(ComponentEvent arg0) {
        
    }

    @Override
    public void componentShown(ComponentEvent arg0) {
        
    }

    @Override
    public void componentHidden(ComponentEvent arg0) {
      
    }
}