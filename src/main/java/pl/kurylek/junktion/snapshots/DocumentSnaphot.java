package pl.kurylek.junktion.snapshots;

import java.util.LinkedList;
import java.util.List;

public class DocumentSnaphot {

    private String title;
    private String modified;
    private String path;
    private String filename;
    private Long size;
    private String author;
    private final List<String> highlights = new LinkedList<>();

    public String getTitle() {
	return title;
    }

    public void setTitle(String title) {
	this.title = title;
    }

    public String getModified() {
	return modified;
    }

    public void setModified(String date) {
	this.modified = date;
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

    public String getAuthor() {
	return author;
    }

    public void setAuthor(String author) {
	this.author = author;
    }

    public List<String> getHighlights() {
	return highlights;
    }

    public void addHighlight(String highlight) {
	this.highlights.add(highlight);
    }
}
