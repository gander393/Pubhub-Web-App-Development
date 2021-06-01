package examples.pubhub.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import examples.pubhub.model.VipTag;
import examples.pubhub.dao.VipTagDAO;
import examples.pubhub.model.Book;
import examples.pubhub.dao.BookDAO;

import examples.pubhub.utilities.DAOUtilities;

@WebServlet("/AddTag")
public class AddTagServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
		
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("tagActions.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String isbn13 = req.getParameter("isbn13");
		String name = req.getParameter("name");
			
		boolean isSuccess = true;
			
		BookDAO dao = DAOUtilities.getBookDAO();
		Book book = dao.getBookByISBN(isbn13);
	
		VipTag tag = new VipTag();
		VipTagDAO database = DAOUtilities.getVipTagDAO();
			
		if (book != null) {
			List<String> tags = database.getAllTagsForBook(book);
			//String search = name;
			for(String str : tags) {
				if(str.trim().contains(name)) {
					isSuccess = false;
					break;
				}
			}
			if(isSuccess) {
				req.setAttribute("book", book);
				isSuccess = database.addTag(tag, book);
			}				
			req.getSession().setAttribute("message", "This tag has already been added");
			req.getSession().setAttribute("messageClass", "alert-danger");
			req.getRequestDispatcher("tagActions.jsp").forward(req, resp);
			
			}else{
				isSuccess = false;
			}
				
			if(isSuccess){
				req.getSession().setAttribute("message", "Tag successfully added");
				req.getSession().setAttribute("messageClass", "alert-success");
				resp.sendRedirect(req.getContextPath() + "/ViewBookDetails?isbn13=" + isbn13);
			}else{
				req.getSession().setAttribute("message", "There was a problem adding the tag");
				req.getSession().setAttribute("messageClass", "alert-danger");
				req.getRequestDispatcher("tagActions.jsp").forward(req, resp);
		}
	}
}



