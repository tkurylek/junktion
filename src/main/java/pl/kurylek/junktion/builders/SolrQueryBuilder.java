package pl.kurylek.junktion.builders;

import static org.apache.commons.lang3.StringUtils.join;

import org.apache.solr.client.solrj.SolrQuery;

import pl.kurylek.utils.builder.Builder;

public class SolrQueryBuilder extends Builder<SolrQuery> {

    private static final String BOOST_PREFIX = "^";
    public static final String QUERY_EDISMAX = "edismax";
    public static final String QUERY_PARSER_TYPE_PARAMETER = "defType";
    public static final String QUERY_FIELDS_PARAMETER = "qf";
    public static final String MULTIPLE_QUERY_VALUES_SEPARATOR = " ";

    public SolrQueryBuilder() {
    }

    private SolrQueryBuilder(String query) {
	getBuiltObject().setQuery(query);
    }

    public static SolrQueryBuilder aQuery() {
	return new SolrQueryBuilder();
    }

    public static SolrQueryBuilder aQuery(String query) {
	return new SolrQueryBuilder(query);
    }

    public SolrQueryBuilder withinFields(String... fields) {
	getBuiltObject().set(QUERY_FIELDS_PARAMETER, join(fields, MULTIPLE_QUERY_VALUES_SEPARATOR));
	return this;
    }

    public SolrQueryBuilder withExtendedDisjunctionMax() {
	return withQueryParser(QUERY_EDISMAX);
    }

    public SolrQueryBuilder withQueryParser(String queryParser) {
	getBuiltObject().set(QUERY_PARSER_TYPE_PARAMETER, queryParser);
	return this;
    }

    public SolrQueryBuilder withEnabledHighlighting() {
	getBuiltObject().setHighlight(true);
	return this;
    }

    public SolrQueryBuilder withNumberOfHighlightedSnippets(int quantity) {
	getBuiltObject().setHighlightSnippets(quantity);
	return this;
    }

    public SolrQueryBuilder withinField(String field) {
	getBuiltObject().set(QUERY_FIELDS_PARAMETER, field);
	return this;
    }

    public SolrQueryBuilder orWithinField(String field) {
	append(QUERY_FIELDS_PARAMETER, field);
	return this;
    }

    private void append(String name, String value) {
	if (getBuiltObject().get(name) != null) {
	    getBuiltObject().set(name, getBuiltObject().get(name) + " " + value);
	} else {
	    getBuiltObject().set(name, value);
	}
    }

    public SolrQueryBuilder withinBoostedField(String field, int boost) {
	withinBoostedField(field, Integer.toString(boost));
	return this;
    }

    public SolrQueryBuilder withinBoostedField(String field, String boost) {
	getBuiltObject().set(QUERY_FIELDS_PARAMETER, field + BOOST_PREFIX + boost);
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
	getBuiltObject().setStart(skip);
	return this;
    }
}