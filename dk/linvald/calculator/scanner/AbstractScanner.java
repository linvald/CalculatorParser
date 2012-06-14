/*
 * Created on 16-09-2003 by jesper
 * This class should 
 */
package dk.linvald.calculator.scanner;

/**
 * @author jesper
 */
public abstract class AbstractScanner {
	
	public final static int EQUALS = 61;
	public final static int MINUS = 45;
	public final static int PLUS = 43;
	public final static int EOF = -100;

	public final static int DIVIDE = 47;
	public final static int TIMES = 42;
	
	public int tok;
	public double nval;
	public String sval;
	
	abstract public void getNextToken();
	
	public IllegalArgumentException parseError(String msg){
		return new Parseerror(msg + "but found " + (char)tok);
	}
}
class Parseerror extends IllegalArgumentException{
	public Parseerror(String s){
		super(s);	
	}
}
