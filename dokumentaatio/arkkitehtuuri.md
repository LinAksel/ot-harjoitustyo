# Arkkitehtuurikuvaus

## Rakenne

Ohjelman rakenne noudattaa pelkistettynä kuvassa näkyvää paketti- ja luokkamallia.

<img src="https://raw.githubusercontent.com/LinAksel/ot-harjoitustyo/master/dokumentaatio/Kuvat/PAKKAUSRAKENNE.png" width="400">

Pakkaus _fraktaalikone.ui_ sisältää Swing-käyttöliittymän, _fraktaalikone.domain_ sovelluslogiikan sekä servicen, ja lopulta _fraktaalikone.dao_ tietokannasta ja sen tiedonvälityksestä vastaavan koodin.

## Käyttöliittymä

Käyttöliittymä on yksi dynaaminen näkymä, josta käyttäjä voi säätää ja valita erilaisia piirrettäviä kuvioita.

Käyttöliittymän riippuvuudet muihin luokkiin on saatu minimiin FraktaalikoneServicen avulla.

Kun käyttäjä säätää erilaisia parametrejä, tai painaa pyöritystilassa nappeja, päivittyy kuvio ruudulla. Myös koordinaattien syöttölaatikot avautuvat valitun pistemäärän mukaisesti.

## Sovelluslogiikka

Sovelluksen logiikka on pitkälti Fractal-rajapinnan toteuttavassa luokassa DotFractal.
Itsessään DotFractalia käytetäänkin vain yhtenä instanssina, mutta tietokannan datamallit muuttavat tätä getData- ja setData-metodien avulla.

## Pysyväistallennus

fraktaalikone.dao huolehtii tietojen hausta ja tallentamisesta tietokantaan.

Käyttöliittymään on kovakoodattu FraktaalikoneServicen kautta kulkeva alustus, joka varmistaa, että _fraktaali.db_-tietokanta on 1) olemassa ja 2) epätyhjä.
Tieto tallennetaan muodossa

<pre>
id:Integer
name:String
realPoints:String
color:String
xCoordinates:String
yCoordinates:String
zCoordinates:String
</pre>

ja koordinaatit erikseen vastaavassa pilkulla eritellyssä muodossa.
Esimerkiksi kahdesta pisteestä (11,-22,33), (-44,55,-66) koostuva keltainen kuvio nimeltään "Viiva" tallentuisi muotoon:

<pre>
id:1
name:Viiva
realPoints:2
color:YELLOW
xCoordinates:11,-44
yCoordinates:-22,55
zCoordinates:33,-66
</pre>

eli koordinaatit tallennetaan pilkulla eroteltuina.

## Päätoiminnallisuudet

Kuvataan sekvenssikaavioilla paria olennaista toiminnallisuutta:

### Pyöritys

<img src="https://raw.githubusercontent.com/LinAksel/ot-harjoitustyo/master/dokumentaatio/Kuvat/RotateX.png" width="400">

Käyttäjän on siis ensin painettava nappia. Napin ollessa valittuna voi käyttäjä nyt pyörittää näppäimellä _x_ kuviota.

### Uuden tallennus

<img src="https://raw.githubusercontent.com/LinAksel/ot-harjoitustyo/master/dokumentaatio/Kuvat/SaveNew.png" width="400">

Tässä kaavio on jo suhteellisen iso. Kun käyttäjä painaa tallennusnappia, tallennetaan uusi data ensin Fractal-olioon, joka näytetään takaisin käyttäjälle.
Tämän jälkeen tehdään ensin tietokantaan update, jonka jälkeen kysytään, onko nimi tallessa. Uudessa nimessä ei, jolloin luodaan tietokantaan create-metodilla uusi fraktaalimalli.
Lopuksi tehdään vielä nimikysely tietokannan kaikista nimistä, ja päivitetään yläruudun lista.

## Rakenteelliset heikkoudet

### Käyttöliittymä

Käyttöliittymä jäi monilta osin spagetiksi, ja sen metodin ovat välillä kammottavan pitkiä.
Sen sisältämä dataparseri olisi ehdottomasti pitänyt eristää pois, ja ylipäänsä paneelien luontia olisi voinut siistiä. Kaikenlainen päivittäminen osoittautui todella haastavaksi, ja käyttöliittymään jäi monilta osin toivomisen varaa.

### DotFractal

Pyöritysmetodeihin jäi toisteisuutta, ja toimintoja olisi voinut olla lisää.

