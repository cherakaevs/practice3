package buildings.dwelling.hotel;

import buildings.Floor;
import buildings.dwelling.Dwelling;
import buildings.Space;

public class Hotel extends Dwelling {
    private HotelFloor[] floors;

    public Hotel(int floorsCount, int[] spacesCount){
        super(floorsCount, spacesCount);
    }

    public Hotel(Floor[] floors){
        super(floors);
    }

    public int getHotelStars(){
        int maxStars = 0;
        for (int i = 0; i < floors.length; i++){
            if (floors[i].getStarsNum() > maxStars){
                maxStars = floors[i].getStarsNum();
            }
        }
        return maxStars;
    }

    public Space getBestSpace(){
        Space bestSpace = null;
        double v = 0;
        for (int i = 0; i < floors.length; i++){
            for (int j = 0; j < floors[i].getSpacesNum(); j++){
                double value = 0;
                switch (floors[i].getStarsNum()){
                    case (1):
                        value = 0.25 * floors[i].getSpace(j).getSquare();
                        break;
                    case (2):
                        value = 0.5 * floors[i].getSpace(j).getSquare();
                        break;
                    case (3):
                        value = 1.0 * floors[i].getSpace(j).getSquare();
                        break;
                    case (4):
                        value = 1.25 * floors[i].getSpace(j).getSquare();
                        break;
                    case (5):
                        value = 1.5 * floors[i].getSpace(j).getSquare();
                        break;
                }
                if(value > v){
                    bestSpace = floors[i].getSpace(j);
                    v = value;
                }
            }
        }
        return bestSpace;
    }

    public String toString(){
        String str = new String("Hotel (" + getHotelStars() + ", " + getFloorsNum() +", ");
        for (int i = 0; i < getFloorsNum(); i++){
            str += getFloor(i).toString();
        }
        str += ")";
        return str;
    }

    public boolean equals(Object object){
        boolean flag = false;
        if((object instanceof Hotel) && ((Hotel) object).getFloorsNum() == this.getFloorsNum()){
            for (int i = 0; i < getFloorsNum(); i++){
                if(this.getFloor(i).equals(((Hotel) object).getFloor(i)) == false){
                    return flag;
                }
            }
        }
        flag = true;
        return flag;
    }

    public int hashCode(){
        int result = 0;
        for (int i = 0; i < getFloorsNum(); i++){
            result += getFloorsNum() ^ getFloor(i).hashCode();
        }
        return result;
    }
}
