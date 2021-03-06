package pl.kurylek.junktion.domain;

import static org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang3.builder.HashCodeBuilder.reflectionHashCode;
import static org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString;

import java.util.ArrayList;
import java.util.Date;

import org.apache.solr.client.solrj.beans.Field;

public class Document {

    public static final String TITLE_FIELD = "attr_title";
    public static final String SIZE_FIELD = "attr_size";
    public static final String FILENAME_FIELD = "attr_filename";
    public static final String PATH_FIELD = "attr_path";
    public static final String MODIFIED_FIELD = "attr_modified";
    public static final String AUTHOR_FIELD = "attr_author";
    public static final String ID_FIELD = "id";
    public static final String CONTENT_FIELD = "attr_content";

    @Field(ID_FIELD)
    private String id;

    @Field(TITLE_FIELD)
    private String title;

    @Field(CONTENT_FIELD)
    private ArrayList<String> content = new ArrayList<>();

    @Field(AUTHOR_FIELD)
    private String author;

    @Field(MODIFIED_FIELD)
    private Date modified;

    @Field(PATH_FIELD)
    private String path;

    @Field(FILENAME_FIELD)
    private String filename;

    @Field(SIZE_FIELD)
    private Long size;

    public String getId() {
	return id;
    }

    public void setId(String id) {
	this.id = id;
    }

    public String getTitle() {
	return title;
    }

    public void setTitle(String title) {
	this.title = title;
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

    public String getAuthor() {
	return author;
    }

    public void setAuthor(String author) {
	this.author = author;
    }

    public Date getModified() {
	return modified;
    }

    public void setModified(Date modified) {
	this.modified = modified;
    }

    public String getPath() {
	return path;
    }

    public void setPath(String path) {
	this.path = path;
    }

    public String getFilename() {
	return filename;
    }

    public void setFilename(String filename) {
	this.filename = filename;
    }

    public Long getSize() {
	return size;
    }

    public void setSize(Long size) {
	this.size = size;
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