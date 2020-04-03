package fraktaalikone.ui;

import fraktaalikone.Tila2D;
import fraktaalikone.Tila3D;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JButton;
import javax.swing.JFrame;

public class FraktaalikoneUI implements ActionListener, KeyListener{
    JButton nappi1;
    JButton nappi2;
    JButton nappi3;
    JButton nappi4;
    private int karki = 0;
    JFrame frame;
    Tila3D kolmonen;
    Tila2D kakkonen;
    int leveys;
    int korkeus;
    boolean kolkki = false;
    
    public void ikkuna(int leveys, int korkeus) {
        //Aloitusruutu
        this.leveys = leveys;
        this.korkeus = korkeus;
        frame = new JFrame("Fraktaalikone");
        nappi1 = new JButton("2D");
        nappi2 = new JButton("3D");
        nappi3 = new JButton("Pyöritä");
        nappi4 = new JButton("Säädä");
        frame.add(nappi1, BorderLayout.NORTH);
        frame.add(nappi2, BorderLayout.SOUTH);
        nappi1.addActionListener(this);
        nappi2.addActionListener(this);
        nappi3.addKeyListener(this);
        nappi4.addActionListener(this);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public int getLeveys(){
        return leveys;
    }
    
    public int getKorkeus(){
        return korkeus;
    }
   
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == nappi1){
            frame.getContentPane().removeAll();
            frame.getContentPane().invalidate();
            kakkonen = new Tila2D(leveys, korkeus);
            frame.getContentPane().add(kakkonen);
            frame.add(nappi2, BorderLayout.WEST);
            nappi2.addActionListener(this);
            frame.pack();
        }
        if(e.getSource() == nappi2){
            frame.getContentPane().removeAll();
            frame.getContentPane().invalidate();
            kolmonen = new Tila3D(Color.BLUE);
            frame.getContentPane().add(kolmonen);
            frame.setVisible(true);
            frame.add(nappi1, BorderLayout.WEST);
            frame.add(nappi3, BorderLayout.NORTH);
            frame.add(nappi4, BorderLayout.EAST);
            nappi3.setFocusable(true);
            frame.setSize(1100,1100);
        }
        if(e.getSource() == nappi4){
                System.out.println("JEA");
        }
    }

    @Override
    public void keyTyped(KeyEvent arg0) {
        
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        int key = ke.getKeyCode();
        if(key == KeyEvent.VK_X){
            kolmonen.kaantoX();
        }
        if(key == KeyEvent.VK_UP){
            kolmonen.venyta(karki);
        }
        if(key == KeyEvent.VK_DOWN){
            kolmonen.kutista(karki);
        }
        if(key == KeyEvent.VK_LEFT){
            karki = (karki+1)%4;
        }
    }

    @Override
    public void keyReleased(KeyEvent arg0) {
        
    }
}