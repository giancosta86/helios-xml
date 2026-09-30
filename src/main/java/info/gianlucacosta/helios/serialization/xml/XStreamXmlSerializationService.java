package info.gianlucacosta.helios.serialization.xml;

import info.gianlucacosta.helios.serialization.LoadingContext;
import info.gianlucacosta.helios.serialization.SavingContext;
import info.gianlucacosta.helios.serialization.SerializationService;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * Creates serialization contexts based on XStream, to perform XML
 * serialization.
 */
public class XStreamXmlSerializationService implements SerializationService {

    private final XStreamSerializationOptions xStreamOptions;

    public XStreamXmlSerializationService(XStreamSerializationOptions xStreamOptions) {
        this.xStreamOptions = xStreamOptions;
    }

    @Override
    public LoadingContext createLoadingContext(InputStream inputStream) {
        return new XmlLoadingContext(inputStream, xStreamOptions);
    }

    @Override
    public SavingContext createSavingContext(OutputStream outputStream) {
        return new XmlSavingContext(outputStream, xStreamOptions);
    }

}
