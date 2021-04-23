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

import model.Funds;

@Path("/Funds") 
public class FundService {

	Funds fundObj = new Funds(); 
	@GET
	@Path("/") 
	@Produces(MediaType.TEXT_HTML) 
	public String readFunds() 
	 { 
		 return fundObj.readFunds();
	 } 
	
	
	@POST
	@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String addFund(@FormParam("admin_ID") String admin_ID, 
	 @FormParam("product_ID") String product_ID, 
	 @FormParam("funds") String funds)
	 
	{ 
	 String output = fundObj.addFund(admin_ID, product_ID, funds); 
	return output; 
	}

	

	@PUT
	@Path("/") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String updateFund(String fundData) 
	{ 
	//Convert the input string to a JSON object 
	 JsonObject fundObject = new JsonParser().parse(fundData).getAsJsonObject(); 
	//Read the values from the JSON object
	 String fund_ID = fundObject.get("fund_ID").getAsString(); 
	 String admin_ID = fundObject.get("admin_ID").getAsString(); 
	 String product_ID = fundObject.get("product_ID").getAsString(); 
	 String funds = fundObject.get("funds").getAsString(); 
	 
	 String output = fundObj.updateFunds(fund_ID, admin_ID, product_ID, funds); 
	return output; 
	}
	
	 
	@DELETE
	@Path("/") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String deleteFund(String fundData) 
	{ 
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(fundData, "", Parser.xmlParser()); 
	 
	//Read the value from the element <itemID>
	 String fund_ID = doc.select("fund_ID").text(); 
	 String output = fundObj.deleteFunds(fund_ID); 
	return output; 
	}
}
