package org.ncibi.ws;

import java.beans.DefaultPersistenceDelegate;
import java.beans.XMLEncoder;

public abstract class AbstractBeanXMLResponseEncoder2<T> extends AbstractBeanXMLEncoder<T>
{
    protected abstract void setupPersistenceDelegatesForResponseValue(XMLEncoder encoder);
    
    public AbstractBeanXMLResponseEncoder2()
    {
        super();
    }
    
    protected void setupPersistenceDelegatesForType(XMLEncoder encoder)
    {
        setupPersistenceDelegatesForResponse(encoder);
        setupPersistenceDelegatesForResponseStatus(encoder);
        setupPersistenceDelegatesForResponseValue(encoder);
    }
    
    private void setupPersistenceDelegatesForResponse(XMLEncoder encoder)
    {
        final String[] responsePersistenceFields = new String[] { "responseStatus", "responseValue" };
        encoder.setPersistenceDelegate(Response.class, new DefaultPersistenceDelegate(
                    responsePersistenceFields));
    }

    private void setupPersistenceDelegatesForResponseStatus(XMLEncoder encoder)
    {
        final String[] responseStatusPersistenceFields = new String[] { "args", "success",
                "message" };
        encoder.setPersistenceDelegate(ResponseStatus.class, new DefaultPersistenceDelegate(
                    responseStatusPersistenceFields));
    }
}