package ua.wordsmatcher.key;

public interface Key {
	int length();
	char charAt(int position);
	Key concat(char addChar);
}
