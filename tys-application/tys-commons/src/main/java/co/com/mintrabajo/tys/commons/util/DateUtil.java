package co.com.mintrabajo.tys.commons.util;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

public class DateUtil {

	public static XMLGregorianCalendar toXmlDate(final Date date) throws DatatypeConfigurationException {
		GregorianCalendar c = new GregorianCalendar();
		c.setTime(date);
		XMLGregorianCalendar xmlDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
		return xmlDate;
	}

	public static XMLGregorianCalendar getCurrentDateAsXmlDate() throws DatatypeConfigurationException {
		return toXmlDate(new Date());
	}

	public static String getDateToString(Date date) {
		Format formatter = new SimpleDateFormat("yyyy-MM-dd");
		String f = formatter.format(date);
		return f;

	}
	public static String getDateToStringNot(Date date) {
		Format formatter = new SimpleDateFormat("dd/MM/yyyy");
		String f = formatter.format(date);
		return f;

	}
	
	public static String getDateHourToString(Date date) {
		Format formatter = new SimpleDateFormat("HH:mm:ss");
		String f = formatter.format(date);
		return f;

	}

}
