package pl.kurylek.junktion.domain;

import static org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang3.builder.HashCodeBuilder.reflectionHashCode;
import static org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString;

import java.util.ArrayList;

import org.apache.solr.client.solrj.beans.Field;

public class Document {

    public static final String ID_FIELD = "id";
    public static final String CONTENT_FIELD = "attr_content";

    @Field(ID_FIELD)
    private String id;

    @Field(CONTENT_FIELD)
    private ArrayList<String> content = new ArrayList<>();

    public String getId() {
	return id;
    }

    public void setId(String id) {
	this.id = id;
    }

    public String getContent() {
	return content.get(0);
    }

    public void setContent(ArrayList<String> content) {
	this.content = content;
    }

    public void setContent(String content) {
	if (this.content.size() > 0) {
	    this.content.set(0, content);
	} else {
	    this.content.add(content);
	}
    }

    @Override
    public boolean equals(Object obj) {
	return reflectionEquals(this, obj);
    }

    @Override
    public int hashCode() {
	return reflectionHashCode(this);
    }

    @Override
    public String toString() {
	return reflectionToString(this);
    }
}