package TFIP.Day26v2.Model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.bson.Document;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;


public class Game implements Serializable{
    private Integer gid;
    private String name;
    private Integer year;
    private Integer ranking;
    private Integer users_rated;
    private String url;
    private String image;
    private LocalDateTime timestamp;

    

    public Integer getGid() {
        return gid;
    }
    public void setGid(Integer gid) {
        this.gid = gid;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getYear() {
        return year;
    }
    public void setYear(Integer year) {
        this.year = year;
    }
    public Integer getRanking() {
        return ranking;
    }
    public void setRanking(Integer ranking) {
        this.ranking = ranking;
    }
    public Integer getUsers_rated() {
        return users_rated;
    }
    public void setUsers_rated(Integer users_rated) {
        this.users_rated = users_rated;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }


    public static Game createFromMongo(Document d) {
        Game game= new Game();
        game.setGid(d.getInteger("gid"));
        game.setName(d.getString("name"));
        game.setYear(d.getInteger("year"));
        game.setRanking(d.getInteger("ranking"));
        game.setUsers_rated(d.getInteger("users_rated"));
        game.setUrl(d.getString("url"));
        game.setImage(d.getString("image"));
        return game;
    }

    public JsonObjectBuilder toJson(){
        return Json.createObjectBuilder()
        .add("gid", getGid())
        .add("name", getName())
        .add("ranking", getRanking());
        
    }

    public JsonObjectBuilder toJsonId(){
        return Json.createObjectBuilder()
        .add("game_id", getGid())
        .add("name", getName())
         .add("year", getYear())
         .add("ranking", getRanking())
         .add("users_rated", getUsers_rated())
         .add("url", getUrl())
         .add("image",getImage())
         .add("timestamp",getTimestamp().toString());
    }

   

    
}
