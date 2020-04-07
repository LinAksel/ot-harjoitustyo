
# Fraktaalikone

Sovelluksen avulla käyttäjä voi luoda ja tutkia erilaisia fraktaalikuvioita.

## Dokumentaatio

* [Työaikakirjanpito](dokumentaatio/Tyoaikakirjanpito.md)

* [Vaatimusmäärittely](dokumentaatio/Vaatimusmaarittely.md)

## Komentorivitoiminnot

### Testaus

##JUnit

Testit suoritetaan komennolla

```
mvn test
```

##Jacoco

Testikattavuusraportti luodaan komennolla

```
mvn jacoco:report
```

Kattavuusraporttia voi tarkastella avaamalla selaimella tiedosto _target/site/jacoco/index.html_

##Checkstyle

Tiedoston [checkstyle.xml](checkstyle.xml) määrittelemät tarkistukset suoritetaan komennolla

```
 mvn jxr:jxr checkstyle:checkstyle
```

Mahdolliset virheilmoitukset selviävät avaamalla selaimella tiedosto _target/site/checkstyle.html_

### Suoritettavan jarin generointi

Komento

```
mvn package
```

generoi hakemistoon _target_ suoritettavan jar-tiedoston _Fraktaalikone-1.0-SNAPSHOT.jar_

