package info.gianlucacosta.helios.serialization.xml;

import com.thoughtworks.xstream.XStream;
import info.gianlucacosta.helios.serialization.LoadingContext;

import java.io.*;
import java.util.zip.GZIPInputStream;

/**
 * Provides an InputStream based on the XStream serialization library
 */
class XmlLoadingContext implements LoadingContext {

    private final XStream xStream;
    private final ObjectInputStream inputStream;

    public XmlLoadingContext(InputStream inputStream, XStreamSerializationOptions serializationOptions) {
        this.xStream = serializationOptions.createXStream();

        try {
            if (serializationOptions.isCompressed()) {
                inputStream = new GZIPInputStream(inputStream);
            }

            this.inputStream = xStream.createObjectInputStream(new BufferedReader(new InputStreamReader(inputStream)));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public ObjectInputStream getInputStream() {
        return inputStream;
    }

    @Override
    public ClassLoader getClassLoader() {
        return xStream.getClassLoader();
    }

    @Override
    public void setClassLoader(ClassLoader classLoader) {
        xStream.setClassLoader(classLoader);
    }

    @Override
    public void close() throws Exception {
        inputStream.close();
    }

}
