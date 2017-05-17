package org.ncibi.ws;

public class ResponseHttpStatus {
	
	private final int statusCode;
	private final String statusReason;

	public ResponseHttpStatus(int statusCode, String statusReason) {
		this.statusCode = statusCode;
		this.statusReason = statusReason;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public String getStatusReason() {
		return statusReason;
	}

	@Override
	public String toString(){
		return "[statusCode = " + statusCode + ", statusReason = " + statusReason + "]";
	}
}
