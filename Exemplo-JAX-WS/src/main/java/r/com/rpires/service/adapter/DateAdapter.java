/**
 * 
 */
package r.com.rpires.service.adapter;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * @author rpires
 *
 */
public class DateAdapter extends XmlAdapter<String, Date> {

	private String pattern = "dd/MM/yyyy";

	public DateAdapter() {
	}

	public Date unmarshal(String dateString) throws Exception {
		return new SimpleDateFormat(pattern).parse(dateString);
	}

	public String marshal(Date date) throws Exception {
		return new SimpleDateFormat(pattern).format(date);
	}

}
