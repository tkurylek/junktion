package pl.kurylek.junktion.test;

import org.apache.solr.client.solrj.SolrServer;
import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(locations = { "classpath:spring/database-context.xml", "classpath:spring/servlet-context.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("integration-test")
public class SolrIntegrationTestBase {

    @Autowired
    protected SolrServer solrServer;

    @After
    public void after() throws Exception {
	solrServer.deleteByQuery("*:*");
    }
}
