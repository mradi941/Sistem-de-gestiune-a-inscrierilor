
import java.util.ArrayList;

public class Check { //clasa cauta in lista de participanti a evenimentului (de tip GuestList)
	                 // dupa metoda de cautare fullname/email/phone number si un cuvant cheie corespunzator metodei
	                 // la final avem toate datele necesare stocate in campurile protected ale clasei de mai jos:	
	private GuestsList eveniment;	
	protected boolean existParticipant;
	protected ArrayList<Boolean> isOnParticipantList;
	protected ArrayList<Integer> orderNo;
	protected int numberPartipantsChecked;
    
	// constructorul clasei:
	public Check(String searchMethod, String searchWord,GuestsList eveniment) {
		// searchMethod can be :"NAME","EMAIL" or "PHONE"
		
		this.numberPartipantsChecked=0;
		this.existParticipant=false;
		this.eveniment=eveniment; // avem referinta listelor de partipanti/lista de asteptare
		this.isOnParticipantList= new ArrayList<Boolean>();
		this.orderNo= new ArrayList<Integer>();
		
		switch (searchMethod) {
		case "NAME":
		
			searchWord=StringFunctionalities.theCorrectName(searchWord);
			this.name(searchWord);
			break;
		case "EMAIL":
			searchWord=searchWord.toLowerCase().trim();
		    this.email(searchWord);
			break;
		case "PHONE":
			searchWord=searchWord.trim();
			this.phone(searchWord);
			break;
		default:
			System.out.println("ERROR!! Something is wrong !!");
			break;
		}	
		
		
	}
	private void name(String searchWord) {
		
		
		for(int i=0;i<eveniment.getParticipantsList().size();i++) {
			Guest participant=eveniment.getParticipantsList().get(i); 
			if(StringFunctionalities.isNameContained(participant.getFullName(), searchWord)) {
				existParticipant=true;
				isOnParticipantList.add(true);
				orderNo.add(i);
				numberPartipantsChecked++;
			}
		}
		for(int i=0;i<eveniment.getWaitingList().size();i++) {
			Guest participant=eveniment.getWaitingList().get(i); 
			if(StringFunctionalities.isNameContained(participant.getFullName(), searchWord)) {
				existParticipant=true;
				isOnParticipantList.add(false);
				orderNo.add(i);
				numberPartipantsChecked++;
			}
		}
		
		
	}
	private void email(String searchWord) {
		searchWord=searchWord.trim().toLowerCase();
		for(int i=0;i<eveniment.getParticipantsList().size();i++) {
			Guest participant=eveniment.getParticipantsList().get(i); 
			if(participant.getEmail().equals(searchWord)) {
				existParticipant=true;
				isOnParticipantList.add(true);//
				orderNo.add(i);
				numberPartipantsChecked++;
				return;
			}
		}
		for(int i=0;i<eveniment.getWaitingList().size();i++) {
			Guest participant=eveniment.getWaitingList().get(i); 
			if(participant.getEmail().equals(searchWord)) {
				existParticipant=true;
				isOnParticipantList.add(false);
				orderNo.add(i);
				numberPartipantsChecked++;
				return;
			}
		}
		
	}
	private void phone(String searchWord) {
	
		searchWord=searchWord.trim();
		for(int i=0;i<eveniment.getParticipantsList().size();i++) {
			Guest participant=eveniment.getParticipantsList().get(i); 
		    String phoneNumber=participant.getPhoneNumber();
			if(phoneNumber.length()==12) {
				phoneNumber= phoneNumber.substring(2);
			}
			if(searchWord.length()==12) {
				searchWord= searchWord.substring(2);
			}
		    if(phoneNumber.equals(searchWord)) {
				existParticipant=true;
				isOnParticipantList.add(true);
				orderNo.add(i);
				numberPartipantsChecked++;
				return;
			}
		}
		for(int i=0;i<eveniment.getWaitingList().size();i++) {
			Guest participant=eveniment.getWaitingList().get(i); 
			String phoneNumber=participant.getPhoneNumber();
			if(phoneNumber.length()==12) {
				phoneNumber= phoneNumber.substring(2);
			}
			if(searchWord.length()==12) {
				searchWord= searchWord.substring(2);
			}
			if(phoneNumber.equals(searchWord)) {
				existParticipant=true;
				isOnParticipantList.add(false);
				orderNo.add(i);
				numberPartipantsChecked++;
				return;
			}
		}
		
	}
	
}
