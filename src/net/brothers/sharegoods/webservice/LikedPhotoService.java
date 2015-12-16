package net.brothers.sharegoods.webservice;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import net.sf.json.*;

import java.util.*;

import com.brothers.sharegoods.database.DatabaseAgent;
import com.brothers.sharegoods.model.LikedPhotoBean;

@Path("/likedphoto")
public class LikedPhotoService {
	private DatabaseAgent db = new DatabaseAgent();
	
	@GET
	@Path("u/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getLikedPhotoByUser(@PathParam("id") String id) {
		List<LikedPhotoBean> temp = db.getLikedPhotoByUser(Integer.parseInt(id));
		JSONArray r = JSONArray.fromObject(temp);
		return r.toString();
	}
	
	@GET
	@Path("p/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getLikedPhotoByPhoto(@PathParam("id") String id) {
		List<LikedPhotoBean> temp = db.getLikedPhotoByPhoto(Integer.parseInt(id));
		JSONArray r = JSONArray.fromObject(temp);
		return r.toString();
	}
	
	@GET
	@Path("up/{uid}/up/{pid}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getLikedPhotoByUserAndPhoto(@PathParam("uid") String uid,@PathParam("pid") String pid) {
		LikedPhotoBean temp = db.getLikedPhotoByUserAndPhoto(Integer.parseInt(uid),Integer.parseInt(pid));
		JSONObject r = (JSONObject)JSONObject.fromObject(temp);
		return r.toString();
	}
	
	@GET
	@Path("delete/u/{uid}/p/{pid}")
	@Produces(MediaType.APPLICATION_JSON)
	public String deletLikedPhoto(@PathParam("uid") String uid,@PathParam("pid") String pid) {
		System.out.println("Service: " + uid + "," + pid);
		int temp = db.deleteLikedPhoto(Integer.parseInt(uid),Integer.parseInt(pid));
		return temp + "";
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String createLikedPhoto(String likedPhotoJSON) {
		LikedPhotoBean temp = (LikedPhotoBean)JSONObject.toBean(JSONObject.fromObject(likedPhotoJSON), LikedPhotoBean.class);
		int status = db.createLikedPhoto(temp);
		return ""+status;
	}
}
