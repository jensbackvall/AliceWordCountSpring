package com.jensbackvall.alicewordcountspring.models;

import java.util.Comparator;

public class Word implements Comparator<Word>, Comparable<Word> {

    private String theWord;
    private int occurances;
    private int ranking;

    public Word() {
    }

    public Word(String theWord) {
        this.theWord = theWord;
    }

    public String getTheWord() {
        return theWord;
    }

    public void setTheWord(String theWord) {
        this.theWord = theWord;
    }

    public int getOccurances() {
        return occurances;
    }

    public void setOccurances(int occurances) {
        this.occurances = occurances;
    }

    public int getRanking() {
        return ranking;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }

    @Override
    public int compare(Word w, Word w1) {
        return w.occurances - w1.occurances;
    }

    @Override
    public int compareTo(Word w) {
        return (this.theWord).compareTo(w.theWord);
    }
}
