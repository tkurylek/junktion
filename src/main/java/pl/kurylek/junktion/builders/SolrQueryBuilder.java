package pl.kurylek.junktion.builders;

import static org.apache.commons.lang3.StringUtils.join;

import org.apache.solr.client.solrj.SolrQuery;

public class SolrQueryBuilder {

    private static final String BOOST_PREFIX = "^";
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

    public SolrQueryBuilder withNumberOfHighlightedSnippets(int quantity) {
	solrQuery.setHighlightSnippets(quantity);
	return this;
    }

    public SolrQueryBuilder withinField(String field) {
	solrQuery.set(QUERY_FIELDS_PARAMETER, field);
	return this;
    }

    public SolrQueryBuilder orWithinField(String field) {
	append(QUERY_FIELDS_PARAMETER, field);
	return this;
    }

    private void append(String name, String value) {
	if (solrQuery.get(name) != null) {
	    solrQuery.set(name, solrQuery.get(name) + " " + value);
	} else {
	    solrQuery.set(name, value);
	}
    }

    public SolrQueryBuilder withinBoostedField(String field, int boost) {
	solrQuery.set(QUERY_FIELDS_PARAMETER, field + BOOST_PREFIX + boost);
	return this;
    }

    public SolrQueryBuilder withinBoostedField(String field, String boost) {
	solrQuery.set(QUERY_FIELDS_PARAMETER, field + BOOST_PREFIX + boost);
	return this;
    }

    public SolrQueryBuilder orWithinBoostedField(String field, int boost) {
	append(QUERY_FIELDS_PARAMETER, field + BOOST_PREFIX + boost);
	return this;
    }

    public SolrQueryBuilder orWithinBoostedField(String field, String boost) {
	append(QUERY_FIELDS_PARAMETER, field + BOOST_PREFIX + boost);
	return this;
    }

    public SolrQueryBuilder wtihSkippingFirst(int skip) {
	solrQuery.setStart(skip);
	return this;
    }

    public SolrQuery build() {
	return solrQuery;
    }
}
