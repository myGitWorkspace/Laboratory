package ua.wordsmatcher.key;


public class StringKey implements Key {
	private String key;
		
	public StringKey(String key) {
		this.key = key;
	}
	
	public StringKey() {
		this.key = "";
	}
	
	@Override
	public int length(){
		return key.length();
	}
	
	@Override
	public char charAt(int position) {
		return key.charAt(position);
	}
	
	@Override
	public StringKey concat(char addChar) {
		return new StringKey(key + addChar);
	}
	
	@Override
	public boolean equals(Object ob) {
		if(ob == null)
			return false;
		if(ob.getClass() != this.getClass())
			return false;
		StringKey sk = (StringKey)ob;
		if(sk.key == null || !sk.key.equals(this.key))
			return false;
		return true;
	}
	
	@Override
	public int hashCode() {
		return key.hashCode();
	}
	
	@Override
	public String toString() {
		return key;
	}
}
