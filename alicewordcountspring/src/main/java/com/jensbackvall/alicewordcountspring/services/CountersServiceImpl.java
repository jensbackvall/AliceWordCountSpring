package com.jensbackvall.alicewordcountspring.services;


import com.jensbackvall.alicewordcountspring.models.Letter;
import com.jensbackvall.alicewordcountspring.models.Word;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;

@Service("countersService")
public class CountersServiceImpl implements CountersService{

    public String getTextString() {
        String alice = "";
        try {
            URL urlName = new URL("http://www.gutenberg.org/files/11/11-0.txt");
            BufferedReader readFile = new BufferedReader(new InputStreamReader(urlName.openStream()));
            String currentLine = readFile.readLine();
            while (currentLine != null) {
                alice = alice + currentLine + " ";
                currentLine = readFile.readLine();
            }
            readFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return alice;
    }



    public int countAllLetters() {
        int numberOfCharacters = 0;
        String alice = getTextString();
        alice = alice.replaceAll("[^a-zA-Z]+","");
        for (char aLetter: alice.toCharArray()) {
            numberOfCharacters++;
        }
        return numberOfCharacters;
    }



    public int countSingleLetter(char letter) {
        String alice = getTextString();
        alice = alice.replaceAll("[^a-zA-Z]+","");
        int letterCounter = 0;
        for (char aLetter: alice.toCharArray()) {
            if (Character.toLowerCase(aLetter) == letter) {
                letterCounter++;
            }
        }
        return letterCounter;
    }



    public ArrayList<Word> wordNumberAndRankings() {
        String alice = getTextString();
        ArrayList<String> allOccurancesOfAllWords = new ArrayList<>();
        for (String thisWord: alice.split(" ")) {
            if (!thisWord.equals("")) {
                thisWord = thisWord.toLowerCase();
                thisWord = thisWord.replaceAll("--", " ");
                thisWord = thisWord.replaceAll("'s", "");
                thisWord = thisWord.replaceAll("[^a-zA-Z]+","");
                allOccurancesOfAllWords.add(thisWord);
            }
        }

        ArrayList<Word> allWords = new ArrayList<>();
        ArrayList<String> checkListSingleWords = new ArrayList<>();

        for (String theWord: allOccurancesOfAllWords) {
            int checkListCounter = 0;
            if (!checkListSingleWords.isEmpty()) {
                for (String aWord: checkListSingleWords) {
                    if (aWord.equals(theWord)) {
                        checkListCounter++;
                    }
                }
            }
            if (checkListCounter == 0) {
                checkListSingleWords.add(theWord);
            }
        }
        for (String word: checkListSingleWords) {
            int freq = Collections.frequency(allOccurancesOfAllWords, word);
            Word theWord = new Word(word);
            theWord.setOccurances(freq);
            allWords.add(theWord);
        }

        Collections.sort(allWords, new Word());
        Collections.reverse(allWords);

        return allWords;
    }


    public ArrayList<Letter> letterNumberAndPercentage() {
        int numberOfLetters;
        int totalNumberOfCharacters = countAllLetters();
        ArrayList<Letter> listOfAllLetterObjects = new ArrayList<>();
        for (char alphabet = 'a'; alphabet <= 'z'; alphabet++) {
            numberOfLetters = countSingleLetter(alphabet);
            double percentage = (((double)numberOfLetters / (double)totalNumberOfCharacters) * 100.0);
            DecimalFormat dfPercentage = new DecimalFormat("#.##");
            double thePercentage = Double.parseDouble(dfPercentage.format(percentage));
            Letter thisLetter = new Letter(alphabet);
            thisLetter.setOccurances(numberOfLetters);
            thisLetter.setPercentage(thePercentage);
            listOfAllLetterObjects.add(thisLetter);
        }
        return listOfAllLetterObjects;
    }
}
