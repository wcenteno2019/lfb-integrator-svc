package com.loyalty.lfbintegratorsvc.exception;


public class ServiceExceptionWrapper extends Exception{
	private static final long serialVersionUID = -4936366940961026011L;
	transient Object additionalResponseObject;
	String description;
	Exception innerException;
	
	public ServiceExceptionWrapper(Exception e, Object additionalResponseObject, String description) {
		this.innerException = e;
		this.additionalResponseObject = additionalResponseObject;
		this.description = description;
	}
	public Object getAdditionalResponseObject() {
		return additionalResponseObject;
	}

	public void setAdditionalResponseObject(Object additionalResponseObject) {
		this.additionalResponseObject = additionalResponseObject;
	}

	public Exception getInnerException() {
		return innerException;
	}

	public void setInnerException(Exception innerException) {
		this.innerException = innerException;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}

