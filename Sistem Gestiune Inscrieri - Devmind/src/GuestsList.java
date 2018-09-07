import java.rmi.server.ServerNotActiveException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.crypto.NoSuchPaddingException;


public class GuestsList {

	private int avaiblePlacesNo;
	private  ArrayList<Guest> participantsList ;
	private  ArrayList<Guest> waitingList;
	
	public GuestsList(int avaiblePlacesNo){
		this.avaiblePlacesNo=avaiblePlacesNo;
		this.participantsList=new ArrayList<Guest>(avaiblePlacesNo);
        this.waitingList=new ArrayList<Guest>();
	}
	
	public ArrayList<Guest> getParticipantsList() {
		return participantsList;
	}

	
	public ArrayList<Guest> getWaitingList() {
		return waitingList;
	}
    
 	public static void help() {
		System.out.print(""+
	            "add          - Adauga o noua persoana (inscriere)\r\n" + 
				"check        - Verifica daca o persoana este inscrisa la eveniment\r\n" + 
				"remove       - Sterge o persoana existenta din lista\r\n" + 
				"update       - Actualizeaza detaliile unei persoane\r\n" + 
				"guests       - Lista de persoane care participa la eveniment\r\n" + 
				"waitlist     - Persoanele din lista de asteptare\r\n" + 
				"available    - Numarul de locuri libere\r\n" + 
				"guests_no    - Numarul de persoane care participa la eveniment\r\n" + 
				"waitlist_no  - Numarul de persoane din lista de asteptare\r\n" + 
				"subscribe_no - Numarul total de persoane inscrise\r\n" + 
				"search       - Cauta toti invitatii conform sirului de caractere introdus\r\n" + 
				"quit         - Inchide aplicatia");
		System.out.println();
	}

    public void add() {
    	Scanner cin= new Scanner(System.in);
 		String firstName,lastName,email,phoneNumber;
    	
 		//Introducere date personale participant + verificare date 
 		boolean isFirstNameValid;
 		do {
 			isFirstNameValid=true;
	 		System.out.println("First name:");
	 		firstName=cin.nextLine();
	 		if(!StringFunctionalities.isNameValid(firstName)) {
	 			System.out.println("Te rugam sa reintroduci un nume valid!");
	 			isFirstNameValid=false;
	 		}
 		}while(!isFirstNameValid);
    	firstName=StringFunctionalities.theCorrectName(firstName);
 		
    	boolean isLastNameValid;
 		do {
 			isLastNameValid=true;
	 		System.out.println("Last name:");
	 		lastName=cin.nextLine();
	 		if(!StringFunctionalities.isNameValid(lastName)) {
	 			System.out.println("Te rugam sa reintroduci un nume valid!");
	 			isLastNameValid=false;
	 		}
 		}while(!isLastNameValid);
    	lastName=StringFunctionalities.theCorrectName(lastName);
 		
    	// 
    	
    	boolean isEmailValid;
    	do{
    		 isEmailValid=true;
	    	System.out.println("Email adress:");
	    	email= cin.nextLine();
	    	email=email.trim().toLowerCase();
	    	if(!StringFunctionalities.isEmailValid(email)) {
	    		System.out.println("Te rugam sa introduci email valid(format \"name@Company.com\") ");
	    	    isEmailValid=false;
	    		continue;
	    	}
	    	Check ch= new Check("EMAIL", email, this);
	    	for(int i=0;i<participantsList.size();i++) {
	    		if(email.equals(participantsList.get(i).getFirstName()));
	    	}
	    	if(ch.existParticipant) {
	    		System.out.println("Exista deja un utilizator cu emailul "+email+".Introduceti un alt email.");
	    		isEmailValid=false;
	    	}	
    	}while(!isEmailValid);
    	
    	boolean isPhoneNumberValid;
	    do {	
	    	isPhoneNumberValid=true;
	    	System.out.println("Phone Number:");
	    	phoneNumber=cin.nextLine();
	    	phoneNumber=phoneNumber.trim();
	    	if(!StringFunctionalities.isPhoneNumberValid(phoneNumber)) {
	    		System.out.println("Te rugam sa reintroduci un numar de telefon valid(format „+40733386463“) ");
	    	    isPhoneNumberValid=false;
	    		continue;
	    	}
	    	Check ch= new Check("PHONE", phoneNumber, this);
	    	if(ch.existParticipant) {
	    		System.out.println("Exista deja un utilizator cu numarul de telefon "+phoneNumber+".Introduceti alt numar de telefon.");
	    		isPhoneNumberValid=false;
	    	}
	    }while(!isPhoneNumberValid);
    	
    	// inscrierea participantului la eveniment
    	if(participantsList.size()<this.avaiblePlacesNo) {
    	 	participantsList.add(new Guest(firstName,lastName,email,phoneNumber));
    	 	
    	 	System.out.println("["+participantsList.get(participantsList.size()-1).getFullName()+"] "+"Felicitari! Locul tau la eveniment este confirmat. Te asteptam!");
    	 	return ;
    	}else {
    		waitingList.add(new Guest(firstName,lastName,email,phoneNumber));
    		
    		System.out.println("["+waitingList.get(waitingList.size()-1).getFullName()+"] "+"Te-ai inscris cu succes in lista de asteptare si ai primit numarul de ordine "+ waitingList.size()+".\nTe vom notifica daca un loc devine disponibil.");
    		return ;
    	}
    
    }
	private Check mainCheck() {
		// aceasta metoda cere toate datele necesare pentru a apela constructorul clasei Check si a returna obiectul 
		// clasei aferent construit
		
		Scanner cin= new Scanner(System.in);
        boolean isNotCorrect= true;
        String command="";
        String searchMethod="";// METODA DE CAUTARE 
        while(isNotCorrect) {
        
        	
    		System.out.println("Alege modul de autentificare, tastand:\r\n" + 
    				"\"1\" - Nume si prenume\r\n" + 
    				"\"2\" - Email\r\n" + 
    				"\"3\" - Numar de telefon (format \"+40733386463\")");
    		
        	command=cin.nextLine(); // comanda NAME/EMAIL/PHONENUMBER
        	command=command.trim();
        	switch(command) {
        	case "1":
        	    searchMethod="NAME";
        	    System.out.println("Introduceti numele:");
        	    isNotCorrect=false;
        	    break;
        	case "2":
        		searchMethod="EMAIL";
        		System.out.println("Introduceti email:");
        		isNotCorrect=false;
        	    break;
        	case "3":
        		searchMethod="PHONE";
        		System.out.println("Introduceti nr. de telefon:");
        	    isNotCorrect=false;
        	    break;
        	default :
        		System.out.println("Comanda \""+command+"\" nu exista!Introduceti 1,2 sau 3:");
        		break;
        	}
        }
        	String searchWord= cin.nextLine();
        	searchWord=searchWord.trim();
    	Check ch= new Check(searchMethod, searchWord, this);
    	
    
    	
    	
		return ch;
	}
    
