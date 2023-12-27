package qualite_log.data_import;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ Parser.class, ParserQLog.class })
public class ParserTest {
    private ParserQLog testedParser;
    private ParserQLog testedParserQLog; 

    @Test
    public void test1() {
        
    }
}
