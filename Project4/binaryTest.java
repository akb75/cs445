
public class binaryTest
{

public static void main(String [] args)
{

String myBit = "";
 int n = 250;
//base case return empty string if n = 0
if(n==0){
  myBit= "";}
  char[] bits;
  bits = new char[(int)((Math.log(n))/Math.log(2))+1];			//length of bits is log2n and use int to truncate down from double

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

  }while(n>=1);

      for(int i = 0;i<bits.length; i++)
      {
        myBit += bits[i] + "";
      }


      System.out.println(myBit);

//create a new array and copy that and start from
// bits.length up to 16

//we want to fill from the length of the bits up to
//
      // right now our bits.length is the length of
      //the binary numbers

      //we want to fill out the rest of the space with 0s

char[] bitsFilled = new char[16];

      //pad the rest of the space with 0s
      for(int j = bits.length; j<16; j++)
      {
        // want to start from the location of the last
        // i in bits and end at 16
        myBit += bits[j] + "";
      }



      //now that you have filled myBit, you have to
      // pad with 0s
      int zeroes = 16 - bits.length;
    //string that is zeroes long
    String zeroFill = "";
      for(int i = 0; i <zeroes; i++)
      {
        zeroFill = "0";
      }





}
}
