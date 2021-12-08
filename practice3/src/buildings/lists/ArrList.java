package buildings.lists;

import buildings.office.Office;
import buildings.Space;

import java.io.Serializable;
import java.util.Iterator;

public class ArrList implements Serializable, Cloneable{
    private ArrListNode head;
    private ArrListNode tail;
    private int size;

    public ArrList(){
        head = null;
        tail = null;
        size = 0;
    }

    public ArrList(int num){
        for(int i = 0; i < num; i++){
            pushBack(new Office());
        }
    }

    public int length() {
        return size;
    }

    public boolean isEmpty(){
        return head == null;
    }

    public void pushBack(Space space){
        if (head == null){
            head = new ArrListNode(space);
            tail = head;
        }
        else {
            if (size == 1){
                ArrListNode tmp = new ArrListNode(space, head);
                tail = tmp;
                head.next = tmp;
            }
            else {
                ArrListNode newArrListNode = new ArrListNode(space, head);
                tail.next = newArrListNode;
                tail = newArrListNode;
            }
        }
        size++;
    }

    public void pushFront(Space space){
        if (head == null){
            head = new ArrListNode(space, null);
            tail = head;
        }
        else {
            if (size == 1){
                ArrListNode tmp = new ArrListNode(space, tail);
                head = tmp;
                tail.next = tmp;
            }
            else {
                ArrListNode tmp = new ArrListNode(space, head);
                head = tmp;
                tail.next = tmp;
            }
        }
        size++;
    }

    public void popFront(){
        head = head.next;
        tail.next = head;
        size--;
    }

    public void insert(Space space, int num){
        if (num == 0){
            pushFront(space);
        }
        else {
            if (num == size){
                pushBack(space);
            }
            else {
                ArrListNode currentArrListNode = head;
                for (int i = 0; i < num - 1; i++){
                    currentArrListNode = currentArrListNode.next;
                }
                ArrListNode newArrListNode = new ArrListNode(space, currentArrListNode.next);
                currentArrListNode.next = newArrListNode;
                size++;
            }
        }
    }

    public void remove(int num){
        if (num == 0){
            popFront();
        }
        else {
            ArrListNode currentArrListNode = head;
            for (int i = 0; i < num - 1; i++){
                currentArrListNode = currentArrListNode.next;
            }
            if (num == size - 1){
               ArrListNode del = currentArrListNode.next;
               currentArrListNode.next = del.next;
               tail = currentArrListNode;
            }
            else {
                ArrListNode del = currentArrListNode.next;
                currentArrListNode.next = del.next;
            }
            size--;
        }
    }

    public ArrListNode getNode(int num){
        int count = 0;
        ArrListNode currentArrListNode = head;
        if(num == size - 1){
            return tail;
        }
        while (currentArrListNode != tail){
            if (count == num) {
                break;
            }
            currentArrListNode = currentArrListNode.next;
            count++;
        }
        return currentArrListNode;
    }

    public Object clone(){
        Object result = null;
        try {
            result = super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return result;
    }
}

