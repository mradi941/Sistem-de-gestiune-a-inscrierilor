import java.util.ArrayList;

public class StringFunctionalities {

	public static boolean isEmailValid(String emailAdress) {
		emailAdress=emailAdress.trim().toLowerCase();
		
		if(emailAdress.charAt(0)=='.'||emailAdress.charAt(0)=='@'|| Character.isDigit(emailAdress.charAt(0))
				|| emailAdress.charAt(emailAdress.length()-1)=='.'||emailAdress.charAt(emailAdress.length()-1)=='@') {
			
			return false;
			
		}
		int nrArond=0, nrPointAfterArond=0;
		int indexFirstArond;
		for(int i=1;i<emailAdress.length();i++) {
			if(emailAdress.charAt(i)=='@') {
				nrArond++;
				indexFirstArond=i;
				if(emailAdress.charAt(indexFirstArond+1)=='.') {
					return false;
				}
			}
			if(nrArond>1) {
				
				return false;
		    }
			if(nrArond>0 && emailAdress.charAt(i)=='.') {
				nrPointAfterArond++;
				
			}
			
		}	
		if(nrPointAfterArond==0) {
			return false;
		}
		return true;
	}
	public static boolean isPhoneNumberValid(String phoneNumber) {
		if(phoneNumber.length()!=10 && phoneNumber.length()!=12) {
	  	   return false;
	  	}
  	    if(phoneNumber.length()==10) {
  		   for(int i=0;i<phoneNumber.length();i++) {
  			   if(!Character.isDigit(phoneNumber.charAt(i))) {
  		 		   return false;
  			   }
  		   }
  	    }
	  	if(phoneNumber.length()==12) {
	  		if(phoneNumber.charAt(0)!='+') {
	  			return false;
	  		}
	 		for(int i=1;i<phoneNumber.length();i++) {
	 			if(!Character.isDigit(phoneNumber.charAt(i))) {
	 				return false;
	 			}
	 		}
	 	}
	  	return true;
	}
    public static boolean isNameValid(String name) {
    	for(int i=0;i<name.length();i++) {
    		char ch=name.charAt(i);
    		if(!Character.isLetter(ch) && !Character.isWhitespace(ch)) {
    			return false;
    		}
    	}
    	if(name.length()<2) {
    		return false;
    	}
    	return true;
    }
    public static boolean isNameContained(String name1,String name2) {

    	ArrayList<String> wordsName1= stringToLowerWords(name1);
    	ArrayList<String> wordsName2= stringToLowerWords(name2);
    	wordsName1.sort(null);
    	wordsName2.sort(null);
    	
    	int iterator=0,i=0;
    	while(iterator<wordsName2.size()&& i<wordsName1.size()) {
    		if(wordsName1.get(i).equals(wordsName2.get(iterator))) {
    			iterator++;
    		}
    		i++;
    	}
    	if(iterator==wordsName2.size()) {
    		return true;
    	}
    	
    	
      //// aici mai e de lucrat
    
    	return false;
    }
	public static ArrayList<String> stringToLowerWords(String sentence){
    	sentence=sentence.toLowerCase();
    	
		ArrayList<String> lowerWordsArray = new ArrayList<String>();
		
		
		int beginIndex=-1,endIndex=-1;
		boolean searchFirstLetter=true;
		for(int i=0;i<sentence.length();i++) {
	    	
			if(searchFirstLetter) {// daca se cauta prima litera // ca sa aflam begin index
			
				if(!Character.isWhitespace(sentence.charAt(i)) ) {
				searchFirstLetter=false;
				beginIndex=i;
				}
			}
			else { // daca se cauta primul spatiu ca sa aflam final index si sa printam
				if(Character.isWhitespace(sentence.charAt(i))) {
					searchFirstLetter=true;
					endIndex=i;
					
					lowerWordsArray.add(sentence.substring(beginIndex, endIndex));
					
				}
				else if(i==sentence.length()-1) {
					lowerWordsArray.add(sentence.substring(beginIndex));
					
				}
			}
			
			
		}
		return  lowerWordsArray;
    }
    public static String theCorrectName(String sentence) {
    	ArrayList<String> words = stringToLowerWords(sentence);
    	for(int i=0;i<words.size();i++) {
       		StringBuilder sb = new StringBuilder(words.get(i));
       		sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
       		words.set(i, sb.toString());
       	}
       	String nameCorectly="";
       	for(int i=0;i<words.size();i++) {
       		if(i!=words.size()-1) {
       			nameCorectly+=words.get(i)+" ";
       		}
       		else {
       			nameCorectly+=words.get(i);
       		}
       	}
       	return nameCorectly;
    }
    public static boolean isInteger( String input )
    {
       try
       {
          Integer.parseInt( input );
          return true;
       }
       catch( Exception e)
       {
          return false;
       }
    }

}
