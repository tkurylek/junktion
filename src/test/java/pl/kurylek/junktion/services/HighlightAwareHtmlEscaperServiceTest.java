package pl.kurylek.junktion.services;

import static org.fest.assertions.Assertions.assertThat;
import static pl.kurylek.junktion.services.HighlightAwareHtmlEscaperService.HIGHLIGHT_TAG_POST;
import static pl.kurylek.junktion.services.HighlightAwareHtmlEscaperService.HIGHLIGHT_TAG_POST_HTML;
import static pl.kurylek.junktion.services.HighlightAwareHtmlEscaperService.HIGHLIGHT_TAG_PRE;
import static pl.kurylek.junktion.services.HighlightAwareHtmlEscaperService.HIGHLIGHT_TAG_PRE_HTML;

import org.junit.Test;

public class HighlightAwareHtmlEscaperServiceTest {

    HighlightAwareHtmlEscaperService highlightAwareHtmlEscaperService = new HighlightAwareHtmlEscaperService();

    @Test
    public void shouldEscapeNull() {
	// given
	String nullText = null;

	// when
	String result = highlightAwareHtmlEscaperService.escape(nullText);

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
	String textWithHighlight = HIGHLIGHT_TAG_PRE + "Integral" + HIGHLIGHT_TAG_POST
		+ " of <i>dx</i> equals x";
	String ecapedTextWithHighlight = HIGHLIGHT_TAG_PRE_HTML + "Integral" + HIGHLIGHT_TAG_POST_HTML
		+ " of &lt;i&gt;dx&lt;/i&gt; equals x";

	// when
	String result = highlightAwareHtmlEscaperService.escapeRespectingHighlighting(textWithHighlight);

	// then
	assertThat(result).isEqualTo(ecapedTextWithHighlight);
    }
}