package com.brothers.sharegoods.function;

import com.brothers.sharegoods.model.*;

import java.net.URI;
import java.util.*;

import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;

import net.sf.json.*;

public class ModelApplier {
	private static ClientConfig config = new ClientConfig();
	
	private static URI getBaseUri() {
		String uri = "http://localhost:8080/sharegood/rest";
		return UriBuilder.fromUri(uri).build();
	}
	
	public static String createUser(UserBean user) {
		String userJSON = JSONObject.fromObject(user).toString();
		Client client = ClientBuilder.newClient(config);
		WebTarget target = client.target(getBaseUri());
		String result = target.path("users").request().accept(MediaType.APPLICATION_JSON).post(Entity.entity(userJSON, MediaType.APPLICATION_JSON), String.class);
		return result;
	}
	
	public static String createPhoto(PhotoBean photo) {
		String photoJSON = JSONObject.fromObject(photo).toString();
		Client client = ClientBuilder.newClient(config);
		WebTarget target = client.target(getBaseUri());
		String result = target.path("photo").request().accept(MediaType.APPLICATION_JSON).post(Entity.entity(photoJSON, MediaType.APPLICATION_JSON), String.class);
		return result;
	}
	
	public static String createPhotoComment(PhotoCommentBean photoComment) {
		String photoCommentJSON = JSONObject.fromObject(photoComment).toString();
		Client client = ClientBuilder.newClient(config);
		WebTarget target = client.target(getBaseUri());
		String result = target.path("photocomment").request().accept(MediaType.APPLICATION_JSON).post(Entity.entity(photoCommentJSON, MediaType.APPLICATION_JSON), String.class);
		return result;
	}
	
	public static String createPhotoTag(PhotoTagBean photoTag) {
		String photoTagJSON = JSONObject.fromObject(photoTag).toString();
		Client client = ClientBuilder.newClient(config);
		WebTarget target = client.target(getBaseUri());
		String result = target.path("phototag").request().accept(MediaType.APPLICATION_JSON).post(Entity.entity(photoTagJSON, MediaType.APPLICATION_JSON), String.class);
		return result;
	}
	
	public static String createFans(FansBean fans) {
		String fansJSON = JSONObject.fromObject(fans).toString();
		Client client = ClientBuilder.newClient(config);
		WebTarget target = client.target(getBaseUri());
		String result = target.path("fans").request().accept(MediaType.APPLICATION_JSON).post(Entity.entity(fansJSON, MediaType.APPLICATION_JSON), String.class);
		return result;
	}
	
	public static String createRelation(RelationBean relation) {
		String relationJSON = JSONObject.fromObject(relation).toString();
		Client client = ClientBuilder.newClient(config);
		WebTarget target = client.target(getBaseUri());
		String result = target.path("relation").request().accept(MediaType.APPLICATION_JSON).post(Entity.entity(relationJSON, MediaType.APPLICATION_JSON), String.class);
		return result;
	}
	
	public static String createTag(TagBean tag) {
		String tagJSON = JSONObject.fromObject(tag).toString();
		Client client = ClientBuilder.newClient(config);
		WebTarget target = client.target(getBaseUri());
		String result = target.path("tag").request().accept(MediaType.APPLICATION_JSON).post(Entity.entity(tagJSON, MediaType.APPLICATION_JSON), String.class);
		return result;
	}
	
	public static String createLikedPhotot(LikedPhotoBean likedPhoto) {
		String likedPhotoJSON = JSONObject.fromObject(likedPhoto).toString();
		Client client = ClientBuilder.newClient(config);
		WebTarget target = client.target(getBaseUri());
		String result = target.path("likedphoto").request().accept(MediaType.APPLICATION_JSON).post(Entity.entity(likedPhotoJSON, MediaType.APPLICATION_JSON), String.class);
		return result;
	}
	
	public static UserBean getUserById(int id) {
		UserBean temp = new UserBean();
		Client client = ClientBuilder.newClient(config);
		WebTarget target = client.target(getBaseUri());
		String userJSON = target.path("users").path(""+id).request().accept(MediaType.APPLICATION_JSON).get(String.class);
		temp = (UserBean)JSONObject.toBean(JSONObject.fromObject(userJSON), UserBean.class);
		return temp;
	}
	
