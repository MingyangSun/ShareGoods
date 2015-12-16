package net.brothers.sharegoods.webservice;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.List;

import com.brothers.sharegoods.database.DatabaseAgent;
import com.brothers.sharegoods.model.PhotoTagBean;

@Path("/phototag")
public class PhotoTagService {
	private DatabaseAgent db = new DatabaseAgent();
	
	@GET
	@Path("p/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getPhotoTagByPhotoId(@PathParam("id") String id) {
		List<PhotoTagBean> temp = db.getPhotoTag(Integer.parseInt(id));
		JSONArray r = JSONArray.fromObject(temp);
		return r.toString();
	}
	
	@GET
	@Path("t/{tagname}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getPhotoTagByTagId(@PathParam("tagname") String tagname) {
		List<PhotoTagBean> temp = db.getPhotoTag(tagname);
		JSONArray r = JSONArray.fromObject(temp);
		return r.toString();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String createPhotoTag(String photoTagJSON) {
		PhotoTagBean temp = (PhotoTagBean)JSONObject.toBean(JSONObject.fromObject(photoTagJSON), PhotoTagBean.class);
		int status = db.createPhotoTag(temp);
		return ""+status;
	}
}
