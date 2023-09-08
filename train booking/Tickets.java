package ticket;

public class Tickets{
	private String name,gender,berth,child=" ";
	private int age,ticketNumber;
	private boolean priority;
	private String childName=null,parentName=null;
	public void ticketSetter(String name, int age, String gender, String berth,int ticketNumber){
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.berth = berth;
		this.ticketNumber = ticketNumber;
		if((age>65) || child.equals("true"))
			this.priority = true;
	}
	public void setChild(String child){
		this.child = child;
	}
	public void setChildName(String childName){
		this.childName = childName;
	}
	public void setParentName(String parentName){
		this.parentName = parentName;
	}
	public String getName(){
		return this.name;
	}
	public String getGender(){
		return this.gender;
	}
	public String getBerth(){
		return this.berth;
	}
	public String getChild(){
		return this.child;
	}
	public int getAge(){
		return this.age;
	}
	public int getTicketNumber(){
		return this.ticketNumber;
	}
	public boolean getPriority(){
		return this.priority;
	}	
}


