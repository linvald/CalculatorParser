/*
 * Created on 12-09-2003 
 * 
 * DigitAST is intended to 
 */
package dk.linvald.calculator.ast;

/**
 * @author linvald
 */
public class DigitAST extends AST {
	public int digit;
	
	public DigitAST(int c){
		this.digit = c;
	}
	
	public String toString(){
		return dk.linvald.calculator.Util.DIGITINDENT + "DigitAST:" + digit;
	}
}
