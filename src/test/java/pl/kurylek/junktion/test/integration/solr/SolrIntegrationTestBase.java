package pl.kurylek.junktion.test.integration.solr;

import org.apache.solr.client.solrj.SolrServer;
import org.junit.After;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.kurylek.junktion.domain.Document;

@ContextConfiguration(locations = { "classpath:spring/database-context.xml",
	"classpath:spring/servlet-context.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("integration-test")
@Ignore
public class SolrIntegrationTestBase {

    private static final String QUERY_MATCHING_ALL_DOCUMENTS = "*:*";
    @Autowired
    SolrServer solrServer;

    protected void savedInRepository(final Document document) {
	tryToPerformDatabaseOperation(new SolrTestInsanceOperation() {

	    @Override
	    public void operate() throws Exception {
		solrServer.addBean(document);
		solrServer.commit();
	    }
	});
    }

    protected void savedInRepository(final Document... documents) {
	tryToPerformDatabaseOperation(new SolrTestInsanceOperation() {

	    @Override
	    public void operate() throws Exception {
		for (Document document : documents) {
		    solrServer.addBean(document);
		}
		solrServer.commit();
	    }
	});
    }

    private void tryToPerformDatabaseOperation(SolrTestInsanceOperation solrTestInsanceOperation) {
	try {
	    solrTestInsanceOperation.operate();
	} catch (Exception e) {
	    throw new SolrTestInsanceException(e);
	}
    }

    @After
    public void after() throws Exception {
	solrServer.deleteByQuery(QUERY_MATCHING_ALL_DOCUMENTS);
    }
}