package Seuranta;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * Ruokailut luokka joka tallentaa ruokailuja ja osaa lisätä uuden ruokailun.
 * @author Konsta
 * @version 1.0 17.3.2021
 * @version 1.1 22.4.2021
 *
 */
public class Ruokailut implements Iterable<Ruokailu>{
    private String tiedostonPerusNimi = "";
    private boolean muutettu = false;
    private final ArrayList<Ruokailu> ruokailut = new ArrayList<Ruokailu>();
    
    
    /**
     * Ruokailut alustaminen
     */
    public Ruokailut() {
        // Riittää tyhjänä, ei tarvitse alustuksia tässä.
    }
    
    
    /**
     * Lisää uuden ruokailun 
     * @param ruokailu lisättävä ruokailu
     */
    public void lisaaRuokailu(Ruokailu ruokailu) {
        ruokailut.add(ruokailu);
        muutettu = true;
    }
    
    /**
     * Palauttaa ruokailujen määrän
     * @return ruokailujen määrä
     */
    public int getRuokailutMaara() {
        return ruokailut.size();
    }
    
    
    /**
     * korvaa ruokailun tietorakenteeseeen
     * @param ruokailu lisättävän ruokailun viite
     * @throws TallennusException jos tietorakenne on jo täysi
     */
    public void korvaa(Ruokailu ruokailu) throws TallennusException {
        int id = ruokailu.getRuokailuPvmNro();
        for (int i = 0; i < getRuokailutMaara(); i++) {
            if (ruokailut.get(i).getRuokailuNro() == id) {
                ruokailut.set(i, ruokailu);
                muutettu = true;
                return;
            }
        }
        lisaaRuokailu(ruokailu);
    }
    
