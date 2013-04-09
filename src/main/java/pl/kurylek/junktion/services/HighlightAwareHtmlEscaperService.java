package pl.kurylek.junktion.services;

import static org.apache.commons.lang3.StringEscapeUtils.escapeHtml4;
import static pl.kurylek.utils.nullsafe.NullSafeUtils.nullSafeToString;

import org.springframework.stereotype.Service;

@Service
public class HighlightAwareHtmlEscaperService {

    public static final String HIGHLIGHT_SIMPLE_TAG_PRE = "@@@hl@@@";
    public static final String HIGHLIGHT_HTML_TAG_PRE = "<em class='highlight'>";
    public static final String HIGHLIGHT_SIMPLE_TAG_POST = "@@@endhl@@@";
    public static final String HIGHLIGHT_HTML_TAG_POST = "</em>";

    public String escape(String string) {
	return escapeHtml4(nullSafeToString(string));
    }

    public String escapeRespectingHighlighting(String string) {
	return escape(string).replaceAll(HIGHLIGHT_SIMPLE_TAG_PRE, HIGHLIGHT_HTML_TAG_PRE).replaceAll(
		HIGHLIGHT_SIMPLE_TAG_POST, HIGHLIGHT_HTML_TAG_POST);
    }
}
