package buildings.exceptions;

public class InexchangeableSpacesException extends Error{
    public InexchangeableSpacesException(){
        String string = "InexchangableSpacesException\nImpossible to replace this spaces\nPlease choose another indexes";
        throw new Error(string);
    }
}
