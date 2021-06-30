package Seuranta;

import java.io.OutputStream;
import java.io.PrintStream;

import fi.jyu.mit.ohj2.Mjonot;
import tarkistus.PvmTarkistus;
import tietuepaketti.Tietue;

/**
 * Uni joka osaa huolehtia omasta pvm numerostaan.
 * @author Konsta
 * @version 1.0 17.3.2021
 * @version 1.1 23.4.2021 lisätty testejä ja muutama metodi
 *
 */
public class Uni implements Cloneable, Tietue {
    
    private String pvm;
    private String pituus;
    private String keskisyke;
    private String svv;
    private int pvmNro;
    
    private static int seuraavaPvmNro = 1;
    
    
    private PvmTarkistus pvmTarkistin = new PvmTarkistus();
    
    /**
     * Uni alustus
     */
    public Uni() {
        //Riittää atribuuttejen alustus tällä hetkellä.
    }
    
    /**
     * Palautetaan unen kenttien lukumäärä
     * @return kenttien lukumäärä
     */
    @Override
    public int getKenttia() {
        return 5;
    }
    
    /**
     * Eka meille kiinostava kenttä
     * @return ekan kentän indeksi
     */
    @Override
    public int ekaKentta() {
        return 1;
    }
    
    /**
     * Paluttaa k vastaavan kysymysken
     * @param k kysymyksen indeksi
     * @return indeksiä vastaava kysymys
     */
    @Override
    public String getKysymys(int k) {
        switch (k) {
        case 0: return "Pvm numero";
        case 1: return "Päivämäärä";
        case 2: return "Pituus";
        case 3: return "Keskisyke";
        case 4: return "Sykevälivaihtelu";
        default: return "Mitä";
        }
    }
    
    /**
     * Antaa j kentän sisällön merkkijonon
     * @param j monenko kentän sisältö palautetaan
     * @return kentän sisältö stringinä
     */
    @Override
    public String anna(int j) {
        switch (j) {
        case 0: return "" + pvmNro;
        case 1: return "" + pvm;
        case 2: return "" + pituus;
        case 3: return "" + keskisyke;
        case 4: return "" + svv;
        default: return "Miten tää tälläi";
        }
    }
    
    /**
     * Asetetaan k kentän arvoksi merkkijono
     * @param k minkä kentän arvoksi asetetaan
     * @param jono joka asetetaan kentän arvoksi
     * @return null jos onnistuu, muuten virheilmoitus
     * @example
     * <pre name="test">
     * Uni uni = new Uni();
     * uni.aseta(3,"60bpm") === null;
     * uni.aseta(1,"1.1.2021") === "Liian lyhyt päivämäärä, jotakin puuttuu!";
     * uni.aseta(1, "39.02.2021") === "Liian suuri päivä!";
     * uni.aseta(4, "35ms") === null;
     * </pre>
     */
    @Override
    public String aseta(int k, String jono) {
        String tjono = jono.trim();
        StringBuilder sb = new StringBuilder(tjono);
        switch (k) {
        case 0: 
            asetaPvmNro(Mjonot.erota(sb, '|', getPvmNro()));
            return null;
        case 1:
            PvmTarkistus tarkistus = new PvmTarkistus();
            String virhe = tarkistus.tarkista(tjono);
            if ( virhe != null) return virhe;
            pvm = tjono;
            return null;
        case 2:  
            pituus = tjono;
            return null;
        case 3:
            keskisyke = tjono;
            return null;
        case 4:
            svv = tjono;
            return null;
        default:
            return "miten tääl taas";
        }
    }
    
    /**
     * Asetetaan tietyn päivän pvm ja sen avulla saadaan aina uusi pvmNro
     * @param paivamaara tietyn päivän uni
     * @return kyseisen päivämäärän numeron
     */
    public int asetaPvm(String paivamaara) {
        this.pvm = paivamaara;
        pvmNro = seuraavaPvmNro;
        seuraavaPvmNro++;
        return pvmNro;
    }
    
    /**
     * Asetetaan pvmNro eli rekisteröidään
     * @return pvmnumero
     * @example
     * <pre name="test">
     * Uni yo1 = new Uni();
     * yo1.getPvmNro() === 0;
     * yo1.asetaPvm();
     * Uni yo2 = new Uni();
     * yo2.asetaPvm();
     * int testi = yo1.getPvmNro();
     * int testi1 = yo2.getPvmNro();
     * testi === testi1 - 1;
     * </pre>
     */
    public int asetaPvm() {
        pvmNro = seuraavaPvmNro;
        seuraavaPvmNro++;
        return pvmNro;
    }
    
