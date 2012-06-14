/*
 * Created on 12-09-2003 
 * 
 * CommandAST is intended to 
 */
package dk.linvald.calculator.ast;

/**
 * @author linvald
 */
public class CommandAST extends AST {
	public ExpressionAST E;
	public int _result;
	
	public CommandAST(ExpressionAST E){
		this.E = E;
	}
	
	public void setResult(int result){
		this._result = result;
	}
	
	public String toString(){
		return dk.linvald.calculator.Util.COMMANDINDENT + "CommandAST [result="+ _result + "]:\n" + E;
	}
}
