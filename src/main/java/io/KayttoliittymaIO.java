package io;

import java.util.Scanner;

/**
 * Luokka tekstipohjaiselle IO:lle. Lukee käyttäjän syötteitä ja tulostaa
 * ohjelman tulosteita.
 */
public class KayttoliittymaIO implements IO {

    /**
     * Scanneri lukemaan käyttäjän syötteitä.
     */
    private Scanner lukija;

    /**
     * Konstruktori luo uuden lukija-olion.
     */
    public KayttoliittymaIO() {
        this.lukija = new Scanner(System.in);
    }

    /**
     * Lukee käyttäjän antaman syöterivin.
     * @return luetun rivin Stringinä.
     */
    @Override
    public String lueRivi() {
        return lukija.nextLine();
    }

    /**
     * Tulostaa rivin rivinvaihdolla.
     * @param rivi String tulostettava rivi.
     */
    @Override
    public void tulostaRivi(String rivi) {
        System.out.println(rivi);
    }

    /**
     * Tulostaa rivin ilman rivinvaihtoa.
     * @param rivi String tulostettava rivi.
     */
    @Override
    public void tulostaIlmanRivinvaihtoa(String rivi) {
        System.out.print(rivi);
    }

    /**
     * Asettaa luettavan rivin lukijalle.
     * @param luettavaRivi
     */
    public void asetaLuettavaRivi(String luettavaRivi) {
        lukija = new Scanner(luettavaRivi);
    }
}
