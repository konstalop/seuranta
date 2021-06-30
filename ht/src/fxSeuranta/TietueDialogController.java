package fxSeuranta;

import java.net.URL;
import java.util.ResourceBundle;

import tietuepaketti.Tietue;
import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import fi.jyu.mit.ohj2.Mjonot;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * Tietueen tiedot luomalla sille uusi dialogi
 * @author Konsta
 * @version 21.4.2021
 *
 * @param <TYPE> minkä tyyppisiä olioita käsitellään
 */
public class TietueDialogController<TYPE extends Tietue> implements ModalControllerInterface<TYPE>,Initializable  {

    @FXML private Label labelVirhe;
    @FXML private GridPane gridTietue;
    
    @FXML private void handleOK() {
        if (TietueKohdalla != null && TietueKohdalla.anna(TietueKohdalla.ekaKentta()).trim().equals("")) {
            naytaVirhe("Ei voi olla tyhjänä!");
            return;
        }
        ModalController.closeStage(labelVirhe);
    }
    
    @FXML private void handleCancel() {
        TietueKohdalla = null;
        ModalController.closeStage(labelVirhe);
    }
    
    
    //=============================================================OMAA KOODIA ALAPUOLELLA=======================================================
    
    private TYPE TietueKohdalla;
    private TextField edits[];
    private int kentta = 0;
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        //         
    }

    @Override
    public TYPE getResult() {        
        return TietueKohdalla;
    }

    @Override
    public void handleShown() {
        kentta = Math.max(TietueKohdalla.ekaKentta(), Math.min(kentta, TietueKohdalla.getKenttia() - 1));
        edits[kentta].requestFocus();
        
    }
    
    @Override
    public void setDefault(TYPE oletus) {
        TietueKohdalla = oletus;
        alusta();
        naytaTietue(edits, TietueKohdalla);
        
    }
    
    /**
     * Asetetaan kenttä
     * @param kentta asetettavan kentän indeksi
     */
    private void setKentta(int kentta) {
        this.kentta = kentta;
    }
    
    /**
     * alustetaan
     */
    protected void alusta() {
        edits = luoKentat(gridTietue, TietueKohdalla);
       // int i = 0;
        for (TextField edit : edits) {
            if ( edit != null) 
                edit.setOnKeyReleased(e -> kasitteleMuutos((TextField)(e.getSource())));
        }
    }
    
    /**
     * Palautetaan komponentin idstä luku
     * @param obj tutkittava kompomnenenti
     * @param oletus mitä palautetaan jos ei saada idtä
     * @return komponentin id numero
     */
    public static int annaFieldId(Object obj, int oletus) {
        if ( !(obj instanceof Node)) return oletus;
        Node node = (Node)obj;
        String jono = node.getId();
        if ( jono.length() < 1) return oletus;
        return Mjonot.erotaInt(jono.substring(1), oletus);
    }
    
    /**
     * Näytetään virhe 
     * @param virheViesti virheestä saatu viesti
     */
    private void naytaVirhe(String virheViesti) {
        if ( virheViesti == null || virheViesti.isEmpty()) {
            labelVirhe.setText("");
            labelVirhe.getStyleClass().removeAll("virhe");
            return;
        }
        labelVirhe.setText(virheViesti);
        labelVirhe.getStyleClass().add("virhe");
    }
    
    /**
     * Käsitellään unen tullut muutos
     * @param k indeksi kenttään
     * @param edit muuttunut kenttä
     */
    private void kasitteleMuutos(TextField edit) {
        if (TietueKohdalla == null) return;
        int k = annaFieldId(edit, TietueKohdalla.ekaKentta());
        String jono = edit.getText();
        String virhe = null;
        virhe = TietueKohdalla.aseta(k, jono);
        if (virhe == null) {
            Dialogs.setToolTipText(edit, "");
            edit.getStyleClass().removeAll("virhe");
            naytaVirhe(virhe);
        } else {
            Dialogs.setToolTipText(edit, virhe);
            edit.getStyleClass().add("virhe");
            naytaVirhe(virhe);
        }
    }
    
    
    /**
     * Näytetään Tietue
     * @param edits taulukko missä on tekstikentät
     * @param Tietue näytettävän unen tiedot
     */
    public static void naytaTietue(TextField[] edits, Tietue Tietue) {
        if (Tietue == null) return;
        for (int k = Tietue.ekaKentta(); k < Tietue.getKenttia(); k++) {
            edits[k].setText(Tietue.anna(k));
        }
    }
    
    /**
     * Näytetään unen tiedot textfieldiin
     * @param Tietue käsiteltävä Tietue
     */
    public void naytaTietue(Tietue Tietue) {
        naytaTietue(edits, Tietue);
    }
    
    /**
     *  Luodaan unen kysymysdialogi.
     * @param modalityStage mille ollaan modaalisia
     * @param oletus oletus data (mitä näytetään)
     * @param kentta joka saa fokuksen
     * @return null jos perutaan muuten täytetty 
     */
    public static<TYPE extends Tietue> TYPE kysyTietue(Stage modalityStage, TYPE oletus, int kentta) {
      return ModalController.<TYPE, TietueDialogController<TYPE>>showModal(
       TietueDialogController.class.getResource("UusiPalautuminenGUIView.fxml"),
       "Seuranta",
       modalityStage, oletus,
       ctrl -> ctrl.setKentta(kentta));                      
    }
        
       
    
    /**
     * Luodaan gridpaneen unen tiedot
     * @param gridTietue mihin tiedot laitetaan
     * @param apuTietue malli
     * @return luodut kentät
     */
    public static<TYPE extends Tietue>TextField[] luoKentat(GridPane gridTietue, TYPE apuTietue) {
        gridTietue.getChildren().clear();
        TextField[] edits = new TextField[apuTietue.getKenttia()];
        
        for (int i = 0, k = apuTietue.ekaKentta(); k < apuTietue.getKenttia(); k++ , i++) {
            Label label = new Label(apuTietue.getKysymys(k));
            gridTietue.add(label, 0, i);
            TextField edit = new TextField();
            edits[k] = edit;
            edit.setId("e"+k);
            gridTietue.add(edit, 1, i);
        }
        return edits;
    }

}
