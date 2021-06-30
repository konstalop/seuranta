package Seuranta;

import java.io.OutputStream;
import java.io.PrintStream;

import fi.jyu.mit.ohj2.Mjonot;
import tietuepaketti.Tietue;

/**
 * Seurannan treeni luokka joka osaa huolehtia esim. omasta id:stä.
 * @author Konsta
 * @version 1.0 16.3.2021
 * @version 1.1 22.4.2021 lisätty cloneable, tietue, muutama metodi myös
 *
 */
public class Treeni implements Cloneable, Tietue {

    private int treeniNro;
    private int pvmNro;
    private String liike = "";
    private double sarjapaino = 0.0;
    private String lisatiedot = "";
    private String sarjatToistot = "";
    
    private static int seuraavaTreeniNro = 1;
    /**
     * Tulostetaan jonkin treenin tiedot
     * @param os tietovirta mihin tiedot tulostetaan
     */
    public void tulosta(OutputStream os) {
        PrintStream out = new PrintStream(os);
        out.println("Liike: " + liike);
        out.println("Sarjapaino " + sarjapaino + "kg");
        out.println("Lisätiedot: " + lisatiedot);
        out.println("Sarjat & Toistot: " + sarjatToistot);
        out.println(pvmNro);
        
    }
    
    /**
     * toimii näin
     */
    public Treeni() {
        //
    }
    
    /** 
     * @return treenin kenttien lukumäärä
     */
    @Override
    public int getKenttia() {
        return 6;
    }
    
    /**
     * Annetaan kysymys
     * @param k minkä kentän kysymys halutaan
     * @return valitun kentän teksti
     */
    @Override
    public String getKysymys(int k) {
        switch (k) {
        case 0:
            return "id";
        case 1:
            return "Pvm numero";
        case 2:
            return "Liike";
        case 3:
            return "Paino";
        case 4:
            return "S&T";
        case 5:
            return "Lisätietoja";
        default:
            return "mitä taasko";
        }
    }
    
    /**
     * Annetana tietyn kentän k sisältö
     * @param k halutun kentän indeksi
     * @return halutun kentän sisältö
     */
    @Override
    public String anna(int k) {
        switch (k) {
        case 0:
            return "" + treeniNro;
        case 1:
            return "" + pvmNro;
        case 2:
            return liike;
        case 3:
            return "" + sarjapaino;
        case 4:
            return "" + sarjatToistot;
        case 5:
            return lisatiedot;
        default:
            return "mitä taas";
        }
    }
    
    /**
     * @return eka kiinostava kenttä
     */
    @Override
    public int ekaKentta() {
        return 2;
    }
    /**
     * alustetaan pvmnro
     * @param pvmNro pvmnro
     */
    public Treeni(int pvmNro) {
        this.pvmNro = pvmNro;
    }
    
    /**
     * Täytetään testiarvot jonkin treenin liikkeelle
     * @param pvmNro1 mille päivälle treeni menee
     */
    public void taytaSelkaTreenilla(int pvmNro1) {
        liike = "Maastaveto";
        sarjapaino = 180;
        lisatiedot = "Kulki kivasti";
        sarjatToistot = "3x1";
        this.pvmNro = pvmNro1;      
    }
    
    /**
     * Asetetaan treenin oma id numero
     * @param nro asetettava numero
     */
    private void setTreeniNro(int nro) {
        treeniNro = nro;
        if (treeniNro >= seuraavaTreeniNro) seuraavaTreeniNro = treeniNro + 1;
    }
    
    
    /**
     * Antaa treenille uuden numeron 
     * @return uusi numero
     * @example
     * <pre name="test">
     * Treeni selka = new Treeni();
     * selka.getTreeniNro() === 0;
     * selka.rekisteroi();
     * Treeni jalka = new Treeni();
     * jalka.rekisteroi();
     * 
     * int t1 = selka.getTreeniNro();
     * int t2 = jalka.getTreeniNro();
     * t1 === t2 - 1;
     * </pre>
     */
    public int rekisteroi() {
        treeniNro = seuraavaTreeniNro;
        seuraavaTreeniNro++;
        return treeniNro;
    }
    
