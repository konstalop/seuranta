package Seuranta.test;
// Generated by ComTest BEGIN
import static org.junit.Assert.*;
import org.junit.*;
import Seuranta.*;
// Generated by ComTest END

/**
 * Test class made by ComTest
 * @version 2021.04.23 22:48:45 // Generated by ComTest
 *
 */
@SuppressWarnings({ "all" })
public class UniTest {



  // Generated by ComTest BEGIN
  /** testAseta94 */
  @Test
  public void testAseta94() {    // Uni: 94
    Uni uni = new Uni(); 
    assertEquals("From: Uni line: 96", null, uni.aseta(3,"60bpm")); 
    assertEquals("From: Uni line: 97", "Liian lyhyt päivämäärä, jotakin puuttuu!", uni.aseta(1,"1.1.2021")); 
    assertEquals("From: Uni line: 98", "Liian suuri päivä!", uni.aseta(1, "39.02.2021")); 
    assertEquals("From: Uni line: 99", null, uni.aseta(4, "35ms")); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testAsetaPvm146 */
  @Test
  public void testAsetaPvm146() {    // Uni: 146
    Uni yo1 = new Uni(); 
    assertEquals("From: Uni line: 148", 0, yo1.getPvmNro()); 
    yo1.asetaPvm(); 
    Uni yo2 = new Uni(); 
    yo2.asetaPvm(); 
    int testi = yo1.getPvmNro(); 
    int testi1 = yo2.getPvmNro(); 
    assertEquals("From: Uni line: 154", testi1 - 1, testi); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testToString166 */
  @Test
  public void testToString166() {    // Uni: 166
    Uni uni = new Uni(); 
    uni.parse("1|1.1.2021|8h"); 
    assertEquals("From: Uni line: 169", false, uni.toString().startsWith("1|5.1.2021|8h|")); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testParse195 */
  @Test
  public void testParse195() {    // Uni: 195
    Uni uni = new Uni(); 
    uni.parse("1|1.1.2021|8h"); 
    assertEquals("From: Uni line: 198", 1, uni.getPvmNro()); 
    uni.asetaPvm("1.1.2021"); 
    int numero = uni.getPvmNro(); 
    uni.parse(""+(numero+20)); 
    uni.asetaPvm("2.1.2021"); 
    assertEquals("From: Uni line: 204", numero+20+1, uni.getPvmNro()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** 
   * testClone316 
   * @throws CloneNotSupportedException when error
   */
  @Test
  public void testClone316() throws CloneNotSupportedException {    // Uni: 316
    Uni uni = new Uni(); 
    uni.parse("1|01.01.2021|8h|50bpm|35ms"); 
    Uni uni2 = uni.clone(); 
    assertEquals("From: Uni line: 321", uni.toString(), uni2.toString()); 
    uni.parse("2|02.02.2022|9h|60bpm|99ms"); 
    assertEquals("From: Uni line: 323", false, uni2.toString().equals(uni.toString())); 
  } // Generated by ComTest END
}