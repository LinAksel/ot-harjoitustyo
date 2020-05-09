package fraktaalikone;

import fraktaalikone.ui.FraktaalikoneUI;

public class Main {
    public static void main(String[] args) {
        FraktaalikoneUI frakkis = new FraktaalikoneUI();
        System.setProperty("RED", "#FF0000");
        System.setProperty("BLUE", "#0000FF");
        System.setProperty("GREEN", "#00FF00");
        System.setProperty("BLACK", "#000000");
        System.setProperty("YELLOW", "#FFFF00");
        frakkis.window(900, 900);
    }
}
