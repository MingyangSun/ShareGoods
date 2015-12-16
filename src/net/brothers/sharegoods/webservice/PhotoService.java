package net.brothers.sharegoods.webservice;

import java.util.List;

import com.brothers.sharegoods.model.PhotoBean;
import com.brothers.sharegoods.database.DatabaseAgent;
import com.brothers.sharegoods.database.TestDB;


import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import net.sf.json.*;

@Path("/photo")
public class PhotoService {
	private DatabaseAgent db = new DatabaseAgent();
	
	@GET
	@Path("{id: [0-9]*}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getPhotoById(@PathParam("id") String id) {
		PhotoBean temp = db.getPhoto(Integer.parseInt(id));
		JSONObject r = (JSONObject)JSONObject.fromObject(temp);
		return r.toString();
	}
	
	@GET
	@Path("u/{id: [0-9]*}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getPhotoByUserId(@PathParam("id") String id) {
		List<PhotoBean> temp = db.getPhotoByUser(Integer.parseInt(id));
		JSONArray r = JSONArray.fromObject(temp);
		return r.toString();
	}
	
	@GET
	@Path("top/{number:[0-9]*}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getTopNPhoto(@PathParam("number") String number) {
		System.out.println("number" + number);
		List<PhotoBean> temp = TestDB.getTopPhoto(Integer.parseInt(number));
		//System.out.println("size:" + temp.size());
		JSONArray r = JSONArray.fromObject(temp);
		return r.toString();
	}
	
	@GET
	@Path("update/{id:[0-9]*}/likedphoto/{number:[0-9]*}")
	@Produces(MediaType.APPLICATION_JSON)
	public String updateLikedPhoto(@PathParam("id") String photoId,@PathParam("number") String number) {
		System.out.println("number" + number);
		int result = db.updateLikedPhoto(Integer.parseInt(photoId), Integer.parseInt(number));
		return "" + result;
	}
	
	@POST
	@Path("search")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON) 
	public String searchPhoto(String search) {
		JSONObject json = JSONObject.fromObject(search);

		String newSearch = json.getString("search");
		List<PhotoBean> temp = db.getSearchPhoto(newSearch);
		JSONArray r = JSONArray.fromObject(temp);
		return r.toString();
	}

	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON) 
	public String createPhoto(String photoJSON) {
		PhotoBean temp = (PhotoBean)JSONObject.toBean(JSONObject.fromObject(photoJSON), PhotoBean.class);
		int status = db.createPhoto(temp);
		return ""+status;
	}
}
