package pl.kurylek.junktion.services;

import static org.slf4j.LoggerFactory.getLogger;

import org.apache.solr.client.solrj.request.QueryRequest;
import org.apache.solr.common.params.ModifiableSolrParams;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.kurylek.junktion.repositories.SolrRepository;

@Service
public class SolrDataImportService {

    final Logger logger = getLogger(getClass());
    private static final String DATA_IMPORT_PATH = "/dataimport";
    private static final String FULL_IMPORT = "full-import";
    private static final String COMMAND_PARAM = "command";
    private final ModifiableSolrParams solrParams = new ModifiableSolrParams();
    private final QueryRequest queryRequest = new QueryRequest(solrParams);
    @Autowired
    private SolrRepository solrRepository;

    public void requestDataImport() {
	logger.info("Requesting full data import ...");
	solrParams.set(COMMAND_PARAM, FULL_IMPORT);
	queryRequest.setPath(DATA_IMPORT_PATH);
	solrRepository.request(queryRequest);
    }
}