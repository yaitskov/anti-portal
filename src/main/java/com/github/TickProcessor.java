package com.github;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class TickProcessor {

    private static final Logger logger = LoggerFactory.getLogger(TickProcessor.class);
    final Cluster cluster;
    final Session s;
    final AtomicInteger c = new AtomicInteger(0);
    final SimpleDateFormat fo = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static final String Q = "insert into ticks (tenmin,instr," +
            "quota, milsecs, torder, meta, price,  " +
            "volume ) values (?,?,?,?,?,?,?,?)";

    public TickProcessor(CountDownLatch l) {
        cluster = new Cluster.Builder()
                .addContactPoints("cas3")
                .build();
        s = cluster.connect("anti_portal");
        fo.setTimeZone(TimeZone.getTimeZone("GMT"));
    }

    public void process(String[] t) throws Exception {

        if (c.incrementAndGet() % 10000 == 0) {
            logger.info("tick {} count {}", t[0], c.get());
        }
        Date created = fo.parse(t[1]);
        long tenmin = created.getTime() / TimeUnit.MINUTES.toMillis(10);
        int millisec = (int) (created.getTime() % TimeUnit.MINUTES.toMillis(10));
        String[] instr = t[0].split(":");


        s.executeAsync(Q, tenmin, instr[0], instr[1],
                millisec,
                (int) (Long.parseLong(t[2]) % Integer.MAX_VALUE),
                t[5],
                Double.parseDouble(t[3]),
                Double.parseDouble(t[4].isEmpty() ? "NaN" : t[4]));
    }
}
