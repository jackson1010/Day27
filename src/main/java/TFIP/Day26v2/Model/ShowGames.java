package TFIP.Day26v2.Model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;

public class ShowGames implements Serializable{
    private List<Game> allGames;
    private Integer offset;
    private Integer limit;
    private Integer total;
    private LocalDate timestamp;

    public List<Game> getAllGames() {
        return allGames;
    }
    public void setAllGames(List<Game> allGames) {
        this.allGames = allGames;
    }
    public Integer getOffset() {
        return offset;
    }
    public void setOffset(Integer offset) {
        this.offset = offset;
    }
    public Integer getLimit() {
        return limit;
    }
    public void setLimit(Integer limit) {
        this.limit = limit;
    }
    public Integer getTotal() {
        return total;
    }
    public void setTotal(Integer total) {
        this.total = total;
    }
    public LocalDate getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(LocalDate timestamp) {
        this.timestamp = timestamp;
    }


    public JsonObject toJson(){
        
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        List<JsonObjectBuilder> listOfGames = this.getAllGames()
        .stream()
        .map(g -> g.toJson())
        .toList();

        for(JsonObjectBuilder x :listOfGames){
            arrayBuilder.add(x);
        }

        return Json.createObjectBuilder()
        //string
        // .add("games", getAllGames().toString())
        .add("games", arrayBuilder)
        .add("offset", getOffset())
        .add("limit", getLimit())
        .add("total", getTotal())
        .add("timestamp", getTimestamp().toString())
        .build();
    }
  


    

}
