package com;
import model.Projects; 
//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 
//For JSON
import com.google.gson.*; 
//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document; 




@Path("/Projects") 
public class ProjectService 
{ 
	 Projects projectObj = new Projects(); 
	@GET
	@Path("/") 
	@Produces(MediaType.TEXT_HTML) 
	public String readProjects() 
	 { 
		 return projectObj.readProjects();
	 } 
	
	

	 
	@POST
	@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String addProjects(@FormParam("projectName") String projectName, 
	 @FormParam("owner") String owner, 
	 @FormParam("description") String description,
	 @FormParam("price") String price, 
	 @FormParam("email") String email,
	 @FormParam("phone") String phone) 
	{ 
	 String output = projectObj.addProjects(projectName, owner, description, price,email,phone); 
	return output; 
	}

	

	@PUT
	@Path("/") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String updateProjects(String projectData) 
	{ 
	//Convert the input string to a JSON object 
	 JsonObject projectObject = new JsonParser().parse(projectData).getAsJsonObject(); 
	//Read the values from the JSON object
	 String projectID = projectObject.get("projectID").getAsString(); 
	 String projectName = projectObject.get("projectName").getAsString(); 
	 String owner = projectObject.get("owner").getAsString(); 
	 String description = projectObject.get("description").getAsString(); 
	 String price = projectObject.get("price").getAsString();
	 String email = projectObject.get("email").getAsString(); 
	 String phone = projectObject.get("phone").getAsString();
	 String output = projectObj.updateProjects(projectID, projectName, owner, description, price,email,phone); 
	return output; 
	}
	
	 
	@DELETE
	@Path("/") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String deleteProjects(String projectData) 
	{ 
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(projectData, "", Parser.xmlParser()); 
	 
	//Read the value from the element <itemID>
	 String projectID = doc.select("projectID").text(); 
	 String output = projectObj.deleteProjects(projectID); 
	return output; 
	}

	
	
}

