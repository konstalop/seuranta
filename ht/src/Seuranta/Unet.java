package Seuranta;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import fi.jyu.mit.ohj2.WildChars;

/**
 * Seurannan unet jonka avulla voidaan lisätä tietorakenteeseen esim. yksi uusi nukuttu yö (uni)
 * @author Konsta
 * @version 1.0 17.3.2021
 * @version 1.1 21.4.2021 lisätty korvaa metodi
 *
 */
public class Unet implements Iterable<Uni> {
    private static final int MAX_UNIA = 365;
    private Uni unet[] = new Uni[MAX_UNIA];
    private String tiedostonPerusNimi = "uni";
    private int lukumaara = 0;
    private boolean muutettu = false;
    
    
    /**
     * Lisää yhden uuden unen taulukkoon.
     * @param uni lisättävä uni
     * @throws TallennusException jos ongelmia 
     * @example
     * <pre name="test">
     * #THROWS TallennusException
     *  Unet unet = new Unet();
     *  Uni yo1 = new Uni(), yo2 = new Uni();
     *  unet.getUnet() === 0;
     *  unet.lisaaUni(yo1); 
     *  unet.getUnet() === 1;
     *  unet.lisaaUni(yo2);
     *  unet.getUnet() === 2;
     *  unet.annaUni(0) === yo1;
     *  unet.annaUni(1) === yo2;
     * </pre>
     */
    public void lisaaUni(Uni uni) throws TallennusException{ 
        if (lukumaara >= unet.length) throw new TallennusException("Liikaa alkioita");
        unet[lukumaara] = uni;
        lukumaara++;
        muutettu = true;
    }
    
    /**
     * Korvaa unen tietorakenteessa //TODO: testit
     * @param uni lisättävä/korvattava uni
     * @throws TallennusException jos ongelmia korvaamisessa / lisäämisessä
     * @example
     * <pre name="test">
     * 
     * </pre>
     */
    public void korvaa(Uni uni) throws TallennusException {
        int id = uni.getPvmNro();
        for (int i = 0; i < lukumaara; i++) {
            if (unet[i].getPvmNro() == id) {
                unet[i] = uni;
                muutettu = true;
                return;
            }
        }lisaaUni(uni);
    }
    
    /**
     * Tallentaa tiedostoon
     * @throws TallennusException jos jotain menee pieleen tallennuksessa
     * @throws IOException  poikkeus
     */
    public void tallenna() throws TallennusException, IOException {
        if (!muutettu) return;
        
        File ftiedosto = new File(getTiedostonNimi());
        
        try (PrintWriter fo = new PrintWriter(new FileWriter(ftiedosto.getCanonicalPath()))) {
            for (Uni uni : this) {
                fo.println(uni.toString());
            }
        } catch ( FileNotFoundException ex ) {
            throw new TallennusException("Tiedosto " + ftiedosto.getCanonicalPath()+ " Ei aukea");
        } catch (IOException ex) {
            throw new TallennusException("Tiedoston" + ftiedosto.getCanonicalPath() + " Kirjoittamisessa ongelmia");
        }
        
        muutettu = false;
    } 
    
    
    
