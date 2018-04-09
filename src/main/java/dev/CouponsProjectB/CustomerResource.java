package dev.CouponsProjectB;

import java.util.Collection;
import javaBeans.Coupon;
import javaBeans.CouponType;
import javaBeans.Customer;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.log4j.Logger;
import com.google.gson.Gson;
import clients.CustomerFacade;
import webObjects.WebCoupon;
import webObjects.WebCustomer;


@Path("CustomerResource")
public class CustomerResource {
	private static Logger logger=Logger.getLogger(CustomerResource.class);//logger to log actions and errors to log file
	
	@Context HttpServletRequest request;
	@Context private HttpServletResponse response;
	
	
	@Path("purchaseCoupon")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response purchaseCoupon(WebCoupon webcoupon){
		try{
		CustomerFacade customerFacade=(CustomerFacade)request.getSession().getAttribute("facade");
		Coupon coupon=webcoupon.convertToCoupon();
		customerFacade.purchaseCoupon(coupon);
		logger.info("the coupon "+coupon.getTitle()+" has been successfully purchased");
		return Response.ok().status(200).build();
		}catch(Exception e){
			logger.error(e.getMessage());
			return Response.ok(e.getMessage()).status(500).build();

		}
	}
	
	
	
	@Path("readThisCustomer")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response readThisCustomer(){
		try{
	    CustomerFacade customerFacade=(CustomerFacade)request.getSession().getAttribute("facade");
		Customer customer=customerFacade.readThisCustomer();
		WebCustomer webCustomer=new WebCustomer(customer);
		logger.info("get this customer details success");
		return Response.ok(webCustomer).status(200).build();
		}catch(Exception e){
			logger.error(e.getMessage());
			return Response.ok(e.getMessage()).status(500).build();

		}
	}
	
	
	@Path("readAllPurchasedCoupons")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response readAllPurchasedCoupons(){
		try{
	    CustomerFacade customerFacade=(CustomerFacade)request.getSession().getAttribute("facade");
		Collection<Coupon> coupons=customerFacade.readAllPurchasedCoupons();
		Collection<WebCoupon> webCoupons=WebCoupon.convertToWebCoupons(coupons);
		Gson json=new Gson();
		logger.info("get all customer purchased coupons success");
		return Response.ok(json.toJson(webCoupons)).status(200).build();

		}catch(Exception e){
			logger.error(e.getMessage());
			return Response.ok(e.getMessage()).status(500).build();

		}
	}
	
	
	@Path("readAllPurchasedCouponsByType/{type}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response readAllPurchasedCouponsByType(@PathParam("type") String type){
		try{
	    CustomerFacade customerFacade=(CustomerFacade)request.getSession().getAttribute("facade");
		Collection<Coupon> coupons=customerFacade.readAllPurchasedCouponsByType(CouponType.valueOf(type));
		Collection<WebCoupon> webCoupons=WebCoupon.convertToWebCoupons(coupons);
		Gson json=new Gson();
		logger.info("get all customer purchased coupons by type success");
		return Response.ok(json.toJson(webCoupons)).status(200).build();
		}catch(Exception e){
			logger.error(e.getMessage());
			return Response.ok(e.getMessage()).status(500).build();

		}
		
	}
	
	
	@Path("readAllPurchasedCouponsByPrice/{price}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response readAllPurchasedCouponsByPrice(@PathParam("price") double price){
		try{
	    CustomerFacade customerFacade=(CustomerFacade)request.getSession().getAttribute("facade");
		Collection<Coupon> coupons=customerFacade.readAllPurchasedCouponsByPrice(price);
		Collection<WebCoupon> webCoupons=WebCoupon.convertToWebCoupons(coupons);
		Gson json=new Gson();
		logger.info("get all customer purchased coupons by price success");
		return Response.ok(json.toJson(webCoupons)).status(200).build();
		}catch(Exception e){
			logger.error(e.getMessage());
			return Response.ok(e.getMessage()).status(500).build();

		}
	}
	
	
	@Path("getAllCouponsToCustomer")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllCouponsToCustomer(){
		try{
	    CustomerFacade customerFacade=(CustomerFacade)request.getSession().getAttribute("facade");
		Collection<Coupon> coupons=customerFacade.getAllCouponsToCustomer();
		Collection<WebCoupon> webCoupons=WebCoupon.convertToWebCoupons(coupons);
		Gson json=new Gson();
		logger.info("get all coupons to table success");
		return Response.ok(json.toJson(webCoupons)).status(200).build();
		}catch(Exception e){
			logger.error(e.getMessage());
			return Response.ok(e.getMessage()).status(500).build();

		}
	}
	
	
	
	
	
	
	
}