    /**
     * Iteraattori jolla voidaan käydä läpi kaikki ruokailut.
     * @return ruokalujen iteraattori
     * @example
     * <pre name="test">
     * #PACKAGEIMPORT
     * #import java.util.*;
     * 
     * Ruokailut ruokailut = new Ruokailut();
     * Ruokailu ruoka1 = new Ruokailu(); ruokailut.lisaaRuokailu(ruoka1);
     * Ruokailu ruoka2 = new Ruokailu(); ruokailut.lisaaRuokailu(ruoka2);
     * 
     * Iterator<Ruokailu> ruokailu2 = ruokailut.iterator();
     * ruokailu2.next() === ruoka1;
     * ruokailu2.next() === ruoka2;
     * </pre>
     */
    @Override
    public Iterator<Ruokailu> iterator() {
        return ruokailut.iterator();
    }
    
    
    /**
     * Haetaan tietyn päivän kaikki ruokailut
     * @param pvmNro päivämäärän tunnusnumero
     * @return lista löytyneistä
     * @example
     * <pre name="test">
     *  Ruokailut ruokailut = new Ruokailut();
     *  
     *  Ruokailu ruoka1 = new Ruokailu(); 
     *  ruoka1.taytaAamupala(1);
     *  ruokailut.lisaaRuokailu(ruoka1);
     *  
     *  Ruokailu ruoka2 = new Ruokailu(); 
     *  ruoka2.taytaAamupala(2);
     *  ruokailut.lisaaRuokailu(ruoka2);
     *  
     *  List<Ruokailu> loyty;
     *  loyty = ruokailut.haeRuokailut(1);
     *  loyty.size() === 1;
     *  loyty = ruokailut.haeRuokailut(2);
     *  loyty.size() === 1; 
     * </pre>
     */
    public List<Ruokailu> haeRuokailut(int pvmNro) {
        List<Ruokailu> loytyneet = new ArrayList<Ruokailu>();
        for (Ruokailu ruokailu : ruokailut) {
            if (ruokailu.getRuokailuPvmNro() == pvmNro) loytyneet.add(ruokailu);
        }
        return loytyneet;
    }
    
    
    /**
     * Tallentaa tiedostoon
     * Kesken.
     * @throws TallennusException jos tallennus ei toimi
     * @throws IOException jos ongelmia kirjoittamisessa
     */
    public void tallenna() throws TallennusException, IOException {
        if (!muutettu) return;
        
        File ftiedosto = new File(getTiedostonNimi());
        
        try (PrintWriter fo = new PrintWriter(new FileWriter(ftiedosto.getCanonicalPath()))) {
            for (Ruokailu ruokailu : this) {
                fo.println(ruokailu.toString());
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
     * @throws TallennusException jos ongelmia
     * @example
     * <pre name="test">
     * #THROWS TallennusException, IOException
     * #import java.io.File;
     * #import java.util.*;
     * #import java.io.IOException;
     * 
     * Ruokailut ruokailut = new Ruokailut();
     * Ruokailu aamu = new Ruokailu(), ilta = new Ruokailu();
     * aamu.taytaAamupala(1);
     * ilta.taytaAamupala(1);
     * 
     * String tiedosto = "testiruokailut";
     * File ftied = new File(tiedosto + ".dat");
     * ftied.delete();
     * ruokailut.lue(tiedosto); #THROWS TallennusException
     * ruokailut.lisaaRuokailu(aamu);
     * ruokailut.lisaaRuokailu(ilta);
     * ruokailut.tallenna(); 
     * ruokailut = new Ruokailut();
     * ruokailut.lue(tiedosto);
     * Iterator<Ruokailu> i = ruokailut.iterator();
     * i.next().toString() === aamu.toString();
     * i.next().toString() === ilta.toString(); #THROWS NoSuchElementException
     * i.hasNext() === false;
     * ftied.delete() === true;
     * </pre>
     */
    public void lue(String tiedosto) throws TallennusException {
        setTiedostonPerusNimi(tiedosto);
        
        try (BufferedReader fi = new BufferedReader(new FileReader(getTiedostonNimi()))) {
            String rivi = fi.readLine();
            if (rivi == null) throw new TallennusException("Ei ole maksimikokoa!");
            
            while ( (rivi = fi.readLine()) != null) {
                rivi = rivi.trim();
                Ruokailu ruokailu = new Ruokailu();
                ruokailu.parse(rivi);
                lisaaRuokailu(ruokailu);
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
     * Testi maini luokalle ruokailut
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        Ruokailut ruokailut = new Ruokailut();
        Ruokailu aamupala = new Ruokailu();
        Ruokailu aamupala2 = new Ruokailu();
        
        aamupala.taytaAamupala(1);
        aamupala2.taytaAamupala(1);
        
        
        ruokailut.lisaaRuokailu(aamupala2);
        ruokailut.lisaaRuokailu(aamupala);
        
        
        System.out.println("---Testatataan ruokailuja---");
        
        List<Ruokailu> ruokailut2 = ruokailut.haeRuokailut(1);
        
        for (Ruokailu ruokailu: ruokailut2) {
            ruokailu.tulosta(System.out);
            System.out.println("");
        }

    }


    /**
     * Poistetaan tietyn unen kaikki ruokailut TODO: TESTAUS
     * @param pvmNro poistettavan unen pvmnro
     * @return montako poistetiin
     * @example
     * <pre name="test">
     * Ruokailut ruokailut = new Ruokailut();
     * Ruokailu ruoka1 = new Ruokailu(); ruoka1.taytaAamupala(1);
     * Ruokailu ruoka2 = new Ruokailu(); ruoka2.taytaAamupala(2);
     * Ruokailu ruoka3 = new Ruokailu(); ruoka3.taytaAamupala(1);
     * 
     * ruokailut.lisaaRuokailu(ruoka1);
     * ruokailut.lisaaRuokailu(ruoka2);
     * ruokailut.lisaaRuokailu(ruoka3);
     * 
     * 
     * ruokailut.poistaUniRuokailut(1) === 2; ruokailut.getRuokailutMaara() === 1;
     * ruokailut.poistaUniRuokailut(3) === 0; ruokailut.getRuokailutMaara() === 1;
     * </pre>
     */
    public int poistaUniRuokailut(int pvmNro) {
        int j = 0;
        for (Iterator<Ruokailu> it = ruokailut.iterator(); it.hasNext();) {
            Ruokailu ruokailu = it.next();
            if (ruokailu.getRuokailuPvmNro() == pvmNro) {
                it.remove();
                j++;
            }
        }
        if (j > 0) muutettu = true;
        return j;
    }


    /**
     * Poistaa valitun ruokailun TODO TESTAUS
     * @param ruokailu poistettava ruokailu
     * @return true jos löytyi
     * @example
     * <pre name="test">
     * #THROWS TallennusException
     * #import java.io.File;
     * 
     * Ruokailut ruokailut = new Ruokailut();
     * Ruokailu tr1 = new Ruokailu(); tr1.taytaAamupala(1);
     * Ruokailu tr2 = new Ruokailu(); tr2.taytaAamupala(2);
     * Ruokailu tr3 = new Ruokailu(); tr3.taytaAamupala(1);
     * 
     * ruokailut.lisaaRuokailu(tr1);
     * ruokailut.lisaaRuokailu(tr2);
     * ruokailut.lisaaRuokailu(tr3);
     * 
     * ruokailut.poista(tr3) === true; ruokailut.getRuokailutMaara() === 2;
     * ruokailut.poista(tr1) === true; ruokailut.getRuokailutMaara() === 1;
     * </pre>
     */
    public boolean poista(Ruokailu ruokailu) {
        boolean ret = ruokailut.remove(ruokailu);
        if (ret) muutettu = true;
        return ret;
        
    }


    

}
