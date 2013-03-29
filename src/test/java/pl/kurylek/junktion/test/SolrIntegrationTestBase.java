package pl.kurylek.junktion.test;

import org.apache.solr.client.solrj.SolrServer;
import org.junit.After;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.kurylek.junktion.domain.Document;

@ContextConfiguration(locations = { "classpath:spring/database-context.xml", "classpath:spring/servlet-context.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("integration-test")
@Ignore
public class SolrIntegrationTestBase {

    @Autowired
    SolrServer solrServer;

    protected void savedInRepository(Document document) throws Exception {
	solrServer.addBean(document);
	solrServer.commit();
    }

    @After
    public void after() throws Exception {
	solrServer.deleteByQuery("*:*");
    }
}
