package viitehallinta;

import dao.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Luokka viitteiden lisäämiseen ja poistamiseen järjestelmästä.
 */
public class Viitearkisto {

    /**
     * Lista, jossa viitteitä säilytetään kun ohjelma on käynnissä.
     */
    private List<Viite> viitteet;

    /**
     * Dao tietojen tiedostoon tallentamiseen ja sieltä lukemiseen.
     */
    private dao fileDao;

    public Viitearkisto() {
        this.fileDao = new FileDao();
        this.viitteet = new ArrayList<>();
    }

    /**
     * Konstruktori. Luodaan viite-lista.
     *
     * @param fileDao
     */
    public Viitearkisto(dao fileDao) {
        this.fileDao = fileDao;
        this.viitteet = new ArrayList<>();
    }

    /**
     * Luo uuden artikkelin ja lisää sille attribuuttit setterien avulla metodin
     * saamien parametrien mukaan ja lopuksi lisää uuden artikkelin
     * järjestelmään.
     *
     * @param author String artikkelin kirjoittaja.
     * @param title String artikkelin otsikko.
     * @param journal String julkaisun nimi, jossa artikkeli on ilmestynyt.
     * @param volume int julkaisun osa, jossa artikkeli on ilmestynyt.
     * @param number int julkaisun numero, jossa artikkeli on ilmestynyt.
     * @param year int vuosiluku jolloin julkaisu, jossa artikkeli on, ilmestyi.
     * @param pages String sivunumerot, joista artikkeli löytyy julkaisussa.
     *              erotetaan toisistaan kahdella viivalla.
     * @param publisher String julkaisijan nimi;
     * @param address String julkaisijan osoite;
     */
    public void lisaaArtikkeli(String author, String title, String journal, int volume,
            int number, int year, String pages, String publisher, String address) {
        Artikkeli artikkeli = new Artikkeli();
        artikkeli.setAuthor(author);
        artikkeli.setTitle(title);
        artikkeli.setJournal(journal);
        artikkeli.setVolume(volume);
        artikkeli.setNumber(number);
        artikkeli.setYear(year);
        artikkeli.setPages(pages);
        artikkeli.setPublisher(publisher);
        artikkeli.setAddress(address);
        artikkeli.luoID();
        this.viitteet.add(artikkeli);
        tallenna();
    }

    /**
     * Tallentaa viitteet tiedostoon.
     */
    public void tallenna() {
        this.fileDao.tallennaViitteet(viitteet);
    }

    /**
     * Hakee viitteet tiedostosta listaan.
     */
    public void lueTiedosto() {
        this.viitteet = this.fileDao.lueViitteetTiedostosta();
    }

    /**
     * Hakee Viitelistan.
     *
     * @return Listan viite-olioita
     */
    public List<Viite> getViitteet() {
        return viitteet;
    }

    /**
     * Luo uuden kirja-olion ja lisää sille attribuutit setterien avulla metodin
     * saamien parametrien mukaan. Lopuksi metodi lisää uuden kirjan järjestelmään.
     * @param author String kirjan kirjoittajan nimi.
     * @param title String kirjan nimi.
     * @param year int kirjan julkaisuvuosi.
     * @param publisher String kirjan julkaisija.
     * @param address String kirjan julkaisijan osoite.
     */
    public void lisaaKirja(String author, String title, int year, String publisher, String address) {
        Kirja kirja = new Kirja();
        kirja.setAuthor(author);
        kirja.setTitle(title);
        kirja.setYear(year);
        kirja.setPublisher(publisher);
        kirja.setAddress(address);
        kirja.luoID();
        this.viitteet.add(kirja);
        tallenna();
    }

    /**
     * Luo uuden konferenssijulkaisu-olion ja lisää sille attribuutit setterien 
     * avulla metodin saamien parametrien mukaan ja lisää uuden 
     * konferenssijulkaisun järjestelmään.
     * @param author String konferenssijulkaisun tekijän nimi.
     * @param title String konferenssijulkaisun otsikko.
     * @param booktitle String konferenssijulkaisun kirjan nimi, 
     *                  jos haluttu otsikko on vain osa kirjasta.
     * @param year int konferenssijulkaisun julkaisuvuosi.
     * @param pages String sivunumerot, joista artikkeli löytyy julkaisussa.
     *              erotetaan toisistaan kahdella viivalla.
     * @param publisher String konferenssijulkaisun julkaisija, jos kirja.
     */
    public void lisaaInproceedings(String author, String title, String booktitle, int year, String pages, String publisher) {
        Inproceedings inproceedings = new Inproceedings();
        inproceedings.setAuthor(author);
        inproceedings.setTitle(title);
        inproceedings.setBooktitle(booktitle);
        inproceedings.setYear(year);
        inproceedings.setPages(pages);
        inproceedings.setPublisher(publisher);
        inproceedings.luoID();
        this.viitteet.add(inproceedings);
        tallenna();
    }

    /**
     * Luo uuden misc-olion ja lisää sille attribuutit setterien 
     * avulla metodin saamien parametrien mukaan ja lisää lopuksi uuden 
     * miscin järjestelmään.
     * @param author Sting tekijän nimi.
     * @param title String otsikko.
     * @param howpublished String miten ja missä julkaistu.
     * @param month String kuukausi, jolloin julkaistu.
     * @param year String vuosi, jolloin julkaistu.
     * @param note String lisäosio, esim. url tai tarkempi kuvaus.
     */
    public void lisaaMisc(String author, String title, String howpublished, int month, int year, String note) {
        Misc misc = new Misc();
        misc.setAuthor(author);
        misc.setTitle(title);
        misc.setHowPublished(howpublished);
        misc.setMonth(month);
        misc.setYear(year);
        misc.setNote(note);
        misc.luoID();
        this.viitteet.add(misc);
        tallenna();
    }
    
    /**
     * Poistaa viitteen järjestelmästä parametrinaan saadun yksilöllisen ID:n avulla.
     * @param poistettavaViite String käyttäjän syöttämä ID, jolla etsitään oikea
     *                         poistettava viite.
     */
    public void poistaViite(String poistettavaViite) {
        Iterator<Viite> iteraattori = viitteet.iterator();
        while (iteraattori.hasNext()) {
            if (iteraattori.next().getID().equals(poistettavaViite)) {
                iteraattori.remove();
            }
        }
        tallenna();
    }
}
