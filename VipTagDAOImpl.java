package examples.pubhub.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import examples.pubhub.model.Book;
import examples.pubhub.model.VipTag;
//import examples.pubhub.dao.VipTagDAO;
import examples.pubhub.utilities.DAOUtilities;

public class VipTagDAOImpl implements VipTagDAO{
	Connection connection = null;	// Our connection to the database
	PreparedStatement stmt = null;	// We use prepared statements to help protect against SQL injection

	@Override
	public boolean addTag(VipTag tag, Book book) {
		try {
			connection = DAOUtilities.getConnection();
			String sql = "INSERT INTO book_tags VALUES (?, ?)"; 
			stmt = connection.prepareStatement(sql);
				
			stmt.setString(1, tag.getIsbn_13());	
			stmt.setString(2, book.getIsbn13());
			stmt.setString(3, tag.getTag_name());
			
			if (stmt.executeUpdate() != 0)
				return true;
			else
				return false;	
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			closeResources();
		}
	}

	@Override
	public boolean removeTag(VipTag tag, Book book) {
		try {
			connection = DAOUtilities.getConnection();
			String sql = "DELETE FROM book_tags WHERE tag_name=? AND isbn_13=?";
			stmt = connection.prepareStatement(sql);
	
			stmt.setString(1, tag.getIsbn_13());						
			stmt.setString(2, book.getIsbn13());
			stmt.setString(3, tag.getTag_name());
			
			if (stmt.executeUpdate() != 0)
				return true;
			else
				return false;	
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			closeResources();
		}
	}

	@Override
	public VipTag getTagByName(String name, String isbn13) {
		VipTag tag = null;
		try {
			connection = DAOUtilities.getConnection();
			String sql = "SELECT * FROM Tags WHERE tag_name = ?";
			stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, name);
			
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				tag = new VipTag();
				tag.setTag_name(rs.getString("tag_name"));
				tag.setIsbn_13(rs.getString("isbn_13"));			
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}
		
		return tag;
	}
	
	@Override
	public List<String> getAllTagsForBook(Book book) {
		List<String> tags = new ArrayList<>();

		try {
			connection = DAOUtilities.getConnection();	// Get our database connection from the manager
			String sql = "SELECT * FROM book_tags where isbn_13=?";			// Our SQL query
			stmt = connection.prepareStatement(sql);	// Creates the prepared statement from the query
			stmt.setString(1, book.getIsbn13());
			
			System.out.println(stmt);
			ResultSet rs = stmt.executeQuery();			// Queries the database

			while (rs.next()) {
				tags.add(rs.getString("tag_name"));		
			}
			rs.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}
		return tags;
	}

	@Override
	public List<Book> getBooksByTag(String Tag) {
		List<Book> books = new ArrayList<>();
		
		try {
			connection = DAOUtilities.getConnection();
			String sql = "SELECT b.* FROM books b LEFT OUTER JOIN book_tags bt ON b.isbn_13 = bt.isbn13 WHERE tag_name=?";	// Note the ? in the query
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, Tag);
			
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Book book = new Book();
				
				book.setIsbn13(rs.getString("isbn_13"));
				book.setAuthor(rs.getString("author"));
				book.setTitle(rs.getString("title"));
				
				book.setPublishDate(rs.getDate("publish_date").toLocalDate());
				book.setPrice(rs.getDouble("price"));
				
				book.setContent(rs.getBytes("content"));
		
				books.add(book);
			}
			
			rs.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}
		
		return books;
	}
	
	private void closeResources() {
		try {
			if (stmt != null)
				stmt.close();
		} catch (SQLException e) {
			System.out.println("Could not close statement!");
			e.printStackTrace();
		}
		
		try {
			if (connection != null)
				connection.close();
		} catch (SQLException e) {
			System.out.println("Could not close connection!");
			e.printStackTrace();
		}
	}

}
