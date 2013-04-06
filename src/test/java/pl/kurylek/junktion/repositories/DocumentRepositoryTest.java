package pl.kurylek.junktion.repositories;

import static org.fest.assertions.Assertions.assertThat;
import static pl.kurylek.junktion.test.builders.DocumentBuilder.aDocument;

import org.apache.solr.client.solrj.response.QueryResponse;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import pl.kurylek.junktion.domain.Document;
import pl.kurylek.junktion.test.integration.solr.SolrIntegrationTestBase;

public class DocumentRepositoryTest extends SolrIntegrationTestBase {

    private static final String MATCHING_DOCUMENT_ID = "documentMatching.pdf";
    private static final String NOT_MATCHING_DOCUMENT_ID = "documentNotMatching.pdf";

    @Autowired
    DocumentRepository documentRepository;

    @Test
    public void shouldQueryByContentOrAuthorOrTitleOrPathReturningDocumentMatchingByContent() {
	// given
	Document documentMatchingByContent = aDocument().empty().withId(MATCHING_DOCUMENT_ID)
		.withContent("The theory states that the matter is built from strings.").build();
	Document documentNotMatching = aDocument().empty().withId(NOT_MATCHING_DOCUMENT_ID)
		.withContent("Not matching content").build();
	savedInRepository(documentMatchingByContent, documentNotMatching);

	// when
	QueryResponse result = documentRepository.queryByContentOrAuthorOrTitleOrPath("matter");

	// then
	assertThat(getDocuments(result)).containsOnly(documentMatchingByContent);
    }

    @Test
    public void shouldQueryByContentOrAuthorOrTitleOrPathReturningDocumentMatchingByAuthor() {
	// given
	Document documentMatchingByAuthor = aDocument().empty().withId(MATCHING_DOCUMENT_ID)
		.withAuthor("Dr Sheldon Cooper").build();
	Document documentNotMatching = aDocument().empty().withId(NOT_MATCHING_DOCUMENT_ID)
		.withAuthor("Not matching author").build();
	savedInRepository(documentMatchingByAuthor, documentNotMatching);

	// when
	QueryResponse result = documentRepository.queryByContentOrAuthorOrTitleOrPath("sheldon");

	// then
	assertThat(getDocuments(result)).containsOnly(documentMatchingByAuthor);
    }

    @Test
    public void shouldQueryByContentOrAuthorOrTitleOrPathReturningDocumentMatchingByTitle() {
	// given
	Document documentMatchingByTitle = aDocument().empty().withId(MATCHING_DOCUMENT_ID)
		.withTitle("Sheldon's black list").build();
	Document documentNotMatching = aDocument().empty().withId(NOT_MATCHING_DOCUMENT_ID)
		.withTitle("Not matching title").build();
	savedInRepository(documentMatchingByTitle, documentNotMatching);

	// when
	QueryResponse result = documentRepository.queryByContentOrAuthorOrTitleOrPath("black list");

	// then
	assertThat(getDocuments(result)).containsOnly(documentMatchingByTitle);
    }

    @Test
    public void shouldQueryByContentOrAuthorOrTitleOrPathReturningDocumentMatchingByPath() {
	// given
	Document documentMatchingByPath = aDocument().empty().withId(MATCHING_DOCUMENT_ID)
		.withPath("/home/sheldon/Documents/doc.pdf").build();
	Document documentNotMatching = aDocument().empty().withId(NOT_MATCHING_DOCUMENT_ID)
		.withPath("/not/matching/path").build();
	savedInRepository(documentMatchingByPath, documentNotMatching);

	// when
	QueryResponse result = documentRepository.queryByContentOrAuthorOrTitleOrPath("*/Documents*");

	// then
	assertThat(getDocuments(result)).containsOnly(documentMatchingByPath);
    }
}