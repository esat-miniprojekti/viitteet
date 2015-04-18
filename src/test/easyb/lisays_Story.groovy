import io.*;
import dao.*;
import ui.*;
import viitehallinta.*;

description 'Käyttäjä voi lisätä artikkeliviitteen'

scenario 'käyttäjä voi lisätä viitteen', {
    given 'lisäämis-toiminto on valittu', {
        io = new StubIO("1", "1", "9", "lokki", "lintu", "9", "9", "9", "2015", "9", "9", "katu", "5");
        testiDao = new FileDao("tyhjatestiviite.txt", io);
        viitearkisto = new Viitearkisto(testiDao);
        kl = new Kayttoliittyma(io, viitearkisto);
        
    }
    when 'pakolliset kentät on täytetty', {
        kl.kaynnista();
    }
    then 'artikkeliviite on tallennettu' , {
        viitearkisto.getArtikkelit().size().shouldNotBe 0;
        testiDao.tyhjennaTiedosto();
    }

}
