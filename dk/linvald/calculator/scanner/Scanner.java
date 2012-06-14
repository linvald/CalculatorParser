
package dk.linvald.calculator.scanner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;



public class Scanner {
	
	private File _calculatorFile;
	private ArrayList _queue;
	InputStreamReader isr ;
	BufferedReader br;
	
	
	public Scanner(File calculatorFile) {
		this._queue = new ArrayList();
		this._calculatorFile = calculatorFile;
		try {
			init();
		} catch (Exception e) {
			System.out.println("Exception caught: " +e);
			//System.exit(1);
		}
	}
	
	public Scanner(){
		this._queue = new ArrayList();
		isr = new InputStreamReader(System.in);
		br = new BufferedReader(isr);
	}
	
	public void addExppression(String all) {
		char[]tokens = all.toCharArray();
		for (int i = 0; i < tokens.length; i++) {
			_queue.add( new Character( tokens[i]));
		}
	}

	private void init() throws Exception {
		if(!_calculatorFile.exists()){
			System.err.println("Couldnt find file");
			throw new Exception("The calculator file dosent exist...");
		}else{
			FileReader     aFile     = new FileReader(this._calculatorFile);
			BufferedReader inputFile = new BufferedReader(aFile);

			// Read file until we hit end of file (i.e. a readLine() returns a null)
			String aLine;
			while ((aLine = inputFile.readLine()) != null) {
				//aLine  = aLine.trim();
				if (!aLine.equals("")) {
					String[] splitted = aLine.split(" ");
					for (int i = 0; i < splitted.length; i++) {
						_queue.add((String)splitted[i]);
					}
				}
			}	
		}
	}	
	public char getNextToken() {
		if (!this._queue.isEmpty()) {
			char c = ((Character) this._queue.get(_queue.size() - 1)).charValue();
			if (c == '=') {
				char tok = ((Character) this._queue.remove(0)).charValue();
				switch (tok) {
					case '+' :
					case '-' :
					case '*' :
					case '/' :
					case '=' :
					case '0' :
					case '1' :
					case '2' :
					case '3' :
					case '4' :
					case '5' :
					case '6' :
					case '7' :
					case '8' :
					case '9' :
						return tok;
					default :
						System.err.println("Unknow token---");
						try {
							throw new Exception("Illegal token " + tok);
						} catch (Exception e) {
							e.printStackTrace();
						}
				}
			}
		} else {
			System.err.println("Must end with equals");
		}
		return 0;
	}
	
	public boolean hasMoreTokens(){
		return !this._queue.isEmpty();
	}

}

