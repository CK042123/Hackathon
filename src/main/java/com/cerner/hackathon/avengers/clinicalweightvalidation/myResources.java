package com.cerner.hackathon.avengers.clinicalweightvalidation;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONObject;

@Path("weight")
public class myResources {
	@Path("getWeight")
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public String getWeightDetails() {
   		DataBaseConnect db = new DataBaseConnect();
   		return(db.getWeightOfPatient().toString());
    }
    
    @Path("addWeight")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public String setClinicalWeightValidationAttributes(final ClinicalWeightValidationObject cwv)
    {   	
    	DataBaseConnect db = new DataBaseConnect();
    	return(db.addWeight(cwv).toString());
    }
}
