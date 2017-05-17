package org.ncibi.ws;

public final class Response<T>
{
    private final ResponseStatus responseStatus;
    private final T responseValue;
    
    public Response(ResponseStatus responseStatus, T responseValue)
    {
        this.responseStatus = responseStatus;
        this.responseValue = responseValue;
    }

    public ResponseStatus getResponseStatus()
    {
        return this.responseStatus;
    }

    public T getResponseValue()
    {
        return this.responseValue;
    }
    
    public boolean isSuccess()
    {
        return (responseStatus != null && responseStatus.isSuccess());
    }

    @Override
    public String toString()
    {
        return "Response [responseStatus=" + responseStatus + ", responseValue=" + responseValue
                + "]";
    }
}
