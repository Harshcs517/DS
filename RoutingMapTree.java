import java.util.*;
/* Problems can come:
1. If we switch on the phone and phone did not exist.
2. 
*/
public class RoutingMapTree {

	ExchangeList tree = new ExchangeList();
	Exchange root;
	public RoutingMapTree() {
		root = new Exchange(0);
		tree.ListOfExchange.add(root);
	}

	public boolean containsNode(Exchange a)
	{
		return root.children.size()!=0;
	}

	public void switchOn(MobilePhone a, Exchange b)
	{
		try {
			a.switchOn();
			if(a.parent != null){
				a.parent.setOfPhones.phones.Delete(a);
				a.parent=null;
			}
			a.parent = b;
			b.setOfPhones.phones.Insert(a);
		} catch (NullPointerException e) {
			
		}
		
		//Required deletion of element where it exist
	}

	public void switchOff(MobilePhone a)
	{
		a.switchOff();
		
	}
	////////////////////////////////////////////////////////////////////
	public Exchange findPhone(MobilePhone m) throws PhoneNotFound, PhoneSwitchedOff{
		
		// for (int i = 0; i < this.root.setOfPhones.phones.head.size(); i++) {
		// 	//this another option
		// }
		Exchange getParent = null;
		if(m==null)
		{
			throw new PhoneNotFound("bhai phone exist hi nahi karta");
		}
		else if(!m.status())
		{
			throw new PhoneSwitchedOff("phone bandh rakha hai bhai");
		}
		else if(this.root.setOfPhones.phones.IsMember(m))
		{
			getParent = m.parent;
		}
		return getParent;
	}
	public Exchange lowestRouter(Exchange a, Exchange b){
		Exchange parentA = a.parent;
		Exchange parentB = b.parent;
		// while(true)
		// {
		// 	if(a==b){
		// 		return a;
		// 	}
		// 	if(parentA==parentB)
		// 	{
		// 		return parentA;
		// 	}
		// 	parentA=parentA.parent;
		// 	parentB=parentB.parent;
		// }
		// return null;	
		
		if(a==b)
		{
			return a;
		}
		for(parentA = a.parent; parentA!=null ; parentA=parentA.parent)
		{
			for(parentB = b.parent; parentB!=null ; parentB=parentB.parent)
			{
				if(parentA==parentB)
				{
					return parentA;
				}
			}
		}
		return null;
	}
	public ExchangeList routeCall(MobilePhone a, MobilePhone b) throws PhoneNotFound, PhoneSwitchedOff{
		ExchangeList RastaBata = new ExchangeList();
		ExchangeList TrackOfb = new ExchangeList();
		Exchange MountainToppestPeak;
		if(!a.status() || !b.status())
		{
			throw new PhoneSwitchedOff("Phone Bandh hai");
		}
		else if(a==null || b==null)
		{
			throw new PhoneNotFound("phone nahi mila");
		}

		MountainToppestPeak = lowestRouter(a.parent, b.parent);
		for(Exchange i = a.parent; i!=MountainToppestPeak; i=i.parent)
		{
			RastaBata.ListOfExchange.add(i);
		}
		for(Exchange i = b.parent; i!=MountainToppestPeak; i=i.parent)
		{
			TrackOfb.ListOfExchange.add(i);
		}

		RastaBata.ListOfExchange.add(MountainToppestPeak);
		for(int i= TrackOfb.ListOfExchange.size()-1;i>=0;i=i-1)
		{
			RastaBata.ListOfExchange.add(TrackOfb.ListOfExchange.get(i));
		}
		return RastaBata;
	}

