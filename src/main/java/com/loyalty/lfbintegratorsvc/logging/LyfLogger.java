package com.loyalty.lfbintegratorsvc.logging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.loyalty.lfbintegratorsvc.exception.GenericServiceException;
import com.loyalty.lfbintegratorsvc.exception.ServiceExceptionWrapper;
import com.loyalty.lfbintegratorsvc.utility.XmlHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service("LyfLoggerBean")
public final class LyfLogger implements ILyfLogger {
	private static Logger log;

	@Autowired
	public LyfLogger() {
		LyfLogger.log = LoggerFactory.getLogger(getClass());
	}

	public void logError(GenericServiceException e, LocalDateTime creationDate, Object requestObject,
						 String lmNumber, String description) {
		logError(e.getInnerException(), creationDate, requestObject, lmNumber, description);
	}

	@Override
	public void logError(Exception e, LocalDateTime creationDate, Object requestObject,
			String lmNumber, String description) {
		if (MDCHandler.isSetSenderRequest()) {
			logError(e, creationDate, requestObject, lmNumber, description, MDCHandler.getSenderRequest());
			return;
		}
		String jsonRequestObject;
		if (requestObject != null) {
			jsonRequestObject = convertObjectToJson(requestObject);
		} else {
			jsonRequestObject = "";
		}
		log.error("request object:" + jsonRequestObject);
		log.error("exception for client: " + lmNumber + ", with stacktrace:\n");
		log.error(convertStackTraceToString(e));
	}

	@Override
	public void logError(Exception e, LocalDateTime creationDate, Object requestObject,
			String lmNumber, String description, Object endpointRequestObject) {
		String sentRequest = null;
		if(endpointRequestObject != null && endpointRequestObject.getClass() != String.class) {
			sentRequest = XmlHandler.converObjectToXml(endpointRequestObject);
		}
		if (MDCHandler.isSetSenderRequest() && endpointRequestObject == null) {
			sentRequest = MDCHandler.getSenderRequest();
		}
		String jsonRequestObject;
		if (requestObject != null) {
			jsonRequestObject = convertObjectToJson(requestObject);
		} else {
			jsonRequestObject = "";
		}
		log.error("request object:" + jsonRequestObject);
		log.error("sent requestObject:" + sentRequest != null ? sentRequest : "");
		log.error("request endpoint sent object:" + endpointRequestObject);
		log.error(convertStackTraceToString(e));
		if (MDCHandler.isSetSenderRequest()) {
			MDCHandler.clearSenderRequest();
		}
	}

	public static void logGeneralError(Exception e, String description) {
		log.info(description + " with stacktrace:");
		log.error(convertStackTraceToString(e));
	}

	public void logError(String serviceName, ServiceExceptionWrapper e, LocalDateTime creationDate,
						 Object requestObject, String lmNumber, String description) {
		logError(e.getInnerException(), creationDate, requestObject, lmNumber, description);
	}

	private static String convertObjectToJson(Object requestObject) {

		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.writeValueAsString(requestObject);
		} catch (JsonProcessingException e) {
			log.error(convertStackTraceToString(e));
			return null;
		}
	}
	private static String convertStackTraceToString(Exception e) {
		StackTraceElement[] inputStackTrace = e.getStackTrace();
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("cause: " + e.getCause() + "\n");
		stringBuilder.append(e.getMessage());
		for(int x = 0; x < inputStackTrace.length; x++) {
			stringBuilder.append("at " + inputStackTrace[x].toString() + "\n");
		}
		String sStackTrace = stringBuilder.toString();
		return sStackTrace;
	}
}
