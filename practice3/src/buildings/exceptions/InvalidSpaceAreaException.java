package buildings.exceptions;

public class InvalidSpaceAreaException extends IllegalArgumentException{
    public InvalidSpaceAreaException(int space){
        String string= "InvalidSpaceAreaException\nInvalid argument: " + space + "\nEnter space between 20 and 500";
        throw new IllegalArgumentException(string);
    }
}
