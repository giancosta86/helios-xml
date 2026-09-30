package info.gianlucacosta.helios.serialization.xml;

import com.thoughtworks.xstream.XStream;

/**
 * Provides the options required to instantiate the XStream-based serialization
 * service and its contexts - for example, how to create the underlying XStream
 * object itself, or if the XML is to be compressed.
 */
public interface XStreamSerializationOptions {

    XStream createXStream();

    boolean isCompressed();

}
