package buildings.dwelling.hotel;

import buildings.Space;
import buildings.dwelling.DwellingFloor;

public class HotelFloor extends DwellingFloor {
    private int starsNum;
    private static final int STARS_NUM = 1;

    public HotelFloor(int num){
        super(num);
        starsNum = STARS_NUM;
    }

    public HotelFloor(Space[] spaces){
        super(spaces);
        starsNum = STARS_NUM;
    }

    public int getStarsNum(){
        return starsNum;
    }

    public void setStarsNum(int num){
        starsNum = num;
    }

    public String toString(){
        String str = new String("HotelFloor (" + starsNum +", " + this.getSpacesNum() + ", ");
        for (int i = 0; i < getSpacesNum(); i++){
            str += this.getSpace(i).toString();
        }
        str += ")";
        return str;
    }

    public boolean equals(Object object){
        boolean flag = false;
        if((object instanceof HotelFloor) && (((HotelFloor) object).getSpacesNum() == this.getSpacesNum())){
            for (int i = 0; i < getSpacesNum(); i++){
                if(getSpace(i).equals(((HotelFloor)object).getSpace(i)) == false){
                    return flag;
                }
            }
        }
        flag = true;
        return flag;
    }

    public int hashCode(){
        int result = 0;
        for (int i = 0; i < getSpacesNum(); i++){
            result += starsNum ^ getSpacesNum() ^ getSpace(i).hashCode();
        }
        return result;
    }

}
