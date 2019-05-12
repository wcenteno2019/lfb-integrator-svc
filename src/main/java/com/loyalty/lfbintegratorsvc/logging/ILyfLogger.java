package com.loyalty.lfbintegratorsvc.logging;
import java.time.LocalDateTime;

public interface ILyfLogger {
	void logError(Exception e, LocalDateTime creationDate, Object requestObject,
                  String lmNumber, String description);
	void logError(Exception e, LocalDateTime creationDate, Object requestObject,
                  String lmNumber, String description, Object endpointRequestObject);
}
