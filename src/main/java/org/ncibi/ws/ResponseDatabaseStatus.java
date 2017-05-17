package org.ncibi.ws;

public class ResponseDatabaseStatus {
	
	private final boolean connected;
	private final String message;
	
	public ResponseDatabaseStatus(){
		this(true);
	}
			
	public ResponseDatabaseStatus(boolean connected) {
		this(connected,(connected?"connected - ok":"generic failure"));
	}

	public ResponseDatabaseStatus(boolean connected, String message) {
		this.connected = connected;
		this.message = message;
	}

	public boolean isConnected() {
		return connected;
	}

	public String getMessage() {
		return message;
	}
	
	@Override
	public String toString(){
		return "[connected = " + connected + ", message = " + message + "]";
	}

}
