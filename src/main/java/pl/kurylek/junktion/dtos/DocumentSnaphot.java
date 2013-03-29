package pl.kurylek.junktion.dtos;

import java.util.Date;
import java.util.List;

public class DocumentSnaphot {

    private Date modified;
    private String path;
    private String filename;
    private Long size;
    private String author;
    private List<String> highlights;

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

    public String getAuthor() {
	return author;
    }

    public void setAuthor(String author) {
	this.author = author;
    }

    public List<String> getHighlights() {
	return highlights;
    }

    public void setHighlights(List<String> highlights) {
	this.highlights = highlights;
    }
}
