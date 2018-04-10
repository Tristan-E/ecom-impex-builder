package com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.export;

import com.google.common.collect.Iterables;
import org.springframework.util.Assert;

import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Inspired from org.apache.commons.csv.CSVPrinter
 * @author teyma
 * @since 22/03/2018
 */
public class ImpexPrinter implements Flushable, Closeable {
    private static final String RECORD_SEPARATOR = "\r\n";
    private static final String SEPARATOR = ";";
    private static final String COLLECTION_SEPARATOR = ",";

    private final Appendable out;

    public ImpexPrinter(Appendable out) {
        Assert.notNull(out, "out");
        this.out = out;
    }

    public void close() throws IOException {
        if(this.out instanceof Closeable) {
            ((Closeable)this.out).close();
        }
    }

    public void flush() throws IOException {
        if(this.out instanceof Flushable) {
            ((Flushable)this.out).flush();
        }
    }

    private void print(Object value) throws IOException {
        if (null != value) {
            out.append(value.toString());
            out.append(SEPARATOR);
        }
    }

    private void printCollection(Iterable<?> values) throws IOException {
        if (!Iterables.isEmpty(values)) {
            // Build a String with separator : value1,value2, ...
            String valuesToString = String.join(
                    COLLECTION_SEPARATOR,
                    StreamSupport.stream(values.spliterator(), false).map(o -> o.toString()).collect(Collectors.toList())
            );
            this.print(valuesToString);
        }
    }

    public void println(String value) throws IOException {
        out.append(value);
        this.println();
    }

    public void println() throws IOException {
        out.append(RECORD_SEPARATOR);
    }

    public void printRecord(Object... values) throws IOException {
        out.append(SEPARATOR);

        for (Object value : values) {
            if (value instanceof Iterable) {
                this.printCollection((Iterable)value);
            } else {
                this.print(value);
            }
        }

        this.println();
    }
}
