package dev.CouponsProjectB;

import java.util.Collection;
import javaBeans.Company;
import javaBeans.Coupon;
import javaBeans.CouponType;
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
import com.google.gson.Gson;
import clients.CompanyFacade;
import webObjects.WebCompany;
import webObjects.WebCoupon;

@Path("CompanyResource")
public class CompanyResource {
	private static Logger logger=Logger.getLogger(CompanyResource.class);//logger to log actions and errors to log file

	@Context HttpServletRequest request;
	@Context private HttpServletResponse response;
	
	@Path("createCoupon")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createCoupon(WebCoupon webcoupon){
		try{
	    CompanyFacade companyFacade=(CompanyFacade)request.getSession().getAttribute("facade");
		Coupon coupon=webcoupon.convertToCoupon();
		companyFacade.createCoupon(coupon);	
		logger.info("the coupon "+coupon.getTitle()+" has been successfully added to the database" );
		return Response.ok().status(200).build();
	}catch(Exception e){
		logger.error(e.getMessage());
		return Response.ok(e.getMessage()).status(500).build();
	}
	}
	
	
	@Path("removeCoupon")
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	public Response removeCoupon(WebCoupon webcoupon){
		try{
		CompanyFacade companyFacade=(CompanyFacade)request.getSession().getAttribute("facade");
		Coupon coupon=webcoupon.convertToCoupon();
		companyFacade.removeCoupon(coupon);
		logger.info("the coupon "+coupon.getTitle()+" has been successfully removed");
		return Response.ok().status(200).build();
		}catch(Exception e){
			logger.error(e.getMessage());
			return Response.ok(e.getMessage()).status(500).build();
 
		}
	}
	
	
	@Path("updateCoupon")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateCoupon(WebCoupon webcoupon){
		try{
	    CompanyFacade companyFacade=(CompanyFacade)request.getSession().getAttribute("facade");
		Coupon coupon=webcoupon.convertToCoupon();
		companyFacade.updateCoupon(coupon);
		logger.info("the coupon "+coupon.getTitle()+" details has been successfully updated");
		return Response.ok().status(200).build();
		}catch(Exception e){
			logger.error(e.getMessage());
			return Response.ok(e.getMessage()).status(500).build();

		}

	}
	
	
	@Path("readThisCompany")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response readThisCompany(){
		try{
	    CompanyFacade companyFacade=(CompanyFacade)request.getSession().getAttribute("facade");
		Company company=companyFacade.readThisCompany();
		WebCompany webCompany=new WebCompany(company);
		logger.info("get this company details success");
		return Response.ok(webCompany).status(200).build();
		}catch(Exception e){
			logger.error(e.getMessage());
			return Response.ok(e.getMessage()).status(500).build();

		}
	}
	
	
	@Path("readCoupon/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response readCoupon(@PathParam("id")long id) {
		try{
		CompanyFacade companyFacade=(CompanyFacade)request.getSession().getAttribute("facade");
		Coupon coupon=companyFacade.readCoupon(id);
		WebCoupon webCoupon=new WebCoupon(coupon);
		logger.info("get coupon by id success");
		return Response.ok(webCoupon).status(200).build();
		}catch(Exception e){
			logger.error(e.getMessage());
			return Response.ok(e.getMessage()).status(500).build();

		}
	}
	
	@Path("readAllCoupons")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response readAllCoupons() {
		try{
		CompanyFacade companyFacade=(CompanyFacade)request.getSession().getAttribute("facade");
		Collection<Coupon> coupons=companyFacade.readAllCoupons();
		Collection<WebCoupon> webcoupons=WebCoupon.convertToWebCoupons(coupons);
		Gson json=new Gson();
		logger.info("get all company coupons success");
		return Response.ok(json.toJson(webcoupons)).status(200).build();

		}catch(Exception e){
			logger.error(e.getMessage());
			return Response.ok(e.getMessage()).status(500).build();

		}
	}
	
	@Path("readCouponsByType/{type}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response readCouponsByType(@PathParam("type") String type){
		try{
	    CompanyFacade companyFacade=(CompanyFacade)request.getSession().getAttribute("facade");
		Collection<Coupon> coupons=companyFacade.readCouponsByType(CouponType.valueOf(type));
		Collection<WebCoupon> webcoupons=WebCoupon.convertToWebCoupons(coupons);
		Gson json=new Gson();
		logger.info("get all company coupons by type success");
		return Response.ok(json.toJson(webcoupons)).status(200).build();
		}catch(Exception e){
			logger.error(e.getMessage());
			return Response.ok(e.getMessage()).status(500).build();
		

		}
	}

	@Path("readCouponsByPrice/{price}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response readCouponsByPrice(@PathParam("price")double price){
		try{
		CompanyFacade companyFacade=(CompanyFacade)request.getSession().getAttribute("facade");
		Collection<Coupon> coupons=companyFacade.readCouponsByPrice(price);
		Collection<WebCoupon> webcoupons=WebCoupon.convertToWebCoupons(coupons);
		Gson json=new Gson();
		logger.info("get all company coupons by price success");
		return Response.ok(json.toJson(webcoupons)).status(200).build();
		}catch(Exception e){
			logger.error(e.getMessage());
			return Response.ok(e.getMessage()).status(500).build();

		}
		
	}
	
	@Path("readCouponsByDate/{date}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response readCouponsByDate(@PathParam("date")String date){
		try{
		CompanyFacade companyFacade=(CompanyFacade)request.getSession().getAttribute("facade");
		Collection<Coupon> coupons=companyFacade.readCouponsByDate(date);
		Collection<WebCoupon> webcoupons=WebCoupon.convertToWebCoupons(coupons);
		Gson json=new Gson();
		logger.info("get all company coupons by date success");
		return Response.ok(json.toJson(webcoupons)).status(200).build();
		}catch(Exception e){
			logger.error(e.getMessage());
			return Response.ok(e.getMessage()).status(500).build();

		}
		
	
	}
	
	

}