	public static UserBean getUserByUsernameAndPassword(String username, String password) {
		UserBean temp = new UserBean();
		Client client = ClientBuilder.newClient(config);
		WebTarget target = client.target(getBaseUri());
		String userJSON = target.path("users").path(""+username).path(""+password).request().accept(MediaType.APPLICATION_JSON).get(String.class);
		temp = (UserBean)JSONObject.toBean(JSONObject.fromObject(userJSON), UserBean.class);
		return temp;
	}
	
	public static List<UserBean> getSearchUser(String search) {
		List<UserBean> temp = new ArrayList<UserBean>();
		JSONObject json = new JSONObject();
		json.put("search", search);
		Client client = ClientBuilder.newClient(config);
		WebTarget target = client.target(getBaseUri());
		String result = target.path("users").path("search").request().accept(MediaType.APPLICATION_JSON).post(Entity.entity(json.toString(), MediaType.APPLICATION_JSON), String.class);
		JSONArray jsonArray = JSONArray.fromObject(result);
		for(int i = 0; i < jsonArray.size(); i++) {
			UserBean p = (UserBean)JSONObject.toBean(jsonArray.getJSONObject(i), UserBean.class);
			temp.add(p);
		}
		return temp;
	}
	
	public static PhotoBean getPhotoByPhotoId(int id) {
		PhotoBean temp = new PhotoBean();
		Client client = ClientBuilder.newClient(config);
		WebTarget target = client.target(getBaseUri());
		String photoJSON = target.path("photo").path(""+id).request().accept(MediaType.APPLICATION_JSON).get(String.class);
		temp = (PhotoBean)JSONObject.toBean(JSONObject.fromObject(photoJSON), PhotoBean.class);
		return temp;
	}
	
	public static List<PhotoBean> getPhotoByUserId(int id) {
		List<PhotoBean> temp = new ArrayList<PhotoBean>();
		Client client = ClientBuilder.newClient(config);
		WebTarget target = client.target(getBaseUri());
		String photoJSONArray = target.path("photo").path("u").path(""+id).request().accept(MediaType.APPLICATION_JSON).get(String.class);
		JSONArray jsonArray = JSONArray.fromObject(photoJSONArray);
		for(int i = 0; i < jsonArray.size(); i++) {
			PhotoBean p = (PhotoBean)JSONObject.toBean(jsonArray.getJSONObject(i), PhotoBean.class);
			temp.add(p);
		}
		return temp;
	}
	
	public static List<PhotoBean> getTopPhoto(int number) {
		List<PhotoBean> temp = new ArrayList<PhotoBean>();
		Client client = ClientBuilder.newClient(config);
		WebTarget target = client.target(getBaseUri());
		String photoJSONArray = target.path("photo").path("top").path(""+number).request().accept(MediaType.APPLICATION_JSON).get(String.class);
		JSONArray jsonArray = JSONArray.fromObject(photoJSONArray);
		for(int i = 0; i < jsonArray.size(); i++) {
			PhotoBean p = (PhotoBean)JSONObject.toBean(jsonArray.getJSONObject(i), PhotoBean.class);
			temp.add(p);
		}
		return temp;
	}
	
	public static String updateLikedPhoto(int photoId, int number) {
		Client client = ClientBuilder.newClient(config);
		WebTarget target = client.target(getBaseUri());
		String temp = target.path("photo").path("update").path(""+photoId).path("likedphoto").path(""+number).request().accept(MediaType.APPLICATION_JSON).get(String.class);
		return temp;
	}
	
	public static List<PhotoBean> getSearchPhoto(String search) {
		List<PhotoBean> temp = new ArrayList<PhotoBean>();
		JSONObject json = new JSONObject();
		json.put("search", search);
		Client client = ClientBuilder.newClient(config);
		WebTarget target = client.target(getBaseUri());
		String result = target.path("photo").path("search").request().accept(MediaType.APPLICATION_JSON).post(Entity.entity(json.toString(), MediaType.APPLICATION_JSON), String.class);
		JSONArray jsonArray = JSONArray.fromObject(result);
		for(int i = 0; i < jsonArray.size(); i++) {
			PhotoBean p = (PhotoBean)JSONObject.toBean(jsonArray.getJSONObject(i), PhotoBean.class);
			temp.add(p);
		}
		return temp;
	}
	
