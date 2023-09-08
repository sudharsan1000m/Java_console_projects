import java.util.*;
import ticket.Tickets;
import bookTicket.BookTicket;

class Train{
	static String name,gender,berth;
	static int age;
	static int action;
	static BookTicket box = new BookTicket();
	public static void main(String[] args){
		int ticketNumber = 0;
		Scanner sc = new Scanner(System.in);
		while(true){
		System.out.println("1.Booking 2.Canceling 3.bookedtickets 4.availabletickets 5.Exit");
		action = Integer.parseInt(sc.next());
		switch(action){
		case 1:
			ticketNumber++;
			booking(ticketNumber);
			System.out.println("ticket number is "+ticketNumber);
			break;
		case 2:
			canceling();
			break;
		case 3:
			bookedtickets();
			break;
		case 4:
			ticketsAvailable();
			break;
		case 5:
			System.out.println("Exit program");
			System.exit(0);
		default:
			System.out.println("Invalid choice");
		}
		}
	}
	static void booking(int ticketNumber){
		Scanner sc = new Scanner(System.in);
		Tickets ticket = new Tickets();
		System.out.print("Enter name ");
		name = sc.nextLine();
		System.out.print("Enter gender m/f ");
		gender = sc.nextLine();
		System.out.print("berth preference upper/middle/lower ");
		berth = sc.nextLine();
		System.out.print("age ");
		age = Integer.parseInt(sc.nextLine());
		if(age<=5){
			System.out.println("Enter Parent or Gaurdian name");
			ticket.setParentName(sc.nextLine());
			ticket.ticketSetter(name, age, gender, berth,ticketNumber);
			box.childDetails(ticket);
			System.out.println("no tickets for children");
			return ;
		}
		if(gender.equals("f")){
			System.out.println("do you have child true/false");
			if(sc.nextLine().equals("true")){
			ticket.setChild("true");
			System.out.println("Enter child name");
			ticket.setChildName(sc.nextLine());}
			}
		ticket.ticketSetter(name, age, gender, berth,ticketNumber);
		box.book(ticket);
	}
	static void canceling(){
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter ticket Number:");
		box.cancel(sc.nextInt());
		System.out.println("Ticket canceled");
		
	}
	static void bookedtickets(){
		box.show();
	}
	
	static void ticketsAvailable(){
		box.available();

	}
}