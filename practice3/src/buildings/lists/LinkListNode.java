package buildings.lists;

import buildings.office.OfficeFloor;
import buildings.Floor;

import java.io.Serializable;

public class LinkListNode implements Serializable, Cloneable {
    public Floor floor;
    public LinkListNode next;
    public LinkListNode prev;

    public LinkListNode() {
        floor = new OfficeFloor(1);
        next = null;
        prev = null;
    }

    public LinkListNode(Floor newFloor) {
        floor = newFloor;
        next = null;
        prev = null;
    }

    public LinkListNode(Floor newFloor, LinkListNode next, LinkListNode prev){
        floor = newFloor;
        this.next = next;
        this.prev = prev;
    }

    public Floor getOffice() {
        return floor;
    }

    public Object clone(){
        LinkListNode result = null;
        try {
            result = (LinkListNode)super.clone();
            result.floor = (Floor)floor.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return result;
    }
}