	public void movePhone(MobilePhone a, Exchange b) throws PhoneSwitchedOff, PhoneNotFound
	{
		if(a==null)
		{
			throw new PhoneNotFound("phone hai hi nahi");
		}
		else if(!a.status())
		{
			// System.out.println("statusgone");
			throw new PhoneSwitchedOff("Phone Bandh hai");
		}
		else 
		{
			// System.out.println("gone");
			a.parent.setOfPhones.phones.Delete(a);
			a.parent = null;
			a.parent = b;
			b.setOfPhones.phones.Insert(a);
		}
	}

///////////////////////////////////////////////////////////////////
	public String performAction(String actionMessage) {
		
		// System.out.printf("%s: ",actionMessage);
		int a,b;
		String[] split = actionMessage.split(" ",0);
		
		if(Objects.equals(split[0], "addExchange"))
		{	
			
			a = Integer.parseInt(split[1]);
			try {
				//b may exist
				b = Integer.parseInt(split[2]);
				// Exchange node = new Exchange(b);
				// Exchange parent = tree.Find(a);
				// parent.children.add(node);
				// tree.ListOfExchange.add(node);
				Exchange exist = this.tree.Find(b);
				Exchange parent = this.tree.Find(a);
				if(exist!=null)
				{
					System.out.printf("%s: ",actionMessage);
					System.out.println("Error - Exchange with identifier "+b+" already in the network");
					return actionMessage+": Error - Exchange with identifier "+b+" already in the network";
				}
				if(parent.setOfPhones.phones.head.size()!=0 && parent.children.size()==0)
				{
					System.out.printf("%s: ",actionMessage);
					System.out.println("Error - Exchange with identifier "+a+" is a lowest level base station");
					return actionMessage+": Error - Exchange with identifier "+a+" is a lowest level base station";
				}
				RoutingMapTree subTree = new RoutingMapTree();
				subTree.root = new Exchange(b);
				parent.children.add(subTree);
				subTree.root.parent = parent;
				tree.ListOfExchange.add(subTree.root);
				// System.out.println(parent.children);
			} catch (NullPointerException e) {
				System.out.printf("%s: ",actionMessage);
				System.out.println("Error - No Exchange with identifier "+a);
				return actionMessage+": Error - No Exchange with identifier "+a;
			}		
		}
		else if(Objects.equals(split[0], "switchOnMobile")) 
		{
			a = Integer.parseInt(split[1]);
			b = Integer.parseInt(split[2]);
			// System.out.println(tree.ListOfExchange.size()-1);
			
			Exchange baseStation = this.tree.Find(b);
			try {
				if(baseStation.children.size()!=0)
				{
					System.out.printf("%s: ",actionMessage);
					System.out.println("Error - Exchange with identifier "+b+" is not the base station");
					return actionMessage+": Error - Exchange with identifier "+b+" is not the base station";
				}
			} catch (NullPointerException e) {
				System.out.printf("%s: ",actionMessage);
				System.out.println("Error - No Exchange with identifier "+b);
				return actionMessage+": Error - No Exchange with identifier "+b;
			}
			
			
			for (int i = tree.ListOfExchange.size()-1; i >=0; i=i-1) {
				tree.ListOfExchange.get(i).residentSet();
				
				//This may take time
			}

			int i;
			
			try {
				for(i=0;i< this.root.setOfPhones.phones.head.size() ;i++)
				{
					if(this.root.setOfPhones.phones.head.get(i).number()==a)
					{
						if(this.root.setOfPhones.phones.head.get(i).status())
						{
							System.out.printf("%s: ",actionMessage);
							System.out.println("Error - phone with identifier "+a+" is already SwitchOn");
							return actionMessage+": Error - phone with identifier "+a+" is already SwitchOn";
						}
						switchOn(this.root.setOfPhones.phones.head.get(i), baseStation);
						break;
					}
				}
				if(i==root.setOfPhones.phones.head.size())
				{
					MobilePhone phoneRegister = new MobilePhone(a);
					switchOn(phoneRegister, baseStation);
				}
			} catch (NullPointerException e) {
				System.out.printf("%s: ",actionMessage);
				System.out.println("Error - No Exchange with identifier "+b);
				return actionMessage+": Error - No Exchange with identifier "+b;
			}
			for (int j = tree.ListOfExchange.size()-1; j >=0; j=j-1) {
				tree.ListOfExchange.get(j).residentSet();
			}		

		}
		else if (Objects.equals(split[0], "switchOffMobile"))
		{
			a = Integer.parseInt(split[1]);
			int i=0;
			for(i=0;i< this.root.setOfPhones.phones.head.size() ;i++)
			{
				if(this.root.setOfPhones.phones.head.get(i).number()==a)
				{
					switchOff(this.root.setOfPhones.phones.head.get(i));
					break;
				}
			}
			if(i==this.root.setOfPhones.phones.head.size())
			{
				System.out.printf("%s: ",actionMessage);
				System.out.println("Error - No mobile phone with identifier "+a);
				return actionMessage+": Error - No mobile phone with identifier "+a;
			}
			
		}
		else if (Objects.equals(split[0], "queryNthChild")) 
		{
			a = Integer.parseInt(split[1]);
			b = Integer.parseInt(split[2]);
			try {
				System.out.printf("%s: ",actionMessage);
				System.out.println(this.tree.Find(a).children.get(b).root.baseno);
				return actionMessage+": "+this.tree.Find(a).children.get(b).root.baseno;
			} catch (NullPointerException e) {
				System.out.println("Error - No Exchange with identifier "+a);
				return actionMessage+": Error - No Exchange with identifier "+a;
			} catch (IndexOutOfBoundsException e) {
				System.out.println("Error - No "+b+" child of Exchange "+a);
				return actionMessage+": Error - No "+b+" child of Exchange "+a;
			}		

		} 
		else if(Objects.equals(split[0], "queryMobilePhoneSet"))
		{
			a = Integer.parseInt(split[1]);
			String answer = actionMessage+": ";
			// System.out.println(this.tree.ListOfExchange.get(a).setOfPhones.phones.head.size() + " itna");
			try {
				for (int i = this.tree.ListOfExchange.size()-1; i >=0; i=i-1) {
					this.tree.ListOfExchange.get(i).residentSet();
					//This may take time
				}
				for(int j=0; j<this.tree.Find(a).setOfPhones.phones.head.size() ;j++)
				{
					if(this.tree.Find(a).setOfPhones.phones.head.get(j).status())
					{
						if(j==0){
							// System.out.printf(" %d",this.tree.Find(a).setOfPhones.phones.head.get(j).number());
							answer=answer+this.tree.Find(a).setOfPhones.phones.head.get(j).number();
						}
						else{
							// System.out.printf(", %d",this.tree.Find(a).setOfPhones.phones.head.get(j).number());
							answer=answer+", "+this.tree.Find(a).setOfPhones.phones.head.get(j).number();
						}

					}
				}
				System.out.printf("%s",answer);
				System.out.printf("\n");
				return answer;	
			} catch (NullPointerException e) {
				System.out.printf("%s: ",actionMessage);
				System.out.println("Error - No Exchange with identifier "+a);
				return actionMessage+": Error - No Exchange with identifier "+a;
			}
			
		}	
		/////////////////////////////////////////////////////
		else if(Objects.equals(split[0], "findPhone") || Objects.equals(split[0], "queryFindPhone"))
		{
			a = Integer.parseInt(split[1]);
			MobilePhone giveMobile=null;
			try {
				for(int i=0;i<this.root.setOfPhones.phones.head.size();i++)
				{
					if(a==this.root.setOfPhones.phones.head.get(i).number())
					{
						giveMobile=this.root.setOfPhones.phones.head.get(i);
						break;
					}
				}
				Exchange giveParent;
				giveParent = findPhone(giveMobile);
				System.out.printf("queryFindPhone "+a+": ");
				System.out.println(giveParent.baseno);
				return "queryFindPhone "+a+": "+giveParent.baseno;

				
			} catch (PhoneNotFound e) {
				System.out.printf("queryFindPhone "+a+":");
				System.out.println(" Error - No mobile phone with identifier "+a+" found in the network");
				return "queryFindPhone "+a+": Error - No mobile phone with identifier "+a+" found in the network";
			} catch (PhoneSwitchedOff e) {
				System.out.printf("queryFindPhone "+a+":");
				System.out.println(" Error - phone with identifier "+a+" is currently switched off");
				return "queryFindPhone "+a+": Error - phone with identifier "+a+" is currently switched off";
			} catch (NullPointerException e)
			{
			}
		}
		else if(Objects.equals(split[0], "lowestRouter") || Objects.equals(split[0], "queryLowestRouter"))
		{
			a = Integer.parseInt(split[1]);
			b = Integer.parseInt(split[2]);
			Exchange baseStationA = this.tree.Find(a);
			try {
				if(baseStationA.children.size()!=0)
				{
					System.out.printf("queryLowestRouter "+a+" "+b);
					System.out.println(": Error - Exchange with identifier "+a+" is not the base station");
					return "queryLowestRouter "+a+" "+b+": Error - Exchange with identifier "+a+" is not the base station";
				}
			} catch (NullPointerException e) {
				System.out.printf("queryLowestRouter "+a+" "+b);
				System.out.println(": Error - No Exchange with identifier "+a);
				return "queryLowestRouter "+a+" "+b+": Error - No Exchange with identifier "+a;
			}
			Exchange baseStationB = this.tree.Find(b);
			try {
				if(baseStationB.children.size()!=0)
				{
					System.out.printf("queryLowestRouter "+a+" "+b);
					System.out.println(": Error - Exchange with identifier "+b+" is not the base station");
					return "queryLowestRouter "+a+" "+b+": Error - Exchange with identifier "+b+" is not the base station";
				}
			} catch (NullPointerException e) {
				System.out.printf("queryLowestRouter "+a+" "+b);
				System.out.println(": Error - No Exchange with identifier "+b);
				return "queryLowestRouter "+a+" "+b+": Error - No Exchange with identifier "+b;
			}
			Exchange Peak;
			Peak = lowestRouter(baseStationA,baseStationB);
			try {
				System.out.printf("queryLowestRouter "+a+" "+b+": ");
				System.out.println(Peak.baseno);
			return "queryLowestRouter "+a+" "+b+": "+Peak.baseno;
			} catch (Exception e) {
				System.out.println("peak is null");
			}
		}
		else if(Objects.equals(split[0], "findCallPath") || Objects.equals(split[0], "queryFindCallPath"))
		{
			a = Integer.parseInt(split[1]);
			b = Integer.parseInt(split[2]);
			MobilePhone giveMobileA = null;
			MobilePhone giveMobileB = null ;
			for(int i=0;i<this.root.setOfPhones.phones.head.size();i++)
			{
				if(a==this.root.setOfPhones.phones.head.get(i).number() && giveMobileA==null)
				{
					giveMobileA=this.root.setOfPhones.phones.head.get(i);
				}
				if(b==this.root.setOfPhones.phones.head.get(i).number() && giveMobileB==null)
				{
					giveMobileB=this.root.setOfPhones.phones.head.get(i);
				}
				if(giveMobileA!=null && giveMobileB!=null)
				{
					break;
				}
			}
			try {
				ExchangeList Map;
				String output;
				output = "queryFindCallPath "+a+" "+b+": ";
				Map = routeCall(giveMobileA, giveMobileB);
				for(int i=0;i<Map.ListOfExchange.size();i++)
				{
					if(i==Map.ListOfExchange.size()-1)
					{
						// System.out.printf("%d",Map.ListOfExchange.get(i).baseno);
						output = output + Map.ListOfExchange.get(i).baseno;
						break;
					}
					// System.out.printf("%d, ",Map.ListOfExchange.get(i).baseno);
					output = output + Map.ListOfExchange.get(i).baseno + ", ";
				}
				System.out.println(output);
				return output;
			} catch (PhoneSwitchedOff e) {
				if(!giveMobileA.status())
				{
					System.out.printf("queryFindCallPath "+a+" "+b+": ");
					System.out.println("Error - Mobile phone with identifier "+a+" is currently switched off");
					return "queryFindCallPath "+a+" "+b+""+": Error - Mobile phone with identifier "+a+" is currently switched off";
				}
				else
				{
					System.out.printf("queryFindCallPath "+a+" "+b+": ");
					System.out.println("Error - Mobile phone with identifier "+b+" is currently switched off");
					return "queryFindCallPath "+a+" "+b+""+": Error - Mobile phone with identifier "+b+" is currently switched off";
				}
			} catch (PhoneNotFound e) {
				if(giveMobileA==null)
				{
					System.out.printf("queryFindCallPath "+a+" "+b+": ");
					System.out.println("Error - No Mobile phone with identifier "+a);
					return "queryFindCallPath "+a+" "+b+""+": Error - No Mobile phone with identifier "+a;
				}
				else
				{
					System.out.printf("queryFindCallPath "+a+" "+b+": ");
					System.out.println("Error - No Mobile phone with identifier "+b);
					return "queryFindCallPath "+a+" "+b+""+": Error - No Mobile phone with identifier "+b;
				}
			} catch(NullPointerException e){
				System.out.println("map null ho sakta ha");
			}
			catch(IndexOutOfBoundsException e){
				System.out.println("for loop band baja raha hai");
			}
		}
		else if(Objects.equals(split[0], "movePhone"))
		{
			a = Integer.parseInt(split[1]);
			b = Integer.parseInt(split[2]);
			// System.out.println("gone");
			MobilePhone giveMobile =null;
			for(int i=0;i<this.root.setOfPhones.phones.head.size();i++)
			{
				if(a==this.root.setOfPhones.phones.head.get(i).number())
				{
					giveMobile=this.root.setOfPhones.phones.head.get(i);
					break;
				}
			}
			Exchange baseStation = this.tree.Find(b);
			try {
				if(baseStation.children.size()!=0)
				{
					System.out.printf("%s: ",actionMessage);
					System.out.println("Error - Exchange with identifier "+b+" is not the base station");
					return actionMessage+": Error - Exchange with identifier "+b+" is not the base station";
				}
			} catch (NullPointerException e) {
				System.out.printf("%s: ",actionMessage);
				System.out.println(": Error - No Exchange with identifier "+b);
				return actionMessage+": Error - No Exchange with identifier "+b;
			}
			try {
				// System.out.println("gone");
				movePhone(giveMobile,baseStation);
				// System.out.println("gone");

			} catch (PhoneSwitchedOff e) {
				System.out.printf("%s: ",actionMessage);
				System.out.println("Error - Mobile phone with identifier "+a+" is currently switched off");
				return actionMessage+": Error - Mobile phone with identifier "+a+" is currently switched off";
			} catch (PhoneNotFound e)
			{
				System.out.printf("%s: ",actionMessage);
				System.out.println("Error - No Mobile phone with identifier "+a);
				return actionMessage+": Error - No Mobile phone with identifier "+a;
			}
		
			for (int i = tree.ListOfExchange.size()-1; i >=0; i=i-1) {
				tree.ListOfExchange.get(i).residentSet();
				
				//This may take time
			}

		}
		/////////////////////////////////////////////////////////
		return "";
	}
}
