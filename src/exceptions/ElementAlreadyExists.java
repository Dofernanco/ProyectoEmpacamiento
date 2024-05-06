package exceptions;

import java.lang.Exception;

public class ElementAlreadyExists extends Exception{
  public ElementAlreadyExists(){
    super();
  }
  public ElementAlreadyExists(String message){
    super(message);
  }
  
}
