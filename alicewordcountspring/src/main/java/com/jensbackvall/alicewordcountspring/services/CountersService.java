package com.jensbackvall.alicewordcountspring.services;

import com.jensbackvall.alicewordcountspring.models.Letter;
import com.jensbackvall.alicewordcountspring.models.Word;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

public interface CountersService {
    String getTextString();
    int countAllLetters();
    int countSingleLetter(char letter);
    ArrayList<Word> wordNumberAndRankings();
    ArrayList<Letter> letterNumberAndPercentage();
}
