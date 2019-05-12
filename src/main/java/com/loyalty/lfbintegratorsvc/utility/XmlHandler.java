package com.loyalty.lfbintegratorsvc.utility;

import com.loyalty.lfbintegratorsvc.logging.LyfLogger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;

public final class XmlHandler {
	private XmlHandler() {
		
	}
	public static final String converObjectToXml(Object object) {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(object.getClass());
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			StringWriter stringWriter = new StringWriter();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.marshal(object, stringWriter);
			return stringWriter.toString();
		}
		catch (JAXBException e) {
			LyfLogger.logGeneralError(e, "Exception while converting object to xml");
			return null;
		}
	}
}
