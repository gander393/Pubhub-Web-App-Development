package examples.pubhub.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import examples.pubhub.dao.VipTagDAO;
//import examples.pubhub.model.VipTag;
import examples.pubhub.utilities.DAOUtilities;

/*
 * This servlet will take you to the homepage for the Tag Actions module 
 */
@WebServlet("/TagAdding")

public class TagAddingServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Grab the list of Tags from the Database
		VipTagDAO dao = DAOUtilities.getVipTagDAO();
		List<String> tagList = dao.getAllTagsForBook(null);

		// Populate the list into a variable that will be stored in the session
		request.getSession().setAttribute("tags", tagList);
		
		request.getRequestDispatcher("bookPublishingHome.jsp").forward(request, response);
	}
}
