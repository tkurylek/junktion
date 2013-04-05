package pl.kurylek.junktion.services;

import static org.fest.assertions.Assertions.assertThat;
import static pl.kurylek.junktion.services.HighlightAwareHtmlEscaperService.HIGHLIGHT_SIMPLE_TAG_POST;
import static pl.kurylek.junktion.services.HighlightAwareHtmlEscaperService.HIGHLIGHT_HTML_TAG_POST;
import static pl.kurylek.junktion.services.HighlightAwareHtmlEscaperService.HIGHLIGHT_SIMPLE_TAG_PRE;
import static pl.kurylek.junktion.services.HighlightAwareHtmlEscaperService.HIGHLIGHT_HTML_TAG_PRE;

import org.junit.Test;

public class HighlightAwareHtmlEscaperServiceTest {

    HighlightAwareHtmlEscaperService highlightAwareHtmlEscaperService = new HighlightAwareHtmlEscaperService();

    @Test
    public void shouldEscapeNullString() {
	// given
	String nullString = null;

	// when
	String result = highlightAwareHtmlEscaperService.escape(nullString);

	// then
	assertThat(result).isEmpty();
    }

    @Test
    public void shouldEscapeHtml() {
	// given
	String textWithHtml = "<script>document.location='google.pl'</script>";
	String textEscaped = "&lt;script&gt;document.location='google.pl'&lt;/script&gt;";

	// when
	String result = highlightAwareHtmlEscaperService.escape(textWithHtml);

	// then
	assertThat(result).isEqualTo(textEscaped);
    }

    @Test
    public void shouldEscapeRespectingHighlighting() {
	// given
	String textWithHighlight = HIGHLIGHT_SIMPLE_TAG_PRE + "Integral" + HIGHLIGHT_SIMPLE_TAG_POST
		+ " of <i>dx</i> equals x";
	String ecapedTextWithHighlight = HIGHLIGHT_HTML_TAG_PRE + "Integral" + HIGHLIGHT_HTML_TAG_POST
		+ " of &lt;i&gt;dx&lt;/i&gt; equals x";

	// when
	String result = highlightAwareHtmlEscaperService.escapeRespectingHighlighting(textWithHighlight);

	// then
	assertThat(result).isEqualTo(ecapedTextWithHighlight);
    }
}