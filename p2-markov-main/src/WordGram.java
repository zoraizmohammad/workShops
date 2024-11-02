/**
 * Mohammad Zoraiz - CS201
 * A WordGram object represents an immutable
 * sequence of words.
 * For use in Compsci 201, Duke University, Fall 2022
 * Add yourself as an author when you make edits
 * @author Brandon Fain
 * @author Owen Astrachan, revised Fall 2023
 */

public class WordGram {
	private String[] myWords; 	// Stores WordGram words
	private String myToString;	// Stores space separated words as one string
	private int myHash;			// Stores hash value of WordGram

	
	/**
	 * Constructor should generate a WordGram with size words
	 * beginning at the start index value of source array.
	 * Each element of source array should be a single word.
	 * @param source Source array, each element should be a single word
	 * @param start Index of first word for WordGram object
	 * @param size Number of elements in WordGram object
	 */
	public WordGram(String[] source, int start, int size) 
	{
		myToString = null;
		myHash = 0;
		myWords = new String[size];
		int localStart = 0;
		
		for(int k=start; k < start+size; k++) 
		{
			myWords[localStart] = source[k];
			localStart++;
		}
	}

	/**
	 * Returns number of words in this WordGram
	 * @return order of wordgram, number of words
	 */
	public int length() {
		return myWords.length;
	}
	


	/** 
	 * Returns true if o is also a wordgram with the
	 * same words, otherwise returns false 
	*/
	@Override
	public boolean equals(Object o) {
		if (! (o instanceof WordGram) || o == null)
		{
			return false;
		}
		
		WordGram other = (WordGram) o;

		if (this.myWords.length == other.myWords.length)
		{
			for (int x=0; x<myWords.length; x++)
			{
				if (!myWords[x].equals(other.myWords[x]))
				{
					return false;
				}
			}

			return true;
		}
		else
		{
			return false;
		}
		
	}


	/**
	 * Returns a hashCode for WordGram object equal to 
	 * the hashCode of the space separated words stored in 
	 * the WordGram.
	 */
	@Override
	public int hashCode() 
	{
		if (myHash == 0)
		{
			myHash = this.toString().hashCode();
		}
		return myHash;
	}


	/**
	 * Return a new WordGram of the same length as this WordGram 
	 * consisting of words 1 through length-1 of this WordGram
	 * followed by last. Does NOT mutate this WordGram.
	 * @param last added as last string of returned WordGram
	 * Should be a single word
	 * @return new WordGram
	 */
	public WordGram shiftAdd(String last) 
	{
		WordGram wg = new WordGram(myWords, 0, myWords.length);

		for (int x=0; x<myWords.length-1; x++)
		{
			wg.myWords[x] = wg.myWords[x+1];
		}

		wg.myWords[wg.myWords.length-1] = last;

		return wg;
	}


	/**
	 * Returns space separated words stored in the WordGram
	 * as a single String.
	 */

	@Override
	public String toString() {
		if (myToString == null)
		{
			myToString = String.join(" ",myWords);
		}
		return myToString;
	}
}
