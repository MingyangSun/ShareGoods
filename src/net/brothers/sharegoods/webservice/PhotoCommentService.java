package net.brothers.sharegoods.webservice;


import java.util.List;

import com.brothers.sharegoods.model.PhotoCommentBean;
import com.brothers.sharegoods.database.DatabaseAgent;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import net.sf.json.*;

@Path("/photocomment")
public class PhotoCommentService {
	
	private DatabaseAgent db = new DatabaseAgent();
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getCommentByCommentId(@PathParam("id") String id) {
		PhotoCommentBean temp = db.getComment(Integer.parseInt(id));
		JSONObject r = JSONObject.fromObject(temp);
		return r.toString();
	}
	
	@GET
	@Path("p/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getCommentByUser1(@PathParam("id") String id) {
		List<PhotoCommentBean> temp = db.getCommentByPhoto(Integer.parseInt(id));
		JSONArray r = JSONArray.fromObject(temp);
		return r.toString();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String createComment(String photoCommentJSON) {
		PhotoCommentBean temp = (PhotoCommentBean)JSONObject.toBean(JSONObject.fromObject(photoCommentJSON), PhotoCommentBean.class);
		int r = db.createComment(temp);
		return ""+r;
	}
	
}
