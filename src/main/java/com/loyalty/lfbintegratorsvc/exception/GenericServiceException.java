package com.loyalty.lfbintegratorsvc.exception;

import java.io.PrintStream;
import java.io.PrintWriter;

public class GenericServiceException extends Exception{
	private static final long serialVersionUID = 5975812681516237598L;
	private String mensaje;
	private Exception ex;
	public GenericServiceException(String mensaje, Exception e) {
		super(e);
		this.mensaje = mensaje;
		this.ex = e;
	}

	public GenericServiceException(Exception e) {
		this.ex = e;
	}
	public Exception getInnerException(){
		return ex;
	}

	@Override
	public String getMessage() {
		return this.mensaje;
	}

	@Override
	public String getLocalizedMessage() {
		return ex.getLocalizedMessage();
	}

	@Override
	public Throwable getCause() {
		return ex.getCause();
	}

	@Override
	public Throwable initCause(Throwable cause) {
		return ex.initCause(cause);
	}

	@Override
	public void printStackTrace() {
		ex.printStackTrace();
	}

	@Override
	public void printStackTrace(PrintStream s) {
		ex.printStackTrace(s);
	}

	@Override
	public void printStackTrace(PrintWriter s) {
		ex.printStackTrace(s);
	}

	@Override
	public StackTraceElement[] getStackTrace() {
		return ex.getStackTrace();
	}

	@Override
	public void setStackTrace(StackTraceElement[] stackTrace) {
		ex.setStackTrace(stackTrace);
	}

	@Override
	public boolean equals(Object obj) {
		return ex.equals(obj);
	}

	@Override
	public int hashCode() {
		return ex.hashCode();
	}
}
