package fraktaalikone;

import fraktaalikone.ui.FraktaalikoneUI;

public class Main {
    public static void main(String[] args) {
        FraktaalikoneUI frakkis = new FraktaalikoneUI();
        //Systeemiin määritellyt värit
        System.setProperty("RED", "#FF0000");
        System.setProperty("BLUE", "#0000FF");
        System.setProperty("GREEN", "#00FF00");
        System.setProperty("YELLOW", "#FFFF00");
        
        frakkis.window(900, 900, "fraktaali.db");
    }
}
