import java.util.*;
/* Problems can come:
1. If we switch on the phone and phone did not exist.
2. 
*/
public class RoutingMapTree{

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
				if(exist!=null)
				{
					System.out.printf("%s: ",actionMessage);
					System.out.println("Error - Exchange with identifier "+b+" already in the network");
					return actionMessage+": Error - Exchange with identifier "+b+" already in the network";
				}
				RoutingMapTree subTree = new RoutingMapTree();
				subTree.root = new Exchange(b);
				Exchange parent = this.tree.Find(a);
				parent.children.add(subTree);
				tree.ListOfExchange.add(subTree.root);
				// System.out.println(parent.children);
			} catch (NullPointerException e) {
				System.out.printf("%s: ",actionMessage);
				System.out.println("Error - No Exchange with identifier "+a+" found in the network");
				return actionMessage+": Error - No Exchange with identifier "+a+" found in the network";
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
				System.out.println("Error - No Exchange with identifier "+b+" found in the network");
				return actionMessage+": Error - No Exchange with identifier "+b+" found in the network";
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
							System.out.println("Error - phone with id "+a+" is already SwitchOn");
							return actionMessage+": Error - phone with id "+a+" is already SwitchOn";
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
				System.out.println("Error - No Exchange with identifier "+b+" found in the network");
				return actionMessage+": Error - No Exchange with identifier "+b+" found in the network";
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
				System.out.println("Error - No mobile phone with identifier "+a+" found in the network");
				return actionMessage+": Error - No mobile phone with identifier "+a+" found in the network";
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
				System.out.println("Error - No Exchange with identifier "+a+" found in the network");
				return actionMessage+": Error - No Exchange with identifier "+a+" found in the network";
			} catch (IndexOutOfBoundsException e) {
				System.out.println("Error - No Exchange which is "+b+"th child of Exchange "+a);
				return actionMessage+": Error - No Exchange which is "+b+"th child of Exchange "+a;
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
				System.out.println("Error - No Exchange with identifier "+a+" found in the network");
				return actionMessage+": Error - No Exchange with identifier "+a+" found in the network";
			}
			
		}	
		return " ";
	}
}
