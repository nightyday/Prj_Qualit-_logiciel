package qualite_log.data_import;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ DataReader.class, DataWriter.class })
public class ParserTest {
    private DataWriter testedParser;
    private DataWriter testedParserQLog; 

    @Test
    public void test1() {
        
    }
}
