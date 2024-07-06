// key      value
TreeMap<String,TreeSet<String>> member2pacs = new TreeMap<String,TreeSet<String>>();

while ( file.ready() )
{
BufferedReader file =
new BufferedREader( new FileReader( memberpacs.txt ) )
String[] tokens = file.readLine().split("\\s+");
TreeSet<String> pacs = new TreeSet<String>( ref to a list );
for ( int i=1 ; i<tokens.length ; ++i )
pacs.add( tokens[i] );
member2pacs.put( tokens[0], pacs );

// MAP IS FILLED NOW TRAVERSE & PRINT

for ( String member : member2pacs.keySet() )
{
System.out.print( member );
TreeSet<String> pacs = member2pacs.get( member );
for ( String pac : pacs )
System.out.print( " " + pac );
System.out.println();
}

]

TreeSet<String> pacs = new TreeSet<String>( );
 for(int i = 1; i< tokens.length; i++)
 {
   System.out.println(tokens[i]);
   if(pacs.containsKey(tokens[i]))
   {
     pacs.get(tokens[i]);
   }
 }

bgates BFPR AFCTC
jsmith ASPCA YCL ACLU
bjones ASPCA NRA
gjetson BFPR AFCTC ACLU SDS
afauxci  PETA ASPCA
kjohnson SAVE_THE_WHALES MADD
jrogers NRA MADD GREENPEACE
jsandburg YCL AFCTC ACLU
