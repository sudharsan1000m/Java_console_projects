package queue;

import ticket.Tickets;

public class Queue{
	public Node head;
	String berth;
	public Queue(String berth){
		this.berth = berth;
	}
	public class Node{
		public Tickets ticket;
		Node next;
		Node(Tickets ticket){
			this.ticket = ticket;
		}
		
	}
	public void push(Tickets ticket){
		Node node = new Node(ticket);
		if(head==null){
			head = node;
		}
		else{
			Node last = head;
			while(last.next!=null){
				last=last.next;
			}
				last.next = node;
		}
	}
	public void pushAfter(Tickets ticket){
		Node node = new Node(ticket);
		if(head == null || berth.equals(ticket.getBerth())){
			node.next = head;
		head = node;}
		else{
			Node last = head;
			Node prev = null;
			while(last!=null){
				if (last.ticket.getBerth().equals(berth)){
						break;
				}
				prev = last;
				last = last.next;
			}
			node.next = last;
			prev.next = node;
		}
			
		}
	
	public Tickets popFront(){
		Tickets transfer = head.ticket;
		head = head.next;
		return transfer;
	}
	
	
	public Tickets mismatch(boolean priority,String berth){
		Tickets transfer = null;
		Node last = head;
		while(last!=null){
			if(last.ticket.getBerth().equals(berth)){
				transfer = last.ticket;
				break;
			}
			last = last.next;
		}
		if(priority){
			while(last!=null && (last.ticket.getBerth().equals(this.berth)==false)){
			if(last.ticket.getBerth().equals(berth)&&last.ticket.getPriority()){
				transfer = last.ticket;
				break;
			}
			last = last.next;
		}
		}
		return transfer;
	}
	
	public boolean deleteTicket(int ticketNumber){
		Node last = head;
		Node prev = null;
		
		while(last!=null){
			if(head.ticket.getTicketNumber() == ticketNumber){
			head = head.next;
			return true;
			}
			if(last.ticket.getTicketNumber() == ticketNumber){
				prev.next = last.next;
				return true;
			}
			prev = last;
			last = last.next;
			}
		return false;
	}
	
	public void list(){
		Node last = head;
		int i = 0;
		while(last!=null){
			i++;
			System.out.println(last.ticket.getName()+"\t"+last.ticket.getAge()+"\t"+last.ticket.getGender()+"\t"+i+"\t"+berth+"\t"+last.ticket.getTicketNumber());
			last = last.next;
		}
	}
}