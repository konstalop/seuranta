package Seuranta.test;
// Generated by ComTest BEGIN
import java.io.File;
import java.util.*;
import java.io.IOException;
import static org.junit.Assert.*;
import org.junit.*;
import Seuranta.*;
// Generated by ComTest END

/**
 * Test class made by ComTest
 * @version 2021.04.24 19:18:27 // Generated by ComTest
 *
 */
@SuppressWarnings({ "all" })
public class UnetTest {



  // Generated by ComTest BEGIN
  /** 
   * testLisaaUni37 
   * @throws TallennusException when error
   */
  @Test
  public void testLisaaUni37() throws TallennusException {    // Unet: 37
    Unet unet = new Unet(); 
    Uni yo1 = new Uni(), yo2 = new Uni(); 
    assertEquals("From: Unet line: 41", 0, unet.getUnet()); 
    unet.lisaaUni(yo1); 
    assertEquals("From: Unet line: 43", 1, unet.getUnet()); 
    unet.lisaaUni(yo2); 
    assertEquals("From: Unet line: 45", 2, unet.getUnet()); 
    assertEquals("From: Unet line: 46", yo1, unet.annaUni(0)); 
    assertEquals("From: Unet line: 47", yo2, unet.annaUni(1)); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** 
   * testLue107 
   * @throws TallennusException when error
   * @throws IOException when error
   */
  @Test
  public void testLue107() throws TallennusException, IOException {    // Unet: 107
    Unet unet = new Unet(); 
    Uni yo1 = new Uni(), yo2 = new Uni(); 
    yo1.taytaUni(); 
    yo2.taytaUni(); 
    String hakemisto = "testiunet"; 
    String tiedosto = hakemisto + "/nimet"; 
    File ftied = new File(tiedosto + ".dat"); 
    File dir = new File(hakemisto); 
    dir.mkdir(); 
    ftied.delete(); 
    try {
    unet.lue(tiedosto); 
    fail("Unet: 124 Did not throw TallennusException");
    } catch(TallennusException _e_){ _e_.getMessage(); }
    unet.lisaaUni(yo1); 
    unet.lisaaUni(yo2); 
    unet.tallenna(); 
    unet = new Unet(); 
    unet.lue(tiedosto); 
    Iterator<Uni> i = unet.iterator(); 
    assertEquals("From: Unet line: 131", yo1, i.next()); 
    try {
    assertEquals("From: Unet line: 132", yo2, i.next()); 
    fail("Unet: 132 Did not throw NoSuchElementException");
    } catch(NoSuchElementException _e_){ _e_.getMessage(); }
    assertEquals("From: Unet line: 133", false, i.hasNext()); 
    assertEquals("From: Unet line: 134", true, ftied.delete()); 
    assertEquals("From: Unet line: 135", true, dir.delete()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** 
   * testEtsi287 
   * @throws TallennusException when error
   */
  @Test
  public void testEtsi287() throws TallennusException {    // Unet: 287
    Unet unet = new Unet(); 
    Uni uni1 = new Uni(); uni1.parse("1|01.01.2021|8h|50bpm|35ms"); 
    Uni uni2 = new Uni(); uni2.parse("2|02.01.2021|9h|60bpm|40ms"); 
    Uni uni3 = new Uni(); uni3.parse("3|03.01.2021|7h|70bpm|30ms"); 
    unet.lisaaUni(uni1); 
    unet.lisaaUni(uni2); 
    unet.lisaaUni(uni3); 
    List<Uni> loytyneet; 
    loytyneet = (List<Uni>)unet.etsi("*01*", 1); 
    assertEquals("From: Unet line: 298", 3, loytyneet.size()); 
    assertEquals("From: Unet line: 299", true, loytyneet.get(0) == uni1); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** 
   * testPoista320 
   * @throws TallennusException when error
   */
  @Test
  public void testPoista320() throws TallennusException {    // Unet: 320
    Unet unet = new Unet(); 
    Uni yo1 = new Uni(), yo2 = new Uni(), yo3 = new Uni(); 
    yo1.asetaPvm(); yo2.asetaPvm(); yo3.asetaPvm(); 
    int pv1 = yo1.getPvmNro(); 
    unet.lisaaUni(yo1); unet.lisaaUni(yo2); unet.lisaaUni(yo3); 
    assertEquals("From: Unet line: 327", 1, unet.poista(pv1+1)); 
    assertEquals("From: Unet line: 328", 1, unet.poista(pv1)); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** 
   * testEtsiId348 
   * @throws TallennusException when error
   */
  @Test
  public void testEtsiId348() throws TallennusException {    // Unet: 348
    Unet unet = new Unet(); 
    Uni yo1 = new Uni(), yo2 = new Uni(), yo3 = new Uni(); 
    yo1.asetaPvm(); yo2.asetaPvm(); yo3.asetaPvm(); 
    int pv1 = yo1.getPvmNro(); 
    unet.lisaaUni(yo1); unet.lisaaUni(yo2); unet.lisaaUni(yo3); 
    assertEquals("From: Unet line: 355", 1, unet.etsiId(pv1+1)); 
    assertEquals("From: Unet line: 356", 2, unet.etsiId(pv1+2)); 
  } // Generated by ComTest END
}