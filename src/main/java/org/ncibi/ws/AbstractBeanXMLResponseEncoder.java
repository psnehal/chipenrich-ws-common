package org.ncibi.ws;

import java.beans.DefaultPersistenceDelegate;
import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public abstract class AbstractBeanXMLResponseEncoder<T>
{
    protected abstract void setupPersistenceDelegatesForResponseValue(XMLEncoder encoder);

    private ByteArrayOutputStream out;
    private XMLEncoder encoder;
    private Response<T> response;

    public AbstractBeanXMLResponseEncoder(final Response<T> response)
    {
        this.response = response;
        setupEncoder();
    }
    
    public AbstractBeanXMLResponseEncoder()
    {
        setupEncoder();
    }

    private void setupEncoder()
    {
        this.out = new ByteArrayOutputStream();
        this.encoder = new XMLEncoder(new BufferedOutputStream(this.out));
        setupPersistenceDelegatesForResponse();
        setupPersistenceDelegatesForResponseStatus();
        setupPersistenceDelegatesForResponseValue(this.encoder);
    }

    private void setupPersistenceDelegatesForResponse()
    {
        final String[] responsePersistenceFields = new String[] { "responseStatus", "responseValue" };
        this.encoder.setPersistenceDelegate(Response.class, new DefaultPersistenceDelegate(
                    responsePersistenceFields));
    }

    private void setupPersistenceDelegatesForResponseStatus()
    {
        final String[] responseStatusPersistenceFields = new String[] { "args", "success",
                "message" };
        this.encoder.setPersistenceDelegate(ResponseStatus.class, new DefaultPersistenceDelegate(
                    responseStatusPersistenceFields));
    }
    
    public void setResponse(Response<T> response)
    {
        this.response = response;
    }

    public String toXmlString()
    {
        try
        {
            return encodeResponseAsXml();
        }
        catch (final IOException e)
        {
            throw new IllegalStateException("Unable to encode name response: " + this.response);
        }
    }

    private String encodeResponseAsXml() throws IOException
    {
        encodeResponseUsingEncoder();
        return retrieveXmlStringFromStream();
    }

    private void encodeResponseUsingEncoder()
    {
        this.encoder.writeObject(this.response);
        this.encoder.close();
    }

    private String retrieveXmlStringFromStream() throws IOException
    {
        this.out.flush();
        final String xml = this.out.toString();
        this.out.close();
        return xml;
    }
}
