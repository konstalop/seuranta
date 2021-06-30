package Seuranta;

import java.io.OutputStream;
import java.io.PrintStream;

import fi.jyu.mit.ohj2.Mjonot;
import tietuepaketti.Tietue;

/**
 * Ruokailu luokka joka huolehtii esim. omasta id:stä
 * @author Konsta
 * @version 1.0 17.3.2021
 * @version 1.1 22.4.2021
 *
 */
public class Ruokailu implements Cloneable, Tietue {
    
    private int ruokailuNro;
    private int pvmNro;
    private String ruokailuaika;
    private String maarat;
    private String ruoat;
    
    private static int seuraavaRuokailuNro = 1;
    
    
    
    /**
     * Alustetaan yksi ruokailu
     */
    public Ruokailu() {
        // riittää tällaisena
    }
    
    /**
     * alustetaan tietyn päivän ruokailu
     * @param pvmNro ruokailun viite päivään
     */
    public Ruokailu(int pvmNro) {
        this.pvmNro = pvmNro;
    }
    
    
    /**
     * Antaa ruokailulle uuden numeron 
     * @return uusi numero
     * @example
     * <pre name="test">
     * Ruokailu ruokailu = new Ruokailu();
     * ruokailu.getRuokailuNro() === 0;
     * ruokailu.rekisteroi();
     * Ruokailu ruokailu2 = new Ruokailu();
     * ruokailu2.rekisteroi();
     * int r1 = ruokailu.getRuokailuNro();
     * int r2 = ruokailu2.getRuokailuNro();
     * r1 === r2-1;
     * </pre>  
     */
    public int rekisteroi() {
        ruokailuNro = seuraavaRuokailuNro;
        seuraavaRuokailuNro++;
        return ruokailuNro;
    }
    
    /**
     * Parse metodi jolla selvitetään treenin teidot
     * @param rivi rivi josta haetaan tietoa
     * @example
     * <pre name="test">
     * Ruokailu ruokailu = new Ruokailu();
     * ruokailu.parse(" 1   |  1 | aamupala | kana | 120g");
     * ruokailu.getRuokailuNro() === 1;
     * 
     * ruokailu.rekisteroi();
     * int n = ruokailu.getRuokailuNro();
     * ruokailu.parse(""+(n+20));
     * ruokailu.rekisteroi();
     * 
     * ruokailu.getRuokailuNro() === n+20+1;
     * ruokailu.toString() === "" + (n+20+1) + "|1|aamupala|kana|120g";
     * </pre>
     */
    public void parse(String rivi) {
        StringBuffer sb = new StringBuffer(rivi);
        setRuokailuNro(Mjonot.erota(sb, '|', ruokailuNro));
        pvmNro = Mjonot.erota(sb, '|', pvmNro);
        ruokailuaika = Mjonot.erota(sb, '|', ruokailuaika);
        ruoat = Mjonot.erota(sb, '|', ruoat);
        maarat = Mjonot.erota(sb, '|', maarat);
 
    }
    
    
    /**
     * palauttaa treenin tallennettaan muotoon
     * @return treenit jotka eroteltu tolpilla
     * @example
     * <pre name="test">
     * Ruokailu ruokailu = new Ruokailu();
     * ruokailu.parse("1  | 1 | aamupala | kana");
     * ruokailu.toString() === "1|1|aamupala|kana|";
     * </pre>
     */
    @Override
    public String toString() {
        return "" + getRuokailuNro() + "|" + pvmNro + "|" + ruokailuaika + "|" + ruoat +"|" + maarat;
    }
    
    /**
     * Asetetaan treenin oma id numero
     * @param nro asetettava numero
     */
    private void setRuokailuNro(int nro) {
        ruokailuNro = nro;
        if (ruokailuNro >= seuraavaRuokailuNro) seuraavaRuokailuNro = ruokailuNro + 1;
    }
    
    
    /**
     * Palautetaan ruokailun oma id
     * @return ruokailu id
     */
    public int getRuokailuNro() {
        return ruokailuNro;
    }
    
