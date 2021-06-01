package examples.pubhub.model;

public class VipTag {
	
	private String tag_name;
	private String isbn_13;
	
	//Constructor when tag name and isbn are specified
	public VipTag(String tagName, String isbn) {
		this.tag_name = tagName;
		this.isbn_13 = isbn;
	}
	
	//Default constructor
	public VipTag() {
		this.tag_name = null;
		this.isbn_13 = null;
	}
	
	//Getters and setters
	public String getTag_name() {
		return tag_name;
	}

	public void setTag_name(String tag_name) {
		this.tag_name = tag_name;
	}

	public String getIsbn_13() {
		return isbn_13;
	}

	public void setIsbn_13(String isbn_13) {
		this.isbn_13 = isbn_13;
	}
}
