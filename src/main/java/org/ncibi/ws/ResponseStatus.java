package org.ncibi.ws;

import java.util.Collections;
import java.util.Map;

public final class ResponseStatus
{
    private final Map<String, String> args;
    private final boolean success;
    private final String message;
    private final ResponseStatusType type;
    private ResponseHttpStatus httpStatus;
    private ResponseDatabaseStatus databaseStatus;
    
    public ResponseStatus(Map<String, String> args, boolean success, String message){
    	this(args,success, null, message);
    }
    
    public ResponseStatus(Map<String, String> args, boolean success, ResponseStatusType type, String message)
    {
        if (argsIsEmpty(args))
        {
            this.args = Collections.emptyMap();
        }
        else
        {
            this.args = args;
        }
        if (type == null) type = typeBasedOnFlag(success);
        if (message == null) message = "";
        
        this.type = type;
        this.success = success;
        this.message = message;
    }
    
    private ResponseStatusType typeBasedOnFlag(boolean success2)
    {
    	return (success)?(ResponseStatusType.OK):(ResponseStatusType.GENERIC_ERROR);
	}

	private boolean argsIsEmpty(Map<String, String> args)
    {
        return args == null || args.size() == 0;
    }

    public Map<String, String> getArgs()
    {
        return this.args;
    }

    public boolean isSuccess()
    {
        return this.success;
    }

	public ResponseStatusType getType() {
		return type;
	}

    public String getMessage()
    {
        return this.message;
    }
    
    public ResponseHttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(ResponseHttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

	public ResponseDatabaseStatus getDatabaseStatus() {
		return databaseStatus;
	}

	public void setDatabaseStatus(ResponseDatabaseStatus databaseStatus) {
		this.databaseStatus = databaseStatus;
	}

	/* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return "ResponseStatus [args=" + this.args + ", success=" + this.success + ", " +
        		"error type = " + type + ", message=" + this.message + ", " +
        		"httpStatus = " + httpStatus + ", databaseStatus = " + databaseStatus + "]";
    }
}
