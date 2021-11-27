package buildings.dwelling.hotel;

import buildings.Floor;
import buildings.dwelling.Dwelling;
import buildings.Space;

public class Hotel extends Dwelling {
    private int starsNum;
    private static final int STARS_NUM = 1;

    public Hotel(int floorsCount, int[] spacesCount){
        super(floorsCount, spacesCount);
        for (int i = 0; i < floorsCount; i++){
            setFloor(i, new HotelFloor(spacesCount[i]));
        }
        starsNum = STARS_NUM;
    }

    public Hotel(Floor[] floors){
        super(floors);
        int max = ((HotelFloor)floors[0]).getStarsNum();
        for(int i = 0; i < floors.length; i++){
            if (floors[i] instanceof HotelFloor){
                if (max < ((HotelFloor) floors[i]).getStarsNum()){
                    max = ((HotelFloor) floors[i]).getStarsNum();
                }
            }
        }
        starsNum = max;
    }



    public Space getBestSpace(){
        Space bestSpace = null;
        double v = 0;
        double coef = 0.25;
        double square;
        for(int i = 0; i < getFloorsNum(); i++){
            if(!(getFloor(i) instanceof HotelFloor)) continue;
            switch (((HotelFloor) getFloor(i)).getStarsNum()){
                case 1:
                    coef = 0.25;
                    break;
                case 2:
                    coef = 0.5;
                    break;
                case 3:
                    coef = 1;
                    break;
                case 4:
                    coef = 1.25;
                    break;
                case 5:
                    coef = 1.5;
                    break;
            }
            square = getFloor(i).getBestSpace().getSquare();

            if (v < square * coef){
                v = square * coef;
                bestSpace = getFloor(i).getBestSpace();
            }
        }
        return bestSpace;
    }

    public String toString(){
        String str = new String("Hotel (" + starsNum + ", " + getFloorsNum() +", ");
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
            flag = true;
        }
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
