package com.github;

import org.apache.camel.Exchange;
import org.apache.camel.Expression;
import org.apache.camel.Message;
import org.apache.camel.component.file.GenericFile;

import java.io.*;

public class CsvTokenizer implements Expression {

    private InputStreamReader isr;

    private StringBuilder b = new StringBuilder(2000);
    @Override
    public <T> T evaluate(Exchange exchange, Class<T> type) {
        if (isr == null) {
            try {
                InputStream is = new FileInputStream
                        ((File)((GenericFile) exchange.getIn().getBody()).getBody());
                isr = new InputStreamReader(is);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

        b.setLength(0);
        boolean open = false;
        while (true) {
            try {
                int x = isr.read();
                if (x < 0) {
                    break;
                } else if ('"' == x) {
                    if (open) {
                        open = false;
                    } else {
                        open = true;
                    }
                    b.append((char)x);
                } else if ('\n' == x) {
                    if (open) {
                        b.append((char)x);
                    } else {
                        b.append((char)x);
                        break;
                    }
                } else {
                    b.append((char)x);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return (T)b.toString();
    }
}
