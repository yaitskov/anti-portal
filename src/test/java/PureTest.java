import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Session;
import org.apache.cassandra.db.Keyspace;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: dan
 * Date: 12/2/13
 * Time: 11:57 PM
 * To change this template use File | Settings | File Templates.
 */
public class PureTest {

    @Test
    public void test() {
        Cluster cluster = new Cluster.Builder()
                .addContactPoints("cas3")
//                .withPoolingOptions(pools)
                .build();
        try {
            Session s = cluster.connect("xxx");
            try {
                for (int i = 0; i < 100000; ++i) {
                    ResultSet rs = s.execute("insert into ticks (idx, lbl) values (?,?)",
                            i, "hello " + i);

                }
            } finally {
                s.shutdown();
            }
        } finally {
            cluster.shutdown();
        }
    }
}
