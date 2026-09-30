package info.gianlucacosta.helios.serialization.xml;

import com.thoughtworks.xstream.XStream;
import info.gianlucacosta.helios.serialization.SavingContext;

import java.io.*;
import java.util.zip.GZIPOutputStream;

/**
 * Provides an OutputStream based on the XStream serialization library
 */
class XmlSavingContext implements SavingContext {

    private final XStream xStream;
    private final ObjectOutputStream outputStream;

    public XmlSavingContext(OutputStream outputStream, XStreamSerializationOptions serializationOptions) {
        xStream = serializationOptions.createXStream();
        try {
            if (serializationOptions.isCompressed()) {
                outputStream = new GZIPOutputStream(outputStream);
            }

            this.outputStream = xStream.createObjectOutputStream(new BufferedWriter(new OutputStreamWriter(outputStream)));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public ObjectOutputStream getOutputStream() {
        return outputStream;
    }

    @Override
    public void close() throws Exception {
        outputStream.close();
    }

}
