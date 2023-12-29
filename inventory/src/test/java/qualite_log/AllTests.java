package qualite_log;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import qualite_log.data_import.ParserTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
    ParserTest.class
})
public class AllTests {
}