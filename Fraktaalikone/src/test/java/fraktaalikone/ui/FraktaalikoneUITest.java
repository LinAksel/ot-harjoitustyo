/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fraktaalikone.ui;
        
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class FraktaalikoneUITest {
    
    @Test
    public void ikkunanKorkeusOikea(){
        FraktaalikoneUI frakkis = new FraktaalikoneUI();
        frakkis.ikkuna(500, 500);
        assertTrue(frakkis.getKorkeus()==500);
    }
    
    @Test
    public void ikkunanLeveysOikea(){
        FraktaalikoneUI frakkis = new FraktaalikoneUI();
        frakkis.ikkuna(500, 500);
        assertTrue(frakkis.getLeveys()==500);
    }
}
