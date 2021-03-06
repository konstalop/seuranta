package Seuranta.test;
// Generated by ComTest BEGIN
import java.io.*;
import java.util.*;
import static org.junit.Assert.*;
import org.junit.*;
import Seuranta.*;
// Generated by ComTest END

/**
 * Test class made by ComTest
 * @version 2021.04.24 19:13:17 // Generated by ComTest
 *
 */
@SuppressWarnings({ "all" })
public class SeurantaTest {



  // Generated by ComTest BEGIN
  /** testHaeTreenit96 */
  @Test
  public void testHaeTreenit96() {    // Seuranta: 96
    Seuranta seuranta = new Seuranta(); 
    Uni yo1 = new Uni(), yo2 = new Uni(), yo3 = new Uni(); 
    yo1.asetaPvm("1.1.2021"); 
    yo2.asetaPvm("2.1.2021"); 
    int testiPvm = yo1.getPvmNro(); 
    int testiPvm1 = yo2.getPvmNro(); 
    Treeni treeni1 = new Treeni(); 
    treeni1.taytaSelkaTreenilla(testiPvm); 
    seuranta.lisaa(treeni1); 
    Treeni treeni2 = new Treeni(); 
    treeni2.taytaSelkaTreenilla(testiPvm1); 
    seuranta.lisaa(treeni2); 
    Treeni treeni3 = new Treeni(); 
    treeni3.taytaSelkaTreenilla(testiPvm1); 
    seuranta.lisaa(treeni3); 
    List<Treeni> loyty; 
    loyty = seuranta.haeTreenit(yo3); 
    assertEquals("From: Seuranta line: 123", 0, loyty.size()); 
    loyty = seuranta.haeTreenit(yo1); 
    assertEquals("From: Seuranta line: 125", 1, loyty.size()); 
    loyty = seuranta.haeTreenit(yo2); 
    assertEquals("From: Seuranta line: 127", 2, loyty.size()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testHaeRuokailut140 */
  @Test
  public void testHaeRuokailut140() {    // Seuranta: 140
    Seuranta seuranta = new Seuranta(); 
    Uni yo1 = new Uni(), yo2 = new Uni(), yo3 = new Uni(); 
    yo1.asetaPvm("1.1.2021"); 
    yo2.asetaPvm("2.1.2021"); 
    int testiPvm = yo1.getPvmNro(); 
    int testiPvm1 = yo2.getPvmNro(); 
    Ruokailu ruokailu1 = new Ruokailu(); 
    ruokailu1.taytaAamupala(testiPvm); 
    seuranta.lisaa(ruokailu1); 
    Ruokailu ruokailu2 = new Ruokailu(); 
    ruokailu2.taytaAamupala(testiPvm1); 
    seuranta.lisaa(ruokailu2); 
    Ruokailu ruokailu3 = new Ruokailu(); 
    ruokailu3.taytaAamupala(testiPvm1); 
    seuranta.lisaa(ruokailu3); 
    List<Ruokailu> loyty; 
    loyty = seuranta.haeRuokailut(yo3); 
    assertEquals("From: Seuranta line: 167", 0, loyty.size()); 
    loyty = seuranta.haeRuokailut(yo1); 
    assertEquals("From: Seuranta line: 169", 1, loyty.size()); 
    loyty = seuranta.haeRuokailut(yo2); 
    assertEquals("From: Seuranta line: 171", 2, loyty.size()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** 
   * testLueTiedosto197 
   * @throws TallennusException when error
   * @throws IOException when error
   */
  @Test
  public void testLueTiedosto197() throws TallennusException, IOException {    // Seuranta: 197
    Seuranta seuranta = new Seuranta(); 
    Uni yo1 = new Uni(); yo1.taytaUni(); yo1.asetaPvm("1.1.2021"); 
    Treeni selka = new Treeni(); selka.taytaSelkaTreenilla(yo1.getPvmNro()); 
    Ruokailu aamu = new Ruokailu(); aamu.taytaAamupala(yo1.getPvmNro()); 
    String hakemisto = "testiseuranta"; 
    File dir = new File(hakemisto); 
    File utied = new File(hakemisto+"/unet.dat"); 
    File ttied = new File(hakemisto+"/treenit.dat"); 
    File rtied = new File(hakemisto+"/ruokailut.dat"); 
    dir.mkdir(); 
    utied.delete(); 
    ttied.delete(); 
    rtied.delete(); 
    try {
    seuranta.lueTiedosto(hakemisto); 
    fail("Seuranta: 218 Did not throw TallennusException");
    } catch(TallennusException _e_){ _e_.getMessage(); }
    seuranta.lisaa(yo1); 
    seuranta.lisaa(selka); 
    seuranta.lisaa(aamu); 
    seuranta.tallenna(); 
    List<Treeni> loytyneet = seuranta.haeTreenit(yo1); 
    Iterator<Treeni> it = loytyneet.iterator(); 
    assertEquals("From: Seuranta line: 228", selka, it.next()); 
    assertEquals("From: Seuranta line: 229", true, it.hasNext()); 
    List<Ruokailu> loytyneet2 = seuranta.haeRuokailut(yo1); 
    Iterator<Ruokailu> ir = loytyneet2.iterator(); 
    assertEquals("From: Seuranta line: 234", aamu, ir.next()); 
    assertEquals("From: Seuranta line: 235", false, ir.hasNext()); 
    seuranta.lisaa(yo1); 
    seuranta.lisaa(selka); 
    seuranta.lisaa(aamu); 
    seuranta.tallenna(); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** 
   * testEtsi361 
   * @throws CloneNotSupportedException when error
   * @throws TallennusException when error
   */
  @Test
  public void testEtsi361() throws CloneNotSupportedException, TallennusException {    // Seuranta: 361
    Seuranta seuranta = new Seuranta(); 
    Uni uni = new Uni(); 
    Treeni treeni = new Treeni(); 
    Ruokailu ruokailu = new Ruokailu(); 
    uni.taytaUni(); 
    treeni.taytaSelkaTreenilla(1); 
    ruokailu.taytaAamupala(1); 
    seuranta.lisaa(uni); 
    seuranta.lisaa(treeni); 
    seuranta.lisaa(ruokailu); 
    Uni uni2 = new Uni(); 
    uni2.aseta(1, "20.04.2021"); 
    seuranta.lisaa(uni2); 
    Collection<Uni> loytyneet = seuranta.etsi("*20*", 1); 
    assertEquals("From: Seuranta line: 379", 1, loytyneet.size()); 
    Iterator<Uni> iu = loytyneet.iterator(); 
    assertEquals("From: Seuranta line: 382", true, iu.next() == uni2); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** 
   * testPoista395 
   * @throws TallennusException when error
   */
  @Test
  public void testPoista395() throws TallennusException {    // Seuranta: 395
    Seuranta seuranta = new Seuranta(); 
    Uni uni = new Uni(); 
    Treeni treeni = new Treeni(); 
    Ruokailu ruokailu = new Ruokailu(); 
    uni.taytaUni(); 
    treeni.taytaSelkaTreenilla(1); 
    ruokailu.taytaAamupala(1); 
    seuranta.lisaa(uni); 
    seuranta.lisaa(treeni); 
    seuranta.lisaa(ruokailu); 
    assertEquals("From: Seuranta line: 408", 1, seuranta.etsi("*",0).size()); 
    assertEquals("From: Seuranta line: 409", 1, seuranta.poista(uni)); 
    assertEquals("From: Seuranta line: 410", 0, seuranta.etsi("*",0).size()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** 
   * testPoistaRuokailu425 
   * @throws TallennusException when error
   */
  @Test
  public void testPoistaRuokailu425() throws TallennusException {    // Seuranta: 425
    Seuranta seuranta = new Seuranta(); 
    Uni uni = new Uni(); 
    Treeni treeni = new Treeni(); 
    Ruokailu ruokailu = new Ruokailu(); 
    uni.taytaUni(); 
    uni.asetaPvm(); 
    int uid = uni.getPvmNro(); 
    treeni.taytaSelkaTreenilla(uid); 
    ruokailu.taytaAamupala(uid); 
    seuranta.lisaa(uni); 
    seuranta.lisaa(treeni); 
    seuranta.lisaa(ruokailu); 
    assertEquals("From: Seuranta line: 440", 1, seuranta.haeRuokailut(uni).size()); 
    seuranta.poistaRuokailu(ruokailu); 
    assertEquals("From: Seuranta line: 442", 0, seuranta.haeRuokailut(uni).size()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** 
   * testPoistaTreeni454 
   * @throws TallennusException when error
   */
  @Test
  public void testPoistaTreeni454() throws TallennusException {    // Seuranta: 454
    Seuranta seuranta = new Seuranta(); 
    Uni uni = new Uni(); 
    Treeni treeni = new Treeni(); 
    Ruokailu ruokailu = new Ruokailu(); 
    uni.taytaUni(); 
    uni.asetaPvm(); 
    int uid = uni.getPvmNro(); 
    treeni.taytaSelkaTreenilla(uid); 
    ruokailu.taytaAamupala(uid); 
    seuranta.lisaa(uni); 
    seuranta.lisaa(treeni); 
    seuranta.lisaa(ruokailu); 
    assertEquals("From: Seuranta line: 469", 1, seuranta.haeTreenit(uni).size()); 
    seuranta.poistaTreeni(treeni); 
    assertEquals("From: Seuranta line: 471", 0, seuranta.haeTreenit(uni).size()); 
  } // Generated by ComTest END
}