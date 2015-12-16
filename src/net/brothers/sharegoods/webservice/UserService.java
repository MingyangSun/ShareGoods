package net.brothers.sharegoods.webservice;

import java.util.List;

import com.brothers.sharegoods.model.PhotoBean;
import com.brothers.sharegoods.model.UserBean;
import com.brothers.sharegoods.database.DatabaseAgent;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import net.sf.json.*;

@Path("/users")
public class UserService {
	
	private DatabaseAgent db = new DatabaseAgent();
	
	@GET
	@Path("{id:[0-9]*}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getUserById(@PathParam("id") String id) {
		UserBean ub = db.getUser(Integer.parseInt(id));
		JSONObject r = (JSONObject)JSONObject.fromObject(ub);
		return r.toString();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String createUser(String userJson) {
		UserBean temp =(UserBean)JSONObject.toBean( JSONObject.fromObject(userJson),UserBean.class);
		int status = db.createUser(temp);
		return ""+status;
	}
	
	@GET
	@Path("{email}/{password}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getUserByUsernameAndPassword(@PathParam("email") String email, @PathParam("password") String password) {
		UserBean temp = db.getUser(email, password);
		JSONObject r = (JSONObject)JSONObject.fromObject(temp);
		return r.toString();
	}
	
	@GET
	@Path("{username:[a-zA-Z][a-zA-Z_0-9]*}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getUserByUsername(@PathParam("username") String username) {
		UserBean temp = db.getUser(username);
		JSONObject r = (JSONObject)JSONObject.fromObject(temp);
		return r.toString();
	}
	
	@POST
	@Path("search")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON) 
	public String searchPhoto(String search) {
		JSONObject json = JSONObject.fromObject(search);
		String newSearch = json.getString("search");
		List<UserBean> temp = db.getSearchUser(newSearch);
		JSONArray r = JSONArray.fromObject(temp);
		return r.toString();
	}
}
