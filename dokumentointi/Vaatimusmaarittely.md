# Vaatimusmäärittely

## Sovelluksen tarkoitus

Sovelluksen tarkoituksena on renderöidä erilaisia fraktaalinomaisia kuvioita yksittäisistä pisteistä.
Sovellusta voi käyttää sekä kaksi- että kolmiulotteisten pistefraktaalien luomiseen ja
eri kulmista tarkasteluun sekä kääntelyyn reaaliajassa.

## Sovelluksen tarjoama toiminnallisuus

* Käyttäjä voi alustaa uuden fraktaalin

	* Alustaessa valitaan joko 2D- tai 3D-pohja z-akselin oikeanlaisen käytön alustamiseksi
	* Alustaessa pohjatapauksena on aina Sierpinskin kolmio

* Käyttäjä voi muuttaa fraktaalikuviota

	* Parametrit ovat vapaasti muokattavissa alustuksen jälkeen
	* Kuitenkin parametreillä ala- sekä ylärajat, joiden sisällä on mielekästä toimia

* Käyttäjä voi suurentaa ja pienentää kuviota

* Käyttäjä voi käännellä sekä kaksi- että kolmiulotteisia kuvioita z-, x- ja y-akselien mukaan

* Käyttäjä voi tallentaa kuvion senhetkisen näkymän

* Käyttäjä voi avata aikaisemmin tallennettuja kuvioita ja muokata niitä

## Jatkokehitysideoita

Yllä kuvattu versio mahdollistaa paikallisen käyttäjän toiminnan ja tallennuksen.
Ajan salliessa tätä voisi kuitenkin laajentaa kaikkien käyttäjien yhteiseen tietokantaan, jolloin:

* Kaikki paikalliset käyttäjät voisivat tallentaa ja hakea kuvioita yhteisestä tietokannasta

* Käyttäjille voisi tehdä tunnuksen ja salasanan

* Tiettyjen käyttäjien tuotoksia olisi mahdollista hakea käyttäjänimellä

* Kuviot voisi varmuuskopioida salasanan taakse myös ei-julkisina tietokantaan 
