package br.com.vanderson.stackoverflow.db.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by vauruk on 24/03/16.
 */

public class StackOverflowQuestion {

    @SerializedName("is_answered")
    private boolean is_answered;
    @SerializedName("view_count")
    private Integer view_count;
    @SerializedName("answer_count")
    private Integer answer_count;
    @SerializedName("score")
    private Integer score;
    @SerializedName("last_activity_date")
    private Long last_activity_date;
    @SerializedName("creation_date")
    private Long creation_date;
    @SerializedName("question_id")
    private Integer question_id;
    @SerializedName("link")
    private String link;
    @SerializedName("title")
    private String title;
    @SerializedName("owner")
    private Owner owner;
    @SerializedName("tags")
    private List<String> tags;


    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public boolean is_answered() {
        return is_answered;
    }

    public void setIs_answered(boolean is_answered) {
        this.is_answered = is_answered;
    }

    public Integer getView_count() {
        return view_count;
    }

    public void setView_count(Integer view_count) {
        this.view_count = view_count;
    }

    public Integer getAnswer_count() {
        return answer_count;
    }

    public void setAnswer_count(Integer answer_count) {
        this.answer_count = answer_count;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Long getLast_activity_date() {
        return last_activity_date;
    }

    public void setLast_activity_date(Long last_activity_date) {
        this.last_activity_date = last_activity_date;
    }

    public Long getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(Long creation_date) {
        this.creation_date = creation_date;
    }

    public Integer getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(Integer question_id) {
        this.question_id = question_id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