	public void check() {
    	Check ch=this.mainCheck();  // mainCheck va cere metoda de cautare,va capta datele introduse de utilizator de 
    	                        //la tastatura si va returna rezultatul de tip "Check"-> vezi clasa Check
                               // mainCheck este folosit  atat pt comanda "CHECK"
    	                       // cat si pentru "REMOVE" sau "UPDATE" ::
    	
    	int noPersons=ch.numberPartipantsChecked;
    	if(noPersons==0) {
    		System.out.println("Nu s-a gasit niciun participant dupa metoda de cautare!");
    		return;
    	}
    	
        
    	for(int i=0;i<noPersons;i++) {
    		if(ch.isOnParticipantList.get(i)) {
    			int orderNo=ch.orderNo.get(i);
    			Guest participant=participantsList.get(orderNo);
    			System.out.println(participant.getFullName()+" | "+participant.getEmail()+" | "+participant.getPhoneNumber());
    		}
    		else {
    			int orderNo=ch.orderNo.get(i);
    			Guest participant=waitingList.get(orderNo);
    			System.out.println(participant.getFullName()+" | "+participant.getEmail()+" | "+participant.getPhoneNumber());
    		}
    	}
    	
    }


	public void remove() {
		Check ch=mainCheck();
		int noPersons=ch.numberPartipantsChecked;
		//System.out.println(noPersons);
    	if(noPersons==0) {
    		System.out.println("Erroare!! Nu s-a gasit niciun participant dupa metoda de cautare!");
    		return;
    	}
    	
		if(noPersons==1) {
			int participantChecked=ch.orderNo.get(0); // nr de ordine din lista de participanti sau lista de asteptare 

			
			if(ch.isOnParticipantList.get(0)) {  //daca se afla pe lista de participanti
			    if(participantsList.size()<this.avaiblePlacesNo) {
			    	participantsList.remove(participantChecked);
					System.out.println("Stergerea persoanei s-a realizat cu succes");
					return;
				}
			    else {
			    	participantsList.remove(participantChecked);
					if(waitingList.size()!=0) {
						participantsList.set(this.avaiblePlacesNo-1, waitingList.get(0));
						waitingList.remove(0);
					}
					System.out.println("Stergerea persoanei s-a realizat cu succes");
					return;
				}
			}

			else {    			//daca se afla pe lista de asteptare
				waitingList.remove(participantChecked);
				System.out.println("Stergerea persoanei s-a realizat cu succes");
				return;
			}
			
		}
		
		if(noPersons>1) {
			System.out.println("Exista "+noPersons+ " participanti cu acelasi nume:");
	    	for(int i=0;i<noPersons;i++) {
	    		if(ch.isOnParticipantList.get(i)) {
	    			int orderNo=ch.orderNo.get(i);
	    			Guest participant=participantsList.get(orderNo);
	    			System.out.println((i+1)+") "+participant.getFullName()+" | "+participant.getEmail()+" | "+participant.getPhoneNumber()+" ->lista de participanti");
	    		}
	    		else {
	    			int orderNo=ch.orderNo.get(i);
	    			Guest participant=waitingList.get(orderNo);
	    			System.out.println((i+1)+") "+participant.getFullName()+" | "+participant.getEmail()+" | "+participant.getPhoneNumber()+" ->lista de asteptare");
	    		}
	    	}
	    	System.out.println("Introdu numarul corespunzator persoanei pe care doresti sa o stergi");
	    	boolean numarIncorect=true;
	    	Scanner cin= new Scanner(System.in);
	    	String command;
	    	do {
	    		
	    		command= cin.nextLine();
	    		command=command.trim();
	    		////
	    		if(!StringFunctionalities.isInteger(command)) {
	    			System.out.println("Introduceti un numar valid de comanda valid [1,"+noPersons+"]");
	    			continue;
	    		}
	    		if(Integer.parseInt(command)>noPersons || Integer.parseInt(command)<1) {
	    			System.out.println("Introduceti un numar valid de comanda valid [1,"+noPersons+"]");
	    			continue;
	    			
	    		}
	    		numarIncorect=false;
	    	}while(numarIncorect);
			int nrParticipant=Integer.parseInt(command)-1;
			// stergere participant
            
			
			
			
			
			int participantChecked=ch.orderNo.get(nrParticipant); // nr de ordine din lista de participanti sau lista de asteptare 

			
			if(ch.isOnParticipantList.get(nrParticipant)) {  //daca se afla pe lista de participanti
			    if(participantsList.size()<this.avaiblePlacesNo) {
			    	participantsList.remove(participantChecked);
					System.out.println("Stergerea persoanei s-a realizat cu succes");
					return;
				}
			    else {
			    	participantsList.remove(participantChecked);
					if(waitingList.size()!=0) {
						participantsList.set(this.avaiblePlacesNo-1, waitingList.get(0));
						waitingList.remove(0);
					}
					System.out.println("Stergerea persoanei s-a realizat cu succes");
					return;
				}
			}

			else {    			//daca se afla pe lista de asteptare
				waitingList.remove(participantChecked);
				System.out.println("Stergerea persoanei s-a realizat cu succes");
				return;
			}
	    	
		}

	}
	private Guest updateMain(Guest participant) {
	    Scanner cin= new Scanner(System.in);
		boolean isCommandInvalid;
		String command;
		do {
			isCommandInvalid=true;
			System.out.println("Alege campul de actualizat, tastand:\r\n" + 
					"\"1\" - Nume\r\n" + 
					"\"2\" - Prenume\r\n" + 
					"\"3\" - Email \r\n" + 
					"\"4\" - Numar de telefon (format \"+40733386463\")");
			command=cin.nextLine();
			command=command.trim();
			if(!StringFunctionalities.isInteger(command)) {
				System.out.println("Introduceti un numar de comanda valid:");
				continue;
			}
			if(Integer.parseInt(command)>4 || Integer.parseInt(command)<1) {
				System.out.println("Introduceti un numar de comanda valid:");
				continue;
			}
			isCommandInvalid=false;
			
		}while(isCommandInvalid);
		////
		 int nrComanda=Integer.parseInt(command);
		if(nrComanda==1) {
			String firstName;
	    	
	 		//Introducere date personale participant + verificare date 
	 		boolean isFirstNameValid;
	 		do {
	 			isFirstNameValid=true;
		 		System.out.println("First name:");
		 		firstName=cin.nextLine();
		 		if(!StringFunctionalities.isNameValid(firstName)) {
		 			System.out.println("Te rugam sa reintroduci un nume valid!");
		 			isFirstNameValid=false;
		 		}
	 		}while(!isFirstNameValid);
	    	firstName=StringFunctionalities.theCorrectName(firstName);
	        participant.setFirstName(firstName);
	        return participant;
		}
		if(nrComanda==2) {
			String lastName;
			boolean isLastNameValid;
	 		do {
	 			isLastNameValid=true;
		 		System.out.println("Last name:");
		 		lastName=cin.nextLine();
		 		if(!StringFunctionalities.isNameValid(lastName)) {
		 			System.out.println("Te rugam sa reintroduci un nume valid!");
		 			isLastNameValid=false;
		 		}
	 		}while(!isLastNameValid);
	    	lastName=StringFunctionalities.theCorrectName(lastName);
	 		participant.setLastName(lastName);
	    	return participant;
		
		}
		
		if(nrComanda==3) {
			boolean isEmailValid;
	    	String email;
			do{
	    		 isEmailValid=true;
		    	System.out.println("Email adress:");
		    	email= cin.nextLine();
		    	email=email.trim().toLowerCase();
		    	if(!StringFunctionalities.isEmailValid(email)) {
		    		System.out.println("Te rugam sa introduci email valid. ");
		    	    isEmailValid=false;
		    		continue;
		    	}
		    	Check ch= new Check("EMAIL", email, this);
		    	if(ch.existParticipant) {
		    		System.out.println("Exista deja un utilizator cu emailul "+email+".Introduceti un alt email.");
		    		isEmailValid=false;
		    	}	
	    	}while(!isEmailValid);
		    participant.setEmail(email);
		    return participant;
		}
		if(nrComanda==4) {
			String phoneNumber;
		 	boolean isPhoneNumberValid;
		    do {	
		    	isPhoneNumberValid=true;
		    	System.out.println("Phone Number:");
		    	phoneNumber=cin.nextLine();
		    	phoneNumber=phoneNumber.trim();
		    	if(!StringFunctionalities.isPhoneNumberValid(phoneNumber)) {
		    		System.out.println("Te rugam sa reintroduci un numar de telefon valid(format „+40733386463“) ");
		    	    isPhoneNumberValid=false;
		    		continue;
		    	}
		    	Check ch= new Check("PHONE", phoneNumber, this);
		    	if(ch.existParticipant) {
		    		System.out.println("Exista deja un utilizator cu numarul de telefon "+phoneNumber+".Introduceti alt numar de telefon.");
		    		isPhoneNumberValid=false;
		    	}
		    }while(!isPhoneNumberValid);
		    participant.setPhoneNumber(phoneNumber);
		    return participant;
		}
		return participant;
	}
	public void update() {
		Check ch=mainCheck();
		int noPersons=ch.numberPartipantsChecked;
    	if(noPersons==0) {
    		System.out.println("Erroare!! Nu s-a gasit niciun participant dupa metoda de cautare!");
    		return;
    	}
    	
		if(noPersons==1) {
			int participantChecked=ch.orderNo.get(0); // nr de ordine din lista de participanti sau lista de asteptare 

			
			if(ch.isOnParticipantList.get(0)) {  //daca se afla pe lista de participanti
			    updateMain(participantsList.get(participantChecked));
			    System.out.println("Participantul a fost actualizat!");
			    return;
			}

			else {    			//daca se afla pe lista de asteptare
				updateMain(waitingList.get(participantChecked));
				System.out.println("Participantul a fost actualizat!");
				return;
			}
			
		}
		
		if(noPersons>1) {
			System.out.println("Exista "+noPersons+ " participanti cu acelasi nume:");
	    	for(int i=0;i<noPersons;i++) {
	    		if(ch.isOnParticipantList.get(i)) {
	    			int orderNo=ch.orderNo.get(i);
	    			Guest participant=participantsList.get(orderNo);
	    			System.out.println((i+1)+") "+participant.getFullName()+" | "+participant.getEmail()+" | "+participant.getPhoneNumber()+" ->lista de participanti");
	    		}
	    		else {
	    			int orderNo=ch.orderNo.get(i);
	    			Guest participant=waitingList.get(orderNo);
	    			System.out.println((i+1)+") "+participant.getFullName()+" | "+participant.getEmail()+" | "+participant.getPhoneNumber()+" ->lista de asteptare");
	    		}
	    	}
	    	System.out.println("Introdu numarul corespunzator persoanei pe care doresti sa ii faci UPDATE:");
	    	boolean numarIncorect=true;
	    	Scanner cin= new Scanner(System.in);
	    	String command;
	    	do {
	    		
	    		command= cin.nextLine();
	    		command=command.trim();
	    		////
	    		if(!StringFunctionalities.isInteger(command)) {
	    			System.out.println("Introduceti un numar valid de comanda valid [1,"+noPersons+"]");
	    			continue;
	    		}
	    		if(Integer.parseInt(command)>noPersons) {
	    			System.out.println("Introduceti un numar valid de comanda valid [1,"+noPersons+"]");
	    			continue;
	    			
	    		}
	    		numarIncorect=false;
	    	}while(numarIncorect);
	    	
			int nrParticipant=Integer.parseInt(command)-1;
			 
			int participantChecked=ch.orderNo.get(nrParticipant); // nr de ordine din lista de participanti sau lista de asteptare 

			
			if(ch.isOnParticipantList.get(nrParticipant)) {  //daca se afla pe lista de participanti

                 Guest g=updateMain(participantsList.get(participantChecked));
				 participantsList.set(participantChecked, g);
				 System.out.println("Participantul a fost actualizat!");
			}

			
			
			else {    			//daca se afla pe lista de asteptare
				Guest g=updateMain(waitingList.get(participantChecked));
			    waitingList.set(participantChecked, g);
			    System.out.println("Participantul a fost actualizat!");
			}
	    	
		}

	}
	public void guests() {
		if(participantsList.size()==0) {
			System.out.println("Lista de participanti este goala!");
			return;
		}
		for(int i=0;i<participantsList.size();i++) {
			 Guest participant=participantsList.get(i);
			System.out.println((i+1)+") "+participant.getFullName()+" | "+participant.getEmail()+" | "+participant.getPhoneNumber());
			
		}
		
	}
	public void waitlist() {
		if(waitingList.size()==0) {
			System.out.println("Lista de asteptare este goala!");
			return;
		}
		for(int i=0;i<waitingList.size();i++) {
			 Guest participant=waitingList.get(i);
			System.out.println((i+1)+") "+participant.getFullName()+" | "+participant.getEmail()+" | "+participant.getPhoneNumber());
			
		}
		
	}

