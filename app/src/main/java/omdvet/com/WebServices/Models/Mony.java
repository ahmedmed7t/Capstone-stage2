package omdvet.com.WebServices.Models;

import com.google.gson.annotations.SerializedName;

public class Mony{

	private Double cost;

	@SerializedName("mony_agel")
	private Double monyAgel;

	private int id;

	@SerializedName("is_pay")
	private String isPay;

	@SerializedName("client_id")
	private String clientId;

	public void setCost(Double cost){
		this.cost = cost;
	}

	public Double getCost(){
		return cost;
	}

	public void setMonyAgel(Double monyAgel){
		this.monyAgel = monyAgel;
	}

	public Double getMonyAgel(){
		return monyAgel;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setIsPay(String isPay){
		this.isPay = isPay;
	}

	public String getIsPay(){
		return isPay;
	}

	public void setClientId(String clientId){
		this.clientId = clientId;
	}

	public String getClientId(){
		return clientId;
	}

	@Override
 	public String toString(){
		return 
			"Mony{" + 
			"cost = '" + cost + '\'' + 
			",mony_agel = '" + monyAgel + '\'' + 
			",id = '" + id + '\'' + 
			",is_pay = '" + isPay + '\'' + 
			",client_id = '" + clientId + '\'' + 
			"}";
		}
}
