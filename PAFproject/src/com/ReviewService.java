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


import model.Review;


@Path("/Reviews") 
public class ReviewService {
	
	Review Obj = new Review(); 
	@GET
	@Path("/") 
	@Produces(MediaType.TEXT_HTML) 
	public String readItems() 
	 { 
		 return Obj.readItems();
	 } 
	
	

	 
	@POST
	@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String insertItem
	(@FormParam("project_id") String project_id, 
	 @FormParam("project_name") String project_name, 
	 @FormParam("owner") String owner, 
	 @FormParam("description") String description, 
	@FormParam("price") String price,
	@FormParam("email") String email,
	@FormParam("phone") String phone,
	@FormParam("review") String review,
	@FormParam("acceptance") String acceptance)
	{ 
	 String output = Obj.insertItem(project_id, project_name, owner, description, price, email, phone, review, acceptance); 
	return output; 
	}

	

	@PUT
	@Path("/") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String updateItem(String itemData) 
	{ 
	//Convert the input string to a JSON object 
	 JsonObject itemObject = new JsonParser().parse(itemData).getAsJsonObject(); 
	//Read the values from the JSON object
	 String project_id = itemObject.get("project_id").getAsString(); 
	 String project_name = itemObject.get("project_name").getAsString(); 
	 String owner = itemObject.get("owner").getAsString(); 
	 String description = itemObject.get("description").getAsString(); 
	 String price = itemObject.get("price").getAsString(); 
	 String email = itemObject.get("email").getAsString();
	 String phone = itemObject.get("phone").getAsString();
	 String review = itemObject.get("review").getAsString();
	 String acceptance = itemObject.get("acceptance").getAsString();
	 
	 String output = Obj.updateItem(project_id, project_name, owner, description, price, email, phone, review, acceptance); 
	return output; 
	}
	
	 
	@DELETE
	@Path("/") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String deleteItem(String itemData) 
	{ 
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(itemData, "", Parser.xmlParser()); 
	 
	//Read the value from the element <itemID>
	 String projectID = doc.select("project_id").text(); 
	 String output = Obj.deleteItem(projectID); 
	return output; 
	}

}
