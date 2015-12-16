package net.brothers.sharegoods.webservice;

import com.brothers.sharegoods.database.DatabaseAgent;
import com.brothers.sharegoods.model.FansBean;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;

import net.sf.json.*;

@Path("/fans")
public class FansService {
	private DatabaseAgent db = new DatabaseAgent();
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getFans(@PathParam("id") String id) {
		FansBean temp = db.getFans(Integer.parseInt(id));
		JSONObject r = JSONObject.fromObject(temp);
		return r.toString();
	}
	
	@POST
	@Path("update")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String updateFans(String fansJSON) {
		FansBean temp = (FansBean)JSONObject.toBean(JSONObject.fromObject(fansJSON), FansBean.class);
		int status = db.updateFans(temp);
		return ""+status;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String createFans(String fansJSON) {
		FansBean temp = (FansBean)JSONObject.toBean(JSONObject.fromObject(fansJSON), FansBean.class);
		int status = db.createFans(temp);
		return ""+status;
	}
}
