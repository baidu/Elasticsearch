// $ANTLR 3.5.2 D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g 2017-08-09 10:55:55

    package io.crate.sql.parser;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class StatementLexer extends Lexer {
	public static final int EOF=-1;
	public static final int T__328=328;
	public static final int T__329=329;
	public static final int T__330=330;
	public static final int T__331=331;
	public static final int T__332=332;
	public static final int T__333=333;
	public static final int T__334=334;
	public static final int T__335=335;
	public static final int T__336=336;
	public static final int T__337=337;
	public static final int T__338=338;
	public static final int T__339=339;
	public static final int T__340=340;
	public static final int T__341=341;
	public static final int T__342=342;
	public static final int T__343=343;
	public static final int T__344=344;
	public static final int T__345=345;
	public static final int ADD=4;
	public static final int ADD_COLUMN=5;
	public static final int ADD_COLUMN_DEF=6;
	public static final int ADD_NODES=7;
	public static final int ALIAS=8;
	public static final int ALIASED_COLUMNS=9;
	public static final int ALIASED_RELATION=10;
	public static final int ALL=11;
	public static final int ALL_COLUMNS=12;
	public static final int ALTER=13;
	public static final int ALTER_BLOB_TABLE=14;
	public static final int ALTER_CLUSTER=15;
	public static final int ALTER_CLUSTER_ADD_NODES=16;
	public static final int ALTER_CLUSTER_DECOMMISSION_NODES=17;
	public static final int ALTER_CLUSTER_DROP_NODES=18;
	public static final int ALTER_TABLE=19;
	public static final int ALTER_USER=20;
	public static final int ALWAYS=21;
	public static final int ANALYZER=22;
	public static final int ANALYZER_ELEMENTS=23;
	public static final int AND=24;
	public static final int ANY=25;
	public static final int ARRAY=26;
	public static final int ARRAY_CMP=27;
	public static final int ARRAY_LIKE=28;
	public static final int ARRAY_LITERAL=29;
	public static final int ARRAY_NOT_LIKE=30;
	public static final int AS=31;
	public static final int ASC=32;
	public static final int ASSIGNMENT=33;
	public static final int ASSIGNMENT_LIST=34;
	public static final int BACKQUOTED_IDENT=35;
	public static final int BERNOULLI=36;
	public static final int BETWEEN=37;
	public static final int BLOB=38;
	public static final int BOOLEAN=39;
	public static final int BY=40;
	public static final int BYTE=41;
	public static final int CASE=42;
	public static final int CAST=43;
	public static final int CATALOGS=44;
	public static final int CHAR_FILTERS=45;
	public static final int CLUSTER=46;
	public static final int CLUSTERED=47;
	public static final int CLUSTERS=48;
	public static final int COALESCE=49;
	public static final int COLON_IDENT=50;
	public static final int COLUMN=51;
	public static final int COLUMNS=52;
	public static final int COLUMN_DEF=53;
	public static final int COLUMN_LIST=54;
	public static final int COMMENT=55;
	public static final int COMPARE=56;
	public static final int CONSTRAINT=57;
	public static final int COPY=58;
	public static final int COPY_FROM=59;
	public static final int COPY_TO=60;
	public static final int CREATE=61;
	public static final int CREATE_ALIAS=62;
	public static final int CREATE_BLOB_TABLE=63;
	public static final int CREATE_CLUSTER=64;
	public static final int CREATE_MATERIALIZED_VIEW=65;
	public static final int CREATE_REPOSITORY=66;
	public static final int CREATE_SNAPSHOT=67;
	public static final int CREATE_TABLE=68;
	public static final int CREATE_USER=69;
	public static final int CROSS=70;
	public static final int CROSS_JOIN=71;
	public static final int CURRENT=72;
	public static final int CURRENT_DATE=73;
	public static final int CURRENT_ROW=74;
	public static final int CURRENT_TIME=75;
	public static final int CURRENT_TIMESTAMP=76;
	public static final int DATABASES=77;
	public static final int DATE=78;
	public static final int DAY=79;
	public static final int DECIMAL_VALUE=80;
	public static final int DECOMMISSION=81;
	public static final int DELETE=82;
	public static final int DESC=83;
	public static final int DESCRIBE=84;
	public static final int DIGIT=85;
	public static final int DIGIT_IDENT=86;
	public static final int DIRECTORY=87;
	public static final int DISTINCT=88;
	public static final int DISTRIBUTED=89;
	public static final int DOUBLE=90;
	public static final int DROP=91;
	public static final int DROP_ALIAS=92;
	public static final int DROP_BLOB_TABLE=93;
	public static final int DROP_CLUSTER=94;
	public static final int DROP_REPOSITORY=95;
	public static final int DROP_SNAPSHOT=96;
	public static final int DROP_TABLE=97;
	public static final int DROP_USER=98;
	public static final int DUPLICATE=99;
	public static final int DYNAMIC=100;
	public static final int ELSE=101;
	public static final int END=102;
	public static final int EQ=103;
	public static final int ESCAPE=104;
	public static final int EXCEPT=105;
	public static final int EXISTS=106;
	public static final int EXPLAIN=107;
	public static final int EXPLAIN_FORMAT=108;
	public static final int EXPLAIN_OPTIONS=109;
	public static final int EXPLAIN_TYPE=110;
	public static final int EXPONENT=111;
	public static final int EXTENDS=112;
	public static final int EXTRACT=113;
	public static final int FALSE=114;
	public static final int FIRST=115;
	public static final int FLOAT=116;
	public static final int FOLLOWING=117;
	public static final int FOR=118;
	public static final int FORMAT=119;
	public static final int FROM=120;
	public static final int FULL=121;
	public static final int FULLTEXT=122;
	public static final int FULL_JOIN=123;
	public static final int FUNCTIONS=124;
	public static final int FUNCTION_CALL=125;
	public static final int GENERATED=126;
	public static final int GENERATED_COLUMN_DEF=127;
	public static final int GENERIC_PROPERTIES=128;
	public static final int GENERIC_PROPERTY=129;
	public static final int GEO_POINT=130;
	public static final int GEO_SHAPE=131;
	public static final int GLOBAL=132;
	public static final int GRANT=133;
	public static final int GRANTS=134;
	public static final int GRANT_PRIVILEGE=135;
	public static final int GRAPHVIZ=136;
	public static final int GROUP=137;
	public static final int GROUP_BY=138;
	public static final int GT=139;
	public static final int GTE=140;
	public static final int HAVING=141;
	public static final int HOUR=142;
	public static final int IDENT=143;
	public static final int IDENTIFIED=144;
	public static final int IDENTIFIED_BY=145;
	public static final int IDENT_EXPR=146;
	public static final int IDENT_LIST=147;
	public static final int IF=148;
	public static final int IGNORED=149;
	public static final int IN=150;
	public static final int INDEX=151;
	public static final int INDEX_COLUMNS=152;
	public static final int INDEX_OFF=153;
	public static final int INNER=154;
	public static final int INNER_JOIN=155;
	public static final int INSERT=156;
	public static final int INSERT_VALUES=157;
	public static final int INT=158;
	public static final int INTEGER=159;
	public static final int INTEGER_VALUE=160;
	public static final int INTERSECT=161;
	public static final int INTO=162;
	public static final int IN_LIST=163;
	public static final int IP=164;
	public static final int IS=165;
	public static final int IS_DISTINCT_FROM=166;
	public static final int IS_NOT_NULL=167;
	public static final int IS_NULL=168;
	public static final int JOIN=169;
	public static final int JOINED_TABLE=170;
	public static final int KEY=171;
	public static final int KEY_VALUE=172;
	public static final int KILL=173;
	public static final int LAST=174;
	public static final int LEFT=175;
	public static final int LEFT_JOIN=176;
	public static final int LETTER=177;
	public static final int LEXER_ERROR=178;
	public static final int LIKE=179;
	public static final int LIMIT=180;
	public static final int LITERAL_LIST=181;
	public static final int LOGICAL=182;
	public static final int LONG=183;
	public static final int LT=184;
	public static final int LTE=185;
	public static final int MATCH=186;
	public static final int MATCH_PREDICATE_IDENT=187;
	public static final int MATCH_PREDICATE_IDENT_LIST=188;
	public static final int MATERIALIZED=189;
	public static final int MIGRATE=190;
	public static final int MIGRATE_TABLE=191;
	public static final int MINUTE=192;
	public static final int MONTH=193;
	public static final int NAMED_PROPERTIES=194;
	public static final int NATURAL=195;
	public static final int NEGATIVE=196;
	public static final int NEQ=197;
	public static final int NODES=198;
	public static final int NOT=199;
	public static final int NOT_NULL=200;
	public static final int NULL=201;
	public static final int NULLIF=202;
	public static final int NULLS=203;
	public static final int OBJECT=204;
	public static final int OBJECT_COLUMNS=205;
	public static final int OBJECT_LITERAL=206;
	public static final int OFF=207;
	public static final int OFFSET=208;
	public static final int ON=209;
	public static final int ONLY=210;
	public static final int ON_DUP_KEY=211;
	public static final int OR=212;
	public static final int ORDER=213;
	public static final int ORDER_BY=214;
	public static final int OUTER=215;
	public static final int OVER=216;
	public static final int PARTITION=217;
	public static final int PARTITIONED=218;
	public static final int PARTITIONS=219;
	public static final int PARTITION_BY=220;
	public static final int PERSISTENT=221;
	public static final int PLAIN=222;
	public static final int PRECEDING=223;
	public static final int PRIMARY_KEY=224;
	public static final int QNAME=225;
	public static final int QUALIFIED_JOIN=226;
	public static final int QUERY=227;
	public static final int QUERY_SPEC=228;
	public static final int QUOTED_IDENT=229;
	public static final int RANGE=230;
	public static final int READ_ONLY=231;
	public static final int READ_WRITE=232;
	public static final int RECURSIVE=233;
	public static final int REFRESH=234;
	public static final int REFRESH_MATERIALIZED_VIEW=235;
	public static final int REGEX_MATCH=236;
	public static final int REGEX_MATCH_CI=237;
	public static final int REGEX_NO_MATCH=238;
	public static final int REGEX_NO_MATCH_CI=239;
	public static final int REMOVE=240;
	public static final int REMOVE_NODES=241;
	public static final int REPOSITORY=242;
	public static final int RESET=243;
	public static final int RESET_PASSWORD=244;
	public static final int RESET_WHITELIST=245;
	public static final int RESTORE=246;
	public static final int RESTORE_SNAPSHOT=247;
	public static final int REVOKE=248;
	public static final int REVOKE_PRIVILEGE=249;
	public static final int RIGHT=250;
	public static final int RIGHT_JOIN=251;
	public static final int ROW=252;
	public static final int ROWS=253;
	public static final int SAMPLED_RELATION=254;
	public static final int SCHEMAS=255;
	public static final int SEARCHED_CASE=256;
	public static final int SECOND=257;
	public static final int SELECT=258;
	public static final int SELECT_ITEM=259;
	public static final int SELECT_LIST=260;
	public static final int SET=261;
	public static final int SHARDS=262;
	public static final int SHORT=263;
	public static final int SHOW=264;
	public static final int SHOW_CATALOGS=265;
	public static final int SHOW_CLUSTERS=266;
	public static final int SHOW_COLUMNS=267;
	public static final int SHOW_CREATE_TABLE=268;
	public static final int SHOW_DATABASES=269;
	public static final int SHOW_FUNCTIONS=270;
	public static final int SHOW_GRANTS=271;
	public static final int SHOW_PARTITIONS=272;
	public static final int SHOW_SCHEMAS=273;
	public static final int SHOW_TABLES=274;
	public static final int SHOW_USERS=275;
	public static final int SIMPLE_CASE=276;
	public static final int SNAPSHOT=277;
	public static final int SOME=278;
	public static final int SORT_ITEM=279;
	public static final int STATEMENT_LIST=280;
	public static final int STRATIFY=281;
	public static final int STRATIFY_ON=282;
	public static final int STRICT=283;
	public static final int STRING=284;
	public static final int STRING_TYPE=285;
	public static final int SUBSTRING=286;
	public static final int SYSTEM=287;
	public static final int TABLE=288;
	public static final int TABLES=289;
	public static final int TABLESAMPLE=290;
	public static final int TABLE_ELEMENT_LIST=291;
	public static final int TABLE_FUNCTION=292;
	public static final int TABLE_PARTITION_LIST=293;
	public static final int TABLE_SUBQUERY=294;
	public static final int TERMINATOR=295;
	public static final int TEXT=296;
	public static final int THEN=297;
	public static final int TIME=298;
	public static final int TIMESTAMP=299;
	public static final int TO=300;
	public static final int TOKENIZER=301;
	public static final int TOKEN_FILTERS=302;
	public static final int TRANSIENT=303;
	public static final int TRUE=304;
	public static final int TRY_CAST=305;
	public static final int TYPE=306;
	public static final int UNBOUNDED=307;
	public static final int UNBOUNDED_FOLLOWING=308;
	public static final int UNBOUNDED_PRECEDING=309;
	public static final int UNION=310;
	public static final int UPDATE=311;
	public static final int USER=312;
	public static final int USERS=313;
	public static final int USING=314;
	public static final int VALUES=315;
	public static final int VALUES_LIST=316;
	public static final int VIEW=317;
	public static final int VIEW_REFRESH=318;
	public static final int WHEN=319;
	public static final int WHERE=320;
	public static final int WHITELIST=321;
	public static final int WINDOW=322;
	public static final int WITH=323;
	public static final int WITH_LIST=324;
	public static final int WITH_QUERY=325;
	public static final int WS=326;
	public static final int YEAR=327;

	    @Override
	    public void reportError(RecognitionException e)
	    {
	        throw new ParsingException(getErrorMessage(e, getTokenNames()), e);
	    }


	// delegates
	// delegators
	public Lexer[] getDelegates() {
		return new Lexer[] {};
	}

	public StatementLexer() {} 
	public StatementLexer(CharStream input) {
		this(input, new RecognizerSharedState());
	}
	public StatementLexer(CharStream input, RecognizerSharedState state) {
		super(input,state);
	}
	@Override public String getGrammarFileName() { return "D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g"; }

	// $ANTLR start "T__328"
	public final void mT__328() throws RecognitionException {
		try {
			int _type = T__328;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:13:8: ( '$' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:13:10: '$'
			{
			match('$'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__328"

	// $ANTLR start "T__329"
	public final void mT__329() throws RecognitionException {
		try {
			int _type = T__329;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:14:8: ( '%' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:14:10: '%'
			{
			match('%'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__329"

	// $ANTLR start "T__330"
	public final void mT__330() throws RecognitionException {
		try {
			int _type = T__330;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:15:8: ( '(' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:15:10: '('
			{
			match('('); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__330"

	// $ANTLR start "T__331"
	public final void mT__331() throws RecognitionException {
		try {
			int _type = T__331;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:16:8: ( ')' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:16:10: ')'
			{
			match(')'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__331"

	// $ANTLR start "T__332"
	public final void mT__332() throws RecognitionException {
		try {
			int _type = T__332;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:17:8: ( '*' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:17:10: '*'
			{
			match('*'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__332"

	// $ANTLR start "T__333"
	public final void mT__333() throws RecognitionException {
		try {
			int _type = T__333;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:18:8: ( '+' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:18:10: '+'
			{
			match('+'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__333"

	// $ANTLR start "T__334"
	public final void mT__334() throws RecognitionException {
		try {
			int _type = T__334;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:19:8: ( ',' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:19:10: ','
			{
			match(','); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__334"

	// $ANTLR start "T__335"
	public final void mT__335() throws RecognitionException {
		try {
			int _type = T__335;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:20:8: ( '-' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:20:10: '-'
			{
			match('-'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__335"

	// $ANTLR start "T__336"
	public final void mT__336() throws RecognitionException {
		try {
			int _type = T__336;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:21:8: ( '.' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:21:10: '.'
			{
			match('.'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__336"

	// $ANTLR start "T__337"
	public final void mT__337() throws RecognitionException {
		try {
			int _type = T__337;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:22:8: ( '/' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:22:10: '/'
			{
			match('/'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__337"

	// $ANTLR start "T__338"
	public final void mT__338() throws RecognitionException {
		try {
			int _type = T__338;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:23:8: ( '?' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:23:10: '?'
			{
			match('?'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__338"

	// $ANTLR start "T__339"
	public final void mT__339() throws RecognitionException {
		try {
			int _type = T__339;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:24:8: ( 'READ_ONLY' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:24:10: 'READ_ONLY'
			{
			match("READ_ONLY"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__339"

	// $ANTLR start "T__340"
	public final void mT__340() throws RecognitionException {
		try {
			int _type = T__340;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:25:8: ( 'READ_WRITE' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:25:10: 'READ_WRITE'
			{
			match("READ_WRITE"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__340"

	// $ANTLR start "T__341"
	public final void mT__341() throws RecognitionException {
		try {
			int _type = T__341;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:26:8: ( '[' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:26:10: '['
			{
			match('['); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__341"

	// $ANTLR start "T__342"
	public final void mT__342() throws RecognitionException {
		try {
			int _type = T__342;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:27:8: ( ']' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:27:10: ']'
			{
			match(']'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__342"

	// $ANTLR start "T__343"
	public final void mT__343() throws RecognitionException {
		try {
			int _type = T__343;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:28:8: ( '{' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:28:10: '{'
			{
			match('{'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__343"

	// $ANTLR start "T__344"
	public final void mT__344() throws RecognitionException {
		try {
			int _type = T__344;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:29:8: ( '||' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:29:10: '||'
			{
			match("||"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__344"

	// $ANTLR start "T__345"
	public final void mT__345() throws RecognitionException {
		try {
			int _type = T__345;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:30:8: ( '}' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:30:10: '}'
			{
			match('}'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__345"

	// $ANTLR start "SELECT"
	public final void mSELECT() throws RecognitionException {
		try {
			int _type = SELECT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1221:7: ( 'SELECT' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1221:9: 'SELECT'
			{
			match("SELECT"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "SELECT"

	// $ANTLR start "FROM"
	public final void mFROM() throws RecognitionException {
		try {
			int _type = FROM;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1222:5: ( 'FROM' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1222:7: 'FROM'
			{
			match("FROM"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "FROM"

	// $ANTLR start "TO"
	public final void mTO() throws RecognitionException {
		try {
			int _type = TO;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1223:3: ( 'TO' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1223:5: 'TO'
			{
			match("TO"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "TO"

	// $ANTLR start "AS"
	public final void mAS() throws RecognitionException {
		try {
			int _type = AS;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1224:3: ( 'AS' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1224:5: 'AS'
			{
			match("AS"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "AS"

	// $ANTLR start "ALL"
	public final void mALL() throws RecognitionException {
		try {
			int _type = ALL;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1225:4: ( 'ALL' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1225:6: 'ALL'
			{
			match("ALL"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "ALL"

	// $ANTLR start "ANY"
	public final void mANY() throws RecognitionException {
		try {
			int _type = ANY;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1226:4: ( 'ANY' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1226:6: 'ANY'
			{
			match("ANY"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "ANY"

	// $ANTLR start "SOME"
	public final void mSOME() throws RecognitionException {
		try {
			int _type = SOME;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1227:5: ( 'SOME' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1227:7: 'SOME'
			{
			match("SOME"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "SOME"

	// $ANTLR start "DIRECTORY"
	public final void mDIRECTORY() throws RecognitionException {
		try {
			int _type = DIRECTORY;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1228:10: ( 'DIRECTORY' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1228:12: 'DIRECTORY'
			{
			match("DIRECTORY"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "DIRECTORY"

	// $ANTLR start "DISTINCT"
	public final void mDISTINCT() throws RecognitionException {
		try {
			int _type = DISTINCT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1229:9: ( 'DISTINCT' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1229:11: 'DISTINCT'
			{
			match("DISTINCT"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "DISTINCT"

	// $ANTLR start "WHERE"
	public final void mWHERE() throws RecognitionException {
		try {
			int _type = WHERE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1230:6: ( 'WHERE' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1230:8: 'WHERE'
			{
			match("WHERE"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "WHERE"

	// $ANTLR start "GROUP"
	public final void mGROUP() throws RecognitionException {
		try {
			int _type = GROUP;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1231:6: ( 'GROUP' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1231:8: 'GROUP'
			{
			match("GROUP"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "GROUP"

	// $ANTLR start "BY"
	public final void mBY() throws RecognitionException {
		try {
			int _type = BY;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1232:3: ( 'BY' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1232:5: 'BY'
			{
			match("BY"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "BY"

	// $ANTLR start "ORDER"
	public final void mORDER() throws RecognitionException {
		try {
			int _type = ORDER;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1233:6: ( 'ORDER' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1233:8: 'ORDER'
			{
			match("ORDER"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "ORDER"

	// $ANTLR start "HAVING"
	public final void mHAVING() throws RecognitionException {
		try {
			int _type = HAVING;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1234:7: ( 'HAVING' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1234:9: 'HAVING'
			{
			match("HAVING"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "HAVING"

	// $ANTLR start "LIMIT"
	public final void mLIMIT() throws RecognitionException {
		try {
			int _type = LIMIT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1235:6: ( 'LIMIT' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1235:8: 'LIMIT'
			{
			match("LIMIT"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "LIMIT"

	// $ANTLR start "OFFSET"
	public final void mOFFSET() throws RecognitionException {
		try {
			int _type = OFFSET;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1236:7: ( 'OFFSET' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1236:9: 'OFFSET'
			{
			match("OFFSET"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "OFFSET"

	// $ANTLR start "OR"
	public final void mOR() throws RecognitionException {
		try {
			int _type = OR;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1237:3: ( 'OR' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1237:5: 'OR'
			{
			match("OR"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "OR"

	// $ANTLR start "AND"
	public final void mAND() throws RecognitionException {
		try {
			int _type = AND;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1238:4: ( 'AND' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1238:6: 'AND'
			{
			match("AND"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "AND"

	// $ANTLR start "IN"
	public final void mIN() throws RecognitionException {
		try {
			int _type = IN;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1239:3: ( 'IN' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1239:5: 'IN'
			{
			match("IN"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "IN"

	// $ANTLR start "NOT"
	public final void mNOT() throws RecognitionException {
		try {
			int _type = NOT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1240:4: ( 'NOT' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1240:6: 'NOT'
			{
			match("NOT"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "NOT"

	// $ANTLR start "EXISTS"
	public final void mEXISTS() throws RecognitionException {
		try {
			int _type = EXISTS;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1241:7: ( 'EXISTS' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1241:9: 'EXISTS'
			{
			match("EXISTS"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "EXISTS"

	// $ANTLR start "BETWEEN"
	public final void mBETWEEN() throws RecognitionException {
		try {
			int _type = BETWEEN;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1242:8: ( 'BETWEEN' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1242:10: 'BETWEEN'
			{
			match("BETWEEN"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "BETWEEN"

	// $ANTLR start "LIKE"
	public final void mLIKE() throws RecognitionException {
		try {
			int _type = LIKE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1243:5: ( 'LIKE' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1243:7: 'LIKE'
			{
			match("LIKE"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "LIKE"

	// $ANTLR start "IS"
	public final void mIS() throws RecognitionException {
		try {
			int _type = IS;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1244:3: ( 'IS' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1244:5: 'IS'
			{
			match("IS"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "IS"

	// $ANTLR start "NULL"
	public final void mNULL() throws RecognitionException {
		try {
			int _type = NULL;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1245:5: ( 'NULL' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1245:7: 'NULL'
			{
			match("NULL"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "NULL"

	// $ANTLR start "TRUE"
	public final void mTRUE() throws RecognitionException {
		try {
			int _type = TRUE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1246:5: ( 'TRUE' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1246:7: 'TRUE'
			{
			match("TRUE"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "TRUE"

	// $ANTLR start "FALSE"
	public final void mFALSE() throws RecognitionException {
		try {
			int _type = FALSE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1247:6: ( 'FALSE' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1247:8: 'FALSE'
			{
			match("FALSE"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "FALSE"

	// $ANTLR start "NULLS"
	public final void mNULLS() throws RecognitionException {
		try {
			int _type = NULLS;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1248:6: ( 'NULLS' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1248:8: 'NULLS'
			{
			match("NULLS"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "NULLS"

	// $ANTLR start "FIRST"
	public final void mFIRST() throws RecognitionException {
		try {
			int _type = FIRST;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1249:6: ( 'FIRST' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1249:8: 'FIRST'
			{
			match("FIRST"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "FIRST"

	// $ANTLR start "LAST"
	public final void mLAST() throws RecognitionException {
		try {
			int _type = LAST;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1250:5: ( 'LAST' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1250:7: 'LAST'
			{
			match("LAST"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "LAST"

	// $ANTLR start "ESCAPE"
	public final void mESCAPE() throws RecognitionException {
		try {
			int _type = ESCAPE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1251:7: ( 'ESCAPE' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1251:9: 'ESCAPE'
			{
			match("ESCAPE"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "ESCAPE"

	// $ANTLR start "ASC"
	public final void mASC() throws RecognitionException {
		try {
			int _type = ASC;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1252:4: ( 'ASC' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1252:6: 'ASC'
			{
			match("ASC"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "ASC"

	// $ANTLR start "DESC"
	public final void mDESC() throws RecognitionException {
		try {
			int _type = DESC;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1253:5: ( 'DESC' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1253:7: 'DESC'
			{
			match("DESC"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "DESC"

	// $ANTLR start "SUBSTRING"
	public final void mSUBSTRING() throws RecognitionException {
		try {
			int _type = SUBSTRING;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1254:10: ( 'SUBSTRING' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1254:12: 'SUBSTRING'
			{
			match("SUBSTRING"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "SUBSTRING"

	// $ANTLR start "FOR"
	public final void mFOR() throws RecognitionException {
		try {
			int _type = FOR;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1255:4: ( 'FOR' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1255:6: 'FOR'
			{
			match("FOR"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "FOR"

	// $ANTLR start "DATE"
	public final void mDATE() throws RecognitionException {
		try {
			int _type = DATE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1256:5: ( 'DATE' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1256:7: 'DATE'
			{
			match("DATE"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "DATE"

	// $ANTLR start "TIME"
	public final void mTIME() throws RecognitionException {
		try {
			int _type = TIME;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1257:5: ( 'TIME' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1257:7: 'TIME'
			{
			match("TIME"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "TIME"

	// $ANTLR start "YEAR"
	public final void mYEAR() throws RecognitionException {
		try {
			int _type = YEAR;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1258:5: ( 'YEAR' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1258:7: 'YEAR'
			{
			match("YEAR"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "YEAR"

	// $ANTLR start "MONTH"
	public final void mMONTH() throws RecognitionException {
		try {
			int _type = MONTH;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1259:6: ( 'MONTH' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1259:8: 'MONTH'
			{
			match("MONTH"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "MONTH"

	// $ANTLR start "DAY"
	public final void mDAY() throws RecognitionException {
		try {
			int _type = DAY;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1260:4: ( 'DAY' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1260:6: 'DAY'
			{
			match("DAY"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "DAY"

	// $ANTLR start "HOUR"
	public final void mHOUR() throws RecognitionException {
		try {
			int _type = HOUR;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1261:5: ( 'HOUR' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1261:7: 'HOUR'
			{
			match("HOUR"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "HOUR"

	// $ANTLR start "MINUTE"
	public final void mMINUTE() throws RecognitionException {
		try {
			int _type = MINUTE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1262:7: ( 'MINUTE' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1262:9: 'MINUTE'
			{
			match("MINUTE"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "MINUTE"

	// $ANTLR start "SECOND"
	public final void mSECOND() throws RecognitionException {
		try {
			int _type = SECOND;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1263:7: ( 'SECOND' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1263:9: 'SECOND'
			{
			match("SECOND"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "SECOND"

	// $ANTLR start "CURRENT_DATE"
	public final void mCURRENT_DATE() throws RecognitionException {
		try {
			int _type = CURRENT_DATE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1264:13: ( 'CURRENT_DATE' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1264:15: 'CURRENT_DATE'
			{
			match("CURRENT_DATE"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "CURRENT_DATE"

	// $ANTLR start "CURRENT_TIME"
	public final void mCURRENT_TIME() throws RecognitionException {
		try {
			int _type = CURRENT_TIME;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1265:13: ( 'CURRENT_TIME' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1265:15: 'CURRENT_TIME'
			{
			match("CURRENT_TIME"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "CURRENT_TIME"

	// $ANTLR start "CURRENT_TIMESTAMP"
	public final void mCURRENT_TIMESTAMP() throws RecognitionException {
		try {
			int _type = CURRENT_TIMESTAMP;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1266:18: ( 'CURRENT_TIMESTAMP' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1266:20: 'CURRENT_TIMESTAMP'
			{
			match("CURRENT_TIMESTAMP"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "CURRENT_TIMESTAMP"

	// $ANTLR start "EXTRACT"
	public final void mEXTRACT() throws RecognitionException {
		try {
			int _type = EXTRACT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1267:8: ( 'EXTRACT' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1267:10: 'EXTRACT'
			{
			match("EXTRACT"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "EXTRACT"

	// $ANTLR start "COALESCE"
	public final void mCOALESCE() throws RecognitionException {
		try {
			int _type = COALESCE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1268:9: ( 'COALESCE' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1268:11: 'COALESCE'
			{
			match("COALESCE"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "COALESCE"

	// $ANTLR start "NULLIF"
	public final void mNULLIF() throws RecognitionException {
		try {
			int _type = NULLIF;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1269:7: ( 'NULLIF' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1269:9: 'NULLIF'
			{
			match("NULLIF"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "NULLIF"

	// $ANTLR start "CASE"
	public final void mCASE() throws RecognitionException {
		try {
			int _type = CASE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1270:5: ( 'CASE' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1270:7: 'CASE'
			{
			match("CASE"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "CASE"

	// $ANTLR start "WHEN"
	public final void mWHEN() throws RecognitionException {
		try {
			int _type = WHEN;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1271:5: ( 'WHEN' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1271:7: 'WHEN'
			{
			match("WHEN"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "WHEN"

	// $ANTLR start "THEN"
	public final void mTHEN() throws RecognitionException {
		try {
			int _type = THEN;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1272:5: ( 'THEN' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1272:7: 'THEN'
			{
			match("THEN"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "THEN"

	// $ANTLR start "ELSE"
	public final void mELSE() throws RecognitionException {
		try {
			int _type = ELSE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1273:5: ( 'ELSE' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1273:7: 'ELSE'
			{
			match("ELSE"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "ELSE"

	// $ANTLR start "END"
	public final void mEND() throws RecognitionException {
		try {
			int _type = END;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1274:4: ( 'END' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1274:6: 'END'
			{
			match("END"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "END"

	// $ANTLR start "IF"
	public final void mIF() throws RecognitionException {
		try {
			int _type = IF;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1275:3: ( 'IF' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1275:5: 'IF'
			{
			match("IF"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "IF"

	// $ANTLR start "JOIN"
	public final void mJOIN() throws RecognitionException {
		try {
			int _type = JOIN;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1276:5: ( 'JOIN' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1276:7: 'JOIN'
			{
			match("JOIN"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "JOIN"

	// $ANTLR start "CROSS"
	public final void mCROSS() throws RecognitionException {
		try {
			int _type = CROSS;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1277:6: ( 'CROSS' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1277:8: 'CROSS'
			{
			match("CROSS"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "CROSS"

	// $ANTLR start "OUTER"
	public final void mOUTER() throws RecognitionException {
		try {
			int _type = OUTER;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1278:6: ( 'OUTER' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1278:8: 'OUTER'
			{
			match("OUTER"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "OUTER"

	// $ANTLR start "INNER"
	public final void mINNER() throws RecognitionException {
		try {
			int _type = INNER;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1279:6: ( 'INNER' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1279:8: 'INNER'
			{
			match("INNER"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "INNER"

	// $ANTLR start "LEFT"
	public final void mLEFT() throws RecognitionException {
		try {
			int _type = LEFT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1280:5: ( 'LEFT' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1280:7: 'LEFT'
			{
			match("LEFT"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "LEFT"

	// $ANTLR start "RIGHT"
	public final void mRIGHT() throws RecognitionException {
		try {
			int _type = RIGHT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1281:6: ( 'RIGHT' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1281:8: 'RIGHT'
			{
			match("RIGHT"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "RIGHT"

	// $ANTLR start "FULL"
	public final void mFULL() throws RecognitionException {
		try {
			int _type = FULL;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1282:5: ( 'FULL' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1282:7: 'FULL'
			{
			match("FULL"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "FULL"

	// $ANTLR start "NATURAL"
	public final void mNATURAL() throws RecognitionException {
		try {
			int _type = NATURAL;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1283:8: ( 'NATURAL' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1283:10: 'NATURAL'
			{
			match("NATURAL"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "NATURAL"

	// $ANTLR start "USING"
	public final void mUSING() throws RecognitionException {
		try {
			int _type = USING;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1284:6: ( 'USING' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1284:8: 'USING'
			{
			match("USING"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "USING"

	// $ANTLR start "ON"
	public final void mON() throws RecognitionException {
		try {
			int _type = ON;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1285:3: ( 'ON' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1285:5: 'ON'
			{
			match("ON"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "ON"

	// $ANTLR start "OVER"
	public final void mOVER() throws RecognitionException {
		try {
			int _type = OVER;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1286:5: ( 'OVER' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1286:7: 'OVER'
			{
			match("OVER"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "OVER"

	// $ANTLR start "PARTITION"
	public final void mPARTITION() throws RecognitionException {
		try {
			int _type = PARTITION;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1287:10: ( 'PARTITION' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1287:12: 'PARTITION'
			{
			match("PARTITION"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "PARTITION"

	// $ANTLR start "RANGE"
	public final void mRANGE() throws RecognitionException {
		try {
			int _type = RANGE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1288:6: ( 'RANGE' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1288:8: 'RANGE'
			{
			match("RANGE"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "RANGE"

	// $ANTLR start "ROWS"
	public final void mROWS() throws RecognitionException {
		try {
			int _type = ROWS;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1289:5: ( 'ROWS' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1289:7: 'ROWS'
			{
			match("ROWS"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "ROWS"

	// $ANTLR start "UNBOUNDED"
	public final void mUNBOUNDED() throws RecognitionException {
		try {
			int _type = UNBOUNDED;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1290:10: ( 'UNBOUNDED' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1290:12: 'UNBOUNDED'
			{
			match("UNBOUNDED"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "UNBOUNDED"

	// $ANTLR start "PRECEDING"
	public final void mPRECEDING() throws RecognitionException {
		try {
			int _type = PRECEDING;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1291:10: ( 'PRECEDING' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1291:12: 'PRECEDING'
			{
			match("PRECEDING"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "PRECEDING"

	// $ANTLR start "FOLLOWING"
	public final void mFOLLOWING() throws RecognitionException {
		try {
			int _type = FOLLOWING;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1292:10: ( 'FOLLOWING' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1292:12: 'FOLLOWING'
			{
			match("FOLLOWING"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "FOLLOWING"

	// $ANTLR start "CURRENT"
	public final void mCURRENT() throws RecognitionException {
		try {
			int _type = CURRENT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1293:8: ( 'CURRENT' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1293:10: 'CURRENT'
			{
			match("CURRENT"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "CURRENT"

	// $ANTLR start "ROW"
	public final void mROW() throws RecognitionException {
		try {
			int _type = ROW;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1294:4: ( 'ROW' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1294:6: 'ROW'
			{
			match("ROW"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "ROW"

	// $ANTLR start "WITH"
	public final void mWITH() throws RecognitionException {
		try {
			int _type = WITH;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1295:5: ( 'WITH' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1295:7: 'WITH'
			{
			match("WITH"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "WITH"

	// $ANTLR start "RECURSIVE"
	public final void mRECURSIVE() throws RecognitionException {
		try {
			int _type = RECURSIVE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1296:10: ( 'RECURSIVE' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1296:12: 'RECURSIVE'
			{
			match("RECURSIVE"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "RECURSIVE"

	// $ANTLR start "CREATE"
	public final void mCREATE() throws RecognitionException {
		try {
			int _type = CREATE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1297:7: ( 'CREATE' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1297:9: 'CREATE'
			{
			match("CREATE"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "CREATE"

	// $ANTLR start "BLOB"
	public final void mBLOB() throws RecognitionException {
		try {
			int _type = BLOB;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1298:5: ( 'BLOB' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1298:7: 'BLOB'
			{
			match("BLOB"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "BLOB"

	// $ANTLR start "TABLE"
	public final void mTABLE() throws RecognitionException {
		try {
			int _type = TABLE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1299:6: ( 'TABLE' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1299:8: 'TABLE'
			{
			match("TABLE"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "TABLE"

	// $ANTLR start "REPOSITORY"
	public final void mREPOSITORY() throws RecognitionException {
		try {
			int _type = REPOSITORY;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1300:11: ( 'REPOSITORY' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1300:13: 'REPOSITORY'
			{
			match("REPOSITORY"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "REPOSITORY"

	// $ANTLR start "SNAPSHOT"
	public final void mSNAPSHOT() throws RecognitionException {
		try {
			int _type = SNAPSHOT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1301:9: ( 'SNAPSHOT' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1301:11: 'SNAPSHOT'
			{
			match("SNAPSHOT"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "SNAPSHOT"

	// $ANTLR start "ALTER"
	public final void mALTER() throws RecognitionException {
		try {
			int _type = ALTER;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1302:6: ( 'ALTER' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1302:8: 'ALTER'
			{
			match("ALTER"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "ALTER"

	// $ANTLR start "KILL"
	public final void mKILL() throws RecognitionException {
		try {
			int _type = KILL;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1303:5: ( 'KILL' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1303:7: 'KILL'
			{
			match("KILL"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "KILL"

	// $ANTLR start "ONLY"
	public final void mONLY() throws RecognitionException {
		try {
			int _type = ONLY;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1304:5: ( 'ONLY' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1304:7: 'ONLY'
			{
			match("ONLY"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "ONLY"

	// $ANTLR start "ADD"
	public final void mADD() throws RecognitionException {
		try {
			int _type = ADD;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1306:4: ( 'ADD' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1306:6: 'ADD'
			{
			match("ADD"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "ADD"

	// $ANTLR start "COLUMN"
	public final void mCOLUMN() throws RecognitionException {
		try {
			int _type = COLUMN;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1307:7: ( 'COLUMN' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1307:9: 'COLUMN'
			{
			match("COLUMN"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "COLUMN"

	// $ANTLR start "BOOLEAN"
	public final void mBOOLEAN() throws RecognitionException {
		try {
			int _type = BOOLEAN;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1309:8: ( 'BOOLEAN' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1309:10: 'BOOLEAN'
			{
			match("BOOLEAN"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "BOOLEAN"

	// $ANTLR start "BYTE"
	public final void mBYTE() throws RecognitionException {
		try {
			int _type = BYTE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1310:5: ( 'BYTE' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1310:7: 'BYTE'
			{
			match("BYTE"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "BYTE"

	// $ANTLR start "SHORT"
	public final void mSHORT() throws RecognitionException {
		try {
			int _type = SHORT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1311:6: ( 'SHORT' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1311:8: 'SHORT'
			{
			match("SHORT"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "SHORT"

	// $ANTLR start "INTEGER"
	public final void mINTEGER() throws RecognitionException {
		try {
			int _type = INTEGER;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1312:8: ( 'INTEGER' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1312:10: 'INTEGER'
			{
			match("INTEGER"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "INTEGER"

	// $ANTLR start "INT"
	public final void mINT() throws RecognitionException {
		try {
			int _type = INT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1313:4: ( 'INT' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1313:6: 'INT'
			{
			match("INT"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "INT"

	// $ANTLR start "LONG"
	public final void mLONG() throws RecognitionException {
		try {
			int _type = LONG;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1314:5: ( 'LONG' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1314:7: 'LONG'
			{
			match("LONG"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "LONG"

	// $ANTLR start "FLOAT"
	public final void mFLOAT() throws RecognitionException {
		try {
			int _type = FLOAT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1315:6: ( 'FLOAT' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1315:8: 'FLOAT'
			{
			match("FLOAT"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "FLOAT"

	// $ANTLR start "DOUBLE"
	public final void mDOUBLE() throws RecognitionException {
		try {
			int _type = DOUBLE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1316:7: ( 'DOUBLE' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1316:9: 'DOUBLE'
			{
			match("DOUBLE"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "DOUBLE"

	// $ANTLR start "TIMESTAMP"
	public final void mTIMESTAMP() throws RecognitionException {
		try {
			int _type = TIMESTAMP;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1317:10: ( 'TIMESTAMP' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1317:12: 'TIMESTAMP'
			{
			match("TIMESTAMP"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "TIMESTAMP"

	// $ANTLR start "IP"
	public final void mIP() throws RecognitionException {
		try {
			int _type = IP;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1318:3: ( 'IP' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1318:5: 'IP'
			{
			match("IP"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "IP"

	// $ANTLR start "OBJECT"
	public final void mOBJECT() throws RecognitionException {
		try {
			int _type = OBJECT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1319:7: ( 'OBJECT' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1319:9: 'OBJECT'
			{
			match("OBJECT"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "OBJECT"

	// $ANTLR start "STRING_TYPE"
	public final void mSTRING_TYPE() throws RecognitionException {
		try {
			int _type = STRING_TYPE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1320:12: ( 'STRING' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1320:14: 'STRING'
			{
			match("STRING"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "STRING_TYPE"

	// $ANTLR start "GEO_POINT"
	public final void mGEO_POINT() throws RecognitionException {
		try {
			int _type = GEO_POINT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1321:10: ( 'GEO_POINT' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1321:12: 'GEO_POINT'
			{
			match("GEO_POINT"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "GEO_POINT"

	// $ANTLR start "GEO_SHAPE"
	public final void mGEO_SHAPE() throws RecognitionException {
		try {
			int _type = GEO_SHAPE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1322:10: ( 'GEO_SHAPE' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1322:12: 'GEO_SHAPE'
			{
			match("GEO_SHAPE"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "GEO_SHAPE"

	// $ANTLR start "GLOBAL"
	public final void mGLOBAL() throws RecognitionException {
		try {
			int _type = GLOBAL;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1323:8: ( 'GLOBAL' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1323:10: 'GLOBAL'
			{
			match("GLOBAL"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "GLOBAL"

	// $ANTLR start "CONSTRAINT"
	public final void mCONSTRAINT() throws RecognitionException {
		try {
			int _type = CONSTRAINT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1325:11: ( 'CONSTRAINT' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1325:13: 'CONSTRAINT'
			{
			match("CONSTRAINT"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "CONSTRAINT"

	// $ANTLR start "DESCRIBE"
	public final void mDESCRIBE() throws RecognitionException {
		try {
			int _type = DESCRIBE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1326:9: ( 'DESCRIBE' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1326:11: 'DESCRIBE'
			{
			match("DESCRIBE"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "DESCRIBE"

	// $ANTLR start "EXPLAIN"
	public final void mEXPLAIN() throws RecognitionException {
		try {
			int _type = EXPLAIN;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1327:8: ( 'EXPLAIN' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1327:10: 'EXPLAIN'
			{
			match("EXPLAIN"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "EXPLAIN"

	// $ANTLR start "FORMAT"
	public final void mFORMAT() throws RecognitionException {
		try {
			int _type = FORMAT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1328:7: ( 'FORMAT' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1328:9: 'FORMAT'
			{
			match("FORMAT"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "FORMAT"

	// $ANTLR start "TYPE"
	public final void mTYPE() throws RecognitionException {
		try {
			int _type = TYPE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1329:5: ( 'TYPE' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1329:7: 'TYPE'
			{
			match("TYPE"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "TYPE"

	// $ANTLR start "TEXT"
	public final void mTEXT() throws RecognitionException {
		try {
			int _type = TEXT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1330:5: ( 'TEXT' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1330:7: 'TEXT'
			{
			match("TEXT"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "TEXT"

	// $ANTLR start "GRAPHVIZ"
	public final void mGRAPHVIZ() throws RecognitionException {
		try {
			int _type = GRAPHVIZ;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1331:9: ( 'GRAPHVIZ' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1331:11: 'GRAPHVIZ'
			{
			match("GRAPHVIZ"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "GRAPHVIZ"

	// $ANTLR start "LOGICAL"
	public final void mLOGICAL() throws RecognitionException {
		try {
			int _type = LOGICAL;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1332:8: ( 'LOGICAL' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1332:10: 'LOGICAL'
			{
			match("LOGICAL"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "LOGICAL"

	// $ANTLR start "DISTRIBUTED"
	public final void mDISTRIBUTED() throws RecognitionException {
		try {
			int _type = DISTRIBUTED;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1333:12: ( 'DISTRIBUTED' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1333:14: 'DISTRIBUTED'
			{
			match("DISTRIBUTED"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "DISTRIBUTED"

	// $ANTLR start "CAST"
	public final void mCAST() throws RecognitionException {
		try {
			int _type = CAST;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1334:5: ( 'CAST' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1334:7: 'CAST'
			{
			match("CAST"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "CAST"

	// $ANTLR start "TRY_CAST"
	public final void mTRY_CAST() throws RecognitionException {
		try {
			int _type = TRY_CAST;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1335:9: ( 'TRY_CAST' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1335:11: 'TRY_CAST'
			{
			match("TRY_CAST"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "TRY_CAST"

	// $ANTLR start "SHOW"
	public final void mSHOW() throws RecognitionException {
		try {
			int _type = SHOW;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1336:5: ( 'SHOW' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1336:7: 'SHOW'
			{
			match("SHOW"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "SHOW"

	// $ANTLR start "TABLES"
	public final void mTABLES() throws RecognitionException {
		try {
			int _type = TABLES;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1337:7: ( 'TABLES' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1337:9: 'TABLES'
			{
			match("TABLES"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "TABLES"

	// $ANTLR start "SCHEMAS"
	public final void mSCHEMAS() throws RecognitionException {
		try {
			int _type = SCHEMAS;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1338:8: ( 'SCHEMAS' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1338:10: 'SCHEMAS'
			{
			match("SCHEMAS"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "SCHEMAS"

	// $ANTLR start "DATABASES"
	public final void mDATABASES() throws RecognitionException {
		try {
			int _type = DATABASES;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1339:10: ( 'DATABASES' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1339:12: 'DATABASES'
			{
			match("DATABASES"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "DATABASES"

	// $ANTLR start "CATALOGS"
	public final void mCATALOGS() throws RecognitionException {
		try {
			int _type = CATALOGS;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1340:9: ( 'CATALOGS' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1340:11: 'CATALOGS'
			{
			match("CATALOGS"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "CATALOGS"

	// $ANTLR start "COLUMNS"
	public final void mCOLUMNS() throws RecognitionException {
		try {
			int _type = COLUMNS;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1341:8: ( 'COLUMNS' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1341:10: 'COLUMNS'
			{
			match("COLUMNS"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "COLUMNS"

	// $ANTLR start "PARTITIONS"
	public final void mPARTITIONS() throws RecognitionException {
		try {
			int _type = PARTITIONS;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1342:11: ( 'PARTITIONS' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1342:13: 'PARTITIONS'
			{
			match("PARTITIONS"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "PARTITIONS"

	// $ANTLR start "FUNCTIONS"
	public final void mFUNCTIONS() throws RecognitionException {
		try {
			int _type = FUNCTIONS;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1343:10: ( 'FUNCTIONS' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1343:12: 'FUNCTIONS'
			{
			match("FUNCTIONS"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "FUNCTIONS"

	// $ANTLR start "MATERIALIZED"
	public final void mMATERIALIZED() throws RecognitionException {
		try {
			int _type = MATERIALIZED;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1344:13: ( 'MATERIALIZED' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1344:15: 'MATERIALIZED'
			{
			match("MATERIALIZED"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "MATERIALIZED"

	// $ANTLR start "VIEW"
	public final void mVIEW() throws RecognitionException {
		try {
			int _type = VIEW;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1345:5: ( 'VIEW' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1345:7: 'VIEW'
			{
			match("VIEW"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "VIEW"

	// $ANTLR start "REFRESH"
	public final void mREFRESH() throws RecognitionException {
		try {
			int _type = REFRESH;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1346:8: ( 'REFRESH' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1346:10: 'REFRESH'
			{
			match("REFRESH"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "REFRESH"

	// $ANTLR start "RESTORE"
	public final void mRESTORE() throws RecognitionException {
		try {
			int _type = RESTORE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1347:8: ( 'RESTORE' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1347:10: 'RESTORE'
			{
			match("RESTORE"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "RESTORE"

	// $ANTLR start "DROP"
	public final void mDROP() throws RecognitionException {
		try {
			int _type = DROP;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1348:5: ( 'DROP' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1348:7: 'DROP'
			{
			match("DROP"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "DROP"

	// $ANTLR start "ALIAS"
	public final void mALIAS() throws RecognitionException {
		try {
			int _type = ALIAS;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1349:6: ( 'ALIAS' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1349:8: 'ALIAS'
			{
			match("ALIAS"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "ALIAS"

	// $ANTLR start "UNION"
	public final void mUNION() throws RecognitionException {
		try {
			int _type = UNION;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1350:6: ( 'UNION' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1350:8: 'UNION'
			{
			match("UNION"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "UNION"

	// $ANTLR start "EXCEPT"
	public final void mEXCEPT() throws RecognitionException {
		try {
			int _type = EXCEPT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1351:7: ( 'EXCEPT' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1351:9: 'EXCEPT'
			{
			match("EXCEPT"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "EXCEPT"

	// $ANTLR start "INTERSECT"
	public final void mINTERSECT() throws RecognitionException {
		try {
			int _type = INTERSECT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1352:10: ( 'INTERSECT' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1352:12: 'INTERSECT'
			{
			match("INTERSECT"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "INTERSECT"

	// $ANTLR start "SYSTEM"
	public final void mSYSTEM() throws RecognitionException {
		try {
			int _type = SYSTEM;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1353:7: ( 'SYSTEM' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1353:9: 'SYSTEM'
			{
			match("SYSTEM"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "SYSTEM"

	// $ANTLR start "BERNOULLI"
	public final void mBERNOULLI() throws RecognitionException {
		try {
			int _type = BERNOULLI;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1354:10: ( 'BERNOULLI' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1354:12: 'BERNOULLI'
			{
			match("BERNOULLI"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "BERNOULLI"

	// $ANTLR start "TABLESAMPLE"
	public final void mTABLESAMPLE() throws RecognitionException {
		try {
			int _type = TABLESAMPLE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1355:12: ( 'TABLESAMPLE' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1355:14: 'TABLESAMPLE'
			{
			match("TABLESAMPLE"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "TABLESAMPLE"

	// $ANTLR start "STRATIFY"
	public final void mSTRATIFY() throws RecognitionException {
		try {
			int _type = STRATIFY;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1356:9: ( 'STRATIFY' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1356:11: 'STRATIFY'
			{
			match("STRATIFY"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "STRATIFY"

	// $ANTLR start "INSERT"
	public final void mINSERT() throws RecognitionException {
		try {
			int _type = INSERT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1357:7: ( 'INSERT' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1357:9: 'INSERT'
			{
			match("INSERT"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "INSERT"

	// $ANTLR start "INTO"
	public final void mINTO() throws RecognitionException {
		try {
			int _type = INTO;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1358:5: ( 'INTO' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1358:7: 'INTO'
			{
			match("INTO"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "INTO"

	// $ANTLR start "VALUES"
	public final void mVALUES() throws RecognitionException {
		try {
			int _type = VALUES;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1359:7: ( 'VALUES' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1359:9: 'VALUES'
			{
			match("VALUES"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "VALUES"

	// $ANTLR start "DELETE"
	public final void mDELETE() throws RecognitionException {
		try {
			int _type = DELETE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1360:7: ( 'DELETE' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1360:9: 'DELETE'
			{
			match("DELETE"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "DELETE"

	// $ANTLR start "UPDATE"
	public final void mUPDATE() throws RecognitionException {
		try {
			int _type = UPDATE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1361:7: ( 'UPDATE' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1361:9: 'UPDATE'
			{
			match("UPDATE"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "UPDATE"

	// $ANTLR start "KEY"
	public final void mKEY() throws RecognitionException {
		try {
			int _type = KEY;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1362:4: ( 'KEY' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1362:6: 'KEY'
			{
			match("KEY"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "KEY"

	// $ANTLR start "DUPLICATE"
	public final void mDUPLICATE() throws RecognitionException {
		try {
			int _type = DUPLICATE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1363:10: ( 'DUPLICATE' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1363:12: 'DUPLICATE'
			{
			match("DUPLICATE"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "DUPLICATE"

	// $ANTLR start "SET"
	public final void mSET() throws RecognitionException {
		try {
			int _type = SET;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1364:4: ( 'SET' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1364:6: 'SET'
			{
			match("SET"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "SET"

	// $ANTLR start "RESET"
	public final void mRESET() throws RecognitionException {
		try {
			int _type = RESET;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1365:6: ( 'RESET' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1365:8: 'RESET'
			{
			match("RESET"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "RESET"

	// $ANTLR start "COPY"
	public final void mCOPY() throws RecognitionException {
		try {
			int _type = COPY;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1366:5: ( 'COPY' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1366:7: 'COPY'
			{
			match("COPY"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "COPY"

	// $ANTLR start "CLUSTERED"
	public final void mCLUSTERED() throws RecognitionException {
		try {
			int _type = CLUSTERED;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1367:10: ( 'CLUSTERED' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1367:12: 'CLUSTERED'
			{
			match("CLUSTERED"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "CLUSTERED"

	// $ANTLR start "SHARDS"
	public final void mSHARDS() throws RecognitionException {
		try {
			int _type = SHARDS;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1368:7: ( 'SHARDS' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1368:9: 'SHARDS'
			{
			match("SHARDS"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "SHARDS"

	// $ANTLR start "PRIMARY_KEY"
	public final void mPRIMARY_KEY() throws RecognitionException {
		try {
			int _type = PRIMARY_KEY;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1369:12: ( 'PRIMARY KEY' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1369:14: 'PRIMARY KEY'
			{
			match("PRIMARY KEY"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "PRIMARY_KEY"

	// $ANTLR start "OFF"
	public final void mOFF() throws RecognitionException {
		try {
			int _type = OFF;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1370:4: ( 'OFF' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1370:6: 'OFF'
			{
			match("OFF"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "OFF"

	// $ANTLR start "FULLTEXT"
	public final void mFULLTEXT() throws RecognitionException {
		try {
			int _type = FULLTEXT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1371:9: ( 'FULLTEXT' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1371:11: 'FULLTEXT'
			{
			match("FULLTEXT"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "FULLTEXT"

	// $ANTLR start "PLAIN"
	public final void mPLAIN() throws RecognitionException {
		try {
			int _type = PLAIN;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1372:6: ( 'PLAIN' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1372:8: 'PLAIN'
			{
			match("PLAIN"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "PLAIN"

	// $ANTLR start "INDEX"
	public final void mINDEX() throws RecognitionException {
		try {
			int _type = INDEX;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1373:6: ( 'INDEX' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1373:8: 'INDEX'
			{
			match("INDEX"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "INDEX"

	// $ANTLR start "DYNAMIC"
	public final void mDYNAMIC() throws RecognitionException {
		try {
			int _type = DYNAMIC;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1375:8: ( 'DYNAMIC' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1375:10: 'DYNAMIC'
			{
			match("DYNAMIC"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "DYNAMIC"

	// $ANTLR start "STRICT"
	public final void mSTRICT() throws RecognitionException {
		try {
			int _type = STRICT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1376:7: ( 'STRICT' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1376:9: 'STRICT'
			{
			match("STRICT"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "STRICT"

	// $ANTLR start "IGNORED"
	public final void mIGNORED() throws RecognitionException {
		try {
			int _type = IGNORED;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1377:8: ( 'IGNORED' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1377:10: 'IGNORED'
			{
			match("IGNORED"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "IGNORED"

	// $ANTLR start "ARRAY"
	public final void mARRAY() throws RecognitionException {
		try {
			int _type = ARRAY;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1379:6: ( 'ARRAY' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1379:8: 'ARRAY'
			{
			match("ARRAY"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "ARRAY"

	// $ANTLR start "ANALYZER"
	public final void mANALYZER() throws RecognitionException {
		try {
			int _type = ANALYZER;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1381:9: ( 'ANALYZER' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1381:11: 'ANALYZER'
			{
			match("ANALYZER"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "ANALYZER"

	// $ANTLR start "EXTENDS"
	public final void mEXTENDS() throws RecognitionException {
		try {
			int _type = EXTENDS;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1382:8: ( 'EXTENDS' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1382:10: 'EXTENDS'
			{
			match("EXTENDS"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "EXTENDS"

	// $ANTLR start "TOKENIZER"
	public final void mTOKENIZER() throws RecognitionException {
		try {
			int _type = TOKENIZER;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1383:10: ( 'TOKENIZER' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1383:12: 'TOKENIZER'
			{
			match("TOKENIZER"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "TOKENIZER"

	// $ANTLR start "TOKEN_FILTERS"
	public final void mTOKEN_FILTERS() throws RecognitionException {
		try {
			int _type = TOKEN_FILTERS;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1384:14: ( 'TOKEN_FILTERS' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1384:16: 'TOKEN_FILTERS'
			{
			match("TOKEN_FILTERS"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "TOKEN_FILTERS"

	// $ANTLR start "CHAR_FILTERS"
	public final void mCHAR_FILTERS() throws RecognitionException {
		try {
			int _type = CHAR_FILTERS;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1385:13: ( 'CHAR_FILTERS' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1385:15: 'CHAR_FILTERS'
			{
			match("CHAR_FILTERS"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "CHAR_FILTERS"

	// $ANTLR start "PARTITIONED"
	public final void mPARTITIONED() throws RecognitionException {
		try {
			int _type = PARTITIONED;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1387:12: ( 'PARTITIONED' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1387:14: 'PARTITIONED'
			{
			match("PARTITIONED"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "PARTITIONED"

	// $ANTLR start "TRANSIENT"
	public final void mTRANSIENT() throws RecognitionException {
		try {
			int _type = TRANSIENT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1389:10: ( 'TRANSIENT' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1389:12: 'TRANSIENT'
			{
			match("TRANSIENT"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "TRANSIENT"

	// $ANTLR start "PERSISTENT"
	public final void mPERSISTENT() throws RecognitionException {
		try {
			int _type = PERSISTENT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1390:11: ( 'PERSISTENT' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1390:13: 'PERSISTENT'
			{
			match("PERSISTENT"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "PERSISTENT"

	// $ANTLR start "MATCH"
	public final void mMATCH() throws RecognitionException {
		try {
			int _type = MATCH;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1392:6: ( 'MATCH' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1392:8: 'MATCH'
			{
			match("MATCH"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "MATCH"

	// $ANTLR start "GENERATED"
	public final void mGENERATED() throws RecognitionException {
		try {
			int _type = GENERATED;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1394:10: ( 'GENERATED' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1394:12: 'GENERATED'
			{
			match("GENERATED"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "GENERATED"

	// $ANTLR start "ALWAYS"
	public final void mALWAYS() throws RecognitionException {
		try {
			int _type = ALWAYS;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1395:7: ( 'ALWAYS' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1395:9: 'ALWAYS'
			{
			match("ALWAYS"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "ALWAYS"

	// $ANTLR start "IDENTIFIED"
	public final void mIDENTIFIED() throws RecognitionException {
		try {
			int _type = IDENTIFIED;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1397:11: ( 'IDENTIFIED' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1397:13: 'IDENTIFIED'
			{
			match("IDENTIFIED"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "IDENTIFIED"

	// $ANTLR start "USER"
	public final void mUSER() throws RecognitionException {
		try {
			int _type = USER;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1398:5: ( 'USER' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1398:7: 'USER'
			{
			match("USER"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "USER"

	// $ANTLR start "CLUSTER"
	public final void mCLUSTER() throws RecognitionException {
		try {
			int _type = CLUSTER;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1399:8: ( 'CLUSTER' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1399:10: 'CLUSTER'
			{
			match("CLUSTER"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "CLUSTER"

	// $ANTLR start "CLUSTERS"
	public final void mCLUSTERS() throws RecognitionException {
		try {
			int _type = CLUSTERS;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1400:9: ( 'CLUSTERS' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1400:11: 'CLUSTERS'
			{
			match("CLUSTERS"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "CLUSTERS"

	// $ANTLR start "MIGRATE"
	public final void mMIGRATE() throws RecognitionException {
		try {
			int _type = MIGRATE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1401:8: ( 'MIGRATE' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1401:10: 'MIGRATE'
			{
			match("MIGRATE"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "MIGRATE"

	// $ANTLR start "NODES"
	public final void mNODES() throws RecognitionException {
		try {
			int _type = NODES;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1402:6: ( 'NODES' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1402:8: 'NODES'
			{
			match("NODES"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "NODES"

	// $ANTLR start "DECOMMISSION"
	public final void mDECOMMISSION() throws RecognitionException {
		try {
			int _type = DECOMMISSION;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1403:13: ( 'DECOMMISSION' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1403:15: 'DECOMMISSION'
			{
			match("DECOMMISSION"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "DECOMMISSION"

	// $ANTLR start "USERS"
	public final void mUSERS() throws RecognitionException {
		try {
			int _type = USERS;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1404:6: ( 'USERS' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1404:8: 'USERS'
			{
			match("USERS"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "USERS"

	// $ANTLR start "REMOVE"
	public final void mREMOVE() throws RecognitionException {
		try {
			int _type = REMOVE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1405:7: ( 'REMOVE' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1405:9: 'REMOVE'
			{
			match("REMOVE"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "REMOVE"

	// $ANTLR start "WHITELIST"
	public final void mWHITELIST() throws RecognitionException {
		try {
			int _type = WHITELIST;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1406:10: ( 'WHITELIST' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1406:12: 'WHITELIST'
			{
			match("WHITELIST"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "WHITELIST"

	// $ANTLR start "GRANT"
	public final void mGRANT() throws RecognitionException {
		try {
			int _type = GRANT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1407:9: ( 'GRANT' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1407:13: 'GRANT'
			{
			match("GRANT"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "GRANT"

	// $ANTLR start "GRANTS"
	public final void mGRANTS() throws RecognitionException {
		try {
			int _type = GRANTS;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1408:9: ( 'GRANTS' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1408:13: 'GRANTS'
			{
			match("GRANTS"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "GRANTS"

	// $ANTLR start "REVOKE"
	public final void mREVOKE() throws RecognitionException {
		try {
			int _type = REVOKE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1409:9: ( 'REVOKE' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1409:13: 'REVOKE'
			{
			match("REVOKE"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "REVOKE"

	// $ANTLR start "EQ"
	public final void mEQ() throws RecognitionException {
		try {
			int _type = EQ;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1411:5: ( '=' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1411:7: '='
			{
			match('='); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "EQ"

	// $ANTLR start "NEQ"
	public final void mNEQ() throws RecognitionException {
		try {
			int _type = NEQ;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1412:5: ( '<>' | '!=' )
			int alt1=2;
			int LA1_0 = input.LA(1);
			if ( (LA1_0=='<') ) {
				alt1=1;
			}
			else if ( (LA1_0=='!') ) {
				alt1=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 1, 0, input);
				throw nvae;
			}

			switch (alt1) {
				case 1 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1412:7: '<>'
					{
					match("<>"); 

					}
					break;
				case 2 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1412:14: '!='
					{
					match("!="); 

					}
					break;

			}
			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "NEQ"

	// $ANTLR start "LT"
	public final void mLT() throws RecognitionException {
		try {
			int _type = LT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1413:5: ( '<' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1413:7: '<'
			{
			match('<'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "LT"

	// $ANTLR start "LTE"
	public final void mLTE() throws RecognitionException {
		try {
			int _type = LTE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1414:5: ( '<=' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1414:7: '<='
			{
			match("<="); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "LTE"

	// $ANTLR start "GT"
	public final void mGT() throws RecognitionException {
		try {
			int _type = GT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1415:5: ( '>' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1415:7: '>'
			{
			match('>'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "GT"

	// $ANTLR start "GTE"
	public final void mGTE() throws RecognitionException {
		try {
			int _type = GTE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1416:5: ( '>=' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1416:7: '>='
			{
			match(">="); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "GTE"

	// $ANTLR start "REGEX_MATCH"
	public final void mREGEX_MATCH() throws RecognitionException {
		try {
			int _type = REGEX_MATCH;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1417:12: ( '~' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1417:14: '~'
			{
			match('~'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "REGEX_MATCH"

	// $ANTLR start "REGEX_NO_MATCH"
	public final void mREGEX_NO_MATCH() throws RecognitionException {
		try {
			int _type = REGEX_NO_MATCH;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1418:15: ( '!~' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1418:17: '!~'
			{
			match("!~"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "REGEX_NO_MATCH"

	// $ANTLR start "REGEX_MATCH_CI"
	public final void mREGEX_MATCH_CI() throws RecognitionException {
		try {
			int _type = REGEX_MATCH_CI;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1419:15: ( '~*' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1419:17: '~*'
			{
			match("~*"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "REGEX_MATCH_CI"

	// $ANTLR start "REGEX_NO_MATCH_CI"
	public final void mREGEX_NO_MATCH_CI() throws RecognitionException {
		try {
			int _type = REGEX_NO_MATCH_CI;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1420:18: ( '!~*' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1420:20: '!~*'
			{
			match("!~*"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "REGEX_NO_MATCH_CI"

	// $ANTLR start "STRING"
	public final void mSTRING() throws RecognitionException {
		try {
			int _type = STRING;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1423:5: ( '\\'' (~ '\\'' | '\\'\\'' )* '\\'' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1423:7: '\\'' (~ '\\'' | '\\'\\'' )* '\\''
			{
			match('\''); 
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1423:12: (~ '\\'' | '\\'\\'' )*
			loop2:
			while (true) {
				int alt2=3;
				int LA2_0 = input.LA(1);
				if ( (LA2_0=='\'') ) {
					int LA2_1 = input.LA(2);
					if ( (LA2_1=='\'') ) {
						alt2=2;
					}

				}
				else if ( ((LA2_0 >= '\u0000' && LA2_0 <= '&')||(LA2_0 >= '(' && LA2_0 <= '\uFFFF')) ) {
					alt2=1;
				}

				switch (alt2) {
				case 1 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1423:14: ~ '\\''
					{
					if ( (input.LA(1) >= '\u0000' && input.LA(1) <= '&')||(input.LA(1) >= '(' && input.LA(1) <= '\uFFFF') ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;
				case 2 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1423:22: '\\'\\''
					{
					match("''"); 

					}
					break;

				default :
					break loop2;
				}
			}

			match('\''); 
			 setText(getText().substring(1, getText().length() - 1).replace("''", "'")); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "STRING"

	// $ANTLR start "INTEGER_VALUE"
	public final void mINTEGER_VALUE() throws RecognitionException {
		try {
			int _type = INTEGER_VALUE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1428:5: ( ( DIGIT )+ )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1428:7: ( DIGIT )+
			{
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1428:7: ( DIGIT )+
			int cnt3=0;
			loop3:
			while (true) {
				int alt3=2;
				int LA3_0 = input.LA(1);
				if ( ((LA3_0 >= '0' && LA3_0 <= '9')) ) {
					alt3=1;
				}

				switch (alt3) {
				case 1 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:
					{
					if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					if ( cnt3 >= 1 ) break loop3;
					EarlyExitException eee = new EarlyExitException(3, input);
					throw eee;
				}
				cnt3++;
			}

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "INTEGER_VALUE"

	// $ANTLR start "DECIMAL_VALUE"
	public final void mDECIMAL_VALUE() throws RecognitionException {
		try {
			int _type = DECIMAL_VALUE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1432:5: ( ( DIGIT )+ '.' ( DIGIT )* | '.' ( DIGIT )+ | ( DIGIT )+ ( '.' ( DIGIT )* )? EXPONENT | '.' ( DIGIT )+ EXPONENT )
			int alt11=4;
			alt11 = dfa11.predict(input);
			switch (alt11) {
				case 1 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1432:7: ( DIGIT )+ '.' ( DIGIT )*
					{
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1432:7: ( DIGIT )+
					int cnt4=0;
					loop4:
					while (true) {
						int alt4=2;
						int LA4_0 = input.LA(1);
						if ( ((LA4_0 >= '0' && LA4_0 <= '9')) ) {
							alt4=1;
						}

						switch (alt4) {
						case 1 :
							// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:
							{
							if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
								input.consume();
							}
							else {
								MismatchedSetException mse = new MismatchedSetException(null,input);
								recover(mse);
								throw mse;
							}
							}
							break;

						default :
							if ( cnt4 >= 1 ) break loop4;
							EarlyExitException eee = new EarlyExitException(4, input);
							throw eee;
						}
						cnt4++;
					}

					match('.'); 
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1432:18: ( DIGIT )*
					loop5:
					while (true) {
						int alt5=2;
						int LA5_0 = input.LA(1);
						if ( ((LA5_0 >= '0' && LA5_0 <= '9')) ) {
							alt5=1;
						}

						switch (alt5) {
						case 1 :
							// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:
							{
							if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
								input.consume();
							}
							else {
								MismatchedSetException mse = new MismatchedSetException(null,input);
								recover(mse);
								throw mse;
							}
							}
							break;

						default :
							break loop5;
						}
					}

					}
					break;
				case 2 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1433:7: '.' ( DIGIT )+
					{
					match('.'); 
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1433:11: ( DIGIT )+
					int cnt6=0;
					loop6:
					while (true) {
						int alt6=2;
						int LA6_0 = input.LA(1);
						if ( ((LA6_0 >= '0' && LA6_0 <= '9')) ) {
							alt6=1;
						}

						switch (alt6) {
						case 1 :
							// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:
							{
							if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
								input.consume();
							}
							else {
								MismatchedSetException mse = new MismatchedSetException(null,input);
								recover(mse);
								throw mse;
							}
							}
							break;

						default :
							if ( cnt6 >= 1 ) break loop6;
							EarlyExitException eee = new EarlyExitException(6, input);
							throw eee;
						}
						cnt6++;
					}

					}
					break;
				case 3 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1434:7: ( DIGIT )+ ( '.' ( DIGIT )* )? EXPONENT
					{
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1434:7: ( DIGIT )+
					int cnt7=0;
					loop7:
					while (true) {
						int alt7=2;
						int LA7_0 = input.LA(1);
						if ( ((LA7_0 >= '0' && LA7_0 <= '9')) ) {
							alt7=1;
						}

						switch (alt7) {
						case 1 :
							// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:
							{
							if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
								input.consume();
							}
							else {
								MismatchedSetException mse = new MismatchedSetException(null,input);
								recover(mse);
								throw mse;
							}
							}
							break;

						default :
							if ( cnt7 >= 1 ) break loop7;
							EarlyExitException eee = new EarlyExitException(7, input);
							throw eee;
						}
						cnt7++;
					}

					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1434:14: ( '.' ( DIGIT )* )?
					int alt9=2;
					int LA9_0 = input.LA(1);
					if ( (LA9_0=='.') ) {
						alt9=1;
					}
					switch (alt9) {
						case 1 :
							// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1434:15: '.' ( DIGIT )*
							{
							match('.'); 
							// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1434:19: ( DIGIT )*
							loop8:
							while (true) {
								int alt8=2;
								int LA8_0 = input.LA(1);
								if ( ((LA8_0 >= '0' && LA8_0 <= '9')) ) {
									alt8=1;
								}

								switch (alt8) {
								case 1 :
									// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:
									{
									if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
										input.consume();
									}
									else {
										MismatchedSetException mse = new MismatchedSetException(null,input);
										recover(mse);
										throw mse;
									}
									}
									break;

								default :
									break loop8;
								}
							}

							}
							break;

					}

					mEXPONENT(); 

					}
					break;
				case 4 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1435:7: '.' ( DIGIT )+ EXPONENT
					{
					match('.'); 
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1435:11: ( DIGIT )+
					int cnt10=0;
					loop10:
					while (true) {
						int alt10=2;
						int LA10_0 = input.LA(1);
						if ( ((LA10_0 >= '0' && LA10_0 <= '9')) ) {
							alt10=1;
						}

						switch (alt10) {
						case 1 :
							// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:
							{
							if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
								input.consume();
							}
							else {
								MismatchedSetException mse = new MismatchedSetException(null,input);
								recover(mse);
								throw mse;
							}
							}
							break;

						default :
							if ( cnt10 >= 1 ) break loop10;
							EarlyExitException eee = new EarlyExitException(10, input);
							throw eee;
						}
						cnt10++;
					}

					mEXPONENT(); 

					}
					break;

			}
			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "DECIMAL_VALUE"

	// $ANTLR start "IDENT"
	public final void mIDENT() throws RecognitionException {
		try {
			int _type = IDENT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1439:5: ( ( LETTER | '_' ) ( LETTER | DIGIT | '_' | '\\@' )* )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1439:7: ( LETTER | '_' ) ( LETTER | DIGIT | '_' | '\\@' )*
			{
			if ( (input.LA(1) >= 'A' && input.LA(1) <= 'Z')||input.LA(1)=='_' ) {
				input.consume();
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				recover(mse);
				throw mse;
			}
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1439:22: ( LETTER | DIGIT | '_' | '\\@' )*
			loop12:
			while (true) {
				int alt12=2;
				int LA12_0 = input.LA(1);
				if ( ((LA12_0 >= '0' && LA12_0 <= '9')||(LA12_0 >= '@' && LA12_0 <= 'Z')||LA12_0=='_') ) {
					alt12=1;
				}

				switch (alt12) {
				case 1 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:
					{
					if ( (input.LA(1) >= '0' && input.LA(1) <= '9')||(input.LA(1) >= '@' && input.LA(1) <= 'Z')||input.LA(1)=='_' ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					break loop12;
				}
			}

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "IDENT"

	// $ANTLR start "DIGIT_IDENT"
	public final void mDIGIT_IDENT() throws RecognitionException {
		try {
			int _type = DIGIT_IDENT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1443:5: ( DIGIT ( LETTER | DIGIT | '_' | '\\@' )+ )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1443:7: DIGIT ( LETTER | DIGIT | '_' | '\\@' )+
			{
			mDIGIT(); 

			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1443:13: ( LETTER | DIGIT | '_' | '\\@' )+
			int cnt13=0;
			loop13:
			while (true) {
				int alt13=2;
				int LA13_0 = input.LA(1);
				if ( ((LA13_0 >= '0' && LA13_0 <= '9')||(LA13_0 >= '@' && LA13_0 <= 'Z')||LA13_0=='_') ) {
					alt13=1;
				}

				switch (alt13) {
				case 1 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:
					{
					if ( (input.LA(1) >= '0' && input.LA(1) <= '9')||(input.LA(1) >= '@' && input.LA(1) <= 'Z')||input.LA(1)=='_' ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					if ( cnt13 >= 1 ) break loop13;
					EarlyExitException eee = new EarlyExitException(13, input);
					throw eee;
				}
				cnt13++;
			}

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "DIGIT_IDENT"

	// $ANTLR start "QUOTED_IDENT"
	public final void mQUOTED_IDENT() throws RecognitionException {
		try {
			int _type = QUOTED_IDENT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1447:5: ( '\"' (~ '\"' | '\"\"' )* '\"' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1447:7: '\"' (~ '\"' | '\"\"' )* '\"'
			{
			match('\"'); 
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1447:11: (~ '\"' | '\"\"' )*
			loop14:
			while (true) {
				int alt14=3;
				int LA14_0 = input.LA(1);
				if ( (LA14_0=='\"') ) {
					int LA14_1 = input.LA(2);
					if ( (LA14_1=='\"') ) {
						alt14=2;
					}

				}
				else if ( ((LA14_0 >= '\u0000' && LA14_0 <= '!')||(LA14_0 >= '#' && LA14_0 <= '\uFFFF')) ) {
					alt14=1;
				}

				switch (alt14) {
				case 1 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1447:13: ~ '\"'
					{
					if ( (input.LA(1) >= '\u0000' && input.LA(1) <= '!')||(input.LA(1) >= '#' && input.LA(1) <= '\uFFFF') ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;
				case 2 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1447:20: '\"\"'
					{
					match("\"\""); 

					}
					break;

				default :
					break loop14;
				}
			}

			match('\"'); 
			 setText(getText().substring(1, getText().length() - 1).replace("\"\"", "\"")); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "QUOTED_IDENT"

	// $ANTLR start "BACKQUOTED_IDENT"
	public final void mBACKQUOTED_IDENT() throws RecognitionException {
		try {
			int _type = BACKQUOTED_IDENT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1452:5: ( '`' (~ '`' | '``' )* '`' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1452:7: '`' (~ '`' | '``' )* '`'
			{
			match('`'); 
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1452:11: (~ '`' | '``' )*
			loop15:
			while (true) {
				int alt15=3;
				int LA15_0 = input.LA(1);
				if ( (LA15_0=='`') ) {
					int LA15_1 = input.LA(2);
					if ( (LA15_1=='`') ) {
						alt15=2;
					}

				}
				else if ( ((LA15_0 >= '\u0000' && LA15_0 <= '_')||(LA15_0 >= 'a' && LA15_0 <= '\uFFFF')) ) {
					alt15=1;
				}

				switch (alt15) {
				case 1 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1452:13: ~ '`'
					{
					if ( (input.LA(1) >= '\u0000' && input.LA(1) <= '_')||(input.LA(1) >= 'a' && input.LA(1) <= '\uFFFF') ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;
				case 2 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1452:20: '``'
					{
					match("``"); 

					}
					break;

				default :
					break loop15;
				}
			}

			match('`'); 
			 setText(getText().substring(1, getText().length() - 1).replace("``", "`")); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "BACKQUOTED_IDENT"

	// $ANTLR start "COLON_IDENT"
	public final void mCOLON_IDENT() throws RecognitionException {
		try {
			int _type = COLON_IDENT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1457:5: ( ( LETTER | DIGIT | '_' )+ ':' ( LETTER | DIGIT | '_' )+ )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1457:7: ( LETTER | DIGIT | '_' )+ ':' ( LETTER | DIGIT | '_' )+
			{
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1457:7: ( LETTER | DIGIT | '_' )+
			int cnt16=0;
			loop16:
			while (true) {
				int alt16=2;
				int LA16_0 = input.LA(1);
				if ( ((LA16_0 >= '0' && LA16_0 <= '9')||(LA16_0 >= 'A' && LA16_0 <= 'Z')||LA16_0=='_') ) {
					alt16=1;
				}

				switch (alt16) {
				case 1 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:
					{
					if ( (input.LA(1) >= '0' && input.LA(1) <= '9')||(input.LA(1) >= 'A' && input.LA(1) <= 'Z')||input.LA(1)=='_' ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					if ( cnt16 >= 1 ) break loop16;
					EarlyExitException eee = new EarlyExitException(16, input);
					throw eee;
				}
				cnt16++;
			}

			match(':'); 
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1457:36: ( LETTER | DIGIT | '_' )+
			int cnt17=0;
			loop17:
			while (true) {
				int alt17=2;
				int LA17_0 = input.LA(1);
				if ( ((LA17_0 >= '0' && LA17_0 <= '9')||(LA17_0 >= 'A' && LA17_0 <= 'Z')||LA17_0=='_') ) {
					alt17=1;
				}

				switch (alt17) {
				case 1 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:
					{
					if ( (input.LA(1) >= '0' && input.LA(1) <= '9')||(input.LA(1) >= 'A' && input.LA(1) <= 'Z')||input.LA(1)=='_' ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					if ( cnt17 >= 1 ) break loop17;
					EarlyExitException eee = new EarlyExitException(17, input);
					throw eee;
				}
				cnt17++;
			}

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "COLON_IDENT"

	// $ANTLR start "EXPONENT"
	public final void mEXPONENT() throws RecognitionException {
		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1461:5: ( 'E' ( '+' | '-' )? ( DIGIT )+ )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1461:7: 'E' ( '+' | '-' )? ( DIGIT )+
			{
			match('E'); 
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1461:11: ( '+' | '-' )?
			int alt18=2;
			int LA18_0 = input.LA(1);
			if ( (LA18_0=='+'||LA18_0=='-') ) {
				alt18=1;
			}
			switch (alt18) {
				case 1 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:
					{
					if ( input.LA(1)=='+'||input.LA(1)=='-' ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

			}

			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1461:24: ( DIGIT )+
			int cnt19=0;
			loop19:
			while (true) {
				int alt19=2;
				int LA19_0 = input.LA(1);
				if ( ((LA19_0 >= '0' && LA19_0 <= '9')) ) {
					alt19=1;
				}

				switch (alt19) {
				case 1 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:
					{
					if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					if ( cnt19 >= 1 ) break loop19;
					EarlyExitException eee = new EarlyExitException(19, input);
					throw eee;
				}
				cnt19++;
			}

			}

		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "EXPONENT"

	// $ANTLR start "DIGIT"
	public final void mDIGIT() throws RecognitionException {
		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1465:5: ( '0' .. '9' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:
			{
			if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
				input.consume();
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				recover(mse);
				throw mse;
			}
			}

		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "DIGIT"

	// $ANTLR start "LETTER"
	public final void mLETTER() throws RecognitionException {
		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1469:5: ( 'A' .. 'Z' )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:
			{
			if ( (input.LA(1) >= 'A' && input.LA(1) <= 'Z') ) {
				input.consume();
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				recover(mse);
				throw mse;
			}
			}

		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "LETTER"

	// $ANTLR start "COMMENT"
	public final void mCOMMENT() throws RecognitionException {
		try {
			int _type = COMMENT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1473:5: ( '--' (~ ( '\\r' | '\\n' ) )* ( ( '\\r' )? '\\n' )? | '/*' ( options {greedy=false; } : . )* '*/' )
			int alt24=2;
			int LA24_0 = input.LA(1);
			if ( (LA24_0=='-') ) {
				alt24=1;
			}
			else if ( (LA24_0=='/') ) {
				alt24=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 24, 0, input);
				throw nvae;
			}

			switch (alt24) {
				case 1 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1473:7: '--' (~ ( '\\r' | '\\n' ) )* ( ( '\\r' )? '\\n' )?
					{
					match("--"); 

					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1473:12: (~ ( '\\r' | '\\n' ) )*
					loop20:
					while (true) {
						int alt20=2;
						int LA20_0 = input.LA(1);
						if ( ((LA20_0 >= '\u0000' && LA20_0 <= '\t')||(LA20_0 >= '\u000B' && LA20_0 <= '\f')||(LA20_0 >= '\u000E' && LA20_0 <= '\uFFFF')) ) {
							alt20=1;
						}

						switch (alt20) {
						case 1 :
							// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:
							{
							if ( (input.LA(1) >= '\u0000' && input.LA(1) <= '\t')||(input.LA(1) >= '\u000B' && input.LA(1) <= '\f')||(input.LA(1) >= '\u000E' && input.LA(1) <= '\uFFFF') ) {
								input.consume();
							}
							else {
								MismatchedSetException mse = new MismatchedSetException(null,input);
								recover(mse);
								throw mse;
							}
							}
							break;

						default :
							break loop20;
						}
					}

					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1473:30: ( ( '\\r' )? '\\n' )?
					int alt22=2;
					int LA22_0 = input.LA(1);
					if ( (LA22_0=='\n'||LA22_0=='\r') ) {
						alt22=1;
					}
					switch (alt22) {
						case 1 :
							// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1473:31: ( '\\r' )? '\\n'
							{
							// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1473:31: ( '\\r' )?
							int alt21=2;
							int LA21_0 = input.LA(1);
							if ( (LA21_0=='\r') ) {
								alt21=1;
							}
							switch (alt21) {
								case 1 :
									// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1473:31: '\\r'
									{
									match('\r'); 
									}
									break;

							}

							match('\n'); 
							}
							break;

					}

					 _channel=HIDDEN; 
					}
					break;
				case 2 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1474:7: '/*' ( options {greedy=false; } : . )* '*/'
					{
					match("/*"); 

					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1474:12: ( options {greedy=false; } : . )*
					loop23:
					while (true) {
						int alt23=2;
						int LA23_0 = input.LA(1);
						if ( (LA23_0=='*') ) {
							int LA23_1 = input.LA(2);
							if ( (LA23_1=='/') ) {
								alt23=2;
							}
							else if ( ((LA23_1 >= '\u0000' && LA23_1 <= '.')||(LA23_1 >= '0' && LA23_1 <= '\uFFFF')) ) {
								alt23=1;
							}

						}
						else if ( ((LA23_0 >= '\u0000' && LA23_0 <= ')')||(LA23_0 >= '+' && LA23_0 <= '\uFFFF')) ) {
							alt23=1;
						}

						switch (alt23) {
						case 1 :
							// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1474:39: .
							{
							matchAny(); 
							}
							break;

						default :
							break loop23;
						}
					}

					match("*/"); 

					 _channel=HIDDEN; 
					}
					break;

			}
			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "COMMENT"

	// $ANTLR start "WS"
	public final void mWS() throws RecognitionException {
		try {
			int _type = WS;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1478:5: ( ( ' ' | '\\t' | '\\n' | '\\r' )+ )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1478:7: ( ' ' | '\\t' | '\\n' | '\\r' )+
			{
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1478:7: ( ' ' | '\\t' | '\\n' | '\\r' )+
			int cnt25=0;
			loop25:
			while (true) {
				int alt25=2;
				int LA25_0 = input.LA(1);
				if ( ((LA25_0 >= '\t' && LA25_0 <= '\n')||LA25_0=='\r'||LA25_0==' ') ) {
					alt25=1;
				}

				switch (alt25) {
				case 1 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:
					{
					if ( (input.LA(1) >= '\t' && input.LA(1) <= '\n')||input.LA(1)=='\r'||input.LA(1)==' ' ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					if ( cnt25 >= 1 ) break loop25;
					EarlyExitException eee = new EarlyExitException(25, input);
					throw eee;
				}
				cnt25++;
			}

			 _channel=HIDDEN; 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "WS"

	@Override
	public void mTokens() throws RecognitionException {
		// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:8: ( T__328 | T__329 | T__330 | T__331 | T__332 | T__333 | T__334 | T__335 | T__336 | T__337 | T__338 | T__339 | T__340 | T__341 | T__342 | T__343 | T__344 | T__345 | SELECT | FROM | TO | AS | ALL | ANY | SOME | DIRECTORY | DISTINCT | WHERE | GROUP | BY | ORDER | HAVING | LIMIT | OFFSET | OR | AND | IN | NOT | EXISTS | BETWEEN | LIKE | IS | NULL | TRUE | FALSE | NULLS | FIRST | LAST | ESCAPE | ASC | DESC | SUBSTRING | FOR | DATE | TIME | YEAR | MONTH | DAY | HOUR | MINUTE | SECOND | CURRENT_DATE | CURRENT_TIME | CURRENT_TIMESTAMP | EXTRACT | COALESCE | NULLIF | CASE | WHEN | THEN | ELSE | END | IF | JOIN | CROSS | OUTER | INNER | LEFT | RIGHT | FULL | NATURAL | USING | ON | OVER | PARTITION | RANGE | ROWS | UNBOUNDED | PRECEDING | FOLLOWING | CURRENT | ROW | WITH | RECURSIVE | CREATE | BLOB | TABLE | REPOSITORY | SNAPSHOT | ALTER | KILL | ONLY | ADD | COLUMN | BOOLEAN | BYTE | SHORT | INTEGER | INT | LONG | FLOAT | DOUBLE | TIMESTAMP | IP | OBJECT | STRING_TYPE | GEO_POINT | GEO_SHAPE | GLOBAL | CONSTRAINT | DESCRIBE | EXPLAIN | FORMAT | TYPE | TEXT | GRAPHVIZ | LOGICAL | DISTRIBUTED | CAST | TRY_CAST | SHOW | TABLES | SCHEMAS | DATABASES | CATALOGS | COLUMNS | PARTITIONS | FUNCTIONS | MATERIALIZED | VIEW | REFRESH | RESTORE | DROP | ALIAS | UNION | EXCEPT | INTERSECT | SYSTEM | BERNOULLI | TABLESAMPLE | STRATIFY | INSERT | INTO | VALUES | DELETE | UPDATE | KEY | DUPLICATE | SET | RESET | COPY | CLUSTERED | SHARDS | PRIMARY_KEY | OFF | FULLTEXT | PLAIN | INDEX | DYNAMIC | STRICT | IGNORED | ARRAY | ANALYZER | EXTENDS | TOKENIZER | TOKEN_FILTERS | CHAR_FILTERS | PARTITIONED | TRANSIENT | PERSISTENT | MATCH | GENERATED | ALWAYS | IDENTIFIED | USER | CLUSTER | CLUSTERS | MIGRATE | NODES | DECOMMISSION | USERS | REMOVE | WHITELIST | GRANT | GRANTS | REVOKE | EQ | NEQ | LT | LTE | GT | GTE | REGEX_MATCH | REGEX_NO_MATCH | REGEX_MATCH_CI | REGEX_NO_MATCH_CI | STRING | INTEGER_VALUE | DECIMAL_VALUE | IDENT | DIGIT_IDENT | QUOTED_IDENT | BACKQUOTED_IDENT | COLON_IDENT | COMMENT | WS )
		int alt26=216;
		alt26 = dfa26.predict(input);
		switch (alt26) {
			case 1 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:10: T__328
				{
				mT__328(); 

				}
				break;
			case 2 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:17: T__329
				{
				mT__329(); 

				}
				break;
			case 3 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:24: T__330
				{
				mT__330(); 

				}
				break;
			case 4 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:31: T__331
				{
				mT__331(); 

				}
				break;
			case 5 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:38: T__332
				{
				mT__332(); 

				}
				break;
			case 6 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:45: T__333
				{
				mT__333(); 

				}
				break;
			case 7 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:52: T__334
				{
				mT__334(); 

				}
				break;
			case 8 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:59: T__335
				{
				mT__335(); 

				}
				break;
			case 9 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:66: T__336
				{
				mT__336(); 

				}
				break;
			case 10 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:73: T__337
				{
				mT__337(); 

				}
				break;
			case 11 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:80: T__338
				{
				mT__338(); 

				}
				break;
			case 12 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:87: T__339
				{
				mT__339(); 

				}
				break;
			case 13 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:94: T__340
				{
				mT__340(); 

				}
				break;
			case 14 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:101: T__341
				{
				mT__341(); 

				}
				break;
			case 15 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:108: T__342
				{
				mT__342(); 

				}
				break;
			case 16 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:115: T__343
				{
				mT__343(); 

				}
				break;
			case 17 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:122: T__344
				{
				mT__344(); 

				}
				break;
			case 18 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:129: T__345
				{
				mT__345(); 

				}
				break;
			case 19 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:136: SELECT
				{
				mSELECT(); 

				}
				break;
			case 20 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:143: FROM
				{
				mFROM(); 

				}
				break;
			case 21 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:148: TO
				{
				mTO(); 

				}
				break;
			case 22 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:151: AS
				{
				mAS(); 

				}
				break;
			case 23 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:154: ALL
				{
				mALL(); 

				}
				break;
			case 24 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:158: ANY
				{
				mANY(); 

				}
				break;
			case 25 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:162: SOME
				{
				mSOME(); 

				}
				break;
			case 26 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:167: DIRECTORY
				{
				mDIRECTORY(); 

				}
				break;
			case 27 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:177: DISTINCT
				{
				mDISTINCT(); 

				}
				break;
			case 28 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:186: WHERE
				{
				mWHERE(); 

				}
				break;
			case 29 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:192: GROUP
				{
				mGROUP(); 

				}
				break;
			case 30 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:198: BY
				{
				mBY(); 

				}
				break;
			case 31 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:201: ORDER
				{
				mORDER(); 

				}
				break;
			case 32 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:207: HAVING
				{
				mHAVING(); 

				}
				break;
			case 33 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:214: LIMIT
				{
				mLIMIT(); 

				}
				break;
			case 34 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:220: OFFSET
				{
				mOFFSET(); 

				}
				break;
			case 35 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:227: OR
				{
				mOR(); 

				}
				break;
			case 36 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:230: AND
				{
				mAND(); 

				}
				break;
			case 37 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:234: IN
				{
				mIN(); 

				}
				break;
			case 38 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:237: NOT
				{
				mNOT(); 

				}
				break;
			case 39 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:241: EXISTS
				{
				mEXISTS(); 

				}
				break;
			case 40 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:248: BETWEEN
				{
				mBETWEEN(); 

				}
				break;
			case 41 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:256: LIKE
				{
				mLIKE(); 

				}
				break;
			case 42 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:261: IS
				{
				mIS(); 

				}
				break;
			case 43 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:264: NULL
				{
				mNULL(); 

				}
				break;
			case 44 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:269: TRUE
				{
				mTRUE(); 

				}
				break;
			case 45 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:274: FALSE
				{
				mFALSE(); 

				}
				break;
			case 46 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:280: NULLS
				{
				mNULLS(); 

				}
				break;
			case 47 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:286: FIRST
				{
				mFIRST(); 

				}
				break;
			case 48 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:292: LAST
				{
				mLAST(); 

				}
				break;
			case 49 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:297: ESCAPE
				{
				mESCAPE(); 

				}
				break;
			case 50 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:304: ASC
				{
				mASC(); 

				}
				break;
			case 51 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:308: DESC
				{
				mDESC(); 

				}
				break;
			case 52 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:313: SUBSTRING
				{
				mSUBSTRING(); 

				}
				break;
			case 53 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:323: FOR
				{
				mFOR(); 

				}
				break;
			case 54 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:327: DATE
				{
				mDATE(); 

				}
				break;
			case 55 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:332: TIME
				{
				mTIME(); 

				}
				break;
			case 56 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:337: YEAR
				{
				mYEAR(); 

				}
				break;
			case 57 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:342: MONTH
				{
				mMONTH(); 

				}
				break;
			case 58 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:348: DAY
				{
				mDAY(); 

				}
				break;
			case 59 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:352: HOUR
				{
				mHOUR(); 

				}
				break;
			case 60 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:357: MINUTE
				{
				mMINUTE(); 

				}
				break;
			case 61 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:364: SECOND
				{
				mSECOND(); 

				}
				break;
			case 62 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:371: CURRENT_DATE
				{
				mCURRENT_DATE(); 

				}
				break;
			case 63 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:384: CURRENT_TIME
				{
				mCURRENT_TIME(); 

				}
				break;
			case 64 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:397: CURRENT_TIMESTAMP
				{
				mCURRENT_TIMESTAMP(); 

				}
				break;
			case 65 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:415: EXTRACT
				{
				mEXTRACT(); 

				}
				break;
			case 66 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:423: COALESCE
				{
				mCOALESCE(); 

				}
				break;
			case 67 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:432: NULLIF
				{
				mNULLIF(); 

				}
				break;
			case 68 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:439: CASE
				{
				mCASE(); 

				}
				break;
			case 69 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:444: WHEN
				{
				mWHEN(); 

				}
				break;
			case 70 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:449: THEN
				{
				mTHEN(); 

				}
				break;
			case 71 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:454: ELSE
				{
				mELSE(); 

				}
				break;
			case 72 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:459: END
				{
				mEND(); 

				}
				break;
			case 73 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:463: IF
				{
				mIF(); 

				}
				break;
			case 74 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:466: JOIN
				{
				mJOIN(); 

				}
				break;
			case 75 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:471: CROSS
				{
				mCROSS(); 

				}
				break;
			case 76 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:477: OUTER
				{
				mOUTER(); 

				}
				break;
			case 77 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:483: INNER
				{
				mINNER(); 

				}
				break;
			case 78 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:489: LEFT
				{
				mLEFT(); 

				}
				break;
			case 79 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:494: RIGHT
				{
				mRIGHT(); 

				}
				break;
			case 80 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:500: FULL
				{
				mFULL(); 

				}
				break;
			case 81 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:505: NATURAL
				{
				mNATURAL(); 

				}
				break;
			case 82 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:513: USING
				{
				mUSING(); 

				}
				break;
			case 83 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:519: ON
				{
				mON(); 

				}
				break;
			case 84 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:522: OVER
				{
				mOVER(); 

				}
				break;
			case 85 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:527: PARTITION
				{
				mPARTITION(); 

				}
				break;
			case 86 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:537: RANGE
				{
				mRANGE(); 

				}
				break;
			case 87 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:543: ROWS
				{
				mROWS(); 

				}
				break;
			case 88 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:548: UNBOUNDED
				{
				mUNBOUNDED(); 

				}
				break;
			case 89 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:558: PRECEDING
				{
				mPRECEDING(); 

				}
				break;
			case 90 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:568: FOLLOWING
				{
				mFOLLOWING(); 

				}
				break;
			case 91 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:578: CURRENT
				{
				mCURRENT(); 

				}
				break;
			case 92 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:586: ROW
				{
				mROW(); 

				}
				break;
			case 93 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:590: WITH
				{
				mWITH(); 

				}
				break;
			case 94 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:595: RECURSIVE
				{
				mRECURSIVE(); 

				}
				break;
			case 95 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:605: CREATE
				{
				mCREATE(); 

				}
				break;
			case 96 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:612: BLOB
				{
				mBLOB(); 

				}
				break;
			case 97 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:617: TABLE
				{
				mTABLE(); 

				}
				break;
			case 98 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:623: REPOSITORY
				{
				mREPOSITORY(); 

				}
				break;
			case 99 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:634: SNAPSHOT
				{
				mSNAPSHOT(); 

				}
				break;
			case 100 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:643: ALTER
				{
				mALTER(); 

				}
				break;
			case 101 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:649: KILL
				{
				mKILL(); 

				}
				break;
			case 102 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:654: ONLY
				{
				mONLY(); 

				}
				break;
			case 103 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:659: ADD
				{
				mADD(); 

				}
				break;
			case 104 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:663: COLUMN
				{
				mCOLUMN(); 

				}
				break;
			case 105 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:670: BOOLEAN
				{
				mBOOLEAN(); 

				}
				break;
			case 106 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:678: BYTE
				{
				mBYTE(); 

				}
				break;
			case 107 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:683: SHORT
				{
				mSHORT(); 

				}
				break;
			case 108 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:689: INTEGER
				{
				mINTEGER(); 

				}
				break;
			case 109 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:697: INT
				{
				mINT(); 

				}
				break;
			case 110 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:701: LONG
				{
				mLONG(); 

				}
				break;
			case 111 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:706: FLOAT
				{
				mFLOAT(); 

				}
				break;
			case 112 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:712: DOUBLE
				{
				mDOUBLE(); 

				}
				break;
			case 113 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:719: TIMESTAMP
				{
				mTIMESTAMP(); 

				}
				break;
			case 114 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:729: IP
				{
				mIP(); 

				}
				break;
			case 115 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:732: OBJECT
				{
				mOBJECT(); 

				}
				break;
			case 116 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:739: STRING_TYPE
				{
				mSTRING_TYPE(); 

				}
				break;
			case 117 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:751: GEO_POINT
				{
				mGEO_POINT(); 

				}
				break;
			case 118 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:761: GEO_SHAPE
				{
				mGEO_SHAPE(); 

				}
				break;
			case 119 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:771: GLOBAL
				{
				mGLOBAL(); 

				}
				break;
			case 120 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:778: CONSTRAINT
				{
				mCONSTRAINT(); 

				}
				break;
			case 121 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:789: DESCRIBE
				{
				mDESCRIBE(); 

				}
				break;
			case 122 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:798: EXPLAIN
				{
				mEXPLAIN(); 

				}
				break;
			case 123 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:806: FORMAT
				{
				mFORMAT(); 

				}
				break;
			case 124 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:813: TYPE
				{
				mTYPE(); 

				}
				break;
			case 125 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:818: TEXT
				{
				mTEXT(); 

				}
				break;
			case 126 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:823: GRAPHVIZ
				{
				mGRAPHVIZ(); 

				}
				break;
			case 127 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:832: LOGICAL
				{
				mLOGICAL(); 

				}
				break;
			case 128 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:840: DISTRIBUTED
				{
				mDISTRIBUTED(); 

				}
				break;
			case 129 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:852: CAST
				{
				mCAST(); 

				}
				break;
			case 130 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:857: TRY_CAST
				{
				mTRY_CAST(); 

				}
				break;
			case 131 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:866: SHOW
				{
				mSHOW(); 

				}
				break;
			case 132 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:871: TABLES
				{
				mTABLES(); 

				}
				break;
			case 133 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:878: SCHEMAS
				{
				mSCHEMAS(); 

				}
				break;
			case 134 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:886: DATABASES
				{
				mDATABASES(); 

				}
				break;
			case 135 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:896: CATALOGS
				{
				mCATALOGS(); 

				}
				break;
			case 136 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:905: COLUMNS
				{
				mCOLUMNS(); 

				}
				break;
			case 137 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:913: PARTITIONS
				{
				mPARTITIONS(); 

				}
				break;
			case 138 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:924: FUNCTIONS
				{
				mFUNCTIONS(); 

				}
				break;
			case 139 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:934: MATERIALIZED
				{
				mMATERIALIZED(); 

				}
				break;
			case 140 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:947: VIEW
				{
				mVIEW(); 

				}
				break;
			case 141 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:952: REFRESH
				{
				mREFRESH(); 

				}
				break;
			case 142 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:960: RESTORE
				{
				mRESTORE(); 

				}
				break;
			case 143 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:968: DROP
				{
				mDROP(); 

				}
				break;
			case 144 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:973: ALIAS
				{
				mALIAS(); 

				}
				break;
			case 145 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:979: UNION
				{
				mUNION(); 

				}
				break;
			case 146 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:985: EXCEPT
				{
				mEXCEPT(); 

				}
				break;
			case 147 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:992: INTERSECT
				{
				mINTERSECT(); 

				}
				break;
			case 148 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:1002: SYSTEM
				{
				mSYSTEM(); 

				}
				break;
			case 149 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:1009: BERNOULLI
				{
				mBERNOULLI(); 

				}
				break;
			case 150 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:1019: TABLESAMPLE
				{
				mTABLESAMPLE(); 

				}
				break;
			case 151 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:1031: STRATIFY
				{
				mSTRATIFY(); 

				}
				break;
			case 152 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:1040: INSERT
				{
				mINSERT(); 

				}
				break;
			case 153 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:1047: INTO
				{
				mINTO(); 

				}
				break;
			case 154 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:1052: VALUES
				{
				mVALUES(); 

				}
				break;
			case 155 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:1059: DELETE
				{
				mDELETE(); 

				}
				break;
			case 156 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:1066: UPDATE
				{
				mUPDATE(); 

				}
				break;
			case 157 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:1073: KEY
				{
				mKEY(); 

				}
				break;
			case 158 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:1077: DUPLICATE
				{
				mDUPLICATE(); 

				}
				break;
			case 159 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:1087: SET
				{
				mSET(); 

				}
				break;
			case 160 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:1091: RESET
				{
				mRESET(); 

				}
				break;
			case 161 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:1097: COPY
				{
				mCOPY(); 

				}
				break;
			case 162 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:1102: CLUSTERED
				{
				mCLUSTERED(); 

				}
				break;
			case 163 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:1112: SHARDS
				{
				mSHARDS(); 

				}
				break;
			case 164 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:1119: PRIMARY_KEY
				{
				mPRIMARY_KEY(); 

				}
				break;
			case 165 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:1131: OFF
				{
				mOFF(); 

				}
				break;
			case 166 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:1135: FULLTEXT
				{
				mFULLTEXT(); 

				}
				break;
			case 167 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:1144: PLAIN
				{
				mPLAIN(); 

				}
				break;
			case 168 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:1150: INDEX
				{
				mINDEX(); 

				}
				break;
			case 169 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:1156: DYNAMIC
				{
				mDYNAMIC(); 

				}
				break;
			case 170 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:1164: STRICT
				{
				mSTRICT(); 

				}
				break;
			case 171 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:1171: IGNORED
				{
				mIGNORED(); 

				}
				break;
			case 172 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:1179: ARRAY
				{
				mARRAY(); 

				}
				break;
			case 173 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:1185: ANALYZER
				{
				mANALYZER(); 

				}
				break;
			case 174 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:1194: EXTENDS
				{
				mEXTENDS(); 

				}
				break;
			case 175 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:1202: TOKENIZER
				{
				mTOKENIZER(); 

				}
				break;
			case 176 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:1212: TOKEN_FILTERS
				{
				mTOKEN_FILTERS(); 

				}
				break;
			case 177 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:1226: CHAR_FILTERS
				{
				mCHAR_FILTERS(); 

				}
				break;
			case 178 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:1239: PARTITIONED
				{
				mPARTITIONED(); 

				}
				break;
			case 179 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:1251: TRANSIENT
				{
				mTRANSIENT(); 

				}
				break;
			case 180 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:1261: PERSISTENT
				{
				mPERSISTENT(); 

				}
				break;
			case 181 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:1272: MATCH
				{
				mMATCH(); 

				}
				break;
			case 182 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:1278: GENERATED
				{
				mGENERATED(); 

				}
				break;
			case 183 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:1288: ALWAYS
				{
				mALWAYS(); 

				}
				break;
			case 184 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:1295: IDENTIFIED
				{
				mIDENTIFIED(); 

				}
				break;
			case 185 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:1306: USER
				{
				mUSER(); 

				}
				break;
			case 186 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:1311: CLUSTER
				{
				mCLUSTER(); 

				}
				break;
			case 187 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:1319: CLUSTERS
				{
				mCLUSTERS(); 

				}
				break;
			case 188 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:1328: MIGRATE
				{
				mMIGRATE(); 

				}
				break;
			case 189 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:1336: NODES
				{
				mNODES(); 

				}
				break;
			case 190 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:1342: DECOMMISSION
				{
				mDECOMMISSION(); 

				}
				break;
			case 191 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:1355: USERS
				{
				mUSERS(); 

				}
				break;
			case 192 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:1361: REMOVE
				{
				mREMOVE(); 

				}
				break;
			case 193 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:1368: WHITELIST
				{
				mWHITELIST(); 

				}
				break;
			case 194 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:1378: GRANT
				{
				mGRANT(); 

				}
				break;
			case 195 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:1384: GRANTS
				{
				mGRANTS(); 

				}
				break;
			case 196 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:1391: REVOKE
				{
				mREVOKE(); 

				}
				break;
			case 197 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:1398: EQ
				{
				mEQ(); 

				}
				break;
			case 198 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:1401: NEQ
				{
				mNEQ(); 

				}
				break;
			case 199 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:1405: LT
				{
				mLT(); 

				}
				break;
			case 200 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:1408: LTE
				{
				mLTE(); 

				}
				break;
			case 201 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:1412: GT
				{
				mGT(); 

				}
				break;
			case 202 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:1415: GTE
				{
				mGTE(); 

				}
				break;
			case 203 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:1419: REGEX_MATCH
				{
				mREGEX_MATCH(); 

				}
				break;
			case 204 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:1431: REGEX_NO_MATCH
				{
				mREGEX_NO_MATCH(); 

				}
				break;
			case 205 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:1446: REGEX_MATCH_CI
				{
				mREGEX_MATCH_CI(); 

				}
				break;
			case 206 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:1461: REGEX_NO_MATCH_CI
				{
				mREGEX_NO_MATCH_CI(); 

				}
				break;
			case 207 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:1479: STRING
				{
				mSTRING(); 

				}
				break;
			case 208 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:1486: INTEGER_VALUE
				{
				mINTEGER_VALUE(); 

				}
				break;
			case 209 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:1500: DECIMAL_VALUE
				{
				mDECIMAL_VALUE(); 

				}
				break;
			case 210 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:1514: IDENT
				{
				mIDENT(); 

				}
				break;
			case 211 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:1520: DIGIT_IDENT
				{
				mDIGIT_IDENT(); 

				}
				break;
			case 212 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:1532: QUOTED_IDENT
				{
				mQUOTED_IDENT(); 

				}
				break;
			case 213 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:1545: BACKQUOTED_IDENT
				{
				mBACKQUOTED_IDENT(); 

				}
				break;
			case 214 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:1562: COLON_IDENT
				{
				mCOLON_IDENT(); 

				}
				break;
			case 215 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:1574: COMMENT
				{
				mCOMMENT(); 

				}
				break;
			case 216 :
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/Statement.g:1:1582: WS
				{
				mWS(); 

				}
				break;

		}
	}


	protected DFA11 dfa11 = new DFA11(this);
	protected DFA26 dfa26 = new DFA26(this);
	static final String DFA11_eotS =
		"\3\uffff\1\7\1\uffff\1\10\1\7\3\uffff";
	static final String DFA11_eofS =
		"\12\uffff";
	static final String DFA11_minS =
		"\2\56\2\60\1\uffff\2\60\3\uffff";
	static final String DFA11_maxS =
		"\1\71\1\105\1\71\1\105\1\uffff\2\105\3\uffff";
	static final String DFA11_acceptS =
		"\4\uffff\1\3\2\uffff\1\1\1\2\1\4";
	static final String DFA11_specialS =
		"\12\uffff}>";
	static final String[] DFA11_transitionS = {
			"\1\2\1\uffff\12\1",
			"\1\3\1\uffff\12\1\13\uffff\1\4",
			"\12\5",
			"\12\6\13\uffff\1\4",
			"",
			"\12\5\13\uffff\1\11",
			"\12\6\13\uffff\1\4",
			"",
			"",
			""
	};

	static final short[] DFA11_eot = DFA.unpackEncodedString(DFA11_eotS);
	static final short[] DFA11_eof = DFA.unpackEncodedString(DFA11_eofS);
	static final char[] DFA11_min = DFA.unpackEncodedStringToUnsignedChars(DFA11_minS);
	static final char[] DFA11_max = DFA.unpackEncodedStringToUnsignedChars(DFA11_maxS);
	static final short[] DFA11_accept = DFA.unpackEncodedString(DFA11_acceptS);
	static final short[] DFA11_special = DFA.unpackEncodedString(DFA11_specialS);
	static final short[][] DFA11_transition;

	static {
		int numStates = DFA11_transitionS.length;
		DFA11_transition = new short[numStates][];
		for (int i=0; i<numStates; i++) {
			DFA11_transition[i] = DFA.unpackEncodedString(DFA11_transitionS[i]);
		}
	}

	protected class DFA11 extends DFA {

		public DFA11(BaseRecognizer recognizer) {
			this.recognizer = recognizer;
			this.decisionNumber = 11;
			this.eot = DFA11_eot;
			this.eof = DFA11_eof;
			this.min = DFA11_min;
			this.max = DFA11_max;
			this.accept = DFA11_accept;
			this.special = DFA11_special;
			this.transition = DFA11_transition;
		}
		@Override
		public String getDescription() {
			return "1431:1: DECIMAL_VALUE : ( ( DIGIT )+ '.' ( DIGIT )* | '.' ( DIGIT )+ | ( DIGIT )+ ( '.' ( DIGIT )* )? EXPONENT | '.' ( DIGIT )+ EXPONENT );";
		}
	}

	static final String DFA26_eotS =
		"\10\uffff\1\64\1\65\1\67\1\uffff\1\75\5\uffff\26\75\1\uffff\1\u009a\1"+
		"\uffff\1\u009d\1\u009f\1\uffff\1\u00a0\1\75\10\uffff\5\75\2\uffff\16\75"+
		"\1\u00c3\6\75\1\u00cd\20\75\1\u00eb\3\75\1\u00f1\2\75\1\u00f5\10\75\1"+
		"\u0104\1\u0105\1\u0106\1\u0107\37\75\3\uffff\1\u0135\5\uffff\1\u00a0\2"+
		"\u00a4\1\uffff\11\75\1\u0142\2\75\1\u0145\13\75\1\u0154\5\75\1\uffff\10"+
		"\75\1\u0162\1\uffff\1\u0163\3\75\1\u0167\1\u0168\1\75\1\u016a\7\75\1\u0173"+
		"\15\75\1\uffff\5\75\1\uffff\1\u0189\2\75\1\uffff\13\75\1\u0199\2\75\4"+
		"\uffff\2\75\1\u019e\11\75\1\u01a9\34\75\1\u01c8\2\75\2\uffff\1\66\12\75"+
		"\1\u01d5\1\uffff\2\75\1\uffff\1\u01d8\3\75\1\u01dc\5\75\1\u01e3\3\75\1"+
		"\uffff\1\75\1\u01e9\3\75\1\u01ed\2\75\1\u01f1\1\u01f2\1\75\1\u01f4\1\u01f5"+
		"\2\uffff\3\75\2\uffff\1\75\1\uffff\3\75\1\u01ff\2\75\1\u0202\1\75\1\uffff"+
		"\1\75\1\u0205\3\75\1\u0209\1\75\1\u020b\6\75\1\u0213\2\75\1\u0216\3\75"+
		"\1\uffff\1\75\1\u021b\1\u021c\2\75\1\u021f\1\75\1\u0221\1\u0222\1\u0223"+
		"\1\u0224\3\75\1\u0229\1\uffff\4\75\1\uffff\1\75\1\u0231\7\75\1\u0239\1"+
		"\uffff\1\u023a\11\75\1\u0244\1\u0245\1\u0246\5\75\1\u024c\1\75\1\u024f"+
		"\10\75\1\u0258\1\uffff\1\u0259\6\75\1\u0261\2\75\1\u0264\1\u0265\1\uffff"+
		"\2\75\1\uffff\2\75\1\u026a\1\uffff\6\75\1\uffff\1\u0271\1\u0272\3\75\1"+
		"\uffff\1\75\1\u0277\1\75\1\uffff\3\75\2\uffff\1\u027e\2\uffff\1\u027f"+
		"\1\u0280\2\75\1\u0283\4\75\1\uffff\2\75\1\uffff\2\75\1\uffff\2\75\1\u028e"+
		"\1\uffff\1\75\1\uffff\1\u0290\1\75\1\u0293\4\75\1\uffff\2\75\1\uffff\1"+
		"\75\1\u029b\1\75\1\u029d\2\uffff\2\75\1\uffff\1\u02a0\4\uffff\1\75\1\u02a2"+
		"\2\75\1\uffff\1\75\1\u02a6\2\75\1\u02a9\1\u02aa\1\75\1\uffff\7\75\2\uffff"+
		"\1\u02b3\3\75\1\u02b7\4\75\3\uffff\1\75\1\u02bd\3\75\1\uffff\1\u02c1\1"+
		"\u02c2\1\uffff\1\75\1\u02c4\4\75\1\u02c9\1\75\2\uffff\7\75\1\uffff\1\u02d2"+
		"\1\u02d3\2\uffff\1\u02d4\1\u02d5\2\75\1\uffff\1\u02d8\1\u02d9\1\u02da"+
		"\2\75\1\u02dd\2\uffff\1\u02de\3\75\1\uffff\5\75\1\u02e8\3\uffff\1\u02e9"+
		"\1\75\1\uffff\4\75\1\u02ef\2\75\1\u02f2\2\75\1\uffff\1\75\1\uffff\1\75"+
		"\1\u02f7\1\uffff\3\75\1\u02fb\3\75\1\uffff\1\u02ff\1\uffff\1\u0300\1\u0301"+
		"\1\uffff\1\75\1\uffff\2\75\1\u0305\1\uffff\2\75\2\uffff\1\u0308\1\75\1"+
		"\u030a\3\75\1\u030e\1\u030f\1\uffff\1\u0310\2\75\1\uffff\2\75\1\u0316"+
		"\2\75\1\uffff\1\u0319\2\75\2\uffff\1\75\1\uffff\1\u031d\3\75\1\uffff\1"+
		"\75\1\u0322\4\75\1\u0327\1\u0328\4\uffff\2\75\3\uffff\1\75\1\u032c\2\uffff"+
		"\11\75\2\uffff\5\75\1\uffff\2\75\1\uffff\1\75\1\u033e\2\75\1\uffff\3\75"+
		"\1\uffff\1\u0344\1\75\1\u0346\3\uffff\1\u0347\1\u0348\1\75\1\uffff\1\u034a"+
		"\1\75\1\uffff\1\u034c\1\uffff\1\u034d\1\u034e\1\u034f\3\uffff\1\u0350"+
		"\1\75\1\u0353\1\75\1\u0355\1\uffff\2\75\1\uffff\1\u035a\2\75\1\uffff\4"+
		"\75\1\uffff\4\75\2\uffff\1\75\1\u0366\1\u0367\1\uffff\1\75\1\u0369\3\75"+
		"\1\u036d\3\75\1\u0371\1\75\1\u0373\1\75\1\u0375\3\75\1\uffff\1\75\1\u037a"+
		"\3\75\1\uffff\1\75\3\uffff\1\75\1\uffff\1\75\5\uffff\2\75\1\uffff\1\u0384"+
		"\1\uffff\1\75\1\u0386\1\75\1\u0388\1\uffff\4\75\1\uffff\1\75\1\u038e\1"+
		"\75\1\u0390\1\75\1\u0392\2\uffff\1\u0393\1\uffff\1\u0394\1\u0395\1\75"+
		"\1\uffff\1\u0397\1\u0398\1\75\1\uffff\1\u039a\1\uffff\1\75\1\uffff\1\75"+
		"\1\u039d\1\u039e\1\u039f\1\uffff\1\u03a0\1\u03a1\1\u03a2\1\u03a3\1\u03a4"+
		"\4\75\1\uffff\1\75\1\uffff\1\u03aa\1\uffff\1\75\1\u03ac\1\u03af\1\u03b0"+
		"\1\75\1\uffff\1\u03b2\1\uffff\1\u03b3\4\uffff\1\75\2\uffff\1\75\1\uffff"+
		"\2\75\10\uffff\1\u03b8\3\75\1\u03bc\1\uffff\1\75\1\uffff\1\u03be\1\75"+
		"\2\uffff\1\u03c0\2\uffff\1\75\1\u03c2\1\u03c3\1\75\1\uffff\3\75\1\uffff"+
		"\1\75\1\uffff\1\u03c9\1\uffff\1\75\2\uffff\1\u03cb\1\u03cc\1\u03cd\1\u03cf"+
		"\1\u03d0\1\uffff\1\u03d1\3\uffff\1\75\3\uffff\3\75\1\u03d6\1\uffff";
	static final String DFA26_eofS =
		"\u03d7\uffff";
	static final String DFA26_minS =
		"\1\11\7\uffff\1\55\1\60\1\52\1\uffff\1\60\5\uffff\26\60\1\uffff\3\75\1"+
		"\52\1\uffff\1\56\1\60\10\uffff\5\60\2\uffff\131\60\3\uffff\1\52\5\uffff"+
		"\1\56\1\53\1\60\1\uffff\36\60\1\uffff\11\60\1\uffff\35\60\1\uffff\5\60"+
		"\1\uffff\3\60\1\uffff\16\60\4\uffff\54\60\2\uffff\14\60\1\uffff\2\60\1"+
		"\uffff\16\60\1\uffff\15\60\2\uffff\3\60\2\uffff\1\60\1\uffff\10\60\1\uffff"+
		"\25\60\1\uffff\17\60\1\uffff\4\60\1\uffff\12\60\1\uffff\36\60\1\uffff"+
		"\14\60\1\uffff\2\60\1\uffff\3\60\1\uffff\6\60\1\uffff\5\60\1\uffff\3\60"+
		"\1\uffff\3\60\2\uffff\1\60\2\uffff\11\60\1\uffff\2\60\1\uffff\2\60\1\uffff"+
		"\3\60\1\uffff\1\60\1\uffff\7\60\1\uffff\2\60\1\uffff\4\60\2\uffff\2\60"+
		"\1\uffff\1\60\4\uffff\4\60\1\uffff\7\60\1\uffff\7\60\2\uffff\11\60\3\uffff"+
		"\5\60\1\uffff\2\60\1\uffff\10\60\2\uffff\7\60\1\uffff\2\60\2\uffff\4\60"+
		"\1\uffff\6\60\2\uffff\4\60\1\uffff\6\60\3\uffff\2\60\1\uffff\12\60\1\uffff"+
		"\1\60\1\uffff\2\60\1\uffff\7\60\1\uffff\1\60\1\uffff\2\60\1\uffff\1\60"+
		"\1\uffff\3\60\1\uffff\2\60\2\uffff\10\60\1\uffff\3\60\1\uffff\5\60\1\uffff"+
		"\3\60\2\uffff\1\60\1\uffff\4\60\1\uffff\10\60\4\uffff\2\60\3\uffff\2\60"+
		"\2\uffff\11\60\2\uffff\5\60\1\uffff\2\60\1\uffff\4\60\1\uffff\3\60\1\uffff"+
		"\3\60\3\uffff\3\60\1\uffff\2\60\1\uffff\1\60\1\uffff\3\60\3\uffff\5\60"+
		"\1\uffff\2\60\1\uffff\3\60\1\uffff\2\60\1\40\1\60\1\uffff\4\60\2\uffff"+
		"\3\60\1\uffff\21\60\1\uffff\5\60\1\uffff\1\60\3\uffff\1\60\1\uffff\1\60"+
		"\5\uffff\2\60\1\uffff\1\60\1\uffff\4\60\1\uffff\4\60\1\uffff\6\60\2\uffff"+
		"\1\60\1\uffff\3\60\1\uffff\3\60\1\uffff\1\60\1\uffff\1\60\1\uffff\4\60"+
		"\1\uffff\11\60\1\uffff\1\60\1\uffff\1\60\1\uffff\5\60\1\uffff\1\60\1\uffff"+
		"\1\60\4\uffff\1\60\2\uffff\1\60\1\uffff\2\60\10\uffff\5\60\1\uffff\1\60"+
		"\1\uffff\2\60\2\uffff\1\60\2\uffff\4\60\1\uffff\3\60\1\uffff\1\60\1\uffff"+
		"\1\60\1\uffff\1\60\2\uffff\5\60\1\uffff\1\60\3\uffff\1\60\3\uffff\4\60"+
		"\1\uffff";
	static final String DFA26_maxS =
		"\1\176\7\uffff\1\55\1\71\1\52\1\uffff\1\137\5\uffff\26\137\1\uffff\1\76"+
		"\1\176\1\75\1\52\1\uffff\2\137\10\uffff\5\137\2\uffff\131\137\3\uffff"+
		"\1\52\5\uffff\3\137\1\uffff\36\137\1\uffff\11\137\1\uffff\35\137\1\uffff"+
		"\5\137\1\uffff\3\137\1\uffff\16\137\4\uffff\54\137\2\uffff\14\137\1\uffff"+
		"\2\137\1\uffff\16\137\1\uffff\15\137\2\uffff\3\137\2\uffff\1\137\1\uffff"+
		"\10\137\1\uffff\25\137\1\uffff\17\137\1\uffff\4\137\1\uffff\12\137\1\uffff"+
		"\36\137\1\uffff\14\137\1\uffff\2\137\1\uffff\3\137\1\uffff\6\137\1\uffff"+
		"\5\137\1\uffff\3\137\1\uffff\3\137\2\uffff\1\137\2\uffff\11\137\1\uffff"+
		"\2\137\1\uffff\2\137\1\uffff\3\137\1\uffff\1\137\1\uffff\7\137\1\uffff"+
		"\2\137\1\uffff\4\137\2\uffff\2\137\1\uffff\1\137\4\uffff\4\137\1\uffff"+
		"\7\137\1\uffff\7\137\2\uffff\11\137\3\uffff\5\137\1\uffff\2\137\1\uffff"+
		"\10\137\2\uffff\7\137\1\uffff\2\137\2\uffff\4\137\1\uffff\6\137\2\uffff"+
		"\4\137\1\uffff\6\137\3\uffff\2\137\1\uffff\12\137\1\uffff\1\137\1\uffff"+
		"\2\137\1\uffff\7\137\1\uffff\1\137\1\uffff\2\137\1\uffff\1\137\1\uffff"+
		"\3\137\1\uffff\2\137\2\uffff\10\137\1\uffff\3\137\1\uffff\5\137\1\uffff"+
		"\3\137\2\uffff\1\137\1\uffff\4\137\1\uffff\10\137\4\uffff\2\137\3\uffff"+
		"\2\137\2\uffff\11\137\2\uffff\5\137\1\uffff\2\137\1\uffff\4\137\1\uffff"+
		"\3\137\1\uffff\3\137\3\uffff\3\137\1\uffff\2\137\1\uffff\1\137\1\uffff"+
		"\3\137\3\uffff\5\137\1\uffff\2\137\1\uffff\3\137\1\uffff\4\137\1\uffff"+
		"\4\137\2\uffff\3\137\1\uffff\21\137\1\uffff\5\137\1\uffff\1\137\3\uffff"+
		"\1\137\1\uffff\1\137\5\uffff\2\137\1\uffff\1\137\1\uffff\4\137\1\uffff"+
		"\4\137\1\uffff\6\137\2\uffff\1\137\1\uffff\3\137\1\uffff\3\137\1\uffff"+
		"\1\137\1\uffff\1\137\1\uffff\4\137\1\uffff\11\137\1\uffff\1\137\1\uffff"+
		"\1\137\1\uffff\5\137\1\uffff\1\137\1\uffff\1\137\4\uffff\1\137\2\uffff"+
		"\1\137\1\uffff\2\137\10\uffff\5\137\1\uffff\1\137\1\uffff\2\137\2\uffff"+
		"\1\137\2\uffff\4\137\1\uffff\3\137\1\uffff\1\137\1\uffff\1\137\1\uffff"+
		"\1\137\2\uffff\5\137\1\uffff\1\137\3\uffff\1\137\3\uffff\4\137\1\uffff";
	static final String DFA26_acceptS =
		"\1\uffff\1\1\1\2\1\3\1\4\1\5\1\6\1\7\3\uffff\1\13\1\uffff\1\16\1\17\1"+
		"\20\1\21\1\22\26\uffff\1\u00c5\4\uffff\1\u00cf\2\uffff\1\u00d4\1\u00d5"+
		"\1\u00d8\1\u00d7\1\10\1\11\1\u00d1\1\12\5\uffff\1\u00d2\1\u00d6\131\uffff"+
		"\1\u00c6\1\u00c8\1\u00c7\1\uffff\1\u00ca\1\u00c9\1\u00cd\1\u00cb\1\u00d0"+
		"\3\uffff\1\u00d3\36\uffff\1\25\11\uffff\1\26\35\uffff\1\36\5\uffff\1\43"+
		"\3\uffff\1\123\16\uffff\1\45\1\52\1\111\1\162\54\uffff\1\u00ce\1\u00cc"+
		"\14\uffff\1\134\2\uffff\1\u009f\16\uffff\1\65\15\uffff\1\62\1\27\3\uffff"+
		"\1\30\1\44\1\uffff\1\147\10\uffff\1\72\25\uffff\1\u00a5\17\uffff\1\155"+
		"\4\uffff\1\46\12\uffff\1\110\36\uffff\1\u009d\14\uffff\1\127\2\uffff\1"+
		"\31\3\uffff\1\u0083\6\uffff\1\24\5\uffff\1\120\3\uffff\1\54\3\uffff\1"+
		"\67\1\106\1\uffff\1\174\1\175\11\uffff\1\63\2\uffff\1\66\2\uffff\1\u008f"+
		"\3\uffff\1\105\1\uffff\1\135\7\uffff\1\152\2\uffff\1\140\4\uffff\1\146"+
		"\1\124\2\uffff\1\73\1\uffff\1\51\1\60\1\116\1\156\4\uffff\1\u0099\7\uffff"+
		"\1\53\7\uffff\1\107\1\70\11\uffff\1\u00a1\1\104\1\u0081\5\uffff\1\112"+
		"\2\uffff\1\u00b9\10\uffff\1\145\1\u008c\7\uffff\1\u00a0\2\uffff\1\117"+
		"\1\126\4\uffff\1\153\6\uffff\1\55\1\57\4\uffff\1\157\6\uffff\1\141\1\144"+
		"\1\u0090\2\uffff\1\u00ac\12\uffff\1\34\1\uffff\1\35\2\uffff\1\u00c2\7"+
		"\uffff\1\37\1\uffff\1\114\2\uffff\1\41\1\uffff\1\115\3\uffff\1\u00a8\2"+
		"\uffff\1\u00bd\1\56\10\uffff\1\71\3\uffff\1\u00b5\5\uffff\1\113\3\uffff"+
		"\1\122\1\u00bf\1\uffff\1\u0091\4\uffff\1\u00a7\10\uffff\1\u00c0\1\u00c4"+
		"\1\23\1\75\2\uffff\1\u00a3\1\164\1\u00aa\2\uffff\1\u0094\1\173\11\uffff"+
		"\1\u0084\1\u00b7\5\uffff\1\u009b\2\uffff\1\160\4\uffff\1\u00c3\3\uffff"+
		"\1\167\3\uffff\1\42\1\163\1\40\3\uffff\1\u0098\2\uffff\1\103\1\uffff\1"+
		"\47\3\uffff\1\u0092\1\61\1\74\5\uffff\1\150\2\uffff\1\137\3\uffff\1\u009c"+
		"\4\uffff\1\u009a\4\uffff\1\u008d\1\u008e\3\uffff\1\u0085\21\uffff\1\u00a9"+
		"\5\uffff\1\50\1\uffff\1\151\1\177\1\154\1\uffff\1\u00ab\1\uffff\1\121"+
		"\1\101\1\u00ae\1\172\1\u00bc\2\uffff\1\133\1\uffff\1\u0088\4\uffff\1\u00ba"+
		"\4\uffff\1\u00a4\6\uffff\1\143\1\u0097\1\uffff\1\u00a6\3\uffff\1\u0082"+
		"\3\uffff\1\u00ad\1\uffff\1\33\1\uffff\1\171\4\uffff\1\176\11\uffff\1\102"+
		"\1\uffff\1\u0087\1\uffff\1\u00bb\5\uffff\1\14\1\uffff\1\136\1\uffff\1"+
		"\64\1\132\1\u008a\1\u00af\1\uffff\1\u00b3\1\161\1\uffff\1\32\2\uffff\1"+
		"\u0086\1\u009e\1\u00c1\1\165\1\166\1\u00b6\1\u0095\1\u0093\5\uffff\1\u00a2"+
		"\1\uffff\1\130\2\uffff\1\125\1\131\1\uffff\1\15\1\142\4\uffff\1\u00b8"+
		"\3\uffff\1\170\1\uffff\1\u0089\1\uffff\1\u00b4\1\uffff\1\u0096\1\u0080"+
		"\5\uffff\1\u00b2\1\uffff\1\u00be\1\u008b\1\76\1\uffff\1\77\1\u00b1\1\u00b0"+
		"\4\uffff\1\100";
	static final String DFA26_specialS =
		"\u03d7\uffff}>";
	static final String[] DFA26_transitionS = {
			"\2\62\2\uffff\1\62\22\uffff\1\62\1\52\1\60\1\uffff\1\1\1\2\1\uffff\1"+
			"\55\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\12\12\56\2\uffff\1\51\1\50\1\53\1"+
			"\13\1\uffff\1\25\1\31\1\42\1\26\1\37\1\23\1\30\1\33\1\35\1\43\1\46\1"+
			"\34\1\41\1\36\1\32\1\45\1\57\1\14\1\22\1\24\1\44\1\47\1\27\1\57\1\40"+
			"\1\57\1\15\1\uffff\1\16\1\uffff\1\57\1\61\32\uffff\1\17\1\20\1\21\1\54",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"\1\63",
			"\12\66",
			"\1\63",
			"",
			"\12\74\1\76\6\uffff\1\72\3\74\1\70\3\74\1\71\5\74\1\73\13\74\4\uffff"+
			"\1\74",
			"",
			"",
			"",
			"",
			"",
			"\12\74\1\76\6\uffff\2\74\1\105\1\74\1\77\2\74\1\103\5\74\1\102\1\100"+
			"\4\74\1\104\1\101\3\74\1\106\1\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\1\110\7\74\1\111\2\74\1\114\2\74\1\112\2\74\1\107"+
			"\2\74\1\113\5\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\1\121\3\74\1\123\2\74\1\120\1\117\5\74\1\115\2\74"+
			"\1\116\6\74\1\122\1\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\3\74\1\127\7\74\1\125\1\74\1\126\3\74\1\130\1\124"+
			"\7\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\1\133\3\74\1\132\3\74\1\131\5\74\1\134\2\74\1\135"+
			"\2\74\1\136\3\74\1\137\1\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\7\74\1\140\1\141\21\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\4\74\1\143\6\74\1\144\5\74\1\142\10\74\4\uffff\1"+
			"\74",
			"\12\74\1\76\6\uffff\4\74\1\146\6\74\1\147\2\74\1\150\11\74\1\145\1\74"+
			"\4\uffff\1\74",
			"\12\74\1\76\6\uffff\1\74\1\156\3\74\1\152\7\74\1\154\3\74\1\151\2\74"+
			"\1\153\1\155\4\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\1\157\15\74\1\160\13\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\1\162\3\74\1\163\3\74\1\161\5\74\1\164\13\74\4\uffff"+
			"\1\74",
			"\12\74\1\76\6\uffff\3\74\1\172\1\74\1\167\1\171\6\74\1\165\1\74\1\170"+
			"\2\74\1\166\7\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\1\175\15\74\1\173\5\74\1\174\5\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\13\74\1\u0080\1\74\1\u0081\4\74\1\177\4\74\1\176"+
			"\2\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\4\74\1\u0082\25\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\1\u0085\7\74\1\u0084\5\74\1\u0083\13\74\4\uffff"+
			"\1\74",
			"\12\74\1\76\6\uffff\1\u0088\6\74\1\u008b\3\74\1\u008a\2\74\1\u0087\2"+
			"\74\1\u0089\2\74\1\u0086\5\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\16\74\1\u008c\13\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\15\74\1\u008e\1\74\1\u008f\2\74\1\u008d\7\74\4\uffff"+
			"\1\74",
			"\12\74\1\76\6\uffff\1\u0090\3\74\1\u0093\6\74\1\u0092\5\74\1\u0091\10"+
			"\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\4\74\1\u0095\3\74\1\u0094\21\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\1\u0097\7\74\1\u0096\21\74\4\uffff\1\74",
			"",
			"\1\u0099\1\u0098",
			"\1\u0098\100\uffff\1\u009b",
			"\1\u009c",
			"\1\u009e",
			"",
			"\1\66\1\uffff\12\u00a1\1\76\5\uffff\1\u00a4\4\u00a3\1\u00a2\25\u00a3"+
			"\4\uffff\1\u00a3",
			"\12\74\1\76\6\uffff\32\74\4\uffff\1\74",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"\12\74\1\76\6\uffff\1\u00a5\1\74\1\u00a6\2\74\1\u00a8\6\74\1\u00aa\2"+
			"\74\1\u00a7\2\74\1\u00a9\2\74\1\u00ab\4\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\6\74\1\u00ac\23\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\15\74\1\u00ad\14\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\26\74\1\u00ae\3\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\32\74\4\uffff\1\74",
			"",
			"",
			"\12\74\1\76\6\uffff\2\74\1\u00b0\10\74\1\u00af\7\74\1\u00b1\6\74\4\uffff"+
			"\1\74",
			"\12\74\1\76\6\uffff\14\74\1\u00b2\15\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\1\74\1\u00b3\30\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\1\u00b4\31\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\1\u00b6\15\74\1\u00b5\13\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\21\74\1\u00b7\10\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\7\74\1\u00b8\22\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\22\74\1\u00b9\7\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\16\74\1\u00ba\13\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\13\74\1\u00bb\16\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\21\74\1\u00bc\10\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\13\74\1\u00be\5\74\1\u00bd\10\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\13\74\1\u00bf\1\74\1\u00c0\14\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\16\74\1\u00c1\13\74\4\uffff\1\74",
			"\12\74\1\76\5\uffff\1\75\12\74\1\u00c2\17\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\1\u00c6\23\74\1\u00c4\3\74\1\u00c5\1\74\4\uffff"+
			"\1\74",
			"\12\74\1\76\6\uffff\14\74\1\u00c7\15\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\4\74\1\u00c8\25\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\1\74\1\u00c9\30\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\17\74\1\u00ca\12\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\27\74\1\u00cb\2\74\4\uffff\1\74",
			"\12\74\1\76\5\uffff\1\75\2\74\1\u00cc\27\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\10\74\1\u00d0\2\74\1\u00ce\7\74\1\u00cf\2\74\1\u00d1"+
			"\3\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\1\u00d4\2\74\1\u00d3\24\74\1\u00d2\1\74\4\uffff"+
			"\1\74",
			"\12\74\1\76\6\uffff\3\74\1\u00d5\26\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\21\74\1\u00d6\10\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\21\74\1\u00d7\1\u00d8\7\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\2\74\1\u00db\10\74\1\u00da\6\74\1\u00d9\7\74\4\uffff"+
			"\1\74",
			"\12\74\1\76\6\uffff\23\74\1\u00dc\4\74\1\u00dd\1\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\24\74\1\u00de\5\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\16\74\1\u00df\13\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\17\74\1\u00e0\12\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\15\74\1\u00e1\14\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\4\74\1\u00e2\3\74\1\u00e3\21\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\23\74\1\u00e4\6\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\1\u00e6\15\74\1\u00e5\13\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\15\74\1\u00e8\1\u00e7\13\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\16\74\1\u00e9\13\74\4\uffff\1\74",
			"\12\74\1\76\5\uffff\1\75\23\74\1\u00ea\6\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\21\74\1\u00ed\1\74\1\u00ec\6\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\16\74\1\u00ee\13\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\16\74\1\u00ef\13\74\4\uffff\1\74",
			"\12\74\1\76\5\uffff\1\75\3\74\1\u00f0\26\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\5\74\1\u00f2\24\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\23\74\1\u00f3\6\74\4\uffff\1\74",
			"\12\74\1\76\5\uffff\1\75\13\74\1\u00f4\16\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\4\74\1\u00f6\25\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\11\74\1\u00f7\20\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\25\74\1\u00f8\4\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\24\74\1\u00f9\5\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\12\74\1\u00fb\1\74\1\u00fa\15\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\22\74\1\u00fc\7\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\5\74\1\u00fd\24\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\6\74\1\u00ff\6\74\1\u00fe\14\74\4\uffff\1\74",
			"\12\74\1\76\5\uffff\1\75\3\74\1\u0103\11\74\1\u0100\4\74\1\u0102\1\u0101"+
			"\6\74\4\uffff\1\74",
			"\12\74\1\76\5\uffff\1\75\32\74\4\uffff\1\74",
			"\12\74\1\76\5\uffff\1\75\32\74\4\uffff\1\74",
			"\12\74\1\76\5\uffff\1\75\32\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\15\74\1\u0108\14\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\4\74\1\u0109\25\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\3\74\1\u010b\17\74\1\u010a\6\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\13\74\1\u010c\16\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\23\74\1\u010d\6\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\2\74\1\u0111\5\74\1\u010e\6\74\1\u0110\3\74\1\u010f"+
			"\6\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\2\74\1\u0112\27\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\22\74\1\u0113\7\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\3\74\1\u0114\26\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\1\u0115\31\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\15\74\1\u0116\14\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\6\74\1\u0118\6\74\1\u0117\14\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\23\74\1\u0119\6\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\21\74\1\u011a\10\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\1\u011b\12\74\1\u011c\1\74\1\u011d\1\74\1\u011e"+
			"\12\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\22\74\1\u011f\1\u0120\6\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\4\74\1\u0122\11\74\1\u0121\13\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\24\74\1\u0123\5\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\1\u0124\31\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\10\74\1\u0125\21\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\4\74\1\u0127\3\74\1\u0126\21\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\1\74\1\u0128\6\74\1\u0129\21\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\3\74\1\u012a\26\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\21\74\1\u012b\10\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\4\74\1\u012c\3\74\1\u012d\21\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\1\u012e\31\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\21\74\1\u012f\10\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\13\74\1\u0130\16\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\30\74\1\u0131\1\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\4\74\1\u0132\25\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\13\74\1\u0133\16\74\4\uffff\1\74",
			"",
			"",
			"",
			"\1\u0134",
			"",
			"",
			"",
			"",
			"",
			"\1\66\1\uffff\12\u00a1\1\76\5\uffff\1\u00a4\4\u00a3\1\u00a2\25\u00a3"+
			"\4\uffff\1\u00a3",
			"\1\66\1\uffff\1\66\2\uffff\12\u0136\1\76\6\uffff\32\u00a3\4\uffff\1"+
			"\u00a3",
			"\12\u00a3\1\76\6\uffff\32\u00a3\4\uffff\1\u00a3",
			"",
			"\12\74\1\76\6\uffff\3\74\1\u0137\26\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\24\74\1\u0138\5\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\16\74\1\u0139\13\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\21\74\1\u013a\10\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\4\74\1\u013c\16\74\1\u013b\6\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\16\74\1\u013d\13\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\16\74\1\u013e\13\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\7\74\1\u013f\22\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\6\74\1\u0140\23\74\4\uffff\1\74",
			"\12\74\1\76\5\uffff\1\75\22\74\1\u0141\7\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\4\74\1\u0143\25\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\16\74\1\u0144\13\74\4\uffff\1\74",
			"\12\74\1\76\5\uffff\1\75\32\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\4\74\1\u0146\25\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\22\74\1\u0147\7\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\17\74\1\u0148\12\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\21\74\1\u0149\4\74\1\u014a\3\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\21\74\1\u014b\10\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\1\u014d\7\74\1\u014c\21\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\4\74\1\u014e\25\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\23\74\1\u014f\6\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\14\74\1\u0150\15\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\22\74\1\u0151\7\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\22\74\1\u0152\7\74\4\uffff\1\74",
			"\12\74\1\76\5\uffff\1\75\14\74\1\u0153\15\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\13\74\1\u0155\16\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\13\74\1\u0156\16\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\2\74\1\u0157\27\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\1\u0158\31\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\4\74\1\u0159\25\74\4\uffff\1\74",
			"",
			"\12\74\1\76\6\uffff\4\74\1\u015a\25\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\32\74\4\uffff\1\u015b",
			"\12\74\1\76\6\uffff\15\74\1\u015c\14\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\4\74\1\u015d\25\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\15\74\1\u015e\14\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\13\74\1\u015f\16\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\4\74\1\u0160\25\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\23\74\1\u0161\6\74\4\uffff\1\74",
			"\12\74\1\76\5\uffff\1\75\32\74\4\uffff\1\74",
			"",
			"\12\74\1\76\5\uffff\1\75\32\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\4\74\1\u0164\25\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\1\u0165\31\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\1\u0166\31\74\4\uffff\1\74",
			"\12\74\1\76\5\uffff\1\75\32\74\4\uffff\1\74",
			"\12\74\1\76\5\uffff\1\75\32\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\13\74\1\u0169\16\74\4\uffff\1\74",
			"\12\74\1\76\5\uffff\1\75\32\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\1\u016b\31\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\4\74\1\u016c\25\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\23\74\1\u016d\6\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\2\74\1\u016e\27\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\4\74\1\u016f\25\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\16\74\1\u0170\13\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\1\u0172\3\74\1\u0171\25\74\4\uffff\1\74",
			"\12\74\1\76\5\uffff\1\75\32\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\1\74\1\u0174\30\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\17\74\1\u0175\12\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\13\74\1\u0176\16\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\1\u0177\31\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\15\74\1\u0179\3\74\1\u0178\10\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\23\74\1\u017a\6\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\7\74\1\u017b\22\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\24\74\1\u017c\5\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\15\74\1\u017e\1\74\1\u017d\12\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\32\74\4\uffff\1\u017f",
			"\12\74\1\76\6\uffff\4\74\1\u0180\25\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\1\74\1\u0181\30\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\4\74\1\u0182\25\74\4\uffff\1\74",
			"",
			"\12\74\1\76\6\uffff\26\74\1\u0183\3\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\15\74\1\u0184\14\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\1\74\1\u0185\30\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\13\74\1\u0186\16\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\4\74\1\u0187\25\74\4\uffff\1\74",
			"",
			"\12\74\1\76\5\uffff\1\75\22\74\1\u0188\7\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\4\74\1\u018a\25\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\30\74\1\u018b\1\74\4\uffff\1\74",
			"",
			"\12\74\1\76\6\uffff\21\74\1\u018c\10\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\4\74\1\u018d\25\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\10\74\1\u018e\21\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\21\74\1\u018f\10\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\10\74\1\u0190\21\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\4\74\1\u0191\25\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\23\74\1\u0192\6\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\23\74\1\u0193\6\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\6\74\1\u0194\23\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\10\74\1\u0195\21\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\4\74\1\u0196\25\74\4\uffff\1\74",
			"\12\74\1\76\5\uffff\1\75\4\74\1\u0197\11\74\1\u0198\13\74\4\uffff\1"+
			"\74",
			"\12\74\1\76\6\uffff\4\74\1\u019a\25\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\4\74\1\u019b\25\74\4\uffff\1\74",
			"",
			"",
			"",
			"",
			"\12\74\1\76\6\uffff\16\74\1\u019c\13\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\15\74\1\u019d\14\74\4\uffff\1\74",
			"\12\74\1\76\5\uffff\1\75\32\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\4\74\1\u019f\25\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\13\74\1\u01a0\16\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\24\74\1\u01a1\5\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\22\74\1\u01a2\7\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\4\74\1\u01a4\14\74\1\u01a3\10\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\13\74\1\u01a5\16\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\4\74\1\u01a6\25\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\1\u01a7\31\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\4\74\1\u01a8\25\74\4\uffff\1\74",
			"\12\74\1\76\5\uffff\1\75\32\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\21\74\1\u01aa\10\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\23\74\1\u01ab\6\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\24\74\1\u01ac\5\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\21\74\1\u01ad\10\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\2\74\1\u01af\1\74\1\u01ae\25\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\21\74\1\u01b0\10\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\13\74\1\u01b1\16\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\24\74\1\u01b2\5\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\22\74\1\u01b3\7\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\30\74\1\u01b4\1\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\4\74\1\u01b5\16\74\1\u01b6\6\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\1\u01b7\31\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\22\74\1\u01b8\7\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\1\u01b9\31\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\22\74\1\u01ba\7\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\21\74\1\u01bb\10\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\15\74\1\u01bc\14\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\15\74\1\u01bd\14\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\21\74\1\u01be\10\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\16\74\1\u01bf\13\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\16\74\1\u01c0\13\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\1\u01c1\31\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\23\74\1\u01c2\6\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\2\74\1\u01c3\27\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\14\74\1\u01c4\15\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\10\74\1\u01c5\21\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\22\74\1\u01c6\7\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\13\74\1\u01c7\16\74\4\uffff\1\74",
			"\12\74\1\76\5\uffff\1\75\32\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\26\74\1\u01c9\3\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\24\74\1\u01ca\5\74\4\uffff\1\74",
			"",
			"",
			"\12\u0136\1\76\5\uffff\1\u00a4\32\u00a3\4\uffff\1\u00a3",
			"\12\74\1\76\6\uffff\32\74\4\uffff\1\u01cb",
			"\12\74\1\76\6\uffff\21\74\1\u01cc\10\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\22\74\1\u01cd\7\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\4\74\1\u01ce\25\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\16\74\1\u01cf\13\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\23\74\1\u01d0\6\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\25\74\1\u01d1\4\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\12\74\1\u01d2\17\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\23\74\1\u01d3\6\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\4\74\1\u01d4\25\74\4\uffff\1\74",
			"\12\74\1\76\5\uffff\1\75\32\74\4\uffff\1\74",
			"",
			"\12\74\1\76\6\uffff\2\74\1\u01d6\27\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\15\74\1\u01d7\14\74\4\uffff\1\74",
			"",
			"\12\74\1\76\5\uffff\1\75\32\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\23\74\1\u01d9\6\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\22\74\1\u01da\7\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\23\74\1\u01db\6\74\4\uffff\1\74",
			"\12\74\1\76\5\uffff\1\75\32\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\3\74\1\u01dd\26\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\2\74\1\u01df\12\74\1\u01de\14\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\23\74\1\u01e0\6\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\14\74\1\u01e1\15\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\4\74\1\u01e2\25\74\4\uffff\1\74",
			"\12\74\1\76\5\uffff\1\75\32\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\4\74\1\u01e4\25\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\23\74\1\u01e5\6\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\1\u01e6\31\74\4\uffff\1\74",
			"",
			"\12\74\1\76\6\uffff\16\74\1\u01e7\13\74\4\uffff\1\74",
			"\12\74\1\76\5\uffff\1\75\23\74\1\u01e8\6\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\23\74\1\u01ea\6\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\23\74\1\u01eb\6\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\15\74\1\u01ec\14\74\4\uffff\1\74",
			"\12\74\1\76\5\uffff\1\75\32\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\2\74\1\u01ee\27\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\22\74\1\u01ef\7\74\4\uffff\1\74",
			"\12\74\1\76\5\uffff\1\75\22\74\1\u01f0\7\74\4\uffff\1\74",
			"\12\74\1\76\5\uffff\1\75\32\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\4\74\1\u01f3\25\74\4\uffff\1\74",
			"\12\74\1\76\5\uffff\1\75\32\74\4\uffff\1\74",
			"\12\74\1\76\5\uffff\1\75\32\74\4\uffff\1\74",
			"",
			"",
			"\12\74\1\76\6\uffff\21\74\1\u01f6\10\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\22\74\1\u01f7\7\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\30\74\1\u01f8\1\74\4\uffff\1\74",
			"",
			"",
			"\12\74\1\76\6\uffff\30\74\1\u01f9\1\74\4\uffff\1\74",
			"",
			"\12\74\1\76\6\uffff\30\74\1\u01fa\1\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\2\74\1\u01fb\27\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\10\74\1\u01fc\10\74\1\u01fd\10\74\4\uffff\1\74",
			"\12\74\1\76\5\uffff\1\75\21\74\1\u01fe\10\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\23\74\1\u0200\6\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\14\74\1\u0201\15\74\4\uffff\1\74",
			"\12\74\1\76\5\uffff\1\75\32\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\1\74\1\u0203\30\74\4\uffff\1\74",
			"",
			"\12\74\1\76\6\uffff\13\74\1\u0204\16\74\4\uffff\1\74",
			"\12\74\1\76\5\uffff\1\75\32\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\10\74\1\u0206\21\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\14\74\1\u0207\15\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\4\74\1\u0208\25\74\4\uffff\1\74",
			"\12\74\1\76\5\uffff\1\75\32\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\4\74\1\u020a\25\74\4\uffff\1\74",
			"\12\74\1\76\5\uffff\1\75\32\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\17\74\1\u020c\12\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\7\74\1\u020d\22\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\23\74\1\u020e\6\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\17\74\1\u020f\2\74\1\u0210\7\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\21\74\1\u0211\10\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\1\u0212\31\74\4\uffff\1\74",
			"\12\74\1\76\5\uffff\1\75\32\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\4\74\1\u0214\25\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\16\74\1\u0215\13\74\4\uffff\1\74",
			"\12\74\1\76\5\uffff\1\75\32\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\4\74\1\u0217\25\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\21\74\1\u0218\10\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\4\74\1\u0219\25\74\4\uffff\1\74",
			"",
			"\12\74\1\76\6\uffff\21\74\1\u021a\10\74\4\uffff\1\74",
			"\12\74\1\76\5\uffff\1\75\32\74\4\uffff\1\74",
			"\12\74\1\76\5\uffff\1\75\32\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\2\74\1\u021d\27\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\15\74\1\u021e\14\74\4\uffff\1\74",
			"\12\74\1\76\5\uffff\1\75\32\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\23\74\1\u0220\6\74\4\uffff\1\74",
			"\12\74\1\76\5\uffff\1\75\32\74\4\uffff\1\74",
			"\12\74\1\76\5\uffff\1\75\32\74\4\uffff\1\74",
			"\12\74\1\76\5\uffff\1\75\32\74\4\uffff\1\74",
			"\12\74\1\76\5\uffff\1\75\32\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\2\74\1\u0225\27\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\21\74\1\u0226\10\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\6\74\1\u0227\12\74\1\u0228\10\74\4\uffff\1\74",
			"\12\74\1\76\5\uffff\1\75\32\74\4\uffff\1\74",
			"",
			"\12\74\1\76\6\uffff\21\74\1\u022a\10\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\27\74\1\u022b\2\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\21\74\1\u022c\10\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\23\74\1\u022d\6\74\4\uffff\1\74",
			"",
			"\12\74\1\76\6\uffff\22\74\1\u022e\7\74\4\uffff\1\74",
			"\12\74\1\76\5\uffff\1\75\10\74\1\u0230\11\74\1\u022f\7\74\4\uffff\1"+
			"\74",
			"\12\74\1\76\6\uffff\21\74\1\u0232\10\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\23\74\1\u0233\6\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\1\u0234\31\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\15\74\1\u0235\14\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\1\u0236\31\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\17\74\1\u0237\12\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\17\74\1\u0238\12\74\4\uffff\1\74",
			"\12\74\1\76\5\uffff\1\75\32\74\4\uffff\1\74",
			"",
			"\12\74\1\76\5\uffff\1\75\32\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\7\74\1\u023b\22\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\23\74\1\u023c\6\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\1\u023d\31\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\21\74\1\u023e\10\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\7\74\1\u023f\22\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\4\74\1\u0240\25\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\4\74\1\u0241\25\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\14\74\1\u0242\15\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\23\74\1\u0243\6\74\4\uffff\1\74",
			"\12\74\1\76\5\uffff\1\75\32\74\4\uffff\1\74",
			"\12\74\1\76\5\uffff\1\75\32\74\4\uffff\1\74",
			"\12\74\1\76\5\uffff\1\75\32\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\13\74\1\u0247\16\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\22\74\1\u0248\7\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\23\74\1\u0249\6\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\23\74\1\u024a\6\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\32\74\4\uffff\1\u024b",
			"\12\74\1\76\5\uffff\1\75\32\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\6\74\1\u024d\23\74\4\uffff\1\74",
			"\12\74\1\76\5\uffff\1\75\22\74\1\u024e\7\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\24\74\1\u0250\5\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\15\74\1\u0251\14\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\23\74\1\u0252\6\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\10\74\1\u0253\21\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\4\74\1\u0254\25\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\1\u0255\31\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\15\74\1\u0256\14\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\10\74\1\u0257\21\74\4\uffff\1\74",
			"\12\74\1\76\5\uffff\1\75\32\74\4\uffff\1\74",
			"",
			"\12\74\1\76\5\uffff\1\75\32\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\4\74\1\u025a\25\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\16\74\1\u025b\7\74\1\u025c\3\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\22\74\1\u025d\7\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\10\74\1\u025e\21\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\22\74\1\u025f\7\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\21\74\1\u0260\10\74\4\uffff\1\74",
			"\12\74\1\76\5\uffff\1\75\32\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\4\74\1\u0262\25\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\4\74\1\u0263\25\74\4\uffff\1\74",
			"\12\74\1\76\5\uffff\1\75\32\74\4\uffff\1\74",
			"\12\74\1\76\5\uffff\1\75\32\74\4\uffff\1\74",
			"",
			"\12\74\1\76\6\uffff\23\74\1\u0266\6\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\3\74\1\u0267\26\74\4\uffff\1\74",
			"",
			"\12\74\1\76\6\uffff\21\74\1\u0268\10\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\7\74\1\u0269\22\74\4\uffff\1\74",
			"\12\74\1\76\5\uffff\1\75\32\74\4\uffff\1\74",
			"",
			"\12\74\1\76\6\uffff\22\74\1\u026b\7\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\6\74\1\u026c\23\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\23\74\1\u026d\6\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\10\74\1\u026e\21\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\1\u026f\31\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\14\74\1\u0270\15\74\4\uffff\1\74",
			"",
			"\12\74\1\76\5\uffff\1\75\32\74\4\uffff\1\74",
			"\12\74\1\76\5\uffff\1\75\32\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\23\74\1\u0273\6\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\26\74\1\u0274\3\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\4\74\1\u0275\25\74\4\uffff\1\74",
			"",
			"\12\74\1\76\6\uffff\10\74\1\u0276\21\74\4\uffff\1\74",
			"\12\74\1\76\5\uffff\1\75\32\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\10\74\1\u0278\21\74\4\uffff\1\u0279",
			"",
			"\12\74\1\76\6\uffff\1\u027a\31\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\10\74\1\u027b\21\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\23\74\1\u027c\6\74\4\uffff\1\74",
			"",
			"",
			"\12\74\1\76\5\uffff\1\75\22\74\1\u027d\7\74\4\uffff\1\74",
			"",
			"",
			"\12\74\1\76\5\uffff\1\75\32\74\4\uffff\1\74",
			"\12\74\1\76\5\uffff\1\75\32\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\22\74\1\u0281\7\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\31\74\1\u0282\4\uffff\1\74",
			"\12\74\1\76\5\uffff\1\75\32\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\23\74\1\u0284\6\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\15\74\1\u0285\14\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\10\74\1\u0286\21\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\10\74\1\u0287\21\74\4\uffff\1\74",
			"",
			"\12\74\1\76\6\uffff\4\74\1\u0288\25\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\14\74\1\u0289\15\74\4\uffff\1\74",
			"",
			"\12\74\1\76\6\uffff\1\u028a\31\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\4\74\1\u028b\25\74\4\uffff\1\74",
			"",
			"\12\74\1\76\6\uffff\2\74\1\u028c\27\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\10\74\1\u028d\21\74\4\uffff\1\74",
			"\12\74\1\76\5\uffff\1\75\32\74\4\uffff\1\74",
			"",
			"\12\74\1\76\6\uffff\13\74\1\u028f\16\74\4\uffff\1\74",
			"",
			"\12\74\1\76\5\uffff\1\75\32\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\25\74\1\u0291\4\74\4\uffff\1\74",
			"\12\74\1\76\5\uffff\1\75\22\74\1\u0292\7\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\16\74\1\u0294\13\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\7\74\1\u0295\22\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\1\u0296\31\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\13\74\1\u0297\16\74\4\uffff\1\74",
			"",
			"\12\74\1\76\6\uffff\4\74\1\u0298\25\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\24\74\1\u0299\5\74\4\uffff\1\74",
			"",
			"\12\74\1\76\6\uffff\1\u029a\31\74\4\uffff\1\74",
			"\12\74\1\76\5\uffff\1\75\32\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\23\74\1\u029c\6\74\4\uffff\1\74",
			"\12\74\1\76\5\uffff\1\75\32\74\4\uffff\1\74",
			"",
			"",
			"\12\74\1\76\6\uffff\23\74\1\u029e\6\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\6\74\1\u029f\23\74\4\uffff\1\74",
			"",
			"\12\74\1\76\5\uffff\1\75\32\74\4\uffff\1\74",
			"",
			"",
			"",
			"",
			"\12\74\1\76\6\uffff\1\u02a1\31\74\4\uffff\1\74",
			"\12\74\1\76\5\uffff\1\75\32\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\4\74\1\u02a3\25\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\22\74\1\u02a4\7\74\4\uffff\1\74",
			"",
			"\12\74\1\76\6\uffff\23\74\1\u02a5\6\74\4\uffff\1\74",
			"\12\74\1\76\5\uffff\1\75\32\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\4\74\1\u02a7\25\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\10\74\1\u02a8\21\74\4\uffff\1\74",
			"\12\74\1\76\5\uffff\1\75\32\74\4\uffff\1\74",
			"\12\74\1\76\5\uffff\1\75\32\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\5\74\1\u02ab\24\74\4\uffff\1\74",
			"",
			"\12\74\1\76\6\uffff\1\u02ac\31\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\22\74\1\u02ad\7\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\2\74\1\u02ae\27\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\3\74\1\u02af\26\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\10\74\1\u02b0\21\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\23\74\1\u02b1\6\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\4\74\1\u02b2\25\74\4\uffff\1\74",
			"",
			"",
			"\12\74\1\76\5\uffff\1\75\32\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\4\74\1\u02b4\25\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\23\74\1\u02b5\6\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\10\74\1\u02b6\21\74\4\uffff\1\74",
			"\12\74\1\76\5\uffff\1\75\32\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\15\74\1\u02b8\14\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\22\74\1\u02b9\7\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\15\74\1\u02ba\14\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\21\74\1\u02bb\10\74\4\uffff\1\74",
			"",
			"",
			"",
			"\12\74\1\76\6\uffff\16\74\1\u02bc\13\74\4\uffff\1\74",
			"\12\74\1\76\5\uffff\1\75\32\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\4\74\1\u02be\25\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\4\74\1\u02bf\25\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\5\74\1\u02c0\24\74\4\uffff\1\74",
			"",
			"\12\74\1\76\5\uffff\1\75\32\74\4\uffff\1\74",
			"\12\74\1\76\5\uffff\1\75\32\74\4\uffff\1\74",
			"",
			"\12\74\1\76\6\uffff\15\74\1\u02c3\14\74\4\uffff\1\74",
			"\12\74\1\76\5\uffff\1\75\32\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\4\74\1\u02c5\25\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\23\74\1\u02c6\6\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\3\74\1\u02c7\26\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\21\74\1\u02c8\10\74\4\uffff\1\74",
			"\12\74\1\76\5\uffff\1\75\32\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\22\74\1\u02ca\7\74\4\uffff\1\74",
			"",
			"",
			"\12\74\1\76\6\uffff\22\74\1\u02cb\7\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\15\74\1\u02cc\14\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\21\74\1\u02cd\10\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\10\74\1\u02ce\21\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\23\74\1\u02cf\6\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\7\74\1\u02d0\22\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\4\74\1\u02d1\25\74\4\uffff\1\74",
			"",
			"\12\74\1\76\5\uffff\1\75\32\74\4\uffff\1\74",
			"\12\74\1\76\5\uffff\1\75\32\74\4\uffff\1\74",
			"",
			"",
			"\12\74\1\76\5\uffff\1\75\32\74\4\uffff\1\74",
			"\12\74\1\76\5\uffff\1\75\32\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\10\74\1\u02d6\21\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\16\74\1\u02d7\13\74\4\uffff\1\74",
			"",
			"\12\74\1\76\5\uffff\1\75\32\74\4\uffff\1\74",
			"\12\74\1\76\5\uffff\1\75\32\74\4\uffff\1\74",
			"\12\74\1\76\5\uffff\1\75\32\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\5\74\1\u02db\24\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\22\74\1\u02dc\7\74\4\uffff\1\74",
			"\12\74\1\76\5\uffff\1\75\32\74\4\uffff\1\74",
			"",
			"",
			"\12\74\1\76\5\uffff\1\75\32\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\10\74\1\u02df\21\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\27\74\1\u02e0\2\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\16\74\1\u02e1\13\74\4\uffff\1\74",
			"",
			"\12\74\1\76\6\uffff\31\74\1\u02e2\4\uffff\1\74",
			"\12\74\1\76\6\uffff\5\74\1\u02e3\24\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\22\74\1\u02e4\7\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\4\74\1\u02e5\25\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\1\u02e6\31\74\4\uffff\1\74",
			"\12\74\1\76\5\uffff\1\75\1\u02e7\31\74\4\uffff\1\74",
			"",
			"",
			"",
			"\12\74\1\76\5\uffff\1\75\32\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\4\74\1\u02ea\25\74\4\uffff\1\74",
			"",
			"\12\74\1\76\6\uffff\16\74\1\u02eb\13\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\2\74\1\u02ec\27\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\1\74\1\u02ed\30\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\1\74\1\u02ee\30\74\4\uffff\1\74",
			"\12\74\1\76\5\uffff\1\75\32\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\10\74\1\u02f0\21\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\22\74\1\u02f1\7\74\4\uffff\1\74",
			"\12\74\1\76\5\uffff\1\75\32\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\1\u02f3\31\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\2\74\1\u02f4\27\74\4\uffff\1\74",
			"",
			"\12\74\1\76\6\uffff\10\74\1\u02f5\21\74\4\uffff\1\74",
			"",
			"\12\74\1\76\6\uffff\10\74\1\u02f6\21\74\4\uffff\1\74",
			"\12\74\1\76\5\uffff\1\75\32\74\4\uffff\1\74",
			"",
			"\12\74\1\76\6\uffff\10\74\1\u02f8\21\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\1\u02f9\31\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\23\74\1\u02fa\6\74\4\uffff\1\74",
			"\12\74\1\76\5\uffff\1\75\32\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\15\74\1\u02fc\14\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\13\74\1\u02fd\16\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\15\74\1\u02fe\14\74\4\uffff\1\74",
			"",
			"\12\74\1\76\5\uffff\1\75\32\74\4\uffff\1\74",
			"",
			"\12\74\1\76\5\uffff\1\75\32\74\4\uffff\1\74",
			"\12\74\1\76\5\uffff\1\75\32\74\4\uffff\1\74",
			"",
			"\12\74\1\76\6\uffff\13\74\1\u0302\16\74\4\uffff\1\74",
			"",
			"\12\74\1\76\6\uffff\21\74\1\u0303\10\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\4\74\1\u0304\25\74\4\uffff\1\74",
			"\12\74\1\76\5\uffff\1\75\32\74\4\uffff\1\74",
			"",
			"\12\74\1\76\6\uffff\3\74\1\u0306\26\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\5\74\1\u0307\24\74\4\uffff\1\74",
			"",
			"",
			"\12\74\1\76\5\uffff\1\75\32\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\13\74\1\u0309\16\74\4\uffff\1\74",
			"\12\74\1\76\5\uffff\1\75\32\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\23\74\1\u030b\6\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\22\74\1\u030c\7\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\15\74\1\u030d\14\74\4\uffff\1\74",
			"\12\74\1\76\5\uffff\1\75\32\74\4\uffff\1\74",
			"\12\74\1\76\5\uffff\1\75\32\74\4\uffff\1\74",
			"",
			"\12\74\1\76\5\uffff\1\75\32\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\4\74\1\u0311\25\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\1\u0312\31\74\4\uffff\1\74",
			"",
			"\12\74\1\76\6\uffff\23\74\1\u0313\6\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\2\74\1\u0314\27\74\4\uffff\1\74",
			"\12\74\1\76\5\uffff\1\75\22\74\1\u0315\7\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\1\u0317\31\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\6\74\1\u0318\23\74\4\uffff\1\74",
			"",
			"\12\74\1\76\5\uffff\1\75\32\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\21\74\1\u031a\10\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\10\74\1\u031b\21\74\4\uffff\1\74",
			"",
			"",
			"\12\74\1\76\6\uffff\3\74\1\u031c\26\74\4\uffff\1\74",
			"",
			"\12\74\1\76\5\uffff\1\75\32\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\10\74\1\u031e\21\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\10\74\1\u031f\21\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\30\74\1\u0320\1\74\4\uffff\1\74",
			"",
			"\12\74\1\76\6\uffff\23\74\1\u0321\6\74\4\uffff\1\74",
			"\12\74\1\76\5\uffff\1\75\32\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\13\74\1\u0323\16\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\10\74\1\u0324\21\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\25\74\1\u0325\4\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\16\74\1\u0326\13\74\4\uffff\1\74",
			"\12\74\1\76\5\uffff\1\75\32\74\4\uffff\1\74",
			"\12\74\1\76\5\uffff\1\75\32\74\4\uffff\1\74",
			"",
			"",
			"",
			"",
			"\12\74\1\76\6\uffff\15\74\1\u0329\14\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\23\74\1\u032a\6\74\4\uffff\1\74",
			"",
			"",
			"",
			"\12\74\1\76\6\uffff\30\74\1\u032b\1\74\4\uffff\1\74",
			"\12\74\1\76\5\uffff\1\75\32\74\4\uffff\1\74",
			"",
			"",
			"\12\74\1\76\6\uffff\15\74\1\u032d\14\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\23\74\1\u032e\6\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\15\74\1\u032f\14\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\4\74\1\u0330\25\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\10\74\1\u0331\21\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\23\74\1\u0332\6\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\15\74\1\u0333\14\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\14\74\1\u0334\15\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\14\74\1\u0335\15\74\4\uffff\1\74",
			"",
			"",
			"\12\74\1\76\6\uffff\21\74\1\u0336\10\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\21\74\1\u0337\10\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\23\74\1\u0338\6\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\24\74\1\u0339\5\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\4\74\1\u033a\25\74\4\uffff\1\74",
			"",
			"\12\74\1\76\6\uffff\22\74\1\u033b\7\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\4\74\1\u033c\25\74\4\uffff\1\74",
			"",
			"\12\74\1\76\6\uffff\23\74\1\u033d\6\74\4\uffff\1\74",
			"\12\74\1\76\5\uffff\1\75\32\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\22\74\1\u033f\7\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\31\74\1\u0340\4\uffff\1\74",
			"",
			"\12\74\1\76\6\uffff\15\74\1\u0341\14\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\17\74\1\u0342\12\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\4\74\1\u0343\25\74\4\uffff\1\74",
			"",
			"\12\74\1\76\5\uffff\1\75\32\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\13\74\1\u0345\16\74\4\uffff\1\74",
			"\12\74\1\76\5\uffff\1\75\32\74\4\uffff\1\74",
			"",
			"",
			"",
			"\12\74\1\76\5\uffff\1\75\32\74\4\uffff\1\74",
			"\12\74\1\76\5\uffff\1\75\32\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\2\74\1\u0349\27\74\4\uffff\1\74",
			"",
			"\12\74\1\76\5\uffff\1\75\32\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\10\74\1\u034b\21\74\4\uffff\1\74",
			"",
			"\12\74\1\76\5\uffff\1\75\32\74\4\uffff\1\74",
			"",
			"\12\74\1\76\5\uffff\1\75\32\74\4\uffff\1\74",
			"\12\74\1\76\5\uffff\1\75\32\74\4\uffff\1\74",
			"\12\74\1\76\5\uffff\1\75\32\74\4\uffff\1\74",
			"",
			"",
			"",
			"\12\74\1\76\5\uffff\1\75\32\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\13\74\1\u0351\16\74\4\uffff\1\74",
			"\12\74\1\76\5\uffff\1\75\32\74\4\uffff\1\u0352",
			"\12\74\1\76\6\uffff\4\74\1\u0354\25\74\4\uffff\1\74",
			"\12\74\1\76\5\uffff\1\75\32\74\4\uffff\1\74",
			"",
			"\12\74\1\76\6\uffff\10\74\1\u0356\21\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\22\74\1\u0357\7\74\4\uffff\1\74",
			"",
			"\12\74\1\76\5\uffff\1\75\4\74\1\u0358\15\74\1\u0359\7\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\13\74\1\u035b\16\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\4\74\1\u035c\25\74\4\uffff\1\74",
			"",
			"\12\74\1\76\6\uffff\16\74\1\u035d\13\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\15\74\1\u035e\14\74\4\uffff\1\74",
			"\1\u035f\17\uffff\12\74\1\76\6\uffff\32\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\4\74\1\u0360\25\74\4\uffff\1\74",
			"",
			"\12\74\1\76\6\uffff\30\74\1\u0361\1\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\23\74\1\u0362\6\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\4\74\1\u0363\25\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\21\74\1\u0364\10\74\4\uffff\1\74",
			"",
			"",
			"\12\74\1\76\6\uffff\6\74\1\u0365\23\74\4\uffff\1\74",
			"\12\74\1\76\5\uffff\1\75\32\74\4\uffff\1\74",
			"\12\74\1\76\5\uffff\1\75\32\74\4\uffff\1\74",
			"",
			"\12\74\1\76\6\uffff\6\74\1\u0368\23\74\4\uffff\1\74",
			"\12\74\1\76\5\uffff\1\75\32\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\22\74\1\u036a\7\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\21\74\1\u036b\10\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\13\74\1\u036c\16\74\4\uffff\1\74",
			"\12\74\1\76\5\uffff\1\75\32\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\23\74\1\u036e\6\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\17\74\1\u036f\12\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\17\74\1\u0370\12\74\4\uffff\1\74",
			"\12\74\1\76\5\uffff\1\75\32\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\30\74\1\u0372\1\74\4\uffff\1\74",
			"\12\74\1\76\5\uffff\1\75\32\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\23\74\1\u0374\6\74\4\uffff\1\74",
			"\12\74\1\76\5\uffff\1\75\32\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\22\74\1\u0376\7\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\22\74\1\u0377\7\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\4\74\1\u0378\25\74\4\uffff\1\74",
			"",
			"\12\74\1\76\6\uffff\23\74\1\u0379\6\74\4\uffff\1\74",
			"\12\74\1\76\5\uffff\1\75\32\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\23\74\1\u037b\6\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\4\74\1\u037c\25\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\3\74\1\u037d\26\74\4\uffff\1\74",
			"",
			"\12\74\1\76\6\uffff\10\74\1\u037e\21\74\4\uffff\1\74",
			"",
			"",
			"",
			"\12\74\1\76\6\uffff\23\74\1\u037f\6\74\4\uffff\1\74",
			"",
			"\12\74\1\76\6\uffff\4\74\1\u0380\25\74\4\uffff\1\74",
			"",
			"",
			"",
			"",
			"",
			"\12\74\1\76\6\uffff\10\74\1\u0381\21\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\3\74\1\u0382\17\74\1\u0383\6\74\4\uffff\1\74",
			"",
			"\12\74\1\76\5\uffff\1\75\32\74\4\uffff\1\74",
			"",
			"\12\74\1\76\6\uffff\15\74\1\u0385\14\74\4\uffff\1\74",
			"\12\74\1\76\5\uffff\1\75\32\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\3\74\1\u0387\26\74\4\uffff\1\74",
			"\12\74\1\76\5\uffff\1\75\32\74\4\uffff\1\74",
			"",
			"\12\74\1\76\6\uffff\23\74\1\u0389\6\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\3\74\1\u038a\26\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\15\74\1\u038b\14\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\6\74\1\u038c\23\74\4\uffff\1\74",
			"",
			"\12\74\1\76\6\uffff\15\74\1\u038d\14\74\4\uffff\1\74",
			"\12\74\1\76\5\uffff\1\75\32\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\4\74\1\u038f\25\74\4\uffff\1\74",
			"\12\74\1\76\5\uffff\1\75\32\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\30\74\1\u0391\1\74\4\uffff\1\74",
			"\12\74\1\76\5\uffff\1\75\32\74\4\uffff\1\74",
			"",
			"",
			"\12\74\1\76\5\uffff\1\75\32\74\4\uffff\1\74",
			"",
			"\12\74\1\76\5\uffff\1\75\32\74\4\uffff\1\74",
			"\12\74\1\76\5\uffff\1\75\32\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\23\74\1\u0396\6\74\4\uffff\1\74",
			"",
			"\12\74\1\76\5\uffff\1\75\32\74\4\uffff\1\74",
			"\12\74\1\76\5\uffff\1\75\32\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\13\74\1\u0399\16\74\4\uffff\1\74",
			"",
			"\12\74\1\76\5\uffff\1\75\32\74\4\uffff\1\74",
			"",
			"\12\74\1\76\6\uffff\4\74\1\u039b\25\74\4\uffff\1\74",
			"",
			"\12\74\1\76\6\uffff\10\74\1\u039c\21\74\4\uffff\1\74",
			"\12\74\1\76\5\uffff\1\75\32\74\4\uffff\1\74",
			"\12\74\1\76\5\uffff\1\75\32\74\4\uffff\1\74",
			"\12\74\1\76\5\uffff\1\75\32\74\4\uffff\1\74",
			"",
			"\12\74\1\76\5\uffff\1\75\32\74\4\uffff\1\74",
			"\12\74\1\76\5\uffff\1\75\32\74\4\uffff\1\74",
			"\12\74\1\76\5\uffff\1\75\32\74\4\uffff\1\74",
			"\12\74\1\76\5\uffff\1\75\32\74\4\uffff\1\74",
			"\12\74\1\76\5\uffff\1\75\32\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\3\74\1\u03a5\26\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\31\74\1\u03a6\4\uffff\1\74",
			"\12\74\1\76\6\uffff\1\u03a7\31\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\10\74\1\u03a8\21\74\4\uffff\1\74",
			"",
			"\12\74\1\76\6\uffff\23\74\1\u03a9\6\74\4\uffff\1\74",
			"",
			"\12\74\1\76\5\uffff\1\75\32\74\4\uffff\1\74",
			"",
			"\12\74\1\76\6\uffff\4\74\1\u03ab\25\74\4\uffff\1\74",
			"\12\74\1\76\5\uffff\1\75\32\74\4\uffff\1\74",
			"\12\74\1\76\5\uffff\1\75\4\74\1\u03ae\15\74\1\u03ad\7\74\4\uffff\1\74",
			"\12\74\1\76\5\uffff\1\75\32\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\23\74\1\u03b1\6\74\4\uffff\1\74",
			"",
			"\12\74\1\76\5\uffff\1\75\32\74\4\uffff\1\74",
			"",
			"\12\74\1\76\5\uffff\1\75\32\74\4\uffff\1\74",
			"",
			"",
			"",
			"",
			"\12\74\1\76\6\uffff\4\74\1\u03b4\25\74\4\uffff\1\74",
			"",
			"",
			"\12\74\1\76\6\uffff\4\74\1\u03b5\25\74\4\uffff\1\74",
			"",
			"\12\74\1\76\6\uffff\3\74\1\u03b6\26\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\16\74\1\u03b7\13\74\4\uffff\1\74",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"\12\74\1\76\5\uffff\1\75\32\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\4\74\1\u03b9\25\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\23\74\1\u03ba\6\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\14\74\1\u03bb\15\74\4\uffff\1\74",
			"\12\74\1\76\5\uffff\1\75\32\74\4\uffff\1\74",
			"",
			"\12\74\1\76\6\uffff\21\74\1\u03bd\10\74\4\uffff\1\74",
			"",
			"\12\74\1\76\5\uffff\1\75\32\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\3\74\1\u03bf\26\74\4\uffff\1\74",
			"",
			"",
			"\12\74\1\76\5\uffff\1\75\32\74\4\uffff\1\74",
			"",
			"",
			"\12\74\1\76\6\uffff\21\74\1\u03c1\10\74\4\uffff\1\74",
			"\12\74\1\76\5\uffff\1\75\32\74\4\uffff\1\74",
			"\12\74\1\76\5\uffff\1\75\32\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\15\74\1\u03c4\14\74\4\uffff\1\74",
			"",
			"\12\74\1\76\6\uffff\3\74\1\u03c5\26\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\4\74\1\u03c6\25\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\4\74\1\u03c7\25\74\4\uffff\1\74",
			"",
			"\12\74\1\76\6\uffff\22\74\1\u03c8\7\74\4\uffff\1\74",
			"",
			"\12\74\1\76\5\uffff\1\75\32\74\4\uffff\1\74",
			"",
			"\12\74\1\76\6\uffff\22\74\1\u03ca\7\74\4\uffff\1\74",
			"",
			"",
			"\12\74\1\76\5\uffff\1\75\32\74\4\uffff\1\74",
			"\12\74\1\76\5\uffff\1\75\32\74\4\uffff\1\74",
			"\12\74\1\76\5\uffff\1\75\32\74\4\uffff\1\74",
			"\12\74\1\76\5\uffff\1\75\22\74\1\u03ce\7\74\4\uffff\1\74",
			"\12\74\1\76\5\uffff\1\75\32\74\4\uffff\1\74",
			"",
			"\12\74\1\76\5\uffff\1\75\32\74\4\uffff\1\74",
			"",
			"",
			"",
			"\12\74\1\76\6\uffff\23\74\1\u03d2\6\74\4\uffff\1\74",
			"",
			"",
			"",
			"\12\74\1\76\6\uffff\1\u03d3\31\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\14\74\1\u03d4\15\74\4\uffff\1\74",
			"\12\74\1\76\6\uffff\17\74\1\u03d5\12\74\4\uffff\1\74",
			"\12\74\1\76\5\uffff\1\75\32\74\4\uffff\1\74",
			""
	};

	static final short[] DFA26_eot = DFA.unpackEncodedString(DFA26_eotS);
	static final short[] DFA26_eof = DFA.unpackEncodedString(DFA26_eofS);
	static final char[] DFA26_min = DFA.unpackEncodedStringToUnsignedChars(DFA26_minS);
	static final char[] DFA26_max = DFA.unpackEncodedStringToUnsignedChars(DFA26_maxS);
	static final short[] DFA26_accept = DFA.unpackEncodedString(DFA26_acceptS);
	static final short[] DFA26_special = DFA.unpackEncodedString(DFA26_specialS);
	static final short[][] DFA26_transition;

	static {
		int numStates = DFA26_transitionS.length;
		DFA26_transition = new short[numStates][];
		for (int i=0; i<numStates; i++) {
			DFA26_transition[i] = DFA.unpackEncodedString(DFA26_transitionS[i]);
		}
	}

	protected class DFA26 extends DFA {

		public DFA26(BaseRecognizer recognizer) {
			this.recognizer = recognizer;
			this.decisionNumber = 26;
			this.eot = DFA26_eot;
			this.eof = DFA26_eof;
			this.min = DFA26_min;
			this.max = DFA26_max;
			this.accept = DFA26_accept;
			this.special = DFA26_special;
			this.transition = DFA26_transition;
		}
		@Override
		public String getDescription() {
			return "1:1: Tokens : ( T__328 | T__329 | T__330 | T__331 | T__332 | T__333 | T__334 | T__335 | T__336 | T__337 | T__338 | T__339 | T__340 | T__341 | T__342 | T__343 | T__344 | T__345 | SELECT | FROM | TO | AS | ALL | ANY | SOME | DIRECTORY | DISTINCT | WHERE | GROUP | BY | ORDER | HAVING | LIMIT | OFFSET | OR | AND | IN | NOT | EXISTS | BETWEEN | LIKE | IS | NULL | TRUE | FALSE | NULLS | FIRST | LAST | ESCAPE | ASC | DESC | SUBSTRING | FOR | DATE | TIME | YEAR | MONTH | DAY | HOUR | MINUTE | SECOND | CURRENT_DATE | CURRENT_TIME | CURRENT_TIMESTAMP | EXTRACT | COALESCE | NULLIF | CASE | WHEN | THEN | ELSE | END | IF | JOIN | CROSS | OUTER | INNER | LEFT | RIGHT | FULL | NATURAL | USING | ON | OVER | PARTITION | RANGE | ROWS | UNBOUNDED | PRECEDING | FOLLOWING | CURRENT | ROW | WITH | RECURSIVE | CREATE | BLOB | TABLE | REPOSITORY | SNAPSHOT | ALTER | KILL | ONLY | ADD | COLUMN | BOOLEAN | BYTE | SHORT | INTEGER | INT | LONG | FLOAT | DOUBLE | TIMESTAMP | IP | OBJECT | STRING_TYPE | GEO_POINT | GEO_SHAPE | GLOBAL | CONSTRAINT | DESCRIBE | EXPLAIN | FORMAT | TYPE | TEXT | GRAPHVIZ | LOGICAL | DISTRIBUTED | CAST | TRY_CAST | SHOW | TABLES | SCHEMAS | DATABASES | CATALOGS | COLUMNS | PARTITIONS | FUNCTIONS | MATERIALIZED | VIEW | REFRESH | RESTORE | DROP | ALIAS | UNION | EXCEPT | INTERSECT | SYSTEM | BERNOULLI | TABLESAMPLE | STRATIFY | INSERT | INTO | VALUES | DELETE | UPDATE | KEY | DUPLICATE | SET | RESET | COPY | CLUSTERED | SHARDS | PRIMARY_KEY | OFF | FULLTEXT | PLAIN | INDEX | DYNAMIC | STRICT | IGNORED | ARRAY | ANALYZER | EXTENDS | TOKENIZER | TOKEN_FILTERS | CHAR_FILTERS | PARTITIONED | TRANSIENT | PERSISTENT | MATCH | GENERATED | ALWAYS | IDENTIFIED | USER | CLUSTER | CLUSTERS | MIGRATE | NODES | DECOMMISSION | USERS | REMOVE | WHITELIST | GRANT | GRANTS | REVOKE | EQ | NEQ | LT | LTE | GT | GTE | REGEX_MATCH | REGEX_NO_MATCH | REGEX_MATCH_CI | REGEX_NO_MATCH_CI | STRING | INTEGER_VALUE | DECIMAL_VALUE | IDENT | DIGIT_IDENT | QUOTED_IDENT | BACKQUOTED_IDENT | COLON_IDENT | COMMENT | WS );";
		}
	}

}
