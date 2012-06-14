/*
 * Created on 12-09-2003 
 * 
 * ExpressionAST is intended to 
 */
package dk.linvald.calculator.ast;

import java.util.ArrayList;
import java.util.Iterator;

import dk.linvald.calculator.Util;

/**
 * @author linvald
 */
public class ExpressionAST extends AST {
	public ArrayList _numeralList = new ArrayList();
	
	public void addNumeral(AST n) {
		_numeralList.add(n);
	}
	public String toString(){
		String s = Util.EXPRESSIONINDENT + "ExpressionAST:\n";
		for (Iterator iter = _numeralList.iterator(); iter.hasNext();) {
			Object o =  iter.next();
			if(o instanceof NumeralAST ){
				NumeralAST element = (NumeralAST)o;
				s+=Util.NUMERALINDENT + element;
			}
			if(o instanceof OperatorAST){
				OperatorAST element = (OperatorAST)o;
				s+=Util.DIGITINDENT +element;
			}
			s+="\n";
			
		}
		return s;
	}
}
