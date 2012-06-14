/*
 * Created on 16-09-2003 by jesper
 * This class should 
 */
package dk.linvald.calculator.ast;

/**
 * @author jesper
 */
public class OperatorAST extends AST {
	public char OPERATOR;
	public String S_OPERATOR;
	
	public OperatorAST(char c){
		this.OPERATOR = c;
		this.S_OPERATOR = new Character(c).toString();
	}
	public OperatorAST(String c){
		this.S_OPERATOR = c;
	}
	public String toString(){
		return ""+S_OPERATOR;
	}
}