    /**
     * Parse metodi jolla selvitetään treenin teidot
     * @param rivi rivi josta haetaan tietoa
     * @example
     * <pre name="test">
     * Treeni treeni = new Treeni();
     * treeni.parse("1|1|mave|180");
     * treeni.getTreeniNro() === 1;
     * 
     * treeni.toString() === "1|1|mave|180.0||";
     * 
     * treeni.rekisteroi();
     * 
     * int numero = treeni.getTreeniNro();
     * treeni.parse(""+(numero+20));
     * treeni.rekisteroi();
     * treeni.getTreeniNro();
     * treeni.toString() === "" + (numero+20+1) + "|1|mave|180.0||";
     * </pre>
     */
    public void parse(String rivi) {
        StringBuffer sb = new StringBuffer(rivi);
        setTreeniNro(Mjonot.erota(sb, '|', treeniNro));
        pvmNro = Mjonot.erota(sb, '|', pvmNro);
        liike = Mjonot.erota(sb, '|', liike);
        sarjapaino = Mjonot.erota(sb, '|', sarjapaino);
        sarjatToistot = Mjonot.erota(sb, '|', sarjatToistot);
        lisatiedot = Mjonot.erota(sb, '|', lisatiedot);
 
    }
    
    /**
     * palauttaa treenin tallennettaan muotoon
     * @return treenit jotka eroteltu tolpilla
     * @example
     * <pre name="test">
     * Treeni treeni = new Treeni();
     * treeni.parse(" 1 | 1 | mave | 180.0 | 4x3 | hyvin |");
     * treeni.toString() === "1|1|mave|180.0|4x3|hyvin";
     * </pre>
     */
    @Override
    public String toString() {
        return "" + getTreeniNro() + "|" + pvmNro + "|" + liike + "|" + sarjapaino +"|" + sarjatToistot + "|" +lisatiedot;
    }
    
    /**
     * palautetaan treeniNro
     * @return treeninumero
     */
    public int getTreeniNro() {
        return treeniNro;
    }

    /**
     * Palauttaa treenin päivämäärän tunnusnumeron
     * @return jonkin päivämäärän tunnusnumero
     */
    public int getTreeniPvmNro() {
        return pvmNro;
    }
    
    /**
     * Testiohjelmaa treenille
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        Treeni pullLiike1 = new Treeni(), pullLiike2 = new Treeni();
        System.out.println("===Tyhjät Treeni===");
        pullLiike1.tulosta(System.out);
        pullLiike2.tulosta(System.out);
        pullLiike1.taytaSelkaTreenilla(1);
        System.out.println("--------Täytetään pull treenin ekan liikkeen tiedot--------");
        pullLiike1.tulosta(System.out);
        System.out.println("--------Täytetään pull treenin toisen liikkeen tiedot (samat tiedot)------");
        pullLiike2.taytaSelkaTreenilla(1);
        pullLiike2.tulosta(System.out);
    }

    /**
     * Asetetaan valitun kentän sisätlö
     * @param k mikä kenttä
     * @param s asetettava sisältö merkkijonona
     * @return null jos ok, muuten virheteksti
     * @example
     * <pre name="test">
     * Treeni treeni = new Treeni();
     * treeni.aseta(2, "mave") === null;
     * treeni.aseta(3, "paino") === "Sarjapaino ei ole kokonaisluku (paino)";
     * treeni.aseta(5, "kiva") === null;  
     * </pre>
     */
    @Override
    public String aseta(int k, String s) {
       String jono = s.trim();
       StringBuilder sb = new StringBuilder(jono);
       switch (k) {
       case 0:
           setTreeniNro(Mjonot.erota(sb, '|', getTreeniNro()));
           return null;
       case 1:
           pvmNro = Mjonot.erota(sb, '|', pvmNro);
           return null;
       case 2:
           liike = jono;
           return null;
       case 3:
           try {
               sarjapaino = Mjonot.erotaEx(sb, '|', sarjapaino);
           } catch (NumberFormatException ex) {
               return "Sarjapaino ei ole kokonaisluku ("+jono+")";
           }
           
           return null;
       case 4:
           sarjatToistot = jono;
           return null;
       case 5:
           lisatiedot = jono;
           return null;
       default: 
           return "miten taas";
       }
    }

    /**
     * Identtinen klooni treenistä
     * @return Kloonattu treeni
     * @example
     * <pre name="test">
     * #THROWS CloneNotSupportedException
     * Treeni treeni = new Treeni();
     * treeni.parse("1|1|mave|180.0|3x1|kiva");
     * Treeni treeni2 = treeni.clone();
     * treeni2.toString() === treeni.toString();
     * treeni.parse("2|2|pena|120.0|4x1|kivaaaa");
     * treeni2.toString().equals(treeni.toString()) === false;
     * </pre>
     */
    @Override
    public Treeni clone() throws CloneNotSupportedException {
        return (Treeni)super.clone();
    }


    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        return this.toString().equals(obj.toString());
    }
    
    @Override
    public int hashCode() {
        return treeniNro;
    }
}
