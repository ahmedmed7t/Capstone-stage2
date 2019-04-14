package omdvet.com.WebServices.Models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class DetailsOrder{

	@SerializedName("quantity")
	private String quantity;

	@SerializedName("product_id")
	private String productId;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("billes_id")
	private String billesId;

	@SerializedName("id")
	private int id;

	@SerializedName("client_id")
	private String clientId;

	@SerializedName("products")
	private DetailsProduct detailsProducts;

	public void setQuantity(String quantity){
		this.quantity = quantity;
	}

	public String getQuantity(){
		return quantity;
	}

	public void setProductId(String productId){
		this.productId = productId;
	}

	public String getProductId(){
		return productId;
	}

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public void setBillesId(String billesId){
		this.billesId = billesId;
	}

	public String getBillesId(){
		return billesId;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setClientId(String clientId){
		this.clientId = clientId;
	}

	public String getClientId(){
		return clientId;
	}

	public DetailsProduct getDetailsProducts() {
		return detailsProducts;
	}

	public void setDetailsProducts(DetailsProduct detailsProducts) {
		this.detailsProducts = detailsProducts;
	}
}