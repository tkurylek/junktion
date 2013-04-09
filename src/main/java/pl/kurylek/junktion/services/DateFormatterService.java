package pl.kurylek.junktion.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Service;

import pl.kurylek.junktion.services.exceptions.FormattedDateParsingException;

@Service
public class DateFormatterService {

    public static final String DATE_FORMAT = "dd.MM.yyyy hh:mm";
    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT);

    public String format(Date date) {
	return simpleDateFormat.format(date);
    }

    public Date getDate(String formattedDate) {
	try {
	    return simpleDateFormat.parse(formattedDate);
	} catch (ParseException e) {
	    throw new FormattedDateParsingException(formattedDate, e);
	}
    }
}