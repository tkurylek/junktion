package pl.kurylek.junktion.services.exceptions;

@SuppressWarnings("serial")
public class FormattedDateParsingException extends RuntimeException {

    public FormattedDateParsingException(String formattedDate, Throwable e) {
	super("Could not parse formatted date: \"" + formattedDate + "\". Possible bad format.", e);
    }
}