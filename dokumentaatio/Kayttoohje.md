# Käyttöohje

## Käynnistäminen

Lataa lähdekoodi, ja aja kansiossa _Fraktaalikone_ komento

```

mvn compile exec:java -Dexec.mainClass=fraktaalikone.Main
```
TAI

lataa viimeisin release, ja aja se komennolla

```

java -jar FraktaalikoneFINAL.jar
```
## Käyttö

Käynnistyessä ohjelmaan ilmestyy valmis fraktaalikuvio.

Näkymässä on nappi _Pyöritä ja venytä_. Tätä painamalla
saat käyttöösi näppäinkomennot:

* painamalla _x_ saat kuvion pyörimään x-akselin mukaan
* painamalla _y_ saat kuvion pyörimään y-akselin mukaan
* painamalla _z_ saat kuvion pyörimään z-akselin mukaan
* painamalla nuolinäppäimiä vasen tai oikea saat muutettua kohdekärkeä
* painamalla nuolinäppäintä ylös saat venytettyä kuviota kärjestä
* painamalla nuolinäppäintä alas saat kutistettua kuviota kärjestä
* painamalla _i_ saat suurennettua kuviota
* painamalla _o_ saat kutistettua kuviota

Näkymässä on myös kolme slideria:

* keskislideria säätämällä voit vaikuttaa pisteiden määrään
* oikeaa slideria säätämällä voit vaikuttaa jakosääntöön
* vasenta slideriä säätämällä voit vaikuttaa laskennallisten kulmapisteiden määrään

Vasemman sliderin luvut kertovat pisteiden määrän kertaa 1000, esimerkiksi kohta
_100_ tarkoittaa 100000 pistettä. Muut ovat suoraan parametrejä.

Yläkulmassa näkyvästä valikosta voit valita tietokannasssa olevia fraktaalimalleja.
Kun käynnistät ohjelman ensimmäisen kerran, luo se samaan kansioon tiedoston "fraktaali.db", jossa on oletuksena yksi kuvio.

Oikealla puolella ovat fraktaalin tallennetut asetukset. Värivaihtoehtoja on neljä, reaalipisteitä saman verran kuin sliderissa ja nimikenttää voi syöttää haluamansa nimen.
Tallenna-nappia painamalla joko uudenniminen fraktaali tallentuu tietokantaan, tai samanniminen päivittyy.

Muista antaa luvut korrektissa liukulukumuodossa, eli edessä tyhjä (plus) tai miinusmerkki, ja desimaalipilkkuna '.', ei ','.
Ohjelma huomauttaa käyttäjää syöttövirheistä!

Ohjelma sulkeutuu ikkuna sulkemalla.
