package pl.kurylek.junktion.services;

import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SchedulerServiceTest {

    @Mock
    SolrDataImportService mockedSolrDataImportService;

    @InjectMocks
    SchedulerService schedulerService = new SchedulerService();

    @Test
    public void shouldDoScheduledRequestForDataImport() {
	// when
	schedulerService.doScheduledRequestForDataImport();

	// then
	verify(mockedSolrDataImportService).requestDataImport();
    }
}