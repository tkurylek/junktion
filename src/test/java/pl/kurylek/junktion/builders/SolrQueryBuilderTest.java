package pl.kurylek.junktion.builders;

import static org.fest.assertions.Assertions.assertThat;
import static pl.kurylek.junktion.builders.SolrQueryBuilder.QUERY_EDISMAX;
import static pl.kurylek.junktion.builders.SolrQueryBuilder.QUERY_FIELDS_PARAMETER;
import static pl.kurylek.junktion.builders.SolrQueryBuilder.QUERY_PARSER_TYPE_PARAMETER;
import static pl.kurylek.junktion.builders.SolrQueryBuilder.aQuery;

import org.apache.solr.client.solrj.SolrQuery;
import org.junit.Test;

public class SolrQueryBuilderTest {

    private static final int TWO = 2;
    private static final String AUTHOR = "Dr Cooper";
    private static final String TEXT = "The Big Bang Theory";

    @Test
    public void shouldBuildQueryOnMultipleFields() {
	// given
	SolrQuery solrQuery = (SolrQuery) new SolrQuery().set(QUERY_FIELDS_PARAMETER, AUTHOR + " " + TEXT);

	// when
	SolrQuery result = aQuery().withinFields(AUTHOR, TEXT).build();

	// then
	assertThat(result.toString()).isEqualTo(solrQuery.toString());
    }

    @Test
    public void shouldQueryWithExtendedDisjunctionMax() {
	// given
	SolrQuery solrQuery = (SolrQuery) new SolrQuery().set(QUERY_PARSER_TYPE_PARAMETER, QUERY_EDISMAX);

	// when
	SolrQuery result = aQuery().withExtendedDisjunctionMax().build();

	// then
	assertThat(result.toString()).isEqualTo(solrQuery.toString());
    }

    @Test
    public void shouldQueryWithEnabledHighlighting() {
	// given
	SolrQuery solrQuery = new SolrQuery().setHighlight(true);

	// when
	SolrQuery result = aQuery().withEnabledHighlighting().build();

	// then
	assertThat(result.toString()).isEqualTo(solrQuery.toString());
    }

    @Test
    public void shouldQueryWithTwoHighlightedSnippets() {
	// given
	SolrQuery solrQuery = new SolrQuery().setHighlightSnippets(TWO);

	// when
	SolrQuery result = aQuery().withHighlightedSnippets(TWO).build();

	// then
	assertThat(result.toString()).isEqualTo(solrQuery.toString());
    }
}
