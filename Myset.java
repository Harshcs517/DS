import java.util.*;

/**
 * Myset
 */
public class Myset{
    
    LinkedList<MobilePhone> head = new LinkedList<MobilePhone>();
    int size = 0;
    public Boolean IsEmpty(){
        //returns true if set is empty
        return size==0;
    }
    public Boolean IsMember(MobilePhone o){
        //returns true if o is in the set, false otherwise.
        return head.contains(o);
    }
    public void Insert(MobilePhone o){
        //insert o in the set.
        if(!this.IsMember(o))
        {
            head.add(o);
            size=size+1;
        }
        else{
            System.out.println("ELement already exist");
        }
    }
    public void Delete(MobilePhone o){
        //Deletes o from the set, throws exception if o is not in the set.
        try{
            if(this.IsMember(o))
            {
                head.remove(o);
                size=size-1;
            }
            else{
                System.out.println("Element does not exist");
            }
        }
        catch(IndexOutOfBoundsException e)
        {
            System.out.println("Your set is empty");
        }
    }
    public Myset Union(Myset a){
        // returns the set Which is the union of the current set with the set a
        Myset union = new Myset();
        int check=0;
        union.head.addAll(this.head);
        for(int i=0; i< a.head.size(); i++)
        {
            for(int j=0; j< this.head.size(); j++){
                if(this.head.get(j)==a.head.get(i))
                {
                    check=0;
                    break;
                }
                check=check+1;
            }
            if(check==this.head.size())
            {
                union.head.add(a.head.get(i));
                check=0;
            }
        }
        return union;
    }
    public Myset Intersection(Myset a){
        // returns a set which is the intersection of the current set with the set a
        Myset inter = new Myset();
        for(int i=0; i< a.head.size(); i++)
        {
            for(int j=0; j< this.head.size(); j++){
                if(this.head.get(j)==a.head.get(i))
                {
                    inter.head.add(a.head.get(i));
                    break;
                }
            }
        }
        return inter;
    }

    public static void main(String[] args) {
        /* Used for checking of this
        Myset check = new Myset();
        check.head.add("A");
        check.head.add("B");
        check.head.add("C");
        check.head.add("D");
        check.head.add("E");
        check.head.add("F");
        check.head.add("G");
        System.out.println(check.head);
        check.IsEmpty();
        check.IsMember("B");
        check.Insert("O");
        System.out.println(check.head);
        check.Delete("A");
        System.out.println(check.head);

        Myset pile = new Myset();
        pile.head.add("A");
        pile.head.add("B");
        pile.head.add("C");

        pile.head=check.Intersection(pile);
        System.out.println(pile.head); */
        Myset check = new Myset();
        MobilePhone a = new MobilePhone(857);
        MobilePhone b = new MobilePhone(855);
        MobilePhone c = new MobilePhone(834);
        MobilePhone d = new MobilePhone(8343);
        check.Insert(a);
        check.Insert(b);
        check.Insert(c);
        System.out.println(check.head);
        Myset pile = new Myset();
        System.out.println(pile.head);
        pile=check.Union(pile);
        System.out.println(pile.head);
        check.Delete(d);
        check.Insert(a);
    }
}