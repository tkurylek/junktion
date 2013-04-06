package pl.kurylek.junktion.test.integration.solr.selfTest;

import static org.fest.assertions.Assertions.assertThat;
import static pl.kurylek.junktion.test.builders.DocumentProfiledBuilder.aDefaultDocument;
import static pl.kurylek.junktion.test.builders.DocumentProfiledBuilder.aStringTheoryBySheldonCooperDocument;

import org.apache.solr.client.solrj.SolrQuery;
import org.junit.Test;

import pl.kurylek.junktion.domain.Document;
import pl.kurylek.junktion.test.integration.solr.SolrIntegrationTestBase;

public class SolrIntegrationTestBaseSelfTest extends SolrIntegrationTestBase {

    private static final SolrQuery SOLR_QUERY_MATCHING_ALL_DOCUMENTS = new SolrQuery(
	    QUERY_MATCHING_ALL_DOCUMENTS);

    @Test
    public void shouldSaveInRepositoryOneDocument() throws Exception {
	// given
	Document document = aDefaultDocument().build();

	// when
	savedInRepository(document);

	// then
	assertThat(getDocuments(solrServer.query(SOLR_QUERY_MATCHING_ALL_DOCUMENTS))).containsOnly(document);
    }

    @Test
    public void shouldSaveInRepositoryMultipleDocuments() throws Exception {
	// given
	Document defaultDocument = aDefaultDocument().build();
	Document stringTheoryDocument = aStringTheoryBySheldonCooperDocument().build();

	// when
	savedInRepository(defaultDocument, stringTheoryDocument);

	// then
	assertThat(getDocuments(solrServer.query(SOLR_QUERY_MATCHING_ALL_DOCUMENTS))).containsOnly(
		defaultDocument, stringTheoryDocument);
    }
}
