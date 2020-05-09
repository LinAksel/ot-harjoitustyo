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
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class FraktaalikoneUI implements ActionListener, KeyListener, ChangeListener, ComponentListener {
    
    //Alussa määriteltyjä asioita on liuta, sillä näitä ei ole tarkoituskaan pystyä muuttamaan konstruktorissa
    JButton rotate = new JButton("Pyöritä ja venytä");
    FraktaalikoneService service;
    JTextField nameField;
    ArrayList<JTextField> fields = new ArrayList<>();
    JButton load = new JButton("Lataa");
    JButton save;
    DotFractalDao database = new DotFractalDao("testi.db");
    JSlider dotSlider = new JSlider(JSlider.VERTICAL,0,300,100);
    JSlider divideSlider = new JSlider(JSlider.VERTICAL, 2, 6, 2);
    JSlider pointSlider = new JSlider(JSlider.VERTICAL, 2, 20, 3);
    JPanel boxPanel = new JPanel();
    JPanel sliderPanel = new JPanel();
    JPanel settingsPanel;
    GridLayout settingsLayout = new GridLayout(0,4);
    DefaultComboBoxModel model;
    JComboBox fractalBox;
    JComboBox realPointBox;
    JComboBox colorBox;
    DotFractal fractal;
    JFrame frame  = new JFrame("Fraktaalikone");
    String[] nimilista;
    String[] colorList = { "RED", "BLUE", "GREEN", "YELLOW" };
    String[] dotList = { "2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20" };
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
        ArrayList<String> nimet = database.getFractalNames();
        nimilista = new String[nimet.size()]; 
        nimilista = nimet.toArray(nimilista);
        model = new DefaultComboBoxModel(nimilista);
        fractalBox = new JComboBox(model);
        fractalBox.setName("fractalBox");
        fractalBox.setSelectedItem("Kolmipiste2D");
        fractalBox.addActionListener(this);
        boxPanel.add(fractalBox);
        sliderPanel.add(pointSlider);
        sliderPanel.add(dotSlider);
        sliderPanel.add(divideSlider);
        sliderPanel.setBackground(Color.black);
        boxPanel.setBackground(Color.black);
        fractal = new DotFractal(100, width, height);
        service = new FraktaalikoneService(database, fractal);
        service.getFractalDataFromDB("Kolmipiste2D");
        settingsPanelSetup(service.getRealPoints());
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
    
    public void settingsPanelSetup(String realPoints) {
        settingsPanel = new JPanel();
        settingsPanel.setBackground(Color.BLACK);
        String real = realPoints;
        String name = fractal.getName();
        String colorName = fractal.getColorName();
        double[] list = service.getSimpleDotList();
        settingsPanel.setLayout(settingsLayout);
        int counter = 0;
        JLabel dot = new JLabel("Piste");
        dot.setForeground(Color.BLUE);
        settingsPanel.add(dot);
        JLabel xText = new JLabel("X");
        xText.setForeground(Color.BLUE);
        settingsPanel.add(xText);
        JLabel yText = new JLabel("Y");
        yText.setForeground(Color.BLUE);
        settingsPanel.add(yText);
        JLabel zText = new JLabel("Z");
        zText.setForeground(Color.BLUE);
        settingsPanel.add(zText);
        fields = new ArrayList<>();
        for(int i = 0; i < 80; i++){
            if(i % 4 == 0) {
                JLabel label = new JLabel("  " + ((i / 4) + 1) + ": ");
                label.setForeground(Color.BLUE);
                settingsPanel.add(label);
            } else if (i < Integer.parseInt(real)*4) {
                JTextField coordField = new JTextField(Double.toString(list[counter]), 5);
                fields.add(coordField);
                settingsPanel.add(coordField);
                counter++;
            } else {
                JLabel lock = new JLabel("Lukittu");
                lock.setForeground(Color.BLUE);
                settingsPanel.add(lock);
            }
        }
        DefaultComboBoxModel tempModel = new DefaultComboBoxModel(colorList);
        colorBox = new JComboBox(tempModel);
        colorBox.setSelectedItem(colorName);
        settingsPanel.add(colorBox);
        tempModel = new DefaultComboBoxModel(dotList);
        realPointBox = new JComboBox(tempModel);
        realPointBox.setSelectedItem(real);
        realPointBox.setName("realPointBox");
        realPointBox.addActionListener(this);
        settingsPanel.add(realPointBox);
        nameField = new JTextField(fractal.getName(), 4);
        settingsPanel.add(nameField);
        save = new JButton("Tallenna");
        save.addActionListener(this);
        settingsPanel.add(save);
    }
    //Kuuntelee 
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(realPointBox) || e.getSource().equals(fractalBox)){
            JComboBox source = (JComboBox) e.getSource();
            if(source.getName().equals("fractalBox")) {
                service.getFractalDataFromDB(source.getSelectedItem().toString());
                frame.remove(settingsPanel);
                settingsPanelSetup(service.getRealPoints());
                frame.add(settingsPanel, BorderLayout.EAST);
                frame.revalidate();
                frame.repaint();
            } else if (source.getName().equals("realPointBox")) {
                frame.remove(settingsPanel);
                settingsPanelSetup(source.getSelectedItem().toString());
                frame.add(settingsPanel, BorderLayout.EAST);
                frame.revalidate();
                frame.repaint();
            }
        } else {
            List<String> data = new ArrayList<>();
            data.add(nameField.getText());
            data.add(realPointBox.getSelectedItem().toString());
            data.add(colorBox.getSelectedItem().toString());
            int counter = 0;
            boolean send = true;
            String xCoord = "";
            String yCoord = "";
            String zCoord = "";
            for(JTextField field: fields){
                if(field.getText().matches("-?[0-9]+.?[0-9]*")) {
                    if(counter % 3 == 0){
                        xCoord = xCoord + field.getText() + ",";
                        counter++;
                    } else if (counter % 3 == 1) {
                        yCoord = yCoord + field.getText() + ",";
                        counter++;
                    } else {
                        zCoord = zCoord + field.getText() + ",";
                        counter++;
                    }
                } else {
                    JOptionPane.showMessageDialog(frame,"Koordinaatit tulee antaa muodossa (-)123(.123)! Tarkempaa tietoa GitHubissa sijaitsevassa käyttöohjeessa.","Alert",JOptionPane.WARNING_MESSAGE);
                    send = false;
                    break;
                }
            }
            if(send){
                xCoord = removeLast(xCoord);
                yCoord = removeLast(yCoord);
                zCoord = removeLast(zCoord);
                data.add(xCoord);
                System.out.println(xCoord);
                data.add(yCoord);
                System.out.println(yCoord);
                data.add(zCoord);
                System.out.println(zCoord);
                service.addDataToDB(data);
            }
        }
    }

    //Kun rotate-nappi on valittuna, näppäinkuuntelija fraktaalille
    @Override
    public void keyPressed(KeyEvent ke) {
        JButton source = (JButton) ke.getSource();
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
    
    private String removeLast(String string) {
        return string.substring(0, string.length() - 1);
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