package fraktaalikone.ui;

import fraktaalikone.Tila2D;
import fraktaalikone.Tila3D;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;

public class FraktaalikoneUI implements ActionListener{
    JButton nappi1;
    JButton nappi2;
    JFrame frame;
    Tila3D kolmonen;
    Tila2D kakkonen;
    int leveys;
    int korkeus;
    
    public void ikkuna(int leveys, int korkeus) {
        //Aloitusruutu
        this.leveys = leveys;
        this.korkeus = korkeus;
        frame = new JFrame("Fraktaalikone");
        nappi1 = new JButton("2D");
        nappi2 = new JButton("3D");
        frame.add(nappi1, BorderLayout.NORTH);
        frame.add(nappi2, BorderLayout.SOUTH);
        nappi1.addActionListener(this);
        nappi2.addActionListener(this);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);
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
            frame.pack();
        }
        if(e.getSource() == nappi2){
            frame.getContentPane().removeAll();
            frame.getContentPane().invalidate();
            kolmonen = new Tila3D(leveys, korkeus);
            frame.getContentPane().add(kolmonen);
            frame.pack();
            for(int i = 0; i < 45; i++){
                kolmonen.kaantoX(leveys, korkeus);
            }
        } 
    }

}