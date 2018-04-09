package dev.CouponsProjectB;

import java.util.Collection;
import javaBeans.Company;
import javaBeans.Customer;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.log4j.Logger;
import webObjects.WebCompany;
import webObjects.WebCustomer;
import clients.AdminFacade;
import com.google.gson.Gson;




@Path("AdminResource")
public class AdminResource {
	private static Logger logger=Logger.getLogger(AdminResource.class);//logger to log actions and errors to log file
	
	@Context HttpServletRequest request;
	@Context private HttpServletResponse response;
	
		
		@Path("createCompany")
		@POST
		@Consumes(MediaType.APPLICATION_JSON)
		public Response createCompany(WebCompany webcompany){
			
			if(request.getSession().getAttribute("facade")!=null){
			   try {
				AdminFacade adminFacade=(AdminFacade)request.getSession().getAttribute("facade");
				Company company=webcompany.convertToCompany();
				adminFacade.createCompany(company);
				logger.info("the company "+company.getCompanyName()+" has been successfully added to the database");
				return Response.ok().status(200).build();
			   } catch (Exception e) {
					logger.error(e.getMessage());
				return Response.ok(e.getMessage()).status(500).build();
			   }  
		     }
			else{
				return Response.ok().status(401).build();
			}
		}
		
		@Path("removeCompany")
		@DELETE
		@Consumes(MediaType.APPLICATION_JSON)
		public Response removeCompany(WebCompany webcompany) {
			
	      if(request.getSession().getAttribute("facade")!=null){
			try {
				AdminFacade adminFacade=(AdminFacade)request.getSession().getAttribute("facade");
				Company company=webcompany.convertToCompany();
				adminFacade.removeCompany(company);
				logger.info("the company "+company.getCompanyName()+" has been successfully removed");
				return Response.ok().status(200).build();
			} catch (Exception e) {
				logger.error(e.getMessage());
				return Response.ok(e.getMessage()).status(500).build();
			}
	      }
	      else{
				return Response.ok().status(401).build();
	      }
			 
			
		}
		
		
		@Path("updateCompany")
		@PUT
		@Consumes(MediaType.APPLICATION_JSON)
		public Response updateCompany(WebCompany webcompany){
			
		  if(request.getSession().getAttribute("facade")!=null){
			try {
				AdminFacade adminFacade=(AdminFacade)request.getSession().getAttribute("facade");
				Company company=webcompany.convertToCompany();
				adminFacade.updateCompany(company);
				logger.info("the company "+company.getCompanyName()+" details has been successfully updated");
				return Response.ok().status(200).build();

			} catch (Exception e) {
				logger.error(e.getMessage());
				return Response.ok(e.getMessage()).status(500).build();

			}
		  }
		  else{
			return Response.ok().status(401).build();
		  }
		}
		
		@Path("readCompany/{id}")
		@GET
		@Produces(MediaType.APPLICATION_JSON)
		public Response readCompany(@PathParam("id") long id){
			
		if(request.getSession().getAttribute("facade")!=null){
			try {
				AdminFacade adminFacade=(AdminFacade)request.getSession().getAttribute("facade");
				Company company = adminFacade.readCompany(id);
				WebCompany webcompany=new WebCompany(company);
				logger.info("get company by id success");
				return Response.ok(webcompany).status(200).build();

			} catch (Exception e) {
				logger.error(e.getMessage());
				return Response.ok(e.getMessage()).status(500).build();

			}
		}
		else{
			return Response.ok().status(401).build();
		}
		
		
				
		}
		
		
		@Path("readAllCompanies")
		@GET
		@Produces(MediaType.APPLICATION_JSON)
		public Response readAllCompanies()
		{
		 if(request.getSession().getAttribute("facade")!=null){
			try {
				AdminFacade adminFacade=(AdminFacade)request.getSession().getAttribute("facade");
				Collection<Company> companies = adminFacade.readAllCompanies();
				Collection< WebCompany> webcompanies=WebCompany.convertToWebCompanies(companies);
				Gson gson = new Gson();
				logger.info("get all companies success");
				return Response.ok(gson.toJson(webcompanies)).status(200).build();
			
			} catch (Exception e) {
				logger.error(e.getMessage());
				return Response.ok(e.getMessage()).status(500).build();
			}
		  }
		 else{
			return Response.ok().status(401).build();
		 }
		
		}
		
