package fxSeuranta;

import java.io.IOException;
import java.io.PrintStream;
import java.net.URL;
import static fxSeuranta.TietueDialogController.annaFieldId;
import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ListChooser;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.StringGrid;
import fi.jyu.mit.ohj2.Mjonot;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.util.*;

import Seuranta.Ruokailu;
import Seuranta.Seuranta;
import Seuranta.TallennusException;
import Seuranta.Treeni;
import Seuranta.Uni;

/**
 * @author Konsta
 * @version 1.0 15.2.2021
 * @version 1.1 15.3.2021 
 * @version 1.2 22.4.2021 lisätty hakeminen
 * Seuranta ohjelman kontrolleri
 */
public class SeurantaGUIController implements Initializable  {

    
    
    @FXML private Label labelVirhe;
    
    @FXML
    private TextField editSVV;
    
    @FXML 
    private TextField editPituus;
    
    @FXML 
    private TextField editPvm;
    
    @FXML 
    private TextField editSyke;
    
    @FXML
    private MenuItem itemSulje;

    @FXML
    private Button closeButton;
  
    @FXML
    private Button buttonTreeni;

    @FXML
    private Button buttonPalautuminen;

    @FXML
    private ListChooser<Uni> chooserPalautuminen;
    
    @FXML
    private AnchorPane panelUni;
    
    @FXML
    private TitledPane panelUni2;
    
    @FXML
    private Button buttonTallenna;
    
    @FXML
    private Button buttonAvaa;
    
    @FXML
    private StringGrid<Treeni> tableTreeni;
    
    @FXML
    private StringGrid<Ruokailu> tableRuokailu;
    
    @FXML
    private GridPane gridUni;
    
    @FXML
    private TextField hakuEdit;
    
    
    
    
    /**
     * Avataan palautumisen seuranta ikkuna
     */
    @FXML private void handlePalautuminen() {    
       ModalController.showModal(SeurantaGUIController.class.getResource("PalautuminenGUIView.fxml"), "Palautuminen", null, "");
    }
    
    /**
     * Avataan treenin seuranta ikkuna
     */
    @FXML private void handleTreeni() {
        ModalController.showModal(SeurantaGUIController.class.getResource("TreeniGUIView.fxml"), "Treeni", null, "");
    }
    
