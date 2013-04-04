package pl.kurylek.junktion.repositories;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.request.QueryRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import pl.kurylek.junktion.builders.SolrQueryBuilder;
import pl.kurylek.junktion.repositories.exceptions.SolrRepositoryException;

@RunWith(MockitoJUnitRunner.class)
public class SolrRepositoryTest {

    @Mock
    SolrServer mockedSolrServer;

    @InjectMocks
    SolrRepository solrRepository = new SolrRepository();

    @Test
    public void shouldThrowSolrRepositoryExceptionWhenQueryingUnreachableServer() throws Exception {
	// given
	SolrQuery query = SolrQueryBuilder.aQuery().build();
	given(mockedSolrServer.query(query)).willThrow(new SolrServerException(""));

	// when
	SolrRepositoryException caughtException = null;
	try {
	    solrRepository.query(query);
	} catch (SolrRepositoryException e) {
	    caughtException = e;
	}
	// then
	assertThat(caughtException).isNotNull();
    }

    @Test
    public void shouldThrowSolrRepositoryExceptionWhenRequestingUnreachableServer() throws Exception {
	// given
	QueryRequest queryRequest = new QueryRequest();
	given(mockedSolrServer.request(queryRequest)).willThrow(new SolrServerException(""));

	// when
	SolrRepositoryException caughtException = null;
	try {
	    solrRepository.request(queryRequest);
	} catch (SolrRepositoryException e) {
	    caughtException = e;
	}
	// then
	assertThat(caughtException).isNotNull();
    }
}