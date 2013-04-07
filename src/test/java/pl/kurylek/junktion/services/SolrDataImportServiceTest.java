package pl.kurylek.junktion.services;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

import org.apache.solr.client.solrj.request.QueryRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import pl.kurylek.junktion.repositories.SolrRepository;

@RunWith(MockitoJUnitRunner.class)
public class SolrDataImportServiceTest {

    @Mock
    SolrRepository mockedSolrRepository;

    @InjectMocks
    SolrDataImportService solrDataImportService = new SolrDataImportService();

    @Test
    public void shouldRequestDataImport() {
	// when
	solrDataImportService.requestDataImport();

	// then
	verify(mockedSolrRepository).request(any(QueryRequest.class));
    }
}