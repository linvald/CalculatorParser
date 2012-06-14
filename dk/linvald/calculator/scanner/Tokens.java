/*
 * Created on 15-09-2003 by jesper
 * This class should 
 */
package dk.linvald.calculator.scanner;

/**
 * @author jesper
 */
public abstract class Tokens {
	
	public final static String MINUS = "-";
	public final static String PLUS = "+";
	public final static String TIMES = "*";
	public final static String DIVIDE = "/";
	
	public final static char MINUSCHAR = '-';
	public final static char PLUSCHAR = '+';
	public final static char TIMESCHAR = '*';
	public final static char DIVIDECHAR = '/';

	
	public final static char[]DIGITS={'0','1','2','3','4','5','6','7','8','9'};
	
	public static boolean isDigit(char c){
		boolean isIt = false;
		for (int i = 0; i < DIGITS.length; i++) {
			if(c ==(char)DIGITS[i]){
				return true;
			}else{
				return false;
			}
		}
		return false;
	}
	
	public static boolean isOperator(char c){
		if(c==MINUSCHAR || c==PLUSCHAR || c==TIMESCHAR || c==DIVIDECHAR)
			return true;
		return false;
	}
	


	
}
