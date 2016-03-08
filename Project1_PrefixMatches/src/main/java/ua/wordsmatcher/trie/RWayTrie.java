package ua.wordsmatcher.trie;

import java.util.Queue;
import java.util.LinkedList;
import java.util.Iterator;

import ua.wordsmatcher.Tuple;
import ua.wordsmatcher.key.Key;
import ua.wordsmatcher.key.StringKey;

public class RWayTrie<K extends Key, V> implements Trie<K, V> {
	
	private Node<V> root;	
	private static int MAX_SIZE = 256;
	
	public void add(Tuple<K, V> tuple) {
		if (tuple == null || tuple.term.length() == 0)
			throw new IllegalArgumentException("Wrong argument!");		
		root = add(root, tuple.term, tuple.weight, 0);
	}
	
	private Node<V> add(Node<V> node, K term, V weight, int depth)
	{
		if (node == null) {
			node = new Node<V>();
			if(depth != 0)
				node.key = new Character( term.charAt(depth-1) );
		}			
		
		if (depth == term.length()){
			node.value = weight;
			return node;
		}
		
		char currChar = term.charAt(depth);
		node.links[currChar] = add(node.links[currChar], term, weight, depth+1);		
		
		return node;
	}
	
	public V get(K word) {
		Node<V> node = get(root, word, 0);
		
		if (node == null)
			return null;
		
		return node.value;
	}
	
	private Node<V> get(Node<V> node, K word, int depth) {
		if (node == null)
			return null;
		
		if(word == null)
			return root;
		
		if (depth == word.length())
			return node;
				
		char currChar = word.charAt(depth);
		
		return get(node.links[currChar], word, depth+1);
	}
	
    public boolean contains(K word) {
    	return (get(word) != null);
    }
    
    public boolean delete(K word) {
    	if(!this.contains(word))
    		return false;
    	root = delete(root, word, 0);
    	
    	return true;
    }
    
    private Node<V> delete(Node<V> node, K word, int depth) {
    	if (node == null)
    		return null;
    	
    	if (depth == word.length())
    		node.value = null;
    	else {
        	char currChar = word.charAt(depth);
        	node.links[currChar] = delete(node.links[currChar], word, depth+1);
    	}
    	
    	if (node.value != null)
    		return node;
    	
    	for (char currChar = 0; currChar < MAX_SIZE; currChar++)
    		if (node.links[currChar] != null)
    			return node;
    	
    	return null;
    }

    public Iterable<K> words() {
    	return wordsWithPrefix((K)(new StringKey("")));
    }
    
    public Iterable<K> wordsWithPrefix(K pref) {    	    
    	if(pref == null)
    		throw new IllegalArgumentException("Wrong argument!");
    	return new IterableImpl(pref);
    }
    
    private class IterableImpl implements Iterable<K> {
    	
    	private K pref;
    	public IterableImpl(K pref) {
    		this.pref = pref;
    	}
    	
    	@Override
    	public Iterator<K> iterator() {
    		return new RWayTrieIterator(this.pref);
    	}
    }
    
    public class RWayTrieIterator implements Iterator<K> {

    	private Queue<Node<V>> childs = new LinkedList<Node<V>>();
    	private Queue<K> childVals = new LinkedList<K>();
    	private Node<V> startNode;    	
    	
    	public RWayTrieIterator(K pref) {
    		    		
    		startNode = get(root, pref, 0);
    		if(startNode != null) {
        		childs.add(startNode);
        		childVals.add(pref);        		
    		}
    	}
    	
        public boolean hasNext() {
        	return !childs.isEmpty();            
        }

        public K next() {
        	
        	if(!hasNext()) {        		
        		return null;
        	}
        	
        	K nextValue = null;
        	boolean found = false;        	
        	while(!found) {
        		
            	Node<V> currNode = childs.poll();
            	K currVal = childVals.poll();
            	
            	if(currNode.value != null){
            		
            		found = true;
            		nextValue = currVal;            		
            	}
            	
            	for(char currChar = 0; currChar < MAX_SIZE; currChar++ ) {
            		Node<V> tempNode = currNode.links[currChar];
            		if(tempNode != null) {
            			childs.add(tempNode);
            			childVals.add((K)currVal.concat(currChar));
            		}
            	}
        	}

        	return nextValue;
        }
        
        public void remove() {
        	throw new UnsupportedOperationException();
        }
    }

    public int size() {
    	return size(root);
    }
    
    private int size(Node<V> node) {
    	if (node == null)
    		return 0;
    	
    	int counter = 0;
    	if (node.value != null)
    		counter++;
    	
	    for (char currChar = 0; currChar < MAX_SIZE; currChar++)
	    	counter += size(node.links[currChar]);
	    
	    return counter;
    }

	private static class Node<V> {
		private Node<V>[] links = new Node[MAX_SIZE];
		private V value;
		private Character key;
	}

}
