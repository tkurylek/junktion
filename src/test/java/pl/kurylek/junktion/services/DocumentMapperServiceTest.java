package pl.kurylek.junktion.services;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Maps.newHashMap;
import static org.fest.assertions.Assertions.assertThat;
import static pl.kurylek.junktion.domain.Document.CONTENT_FIELD;
import static pl.kurylek.junktion.test.builders.DocumentProfiledBuilder.aDefaultDocument;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import pl.kurylek.junktion.domain.Document;
import pl.kurylek.junktion.snapshots.DocumentSnaphot;
import pl.kurylek.junktion.test.integration.solr.SolrIntegrationTestBase;

public class DocumentMapperServiceTest extends SolrIntegrationTestBase {

    private static final String ANY_ID = "default-id.doc";
    private static final long ANY_SIZE = 1L;
    private static final Date LAST_MODIFICATION_DATE = new Date(1L);
    private static final String FORMATTED_LAST_MODIFICATION_DATE = "01.01.1970 01:00";
    private static final String AUTHOR_WITH_HTML = "<a>Dr Leonard Leakey Hofstadter</a>";
    private static final String AUTHOR_ESCAPED = "&lt;a&gt;Dr Leonard Leakey Hofstadter&lt;/a&gt;";
    private static final String FILENAME_WITH_HTML = "<script>alert('filename')</script>";
    private static final String FILENAME_ESCAPED = "&lt;script&gt;alert('filename')&lt;/script&gt;";
    private static final String PATH_WITH_HTML = "path/to/file<script>";
    private static final String PATH_ESCAPED = "path/to/file&lt;script&gt;";
    private static final String TITLE_WITH_HTML = "<b>title</b>";
    private static final String TITLE_ESCAPED = "&lt;b&gt;title&lt;/b&gt;";
    private static final String HIGHLIGHT_WITH_HTML = "<b>Lorem ipsum</b>";
    private static final String HIGHLIGHT_ESCAPED = "&lt;b&gt;Lorem ipsum&lt;/b&gt;";

    @Autowired
    DocumentMapperService documentMapperService;

    @Test
    public void shouldMapDocumentEscapingHtml() {
	// given
	Map<String, Map<String, List<String>>> emptyHighlightings = newHashMap();
	Document documentWithHtmlFields = aDefaultDocument().withTitle(TITLE_WITH_HTML)
		.withModified(LAST_MODIFICATION_DATE).withPath(PATH_WITH_HTML)
		.withFilename(FILENAME_WITH_HTML).withSize(ANY_SIZE).withAuthor(AUTHOR_WITH_HTML).build();

	// when
	DocumentSnaphot result = documentMapperService.map(documentWithHtmlFields, emptyHighlightings);

	// then
	assertThat(result.getTitle()).isEqualTo(TITLE_ESCAPED);
	assertThat(result.getModified()).isEqualTo(FORMATTED_LAST_MODIFICATION_DATE);
	assertThat(result.getPath()).isEqualTo(PATH_ESCAPED);
	assertThat(result.getFilename()).isEqualTo(FILENAME_ESCAPED);
	assertThat(result.getSize()).isEqualTo(ANY_SIZE);
	assertThat(result.getAuthor()).isEqualTo(AUTHOR_ESCAPED);
    }

    @Test
    public void shouldMapDocumentWithHighlighting() {
	// given
	Document document = aDefaultDocument().withId(ANY_ID).build();
	Map<String, Map<String, List<String>>> highlightings = newHashMap();
	Map<String, List<String>> highlightingsForDocument = newHashMap();
	List<String> highlightingsForDocumentContentField = newArrayList();
	highlightingsForDocumentContentField.add(HIGHLIGHT_WITH_HTML);
	highlightingsForDocument.put(CONTENT_FIELD, highlightingsForDocumentContentField);
	highlightings.put(ANY_ID, highlightingsForDocument);

	// when
	DocumentSnaphot result = documentMapperService.map(document, highlightings);

	// then
	assertThat(result.getHighlights()).containsOnly(HIGHLIGHT_ESCAPED);
    }
}