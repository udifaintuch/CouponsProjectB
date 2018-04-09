package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import main.CouponSystem;
import clients.AdminFacade;
import clients.CompanyFacade;
import clients.CustomerFacade;
import exceptions.ClientLoginException;
import exceptions.CouponSystemException;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger=Logger.getLogger(LoginServlet.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name=request.getParameter("user");
		String password=request.getParameter("password");
		String userType=request.getParameter("type");
		
		if(userType.equals("admin")){
			try {
				AdminFacade adminFacade=(AdminFacade)CouponSystem.getInstance().login(name, password, userType);
				request.getSession().setAttribute("facade", adminFacade);
				response.sendRedirect("http://35.200.145.157:8080/CouponsProjectB/distAdmin/#/home");
				logger.info("user " + name+" from type admin has been successfully logged in");
			} catch (ClientLoginException e) {
				response.sendRedirect("http://35.200.145.157:8080/CouponsProjectB/distLogin?x=fail1");
				logger.error(e.getMessage());
			} catch (CouponSystemException e) {
				logger.error(e.getMessage());
				e.printStackTrace();
			}
		}
		
		if(userType.equals("company")){
			try {
				CompanyFacade companyFacade=(CompanyFacade)CouponSystem.getInstance().login(name, password, userType);
				request.getSession().setAttribute("facade", companyFacade);
				response.sendRedirect("http://35.200.145.157:8080/CouponsProjectB/distCompany/#/home");
				logger.info("user " + name+" from type company has been successfully logged in");
			} catch (ClientLoginException e) {
				logger.error(e.getMessage());
				response.sendRedirect("http://35.200.145.157:8080/CouponsProjectB/distLogin?x=fail2");
			} catch (CouponSystemException e) {
				logger.error(e.getMessage());
				e.printStackTrace();
			}
		}
		
		if(userType.equals("customer")){
			try {
				CustomerFacade customerFacade=(CustomerFacade)CouponSystem.getInstance().login(name, password, userType);
				request.getSession().setAttribute("facade",customerFacade);
				response.sendRedirect("http://35.200.145.157:8080/CouponsProjectB/distCustomer#/home");
				logger.info("user " + name+" from type customer has been successfully logged in");
			} catch (ClientLoginException e) {
				logger.error(e.getMessage());
				response.sendRedirect("http://35.200.145.157:8080/CouponsProjectB/distLogin?x=fail3");
			} catch (CouponSystemException e) {
				logger.error(e.getMessage());
				e.printStackTrace();
			}
		}
		
		
	
			
		
			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
