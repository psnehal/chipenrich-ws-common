package org.ncibi.ws;

import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public abstract class AbstractBeanXMLEncoder<T>
{
    protected abstract void setupPersistenceDelegatesForType(XMLEncoder encoder);
    
    private ByteArrayOutputStream out;
    private XMLEncoder encoder;
    private Object obj;
    
    public AbstractBeanXMLEncoder()
    {
        setupEncoder();
       
    }
    
    private void setupEncoder()
    {
        this.out = new ByteArrayOutputStream();
        this.encoder = new XMLEncoder(new BufferedOutputStream(this.out));
        setupPersistenceDelegatesForType(this.encoder);
    }
    
    public <O> void setObjectToEncode(O obj)
    {
        this.obj = obj;
    }
    
    public String toXmlString()
    {
        try
        {
            return encodeObjectAsXml();
        }
        catch (final IOException e)
        {
            throw new IllegalStateException("Unable to encode object: " + this.obj);
        }
    }
    
    private String encodeObjectAsXml() throws IOException
    {
        encodeObjectUsingEncoder();
        return retrieveXmlStringFromStream();
    }

    private void encodeObjectUsingEncoder()
    {
        this.encoder.writeObject(this.obj);
        this.encoder.close();
    }

    private String retrieveXmlStringFromStream() throws IOException
    {
        this.out.flush();
        final String xml = this.out.toString();
        this.out.close();
      //  System.out.println("inside retrieveXmlStringFromStream"+xml);
        return xml;
    }
}