    /**
     * Lukee tiedostosta 
     * @param tiedosto tiedoston sijainti
     * @throws TallennusException jos jotain menee pieleen lukemisessa
     * @example
     * <pre name="test">
     * #THROWS TallennusException, IOException
     * #import java.io.File;
     * #import java.util.*;
     * #import java.io.IOException;
     * 
     * Unet unet = new Unet();
     * Uni yo1 = new Uni(), yo2 = new Uni();
     * yo1.taytaUni();
     * yo2.taytaUni();
     * 
     * String hakemisto = "testiunet";
     * String tiedosto = hakemisto + "/nimet";
     * File ftied = new File(tiedosto + ".dat");
     * File dir = new File(hakemisto);
     * dir.mkdir();
     * ftied.delete();
     * unet.lue(tiedosto); #THROWS TallennusException
     * unet.lisaaUni(yo1);
     * unet.lisaaUni(yo2);
     * unet.tallenna(); 
     * unet = new Unet();
     * unet.lue(tiedosto);
     * Iterator<Uni> i = unet.iterator();
     * i.next() === yo1;
     * i.next() === yo2; #THROWS NoSuchElementException
     * i.hasNext() === false;
     * ftied.delete() === true;
     * dir.delete() === true;
     * </pre>
     */
    public void lue(String tiedosto) throws TallennusException {
        setTiedostonPerusNimi(tiedosto);
        
        try (BufferedReader fi = new BufferedReader(new FileReader(getTiedostonNimi()))) {
            String rivi = fi.readLine();
            while ( (rivi = fi.readLine()) != null) {
                rivi = rivi.trim();
                Uni uni = new Uni();
                uni.parse(rivi);
                lisaaUni(uni);
            }
            muutettu = false;
        } catch ( FileNotFoundException e ) {
            throw new TallennusException("Tiedosto " + getTiedostonNimi() + " ei aukea");
        } catch (IOException e) {
            throw new TallennusException("Ongelmia tiedoston kanssa:" + e.getMessage());
        }
    }
    
    /**
     * luetaan tiedostosta jonka nimi annettu aikaisemmin
     * @throws TallennusException jos poikkeus
     */
    public void lueTiedostosta() throws TallennusException {
        lue(getTiedostonPerusNimi());
    }
    
    /**
     * Palauttaa tiedoston nimen jolla tallennetaan
     * @return tallennustiedoston nimi
     */
    public String getTiedostonPerusNimi() {
        return tiedostonPerusNimi;
    }
    
    /**
     * @return tallennustiedoston nimi
     */
    public String getTiedostonNimi() {
        return getTiedostonPerusNimi() +".dat";
    }
    
    /**
     * asetetaan tiedoston perusnimi ilman päätettä
     * @param nimi tiedoston nimi
     */
    public void setTiedostonPerusNimi(String nimi) {
        tiedostonPerusNimi = nimi;
        
       
    }
    
    /**
     * Metodi jolla voidaan katsoa tallennettujen unien lukumäärä.
     * @return unien määrä
     */
    public int getUnet() {
        return lukumaara;
    }
    
    /**
     * palautetaan viite unet taulukon alkio kohdassa olevaan uneen.
     * @param alkio monesko uni halutaan
     * @return palauttaa alkion kohdalla olevan unene
     * @throws IndexOutOfBoundsException jos alkio menee yli sallitun alueen 
     * @example
     */
    public Uni annaUni(int alkio) throws IndexOutOfBoundsException {
        if (alkio < 0 || lukumaara <= alkio)
            throw new IndexOutOfBoundsException("Syötetty indeksi ylittää sallitun rajan, syötä uusi indeksi");
        return unet[alkio];
    }
    
    /**
     * Luokka jolla voidaan iteroida unia
     */
    public class UnetIterator implements Iterator<Uni> {
        private int kohdalla = 0;
        
        
        /**
         * onko olemassa seuraavaa
         * @return true jos on olemassa
         */
        @Override
        public boolean hasNext() {
            return kohdalla < getUnet();
        }
        
        @Override
        public Uni next() throws NoSuchElementException {
            if (!hasNext() ) throw new NoSuchElementException("Ei ole!");
            return annaUni(kohdalla++);
        }
        
    }
    
    
    /**
     * Palautetaan iteraattori unista.
     */
    @Override
    public Iterator<Uni> iterator() {
        return new UnetIterator();
    }
    
