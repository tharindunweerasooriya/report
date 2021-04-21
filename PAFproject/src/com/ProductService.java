package com;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import model.Product;


@Path("/Products") 
public class ProductService {
	
	Product Obj = new Product(); 
	@GET
	@Path("/") 
	@Produces(MediaType.TEXT_HTML) 
	public String readProducts() 
	 { 
		 return Obj.readProducts();
	 } 
	
	

	 
	@POST
	@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String insertProduct
	(@FormParam("product_id") String product_id, 
	 @FormParam("product_name") String product_name, 
	 @FormParam("owner") String owner, 
	 @FormParam("description") String description, 
	@FormParam("price") String price,
	@FormParam("email") String email)
	{ 
	 String output = Obj.insertProduct(product_id, product_name, owner, description, price, email); 
	return output; 
	}

	

	@PUT
	@Path("/") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String updateProduct(String itemData) 
	{ 
	//Convert the input string to a JSON object 
	 JsonObject itemObject = new JsonParser().parse(itemData).getAsJsonObject(); 
	//Read the values from the JSON object
	 String product_id = itemObject.get("product_id").getAsString(); 
	 String product_name = itemObject.get("product_name").getAsString(); 
	 String owner = itemObject.get("owner").getAsString(); 
	 String description = itemObject.get("description").getAsString(); 
	 String price = itemObject.get("price").getAsString(); 
	 String email = itemObject.get("email").getAsString();
	 
	 String output = Obj.updateProduct(product_id, product_name, owner, description, price, email); 
	return output; 
	}
	
	 
	@DELETE
	@Path("/") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String deleteProduct(String itemData) 
	{ 
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(itemData, "", Parser.xmlParser()); 
	 
	//Read the value from the element <itemID>
	 String productID = doc.select("product_id").text(); 
	 String output = Obj.deleteProduct(productID); 
	return output; 
	}

}
