package Seuranta;

/**
 * Poikkeusluokka jolla käsitellään tietorakenteen poikkeukset.
 * @author Konsta
 * @version 1.0 7.4.2021
 *
 */
public class TallennusException extends Exception {

    private static final long serialVersionUID = 1L;
    
    /**
     * Kyseisen poikkeuksen muodostja jolle tuodaan poikkeuksen viesti.
     * @param viesti poikkeuksesta saatu viesti
     */
    public TallennusException(String viesti) {
        super(viesti);
    }

}
