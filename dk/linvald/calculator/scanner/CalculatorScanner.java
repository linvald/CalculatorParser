package dk.linvald.calculator.scanner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayList;



public class CalculatorScanner extends AbstractScanner {
	
	private File _calculatorFile;
	private ArrayList _queue;
	private StreamTokenizer _strtok;
	InputStreamReader isr ;
	BufferedReader br;
	
	
	public CalculatorScanner(File calculatorFile) {
		this._queue = new ArrayList();
		this._calculatorFile = calculatorFile;
		try {
			this._strtok = new StreamTokenizer(new FileReader(_calculatorFile));
			init();
			getNextToken();
		} catch (FileNotFoundException e1) {
			throw new Tokenerror("Error opening file " + _calculatorFile);
		}
	}
	
	public CalculatorScanner(){
		this._queue = new ArrayList();
		isr = new InputStreamReader(System.in);
		br = new BufferedReader(isr);
	}
	
	public void addExppression(String all) {
		//String[] splitted = all.split(" ");
		char[]tokens = all.toCharArray();
		for (int i = 0; i < tokens.length; i++) {
			_queue.add( new Character( tokens[i]));
		}
	}

	private void init(){
		this._strtok.resetSyntax();
		this._strtok.whitespaceChars(' ', ' ');
		this._strtok.whitespaceChars('\t','\t');
		this._strtok.whitespaceChars('\n','\n');
		this._strtok.whitespaceChars('\r','\r');
		
		this._strtok.parseNumbers();
	}	
		/*if(!_calculatorFile.exists()){
			System.err.println("Couldnt find file");
			throw new Exception("The calculator file dosent exist...");
		}else{
			FileReader     aFile     = new FileReader(this._calculatorFile);
			BufferedReader inputFile = new BufferedReader(aFile);

 			
			String aLine;
			while ((aLine = inputFile.readLine()) != null) {
				//aLine  = aLine.trim();
				if (!aLine.equals("")) {
					String[] splitted = aLine.split(" ");
					for (int i = 0; i < splitted.length; i++) {
						_queue.add((String)splitted[i]);
					}
				}
			} // while
		}
	}*/
	
	/*public char getNextToken(){	
			if(!this._queue.isEmpty()){
				return ((Character)this._queue.remove(0)).charValue();
			}else{
				return Character.UNASSIGNED;
			}	
	}*/
	public void getNextToken(){
		if(tok != EOF){
			try {
				tok = _strtok.nextToken();	
				switch(tok){
					case StreamTokenizer.TT_EOF:
						tok = EOF; break;
					case StreamTokenizer.TT_EOL:
						System.out.println("END OF LINE");
						break;	
					case StreamTokenizer.TT_NUMBER:
						nval = this._strtok.nval;
						sval = this._strtok.sval;
						break;	
					case '+': case '-': case '*': case '/':
						nval = this._strtok.nval;
						sval = this._strtok.sval;
						break;	
					default:
						throw new Tokenerror("Illegal token " + this);	 	
				}
			} catch (IOException e) {
				throw new Tokenerror(e.getMessage());
			}
		}
	}
	
	public boolean hasMoreTokens(){
		return !this._queue.isEmpty();
	}

}
class Tokenerror extends IllegalArgumentException{
	public Tokenerror(String s)	{
		super(s);
	}
}