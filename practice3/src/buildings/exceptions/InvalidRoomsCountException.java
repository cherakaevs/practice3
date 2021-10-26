package buildings.exceptions;

public class InvalidRoomsCountException extends IllegalArgumentException{
    public InvalidRoomsCountException(int num){
        String string = "InvalidRoomsCountException\nInvalid argument: " + num + "\nEnter number between 1 and 10";
        throw new IllegalArgumentException(string);
    }
}
