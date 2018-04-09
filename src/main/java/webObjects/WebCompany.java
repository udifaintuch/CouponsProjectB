package webObjects;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import javaBeans.Company;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class WebCompany implements Serializable {
	private long id;    //represents the company id
	private String companyName;   //represents the company name
	private String password;     //represents the company password
	private String email;  //represents the company email

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	public WebCompany(){
		
	}
	
	
	public WebCompany(Company company){
		this.id=company.getId();
		this.companyName=company.getCompanyName();
		this.password=company.getPassword();
		this.email=company.getEmail();
	}
	
	public static Collection<WebCompany> convertToWebCompanies(Collection<Company> companies){
		Collection<WebCompany> webcompanies=new HashSet<>();
		for(Company company:companies){
			webcompanies.add(new WebCompany(company));
		}
		return webcompanies;
		
	}
	
	public Company convertToCompany(){
		Company company=new Company(this.id,this.companyName, this.password, this.email);
		return company;
	}
	
	public static Collection<Company> convertToCompanies(Collection<WebCompany> webcompanies){
		Collection<Company> companies=new HashSet<>();
		for(WebCompany webCompany:webcompanies){
			companies.add(webCompany.convertToCompany());
		}
		return companies;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getCompanyName() {
		return companyName;
	}


	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	@Override
	public String toString() {
		return "WebCompany [id=" + id + ", companyName=" + companyName
				+ ", password=" + password + ", email=" + email + "]";
	}
	
	
	
	
	
	
}
