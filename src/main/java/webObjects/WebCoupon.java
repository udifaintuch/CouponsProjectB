package webObjects;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import javax.xml.bind.annotation.XmlRootElement;
import javaBeans.Coupon;
import javaBeans.CouponType;

@XmlRootElement
public class WebCoupon implements Serializable {
	private long id;  //represents the coupon's id
	private String title;  //represents the coupon's title 
	private String startdate;  //represents the coupon's start date
	private String enddate;  //represents the coupon's end date
	private int amount;  // represents the coupon amount
	private CouponType type;  //represents the coupon's type
	private String message;  //represents the coupon's message
	private double price;  //represents the coupon's price
	private String image;  //represents the coupom's image

	



	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	public WebCoupon(){
		
	}



	public WebCoupon(Coupon coupon){
		this.id=coupon.getId();
		this.title=coupon.getTitle();
		this.startdate=coupon.getStartdate();
		this.enddate=coupon.getEnddate();
		this.amount=coupon.getAmount();
		this.type=coupon.getType();
		this.message=coupon.getMessage();
		this.price=coupon.getPrice();
		this.image=coupon.getImage();
		
		
	}
	
	public static Collection<WebCoupon> convertToWebCoupons(Collection<Coupon> coupons){
		Collection<WebCoupon> webcoupons=new HashSet<>();
		for(Coupon coupon:coupons){
			webcoupons.add(new WebCoupon(coupon));
			
		}
		
		 return webcoupons;
	}
	
	
	public  Coupon convertToCoupon(){
		Coupon coupon=new Coupon(this.getId(), this.getTitle(), this.getStartdate(), this.getEnddate(),
		this.getAmount(), this.getType(), this.getMessage(), this.getPrice(), this.getImage());
		return coupon;
	}
	
	public static Collection<Coupon> convertToCoupons(Collection<WebCoupon> webcoupons){
		Collection<Coupon> coupons=new HashSet<>();
		for(WebCoupon webCoupon:webcoupons){
			coupons.add(webCoupon.convertToCoupon());
		}
		return coupons;
	
		
	}



	public long getId() {
		return id;
	}



	public void setId(long id) {
		this.id = id;
	}



	public String getTitle() {
		return title;
	}



	public void setTitle(String title) {
		this.title = title;
	}



	public String getStartdate() {
		return startdate;
	}



	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}



	public String getEnddate() {
		return enddate;
	}



	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}



	public int getAmount() {
		return amount;
	}



	public void setAmount(int amount) {
		this.amount = amount;
	}



	public CouponType getType() {
		return type;
	}



	public void setType(CouponType type) {
		this.type = type;
	}



	public String getMessage() {
		return message;
	}



	public void setMessage(String message) {
		this.message = message;
	}



	public double getPrice() {
		return price;
	}



	public void setPrice(double price) {
		this.price = price;
	}



	public String getImage() {
		return image;
	}



	public void setImage(String image) {
		this.image = image;
	}



	@Override
	public String toString() {
		return "WebCoupon [id=" + id + ", title=" + title + ", startdate="
				+ startdate + ", enddate=" + enddate + ", amount=" + amount
				+ ", type=" + type + ", message=" + message + ", price="
				+ price + ", image=" + image + "]";
	}
	
	
	
		
		
	}
