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
		Exchange a; 
		try{
			for(int i=0; i < children.size(); i++)
			{
				a = children.elementAt(i);
				setOfPhones.phones = setOfPhones.phones.Union(a.setOfPhones.phones);
			}
		}
		catch(IndexOutOfBoundsException e){
			
		}
		return setOfPhones;
	}
}