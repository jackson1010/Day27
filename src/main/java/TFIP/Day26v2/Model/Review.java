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
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class Review implements Serializable {

    @NotBlank(message = "What is your name?")
    private String user;
    @Min(value = 1, message = "Must be more than 0")
    @Max(value = 10, message = "Must be less than 11")
    private Integer rating;
    private String comment;
    private String ID;
    private LocalDateTime posted;
    private String name;
    private List<EditedReview> reviewList= new ArrayList<>();

    private Boolean edited; 
    private LocalDateTime timestamp;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
    public LocalDateTime getPosted() {
        return posted;
    }

    public void setPosted(LocalDateTime posted) {
        this.posted = posted;
    }    


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getID() {
        return ID;
    }

    public void setID(String iD) {
        ID = iD;
    }

    public List<EditedReview> getReviewList() {
        return reviewList;
    }

    public void setReviewList(List<EditedReview> reviewList) {
        this.reviewList = reviewList;
    }

    public Boolean getEdited() {
        return edited;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }


    public void setEdited(Boolean edited) {
        this.edited = edited;
    }

    public JsonObject toJson() {
        return Json.createObjectBuilder()
                .add("user", getUser())
                .add("rating", getRating())
                .add("comment", getComment())
                .add("ID", getID())
                .add("posted", getPosted().toString())
                .add("name", getName())
                .build();
    }


    public JsonObject toJsonEdited() {
        setEdited(true);
        
        if(getReviewList().isEmpty()){
            setEdited(false);
        }
        return Json.createObjectBuilder()
                .add("user", getUser())
                .add("rating", getRating())
                .add("comment", getComment())
                .add("ID", getID())
                .add("posted", getPosted().toString())
                .add("name", getName())
                .add("edited", getEdited())
                .add("timestamp", getTimestamp().toString())
                .build();
    }

    public JsonObject toJsonD() {

        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        List<JsonObjectBuilder> listOfReviews = this.getReviewList()
        .stream()
        .map(g -> g.toJson())
        .toList();

        for(JsonObjectBuilder x :listOfReviews){
            arrayBuilder.add(x);
        }
    
        return Json.createObjectBuilder()
                .add("user", getUser())
                .add("rating", getRating())
                .add("comment", getComment())
                .add("ID", getID())
                .add("posted", getPosted().toString())
                .add("name", getName())
                .add("edited", arrayBuilder)
                .add("timestamp", getTimestamp().toString())
                .build();
    }


    



    





}
