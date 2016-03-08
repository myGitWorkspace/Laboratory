package ua.wordsmatcher;

import java.util.Iterator;

import org.junit.Test;

import ua.wordsmatcher.Tuple;
import ua.wordsmatcher.key.StringKey;
import ua.wordsmatcher.trie.RWayTrie;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class RWayTrieTest {

	@Test
	public void testAddingNewValue() {
		RWayTrie<StringKey, Integer> trie = new RWayTrie<StringKey, Integer>();
		trie.add(new Tuple<StringKey, Integer>(new StringKey("a"),0));
		assertEquals(trie.size(), 1);		
	}
	
	@Test(expected = IllegalArgumentException.class)  
	public void passNullParameterToGetException() {  
		RWayTrie<StringKey, Integer> trie = new RWayTrie<StringKey, Integer>();
		trie.add(null);
	} 
	
	@Test(expected = IllegalArgumentException.class)  
	public void passZeroLengthParameterToGetException() {  
		RWayTrie<StringKey, Integer> trie = new RWayTrie<StringKey, Integer>();
		trie.add(new Tuple<StringKey, Integer>(new StringKey(""),0));
	}
	
	@Test
	public void testGettingValue() {
		RWayTrie<StringKey, Integer> trie = new RWayTrie<StringKey, Integer>();
		StringKey sk = new StringKey("a");
		trie.add(new Tuple<StringKey, Integer>(sk,0));
		int k = trie.get(sk);		
		assertEquals(k, 0);
	}
	
	@Test
	public void testContainsValue() {
		RWayTrie<StringKey, Integer> trie = new RWayTrie<StringKey, Integer>();
		StringKey sk = new StringKey("a");
		trie.add(new Tuple<StringKey, Integer>(sk,0));
		assertTrue(trie.contains(sk));		
	}
	
	@Test
	public void testContainsNullValue() {
		RWayTrie<StringKey, Integer> trie = new RWayTrie<StringKey, Integer>();
		StringKey sk = new StringKey("a");
		trie.add(new Tuple<StringKey, Integer>(sk,0));
		assertFalse(trie.contains(null));		
	}
	
	@Test
	public void testDeletingValue() {
		RWayTrie<StringKey, Integer> trie = new RWayTrie<StringKey, Integer>();
		StringKey sk = new StringKey("a");
		trie.add(new Tuple<StringKey, Integer>(sk,0));
		trie.delete(sk);
		assertTrue((trie.size()==0));
	}
	
	@Test
	public void testDeletingNullValue() {
		RWayTrie<StringKey, Integer> trie = new RWayTrie<StringKey, Integer>();
		StringKey sk = new StringKey("a");
		trie.add(new Tuple<StringKey, Integer>(sk,0));
		assertFalse(trie.delete(null));
	}
	
	@Test
	public void testFindingPrefix() {
		RWayTrie<StringKey, Integer> trie = new RWayTrie<StringKey, Integer>();
		StringKey sk1 = new StringKey("abc");
		trie.add(new Tuple<StringKey, Integer>(sk1,3));
		StringKey sk2 = new StringKey("abcd");
		trie.add(new Tuple<StringKey, Integer>(sk2,4));
		StringKey sk3 = new StringKey("abcde");
		trie.add(new Tuple<StringKey, Integer>(sk3,5));
		Iterator<StringKey> iterator = trie.wordsWithPrefix(new StringKey("abcd")).iterator();

		assertEquals(iterator.next(), sk2);
		assertEquals(iterator.next(), sk3);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testNullArgumentInFindingPrefixToGetException() {
		RWayTrie<StringKey, Integer> trie = new RWayTrie<StringKey, Integer>();
		Iterable<StringKey> iterable = trie.wordsWithPrefix(null);
	}
	
	@Test
	public void testFindingAllWordsInTrie() {
		RWayTrie<StringKey, Integer> trie = new RWayTrie<StringKey, Integer>();
		StringKey sk1 = new StringKey("abc");
		trie.add(new Tuple<StringKey, Integer>(sk1,3));
		StringKey sk2 = new StringKey("abcd");
		trie.add(new Tuple<StringKey, Integer>(sk2,4));
		Iterator<StringKey> iterator = trie.words().iterator();

		assertEquals(iterator.next(), sk1);
		assertEquals(iterator.next(), sk2);
	}
	
	@Test
	public void testSize() {
		RWayTrie<StringKey, Integer> trie = new RWayTrie<StringKey, Integer>();
		assertEquals(trie.size(), 0);
		StringKey sk = new StringKey("a");
		trie.add(new Tuple<StringKey, Integer>(sk,0));
		assertEquals(trie.size(), 1);		
	}
	
}
