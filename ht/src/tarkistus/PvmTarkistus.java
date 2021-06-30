package tarkistus;

/**
 * Luokka jolla tarkistetaan päivämäärä.
 * @author Konsta
 * @version 22.4.2021
 *
 */
public class PvmTarkistus {
    
    /**
     * Tarkistetaan päivämäärä. 
     * Oikea pvm muoto on pv.kk.vvvv
     * esim. 12.04.2021
     * @param pvm tutkittava päivämäärä
     * @return null jos oikein, muuten virhe viesti.
     * @example
     * <pre name="test">
     * #PACKAGEIMPORT
     * PvmTarkistus pvm = new PvmTarkistus();
     * pvm.tarkista("32.02.2021") === "Liian suuri päivä!";
     * pvm.tarkista("1.1.2021") === "Liian lyhyt päivämäärä, jotakin puuttuu!";
     * pvm.tarkista("12.02.2021") === null;
     * pvm.tarkista("13.04.1999") === "Liian pieni vuosi!";
     * pvm.tarkista("20.19.2021") === "Liian suuri kuukausi!";
     * </pre>
     */
    public String tarkista(String pvm) {
        
        int pituus = pvm.length();
        if (pituus < 10) return "Liian lyhyt päivämäärä, jotakin puuttuu!";
        
        
        int pv = Integer.parseInt(pvm.substring(0,2));
        int kk = Integer.parseInt(pvm.substring(3,5));
        int vuosi = Integer.parseInt(pvm.substring(6,10));
        
        
        if (pv > 31) return "Liian suuri päivä!";
        if (kk > 12) return "Liian suuri kuukausi!";
        if (vuosi < 2021) return "Liian pieni vuosi!";
        return null;
    }
}
