import java.io.*;
import java.util.*;
import java.util.Scanner;

//I keep a lot of comments in my programs so that I can go back and remember why I did what
public class Knapsack
{
  public static void main(String[] args) throws Exception
  {

    if(args.length <1)
        die(" FATAL ERROR: must enter one file name on command line. \n");

    //initialize a scanner
    Scanner scan = new Scanner(new File (args[0]));

    //initialize an integer array of length 16
    int[] numbers = new int[16];
    int target = 0;

    //use the scanner to read each of the 16 numbers into the array
          for(int i =0; i< numbers.length; i++)
          {
            //add 16 numbers into array
            numbers[i] = (scan.nextInt());
          }

      //at next line in the file
      //read in the target
      target = scan.nextInt();

    // some subset of these numbers add up to target value
    //close the scanner

    scan.close();

    //int count = 0;


    for(int i = 0; i< 65536; i++)
    {
      //System.out.println("A");
      //initialize a sum to 0
      int sum = 0;
      //initialize an empty integer string
      String intStr = "";
      // initialize a bitMap by calling toBitString using i and
      // the bitwidth of 16
      int bitwidth = 16;
      String bitMap = toBitString(i, bitwidth);
      //index in the bitmap is j
      // i is looping for the bitMap.length()
      //bitMap length should be 16
        for(int j = 0; j<bitMap.length(); j++)
        {
          // loop over bitmap if current char in map is a 1
         // add corresponding int array number to sum and intString
          if(bitMap.charAt(j)=='1')
          {
              intStr += numbers[j] + " ";
                sum = sum + numbers[j];
                //add the number in integer array (numbers)
                //that corresponds to the location in the bitmap
                // to the sum and intString
          }

        }
        if(sum == target)
        {
          //count++;
          System.out.println(intStr);
        }

      }

      //System.out.println("Total count: " + count);

  }


//convert a decimal number to its binary representation
private static String toBitString(int n, int bitwidth)
{
  // i = n the number to convert and bitwidth = 16
  /*generate a binary representation same as in project 1
  //pad 0s to the left side up to the bitwidth
  //number and bitwidth
  //convert a decimal number into its binary representation
  //project 1
  // after converting you just have to pad 0s to the left side up to the bitwidth
  //takes an int and returns a string of characters that represents the binary value of the number  */
  String myBit = "";

  //base case return empty string if n = 0
  if(n==0){
    return "";}
    char[] bits = new char[16];
    //bits = new char[(int)((Math.log(n))/Math.log(2))+1];			//length of bits is log2n and use int to truncate down from double

    //fill array of chars with zeros
    for(int i = 0; i<bits.length; i++)
    {
      bits[i] = '0';													//i is less than or equal to numCards-1
    }
      int p;																		//makes p into 0,1,2,3,4,5,6,7,8

    //ex: if p = 8 then 2^8 = 256, put a 1 in the index 0 spot and subtract 273-256
    //and continue with the loop}
    //do while loop versus while loop					p	8 7 6 5 4 3 2 1 0
                          //bit position	  0 1 2 3 4 5 6 7 8
    do
    {
       p = (int)(Math.log(n)/Math.log(2));
       bits[bits.length- p-1]= '1';
      n = n-(int)(Math.pow(2,p));
      //System.out.println("do while");

    }while(n>=1);

        for(int i = 0;i<bits.length; i++)
        {
          //System.out.println("adding bits");
          myBit += bits[i] + "";
        }

        //now that you have filled myBit, you have to
        // pad with 0s
        int zeroes = 16 - bits.length;
      //string that is zeroes long
      //String zeroFill = "";
     char[] zeroOne = new char[zeroes];
        for(int i = 0; i <zeroOne.length; i++)
        {
          zeroOne[i] = '0';
        }

        String zeroFill = new String(zeroOne);
        //System.out.println("concatination");
        // int zeroes = 16- myBit.length
        // generate a string that is zeroes long
        // return concatination of string zeroes and myBits
        // adding the padding to the myBit

  return myBit.concat(zeroFill);

}

static void die( String errMsg)
{
  System.out.println(errMsg);
  System.exit(0);
}

}
