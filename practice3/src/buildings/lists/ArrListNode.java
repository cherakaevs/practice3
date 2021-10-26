package buildings.lists;

import buildings.Space;

import java.io.Serializable;

public class ArrListNode implements Serializable, Cloneable {
    public Space space;
    public ArrListNode next;

    public ArrListNode(Space space) {
        this.space = space;
        next = null;
    }

    public ArrListNode(Space space, ArrListNode next){
        this.space = space;
        this.next = next;
    }

    public Space getSpace() {
        return space;
    }

    public Object clone(){
        Object result = null;
        try {
            result = super.clone();
            ((ArrListNode)result).space = (Space)space.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return result;
    }

}
