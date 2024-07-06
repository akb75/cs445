key      value
TreeMap<String,TreeSet<String>> pac2members =
new TreeMap<String,TreeSet<String>>();

while ( file.ready() )
{
BufferedReader file =
new BufferedReader( new FileReader( memberpacs.txt ) )
String[] tokens = file.readLine().split("\\s+");
** gjetson BFPR AFCTC ACLU SDS
String member = tokens[0];

** ASSUME THIS LINE IS ->  "jsandburg YCL AFCTC ACLU"

walk the tokens list 1 to end DONT CHECK ON TOKENS[0] thats a member
{
with each pac see if it is already a key in the map
use .containsKey( ACLU ) whatever to see if its in the map yet?

if its not there .put( ACLU, a TreeSset contaning jsandburg )

 type2pres.put( bloodType, new TreeSet<String>(tokens) );

if it IS there then use .get( ACLU ) to pull out the tresset of
current members of ACLU. Then .add( jsandburg )

then .put( ACLU, the treeset you just added jsanburg to )
}

TreeMap<String,TreeSet<String>> member2pacs =
new TreeMap<String,TreeSet<String>>();
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


map:
BFPR {gjetson}
AFCTC { gjetson,jsandburg }
ACLU  { gjetson, jsandburg }
SDS   { gjetson }
YCL   { jsandbug }






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


bgates BFPR AFCTC
jsmith ASPCA YCL ACLU
bjones ASPCA NRA
gjetson BFPR AFCTC ACLU SDS
afauxci  PETA ASPCA
kjohnson SAVE_THE_WHALES MADD
jrogers NRA MADD GREENPEACE
jsandburg YCL AFCTC ACLU
