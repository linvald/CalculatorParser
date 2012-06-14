/*
 * Created on 12-09-2003 
 * 
 * NumeralAST is intended to 
 */
package dk.linvald.calculator.ast;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author linvald
 */
public class NumeralAST extends AST {
	private ArrayList _digits = new ArrayList();
	
	public void addDigit(DigitAST dAST){
		_digits.add(dAST);
	}
	//public DigitAST DIGIT;
	
	public int getNumeral(){
		String s = "";
		for (Iterator iter = _digits.iterator(); iter.hasNext();) {
					s+= ((DigitAST) iter.next()).digit;
		}
		return Integer.parseInt(s);
	}
	
	public String toString(){
		String s = "NumeralAST has " + _digits.size() + " digits:("+this.getNumeral()+")\n";
		for (Iterator iter = _digits.iterator(); iter.hasNext();) {
			s+= (DigitAST) iter.next();
		}
		return dk.linvald.calculator.Util.NUMERALINDENT + s;
	}
}
