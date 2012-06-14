/*
 * Created on 12-09-2003 
 * 
 * Parser is intended to parse an Expression
 * 
 * TODO: scan for characters insted of splitting on spaces
 */
package dk.linvald.calculator.parser;

import java.util.ArrayList;

import dk.linvald.calculator.ast.*;
import dk.linvald.calculator.scanner.Scanner;
import dk.linvald.calculator.scanner.Tokens;

/**
 * @author linvald
 * 3+5-2*6=
 * 
 * Grammar:	
 * Command		::= Expression =
 * Expression	::= Numeral ((+|-|*) Numeral)*
 * Numeral		::= Digit Digit* (As Token this would be just one - no while needed)
 * Digit		::= 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9	
 * 
 */
public class Parser {
	private char _currentToken;
	private char _lastToken;
	private int _result = 0;
	private Scanner _scanner;
	private ArrayList _traversed = new ArrayList();
	private NumeralAST _lastNumeral;
	private char _lastOperator = Character.UNASSIGNED;
	
	public Parser(Scanner scanner){
		//this._scanner = new Scanner(new File("inputtext.txt"));
		this._scanner = scanner;
		_currentToken = _scanner.getNextToken();
		_traversed.add(new Character(_currentToken));
	}
	
	
	
	private void accept(char expectedChar){
		if(expectedChar== '='){
			return;
		}
		if(_currentToken == expectedChar){		
			char next = _scanner.getNextToken();
			if (next != Character.UNASSIGNED) {
				_lastToken = _currentToken;
				_currentToken = next;
				_traversed.add(new Character(_currentToken));
			}
			if(_currentToken == Character.UNASSIGNED){
				System.err.println("Error: accept - currentToken is null - lastToken:" + _lastToken);
				return;
			}
		}else{
			System.err.println("ACCEPT FAILED:" +_currentToken);
		}
	}
	/**
	 * Expression =
	 */
	public CommandAST parseCommand(){
		CommandAST cmdAst = null;
		ExpressionAST expAst = null;		
		
		expAst = parseExpression();
		if(expAst != null){
			cmdAst = new CommandAST(expAst);
			if(_currentToken == '='){
				accept('=');
				cmdAst.setResult(this._result);
					return cmdAst;
		
			}else{
				System.err.println("ERROR: you need to specify an equals sign(=) after expression " );
			}
		}else{
			System.err.println("Input error: Couldn´t construct AST - error in expression!");
		}
		return null;
	}
	
	/**
	 * Numeral ((+|-|*) Numeral)*
	 */
	public ExpressionAST parseExpression(){
		ExpressionAST eAST = new ExpressionAST();
		if(_currentToken>='0' && _currentToken <= '9' || Tokens.isOperator(_currentToken)){
			while(_currentToken>='0' && _currentToken <= '9' || Tokens.isOperator(_currentToken))
				switch(_currentToken){
					case Tokens.DIVIDECHAR:
					case Tokens.PLUSCHAR:
					case Tokens.TIMESCHAR:
					case Tokens.MINUSCHAR:
						eAST.addNumeral(new OperatorAST(_currentToken));
						_lastOperator = _currentToken;
						accept(_currentToken);
						break;
					case '0': case'1': case'2': case'3':case'4':case'5':case'6':case'7':case'8':case'9':
						NumeralAST numAST = parseNumeral();		
						eAST.addNumeral(numAST);			
					default:	
						break;	
				}
				return eAST;
		}else{
			return null;
		}
	}
	
	/**
	 * Digit Digit*
	 */
	public NumeralAST parseNumeral(){
		NumeralAST numAST = new NumeralAST();
		DigitAST dAST;
		while (_currentToken>='0' && _currentToken <= '9') {
			dAST = parseDigit();
			numAST.addDigit(dAST);		
		}
		if(numAST != null){
			_lastNumeral = numAST;	
			updateResult();	
			return numAST;
		}else{
			System.err.println("ERROR: failed in parseNumeral -" + _currentToken);
			return null;
		}	
	}
	
	/**
	 * 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9
	 */
	public DigitAST parseDigit(){
		if(_currentToken>='0' && _currentToken <= '9'){
			accept(_currentToken);
			return new DigitAST(Character.getNumericValue(_lastToken));	
		}else{
			System.err.println("ERROR: failed in parseDigit - " + "("+ _currentToken + ") is probably not a legal digit" );
			return null;
		}
	}

	/**
	 * Called when a digit has been resolved
	 */
	private void updateResult() {
		if (_lastToken != Character.UNASSIGNED && _lastOperator != Character.UNASSIGNED) {
			
			char operator = this._lastOperator;
			switch (operator) {
				case Tokens.DIVIDECHAR :
					_result = _result / _lastNumeral.getNumeral();
					break;
				case Tokens.MINUSCHAR :
					_result = _result - _lastNumeral.getNumeral();
					break;
				case Tokens.PLUSCHAR :
					_result = _result + _lastNumeral.getNumeral();
					break;
				case Tokens.TIMESCHAR :
					_result = _result * _lastNumeral.getNumeral();
					break;
				case '=':
				_result = _result + _lastNumeral.getNumeral();
					break;	
				default :
					System.err.println("Unknow operator...." + operator);
					break;
		  }
		}else{
			try {
				_result = _lastNumeral.getNumeral(); //the first number
			} catch (NumberFormatException e) {
				System.err.println("ERROR: expected this to be the first number and add it as a result" );
				e.printStackTrace();
			}
		}
			
	}
}
