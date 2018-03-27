package com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.export;

import org.springframework.util.Assert;

import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;
import java.util.Iterator;

/**
 * Inspired from org.apache.commons.csv.CSVPrinter
 * @author teyma
 * @since 22/03/2018
 */
public class ImpexPrinter implements Flushable, Closeable {
    private static final String RECORD_SEPARATOR = "\r\n";
    private static final String SEPARATOR = ";";
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
        Iterator var2 = values.iterator();

        while(var2.hasNext()) {
            Object value = var2.next();
            this.print(value);
        }
    }

    public void println(String value) throws IOException {
        out.append(value);
        this.println();
    }

    public void println() throws IOException {
        out.append(RECORD_SEPARATOR);
    }

    public void printRecord(Iterable<?> values) throws IOException {
        out.append(SEPARATOR);

        Iterator var2 = values.iterator();

        while(var2.hasNext()) {
            Object value = var2.next();
            this.print(value);
        }

        this.println();
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
