package pl.kurylek.junktion.services;

import static org.fest.assertions.Assertions.assertThat;

import java.util.Date;

import org.junit.Test;

public class DateFormatterServiceTest {

    DateFormatterService dateFormatterService = new DateFormatterService();

    @Test
    public void shouldFormatDate() {
	// given
	Date date = new Date(0L);
	String formattedDate = "01.01.1970 01:00";

	// when
	String result = dateFormatterService.format(date);

	// then
	assertThat(result).isEqualTo(formattedDate);
    }
}