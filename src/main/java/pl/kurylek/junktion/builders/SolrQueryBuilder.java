package pl.kurylek.junktion.builders;

import static org.apache.commons.lang3.StringUtils.join;

import org.apache.solr.client.solrj.SolrQuery;

public class SolrQueryBuilder {

    public static final String QUERY_EDISMAX = "edismax";
    public static final String QUERY_PARSER_TYPE_PARAMETER = "defType";
    public static final String QUERY_FIELDS_PARAMETER = "qf";
    public static final String MULTIPLE_QUERY_VALUES_SEPARATOR = " ";
    private final SolrQuery solrQuery;

    private SolrQueryBuilder() {
	solrQuery = new SolrQuery();
    }

    private SolrQueryBuilder(String query) {
	solrQuery = new SolrQuery(query);
    }

    public static SolrQueryBuilder aQuery() {
	return new SolrQueryBuilder();
    }

    public static SolrQueryBuilder aQuery(String query) {
	return new SolrQueryBuilder(query);
    }

    public SolrQueryBuilder withinFields(String... fields) {
	solrQuery.set(QUERY_FIELDS_PARAMETER, join(fields, MULTIPLE_QUERY_VALUES_SEPARATOR));
	return this;
    }

    public SolrQueryBuilder withExtendedDisjunctionMax() {
	return withQueryParser(QUERY_EDISMAX);
    }

    public SolrQueryBuilder withQueryParser(String queryParser) {
	solrQuery.set(QUERY_PARSER_TYPE_PARAMETER, queryParser);
	return this;
    }

    public SolrQueryBuilder withEnabledHighlighting() {
	solrQuery.setHighlight(true);
	return this;
    }

    public SolrQueryBuilder withHighlightedSnippets(int quantity) {
	solrQuery.setHighlightSnippets(quantity);
	return this;
    }

    public SolrQuery build() {
	return solrQuery;
    }
}
