package edu.cnm.deepdive.diceware.service;

import java.util.ArrayList;
import java.util.List;
import java.util.random.RandomGenerator;
import java.util.stream.IntStream;
import org.springframework.stereotype.Service;

@Service
class PassphraseServiceImpl implements PassphraseService {

  private final List<String> wordList;
  private final RandomGenerator rng;

  PassphraseServiceImpl(WordListProvider provider,  RandomGenerator rng) {
    wordList = new ArrayList<>(provider.getWordList());
    this.rng = rng;
  }


  @Override
  public List<String> generate(int length) {
    int size = wordList.size();
    return IntStream.generate(() -> rng.nextInt(size))
        .limit(length)
        .mapToObj(wordList::get)
        .toList();

  }
}
