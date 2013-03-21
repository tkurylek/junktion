package pl.kurylek.junktion.repositories;

import java.util.List;

import org.springframework.data.solr.repository.SolrCrudRepository;
import org.springframework.stereotype.Component;

import pl.kurylek.junktion.domain.Document;

@Component
public interface DocumentRepository extends SolrCrudRepository<Document, String> {

    List<Document> findByContent(String content);
}
