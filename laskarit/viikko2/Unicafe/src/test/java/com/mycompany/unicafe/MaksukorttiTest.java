package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
    
    @Test
    public void saldoAlussaOikein() {
        assertTrue(kortti.saldo() == 10);
    }
    
    @Test
    public void lisaysKasvattaaArvoa() {
        kortti.lataaRahaa(10);
        assertTrue(kortti.saldo() == 20);
    }
    
    @Test
    public void saldoVaheneeOtettaessa(){
        kortti.otaRahaa(5);
        assertTrue(kortti.saldo() == 5);
    }
    
    @Test
    public void saldoEiVaheneLiianSuurestaOtosta() {
        kortti.otaRahaa(20);
        assertTrue(kortti.saldo() == 10);
    }
    
    @Test
    public void vahennysPalauttaaTrue() {
        assertTrue(kortti.otaRahaa(5));
    }
    
    @Test
    public void liianSuuriVahennysPalauttaaFalse(){
        assertFalse(kortti.otaRahaa(50));
    }
    
    @Test
    public void toStringTulostaaOikein() {
        assertEquals("saldo: 0.10", kortti.toString());
    }
}
