package TFIP.Day26v2.Controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import TFIP.Day26v2.Exception.GameNotFoundException;
import TFIP.Day26v2.Exception.ReviewNotFoundException;
import TFIP.Day26v2.Model.EditedReview;
import TFIP.Day26v2.Model.Game;
import TFIP.Day26v2.Model.Review;
import TFIP.Day26v2.Model.ShowGames;
import TFIP.Day26v2.Services.Services;
import jakarta.validation.Valid;

@RestController
public class GameController {

    @Autowired
    private Services services;

    @GetMapping("/games")
    public ResponseEntity<String> showAllGames(@RequestParam(defaultValue = "0") Integer offset,
            @RequestParam(defaultValue = "25") Integer limit) {

        List<Game> getAllGames = services.getAllGames(offset, limit);
        ShowGames allGames = new ShowGames();
        allGames.setAllGames(getAllGames);
        allGames.setOffset(offset);
        allGames.setLimit(limit);
        allGames.setTotal(getAllGames.size());
        allGames.setTimestamp(LocalDate.now());

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(allGames.toJson().toString());

    }

    @GetMapping("/games/rank")
    public ResponseEntity<String> showAllGamesByRank(@RequestParam(defaultValue = "0") Integer offset,
            @RequestParam(defaultValue = "25") Integer limit) {

        List<Game> getAllGamesByRank = services.getGamesByRank(offset, limit);
        ShowGames allGames = new ShowGames();
        allGames.setAllGames(getAllGamesByRank);
        allGames.setOffset(offset);
        allGames.setLimit(limit);
        allGames.setTotal(getAllGamesByRank.size());
        allGames.setTimestamp(LocalDate.now());

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(allGames.toJson().toString());

    }

    @GetMapping("/games/{game_id}")
    public ResponseEntity<String> showGameById(@PathVariable String game_id) {

        Game getGameById = null;
        try {
            getGameById = services.getGameById(game_id);
            getGameById.setTimestamp(LocalDateTime.now());
        } catch (GameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(e.toString());
        }

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(getGameById.toJsonId().build().toString());
    }

    @PostMapping(path = "/review", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<String> postReview(@ModelAttribute @Valid Review review, BindingResult bindings) {

        if (bindings.hasErrors()) {
            String errorMessages = bindings.getAllErrors().stream()
                    .map(ObjectError::getDefaultMessage)
                    .collect(Collectors.joining(","));

            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("Error:" + errorMessages);
        }

        try {
            services.newReview(review);
        } catch (GameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(e.toString());
        }
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(review.toJson().toString());
    }

    @PutMapping(path = "/review/{review_id}")
    public ResponseEntity updateReview(@PathVariable String review_id, @Valid @RequestBody EditedReview updateReview, BindingResult bindings) {


        if (bindings.hasErrors()) {
            String errorMessages = bindings.getAllErrors().stream()
                    .map(ObjectError::getDefaultMessage)
                    .collect(Collectors.joining(","));

            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("Error:" + errorMessages);
        }

        try {
            Review review = services.UpdateReview(review_id, updateReview);
        } catch (ReviewNotFoundException | GameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(e.toString());
        }

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body("Updated");

    }

    @GetMapping("/review/{review_id}")
    public ResponseEntity<String> showEditedReview(@PathVariable String review_id) {

        Review review = null;
        try {
            review = services.getReviewById(review_id);
            review.setTimestamp(LocalDateTime.now());
        } catch (ReviewNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(e.toString());
        }

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(review.toJsonEdited().toString());
    }

    @GetMapping("/review/{review_id}/history")
    public ResponseEntity<String> showEditedReviewHistory(@PathVariable String review_id) {

        Review review = null;
        try {
            review = services.getReviewById(review_id);
            review.setTimestamp(LocalDateTime.now());
        } catch (ReviewNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(e.toString());
        }

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(review.toJsonD().toString());
    }

    


}
