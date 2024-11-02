//Mohammad Zoraiz
import java.util.*;

public class HashMarkov implements MarkovInterface 
{

    protected String[] myWords;		// Training text split into array of words 
	protected Random myRandom;		// Random number generator
	protected int myOrder;			// Length of WordGrams used
	protected static String END_OF_TEXT = "*** ERROR ***"; 
    protected HashMap<WordGram, List<String>> mainMap;


    public HashMarkov(int order) 
    {
        myOrder = order;
        myRandom = new Random();
        mainMap = new HashMap<>();
	}

    @Override
    public void setTraining(String text) 
    {
        myWords = text.split("\\s+");
        mainMap.clear();
        
        WordGram key = new WordGram(myWords,0,myOrder);
        WordGram currentWG = new WordGram(myWords,0,myOrder);

		for (int x = key.length(); x < myWords.length; x += 1) 
        {
            String currentWord = myWords[x];
			mainMap.putIfAbsent(currentWG, new ArrayList<String>());
            
            mainMap.get(currentWG).add(currentWord);
			currentWG = currentWG.shiftAdd(currentWord);

		}
    }

    @Override
    public List<String> getFollows(WordGram wgram) 
    {
        List<String> blankArray = new ArrayList<>();
        if (mainMap.containsKey(wgram))
        {
            return mainMap.get(wgram);
        }
        else return blankArray;
    }

    private String getNextWord(WordGram wgram) 
    {
		List<String> follows = getFollows(wgram);
		if (follows.size() == 0) 
        {
			return END_OF_TEXT;
		}
		else 
        {
			int randomIndex = myRandom.nextInt(follows.size());
			return follows.get(randomIndex);
		}
	}

	
	@Override
	public String getRandomText(int length)
    {
		ArrayList<String> randomWords = new ArrayList<>(length);
		int index = myRandom.nextInt(myWords.length - myOrder + 1);
		WordGram current = new WordGram(myWords,index,myOrder);
		randomWords.add(current.toString());

		for(int k=0; k < length-myOrder; k += 1) {
			String nextWord = getNextWord(current);
			if (nextWord.equals(END_OF_TEXT)) {
				break;
			}
			randomWords.add(nextWord);
			current = current.shiftAdd(nextWord);
		}
		return String.join(" ", randomWords);
	}

    @Override
    public int getOrder() 
    {
        return myOrder;
    }
        

    @Override
    public void setSeed(long seed) 
    {
        myRandom.setSeed(seed);
    }
    
}
