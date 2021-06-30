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
 * Treenit luokka joka tallentaa treenejä ja osaa lisätä uuden treenin.
 * @author Konsta
 * @version 1.0 16.3.2021
 * @version 1.1 22.4.2021 lisätty korvaa metodi
 *
 */
public class Treenit implements Iterable<Treeni> {
    
    private String tiedostonPerusNimi = "";
    
    private boolean muutettu = false;
    
    private final List<Treeni> treenit = new ArrayList<Treeni>();
   
    /**
     * Alustetaan treenit
     */
    public Treenit() {
        // Tyhjänä riittää
    }
    
    /**
     * Lisätään uuden treenin
     * @param treeni lisättävä treeni
     */
    public void lisaaTreeni(Treeni treeni) {
        treenit.add(treeni);
        muutettu = true;
    }
   
    /**
     * Iteraattori jolla voidaan käydä läpi kaikki treenit
     * @return treenin iteraattori
     * @example
     * <pre name="test">
     * #PACKAGEIMPORT
     * #import java.util.*;
     * 
     * Treenit treenit = new Treenit();
     * Treeni pull = new Treeni(); treenit.lisaaTreeni(pull);
     * Treeni pull2 = new Treeni(); treenit.lisaaTreeni(pull2);
     * 
     * Iterator<Treeni> treeni2 = treenit.iterator();
     * treeni2.next() === pull;
     * treeni2.next() === pull2;
     * </pre>
     */
    @Override
    public Iterator<Treeni> iterator() {
        return treenit.iterator();
    }
    
    
    /**
     * Tallentaa treenien tiedoston
     * @throws TallennusException jos tallennus ei toimi
     * @throws IOException jos ongelmia kirjoittamisessa
     */
    public void tallenna() throws TallennusException, IOException {
        if (!muutettu) return;
        
        File ftiedosto = new File(getTiedostonNimi());
        
        try (PrintWriter fo = new PrintWriter(new FileWriter(ftiedosto.getCanonicalPath()))) {
            for (Treeni treeni : this) {
                fo.println(treeni.toString());
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
     * @throws TallennusException jos ongelmia lukemisessa
     * @example
     * <pre name="test">
     * #THROWS TallennusException, IOException, NoSuchElementException
     * #import java.io.File;
     * #import java.util.*;
     * #import java.io.IOException;
     * #import java.util.NoSuchElementException;
     * 
     * Treenit treenit = new Treenit();
     * Treeni selka = new Treeni(), jalat = new Treeni();
     * selka.taytaSelkaTreenilla(1);
     * jalat.taytaSelkaTreenilla(1);
     * 
     * String tiedosto = "testitreenit";
     * File ftied = new File(tiedosto + ".dat");
     * ftied.delete();
     * treenit.lue(tiedosto); #THROWS TallennusException
     * treenit.lisaaTreeni(selka);
     * treenit.lisaaTreeni(jalat);
     * treenit.tallenna(); 
     * treenit = new Treenit();
     * treenit.lue(tiedosto);
     * Iterator<Treeni> i = treenit.iterator();
     * i.next().toString() === selka.toString();
     * i.next().toString() === jalat.toString(); #THROWS NoSuchElementException
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
                Treeni treeni = new Treeni();
                treeni.parse(rivi);
                lisaaTreeni(treeni);
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
     * Haetaan tietyn päivän kaikki treenit (liikkeet)
     * @param pvmNro päivämäärän numero
     * @return lista kyseisistä treeneistä
     * @example
     * <pre name="test">
     * #import java.util.*;
     * 
     *  Treenit treenit = new Treenit();
     *  
     *  Treeni pull1 = new Treeni(); 
     *  pull1.taytaSelkaTreenilla(1);
     *  treenit.lisaaTreeni(pull1);
     *  
     *  Treeni pull2 = new Treeni(); 
     *  pull2.taytaSelkaTreenilla(2);
     *  treenit.lisaaTreeni(pull2);
     *  
     *  List<Treeni> loyty;
     *  loyty = treenit.haeTreenit(1);
     *  loyty.size() === 1;
     *  loyty = treenit.haeTreenit(2);
     *  loyty.size() === 1;
     *  
     * </pre>
     */
    public List<Treeni> haeTreenit(int pvmNro) {
        List<Treeni> loyty = new ArrayList<Treeni>();
        for (Treeni treeni : treenit) {
            if (treeni.getTreeniPvmNro() == pvmNro) loyty.add(treeni);
        }
        return loyty;
    }
    
    
    /**
     * Palauttaa treenejen lukumäärän
     * @return treenejen lukumäärä
     */
    public int getTreenitMaara() {
        return treenit.size();
    }
    
    /**
     * Korvaa treenin tietorakenteeseen. Ottaa treenin omistukseen
     * @param treeni lisättävän treenin viite
     * @throws TallennusException jos tietorakenne on jo täysi
     */
    public void korvaa(Treeni treeni) throws TallennusException {
        int id = treeni.getTreeniPvmNro();
        for (int i = 0; i < getTreenitMaara(); i++) {
            if (treenit.get(i).getTreeniNro() == id) {
                treenit.set(i, treeni);
                muutettu = true;
                return;
            }
        }
        lisaaTreeni(treeni);
    }
    
    
    /**
     * Poistaa kaikki tietyn unen treenit TODO: Testaus
     * @param pvmNro viite uneen minkä treenit poistetaan
     * @return montako poistettiin
     * @example
     * <pre name="test">
     * Treenit treenit = new Treenit();
     * Treeni tr1 = new Treeni(); tr1.taytaSelkaTreenilla(1);
     * Treeni tr2 = new Treeni(); tr2.taytaSelkaTreenilla(2);
     * Treeni tr3 = new Treeni(); tr3.taytaSelkaTreenilla(1);
     * 
     * treenit.lisaaTreeni(tr1);
     * treenit.lisaaTreeni(tr2);
     * treenit.lisaaTreeni(tr3);
     * 
     * 
     * treenit.poistaUniTreenit(1) === 2; treenit.getTreenitMaara() === 1;
     * treenit.poistaUniTreenit(3) === 0; treenit.getTreenitMaara() === 1; 
     * </pre>
     */
    public int poistaUniTreenit(int pvmNro) {
        int j = 0;
        for (Iterator<Treeni> it = treenit.iterator(); it.hasNext();) {
            Treeni treeni = it.next();
            if (treeni.getTreeniPvmNro() == pvmNro) {
                it.remove();
                j++;
            }
        }
        if (j > 0) muutettu = true;
        return j;
    }
    
    /**
     * Testi maini luokalle treenit
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        Treenit treenit = new Treenit();
        Treeni pull = new Treeni(), pull2 = new Treeni(), pull3 = new Treeni();
        pull.taytaSelkaTreenilla(1);
        pull2.taytaSelkaTreenilla(1);
        pull3.taytaSelkaTreenilla(1);
        
        treenit.lisaaTreeni(pull);
        treenit.lisaaTreeni(pull2);
        treenit.lisaaTreeni(pull3);
        
        System.out.println("--Testataan Treenit luokkaa--");
        System.out.println("");
        
        List<Treeni> toinenTreeni = treenit.haeTreenit(1);
        
        for (Treeni treeni: toinenTreeni) {
            treeni.tulosta(System.out);
            System.out.println("");
        }
    }
    
    /**
     * Poistetaan treeni 
     * @param treeni poistettava treeni
     * @return true jos onnistui
     * @example
     * <pre name="test">
     * #THROWS TallennusException
     * #import java.io.File;
     * 
     * Treenit treenit = new Treenit();
     * Treeni tr1 = new Treeni(); tr1.taytaSelkaTreenilla(1);
     * Treeni tr2 = new Treeni(); tr2.taytaSelkaTreenilla(2);
     * Treeni tr3 = new Treeni(); tr3.taytaSelkaTreenilla(1);
     * 
     * treenit.lisaaTreeni(tr1);
     * treenit.lisaaTreeni(tr2);
     * treenit.lisaaTreeni(tr3);
     * 
     * treenit.poista(tr3) === true; treenit.getTreenitMaara() === 2;
     * treenit.poista(tr1) === true; treenit.getTreenitMaara() === 1;
     * </pre>
     */
    public boolean poista(Treeni treeni) {
        boolean ret = treenit.remove(treeni);
        if (ret) muutettu = true;
        return ret;
        
    }
    
    
}
