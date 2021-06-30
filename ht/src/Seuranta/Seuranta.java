package Seuranta;

import java.io.File;
import java.io.IOException;
import java.util.*;
/**
 * Seuranta luokka joka huolehtii treeneistä sekä palautumisesta johon kuuluu unet ja ruokailut. 
 * @author Konsta
 * @version 1.0 16.3.2021
 * @version 1.1 19.4.2021
 * @version 1.2 22.4.2021 
 *
 */
public class Seuranta {
    private  Ruokailut ruokailut = new Ruokailut();
    private  Unet unet = new Unet();
    private  Treenit treenit = new Treenit();
    
    /**
     * Palautetaan uni seurantojen määrä joka tarkoittaa samalla päivien määrää.
     * @return päivien määrä
     */
    public int getPaivat() {
        return unet.getUnet();
    }
    
    /**
     * Lisätään seurataan uusi treeni
     * @param treeni lisättävä treeni
     */
    public void lisaa(Treeni treeni) {
        treenit.lisaaTreeni(treeni);
    }
    
    /**
     * Lisätään seurantaan uusi ruokailu
     * @param ruokailu lisättävä ruokailu
     */
    public void lisaa(Ruokailu ruokailu) {
        ruokailut.lisaaRuokailu(ruokailu);
    }
    
    /**
     * Lisätään seurantaan uusi uni
     * @param uni lisättävä uni
     * @throws TallennusException jos ongelmia
     */
    public void lisaa(Uni uni) throws TallennusException {
        unet.lisaaUni(uni);
    }
    
    
    /**
     * Korvaa unen tietorakenteessa.
     * @param uni viite lisättävään uneen
     * @throws TallennusException jos ongelmia
     */
    public void korvaa(Uni uni) throws TallennusException {
        unet.korvaa(uni);
    }
    
    /**
     * Korvaa treenin tietorakenteeseen
     * @param treeni viite lisättävään treeniin
     * @throws TallennusException jos ongelmia
     */ 
    public void korvaa(Treeni treeni) throws TallennusException {
        treenit.korvaa(treeni);
    }
    
    /**
     * Korvaa ruokaiul tietoraketneeseen
     * @param ruokailu viite lisätätän ruokaiulun
     * @throws TallennusException jos ongelmia
     */
    public void korvaa(Ruokailu ruokailu) throws TallennusException {
        ruokailut.korvaa(ruokailu);
    }
    