	public void available() {
		
		System.out.println("Numarul de locuri ramase: "+(this.avaiblePlacesNo-this.participantsList.size()));
		
		
	}



	public void guestsNo() {
		System.out.println(participantsList.size()+" persoane participa la eveniment!");
	}

	public void waitlistNo() {
		System.out.println(waitingList.size()+" persoane sunt pe lista de asteptare!");
	}
	public void subscribeNo() {
		System.out.println("Numarul total de persoane inscrise:" + (participantsList.size()+waitingList.size()));
	}

	public void search() {
		
		Scanner cin= new Scanner(System.in);
		System.out.println("Introdu cuvantul cheie:");
		String keyWord=cin.nextLine();
		keyWord=keyWord.trim().toLowerCase();
		boolean nothingFound=true;
	
		int noFound=0;
		for(int i=0;i<participantsList.size();i++) {
			Guest participant=participantsList.get(i);
			String fullName,email,phone;
			fullName=participant.getFullName().toLowerCase();
			email=participant.getEmail().toLowerCase();;
			phone=participant.getPhoneNumber();
		    
			if(fullName.contains(keyWord)) {
				noFound++;
				System.out.println(noFound+") "+participant.getFullName()+" "+participant.getEmail()+" "+participant.getPhoneNumber());
				nothingFound=true;
				continue;
			}
			if(email.contains(keyWord)) {
				noFound++;
				System.out.println(noFound+") "+participant.getFullName()+" "+participant.getEmail()+" "+participant.getPhoneNumber());
				nothingFound=true;
				continue;
			}
			if(phone.contains(keyWord)) {
				noFound++;
				System.out.println(noFound+") "+participant.getFullName()+" "+participant.getEmail()+" "+participant.getPhoneNumber());
				nothingFound=true;
				continue;
			}
		
		}
		for(int i=0;i<waitingList.size();i++) {
			Guest participant=waitingList.get(i);
			String fullName,email,phone;
			fullName=participant.getFullName().toLowerCase();
			email=participant.getEmail().toLowerCase();;
			phone=participant.getPhoneNumber();
		    
			if(fullName.contains(keyWord)) {
				noFound++;
				System.out.println(noFound+") "+participant.getFullName()+" "+participant.getEmail()+" "+participant.getPhoneNumber());
				nothingFound=true;
				continue;
			}
			if(email.contains(keyWord)) {
				noFound++;
				System.out.println(noFound+") "+participant.getFullName()+" "+participant.getEmail()+" "+participant.getPhoneNumber());
				nothingFound=true;
				continue;
			}
			if(phone.contains(keyWord)) {
				noFound++;
				System.out.println(noFound+") "+participant.getFullName()+" "+participant.getEmail()+" "+participant.getPhoneNumber());
				nothingFound=true;
				continue;
			}
		}
	
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}



