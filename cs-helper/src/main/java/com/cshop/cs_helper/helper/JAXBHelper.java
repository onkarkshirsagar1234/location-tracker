package com.cshop.cs_helper.helper;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.MarshalException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public abstract class JAXBHelper {

	/**
	 * Marshalling itself to XML document by JAXB annotations and serializing it to
	 * String
	 * 
	 * @return serialized XML document
	 * @throws MarshalException
	 */
	public String marshal() throws MarshalException {
		return marshal(this);
	}

	/**
	 * Unmarshalling itself from XML document by JAXB annotations
	 * 
	 * @param xml xml document serialized as String
	 * @return deserialized object
	 * @throws JAXBException
	 */
	public Object unmarshal(String xml) throws JAXBException {
		return unmarshal(xml, this.getClass());
	}

	/**
	 * Marshalling supplied object to XML document by JAXB annotations and
	 * serializing it to String
	 * 
	 * @param obj object to be marshalled
	 * @return serialized XML document
	 * @throws MarshalException
	 */
	public static String marshal(Object obj) throws MarshalException {
		try {
			StringWriter sw = new StringWriter();
			JAXBContext jc = JAXBContext.newInstance(obj.getClass());
			Marshaller m = jc.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			m.marshal(obj, sw);
			return sw.toString();
		} catch (JAXBException jbe) {
			throw new MarshalException("Error when marshalling " + obj.getClass().getCanonicalName(), jbe);
		}
	}

	/**
	 * Unmarshalling itself from XML document by JAXB annotations
	 * 
	 * @param xml   xml document serialized as String
	 * @param clazz Class to which shoud be object unmarshalled
	 * @return serialized XML document
	 * @throws JAXBException
	 */
	@SuppressWarnings("unchecked")
	public static <T> T unmarshal(String xml, Class<?> clazz) throws JAXBException {
		T genBean = null;
		JAXBContext jc = JAXBContext.newInstance(clazz);
		Unmarshaller u = jc.createUnmarshaller();
		genBean = (T) u.unmarshal(new StringReader(xml));
		return genBean;
	}

	public static String getTagValue(String xml, String tagName) {
		return xml.split("<" + tagName + ">")[1].split("</" + tagName + ">")[0];
	}

	public static String setTagValue(String inputXML, String nodeName, String nodeValue) {
		String sTemp1 = null;
		String sTemp2 = null;
		String sOutput = null;
		String sReturnCode = null;
		int iLoc = inputXML.indexOf("</" + nodeName + ">");
		if (iLoc > -1) {
			sTemp1 = inputXML.substring(0, iLoc);
			sTemp2 = inputXML.substring(iLoc);
			sOutput = sTemp1 + nodeValue + sTemp2;
		} else {

			sReturnCode = "5000";
			nodeName = "Error : " + sReturnCode + " Message : " + nodeName;
			nodeName = "0009" + nodeName
					+ ". Could not add Process Message. Also Error in input XML format. Please check.";
			sOutput = nodeName;
		}
		return sOutput;
	}

}
