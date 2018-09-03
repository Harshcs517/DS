import java.util.Objects;

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
		//ArrayIndexOutOfBounddsException if b is not there
		try {
			a.switchOn();
			if(a.parent != null)
				a.parent.setOfPhones.phones.Delete(a);
			a.parent = b;
			b.setOfPhones.phones.Insert(a);
		} catch (IndexOutOfBoundsException e) {
			
		}
		
		//Required deletion of element where it exist
	}

	public void switchOff(MobilePhone a)
	{
		a.switchOff();
	}
	public void performAction(String actionMessage) {
		System.out.println(actionMessage);
		int a,b;
		String[] split = actionMessage.split(" ",0);
		
		if(Objects.equals(split[0], "addExchange"))
		{	
			
			a = Integer.parseInt(split[1]);
			try {
				//b may exist
				b = Integer.parseInt(split[2]);
				Exchange node = new Exchange(b);
				Exchange parent = tree.ListOfExchange.get(a);
				parent.children.add(node);
				tree.ListOfExchange.add(node);
				// System.out.println(parent.children);
				return;
			} catch (ArrayIndexOutOfBoundsException e) {
				System.out.println("b is Required");
				return;
			}		
		}
		else if(Objects.equals(split[0], "switchOnMobile")) 
		{
			a = Integer.parseInt(split[1]);
			b = Integer.parseInt(split[2]);
			// System.out.println(tree.ListOfExchange.size()-1);
			for (int i = tree.ListOfExchange.size()-1; i >=0; i=i-1) {
				tree.ListOfExchange.elementAt(i).residentSet();
				
				//This may take time
			}
			int i;
			for(i=0;i< root.setOfPhones.phones.head.size() ;i++)
			{
				if(root.setOfPhones.phones.head.get(i).number()==a)
				{
					switchOn(root.setOfPhones.phones.head.get(i), tree.ListOfExchange.get(b));
					return;
				}
			}
			if(i==root.setOfPhones.phones.head.size())
			{
				//phone not registered
				MobilePhone phoneRegister = new MobilePhone(a);
				switchOn(phoneRegister, tree.ListOfExchange.get(b));
			}
			for (int j = tree.ListOfExchange.size()-1; j >=0; j=j-1) {
				// System.out.printf("%d",j);
				tree.ListOfExchange.elementAt(j).residentSet();
				
				//This may take time
			}
		}
		else if (Objects.equals(split[0], "switchOffMobile"))
		{
			a = Integer.parseInt(split[1]);
			int i=0;
			for(i=0;i< root.setOfPhones.phones.head.size() ;i++)
			{
				if(root.setOfPhones.phones.head.get(i).number()==a)
				{
					switchOff(root.setOfPhones.phones.head.get(i));
					return;
				}
			}
			if(i==root.setOfPhones.phones.head.size())
			{
				System.out.println("Element Does not exist");
			}
		}
		else if (Objects.equals(split[0], "queryNthChild")) 
		{
			a = Integer.parseInt(split[1]);
			b = Integer.parseInt(split[2]);
			try {
				System.out.println(tree.ListOfExchange.get(a).children.get(b).baseno);
			} catch (IndexOutOfBoundsException e) {
				System.out.println("Child does not exist");
			}
		}
		else if(Objects.equals(split[0], "queryMobilePhoneSet"))
		{
			a = Integer.parseInt(split[1]);
			// System.out.println(tree.ListOfExchange.get(a).setOfPhones.phones.head.size() + " itna");
			for (int i = tree.ListOfExchange.size()-1; i >=0; i=i-1) {
				tree.ListOfExchange.elementAt(i).residentSet();
				//This may take time
			}
			
			for(int j=0; j<tree.ListOfExchange.get(a).setOfPhones.phones.head.size() ;j++)
			{
				if(tree.ListOfExchange.get(a).setOfPhones.phones.head.get(j).status())
					System.out.printf("%d ",tree.ListOfExchange.get(a).setOfPhones.phones.head.get(j).number());
				}
				System.out.printf("\n");
			}	
		}	
	}

