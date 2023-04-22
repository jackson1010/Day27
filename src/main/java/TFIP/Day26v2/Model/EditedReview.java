package TFIP.Day26v2.Model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.json.Json;
import jakarta.json.JsonObjectBuilder;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class EditedReview {
    private String comment;
    @Min(value = 1, message = "Must be more than 0")
    @Max(value = 10, message = "Must be less than 11")
    private Integer rating;
    private LocalDateTime posted;
    private Integer cid;

    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
    public Integer getRating() {
        return rating;
    }
    public void setRating(Integer rating) {
        this.rating = rating;
    }
    public Integer getCid() {
        return cid;
    }
    public void setCid(Integer cid) {
        this.cid = cid;
    }
    public LocalDateTime getPosted() {
        return posted;
    }
    public void setPosted(LocalDateTime posted) {
        this.posted = posted;
    }
    public JsonObjectBuilder toJson() {
        return Json.createObjectBuilder()
        .add("comment", getComment())
        .add("rating",getRating())
        .add("posted", getPosted().toString());
    }

    

    

}
