package ua.wordsmatcher;

import java.util.LinkedList;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import org.junit.Test;

import ua.wordsmatcher.PrefixMatches;
import ua.wordsmatcher.Tuple;
import ua.wordsmatcher.key.StringKey;
import ua.wordsmatcher.trie.RWayTrie;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class PrefixMatchesTest {

	@Test
	public void testAddingNewValue() {		
		PrefixMatches m = new PrefixMatches();
		assertEquals(m.add("abc"), 1);
		assertTrue(m.contains("abc"));		
	}
	
	@Test
	public void testAddingNewValues() {		
		PrefixMatches m = new PrefixMatches();
		assertEquals(m.add("abc", "abcd", "abce"), 3);
		assertTrue(m.contains("abc"));
		assertTrue(m.contains("abcd"));
		assertTrue(m.contains("abce"));
	}
	
	@Test
	public void testAddingNewValueWithMinWordLength() {	
		PrefixMatches m = new PrefixMatches();
		assertEquals(m.add("ab"), 0);
		assertFalse(m.contains("ab"));		
	}
	
	@Test
	public void testAddingNewValueWithTrie() {	
		PrefixMatches m = new PrefixMatches();
		RWayTrie<StringKey, Integer> trie = mock(RWayTrie.class);
		m.setTrie(trie);
		String strParam = "abc";
		m.add(strParam);
		verify(trie, times(1)).add( eq(new Tuple<StringKey, Integer>(new StringKey(strParam), strParam.length())) );		
	}
	
	@Test
	public void testAddingNullValue() {
		PrefixMatches m = new PrefixMatches();
		assertEquals(m.add(null), 0);
	}
	 
	@Test
	public void testAddingZeroLengthValue() {
		PrefixMatches m = new PrefixMatches();
		assertEquals(m.add(""), 0);
	}
	
	@Test
	public void testGetWordsByPrefix() {
		
		PrefixMatches p = new PrefixMatches();		
		p.add("abc","abcd","abce","abcde","abcdef");
		List<String> list = new LinkedList<String>();
		list.add("abc");
		assertEquals(p.wordsWithPrefix("abc", 1), list);
		list.add("abcd");
		list.add("abce");
		assertEquals(p.wordsWithPrefix("abc", 2), list);
		list.add("abcde");
		assertEquals(p.wordsWithPrefix("abc", 3), list);
		list.add("abcdef");
		assertEquals(p.wordsWithPrefix("abc", 4), list);
	}
	
	@Test
	public void testGetWordsByPrefixWithLessMinWordLength() {
		PrefixMatches p = new PrefixMatches();		
		p.add("abc","abcd","abce","abcde","abcdef");
		assertEquals(((Queue<String>)p.wordsWithPrefix("a", 1)).size(), 0);
	}
	
	@Test
	public void testGetWordsByPrefixWithMinWordLength() {
		PrefixMatches p = new PrefixMatches();		
		p.add("abc");
		assertEquals(((Queue<String>)p.wordsWithPrefix("ab", 1)).size(), 1);
	}
	
	@Test
	public void testGetWordsByPrefixWithGreaterMinWordLength() {
		PrefixMatches p = new PrefixMatches();		
		p.add("abcde");
		assertEquals(((Queue<String>)p.wordsWithPrefix("abcd", 1)).size(), 1);
	}
	
	@Test
	public void testGetWordsByPrefixWithTrie() {	
		PrefixMatches m = new PrefixMatches();
		RWayTrie<StringKey, Integer> trie = mock(RWayTrie.class);		
		m.setTrie(trie);
		String strParam = "abc";
		when(trie.wordsWithPrefix(new StringKey(strParam))).thenReturn(new ArrayList<StringKey>());
		m.wordsWithPrefix(strParam, 3);
		verify(trie, times(1)).wordsWithPrefix( eq(new StringKey(strParam)) );		
	}
	
	@Test
	public void testGetWordsByPrefixWithDefaultKParam() {
		
		PrefixMatches p = new PrefixMatches();		
		p.add("abc","abcd","abce","abcde","abcdef");
		List<String> list = new LinkedList<String>();
		list.add("abc");
		list.add("abcd");
		list.add("abce");
		list.add("abcde");
		assertEquals(p.wordsWithPrefix("abc"), list);
	}
	
	@Test
	public void testGetWordsByPrefixWithDefaultKParamWithTrie() {
		PrefixMatches m = new PrefixMatches();
		RWayTrie<StringKey, Integer> trie = mock(RWayTrie.class);		
		m.setTrie(trie);
		String strParam = "abc";
		when(trie.wordsWithPrefix(new StringKey(strParam))).thenReturn(new ArrayList<StringKey>());
		m.wordsWithPrefix(strParam);
		verify(trie, times(1)).wordsWithPrefix( eq(new StringKey(strParam)) );
	}
	
	@Test(expected = IllegalArgumentException.class)  
	public void passNullParameterAsPrefixToGetException() {
		PrefixMatches p = new PrefixMatches();
		p.wordsWithPrefix(null, 3);
	}
	
	@Test(expected = IllegalArgumentException.class)  
	public void passNegativeIntValueToGetException() {
		PrefixMatches p = new PrefixMatches();
		p.wordsWithPrefix("abc", -3);
	}
	
	@Test
	public void testContainsValue() {
		PrefixMatches m = new PrefixMatches();
		m.add("abc", "abcd");
		assertTrue(m.contains("abc"));
		assertTrue(m.contains("abcd"));
	}
	
	@Test
	public void testContainsValueWithTrie() {
		PrefixMatches m = new PrefixMatches();
		RWayTrie<StringKey, Integer> trie = mock(RWayTrie.class);		
		m.setTrie(trie);
		String strParam1 = "abc";
		String strParam2 = "abcd";
		m.add(strParam1, strParam2);
		m.contains(strParam1);
		verify(trie, times(1)).contains( eq(new StringKey(strParam1)) );
		m.contains(strParam2);
		verify(trie, times(1)).contains( eq(new StringKey(strParam2)) );
	}
	
	@Test
	public void testDeletingValue() {
		PrefixMatches m = new PrefixMatches();
		m.add("abc", "abcd");
		m.delete("abc");
		m.delete("abcd");
		assertFalse(m.contains("abc"));
		assertFalse(m.contains("abcd"));
	}
	
	@Test
	public void testDeletingValueWithTrie() {
		PrefixMatches m = new PrefixMatches();
		RWayTrie<StringKey, Integer> trie = mock(RWayTrie.class);		
		m.setTrie(trie);
		String strParam1 = "abc";
		String strParam2 = "abcd";
		m.add(strParam1, strParam2);
		m.delete(strParam1);
		verify(trie, times(1)).delete( eq(new StringKey(strParam1)) );
		m.delete(strParam2);
		verify(trie, times(1)).delete( eq(new StringKey(strParam2)) );
	}
	
	@Test
	public void testSize() {
		PrefixMatches m = new PrefixMatches();
		assertEquals(m.size(), 0);
		m.add("abc", "abcd");
		assertEquals(m.size(), 2);		
	}
	
	@Test
	public void testSizeWithTrie() {
		PrefixMatches m = new PrefixMatches();
		RWayTrie<StringKey, Integer> trie = mock(RWayTrie.class);		
		m.setTrie(trie);
		String strParam1 = "abc";
		String strParam2 = "abcd";		
		m.add(strParam1, strParam2);
		m.size();
		verify(trie, times(1)).size();
	}
}
