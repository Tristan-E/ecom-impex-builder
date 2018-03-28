package com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.export;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

/**
 * @author teyma
 * @since 27/03/18.
 */
public class ImpexPrinterTests {

    @InjectMocks
    private ImpexPrinter impexPrinter;

    private static final String TO_PRINT = "RPU is soooo cool ... Or not.";
    private static final String RECORD_SEPARATOR = "\r\n";
    private static final String SEPARATOR = ";";

    @Test
    public void testCloseShouldCallClose() throws IOException {
        final Writer writer = Mockito.mock(Writer.class);
        final ImpexPrinter impexPrinter = new ImpexPrinter(writer);
        impexPrinter.close();
        Mockito.verify(writer, Mockito.times(1)).close();
    }

    @Test
    public void testFlushShouldCallFlush() throws IOException {
        final Writer writer = Mockito.mock(Writer.class);
        final ImpexPrinter impexPrinter = new ImpexPrinter(writer);
        impexPrinter.flush();
        Mockito.verify(writer, Mockito.times(1)).flush();
    }

    @Test
    public void testPrintln() throws IOException {
        final StringWriter sw = new StringWriter();
        try (final ImpexPrinter printer = new ImpexPrinter(sw)) {
            printer.println();
        }
        Assertions.assertThat(RECORD_SEPARATOR).isEqualTo(sw.toString());
    }

    @Test
    public void testPrintlnWithText() throws IOException {
        final StringWriter sw = new StringWriter();
        try (final ImpexPrinter printer = new ImpexPrinter(sw)) {
            printer.println(TO_PRINT);
        }
        Assertions.assertThat(TO_PRINT + RECORD_SEPARATOR).isEqualTo(sw.toString());
    }

    @Test
    public void testPrintRecord() throws IOException {
        final StringWriter sw = new StringWriter();
        try (final ImpexPrinter printer = new ImpexPrinter(sw)) {
            printer.printRecord(TO_PRINT, TO_PRINT, TO_PRINT);
        }
        Assertions.assertThat(SEPARATOR + TO_PRINT + SEPARATOR  + TO_PRINT + SEPARATOR + TO_PRINT + SEPARATOR + RECORD_SEPARATOR).isEqualTo(sw.toString());
    }
}