    /**
     * Palauttaa i:n unen
     * @param i monesko uni palautetaan
     * @return viite uneen
     * @throws IndexOutOfBoundsException jos i on yli rajojen
     */
    public Uni annaUni(int i) throws IndexOutOfBoundsException {
        return unet.annaUni(i);
    }
    
    
    /**
     * Haetaan tietyn päivän treeni
     * @param uni uniseuranta josta saadaan pvmnro jonka avulla saadaan treeni
     * @return lista treeneistä
     * @example
     * <pre name="test">
     *  #import java.util.*;
     *  
     *  Seuranta seuranta = new Seuranta();
     *  
     *  Uni yo1 = new Uni(), yo2 = new Uni(), yo3 = new Uni();
     *  yo1.asetaPvm("1.1.2021");
     *  yo2.asetaPvm("2.1.2021");
     *  
     *  int testiPvm = yo1.getPvmNro();
     *  int testiPvm1 = yo2.getPvmNro();
     *  
     *  Treeni treeni1 = new Treeni();
     *  treeni1.taytaSelkaTreenilla(testiPvm);
     *  seuranta.lisaa(treeni1);
     *  
     *  Treeni treeni2 = new Treeni();
     *  treeni2.taytaSelkaTreenilla(testiPvm1);
     *  seuranta.lisaa(treeni2);
     *  
     *  Treeni treeni3 = new Treeni();
     *  treeni3.taytaSelkaTreenilla(testiPvm1);
     *  seuranta.lisaa(treeni3);
     *  
     *  List<Treeni> loyty;
     *  
     *  loyty = seuranta.haeTreenit(yo3);
     *  loyty.size() === 0;
     *  loyty = seuranta.haeTreenit(yo1);
     *  loyty.size() === 1; 
     *  loyty = seuranta.haeTreenit(yo2);
     *  loyty.size() === 2;
     * </pre>
     */
    public List<Treeni> haeTreenit(Uni uni) {
        return treenit.haeTreenit(uni.getPvmNro());
    }
     
    
    /**
     * Haetaan tietyn päivän ruokailuja
     * @param uni uniseuranta jonka avulla saadaan pvmnro josta ruokailu
     * @return lista ruokailusta kyseiselle päivälle
     * @example
     * <pre name="test">
     *  #import java.util.*;
     *  
     *  Seuranta seuranta = new Seuranta();
     *  
     *  Uni yo1 = new Uni(), yo2 = new Uni(), yo3 = new Uni();
     *  yo1.asetaPvm("1.1.2021");
     *  yo2.asetaPvm("2.1.2021");
     *  
     *  int testiPvm = yo1.getPvmNro();
     *  int testiPvm1 = yo2.getPvmNro();
     *  
     *  Ruokailu ruokailu1 = new Ruokailu(); 
     *  ruokailu1.taytaAamupala(testiPvm);
     *  seuranta.lisaa(ruokailu1);
     *  
     *  Ruokailu ruokailu2 = new Ruokailu(); 
     *  ruokailu2.taytaAamupala(testiPvm1);
     *  seuranta.lisaa(ruokailu2);
     *  
     *  Ruokailu ruokailu3 = new Ruokailu();
     *  ruokailu3.taytaAamupala(testiPvm1);
     *  seuranta.lisaa(ruokailu3);
     *  
     *  List<Ruokailu> loyty;
     *  
     *  loyty = seuranta.haeRuokailut(yo3);
     *  loyty.size() === 0;
     *  loyty = seuranta.haeRuokailut(yo1);
     *  loyty.size() === 1;
     *  loyty = seuranta.haeRuokailut(yo2);
     *  loyty.size() === 2;
     * </pre>
     */
    public List<Ruokailu> haeRuokailut(Uni uni) {
        return ruokailut.haeRuokailut(uni.getPvmNro());
    }
     
    /**
     * Asetetaan tiedostejen nimet.
     * @param nimi uusi nimi
     */
    public void setTiedosto(String nimi) {
        File dir = new File(nimi);
        dir.mkdirs();
        String hakemistonNimi = "";
        if ( !nimi.isEmpty() ) hakemistonNimi = nimi + "/";
        unet.setTiedostonPerusNimi(hakemistonNimi + "uni");
        treenit.setTiedostonPerusNimi(hakemistonNimi + "treeni" );
        ruokailut.setTiedostonPerusNimi(hakemistonNimi + "ruokailut");
    }
    
    /**
     * lukee seurannan tiedostot
     * @param nimi nimi josta luetaan
     * @throws TallennusException jos lukemisessa ilmenee ongelmia / epäonnistuuu
     * @example
     * <pre name="test">
     * #THROWS TallennusException, IOException
     * 
     * #import java.io.*;
     * #import java.util.*;
     * 
     * Seuranta seuranta = new Seuranta();
     * Uni yo1 = new Uni(); yo1.taytaUni(); yo1.asetaPvm("1.1.2021");
     * Treeni selka = new Treeni(); selka.taytaSelkaTreenilla(yo1.getPvmNro());
     * Ruokailu aamu = new Ruokailu(); aamu.taytaAamupala(yo1.getPvmNro());
     * 
     * String hakemisto = "testiseuranta";
     * File dir = new File(hakemisto);
     * File utied = new File(hakemisto+"/unet.dat");
     * File ttied = new File(hakemisto+"/treenit.dat");
     * File rtied = new File(hakemisto+"/ruokailut.dat");
     * dir.mkdir();
     * utied.delete();
     * ttied.delete();
     * rtied.delete();
     * 
     * seuranta.lueTiedosto(hakemisto); #THROWS TallennusException
     * seuranta.lisaa(yo1);
     * seuranta.lisaa(selka);
     * seuranta.lisaa(aamu);
     * seuranta.tallenna();
     * 
     * 
     * List<Treeni> loytyneet = seuranta.haeTreenit(yo1);
     * 
     * Iterator<Treeni> it = loytyneet.iterator();
     * it.next() === selka;
     * it.hasNext() === true;
     * 
     * List<Ruokailu> loytyneet2 = seuranta.haeRuokailut(yo1);
     * 
     * Iterator<Ruokailu> ir = loytyneet2.iterator();
     * ir.next() === aamu;
     * ir.hasNext() === false;
     * 
     * seuranta.lisaa(yo1);
     * seuranta.lisaa(selka);
     * seuranta.lisaa(aamu);
     * seuranta.tallenna();
     * 
     * 
     */
    public void lueTiedosto(String nimi) throws TallennusException {
        //unet = new Unet();
        //treenit = new Treenit();
        //ruokailut = new Ruokailut();
        
        setTiedosto(nimi);
        unet.lueTiedostosta();
        treenit.lueTiedostosta();
        ruokailut.lueTiedostosta();
    }
    