		@Path("createCustomer")
		@POST
		@Consumes(MediaType.APPLICATION_JSON)
		public Response createCustomer(WebCustomer webcustomer){
		  if(request.getSession().getAttribute("facade")!=null){
			try {
		           AdminFacade adminFacade=(AdminFacade)request.getSession().getAttribute("facade");
			       Customer customer=webcustomer.convertToCustomer();
				   adminFacade.createCustomer(customer);
				   logger.info("the customer "+customer.getCustName()+" has been successfully added to the database");
				return Response.ok().status(200).build();
			} catch (Exception e) {
				logger.error(e.getMessage());
				return Response.ok(e.getMessage()).status(500).build();
			}
		  }
		  else{
			return Response.ok().status(401).build();

		  }
			
			
			
		}
		
		@Path("removeCustomer")
		@DELETE
		@Consumes(MediaType.APPLICATION_JSON)
		public Response removeCustomer(WebCustomer webcustomer){
		 if(request.getSession().getAttribute("facade")!=null){
			try{
				AdminFacade adminFacade=(AdminFacade)request.getSession().getAttribute("facade");
				 Customer customer=webcustomer.convertToCustomer();
				 adminFacade.removeCustomer(customer);
				 logger.info("the customer "+customer.getCustName()+" has been successfully removed");
				return Response.ok().status(200).build();

			}catch(Exception e){
				logger.error(e.getMessage());
				return Response.ok(e.getMessage()).status(500).build();

			}
		 }
		 else{
			return Response.ok().status(401).build();
		 }
			
		}
		
		

		@Path("updateCustomer")
		@PUT
		@Consumes(MediaType.APPLICATION_JSON)
		public Response updateCustomer(WebCustomer webcustomer){
		 if(request.getSession().getAttribute("facade")!=null){
			try{
				AdminFacade adminFacade=(AdminFacade)request.getSession().getAttribute("facade");
				Customer customer=webcustomer.convertToCustomer();
				adminFacade.updateCustomer(customer);
				logger.info("the customer "+customer.getCustName()+" details has been successfully updated");
				return Response.ok().status(200).build();
			}catch(Exception e){
				logger.error(e.getMessage());
				return Response.ok(e.getMessage()).status(500).build();

			}
		  }
		 else{
			return Response.ok().status(401).build();

		 }
		 
			
			
			
	  }
		
		
		@Path("readCustomer/{id}")
		@GET
		@Produces(MediaType.APPLICATION_JSON)
		public Response readCustomer(@PathParam("id") long id){
			
		
		 if(request.getSession().getAttribute("facade")!=null){
			try{
				AdminFacade adminFacade=(AdminFacade)request.getSession().getAttribute("facade");
				Customer customer=adminFacade.readCustomer(id);
				WebCustomer webCustomer=new WebCustomer(customer);
				logger.info("get customer by id success");
				return Response.ok(webCustomer).status(200).build();
			}catch(Exception e){
				logger.error(e.getMessage());
				return Response.ok(e.getMessage()).status(500).build();

			}
		 }
		 else{
				return Response.ok().status(401).build();
 
		 }
			
			
			
	 } 
		
		
		@Path("readAllCustomers")
		@GET
		@Produces(MediaType.APPLICATION_JSON)
		public Response readAllCustomers(){
			
		
		 if(request.getSession().getAttribute("facade")!=null){
			try{
				AdminFacade adminFacade=(AdminFacade)request.getSession().getAttribute("facade");
				Collection<Customer> customers=adminFacade.readAllCustomers();
				Collection<WebCustomer> webcustomers=WebCustomer.convertToWebCustomers(customers);
				Gson gson = new Gson();
				logger.info("get all customers success");
				return Response.ok(gson.toJson(webcustomers)).status(200).build();
			} catch(Exception e){
				logger.error(e.getMessage());
				return Response.ok(e.getMessage()).status(500).build();

			}
		 }
		 else{
			return Response.ok().status(401).build();
 
		 }
			
			
		}

	

}