	public static List<PhotoCommentBean> getCommentByPhotoId(int id) {
		List<PhotoCommentBean> temp = new ArrayList<PhotoCommentBean>();
		Client client = ClientBuilder.newClient(config);
		WebTarget target = client.target(getBaseUri());
		String commentJSONArray = target.path("photocomment").path("p").path(""+id).request().accept(MediaType.APPLICATION_JSON).get(String.class);
		JSONArray jsonArray = JSONArray.fromObject(commentJSONArray);
		for(int i = 0; i < jsonArray.size(); i++) {
			PhotoCommentBean p = (PhotoCommentBean)JSONObject.toBean(jsonArray.getJSONObject(i), PhotoCommentBean.class);
			temp.add(p);
		}
		return temp;
	}
	
	public static List<PhotoTagBean> getPhotoTagByPhotoId(int id) {
		List<PhotoTagBean> temp = new ArrayList<PhotoTagBean>();
		Client client = ClientBuilder.newClient(config);
		WebTarget target = client.target(getBaseUri());
		String photoTagJSONArray = target.path("phototag").path("p").path(""+id).request().accept(MediaType.APPLICATION_JSON).get(String.class);
		JSONArray jsonArray = JSONArray.fromObject(photoTagJSONArray);
		for(int i = 0; i < jsonArray.size(); i++) {
			PhotoTagBean p = (PhotoTagBean)JSONObject.toBean(jsonArray.getJSONObject(i), PhotoTagBean.class);
			temp.add(p);
		}
		return temp;
	}
	
	public static FansBean getFansByUserId(int id) {
		FansBean temp = new FansBean();
		Client client = ClientBuilder.newClient(config);
		WebTarget target = client.target(getBaseUri());
		String fansJson = target.path("fans").path(""+id).request().accept(MediaType.APPLICATION_JSON).get(String.class);
		temp = (FansBean)JSONObject.toBean(JSONObject.fromObject(fansJson), FansBean.class);
		return temp;
	}
	
	public static String updateFans(FansBean fansBean) {
		String fansJSON = JSONObject.fromObject(fansBean).toString();
		Client client = ClientBuilder.newClient(config);
		WebTarget target = client.target(getBaseUri());
		String result = target.path("fans").path("update").request().accept(MediaType.APPLICATION_JSON).post(Entity.entity(fansJSON, MediaType.APPLICATION_JSON), String.class);
		return result;
	}
	
	public static List<LikedPhotoBean> getLikedPhotoByPhotoId(int id) {
		List<LikedPhotoBean> temp = new ArrayList<LikedPhotoBean>();
		Client client = ClientBuilder.newClient(config);
		WebTarget target = client.target(getBaseUri());
		String likedPhotoJSONArray = target.path("likedphoto").path("p").path(""+id).request().accept(MediaType.APPLICATION_JSON).get(String.class);
		JSONArray jsonArray = JSONArray.fromObject(likedPhotoJSONArray);
		for(int i = 0; i < jsonArray.size(); i++) {
			LikedPhotoBean l = (LikedPhotoBean)JSONObject.toBean(jsonArray.getJSONObject(i), LikedPhotoBean.class);
			temp.add(l);
		}
		return temp;
	}
	
	public static List<LikedPhotoBean> getLikedPhotoByUserId(int id) {
		List<LikedPhotoBean> temp = new ArrayList<LikedPhotoBean>();
		Client client = ClientBuilder.newClient(config);
		WebTarget target = client.target(getBaseUri());
		String likedPhotoJSONArray = target.path("likedphoto").path("u").path(""+id).request().accept(MediaType.APPLICATION_JSON).get(String.class);
		JSONArray jsonArray = JSONArray.fromObject(likedPhotoJSONArray);
		for(int i = 0; i < jsonArray.size(); i++) {
			LikedPhotoBean l = (LikedPhotoBean)JSONObject.toBean(jsonArray.getJSONObject(i), LikedPhotoBean.class);
			temp.add(l);
		}
		return temp;
	}
	
