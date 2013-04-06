package pl.kurylek.junktion.loaders;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import pl.kurylek.junktion.loaders.exceptions.DocumentNotFoundException;
import pl.kurylek.junktion.repositories.DocumentRepository;

@RunWith(MockitoJUnitRunner.class)
public class DocumentLoaderTest {

    private static final String PHRASE = "theory of strings";
    private static final int NO_QUERY_SKIPPING = 0;

    @Mock
    QueryResponse mockedQueryResponse;

    @Mock
    DocumentRepository mockedDocumentRepository;

    @InjectMocks
    DocumentLoader documentLoader = new DocumentLoader();

    @Test
    public void shouldLoadDocument() {
	// given
	given(mockedQueryResponse.getResults()).willReturn(createNotEmptySolrDocumentList());
	given(mockedDocumentRepository.queryByContentOrAuthorOrTitleOrPath(PHRASE, NO_QUERY_SKIPPING))
		.willReturn(mockedQueryResponse);

	// when
	QueryResponse result = documentLoader.loadByContentOrAuthorOrTitleOrPath(PHRASE);

	// then
	assertThat(result).isEqualTo(mockedQueryResponse);
    }

    @Test
    public void shouldNotLoadDocumentBecauseThereIsNoMatchingOne() {
	// given
	given(mockedQueryResponse.getResults()).willReturn(createEmptySolrDocumentList());
	given(mockedDocumentRepository.queryByContentOrAuthorOrTitleOrPath(PHRASE, NO_QUERY_SKIPPING))
		.willReturn(mockedQueryResponse);

	// when
	DocumentNotFoundException caughtException = null;
	try {
	    documentLoader.loadByContentOrAuthorOrTitleOrPath(PHRASE);
	} catch (DocumentNotFoundException e) {
	    caughtException = e;
	}
	// then
	assertThat(caughtException).isNotNull();
    }

    private SolrDocumentList createNotEmptySolrDocumentList() {
	SolrDocumentList list = createEmptySolrDocumentList();
	list.add(new SolrDocument());
	return list;
    }

    private SolrDocumentList createEmptySolrDocumentList() {
	return new SolrDocumentList();
    }
}