    /**
     * TAllennetaan seurannan tiedot 
     * @throws TallennusException jos tallentamisessa ongelmia
     * @throws IOException  jos ongelmia
     */
    public void tallenna() throws TallennusException, IOException {
        String virheIlmoitus = "";
        try {
            unet.tallenna();
        } catch ( TallennusException ex ) {
            virheIlmoitus = ex.getMessage();
        }
        
        try {
            treenit.tallenna();
        } catch ( TallennusException ex ) {
            virheIlmoitus += ex.getMessage();
        }
        
        try {
            ruokailut.tallenna();
        } catch ( TallennusException ex ) {
            virheIlmoitus += ex.getMessage();
        }
        if ( !"".equals(virheIlmoitus) ) throw new TallennusException(virheIlmoitus);
    }
    
    /**
     * Testiohjelma seurannasta
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        Seuranta seuranta = new Seuranta();
        
   
      try {  
        Uni yo2 = new Uni();
        Uni yo1 = new Uni();
        
        Treeni pull = new Treeni();
        Treeni pull2 = new Treeni();
        
        Ruokailu aamupala = new Ruokailu();
        Ruokailu aamupala2 = new Ruokailu();
        
        yo1.asetaPvm("1.1.2021");
        yo1.taytaUni();
        
        yo2.asetaPvm("2.1.2021");
        yo2.taytaUni();
        
        seuranta.lisaa(aamupala);
        seuranta.lisaa(pull2);
        seuranta.lisaa(pull);
        seuranta.lisaa(yo1);
        seuranta.lisaa(yo2);
        seuranta.lisaa(aamupala2);
        
        pull.taytaSelkaTreenilla(yo1.getPvmNro());
        pull2.taytaSelkaTreenilla(yo2.getPvmNro());
        
        aamupala.taytaAamupala(yo1.getPvmNro());
        aamupala2.taytaAamupala(yo2.getPvmNro());
        
        
        System.out.println("---Testataan seurantaa---");
        
        
        for (int i = 0; i < seuranta.getPaivat(); i++ ) {
            Uni uni = seuranta.annaUni(i);
            System.out.println(uni.getPvm());
            uni.tulosta(System.out);
            
            System.out.println();
            System.out.println("Päivän treenit ja ruokailut");
            System.out.println();
            
            List<Treeni> treenitLoyty = seuranta.haeTreenit(uni);
            for (Treeni treeni : treenitLoyty) {
                treeni.tulosta(System.out);
                System.out.println();
            }
             
            
            List<Ruokailu>  ruokaLoyty = seuranta.haeRuokailut(uni);
            for (Ruokailu ruokailu : ruokaLoyty) {
                ruokailu.tulosta(System.out);
                System.out.println();
            } 
            
         
        
        }
        
          } catch ( TallennusException ex) {
            System.out.println(ex.getMessage());
              }
        }

    /**
     * Palauttaa talukossa hakuehtoa vastaavan unen viitteen
     * @param ehto ehto jolla haetaan (päivämäärä)
     * @param i etsittävän ehdon indeksi
     * @return löytyneet unet
     * @throws TallennusException jos on jotakin ongelmia
     * @example
     * <pre name="test">
     * #THROWS CloneNotSupportedException, TallennusException
     * Seuranta seuranta = new Seuranta();
     * Uni uni = new Uni();
     * Treeni treeni = new Treeni();
     * Ruokailu ruokailu = new Ruokailu();
     * uni.taytaUni();
     * treeni.taytaSelkaTreenilla(1);
     * ruokailu.taytaAamupala(1);
     * seuranta.lisaa(uni);
     * seuranta.lisaa(treeni);
     * seuranta.lisaa(ruokailu);
     * 
     * Uni uni2 = new Uni();
     * uni2.aseta(1, "20.04.2021");
     * seuranta.lisaa(uni2);
     * 
     * Collection<Uni> loytyneet = seuranta.etsi("*20*", 1);
     * loytyneet.size() === 1;
     *
     * Iterator<Uni> iu = loytyneet.iterator();
     * iu.next() == uni2 === true;
     * </pre>
     */
    public Collection<Uni> etsi(String ehto, int i) throws TallennusException {
        
        return unet.etsi(ehto, i);
    }
    
