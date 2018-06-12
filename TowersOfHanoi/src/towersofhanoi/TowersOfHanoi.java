/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package towersofhanoi;

import static towersofhanoi.TowersOfHanoi.getTheNameOfTheOtherPeg;
import static towersofhanoi.TowersOfHanoi.printMoves;

/**
 *
 * @author Jason
 */
public class TowersOfHanoi {

    //Given the names of 2 pegs, this function returns the name of the third one.
    public static String getTheNameOfTheOtherPeg(String peg1, String peg2) {
        
        if( peg1.equals("A") && peg2.equals("B")  || peg1.equals("B") && peg2.equals("A")) {
            return "C";
        }
        
        else if( peg1.equals("B") && peg2.equals("C")  || peg1.equals("C") && peg2.equals("B")) {
            return "A";
        }
        
        else
            return "B";

    }

    //Prints the list of moves needed to solve the Towers of Hanoi problem given 
    //the number of disks in the initial stack and the names of the starting and target pegs, which can be either
    //"A", "B", or "C"
    public static void printMoves(int n, String startingPeg, String targetPeg) {
        
        //Base case
        if ( n==1 ) {
            System.out.println("Move " + startingPeg + " --> " + targetPeg);
        }
        
        //Recursive case
        else{
            String freePeg = getTheNameOfTheOtherPeg( startingPeg, targetPeg);
            
            printMoves(n-1, startingPeg, freePeg);
            System.out.println( "Move " + startingPeg + " --> " + targetPeg );
            printMoves( n-1, freePeg, targetPeg );
        }
    }

    
    public static void main(String[] args) {
        
        printMoves( 3, "A", "C" );
        
    }
}
