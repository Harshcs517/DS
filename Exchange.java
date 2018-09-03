import java.util.*;

public class Exchange {
	int baseno;
	public Exchange(int number){
		baseno = number;
		parent = null;
	}
	Vector<Exchange> children = new Vector<Exchange>();
	Exchange parent;

	public Exchange parent(){
		return parent;
	}

	public int numChildren(){
		//for number of children
		return children.size();
	}

	public Exchange child(int i){
		//returns  the ith  child
		return children.get(i);
	}

	public Boolean isRoot(){
		return baseno==0;
	}
	public Exchange subtree(int i){//not done something according 
		return children.get(i);
	}
	MobilePhoneSet setOfPhones = new MobilePhoneSet();
	public MobilePhoneSet residentSet(){
		//his returns the resident set of mobile phones of the exchange.
		// System.out.printf("%d",this.children.size()) ;
		try{
			for(int i=0; i < this.children.size(); i++)
			{
				// System.out.println(this.children.elementAt(i));
				// children.elementAt(i).residentSet();
				this.setOfPhones.phones = this.setOfPhones.phones.Union(this.children.elementAt(i).setOfPhones.phones);
			}
			// System.out.println(this.setOfPhones.phones.head);
		}
		catch(IndexOutOfBoundsException e){
			
		}
		return setOfPhones;
	}
}