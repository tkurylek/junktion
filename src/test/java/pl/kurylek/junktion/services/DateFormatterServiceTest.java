package pl.kurylek.junktion.services;

import static org.fest.assertions.Assertions.assertThat;
import static pl.kurylek.utils.tests.assertion.ThrowableAssertions.assertThrowable;
import static pl.kurylek.utils.tests.catcher.ExceptionCatcher.tryToCatch;

import java.util.Date;

import org.junit.Test;

import pl.kurylek.junktion.services.exceptions.FormattedDateParsingException;
import pl.kurylek.utils.tests.catcher.ExceptionalOperation;

public class DateFormatterServiceTest {

    DateFormatterService dateFormatterService = new DateFormatterService();

    @Test
    public void shouldFormatDate() {
	// given
	Date date = new Date(0L);
	String expectedFormattedDate = "01.01.1970 01:00";

	// when
	String result = dateFormatterService.format(date);

	// then
	assertThat(result).isEqualTo(expectedFormattedDate);
    }

    @Test
    public void shouldGetDateFromFormattedDate() {
	// given
	String formattedDate = "01.01.1970 01:00";
	Date expectedDate = new Date(0L);

	// when
	Date result = dateFormatterService.getDate(formattedDate);

	// then
	assertThat(result).isEqualTo(expectedDate);
    }

    @Test
    public void shouldThrowExceptionOnBadlyFormattedDate() {
	// given
	final String badlyFormattedDate = "01/01/1970 1:00am";

	// when
	FormattedDateParsingException caughtException = tryToCatch(FormattedDateParsingException.class,
		new ExceptionalOperation() {

		    @Override
		    public void operate() throws Exception {
			dateFormatterService.getDate(badlyFormattedDate);
		    }
		});

	// then
	assertThrowable(caughtException).isThrown();
    }
}