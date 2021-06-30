package tietuepaketti;

/**
 * Rajapinta tietueelle
 * @author Konsta
 * @version 1.0 22.4.2021
 * @version 1.1 23.4.2021 lisätty testit
 *
 */
public interface Tietue {
    
    /**
     * @return tietueen kenttien lukumäärä
     * @example
     * <pre name="test">
     * #import Seuranta.Treeni;
     * Treeni treeni = new Treeni();
     * treeni.getKenttia() === 6;
     * </pre>
     */
    public abstract int getKenttia();
    
    /**
     * @return tietueen eka kenttä
     * @example
     * <pre name="test">
     * #import Seuranta.Treeni;
     * Treeni treeni = new Treeni();
     * treeni.ekaKentta() === 2;
     * </pre>
     */
    public abstract int ekaKentta();
    
    
    /**
     * @param k minkä kentän kysymys halutaan
     * @return valitun kentän sisältö
     * @example
     * <pre name="test">
     * #import Seuranta.Treeni;
     * Treeni treeni = new Treeni();
     * treeni.getKysymys(3) === "Paino";
     * </pre>
     */
    public abstract String getKysymys(int k);
    
    /**
     * @param i minkä kentän sisältöä halutaan
     * @return valitun kentän sisältö
     * @example
     * <pre name="test">
     * Treeni treeni = new Treeni();
     * treeni.parse("1|1|mave|180|3x1|kiva");
     * treeni.anna(0) === "1";
     * treeni.anna(1) === "1";
     * treeni.anna(2) === "mave";
     * </pre>
     */
    public abstract String anna(int i);
    
    /**
     * @param k minkä kentän sisältö asetetaan
     * @param s asetettava sisältö
     * @return null jos ok muuten virheteksti
     * @example
     * <pre name="test">
     * Treeni treeni = new Treeni();
     * treeni.aseta(2, "mave") === null;
     * treeni.aseta(3, "paino") === "Sarjapaino ei ole kokonaisluku (paino)";
     * treeni.aseta(5, "kiva") === null;  
     * </pre>
     */
    public abstract String aseta(int k, String s);
    
    
    /**
     * Tehdään identtinen klooni tietueesta
     * @return kloonattu
     * @throws CloneNotSupportedException jos ei onnistu
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
    public abstract Tietue clone() throws CloneNotSupportedException;
    
    /**
     * Palautetaan tietueen tiedot merkkijono
     * @return tolppaeroteltu merkkijono
     * @example
     * <pre name="test">
     * Treeni treeni = new Treeni();
     * treeni.parse(" 1 | 1 | mave | 180.0 | 4x3 | hyvin |");
     * treeni.toString() === "1|1|mave|180.0|4x3|hyvin";
     * </pre>
     */
    @Override
    public abstract String toString();
}