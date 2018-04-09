package webObjects;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import javaBeans.Customer;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class WebCustomer implements Serializable {
	private long id;  //represents the customer's id
	private String custName;  //represents  the customer's name
	private String password;  //represents the customer's password
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public WebCustomer(){
		
	}
	
	public WebCustomer(Customer customer){
		this.id=customer.getId();
		this.custName=customer.getCustName();
		this.password=customer.getPassword();
	}
	
	public static Collection<WebCustomer> convertToWebCustomers(Collection<Customer> customers){
		Collection<WebCustomer> webcustomers=new HashSet<>();
		for(Customer customer:customers){
			webcustomers.add(new WebCustomer(customer));
		}
		return webcustomers;
	}
	
	
	public Customer convertToCustomer(){
		Customer customer=new Customer(this.id,this.custName, this.password);
		return customer;
	}
	
	public static Collection<Customer> convertToCustomers(Collection<WebCustomer> webcustomers){
		Collection<Customer> customers=new HashSet<>();
		for(WebCustomer webCustomer:webcustomers){
			customers.add(webCustomer.convertToCustomer());
		}
		return customers;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "WebCustomer [id=" + id + ", custName=" + custName
				+ ", password=" + password + "]";
	}
	
	
	

}
