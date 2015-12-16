package net.brothers.sharegoods.webservice;

import javax.ws.rs.Path;
import javax.ws.rs.POST;
import javax.ws.rs.GET;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import net.sf.json.*;

import com.brothers.sharegoods.model.TagBean;
import com.brothers.sharegoods.database.DatabaseAgent;

@Path("/tag")
public class TagService {
	private DatabaseAgent db = new DatabaseAgent();
	
	@GET
	@Path("{tagname}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getTag(@PathParam("tagname") String tagname) {
		TagBean temp = db.getTag(tagname);
		JSONObject r = JSONObject.fromObject(temp);
		return r.toString();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String createTag(String tagJSON) {
		TagBean temp = (TagBean)JSONObject.toBean(JSONObject.fromObject(tagJSON), TagBean.class);
		int status = db.createTag(temp);
		return ""+status;
	}
}
