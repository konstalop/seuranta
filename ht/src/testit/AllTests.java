package testit;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
/**
 * Kaikki testit samassa 
 * @author Konsta
 * @version 1.0 24.4.2021
 *
 */

@RunWith(Suite.class)
@SuiteClasses({
    Seuranta.test.RuokailuTest.class,
    Seuranta.test.RuokailutTest.class,
    Seuranta.test.SeurantaTest.class,
    Seuranta.test.TreeniTest.class,
    Seuranta.test.TreenitTest.class,
    Seuranta.test.UnetTest.class,
    Seuranta.test.UniTest.class,
    tarkistus.test.PvmTarkistusTest.class,
    tietuepaketti.test.TietueTest.class})

    
public class AllTests {
    //
}