	public static int deleteLikedPhoto(LikedPhotoBean likedphoto) {
		Client client = ClientBuilder.newClient(config);
		WebTarget target = client.target(getBaseUri());
		String result = target.path("likedphoto").path("delete").path("u").path(""+likedphoto.getUserId()).path("p").path(""+likedphoto.getPhotoId()).request().accept(MediaType.APPLICATION_JSON).get(String.class);
		return Integer.parseInt(result);
	}
	
	public static LikedPhotoBean getLikedPhotoByUserAndPhoto(int uid, int pid) {
		LikedPhotoBean temp =  null;
		Client client = ClientBuilder.newClient(config);
		WebTarget target = client.target(getBaseUri());
		String likedPhotoJSON = target.path("likedphoto").path("up").path(""+uid).path("up").path(""+pid).request().accept(MediaType.APPLICATION_JSON).get(String.class);
		temp = (LikedPhotoBean)JSONObject.toBean(JSONObject.fromObject(likedPhotoJSON), LikedPhotoBean.class);
		return temp;
	}
	
	public static List<RelationBean> getRelationByUser1Id(int id) {
		List<RelationBean> temp = new ArrayList<RelationBean>();
		Client client = ClientBuilder.newClient(config);
		WebTarget target = client.target(getBaseUri());
		String relationJSONArray = target.path("relation").path("u1").path(""+id).request().accept(MediaType.APPLICATION_JSON).get(String.class);
		JSONArray jsonArray = JSONArray.fromObject(relationJSONArray);
		for(int i = 0; i < jsonArray.size(); i++) {
			RelationBean r = (RelationBean)JSONObject.toBean(jsonArray.getJSONObject(i), RelationBean.class);
			temp.add(r);
		}
		return temp;
	}
	
	public static List<RelationBean> getRelationByUser2Id(int id) {
		List<RelationBean> temp = new ArrayList<RelationBean>();
		Client client = ClientBuilder.newClient(config);
		WebTarget target = client.target(getBaseUri());
		String relationJSONArray = target.path("relation").path("u2").path(""+id).request().accept(MediaType.APPLICATION_JSON).get(String.class);
		JSONArray jsonArray = JSONArray.fromObject(relationJSONArray);
		for(int i = 0; i < jsonArray.size(); i++) {
			RelationBean r = (RelationBean)JSONObject.toBean(jsonArray.getJSONObject(i), RelationBean.class);
			temp.add(r);
		}
		return temp;
	}
	
	public static RelationBean getRelationByAllUser(int user1Id, int user2Id) {
		RelationBean temp = null;
		Client client = ClientBuilder.newClient(config);
		WebTarget target = client.target(getBaseUri());
		String relationJSON = target.path("relation").path("user1").path(""+user1Id).path("user2").path(""+user2Id).request().accept(MediaType.APPLICATION_JSON).get(String.class);
		temp = (RelationBean)JSONObject.toBean(JSONObject.fromObject(relationJSON), RelationBean.class);
		return temp;
	}
	
	public static String deleteRelation(RelationBean relationBean) {
		Client client = ClientBuilder.newClient(config);
		WebTarget target = client.target(getBaseUri());
		String result = target.path("relation").path("delete").path("user1").path(""+relationBean.getUser1Id()).path("user2").path(""+relationBean.getUser2Id()).request().accept(MediaType.APPLICATION_JSON).get(String.class);
		return result;
	}
	
	public static TagBean getTag(String tagname) {
		TagBean temp = new TagBean();
		Client client = ClientBuilder.newClient(config);
		WebTarget target = client.target(getBaseUri());
		String tagJSON = target.path("tag").path(tagname).request().accept(MediaType.APPLICATION_JSON).get(String.class);
		temp = (TagBean)JSONObject.toBean(JSONObject.fromObject(tagJSON), TagBean.class);
		return temp;
	}
}