    /**
     * Poistaa unen tiedot 
     * @param uni uni joka poistetaan
     * @return montako unta poistettiin
     * @example
     * <pre name="test">
     * #THROWS TallennusException
     * Seuranta seuranta = new Seuranta();
     * Uni uni = new Uni();
     * Treeni treeni = new Treeni();
     * Ruokailu ruokailu = new Ruokailu();
     * uni.taytaUni();
     * treeni.taytaSelkaTreenilla(1);
     * ruokailu.taytaAamupala(1);
     * seuranta.lisaa(uni);
     * seuranta.lisaa(treeni);
     * seuranta.lisaa(ruokailu);
     * 
     * seuranta.etsi("*",0).size() === 1;
     * seuranta.poista(uni) === 1;
     * seuranta.etsi("*",0).size() === 0;
     * </pre>
     */
    public int poista(Uni uni) {
        if ( uni == null) return 0;
        int nro = unet.poista(uni.getPvmNro());
        treenit.poistaUniTreenit(uni.getPvmNro());
        ruokailut.poistaUniRuokailut(uni.getPvmNro());
        return nro;
    }

    /**
     * Poistaa tietyn ruokailun 
     * @param ruokailu poistettava ruokailu
     * @example
     * <pre name="test">
     * #THROWS TallennusException
     * Seuranta seuranta = new Seuranta();
     * Uni uni = new Uni();
     * Treeni treeni = new Treeni();
     * Ruokailu ruokailu = new Ruokailu();
     * uni.taytaUni();
     * uni.asetaPvm();
     * int uid = uni.getPvmNro();
     * treeni.taytaSelkaTreenilla(uid);
     * ruokailu.taytaAamupala(uid);
     * seuranta.lisaa(uni);
     * seuranta.lisaa(treeni);
     * seuranta.lisaa(ruokailu); 
     * 
     * seuranta.haeRuokailut(uni).size() === 1;
     * seuranta.poistaRuokailu(ruokailu);
     * seuranta.haeRuokailut(uni).size() === 0;
     * </pre>
     */
    public void poistaRuokailu(Ruokailu ruokailu) {
        ruokailut.poista(ruokailu);
        
    }

    /**
     * Poistetaan treeni 
     * @param treeni poistettava treeni
     * @example
     * <pre name="test">
     * #THROWS TallennusException
     * Seuranta seuranta = new Seuranta();
     * Uni uni = new Uni();
     * Treeni treeni = new Treeni();
     * Ruokailu ruokailu = new Ruokailu();
     * uni.taytaUni();
     * uni.asetaPvm();
     * int uid = uni.getPvmNro();
     * treeni.taytaSelkaTreenilla(uid);
     * ruokailu.taytaAamupala(uid);
     * seuranta.lisaa(uni);
     * seuranta.lisaa(treeni);
     * seuranta.lisaa(ruokailu); 
     * 
     * seuranta.haeTreenit(uni).size() === 1;
     * seuranta.poistaTreeni(treeni);
     * seuranta.haeTreenit(uni).size() === 0;
     * </pre>
     */
    public void poistaTreeni(Treeni treeni) {
        treenit.poista(treeni);        
    }

 
}