    /**
     * Uni luokan tostring
     * @example
     * <pre name="test">
     * Uni uni = new Uni();
     * uni.parse("1|1.1.2021|8h");
     * uni.toString().startsWith("1|5.1.2021|8h|") === false;
     * </pre>
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("");
        String erotin = "";
        for (int k = 0; k < getKenttia();k++) {
            sb.append(erotin);
            sb.append(anna(k));
            erotin = "|";
        }
        
        return sb.toString();    
            
    }
    
    private void asetaPvmNro(int nro) {
        pvmNro = nro;
        if (pvmNro >= seuraavaPvmNro) seuraavaPvmNro = pvmNro + 1; 
    }
    
    /**
     * Selvitetään unen tiedot merkkijonosta 
     * @param rivi rivi jossa jäsenen tiedot
     * @example
     * <pre name="test">
     * Uni uni = new Uni();
     * uni.parse("1|1.1.2021|8h");
     * uni.getPvmNro() === 1;
     * 
     * uni.asetaPvm("1.1.2021");
     * int numero = uni.getPvmNro();
     * uni.parse(""+(numero+20));
     * uni.asetaPvm("2.1.2021");
     * uni.getPvmNro() === numero+20+1;
     * </pre>
     */
    public void parse(String rivi) {
        StringBuilder sb = new StringBuilder(rivi);
        for (int k = 0; k < getKenttia(); k++) {
            aseta(k, Mjonot.erota(sb, '|'));
        }
    }
    
    /**
     * Tulostetaan unen tiedot
     * @param os tulostevirta
     */
    public void tulosta(OutputStream os) {
        PrintStream out = new PrintStream(os);
        out.println(pituus + " " + keskisyke + " " + svv + " " + pvm +  " " + pvmNro);
    }
    
    
    /**
     * Palautetaan päivämäärä
     * @return päivämäärä
     */
    public String getPvm() {
        return pvm;
    }
    
    /**
     * @return unen pituus
     */
    public String getPituus() {
        return pituus;
    }
    
    /**
     * @return unen sykevälivaihtelu
     */
    public String getSVV() {
        return svv;
    }
    
    /**
     * @return unen keskisyke
     */
    public String getSyke() {
        return keskisyke;
    }
    
    /**
     * Palautetaan pvm nro
     * @return pvmNro jokaisella päivämäärällä on uniikki numero.
     */
    public int getPvmNro() {
        return pvmNro;
    }
    
    /**
     * Asetetaan pvm
     * @param s unelle ilmoitettava päivämäärä
     * @return virheimloitus, null jos ok
     */
    public String setPvm(String s) {
        String virhe = pvmTarkistin.tarkista(s);
        if (virhe != null) return virhe;
        pvm = s;
        return null;
    }
    
    /**
     * Lisätään unelle pituus
     * @param s tarkasteltava merkkijono
     * @return null jos ok
     */
    public String setPituus(String s) {
        pituus = s;
        return null;
    }
    
    /**
     * Lisätään unelle syke
     * @param s tarkasltetva merkkijono
     * @return null jos ok
     */
    public String setSyke(String s) {
        keskisyke = s;
        return null;
    }
    
    /**
     * Lisätään unelle svv (sykevälivaihtelu)
     * @param s tarkasteltava merkkijono
     * @return null jos ok
     */
    public String setSVV(String s) {
        svv = s;
        return null;
    }
    
    /**
     * testi täydennys luokan Uni tiedoille.
     */
    public void taytaUni() {
        this.pituus = "8h";
        this.keskisyke = "56bpm";
        this.svv = "35ms";
    }
    
    /**
     * luodaan klooni unesta
     * @return kloonattu uni
     * @example
     * <pre name="test">
     * #THROWS CloneNotSupportedException
     * Uni uni = new Uni();
     * uni.parse("1|01.01.2021|8h|50bpm|35ms");
     * Uni uni2 = uni.clone();
     * uni2.toString() === uni.toString();
     * uni.parse("2|02.02.2022|9h|60bpm|99ms");
     * uni2.toString().equals(uni.toString()) === false;
     * </pre>
     */
    @Override
    public Uni clone() throws CloneNotSupportedException {
        Uni uni;
        uni = (Uni) super.clone();
        return uni;
    }
    
    
    /**
     * Tutkii onko unen tiedot samat kuin parametrina tuodon unen tiedot.
     * @param uni johon verrataan
     * @return true jos samat muuten false 
     */
    public boolean equals(Uni uni) {
        if ( uni == null ) return false;
        for (int k = 0; k < getKenttia(); k++)
            if ( !anna(k).equals(uni.anna(k)) ) return false;
        return true;
    }
    
    /**
     * oma equals methodi
     */
    @Override
    public boolean equals(Object uni) {
        if ( uni instanceof Uni ) return equals((Uni)uni);
        return false;
    }
    
    @Override
    public int hashCode() {
        return pvmNro;
    }
    
    /**
     * Testi maini luokalle uni
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        Uni nukuttu = new Uni();
        nukuttu.asetaPvm("1.1.2021");
        nukuttu.taytaUni();
        nukuttu.tulosta(System.out);
        Uni nukuttu2 = new Uni();
        nukuttu2.asetaPvm("2.1.2021");
        nukuttu2.tulosta(System.out);
        
    }


}
