package ua.wordsmatcher;

import java.util.Queue;
import java.util.LinkedList;
import java.util.Iterator;

import ua.wordsmatcher.key.StringKey;
import ua.wordsmatcher.trie.RWayTrie;
import ua.wordsmatcher.trie.Trie;

public class PrefixMatches {

	private Trie<StringKey, Integer> trie;
	public static final int MIN_WORD_LENGTH = 2;
	public static final int DEFAULT_NUMBER_WORDS_TO_SHOW = 3;
	
	public PrefixMatches() {
		this.trie = new RWayTrie<StringKey, Integer>();
	}	
	
	public void setTrie(Trie<StringKey, Integer> trie) {
		this.trie = trie;
	}
	
	public int add(String... strings) {
		if(strings == null || strings.length == 0)
			return 0;
		
		int counter = 0;
		if(strings.length == 1) {
			String[] words = strings[0].split("\\s");
			counter = addWords(words);
		} else {
			counter = addWords(strings);
		}
		
		return counter;
	}
	
	private int addWords(String[] words) {
		int counter = 0;
		for(int i=0; i<words.length; i++) {
			String word = words[i].trim();
			if(word.length() > MIN_WORD_LENGTH) {
				trie.add(new Tuple<StringKey, Integer>(new StringKey(word),word.length()));
				counter++;
			}
		}
		return counter;
	}
	
	public boolean contains(String word) {
		if(trie == null)
			return false;
		return trie.contains(new StringKey(word));
	}
	
	public boolean delete(String word) {
		return trie.delete(new StringKey(word));
	}
	
	public int size() {
		return trie.size();
	}
	
	public Iterable<String> wordsWithPrefix(String pref, int k) {
		if(pref == null || k < 0)
			throw new IllegalArgumentException("Wrong input parameter!");
		
		Queue<String> resultList = new LinkedList<String>();
		if(pref.length() >= MIN_WORD_LENGTH) {
			
			Iterator<StringKey> iterator = ((RWayTrie<StringKey,Integer>)trie).wordsWithPrefix(new StringKey(pref)).iterator();
			if(iterator.hasNext())
				resultList.add(iterator.next().toString());
						
			if(k > 1) {				
				for(int i=0; i<k; i++) {
					if(iterator.hasNext())
						resultList.add(iterator.next().toString());
					else
						break;
				}
			}			
		}
		
		return resultList;
	}
	
	public Iterable<String> wordsWithPrefix(String pref) {
		return wordsWithPrefix(pref, DEFAULT_NUMBER_WORDS_TO_SHOW);
	}

}
