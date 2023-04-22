package TFIP.Day26v2.Services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import TFIP.Day26v2.Exception.GameNotFoundException;
import TFIP.Day26v2.Exception.ReviewNotFoundException;
import TFIP.Day26v2.Model.EditedReview;
import TFIP.Day26v2.Model.Game;
import TFIP.Day26v2.Model.Review;
import TFIP.Day26v2.Repo.Repo;

@Service
public class Services {
    @Autowired
    private Repo repo;

    public List<Game> getAllGames(Integer offset, Integer limit) {
        return repo.getAllGames(offset, limit);
    }

    public List<Game> getGamesByRank(Integer offset, Integer limit) {
        return repo.getGamesByRank(offset, limit);
    }

    public Game getGameById(String game_id) throws GameNotFoundException {
        return repo.getGameById(game_id);
    }

    public void newReview(Review review) throws GameNotFoundException {
        Game getGameById = null;
        String game_id = review.getID();
        getGameById = getGameById(game_id);
        review.setID(game_id);
        review.setPosted(LocalDateTime.now());
        review.setName(getGameById.getName());
        repo.newReview(review);
    }

    public Review getReviewById(String review_id) throws ReviewNotFoundException {
        return repo.getReviewById(review_id);
    }

    public Review UpdateReview(String review_id, EditedReview updateReview)
            throws ReviewNotFoundException, GameNotFoundException {

        Review review = getReviewById(review_id);
        List<EditedReview> newlist = new ArrayList<>();
        EditedReview oldR = new EditedReview();
        oldR.setComment(review.getComment());
        oldR.setPosted(review.getPosted());
        oldR.setRating(review.getRating());
        newlist.add(oldR);

        if (!review.getReviewList().isEmpty()) {
            newlist.addAll(review.getReviewList());
        }
        review.setReviewList(newlist);
        review.setComment(updateReview.getComment());
        review.setPosted(LocalDateTime.now());
        review.setRating(updateReview.getRating());
        System.out.println("not empty");

        repo.updateReview(review, review_id);

        return review;
    }

}
