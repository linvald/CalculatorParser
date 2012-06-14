/*
 * Created on 12-09-2003 
 * 
 * Main is intended to 
 */
package dk.linvald.calculator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import dk.linvald.calculator.ast.CommandAST;
import dk.linvald.calculator.parser.CalculatorParser;
import dk.linvald.calculator.parser.Parser;
import dk.linvald.calculator.scanner.CalculatorScanner;
import dk.linvald.calculator.scanner.Scanner;

/**
 * @author linvald
 */
public class Main {

	public static void main(String[] args) {
		
		//for reading the language from a text file...
		/*if (args.length == 0) {
			System.out.println("Starting scanner...");
			Scanner scanner = new Scanner(new File("inputtext.txt"));
			Parser parser = new Parser();
			CommandAST cmdAst = parser.parseCommand();
			System.out.println("AST:");
			System.out.println(cmdAst);
		}*/
		
		//reading from cmd...
		InputStreamReader isr ;
		BufferedReader br;
		isr = new InputStreamReader(System.in);
		br = new BufferedReader(isr);
		Scanner scanner;
		Parser p; 
			while (true) {
				String s = null;
				try {
					s = br.readLine();
					scanner = new Scanner();
					scanner.addExppression(s);
					p = new Parser(scanner);
					CommandAST cmdAst = p.parseCommand();
					if (cmdAst != null) {
						System.out.println(s + " " + cmdAst._result);
						System.out.println("---------- AST ----------");
						System.out.println(cmdAst);
						System.out.println("-------------------------");
					}
				} catch (IOException ioe) {
					System.err.println(ioe);
				}
			}
		}
			/*	InputStreamReader isr ;
				BufferedReader br;
				isr = new InputStreamReader(System.in);
				br = new BufferedReader(isr);
				CalculatorScanner scanner;
				CalculatorParser p; 
					while (true) {
						String s = null;
						try {
							s = br.readLine();
							scanner = new CalculatorScanner();
							scanner.addExppression(s);
							p = new CalculatorParser(scanner);
							CommandAST cmdAst = p.parseCommand();
							if (cmdAst != null) {
								System.out.println(s + " " + cmdAst._result);
								System.out.println("---------- AST ----------");
								System.out.println(cmdAst);
								System.out.println("-------------------------");
							}
						} catch (IOException ioe) {
							System.err.println(ioe);
						}
					}
				}*/
}
