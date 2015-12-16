package net.brothers.sharegoods.webservice;

import com.brothers.sharegoods.database.DatabaseAgent;
import com.brothers.sharegoods.model.RelationBean;

import net.sf.json.*;

import java.util.*;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

@Path("/relation")
public class RelationService {
	private DatabaseAgent db = new DatabaseAgent();
	
	@GET
	@Path("u1/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getRelationByUser1(@PathParam("id") String id) {
		List<RelationBean> temp = db.getRelationByUser1(Integer.parseInt(id));
		JSONArray r = JSONArray.fromObject(temp);
		return r.toString();
	}
	
	@GET
	@Path("u2/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getRelationByUser2(@PathParam("id") String id) {
		List<RelationBean> temp = db.getRelationByUser2(Integer.parseInt(id));
		JSONArray r = JSONArray.fromObject(temp);
		return r.toString();
	}
	
	@GET
	@Path("user1/{id1}/user2/{id2}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getRelationByUser2(@PathParam("id1") String id1, @PathParam("id2") String id2) {
		RelationBean temp = db.getRelationByAllUser(Integer.parseInt(id1), Integer.parseInt(id2));
		JSONObject r = JSONObject.fromObject(temp);
		return r.toString();
	}
	
	@GET
	@Path("delete/user1/{id1}/user2/{id2}")
	@Produces(MediaType.APPLICATION_JSON)
	public String deleteRelation(@PathParam("id1") String id1, @PathParam("id2") String id2) {
		RelationBean temp = new RelationBean();
		temp.setUser1Id(Integer.parseInt(id1));
		temp.setUser2Id(Integer.parseInt(id2));
		int result = db.deleteRelation(temp);
		return "" + result;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String createRelation(String relationJSON) {
		RelationBean temp = (RelationBean)JSONObject.toBean(JSONObject.fromObject(relationJSON), RelationBean.class);
		int status = db.createRelation(temp);
		return ""+status;
	}
}