    /**
     * Testi maini luokalle Unet
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        Unet unet = new Unet();
        
        Uni yo1 = new Uni();
        Uni yo2 = new Uni();
        
        yo1.asetaPvm("1.1.2021");
        yo1.taytaUni();
        
        
        yo2.asetaPvm("2.1.2021");
        yo2.taytaUni();
        
        try {
            unet.lisaaUni(yo1);
            unet.lisaaUni(yo2);
        } catch (TallennusException e) {           
            e.printStackTrace();
        }
        
        
        System.out.println("-----------Testiä unille--------------");

        
        for (int i = 0; i < unet.getUnet(); i++) {
            Uni uni = unet.annaUni(i);
            System.out.println("Unen paikka taulukossa: " + i);
            uni.tulosta(System.out);
        }
        
        
    }

    /**
     * Etsitään tietorakenteesta
     * @param ehto jolla haetaan
     * @param i indeksin kenttään
     * @return löytynyt uni
     * @example
     * <pre name="test">
     * #THROWS TallennusException
     *  Unet unet = new Unet();
     *  Uni uni1 = new Uni(); uni1.parse("1|01.01.2021|8h|50bpm|35ms");
     *  Uni uni2 = new Uni(); uni2.parse("2|02.01.2021|9h|60bpm|40ms");
     *  Uni uni3 = new Uni(); uni3.parse("3|03.01.2021|7h|70bpm|30ms");
     *  unet.lisaaUni(uni1);
     *  unet.lisaaUni(uni2);
     *  unet.lisaaUni(uni3);
     *  List<Uni> loytyneet;
     *  loytyneet = (List<Uni>)unet.etsi("*01*", 1);
     *  loytyneet.size() === 3;
     *  loytyneet.get(0) == uni1 === true;
     * </pre>
     */
    public Collection<Uni> etsi(String ehto, int i) {
        String hakuehto = "*";
        if (ehto != null && ehto.length() > 0) hakuehto = ehto;
        int ij = i;
        Collection<Uni> loytyneet = new ArrayList<Uni>();
        for (Uni uni : this) {
            if (WildChars.onkoSamat(uni.anna(ij), hakuehto)) loytyneet.add(uni);
        }
        
        return loytyneet;
        
    }
    
    /**
     * Poistaa unen tietyllä idllä 
     * @param id poistettavan unen id
     * @return 1 jos onnistu 0 jos ei löydy
     * @example
     * <pre name="test">
     * #THROWS TallennusException
     * Unet unet = new Unet();
     * Uni yo1 = new Uni(), yo2 = new Uni(), yo3 = new Uni();
     * yo1.asetaPvm(); yo2.asetaPvm(); yo3.asetaPvm();
     * int pv1 = yo1.getPvmNro();
     * unet.lisaaUni(yo1); unet.lisaaUni(yo2); unet.lisaaUni(yo3);
     * unet.poista(pv1+1) === 1;
     * unet.poista(pv1) === 1;
     * </pre>
     */
    public int poista(int id) {
        int ind = etsiId(id);
        if (ind < 0) return 0;
        lukumaara--;
        for (int i =ind; i < lukumaara; i++) {
            unet[i] = unet[i+1];
        }
        unet[lukumaara] = null;
        muutettu = true;
        return 1;
    }
    
    /**
     * Etsitään unen id:n perusteella TODO: TESTAUS
     * @param id pvmnro jolla etsitään
     * @return löytynyt indeksi tai -1 jos ei löydy
     * @example
     * <pre name="test">
     * #THROWS TallennusException
     * Unet unet = new Unet();
     * Uni yo1 = new Uni(), yo2 = new Uni(), yo3 = new Uni();
     * yo1.asetaPvm(); yo2.asetaPvm(); yo3.asetaPvm();
     * int pv1 = yo1.getPvmNro();
     * unet.lisaaUni(yo1); unet.lisaaUni(yo2); unet.lisaaUni(yo3);
     * unet.etsiId(pv1+1) === 1;
     * unet.etsiId(pv1+2) === 2;
     * </pre>
     */
    public int etsiId(int id) {
        for (int i = 0; i < lukumaara; i++) {
            if ( id == unet[i].getPvmNro()) return i;           
        }
        return -1;
    }

}