    /**
     * Lisätään uusi ruokailu
     */
    @FXML
    void handleLisaaRuokailu() {
        uusiRuokailu();
    }
    
    
    @FXML
    private void handleHaku() {
        hae(0);
    }
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
      alustaminen();
        
    }
    
    
    /**
     * Avataan haluttu treeni
     */
    @FXML
    void handleAvaaTreeni() {
        Dialogs.showMessageDialog("Ei osata avata vielä treeniä!");
    }
    
    /**
     * Lisätään uusi treeni
     */
    @FXML
    void handleLisaaTreeni() {
        //ModalController.showModal(SeurantaGUIController.class.getResource("UusiTreeniGUIView.fxml"), "Uusi Treeni", null, "");
        uusiTreeni();
    }
    /**
     * Muokataan valittua treeniä
     */
    @FXML
    void handleMuokkaaTreeni() {
        ModalController.showModal(SeurantaGUIController.class.getResource("UusiPalautuminenGUIView.fxml"), "Treeni", null, "");
    }
    
    /**
     * Poistetaan treeni
     */
    @FXML
    void handlePoistaTreeni() {
        poistaTreeni();
    }
    
    
    /**
     * poistetaan ruokailu
     */
    @FXML
    void handlePoistaRuokailu() {
        poistaRuokailu();
    }
    
    
    /**
     * Avataan ohjelman infolaatikko
     */
    @FXML
    void avaaApuaOhjelmasta() {
        ModalController.showModal(SeurantaGUIController.class.getResource("TiedotGUIView.fxml"), "Treeni", null, "");       
    }
    
    
    /**
     * Peruutetaan tallennus "uusi treeni ikkunassa"
     */
    @FXML
    void peruutaTallennus() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Peruuta");
        alert.setHeaderText(null);
        alert.setContentText("Peruutetaanko?");
        
        ButtonType buttonTypeYes = new ButtonType("Kyllä", ButtonData.OK_DONE);
        ButtonType buttonTypeCancel = new ButtonType("Ei", ButtonData.BACK_PREVIOUS);
        
        alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeCancel);
        
        Optional<ButtonType> result = alert.showAndWait();
        if ( result.get() == buttonTypeYes ) stage.close();
              
    }

    
    /**
     * Suljetaan ohjelma tämän avulla.
     */
    @FXML
    void handleSulje() {

        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Sulje");
        alert.setHeaderText(null);
        alert.setContentText("Suljetaanko?");

        ButtonType buttonTypeYes = new ButtonType("Kyllä", ButtonData.OK_DONE);
        ButtonType buttonTypeCancel = new ButtonType("Ei", ButtonData.BACK_PREVIOUS);
        
        alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeCancel);
        
        Optional<ButtonType> result = alert.showAndWait();
        if ( result.get() == buttonTypeYes ) System.exit(0);;
    }
    
    /**
     * Tallennetaan uusi treeni
     */
    @FXML
    void tallennaTreeni() {
        Dialogs.showMessageDialog("Ei osata vielä tallentaa!");
    }

    /**
     * Tallennetaan uusi palautuminen
     */
    @FXML
    void tallenaPalautuminen() {
        Dialogs.showMessageDialog("Ei osata viellä tallentaa!");
    }
    
    

    
    /**
     * Avataan haluttu palautumisen seuranta
     */
    @FXML
    void handleAvaaPalautuminen() {    
       //avaa();
    }

    /**
     * Lisätään uusi palautumisen seuranta
     */
    @FXML
    void handleLisaaPalautuminen() {
       //ModalController.showModal(SeurantaGUIController.class.getResource("UusiPalautuminenGUIView.fxml"), "Uusi Palautuminen", null, "");
        uusiPalautuminen();
       // alustaminen();
        
    }

    /**
     * Muokataan haluttua palautumista
     * @throws TallennusException 
     */
    @FXML
    void handleMuokkaaPalautuminen() {
        muokkaa(kentta);
    }

    /**
     * Poistetaan valittu palautumnien
     */
    @FXML
    void handlePoistaUni() {
        poistaUni();
    }
    
    
    /**
     * tallennetaan
     * @throws IOException jos ongelmia tallennuksessa
     */
    @FXML
    void handleTallenna() throws IOException {
        tallenna();
        Dialogs.showMessageDialog("Tallennetaan!");
    }
    
    
    
    //========================================================================================================================//
    //==========================YLÄPUOLELLA FXML JUTUT ALAPUOLELLA OMAA KOODIA============================//
    
    private TextField[] edits;
    private Seuranta seuranta;
    private Uni unenKohdalla;
    private int kentta = 0;
    private static Treeni apuTreeni = new Treeni();
    private static Ruokailu apuRuokailu = new Ruokailu();
    //private static Uni apuUni = new Uni();
    
    
    /**
     * Katsotaan onko unen tavoite täyttynyt
     */
    private void tavoiteUni() {
        
        Uni uni = unenKohdalla;
        
        int pituus = Mjonot.erotaInt(uni.getPituus(), 0);
        
        if (pituus >= 8) {
            naytaVirhe(null);
        } else {
            naytaVirhe("Liian Vähän Unta!");
        }              
    }
    
    
    
    /**
     * tietojen tallennus
     * @return null jos onnistuu muuten virhe
     * @throws IOException jos ongelmia
     */
    private String tallenna() throws IOException  {
        try {
            seuranta.tallenna();
            return null;
        } catch ( TallennusException ex ) {
            Dialogs.showMessageDialog("ongelmia tallennuksessa! " + ex.getMessage());
            return ex.getMessage();
        }
    }
    
    
    /**
     * Alustetaan seuranta lukemalle tiedososta
     * @param nimi tiedosto josta luetaan
     * @return null jos onnistuu
     */
    protected String lueTiedosto(String nimi) {
        try {
            seuranta.lueTiedosto(nimi);
            hae(0);
            return null;
        } catch ( TallennusException e) {
            hae(0);
            String virheita = e.getMessage();
            if ( virheita != null ) Dialogs.showMessageDialog(virheita);
            return virheita;
        }
    }
    
    /**
     * Alustetaan seurantacontrolleri
     */
    private void alustaminen() {
       
        chooserPalautuminen.clear();
        
        chooserPalautuminen.addSelectionListener(e -> naytaUni());
        
        chooserPalautuminen.addSelectionListener(e -> tavoiteUni());
        
        edits = TietueDialogController.luoKentat(gridUni, new  Uni());
        for (TextField edit: edits)  
            if ( edit != null ) {  
                 edit.setEditable(false);  
                 edit.setOnMouseClicked(e -> { if ( e.getClickCount() > 1 ) muokkaa(annaFieldId(e.getSource(),kentta)); });  
                 edit.focusedProperty().addListener((a,o,n) -> kentta = annaFieldId(edit,kentta));  
            }    
        
        int eka = apuTreeni.ekaKentta();
        int lkm = apuTreeni.getKenttia();
        String[] head = new String[lkm-eka];
        for (int i = 0, k = eka; k<lkm; i++, k++) head[i] = apuTreeni.getKysymys(k);
        tableTreeni.initTable(head);
        tableTreeni.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableTreeni.setEditable(false);
        tableTreeni.setPlaceholder(new Label("Ei vielä treenejä!"));
        
        tableTreeni.setColumnSortOrderNumber(1);
        tableTreeni.setColumnSortOrderNumber(2);
        tableTreeni.setColumnWidth(1, 60);
        tableTreeni.setColumnWidth(2, 60);
        
        tableTreeni.setOnMouseClicked(e -> { if ( e.getClickCount() > 1) muokkaaTreenia(); });
        
        
        int ekaRuokailu = apuRuokailu.ekaKentta();
        int lkmRuokailu = apuRuokailu.getKenttia();
        String[] headRuokailu = new String[lkmRuokailu-ekaRuokailu];
        for (int i = 0, k = ekaRuokailu; k<lkmRuokailu; i++, k++) headRuokailu[i] = apuRuokailu.getKysymys(k);
        tableRuokailu.initTable(headRuokailu);
        tableRuokailu.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableRuokailu.setEditable(false);
        tableRuokailu.setPlaceholder(new Label("Ei vielä ruokailuja!"));
        
        tableRuokailu.setColumnSortOrderNumber(1);
        tableRuokailu.setColumnSortOrderNumber(2);
        tableRuokailu.setColumnWidth(1, 60);
        tableRuokailu.setColumnWidth(2, 60);
        
        tableRuokailu.setOnMouseClicked(e -> { if (e.getClickCount() > 1) muokkaaRuokailua(); });
        
        
    }
    
    
    
    /**
     * Muokataan tiettyä uni seurantaa
     * @throws TallennusException 
     */
    private void muokkaa(int k) {
        if (unenKohdalla == null) return;
        try { 
            Uni uni = TietueDialogController.kysyTietue(null, unenKohdalla.clone(), k);
            if (uni == null) return;
            seuranta.korvaa(uni);
            hae(uni.getPvmNro());
        } catch (CloneNotSupportedException e) {
            //
        } catch (TallennusException e) {
            Dialogs.showMessageDialog(e.getMessage());
        }
        
        
            
    }
    
    /**
     * Näytetään uni tietystä kohtaa chooseria
     */
    public void naytaUni() {
        unenKohdalla = chooserPalautuminen.getSelectedObject();
        
        if (unenKohdalla == null) return;
        
        TietueDialogController.naytaTietue(edits, unenKohdalla);
        
        naytaTreenit(unenKohdalla);
        naytaRuokailut(unenKohdalla);
       
    }
    
    /**
     * Näytetään ruokailut taulukossa
     * @param uni päivä jonka unesta ruokailu saadaan
     */
    private void naytaRuokailut(Uni uni) {
        tableRuokailu.clear();
        List<Ruokailu> ruokailut = seuranta.haeRuokailut(uni);
        if ( ruokailut.size() == 0) return;
        for (Ruokailu ruokailu : ruokailut)
            naytaRuokailu(ruokailu);
    }
    
    /**
     * Näytetään ruokailu
     * @param ruokailu käsiteltävä ruokailu
     */
    private void naytaRuokailu(Ruokailu ruokailu) {
        int kenttia = ruokailu.getKenttia();
        String[] jono = new String[kenttia-ruokailu.ekaKentta()];
        for (int i = 0, k=ruokailu.ekaKentta(); k < kenttia; i++, k++)
            jono[i] = ruokailu.anna(k);
        tableRuokailu.add(ruokailu , jono);
    }
    
    /**
     * Näytetään treenit taulukkoon
     * @param uni päivä jonka unesta treeni saadaan
     */
    private void naytaTreenit(Uni uni) {
        tableTreeni.clear();  
        
        if (uni == null) return;
        
        List<Treeni> treenit = seuranta.haeTreenit(uni);
        if ( treenit.size() == 0) return;
        for (Treeni treeni : treenit)
            naytaTreeni(treeni);            
    }
    
    /**
     * Lisätään StringGridiin treenin tiedot
     * @param treeni mikä treeni
     */
    private void naytaTreeni(Treeni treeni) {
        int kenttia = treeni.getKenttia();
        String[] jono = new String[kenttia-treeni.ekaKentta()];
        for (int i = 0, k=treeni.ekaKentta(); k < kenttia; i++, k++)
            jono[i] = treeni.anna(k);
        tableTreeni.add(treeni , jono);
    }
    
    /**
     * Tulostetaan tiedot päivästä
     * @param os tietovirta johon tulostus tehdään
     * @param uni tulostettava uni eli päivä
     */
    public void tulosta(PrintStream os, final Uni uni) {
        uni.tulosta(os);
        List<Treeni> treenit = seuranta.haeTreenit(uni);
        for (Treeni treeni:treenit) {
            treeni.tulosta(os);
        }
        
        List<Ruokailu> ruokailut = seuranta.haeRuokailut(uni);
        for (Ruokailu ruokailu:ruokailut) {
            ruokailu.tulosta(os);
        }
    }
    
    
    /**
     * Tehdään uusi treeni
     */
    public void uusiTreeni() {
        Treeni treeni = new Treeni();
        treeni.rekisteroi();
        treeni.taytaSelkaTreenilla(unenKohdalla.getPvmNro());
        seuranta.lisaa(treeni);
        hae(unenKohdalla.getPvmNro());
    }
    
    /**
     * Muokataan treeniä
     */
    private void muokkaaTreenia() {
        int r = tableTreeni.getRowNr();
        if ( r < 0 ) return;
        Treeni treeni = tableTreeni.getObject();
        if ( treeni == null) return;
        int k = tableTreeni.getColumnNr()+treeni.ekaKentta();
        try {
            treeni = TietueDialogController.kysyTietue(null, treeni.clone(), k);
            if ( treeni == null) return;
            seuranta.korvaa(treeni);
            naytaTreenit(unenKohdalla);
            tableTreeni.selectRow(r);
        } catch (CloneNotSupportedException e) {
            //
        } catch (TallennusException e) {
            Dialogs.showMessageDialog(e.getMessage());
        }
    }
    
    /**
     * Muokataan ruokailua
     */
    private void muokkaaRuokailua() {   
        int r = tableRuokailu.getRowNr();
        if ( r < 0 ) return;
        Ruokailu ruokailu = tableRuokailu.getObject();
        if ( ruokailu == null) return;
        int k = tableTreeni.getColumnNr()+ruokailu.ekaKentta();
        try {
            ruokailu = TietueDialogController.kysyTietue(null, ruokailu.clone(), k);
            if ( ruokailu == null) return;
            seuranta.korvaa(ruokailu);
            naytaRuokailut(unenKohdalla);
            tableRuokailu.selectRow(r);
        } catch (CloneNotSupportedException e) {
            //
        } catch (TallennusException e) {
            Dialogs.showMessageDialog(e.getMessage());
        }
    }

    
    /**
     * lisätään uusi ruokailu
     */
    public void uusiRuokailu() {
        Ruokailu ruokailu = new Ruokailu();
        ruokailu.rekisteroi();
        ruokailu.taytaAamupala(unenKohdalla.getPvmNro());
        seuranta.lisaa(ruokailu);
        hae(unenKohdalla.getPvmNro());
    }
    
    /**
     * Asetetaan käytettävä seuranta
     * @param seuranta1 käytettävä seuranta
     */
    public void asetaSeuranta(Seuranta seuranta1) {
        this.seuranta = seuranta1;       
    }
    
    
    /**
     * Lisätään uusi palautuminen 
     */
    private void uusiPalautuminen() {
        Uni yo1 = new Uni();
        yo1 = TietueDialogController.kysyTietue(null, yo1, 1);
        if (yo1 == null) return;
       
        yo1.asetaPvm();
             
        try {
            seuranta.lisaa(yo1);
        } catch (TallennusException e) {
            Dialogs.showMessageDialog(" Ongelmia luomisessa: "+  e.getMessage());
            return;
        }
        hae(yo1.getPvmNro());        
    }
    

    
    /**
     * haetaan unen tiedot
     * @param pvmNumero unen pvmnumero
     */
    protected void hae(int pvmNumero) {
        int pvmNro = pvmNumero;
        chooserPalautuminen.clear();  
        
        if (pvmNro <= 0) {
            Uni kohdalla = unenKohdalla;
            if ( kohdalla != null) pvmNro = kohdalla.getPvmNro();
        }
        
        String ehto = hakuEdit.getText();
        if (ehto.indexOf('*') < 0) ehto = "*" + ehto + "*";
        
        int indeksi = 0;
        Collection<Uni> unet;
        try {
            unet = seuranta.etsi(ehto, 1);
            int i = 0;
            for (Uni uni:unet) {
                if (uni.getPvmNro() == pvmNro) indeksi = i;
                chooserPalautuminen.add(uni.getPvm(), uni);
                i++;
                }
            } catch (TallennusException ex) {
                Dialogs.showMessageDialog("Hakemisessa ongelmia" + ex.getMessage());
            }
        
            chooserPalautuminen.setSelectedIndex(indeksi);
        }
        
        
        /**
         * Poistetaan haluttu uni
         */
        private void poistaUni() {
            Uni uni = unenKohdalla;
            if ( uni == null) return;
            if (!Dialogs.showQuestionDialog("Poisto", "Poistetaanko uni päivältä?"+ uni.getPvm(), "Kyllä", "Ei")) return;
            seuranta.poista(uni);
            int indeksi = chooserPalautuminen.getSelectedIndex();
            hae(0);
            chooserPalautuminen.setSelectedIndex(indeksi);
        }
        
        /**
         * Poistetaan haluttu ruokailu
         */
        private void poistaRuokailu() {
            int rivi = tableRuokailu.getRowNr();
            if ( rivi < 0 ) return;
            Ruokailu ruokailu = tableRuokailu.getObject();
            if ( ruokailu == null) return;
            seuranta.poistaRuokailu(ruokailu);
            naytaRuokailut(unenKohdalla);
            int ruokailuja = tableRuokailu.getItems().size();
            if ( rivi >= ruokailuja) rivi = ruokailuja -1;
            tableRuokailu.getFocusModel().focus(rivi);
            tableRuokailu.getSelectionModel().select(rivi);
            
        }
        
        /**
         * Poistetaan haluttu treeni
         */
        private void poistaTreeni() {
            int rivi = tableTreeni.getRowNr();
            if ( rivi < 0 ) return;
            Treeni treeni = tableTreeni.getObject();
            if ( treeni == null) return;
            seuranta.poistaTreeni(treeni);
            naytaTreenit(unenKohdalla);
            int treeneja = tableTreeni.getItems().size();
            if ( rivi >= treeneja) rivi = treeneja -1;
            tableTreeni.getFocusModel().focus(rivi);
            tableTreeni.getSelectionModel().select(rivi);
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
        
        
    }   
