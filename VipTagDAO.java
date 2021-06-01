package examples.pubhub.dao;

import java.util.List;

import examples.pubhub.model.Book;
import examples.pubhub.model.VipTag;

public interface VipTagDAO {
	public List<String> getAllTagsForBook(Book book);
	public List<Book> getBooksByTag(String Tag);
	
	public VipTag getTagByName(String name, String isbn13);
	
	public boolean removeTag(VipTag tag, Book book);
	public boolean addTag(VipTag tag, Book book);
}
