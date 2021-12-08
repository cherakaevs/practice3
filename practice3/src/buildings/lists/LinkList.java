package buildings.lists;

import buildings.Floor;
import buildings.office.OfficeFloor;

import java.io.Serializable;
import java.util.Iterator;

public class LinkList implements Serializable, Cloneable {
    private LinkListNode head;
    private LinkListNode tail;
    private int size;

    public LinkList(){
        head = null;
        tail = null;
        size = 0;
    }

    public LinkList(int num){
        for(int i = 0; i < num; i++){
            pushBack(new OfficeFloor(1));
        }
    }

    public int length() {
      return size;
    }

    public void pushBack(Floor floor){
        if (head == null){
            head = new LinkListNode(floor);
            tail = head;
        }
        else {
            if (size == 1){
                LinkListNode tmp = new LinkListNode(floor, head, head);
                tail = tmp;
                tail.next = head;
                tail.prev = head;
                head = tail.next;
                head.next = tail;
            }
            else {
                LinkListNode newLinkListNode = new LinkListNode(floor, head, tail);
                tail.next = newLinkListNode;
                head.prev = newLinkListNode;
                tail = newLinkListNode;
            }
        }
        ++size;
    }

    public void pushFront(Floor floor){
        LinkListNode tmp = new LinkListNode(floor);
        tail.next = tmp;
        head.prev = tmp;
        head = tmp;
        ++size;
    }

    public void popFront(){
        head = head.next;
        tail.next = head;
        head.prev = tail;
        --size;
    }

    public void insert(Floor newFloor, int num){
        if (num == 0){
            pushFront(newFloor);
        }
        else {
            if (num == size){
                pushBack(newFloor);
            }
            else {
                LinkListNode currentLinkListNode = head;
                for (int i = 0; i < num - 1; i++){
                    currentLinkListNode = currentLinkListNode.next;
                }
                LinkListNode newLinkListNode = new LinkListNode(newFloor, currentLinkListNode.prev, currentLinkListNode);
                currentLinkListNode.next.prev = newLinkListNode;
                currentLinkListNode.next = newLinkListNode;
                ++size;
            }
        }
    }

    public void remove(int num){
        if (num == 0){
            popFront();
        }
        else {
            LinkListNode currentLinkListNode = head;
            for (int i = 0; i < num - 1; i++){
                currentLinkListNode = currentLinkListNode.next;
            }
            if (num == size - 1){
                LinkListNode del = currentLinkListNode.next;
                currentLinkListNode.next = del.next;
                head.prev = currentLinkListNode;
                tail = currentLinkListNode;
            }
            else {
                LinkListNode del = currentLinkListNode.next;
                currentLinkListNode.next.prev = currentLinkListNode;
                currentLinkListNode.next = del.next;
            }
            --size;
        }
    }

    public LinkListNode getNode(int num){
        int count = 0;
        LinkListNode currentLinkListNode = head;
        if(num == size - 1){
            return tail;
        }
        while (currentLinkListNode != tail){
            if (count == num) {
                break;
            }
            currentLinkListNode = currentLinkListNode.next;
            ++count;
        }
        return currentLinkListNode;
    }

    public Object clone() {
        LinkList result = null;
        try {
            result = (LinkList) super.clone();
            result.head = (LinkListNode) head.clone();
            result.tail = (LinkListNode) tail.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return result;
    }
}

