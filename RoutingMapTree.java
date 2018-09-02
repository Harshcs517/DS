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
		a.switchOn();
		a.parent = b;
		b.setOfPhones.phones.Insert(a);
	}

	public void switchOff(MobilePhone a)
	{
		a.switchOff();
	}
	public void performAction(String actionMessage) {
		System.out.println(actionMessage);
		int a,b;
		String[] split = actionMessage.split(" ",0);
		if(split[0]=="addExchange")
		{
			a = Integer.parseInt(split[1]);
			try {
				b = Integer.parseInt(split[2]);
				Exchange node = new Exchange(b);
				Exchange parent = tree.ListOfExchange.get(a);
				parent.children.add(node);
				tree.ListOfExchange.add(node);
				return;
			} catch (ArrayIndexOutOfBoundsException e) {
				System.out.println("b is Required");
				return;
			}		
		}
		else if(split[0]=="switchOnMobile") 
		{
			a = Integer.parseInt(split[1]);
			b = Integer.parseInt(split[2]);
			root.residentSet();
			int i;
			for(i=0;i< root.setOfPhones.phones.size ;i++)
			{
				if(root.setOfPhones.phones.head.get(i).number()==a)
				{
					switchOn(root.setOfPhones.phones.head.get(i), tree.ListOfExchange.get(b));
					return;
				}
			}
			if(i==root.setOfPhones.phones.size)
			{
				//phone not registered
				MobilePhone phoneRegister = new MobilePhone(a);
				switchOn(phoneRegister, tree.ListOfExchange.get(b));
			}
			else if (split[0]=="switchOffMobile")
			{
				a = Integer.parseInt(split[1]);
				for(i=0;i< root.setOfPhones.phones.size ;i++)
				{
					if(root.setOfPhones.phones.head.get(i).number()==a)
					{
						switchOff(root.setOfPhones.phones.head.get(i));
						return;
					}
				}
			}
			else if (split[0]=="queryNthChild") 
			{
				a = Integer.parseInt(split[1]);
				b = Integer.parseInt(split[2]);
				try {
					System.out.println(tree.ListOfExchange.get(a).children.get(b).baseno);
				} catch (IndexOutOfBoundsException e) {
					System.out.println("Child does not exist");
				}				
			}
			else if(split[0]=="queryMobilePhoneSet")
			{
				a = Integer.parseInt(split[1]);
				for(int j=0; j<tree.ListOfExchange.get(a).setOfPhones.phones.size ;j++)
				{
					System.out.printf("%d ",tree.ListOfExchange.get(a).children.get(j).baseno);
				}
				System.out.printf("\n");
			}	
		}	
	}
}
