import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
	
		Scanner cin= new Scanner(System.in);
		System.out.print("Bun venit! Introduceti numarul de locuri disponibile: ");
		
		String nrLocuri;
        boolean isNrLocuriInvalid;
		do {
			isNrLocuriInvalid=true;
			nrLocuri=cin.nextLine();
			nrLocuri=nrLocuri.trim();
			
			if(!StringFunctionalities.isInteger(nrLocuri) ) {
				System.out.println("Introduceti un numar valid:");
				continue;		
			}
			if(Integer.parseInt(nrLocuri)<1) {
				System.out.println("Introduceti un numar valid:");
				continue;
			}
			isNrLocuriInvalid=false;
		}while(isNrLocuriInvalid);
		GuestsList devmindEvent= new GuestsList(Integer.parseInt(nrLocuri));
		
		
		boolean isNotQuitting=true;
		boolean isHelpChecked=false;
		while(isNotQuitting) {
			if(!isHelpChecked) {
				System.out.println("__________________________________________________________________________________");
				System.out.print("Asteapta comanda: (HELP - Afiseaza lista de comenzi)\n->  ");
				
			}
			
			String command= cin.nextLine().trim();
			if(command.length()!=0 && command.length()!=1) {
				command=StringFunctionalities.stringToLowerWords(command).get(0).toLowerCase(); // in cazul in care se tasteaza o comanda de genul "add add" programul va taia la primul cuvant			
			}
			switch(command) {
				
				case "help":
					isHelpChecked=true;
					GuestsList.help();
					break;
				case "add":
					System.out.println("Se adauga o noua persoana…");
					devmindEvent.add();
					break;
				case "check":
					devmindEvent.check();
					break;
				case "update":
					devmindEvent.update();
					
					break;	
				case "remove":
					devmindEvent.remove();
					break;
				
				case "quit":
					isNotQuitting=false;
					break;
				
				case "guests":
					devmindEvent.guests();
					break;
				case "waitlist":
					devmindEvent.waitlist();
					break;
				case "available":
					devmindEvent.available();
					break;
				case "guests_no":
					devmindEvent.guestsNo();
					break;
				case "waitlist_no":
					devmindEvent.waitlistNo();
					break;
				case "subscribe_no":
					devmindEvent.subscribeNo();
					break;
				case "search":
					devmindEvent.search();
					break;
				default:
					System.out.println("Comanda introdusa \""+command+"\" nu este o comanda valida.Te rugam sa reincerci!");
					break;
			
			}

			isHelpChecked=false;
			
		}
		
		System.out.println("Thank you for using our software! ");

	

	}

}
