package pl.kurylek.junktion.builders;

import static org.fest.assertions.Assertions.assertThat;
import static pl.kurylek.junktion.builders.SolrQueryBuilder.QUERY_EDISMAX;
import static pl.kurylek.junktion.builders.SolrQueryBuilder.QUERY_FIELDS_PARAMETER;
import static pl.kurylek.junktion.builders.SolrQueryBuilder.QUERY_PARSER_TYPE_PARAMETER;
import static pl.kurylek.junktion.builders.SolrQueryBuilder.aQuery;

import org.apache.solr.client.solrj.SolrQuery;
import org.junit.Test;

public class SolrQueryBuilderTest {

    private static final String AUTHOR_FIELD_BOOST = "2";
    private static final int TWO = 2;
    private static final String AUTHOR_FIELD = "_attr_author";
    private static final String CONTENT_FIELD = "_attr_content";

    @Test
    public void shouldBuildQuerySkippingTwoFirstResults() {
	// given
	SolrQuery solrQuery = new SolrQuery().setStart(TWO);

	// when
	SolrQuery result = aQuery().wtihSkippingFirst(TWO).build();

	// then
	assertThat(result.toString()).isEqualTo(solrQuery.toString());
    }

    @Test
    public void shouldBuildQueryWithAdditionlField() {
	// given
	SolrQuery solrQuery = (SolrQuery) new SolrQuery().set(QUERY_FIELDS_PARAMETER, AUTHOR_FIELD
		+ " " + CONTENT_FIELD);

	// when
	SolrQuery result = aQuery().withinField(AUTHOR_FIELD).orWithinField(CONTENT_FIELD).build();

	// then
	assertThat(result.toString()).isEqualTo(solrQuery.toString());
    }

    @Test
    public void shouldBuildQueryWithBoostedField() {
	// given
	SolrQuery solrQuery = (SolrQuery) new SolrQuery().set(QUERY_FIELDS_PARAMETER, AUTHOR_FIELD
		+ "^" + AUTHOR_FIELD_BOOST);

	// when
	SolrQuery result = aQuery().withinBoostedField(AUTHOR_FIELD, AUTHOR_FIELD_BOOST).build();

	// then
	assertThat(result.toString()).isEqualTo(solrQuery.toString());
    }

    @Test
    public void shouldBuildQueryWithAdditionalBoostedField() {
	// given
	SolrQuery solrQuery = (SolrQuery) new SolrQuery().set(QUERY_FIELDS_PARAMETER, CONTENT_FIELD
		+ " " + AUTHOR_FIELD + "^" + AUTHOR_FIELD_BOOST);

	// when
	SolrQuery result = aQuery().withinField(CONTENT_FIELD)
		.orWithinBoostedField(AUTHOR_FIELD, AUTHOR_FIELD_BOOST).build();

	// then
	assertThat(result.toString()).isEqualTo(solrQuery.toString());
    }

    @Test
    public void shouldBuildQueryOnMultipleFields() {
	// given
	SolrQuery solrQuery = (SolrQuery) new SolrQuery().set(QUERY_FIELDS_PARAMETER, AUTHOR_FIELD
		+ " " + CONTENT_FIELD);

	// when
	SolrQuery result = aQuery().withinFields(AUTHOR_FIELD, CONTENT_FIELD).build();

	// then
	assertThat(result.toString()).isEqualTo(solrQuery.toString());
    }

    @Test
    public void shouldQueryWithExtendedDisjunctionMax() {
	// given
	SolrQuery solrQuery = (SolrQuery) new SolrQuery().set(QUERY_PARSER_TYPE_PARAMETER,
		QUERY_EDISMAX);

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
	SolrQuery result = aQuery().withNumberOfHighlightedSnippets(TWO).build();

	// then
	assertThat(result.toString()).isEqualTo(solrQuery.toString());
    }
}
