package de.metalcon.middleware.backend.newsfeedserver;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;

import api.newsfeed.NewsFeed;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import de.metalcon.middleware.core.request.JsonRequest;
import de.metalcon.middleware.core.request.RequestTransaction;
import de.metalcon.middleware.domain.Muid;

@Component
public class NewsFeedServer implements NewsFeed {

    private static ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private BeanFactory beanFactory;

    public NewsFeedServer() {
        // TODO Auto-generated constructor stub
    }

    private Map<String, Object> fetchNewsFeedFromServer(
            Muid user_id,
            Muid poster_id,
            Boolean ownUpdates) {
        RequestTransaction tx = beanFactory.getBean(RequestTransaction.class);
        tx.request(new JsonRequest(
                "http://localhost:8080/Graphity-Server-0.1/read?" + "user_id="
                        + user_id + "&poster_id=" + poster_id + "&num_items=10"
                        + "&own_updates=" + (ownUpdates ? "1" : "0")));

        List<Map<String, Object>> modelNews =
                new LinkedList<Map<String, Object>>();

        String answer = (String) tx.recieve();

        JsonNode root = null;
        try {
            root = mapper.readValue(answer, JsonNode.class);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        for (JsonNode item : root.path("items")) {
            JsonNode verb = item.get("verb");
            JsonNode actor = item.get("actor");
            JsonNode object = item.get("object");
            JsonNode published = item.get("published");

            Map<String, String> modelActor = new HashMap<String, String>();
            modelActor.put("id", actor.get("id").textValue());
            modelActor.put("objectType", actor.get("objectType").textValue());
            modelActor.put("displayName", actor.get("displayName").textValue());

            Map<String, String> modelObject = new HashMap<String, String>();
            modelObject.put("id", object.get("id").textValue());
            modelObject.put("objectType", object.get("objectType").textValue());
            modelObject.put("message", object.get("message").textValue());
            modelObject.put("type", object.get("type").textValue());

            Map<String, Object> modelItem = new HashMap<String, Object>();
            modelItem.put("verb", verb.textValue());
            modelItem.put("actor", modelActor);
            modelItem.put("object", modelObject);
            modelItem.put("published", published.textValue());
            modelNews.add(modelItem);
        }

        ModelMap model = new ModelMap();
        model.addAttribute("userId", user_id);
        model.addAttribute("posterId", poster_id);
        model.addAttribute("ownUpdates", ownUpdates);
        model.addAttribute("news", modelNews);
        return model;
    }

    public Map<String, Object> getNewsFeed(
            Muid user_id,
            Muid poster_id,
            Boolean ownUpdates) {
        fetchNewsFeedFromServer(user_id, poster_id, ownUpdates);
        return fetchNewsFeedFromServer(user_id, poster_id, ownUpdates);
    }

    public String postNews(
            String userId,
            String posterId,
            Boolean ownUpdates,
            String formMessage) throws IOException {
        MultipartEntity entity = new MultipartEntity();
        entity.addPart("user_id", new StringBody(userId));
        entity.addPart("status_update_id",
                new StringBody(String.valueOf(System.currentTimeMillis())));
        entity.addPart("status_update_type", new StringBody("Plain"));
        entity.addPart("message", new StringBody(formMessage));
        entity.addPart("type", new StringBody("status_update"));

        String url = "http://localhost:8080/Graphity-Server-0.1/create";
        HttpPost httpPost = new HttpPost(url);
        httpPost.setEntity(entity);
        new DefaultHttpClient().execute(httpPost);

        return "redirect:/test/news/" + userId + "/" + posterId + "/"
                + ownUpdates.toString();
    }

    @Override
    public void createUser(
            String userId,
            String displayName,
            String profilePicturePath) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void createStatusUpdate(
            long timestamp,
            String user,
            Map<String, Object> content) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void createFriendship(String followingId, String followedId) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Map<String, Object> readStatusUpdates(
            String posterId,
            String userId,
            int numItems,
            boolean ownUpdates) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void deleteUser(String userId) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public boolean removeFriendship(String followingId, String followedId) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean deleteStatusUpdate(String user, String statusUpdateId) {
        // TODO Auto-generated method stub
        return false;
    }
}
