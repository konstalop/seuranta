package Seuranta.test;
// Generated by ComTest BEGIN
import static org.junit.Assert.*;
import org.junit.*;
import Seuranta.*;
// Generated by ComTest END

/**
 * Test class made by ComTest
 * @version 2021.04.23 22:41:28 // Generated by ComTest
 *
 */
@SuppressWarnings({ "all" })
public class RuokailuTest {



  // Generated by ComTest BEGIN
  /** testRekisteroi47 */
  @Test
  public void testRekisteroi47() {    // Ruokailu: 47
    Ruokailu ruokailu = new Ruokailu(); 
    assertEquals("From: Ruokailu line: 49", 0, ruokailu.getRuokailuNro()); 
    ruokailu.rekisteroi(); 
    Ruokailu ruokailu2 = new Ruokailu(); 
    ruokailu2.rekisteroi(); 
    int r1 = ruokailu.getRuokailuNro(); 
    int r2 = ruokailu2.getRuokailuNro(); 
    assertEquals("From: Ruokailu line: 55", r2-1, r1); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testParse68 */
  @Test
  public void testParse68() {    // Ruokailu: 68
    Ruokailu ruokailu = new Ruokailu(); 
    ruokailu.parse(" 1   |  1 | aamupala | kana | 120g"); 
    assertEquals("From: Ruokailu line: 71", 1, ruokailu.getRuokailuNro()); 
    ruokailu.rekisteroi(); 
    int n = ruokailu.getRuokailuNro(); 
    ruokailu.parse(""+(n+20)); 
    ruokailu.rekisteroi(); 
    assertEquals("From: Ruokailu line: 78", n+20+1, ruokailu.getRuokailuNro()); 
    assertEquals("From: Ruokailu line: 79", "" + (n+20+1) + "|1|aamupala|kana|120g", ruokailu.toString()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testToString97 */
  @Test
  public void testToString97() {    // Ruokailu: 97
    Ruokailu ruokailu = new Ruokailu(); 
    ruokailu.parse("1  | 1 | aamupala | kana"); 
    assertEquals("From: Ruokailu line: 100", "1|1|aamupala|kana|", ruokailu.toString()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testAseta229 */
  @Test
  public void testAseta229() {    // Ruokailu: 229
    Ruokailu ruokailu = new Ruokailu(); 
    assertEquals("From: Ruokailu line: 231", null, ruokailu.aseta(2, "aamupala")); 
    assertEquals("From: Ruokailu line: 232", null, ruokailu.aseta(3, "kana, riisi")); 
    assertEquals("From: Ruokailu line: 233", null, ruokailu.aseta(4, "250g, 300g")); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** 
   * testClone267 
   * @throws CloneNotSupportedException when error
   */
  @Test
  public void testClone267() throws CloneNotSupportedException {    // Ruokailu: 267
    Ruokailu ruokailu = new Ruokailu(); 
    ruokailu.parse("1|1|aamupala|kana|100g"); 
    Ruokailu ruokailu2 = ruokailu.clone(); 
    assertEquals("From: Ruokailu line: 272", ruokailu.toString(), ruokailu2.toString()); 
    ruokailu.parse("2|2|iltapala|leipa|150g"); 
    assertEquals("From: Ruokailu line: 274", false, ruokailu2.toString().equals(ruokailu.toString())); 
  } // Generated by ComTest END
}