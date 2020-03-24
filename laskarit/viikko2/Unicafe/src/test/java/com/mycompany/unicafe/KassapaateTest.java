package com.mycompany.unicafe;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class KassapaateTest {
    
    Kassapaate paate;
    Maksukortti kortti;
    
    @Before
    public void setUp() {
        this.paate = new Kassapaate();
        this.kortti = new Maksukortti(500);
    }
    
    //Testataan alustus
    
    @Test
    public void alussaOikeaRahamaara(){
        assertTrue(paate.kassassaRahaa() == 1000*100);
    }
    
    @Test
    public void alussaEiMyytyja() {
        assertTrue(paate.edullisiaLounaitaMyyty()+paate.maukkaitaLounaitaMyyty() == 0);
    }
    
    //Testataan käteismaksun vaihtoehdot
    
    @Test
    public void riittavaKateisMaksuKasvattaaKassaaEdullisessa() {
        paate.syoEdullisesti(500);
        assertTrue(paate.kassassaRahaa() == (1000*100)+240);
    }
    
    @Test
    public void riittavaKateisMaksuKasvattaaKassaaMaukkaassa() {
        paate.syoMaukkaasti(500);
        assertTrue(paate.kassassaRahaa() == (1000*100)+400);
    }
    
    @Test
    public void riittavassaMaksussaVaihtorahaOikeaEdullisessa() {
        assertTrue(paate.syoEdullisesti(500) == 260);
    }
    
    @Test
    public void riittavassaMaksussaVaihtorahaOikeaMaukkaassa() {
        assertTrue(paate.syoMaukkaasti(500) == 100);
    }
    
    @Test
    public void riittavaMaksuKasvattaaEdullistenMyyntia() {
        paate.syoEdullisesti(500);
        assertTrue(paate.edullisiaLounaitaMyyty() == 1);
    }
    
    @Test
    public void riittavaMaksuKasvattaaMaukkaidenMyyntia() {
        paate.syoMaukkaasti(500);
        assertTrue(paate.maukkaitaLounaitaMyyty() == 1);
    }
    
    @Test
    public void liianPieniMaksuPalauttaaRahatEdullisessa() {
        assertTrue(paate.syoEdullisesti(150) == 150);
    }
    
    @Test
    public void liianPieniMaksuPalauttaaRahatMaukkaassa() {
        assertTrue(paate.syoMaukkaasti(150) == 150);
    }
    
    @Test
    public void liianPieniMaksuEiKasvataKassaaEdullisessa(){
        paate.syoEdullisesti(210);
        assertTrue(paate.kassassaRahaa() == 1000*100);
    }
    
    @Test
    public void liianPieniMaksuEiKasvataKassaaMaukkaassa(){
        paate.syoMaukkaasti(210);
        assertTrue(paate.kassassaRahaa() == 1000*100);
    }
    
    @Test
    public void liianPieniMaksuEiNostaMyyntiaEdullisessa() {
        paate.syoEdullisesti(135);
        assertTrue(paate.edullisiaLounaitaMyyty() == 0);
    }
    
    @Test
    public void liianPieniMaksuEiNostaMyyntiaMaukkaassa() {
        paate.syoMaukkaasti(135);
        assertTrue(paate.maukkaitaLounaitaMyyty() == 0);
    }
    
    //Testataan korttimaksun vaihtoehdot
    
    @Test
    public void korttiMaksuVeloitetaanEdullisessa() {
        paate.syoEdullisesti(kortti);
        assertTrue(kortti.saldo() == 260);
    }
    
    @Test
    public void korttiMaksuVeloitetaanMaukkaassa() {
        paate.syoMaukkaasti(kortti);
        assertTrue(kortti.saldo() == 100);
    }
    
    @Test
    public void trueOnnistuneessaEdullisenKorttimaksussa() {
        assertTrue(paate.syoEdullisesti(kortti));
    }
    
    @Test
    public void trueOnnistuneessaMaukkaanKorttimaksussa() {
        assertTrue(paate.syoMaukkaasti(kortti));
    }
    
    @Test
    public void onnistunutMaksuKasvattaaMyyntiaEdullisessa(){
        paate.syoEdullisesti(kortti);
        assertTrue(paate.edullisiaLounaitaMyyty() == 1);
    }
    
    @Test
    public void onnistunutMaksuKasvattaaMyyntiaMaukkaassa(){
        paate.syoMaukkaasti(kortti);
        assertTrue(paate.maukkaitaLounaitaMyyty() == 1);
    }
    
    @Test
    public void saldoEiMuutuEpaonnistuneessaEdullisessa() {
        kortti = new Maksukortti(100);
        paate.syoEdullisesti(kortti);
        assertTrue(kortti.saldo() == 100);
    }
    
    @Test
    public void saldoEiMuutuEpaonnistuneessaMaukkaassa() {
        kortti = new Maksukortti(200);
        paate.syoMaukkaasti(kortti);
        assertTrue(kortti.saldo() == 200);
    }
    
    @Test
    public void epaonnistunutEiKasvataMyyntiaEdullisessa(){
        paate.syoEdullisesti(kortti);
        paate.syoEdullisesti(kortti);
        paate.syoEdullisesti(kortti);
        assertTrue(paate.edullisiaLounaitaMyyty() == 2);
    }
    
    @Test
    public void epaonnistunutEiKasvataMyyntiaMaukkaassa(){
        paate.syoMaukkaasti(kortti);
        paate.syoMaukkaasti(kortti);
        assertTrue(paate.maukkaitaLounaitaMyyty() == 1);
    }
    
    @Test
    public void epaonnistunutEdullinenPalauttaaFalse(){
        paate.syoMaukkaasti(kortti);
        assertFalse(paate.syoEdullisesti(kortti));
    }
    
    @Test
    public void epaonnistunutMaukkaastiPalauttaaFalse(){
        paate.syoEdullisesti(kortti);
        assertFalse(paate.syoMaukkaasti(kortti));
    }
    
    @Test
    public void edullinenKorttimaksuEiNostaKassanSaldoa(){
        paate.syoEdullisesti(kortti);
        assertTrue(paate.kassassaRahaa() == 1000*100);
    }
    
    @Test
    public void maukkaastiKorttimaksuEiNostaKassanSaldoa(){
        paate.syoMaukkaasti(kortti);
        assertTrue(paate.kassassaRahaa() == 1000*100);
    }
    
    //Testataan kortille rahan lataamisen vaihtoehdot
    
    @Test
    public void kortinSaldooKasvaaLadattaessa(){
        paate.lataaRahaaKortille(kortti, 100);
        assertTrue(kortti.saldo() == 600);
    }
    
    @Test
    public void kassaKasvaaKortilleLadattaessa() {
        paate.lataaRahaaKortille(kortti, 876);
        assertTrue(paate.kassassaRahaa() == (1000*100)+876);
    }
    
    @Test
    public void negatiivinenLatausEiMuutaSaldoa(){
        paate.lataaRahaaKortille(kortti, -700);
        assertTrue(kortti.saldo() == 500);
    }
    
    @Test
    public void negatiivinenLatausEiKasvataKassaa() {
        paate.lataaRahaaKortille(kortti, -660);
        assertTrue(paate.kassassaRahaa() == 1000*100);
    }
}