    /**
     * Metodi ruokailun tietojen tulostamiseen
     * @param os tulostevirta johon tulostetaan
     */
    public void tulosta (OutputStream os) {
        PrintStream out = new PrintStream(os);
        out.println(ruokailuaika + ": " + "Ruoat: " + ruoat +  " Määrä: " + maarat + " " +pvmNro);
    }
    
    /**
     * Täytetään aamupalan tiedot (testi metodi)
     * @param pvmNro1 mille päivälle ruokailusijottuu
     */
    public void taytaAamupala(int pvmNro1) {
        this.ruoat = "Puuro, kananmunia, marjoja";
        this.maarat = "150g, 150g, 100g";
        this.ruokailuaika = "Aamupala";
        this.pvmNro = pvmNro1;
    }
    
    /**
     * Palauttaa ruokailun päivämäärän numeron.
     * @return ruokailun päivämäärän numero
     */
    public int getRuokailuPvmNro() {
        return pvmNro;
    }
    
    /**
     * Testi maini luokalle Ruokailu
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        Ruokailu aamupala = new Ruokailu();
        aamupala.taytaAamupala(1);
        aamupala.tulosta(System.out);
        

    }

    /**
     * @return ekakiinostava kenttä
     */
    @Override
    public int ekaKentta() {
        return 2;
    }

    /**
     * @return yhteismäärä kenttiä
     */
    @Override
    public int getKenttia() {
        return 5;
    }

    @Override
    public String getKysymys(int k) {
        switch (k) {
        case 0:
            return "id";
        case 1:
            return "Pvm numero";
        case 2:
            return "Ruokailuaika";
        case 3:
            return "Ruoat";
        case 4: 
            return "Määrät";
        default:
            return "miten täällä";
        }
    }

    /**
     * @param i mikä kenttä
     * @return valittu kenttä sisältöineen
     */
    @Override
    public String anna(int i) {
        switch (i) {
        case 0:
            return "" + ruokailuNro;
        case 1:
            return "" + pvmNro;
        case 2:
            return ruokailuaika;
        case 3:
            return ruoat;
        case 4:
            return maarat;
        default:
            return "taas pielessä";
        }
       
    }

    /**
     * Asetetaan valitun kentän sisältö
     * @param k mikä kenttä
     * @param s jono mistä asetetaan
     * @return null jos ok, muuten virheilmoitus
     * @example
     * <pre name="test">
     * Ruokailu ruokailu = new Ruokailu();
     * ruokailu.aseta(2, "aamupala") === null;
     * ruokailu.aseta(3, "kana, riisi") === null;
     * ruokailu.aseta(4, "250g, 300g") === null;
     * </pre>
     */
    @Override
    public String aseta(int k, String s) {
        String jono = s.trim();
        StringBuilder sb = new StringBuilder(jono);
        
        switch (k) {
        case 0:
            setRuokailuNro(Mjonot.erota(sb, '|', ruokailuNro));
            return null;
        case 1:
            pvmNro = Mjonot.erota(sb, '|', pvmNro);
            return null;
        case 2:
            ruokailuaika = jono;
            return null;
        case 3:
            ruoat = jono;
            return null;
        case 4:
            maarat = jono;
            return null;
        default:
            return "taas pieles";
            
        }
    }

    /**
     * Identtinen klooni ruokailusa
     * @return kloonattu ruokailu
     * @example
     * <pre name="test">
     * #THROWS CloneNotSupportedException
     * Ruokailu ruokailu = new Ruokailu();
     * ruokailu.parse("1|1|aamupala|kana|100g");
     * Ruokailu ruokailu2 = ruokailu.clone();
     * ruokailu2.toString() === ruokailu.toString();
     * ruokailu.parse("2|2|iltapala|leipa|150g");
     * ruokailu2.toString().equals(ruokailu.toString()) === false;
     * </pre>
     */
    @Override
    public Ruokailu clone() throws CloneNotSupportedException {
        return (Ruokailu)super.clone();
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        return this.toString().equals(obj.toString());
    }
    
    @Override
    public int hashCode() {
        return ruokailuNro;
    }
    
}
