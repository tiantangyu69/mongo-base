/**
 * 
 */
package org.sagacity.core.model;


/**
 * @author LIZHITAO GridFS实体
 */
public class MongoFile {
	private String id;
	private String filename;
	private String contentType;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
}
