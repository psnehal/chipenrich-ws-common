package org.ncibi.ws;

import java.beans.XMLDecoder;
import java.io.ByteArrayInputStream;

public class BeanXMLResponseDecoder<T>
{
    private final XMLDecoder decoder;
    private final String xml;
    
    public BeanXMLResponseDecoder(final String xml)
    {
    	System.out.println("Inside BeanXMLResponseDecoder");
        this.xml = xml;
        this.decoder = createDecoderForXml();
    }
    
    private XMLDecoder createDecoderForXml()
    {
        return new XMLDecoder(new ByteArrayInputStream(xml.getBytes()));
    }
    
    public Response<T> fromXmlString()
    {
    	System.out.println("Inside fromXmlString");
        return responseObjectFromDecoder();
    } 
    
    private Response<T> responseObjectFromDecoder()
    {
        final Object o = retrieveResponseObjectFromDecoder();
        System.out.println("Inside retrieveResponseObjectFromDecoder");

        if (objectIsResponseInstance(o))
        {
            @SuppressWarnings("unchecked")
            Response<T> response = (Response<T>) o;
            return response;
        }

        throw new IllegalArgumentException("XML is not for a Response object");
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
    
    private boolean objectIsResponseInstance(Object o)
    {
        return (o instanceof Response<?>);
    }
}
