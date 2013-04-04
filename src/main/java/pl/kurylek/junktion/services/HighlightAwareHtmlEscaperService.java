package pl.kurylek.junktion.services;

import static org.apache.commons.lang3.StringEscapeUtils.escapeHtml4;
import static pl.kurylek.utils.NullSafeUtils.nullSafeToString;

import org.springframework.stereotype.Service;

@Service
public class HighlightAwareHtmlEscaperService {

    public static final String HL_SIMPLE_PRE = "@@@hl@@@";
    public static final String HL_SIMPLE_PRE_HTML = "<em class='highlight'>";
    public static final String HL_SIMPLE_POST = "@@@endhl@@@";
    public static final String HL_SIMPLE_POST_HTML = "</em>";

    public String escape(String string) {
	return escapeHtml4(nullSafeToString(string));
    }

    public String escapeRespectingHighlighting(String string) {
	return escape(string).replaceAll(HL_SIMPLE_PRE, HL_SIMPLE_PRE_HTML).replaceAll(HL_SIMPLE_POST,
		HL_SIMPLE_POST_HTML);
    }
}
