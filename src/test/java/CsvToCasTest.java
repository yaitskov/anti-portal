import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Session;
import com.espertech.esperio.AdapterInputSource;
import com.espertech.esperio.csv.CSVReader;
import com.espertech.esperio.csv.CSVSource;
import com.github.CsvTokenizer;
import com.github.TickProcessor;
import org.apache.camel.*;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.model.dataformat.BindyType;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.util.concurrent.CountDownLatch;

/**
 * Created with IntelliJ IDEA.
 * User: dan
 * Date: 12/2/13
 * Time: 11:57 PM
 * To change this template use File | Settings | File Templates.
 */
public class CsvToCasTest {

    @Test
    public void test() throws Exception {
        final CountDownLatch l = new CountDownLatch(1);

        FileInputStream fi =
                new FileInputStream(
                new File("data/export-2013-10-24-all.csv"));
        AdapterInputSource a = new AdapterInputSource(fi);
        CSVReader r = new CSVReader(a);

        TickProcessor tp = new TickProcessor(null);
        while (true) {
            String[] record = r.getNextRecord();
            tp.process(record);

        }




//        CamelContext context = new DefaultCamelContext();
//
//        context.addRoutes(new RouteBuilder() {
//            public void configure() {
//                from("file:data?noop=true&maxMessagesPerPoll=1")
//                        .split(new CsvTokenizer()
//                        ).streaming()
//                        .unmarshal().bindy(BindyType.Csv, "com.github")
//                        .process(new TickProcessor(l)).stop();
//            }
//        });
//        context.start();
//        l.await();
//        context.stop();
    }
}
