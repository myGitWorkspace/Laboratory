package ua.wordsmatcher.trie;

import ua.wordsmatcher.Tuple;
import ua.wordsmatcher.key.Key;

public interface Trie<K extends Key, V> {

    public void add(Tuple<K,V> tuple);

    public boolean contains(K word);

    public boolean delete(K word);

    public Iterable<K> words();

    public Iterable<K> wordsWithPrefix(K pref);

    public int size();
}
