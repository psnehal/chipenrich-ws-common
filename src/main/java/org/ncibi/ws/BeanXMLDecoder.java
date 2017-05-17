package org.ncibi.ws;

import java.beans.XMLDecoder;
import java.io.ByteArrayInputStream;

public class BeanXMLDecoder implements XmlDecoder
{
    private final XMLDecoder decoder;
    private final String xml;
    
    public BeanXMLDecoder(final String xml)
    {
        this.xml = xml;
        this.decoder = createDecoderForXml();
    }
    
    private XMLDecoder createDecoderForXml()
    {
        return new XMLDecoder(new ByteArrayInputStream(xml.getBytes()));
    }
    
    @Override
    public <T> T fromXml()
    {
    	final Object o = retrieveResponseObjectFromDecoder();
        
        @SuppressWarnings("unchecked")
        final T castedObject = (T) o;
        return castedObject;
    } 
    
    private Object retrieveResponseObjectFromDecoder()
    {
        Object o = null;
        try
        {
            o = decoder.readObject();
        }
        catch (Exception e)
        {
            throw new IllegalStateException("Unable to decode object.");
        }
        finally
        {
            decoder.close();  
        }
        return o;
    }
}
