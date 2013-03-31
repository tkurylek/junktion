package pl.kurylek.junktion.snapshots;

import static pl.kurylek.utils.NullSafeUtils.nullSafeToString;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class DocumentSnaphot {

    private String title;
    private Date modified;
    private String path;
    private String filename;
    private Long size;
    private String author;
    private final List<String> highlights = new LinkedList<>();

    public String getTitle() {
	return title;
    }

    public void setTitle(String title) {
	this.title = nullSafeToString(title);
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
	this.path = nullSafeToString(path);
    }

    public String getFilename() {
	return filename;
    }

    public void setFilename(String filename) {
	this.filename = nullSafeToString(filename);
    }

    public Long getSize() {
	return size;
    }

    public void setSize(Long size) {
	this.size = size;
    }

    public String getAuthor() {
	return author;
    }

    public void setAuthor(String author) {
	this.author = nullSafeToString(author);
    }

    public List<String> getHighlights() {
	return highlights;
    }

    public void addHighlight(String highlight) {
	this.highlights.add(nullSafeToString(highlight));
    }
}