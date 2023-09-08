package bookTicket;

import ticket.Tickets;
import queue.Queue;

public class BookTicket{
	static Queue upper = new Queue("upper");
	static Queue middle = new Queue("middle");
	static Queue lower = new Queue("lower");
	static Queue rac = new Queue("rac");
	static Queue waitingList = new Queue("waitingList");
	static Queue children = new Queue("child");
	static int upperCount = 27, middleCount = 18, lowerCount=18, racCount=18, waitingListCount=10,i=0;
	public static void childDetails(Tickets ticket){
		children.push(ticket);
	}
	public static void book(Tickets ticket){
		if(upperCount>0 || middleCount>0 || lowerCount>0){
			switch(ticket.getBerth()){
				case "lower":
					if(lowerCount>0){
						if(ticket.getPriority())
							lower.push(ticket);
						else
							lower.pushAfter(ticket);
						lowerCount--;
						
					}
					else if(ticket.getPriority() && (lower.head.ticket.getPriority() == false || lower.head.ticket.getBerth().equals("lower")==false)){
						lower.push(ticket);
						book(lower.popFront());
					}
					else if(middleCount>0){
						middle.pushAfter(ticket);
						System.out.println(middleCount);
						middleCount--;
					}
					else if(upperCount>0){
						upper.pushAfter(ticket);
						upperCount--;
					}
				break;
				case "upper":
					if(upperCount>0){
						upper.push(ticket);
						upperCount--;
					}
					else if(middleCount>0){
						middle.pushAfter(ticket);
						middleCount--;
					}
					else if(lowerCount>0){
						lower.pushAfter(ticket);
						lowerCount--;
					}
				break;
				case "middle":
					if(middleCount>0){
						middle.push(ticket);
						middleCount--;
					}
					else if(upperCount>0){
					upper.pushAfter(ticket);
					upperCount--;
					}
					else if(lowerCount>0){
						lower.pushAfter(ticket);
						lowerCount--;
					}
				break;		
				}
			System.out.println("ticket "+ticket.getTicketNumber()+" booked ~ conformed");
		}
		else if(racCount>0){
			rac.pushAfter(ticket);
			racCount--;
			System.out.println(" in rac ");
		}
		else if(waitingListCount>0){
			waitingList.push(ticket);
			waitingListCount--;
			System.out.println("in waiting list ");
		}
		else{
			System.out.println("no seats available");
		}

	}
	
	public static void show(){
		System.out.println("name\tage\tgender\tseat\tberth\tticketNumber");
		lower.list();
		middle.list();
		upper.list();
		rac.list();
		waitingList.list();
		System.out.println("total booked "+(81-(lowerCount+upperCount+middleCount+racCount)) +" + waiting list count "+(10-waitingListCount)); 
		
	}
	public static void available(){
		System.out.println("seat---berth");
		notBooked(lowerCount,"lower");
		notBooked(middleCount,"middle");
		notBooked(upperCount,"upper");
		notBooked(racCount,"rac");
		notBooked(waitingListCount,"waitingList");
		System.out.println("total available "+(lowerCount+upperCount+middleCount+racCount) +" + waiting list count "+waitingListCount);
		System.out.println("no other seats are available");
	}
	public static void notBooked(int i, String berth){
		System.out.println(i+"\t"+berth+" is Available");

	}
	
	public static void cancel(int ticketNumber){
		Tickets ticket;
		if(lower.deleteTicket(ticketNumber)){
			lowerCount++;
			ticket = middle.mismatch(true,"lower");
			if(ticket != null){
				cancel(ticket.getTicketNumber());
				book(ticket);
			}
			else{ 
			ticket = upper.mismatch(true,"lower");
			if(ticket != null){
				cancel(ticket.getTicketNumber());
				book(ticket);
			}
			else{
				transfer(rac);
				transfer(waitingList);
			}}
		}
		else if(middle.deleteTicket(ticketNumber)){
			middleCount++;
			ticket = upper.mismatch(false,"middle");
			if(ticket != null){
				cancel(ticket.getTicketNumber());
				book(ticket);
			}
			else{ 
			ticket = lower.mismatch(false,"middle");
			if(ticket != null){
				cancel(ticket.getTicketNumber());
				book(ticket);
			}
			else{
				transfer(rac);
				transfer(waitingList);
			}}
			
		}
		else if(upper.deleteTicket(ticketNumber)){
			upperCount++;
			ticket = middle.mismatch(false,"upper");
			if(ticket != null){
				cancel(ticket.getTicketNumber());
				book(ticket);
			}
			else{ 
			ticket = lower.mismatch(false,"upper");
			if(ticket != null){
				cancel(ticket.getTicketNumber());
				book(ticket);
			}
			else{
				transfer(rac);
				transfer(waitingList);
			}}
			
		}
		else if(rac.deleteTicket(ticketNumber)){
			racCount++;
			transfer(waitingList);
			
		}
		else if(waitingList.deleteTicket(ticketNumber)){
			waitingListCount++;
			
		}
		else if(children.deleteTicket(ticketNumber)){
			System.out.println("child ticket ");
		}
		else{
			System.out.print("not booked not ");
		}
	}
	public static void transfer(Queue arr){
		if(arr.head!=null)
		book(arr.popFront());
	}
	
}