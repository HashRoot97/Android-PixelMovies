package com.example.surbh_000.pixelmovies;

/**
 * Created by surbh_000 on 7/6/2016.
 */
public class MyDataProvider {
    private String title;
    private String vote_average;
    public MyDataProvider(String title,String vote_average){
        this.setTitle(title);
        this.setVote_average(vote_average);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getvote_average() {
        return vote_average;
    }

    public void setVote_average(String vote_average) {
        this.vote_average = vote_average;
    }
}
