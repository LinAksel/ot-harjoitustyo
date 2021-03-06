
# Fraktaalikone

Sovelluksen avulla käyttäjä voi luoda ja tutkia erilaisia fraktaalikuvioita.

## Viimeisin release

* [Loppuversio](https://github.com/LinAksel/ot-harjoitustyo/releases/tag/loppupalautus)

## Dokumentaatio

* [Työaikakirjanpito](dokumentaatio/Tyoaikakirjanpito.md)

* [Vaatimusmäärittely](dokumentaatio/Vaatimusmaarittely.md)

* [Käyttöohje](dokumentaatio/Kayttoohje.md)

* [Arkkitehtuuri](dokumentaatio/arkkitehtuuri.md)

## Komentorivitoiminnot

### Testaus

#### JUnit

Testit suoritetaan komennolla

```
mvn test
```

#### Jacoco

Testikattavuusraportti luodaan komennolla

```
mvn jacoco:report
```

Raporttia voi tarkastella avaamalla selaimella tiedoston _target/site/jacoco/index.html_

#### Checkstyle

Tiedoston [checkstyle.xml](Fraktaalikone/checkstyle.xml) määrittelemät tarkistukset suoritetaan komennolla

```
 mvn jxr:jxr checkstyle:checkstyle
```

Virheilmoitukset selviävät avaamalla selaimella tiedosto _target/site/checkstyle.html_

### Suoritettavan jarin generointi

Komento

```
mvn package
```

generoi hakemistoon _target_ suoritettavan jar-tiedoston _Fraktaalikone-1.0-SNAPSHOT.jar_

