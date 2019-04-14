package omdvet.com.WebServices.Models;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ClientOrder{

	@SerializedName("date")
	private String date;

	@SerializedName("product")
	private List<Product> product;

	@SerializedName("apiToken")
	private String apiToken;

	@SerializedName("cost")
	private float cost;

	@SerializedName("afterDiscount")
	private float afterDiscount;

	@SerializedName("type")
	private String type;

	@SerializedName("is_cach")
	private int isCach;

	@SerializedName("client_id")
	private int clientId;

	@SerializedName("emp_id")
	private int empId;

	public void setDate(String date){
		this.date = date;
	}

	public String getDate(){
		return date;
	}

	public void setProduct(List<Product> product){
		this.product = product;
	}

	public List<Product> getProduct(){
		return product;
	}

	public void setApiToken(String apiToken){
		this.apiToken = apiToken;
	}

	public String getApiToken(){
		return apiToken;
	}

	public void setCost(float cost){
		this.cost = cost;
	}

	public float getCost(){
		return cost;
	}

	public void setAfterDiscount(float afterDiscount){
		this.afterDiscount = afterDiscount;
	}

	public float getAfterDiscount(){
		return afterDiscount;
	}

	public void setType(String type){
		this.type = type;
	}

	public String getType(){
		return type;
	}

	public void setIsCach(int isCach){
		this.isCach = isCach;
	}

	public int getIsCach(){
		return isCach;
	}

	public void setClientId(int clientId){
		this.clientId = clientId;
	}

	public int getClientId(){
		return clientId;
	}

	public void setEmpId(int empId){
		this.empId = empId;
	}

	public int getEmpId(){
		return empId;
	}

	@Override
 	public String toString(){
		return 
			"ClientOrder{" + 
			"date = '" + date + '\'' + 
			",product = '" + product + '\'' + 
			",apiToken = '" + apiToken + '\'' + 
			",cost = '" + cost + '\'' +
			",afterDiscount = '" + afterDiscount + '\'' + 
			",type = '" + type + '\'' + 
			",is_cach = '" + isCach + '\'' + 
			",client_id = '" + clientId + '\'' + 
			",emp_id = '" + empId + '\'' + 
			"}";
		}
}