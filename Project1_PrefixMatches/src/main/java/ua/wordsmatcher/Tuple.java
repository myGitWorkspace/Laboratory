package ua.wordsmatcher;

import ua.wordsmatcher.key.Key;

public class Tuple<K extends Key, V> {
	public final K term;
	public final V weight;
	public Tuple(K term, V weight) {
		this.term = term;
		this.weight = weight;
	}
	
	@Override
	public boolean equals(Object object2) {
		if(object2 == null)
			return false;
		if(object2.getClass() != this.getClass())
			return false;
		Tuple<K,V> tuple2 = (Tuple<K,V>)object2;
		return (tuple2.term.equals(this.term) && tuple2.weight.equals(this.weight));
	}
}
