package sample.Exception;

public class UserDoesNotExistException extends Exception{
    /**
     * The UserDoesNotExistException occurs when an action that requires a user is called, but the user does not exist.
     * The user will be created in order to execute the action
     */
    public UserDoesNotExistException(){
        super("The user does not exist. Creating a new user");
    }

    /**
     * User does not exist exception
     * @param e error message
     */
    public UserDoesNotExistException(String e){
        super(e);
    }
}
