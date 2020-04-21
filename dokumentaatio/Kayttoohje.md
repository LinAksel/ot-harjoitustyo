# Käyttöohje

## Käynnistäminen

Lataa lähdekoodi, ja aja kansiossa _Fraktaalikone_ komento

```

mvn compile exec:java -Dexec.mainClass=fraktaalikone.Main
```
TAI

lataa viimeisin release, ja aja se komennolla

```

java -jar FraktaalikoneV1.jar
```
## Käyttö

Käynnistyessä ohjelmaan ilmestyy kaksi nappia, _2D_ ja _3D_. Painamalla
jompaakumpaa alustat joko kaksi- tai kolmiulotteisin fraktaalin.

Kolmiulotteisessa näkymässä on nappi _Pyöritä ja venytä_. Tätä painamalla
saat käyttöösi näppäinkomennot:

* painamalla _x_ saat kuvion pyörimään x-akselin mukaan
* painamalla _y_ saat kuvion pyörimään y-akselin mukaan
* painamalla _z_ saat kuvion pyörimään z-akselin mukaan
* painamalla nuolinäppäimiä vasen tai oikea saat muutettua kohdekärkeä
* painamalla nuolinäppäintä ylös saat venytettyä kuviota kärjestä
* painamalla nuolinäppäintä alas saat kutistettua kuviota kärjestä
* painamalla _i_ saat suurennettua kuviota
* painamalla _o_ saat kutistettua kuviota
* slideria säätämällä voit vaikuttaa pisteiden määrään

Sliderin luvut kertovat pisteiden määrän kertaa 1000, esimerkiksi kohta
_100_ tarkoittaa 100000 pistettä.

Painamalla nappia _Säädä_ voit pysäyttää näppäinkomentojen rekisteröinnin.

Painamalla nappia _2D_ siirrytään kaksiulotteisen kuvion näkymään.


Kaksiulotteisessa näkymässä on nappi _Pyöritä_, jota painamalla saat
käyttöösi samat pyörityskomennot kuin kolmiulotteisessa kuviossa _x_-,
_y_- ja _z_-näppäinten avulla.

Painamalla nappia 3D siirrytään kolmiulotteisen kuvion näkymään.

Ohjelma sulkeutuu ikkuna sulkemalla.
