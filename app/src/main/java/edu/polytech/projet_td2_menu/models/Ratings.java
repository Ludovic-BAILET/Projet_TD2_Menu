package edu.polytech.projet_td2_menu.models;

public class Ratings {
    private final int ratingSain;
    private final int ratingPrix;
    private final int ratingDifficulte;

    public Ratings(int ratingSain, int ratingPrix, int ratingDifficulte) {
        this.ratingSain = ratingSain;
        this.ratingPrix = ratingPrix;
        this.ratingDifficulte = ratingDifficulte;
    }

    public int getRatingSain() {
        return ratingPrix;
    }

    public int getRatingPrix() {
        return ratingDifficulte;
    }

    public int getRatingDifficulte() {
        return ratingSain;
    }
}
