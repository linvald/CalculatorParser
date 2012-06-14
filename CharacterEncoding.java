/*
 * Created on 16-09-2003 by jesper
 * This class should 
 */
/**
 * @author jesper
 */
public class CharacterEncoding {
	public static void main(String[] args) {
		for(int i = -200; i<150; i++){
			System.out.println("Intval:" + i + "  char val:" + (char)i);
		}
		System.out.print("før");
		System.out.print((char)-100);
		System.out.print("efter");
	}
}
