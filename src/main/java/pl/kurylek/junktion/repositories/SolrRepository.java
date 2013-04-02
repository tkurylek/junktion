package pl.kurylek.junktion.repositories;

import static org.slf4j.LoggerFactory.getLogger;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.request.QueryRequest;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.params.SolrParams;
import org.apache.solr.common.util.NamedList;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pl.kurylek.junktion.repositories.exceptions.SolrRepositoryException;

@Repository
public class SolrRepository {

    final Logger logger = getLogger(getClass());
    @Autowired
    private SolrServer solrServer;

    public QueryResponse query(SolrParams solrParams) {
	try {
	    return solrServer.query(solrParams);
	} catch (SolrServerException e) {
	    logger.error("Could not execute query with solrParams: " + solrParams, e);
	    throw new SolrRepositoryException(e);
	}
    }

    public NamedList<Object> request(QueryRequest request) {
	try {
	    return solrServer.request(request);
	} catch (SolrServerException | IOException e) {
	    logger.error("Could not execute request to " + request.getPath(), e);
	    throw new SolrRepositoryException(e);
	}
    }
}
