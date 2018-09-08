import java.util.*;

/**
 * ExchangeList
 */
public class ExchangeList {
    LinkedListimpl<Exchange> ListOfExchange = new LinkedListimpl<Exchange>();
    
    // public Exchange Find1(RoutingMapTree b,int a){
    //     // if(b==null){
    //     //     return null;
    //     // }
    //     if(b.root.baseno==a)
    //     {
    //         return b.root;
    //     }
        
    //     for (int i = 0; i < b.root.children.size(); i++) {
    //         if(b.root.children!=null)
    //         {
    //             Find1(b.root.children.get(i), a);
    //             if(b.root.children.get(i).root.baseno==a){
    //                 return b.root.child(i);
    //             }
    //         }
    //         else break;
    //     }
    //     return null;
    // }
    public Exchange Find(int a)
    {
        for (int i = 0; i < this.ListOfExchange.size(); i++) {
            if(this.ListOfExchange.get(i).baseno==a)
            {
                return this.ListOfExchange.get(i);
            }
        }
        return null;
    }
}