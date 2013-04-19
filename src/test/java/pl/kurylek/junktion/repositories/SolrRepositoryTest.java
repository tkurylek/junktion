package pl.kurylek.junktion.repositories;

import static org.mockito.BDDMockito.given;
import static pl.kurylek.junktion.builders.SolrQueryBuilder.aQuery;
import static pl.kurylek.utils.tests.assertion.ThrowableAssertions.assertThrowable;
import static pl.kurylek.utils.tests.catcher.ExceptionCatcher.tryToCatch;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.request.QueryRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import pl.kurylek.junktion.repositories.exceptions.SolrRepositoryException;
import pl.kurylek.utils.tests.catcher.ExceptionalOperation;

@RunWith(MockitoJUnitRunner.class)
public class SolrRepositoryTest {

    private static final String ANY_EXCEPTION_MESSAGE = "";

    @Mock
    SolrServer mockedSolrServer;

    @InjectMocks
    SolrRepository solrRepository = new SolrRepository();

    @Test
    public void shouldThrowSolrRepositoryExceptionWhenQueryingUnreachableServer() throws Exception {
	// given
	final SolrQuery query = aQuery().build();
	SolrServerException solrServerException = new SolrServerException(ANY_EXCEPTION_MESSAGE);
	given(mockedSolrServer.query(query)).willThrow(solrServerException);

	// when
	SolrRepositoryException caughtException = tryToCatch(SolrRepositoryException.class,
		new ExceptionalOperation() {

		    @Override
		    public void operate() throws Exception {
			solrRepository.query(query);
		    }
		});

	// then
	assertThrowable(caughtException).isThrown();
    }

    @Test
    public void shouldThrowSolrRepositoryExceptionWhenRequestingUnreachableServer() throws Exception {
	// given
	final QueryRequest queryRequest = new QueryRequest();
	SolrServerException solrServerException = new SolrServerException(ANY_EXCEPTION_MESSAGE);
	given(mockedSolrServer.request(queryRequest)).willThrow(solrServerException);

	// when
	SolrRepositoryException caughtException = tryToCatch(SolrRepositoryException.class,
		new ExceptionalOperation() {

		    @Override
		    public void operate() throws Exception {
			solrRepository.request(queryRequest);
		    }
		});

	// then
	assertThrowable(caughtException).isThrown();
    }
}