package com.github;

import org.apache.camel.dataformat.bindy.annotation.CsvRecord;
import org.apache.camel.dataformat.bindy.annotation.DataField;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: dan
 * Date: 12/3/13
 * Time: 12:50 AM
 * To change this template use File | Settings | File Templates.
 */
@CsvRecord(separator = ",", crlf = "UNIX")
public class Tick {
    @DataField(pos = 1)
    private String symbol;

    @DataField(pos = 2, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date created;

    @DataField(pos = 3)
    private long seqId;

    @DataField(pos = 4)
    private double price;

    @DataField(pos = 5)
    private double volume;

    @DataField(pos = 6)
    private String meta;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public long getSeqId() {
        return seqId;
    }

    public void setSeqId(long seqId) {
        this.seqId = seqId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public String getMeta() {
        return meta;
    }

    public void setMeta(String meta) {
        this.meta = meta;
    }
}
