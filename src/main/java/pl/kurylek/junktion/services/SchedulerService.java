package pl.kurylek.junktion.services;

import static org.slf4j.LoggerFactory.getLogger;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class SchedulerService {

    private static final String EVERY_SIX_HOURS_CRON_EXPRESSION = "0 0 */6 * * ?";
    final Logger logger = getLogger(getClass());
    @Autowired
    private SolrDataImportService solrDataImportService;

    @Scheduled(cron = EVERY_SIX_HOURS_CRON_EXPRESSION)
    public void doScheduledRequestForDataImport() {
	logger.info("Invoking service for data import as scheduled.");
	solrDataImportService.requestDataImport();
    }
}