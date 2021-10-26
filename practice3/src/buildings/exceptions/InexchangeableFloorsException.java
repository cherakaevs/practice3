package buildings.exceptions;

public class InexchangeableFloorsException extends Error{
    public InexchangeableFloorsException(){
        String string = "InexchangeableFloorsException\nImpossible to replace this floors\n Please choose another indexes";
        throw new Error(string);
    }
}
