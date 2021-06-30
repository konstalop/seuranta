package tarkistus.test;
// Generated by ComTest BEGIN
import tarkistus.*;
import static org.junit.Assert.*;
import org.junit.*;
// Generated by ComTest END

/**
 * Test class made by ComTest
 * @version 2021.04.22 18:20:35 // Generated by ComTest
 *
 */
@SuppressWarnings({ "all" })
public class PvmTarkistusTest {



  // Generated by ComTest BEGIN
  /** testTarkista20 */
  @Test
  public void testTarkista20() {    // PvmTarkistus: 20
    PvmTarkistus pvm = new PvmTarkistus(); 
    assertEquals("From: PvmTarkistus line: 23", "Liian suuri päivä!", pvm.tarkista("32.02.2021")); 
    assertEquals("From: PvmTarkistus line: 24", "Liian lyhyt päivämäärä, jotakin puuttuu!", pvm.tarkista("1.1.2021")); 
    assertEquals("From: PvmTarkistus line: 25", null, pvm.tarkista("12.02.2021")); 
    assertEquals("From: PvmTarkistus line: 26", "Liian pieni vuosi!", pvm.tarkista("13.04.1999")); 
    assertEquals("From: PvmTarkistus line: 27", "Liian suuri kuukausi!", pvm.tarkista("20.19.2021")); 
  } // Generated by ComTest END
}