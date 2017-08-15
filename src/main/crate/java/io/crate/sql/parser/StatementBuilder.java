// $ANTLR 3.5.2 D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g 2017-08-09 10:55:56

    package io.crate.sql.parser;

    import io.crate.sql.tree.*;

    import java.util.ArrayList;
    import java.util.List;
    import java.util.Locale;
    import com.google.common.collect.ImmutableList;
    import com.google.common.base.MoreObjects;
    import com.google.common.base.Optional;
    import com.google.common.collect.Multimap;
    import com.google.common.collect.LinkedListMultimap;


import org.antlr.runtime.*;
import org.antlr.runtime.tree.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;


@SuppressWarnings("all")
public class StatementBuilder extends TreeParser {
	public static final String[] tokenNames = new String[] {
		"<invalid>", "<EOR>", "<DOWN>", "<UP>", "ADD", "ADD_COLUMN", "ADD_COLUMN_DEF", 
		"ADD_NODES", "ALIAS", "ALIASED_COLUMNS", "ALIASED_RELATION", "ALL", "ALL_COLUMNS", 
		"ALTER", "ALTER_BLOB_TABLE", "ALTER_CLUSTER", "ALTER_CLUSTER_ADD_NODES", 
		"ALTER_CLUSTER_DECOMMISSION_NODES", "ALTER_CLUSTER_DROP_NODES", "ALTER_TABLE", 
		"ALTER_USER", "ALWAYS", "ANALYZER", "ANALYZER_ELEMENTS", "AND", "ANY", 
		"ARRAY", "ARRAY_CMP", "ARRAY_LIKE", "ARRAY_LITERAL", "ARRAY_NOT_LIKE", 
		"AS", "ASC", "ASSIGNMENT", "ASSIGNMENT_LIST", "BACKQUOTED_IDENT", "BERNOULLI", 
		"BETWEEN", "BLOB", "BOOLEAN", "BY", "BYTE", "CASE", "CAST", "CATALOGS", 
		"CHAR_FILTERS", "CLUSTER", "CLUSTERED", "CLUSTERS", "COALESCE", "COLON_IDENT", 
		"COLUMN", "COLUMNS", "COLUMN_DEF", "COLUMN_LIST", "COMMENT", "COMPARE", 
		"CONSTRAINT", "COPY", "COPY_FROM", "COPY_TO", "CREATE", "CREATE_ALIAS", 
		"CREATE_BLOB_TABLE", "CREATE_CLUSTER", "CREATE_MATERIALIZED_VIEW", "CREATE_REPOSITORY", 
		"CREATE_SNAPSHOT", "CREATE_TABLE", "CREATE_USER", "CROSS", "CROSS_JOIN", 
		"CURRENT", "CURRENT_DATE", "CURRENT_ROW", "CURRENT_TIME", "CURRENT_TIMESTAMP", 
		"DATABASES", "DATE", "DAY", "DECIMAL_VALUE", "DECOMMISSION", "DELETE", 
		"DESC", "DESCRIBE", "DIGIT", "DIGIT_IDENT", "DIRECTORY", "DISTINCT", "DISTRIBUTED", 
		"DOUBLE", "DROP", "DROP_ALIAS", "DROP_BLOB_TABLE", "DROP_CLUSTER", "DROP_REPOSITORY", 
		"DROP_SNAPSHOT", "DROP_TABLE", "DROP_USER", "DUPLICATE", "DYNAMIC", "ELSE", 
		"END", "EQ", "ESCAPE", "EXCEPT", "EXISTS", "EXPLAIN", "EXPLAIN_FORMAT", 
		"EXPLAIN_OPTIONS", "EXPLAIN_TYPE", "EXPONENT", "EXTENDS", "EXTRACT", "FALSE", 
		"FIRST", "FLOAT", "FOLLOWING", "FOR", "FORMAT", "FROM", "FULL", "FULLTEXT", 
		"FULL_JOIN", "FUNCTIONS", "FUNCTION_CALL", "GENERATED", "GENERATED_COLUMN_DEF", 
		"GENERIC_PROPERTIES", "GENERIC_PROPERTY", "GEO_POINT", "GEO_SHAPE", "GLOBAL", 
		"GRANT", "GRANTS", "GRANT_PRIVILEGE", "GRAPHVIZ", "GROUP", "GROUP_BY", 
		"GT", "GTE", "HAVING", "HOUR", "IDENT", "IDENTIFIED", "IDENTIFIED_BY", 
		"IDENT_EXPR", "IDENT_LIST", "IF", "IGNORED", "IN", "INDEX", "INDEX_COLUMNS", 
		"INDEX_OFF", "INNER", "INNER_JOIN", "INSERT", "INSERT_VALUES", "INT", 
		"INTEGER", "INTEGER_VALUE", "INTERSECT", "INTO", "IN_LIST", "IP", "IS", 
		"IS_DISTINCT_FROM", "IS_NOT_NULL", "IS_NULL", "JOIN", "JOINED_TABLE", 
		"KEY", "KEY_VALUE", "KILL", "LAST", "LEFT", "LEFT_JOIN", "LETTER", "LEXER_ERROR", 
		"LIKE", "LIMIT", "LITERAL_LIST", "LOGICAL", "LONG", "LT", "LTE", "MATCH", 
		"MATCH_PREDICATE_IDENT", "MATCH_PREDICATE_IDENT_LIST", "MATERIALIZED", 
		"MIGRATE", "MIGRATE_TABLE", "MINUTE", "MONTH", "NAMED_PROPERTIES", "NATURAL", 
		"NEGATIVE", "NEQ", "NODES", "NOT", "NOT_NULL", "NULL", "NULLIF", "NULLS", 
		"OBJECT", "OBJECT_COLUMNS", "OBJECT_LITERAL", "OFF", "OFFSET", "ON", "ONLY", 
		"ON_DUP_KEY", "OR", "ORDER", "ORDER_BY", "OUTER", "OVER", "PARTITION", 
		"PARTITIONED", "PARTITIONS", "PARTITION_BY", "PERSISTENT", "PLAIN", "PRECEDING", 
		"PRIMARY_KEY", "QNAME", "QUALIFIED_JOIN", "QUERY", "QUERY_SPEC", "QUOTED_IDENT", 
		"RANGE", "READ_ONLY", "READ_WRITE", "RECURSIVE", "REFRESH", "REFRESH_MATERIALIZED_VIEW", 
		"REGEX_MATCH", "REGEX_MATCH_CI", "REGEX_NO_MATCH", "REGEX_NO_MATCH_CI", 
		"REMOVE", "REMOVE_NODES", "REPOSITORY", "RESET", "RESET_PASSWORD", "RESET_WHITELIST", 
		"RESTORE", "RESTORE_SNAPSHOT", "REVOKE", "REVOKE_PRIVILEGE", "RIGHT", 
		"RIGHT_JOIN", "ROW", "ROWS", "SAMPLED_RELATION", "SCHEMAS", "SEARCHED_CASE", 
		"SECOND", "SELECT", "SELECT_ITEM", "SELECT_LIST", "SET", "SHARDS", "SHORT", 
		"SHOW", "SHOW_CATALOGS", "SHOW_CLUSTERS", "SHOW_COLUMNS", "SHOW_CREATE_TABLE", 
		"SHOW_DATABASES", "SHOW_FUNCTIONS", "SHOW_GRANTS", "SHOW_PARTITIONS", 
		"SHOW_SCHEMAS", "SHOW_TABLES", "SHOW_USERS", "SIMPLE_CASE", "SNAPSHOT", 
		"SOME", "SORT_ITEM", "STATEMENT_LIST", "STRATIFY", "STRATIFY_ON", "STRICT", 
		"STRING", "STRING_TYPE", "SUBSTRING", "SYSTEM", "TABLE", "TABLES", "TABLESAMPLE", 
		"TABLE_ELEMENT_LIST", "TABLE_FUNCTION", "TABLE_PARTITION_LIST", "TABLE_SUBQUERY", 
		"TERMINATOR", "TEXT", "THEN", "TIME", "TIMESTAMP", "TO", "TOKENIZER", 
		"TOKEN_FILTERS", "TRANSIENT", "TRUE", "TRY_CAST", "TYPE", "UNBOUNDED", 
		"UNBOUNDED_FOLLOWING", "UNBOUNDED_PRECEDING", "UNION", "UPDATE", "USER", 
		"USERS", "USING", "VALUES", "VALUES_LIST", "VIEW", "VIEW_REFRESH", "WHEN", 
		"WHERE", "WHITELIST", "WINDOW", "WITH", "WITH_LIST", "WITH_QUERY", "WS", 
		"YEAR", "'$'", "'%'", "'('", "')'", "'*'", "'+'", "','", "'-'", "'.'", 
		"'/'", "'?'", "'READ_ONLY'", "'READ_WRITE'", "'['", "']'", "'{'", "'||'", 
		"'}'"
	};
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

	// delegates
	public TreeParser[] getDelegates() {
		return new TreeParser[] {};
	}

	// delegators


	public StatementBuilder(TreeNodeStream input) {
		this(input, new RecognizerSharedState());
	}
	public StatementBuilder(TreeNodeStream input, RecognizerSharedState state) {
		super(input, state);
	}

	protected TreeAdaptor adaptor = new CommonTreeAdaptor();

	public void setTreeAdaptor(TreeAdaptor adaptor) {
		this.adaptor = adaptor;
	}
	public TreeAdaptor getTreeAdaptor() {
		return adaptor;
	}
	@Override public String[] getTokenNames() { return StatementBuilder.tokenNames; }
	@Override public String getGrammarFileName() { return "D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g"; }


	    private int parameterPos = 1;

	    @Override
	    protected Object recoverFromMismatchedToken(IntStream input, int tokenType, BitSet follow)
	            throws RecognitionException
	    {
	        throw new MismatchedTokenException(tokenType, input);
	    }

	    @Override
	    public Object recoverFromMismatchedSet(IntStream input, RecognitionException e, BitSet follow)
	            throws RecognitionException
	    {
	        throw e;
	    }


	public static class statement_return extends TreeRuleReturnScope {
		public Statement value;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "statement"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:70:1: statement returns [Statement value] : ( query | explain | showTables | showSchemas | showCatalogs | showColumns | showPartitions | showFunctions | showCreateTable | createTable | createRepository | createSnapshot | alterTable | alterBlobTable | createBlobTable | createMaterializedView | refreshMaterializedView | createAlias | dropAlias | dropTable | dropBlobTable | dropRepository | dropSnapshot | dropUser | restoreSnapshot | insert | delete | update | copyFrom | copyTo | createAnalyzer | refresh | set | resetStatement | killStatement | createUser | alterUser | grantStmt | revokeStmt | showGrantsStmt | showUsersStmt | createClusterStmt | dropClusterStmt | showClustersStmt | alterClusterStmt | migrateTableStmt );
	public final StatementBuilder.statement_return statement() throws RecognitionException {
		StatementBuilder.statement_return retval = new StatementBuilder.statement_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		TreeRuleReturnScope query1 =null;
		TreeRuleReturnScope explain2 =null;
		TreeRuleReturnScope showTables3 =null;
		TreeRuleReturnScope showSchemas4 =null;
		TreeRuleReturnScope showCatalogs5 =null;
		TreeRuleReturnScope showColumns6 =null;
		TreeRuleReturnScope showPartitions7 =null;
		TreeRuleReturnScope showFunctions8 =null;
		TreeRuleReturnScope showCreateTable9 =null;
		TreeRuleReturnScope createTable10 =null;
		TreeRuleReturnScope createRepository11 =null;
		TreeRuleReturnScope createSnapshot12 =null;
		TreeRuleReturnScope alterTable13 =null;
		TreeRuleReturnScope alterBlobTable14 =null;
		TreeRuleReturnScope createBlobTable15 =null;
		TreeRuleReturnScope createMaterializedView16 =null;
		TreeRuleReturnScope refreshMaterializedView17 =null;
		TreeRuleReturnScope createAlias18 =null;
		TreeRuleReturnScope dropAlias19 =null;
		TreeRuleReturnScope dropTable20 =null;
		TreeRuleReturnScope dropBlobTable21 =null;
		TreeRuleReturnScope dropRepository22 =null;
		TreeRuleReturnScope dropSnapshot23 =null;
		TreeRuleReturnScope dropUser24 =null;
		TreeRuleReturnScope restoreSnapshot25 =null;
		TreeRuleReturnScope insert26 =null;
		TreeRuleReturnScope delete27 =null;
		TreeRuleReturnScope update28 =null;
		TreeRuleReturnScope copyFrom29 =null;
		TreeRuleReturnScope copyTo30 =null;
		TreeRuleReturnScope createAnalyzer31 =null;
		TreeRuleReturnScope refresh32 =null;
		TreeRuleReturnScope set33 =null;
		TreeRuleReturnScope resetStatement34 =null;
		TreeRuleReturnScope killStatement35 =null;
		TreeRuleReturnScope createUser36 =null;
		TreeRuleReturnScope alterUser37 =null;
		TreeRuleReturnScope grantStmt38 =null;
		TreeRuleReturnScope revokeStmt39 =null;
		TreeRuleReturnScope showGrantsStmt40 =null;
		TreeRuleReturnScope showUsersStmt41 =null;
		TreeRuleReturnScope createClusterStmt42 =null;
		TreeRuleReturnScope dropClusterStmt43 =null;
		TreeRuleReturnScope showClustersStmt44 =null;
		TreeRuleReturnScope alterClusterStmt45 =null;
		TreeRuleReturnScope migrateTableStmt46 =null;


		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:71:5: ( query | explain | showTables | showSchemas | showCatalogs | showColumns | showPartitions | showFunctions | showCreateTable | createTable | createRepository | createSnapshot | alterTable | alterBlobTable | createBlobTable | createMaterializedView | refreshMaterializedView | createAlias | dropAlias | dropTable | dropBlobTable | dropRepository | dropSnapshot | dropUser | restoreSnapshot | insert | delete | update | copyFrom | copyTo | createAnalyzer | refresh | set | resetStatement | killStatement | createUser | alterUser | grantStmt | revokeStmt | showGrantsStmt | showUsersStmt | createClusterStmt | dropClusterStmt | showClustersStmt | alterClusterStmt | migrateTableStmt )
			int alt1=46;
			switch ( input.LA(1) ) {
			case QUERY:
				{
				alt1=1;
				}
				break;
			case EXPLAIN:
				{
				alt1=2;
				}
				break;
			case SHOW_TABLES:
				{
				alt1=3;
				}
				break;
			case SHOW_SCHEMAS:
				{
				alt1=4;
				}
				break;
			case SHOW_CATALOGS:
				{
				alt1=5;
				}
				break;
			case SHOW_COLUMNS:
				{
				alt1=6;
				}
				break;
			case SHOW_PARTITIONS:
				{
				alt1=7;
				}
				break;
			case SHOW_FUNCTIONS:
				{
				alt1=8;
				}
				break;
			case SHOW_CREATE_TABLE:
				{
				alt1=9;
				}
				break;
			case CREATE_TABLE:
				{
				alt1=10;
				}
				break;
			case CREATE_REPOSITORY:
				{
				alt1=11;
				}
				break;
			case CREATE_SNAPSHOT:
				{
				alt1=12;
				}
				break;
			case ADD_COLUMN:
			case ALTER_TABLE:
				{
				alt1=13;
				}
				break;
			case ALTER_BLOB_TABLE:
				{
				alt1=14;
				}
				break;
			case CREATE_BLOB_TABLE:
				{
				alt1=15;
				}
				break;
			case CREATE_MATERIALIZED_VIEW:
				{
				alt1=16;
				}
				break;
			case REFRESH_MATERIALIZED_VIEW:
				{
				alt1=17;
				}
				break;
			case CREATE_ALIAS:
				{
				alt1=18;
				}
				break;
			case DROP_ALIAS:
				{
				alt1=19;
				}
				break;
			case DROP_TABLE:
				{
				alt1=20;
				}
				break;
			case DROP_BLOB_TABLE:
				{
				alt1=21;
				}
				break;
			case DROP_REPOSITORY:
				{
				alt1=22;
				}
				break;
			case DROP_SNAPSHOT:
				{
				alt1=23;
				}
				break;
			case DROP_USER:
				{
				alt1=24;
				}
				break;
			case RESTORE_SNAPSHOT:
				{
				alt1=25;
				}
				break;
			case INSERT:
				{
				alt1=26;
				}
				break;
			case DELETE:
				{
				alt1=27;
				}
				break;
			case UPDATE:
				{
				alt1=28;
				}
				break;
			case COPY_FROM:
				{
				alt1=29;
				}
				break;
			case COPY_TO:
				{
				alt1=30;
				}
				break;
			case ANALYZER:
				{
				alt1=31;
				}
				break;
			case REFRESH:
				{
				alt1=32;
				}
				break;
			case SET:
				{
				alt1=33;
				}
				break;
			case RESET:
				{
				alt1=34;
				}
				break;
			case KILL:
				{
				alt1=35;
				}
				break;
			case CREATE_USER:
				{
				alt1=36;
				}
				break;
			case RESET_PASSWORD:
			case RESET_WHITELIST:
				{
				alt1=37;
				}
				break;
			case GRANT_PRIVILEGE:
				{
				alt1=38;
				}
				break;
			case REVOKE_PRIVILEGE:
				{
				alt1=39;
				}
				break;
			case SHOW_GRANTS:
				{
				alt1=40;
				}
				break;
			case SHOW_USERS:
				{
				alt1=41;
				}
				break;
			case CREATE_CLUSTER:
				{
				alt1=42;
				}
				break;
			case DROP_CLUSTER:
				{
				alt1=43;
				}
				break;
			case SHOW_CLUSTERS:
				{
				alt1=44;
				}
				break;
			case ALTER_CLUSTER:
			case ALTER_CLUSTER_ADD_NODES:
			case ALTER_CLUSTER_DECOMMISSION_NODES:
			case ALTER_CLUSTER_DROP_NODES:
				{
				alt1=45;
				}
				break;
			case MIGRATE_TABLE:
				{
				alt1=46;
				}
				break;
			default:
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 1, 0, input);
				throw nvae;
			}
			switch (alt1) {
				case 1 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:71:7: query
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_query_in_statement80);
					query1=query();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_0, query1.getTree());

					if ( state.backtracking==0 ) { retval.value = (query1!=null?((StatementBuilder.query_return)query1).value:null); }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 2 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:72:7: explain
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_explain_in_statement110);
					explain2=explain();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_0, explain2.getTree());

					if ( state.backtracking==0 ) { retval.value = (explain2!=null?((StatementBuilder.explain_return)explain2).value:null); }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 3 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:73:7: showTables
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_showTables_in_statement138);
					showTables3=showTables();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_0, showTables3.getTree());

					if ( state.backtracking==0 ) { retval.value = (showTables3!=null?((StatementBuilder.showTables_return)showTables3).value:null); }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 4 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:74:7: showSchemas
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_showSchemas_in_statement163);
					showSchemas4=showSchemas();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_0, showSchemas4.getTree());

					if ( state.backtracking==0 ) { retval.value = (showSchemas4!=null?((StatementBuilder.showSchemas_return)showSchemas4).value:null); }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 5 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:75:7: showCatalogs
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_showCatalogs_in_statement187);
					showCatalogs5=showCatalogs();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_0, showCatalogs5.getTree());

					if ( state.backtracking==0 ) { retval.value = (showCatalogs5!=null?((StatementBuilder.showCatalogs_return)showCatalogs5).value:null); }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 6 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:76:7: showColumns
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_showColumns_in_statement210);
					showColumns6=showColumns();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_0, showColumns6.getTree());

					if ( state.backtracking==0 ) { retval.value = (showColumns6!=null?((StatementBuilder.showColumns_return)showColumns6).value:null); }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 7 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:77:7: showPartitions
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_showPartitions_in_statement234);
					showPartitions7=showPartitions();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_0, showPartitions7.getTree());

					if ( state.backtracking==0 ) { retval.value = (showPartitions7!=null?((StatementBuilder.showPartitions_return)showPartitions7).value:null); }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 8 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:78:7: showFunctions
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_showFunctions_in_statement255);
					showFunctions8=showFunctions();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_0, showFunctions8.getTree());

					if ( state.backtracking==0 ) { retval.value = (showFunctions8!=null?((StatementBuilder.showFunctions_return)showFunctions8).value:null); }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 9 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:79:7: showCreateTable
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_showCreateTable_in_statement277);
					showCreateTable9=showCreateTable();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_0, showCreateTable9.getTree());

					if ( state.backtracking==0 ) { retval.value = (showCreateTable9!=null?((StatementBuilder.showCreateTable_return)showCreateTable9).value:null); }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 10 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:80:7: createTable
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_createTable_in_statement297);
					createTable10=createTable();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_0, createTable10.getTree());

					if ( state.backtracking==0 ) { retval.value = (createTable10!=null?((StatementBuilder.createTable_return)createTable10).value:null); }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 11 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:81:7: createRepository
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_createRepository_in_statement321);
					createRepository11=createRepository();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_0, createRepository11.getTree());

					if ( state.backtracking==0 ) { retval.value = (createRepository11!=null?((StatementBuilder.createRepository_return)createRepository11).value:null); }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 12 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:82:7: createSnapshot
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_createSnapshot_in_statement340);
					createSnapshot12=createSnapshot();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_0, createSnapshot12.getTree());

					if ( state.backtracking==0 ) { retval.value = (createSnapshot12!=null?((StatementBuilder.createSnapshot_return)createSnapshot12).value:null); }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 13 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:83:7: alterTable
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_alterTable_in_statement361);
					alterTable13=alterTable();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_0, alterTable13.getTree());

					if ( state.backtracking==0 ) { retval.value = (alterTable13!=null?((StatementBuilder.alterTable_return)alterTable13).value:null); }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 14 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:84:7: alterBlobTable
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_alterBlobTable_in_statement386);
					alterBlobTable14=alterBlobTable();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_0, alterBlobTable14.getTree());

					if ( state.backtracking==0 ) { retval.value = (alterBlobTable14!=null?((StatementBuilder.alterBlobTable_return)alterBlobTable14).value:null); }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 15 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:85:7: createBlobTable
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_createBlobTable_in_statement407);
					createBlobTable15=createBlobTable();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_0, createBlobTable15.getTree());

					if ( state.backtracking==0 ) { retval.value = (createBlobTable15!=null?((StatementBuilder.createBlobTable_return)createBlobTable15).value:null); }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 16 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:86:7: createMaterializedView
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_createMaterializedView_in_statement427);
					createMaterializedView16=createMaterializedView();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_0, createMaterializedView16.getTree());

					if ( state.backtracking==0 ) { retval.value = (createMaterializedView16!=null?((StatementBuilder.createMaterializedView_return)createMaterializedView16).value:null); }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 17 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:87:7: refreshMaterializedView
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_refreshMaterializedView_in_statement440);
					refreshMaterializedView17=refreshMaterializedView();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_0, refreshMaterializedView17.getTree());

					if ( state.backtracking==0 ) { retval.value = (refreshMaterializedView17!=null?((StatementBuilder.refreshMaterializedView_return)refreshMaterializedView17).value:null); }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 18 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:88:7: createAlias
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_createAlias_in_statement452);
					createAlias18=createAlias();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_0, createAlias18.getTree());

					if ( state.backtracking==0 ) { retval.value = (createAlias18!=null?((StatementBuilder.createAlias_return)createAlias18).value:null); }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 19 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:89:7: dropAlias
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_dropAlias_in_statement476);
					dropAlias19=dropAlias();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_0, dropAlias19.getTree());

					if ( state.backtracking==0 ) { retval.value = (dropAlias19!=null?((StatementBuilder.dropAlias_return)dropAlias19).value:null); }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 20 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:90:7: dropTable
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_dropTable_in_statement502);
					dropTable20=dropTable();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_0, dropTable20.getTree());

					if ( state.backtracking==0 ) { retval.value = (dropTable20!=null?((StatementBuilder.dropTable_return)dropTable20).value:null); }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 21 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:91:7: dropBlobTable
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_dropBlobTable_in_statement528);
					dropBlobTable21=dropBlobTable();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_0, dropBlobTable21.getTree());

					if ( state.backtracking==0 ) { retval.value = (dropBlobTable21!=null?((StatementBuilder.dropBlobTable_return)dropBlobTable21).value:null); }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 22 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:92:7: dropRepository
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_dropRepository_in_statement550);
					dropRepository22=dropRepository();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_0, dropRepository22.getTree());

					if ( state.backtracking==0 ) { retval.value = (dropRepository22!=null?((StatementBuilder.dropRepository_return)dropRepository22).value:null); }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 23 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:93:7: dropSnapshot
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_dropSnapshot_in_statement571);
					dropSnapshot23=dropSnapshot();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_0, dropSnapshot23.getTree());

					if ( state.backtracking==0 ) { retval.value = (dropSnapshot23!=null?((StatementBuilder.dropSnapshot_return)dropSnapshot23).value:null); }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 24 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:94:7: dropUser
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_dropUser_in_statement594);
					dropUser24=dropUser();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_0, dropUser24.getTree());

					if ( state.backtracking==0 ) { retval.value = (dropUser24!=null?((StatementBuilder.dropUser_return)dropUser24).value:null); }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 25 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:95:7: restoreSnapshot
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_restoreSnapshot_in_statement621);
					restoreSnapshot25=restoreSnapshot();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_0, restoreSnapshot25.getTree());

					if ( state.backtracking==0 ) { retval.value = (restoreSnapshot25!=null?((StatementBuilder.restoreSnapshot_return)restoreSnapshot25).value:null); }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 26 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:96:7: insert
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_insert_in_statement641);
					insert26=insert();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_0, insert26.getTree());

					if ( state.backtracking==0 ) { retval.value = (insert26!=null?((StatementBuilder.insert_return)insert26).value:null); }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 27 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:97:7: delete
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_delete_in_statement670);
					delete27=delete();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_0, delete27.getTree());

					if ( state.backtracking==0 ) { retval.value = (delete27!=null?((StatementBuilder.delete_return)delete27).value:null); }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 28 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:98:7: update
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_update_in_statement699);
					update28=update();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_0, update28.getTree());

					if ( state.backtracking==0 ) { retval.value = (update28!=null?((StatementBuilder.update_return)update28).value:null); }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 29 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:99:7: copyFrom
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_copyFrom_in_statement728);
					copyFrom29=copyFrom();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_0, copyFrom29.getTree());

					if ( state.backtracking==0 ) { retval.value = (copyFrom29!=null?((StatementBuilder.copyFrom_return)copyFrom29).value:null); }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 30 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:100:7: copyTo
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_copyTo_in_statement755);
					copyTo30=copyTo();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_0, copyTo30.getTree());

					if ( state.backtracking==0 ) { retval.value = (copyTo30!=null?((StatementBuilder.copyTo_return)copyTo30).value:null); }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 31 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:101:7: createAnalyzer
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_createAnalyzer_in_statement784);
					createAnalyzer31=createAnalyzer();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_0, createAnalyzer31.getTree());

					if ( state.backtracking==0 ) { retval.value = (createAnalyzer31!=null?((StatementBuilder.createAnalyzer_return)createAnalyzer31).value:null); }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 32 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:102:7: refresh
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_refresh_in_statement805);
					refresh32=refresh();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_0, refresh32.getTree());

					if ( state.backtracking==0 ) { retval.value = (refresh32!=null?((StatementBuilder.refresh_return)refresh32).value:null); }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 33 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:103:7: set
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_set_in_statement833);
					set33=set();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_0, set33.getTree());

					if ( state.backtracking==0 ) { retval.value = (set33!=null?((StatementBuilder.set_return)set33).value:null); }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 34 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:104:7: resetStatement
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_resetStatement_in_statement865);
					resetStatement34=resetStatement();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_0, resetStatement34.getTree());

					if ( state.backtracking==0 ) { retval.value = (resetStatement34!=null?((StatementBuilder.resetStatement_return)resetStatement34).value:null); }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 35 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:105:7: killStatement
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_killStatement_in_statement886);
					killStatement35=killStatement();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_0, killStatement35.getTree());

					if ( state.backtracking==0 ) { retval.value = (killStatement35!=null?((StatementBuilder.killStatement_return)killStatement35).value:null); }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 36 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:106:7: createUser
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_createUser_in_statement908);
					createUser36=createUser();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_0, createUser36.getTree());

					if ( state.backtracking==0 ) { retval.value = (createUser36!=null?((StatementBuilder.createUser_return)createUser36).value:null); }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 37 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:107:7: alterUser
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_alterUser_in_statement933);
					alterUser37=alterUser();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_0, alterUser37.getTree());

					if ( state.backtracking==0 ) { retval.value = (alterUser37!=null?((StatementBuilder.alterUser_return)alterUser37).value:null); }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 38 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:108:7: grantStmt
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_grantStmt_in_statement959);
					grantStmt38=grantStmt();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_0, grantStmt38.getTree());

					if ( state.backtracking==0 ) { retval.value = (grantStmt38!=null?((StatementBuilder.grantStmt_return)grantStmt38).value:null); }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 39 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:109:7: revokeStmt
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_revokeStmt_in_statement985);
					revokeStmt39=revokeStmt();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_0, revokeStmt39.getTree());

					if ( state.backtracking==0 ) { retval.value = (revokeStmt39!=null?((StatementBuilder.revokeStmt_return)revokeStmt39).value:null); }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 40 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:110:7: showGrantsStmt
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_showGrantsStmt_in_statement1010);
					showGrantsStmt40=showGrantsStmt();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_0, showGrantsStmt40.getTree());

					if ( state.backtracking==0 ) { retval.value = (showGrantsStmt40!=null?((StatementBuilder.showGrantsStmt_return)showGrantsStmt40).value:null); }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 41 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:111:7: showUsersStmt
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_showUsersStmt_in_statement1031);
					showUsersStmt41=showUsersStmt();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_0, showUsersStmt41.getTree());

					if ( state.backtracking==0 ) { retval.value = (showUsersStmt41!=null?((StatementBuilder.showUsersStmt_return)showUsersStmt41).value:null); }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 42 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:113:7: createClusterStmt
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_createClusterStmt_in_statement1058);
					createClusterStmt42=createClusterStmt();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_0, createClusterStmt42.getTree());

					if ( state.backtracking==0 ) { retval.value = (createClusterStmt42!=null?((StatementBuilder.createClusterStmt_return)createClusterStmt42).value:null); }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 43 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:114:7: dropClusterStmt
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_dropClusterStmt_in_statement1077);
					dropClusterStmt43=dropClusterStmt();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_0, dropClusterStmt43.getTree());

					if ( state.backtracking==0 ) { retval.value = (dropClusterStmt43!=null?((StatementBuilder.dropClusterStmt_return)dropClusterStmt43).value:null); }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 44 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:115:7: showClustersStmt
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_showClustersStmt_in_statement1098);
					showClustersStmt44=showClustersStmt();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_0, showClustersStmt44.getTree());

					if ( state.backtracking==0 ) { retval.value = (showClustersStmt44!=null?((StatementBuilder.showClustersStmt_return)showClustersStmt44).value:null); }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 45 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:116:7: alterClusterStmt
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_alterClusterStmt_in_statement1118);
					alterClusterStmt45=alterClusterStmt();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_0, alterClusterStmt45.getTree());

					if ( state.backtracking==0 ) { retval.value = (alterClusterStmt45!=null?((StatementBuilder.alterClusterStmt_return)alterClusterStmt45).value:null); }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 46 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:117:7: migrateTableStmt
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_migrateTableStmt_in_statement1138);
					migrateTableStmt46=migrateTableStmt();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_0, migrateTableStmt46.getTree());

					if ( state.backtracking==0 ) { retval.value = (migrateTableStmt46!=null?((StatementBuilder.migrateTableStmt_return)migrateTableStmt46).value:null); }
					if ( state.backtracking==0 ) {
					}

					}
					break;

			}
			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "statement"


	public static class query_return extends TreeRuleReturnScope {
		public Query value;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "query"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:120:1: query returns [Query value] : ^( QUERY queryExpr ) ;
	public final StatementBuilder.query_return query() throws RecognitionException {
		StatementBuilder.query_return retval = new StatementBuilder.query_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree QUERY47=null;
		TreeRuleReturnScope queryExpr48 =null;

		CommonTree QUERY47_tree=null;

		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:121:5: ( ^( QUERY queryExpr ) )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:121:7: ^( QUERY queryExpr )
			{
			root_0 = (CommonTree)adaptor.nil();


			_last = (CommonTree)input.LT(1);
			{
			CommonTree _save_last_1 = _last;
			CommonTree _first_1 = null;
			CommonTree root_1 = (CommonTree)adaptor.nil();
			_last = (CommonTree)input.LT(1);
			QUERY47=(CommonTree)match(input,QUERY,FOLLOW_QUERY_in_query1171); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			QUERY47_tree = (CommonTree)adaptor.dupNode(QUERY47);


			root_1 = (CommonTree)adaptor.becomeRoot(QUERY47_tree, root_1);
			}

			match(input, Token.DOWN, null); if (state.failed) return retval;
			_last = (CommonTree)input.LT(1);
			pushFollow(FOLLOW_queryExpr_in_query1173);
			queryExpr48=queryExpr();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) 
			adaptor.addChild(root_1, queryExpr48.getTree());

			match(input, Token.UP, null); if (state.failed) return retval;
			adaptor.addChild(root_0, root_1);
			_last = _save_last_1;
			}


			if ( state.backtracking==0 ) { retval.value = (queryExpr48!=null?((StatementBuilder.queryExpr_return)queryExpr48).value:null); }
			if ( state.backtracking==0 ) {
			}

			}

			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "query"


	public static class queryExpr_return extends TreeRuleReturnScope {
		public Query value;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "queryExpr"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:124:1: queryExpr returns [Query value] : ( withClause )? queryBody ( orderClause )? ( limitClause )? ( offsetClause )? ;
	public final StatementBuilder.queryExpr_return queryExpr() throws RecognitionException {
		StatementBuilder.queryExpr_return retval = new StatementBuilder.queryExpr_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		TreeRuleReturnScope withClause49 =null;
		TreeRuleReturnScope queryBody50 =null;
		TreeRuleReturnScope orderClause51 =null;
		TreeRuleReturnScope limitClause52 =null;
		TreeRuleReturnScope offsetClause53 =null;


		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:125:5: ( ( withClause )? queryBody ( orderClause )? ( limitClause )? ( offsetClause )? )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:125:7: ( withClause )? queryBody ( orderClause )? ( limitClause )? ( offsetClause )?
			{
			root_0 = (CommonTree)adaptor.nil();


			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:125:7: ( withClause )?
			int alt2=2;
			int LA2_0 = input.LA(1);
			if ( (LA2_0==WITH) ) {
				alt2=1;
			}
			switch (alt2) {
				case 1 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:125:7: withClause
					{
					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_withClause_in_queryExpr1197);
					withClause49=withClause();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_0, withClause49.getTree());

					if ( state.backtracking==0 ) {
					}

					}
					break;

			}

			_last = (CommonTree)input.LT(1);
			pushFollow(FOLLOW_queryBody_in_queryExpr1206);
			queryBody50=queryBody();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) 
			adaptor.addChild(root_0, queryBody50.getTree());

			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:127:7: ( orderClause )?
			int alt3=2;
			int LA3_0 = input.LA(1);
			if ( (LA3_0==ORDER_BY) ) {
				alt3=1;
			}
			switch (alt3) {
				case 1 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:127:7: orderClause
					{
					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_orderClause_in_queryExpr1214);
					orderClause51=orderClause();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_0, orderClause51.getTree());

					if ( state.backtracking==0 ) {
					}

					}
					break;

			}

			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:128:7: ( limitClause )?
			int alt4=2;
			int LA4_0 = input.LA(1);
			if ( (LA4_0==LIMIT) ) {
				alt4=1;
			}
			switch (alt4) {
				case 1 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:128:7: limitClause
					{
					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_limitClause_in_queryExpr1223);
					limitClause52=limitClause();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_0, limitClause52.getTree());

					if ( state.backtracking==0 ) {
					}

					}
					break;

			}

			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:129:7: ( offsetClause )?
			int alt5=2;
			int LA5_0 = input.LA(1);
			if ( (LA5_0==OFFSET) ) {
				alt5=1;
			}
			switch (alt5) {
				case 1 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:129:7: offsetClause
					{
					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_offsetClause_in_queryExpr1232);
					offsetClause53=offsetClause();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_0, offsetClause53.getTree());

					if ( state.backtracking==0 ) {
					}

					}
					break;

			}

			if ( state.backtracking==0 ) { retval.value = new Query(
			            Optional.fromNullable((withClause49!=null?((StatementBuilder.withClause_return)withClause49).value:null)),
			            (queryBody50!=null?((StatementBuilder.queryBody_return)queryBody50).value:null),
			            MoreObjects.firstNonNull((orderClause51!=null?((StatementBuilder.orderClause_return)orderClause51).value:null), ImmutableList.<SortItem>of()),
			            Optional.fromNullable((limitClause52!=null?((StatementBuilder.limitClause_return)limitClause52).value:null)),
			            Optional.fromNullable((offsetClause53!=null?((StatementBuilder.offsetClause_return)offsetClause53).value:null)));
			        }
			if ( state.backtracking==0 ) {
			}

			}

			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "queryExpr"


	public static class queryBody_return extends TreeRuleReturnScope {
		public QueryBody value;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "queryBody"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:139:1: queryBody returns [QueryBody value] : ( querySpec | setOperation | tableSubquery | namedTable );
	public final StatementBuilder.queryBody_return queryBody() throws RecognitionException {
		StatementBuilder.queryBody_return retval = new StatementBuilder.queryBody_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		TreeRuleReturnScope querySpec54 =null;
		TreeRuleReturnScope setOperation55 =null;
		TreeRuleReturnScope tableSubquery56 =null;
		TreeRuleReturnScope namedTable57 =null;


		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:140:5: ( querySpec | setOperation | tableSubquery | namedTable )
			int alt6=4;
			switch ( input.LA(1) ) {
			case QUERY_SPEC:
				{
				alt6=1;
				}
				break;
			case EXCEPT:
			case INTERSECT:
			case UNION:
				{
				alt6=2;
				}
				break;
			case TABLE_SUBQUERY:
				{
				alt6=3;
				}
				break;
			case TABLE:
				{
				alt6=4;
				}
				break;
			default:
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 6, 0, input);
				throw nvae;
			}
			switch (alt6) {
				case 1 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:140:7: querySpec
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_querySpec_in_queryBody1264);
					querySpec54=querySpec();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_0, querySpec54.getTree());

					if ( state.backtracking==0 ) { retval.value = (querySpec54!=null?((StatementBuilder.querySpec_return)querySpec54).value:null); }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 2 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:141:7: setOperation
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_setOperation_in_queryBody1286);
					setOperation55=setOperation();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_0, setOperation55.getTree());

					if ( state.backtracking==0 ) { retval.value = (setOperation55!=null?((StatementBuilder.setOperation_return)setOperation55).value:null); }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 3 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:142:7: tableSubquery
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_tableSubquery_in_queryBody1305);
					tableSubquery56=tableSubquery();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_0, tableSubquery56.getTree());

					if ( state.backtracking==0 ) { retval.value = (tableSubquery56!=null?((StatementBuilder.tableSubquery_return)tableSubquery56).value:null); }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 4 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:143:7: namedTable
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_namedTable_in_queryBody1323);
					namedTable57=namedTable();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_0, namedTable57.getTree());

					if ( state.backtracking==0 ) { retval.value = (namedTable57!=null?((StatementBuilder.namedTable_return)namedTable57).value:null); }
					if ( state.backtracking==0 ) {
					}

					}
					break;

			}
			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "queryBody"


	public static class querySpec_return extends TreeRuleReturnScope {
		public QuerySpecification value;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "querySpec"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:146:1: querySpec returns [QuerySpecification value] : ^( QUERY_SPEC selectClause ( fromClause )? ( whereClause )? ( groupClause )? ( havingClause )? ( orderClause )? ( limitClause )? ( offsetClause )? ) ;
	public final StatementBuilder.querySpec_return querySpec() throws RecognitionException {
		StatementBuilder.querySpec_return retval = new StatementBuilder.querySpec_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree QUERY_SPEC58=null;
		TreeRuleReturnScope selectClause59 =null;
		TreeRuleReturnScope fromClause60 =null;
		TreeRuleReturnScope whereClause61 =null;
		TreeRuleReturnScope groupClause62 =null;
		TreeRuleReturnScope havingClause63 =null;
		TreeRuleReturnScope orderClause64 =null;
		TreeRuleReturnScope limitClause65 =null;
		TreeRuleReturnScope offsetClause66 =null;

		CommonTree QUERY_SPEC58_tree=null;

		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:147:5: ( ^( QUERY_SPEC selectClause ( fromClause )? ( whereClause )? ( groupClause )? ( havingClause )? ( orderClause )? ( limitClause )? ( offsetClause )? ) )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:147:7: ^( QUERY_SPEC selectClause ( fromClause )? ( whereClause )? ( groupClause )? ( havingClause )? ( orderClause )? ( limitClause )? ( offsetClause )? )
			{
			root_0 = (CommonTree)adaptor.nil();


			_last = (CommonTree)input.LT(1);
			{
			CommonTree _save_last_1 = _last;
			CommonTree _first_1 = null;
			CommonTree root_1 = (CommonTree)adaptor.nil();
			_last = (CommonTree)input.LT(1);
			QUERY_SPEC58=(CommonTree)match(input,QUERY_SPEC,FOLLOW_QUERY_SPEC_in_querySpec1358); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			QUERY_SPEC58_tree = (CommonTree)adaptor.dupNode(QUERY_SPEC58);


			root_1 = (CommonTree)adaptor.becomeRoot(QUERY_SPEC58_tree, root_1);
			}

			match(input, Token.DOWN, null); if (state.failed) return retval;
			_last = (CommonTree)input.LT(1);
			pushFollow(FOLLOW_selectClause_in_querySpec1368);
			selectClause59=selectClause();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) 
			adaptor.addChild(root_1, selectClause59.getTree());

			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:149:9: ( fromClause )?
			int alt7=2;
			int LA7_0 = input.LA(1);
			if ( (LA7_0==FROM) ) {
				alt7=1;
			}
			switch (alt7) {
				case 1 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:149:9: fromClause
					{
					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_fromClause_in_querySpec1378);
					fromClause60=fromClause();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, fromClause60.getTree());

					if ( state.backtracking==0 ) {
					}

					}
					break;

			}

			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:150:9: ( whereClause )?
			int alt8=2;
			int LA8_0 = input.LA(1);
			if ( (LA8_0==WHERE) ) {
				alt8=1;
			}
			switch (alt8) {
				case 1 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:150:9: whereClause
					{
					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_whereClause_in_querySpec1389);
					whereClause61=whereClause();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, whereClause61.getTree());

					if ( state.backtracking==0 ) {
					}

					}
					break;

			}

			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:151:9: ( groupClause )?
			int alt9=2;
			int LA9_0 = input.LA(1);
			if ( (LA9_0==GROUP_BY) ) {
				alt9=1;
			}
			switch (alt9) {
				case 1 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:151:9: groupClause
					{
					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_groupClause_in_querySpec1400);
					groupClause62=groupClause();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, groupClause62.getTree());

					if ( state.backtracking==0 ) {
					}

					}
					break;

			}

			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:152:9: ( havingClause )?
			int alt10=2;
			int LA10_0 = input.LA(1);
			if ( (LA10_0==HAVING) ) {
				alt10=1;
			}
			switch (alt10) {
				case 1 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:152:9: havingClause
					{
					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_havingClause_in_querySpec1411);
					havingClause63=havingClause();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, havingClause63.getTree());

					if ( state.backtracking==0 ) {
					}

					}
					break;

			}

			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:153:9: ( orderClause )?
			int alt11=2;
			int LA11_0 = input.LA(1);
			if ( (LA11_0==ORDER_BY) ) {
				alt11=1;
			}
			switch (alt11) {
				case 1 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:153:9: orderClause
					{
					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_orderClause_in_querySpec1422);
					orderClause64=orderClause();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, orderClause64.getTree());

					if ( state.backtracking==0 ) {
					}

					}
					break;

			}

			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:154:9: ( limitClause )?
			int alt12=2;
			int LA12_0 = input.LA(1);
			if ( (LA12_0==LIMIT) ) {
				alt12=1;
			}
			switch (alt12) {
				case 1 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:154:9: limitClause
					{
					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_limitClause_in_querySpec1433);
					limitClause65=limitClause();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, limitClause65.getTree());

					if ( state.backtracking==0 ) {
					}

					}
					break;

			}

			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:155:9: ( offsetClause )?
			int alt13=2;
			int LA13_0 = input.LA(1);
			if ( (LA13_0==OFFSET) ) {
				alt13=1;
			}
			switch (alt13) {
				case 1 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:155:9: offsetClause
					{
					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_offsetClause_in_querySpec1444);
					offsetClause66=offsetClause();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, offsetClause66.getTree());

					if ( state.backtracking==0 ) {
					}

					}
					break;

			}

			match(input, Token.UP, null); if (state.failed) return retval;
			adaptor.addChild(root_0, root_1);
			_last = _save_last_1;
			}


			if ( state.backtracking==0 ) { retval.value = new QuerySpecification(
			            (selectClause59!=null?((StatementBuilder.selectClause_return)selectClause59).value:null),
			            (fromClause60!=null?((StatementBuilder.fromClause_return)fromClause60).value:null),
			            Optional.fromNullable((whereClause61!=null?((StatementBuilder.whereClause_return)whereClause61).value:null)),
			            MoreObjects.firstNonNull((groupClause62!=null?((StatementBuilder.groupClause_return)groupClause62).value:null), ImmutableList.<Expression>of()),
			            Optional.fromNullable((havingClause63!=null?((StatementBuilder.havingClause_return)havingClause63).value:null)),
			            MoreObjects.firstNonNull((orderClause64!=null?((StatementBuilder.orderClause_return)orderClause64).value:null), ImmutableList.<SortItem>of()),
			            Optional.fromNullable((limitClause65!=null?((StatementBuilder.limitClause_return)limitClause65).value:null)),
			            Optional.fromNullable((offsetClause66!=null?((StatementBuilder.offsetClause_return)offsetClause66).value:null)));
			        }
			if ( state.backtracking==0 ) {
			}

			}

			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "querySpec"


	public static class setOperation_return extends TreeRuleReturnScope {
		public SetOperation value;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "setOperation"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:168:1: setOperation returns [SetOperation value] : ( ^( UNION q1= queryBody q2= queryBody d= distinct[true] ) | ^( INTERSECT q1= queryBody q2= queryBody d= distinct[true] ) | ^( EXCEPT q1= queryBody q2= queryBody d= distinct[true] ) );
	public final StatementBuilder.setOperation_return setOperation() throws RecognitionException {
		StatementBuilder.setOperation_return retval = new StatementBuilder.setOperation_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree UNION67=null;
		CommonTree INTERSECT68=null;
		CommonTree EXCEPT69=null;
		TreeRuleReturnScope q1 =null;
		TreeRuleReturnScope q2 =null;
		TreeRuleReturnScope d =null;

		CommonTree UNION67_tree=null;
		CommonTree INTERSECT68_tree=null;
		CommonTree EXCEPT69_tree=null;

		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:169:5: ( ^( UNION q1= queryBody q2= queryBody d= distinct[true] ) | ^( INTERSECT q1= queryBody q2= queryBody d= distinct[true] ) | ^( EXCEPT q1= queryBody q2= queryBody d= distinct[true] ) )
			int alt14=3;
			switch ( input.LA(1) ) {
			case UNION:
				{
				alt14=1;
				}
				break;
			case INTERSECT:
				{
				alt14=2;
				}
				break;
			case EXCEPT:
				{
				alt14=3;
				}
				break;
			default:
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 14, 0, input);
				throw nvae;
			}
			switch (alt14) {
				case 1 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:169:7: ^( UNION q1= queryBody q2= queryBody d= distinct[true] )
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					{
					CommonTree _save_last_1 = _last;
					CommonTree _first_1 = null;
					CommonTree root_1 = (CommonTree)adaptor.nil();
					_last = (CommonTree)input.LT(1);
					UNION67=(CommonTree)match(input,UNION,FOLLOW_UNION_in_setOperation1478); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					UNION67_tree = (CommonTree)adaptor.dupNode(UNION67);


					root_1 = (CommonTree)adaptor.becomeRoot(UNION67_tree, root_1);
					}

					match(input, Token.DOWN, null); if (state.failed) return retval;
					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_queryBody_in_setOperation1482);
					q1=queryBody();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, q1.getTree());

					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_queryBody_in_setOperation1486);
					q2=queryBody();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, q2.getTree());

					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_distinct_in_setOperation1490);
					d=distinct(true);
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, d.getTree());

					match(input, Token.UP, null); if (state.failed) return retval;
					adaptor.addChild(root_0, root_1);
					_last = _save_last_1;
					}


					if ( state.backtracking==0 ) { retval.value = new Union(ImmutableList.<Relation>of((q1!=null?((StatementBuilder.queryBody_return)q1).value:null), (q2!=null?((StatementBuilder.queryBody_return)q2).value:null)), (d!=null?((StatementBuilder.distinct_return)d).value:false)); }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 2 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:170:7: ^( INTERSECT q1= queryBody q2= queryBody d= distinct[true] )
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					{
					CommonTree _save_last_1 = _last;
					CommonTree _first_1 = null;
					CommonTree root_1 = (CommonTree)adaptor.nil();
					_last = (CommonTree)input.LT(1);
					INTERSECT68=(CommonTree)match(input,INTERSECT,FOLLOW_INTERSECT_in_setOperation1509); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					INTERSECT68_tree = (CommonTree)adaptor.dupNode(INTERSECT68);


					root_1 = (CommonTree)adaptor.becomeRoot(INTERSECT68_tree, root_1);
					}

					match(input, Token.DOWN, null); if (state.failed) return retval;
					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_queryBody_in_setOperation1513);
					q1=queryBody();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, q1.getTree());

					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_queryBody_in_setOperation1517);
					q2=queryBody();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, q2.getTree());

					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_distinct_in_setOperation1521);
					d=distinct(true);
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, d.getTree());

					match(input, Token.UP, null); if (state.failed) return retval;
					adaptor.addChild(root_0, root_1);
					_last = _save_last_1;
					}


					if ( state.backtracking==0 ) { retval.value = new Intersect(ImmutableList.<Relation>of((q1!=null?((StatementBuilder.queryBody_return)q1).value:null), (q2!=null?((StatementBuilder.queryBody_return)q2).value:null)), (d!=null?((StatementBuilder.distinct_return)d).value:false)); }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 3 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:171:7: ^( EXCEPT q1= queryBody q2= queryBody d= distinct[true] )
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					{
					CommonTree _save_last_1 = _last;
					CommonTree _first_1 = null;
					CommonTree root_1 = (CommonTree)adaptor.nil();
					_last = (CommonTree)input.LT(1);
					EXCEPT69=(CommonTree)match(input,EXCEPT,FOLLOW_EXCEPT_in_setOperation1536); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					EXCEPT69_tree = (CommonTree)adaptor.dupNode(EXCEPT69);


					root_1 = (CommonTree)adaptor.becomeRoot(EXCEPT69_tree, root_1);
					}

					match(input, Token.DOWN, null); if (state.failed) return retval;
					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_queryBody_in_setOperation1540);
					q1=queryBody();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, q1.getTree());

					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_queryBody_in_setOperation1544);
					q2=queryBody();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, q2.getTree());

					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_distinct_in_setOperation1548);
					d=distinct(true);
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, d.getTree());

					match(input, Token.UP, null); if (state.failed) return retval;
					adaptor.addChild(root_0, root_1);
					_last = _save_last_1;
					}


					if ( state.backtracking==0 ) { retval.value = new Except((q1!=null?((StatementBuilder.queryBody_return)q1).value:null), (q2!=null?((StatementBuilder.queryBody_return)q2).value:null), (d!=null?((StatementBuilder.distinct_return)d).value:false)); }
					if ( state.backtracking==0 ) {
					}

					}
					break;

			}
			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "setOperation"


	public static class restrictedSelectStmt_return extends TreeRuleReturnScope {
		public Query value;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "restrictedSelectStmt"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:174:1: restrictedSelectStmt returns [Query value] : selectClause fromClause ;
	public final StatementBuilder.restrictedSelectStmt_return restrictedSelectStmt() throws RecognitionException {
		StatementBuilder.restrictedSelectStmt_return retval = new StatementBuilder.restrictedSelectStmt_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		TreeRuleReturnScope selectClause70 =null;
		TreeRuleReturnScope fromClause71 =null;


		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:175:5: ( selectClause fromClause )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:175:7: selectClause fromClause
			{
			root_0 = (CommonTree)adaptor.nil();


			_last = (CommonTree)input.LT(1);
			pushFollow(FOLLOW_selectClause_in_restrictedSelectStmt1578);
			selectClause70=selectClause();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) 
			adaptor.addChild(root_0, selectClause70.getTree());

			_last = (CommonTree)input.LT(1);
			pushFollow(FOLLOW_fromClause_in_restrictedSelectStmt1580);
			fromClause71=fromClause();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) 
			adaptor.addChild(root_0, fromClause71.getTree());

			if ( state.backtracking==0 ) { retval.value = new Query(
			            Optional.<With>absent(),
			            new QuerySpecification(
			                (selectClause70!=null?((StatementBuilder.selectClause_return)selectClause70).value:null),
			                (fromClause71!=null?((StatementBuilder.fromClause_return)fromClause71).value:null),
			                Optional.<Expression>absent(),
			                ImmutableList.<Expression>of(),
			                Optional.<Expression>absent(),
			                ImmutableList.<SortItem>of(),
			                Optional.<Expression>absent(),
			                Optional.<Expression>absent()),
			            ImmutableList.<SortItem>of(),
			            Optional.<Expression>absent(),
			            Optional.<Expression>absent());
			        }
			if ( state.backtracking==0 ) {
			}

			}

			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "restrictedSelectStmt"


	public static class withClause_return extends TreeRuleReturnScope {
		public With value;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "withClause"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:193:1: withClause returns [With value] : ^( WITH recursive withList ) ;
	public final StatementBuilder.withClause_return withClause() throws RecognitionException {
		StatementBuilder.withClause_return retval = new StatementBuilder.withClause_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree WITH72=null;
		TreeRuleReturnScope recursive73 =null;
		TreeRuleReturnScope withList74 =null;

		CommonTree WITH72_tree=null;

		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:194:5: ( ^( WITH recursive withList ) )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:194:7: ^( WITH recursive withList )
			{
			root_0 = (CommonTree)adaptor.nil();


			_last = (CommonTree)input.LT(1);
			{
			CommonTree _save_last_1 = _last;
			CommonTree _first_1 = null;
			CommonTree root_1 = (CommonTree)adaptor.nil();
			_last = (CommonTree)input.LT(1);
			WITH72=(CommonTree)match(input,WITH,FOLLOW_WITH_in_withClause1612); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			WITH72_tree = (CommonTree)adaptor.dupNode(WITH72);


			root_1 = (CommonTree)adaptor.becomeRoot(WITH72_tree, root_1);
			}

			match(input, Token.DOWN, null); if (state.failed) return retval;
			_last = (CommonTree)input.LT(1);
			pushFollow(FOLLOW_recursive_in_withClause1614);
			recursive73=recursive();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) 
			adaptor.addChild(root_1, recursive73.getTree());

			_last = (CommonTree)input.LT(1);
			pushFollow(FOLLOW_withList_in_withClause1616);
			withList74=withList();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) 
			adaptor.addChild(root_1, withList74.getTree());

			match(input, Token.UP, null); if (state.failed) return retval;
			adaptor.addChild(root_0, root_1);
			_last = _save_last_1;
			}


			if ( state.backtracking==0 ) { retval.value = new With((recursive73!=null?((StatementBuilder.recursive_return)recursive73).value:false), (withList74!=null?((StatementBuilder.withList_return)withList74).value:null)); }
			if ( state.backtracking==0 ) {
			}

			}

			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "withClause"


	public static class recursive_return extends TreeRuleReturnScope {
		public boolean value;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "recursive"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:197:1: recursive returns [boolean value] : ( RECURSIVE |);
	public final StatementBuilder.recursive_return recursive() throws RecognitionException {
		StatementBuilder.recursive_return retval = new StatementBuilder.recursive_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree RECURSIVE75=null;

		CommonTree RECURSIVE75_tree=null;

		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:198:5: ( RECURSIVE |)
			int alt15=2;
			int LA15_0 = input.LA(1);
			if ( (LA15_0==RECURSIVE) ) {
				alt15=1;
			}
			else if ( (LA15_0==WITH_LIST) ) {
				alt15=2;
			}

			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 15, 0, input);
				throw nvae;
			}

			switch (alt15) {
				case 1 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:198:7: RECURSIVE
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					RECURSIVE75=(CommonTree)match(input,RECURSIVE,FOLLOW_RECURSIVE_in_recursive1640); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					RECURSIVE75_tree = (CommonTree)adaptor.dupNode(RECURSIVE75);


					adaptor.addChild(root_0, RECURSIVE75_tree);
					}

					if ( state.backtracking==0 ) { retval.value = true; }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 2 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:199:17: 
					{
					root_0 = (CommonTree)adaptor.nil();


					if ( state.backtracking==0 ) { retval.value = false; }
					if ( state.backtracking==0 ) {
					}

					}
					break;

			}
			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "recursive"


	public static class withList_return extends TreeRuleReturnScope {
		public List<WithQuery> value = new ArrayList<>();
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "withList"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:202:1: withList returns [List<WithQuery> value = new ArrayList<>()] : ^( WITH_LIST ( withQuery )+ ) ;
	public final StatementBuilder.withList_return withList() throws RecognitionException {
		StatementBuilder.withList_return retval = new StatementBuilder.withList_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree WITH_LIST76=null;
		TreeRuleReturnScope withQuery77 =null;

		CommonTree WITH_LIST76_tree=null;

		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:203:5: ( ^( WITH_LIST ( withQuery )+ ) )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:203:7: ^( WITH_LIST ( withQuery )+ )
			{
			root_0 = (CommonTree)adaptor.nil();


			_last = (CommonTree)input.LT(1);
			{
			CommonTree _save_last_1 = _last;
			CommonTree _first_1 = null;
			CommonTree root_1 = (CommonTree)adaptor.nil();
			_last = (CommonTree)input.LT(1);
			WITH_LIST76=(CommonTree)match(input,WITH_LIST,FOLLOW_WITH_LIST_in_withList1682); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			WITH_LIST76_tree = (CommonTree)adaptor.dupNode(WITH_LIST76);


			root_1 = (CommonTree)adaptor.becomeRoot(WITH_LIST76_tree, root_1);
			}

			match(input, Token.DOWN, null); if (state.failed) return retval;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:203:19: ( withQuery )+
			int cnt16=0;
			loop16:
			while (true) {
				int alt16=2;
				int LA16_0 = input.LA(1);
				if ( (LA16_0==WITH_QUERY) ) {
					alt16=1;
				}

				switch (alt16) {
				case 1 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:203:21: withQuery
					{
					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_withQuery_in_withList1686);
					withQuery77=withQuery();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, withQuery77.getTree());

					if ( state.backtracking==0 ) { retval.value.add((withQuery77!=null?((StatementBuilder.withQuery_return)withQuery77).value:null)); }
					if ( state.backtracking==0 ) {
					}

					}
					break;

				default :
					if ( cnt16 >= 1 ) break loop16;
					if (state.backtracking>0) {state.failed=true; return retval;}
					EarlyExitException eee = new EarlyExitException(16, input);
					throw eee;
				}
				cnt16++;
			}

			match(input, Token.UP, null); if (state.failed) return retval;
			adaptor.addChild(root_0, root_1);
			_last = _save_last_1;
			}


			if ( state.backtracking==0 ) {
			}

			}

			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "withList"


	public static class withQuery_return extends TreeRuleReturnScope {
		public WithQuery value;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "withQuery"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:206:1: withQuery returns [WithQuery value] : ^( WITH_QUERY i= ident q= query (c= aliasedColumns )? ) ;
	public final StatementBuilder.withQuery_return withQuery() throws RecognitionException {
		StatementBuilder.withQuery_return retval = new StatementBuilder.withQuery_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree WITH_QUERY78=null;
		TreeRuleReturnScope i =null;
		TreeRuleReturnScope q =null;
		TreeRuleReturnScope c =null;

		CommonTree WITH_QUERY78_tree=null;

		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:207:5: ( ^( WITH_QUERY i= ident q= query (c= aliasedColumns )? ) )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:207:7: ^( WITH_QUERY i= ident q= query (c= aliasedColumns )? )
			{
			root_0 = (CommonTree)adaptor.nil();


			_last = (CommonTree)input.LT(1);
			{
			CommonTree _save_last_1 = _last;
			CommonTree _first_1 = null;
			CommonTree root_1 = (CommonTree)adaptor.nil();
			_last = (CommonTree)input.LT(1);
			WITH_QUERY78=(CommonTree)match(input,WITH_QUERY,FOLLOW_WITH_QUERY_in_withQuery1715); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			WITH_QUERY78_tree = (CommonTree)adaptor.dupNode(WITH_QUERY78);


			root_1 = (CommonTree)adaptor.becomeRoot(WITH_QUERY78_tree, root_1);
			}

			match(input, Token.DOWN, null); if (state.failed) return retval;
			_last = (CommonTree)input.LT(1);
			pushFollow(FOLLOW_ident_in_withQuery1719);
			i=ident();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) 
			adaptor.addChild(root_1, i.getTree());

			_last = (CommonTree)input.LT(1);
			pushFollow(FOLLOW_query_in_withQuery1723);
			q=query();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) 
			adaptor.addChild(root_1, q.getTree());

			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:207:37: (c= aliasedColumns )?
			int alt17=2;
			int LA17_0 = input.LA(1);
			if ( (LA17_0==ALIASED_COLUMNS) ) {
				alt17=1;
			}
			switch (alt17) {
				case 1 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:207:37: c= aliasedColumns
					{
					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_aliasedColumns_in_withQuery1727);
					c=aliasedColumns();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, c.getTree());

					if ( state.backtracking==0 ) {
					}

					}
					break;

			}

			match(input, Token.UP, null); if (state.failed) return retval;
			adaptor.addChild(root_0, root_1);
			_last = _save_last_1;
			}


			if ( state.backtracking==0 ) { retval.value = new WithQuery((i!=null?((StatementBuilder.ident_return)i).value:null), (q!=null?((StatementBuilder.query_return)q).value:null), (c!=null?((StatementBuilder.aliasedColumns_return)c).value:null)); }
			if ( state.backtracking==0 ) {
			}

			}

			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "withQuery"


	public static class selectClause_return extends TreeRuleReturnScope {
		public Select value;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "selectClause"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:210:1: selectClause returns [Select value] : ^( SELECT d= distinct[false] s= selectList ) ;
	public final StatementBuilder.selectClause_return selectClause() throws RecognitionException {
		StatementBuilder.selectClause_return retval = new StatementBuilder.selectClause_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree SELECT79=null;
		TreeRuleReturnScope d =null;
		TreeRuleReturnScope s =null;

		CommonTree SELECT79_tree=null;

		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:211:5: ( ^( SELECT d= distinct[false] s= selectList ) )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:211:7: ^( SELECT d= distinct[false] s= selectList )
			{
			root_0 = (CommonTree)adaptor.nil();


			_last = (CommonTree)input.LT(1);
			{
			CommonTree _save_last_1 = _last;
			CommonTree _first_1 = null;
			CommonTree root_1 = (CommonTree)adaptor.nil();
			_last = (CommonTree)input.LT(1);
			SELECT79=(CommonTree)match(input,SELECT,FOLLOW_SELECT_in_selectClause1753); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			SELECT79_tree = (CommonTree)adaptor.dupNode(SELECT79);


			root_1 = (CommonTree)adaptor.becomeRoot(SELECT79_tree, root_1);
			}

			if ( input.LA(1)==Token.DOWN ) {
				match(input, Token.DOWN, null); if (state.failed) return retval;
				_last = (CommonTree)input.LT(1);
				pushFollow(FOLLOW_distinct_in_selectClause1757);
				d=distinct(false);
				state._fsp--;
				if (state.failed) return retval;
				if ( state.backtracking==0 ) 
				adaptor.addChild(root_1, d.getTree());

				_last = (CommonTree)input.LT(1);
				pushFollow(FOLLOW_selectList_in_selectClause1762);
				s=selectList();
				state._fsp--;
				if (state.failed) return retval;
				if ( state.backtracking==0 ) 
				adaptor.addChild(root_1, s.getTree());

				match(input, Token.UP, null); if (state.failed) return retval;
			}
			adaptor.addChild(root_0, root_1);
			_last = _save_last_1;
			}


			if ( state.backtracking==0 ) { retval.value = new Select((d!=null?((StatementBuilder.distinct_return)d).value:false), (s!=null?((StatementBuilder.selectList_return)s).value:null)); }
			if ( state.backtracking==0 ) {
			}

			}

			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "selectClause"


	public static class copyToTargetSpec_return extends TreeRuleReturnScope {
		public boolean value;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "copyToTargetSpec"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:214:1: copyToTargetSpec[boolean defaultValue] returns [boolean value] : ( DIRECTORY |);
	public final StatementBuilder.copyToTargetSpec_return copyToTargetSpec(boolean defaultValue) throws RecognitionException {
		StatementBuilder.copyToTargetSpec_return retval = new StatementBuilder.copyToTargetSpec_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree DIRECTORY80=null;

		CommonTree DIRECTORY80_tree=null;

		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:215:5: ( DIRECTORY |)
			int alt18=2;
			int LA18_0 = input.LA(1);
			if ( (LA18_0==DIRECTORY) ) {
				alt18=1;
			}
			else if ( (LA18_0==AND||(LA18_0 >= ARRAY_CMP && LA18_0 <= ARRAY_NOT_LIKE)||LA18_0==BETWEEN||LA18_0==CAST||LA18_0==COALESCE||LA18_0==CURRENT_DATE||(LA18_0 >= CURRENT_TIME && LA18_0 <= CURRENT_TIMESTAMP)||LA18_0==DATE||LA18_0==DECIMAL_VALUE||LA18_0==EQ||LA18_0==EXISTS||(LA18_0 >= EXTRACT && LA18_0 <= FALSE)||LA18_0==FUNCTION_CALL||(LA18_0 >= GT && LA18_0 <= GTE)||LA18_0==IDENT_EXPR||LA18_0==IF||LA18_0==IN||LA18_0==INTEGER_VALUE||LA18_0==IN_LIST||(LA18_0 >= IS_DISTINCT_FROM && LA18_0 <= IS_NULL)||LA18_0==LIKE||(LA18_0 >= LT && LA18_0 <= MATCH)||(LA18_0 >= NEGATIVE && LA18_0 <= NEQ)||LA18_0==NOT||(LA18_0 >= NULL && LA18_0 <= NULLIF)||LA18_0==OBJECT_LITERAL||LA18_0==OR||LA18_0==QNAME||LA18_0==QUERY||(LA18_0 >= REGEX_MATCH && LA18_0 <= REGEX_NO_MATCH_CI)||LA18_0==SEARCHED_CASE||LA18_0==SIMPLE_CASE||LA18_0==STRING||(LA18_0 >= TIME && LA18_0 <= TIMESTAMP)||(LA18_0 >= TRUE && LA18_0 <= TRY_CAST)||(LA18_0 >= 328 && LA18_0 <= 329)||(LA18_0 >= 332 && LA18_0 <= 333)||LA18_0==335||(LA18_0 >= 337 && LA18_0 <= 338)||LA18_0==341) ) {
				alt18=2;
			}

			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 18, 0, input);
				throw nvae;
			}

			switch (alt18) {
				case 1 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:215:7: DIRECTORY
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					DIRECTORY80=(CommonTree)match(input,DIRECTORY,FOLLOW_DIRECTORY_in_copyToTargetSpec1787); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					DIRECTORY80_tree = (CommonTree)adaptor.dupNode(DIRECTORY80);


					adaptor.addChild(root_0, DIRECTORY80_tree);
					}

					if ( state.backtracking==0 ) { retval.value = true; }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 2 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:216:17: 
					{
					root_0 = (CommonTree)adaptor.nil();


					if ( state.backtracking==0 ) { retval.value = defaultValue; }
					if ( state.backtracking==0 ) {
					}

					}
					break;

			}
			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "copyToTargetSpec"


	public static class distinct_return extends TreeRuleReturnScope {
		public boolean value;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "distinct"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:219:1: distinct[boolean defaultValue] returns [boolean value] : ( DISTINCT | ALL |);
	public final StatementBuilder.distinct_return distinct(boolean defaultValue) throws RecognitionException {
		StatementBuilder.distinct_return retval = new StatementBuilder.distinct_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree DISTINCT81=null;
		CommonTree ALL82=null;

		CommonTree DISTINCT81_tree=null;
		CommonTree ALL82_tree=null;

		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:220:5: ( DISTINCT | ALL |)
			int alt19=3;
			switch ( input.LA(1) ) {
			case DISTINCT:
				{
				alt19=1;
				}
				break;
			case ALL:
				{
				alt19=2;
				}
				break;
			case UP:
			case AND:
			case ARRAY_CMP:
			case ARRAY_LIKE:
			case ARRAY_LITERAL:
			case ARRAY_NOT_LIKE:
			case BETWEEN:
			case CAST:
			case COALESCE:
			case CURRENT_DATE:
			case CURRENT_TIME:
			case CURRENT_TIMESTAMP:
			case DATE:
			case DECIMAL_VALUE:
			case EQ:
			case EXISTS:
			case EXTRACT:
			case FALSE:
			case FUNCTION_CALL:
			case GT:
			case GTE:
			case IDENT_EXPR:
			case IF:
			case IN:
			case INTEGER_VALUE:
			case IN_LIST:
			case IS_DISTINCT_FROM:
			case IS_NOT_NULL:
			case IS_NULL:
			case LIKE:
			case LT:
			case LTE:
			case MATCH:
			case NEGATIVE:
			case NEQ:
			case NOT:
			case NULL:
			case NULLIF:
			case OBJECT_LITERAL:
			case OR:
			case QNAME:
			case QUERY:
			case REGEX_MATCH:
			case REGEX_MATCH_CI:
			case REGEX_NO_MATCH:
			case REGEX_NO_MATCH_CI:
			case SEARCHED_CASE:
			case SELECT_LIST:
			case SIMPLE_CASE:
			case STRING:
			case TIME:
			case TIMESTAMP:
			case TRUE:
			case TRY_CAST:
			case 328:
			case 329:
			case 332:
			case 333:
			case 335:
			case 337:
			case 338:
			case 341:
				{
				alt19=3;
				}
				break;
			default:
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 19, 0, input);
				throw nvae;
			}
			switch (alt19) {
				case 1 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:220:7: DISTINCT
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					DISTINCT81=(CommonTree)match(input,DISTINCT,FOLLOW_DISTINCT_in_distinct1829); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					DISTINCT81_tree = (CommonTree)adaptor.dupNode(DISTINCT81);


					adaptor.addChild(root_0, DISTINCT81_tree);
					}

					if ( state.backtracking==0 ) { retval.value = true; }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 2 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:221:7: ALL
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					ALL82=(CommonTree)match(input,ALL,FOLLOW_ALL_in_distinct1840); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					ALL82_tree = (CommonTree)adaptor.dupNode(ALL82);


					adaptor.addChild(root_0, ALL82_tree);
					}

					if ( state.backtracking==0 ) { retval.value = false; }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 3 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:222:17: 
					{
					root_0 = (CommonTree)adaptor.nil();


					if ( state.backtracking==0 ) { retval.value = defaultValue; }
					if ( state.backtracking==0 ) {
					}

					}
					break;

			}
			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "distinct"


	public static class selectList_return extends TreeRuleReturnScope {
		public List<SelectItem> value = new ArrayList<>();
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "selectList"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:225:1: selectList returns [List<SelectItem> value = new ArrayList<>()] : ^( SELECT_LIST ( selectItem )+ ) ;
	public final StatementBuilder.selectList_return selectList() throws RecognitionException {
		StatementBuilder.selectList_return retval = new StatementBuilder.selectList_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree SELECT_LIST83=null;
		TreeRuleReturnScope selectItem84 =null;

		CommonTree SELECT_LIST83_tree=null;

		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:226:5: ( ^( SELECT_LIST ( selectItem )+ ) )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:226:7: ^( SELECT_LIST ( selectItem )+ )
			{
			root_0 = (CommonTree)adaptor.nil();


			_last = (CommonTree)input.LT(1);
			{
			CommonTree _save_last_1 = _last;
			CommonTree _first_1 = null;
			CommonTree root_1 = (CommonTree)adaptor.nil();
			_last = (CommonTree)input.LT(1);
			SELECT_LIST83=(CommonTree)match(input,SELECT_LIST,FOLLOW_SELECT_LIST_in_selectList1888); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			SELECT_LIST83_tree = (CommonTree)adaptor.dupNode(SELECT_LIST83);


			root_1 = (CommonTree)adaptor.becomeRoot(SELECT_LIST83_tree, root_1);
			}

			match(input, Token.DOWN, null); if (state.failed) return retval;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:226:21: ( selectItem )+
			int cnt20=0;
			loop20:
			while (true) {
				int alt20=2;
				int LA20_0 = input.LA(1);
				if ( (LA20_0==ALL_COLUMNS||LA20_0==SELECT_ITEM) ) {
					alt20=1;
				}

				switch (alt20) {
				case 1 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:226:23: selectItem
					{
					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_selectItem_in_selectList1892);
					selectItem84=selectItem();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, selectItem84.getTree());

					if ( state.backtracking==0 ) { retval.value.add((selectItem84!=null?((StatementBuilder.selectItem_return)selectItem84).value:null)); }
					if ( state.backtracking==0 ) {
					}

					}
					break;

				default :
					if ( cnt20 >= 1 ) break loop20;
					if (state.backtracking>0) {state.failed=true; return retval;}
					EarlyExitException eee = new EarlyExitException(20, input);
					throw eee;
				}
				cnt20++;
			}

			match(input, Token.UP, null); if (state.failed) return retval;
			adaptor.addChild(root_0, root_1);
			_last = _save_last_1;
			}


			if ( state.backtracking==0 ) {
			}

			}

			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "selectList"


	public static class selectItem_return extends TreeRuleReturnScope {
		public SelectItem value;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "selectItem"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:229:1: selectItem returns [SelectItem value] : ( ^( SELECT_ITEM expr ( ident )? ) | ( ^( ALL_COLUMNS qname ) )=> ^( ALL_COLUMNS qname ) | ALL_COLUMNS );
	public final StatementBuilder.selectItem_return selectItem() throws RecognitionException {
		StatementBuilder.selectItem_return retval = new StatementBuilder.selectItem_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree SELECT_ITEM85=null;
		CommonTree ALL_COLUMNS88=null;
		CommonTree ALL_COLUMNS90=null;
		TreeRuleReturnScope expr86 =null;
		TreeRuleReturnScope ident87 =null;
		TreeRuleReturnScope qname89 =null;

		CommonTree SELECT_ITEM85_tree=null;
		CommonTree ALL_COLUMNS88_tree=null;
		CommonTree ALL_COLUMNS90_tree=null;

		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:230:5: ( ^( SELECT_ITEM expr ( ident )? ) | ( ^( ALL_COLUMNS qname ) )=> ^( ALL_COLUMNS qname ) | ALL_COLUMNS )
			int alt22=3;
			int LA22_0 = input.LA(1);
			if ( (LA22_0==SELECT_ITEM) ) {
				alt22=1;
			}
			else if ( (LA22_0==ALL_COLUMNS) ) {
				int LA22_2 = input.LA(2);
				if ( (LA22_2==DOWN) && (synpred1_StatementBuilder())) {
					alt22=2;
				}
				else if ( (LA22_2==UP||LA22_2==ALL_COLUMNS||LA22_2==SELECT_ITEM) ) {
					alt22=3;
				}

				else {
					if (state.backtracking>0) {state.failed=true; return retval;}
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 22, 2, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

			}

			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 22, 0, input);
				throw nvae;
			}

			switch (alt22) {
				case 1 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:231:7: ^( SELECT_ITEM expr ( ident )? )
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					{
					CommonTree _save_last_1 = _last;
					CommonTree _first_1 = null;
					CommonTree root_1 = (CommonTree)adaptor.nil();
					_last = (CommonTree)input.LT(1);
					SELECT_ITEM85=(CommonTree)match(input,SELECT_ITEM,FOLLOW_SELECT_ITEM_in_selectItem1927); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					SELECT_ITEM85_tree = (CommonTree)adaptor.dupNode(SELECT_ITEM85);


					root_1 = (CommonTree)adaptor.becomeRoot(SELECT_ITEM85_tree, root_1);
					}

					match(input, Token.DOWN, null); if (state.failed) return retval;
					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_expr_in_selectItem1929);
					expr86=expr();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, expr86.getTree());

					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:231:26: ( ident )?
					int alt21=2;
					int LA21_0 = input.LA(1);
					if ( (LA21_0==IDENT||LA21_0==QUOTED_IDENT) ) {
						alt21=1;
					}
					switch (alt21) {
						case 1 :
							// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:231:26: ident
							{
							_last = (CommonTree)input.LT(1);
							pushFollow(FOLLOW_ident_in_selectItem1931);
							ident87=ident();
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) 
							adaptor.addChild(root_1, ident87.getTree());

							if ( state.backtracking==0 ) {
							}

							}
							break;

					}

					match(input, Token.UP, null); if (state.failed) return retval;
					adaptor.addChild(root_0, root_1);
					_last = _save_last_1;
					}


					if ( state.backtracking==0 ) { retval.value = new SingleColumn((expr86!=null?((StatementBuilder.expr_return)expr86).value:null), Optional.fromNullable((ident87!=null?((StatementBuilder.ident_return)ident87).value:null))); }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 2 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:232:7: ( ^( ALL_COLUMNS qname ) )=> ^( ALL_COLUMNS qname )
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					{
					CommonTree _save_last_1 = _last;
					CommonTree _first_1 = null;
					CommonTree root_1 = (CommonTree)adaptor.nil();
					_last = (CommonTree)input.LT(1);
					ALL_COLUMNS88=(CommonTree)match(input,ALL_COLUMNS,FOLLOW_ALL_COLUMNS_in_selectItem1976); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					ALL_COLUMNS88_tree = (CommonTree)adaptor.dupNode(ALL_COLUMNS88);


					root_1 = (CommonTree)adaptor.becomeRoot(ALL_COLUMNS88_tree, root_1);
					}

					match(input, Token.DOWN, null); if (state.failed) return retval;
					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_qname_in_selectItem1978);
					qname89=qname();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, qname89.getTree());

					match(input, Token.UP, null); if (state.failed) return retval;
					adaptor.addChild(root_0, root_1);
					_last = _save_last_1;
					}


					if ( state.backtracking==0 ) { retval.value = new AllColumns((qname89!=null?((StatementBuilder.qname_return)qname89).value:null)); }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 3 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:233:7: ALL_COLUMNS
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					ALL_COLUMNS90=(CommonTree)match(input,ALL_COLUMNS,FOLLOW_ALL_COLUMNS_in_selectItem1991); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					ALL_COLUMNS90_tree = (CommonTree)adaptor.dupNode(ALL_COLUMNS90);


					adaptor.addChild(root_0, ALL_COLUMNS90_tree);
					}

					if ( state.backtracking==0 ) { retval.value = new AllColumns(); }
					if ( state.backtracking==0 ) {
					}

					}
					break;

			}
			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "selectItem"


	public static class fromClause_return extends TreeRuleReturnScope {
		public List<Relation> value;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "fromClause"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:236:1: fromClause returns [List<Relation> value] : ^( FROM t= relationList ) ;
	public final StatementBuilder.fromClause_return fromClause() throws RecognitionException {
		StatementBuilder.fromClause_return retval = new StatementBuilder.fromClause_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree FROM91=null;
		TreeRuleReturnScope t =null;

		CommonTree FROM91_tree=null;

		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:237:5: ( ^( FROM t= relationList ) )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:237:7: ^( FROM t= relationList )
			{
			root_0 = (CommonTree)adaptor.nil();


			_last = (CommonTree)input.LT(1);
			{
			CommonTree _save_last_1 = _last;
			CommonTree _first_1 = null;
			CommonTree root_1 = (CommonTree)adaptor.nil();
			_last = (CommonTree)input.LT(1);
			FROM91=(CommonTree)match(input,FROM,FOLLOW_FROM_in_fromClause2052); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			FROM91_tree = (CommonTree)adaptor.dupNode(FROM91);


			root_1 = (CommonTree)adaptor.becomeRoot(FROM91_tree, root_1);
			}

			match(input, Token.DOWN, null); if (state.failed) return retval;
			_last = (CommonTree)input.LT(1);
			pushFollow(FOLLOW_relationList_in_fromClause2056);
			t=relationList();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) 
			adaptor.addChild(root_1, t.getTree());

			match(input, Token.UP, null); if (state.failed) return retval;
			adaptor.addChild(root_0, root_1);
			_last = _save_last_1;
			}


			if ( state.backtracking==0 ) { retval.value = (t!=null?((StatementBuilder.relationList_return)t).value:null); }
			if ( state.backtracking==0 ) {
			}

			}

			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "fromClause"


	public static class whereClause_return extends TreeRuleReturnScope {
		public Expression value;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "whereClause"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:240:1: whereClause returns [Expression value] : ^( WHERE expr ) ;
	public final StatementBuilder.whereClause_return whereClause() throws RecognitionException {
		StatementBuilder.whereClause_return retval = new StatementBuilder.whereClause_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree WHERE92=null;
		TreeRuleReturnScope expr93 =null;

		CommonTree WHERE92_tree=null;

		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:241:5: ( ^( WHERE expr ) )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:241:7: ^( WHERE expr )
			{
			root_0 = (CommonTree)adaptor.nil();


			_last = (CommonTree)input.LT(1);
			{
			CommonTree _save_last_1 = _last;
			CommonTree _first_1 = null;
			CommonTree root_1 = (CommonTree)adaptor.nil();
			_last = (CommonTree)input.LT(1);
			WHERE92=(CommonTree)match(input,WHERE,FOLLOW_WHERE_in_whereClause2081); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			WHERE92_tree = (CommonTree)adaptor.dupNode(WHERE92);


			root_1 = (CommonTree)adaptor.becomeRoot(WHERE92_tree, root_1);
			}

			match(input, Token.DOWN, null); if (state.failed) return retval;
			_last = (CommonTree)input.LT(1);
			pushFollow(FOLLOW_expr_in_whereClause2083);
			expr93=expr();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) 
			adaptor.addChild(root_1, expr93.getTree());

			match(input, Token.UP, null); if (state.failed) return retval;
			adaptor.addChild(root_0, root_1);
			_last = _save_last_1;
			}


			if ( state.backtracking==0 ) { retval.value = (expr93!=null?((StatementBuilder.expr_return)expr93).value:null); }
			if ( state.backtracking==0 ) {
			}

			}

			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "whereClause"


	public static class groupClause_return extends TreeRuleReturnScope {
		public List<Expression> value;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "groupClause"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:244:1: groupClause returns [List<Expression> value] : ^( GROUP_BY exprList ) ;
	public final StatementBuilder.groupClause_return groupClause() throws RecognitionException {
		StatementBuilder.groupClause_return retval = new StatementBuilder.groupClause_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree GROUP_BY94=null;
		TreeRuleReturnScope exprList95 =null;

		CommonTree GROUP_BY94_tree=null;

		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:245:5: ( ^( GROUP_BY exprList ) )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:245:7: ^( GROUP_BY exprList )
			{
			root_0 = (CommonTree)adaptor.nil();


			_last = (CommonTree)input.LT(1);
			{
			CommonTree _save_last_1 = _last;
			CommonTree _first_1 = null;
			CommonTree root_1 = (CommonTree)adaptor.nil();
			_last = (CommonTree)input.LT(1);
			GROUP_BY94=(CommonTree)match(input,GROUP_BY,FOLLOW_GROUP_BY_in_groupClause2108); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			GROUP_BY94_tree = (CommonTree)adaptor.dupNode(GROUP_BY94);


			root_1 = (CommonTree)adaptor.becomeRoot(GROUP_BY94_tree, root_1);
			}

			if ( input.LA(1)==Token.DOWN ) {
				match(input, Token.DOWN, null); if (state.failed) return retval;
				_last = (CommonTree)input.LT(1);
				pushFollow(FOLLOW_exprList_in_groupClause2110);
				exprList95=exprList();
				state._fsp--;
				if (state.failed) return retval;
				if ( state.backtracking==0 ) 
				adaptor.addChild(root_1, exprList95.getTree());

				match(input, Token.UP, null); if (state.failed) return retval;
			}
			adaptor.addChild(root_0, root_1);
			_last = _save_last_1;
			}


			if ( state.backtracking==0 ) { retval.value = (exprList95!=null?((StatementBuilder.exprList_return)exprList95).value:null); }
			if ( state.backtracking==0 ) {
			}

			}

			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "groupClause"


	public static class havingClause_return extends TreeRuleReturnScope {
		public Expression value;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "havingClause"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:248:1: havingClause returns [Expression value] : ^( HAVING expr ) ;
	public final StatementBuilder.havingClause_return havingClause() throws RecognitionException {
		StatementBuilder.havingClause_return retval = new StatementBuilder.havingClause_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree HAVING96=null;
		TreeRuleReturnScope expr97 =null;

		CommonTree HAVING96_tree=null;

		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:249:5: ( ^( HAVING expr ) )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:249:7: ^( HAVING expr )
			{
			root_0 = (CommonTree)adaptor.nil();


			_last = (CommonTree)input.LT(1);
			{
			CommonTree _save_last_1 = _last;
			CommonTree _first_1 = null;
			CommonTree root_1 = (CommonTree)adaptor.nil();
			_last = (CommonTree)input.LT(1);
			HAVING96=(CommonTree)match(input,HAVING,FOLLOW_HAVING_in_havingClause2135); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			HAVING96_tree = (CommonTree)adaptor.dupNode(HAVING96);


			root_1 = (CommonTree)adaptor.becomeRoot(HAVING96_tree, root_1);
			}

			match(input, Token.DOWN, null); if (state.failed) return retval;
			_last = (CommonTree)input.LT(1);
			pushFollow(FOLLOW_expr_in_havingClause2137);
			expr97=expr();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) 
			adaptor.addChild(root_1, expr97.getTree());

			match(input, Token.UP, null); if (state.failed) return retval;
			adaptor.addChild(root_0, root_1);
			_last = _save_last_1;
			}


			if ( state.backtracking==0 ) { retval.value = (expr97!=null?((StatementBuilder.expr_return)expr97).value:null); }
			if ( state.backtracking==0 ) {
			}

			}

			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "havingClause"


	public static class orderClause_return extends TreeRuleReturnScope {
		public List<SortItem> value = new ArrayList<>();
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "orderClause"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:252:1: orderClause returns [List<SortItem> value = new ArrayList<>()] : ^( ORDER_BY ( sortItem )+ ) ;
	public final StatementBuilder.orderClause_return orderClause() throws RecognitionException {
		StatementBuilder.orderClause_return retval = new StatementBuilder.orderClause_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree ORDER_BY98=null;
		TreeRuleReturnScope sortItem99 =null;

		CommonTree ORDER_BY98_tree=null;

		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:253:5: ( ^( ORDER_BY ( sortItem )+ ) )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:253:7: ^( ORDER_BY ( sortItem )+ )
			{
			root_0 = (CommonTree)adaptor.nil();


			_last = (CommonTree)input.LT(1);
			{
			CommonTree _save_last_1 = _last;
			CommonTree _first_1 = null;
			CommonTree root_1 = (CommonTree)adaptor.nil();
			_last = (CommonTree)input.LT(1);
			ORDER_BY98=(CommonTree)match(input,ORDER_BY,FOLLOW_ORDER_BY_in_orderClause2162); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			ORDER_BY98_tree = (CommonTree)adaptor.dupNode(ORDER_BY98);


			root_1 = (CommonTree)adaptor.becomeRoot(ORDER_BY98_tree, root_1);
			}

			match(input, Token.DOWN, null); if (state.failed) return retval;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:253:18: ( sortItem )+
			int cnt23=0;
			loop23:
			while (true) {
				int alt23=2;
				int LA23_0 = input.LA(1);
				if ( (LA23_0==SORT_ITEM) ) {
					alt23=1;
				}

				switch (alt23) {
				case 1 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:253:20: sortItem
					{
					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_sortItem_in_orderClause2166);
					sortItem99=sortItem();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, sortItem99.getTree());

					if ( state.backtracking==0 ) { retval.value.add((sortItem99!=null?((StatementBuilder.sortItem_return)sortItem99).value:null)); }
					if ( state.backtracking==0 ) {
					}

					}
					break;

				default :
					if ( cnt23 >= 1 ) break loop23;
					if (state.backtracking>0) {state.failed=true; return retval;}
					EarlyExitException eee = new EarlyExitException(23, input);
					throw eee;
				}
				cnt23++;
			}

			match(input, Token.UP, null); if (state.failed) return retval;
			adaptor.addChild(root_0, root_1);
			_last = _save_last_1;
			}


			if ( state.backtracking==0 ) {
			}

			}

			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "orderClause"


	public static class sortItem_return extends TreeRuleReturnScope {
		public SortItem value;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "sortItem"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:256:1: sortItem returns [SortItem value] : ^( SORT_ITEM expr o= ordering n= nullOrdering ) ;
	public final StatementBuilder.sortItem_return sortItem() throws RecognitionException {
		StatementBuilder.sortItem_return retval = new StatementBuilder.sortItem_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree SORT_ITEM100=null;
		TreeRuleReturnScope o =null;
		TreeRuleReturnScope n =null;
		TreeRuleReturnScope expr101 =null;

		CommonTree SORT_ITEM100_tree=null;

		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:257:5: ( ^( SORT_ITEM expr o= ordering n= nullOrdering ) )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:257:7: ^( SORT_ITEM expr o= ordering n= nullOrdering )
			{
			root_0 = (CommonTree)adaptor.nil();


			_last = (CommonTree)input.LT(1);
			{
			CommonTree _save_last_1 = _last;
			CommonTree _first_1 = null;
			CommonTree root_1 = (CommonTree)adaptor.nil();
			_last = (CommonTree)input.LT(1);
			SORT_ITEM100=(CommonTree)match(input,SORT_ITEM,FOLLOW_SORT_ITEM_in_sortItem2195); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			SORT_ITEM100_tree = (CommonTree)adaptor.dupNode(SORT_ITEM100);


			root_1 = (CommonTree)adaptor.becomeRoot(SORT_ITEM100_tree, root_1);
			}

			match(input, Token.DOWN, null); if (state.failed) return retval;
			_last = (CommonTree)input.LT(1);
			pushFollow(FOLLOW_expr_in_sortItem2197);
			expr101=expr();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) 
			adaptor.addChild(root_1, expr101.getTree());

			_last = (CommonTree)input.LT(1);
			pushFollow(FOLLOW_ordering_in_sortItem2201);
			o=ordering();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) 
			adaptor.addChild(root_1, o.getTree());

			_last = (CommonTree)input.LT(1);
			pushFollow(FOLLOW_nullOrdering_in_sortItem2205);
			n=nullOrdering();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) 
			adaptor.addChild(root_1, n.getTree());

			match(input, Token.UP, null); if (state.failed) return retval;
			adaptor.addChild(root_0, root_1);
			_last = _save_last_1;
			}


			if ( state.backtracking==0 ) { retval.value = new SortItem((expr101!=null?((StatementBuilder.expr_return)expr101).value:null), (o!=null?((StatementBuilder.ordering_return)o).value:null), (n!=null?((StatementBuilder.nullOrdering_return)n).value:null)); }
			if ( state.backtracking==0 ) {
			}

			}

			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "sortItem"


	public static class ordering_return extends TreeRuleReturnScope {
		public SortItem.Ordering value;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "ordering"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:260:1: ordering returns [SortItem.Ordering value] : ( ASC | DESC );
	public final StatementBuilder.ordering_return ordering() throws RecognitionException {
		StatementBuilder.ordering_return retval = new StatementBuilder.ordering_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree ASC102=null;
		CommonTree DESC103=null;

		CommonTree ASC102_tree=null;
		CommonTree DESC103_tree=null;

		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:261:5: ( ASC | DESC )
			int alt24=2;
			int LA24_0 = input.LA(1);
			if ( (LA24_0==ASC) ) {
				alt24=1;
			}
			else if ( (LA24_0==DESC) ) {
				alt24=2;
			}

			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 24, 0, input);
				throw nvae;
			}

			switch (alt24) {
				case 1 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:261:7: ASC
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					ASC102=(CommonTree)match(input,ASC,FOLLOW_ASC_in_ordering2229); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					ASC102_tree = (CommonTree)adaptor.dupNode(ASC102);


					adaptor.addChild(root_0, ASC102_tree);
					}

					if ( state.backtracking==0 ) { retval.value = SortItem.Ordering.ASCENDING; }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 2 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:262:7: DESC
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					DESC103=(CommonTree)match(input,DESC,FOLLOW_DESC_in_ordering2240); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					DESC103_tree = (CommonTree)adaptor.dupNode(DESC103);


					adaptor.addChild(root_0, DESC103_tree);
					}

					if ( state.backtracking==0 ) { retval.value = SortItem.Ordering.DESCENDING; }
					if ( state.backtracking==0 ) {
					}

					}
					break;

			}
			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "ordering"


	public static class nullOrdering_return extends TreeRuleReturnScope {
		public SortItem.NullOrdering value;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "nullOrdering"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:265:1: nullOrdering returns [SortItem.NullOrdering value] : ( FIRST | LAST |);
	public final StatementBuilder.nullOrdering_return nullOrdering() throws RecognitionException {
		StatementBuilder.nullOrdering_return retval = new StatementBuilder.nullOrdering_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree FIRST104=null;
		CommonTree LAST105=null;

		CommonTree FIRST104_tree=null;
		CommonTree LAST105_tree=null;

		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:266:5: ( FIRST | LAST |)
			int alt25=3;
			switch ( input.LA(1) ) {
			case FIRST:
				{
				alt25=1;
				}
				break;
			case LAST:
				{
				alt25=2;
				}
				break;
			case UP:
				{
				alt25=3;
				}
				break;
			default:
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 25, 0, input);
				throw nvae;
			}
			switch (alt25) {
				case 1 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:266:7: FIRST
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					FIRST104=(CommonTree)match(input,FIRST,FOLLOW_FIRST_in_nullOrdering2263); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					FIRST104_tree = (CommonTree)adaptor.dupNode(FIRST104);


					adaptor.addChild(root_0, FIRST104_tree);
					}

					if ( state.backtracking==0 ) { retval.value = SortItem.NullOrdering.FIRST; }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 2 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:267:7: LAST
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					LAST105=(CommonTree)match(input,LAST,FOLLOW_LAST_in_nullOrdering2273); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					LAST105_tree = (CommonTree)adaptor.dupNode(LAST105);


					adaptor.addChild(root_0, LAST105_tree);
					}

					if ( state.backtracking==0 ) { retval.value = SortItem.NullOrdering.LAST; }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 3 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:268:13: 
					{
					root_0 = (CommonTree)adaptor.nil();


					if ( state.backtracking==0 ) { retval.value = SortItem.NullOrdering.UNDEFINED; }
					if ( state.backtracking==0 ) {
					}

					}
					break;

			}
			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "nullOrdering"


	public static class limitClause_return extends TreeRuleReturnScope {
		public Expression value;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "limitClause"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:271:1: limitClause returns [Expression value] : ( ^( LIMIT integer ) | ^( LIMIT parameterExpr ) );
	public final StatementBuilder.limitClause_return limitClause() throws RecognitionException {
		StatementBuilder.limitClause_return retval = new StatementBuilder.limitClause_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree LIMIT106=null;
		CommonTree LIMIT108=null;
		TreeRuleReturnScope integer107 =null;
		TreeRuleReturnScope parameterExpr109 =null;

		CommonTree LIMIT106_tree=null;
		CommonTree LIMIT108_tree=null;

		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:272:5: ( ^( LIMIT integer ) | ^( LIMIT parameterExpr ) )
			int alt26=2;
			int LA26_0 = input.LA(1);
			if ( (LA26_0==LIMIT) ) {
				int LA26_1 = input.LA(2);
				if ( (LA26_1==DOWN) ) {
					int LA26_2 = input.LA(3);
					if ( (LA26_2==INTEGER_VALUE) ) {
						alt26=1;
					}
					else if ( (LA26_2==328||LA26_2==338) ) {
						alt26=2;
					}

					else {
						if (state.backtracking>0) {state.failed=true; return retval;}
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 26, 2, input);
							throw nvae;
						} finally {
							input.rewind(nvaeMark);
						}
					}

				}

				else {
					if (state.backtracking>0) {state.failed=true; return retval;}
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 26, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

			}

			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 26, 0, input);
				throw nvae;
			}

			switch (alt26) {
				case 1 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:272:7: ^( LIMIT integer )
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					{
					CommonTree _save_last_1 = _last;
					CommonTree _first_1 = null;
					CommonTree root_1 = (CommonTree)adaptor.nil();
					_last = (CommonTree)input.LT(1);
					LIMIT106=(CommonTree)match(input,LIMIT,FOLLOW_LIMIT_in_limitClause2312); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					LIMIT106_tree = (CommonTree)adaptor.dupNode(LIMIT106);


					root_1 = (CommonTree)adaptor.becomeRoot(LIMIT106_tree, root_1);
					}

					match(input, Token.DOWN, null); if (state.failed) return retval;
					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_integer_in_limitClause2314);
					integer107=integer();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, integer107.getTree());

					match(input, Token.UP, null); if (state.failed) return retval;
					adaptor.addChild(root_0, root_1);
					_last = _save_last_1;
					}


					if ( state.backtracking==0 ) { retval.value = new LongLiteral((integer107!=null?((StatementBuilder.integer_return)integer107).value:null)); }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 2 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:273:7: ^( LIMIT parameterExpr )
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					{
					CommonTree _save_last_1 = _last;
					CommonTree _first_1 = null;
					CommonTree root_1 = (CommonTree)adaptor.nil();
					_last = (CommonTree)input.LT(1);
					LIMIT108=(CommonTree)match(input,LIMIT,FOLLOW_LIMIT_in_limitClause2326); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					LIMIT108_tree = (CommonTree)adaptor.dupNode(LIMIT108);


					root_1 = (CommonTree)adaptor.becomeRoot(LIMIT108_tree, root_1);
					}

					match(input, Token.DOWN, null); if (state.failed) return retval;
					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_parameterExpr_in_limitClause2328);
					parameterExpr109=parameterExpr();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, parameterExpr109.getTree());

					match(input, Token.UP, null); if (state.failed) return retval;
					adaptor.addChild(root_0, root_1);
					_last = _save_last_1;
					}


					if ( state.backtracking==0 ) { retval.value = (parameterExpr109!=null?((StatementBuilder.parameterExpr_return)parameterExpr109).value:null); }
					if ( state.backtracking==0 ) {
					}

					}
					break;

			}
			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "limitClause"


	public static class offsetClause_return extends TreeRuleReturnScope {
		public Expression value;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "offsetClause"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:276:1: offsetClause returns [Expression value] : ( ^( OFFSET integer ) | ^( OFFSET parameterExpr ) );
	public final StatementBuilder.offsetClause_return offsetClause() throws RecognitionException {
		StatementBuilder.offsetClause_return retval = new StatementBuilder.offsetClause_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree OFFSET110=null;
		CommonTree OFFSET112=null;
		TreeRuleReturnScope integer111 =null;
		TreeRuleReturnScope parameterExpr113 =null;

		CommonTree OFFSET110_tree=null;
		CommonTree OFFSET112_tree=null;

		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:277:5: ( ^( OFFSET integer ) | ^( OFFSET parameterExpr ) )
			int alt27=2;
			int LA27_0 = input.LA(1);
			if ( (LA27_0==OFFSET) ) {
				int LA27_1 = input.LA(2);
				if ( (LA27_1==DOWN) ) {
					int LA27_2 = input.LA(3);
					if ( (LA27_2==INTEGER_VALUE) ) {
						alt27=1;
					}
					else if ( (LA27_2==328||LA27_2==338) ) {
						alt27=2;
					}

					else {
						if (state.backtracking>0) {state.failed=true; return retval;}
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 27, 2, input);
							throw nvae;
						} finally {
							input.rewind(nvaeMark);
						}
					}

				}

				else {
					if (state.backtracking>0) {state.failed=true; return retval;}
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 27, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

			}

			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 27, 0, input);
				throw nvae;
			}

			switch (alt27) {
				case 1 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:277:7: ^( OFFSET integer )
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					{
					CommonTree _save_last_1 = _last;
					CommonTree _first_1 = null;
					CommonTree root_1 = (CommonTree)adaptor.nil();
					_last = (CommonTree)input.LT(1);
					OFFSET110=(CommonTree)match(input,OFFSET,FOLLOW_OFFSET_in_offsetClause2353); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					OFFSET110_tree = (CommonTree)adaptor.dupNode(OFFSET110);


					root_1 = (CommonTree)adaptor.becomeRoot(OFFSET110_tree, root_1);
					}

					match(input, Token.DOWN, null); if (state.failed) return retval;
					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_integer_in_offsetClause2355);
					integer111=integer();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, integer111.getTree());

					match(input, Token.UP, null); if (state.failed) return retval;
					adaptor.addChild(root_0, root_1);
					_last = _save_last_1;
					}


					if ( state.backtracking==0 ) { retval.value = new LongLiteral((integer111!=null?((StatementBuilder.integer_return)integer111).value:null)); }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 2 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:278:7: ^( OFFSET parameterExpr )
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					{
					CommonTree _save_last_1 = _last;
					CommonTree _first_1 = null;
					CommonTree root_1 = (CommonTree)adaptor.nil();
					_last = (CommonTree)input.LT(1);
					OFFSET112=(CommonTree)match(input,OFFSET,FOLLOW_OFFSET_in_offsetClause2367); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					OFFSET112_tree = (CommonTree)adaptor.dupNode(OFFSET112);


					root_1 = (CommonTree)adaptor.becomeRoot(OFFSET112_tree, root_1);
					}

					match(input, Token.DOWN, null); if (state.failed) return retval;
					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_parameterExpr_in_offsetClause2369);
					parameterExpr113=parameterExpr();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, parameterExpr113.getTree());

					match(input, Token.UP, null); if (state.failed) return retval;
					adaptor.addChild(root_0, root_1);
					_last = _save_last_1;
					}


					if ( state.backtracking==0 ) { retval.value = (parameterExpr113!=null?((StatementBuilder.parameterExpr_return)parameterExpr113).value:null); }
					if ( state.backtracking==0 ) {
					}

					}
					break;

			}
			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "offsetClause"


	public static class sampleType_return extends TreeRuleReturnScope {
		public SampledRelation.Type value;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "sampleType"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:281:1: sampleType returns [SampledRelation.Type value] : ( BERNOULLI | SYSTEM );
	public final StatementBuilder.sampleType_return sampleType() throws RecognitionException {
		StatementBuilder.sampleType_return retval = new StatementBuilder.sampleType_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree BERNOULLI114=null;
		CommonTree SYSTEM115=null;

		CommonTree BERNOULLI114_tree=null;
		CommonTree SYSTEM115_tree=null;

		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:282:5: ( BERNOULLI | SYSTEM )
			int alt28=2;
			int LA28_0 = input.LA(1);
			if ( (LA28_0==BERNOULLI) ) {
				alt28=1;
			}
			else if ( (LA28_0==SYSTEM) ) {
				alt28=2;
			}

			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 28, 0, input);
				throw nvae;
			}

			switch (alt28) {
				case 1 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:282:7: BERNOULLI
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					BERNOULLI114=(CommonTree)match(input,BERNOULLI,FOLLOW_BERNOULLI_in_sampleType2393); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					BERNOULLI114_tree = (CommonTree)adaptor.dupNode(BERNOULLI114);


					adaptor.addChild(root_0, BERNOULLI114_tree);
					}

					if ( state.backtracking==0 ) { retval.value = SampledRelation.Type.BERNOULLI; }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 2 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:283:7: SYSTEM
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					SYSTEM115=(CommonTree)match(input,SYSTEM,FOLLOW_SYSTEM_in_sampleType2403); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					SYSTEM115_tree = (CommonTree)adaptor.dupNode(SYSTEM115);


					adaptor.addChild(root_0, SYSTEM115_tree);
					}

					if ( state.backtracking==0 ) { retval.value = SampledRelation.Type.SYSTEM; }
					if ( state.backtracking==0 ) {
					}

					}
					break;

			}
			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "sampleType"


	public static class stratifyOn_return extends TreeRuleReturnScope {
		public List<Expression> value;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "stratifyOn"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:286:1: stratifyOn returns [List<Expression> value] : ^( STRATIFY_ON exprList ) ;
	public final StatementBuilder.stratifyOn_return stratifyOn() throws RecognitionException {
		StatementBuilder.stratifyOn_return retval = new StatementBuilder.stratifyOn_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree STRATIFY_ON116=null;
		TreeRuleReturnScope exprList117 =null;

		CommonTree STRATIFY_ON116_tree=null;

		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:287:5: ( ^( STRATIFY_ON exprList ) )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:287:7: ^( STRATIFY_ON exprList )
			{
			root_0 = (CommonTree)adaptor.nil();


			_last = (CommonTree)input.LT(1);
			{
			CommonTree _save_last_1 = _last;
			CommonTree _first_1 = null;
			CommonTree root_1 = (CommonTree)adaptor.nil();
			_last = (CommonTree)input.LT(1);
			STRATIFY_ON116=(CommonTree)match(input,STRATIFY_ON,FOLLOW_STRATIFY_ON_in_stratifyOn2430); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			STRATIFY_ON116_tree = (CommonTree)adaptor.dupNode(STRATIFY_ON116);


			root_1 = (CommonTree)adaptor.becomeRoot(STRATIFY_ON116_tree, root_1);
			}

			if ( input.LA(1)==Token.DOWN ) {
				match(input, Token.DOWN, null); if (state.failed) return retval;
				_last = (CommonTree)input.LT(1);
				pushFollow(FOLLOW_exprList_in_stratifyOn2432);
				exprList117=exprList();
				state._fsp--;
				if (state.failed) return retval;
				if ( state.backtracking==0 ) 
				adaptor.addChild(root_1, exprList117.getTree());

				match(input, Token.UP, null); if (state.failed) return retval;
			}
			adaptor.addChild(root_0, root_1);
			_last = _save_last_1;
			}


			if ( state.backtracking==0 ) { retval.value = (exprList117!=null?((StatementBuilder.exprList_return)exprList117).value:null); }
			if ( state.backtracking==0 ) {
			}

			}

			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "stratifyOn"


	public static class relationList_return extends TreeRuleReturnScope {
		public List<Relation> value = new ArrayList<>();
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "relationList"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:290:1: relationList returns [List<Relation> value = new ArrayList<>()] : ( relation )+ ;
	public final StatementBuilder.relationList_return relationList() throws RecognitionException {
		StatementBuilder.relationList_return retval = new StatementBuilder.relationList_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		TreeRuleReturnScope relation118 =null;


		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:291:5: ( ( relation )+ )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:291:7: ( relation )+
			{
			root_0 = (CommonTree)adaptor.nil();


			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:291:7: ( relation )+
			int cnt29=0;
			loop29:
			while (true) {
				int alt29=2;
				int LA29_0 = input.LA(1);
				if ( (LA29_0==ALIASED_RELATION||LA29_0==CROSS_JOIN||LA29_0==JOINED_TABLE||LA29_0==QUALIFIED_JOIN||LA29_0==SAMPLED_RELATION||LA29_0==TABLE||LA29_0==TABLE_FUNCTION||LA29_0==TABLE_SUBQUERY) ) {
					alt29=1;
				}

				switch (alt29) {
				case 1 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:291:9: relation
					{
					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_relation_in_relationList2458);
					relation118=relation();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_0, relation118.getTree());

					if ( state.backtracking==0 ) { retval.value.add((relation118!=null?((StatementBuilder.relation_return)relation118).value:null)); }
					if ( state.backtracking==0 ) {
					}

					}
					break;

				default :
					if ( cnt29 >= 1 ) break loop29;
					if (state.backtracking>0) {state.failed=true; return retval;}
					EarlyExitException eee = new EarlyExitException(29, input);
					throw eee;
				}
				cnt29++;
			}

			if ( state.backtracking==0 ) {
			}

			}

			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "relationList"


	public static class relation_return extends TreeRuleReturnScope {
		public Relation value;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "relation"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:294:1: relation returns [Relation value] : ( relationType | aliasedRelation | sampledRelation );
	public final StatementBuilder.relation_return relation() throws RecognitionException {
		StatementBuilder.relation_return retval = new StatementBuilder.relation_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		TreeRuleReturnScope relationType119 =null;
		TreeRuleReturnScope aliasedRelation120 =null;
		TreeRuleReturnScope sampledRelation121 =null;


		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:295:5: ( relationType | aliasedRelation | sampledRelation )
			int alt30=3;
			switch ( input.LA(1) ) {
			case CROSS_JOIN:
			case JOINED_TABLE:
			case QUALIFIED_JOIN:
			case TABLE:
			case TABLE_FUNCTION:
			case TABLE_SUBQUERY:
				{
				alt30=1;
				}
				break;
			case ALIASED_RELATION:
				{
				alt30=2;
				}
				break;
			case SAMPLED_RELATION:
				{
				alt30=3;
				}
				break;
			default:
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 30, 0, input);
				throw nvae;
			}
			switch (alt30) {
				case 1 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:295:7: relationType
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_relationType_in_relation2484);
					relationType119=relationType();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_0, relationType119.getTree());

					if ( state.backtracking==0 ) { retval.value = (relationType119!=null?((StatementBuilder.relationType_return)relationType119).value:null); }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 2 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:296:7: aliasedRelation
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_aliasedRelation_in_relation2499);
					aliasedRelation120=aliasedRelation();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_0, aliasedRelation120.getTree());

					if ( state.backtracking==0 ) { retval.value = (aliasedRelation120!=null?((StatementBuilder.aliasedRelation_return)aliasedRelation120).value:null); }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 3 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:297:7: sampledRelation
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_sampledRelation_in_relation2511);
					sampledRelation121=sampledRelation();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_0, sampledRelation121.getTree());

					if ( state.backtracking==0 ) { retval.value = (sampledRelation121!=null?((StatementBuilder.sampledRelation_return)sampledRelation121).value:null); }
					if ( state.backtracking==0 ) {
					}

					}
					break;

			}
			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "relation"


	public static class relationType_return extends TreeRuleReturnScope {
		public Relation value;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "relationType"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:300:1: relationType returns [Relation value] : ( namedTable | tableSubquery | joinedTable | joinRelation | tableFunction );
	public final StatementBuilder.relationType_return relationType() throws RecognitionException {
		StatementBuilder.relationType_return retval = new StatementBuilder.relationType_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		TreeRuleReturnScope namedTable122 =null;
		TreeRuleReturnScope tableSubquery123 =null;
		TreeRuleReturnScope joinedTable124 =null;
		TreeRuleReturnScope joinRelation125 =null;
		TreeRuleReturnScope tableFunction126 =null;


		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:301:5: ( namedTable | tableSubquery | joinedTable | joinRelation | tableFunction )
			int alt31=5;
			switch ( input.LA(1) ) {
			case TABLE:
				{
				alt31=1;
				}
				break;
			case TABLE_SUBQUERY:
				{
				alt31=2;
				}
				break;
			case JOINED_TABLE:
				{
				alt31=3;
				}
				break;
			case CROSS_JOIN:
			case QUALIFIED_JOIN:
				{
				alt31=4;
				}
				break;
			case TABLE_FUNCTION:
				{
				alt31=5;
				}
				break;
			default:
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 31, 0, input);
				throw nvae;
			}
			switch (alt31) {
				case 1 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:301:7: namedTable
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_namedTable_in_relationType2536);
					namedTable122=namedTable();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_0, namedTable122.getTree());

					if ( state.backtracking==0 ) { retval.value = (namedTable122!=null?((StatementBuilder.namedTable_return)namedTable122).value:null); }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 2 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:302:7: tableSubquery
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_tableSubquery_in_relationType2552);
					tableSubquery123=tableSubquery();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_0, tableSubquery123.getTree());

					if ( state.backtracking==0 ) { retval.value = (tableSubquery123!=null?((StatementBuilder.tableSubquery_return)tableSubquery123).value:null); }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 3 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:303:7: joinedTable
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_joinedTable_in_relationType2565);
					joinedTable124=joinedTable();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_0, joinedTable124.getTree());

					if ( state.backtracking==0 ) { retval.value = (joinedTable124!=null?((StatementBuilder.joinedTable_return)joinedTable124).value:null); }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 4 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:304:7: joinRelation
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_joinRelation_in_relationType2580);
					joinRelation125=joinRelation();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_0, joinRelation125.getTree());

					if ( state.backtracking==0 ) { retval.value = (joinRelation125!=null?((StatementBuilder.joinRelation_return)joinRelation125).value:null); }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 5 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:305:7: tableFunction
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_tableFunction_in_relationType2594);
					tableFunction126=tableFunction();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_0, tableFunction126.getTree());

					if ( state.backtracking==0 ) { retval.value = (tableFunction126!=null?((StatementBuilder.tableFunction_return)tableFunction126).value:null); }
					if ( state.backtracking==0 ) {
					}

					}
					break;

			}
			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "relationType"


	public static class namedTable_return extends TreeRuleReturnScope {
		public Table value;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "namedTable"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:308:1: namedTable returns [Table value] : ( ^( TABLE qname ONLY ) | ^( TABLE qname ( assignmentList )? ) );
	public final StatementBuilder.namedTable_return namedTable() throws RecognitionException {
		StatementBuilder.namedTable_return retval = new StatementBuilder.namedTable_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree TABLE127=null;
		CommonTree ONLY129=null;
		CommonTree TABLE130=null;
		TreeRuleReturnScope qname128 =null;
		TreeRuleReturnScope qname131 =null;
		TreeRuleReturnScope assignmentList132 =null;

		CommonTree TABLE127_tree=null;
		CommonTree ONLY129_tree=null;
		CommonTree TABLE130_tree=null;

		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:309:5: ( ^( TABLE qname ONLY ) | ^( TABLE qname ( assignmentList )? ) )
			int alt33=2;
			alt33 = dfa33.predict(input);
			switch (alt33) {
				case 1 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:309:7: ^( TABLE qname ONLY )
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					{
					CommonTree _save_last_1 = _last;
					CommonTree _first_1 = null;
					CommonTree root_1 = (CommonTree)adaptor.nil();
					_last = (CommonTree)input.LT(1);
					TABLE127=(CommonTree)match(input,TABLE,FOLLOW_TABLE_in_namedTable2621); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					TABLE127_tree = (CommonTree)adaptor.dupNode(TABLE127);


					root_1 = (CommonTree)adaptor.becomeRoot(TABLE127_tree, root_1);
					}

					match(input, Token.DOWN, null); if (state.failed) return retval;
					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_qname_in_namedTable2623);
					qname128=qname();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, qname128.getTree());

					_last = (CommonTree)input.LT(1);
					ONLY129=(CommonTree)match(input,ONLY,FOLLOW_ONLY_in_namedTable2625); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					ONLY129_tree = (CommonTree)adaptor.dupNode(ONLY129);


					adaptor.addChild(root_1, ONLY129_tree);
					}

					match(input, Token.UP, null); if (state.failed) return retval;
					adaptor.addChild(root_0, root_1);
					_last = _save_last_1;
					}


					if ( state.backtracking==0 ) { retval.value = new Table((qname128!=null?((StatementBuilder.qname_return)qname128).value:null), true); }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 2 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:310:7: ^( TABLE qname ( assignmentList )? )
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					{
					CommonTree _save_last_1 = _last;
					CommonTree _first_1 = null;
					CommonTree root_1 = (CommonTree)adaptor.nil();
					_last = (CommonTree)input.LT(1);
					TABLE130=(CommonTree)match(input,TABLE,FOLLOW_TABLE_in_namedTable2637); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					TABLE130_tree = (CommonTree)adaptor.dupNode(TABLE130);


					root_1 = (CommonTree)adaptor.becomeRoot(TABLE130_tree, root_1);
					}

					match(input, Token.DOWN, null); if (state.failed) return retval;
					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_qname_in_namedTable2639);
					qname131=qname();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, qname131.getTree());

					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:310:21: ( assignmentList )?
					int alt32=2;
					int LA32_0 = input.LA(1);
					if ( (LA32_0==ASSIGNMENT_LIST) ) {
						alt32=1;
					}
					switch (alt32) {
						case 1 :
							// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:310:21: assignmentList
							{
							_last = (CommonTree)input.LT(1);
							pushFollow(FOLLOW_assignmentList_in_namedTable2641);
							assignmentList132=assignmentList();
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) 
							adaptor.addChild(root_1, assignmentList132.getTree());

							if ( state.backtracking==0 ) {
							}

							}
							break;

					}

					match(input, Token.UP, null); if (state.failed) return retval;
					adaptor.addChild(root_0, root_1);
					_last = _save_last_1;
					}


					if ( state.backtracking==0 ) { retval.value = new Table((qname131!=null?((StatementBuilder.qname_return)qname131).value:null), (assignmentList132!=null?((StatementBuilder.assignmentList_return)assignmentList132).value:null)); }
					if ( state.backtracking==0 ) {
					}

					}
					break;

			}
			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "namedTable"


	public static class tableFunction_return extends TreeRuleReturnScope {
		public TableFunction value;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "tableFunction"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:313:1: tableFunction returns [TableFunction value] : ^( TABLE_FUNCTION ident exprList ) ;
	public final StatementBuilder.tableFunction_return tableFunction() throws RecognitionException {
		StatementBuilder.tableFunction_return retval = new StatementBuilder.tableFunction_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree TABLE_FUNCTION133=null;
		TreeRuleReturnScope ident134 =null;
		TreeRuleReturnScope exprList135 =null;

		CommonTree TABLE_FUNCTION133_tree=null;

		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:314:5: ( ^( TABLE_FUNCTION ident exprList ) )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:314:7: ^( TABLE_FUNCTION ident exprList )
			{
			root_0 = (CommonTree)adaptor.nil();


			_last = (CommonTree)input.LT(1);
			{
			CommonTree _save_last_1 = _last;
			CommonTree _first_1 = null;
			CommonTree root_1 = (CommonTree)adaptor.nil();
			_last = (CommonTree)input.LT(1);
			TABLE_FUNCTION133=(CommonTree)match(input,TABLE_FUNCTION,FOLLOW_TABLE_FUNCTION_in_tableFunction2667); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			TABLE_FUNCTION133_tree = (CommonTree)adaptor.dupNode(TABLE_FUNCTION133);


			root_1 = (CommonTree)adaptor.becomeRoot(TABLE_FUNCTION133_tree, root_1);
			}

			match(input, Token.DOWN, null); if (state.failed) return retval;
			_last = (CommonTree)input.LT(1);
			pushFollow(FOLLOW_ident_in_tableFunction2669);
			ident134=ident();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) 
			adaptor.addChild(root_1, ident134.getTree());

			_last = (CommonTree)input.LT(1);
			pushFollow(FOLLOW_exprList_in_tableFunction2671);
			exprList135=exprList();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) 
			adaptor.addChild(root_1, exprList135.getTree());

			match(input, Token.UP, null); if (state.failed) return retval;
			adaptor.addChild(root_0, root_1);
			_last = _save_last_1;
			}


			if ( state.backtracking==0 ) {
			            retval.value = new TableFunction((ident134!=null?((StatementBuilder.ident_return)ident134).value:null), (exprList135!=null?((StatementBuilder.exprList_return)exprList135).value:null));
			        }
			if ( state.backtracking==0 ) {
			}

			}

			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "tableFunction"


	public static class repository_return extends TreeRuleReturnScope {
		public String value;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "repository"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:320:1: repository returns [String value] : ident ;
	public final StatementBuilder.repository_return repository() throws RecognitionException {
		StatementBuilder.repository_return retval = new StatementBuilder.repository_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		TreeRuleReturnScope ident136 =null;


		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:321:5: ( ident )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:321:7: ident
			{
			root_0 = (CommonTree)adaptor.nil();


			_last = (CommonTree)input.LT(1);
			pushFollow(FOLLOW_ident_in_repository2703);
			ident136=ident();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) 
			adaptor.addChild(root_0, ident136.getTree());

			if ( state.backtracking==0 ) { retval.value = (ident136!=null?((StatementBuilder.ident_return)ident136).value:null); }
			if ( state.backtracking==0 ) {
			}

			}

			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "repository"


	public static class joinedTable_return extends TreeRuleReturnScope {
		public Relation value;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "joinedTable"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:324:1: joinedTable returns [Relation value] : ^( JOINED_TABLE relation ) ;
	public final StatementBuilder.joinedTable_return joinedTable() throws RecognitionException {
		StatementBuilder.joinedTable_return retval = new StatementBuilder.joinedTable_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree JOINED_TABLE137=null;
		TreeRuleReturnScope relation138 =null;

		CommonTree JOINED_TABLE137_tree=null;

		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:325:5: ( ^( JOINED_TABLE relation ) )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:325:7: ^( JOINED_TABLE relation )
			{
			root_0 = (CommonTree)adaptor.nil();


			_last = (CommonTree)input.LT(1);
			{
			CommonTree _save_last_1 = _last;
			CommonTree _first_1 = null;
			CommonTree root_1 = (CommonTree)adaptor.nil();
			_last = (CommonTree)input.LT(1);
			JOINED_TABLE137=(CommonTree)match(input,JOINED_TABLE,FOLLOW_JOINED_TABLE_in_joinedTable2727); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			JOINED_TABLE137_tree = (CommonTree)adaptor.dupNode(JOINED_TABLE137);


			root_1 = (CommonTree)adaptor.becomeRoot(JOINED_TABLE137_tree, root_1);
			}

			match(input, Token.DOWN, null); if (state.failed) return retval;
			_last = (CommonTree)input.LT(1);
			pushFollow(FOLLOW_relation_in_joinedTable2729);
			relation138=relation();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) 
			adaptor.addChild(root_1, relation138.getTree());

			match(input, Token.UP, null); if (state.failed) return retval;
			adaptor.addChild(root_0, root_1);
			_last = _save_last_1;
			}


			if ( state.backtracking==0 ) { retval.value = (relation138!=null?((StatementBuilder.relation_return)relation138).value:null); }
			if ( state.backtracking==0 ) {
			}

			}

			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "joinedTable"


	public static class joinRelation_return extends TreeRuleReturnScope {
		public Join value;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "joinRelation"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:328:1: joinRelation returns [Join value] : ( ^( CROSS_JOIN a= relation b= relation ) | ^( QUALIFIED_JOIN t= joinType c= joinCriteria a= relation b= relation ) );
	public final StatementBuilder.joinRelation_return joinRelation() throws RecognitionException {
		StatementBuilder.joinRelation_return retval = new StatementBuilder.joinRelation_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree CROSS_JOIN139=null;
		CommonTree QUALIFIED_JOIN140=null;
		TreeRuleReturnScope a =null;
		TreeRuleReturnScope b =null;
		TreeRuleReturnScope t =null;
		TreeRuleReturnScope c =null;

		CommonTree CROSS_JOIN139_tree=null;
		CommonTree QUALIFIED_JOIN140_tree=null;

		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:329:5: ( ^( CROSS_JOIN a= relation b= relation ) | ^( QUALIFIED_JOIN t= joinType c= joinCriteria a= relation b= relation ) )
			int alt34=2;
			int LA34_0 = input.LA(1);
			if ( (LA34_0==CROSS_JOIN) ) {
				alt34=1;
			}
			else if ( (LA34_0==QUALIFIED_JOIN) ) {
				alt34=2;
			}

			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 34, 0, input);
				throw nvae;
			}

			switch (alt34) {
				case 1 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:329:7: ^( CROSS_JOIN a= relation b= relation )
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					{
					CommonTree _save_last_1 = _last;
					CommonTree _first_1 = null;
					CommonTree root_1 = (CommonTree)adaptor.nil();
					_last = (CommonTree)input.LT(1);
					CROSS_JOIN139=(CommonTree)match(input,CROSS_JOIN,FOLLOW_CROSS_JOIN_in_joinRelation2754); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					CROSS_JOIN139_tree = (CommonTree)adaptor.dupNode(CROSS_JOIN139);


					root_1 = (CommonTree)adaptor.becomeRoot(CROSS_JOIN139_tree, root_1);
					}

					match(input, Token.DOWN, null); if (state.failed) return retval;
					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_relation_in_joinRelation2758);
					a=relation();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, a.getTree());

					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_relation_in_joinRelation2762);
					b=relation();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, b.getTree());

					match(input, Token.UP, null); if (state.failed) return retval;
					adaptor.addChild(root_0, root_1);
					_last = _save_last_1;
					}


					if ( state.backtracking==0 ) { retval.value = new Join(Join.Type.CROSS, (a!=null?((StatementBuilder.relation_return)a).value:null), (b!=null?((StatementBuilder.relation_return)b).value:null), Optional.<JoinCriteria>absent()); }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 2 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:330:7: ^( QUALIFIED_JOIN t= joinType c= joinCriteria a= relation b= relation )
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					{
					CommonTree _save_last_1 = _last;
					CommonTree _first_1 = null;
					CommonTree root_1 = (CommonTree)adaptor.nil();
					_last = (CommonTree)input.LT(1);
					QUALIFIED_JOIN140=(CommonTree)match(input,QUALIFIED_JOIN,FOLLOW_QUALIFIED_JOIN_in_joinRelation2804); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					QUALIFIED_JOIN140_tree = (CommonTree)adaptor.dupNode(QUALIFIED_JOIN140);


					root_1 = (CommonTree)adaptor.becomeRoot(QUALIFIED_JOIN140_tree, root_1);
					}

					match(input, Token.DOWN, null); if (state.failed) return retval;
					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_joinType_in_joinRelation2808);
					t=joinType();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, t.getTree());

					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_joinCriteria_in_joinRelation2812);
					c=joinCriteria();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, c.getTree());

					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_relation_in_joinRelation2816);
					a=relation();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, a.getTree());

					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_relation_in_joinRelation2820);
					b=relation();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, b.getTree());

					match(input, Token.UP, null); if (state.failed) return retval;
					adaptor.addChild(root_0, root_1);
					_last = _save_last_1;
					}


					if ( state.backtracking==0 ) { retval.value = new Join((t!=null?((StatementBuilder.joinType_return)t).value:null), (a!=null?((StatementBuilder.relation_return)a).value:null), (b!=null?((StatementBuilder.relation_return)b).value:null), Optional.fromNullable((c!=null?((StatementBuilder.joinCriteria_return)c).value:null))); }
					if ( state.backtracking==0 ) {
					}

					}
					break;

			}
			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "joinRelation"


	public static class aliasedRelation_return extends TreeRuleReturnScope {
		public AliasedRelation value;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "aliasedRelation"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:333:1: aliasedRelation returns [AliasedRelation value] : ^( ALIASED_RELATION r= relation i= ident (c= aliasedColumns )? ) ;
	public final StatementBuilder.aliasedRelation_return aliasedRelation() throws RecognitionException {
		StatementBuilder.aliasedRelation_return retval = new StatementBuilder.aliasedRelation_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree ALIASED_RELATION141=null;
		TreeRuleReturnScope r =null;
		TreeRuleReturnScope i =null;
		TreeRuleReturnScope c =null;

		CommonTree ALIASED_RELATION141_tree=null;

		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:334:5: ( ^( ALIASED_RELATION r= relation i= ident (c= aliasedColumns )? ) )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:334:7: ^( ALIASED_RELATION r= relation i= ident (c= aliasedColumns )? )
			{
			root_0 = (CommonTree)adaptor.nil();


			_last = (CommonTree)input.LT(1);
			{
			CommonTree _save_last_1 = _last;
			CommonTree _first_1 = null;
			CommonTree root_1 = (CommonTree)adaptor.nil();
			_last = (CommonTree)input.LT(1);
			ALIASED_RELATION141=(CommonTree)match(input,ALIASED_RELATION,FOLLOW_ALIASED_RELATION_in_aliasedRelation2845); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			ALIASED_RELATION141_tree = (CommonTree)adaptor.dupNode(ALIASED_RELATION141);


			root_1 = (CommonTree)adaptor.becomeRoot(ALIASED_RELATION141_tree, root_1);
			}

			match(input, Token.DOWN, null); if (state.failed) return retval;
			_last = (CommonTree)input.LT(1);
			pushFollow(FOLLOW_relation_in_aliasedRelation2849);
			r=relation();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) 
			adaptor.addChild(root_1, r.getTree());

			_last = (CommonTree)input.LT(1);
			pushFollow(FOLLOW_ident_in_aliasedRelation2853);
			i=ident();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) 
			adaptor.addChild(root_1, i.getTree());

			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:334:46: (c= aliasedColumns )?
			int alt35=2;
			int LA35_0 = input.LA(1);
			if ( (LA35_0==ALIASED_COLUMNS) ) {
				alt35=1;
			}
			switch (alt35) {
				case 1 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:334:46: c= aliasedColumns
					{
					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_aliasedColumns_in_aliasedRelation2857);
					c=aliasedColumns();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, c.getTree());

					if ( state.backtracking==0 ) {
					}

					}
					break;

			}

			match(input, Token.UP, null); if (state.failed) return retval;
			adaptor.addChild(root_0, root_1);
			_last = _save_last_1;
			}


			if ( state.backtracking==0 ) { retval.value = new AliasedRelation((r!=null?((StatementBuilder.relation_return)r).value:null), (i!=null?((StatementBuilder.ident_return)i).value:null), (c!=null?((StatementBuilder.aliasedColumns_return)c).value:null)); }
			if ( state.backtracking==0 ) {
			}

			}

			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "aliasedRelation"


	public static class sampledRelation_return extends TreeRuleReturnScope {
		public SampledRelation value;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "sampledRelation"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:337:1: sampledRelation returns [SampledRelation value] : ^( SAMPLED_RELATION r= relation t= sampleType p= expr (st= stratifyOn )? ) ;
	public final StatementBuilder.sampledRelation_return sampledRelation() throws RecognitionException {
		StatementBuilder.sampledRelation_return retval = new StatementBuilder.sampledRelation_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree SAMPLED_RELATION142=null;
		TreeRuleReturnScope r =null;
		TreeRuleReturnScope t =null;
		TreeRuleReturnScope p =null;
		TreeRuleReturnScope st =null;

		CommonTree SAMPLED_RELATION142_tree=null;

		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:338:5: ( ^( SAMPLED_RELATION r= relation t= sampleType p= expr (st= stratifyOn )? ) )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:338:7: ^( SAMPLED_RELATION r= relation t= sampleType p= expr (st= stratifyOn )? )
			{
			root_0 = (CommonTree)adaptor.nil();


			_last = (CommonTree)input.LT(1);
			{
			CommonTree _save_last_1 = _last;
			CommonTree _first_1 = null;
			CommonTree root_1 = (CommonTree)adaptor.nil();
			_last = (CommonTree)input.LT(1);
			SAMPLED_RELATION142=(CommonTree)match(input,SAMPLED_RELATION,FOLLOW_SAMPLED_RELATION_in_sampledRelation2883); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			SAMPLED_RELATION142_tree = (CommonTree)adaptor.dupNode(SAMPLED_RELATION142);


			root_1 = (CommonTree)adaptor.becomeRoot(SAMPLED_RELATION142_tree, root_1);
			}

			match(input, Token.DOWN, null); if (state.failed) return retval;
			_last = (CommonTree)input.LT(1);
			pushFollow(FOLLOW_relation_in_sampledRelation2887);
			r=relation();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) 
			adaptor.addChild(root_1, r.getTree());

			_last = (CommonTree)input.LT(1);
			pushFollow(FOLLOW_sampleType_in_sampledRelation2891);
			t=sampleType();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) 
			adaptor.addChild(root_1, t.getTree());

			_last = (CommonTree)input.LT(1);
			pushFollow(FOLLOW_expr_in_sampledRelation2895);
			p=expr();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) 
			adaptor.addChild(root_1, p.getTree());

			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:338:59: (st= stratifyOn )?
			int alt36=2;
			int LA36_0 = input.LA(1);
			if ( (LA36_0==STRATIFY_ON) ) {
				alt36=1;
			}
			switch (alt36) {
				case 1 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:338:59: st= stratifyOn
					{
					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_stratifyOn_in_sampledRelation2899);
					st=stratifyOn();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, st.getTree());

					if ( state.backtracking==0 ) {
					}

					}
					break;

			}

			match(input, Token.UP, null); if (state.failed) return retval;
			adaptor.addChild(root_0, root_1);
			_last = _save_last_1;
			}


			if ( state.backtracking==0 ) { retval.value = new SampledRelation((r!=null?((StatementBuilder.relation_return)r).value:null), (t!=null?((StatementBuilder.sampleType_return)t).value:null), (p!=null?((StatementBuilder.expr_return)p).value:null), Optional.fromNullable((st!=null?((StatementBuilder.stratifyOn_return)st).value:null))); }
			if ( state.backtracking==0 ) {
			}

			}

			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "sampledRelation"


	public static class aliasedColumns_return extends TreeRuleReturnScope {
		public List<String> value;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "aliasedColumns"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:341:1: aliasedColumns returns [List<String> value] : ^( ALIASED_COLUMNS identList ) ;
	public final StatementBuilder.aliasedColumns_return aliasedColumns() throws RecognitionException {
		StatementBuilder.aliasedColumns_return retval = new StatementBuilder.aliasedColumns_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree ALIASED_COLUMNS143=null;
		TreeRuleReturnScope identList144 =null;

		CommonTree ALIASED_COLUMNS143_tree=null;

		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:342:5: ( ^( ALIASED_COLUMNS identList ) )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:342:7: ^( ALIASED_COLUMNS identList )
			{
			root_0 = (CommonTree)adaptor.nil();


			_last = (CommonTree)input.LT(1);
			{
			CommonTree _save_last_1 = _last;
			CommonTree _first_1 = null;
			CommonTree root_1 = (CommonTree)adaptor.nil();
			_last = (CommonTree)input.LT(1);
			ALIASED_COLUMNS143=(CommonTree)match(input,ALIASED_COLUMNS,FOLLOW_ALIASED_COLUMNS_in_aliasedColumns2925); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			ALIASED_COLUMNS143_tree = (CommonTree)adaptor.dupNode(ALIASED_COLUMNS143);


			root_1 = (CommonTree)adaptor.becomeRoot(ALIASED_COLUMNS143_tree, root_1);
			}

			match(input, Token.DOWN, null); if (state.failed) return retval;
			_last = (CommonTree)input.LT(1);
			pushFollow(FOLLOW_identList_in_aliasedColumns2927);
			identList144=identList();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) 
			adaptor.addChild(root_1, identList144.getTree());

			match(input, Token.UP, null); if (state.failed) return retval;
			adaptor.addChild(root_0, root_1);
			_last = _save_last_1;
			}


			if ( state.backtracking==0 ) { retval.value = (identList144!=null?((StatementBuilder.identList_return)identList144).value:null); }
			if ( state.backtracking==0 ) {
			}

			}

			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "aliasedColumns"


	public static class joinType_return extends TreeRuleReturnScope {
		public Join.Type value;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "joinType"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:345:1: joinType returns [Join.Type value] : ( INNER_JOIN | LEFT_JOIN | RIGHT_JOIN | FULL_JOIN );
	public final StatementBuilder.joinType_return joinType() throws RecognitionException {
		StatementBuilder.joinType_return retval = new StatementBuilder.joinType_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree INNER_JOIN145=null;
		CommonTree LEFT_JOIN146=null;
		CommonTree RIGHT_JOIN147=null;
		CommonTree FULL_JOIN148=null;

		CommonTree INNER_JOIN145_tree=null;
		CommonTree LEFT_JOIN146_tree=null;
		CommonTree RIGHT_JOIN147_tree=null;
		CommonTree FULL_JOIN148_tree=null;

		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:346:5: ( INNER_JOIN | LEFT_JOIN | RIGHT_JOIN | FULL_JOIN )
			int alt37=4;
			switch ( input.LA(1) ) {
			case INNER_JOIN:
				{
				alt37=1;
				}
				break;
			case LEFT_JOIN:
				{
				alt37=2;
				}
				break;
			case RIGHT_JOIN:
				{
				alt37=3;
				}
				break;
			case FULL_JOIN:
				{
				alt37=4;
				}
				break;
			default:
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 37, 0, input);
				throw nvae;
			}
			switch (alt37) {
				case 1 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:346:7: INNER_JOIN
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					INNER_JOIN145=(CommonTree)match(input,INNER_JOIN,FOLLOW_INNER_JOIN_in_joinType2951); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					INNER_JOIN145_tree = (CommonTree)adaptor.dupNode(INNER_JOIN145);


					adaptor.addChild(root_0, INNER_JOIN145_tree);
					}

					if ( state.backtracking==0 ) { retval.value = Join.Type.INNER; }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 2 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:347:7: LEFT_JOIN
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					LEFT_JOIN146=(CommonTree)match(input,LEFT_JOIN,FOLLOW_LEFT_JOIN_in_joinType2961); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					LEFT_JOIN146_tree = (CommonTree)adaptor.dupNode(LEFT_JOIN146);


					adaptor.addChild(root_0, LEFT_JOIN146_tree);
					}

					if ( state.backtracking==0 ) { retval.value = Join.Type.LEFT; }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 3 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:348:7: RIGHT_JOIN
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					RIGHT_JOIN147=(CommonTree)match(input,RIGHT_JOIN,FOLLOW_RIGHT_JOIN_in_joinType2972); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					RIGHT_JOIN147_tree = (CommonTree)adaptor.dupNode(RIGHT_JOIN147);


					adaptor.addChild(root_0, RIGHT_JOIN147_tree);
					}

					if ( state.backtracking==0 ) { retval.value = Join.Type.RIGHT; }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 4 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:349:7: FULL_JOIN
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					FULL_JOIN148=(CommonTree)match(input,FULL_JOIN,FOLLOW_FULL_JOIN_in_joinType2982); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					FULL_JOIN148_tree = (CommonTree)adaptor.dupNode(FULL_JOIN148);


					adaptor.addChild(root_0, FULL_JOIN148_tree);
					}

					if ( state.backtracking==0 ) { retval.value = Join.Type.FULL; }
					if ( state.backtracking==0 ) {
					}

					}
					break;

			}
			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "joinType"


	public static class joinCriteria_return extends TreeRuleReturnScope {
		public JoinCriteria value;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "joinCriteria"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:352:1: joinCriteria returns [JoinCriteria value] : ( NATURAL | ^( ON expr ) | ^( USING identList ) );
	public final StatementBuilder.joinCriteria_return joinCriteria() throws RecognitionException {
		StatementBuilder.joinCriteria_return retval = new StatementBuilder.joinCriteria_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree NATURAL149=null;
		CommonTree ON150=null;
		CommonTree USING152=null;
		TreeRuleReturnScope expr151 =null;
		TreeRuleReturnScope identList153 =null;

		CommonTree NATURAL149_tree=null;
		CommonTree ON150_tree=null;
		CommonTree USING152_tree=null;

		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:353:5: ( NATURAL | ^( ON expr ) | ^( USING identList ) )
			int alt38=3;
			switch ( input.LA(1) ) {
			case NATURAL:
				{
				alt38=1;
				}
				break;
			case ON:
				{
				alt38=2;
				}
				break;
			case USING:
				{
				alt38=3;
				}
				break;
			default:
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 38, 0, input);
				throw nvae;
			}
			switch (alt38) {
				case 1 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:353:7: NATURAL
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					NATURAL149=(CommonTree)match(input,NATURAL,FOLLOW_NATURAL_in_joinCriteria3006); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					NATURAL149_tree = (CommonTree)adaptor.dupNode(NATURAL149);


					adaptor.addChild(root_0, NATURAL149_tree);
					}

					if ( state.backtracking==0 ) { retval.value = new NaturalJoin(); }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 2 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:354:7: ^( ON expr )
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					{
					CommonTree _save_last_1 = _last;
					CommonTree _first_1 = null;
					CommonTree root_1 = (CommonTree)adaptor.nil();
					_last = (CommonTree)input.LT(1);
					ON150=(CommonTree)match(input,ON,FOLLOW_ON_in_joinCriteria3028); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					ON150_tree = (CommonTree)adaptor.dupNode(ON150);


					root_1 = (CommonTree)adaptor.becomeRoot(ON150_tree, root_1);
					}

					match(input, Token.DOWN, null); if (state.failed) return retval;
					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_expr_in_joinCriteria3030);
					expr151=expr();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, expr151.getTree());

					match(input, Token.UP, null); if (state.failed) return retval;
					adaptor.addChild(root_0, root_1);
					_last = _save_last_1;
					}


					if ( state.backtracking==0 ) { retval.value = new JoinOn((expr151!=null?((StatementBuilder.expr_return)expr151).value:null)); }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 3 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:355:7: ^( USING identList )
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					{
					CommonTree _save_last_1 = _last;
					CommonTree _first_1 = null;
					CommonTree root_1 = (CommonTree)adaptor.nil();
					_last = (CommonTree)input.LT(1);
					USING152=(CommonTree)match(input,USING,FOLLOW_USING_in_joinCriteria3050); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					USING152_tree = (CommonTree)adaptor.dupNode(USING152);


					root_1 = (CommonTree)adaptor.becomeRoot(USING152_tree, root_1);
					}

					match(input, Token.DOWN, null); if (state.failed) return retval;
					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_identList_in_joinCriteria3052);
					identList153=identList();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, identList153.getTree());

					match(input, Token.UP, null); if (state.failed) return retval;
					adaptor.addChild(root_0, root_1);
					_last = _save_last_1;
					}


					if ( state.backtracking==0 ) { retval.value = new JoinUsing((identList153!=null?((StatementBuilder.identList_return)identList153).value:null)); }
					if ( state.backtracking==0 ) {
					}

					}
					break;

			}
			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "joinCriteria"


	public static class tableSubquery_return extends TreeRuleReturnScope {
		public TableSubquery value;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "tableSubquery"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:358:1: tableSubquery returns [TableSubquery value] : ^( TABLE_SUBQUERY query ) ;
	public final StatementBuilder.tableSubquery_return tableSubquery() throws RecognitionException {
		StatementBuilder.tableSubquery_return retval = new StatementBuilder.tableSubquery_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree TABLE_SUBQUERY154=null;
		TreeRuleReturnScope query155 =null;

		CommonTree TABLE_SUBQUERY154_tree=null;

		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:359:5: ( ^( TABLE_SUBQUERY query ) )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:359:7: ^( TABLE_SUBQUERY query )
			{
			root_0 = (CommonTree)adaptor.nil();


			_last = (CommonTree)input.LT(1);
			{
			CommonTree _save_last_1 = _last;
			CommonTree _first_1 = null;
			CommonTree root_1 = (CommonTree)adaptor.nil();
			_last = (CommonTree)input.LT(1);
			TABLE_SUBQUERY154=(CommonTree)match(input,TABLE_SUBQUERY,FOLLOW_TABLE_SUBQUERY_in_tableSubquery3077); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			TABLE_SUBQUERY154_tree = (CommonTree)adaptor.dupNode(TABLE_SUBQUERY154);


			root_1 = (CommonTree)adaptor.becomeRoot(TABLE_SUBQUERY154_tree, root_1);
			}

			match(input, Token.DOWN, null); if (state.failed) return retval;
			_last = (CommonTree)input.LT(1);
			pushFollow(FOLLOW_query_in_tableSubquery3079);
			query155=query();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) 
			adaptor.addChild(root_1, query155.getTree());

			match(input, Token.UP, null); if (state.failed) return retval;
			adaptor.addChild(root_0, root_1);
			_last = _save_last_1;
			}


			if ( state.backtracking==0 ) { retval.value = new TableSubquery((query155!=null?((StatementBuilder.query_return)query155).value:null)); }
			if ( state.backtracking==0 ) {
			}

			}

			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "tableSubquery"


	public static class singleExpression_return extends TreeRuleReturnScope {
		public Expression value;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "singleExpression"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:362:1: singleExpression returns [Expression value] : expr EOF ;
	public final StatementBuilder.singleExpression_return singleExpression() throws RecognitionException {
		StatementBuilder.singleExpression_return retval = new StatementBuilder.singleExpression_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree EOF157=null;
		TreeRuleReturnScope expr156 =null;

		CommonTree EOF157_tree=null;

		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:363:5: ( expr EOF )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:363:7: expr EOF
			{
			root_0 = (CommonTree)adaptor.nil();


			_last = (CommonTree)input.LT(1);
			pushFollow(FOLLOW_expr_in_singleExpression3103);
			expr156=expr();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) 
			adaptor.addChild(root_0, expr156.getTree());

			_last = (CommonTree)input.LT(1);
			EOF157=(CommonTree)match(input,EOF,FOLLOW_EOF_in_singleExpression3105); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			EOF157_tree = (CommonTree)adaptor.dupNode(EOF157);


			adaptor.addChild(root_0, EOF157_tree);
			}

			if ( state.backtracking==0 ) { retval.value = (expr156!=null?((StatementBuilder.expr_return)expr156).value:null); }
			if ( state.backtracking==0 ) {
			}

			}

			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "singleExpression"


	public static class expr_return extends TreeRuleReturnScope {
		public Expression value;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "expr"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:366:1: expr returns [Expression value] : ( parameterOrSimpleLiteral | qname | subscript | functionCall | arithmeticExpression | comparisonExpression | arrayComparisonExpression | ^( AND a= expr b= expr ) | ^( OR a= expr b= expr ) | ^( NOT e= expr ) | ^( DATE string ) | ^( TIME string ) | ^( TIMESTAMP string ) | predicate | ^( IN_LIST exprList ) | ^( NEGATIVE e= expr ) | caseExpression | query | extract | current_time | cast | tryCast | arrayLiteral | objectLiteral );
	public final StatementBuilder.expr_return expr() throws RecognitionException {
		StatementBuilder.expr_return retval = new StatementBuilder.expr_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree AND165=null;
		CommonTree OR166=null;
		CommonTree NOT167=null;
		CommonTree DATE168=null;
		CommonTree TIME170=null;
		CommonTree TIMESTAMP172=null;
		CommonTree IN_LIST175=null;
		CommonTree NEGATIVE177=null;
		TreeRuleReturnScope a =null;
		TreeRuleReturnScope b =null;
		TreeRuleReturnScope e =null;
		TreeRuleReturnScope parameterOrSimpleLiteral158 =null;
		TreeRuleReturnScope qname159 =null;
		TreeRuleReturnScope subscript160 =null;
		TreeRuleReturnScope functionCall161 =null;
		TreeRuleReturnScope arithmeticExpression162 =null;
		TreeRuleReturnScope comparisonExpression163 =null;
		TreeRuleReturnScope arrayComparisonExpression164 =null;
		TreeRuleReturnScope string169 =null;
		TreeRuleReturnScope string171 =null;
		TreeRuleReturnScope string173 =null;
		TreeRuleReturnScope predicate174 =null;
		TreeRuleReturnScope exprList176 =null;
		TreeRuleReturnScope caseExpression178 =null;
		TreeRuleReturnScope query179 =null;
		TreeRuleReturnScope extract180 =null;
		TreeRuleReturnScope current_time181 =null;
		TreeRuleReturnScope cast182 =null;
		TreeRuleReturnScope tryCast183 =null;
		TreeRuleReturnScope arrayLiteral184 =null;
		TreeRuleReturnScope objectLiteral185 =null;

		CommonTree AND165_tree=null;
		CommonTree OR166_tree=null;
		CommonTree NOT167_tree=null;
		CommonTree DATE168_tree=null;
		CommonTree TIME170_tree=null;
		CommonTree TIMESTAMP172_tree=null;
		CommonTree IN_LIST175_tree=null;
		CommonTree NEGATIVE177_tree=null;

		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:367:5: ( parameterOrSimpleLiteral | qname | subscript | functionCall | arithmeticExpression | comparisonExpression | arrayComparisonExpression | ^( AND a= expr b= expr ) | ^( OR a= expr b= expr ) | ^( NOT e= expr ) | ^( DATE string ) | ^( TIME string ) | ^( TIMESTAMP string ) | predicate | ^( IN_LIST exprList ) | ^( NEGATIVE e= expr ) | caseExpression | query | extract | current_time | cast | tryCast | arrayLiteral | objectLiteral )
			int alt39=24;
			switch ( input.LA(1) ) {
			case DECIMAL_VALUE:
			case FALSE:
			case IDENT_EXPR:
			case INTEGER_VALUE:
			case NULL:
			case STRING:
			case TRUE:
			case 328:
			case 338:
				{
				alt39=1;
				}
				break;
			case QNAME:
				{
				alt39=2;
				}
				break;
			case 341:
				{
				alt39=3;
				}
				break;
			case FUNCTION_CALL:
				{
				alt39=4;
				}
				break;
			case 329:
			case 332:
			case 333:
			case 335:
			case 337:
				{
				alt39=5;
				}
				break;
			case EQ:
			case GT:
			case GTE:
			case IS_DISTINCT_FROM:
			case LT:
			case LTE:
			case NEQ:
			case REGEX_MATCH:
			case REGEX_MATCH_CI:
			case REGEX_NO_MATCH:
			case REGEX_NO_MATCH_CI:
				{
				alt39=6;
				}
				break;
			case ARRAY_CMP:
				{
				alt39=7;
				}
				break;
			case AND:
				{
				alt39=8;
				}
				break;
			case OR:
				{
				alt39=9;
				}
				break;
			case NOT:
				{
				alt39=10;
				}
				break;
			case DATE:
				{
				alt39=11;
				}
				break;
			case TIME:
				{
				alt39=12;
				}
				break;
			case TIMESTAMP:
				{
				alt39=13;
				}
				break;
			case ARRAY_LIKE:
			case ARRAY_NOT_LIKE:
			case BETWEEN:
			case EXISTS:
			case IN:
			case IS_NOT_NULL:
			case IS_NULL:
			case LIKE:
			case MATCH:
				{
				alt39=14;
				}
				break;
			case IN_LIST:
				{
				alt39=15;
				}
				break;
			case NEGATIVE:
				{
				alt39=16;
				}
				break;
			case COALESCE:
			case IF:
			case NULLIF:
			case SEARCHED_CASE:
			case SIMPLE_CASE:
				{
				alt39=17;
				}
				break;
			case QUERY:
				{
				alt39=18;
				}
				break;
			case EXTRACT:
				{
				alt39=19;
				}
				break;
			case CURRENT_DATE:
			case CURRENT_TIME:
			case CURRENT_TIMESTAMP:
				{
				alt39=20;
				}
				break;
			case CAST:
				{
				alt39=21;
				}
				break;
			case TRY_CAST:
				{
				alt39=22;
				}
				break;
			case ARRAY_LITERAL:
				{
				alt39=23;
				}
				break;
			case OBJECT_LITERAL:
				{
				alt39=24;
				}
				break;
			default:
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 39, 0, input);
				throw nvae;
			}
			switch (alt39) {
				case 1 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:367:7: parameterOrSimpleLiteral
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_parameterOrSimpleLiteral_in_expr3128);
					parameterOrSimpleLiteral158=parameterOrSimpleLiteral();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_0, parameterOrSimpleLiteral158.getTree());

					if ( state.backtracking==0 ) { retval.value = (parameterOrSimpleLiteral158!=null?((StatementBuilder.parameterOrSimpleLiteral_return)parameterOrSimpleLiteral158).value:null); }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 2 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:368:7: qname
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_qname_in_expr3138);
					qname159=qname();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_0, qname159.getTree());

					if ( state.backtracking==0 ) { retval.value = new QualifiedNameReference((qname159!=null?((StatementBuilder.qname_return)qname159).value:null)); }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 3 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:369:7: subscript
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_subscript_in_expr3164);
					subscript160=subscript();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_0, subscript160.getTree());

					if ( state.backtracking==0 ) { retval.value = (subscript160!=null?((StatementBuilder.subscript_return)subscript160).value:null); }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 4 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:370:7: functionCall
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_functionCall_in_expr3186);
					functionCall161=functionCall();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_0, functionCall161.getTree());

					if ( state.backtracking==0 ) { retval.value = (functionCall161!=null?((StatementBuilder.functionCall_return)functionCall161).value:null); }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 5 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:371:7: arithmeticExpression
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_arithmeticExpression_in_expr3205);
					arithmeticExpression162=arithmeticExpression();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_0, arithmeticExpression162.getTree());

					if ( state.backtracking==0 ) { retval.value = (arithmeticExpression162!=null?((StatementBuilder.arithmeticExpression_return)arithmeticExpression162).value:null); }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 6 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:372:7: comparisonExpression
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_comparisonExpression_in_expr3216);
					comparisonExpression163=comparisonExpression();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_0, comparisonExpression163.getTree());

					if ( state.backtracking==0 ) { retval.value = (comparisonExpression163!=null?((StatementBuilder.comparisonExpression_return)comparisonExpression163).value:null); }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 7 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:373:7: arrayComparisonExpression
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_arrayComparisonExpression_in_expr3227);
					arrayComparisonExpression164=arrayComparisonExpression();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_0, arrayComparisonExpression164.getTree());

					if ( state.backtracking==0 ) { retval.value = (arrayComparisonExpression164!=null?((StatementBuilder.arrayComparisonExpression_return)arrayComparisonExpression164).value:null); }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 8 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:374:7: ^( AND a= expr b= expr )
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					{
					CommonTree _save_last_1 = _last;
					CommonTree _first_1 = null;
					CommonTree root_1 = (CommonTree)adaptor.nil();
					_last = (CommonTree)input.LT(1);
					AND165=(CommonTree)match(input,AND,FOLLOW_AND_in_expr3238); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					AND165_tree = (CommonTree)adaptor.dupNode(AND165);


					root_1 = (CommonTree)adaptor.becomeRoot(AND165_tree, root_1);
					}

					match(input, Token.DOWN, null); if (state.failed) return retval;
					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_expr_in_expr3242);
					a=expr();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, a.getTree());

					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_expr_in_expr3246);
					b=expr();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, b.getTree());

					match(input, Token.UP, null); if (state.failed) return retval;
					adaptor.addChild(root_0, root_1);
					_last = _save_last_1;
					}


					if ( state.backtracking==0 ) { retval.value = LogicalBinaryExpression.and((a!=null?((StatementBuilder.expr_return)a).value:null), (b!=null?((StatementBuilder.expr_return)b).value:null)); }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 9 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:375:7: ^( OR a= expr b= expr )
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					{
					CommonTree _save_last_1 = _last;
					CommonTree _first_1 = null;
					CommonTree root_1 = (CommonTree)adaptor.nil();
					_last = (CommonTree)input.LT(1);
					OR166=(CommonTree)match(input,OR,FOLLOW_OR_in_expr3259); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					OR166_tree = (CommonTree)adaptor.dupNode(OR166);


					root_1 = (CommonTree)adaptor.becomeRoot(OR166_tree, root_1);
					}

					match(input, Token.DOWN, null); if (state.failed) return retval;
					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_expr_in_expr3263);
					a=expr();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, a.getTree());

					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_expr_in_expr3267);
					b=expr();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, b.getTree());

					match(input, Token.UP, null); if (state.failed) return retval;
					adaptor.addChild(root_0, root_1);
					_last = _save_last_1;
					}


					if ( state.backtracking==0 ) { retval.value = LogicalBinaryExpression.or((a!=null?((StatementBuilder.expr_return)a).value:null), (b!=null?((StatementBuilder.expr_return)b).value:null)); }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 10 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:376:7: ^( NOT e= expr )
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					{
					CommonTree _save_last_1 = _last;
					CommonTree _first_1 = null;
					CommonTree root_1 = (CommonTree)adaptor.nil();
					_last = (CommonTree)input.LT(1);
					NOT167=(CommonTree)match(input,NOT,FOLLOW_NOT_in_expr3281); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					NOT167_tree = (CommonTree)adaptor.dupNode(NOT167);


					root_1 = (CommonTree)adaptor.becomeRoot(NOT167_tree, root_1);
					}

					match(input, Token.DOWN, null); if (state.failed) return retval;
					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_expr_in_expr3285);
					e=expr();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, e.getTree());

					match(input, Token.UP, null); if (state.failed) return retval;
					adaptor.addChild(root_0, root_1);
					_last = _save_last_1;
					}


					if ( state.backtracking==0 ) { retval.value = new NotExpression((e!=null?((StatementBuilder.expr_return)e).value:null)); }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 11 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:377:7: ^( DATE string )
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					{
					CommonTree _save_last_1 = _last;
					CommonTree _first_1 = null;
					CommonTree root_1 = (CommonTree)adaptor.nil();
					_last = (CommonTree)input.LT(1);
					DATE168=(CommonTree)match(input,DATE,FOLLOW_DATE_in_expr3305); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					DATE168_tree = (CommonTree)adaptor.dupNode(DATE168);


					root_1 = (CommonTree)adaptor.becomeRoot(DATE168_tree, root_1);
					}

					match(input, Token.DOWN, null); if (state.failed) return retval;
					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_string_in_expr3307);
					string169=string();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, string169.getTree());

					match(input, Token.UP, null); if (state.failed) return retval;
					adaptor.addChild(root_0, root_1);
					_last = _save_last_1;
					}


					if ( state.backtracking==0 ) { retval.value = new DateLiteral((string169!=null?((StatementBuilder.string_return)string169).value:null)); }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 12 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:378:7: ^( TIME string )
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					{
					CommonTree _save_last_1 = _last;
					CommonTree _first_1 = null;
					CommonTree root_1 = (CommonTree)adaptor.nil();
					_last = (CommonTree)input.LT(1);
					TIME170=(CommonTree)match(input,TIME,FOLLOW_TIME_in_expr3326); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					TIME170_tree = (CommonTree)adaptor.dupNode(TIME170);


					root_1 = (CommonTree)adaptor.becomeRoot(TIME170_tree, root_1);
					}

					match(input, Token.DOWN, null); if (state.failed) return retval;
					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_string_in_expr3328);
					string171=string();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, string171.getTree());

					match(input, Token.UP, null); if (state.failed) return retval;
					adaptor.addChild(root_0, root_1);
					_last = _save_last_1;
					}


					if ( state.backtracking==0 ) { retval.value = new TimeLiteral((string171!=null?((StatementBuilder.string_return)string171).value:null)); }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 13 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:379:7: ^( TIMESTAMP string )
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					{
					CommonTree _save_last_1 = _last;
					CommonTree _first_1 = null;
					CommonTree root_1 = (CommonTree)adaptor.nil();
					_last = (CommonTree)input.LT(1);
					TIMESTAMP172=(CommonTree)match(input,TIMESTAMP,FOLLOW_TIMESTAMP_in_expr3347); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					TIMESTAMP172_tree = (CommonTree)adaptor.dupNode(TIMESTAMP172);


					root_1 = (CommonTree)adaptor.becomeRoot(TIMESTAMP172_tree, root_1);
					}

					match(input, Token.DOWN, null); if (state.failed) return retval;
					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_string_in_expr3349);
					string173=string();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, string173.getTree());

					match(input, Token.UP, null); if (state.failed) return retval;
					adaptor.addChild(root_0, root_1);
					_last = _save_last_1;
					}


					if ( state.backtracking==0 ) { retval.value = new TimestampLiteral((string173!=null?((StatementBuilder.string_return)string173).value:null)); }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 14 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:380:7: predicate
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_predicate_in_expr3362);
					predicate174=predicate();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_0, predicate174.getTree());

					if ( state.backtracking==0 ) { retval.value = (predicate174!=null?((StatementBuilder.predicate_return)predicate174).value:null); }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 15 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:381:7: ^( IN_LIST exprList )
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					{
					CommonTree _save_last_1 = _last;
					CommonTree _first_1 = null;
					CommonTree root_1 = (CommonTree)adaptor.nil();
					_last = (CommonTree)input.LT(1);
					IN_LIST175=(CommonTree)match(input,IN_LIST,FOLLOW_IN_LIST_in_expr3385); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					IN_LIST175_tree = (CommonTree)adaptor.dupNode(IN_LIST175);


					root_1 = (CommonTree)adaptor.becomeRoot(IN_LIST175_tree, root_1);
					}

					if ( input.LA(1)==Token.DOWN ) {
						match(input, Token.DOWN, null); if (state.failed) return retval;
						_last = (CommonTree)input.LT(1);
						pushFollow(FOLLOW_exprList_in_expr3387);
						exprList176=exprList();
						state._fsp--;
						if (state.failed) return retval;
						if ( state.backtracking==0 ) 
						adaptor.addChild(root_1, exprList176.getTree());

						match(input, Token.UP, null); if (state.failed) return retval;
					}
					adaptor.addChild(root_0, root_1);
					_last = _save_last_1;
					}


					if ( state.backtracking==0 ) { retval.value = new InListExpression((exprList176!=null?((StatementBuilder.exprList_return)exprList176).value:null)); }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 16 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:382:7: ^( NEGATIVE e= expr )
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					{
					CommonTree _save_last_1 = _last;
					CommonTree _first_1 = null;
					CommonTree root_1 = (CommonTree)adaptor.nil();
					_last = (CommonTree)input.LT(1);
					NEGATIVE177=(CommonTree)match(input,NEGATIVE,FOLLOW_NEGATIVE_in_expr3401); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					NEGATIVE177_tree = (CommonTree)adaptor.dupNode(NEGATIVE177);


					root_1 = (CommonTree)adaptor.becomeRoot(NEGATIVE177_tree, root_1);
					}

					match(input, Token.DOWN, null); if (state.failed) return retval;
					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_expr_in_expr3405);
					e=expr();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, e.getTree());

					match(input, Token.UP, null); if (state.failed) return retval;
					adaptor.addChild(root_0, root_1);
					_last = _save_last_1;
					}


					if ( state.backtracking==0 ) { retval.value = new NegativeExpression((e!=null?((StatementBuilder.expr_return)e).value:null)); }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 17 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:383:7: caseExpression
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_caseExpression_in_expr3419);
					caseExpression178=caseExpression();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_0, caseExpression178.getTree());

					if ( state.backtracking==0 ) { retval.value = (caseExpression178!=null?((StatementBuilder.caseExpression_return)caseExpression178).value:null); }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 18 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:384:7: query
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_query_in_expr3436);
					query179=query();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_0, query179.getTree());

					if ( state.backtracking==0 ) { retval.value = new SubqueryExpression((query179!=null?((StatementBuilder.query_return)query179).value:null)); }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 19 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:385:7: extract
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_extract_in_expr3462);
					extract180=extract();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_0, extract180.getTree());

					if ( state.backtracking==0 ) { retval.value = (extract180!=null?((StatementBuilder.extract_return)extract180).value:null); }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 20 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:386:7: current_time
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_current_time_in_expr3486);
					current_time181=current_time();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_0, current_time181.getTree());

					if ( state.backtracking==0 ) { retval.value = (current_time181!=null?((StatementBuilder.current_time_return)current_time181).value:null); }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 21 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:387:7: cast
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_cast_in_expr3505);
					cast182=cast();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_0, cast182.getTree());

					if ( state.backtracking==0 ) { retval.value = (cast182!=null?((StatementBuilder.cast_return)cast182).value:null); }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 22 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:388:7: tryCast
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_tryCast_in_expr3532);
					tryCast183=tryCast();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_0, tryCast183.getTree());

					if ( state.backtracking==0 ) { retval.value = (tryCast183!=null?((StatementBuilder.tryCast_return)tryCast183).value:null); }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 23 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:389:7: arrayLiteral
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_arrayLiteral_in_expr3556);
					arrayLiteral184=arrayLiteral();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_0, arrayLiteral184.getTree());

					if ( state.backtracking==0 ) { retval.value = (arrayLiteral184!=null?((StatementBuilder.arrayLiteral_return)arrayLiteral184).value:null); }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 24 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:390:7: objectLiteral
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_objectLiteral_in_expr3575);
					objectLiteral185=objectLiteral();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_0, objectLiteral185.getTree());

					if ( state.backtracking==0 ) { retval.value = (objectLiteral185!=null?((StatementBuilder.objectLiteral_return)objectLiteral185).value:null); }
					if ( state.backtracking==0 ) {
					}

					}
					break;

			}
			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "expr"


	public static class exprList_return extends TreeRuleReturnScope {
		public List<Expression> value = new ArrayList<>();
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "exprList"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:393:1: exprList returns [List<Expression> value = new ArrayList<>()] : ( expr )* ;
	public final StatementBuilder.exprList_return exprList() throws RecognitionException {
		StatementBuilder.exprList_return retval = new StatementBuilder.exprList_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		TreeRuleReturnScope expr186 =null;


		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:394:5: ( ( expr )* )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:394:7: ( expr )*
			{
			root_0 = (CommonTree)adaptor.nil();


			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:394:7: ( expr )*
			loop40:
			while (true) {
				int alt40=2;
				int LA40_0 = input.LA(1);
				if ( (LA40_0==AND||(LA40_0 >= ARRAY_CMP && LA40_0 <= ARRAY_NOT_LIKE)||LA40_0==BETWEEN||LA40_0==CAST||LA40_0==COALESCE||LA40_0==CURRENT_DATE||(LA40_0 >= CURRENT_TIME && LA40_0 <= CURRENT_TIMESTAMP)||LA40_0==DATE||LA40_0==DECIMAL_VALUE||LA40_0==EQ||LA40_0==EXISTS||(LA40_0 >= EXTRACT && LA40_0 <= FALSE)||LA40_0==FUNCTION_CALL||(LA40_0 >= GT && LA40_0 <= GTE)||LA40_0==IDENT_EXPR||LA40_0==IF||LA40_0==IN||LA40_0==INTEGER_VALUE||LA40_0==IN_LIST||(LA40_0 >= IS_DISTINCT_FROM && LA40_0 <= IS_NULL)||LA40_0==LIKE||(LA40_0 >= LT && LA40_0 <= MATCH)||(LA40_0 >= NEGATIVE && LA40_0 <= NEQ)||LA40_0==NOT||(LA40_0 >= NULL && LA40_0 <= NULLIF)||LA40_0==OBJECT_LITERAL||LA40_0==OR||LA40_0==QNAME||LA40_0==QUERY||(LA40_0 >= REGEX_MATCH && LA40_0 <= REGEX_NO_MATCH_CI)||LA40_0==SEARCHED_CASE||LA40_0==SIMPLE_CASE||LA40_0==STRING||(LA40_0 >= TIME && LA40_0 <= TIMESTAMP)||(LA40_0 >= TRUE && LA40_0 <= TRY_CAST)||(LA40_0 >= 328 && LA40_0 <= 329)||(LA40_0 >= 332 && LA40_0 <= 333)||LA40_0==335||(LA40_0 >= 337 && LA40_0 <= 338)||LA40_0==341) ) {
					alt40=1;
				}

				switch (alt40) {
				case 1 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:394:9: expr
					{
					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_expr_in_exprList3608);
					expr186=expr();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_0, expr186.getTree());

					if ( state.backtracking==0 ) { retval.value.add((expr186!=null?((StatementBuilder.expr_return)expr186).value:null)); }
					if ( state.backtracking==0 ) {
					}

					}
					break;

				default :
					break loop40;
				}
			}

			if ( state.backtracking==0 ) {
			}

			}

			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "exprList"


	public static class parameterExpr_return extends TreeRuleReturnScope {
		public ParameterExpression value;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "parameterExpr"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:397:1: parameterExpr returns [ParameterExpression value] : ( '$' integer | '?' );
	public final StatementBuilder.parameterExpr_return parameterExpr() throws RecognitionException {
		StatementBuilder.parameterExpr_return retval = new StatementBuilder.parameterExpr_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree char_literal187=null;
		CommonTree char_literal189=null;
		TreeRuleReturnScope integer188 =null;

		CommonTree char_literal187_tree=null;
		CommonTree char_literal189_tree=null;

		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:398:5: ( '$' integer | '?' )
			int alt41=2;
			int LA41_0 = input.LA(1);
			if ( (LA41_0==328) ) {
				alt41=1;
			}
			else if ( (LA41_0==338) ) {
				alt41=2;
			}

			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 41, 0, input);
				throw nvae;
			}

			switch (alt41) {
				case 1 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:398:7: '$' integer
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					char_literal187=(CommonTree)match(input,328,FOLLOW_328_in_parameterExpr3634); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					char_literal187_tree = (CommonTree)adaptor.dupNode(char_literal187);


					adaptor.addChild(root_0, char_literal187_tree);
					}

					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_integer_in_parameterExpr3636);
					integer188=integer();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_0, integer188.getTree());

					if ( state.backtracking==0 ) { retval.value = new ParameterExpression(Integer.parseInt((integer188!=null?((StatementBuilder.integer_return)integer188).value:null))); }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 2 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:399:7: '?'
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					char_literal189=(CommonTree)match(input,338,FOLLOW_338_in_parameterExpr3646); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					char_literal189_tree = (CommonTree)adaptor.dupNode(char_literal189);


					adaptor.addChild(root_0, char_literal189_tree);
					}

					if ( state.backtracking==0 ) { retval.value = new ParameterExpression(parameterPos++); }
					if ( state.backtracking==0 ) {
					}

					}
					break;

			}
			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "parameterExpr"


	public static class parameterOrSimpleLiteral_return extends TreeRuleReturnScope {
		public Expression value;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "parameterOrSimpleLiteral"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:402:1: parameterOrSimpleLiteral returns [Expression value] : ( NULL | parameterExpr | string | integer | decimal | TRUE | FALSE | ^( IDENT_EXPR ident ) );
	public final StatementBuilder.parameterOrSimpleLiteral_return parameterOrSimpleLiteral() throws RecognitionException {
		StatementBuilder.parameterOrSimpleLiteral_return retval = new StatementBuilder.parameterOrSimpleLiteral_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree NULL190=null;
		CommonTree TRUE195=null;
		CommonTree FALSE196=null;
		CommonTree IDENT_EXPR197=null;
		TreeRuleReturnScope parameterExpr191 =null;
		TreeRuleReturnScope string192 =null;
		TreeRuleReturnScope integer193 =null;
		TreeRuleReturnScope decimal194 =null;
		TreeRuleReturnScope ident198 =null;

		CommonTree NULL190_tree=null;
		CommonTree TRUE195_tree=null;
		CommonTree FALSE196_tree=null;
		CommonTree IDENT_EXPR197_tree=null;

		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:403:5: ( NULL | parameterExpr | string | integer | decimal | TRUE | FALSE | ^( IDENT_EXPR ident ) )
			int alt42=8;
			switch ( input.LA(1) ) {
			case NULL:
				{
				alt42=1;
				}
				break;
			case 328:
			case 338:
				{
				alt42=2;
				}
				break;
			case STRING:
				{
				alt42=3;
				}
				break;
			case INTEGER_VALUE:
				{
				alt42=4;
				}
				break;
			case DECIMAL_VALUE:
				{
				alt42=5;
				}
				break;
			case TRUE:
				{
				alt42=6;
				}
				break;
			case FALSE:
				{
				alt42=7;
				}
				break;
			case IDENT_EXPR:
				{
				alt42=8;
				}
				break;
			default:
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 42, 0, input);
				throw nvae;
			}
			switch (alt42) {
				case 1 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:403:7: NULL
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					NULL190=(CommonTree)match(input,NULL,FOLLOW_NULL_in_parameterOrSimpleLiteral3677); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					NULL190_tree = (CommonTree)adaptor.dupNode(NULL190);


					adaptor.addChild(root_0, NULL190_tree);
					}

					if ( state.backtracking==0 ) { retval.value = new NullLiteral(); }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 2 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:404:7: parameterExpr
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_parameterExpr_in_parameterOrSimpleLiteral3704);
					parameterExpr191=parameterExpr();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_0, parameterExpr191.getTree());

					if ( state.backtracking==0 ) { retval.value = (parameterExpr191!=null?((StatementBuilder.parameterExpr_return)parameterExpr191).value:null); }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 3 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:405:7: string
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_string_in_parameterOrSimpleLiteral3722);
					string192=string();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_0, string192.getTree());

					if ( state.backtracking==0 ) { retval.value = new StringLiteral((string192!=null?((StatementBuilder.string_return)string192).value:null)); }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 4 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:406:7: integer
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_integer_in_parameterOrSimpleLiteral3747);
					integer193=integer();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_0, integer193.getTree());

					if ( state.backtracking==0 ) { retval.value = new LongLiteral((integer193!=null?((StatementBuilder.integer_return)integer193).value:null)); }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 5 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:407:7: decimal
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_decimal_in_parameterOrSimpleLiteral3771);
					decimal194=decimal();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_0, decimal194.getTree());

					if ( state.backtracking==0 ) { retval.value = new DoubleLiteral((decimal194!=null?((StatementBuilder.decimal_return)decimal194).value:null)); }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 6 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:408:7: TRUE
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					TRUE195=(CommonTree)match(input,TRUE,FOLLOW_TRUE_in_parameterOrSimpleLiteral3795); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					TRUE195_tree = (CommonTree)adaptor.dupNode(TRUE195);


					adaptor.addChild(root_0, TRUE195_tree);
					}

					if ( state.backtracking==0 ) { retval.value = BooleanLiteral.TRUE_LITERAL; }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 7 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:409:7: FALSE
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					FALSE196=(CommonTree)match(input,FALSE,FOLLOW_FALSE_in_parameterOrSimpleLiteral3822); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					FALSE196_tree = (CommonTree)adaptor.dupNode(FALSE196);


					adaptor.addChild(root_0, FALSE196_tree);
					}

					if ( state.backtracking==0 ) { retval.value = BooleanLiteral.FALSE_LITERAL; }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 8 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:410:7: ^( IDENT_EXPR ident )
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					{
					CommonTree _save_last_1 = _last;
					CommonTree _first_1 = null;
					CommonTree root_1 = (CommonTree)adaptor.nil();
					_last = (CommonTree)input.LT(1);
					IDENT_EXPR197=(CommonTree)match(input,IDENT_EXPR,FOLLOW_IDENT_EXPR_in_parameterOrSimpleLiteral3849); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					IDENT_EXPR197_tree = (CommonTree)adaptor.dupNode(IDENT_EXPR197);


					root_1 = (CommonTree)adaptor.becomeRoot(IDENT_EXPR197_tree, root_1);
					}

					match(input, Token.DOWN, null); if (state.failed) return retval;
					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_ident_in_parameterOrSimpleLiteral3851);
					ident198=ident();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, ident198.getTree());

					match(input, Token.UP, null); if (state.failed) return retval;
					adaptor.addChild(root_0, root_1);
					_last = _save_last_1;
					}


					if ( state.backtracking==0 ) { retval.value = new StringLiteral((ident198!=null?((StatementBuilder.ident_return)ident198).value:null)); }
					if ( state.backtracking==0 ) {
					}

					}
					break;

			}
			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "parameterOrSimpleLiteral"


	public static class subscript_return extends TreeRuleReturnScope {
		public SubscriptExpression value;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "subscript"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:414:1: subscript returns [SubscriptExpression value] : ^( '[' a= expr b= expr ) ;
	public final StatementBuilder.subscript_return subscript() throws RecognitionException {
		StatementBuilder.subscript_return retval = new StatementBuilder.subscript_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree char_literal199=null;
		TreeRuleReturnScope a =null;
		TreeRuleReturnScope b =null;

		CommonTree char_literal199_tree=null;

		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:415:5: ( ^( '[' a= expr b= expr ) )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:415:9: ^( '[' a= expr b= expr )
			{
			root_0 = (CommonTree)adaptor.nil();


			_last = (CommonTree)input.LT(1);
			{
			CommonTree _save_last_1 = _last;
			CommonTree _first_1 = null;
			CommonTree root_1 = (CommonTree)adaptor.nil();
			_last = (CommonTree)input.LT(1);
			char_literal199=(CommonTree)match(input,341,FOLLOW_341_in_subscript3881); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			char_literal199_tree = (CommonTree)adaptor.dupNode(char_literal199);


			root_1 = (CommonTree)adaptor.becomeRoot(char_literal199_tree, root_1);
			}

			match(input, Token.DOWN, null); if (state.failed) return retval;
			_last = (CommonTree)input.LT(1);
			pushFollow(FOLLOW_expr_in_subscript3885);
			a=expr();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) 
			adaptor.addChild(root_1, a.getTree());

			_last = (CommonTree)input.LT(1);
			pushFollow(FOLLOW_expr_in_subscript3889);
			b=expr();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) 
			adaptor.addChild(root_1, b.getTree());

			match(input, Token.UP, null); if (state.failed) return retval;
			adaptor.addChild(root_0, root_1);
			_last = _save_last_1;
			}


			if ( state.backtracking==0 ) { retval.value = new SubscriptExpression((a!=null?((StatementBuilder.expr_return)a).value:null), (b!=null?((StatementBuilder.expr_return)b).value:null)); }
			if ( state.backtracking==0 ) {
			}

			}

			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "subscript"


	public static class qname_return extends TreeRuleReturnScope {
		public QualifiedName value;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "qname"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:418:1: qname returns [QualifiedName value] : ^( QNAME i= identList ) ;
	public final StatementBuilder.qname_return qname() throws RecognitionException {
		StatementBuilder.qname_return retval = new StatementBuilder.qname_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree QNAME200=null;
		TreeRuleReturnScope i =null;

		CommonTree QNAME200_tree=null;

		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:419:5: ( ^( QNAME i= identList ) )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:419:7: ^( QNAME i= identList )
			{
			root_0 = (CommonTree)adaptor.nil();


			_last = (CommonTree)input.LT(1);
			{
			CommonTree _save_last_1 = _last;
			CommonTree _first_1 = null;
			CommonTree root_1 = (CommonTree)adaptor.nil();
			_last = (CommonTree)input.LT(1);
			QNAME200=(CommonTree)match(input,QNAME,FOLLOW_QNAME_in_qname3914); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			QNAME200_tree = (CommonTree)adaptor.dupNode(QNAME200);


			root_1 = (CommonTree)adaptor.becomeRoot(QNAME200_tree, root_1);
			}

			match(input, Token.DOWN, null); if (state.failed) return retval;
			_last = (CommonTree)input.LT(1);
			pushFollow(FOLLOW_identList_in_qname3918);
			i=identList();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) 
			adaptor.addChild(root_1, i.getTree());

			match(input, Token.UP, null); if (state.failed) return retval;
			adaptor.addChild(root_0, root_1);
			_last = _save_last_1;
			}


			if ( state.backtracking==0 ) { retval.value = new QualifiedName((i!=null?((StatementBuilder.identList_return)i).value:null)); }
			if ( state.backtracking==0 ) {
			}

			}

			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "qname"


	public static class qnameList_return extends TreeRuleReturnScope {
		public List<QualifiedNameReference> value = new ArrayList<>();
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "qnameList"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:422:1: qnameList returns [List<QualifiedNameReference> value = new ArrayList<>()] : ( qname )+ ;
	public final StatementBuilder.qnameList_return qnameList() throws RecognitionException {
		StatementBuilder.qnameList_return retval = new StatementBuilder.qnameList_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		TreeRuleReturnScope qname201 =null;


		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:423:5: ( ( qname )+ )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:423:7: ( qname )+
			{
			root_0 = (CommonTree)adaptor.nil();


			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:423:7: ( qname )+
			int cnt43=0;
			loop43:
			while (true) {
				int alt43=2;
				int LA43_0 = input.LA(1);
				if ( (LA43_0==QNAME) ) {
					alt43=1;
				}

				switch (alt43) {
				case 1 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:423:9: qname
					{
					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_qname_in_qnameList3944);
					qname201=qname();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_0, qname201.getTree());

					if ( state.backtracking==0 ) { retval.value.add(new QualifiedNameReference((qname201!=null?((StatementBuilder.qname_return)qname201).value:null))); }
					if ( state.backtracking==0 ) {
					}

					}
					break;

				default :
					if ( cnt43 >= 1 ) break loop43;
					if (state.backtracking>0) {state.failed=true; return retval;}
					EarlyExitException eee = new EarlyExitException(43, input);
					throw eee;
				}
				cnt43++;
			}

			if ( state.backtracking==0 ) {
			}

			}

			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "qnameList"


	public static class identList_return extends TreeRuleReturnScope {
		public List<String> value = new ArrayList<>();
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "identList"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:426:1: identList returns [List<String> value = new ArrayList<>()] : ( ident )+ ;
	public final StatementBuilder.identList_return identList() throws RecognitionException {
		StatementBuilder.identList_return retval = new StatementBuilder.identList_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		TreeRuleReturnScope ident202 =null;


		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:427:5: ( ( ident )+ )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:427:7: ( ident )+
			{
			root_0 = (CommonTree)adaptor.nil();


			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:427:7: ( ident )+
			int cnt44=0;
			loop44:
			while (true) {
				int alt44=2;
				int LA44_0 = input.LA(1);
				if ( (LA44_0==IDENT||LA44_0==QUOTED_IDENT) ) {
					alt44=1;
				}

				switch (alt44) {
				case 1 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:427:9: ident
					{
					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_ident_in_identList3972);
					ident202=ident();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_0, ident202.getTree());

					if ( state.backtracking==0 ) { retval.value.add((ident202!=null?((StatementBuilder.ident_return)ident202).value:null)); }
					if ( state.backtracking==0 ) {
					}

					}
					break;

				default :
					if ( cnt44 >= 1 ) break loop44;
					if (state.backtracking>0) {state.failed=true; return retval;}
					EarlyExitException eee = new EarlyExitException(44, input);
					throw eee;
				}
				cnt44++;
			}

			if ( state.backtracking==0 ) {
			}

			}

			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "identList"


	public static class ident_return extends TreeRuleReturnScope {
		public String value;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "ident"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:437:1: ident returns [String value] : (i= IDENT |q= QUOTED_IDENT );
	public final StatementBuilder.ident_return ident() throws RecognitionException {
		StatementBuilder.ident_return retval = new StatementBuilder.ident_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree i=null;
		CommonTree q=null;

		CommonTree i_tree=null;
		CommonTree q_tree=null;

		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:438:5: (i= IDENT |q= QUOTED_IDENT )
			int alt45=2;
			int LA45_0 = input.LA(1);
			if ( (LA45_0==IDENT) ) {
				alt45=1;
			}
			else if ( (LA45_0==QUOTED_IDENT) ) {
				alt45=2;
			}

			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 45, 0, input);
				throw nvae;
			}

			switch (alt45) {
				case 1 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:438:7: i= IDENT
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					i=(CommonTree)match(input,IDENT,FOLLOW_IDENT_in_ident4002); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					i_tree = (CommonTree)adaptor.dupNode(i);


					adaptor.addChild(root_0, i_tree);
					}

					if ( state.backtracking==0 ) { retval.value = (i!=null?i.getText():null).toLowerCase(Locale.ENGLISH); }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 2 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:439:7: q= QUOTED_IDENT
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					q=(CommonTree)match(input,QUOTED_IDENT,FOLLOW_QUOTED_IDENT_in_ident4021); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					q_tree = (CommonTree)adaptor.dupNode(q);


					adaptor.addChild(root_0, q_tree);
					}

					if ( state.backtracking==0 ) { retval.value = (q!=null?q.getText():null); }
					if ( state.backtracking==0 ) {
					}

					}
					break;

			}
			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "ident"


	public static class string_return extends TreeRuleReturnScope {
		public String value;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "string"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:442:1: string returns [String value] : s= STRING ;
	public final StatementBuilder.string_return string() throws RecognitionException {
		StatementBuilder.string_return retval = new StatementBuilder.string_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree s=null;

		CommonTree s_tree=null;

		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:443:5: (s= STRING )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:443:7: s= STRING
			{
			root_0 = (CommonTree)adaptor.nil();


			_last = (CommonTree)input.LT(1);
			s=(CommonTree)match(input,STRING,FOLLOW_STRING_in_string4046); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			s_tree = (CommonTree)adaptor.dupNode(s);


			adaptor.addChild(root_0, s_tree);
			}

			if ( state.backtracking==0 ) { retval.value = (s!=null?s.getText():null); }
			if ( state.backtracking==0 ) {
			}

			}

			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "string"


	public static class numberLiteral_return extends TreeRuleReturnScope {
		public Literal value;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "numberLiteral"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:446:1: numberLiteral returns [Literal value] : ( integer | decimal );
	public final StatementBuilder.numberLiteral_return numberLiteral() throws RecognitionException {
		StatementBuilder.numberLiteral_return retval = new StatementBuilder.numberLiteral_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		TreeRuleReturnScope integer203 =null;
		TreeRuleReturnScope decimal204 =null;


		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:447:5: ( integer | decimal )
			int alt46=2;
			int LA46_0 = input.LA(1);
			if ( (LA46_0==INTEGER_VALUE) ) {
				alt46=1;
			}
			else if ( (LA46_0==DECIMAL_VALUE) ) {
				alt46=2;
			}

			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 46, 0, input);
				throw nvae;
			}

			switch (alt46) {
				case 1 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:447:7: integer
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_integer_in_numberLiteral4069);
					integer203=integer();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_0, integer203.getTree());

					if ( state.backtracking==0 ) { retval.value = new LongLiteral((integer203!=null?((StatementBuilder.integer_return)integer203).value:null)); }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 2 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:448:7: decimal
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_decimal_in_numberLiteral4093);
					decimal204=decimal();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_0, decimal204.getTree());

					if ( state.backtracking==0 ) { retval.value = new DoubleLiteral((decimal204!=null?((StatementBuilder.decimal_return)decimal204).value:null)); }
					if ( state.backtracking==0 ) {
					}

					}
					break;

			}
			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "numberLiteral"


	public static class integer_return extends TreeRuleReturnScope {
		public String value;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "integer"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:451:1: integer returns [String value] : s= INTEGER_VALUE ;
	public final StatementBuilder.integer_return integer() throws RecognitionException {
		StatementBuilder.integer_return retval = new StatementBuilder.integer_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree s=null;

		CommonTree s_tree=null;

		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:452:5: (s= INTEGER_VALUE )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:452:7: s= INTEGER_VALUE
			{
			root_0 = (CommonTree)adaptor.nil();


			_last = (CommonTree)input.LT(1);
			s=(CommonTree)match(input,INTEGER_VALUE,FOLLOW_INTEGER_VALUE_in_integer4132); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			s_tree = (CommonTree)adaptor.dupNode(s);


			adaptor.addChild(root_0, s_tree);
			}

			if ( state.backtracking==0 ) { retval.value = (s!=null?s.getText():null); }
			if ( state.backtracking==0 ) {
			}

			}

			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "integer"


	public static class decimal_return extends TreeRuleReturnScope {
		public String value;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "decimal"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:455:1: decimal returns [String value] : s= DECIMAL_VALUE ;
	public final StatementBuilder.decimal_return decimal() throws RecognitionException {
		StatementBuilder.decimal_return retval = new StatementBuilder.decimal_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree s=null;

		CommonTree s_tree=null;

		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:456:5: (s= DECIMAL_VALUE )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:456:7: s= DECIMAL_VALUE
			{
			root_0 = (CommonTree)adaptor.nil();


			_last = (CommonTree)input.LT(1);
			s=(CommonTree)match(input,DECIMAL_VALUE,FOLLOW_DECIMAL_VALUE_in_decimal4157); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			s_tree = (CommonTree)adaptor.dupNode(s);


			adaptor.addChild(root_0, s_tree);
			}

			if ( state.backtracking==0 ) { retval.value = (s!=null?s.getText():null); }
			if ( state.backtracking==0 ) {
			}

			}

			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "decimal"


	public static class functionCall_return extends TreeRuleReturnScope {
		public FunctionCall value;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "functionCall"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:459:1: functionCall returns [FunctionCall value] : ^( FUNCTION_CALL n= qname (w= window )? d= distinct[false] a= exprList ) ;
	public final StatementBuilder.functionCall_return functionCall() throws RecognitionException {
		StatementBuilder.functionCall_return retval = new StatementBuilder.functionCall_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree FUNCTION_CALL205=null;
		TreeRuleReturnScope n =null;
		TreeRuleReturnScope w =null;
		TreeRuleReturnScope d =null;
		TreeRuleReturnScope a =null;

		CommonTree FUNCTION_CALL205_tree=null;

		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:460:5: ( ^( FUNCTION_CALL n= qname (w= window )? d= distinct[false] a= exprList ) )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:460:7: ^( FUNCTION_CALL n= qname (w= window )? d= distinct[false] a= exprList )
			{
			root_0 = (CommonTree)adaptor.nil();


			_last = (CommonTree)input.LT(1);
			{
			CommonTree _save_last_1 = _last;
			CommonTree _first_1 = null;
			CommonTree root_1 = (CommonTree)adaptor.nil();
			_last = (CommonTree)input.LT(1);
			FUNCTION_CALL205=(CommonTree)match(input,FUNCTION_CALL,FOLLOW_FUNCTION_CALL_in_functionCall4181); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			FUNCTION_CALL205_tree = (CommonTree)adaptor.dupNode(FUNCTION_CALL205);


			root_1 = (CommonTree)adaptor.becomeRoot(FUNCTION_CALL205_tree, root_1);
			}

			match(input, Token.DOWN, null); if (state.failed) return retval;
			_last = (CommonTree)input.LT(1);
			pushFollow(FOLLOW_qname_in_functionCall4185);
			n=qname();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) 
			adaptor.addChild(root_1, n.getTree());

			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:460:32: (w= window )?
			int alt47=2;
			int LA47_0 = input.LA(1);
			if ( (LA47_0==WINDOW) ) {
				alt47=1;
			}
			switch (alt47) {
				case 1 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:460:32: w= window
					{
					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_window_in_functionCall4189);
					w=window();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, w.getTree());

					if ( state.backtracking==0 ) {
					}

					}
					break;

			}

			_last = (CommonTree)input.LT(1);
			pushFollow(FOLLOW_distinct_in_functionCall4194);
			d=distinct(false);
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) 
			adaptor.addChild(root_1, d.getTree());

			_last = (CommonTree)input.LT(1);
			pushFollow(FOLLOW_exprList_in_functionCall4199);
			a=exprList();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) 
			adaptor.addChild(root_1, a.getTree());

			match(input, Token.UP, null); if (state.failed) return retval;
			adaptor.addChild(root_0, root_1);
			_last = _save_last_1;
			}


			if ( state.backtracking==0 ) { retval.value = new FunctionCall((n!=null?((StatementBuilder.qname_return)n).value:null), (w!=null?((StatementBuilder.window_return)w).value:null), (d!=null?((StatementBuilder.distinct_return)d).value:false), (a!=null?((StatementBuilder.exprList_return)a).value:null)); }
			if ( state.backtracking==0 ) {
			}

			}

			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "functionCall"


	public static class window_return extends TreeRuleReturnScope {
		public Window value;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "window"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:463:1: window returns [Window value] : ^( WINDOW ( windowPartition )? ( orderClause )? ( windowFrame )? ) ;
	public final StatementBuilder.window_return window() throws RecognitionException {
		StatementBuilder.window_return retval = new StatementBuilder.window_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree WINDOW206=null;
		TreeRuleReturnScope windowPartition207 =null;
		TreeRuleReturnScope orderClause208 =null;
		TreeRuleReturnScope windowFrame209 =null;

		CommonTree WINDOW206_tree=null;

		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:464:5: ( ^( WINDOW ( windowPartition )? ( orderClause )? ( windowFrame )? ) )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:464:7: ^( WINDOW ( windowPartition )? ( orderClause )? ( windowFrame )? )
			{
			root_0 = (CommonTree)adaptor.nil();


			_last = (CommonTree)input.LT(1);
			{
			CommonTree _save_last_1 = _last;
			CommonTree _first_1 = null;
			CommonTree root_1 = (CommonTree)adaptor.nil();
			_last = (CommonTree)input.LT(1);
			WINDOW206=(CommonTree)match(input,WINDOW,FOLLOW_WINDOW_in_window4224); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			WINDOW206_tree = (CommonTree)adaptor.dupNode(WINDOW206);


			root_1 = (CommonTree)adaptor.becomeRoot(WINDOW206_tree, root_1);
			}

			if ( input.LA(1)==Token.DOWN ) {
				match(input, Token.DOWN, null); if (state.failed) return retval;
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:464:16: ( windowPartition )?
				int alt48=2;
				int LA48_0 = input.LA(1);
				if ( (LA48_0==PARTITION_BY) ) {
					alt48=1;
				}
				switch (alt48) {
					case 1 :
						// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:464:16: windowPartition
						{
						_last = (CommonTree)input.LT(1);
						pushFollow(FOLLOW_windowPartition_in_window4226);
						windowPartition207=windowPartition();
						state._fsp--;
						if (state.failed) return retval;
						if ( state.backtracking==0 ) 
						adaptor.addChild(root_1, windowPartition207.getTree());

						if ( state.backtracking==0 ) {
						}

						}
						break;

				}

				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:464:33: ( orderClause )?
				int alt49=2;
				int LA49_0 = input.LA(1);
				if ( (LA49_0==ORDER_BY) ) {
					alt49=1;
				}
				switch (alt49) {
					case 1 :
						// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:464:33: orderClause
						{
						_last = (CommonTree)input.LT(1);
						pushFollow(FOLLOW_orderClause_in_window4229);
						orderClause208=orderClause();
						state._fsp--;
						if (state.failed) return retval;
						if ( state.backtracking==0 ) 
						adaptor.addChild(root_1, orderClause208.getTree());

						if ( state.backtracking==0 ) {
						}

						}
						break;

				}

				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:464:46: ( windowFrame )?
				int alt50=2;
				int LA50_0 = input.LA(1);
				if ( (LA50_0==RANGE||LA50_0==ROWS) ) {
					alt50=1;
				}
				switch (alt50) {
					case 1 :
						// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:464:46: windowFrame
						{
						_last = (CommonTree)input.LT(1);
						pushFollow(FOLLOW_windowFrame_in_window4232);
						windowFrame209=windowFrame();
						state._fsp--;
						if (state.failed) return retval;
						if ( state.backtracking==0 ) 
						adaptor.addChild(root_1, windowFrame209.getTree());

						if ( state.backtracking==0 ) {
						}

						}
						break;

				}

				match(input, Token.UP, null); if (state.failed) return retval;
			}
			adaptor.addChild(root_0, root_1);
			_last = _save_last_1;
			}


			if ( state.backtracking==0 ) { retval.value = new Window(
			            MoreObjects.firstNonNull((windowPartition207!=null?((StatementBuilder.windowPartition_return)windowPartition207).value:null), ImmutableList.<Expression>of()),
			            MoreObjects.firstNonNull((orderClause208!=null?((StatementBuilder.orderClause_return)orderClause208).value:null), ImmutableList.<SortItem>of()),
			            (windowFrame209!=null?((StatementBuilder.windowFrame_return)windowFrame209).value:null));
			        }
			if ( state.backtracking==0 ) {
			}

			}

			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "window"


	public static class windowPartition_return extends TreeRuleReturnScope {
		public List<Expression> value = new ArrayList<>();
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "windowPartition"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:472:1: windowPartition returns [List<Expression> value = new ArrayList<>()] : ^( PARTITION_BY exprList ) ;
	public final StatementBuilder.windowPartition_return windowPartition() throws RecognitionException {
		StatementBuilder.windowPartition_return retval = new StatementBuilder.windowPartition_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree PARTITION_BY210=null;
		TreeRuleReturnScope exprList211 =null;

		CommonTree PARTITION_BY210_tree=null;

		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:473:5: ( ^( PARTITION_BY exprList ) )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:473:7: ^( PARTITION_BY exprList )
			{
			root_0 = (CommonTree)adaptor.nil();


			_last = (CommonTree)input.LT(1);
			{
			CommonTree _save_last_1 = _last;
			CommonTree _first_1 = null;
			CommonTree root_1 = (CommonTree)adaptor.nil();
			_last = (CommonTree)input.LT(1);
			PARTITION_BY210=(CommonTree)match(input,PARTITION_BY,FOLLOW_PARTITION_BY_in_windowPartition4266); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			PARTITION_BY210_tree = (CommonTree)adaptor.dupNode(PARTITION_BY210);


			root_1 = (CommonTree)adaptor.becomeRoot(PARTITION_BY210_tree, root_1);
			}

			if ( input.LA(1)==Token.DOWN ) {
				match(input, Token.DOWN, null); if (state.failed) return retval;
				_last = (CommonTree)input.LT(1);
				pushFollow(FOLLOW_exprList_in_windowPartition4268);
				exprList211=exprList();
				state._fsp--;
				if (state.failed) return retval;
				if ( state.backtracking==0 ) 
				adaptor.addChild(root_1, exprList211.getTree());

				match(input, Token.UP, null); if (state.failed) return retval;
			}
			adaptor.addChild(root_0, root_1);
			_last = _save_last_1;
			}


			if ( state.backtracking==0 ) { retval.value = (exprList211!=null?((StatementBuilder.exprList_return)exprList211).value:null); }
			if ( state.backtracking==0 ) {
			}

			}

			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "windowPartition"


	public static class windowFrame_return extends TreeRuleReturnScope {
		public WindowFrame value;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "windowFrame"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:476:1: windowFrame returns [WindowFrame value] : ( ^( RANGE s= frameBound (e= frameBound )? ) | ^( ROWS s= frameBound (e= frameBound )? ) );
	public final StatementBuilder.windowFrame_return windowFrame() throws RecognitionException {
		StatementBuilder.windowFrame_return retval = new StatementBuilder.windowFrame_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree RANGE212=null;
		CommonTree ROWS213=null;
		TreeRuleReturnScope s =null;
		TreeRuleReturnScope e =null;

		CommonTree RANGE212_tree=null;
		CommonTree ROWS213_tree=null;

		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:477:5: ( ^( RANGE s= frameBound (e= frameBound )? ) | ^( ROWS s= frameBound (e= frameBound )? ) )
			int alt53=2;
			int LA53_0 = input.LA(1);
			if ( (LA53_0==RANGE) ) {
				alt53=1;
			}
			else if ( (LA53_0==ROWS) ) {
				alt53=2;
			}

			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 53, 0, input);
				throw nvae;
			}

			switch (alt53) {
				case 1 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:477:7: ^( RANGE s= frameBound (e= frameBound )? )
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					{
					CommonTree _save_last_1 = _last;
					CommonTree _first_1 = null;
					CommonTree root_1 = (CommonTree)adaptor.nil();
					_last = (CommonTree)input.LT(1);
					RANGE212=(CommonTree)match(input,RANGE,FOLLOW_RANGE_in_windowFrame4293); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					RANGE212_tree = (CommonTree)adaptor.dupNode(RANGE212);


					root_1 = (CommonTree)adaptor.becomeRoot(RANGE212_tree, root_1);
					}

					match(input, Token.DOWN, null); if (state.failed) return retval;
					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_frameBound_in_windowFrame4297);
					s=frameBound();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, s.getTree());

					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:477:29: (e= frameBound )?
					int alt51=2;
					int LA51_0 = input.LA(1);
					if ( (LA51_0==CURRENT_ROW||LA51_0==FOLLOWING||LA51_0==PRECEDING||(LA51_0 >= UNBOUNDED_FOLLOWING && LA51_0 <= UNBOUNDED_PRECEDING)) ) {
						alt51=1;
					}
					switch (alt51) {
						case 1 :
							// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:477:29: e= frameBound
							{
							_last = (CommonTree)input.LT(1);
							pushFollow(FOLLOW_frameBound_in_windowFrame4301);
							e=frameBound();
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) 
							adaptor.addChild(root_1, e.getTree());

							if ( state.backtracking==0 ) {
							}

							}
							break;

					}

					match(input, Token.UP, null); if (state.failed) return retval;
					adaptor.addChild(root_0, root_1);
					_last = _save_last_1;
					}


					if ( state.backtracking==0 ) { retval.value = new WindowFrame(WindowFrame.Type.RANGE, (s!=null?((StatementBuilder.frameBound_return)s).value:null), (e!=null?((StatementBuilder.frameBound_return)e).value:null)); }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 2 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:478:7: ^( ROWS s= frameBound (e= frameBound )? )
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					{
					CommonTree _save_last_1 = _last;
					CommonTree _first_1 = null;
					CommonTree root_1 = (CommonTree)adaptor.nil();
					_last = (CommonTree)input.LT(1);
					ROWS213=(CommonTree)match(input,ROWS,FOLLOW_ROWS_in_windowFrame4314); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					ROWS213_tree = (CommonTree)adaptor.dupNode(ROWS213);


					root_1 = (CommonTree)adaptor.becomeRoot(ROWS213_tree, root_1);
					}

					match(input, Token.DOWN, null); if (state.failed) return retval;
					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_frameBound_in_windowFrame4318);
					s=frameBound();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, s.getTree());

					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:478:28: (e= frameBound )?
					int alt52=2;
					int LA52_0 = input.LA(1);
					if ( (LA52_0==CURRENT_ROW||LA52_0==FOLLOWING||LA52_0==PRECEDING||(LA52_0 >= UNBOUNDED_FOLLOWING && LA52_0 <= UNBOUNDED_PRECEDING)) ) {
						alt52=1;
					}
					switch (alt52) {
						case 1 :
							// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:478:28: e= frameBound
							{
							_last = (CommonTree)input.LT(1);
							pushFollow(FOLLOW_frameBound_in_windowFrame4322);
							e=frameBound();
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) 
							adaptor.addChild(root_1, e.getTree());

							if ( state.backtracking==0 ) {
							}

							}
							break;

					}

					match(input, Token.UP, null); if (state.failed) return retval;
					adaptor.addChild(root_0, root_1);
					_last = _save_last_1;
					}


					if ( state.backtracking==0 ) { retval.value = new WindowFrame(WindowFrame.Type.ROWS, (s!=null?((StatementBuilder.frameBound_return)s).value:null), (e!=null?((StatementBuilder.frameBound_return)e).value:null)); }
					if ( state.backtracking==0 ) {
					}

					}
					break;

			}
			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "windowFrame"


	public static class frameBound_return extends TreeRuleReturnScope {
		public FrameBound value;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "frameBound"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:481:1: frameBound returns [FrameBound value] : ( UNBOUNDED_PRECEDING | UNBOUNDED_FOLLOWING | CURRENT_ROW | ^( PRECEDING expr ) | ^( FOLLOWING expr ) );
	public final StatementBuilder.frameBound_return frameBound() throws RecognitionException {
		StatementBuilder.frameBound_return retval = new StatementBuilder.frameBound_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree UNBOUNDED_PRECEDING214=null;
		CommonTree UNBOUNDED_FOLLOWING215=null;
		CommonTree CURRENT_ROW216=null;
		CommonTree PRECEDING217=null;
		CommonTree FOLLOWING219=null;
		TreeRuleReturnScope expr218 =null;
		TreeRuleReturnScope expr220 =null;

		CommonTree UNBOUNDED_PRECEDING214_tree=null;
		CommonTree UNBOUNDED_FOLLOWING215_tree=null;
		CommonTree CURRENT_ROW216_tree=null;
		CommonTree PRECEDING217_tree=null;
		CommonTree FOLLOWING219_tree=null;

		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:482:5: ( UNBOUNDED_PRECEDING | UNBOUNDED_FOLLOWING | CURRENT_ROW | ^( PRECEDING expr ) | ^( FOLLOWING expr ) )
			int alt54=5;
			switch ( input.LA(1) ) {
			case UNBOUNDED_PRECEDING:
				{
				alt54=1;
				}
				break;
			case UNBOUNDED_FOLLOWING:
				{
				alt54=2;
				}
				break;
			case CURRENT_ROW:
				{
				alt54=3;
				}
				break;
			case PRECEDING:
				{
				alt54=4;
				}
				break;
			case FOLLOWING:
				{
				alt54=5;
				}
				break;
			default:
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 54, 0, input);
				throw nvae;
			}
			switch (alt54) {
				case 1 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:482:7: UNBOUNDED_PRECEDING
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					UNBOUNDED_PRECEDING214=(CommonTree)match(input,UNBOUNDED_PRECEDING,FOLLOW_UNBOUNDED_PRECEDING_in_frameBound4348); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					UNBOUNDED_PRECEDING214_tree = (CommonTree)adaptor.dupNode(UNBOUNDED_PRECEDING214);


					adaptor.addChild(root_0, UNBOUNDED_PRECEDING214_tree);
					}

					if ( state.backtracking==0 ) { retval.value = new FrameBound(FrameBound.Type.UNBOUNDED_PRECEDING); }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 2 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:483:7: UNBOUNDED_FOLLOWING
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					UNBOUNDED_FOLLOWING215=(CommonTree)match(input,UNBOUNDED_FOLLOWING,FOLLOW_UNBOUNDED_FOLLOWING_in_frameBound4358); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					UNBOUNDED_FOLLOWING215_tree = (CommonTree)adaptor.dupNode(UNBOUNDED_FOLLOWING215);


					adaptor.addChild(root_0, UNBOUNDED_FOLLOWING215_tree);
					}

					if ( state.backtracking==0 ) { retval.value = new FrameBound(FrameBound.Type.UNBOUNDED_FOLLOWING); }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 3 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:484:7: CURRENT_ROW
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					CURRENT_ROW216=(CommonTree)match(input,CURRENT_ROW,FOLLOW_CURRENT_ROW_in_frameBound4368); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					CURRENT_ROW216_tree = (CommonTree)adaptor.dupNode(CURRENT_ROW216);


					adaptor.addChild(root_0, CURRENT_ROW216_tree);
					}

					if ( state.backtracking==0 ) { retval.value = new FrameBound(FrameBound.Type.CURRENT_ROW); }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 4 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:485:7: ^( PRECEDING expr )
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					{
					CommonTree _save_last_1 = _last;
					CommonTree _first_1 = null;
					CommonTree root_1 = (CommonTree)adaptor.nil();
					_last = (CommonTree)input.LT(1);
					PRECEDING217=(CommonTree)match(input,PRECEDING,FOLLOW_PRECEDING_in_frameBound4387); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					PRECEDING217_tree = (CommonTree)adaptor.dupNode(PRECEDING217);


					root_1 = (CommonTree)adaptor.becomeRoot(PRECEDING217_tree, root_1);
					}

					match(input, Token.DOWN, null); if (state.failed) return retval;
					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_expr_in_frameBound4389);
					expr218=expr();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, expr218.getTree());

					match(input, Token.UP, null); if (state.failed) return retval;
					adaptor.addChild(root_0, root_1);
					_last = _save_last_1;
					}


					if ( state.backtracking==0 ) { retval.value = new FrameBound(FrameBound.Type.PRECEDING, (expr218!=null?((StatementBuilder.expr_return)expr218).value:null)); }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 5 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:486:7: ^( FOLLOWING expr )
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					{
					CommonTree _save_last_1 = _last;
					CommonTree _first_1 = null;
					CommonTree root_1 = (CommonTree)adaptor.nil();
					_last = (CommonTree)input.LT(1);
					FOLLOWING219=(CommonTree)match(input,FOLLOWING,FOLLOW_FOLLOWING_in_frameBound4403); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					FOLLOWING219_tree = (CommonTree)adaptor.dupNode(FOLLOWING219);


					root_1 = (CommonTree)adaptor.becomeRoot(FOLLOWING219_tree, root_1);
					}

					match(input, Token.DOWN, null); if (state.failed) return retval;
					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_expr_in_frameBound4405);
					expr220=expr();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, expr220.getTree());

					match(input, Token.UP, null); if (state.failed) return retval;
					adaptor.addChild(root_0, root_1);
					_last = _save_last_1;
					}


					if ( state.backtracking==0 ) { retval.value = new FrameBound(FrameBound.Type.FOLLOWING, (expr220!=null?((StatementBuilder.expr_return)expr220).value:null)); }
					if ( state.backtracking==0 ) {
					}

					}
					break;

			}
			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "frameBound"


	public static class extract_return extends TreeRuleReturnScope {
		public Extract value;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "extract"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:489:1: extract returns [Extract value] : ^( EXTRACT f= expr e= expr ) ;
	public final StatementBuilder.extract_return extract() throws RecognitionException {
		StatementBuilder.extract_return retval = new StatementBuilder.extract_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree EXTRACT221=null;
		TreeRuleReturnScope f =null;
		TreeRuleReturnScope e =null;

		CommonTree EXTRACT221_tree=null;

		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:490:5: ( ^( EXTRACT f= expr e= expr ) )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:490:7: ^( EXTRACT f= expr e= expr )
			{
			root_0 = (CommonTree)adaptor.nil();


			_last = (CommonTree)input.LT(1);
			{
			CommonTree _save_last_1 = _last;
			CommonTree _first_1 = null;
			CommonTree root_1 = (CommonTree)adaptor.nil();
			_last = (CommonTree)input.LT(1);
			EXTRACT221=(CommonTree)match(input,EXTRACT,FOLLOW_EXTRACT_in_extract4432); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			EXTRACT221_tree = (CommonTree)adaptor.dupNode(EXTRACT221);


			root_1 = (CommonTree)adaptor.becomeRoot(EXTRACT221_tree, root_1);
			}

			match(input, Token.DOWN, null); if (state.failed) return retval;
			_last = (CommonTree)input.LT(1);
			pushFollow(FOLLOW_expr_in_extract4436);
			f=expr();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) 
			adaptor.addChild(root_1, f.getTree());

			_last = (CommonTree)input.LT(1);
			pushFollow(FOLLOW_expr_in_extract4440);
			e=expr();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) 
			adaptor.addChild(root_1, e.getTree());

			match(input, Token.UP, null); if (state.failed) return retval;
			adaptor.addChild(root_0, root_1);
			_last = _save_last_1;
			}


			if ( state.backtracking==0 ) { retval.value = new Extract((e!=null?((StatementBuilder.expr_return)e).value:null), (f!=null?((StatementBuilder.expr_return)f).value:null)); }
			if ( state.backtracking==0 ) {
			}

			}

			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "extract"


	public static class cast_return extends TreeRuleReturnScope {
		public Cast value;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "cast"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:493:1: cast returns [Cast value] : ^( CAST expr dataType ) ;
	public final StatementBuilder.cast_return cast() throws RecognitionException {
		StatementBuilder.cast_return retval = new StatementBuilder.cast_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree CAST222=null;
		TreeRuleReturnScope expr223 =null;
		TreeRuleReturnScope dataType224 =null;

		CommonTree CAST222_tree=null;

		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:494:5: ( ^( CAST expr dataType ) )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:494:7: ^( CAST expr dataType )
			{
			root_0 = (CommonTree)adaptor.nil();


			_last = (CommonTree)input.LT(1);
			{
			CommonTree _save_last_1 = _last;
			CommonTree _first_1 = null;
			CommonTree root_1 = (CommonTree)adaptor.nil();
			_last = (CommonTree)input.LT(1);
			CAST222=(CommonTree)match(input,CAST,FOLLOW_CAST_in_cast4465); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			CAST222_tree = (CommonTree)adaptor.dupNode(CAST222);


			root_1 = (CommonTree)adaptor.becomeRoot(CAST222_tree, root_1);
			}

			match(input, Token.DOWN, null); if (state.failed) return retval;
			_last = (CommonTree)input.LT(1);
			pushFollow(FOLLOW_expr_in_cast4467);
			expr223=expr();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) 
			adaptor.addChild(root_1, expr223.getTree());

			_last = (CommonTree)input.LT(1);
			pushFollow(FOLLOW_dataType_in_cast4469);
			dataType224=dataType();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) 
			adaptor.addChild(root_1, dataType224.getTree());

			match(input, Token.UP, null); if (state.failed) return retval;
			adaptor.addChild(root_0, root_1);
			_last = _save_last_1;
			}


			if ( state.backtracking==0 ) { retval.value = new Cast((expr223!=null?((StatementBuilder.expr_return)expr223).value:null), (dataType224!=null?((StatementBuilder.dataType_return)dataType224).value:null)); }
			if ( state.backtracking==0 ) {
			}

			}

			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "cast"


	public static class tryCast_return extends TreeRuleReturnScope {
		public TryCast value;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "tryCast"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:497:1: tryCast returns [TryCast value] : ^( TRY_CAST expr dataType ) ;
	public final StatementBuilder.tryCast_return tryCast() throws RecognitionException {
		StatementBuilder.tryCast_return retval = new StatementBuilder.tryCast_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree TRY_CAST225=null;
		TreeRuleReturnScope expr226 =null;
		TreeRuleReturnScope dataType227 =null;

		CommonTree TRY_CAST225_tree=null;

		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:498:5: ( ^( TRY_CAST expr dataType ) )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:498:7: ^( TRY_CAST expr dataType )
			{
			root_0 = (CommonTree)adaptor.nil();


			_last = (CommonTree)input.LT(1);
			{
			CommonTree _save_last_1 = _last;
			CommonTree _first_1 = null;
			CommonTree root_1 = (CommonTree)adaptor.nil();
			_last = (CommonTree)input.LT(1);
			TRY_CAST225=(CommonTree)match(input,TRY_CAST,FOLLOW_TRY_CAST_in_tryCast4494); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			TRY_CAST225_tree = (CommonTree)adaptor.dupNode(TRY_CAST225);


			root_1 = (CommonTree)adaptor.becomeRoot(TRY_CAST225_tree, root_1);
			}

			match(input, Token.DOWN, null); if (state.failed) return retval;
			_last = (CommonTree)input.LT(1);
			pushFollow(FOLLOW_expr_in_tryCast4496);
			expr226=expr();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) 
			adaptor.addChild(root_1, expr226.getTree());

			_last = (CommonTree)input.LT(1);
			pushFollow(FOLLOW_dataType_in_tryCast4498);
			dataType227=dataType();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) 
			adaptor.addChild(root_1, dataType227.getTree());

			match(input, Token.UP, null); if (state.failed) return retval;
			adaptor.addChild(root_0, root_1);
			_last = _save_last_1;
			}


			if ( state.backtracking==0 ) { retval.value = new TryCast((expr226!=null?((StatementBuilder.expr_return)expr226).value:null), (dataType227!=null?((StatementBuilder.dataType_return)dataType227).value:null)); }
			if ( state.backtracking==0 ) {
			}

			}

			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "tryCast"


	public static class current_time_return extends TreeRuleReturnScope {
		public CurrentTime value;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "current_time"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:501:1: current_time returns [CurrentTime value] : ( CURRENT_DATE | CURRENT_TIME | CURRENT_TIMESTAMP | ^( CURRENT_TIME integer ) | ^( CURRENT_TIMESTAMP integer ) );
	public final StatementBuilder.current_time_return current_time() throws RecognitionException {
		StatementBuilder.current_time_return retval = new StatementBuilder.current_time_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree CURRENT_DATE228=null;
		CommonTree CURRENT_TIME229=null;
		CommonTree CURRENT_TIMESTAMP230=null;
		CommonTree CURRENT_TIME231=null;
		CommonTree CURRENT_TIMESTAMP233=null;
		TreeRuleReturnScope integer232 =null;
		TreeRuleReturnScope integer234 =null;

		CommonTree CURRENT_DATE228_tree=null;
		CommonTree CURRENT_TIME229_tree=null;
		CommonTree CURRENT_TIMESTAMP230_tree=null;
		CommonTree CURRENT_TIME231_tree=null;
		CommonTree CURRENT_TIMESTAMP233_tree=null;

		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:502:5: ( CURRENT_DATE | CURRENT_TIME | CURRENT_TIMESTAMP | ^( CURRENT_TIME integer ) | ^( CURRENT_TIMESTAMP integer ) )
			int alt55=5;
			switch ( input.LA(1) ) {
			case CURRENT_DATE:
				{
				alt55=1;
				}
				break;
			case CURRENT_TIME:
				{
				int LA55_2 = input.LA(2);
				if ( (LA55_2==DOWN) ) {
					alt55=4;
				}
				else if ( (LA55_2==EOF||LA55_2==UP||LA55_2==ALL||(LA55_2 >= AND && LA55_2 <= ARRAY_NOT_LIKE)||LA55_2==ASC||LA55_2==BETWEEN||LA55_2==BOOLEAN||LA55_2==BYTE||LA55_2==CAST||LA55_2==COALESCE||LA55_2==CONSTRAINT||LA55_2==CURRENT_DATE||(LA55_2 >= CURRENT_TIME && LA55_2 <= CURRENT_TIMESTAMP)||LA55_2==DATE||LA55_2==DECIMAL_VALUE||LA55_2==DESC||LA55_2==DOUBLE||LA55_2==EQ||LA55_2==EXISTS||(LA55_2 >= EXTRACT && LA55_2 <= FALSE)||LA55_2==FLOAT||LA55_2==FUNCTION_CALL||LA55_2==GENERIC_PROPERTIES||(LA55_2 >= GEO_POINT && LA55_2 <= GEO_SHAPE)||(LA55_2 >= GT && LA55_2 <= GTE)||LA55_2==IDENT||LA55_2==IDENT_EXPR||LA55_2==IF||LA55_2==IN||(LA55_2 >= INT && LA55_2 <= INTEGER_VALUE)||(LA55_2 >= IN_LIST && LA55_2 <= IP)||(LA55_2 >= IS_DISTINCT_FROM && LA55_2 <= IS_NULL)||LA55_2==LIKE||(LA55_2 >= LONG && LA55_2 <= MATCH)||(LA55_2 >= NEGATIVE && LA55_2 <= NEQ)||LA55_2==NOT||(LA55_2 >= NULL && LA55_2 <= NULLIF)||LA55_2==OBJECT||LA55_2==OBJECT_LITERAL||LA55_2==OR||LA55_2==QNAME||LA55_2==QUERY||LA55_2==QUOTED_IDENT||(LA55_2 >= REGEX_MATCH && LA55_2 <= REGEX_NO_MATCH_CI)||LA55_2==SEARCHED_CASE||LA55_2==SET||LA55_2==SHORT||LA55_2==SIMPLE_CASE||LA55_2==SOME||LA55_2==STRATIFY_ON||(LA55_2 >= STRING && LA55_2 <= STRING_TYPE)||(LA55_2 >= TIME && LA55_2 <= TIMESTAMP)||(LA55_2 >= TRUE && LA55_2 <= TRY_CAST)||LA55_2==WHEN||(LA55_2 >= 328 && LA55_2 <= 329)||(LA55_2 >= 332 && LA55_2 <= 333)||LA55_2==335||(LA55_2 >= 337 && LA55_2 <= 338)||LA55_2==341) ) {
					alt55=2;
				}

				else {
					if (state.backtracking>0) {state.failed=true; return retval;}
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 55, 2, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

				}
				break;
			case CURRENT_TIMESTAMP:
				{
				int LA55_3 = input.LA(2);
				if ( (LA55_3==DOWN) ) {
					alt55=5;
				}
				else if ( (LA55_3==EOF||LA55_3==UP||LA55_3==ALL||(LA55_3 >= AND && LA55_3 <= ARRAY_NOT_LIKE)||LA55_3==ASC||LA55_3==BETWEEN||LA55_3==BOOLEAN||LA55_3==BYTE||LA55_3==CAST||LA55_3==COALESCE||LA55_3==CONSTRAINT||LA55_3==CURRENT_DATE||(LA55_3 >= CURRENT_TIME && LA55_3 <= CURRENT_TIMESTAMP)||LA55_3==DATE||LA55_3==DECIMAL_VALUE||LA55_3==DESC||LA55_3==DOUBLE||LA55_3==EQ||LA55_3==EXISTS||(LA55_3 >= EXTRACT && LA55_3 <= FALSE)||LA55_3==FLOAT||LA55_3==FUNCTION_CALL||LA55_3==GENERIC_PROPERTIES||(LA55_3 >= GEO_POINT && LA55_3 <= GEO_SHAPE)||(LA55_3 >= GT && LA55_3 <= GTE)||LA55_3==IDENT||LA55_3==IDENT_EXPR||LA55_3==IF||LA55_3==IN||(LA55_3 >= INT && LA55_3 <= INTEGER_VALUE)||(LA55_3 >= IN_LIST && LA55_3 <= IP)||(LA55_3 >= IS_DISTINCT_FROM && LA55_3 <= IS_NULL)||LA55_3==LIKE||(LA55_3 >= LONG && LA55_3 <= MATCH)||(LA55_3 >= NEGATIVE && LA55_3 <= NEQ)||LA55_3==NOT||(LA55_3 >= NULL && LA55_3 <= NULLIF)||LA55_3==OBJECT||LA55_3==OBJECT_LITERAL||LA55_3==OR||LA55_3==QNAME||LA55_3==QUERY||LA55_3==QUOTED_IDENT||(LA55_3 >= REGEX_MATCH && LA55_3 <= REGEX_NO_MATCH_CI)||LA55_3==SEARCHED_CASE||LA55_3==SET||LA55_3==SHORT||LA55_3==SIMPLE_CASE||LA55_3==SOME||LA55_3==STRATIFY_ON||(LA55_3 >= STRING && LA55_3 <= STRING_TYPE)||(LA55_3 >= TIME && LA55_3 <= TIMESTAMP)||(LA55_3 >= TRUE && LA55_3 <= TRY_CAST)||LA55_3==WHEN||(LA55_3 >= 328 && LA55_3 <= 329)||(LA55_3 >= 332 && LA55_3 <= 333)||LA55_3==335||(LA55_3 >= 337 && LA55_3 <= 338)||LA55_3==341) ) {
					alt55=3;
				}

				else {
					if (state.backtracking>0) {state.failed=true; return retval;}
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 55, 3, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

				}
				break;
			default:
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 55, 0, input);
				throw nvae;
			}
			switch (alt55) {
				case 1 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:502:7: CURRENT_DATE
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					CURRENT_DATE228=(CommonTree)match(input,CURRENT_DATE,FOLLOW_CURRENT_DATE_in_current_time4522); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					CURRENT_DATE228_tree = (CommonTree)adaptor.dupNode(CURRENT_DATE228);


					adaptor.addChild(root_0, CURRENT_DATE228_tree);
					}

					if ( state.backtracking==0 ) { retval.value = new CurrentTime(CurrentTime.Type.DATE); }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 2 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:503:7: CURRENT_TIME
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					CURRENT_TIME229=(CommonTree)match(input,CURRENT_TIME,FOLLOW_CURRENT_TIME_in_current_time4550); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					CURRENT_TIME229_tree = (CommonTree)adaptor.dupNode(CURRENT_TIME229);


					adaptor.addChild(root_0, CURRENT_TIME229_tree);
					}

					if ( state.backtracking==0 ) { retval.value = new CurrentTime(CurrentTime.Type.TIME); }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 3 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:504:7: CURRENT_TIMESTAMP
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					CURRENT_TIMESTAMP230=(CommonTree)match(input,CURRENT_TIMESTAMP,FOLLOW_CURRENT_TIMESTAMP_in_current_time4578); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					CURRENT_TIMESTAMP230_tree = (CommonTree)adaptor.dupNode(CURRENT_TIMESTAMP230);


					adaptor.addChild(root_0, CURRENT_TIMESTAMP230_tree);
					}

					if ( state.backtracking==0 ) { retval.value = new CurrentTime(CurrentTime.Type.TIMESTAMP); }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 4 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:505:7: ^( CURRENT_TIME integer )
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					{
					CommonTree _save_last_1 = _last;
					CommonTree _first_1 = null;
					CommonTree root_1 = (CommonTree)adaptor.nil();
					_last = (CommonTree)input.LT(1);
					CURRENT_TIME231=(CommonTree)match(input,CURRENT_TIME,FOLLOW_CURRENT_TIME_in_current_time4602); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					CURRENT_TIME231_tree = (CommonTree)adaptor.dupNode(CURRENT_TIME231);


					root_1 = (CommonTree)adaptor.becomeRoot(CURRENT_TIME231_tree, root_1);
					}

					match(input, Token.DOWN, null); if (state.failed) return retval;
					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_integer_in_current_time4604);
					integer232=integer();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, integer232.getTree());

					match(input, Token.UP, null); if (state.failed) return retval;
					adaptor.addChild(root_0, root_1);
					_last = _save_last_1;
					}


					if ( state.backtracking==0 ) { retval.value = new CurrentTime(CurrentTime.Type.TIME, Integer.valueOf((integer232!=null?((StatementBuilder.integer_return)integer232).value:null))); }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 5 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:506:7: ^( CURRENT_TIMESTAMP integer )
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					{
					CommonTree _save_last_1 = _last;
					CommonTree _first_1 = null;
					CommonTree root_1 = (CommonTree)adaptor.nil();
					_last = (CommonTree)input.LT(1);
					CURRENT_TIMESTAMP233=(CommonTree)match(input,CURRENT_TIMESTAMP,FOLLOW_CURRENT_TIMESTAMP_in_current_time4623); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					CURRENT_TIMESTAMP233_tree = (CommonTree)adaptor.dupNode(CURRENT_TIMESTAMP233);


					root_1 = (CommonTree)adaptor.becomeRoot(CURRENT_TIMESTAMP233_tree, root_1);
					}

					match(input, Token.DOWN, null); if (state.failed) return retval;
					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_integer_in_current_time4625);
					integer234=integer();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, integer234.getTree());

					match(input, Token.UP, null); if (state.failed) return retval;
					adaptor.addChild(root_0, root_1);
					_last = _save_last_1;
					}


					if ( state.backtracking==0 ) { retval.value = new CurrentTime(CurrentTime.Type.TIMESTAMP, Integer.valueOf((integer234!=null?((StatementBuilder.integer_return)integer234).value:null))); }
					if ( state.backtracking==0 ) {
					}

					}
					break;

			}
			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "current_time"


	public static class arithmeticExpression_return extends TreeRuleReturnScope {
		public ArithmeticExpression value;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "arithmeticExpression"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:509:1: arithmeticExpression returns [ArithmeticExpression value] : ^(t= arithmeticType a= expr b= expr ) ;
	public final StatementBuilder.arithmeticExpression_return arithmeticExpression() throws RecognitionException {
		StatementBuilder.arithmeticExpression_return retval = new StatementBuilder.arithmeticExpression_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		TreeRuleReturnScope t =null;
		TreeRuleReturnScope a =null;
		TreeRuleReturnScope b =null;


		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:510:5: ( ^(t= arithmeticType a= expr b= expr ) )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:510:7: ^(t= arithmeticType a= expr b= expr )
			{
			root_0 = (CommonTree)adaptor.nil();


			_last = (CommonTree)input.LT(1);
			{
			CommonTree _save_last_1 = _last;
			CommonTree _first_1 = null;
			CommonTree root_1 = (CommonTree)adaptor.nil();
			_last = (CommonTree)input.LT(1);
			pushFollow(FOLLOW_arithmeticType_in_arithmeticExpression4654);
			t=arithmeticType();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) root_1 = (CommonTree)adaptor.becomeRoot(t.getTree(), root_1);

			match(input, Token.DOWN, null); if (state.failed) return retval;
			_last = (CommonTree)input.LT(1);
			pushFollow(FOLLOW_expr_in_arithmeticExpression4658);
			a=expr();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) 
			adaptor.addChild(root_1, a.getTree());

			_last = (CommonTree)input.LT(1);
			pushFollow(FOLLOW_expr_in_arithmeticExpression4662);
			b=expr();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) 
			adaptor.addChild(root_1, b.getTree());

			match(input, Token.UP, null); if (state.failed) return retval;
			adaptor.addChild(root_0, root_1);
			_last = _save_last_1;
			}


			if ( state.backtracking==0 ) { retval.value = new ArithmeticExpression((t!=null?((StatementBuilder.arithmeticType_return)t).value:null), (a!=null?((StatementBuilder.expr_return)a).value:null), (b!=null?((StatementBuilder.expr_return)b).value:null)); }
			if ( state.backtracking==0 ) {
			}

			}

			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "arithmeticExpression"


	public static class arithmeticType_return extends TreeRuleReturnScope {
		public ArithmeticExpression.Type value;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "arithmeticType"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:513:1: arithmeticType returns [ArithmeticExpression.Type value] : ( '+' | '-' | '*' | '/' | '%' );
	public final StatementBuilder.arithmeticType_return arithmeticType() throws RecognitionException {
		StatementBuilder.arithmeticType_return retval = new StatementBuilder.arithmeticType_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree char_literal235=null;
		CommonTree char_literal236=null;
		CommonTree char_literal237=null;
		CommonTree char_literal238=null;
		CommonTree char_literal239=null;

		CommonTree char_literal235_tree=null;
		CommonTree char_literal236_tree=null;
		CommonTree char_literal237_tree=null;
		CommonTree char_literal238_tree=null;
		CommonTree char_literal239_tree=null;

		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:514:5: ( '+' | '-' | '*' | '/' | '%' )
			int alt56=5;
			switch ( input.LA(1) ) {
			case 333:
				{
				alt56=1;
				}
				break;
			case 335:
				{
				alt56=2;
				}
				break;
			case 332:
				{
				alt56=3;
				}
				break;
			case 337:
				{
				alt56=4;
				}
				break;
			case 329:
				{
				alt56=5;
				}
				break;
			default:
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 56, 0, input);
				throw nvae;
			}
			switch (alt56) {
				case 1 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:514:7: '+'
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					char_literal235=(CommonTree)match(input,333,FOLLOW_333_in_arithmeticType4686); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					char_literal235_tree = (CommonTree)adaptor.dupNode(char_literal235);


					adaptor.addChild(root_0, char_literal235_tree);
					}

					if ( state.backtracking==0 ) { retval.value = ArithmeticExpression.Type.ADD; }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 2 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:515:7: '-'
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					char_literal236=(CommonTree)match(input,335,FOLLOW_335_in_arithmeticType4696); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					char_literal236_tree = (CommonTree)adaptor.dupNode(char_literal236);


					adaptor.addChild(root_0, char_literal236_tree);
					}

					if ( state.backtracking==0 ) { retval.value = ArithmeticExpression.Type.SUBTRACT; }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 3 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:516:7: '*'
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					char_literal237=(CommonTree)match(input,332,FOLLOW_332_in_arithmeticType4706); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					char_literal237_tree = (CommonTree)adaptor.dupNode(char_literal237);


					adaptor.addChild(root_0, char_literal237_tree);
					}

					if ( state.backtracking==0 ) { retval.value = ArithmeticExpression.Type.MULTIPLY; }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 4 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:517:7: '/'
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					char_literal238=(CommonTree)match(input,337,FOLLOW_337_in_arithmeticType4716); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					char_literal238_tree = (CommonTree)adaptor.dupNode(char_literal238);


					adaptor.addChild(root_0, char_literal238_tree);
					}

					if ( state.backtracking==0 ) { retval.value = ArithmeticExpression.Type.DIVIDE; }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 5 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:518:7: '%'
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					char_literal239=(CommonTree)match(input,329,FOLLOW_329_in_arithmeticType4726); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					char_literal239_tree = (CommonTree)adaptor.dupNode(char_literal239);


					adaptor.addChild(root_0, char_literal239_tree);
					}

					if ( state.backtracking==0 ) { retval.value = ArithmeticExpression.Type.MODULUS; }
					if ( state.backtracking==0 ) {
					}

					}
					break;

			}
			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "arithmeticType"


	public static class comparisonExpression_return extends TreeRuleReturnScope {
		public ComparisonExpression value;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "comparisonExpression"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:521:1: comparisonExpression returns [ComparisonExpression value] : ^(t= comparisonType a= expr b= expr ) ;
	public final StatementBuilder.comparisonExpression_return comparisonExpression() throws RecognitionException {
		StatementBuilder.comparisonExpression_return retval = new StatementBuilder.comparisonExpression_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		TreeRuleReturnScope t =null;
		TreeRuleReturnScope a =null;
		TreeRuleReturnScope b =null;


		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:522:5: ( ^(t= comparisonType a= expr b= expr ) )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:522:7: ^(t= comparisonType a= expr b= expr )
			{
			root_0 = (CommonTree)adaptor.nil();


			_last = (CommonTree)input.LT(1);
			{
			CommonTree _save_last_1 = _last;
			CommonTree _first_1 = null;
			CommonTree root_1 = (CommonTree)adaptor.nil();
			_last = (CommonTree)input.LT(1);
			pushFollow(FOLLOW_comparisonType_in_comparisonExpression4752);
			t=comparisonType();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) root_1 = (CommonTree)adaptor.becomeRoot(t.getTree(), root_1);

			match(input, Token.DOWN, null); if (state.failed) return retval;
			_last = (CommonTree)input.LT(1);
			pushFollow(FOLLOW_expr_in_comparisonExpression4756);
			a=expr();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) 
			adaptor.addChild(root_1, a.getTree());

			_last = (CommonTree)input.LT(1);
			pushFollow(FOLLOW_expr_in_comparisonExpression4760);
			b=expr();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) 
			adaptor.addChild(root_1, b.getTree());

			match(input, Token.UP, null); if (state.failed) return retval;
			adaptor.addChild(root_0, root_1);
			_last = _save_last_1;
			}


			if ( state.backtracking==0 ) { retval.value = new ComparisonExpression((t!=null?((StatementBuilder.comparisonType_return)t).value:null), (a!=null?((StatementBuilder.expr_return)a).value:null), (b!=null?((StatementBuilder.expr_return)b).value:null)); }
			if ( state.backtracking==0 ) {
			}

			}

			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "comparisonExpression"


	public static class arrayComparisonExpression_return extends TreeRuleReturnScope {
		public ArrayComparisonExpression value;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "arrayComparisonExpression"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:525:1: arrayComparisonExpression returns [ArrayComparisonExpression value] : ^( ARRAY_CMP a= expr compType= comparisonType quant= setCompareQuantifier b= expr ) ;
	public final StatementBuilder.arrayComparisonExpression_return arrayComparisonExpression() throws RecognitionException {
		StatementBuilder.arrayComparisonExpression_return retval = new StatementBuilder.arrayComparisonExpression_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree ARRAY_CMP240=null;
		TreeRuleReturnScope a =null;
		TreeRuleReturnScope compType =null;
		TreeRuleReturnScope quant =null;
		TreeRuleReturnScope b =null;

		CommonTree ARRAY_CMP240_tree=null;

		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:526:5: ( ^( ARRAY_CMP a= expr compType= comparisonType quant= setCompareQuantifier b= expr ) )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:526:7: ^( ARRAY_CMP a= expr compType= comparisonType quant= setCompareQuantifier b= expr )
			{
			root_0 = (CommonTree)adaptor.nil();


			_last = (CommonTree)input.LT(1);
			{
			CommonTree _save_last_1 = _last;
			CommonTree _first_1 = null;
			CommonTree root_1 = (CommonTree)adaptor.nil();
			_last = (CommonTree)input.LT(1);
			ARRAY_CMP240=(CommonTree)match(input,ARRAY_CMP,FOLLOW_ARRAY_CMP_in_arrayComparisonExpression4785); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			ARRAY_CMP240_tree = (CommonTree)adaptor.dupNode(ARRAY_CMP240);


			root_1 = (CommonTree)adaptor.becomeRoot(ARRAY_CMP240_tree, root_1);
			}

			match(input, Token.DOWN, null); if (state.failed) return retval;
			_last = (CommonTree)input.LT(1);
			pushFollow(FOLLOW_expr_in_arrayComparisonExpression4789);
			a=expr();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) 
			adaptor.addChild(root_1, a.getTree());

			_last = (CommonTree)input.LT(1);
			pushFollow(FOLLOW_comparisonType_in_arrayComparisonExpression4793);
			compType=comparisonType();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) 
			adaptor.addChild(root_1, compType.getTree());

			_last = (CommonTree)input.LT(1);
			pushFollow(FOLLOW_setCompareQuantifier_in_arrayComparisonExpression4797);
			quant=setCompareQuantifier();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) 
			adaptor.addChild(root_1, quant.getTree());

			_last = (CommonTree)input.LT(1);
			pushFollow(FOLLOW_expr_in_arrayComparisonExpression4801);
			b=expr();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) 
			adaptor.addChild(root_1, b.getTree());

			match(input, Token.UP, null); if (state.failed) return retval;
			adaptor.addChild(root_0, root_1);
			_last = _save_last_1;
			}


			if ( state.backtracking==0 ) { retval.value = new ArrayComparisonExpression((compType!=null?((StatementBuilder.comparisonType_return)compType).value:null), (quant!=null?((StatementBuilder.setCompareQuantifier_return)quant).value:null), (a!=null?((StatementBuilder.expr_return)a).value:null), (b!=null?((StatementBuilder.expr_return)b).value:null)); }
			if ( state.backtracking==0 ) {
			}

			}

			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "arrayComparisonExpression"


	public static class comparisonType_return extends TreeRuleReturnScope {
		public ComparisonExpression.Type value;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "comparisonType"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:530:1: comparisonType returns [ComparisonExpression.Type value] : ( EQ | NEQ | LT | LTE | GT | GTE | IS_DISTINCT_FROM | REGEX_MATCH | REGEX_NO_MATCH | REGEX_MATCH_CI | REGEX_NO_MATCH_CI );
	public final StatementBuilder.comparisonType_return comparisonType() throws RecognitionException {
		StatementBuilder.comparisonType_return retval = new StatementBuilder.comparisonType_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree EQ241=null;
		CommonTree NEQ242=null;
		CommonTree LT243=null;
		CommonTree LTE244=null;
		CommonTree GT245=null;
		CommonTree GTE246=null;
		CommonTree IS_DISTINCT_FROM247=null;
		CommonTree REGEX_MATCH248=null;
		CommonTree REGEX_NO_MATCH249=null;
		CommonTree REGEX_MATCH_CI250=null;
		CommonTree REGEX_NO_MATCH_CI251=null;

		CommonTree EQ241_tree=null;
		CommonTree NEQ242_tree=null;
		CommonTree LT243_tree=null;
		CommonTree LTE244_tree=null;
		CommonTree GT245_tree=null;
		CommonTree GTE246_tree=null;
		CommonTree IS_DISTINCT_FROM247_tree=null;
		CommonTree REGEX_MATCH248_tree=null;
		CommonTree REGEX_NO_MATCH249_tree=null;
		CommonTree REGEX_MATCH_CI250_tree=null;
		CommonTree REGEX_NO_MATCH_CI251_tree=null;

		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:531:5: ( EQ | NEQ | LT | LTE | GT | GTE | IS_DISTINCT_FROM | REGEX_MATCH | REGEX_NO_MATCH | REGEX_MATCH_CI | REGEX_NO_MATCH_CI )
			int alt57=11;
			switch ( input.LA(1) ) {
			case EQ:
				{
				alt57=1;
				}
				break;
			case NEQ:
				{
				alt57=2;
				}
				break;
			case LT:
				{
				alt57=3;
				}
				break;
			case LTE:
				{
				alt57=4;
				}
				break;
			case GT:
				{
				alt57=5;
				}
				break;
			case GTE:
				{
				alt57=6;
				}
				break;
			case IS_DISTINCT_FROM:
				{
				alt57=7;
				}
				break;
			case REGEX_MATCH:
				{
				alt57=8;
				}
				break;
			case REGEX_NO_MATCH:
				{
				alt57=9;
				}
				break;
			case REGEX_MATCH_CI:
				{
				alt57=10;
				}
				break;
			case REGEX_NO_MATCH_CI:
				{
				alt57=11;
				}
				break;
			default:
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 57, 0, input);
				throw nvae;
			}
			switch (alt57) {
				case 1 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:531:7: EQ
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					EQ241=(CommonTree)match(input,EQ,FOLLOW_EQ_in_comparisonType4833); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					EQ241_tree = (CommonTree)adaptor.dupNode(EQ241);


					adaptor.addChild(root_0, EQ241_tree);
					}

					if ( state.backtracking==0 ) { retval.value = ComparisonExpression.Type.EQUAL; }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 2 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:532:7: NEQ
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					NEQ242=(CommonTree)match(input,NEQ,FOLLOW_NEQ_in_comparisonType4862); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					NEQ242_tree = (CommonTree)adaptor.dupNode(NEQ242);


					adaptor.addChild(root_0, NEQ242_tree);
					}

					if ( state.backtracking==0 ) { retval.value = ComparisonExpression.Type.NOT_EQUAL; }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 3 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:533:7: LT
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					LT243=(CommonTree)match(input,LT,FOLLOW_LT_in_comparisonType4890); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					LT243_tree = (CommonTree)adaptor.dupNode(LT243);


					adaptor.addChild(root_0, LT243_tree);
					}

					if ( state.backtracking==0 ) { retval.value = ComparisonExpression.Type.LESS_THAN; }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 4 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:534:7: LTE
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					LTE244=(CommonTree)match(input,LTE,FOLLOW_LTE_in_comparisonType4919); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					LTE244_tree = (CommonTree)adaptor.dupNode(LTE244);


					adaptor.addChild(root_0, LTE244_tree);
					}

					if ( state.backtracking==0 ) { retval.value = ComparisonExpression.Type.LESS_THAN_OR_EQUAL; }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 5 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:535:7: GT
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					GT245=(CommonTree)match(input,GT,FOLLOW_GT_in_comparisonType4947); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					GT245_tree = (CommonTree)adaptor.dupNode(GT245);


					adaptor.addChild(root_0, GT245_tree);
					}

					if ( state.backtracking==0 ) { retval.value = ComparisonExpression.Type.GREATER_THAN; }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 6 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:536:7: GTE
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					GTE246=(CommonTree)match(input,GTE,FOLLOW_GTE_in_comparisonType4976); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					GTE246_tree = (CommonTree)adaptor.dupNode(GTE246);


					adaptor.addChild(root_0, GTE246_tree);
					}

					if ( state.backtracking==0 ) { retval.value = ComparisonExpression.Type.GREATER_THAN_OR_EQUAL; }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 7 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:537:7: IS_DISTINCT_FROM
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					IS_DISTINCT_FROM247=(CommonTree)match(input,IS_DISTINCT_FROM,FOLLOW_IS_DISTINCT_FROM_in_comparisonType5004); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					IS_DISTINCT_FROM247_tree = (CommonTree)adaptor.dupNode(IS_DISTINCT_FROM247);


					adaptor.addChild(root_0, IS_DISTINCT_FROM247_tree);
					}

					if ( state.backtracking==0 ) { retval.value = ComparisonExpression.Type.IS_DISTINCT_FROM; }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 8 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:538:7: REGEX_MATCH
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					REGEX_MATCH248=(CommonTree)match(input,REGEX_MATCH,FOLLOW_REGEX_MATCH_in_comparisonType5019); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					REGEX_MATCH248_tree = (CommonTree)adaptor.dupNode(REGEX_MATCH248);


					adaptor.addChild(root_0, REGEX_MATCH248_tree);
					}

					if ( state.backtracking==0 ) { retval.value = ComparisonExpression.Type.REGEX_MATCH; }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 9 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:539:7: REGEX_NO_MATCH
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					REGEX_NO_MATCH249=(CommonTree)match(input,REGEX_NO_MATCH,FOLLOW_REGEX_NO_MATCH_in_comparisonType5037); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					REGEX_NO_MATCH249_tree = (CommonTree)adaptor.dupNode(REGEX_NO_MATCH249);


					adaptor.addChild(root_0, REGEX_NO_MATCH249_tree);
					}

					if ( state.backtracking==0 ) { retval.value = ComparisonExpression.Type.REGEX_NO_MATCH; }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 10 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:540:7: REGEX_MATCH_CI
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					REGEX_MATCH_CI250=(CommonTree)match(input,REGEX_MATCH_CI,FOLLOW_REGEX_MATCH_CI_in_comparisonType5051); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					REGEX_MATCH_CI250_tree = (CommonTree)adaptor.dupNode(REGEX_MATCH_CI250);


					adaptor.addChild(root_0, REGEX_MATCH_CI250_tree);
					}

					if ( state.backtracking==0 ) { retval.value = ComparisonExpression.Type.REGEX_MATCH_CI; }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 11 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:541:7: REGEX_NO_MATCH_CI
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					REGEX_NO_MATCH_CI251=(CommonTree)match(input,REGEX_NO_MATCH_CI,FOLLOW_REGEX_NO_MATCH_CI_in_comparisonType5068); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					REGEX_NO_MATCH_CI251_tree = (CommonTree)adaptor.dupNode(REGEX_NO_MATCH_CI251);


					adaptor.addChild(root_0, REGEX_NO_MATCH_CI251_tree);
					}

					if ( state.backtracking==0 ) { retval.value = ComparisonExpression.Type.REGEX_NO_MATCH_CI; }
					if ( state.backtracking==0 ) {
					}

					}
					break;

			}
			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "comparisonType"


	public static class setCompareQuantifier_return extends TreeRuleReturnScope {
		public ArrayComparisonExpression.Quantifier value;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "setCompareQuantifier"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:544:1: setCompareQuantifier returns [ArrayComparisonExpression.Quantifier value] : ( ANY | SOME | ALL );
	public final StatementBuilder.setCompareQuantifier_return setCompareQuantifier() throws RecognitionException {
		StatementBuilder.setCompareQuantifier_return retval = new StatementBuilder.setCompareQuantifier_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree ANY252=null;
		CommonTree SOME253=null;
		CommonTree ALL254=null;

		CommonTree ANY252_tree=null;
		CommonTree SOME253_tree=null;
		CommonTree ALL254_tree=null;

		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:545:5: ( ANY | SOME | ALL )
			int alt58=3;
			switch ( input.LA(1) ) {
			case ANY:
				{
				alt58=1;
				}
				break;
			case SOME:
				{
				alt58=2;
				}
				break;
			case ALL:
				{
				alt58=3;
				}
				break;
			default:
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 58, 0, input);
				throw nvae;
			}
			switch (alt58) {
				case 1 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:545:7: ANY
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					ANY252=(CommonTree)match(input,ANY,FOLLOW_ANY_in_setCompareQuantifier5095); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					ANY252_tree = (CommonTree)adaptor.dupNode(ANY252);


					adaptor.addChild(root_0, ANY252_tree);
					}

					if ( state.backtracking==0 ) { retval.value = ArrayComparisonExpression.Quantifier.ANY; }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 2 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:546:7: SOME
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					SOME253=(CommonTree)match(input,SOME,FOLLOW_SOME_in_setCompareQuantifier5120); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					SOME253_tree = (CommonTree)adaptor.dupNode(SOME253);


					adaptor.addChild(root_0, SOME253_tree);
					}

					if ( state.backtracking==0 ) { retval.value = ArrayComparisonExpression.Quantifier.ANY; }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 3 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:547:7: ALL
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					ALL254=(CommonTree)match(input,ALL,FOLLOW_ALL_in_setCompareQuantifier5144); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					ALL254_tree = (CommonTree)adaptor.dupNode(ALL254);


					adaptor.addChild(root_0, ALL254_tree);
					}

					if ( state.backtracking==0 ) { retval.value = ArrayComparisonExpression.Quantifier.ALL; }
					if ( state.backtracking==0 ) {
					}

					}
					break;

			}
			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "setCompareQuantifier"


	public static class predicate_return extends TreeRuleReturnScope {
		public Expression value;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "predicate"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:550:1: predicate returns [Expression value] : ( ^( BETWEEN v= expr min= expr max= expr ) | ^( LIKE v= expr p= expr (esc= expr )? ) | ^( ARRAY_LIKE v= expr quant= setCompareQuantifier p= expr (esc= expr )? ) | ^( ARRAY_NOT_LIKE v= expr quant= setCompareQuantifier p= expr (esc= expr )? ) | ^( IS_NULL expr ) | ^( IS_NOT_NULL expr ) | ^( IN v= expr list= expr ) | ^( EXISTS q= query ) | ^( MATCH l= matchPredicateIdentList queryTerm= expr ( ident )? ( genericProperties )? ) );
	public final StatementBuilder.predicate_return predicate() throws RecognitionException {
		StatementBuilder.predicate_return retval = new StatementBuilder.predicate_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree BETWEEN255=null;
		CommonTree LIKE256=null;
		CommonTree ARRAY_LIKE257=null;
		CommonTree ARRAY_NOT_LIKE258=null;
		CommonTree IS_NULL259=null;
		CommonTree IS_NOT_NULL261=null;
		CommonTree IN263=null;
		CommonTree EXISTS264=null;
		CommonTree MATCH265=null;
		TreeRuleReturnScope v =null;
		TreeRuleReturnScope min =null;
		TreeRuleReturnScope max =null;
		TreeRuleReturnScope p =null;
		TreeRuleReturnScope esc =null;
		TreeRuleReturnScope quant =null;
		TreeRuleReturnScope list =null;
		TreeRuleReturnScope q =null;
		TreeRuleReturnScope l =null;
		TreeRuleReturnScope queryTerm =null;
		TreeRuleReturnScope expr260 =null;
		TreeRuleReturnScope expr262 =null;
		TreeRuleReturnScope ident266 =null;
		TreeRuleReturnScope genericProperties267 =null;

		CommonTree BETWEEN255_tree=null;
		CommonTree LIKE256_tree=null;
		CommonTree ARRAY_LIKE257_tree=null;
		CommonTree ARRAY_NOT_LIKE258_tree=null;
		CommonTree IS_NULL259_tree=null;
		CommonTree IS_NOT_NULL261_tree=null;
		CommonTree IN263_tree=null;
		CommonTree EXISTS264_tree=null;
		CommonTree MATCH265_tree=null;

		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:551:5: ( ^( BETWEEN v= expr min= expr max= expr ) | ^( LIKE v= expr p= expr (esc= expr )? ) | ^( ARRAY_LIKE v= expr quant= setCompareQuantifier p= expr (esc= expr )? ) | ^( ARRAY_NOT_LIKE v= expr quant= setCompareQuantifier p= expr (esc= expr )? ) | ^( IS_NULL expr ) | ^( IS_NOT_NULL expr ) | ^( IN v= expr list= expr ) | ^( EXISTS q= query ) | ^( MATCH l= matchPredicateIdentList queryTerm= expr ( ident )? ( genericProperties )? ) )
			int alt64=9;
			switch ( input.LA(1) ) {
			case BETWEEN:
				{
				alt64=1;
				}
				break;
			case LIKE:
				{
				alt64=2;
				}
				break;
			case ARRAY_LIKE:
				{
				alt64=3;
				}
				break;
			case ARRAY_NOT_LIKE:
				{
				alt64=4;
				}
				break;
			case IS_NULL:
				{
				alt64=5;
				}
				break;
			case IS_NOT_NULL:
				{
				alt64=6;
				}
				break;
			case IN:
				{
				alt64=7;
				}
				break;
			case EXISTS:
				{
				alt64=8;
				}
				break;
			case MATCH:
				{
				alt64=9;
				}
				break;
			default:
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 64, 0, input);
				throw nvae;
			}
			switch (alt64) {
				case 1 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:551:7: ^( BETWEEN v= expr min= expr max= expr )
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					{
					CommonTree _save_last_1 = _last;
					CommonTree _first_1 = null;
					CommonTree root_1 = (CommonTree)adaptor.nil();
					_last = (CommonTree)input.LT(1);
					BETWEEN255=(CommonTree)match(input,BETWEEN,FOLLOW_BETWEEN_in_predicate5183); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					BETWEEN255_tree = (CommonTree)adaptor.dupNode(BETWEEN255);


					root_1 = (CommonTree)adaptor.becomeRoot(BETWEEN255_tree, root_1);
					}

					match(input, Token.DOWN, null); if (state.failed) return retval;
					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_expr_in_predicate5187);
					v=expr();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, v.getTree());

					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_expr_in_predicate5191);
					min=expr();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, min.getTree());

					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_expr_in_predicate5195);
					max=expr();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, max.getTree());

					match(input, Token.UP, null); if (state.failed) return retval;
					adaptor.addChild(root_0, root_1);
					_last = _save_last_1;
					}


					if ( state.backtracking==0 ) { retval.value = new BetweenPredicate((v!=null?((StatementBuilder.expr_return)v).value:null), (min!=null?((StatementBuilder.expr_return)min).value:null), (max!=null?((StatementBuilder.expr_return)max).value:null)); }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 2 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:552:7: ^( LIKE v= expr p= expr (esc= expr )? )
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					{
					CommonTree _save_last_1 = _last;
					CommonTree _first_1 = null;
					CommonTree root_1 = (CommonTree)adaptor.nil();
					_last = (CommonTree)input.LT(1);
					LIKE256=(CommonTree)match(input,LIKE,FOLLOW_LIKE_in_predicate5207); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					LIKE256_tree = (CommonTree)adaptor.dupNode(LIKE256);


					root_1 = (CommonTree)adaptor.becomeRoot(LIKE256_tree, root_1);
					}

					match(input, Token.DOWN, null); if (state.failed) return retval;
					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_expr_in_predicate5211);
					v=expr();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, v.getTree());

					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_expr_in_predicate5215);
					p=expr();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, p.getTree());

					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:552:31: (esc= expr )?
					int alt59=2;
					int LA59_0 = input.LA(1);
					if ( (LA59_0==AND||(LA59_0 >= ARRAY_CMP && LA59_0 <= ARRAY_NOT_LIKE)||LA59_0==BETWEEN||LA59_0==CAST||LA59_0==COALESCE||LA59_0==CURRENT_DATE||(LA59_0 >= CURRENT_TIME && LA59_0 <= CURRENT_TIMESTAMP)||LA59_0==DATE||LA59_0==DECIMAL_VALUE||LA59_0==EQ||LA59_0==EXISTS||(LA59_0 >= EXTRACT && LA59_0 <= FALSE)||LA59_0==FUNCTION_CALL||(LA59_0 >= GT && LA59_0 <= GTE)||LA59_0==IDENT_EXPR||LA59_0==IF||LA59_0==IN||LA59_0==INTEGER_VALUE||LA59_0==IN_LIST||(LA59_0 >= IS_DISTINCT_FROM && LA59_0 <= IS_NULL)||LA59_0==LIKE||(LA59_0 >= LT && LA59_0 <= MATCH)||(LA59_0 >= NEGATIVE && LA59_0 <= NEQ)||LA59_0==NOT||(LA59_0 >= NULL && LA59_0 <= NULLIF)||LA59_0==OBJECT_LITERAL||LA59_0==OR||LA59_0==QNAME||LA59_0==QUERY||(LA59_0 >= REGEX_MATCH && LA59_0 <= REGEX_NO_MATCH_CI)||LA59_0==SEARCHED_CASE||LA59_0==SIMPLE_CASE||LA59_0==STRING||(LA59_0 >= TIME && LA59_0 <= TIMESTAMP)||(LA59_0 >= TRUE && LA59_0 <= TRY_CAST)||(LA59_0 >= 328 && LA59_0 <= 329)||(LA59_0 >= 332 && LA59_0 <= 333)||LA59_0==335||(LA59_0 >= 337 && LA59_0 <= 338)||LA59_0==341) ) {
						alt59=1;
					}
					switch (alt59) {
						case 1 :
							// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:552:31: esc= expr
							{
							_last = (CommonTree)input.LT(1);
							pushFollow(FOLLOW_expr_in_predicate5219);
							esc=expr();
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) 
							adaptor.addChild(root_1, esc.getTree());

							if ( state.backtracking==0 ) {
							}

							}
							break;

					}

					match(input, Token.UP, null); if (state.failed) return retval;
					adaptor.addChild(root_0, root_1);
					_last = _save_last_1;
					}


					if ( state.backtracking==0 ) { retval.value = new LikePredicate((v!=null?((StatementBuilder.expr_return)v).value:null), (p!=null?((StatementBuilder.expr_return)p).value:null), (esc!=null?((StatementBuilder.expr_return)esc).value:null)); }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 3 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:553:7: ^( ARRAY_LIKE v= expr quant= setCompareQuantifier p= expr (esc= expr )? )
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					{
					CommonTree _save_last_1 = _last;
					CommonTree _first_1 = null;
					CommonTree root_1 = (CommonTree)adaptor.nil();
					_last = (CommonTree)input.LT(1);
					ARRAY_LIKE257=(CommonTree)match(input,ARRAY_LIKE,FOLLOW_ARRAY_LIKE_in_predicate5236); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					ARRAY_LIKE257_tree = (CommonTree)adaptor.dupNode(ARRAY_LIKE257);


					root_1 = (CommonTree)adaptor.becomeRoot(ARRAY_LIKE257_tree, root_1);
					}

					match(input, Token.DOWN, null); if (state.failed) return retval;
					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_expr_in_predicate5240);
					v=expr();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, v.getTree());

					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_setCompareQuantifier_in_predicate5244);
					quant=setCompareQuantifier();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, quant.getTree());

					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_expr_in_predicate5248);
					p=expr();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, p.getTree());

					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:553:64: (esc= expr )?
					int alt60=2;
					int LA60_0 = input.LA(1);
					if ( (LA60_0==AND||(LA60_0 >= ARRAY_CMP && LA60_0 <= ARRAY_NOT_LIKE)||LA60_0==BETWEEN||LA60_0==CAST||LA60_0==COALESCE||LA60_0==CURRENT_DATE||(LA60_0 >= CURRENT_TIME && LA60_0 <= CURRENT_TIMESTAMP)||LA60_0==DATE||LA60_0==DECIMAL_VALUE||LA60_0==EQ||LA60_0==EXISTS||(LA60_0 >= EXTRACT && LA60_0 <= FALSE)||LA60_0==FUNCTION_CALL||(LA60_0 >= GT && LA60_0 <= GTE)||LA60_0==IDENT_EXPR||LA60_0==IF||LA60_0==IN||LA60_0==INTEGER_VALUE||LA60_0==IN_LIST||(LA60_0 >= IS_DISTINCT_FROM && LA60_0 <= IS_NULL)||LA60_0==LIKE||(LA60_0 >= LT && LA60_0 <= MATCH)||(LA60_0 >= NEGATIVE && LA60_0 <= NEQ)||LA60_0==NOT||(LA60_0 >= NULL && LA60_0 <= NULLIF)||LA60_0==OBJECT_LITERAL||LA60_0==OR||LA60_0==QNAME||LA60_0==QUERY||(LA60_0 >= REGEX_MATCH && LA60_0 <= REGEX_NO_MATCH_CI)||LA60_0==SEARCHED_CASE||LA60_0==SIMPLE_CASE||LA60_0==STRING||(LA60_0 >= TIME && LA60_0 <= TIMESTAMP)||(LA60_0 >= TRUE && LA60_0 <= TRY_CAST)||(LA60_0 >= 328 && LA60_0 <= 329)||(LA60_0 >= 332 && LA60_0 <= 333)||LA60_0==335||(LA60_0 >= 337 && LA60_0 <= 338)||LA60_0==341) ) {
						alt60=1;
					}
					switch (alt60) {
						case 1 :
							// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:553:64: esc= expr
							{
							_last = (CommonTree)input.LT(1);
							pushFollow(FOLLOW_expr_in_predicate5252);
							esc=expr();
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) 
							adaptor.addChild(root_1, esc.getTree());

							if ( state.backtracking==0 ) {
							}

							}
							break;

					}

					match(input, Token.UP, null); if (state.failed) return retval;
					adaptor.addChild(root_0, root_1);
					_last = _save_last_1;
					}


					if ( state.backtracking==0 ) { retval.value = new ArrayLikePredicate((quant!=null?((StatementBuilder.setCompareQuantifier_return)quant).value:null), (v!=null?((StatementBuilder.expr_return)v).value:null), (p!=null?((StatementBuilder.expr_return)p).value:null), (esc!=null?((StatementBuilder.expr_return)esc).value:null), false); }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 4 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:555:7: ^( ARRAY_NOT_LIKE v= expr quant= setCompareQuantifier p= expr (esc= expr )? )
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					{
					CommonTree _save_last_1 = _last;
					CommonTree _first_1 = null;
					CommonTree root_1 = (CommonTree)adaptor.nil();
					_last = (CommonTree)input.LT(1);
					ARRAY_NOT_LIKE258=(CommonTree)match(input,ARRAY_NOT_LIKE,FOLLOW_ARRAY_NOT_LIKE_in_predicate5307); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					ARRAY_NOT_LIKE258_tree = (CommonTree)adaptor.dupNode(ARRAY_NOT_LIKE258);


					root_1 = (CommonTree)adaptor.becomeRoot(ARRAY_NOT_LIKE258_tree, root_1);
					}

					match(input, Token.DOWN, null); if (state.failed) return retval;
					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_expr_in_predicate5311);
					v=expr();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, v.getTree());

					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_setCompareQuantifier_in_predicate5315);
					quant=setCompareQuantifier();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, quant.getTree());

					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_expr_in_predicate5319);
					p=expr();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, p.getTree());

					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:555:68: (esc= expr )?
					int alt61=2;
					int LA61_0 = input.LA(1);
					if ( (LA61_0==AND||(LA61_0 >= ARRAY_CMP && LA61_0 <= ARRAY_NOT_LIKE)||LA61_0==BETWEEN||LA61_0==CAST||LA61_0==COALESCE||LA61_0==CURRENT_DATE||(LA61_0 >= CURRENT_TIME && LA61_0 <= CURRENT_TIMESTAMP)||LA61_0==DATE||LA61_0==DECIMAL_VALUE||LA61_0==EQ||LA61_0==EXISTS||(LA61_0 >= EXTRACT && LA61_0 <= FALSE)||LA61_0==FUNCTION_CALL||(LA61_0 >= GT && LA61_0 <= GTE)||LA61_0==IDENT_EXPR||LA61_0==IF||LA61_0==IN||LA61_0==INTEGER_VALUE||LA61_0==IN_LIST||(LA61_0 >= IS_DISTINCT_FROM && LA61_0 <= IS_NULL)||LA61_0==LIKE||(LA61_0 >= LT && LA61_0 <= MATCH)||(LA61_0 >= NEGATIVE && LA61_0 <= NEQ)||LA61_0==NOT||(LA61_0 >= NULL && LA61_0 <= NULLIF)||LA61_0==OBJECT_LITERAL||LA61_0==OR||LA61_0==QNAME||LA61_0==QUERY||(LA61_0 >= REGEX_MATCH && LA61_0 <= REGEX_NO_MATCH_CI)||LA61_0==SEARCHED_CASE||LA61_0==SIMPLE_CASE||LA61_0==STRING||(LA61_0 >= TIME && LA61_0 <= TIMESTAMP)||(LA61_0 >= TRUE && LA61_0 <= TRY_CAST)||(LA61_0 >= 328 && LA61_0 <= 329)||(LA61_0 >= 332 && LA61_0 <= 333)||LA61_0==335||(LA61_0 >= 337 && LA61_0 <= 338)||LA61_0==341) ) {
						alt61=1;
					}
					switch (alt61) {
						case 1 :
							// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:555:68: esc= expr
							{
							_last = (CommonTree)input.LT(1);
							pushFollow(FOLLOW_expr_in_predicate5323);
							esc=expr();
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) 
							adaptor.addChild(root_1, esc.getTree());

							if ( state.backtracking==0 ) {
							}

							}
							break;

					}

					match(input, Token.UP, null); if (state.failed) return retval;
					adaptor.addChild(root_0, root_1);
					_last = _save_last_1;
					}


					if ( state.backtracking==0 ) { retval.value = new ArrayLikePredicate((quant!=null?((StatementBuilder.setCompareQuantifier_return)quant).value:null), (v!=null?((StatementBuilder.expr_return)v).value:null), (p!=null?((StatementBuilder.expr_return)p).value:null), (esc!=null?((StatementBuilder.expr_return)esc).value:null), true); }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 5 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:557:7: ^( IS_NULL expr )
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					{
					CommonTree _save_last_1 = _last;
					CommonTree _first_1 = null;
					CommonTree root_1 = (CommonTree)adaptor.nil();
					_last = (CommonTree)input.LT(1);
					IS_NULL259=(CommonTree)match(input,IS_NULL,FOLLOW_IS_NULL_in_predicate5378); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					IS_NULL259_tree = (CommonTree)adaptor.dupNode(IS_NULL259);


					root_1 = (CommonTree)adaptor.becomeRoot(IS_NULL259_tree, root_1);
					}

					match(input, Token.DOWN, null); if (state.failed) return retval;
					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_expr_in_predicate5380);
					expr260=expr();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, expr260.getTree());

					match(input, Token.UP, null); if (state.failed) return retval;
					adaptor.addChild(root_0, root_1);
					_last = _save_last_1;
					}


					if ( state.backtracking==0 ) { retval.value = new IsNullPredicate((expr260!=null?((StatementBuilder.expr_return)expr260).value:null)); }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 6 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:558:7: ^( IS_NOT_NULL expr )
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					{
					CommonTree _save_last_1 = _last;
					CommonTree _first_1 = null;
					CommonTree root_1 = (CommonTree)adaptor.nil();
					_last = (CommonTree)input.LT(1);
					IS_NOT_NULL261=(CommonTree)match(input,IS_NOT_NULL,FOLLOW_IS_NOT_NULL_in_predicate5412); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					IS_NOT_NULL261_tree = (CommonTree)adaptor.dupNode(IS_NOT_NULL261);


					root_1 = (CommonTree)adaptor.becomeRoot(IS_NOT_NULL261_tree, root_1);
					}

					match(input, Token.DOWN, null); if (state.failed) return retval;
					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_expr_in_predicate5414);
					expr262=expr();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, expr262.getTree());

					match(input, Token.UP, null); if (state.failed) return retval;
					adaptor.addChild(root_0, root_1);
					_last = _save_last_1;
					}


					if ( state.backtracking==0 ) { retval.value = new IsNotNullPredicate((expr262!=null?((StatementBuilder.expr_return)expr262).value:null)); }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 7 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:559:7: ^( IN v= expr list= expr )
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					{
					CommonTree _save_last_1 = _last;
					CommonTree _first_1 = null;
					CommonTree root_1 = (CommonTree)adaptor.nil();
					_last = (CommonTree)input.LT(1);
					IN263=(CommonTree)match(input,IN,FOLLOW_IN_in_predicate5442); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					IN263_tree = (CommonTree)adaptor.dupNode(IN263);


					root_1 = (CommonTree)adaptor.becomeRoot(IN263_tree, root_1);
					}

					match(input, Token.DOWN, null); if (state.failed) return retval;
					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_expr_in_predicate5446);
					v=expr();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, v.getTree());

					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_expr_in_predicate5450);
					list=expr();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, list.getTree());

					match(input, Token.UP, null); if (state.failed) return retval;
					adaptor.addChild(root_0, root_1);
					_last = _save_last_1;
					}


					if ( state.backtracking==0 ) { retval.value = new InPredicate((v!=null?((StatementBuilder.expr_return)v).value:null), (list!=null?((StatementBuilder.expr_return)list).value:null)); }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 8 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:560:7: ^( EXISTS q= query )
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					{
					CommonTree _save_last_1 = _last;
					CommonTree _first_1 = null;
					CommonTree root_1 = (CommonTree)adaptor.nil();
					_last = (CommonTree)input.LT(1);
					EXISTS264=(CommonTree)match(input,EXISTS,FOLLOW_EXISTS_in_predicate5475); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					EXISTS264_tree = (CommonTree)adaptor.dupNode(EXISTS264);


					root_1 = (CommonTree)adaptor.becomeRoot(EXISTS264_tree, root_1);
					}

					match(input, Token.DOWN, null); if (state.failed) return retval;
					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_query_in_predicate5479);
					q=query();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, q.getTree());

					match(input, Token.UP, null); if (state.failed) return retval;
					adaptor.addChild(root_0, root_1);
					_last = _save_last_1;
					}


					if ( state.backtracking==0 ) { retval.value = new ExistsPredicate((q!=null?((StatementBuilder.query_return)q).value:null)); }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 9 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:561:7: ^( MATCH l= matchPredicateIdentList queryTerm= expr ( ident )? ( genericProperties )? )
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					{
					CommonTree _save_last_1 = _last;
					CommonTree _first_1 = null;
					CommonTree root_1 = (CommonTree)adaptor.nil();
					_last = (CommonTree)input.LT(1);
					MATCH265=(CommonTree)match(input,MATCH,FOLLOW_MATCH_in_predicate5509); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					MATCH265_tree = (CommonTree)adaptor.dupNode(MATCH265);


					root_1 = (CommonTree)adaptor.becomeRoot(MATCH265_tree, root_1);
					}

					match(input, Token.DOWN, null); if (state.failed) return retval;
					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_matchPredicateIdentList_in_predicate5513);
					l=matchPredicateIdentList();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, l.getTree());

					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_expr_in_predicate5517);
					queryTerm=expr();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, queryTerm.getTree());

					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:561:56: ( ident )?
					int alt62=2;
					int LA62_0 = input.LA(1);
					if ( (LA62_0==IDENT||LA62_0==QUOTED_IDENT) ) {
						alt62=1;
					}
					switch (alt62) {
						case 1 :
							// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:561:56: ident
							{
							_last = (CommonTree)input.LT(1);
							pushFollow(FOLLOW_ident_in_predicate5519);
							ident266=ident();
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) 
							adaptor.addChild(root_1, ident266.getTree());

							if ( state.backtracking==0 ) {
							}

							}
							break;

					}

					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:561:63: ( genericProperties )?
					int alt63=2;
					int LA63_0 = input.LA(1);
					if ( (LA63_0==GENERIC_PROPERTIES) ) {
						alt63=1;
					}
					switch (alt63) {
						case 1 :
							// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:561:63: genericProperties
							{
							_last = (CommonTree)input.LT(1);
							pushFollow(FOLLOW_genericProperties_in_predicate5522);
							genericProperties267=genericProperties();
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) 
							adaptor.addChild(root_1, genericProperties267.getTree());

							if ( state.backtracking==0 ) {
							}

							}
							break;

					}

					match(input, Token.UP, null); if (state.failed) return retval;
					adaptor.addChild(root_0, root_1);
					_last = _save_last_1;
					}


					if ( state.backtracking==0 ) { retval.value = new MatchPredicate((l!=null?((StatementBuilder.matchPredicateIdentList_return)l).value:null), (queryTerm!=null?((StatementBuilder.expr_return)queryTerm).value:null), (ident266!=null?((StatementBuilder.ident_return)ident266).value:null), (genericProperties267!=null?((StatementBuilder.genericProperties_return)genericProperties267).value:null)); }
					if ( state.backtracking==0 ) {
					}

					}
					break;

			}
			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "predicate"


	public static class matchPredicateIdentList_return extends TreeRuleReturnScope {
		public List<MatchPredicateColumnIdent> value = new ArrayList<>();
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "matchPredicateIdentList"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:564:1: matchPredicateIdentList returns [List<MatchPredicateColumnIdent> value = new ArrayList<>()] : ^( MATCH_PREDICATE_IDENT_LIST ( matchPredicateIdent )+ ) ;
	public final StatementBuilder.matchPredicateIdentList_return matchPredicateIdentList() throws RecognitionException {
		StatementBuilder.matchPredicateIdentList_return retval = new StatementBuilder.matchPredicateIdentList_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree MATCH_PREDICATE_IDENT_LIST268=null;
		TreeRuleReturnScope matchPredicateIdent269 =null;

		CommonTree MATCH_PREDICATE_IDENT_LIST268_tree=null;

		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:565:5: ( ^( MATCH_PREDICATE_IDENT_LIST ( matchPredicateIdent )+ ) )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:565:7: ^( MATCH_PREDICATE_IDENT_LIST ( matchPredicateIdent )+ )
			{
			root_0 = (CommonTree)adaptor.nil();


			_last = (CommonTree)input.LT(1);
			{
			CommonTree _save_last_1 = _last;
			CommonTree _first_1 = null;
			CommonTree root_1 = (CommonTree)adaptor.nil();
			_last = (CommonTree)input.LT(1);
			MATCH_PREDICATE_IDENT_LIST268=(CommonTree)match(input,MATCH_PREDICATE_IDENT_LIST,FOLLOW_MATCH_PREDICATE_IDENT_LIST_in_matchPredicateIdentList5548); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			MATCH_PREDICATE_IDENT_LIST268_tree = (CommonTree)adaptor.dupNode(MATCH_PREDICATE_IDENT_LIST268);


			root_1 = (CommonTree)adaptor.becomeRoot(MATCH_PREDICATE_IDENT_LIST268_tree, root_1);
			}

			match(input, Token.DOWN, null); if (state.failed) return retval;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:565:36: ( matchPredicateIdent )+
			int cnt65=0;
			loop65:
			while (true) {
				int alt65=2;
				int LA65_0 = input.LA(1);
				if ( (LA65_0==MATCH_PREDICATE_IDENT) ) {
					alt65=1;
				}

				switch (alt65) {
				case 1 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:565:37: matchPredicateIdent
					{
					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_matchPredicateIdent_in_matchPredicateIdentList5551);
					matchPredicateIdent269=matchPredicateIdent();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, matchPredicateIdent269.getTree());

					if ( state.backtracking==0 ) { retval.value.add((matchPredicateIdent269!=null?((StatementBuilder.matchPredicateIdent_return)matchPredicateIdent269).value:null)); }
					if ( state.backtracking==0 ) {
					}

					}
					break;

				default :
					if ( cnt65 >= 1 ) break loop65;
					if (state.backtracking>0) {state.failed=true; return retval;}
					EarlyExitException eee = new EarlyExitException(65, input);
					throw eee;
				}
				cnt65++;
			}

			match(input, Token.UP, null); if (state.failed) return retval;
			adaptor.addChild(root_0, root_1);
			_last = _save_last_1;
			}


			if ( state.backtracking==0 ) {
			}

			}

			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "matchPredicateIdentList"


	public static class matchPredicateIdent_return extends TreeRuleReturnScope {
		public MatchPredicateColumnIdent value;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "matchPredicateIdent"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:568:1: matchPredicateIdent returns [MatchPredicateColumnIdent value] : ^( MATCH_PREDICATE_IDENT expr ( parameterOrSimpleLiteral )? ) ;
	public final StatementBuilder.matchPredicateIdent_return matchPredicateIdent() throws RecognitionException {
		StatementBuilder.matchPredicateIdent_return retval = new StatementBuilder.matchPredicateIdent_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree MATCH_PREDICATE_IDENT270=null;
		TreeRuleReturnScope expr271 =null;
		TreeRuleReturnScope parameterOrSimpleLiteral272 =null;

		CommonTree MATCH_PREDICATE_IDENT270_tree=null;

		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:569:5: ( ^( MATCH_PREDICATE_IDENT expr ( parameterOrSimpleLiteral )? ) )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:569:7: ^( MATCH_PREDICATE_IDENT expr ( parameterOrSimpleLiteral )? )
			{
			root_0 = (CommonTree)adaptor.nil();


			_last = (CommonTree)input.LT(1);
			{
			CommonTree _save_last_1 = _last;
			CommonTree _first_1 = null;
			CommonTree root_1 = (CommonTree)adaptor.nil();
			_last = (CommonTree)input.LT(1);
			MATCH_PREDICATE_IDENT270=(CommonTree)match(input,MATCH_PREDICATE_IDENT,FOLLOW_MATCH_PREDICATE_IDENT_in_matchPredicateIdent5580); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			MATCH_PREDICATE_IDENT270_tree = (CommonTree)adaptor.dupNode(MATCH_PREDICATE_IDENT270);


			root_1 = (CommonTree)adaptor.becomeRoot(MATCH_PREDICATE_IDENT270_tree, root_1);
			}

			match(input, Token.DOWN, null); if (state.failed) return retval;
			_last = (CommonTree)input.LT(1);
			pushFollow(FOLLOW_expr_in_matchPredicateIdent5582);
			expr271=expr();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) 
			adaptor.addChild(root_1, expr271.getTree());

			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:569:36: ( parameterOrSimpleLiteral )?
			int alt66=2;
			int LA66_0 = input.LA(1);
			if ( (LA66_0==DECIMAL_VALUE||LA66_0==FALSE||LA66_0==IDENT_EXPR||LA66_0==INTEGER_VALUE||LA66_0==NULL||LA66_0==STRING||LA66_0==TRUE||LA66_0==328||LA66_0==338) ) {
				alt66=1;
			}
			switch (alt66) {
				case 1 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:569:36: parameterOrSimpleLiteral
					{
					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_parameterOrSimpleLiteral_in_matchPredicateIdent5584);
					parameterOrSimpleLiteral272=parameterOrSimpleLiteral();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, parameterOrSimpleLiteral272.getTree());

					if ( state.backtracking==0 ) {
					}

					}
					break;

			}

			match(input, Token.UP, null); if (state.failed) return retval;
			adaptor.addChild(root_0, root_1);
			_last = _save_last_1;
			}


			if ( state.backtracking==0 ) {
			            retval.value = new MatchPredicateColumnIdent((expr271!=null?((StatementBuilder.expr_return)expr271).value:null), (parameterOrSimpleLiteral272!=null?((StatementBuilder.parameterOrSimpleLiteral_return)parameterOrSimpleLiteral272).value:null));
			        }
			if ( state.backtracking==0 ) {
			}

			}

			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "matchPredicateIdent"


	public static class caseExpression_return extends TreeRuleReturnScope {
		public Expression value;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "caseExpression"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:575:1: caseExpression returns [Expression value] : ( ^( NULLIF a= expr b= expr ) | ^( COALESCE exprList ) | ^( SIMPLE_CASE v= expr whenList (e= expr )? ) | ^( SEARCHED_CASE whenList (e= expr )? ) | ^( IF c= expr t= expr (f= expr )? ) );
	public final StatementBuilder.caseExpression_return caseExpression() throws RecognitionException {
		StatementBuilder.caseExpression_return retval = new StatementBuilder.caseExpression_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree NULLIF273=null;
		CommonTree COALESCE274=null;
		CommonTree SIMPLE_CASE276=null;
		CommonTree SEARCHED_CASE278=null;
		CommonTree IF280=null;
		TreeRuleReturnScope a =null;
		TreeRuleReturnScope b =null;
		TreeRuleReturnScope v =null;
		TreeRuleReturnScope e =null;
		TreeRuleReturnScope c =null;
		TreeRuleReturnScope t =null;
		TreeRuleReturnScope f =null;
		TreeRuleReturnScope exprList275 =null;
		TreeRuleReturnScope whenList277 =null;
		TreeRuleReturnScope whenList279 =null;

		CommonTree NULLIF273_tree=null;
		CommonTree COALESCE274_tree=null;
		CommonTree SIMPLE_CASE276_tree=null;
		CommonTree SEARCHED_CASE278_tree=null;
		CommonTree IF280_tree=null;

		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:576:5: ( ^( NULLIF a= expr b= expr ) | ^( COALESCE exprList ) | ^( SIMPLE_CASE v= expr whenList (e= expr )? ) | ^( SEARCHED_CASE whenList (e= expr )? ) | ^( IF c= expr t= expr (f= expr )? ) )
			int alt70=5;
			switch ( input.LA(1) ) {
			case NULLIF:
				{
				alt70=1;
				}
				break;
			case COALESCE:
				{
				alt70=2;
				}
				break;
			case SIMPLE_CASE:
				{
				alt70=3;
				}
				break;
			case SEARCHED_CASE:
				{
				alt70=4;
				}
				break;
			case IF:
				{
				alt70=5;
				}
				break;
			default:
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 70, 0, input);
				throw nvae;
			}
			switch (alt70) {
				case 1 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:576:7: ^( NULLIF a= expr b= expr )
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					{
					CommonTree _save_last_1 = _last;
					CommonTree _first_1 = null;
					CommonTree root_1 = (CommonTree)adaptor.nil();
					_last = (CommonTree)input.LT(1);
					NULLIF273=(CommonTree)match(input,NULLIF,FOLLOW_NULLIF_in_caseExpression5618); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					NULLIF273_tree = (CommonTree)adaptor.dupNode(NULLIF273);


					root_1 = (CommonTree)adaptor.becomeRoot(NULLIF273_tree, root_1);
					}

					match(input, Token.DOWN, null); if (state.failed) return retval;
					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_expr_in_caseExpression5622);
					a=expr();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, a.getTree());

					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_expr_in_caseExpression5626);
					b=expr();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, b.getTree());

					match(input, Token.UP, null); if (state.failed) return retval;
					adaptor.addChild(root_0, root_1);
					_last = _save_last_1;
					}


					if ( state.backtracking==0 ) { retval.value = new NullIfExpression((a!=null?((StatementBuilder.expr_return)a).value:null), (b!=null?((StatementBuilder.expr_return)b).value:null)); }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 2 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:577:7: ^( COALESCE exprList )
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					{
					CommonTree _save_last_1 = _last;
					CommonTree _first_1 = null;
					CommonTree root_1 = (CommonTree)adaptor.nil();
					_last = (CommonTree)input.LT(1);
					COALESCE274=(CommonTree)match(input,COALESCE,FOLLOW_COALESCE_in_caseExpression5653); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					COALESCE274_tree = (CommonTree)adaptor.dupNode(COALESCE274);


					root_1 = (CommonTree)adaptor.becomeRoot(COALESCE274_tree, root_1);
					}

					if ( input.LA(1)==Token.DOWN ) {
						match(input, Token.DOWN, null); if (state.failed) return retval;
						_last = (CommonTree)input.LT(1);
						pushFollow(FOLLOW_exprList_in_caseExpression5655);
						exprList275=exprList();
						state._fsp--;
						if (state.failed) return retval;
						if ( state.backtracking==0 ) 
						adaptor.addChild(root_1, exprList275.getTree());

						match(input, Token.UP, null); if (state.failed) return retval;
					}
					adaptor.addChild(root_0, root_1);
					_last = _save_last_1;
					}


					if ( state.backtracking==0 ) { retval.value = new CoalesceExpression((exprList275!=null?((StatementBuilder.exprList_return)exprList275).value:null)); }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 3 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:578:7: ^( SIMPLE_CASE v= expr whenList (e= expr )? )
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					{
					CommonTree _save_last_1 = _last;
					CommonTree _first_1 = null;
					CommonTree root_1 = (CommonTree)adaptor.nil();
					_last = (CommonTree)input.LT(1);
					SIMPLE_CASE276=(CommonTree)match(input,SIMPLE_CASE,FOLLOW_SIMPLE_CASE_in_caseExpression5685); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					SIMPLE_CASE276_tree = (CommonTree)adaptor.dupNode(SIMPLE_CASE276);


					root_1 = (CommonTree)adaptor.becomeRoot(SIMPLE_CASE276_tree, root_1);
					}

					match(input, Token.DOWN, null); if (state.failed) return retval;
					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_expr_in_caseExpression5689);
					v=expr();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, v.getTree());

					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_whenList_in_caseExpression5691);
					whenList277=whenList();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, whenList277.getTree());

					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:578:38: (e= expr )?
					int alt67=2;
					int LA67_0 = input.LA(1);
					if ( (LA67_0==AND||(LA67_0 >= ARRAY_CMP && LA67_0 <= ARRAY_NOT_LIKE)||LA67_0==BETWEEN||LA67_0==CAST||LA67_0==COALESCE||LA67_0==CURRENT_DATE||(LA67_0 >= CURRENT_TIME && LA67_0 <= CURRENT_TIMESTAMP)||LA67_0==DATE||LA67_0==DECIMAL_VALUE||LA67_0==EQ||LA67_0==EXISTS||(LA67_0 >= EXTRACT && LA67_0 <= FALSE)||LA67_0==FUNCTION_CALL||(LA67_0 >= GT && LA67_0 <= GTE)||LA67_0==IDENT_EXPR||LA67_0==IF||LA67_0==IN||LA67_0==INTEGER_VALUE||LA67_0==IN_LIST||(LA67_0 >= IS_DISTINCT_FROM && LA67_0 <= IS_NULL)||LA67_0==LIKE||(LA67_0 >= LT && LA67_0 <= MATCH)||(LA67_0 >= NEGATIVE && LA67_0 <= NEQ)||LA67_0==NOT||(LA67_0 >= NULL && LA67_0 <= NULLIF)||LA67_0==OBJECT_LITERAL||LA67_0==OR||LA67_0==QNAME||LA67_0==QUERY||(LA67_0 >= REGEX_MATCH && LA67_0 <= REGEX_NO_MATCH_CI)||LA67_0==SEARCHED_CASE||LA67_0==SIMPLE_CASE||LA67_0==STRING||(LA67_0 >= TIME && LA67_0 <= TIMESTAMP)||(LA67_0 >= TRUE && LA67_0 <= TRY_CAST)||(LA67_0 >= 328 && LA67_0 <= 329)||(LA67_0 >= 332 && LA67_0 <= 333)||LA67_0==335||(LA67_0 >= 337 && LA67_0 <= 338)||LA67_0==341) ) {
						alt67=1;
					}
					switch (alt67) {
						case 1 :
							// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:578:38: e= expr
							{
							_last = (CommonTree)input.LT(1);
							pushFollow(FOLLOW_expr_in_caseExpression5695);
							e=expr();
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) 
							adaptor.addChild(root_1, e.getTree());

							if ( state.backtracking==0 ) {
							}

							}
							break;

					}

					match(input, Token.UP, null); if (state.failed) return retval;
					adaptor.addChild(root_0, root_1);
					_last = _save_last_1;
					}


					if ( state.backtracking==0 ) { retval.value = new SimpleCaseExpression((v!=null?((StatementBuilder.expr_return)v).value:null), (whenList277!=null?((StatementBuilder.whenList_return)whenList277).value:null), (e!=null?((StatementBuilder.expr_return)e).value:null)); }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 4 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:579:7: ^( SEARCHED_CASE whenList (e= expr )? )
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					{
					CommonTree _save_last_1 = _last;
					CommonTree _first_1 = null;
					CommonTree root_1 = (CommonTree)adaptor.nil();
					_last = (CommonTree)input.LT(1);
					SEARCHED_CASE278=(CommonTree)match(input,SEARCHED_CASE,FOLLOW_SEARCHED_CASE_in_caseExpression5708); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					SEARCHED_CASE278_tree = (CommonTree)adaptor.dupNode(SEARCHED_CASE278);


					root_1 = (CommonTree)adaptor.becomeRoot(SEARCHED_CASE278_tree, root_1);
					}

					match(input, Token.DOWN, null); if (state.failed) return retval;
					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_whenList_in_caseExpression5710);
					whenList279=whenList();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, whenList279.getTree());

					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:579:33: (e= expr )?
					int alt68=2;
					int LA68_0 = input.LA(1);
					if ( (LA68_0==AND||(LA68_0 >= ARRAY_CMP && LA68_0 <= ARRAY_NOT_LIKE)||LA68_0==BETWEEN||LA68_0==CAST||LA68_0==COALESCE||LA68_0==CURRENT_DATE||(LA68_0 >= CURRENT_TIME && LA68_0 <= CURRENT_TIMESTAMP)||LA68_0==DATE||LA68_0==DECIMAL_VALUE||LA68_0==EQ||LA68_0==EXISTS||(LA68_0 >= EXTRACT && LA68_0 <= FALSE)||LA68_0==FUNCTION_CALL||(LA68_0 >= GT && LA68_0 <= GTE)||LA68_0==IDENT_EXPR||LA68_0==IF||LA68_0==IN||LA68_0==INTEGER_VALUE||LA68_0==IN_LIST||(LA68_0 >= IS_DISTINCT_FROM && LA68_0 <= IS_NULL)||LA68_0==LIKE||(LA68_0 >= LT && LA68_0 <= MATCH)||(LA68_0 >= NEGATIVE && LA68_0 <= NEQ)||LA68_0==NOT||(LA68_0 >= NULL && LA68_0 <= NULLIF)||LA68_0==OBJECT_LITERAL||LA68_0==OR||LA68_0==QNAME||LA68_0==QUERY||(LA68_0 >= REGEX_MATCH && LA68_0 <= REGEX_NO_MATCH_CI)||LA68_0==SEARCHED_CASE||LA68_0==SIMPLE_CASE||LA68_0==STRING||(LA68_0 >= TIME && LA68_0 <= TIMESTAMP)||(LA68_0 >= TRUE && LA68_0 <= TRY_CAST)||(LA68_0 >= 328 && LA68_0 <= 329)||(LA68_0 >= 332 && LA68_0 <= 333)||LA68_0==335||(LA68_0 >= 337 && LA68_0 <= 338)||LA68_0==341) ) {
						alt68=1;
					}
					switch (alt68) {
						case 1 :
							// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:579:33: e= expr
							{
							_last = (CommonTree)input.LT(1);
							pushFollow(FOLLOW_expr_in_caseExpression5714);
							e=expr();
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) 
							adaptor.addChild(root_1, e.getTree());

							if ( state.backtracking==0 ) {
							}

							}
							break;

					}

					match(input, Token.UP, null); if (state.failed) return retval;
					adaptor.addChild(root_0, root_1);
					_last = _save_last_1;
					}


					if ( state.backtracking==0 ) { retval.value = new SearchedCaseExpression((whenList279!=null?((StatementBuilder.whenList_return)whenList279).value:null), (e!=null?((StatementBuilder.expr_return)e).value:null)); }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 5 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:580:7: ^( IF c= expr t= expr (f= expr )? )
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					{
					CommonTree _save_last_1 = _last;
					CommonTree _first_1 = null;
					CommonTree root_1 = (CommonTree)adaptor.nil();
					_last = (CommonTree)input.LT(1);
					IF280=(CommonTree)match(input,IF,FOLLOW_IF_in_caseExpression5732); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					IF280_tree = (CommonTree)adaptor.dupNode(IF280);


					root_1 = (CommonTree)adaptor.becomeRoot(IF280_tree, root_1);
					}

					match(input, Token.DOWN, null); if (state.failed) return retval;
					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_expr_in_caseExpression5736);
					c=expr();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, c.getTree());

					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_expr_in_caseExpression5740);
					t=expr();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, t.getTree());

					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:580:27: (f= expr )?
					int alt69=2;
					int LA69_0 = input.LA(1);
					if ( (LA69_0==AND||(LA69_0 >= ARRAY_CMP && LA69_0 <= ARRAY_NOT_LIKE)||LA69_0==BETWEEN||LA69_0==CAST||LA69_0==COALESCE||LA69_0==CURRENT_DATE||(LA69_0 >= CURRENT_TIME && LA69_0 <= CURRENT_TIMESTAMP)||LA69_0==DATE||LA69_0==DECIMAL_VALUE||LA69_0==EQ||LA69_0==EXISTS||(LA69_0 >= EXTRACT && LA69_0 <= FALSE)||LA69_0==FUNCTION_CALL||(LA69_0 >= GT && LA69_0 <= GTE)||LA69_0==IDENT_EXPR||LA69_0==IF||LA69_0==IN||LA69_0==INTEGER_VALUE||LA69_0==IN_LIST||(LA69_0 >= IS_DISTINCT_FROM && LA69_0 <= IS_NULL)||LA69_0==LIKE||(LA69_0 >= LT && LA69_0 <= MATCH)||(LA69_0 >= NEGATIVE && LA69_0 <= NEQ)||LA69_0==NOT||(LA69_0 >= NULL && LA69_0 <= NULLIF)||LA69_0==OBJECT_LITERAL||LA69_0==OR||LA69_0==QNAME||LA69_0==QUERY||(LA69_0 >= REGEX_MATCH && LA69_0 <= REGEX_NO_MATCH_CI)||LA69_0==SEARCHED_CASE||LA69_0==SIMPLE_CASE||LA69_0==STRING||(LA69_0 >= TIME && LA69_0 <= TIMESTAMP)||(LA69_0 >= TRUE && LA69_0 <= TRY_CAST)||(LA69_0 >= 328 && LA69_0 <= 329)||(LA69_0 >= 332 && LA69_0 <= 333)||LA69_0==335||(LA69_0 >= 337 && LA69_0 <= 338)||LA69_0==341) ) {
						alt69=1;
					}
					switch (alt69) {
						case 1 :
							// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:580:27: f= expr
							{
							_last = (CommonTree)input.LT(1);
							pushFollow(FOLLOW_expr_in_caseExpression5744);
							f=expr();
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) 
							adaptor.addChild(root_1, f.getTree());

							if ( state.backtracking==0 ) {
							}

							}
							break;

					}

					match(input, Token.UP, null); if (state.failed) return retval;
					adaptor.addChild(root_0, root_1);
					_last = _save_last_1;
					}


					if ( state.backtracking==0 ) { retval.value = new IfExpression((c!=null?((StatementBuilder.expr_return)c).value:null), (t!=null?((StatementBuilder.expr_return)t).value:null), (f!=null?((StatementBuilder.expr_return)f).value:null)); }
					if ( state.backtracking==0 ) {
					}

					}
					break;

			}
			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "caseExpression"


	public static class whenList_return extends TreeRuleReturnScope {
		public List<WhenClause> value = new ArrayList<>();
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "whenList"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:583:1: whenList returns [List<WhenClause> value = new ArrayList<>()] : ( ^( WHEN a= expr b= expr ) )+ ;
	public final StatementBuilder.whenList_return whenList() throws RecognitionException {
		StatementBuilder.whenList_return retval = new StatementBuilder.whenList_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree WHEN281=null;
		TreeRuleReturnScope a =null;
		TreeRuleReturnScope b =null;

		CommonTree WHEN281_tree=null;

		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:584:5: ( ( ^( WHEN a= expr b= expr ) )+ )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:584:7: ( ^( WHEN a= expr b= expr ) )+
			{
			root_0 = (CommonTree)adaptor.nil();


			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:584:7: ( ^( WHEN a= expr b= expr ) )+
			int cnt71=0;
			loop71:
			while (true) {
				int alt71=2;
				int LA71_0 = input.LA(1);
				if ( (LA71_0==WHEN) ) {
					alt71=1;
				}

				switch (alt71) {
				case 1 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:584:9: ^( WHEN a= expr b= expr )
					{
					_last = (CommonTree)input.LT(1);
					{
					CommonTree _save_last_1 = _last;
					CommonTree _first_1 = null;
					CommonTree root_1 = (CommonTree)adaptor.nil();
					_last = (CommonTree)input.LT(1);
					WHEN281=(CommonTree)match(input,WHEN,FOLLOW_WHEN_in_whenList5783); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					WHEN281_tree = (CommonTree)adaptor.dupNode(WHEN281);


					root_1 = (CommonTree)adaptor.becomeRoot(WHEN281_tree, root_1);
					}

					match(input, Token.DOWN, null); if (state.failed) return retval;
					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_expr_in_whenList5787);
					a=expr();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, a.getTree());

					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_expr_in_whenList5791);
					b=expr();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, b.getTree());

					match(input, Token.UP, null); if (state.failed) return retval;
					adaptor.addChild(root_0, root_1);
					_last = _save_last_1;
					}


					if ( state.backtracking==0 ) { retval.value.add(new WhenClause((a!=null?((StatementBuilder.expr_return)a).value:null), (b!=null?((StatementBuilder.expr_return)b).value:null))); }
					if ( state.backtracking==0 ) {
					}

					}
					break;

				default :
					if ( cnt71 >= 1 ) break loop71;
					if (state.backtracking>0) {state.failed=true; return retval;}
					EarlyExitException eee = new EarlyExitException(71, input);
					throw eee;
				}
				cnt71++;
			}

			if ( state.backtracking==0 ) {
			}

			}

			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "whenList"


	public static class explain_return extends TreeRuleReturnScope {
		public Statement value;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "explain"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:587:1: explain returns [Statement value] : ^( EXPLAIN ( explainOptions )? statement ) ;
	public final StatementBuilder.explain_return explain() throws RecognitionException {
		StatementBuilder.explain_return retval = new StatementBuilder.explain_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree EXPLAIN282=null;
		TreeRuleReturnScope explainOptions283 =null;
		TreeRuleReturnScope statement284 =null;

		CommonTree EXPLAIN282_tree=null;

		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:588:5: ( ^( EXPLAIN ( explainOptions )? statement ) )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:588:7: ^( EXPLAIN ( explainOptions )? statement )
			{
			root_0 = (CommonTree)adaptor.nil();


			_last = (CommonTree)input.LT(1);
			{
			CommonTree _save_last_1 = _last;
			CommonTree _first_1 = null;
			CommonTree root_1 = (CommonTree)adaptor.nil();
			_last = (CommonTree)input.LT(1);
			EXPLAIN282=(CommonTree)match(input,EXPLAIN,FOLLOW_EXPLAIN_in_explain5819); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			EXPLAIN282_tree = (CommonTree)adaptor.dupNode(EXPLAIN282);


			root_1 = (CommonTree)adaptor.becomeRoot(EXPLAIN282_tree, root_1);
			}

			match(input, Token.DOWN, null); if (state.failed) return retval;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:588:17: ( explainOptions )?
			int alt72=2;
			int LA72_0 = input.LA(1);
			if ( (LA72_0==EXPLAIN_OPTIONS) ) {
				alt72=1;
			}
			switch (alt72) {
				case 1 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:588:17: explainOptions
					{
					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_explainOptions_in_explain5821);
					explainOptions283=explainOptions();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, explainOptions283.getTree());

					if ( state.backtracking==0 ) {
					}

					}
					break;

			}

			_last = (CommonTree)input.LT(1);
			pushFollow(FOLLOW_statement_in_explain5824);
			statement284=statement();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) 
			adaptor.addChild(root_1, statement284.getTree());

			match(input, Token.UP, null); if (state.failed) return retval;
			adaptor.addChild(root_0, root_1);
			_last = _save_last_1;
			}


			if ( state.backtracking==0 ) { retval.value = new Explain((statement284!=null?((StatementBuilder.statement_return)statement284).value:null), (explainOptions283!=null?((StatementBuilder.explainOptions_return)explainOptions283).value:null)); }
			if ( state.backtracking==0 ) {
			}

			}

			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "explain"


	public static class explainOptions_return extends TreeRuleReturnScope {
		public List<ExplainOption> value = new ArrayList<>();
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "explainOptions"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:591:1: explainOptions returns [List<ExplainOption> value = new ArrayList<>()] : ^( EXPLAIN_OPTIONS ( explainOption )+ ) ;
	public final StatementBuilder.explainOptions_return explainOptions() throws RecognitionException {
		StatementBuilder.explainOptions_return retval = new StatementBuilder.explainOptions_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree EXPLAIN_OPTIONS285=null;
		TreeRuleReturnScope explainOption286 =null;

		CommonTree EXPLAIN_OPTIONS285_tree=null;

		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:592:5: ( ^( EXPLAIN_OPTIONS ( explainOption )+ ) )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:592:7: ^( EXPLAIN_OPTIONS ( explainOption )+ )
			{
			root_0 = (CommonTree)adaptor.nil();


			_last = (CommonTree)input.LT(1);
			{
			CommonTree _save_last_1 = _last;
			CommonTree _first_1 = null;
			CommonTree root_1 = (CommonTree)adaptor.nil();
			_last = (CommonTree)input.LT(1);
			EXPLAIN_OPTIONS285=(CommonTree)match(input,EXPLAIN_OPTIONS,FOLLOW_EXPLAIN_OPTIONS_in_explainOptions5849); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			EXPLAIN_OPTIONS285_tree = (CommonTree)adaptor.dupNode(EXPLAIN_OPTIONS285);


			root_1 = (CommonTree)adaptor.becomeRoot(EXPLAIN_OPTIONS285_tree, root_1);
			}

			match(input, Token.DOWN, null); if (state.failed) return retval;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:592:25: ( explainOption )+
			int cnt73=0;
			loop73:
			while (true) {
				int alt73=2;
				int LA73_0 = input.LA(1);
				if ( (LA73_0==EXPLAIN_FORMAT||LA73_0==EXPLAIN_TYPE) ) {
					alt73=1;
				}

				switch (alt73) {
				case 1 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:592:27: explainOption
					{
					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_explainOption_in_explainOptions5853);
					explainOption286=explainOption();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, explainOption286.getTree());

					if ( state.backtracking==0 ) { retval.value.add((explainOption286!=null?((StatementBuilder.explainOption_return)explainOption286).value:null)); }
					if ( state.backtracking==0 ) {
					}

					}
					break;

				default :
					if ( cnt73 >= 1 ) break loop73;
					if (state.backtracking>0) {state.failed=true; return retval;}
					EarlyExitException eee = new EarlyExitException(73, input);
					throw eee;
				}
				cnt73++;
			}

			match(input, Token.UP, null); if (state.failed) return retval;
			adaptor.addChild(root_0, root_1);
			_last = _save_last_1;
			}


			if ( state.backtracking==0 ) {
			}

			}

			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "explainOptions"


	public static class explainOption_return extends TreeRuleReturnScope {
		public ExplainOption value;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "explainOption"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:595:1: explainOption returns [ExplainOption value] : ( ^( EXPLAIN_FORMAT TEXT ) | ^( EXPLAIN_FORMAT GRAPHVIZ ) | ^( EXPLAIN_TYPE LOGICAL ) | ^( EXPLAIN_TYPE DISTRIBUTED ) );
	public final StatementBuilder.explainOption_return explainOption() throws RecognitionException {
		StatementBuilder.explainOption_return retval = new StatementBuilder.explainOption_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree EXPLAIN_FORMAT287=null;
		CommonTree TEXT288=null;
		CommonTree EXPLAIN_FORMAT289=null;
		CommonTree GRAPHVIZ290=null;
		CommonTree EXPLAIN_TYPE291=null;
		CommonTree LOGICAL292=null;
		CommonTree EXPLAIN_TYPE293=null;
		CommonTree DISTRIBUTED294=null;

		CommonTree EXPLAIN_FORMAT287_tree=null;
		CommonTree TEXT288_tree=null;
		CommonTree EXPLAIN_FORMAT289_tree=null;
		CommonTree GRAPHVIZ290_tree=null;
		CommonTree EXPLAIN_TYPE291_tree=null;
		CommonTree LOGICAL292_tree=null;
		CommonTree EXPLAIN_TYPE293_tree=null;
		CommonTree DISTRIBUTED294_tree=null;

		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:596:5: ( ^( EXPLAIN_FORMAT TEXT ) | ^( EXPLAIN_FORMAT GRAPHVIZ ) | ^( EXPLAIN_TYPE LOGICAL ) | ^( EXPLAIN_TYPE DISTRIBUTED ) )
			int alt74=4;
			int LA74_0 = input.LA(1);
			if ( (LA74_0==EXPLAIN_FORMAT) ) {
				int LA74_1 = input.LA(2);
				if ( (LA74_1==DOWN) ) {
					int LA74_3 = input.LA(3);
					if ( (LA74_3==TEXT) ) {
						alt74=1;
					}
					else if ( (LA74_3==GRAPHVIZ) ) {
						alt74=2;
					}

					else {
						if (state.backtracking>0) {state.failed=true; return retval;}
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 74, 3, input);
							throw nvae;
						} finally {
							input.rewind(nvaeMark);
						}
					}

				}

				else {
					if (state.backtracking>0) {state.failed=true; return retval;}
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 74, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

			}
			else if ( (LA74_0==EXPLAIN_TYPE) ) {
				int LA74_2 = input.LA(2);
				if ( (LA74_2==DOWN) ) {
					int LA74_4 = input.LA(3);
					if ( (LA74_4==LOGICAL) ) {
						alt74=3;
					}
					else if ( (LA74_4==DISTRIBUTED) ) {
						alt74=4;
					}

					else {
						if (state.backtracking>0) {state.failed=true; return retval;}
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 74, 4, input);
							throw nvae;
						} finally {
							input.rewind(nvaeMark);
						}
					}

				}

				else {
					if (state.backtracking>0) {state.failed=true; return retval;}
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 74, 2, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

			}

			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 74, 0, input);
				throw nvae;
			}

			switch (alt74) {
				case 1 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:596:7: ^( EXPLAIN_FORMAT TEXT )
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					{
					CommonTree _save_last_1 = _last;
					CommonTree _first_1 = null;
					CommonTree root_1 = (CommonTree)adaptor.nil();
					_last = (CommonTree)input.LT(1);
					EXPLAIN_FORMAT287=(CommonTree)match(input,EXPLAIN_FORMAT,FOLLOW_EXPLAIN_FORMAT_in_explainOption5882); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					EXPLAIN_FORMAT287_tree = (CommonTree)adaptor.dupNode(EXPLAIN_FORMAT287);


					root_1 = (CommonTree)adaptor.becomeRoot(EXPLAIN_FORMAT287_tree, root_1);
					}

					match(input, Token.DOWN, null); if (state.failed) return retval;
					_last = (CommonTree)input.LT(1);
					TEXT288=(CommonTree)match(input,TEXT,FOLLOW_TEXT_in_explainOption5884); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					TEXT288_tree = (CommonTree)adaptor.dupNode(TEXT288);


					adaptor.addChild(root_1, TEXT288_tree);
					}

					match(input, Token.UP, null); if (state.failed) return retval;
					adaptor.addChild(root_0, root_1);
					_last = _save_last_1;
					}


					if ( state.backtracking==0 ) { retval.value = new ExplainFormat(ExplainFormat.Type.TEXT); }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 2 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:597:7: ^( EXPLAIN_FORMAT GRAPHVIZ )
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					{
					CommonTree _save_last_1 = _last;
					CommonTree _first_1 = null;
					CommonTree root_1 = (CommonTree)adaptor.nil();
					_last = (CommonTree)input.LT(1);
					EXPLAIN_FORMAT289=(CommonTree)match(input,EXPLAIN_FORMAT,FOLLOW_EXPLAIN_FORMAT_in_explainOption5901); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					EXPLAIN_FORMAT289_tree = (CommonTree)adaptor.dupNode(EXPLAIN_FORMAT289);


					root_1 = (CommonTree)adaptor.becomeRoot(EXPLAIN_FORMAT289_tree, root_1);
					}

					match(input, Token.DOWN, null); if (state.failed) return retval;
					_last = (CommonTree)input.LT(1);
					GRAPHVIZ290=(CommonTree)match(input,GRAPHVIZ,FOLLOW_GRAPHVIZ_in_explainOption5903); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					GRAPHVIZ290_tree = (CommonTree)adaptor.dupNode(GRAPHVIZ290);


					adaptor.addChild(root_1, GRAPHVIZ290_tree);
					}

					match(input, Token.UP, null); if (state.failed) return retval;
					adaptor.addChild(root_0, root_1);
					_last = _save_last_1;
					}


					if ( state.backtracking==0 ) { retval.value = new ExplainFormat(ExplainFormat.Type.GRAPHVIZ); }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 3 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:598:7: ^( EXPLAIN_TYPE LOGICAL )
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					{
					CommonTree _save_last_1 = _last;
					CommonTree _first_1 = null;
					CommonTree root_1 = (CommonTree)adaptor.nil();
					_last = (CommonTree)input.LT(1);
					EXPLAIN_TYPE291=(CommonTree)match(input,EXPLAIN_TYPE,FOLLOW_EXPLAIN_TYPE_in_explainOption5916); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					EXPLAIN_TYPE291_tree = (CommonTree)adaptor.dupNode(EXPLAIN_TYPE291);


					root_1 = (CommonTree)adaptor.becomeRoot(EXPLAIN_TYPE291_tree, root_1);
					}

					match(input, Token.DOWN, null); if (state.failed) return retval;
					_last = (CommonTree)input.LT(1);
					LOGICAL292=(CommonTree)match(input,LOGICAL,FOLLOW_LOGICAL_in_explainOption5918); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					LOGICAL292_tree = (CommonTree)adaptor.dupNode(LOGICAL292);


					adaptor.addChild(root_1, LOGICAL292_tree);
					}

					match(input, Token.UP, null); if (state.failed) return retval;
					adaptor.addChild(root_0, root_1);
					_last = _save_last_1;
					}


					if ( state.backtracking==0 ) { retval.value = new ExplainType(ExplainType.Type.LOGICAL); }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 4 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:599:7: ^( EXPLAIN_TYPE DISTRIBUTED )
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					{
					CommonTree _save_last_1 = _last;
					CommonTree _first_1 = null;
					CommonTree root_1 = (CommonTree)adaptor.nil();
					_last = (CommonTree)input.LT(1);
					EXPLAIN_TYPE293=(CommonTree)match(input,EXPLAIN_TYPE,FOLLOW_EXPLAIN_TYPE_in_explainOption5934); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					EXPLAIN_TYPE293_tree = (CommonTree)adaptor.dupNode(EXPLAIN_TYPE293);


					root_1 = (CommonTree)adaptor.becomeRoot(EXPLAIN_TYPE293_tree, root_1);
					}

					match(input, Token.DOWN, null); if (state.failed) return retval;
					_last = (CommonTree)input.LT(1);
					DISTRIBUTED294=(CommonTree)match(input,DISTRIBUTED,FOLLOW_DISTRIBUTED_in_explainOption5936); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					DISTRIBUTED294_tree = (CommonTree)adaptor.dupNode(DISTRIBUTED294);


					adaptor.addChild(root_1, DISTRIBUTED294_tree);
					}

					match(input, Token.UP, null); if (state.failed) return retval;
					adaptor.addChild(root_0, root_1);
					_last = _save_last_1;
					}


					if ( state.backtracking==0 ) { retval.value = new ExplainType(ExplainType.Type.DISTRIBUTED); }
					if ( state.backtracking==0 ) {
					}

					}
					break;

			}
			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "explainOption"


	public static class showTables_return extends TreeRuleReturnScope {
		public Statement value;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "showTables"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:602:1: showTables returns [Statement value] : ^( SHOW_TABLES (schema= fromOrIn )? ( likePattern | whereClause )? ) ;
	public final StatementBuilder.showTables_return showTables() throws RecognitionException {
		StatementBuilder.showTables_return retval = new StatementBuilder.showTables_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree SHOW_TABLES295=null;
		TreeRuleReturnScope schema =null;
		TreeRuleReturnScope likePattern296 =null;
		TreeRuleReturnScope whereClause297 =null;

		CommonTree SHOW_TABLES295_tree=null;

		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:603:5: ( ^( SHOW_TABLES (schema= fromOrIn )? ( likePattern | whereClause )? ) )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:603:7: ^( SHOW_TABLES (schema= fromOrIn )? ( likePattern | whereClause )? )
			{
			root_0 = (CommonTree)adaptor.nil();


			_last = (CommonTree)input.LT(1);
			{
			CommonTree _save_last_1 = _last;
			CommonTree _first_1 = null;
			CommonTree root_1 = (CommonTree)adaptor.nil();
			_last = (CommonTree)input.LT(1);
			SHOW_TABLES295=(CommonTree)match(input,SHOW_TABLES,FOLLOW_SHOW_TABLES_in_showTables5961); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			SHOW_TABLES295_tree = (CommonTree)adaptor.dupNode(SHOW_TABLES295);


			root_1 = (CommonTree)adaptor.becomeRoot(SHOW_TABLES295_tree, root_1);
			}

			if ( input.LA(1)==Token.DOWN ) {
				match(input, Token.DOWN, null); if (state.failed) return retval;
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:603:27: (schema= fromOrIn )?
				int alt75=2;
				int LA75_0 = input.LA(1);
				if ( (LA75_0==FROM||LA75_0==IN) ) {
					alt75=1;
				}
				switch (alt75) {
					case 1 :
						// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:603:27: schema= fromOrIn
						{
						_last = (CommonTree)input.LT(1);
						pushFollow(FOLLOW_fromOrIn_in_showTables5965);
						schema=fromOrIn();
						state._fsp--;
						if (state.failed) return retval;
						if ( state.backtracking==0 ) 
						adaptor.addChild(root_1, schema.getTree());

						if ( state.backtracking==0 ) {
						}

						}
						break;

				}

				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:603:38: ( likePattern | whereClause )?
				int alt76=3;
				int LA76_0 = input.LA(1);
				if ( (LA76_0==LIKE) ) {
					alt76=1;
				}
				else if ( (LA76_0==WHERE) ) {
					alt76=2;
				}
				switch (alt76) {
					case 1 :
						// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:603:39: likePattern
						{
						_last = (CommonTree)input.LT(1);
						pushFollow(FOLLOW_likePattern_in_showTables5969);
						likePattern296=likePattern();
						state._fsp--;
						if (state.failed) return retval;
						if ( state.backtracking==0 ) 
						adaptor.addChild(root_1, likePattern296.getTree());

						if ( state.backtracking==0 ) {
						}

						}
						break;
					case 2 :
						// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:603:53: whereClause
						{
						_last = (CommonTree)input.LT(1);
						pushFollow(FOLLOW_whereClause_in_showTables5973);
						whereClause297=whereClause();
						state._fsp--;
						if (state.failed) return retval;
						if ( state.backtracking==0 ) 
						adaptor.addChild(root_1, whereClause297.getTree());

						if ( state.backtracking==0 ) {
						}

						}
						break;

				}

				match(input, Token.UP, null); if (state.failed) return retval;
			}
			adaptor.addChild(root_0, root_1);
			_last = _save_last_1;
			}


			if ( state.backtracking==0 ) { retval.value = new ShowTables((schema!=null?((StatementBuilder.fromOrIn_return)schema).value:null), (likePattern296!=null?((StatementBuilder.likePattern_return)likePattern296).value:null), (whereClause297!=null?((StatementBuilder.whereClause_return)whereClause297).value:null)); }
			if ( state.backtracking==0 ) {
			}

			}

			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "showTables"


	public static class showSchemas_return extends TreeRuleReturnScope {
		public Statement value;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "showSchemas"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:606:1: showSchemas returns [Statement value] : ^( SHOW_SCHEMAS ( likePattern | whereClause )? ) ;
	public final StatementBuilder.showSchemas_return showSchemas() throws RecognitionException {
		StatementBuilder.showSchemas_return retval = new StatementBuilder.showSchemas_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree SHOW_SCHEMAS298=null;
		TreeRuleReturnScope likePattern299 =null;
		TreeRuleReturnScope whereClause300 =null;

		CommonTree SHOW_SCHEMAS298_tree=null;

		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:607:5: ( ^( SHOW_SCHEMAS ( likePattern | whereClause )? ) )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:607:7: ^( SHOW_SCHEMAS ( likePattern | whereClause )? )
			{
			root_0 = (CommonTree)adaptor.nil();


			_last = (CommonTree)input.LT(1);
			{
			CommonTree _save_last_1 = _last;
			CommonTree _first_1 = null;
			CommonTree root_1 = (CommonTree)adaptor.nil();
			_last = (CommonTree)input.LT(1);
			SHOW_SCHEMAS298=(CommonTree)match(input,SHOW_SCHEMAS,FOLLOW_SHOW_SCHEMAS_in_showSchemas6000); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			SHOW_SCHEMAS298_tree = (CommonTree)adaptor.dupNode(SHOW_SCHEMAS298);


			root_1 = (CommonTree)adaptor.becomeRoot(SHOW_SCHEMAS298_tree, root_1);
			}

			if ( input.LA(1)==Token.DOWN ) {
				match(input, Token.DOWN, null); if (state.failed) return retval;
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:607:22: ( likePattern | whereClause )?
				int alt77=3;
				int LA77_0 = input.LA(1);
				if ( (LA77_0==LIKE) ) {
					alt77=1;
				}
				else if ( (LA77_0==WHERE) ) {
					alt77=2;
				}
				switch (alt77) {
					case 1 :
						// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:607:23: likePattern
						{
						_last = (CommonTree)input.LT(1);
						pushFollow(FOLLOW_likePattern_in_showSchemas6003);
						likePattern299=likePattern();
						state._fsp--;
						if (state.failed) return retval;
						if ( state.backtracking==0 ) 
						adaptor.addChild(root_1, likePattern299.getTree());

						if ( state.backtracking==0 ) {
						}

						}
						break;
					case 2 :
						// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:607:37: whereClause
						{
						_last = (CommonTree)input.LT(1);
						pushFollow(FOLLOW_whereClause_in_showSchemas6007);
						whereClause300=whereClause();
						state._fsp--;
						if (state.failed) return retval;
						if ( state.backtracking==0 ) 
						adaptor.addChild(root_1, whereClause300.getTree());

						if ( state.backtracking==0 ) {
						}

						}
						break;

				}

				match(input, Token.UP, null); if (state.failed) return retval;
			}
			adaptor.addChild(root_0, root_1);
			_last = _save_last_1;
			}


			if ( state.backtracking==0 ) { retval.value = new ShowSchemas((likePattern299!=null?((StatementBuilder.likePattern_return)likePattern299).value:null), (whereClause300!=null?((StatementBuilder.whereClause_return)whereClause300).value:null)); }
			if ( state.backtracking==0 ) {
			}

			}

			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "showSchemas"


	public static class likePattern_return extends TreeRuleReturnScope {
		public String value;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "likePattern"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:610:1: likePattern returns [String value] : ^( LIKE string ) ;
	public final StatementBuilder.likePattern_return likePattern() throws RecognitionException {
		StatementBuilder.likePattern_return retval = new StatementBuilder.likePattern_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree LIKE301=null;
		TreeRuleReturnScope string302 =null;

		CommonTree LIKE301_tree=null;

		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:611:5: ( ^( LIKE string ) )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:611:7: ^( LIKE string )
			{
			root_0 = (CommonTree)adaptor.nil();


			_last = (CommonTree)input.LT(1);
			{
			CommonTree _save_last_1 = _last;
			CommonTree _first_1 = null;
			CommonTree root_1 = (CommonTree)adaptor.nil();
			_last = (CommonTree)input.LT(1);
			LIKE301=(CommonTree)match(input,LIKE,FOLLOW_LIKE_in_likePattern6034); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			LIKE301_tree = (CommonTree)adaptor.dupNode(LIKE301);


			root_1 = (CommonTree)adaptor.becomeRoot(LIKE301_tree, root_1);
			}

			match(input, Token.DOWN, null); if (state.failed) return retval;
			_last = (CommonTree)input.LT(1);
			pushFollow(FOLLOW_string_in_likePattern6036);
			string302=string();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) 
			adaptor.addChild(root_1, string302.getTree());

			match(input, Token.UP, null); if (state.failed) return retval;
			adaptor.addChild(root_0, root_1);
			_last = _save_last_1;
			}


			if ( state.backtracking==0 ) { retval.value = (string302!=null?((StatementBuilder.string_return)string302).value:null); }
			if ( state.backtracking==0 ) {
			}

			}

			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "likePattern"


	public static class showCatalogs_return extends TreeRuleReturnScope {
		public Statement value;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "showCatalogs"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:614:1: showCatalogs returns [Statement value] : SHOW_CATALOGS ;
	public final StatementBuilder.showCatalogs_return showCatalogs() throws RecognitionException {
		StatementBuilder.showCatalogs_return retval = new StatementBuilder.showCatalogs_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree SHOW_CATALOGS303=null;

		CommonTree SHOW_CATALOGS303_tree=null;

		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:615:5: ( SHOW_CATALOGS )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:615:7: SHOW_CATALOGS
			{
			root_0 = (CommonTree)adaptor.nil();


			_last = (CommonTree)input.LT(1);
			SHOW_CATALOGS303=(CommonTree)match(input,SHOW_CATALOGS,FOLLOW_SHOW_CATALOGS_in_showCatalogs6060); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			SHOW_CATALOGS303_tree = (CommonTree)adaptor.dupNode(SHOW_CATALOGS303);


			adaptor.addChild(root_0, SHOW_CATALOGS303_tree);
			}

			if ( state.backtracking==0 ) { retval.value = new ShowCatalogs(); }
			if ( state.backtracking==0 ) {
			}

			}

			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "showCatalogs"


	public static class showColumns_return extends TreeRuleReturnScope {
		public Statement value;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "showColumns"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:618:1: showColumns returns [Statement value] : ( ^( SHOW_COLUMNS table= fromOrIn (schema= fromOrIn )? (like= likePattern )? ) | ^( SHOW_COLUMNS table= fromOrIn (schema= fromOrIn )? where= whereClause ) );
	public final StatementBuilder.showColumns_return showColumns() throws RecognitionException {
		StatementBuilder.showColumns_return retval = new StatementBuilder.showColumns_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree SHOW_COLUMNS304=null;
		CommonTree SHOW_COLUMNS305=null;
		TreeRuleReturnScope table =null;
		TreeRuleReturnScope schema =null;
		TreeRuleReturnScope like =null;
		TreeRuleReturnScope where =null;

		CommonTree SHOW_COLUMNS304_tree=null;
		CommonTree SHOW_COLUMNS305_tree=null;

		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:619:5: ( ^( SHOW_COLUMNS table= fromOrIn (schema= fromOrIn )? (like= likePattern )? ) | ^( SHOW_COLUMNS table= fromOrIn (schema= fromOrIn )? where= whereClause ) )
			int alt81=2;
			alt81 = dfa81.predict(input);
			switch (alt81) {
				case 1 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:619:7: ^( SHOW_COLUMNS table= fromOrIn (schema= fromOrIn )? (like= likePattern )? )
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					{
					CommonTree _save_last_1 = _last;
					CommonTree _first_1 = null;
					CommonTree root_1 = (CommonTree)adaptor.nil();
					_last = (CommonTree)input.LT(1);
					SHOW_COLUMNS304=(CommonTree)match(input,SHOW_COLUMNS,FOLLOW_SHOW_COLUMNS_in_showColumns6084); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					SHOW_COLUMNS304_tree = (CommonTree)adaptor.dupNode(SHOW_COLUMNS304);


					root_1 = (CommonTree)adaptor.becomeRoot(SHOW_COLUMNS304_tree, root_1);
					}

					match(input, Token.DOWN, null); if (state.failed) return retval;
					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_fromOrIn_in_showColumns6088);
					table=fromOrIn();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, table.getTree());

					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:619:43: (schema= fromOrIn )?
					int alt78=2;
					int LA78_0 = input.LA(1);
					if ( (LA78_0==FROM||LA78_0==IN) ) {
						alt78=1;
					}
					switch (alt78) {
						case 1 :
							// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:619:43: schema= fromOrIn
							{
							_last = (CommonTree)input.LT(1);
							pushFollow(FOLLOW_fromOrIn_in_showColumns6092);
							schema=fromOrIn();
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) 
							adaptor.addChild(root_1, schema.getTree());

							if ( state.backtracking==0 ) {
							}

							}
							break;

					}

					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:619:58: (like= likePattern )?
					int alt79=2;
					int LA79_0 = input.LA(1);
					if ( (LA79_0==LIKE) ) {
						alt79=1;
					}
					switch (alt79) {
						case 1 :
							// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:619:58: like= likePattern
							{
							_last = (CommonTree)input.LT(1);
							pushFollow(FOLLOW_likePattern_in_showColumns6097);
							like=likePattern();
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) 
							adaptor.addChild(root_1, like.getTree());

							if ( state.backtracking==0 ) {
							}

							}
							break;

					}

					match(input, Token.UP, null); if (state.failed) return retval;
					adaptor.addChild(root_0, root_1);
					_last = _save_last_1;
					}


					if ( state.backtracking==0 ) { retval.value = new ShowColumns((table!=null?((StatementBuilder.fromOrIn_return)table).value:null), (schema!=null?((StatementBuilder.fromOrIn_return)schema).value:null), (like!=null?((StatementBuilder.likePattern_return)like).value:null)); }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 2 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:620:7: ^( SHOW_COLUMNS table= fromOrIn (schema= fromOrIn )? where= whereClause )
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					{
					CommonTree _save_last_1 = _last;
					CommonTree _first_1 = null;
					CommonTree root_1 = (CommonTree)adaptor.nil();
					_last = (CommonTree)input.LT(1);
					SHOW_COLUMNS305=(CommonTree)match(input,SHOW_COLUMNS,FOLLOW_SHOW_COLUMNS_in_showColumns6110); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					SHOW_COLUMNS305_tree = (CommonTree)adaptor.dupNode(SHOW_COLUMNS305);


					root_1 = (CommonTree)adaptor.becomeRoot(SHOW_COLUMNS305_tree, root_1);
					}

					match(input, Token.DOWN, null); if (state.failed) return retval;
					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_fromOrIn_in_showColumns6114);
					table=fromOrIn();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, table.getTree());

					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:620:43: (schema= fromOrIn )?
					int alt80=2;
					int LA80_0 = input.LA(1);
					if ( (LA80_0==FROM||LA80_0==IN) ) {
						alt80=1;
					}
					switch (alt80) {
						case 1 :
							// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:620:43: schema= fromOrIn
							{
							_last = (CommonTree)input.LT(1);
							pushFollow(FOLLOW_fromOrIn_in_showColumns6118);
							schema=fromOrIn();
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) 
							adaptor.addChild(root_1, schema.getTree());

							if ( state.backtracking==0 ) {
							}

							}
							break;

					}

					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_whereClause_in_showColumns6123);
					where=whereClause();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, where.getTree());

					match(input, Token.UP, null); if (state.failed) return retval;
					adaptor.addChild(root_0, root_1);
					_last = _save_last_1;
					}


					if ( state.backtracking==0 ) { retval.value = new ShowColumns((table!=null?((StatementBuilder.fromOrIn_return)table).value:null), (schema!=null?((StatementBuilder.fromOrIn_return)schema).value:null), (where!=null?((StatementBuilder.whereClause_return)where).value:null)); }
					if ( state.backtracking==0 ) {
					}

					}
					break;

			}
			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "showColumns"


	public static class fromOrIn_return extends TreeRuleReturnScope {
		public QualifiedName value;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "fromOrIn"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:623:1: fromOrIn returns [QualifiedName value] : ( ^( FROM qname ) | ^( IN qname ) );
	public final StatementBuilder.fromOrIn_return fromOrIn() throws RecognitionException {
		StatementBuilder.fromOrIn_return retval = new StatementBuilder.fromOrIn_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree FROM306=null;
		CommonTree IN308=null;
		TreeRuleReturnScope qname307 =null;
		TreeRuleReturnScope qname309 =null;

		CommonTree FROM306_tree=null;
		CommonTree IN308_tree=null;

		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:624:5: ( ^( FROM qname ) | ^( IN qname ) )
			int alt82=2;
			int LA82_0 = input.LA(1);
			if ( (LA82_0==FROM) ) {
				alt82=1;
			}
			else if ( (LA82_0==IN) ) {
				alt82=2;
			}

			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 82, 0, input);
				throw nvae;
			}

			switch (alt82) {
				case 1 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:624:7: ^( FROM qname )
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					{
					CommonTree _save_last_1 = _last;
					CommonTree _first_1 = null;
					CommonTree root_1 = (CommonTree)adaptor.nil();
					_last = (CommonTree)input.LT(1);
					FROM306=(CommonTree)match(input,FROM,FOLLOW_FROM_in_fromOrIn6148); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					FROM306_tree = (CommonTree)adaptor.dupNode(FROM306);


					root_1 = (CommonTree)adaptor.becomeRoot(FROM306_tree, root_1);
					}

					match(input, Token.DOWN, null); if (state.failed) return retval;
					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_qname_in_fromOrIn6150);
					qname307=qname();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, qname307.getTree());

					match(input, Token.UP, null); if (state.failed) return retval;
					adaptor.addChild(root_0, root_1);
					_last = _save_last_1;
					}


					if ( state.backtracking==0 ) { retval.value = (qname307!=null?((StatementBuilder.qname_return)qname307).value:null); }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 2 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:625:7: ^( IN qname )
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					{
					CommonTree _save_last_1 = _last;
					CommonTree _first_1 = null;
					CommonTree root_1 = (CommonTree)adaptor.nil();
					_last = (CommonTree)input.LT(1);
					IN308=(CommonTree)match(input,IN,FOLLOW_IN_in_fromOrIn6162); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					IN308_tree = (CommonTree)adaptor.dupNode(IN308);


					root_1 = (CommonTree)adaptor.becomeRoot(IN308_tree, root_1);
					}

					match(input, Token.DOWN, null); if (state.failed) return retval;
					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_qname_in_fromOrIn6164);
					qname309=qname();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, qname309.getTree());

					match(input, Token.UP, null); if (state.failed) return retval;
					adaptor.addChild(root_0, root_1);
					_last = _save_last_1;
					}


					if ( state.backtracking==0 ) { retval.value = (qname309!=null?((StatementBuilder.qname_return)qname309).value:null); }
					if ( state.backtracking==0 ) {
					}

					}
					break;

			}
			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "fromOrIn"


	public static class showPartitions_return extends TreeRuleReturnScope {
		public Statement value;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "showPartitions"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:628:1: showPartitions returns [Statement value] : ^( SHOW_PARTITIONS qname ( whereClause )? ( orderClause )? ( limitClause )? ( offsetClause )? ) ;
	public final StatementBuilder.showPartitions_return showPartitions() throws RecognitionException {
		StatementBuilder.showPartitions_return retval = new StatementBuilder.showPartitions_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree SHOW_PARTITIONS310=null;
		TreeRuleReturnScope qname311 =null;
		TreeRuleReturnScope whereClause312 =null;
		TreeRuleReturnScope orderClause313 =null;
		TreeRuleReturnScope limitClause314 =null;
		TreeRuleReturnScope offsetClause315 =null;

		CommonTree SHOW_PARTITIONS310_tree=null;

		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:629:5: ( ^( SHOW_PARTITIONS qname ( whereClause )? ( orderClause )? ( limitClause )? ( offsetClause )? ) )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:629:7: ^( SHOW_PARTITIONS qname ( whereClause )? ( orderClause )? ( limitClause )? ( offsetClause )? )
			{
			root_0 = (CommonTree)adaptor.nil();


			_last = (CommonTree)input.LT(1);
			{
			CommonTree _save_last_1 = _last;
			CommonTree _first_1 = null;
			CommonTree root_1 = (CommonTree)adaptor.nil();
			_last = (CommonTree)input.LT(1);
			SHOW_PARTITIONS310=(CommonTree)match(input,SHOW_PARTITIONS,FOLLOW_SHOW_PARTITIONS_in_showPartitions6189); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			SHOW_PARTITIONS310_tree = (CommonTree)adaptor.dupNode(SHOW_PARTITIONS310);


			root_1 = (CommonTree)adaptor.becomeRoot(SHOW_PARTITIONS310_tree, root_1);
			}

			match(input, Token.DOWN, null); if (state.failed) return retval;
			_last = (CommonTree)input.LT(1);
			pushFollow(FOLLOW_qname_in_showPartitions6191);
			qname311=qname();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) 
			adaptor.addChild(root_1, qname311.getTree());

			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:629:31: ( whereClause )?
			int alt83=2;
			int LA83_0 = input.LA(1);
			if ( (LA83_0==WHERE) ) {
				alt83=1;
			}
			switch (alt83) {
				case 1 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:629:31: whereClause
					{
					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_whereClause_in_showPartitions6193);
					whereClause312=whereClause();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, whereClause312.getTree());

					if ( state.backtracking==0 ) {
					}

					}
					break;

			}

			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:629:44: ( orderClause )?
			int alt84=2;
			int LA84_0 = input.LA(1);
			if ( (LA84_0==ORDER_BY) ) {
				alt84=1;
			}
			switch (alt84) {
				case 1 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:629:44: orderClause
					{
					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_orderClause_in_showPartitions6196);
					orderClause313=orderClause();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, orderClause313.getTree());

					if ( state.backtracking==0 ) {
					}

					}
					break;

			}

			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:629:57: ( limitClause )?
			int alt85=2;
			int LA85_0 = input.LA(1);
			if ( (LA85_0==LIMIT) ) {
				alt85=1;
			}
			switch (alt85) {
				case 1 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:629:57: limitClause
					{
					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_limitClause_in_showPartitions6199);
					limitClause314=limitClause();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, limitClause314.getTree());

					if ( state.backtracking==0 ) {
					}

					}
					break;

			}

			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:629:70: ( offsetClause )?
			int alt86=2;
			int LA86_0 = input.LA(1);
			if ( (LA86_0==OFFSET) ) {
				alt86=1;
			}
			switch (alt86) {
				case 1 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:629:70: offsetClause
					{
					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_offsetClause_in_showPartitions6202);
					offsetClause315=offsetClause();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, offsetClause315.getTree());

					if ( state.backtracking==0 ) {
					}

					}
					break;

			}

			match(input, Token.UP, null); if (state.failed) return retval;
			adaptor.addChild(root_0, root_1);
			_last = _save_last_1;
			}


			if ( state.backtracking==0 ) { retval.value = new ShowPartitions(
			            (qname311!=null?((StatementBuilder.qname_return)qname311).value:null),
			            Optional.fromNullable((whereClause312!=null?((StatementBuilder.whereClause_return)whereClause312).value:null)),
			            MoreObjects.firstNonNull((orderClause313!=null?((StatementBuilder.orderClause_return)orderClause313).value:null), ImmutableList.<SortItem>of()),
			            Optional.fromNullable((limitClause314!=null?((StatementBuilder.limitClause_return)limitClause314).value:null)),
			            Optional.fromNullable((offsetClause315!=null?((StatementBuilder.offsetClause_return)offsetClause315).value:null)));
			        }
			if ( state.backtracking==0 ) {
			}

			}

			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "showPartitions"


	public static class showFunctions_return extends TreeRuleReturnScope {
		public Statement value;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "showFunctions"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:639:1: showFunctions returns [Statement value] : SHOW_FUNCTIONS ;
	public final StatementBuilder.showFunctions_return showFunctions() throws RecognitionException {
		StatementBuilder.showFunctions_return retval = new StatementBuilder.showFunctions_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree SHOW_FUNCTIONS316=null;

		CommonTree SHOW_FUNCTIONS316_tree=null;

		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:640:5: ( SHOW_FUNCTIONS )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:640:7: SHOW_FUNCTIONS
			{
			root_0 = (CommonTree)adaptor.nil();


			_last = (CommonTree)input.LT(1);
			SHOW_FUNCTIONS316=(CommonTree)match(input,SHOW_FUNCTIONS,FOLLOW_SHOW_FUNCTIONS_in_showFunctions6235); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			SHOW_FUNCTIONS316_tree = (CommonTree)adaptor.dupNode(SHOW_FUNCTIONS316);


			adaptor.addChild(root_0, SHOW_FUNCTIONS316_tree);
			}

			if ( state.backtracking==0 ) { retval.value = new ShowFunctions(); }
			if ( state.backtracking==0 ) {
			}

			}

			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "showFunctions"


	public static class showCreateTable_return extends TreeRuleReturnScope {
		public Statement value;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "showCreateTable"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:643:1: showCreateTable returns [Statement value] : ^( SHOW_CREATE_TABLE namedTable ) ;
	public final StatementBuilder.showCreateTable_return showCreateTable() throws RecognitionException {
		StatementBuilder.showCreateTable_return retval = new StatementBuilder.showCreateTable_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree SHOW_CREATE_TABLE317=null;
		TreeRuleReturnScope namedTable318 =null;

		CommonTree SHOW_CREATE_TABLE317_tree=null;

		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:644:5: ( ^( SHOW_CREATE_TABLE namedTable ) )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:644:7: ^( SHOW_CREATE_TABLE namedTable )
			{
			root_0 = (CommonTree)adaptor.nil();


			_last = (CommonTree)input.LT(1);
			{
			CommonTree _save_last_1 = _last;
			CommonTree _first_1 = null;
			CommonTree root_1 = (CommonTree)adaptor.nil();
			_last = (CommonTree)input.LT(1);
			SHOW_CREATE_TABLE317=(CommonTree)match(input,SHOW_CREATE_TABLE,FOLLOW_SHOW_CREATE_TABLE_in_showCreateTable6259); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			SHOW_CREATE_TABLE317_tree = (CommonTree)adaptor.dupNode(SHOW_CREATE_TABLE317);


			root_1 = (CommonTree)adaptor.becomeRoot(SHOW_CREATE_TABLE317_tree, root_1);
			}

			match(input, Token.DOWN, null); if (state.failed) return retval;
			_last = (CommonTree)input.LT(1);
			pushFollow(FOLLOW_namedTable_in_showCreateTable6261);
			namedTable318=namedTable();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) 
			adaptor.addChild(root_1, namedTable318.getTree());

			match(input, Token.UP, null); if (state.failed) return retval;
			adaptor.addChild(root_0, root_1);
			_last = _save_last_1;
			}


			if ( state.backtracking==0 ) {
			            retval.value = new ShowCreateTable((namedTable318!=null?((StatementBuilder.namedTable_return)namedTable318).value:null));
			        }
			if ( state.backtracking==0 ) {
			}

			}

			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "showCreateTable"


	public static class createMaterializedView_return extends TreeRuleReturnScope {
		public Statement value;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "createMaterializedView"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:650:1: createMaterializedView returns [Statement value] : ^( CREATE_MATERIALIZED_VIEW qname (refreshView= viewRefresh )? select= restrictedSelectStmt ) ;
	public final StatementBuilder.createMaterializedView_return createMaterializedView() throws RecognitionException {
		StatementBuilder.createMaterializedView_return retval = new StatementBuilder.createMaterializedView_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree CREATE_MATERIALIZED_VIEW319=null;
		TreeRuleReturnScope refreshView =null;
		TreeRuleReturnScope select =null;
		TreeRuleReturnScope qname320 =null;

		CommonTree CREATE_MATERIALIZED_VIEW319_tree=null;

		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:651:5: ( ^( CREATE_MATERIALIZED_VIEW qname (refreshView= viewRefresh )? select= restrictedSelectStmt ) )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:651:7: ^( CREATE_MATERIALIZED_VIEW qname (refreshView= viewRefresh )? select= restrictedSelectStmt )
			{
			root_0 = (CommonTree)adaptor.nil();


			_last = (CommonTree)input.LT(1);
			{
			CommonTree _save_last_1 = _last;
			CommonTree _first_1 = null;
			CommonTree root_1 = (CommonTree)adaptor.nil();
			_last = (CommonTree)input.LT(1);
			CREATE_MATERIALIZED_VIEW319=(CommonTree)match(input,CREATE_MATERIALIZED_VIEW,FOLLOW_CREATE_MATERIALIZED_VIEW_in_createMaterializedView6294); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			CREATE_MATERIALIZED_VIEW319_tree = (CommonTree)adaptor.dupNode(CREATE_MATERIALIZED_VIEW319);


			root_1 = (CommonTree)adaptor.becomeRoot(CREATE_MATERIALIZED_VIEW319_tree, root_1);
			}

			match(input, Token.DOWN, null); if (state.failed) return retval;
			_last = (CommonTree)input.LT(1);
			pushFollow(FOLLOW_qname_in_createMaterializedView6296);
			qname320=qname();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) 
			adaptor.addChild(root_1, qname320.getTree());

			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:651:51: (refreshView= viewRefresh )?
			int alt87=2;
			int LA87_0 = input.LA(1);
			if ( (LA87_0==REFRESH) ) {
				alt87=1;
			}
			switch (alt87) {
				case 1 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:651:51: refreshView= viewRefresh
					{
					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_viewRefresh_in_createMaterializedView6300);
					refreshView=viewRefresh();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, refreshView.getTree());

					if ( state.backtracking==0 ) {
					}

					}
					break;

			}

			_last = (CommonTree)input.LT(1);
			pushFollow(FOLLOW_restrictedSelectStmt_in_createMaterializedView6305);
			select=restrictedSelectStmt();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) 
			adaptor.addChild(root_1, select.getTree());

			match(input, Token.UP, null); if (state.failed) return retval;
			adaptor.addChild(root_0, root_1);
			_last = _save_last_1;
			}


			if ( state.backtracking==0 ) { retval.value = new CreateMaterializedView((qname320!=null?((StatementBuilder.qname_return)qname320).value:null), Optional.fromNullable((refreshView!=null?((StatementBuilder.viewRefresh_return)refreshView).value:null)), (select!=null?((StatementBuilder.restrictedSelectStmt_return)select).value:null)); }
			if ( state.backtracking==0 ) {
			}

			}

			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "createMaterializedView"


	public static class refreshMaterializedView_return extends TreeRuleReturnScope {
		public Statement value;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "refreshMaterializedView"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:655:1: refreshMaterializedView returns [Statement value] : ^( REFRESH_MATERIALIZED_VIEW qname ) ;
	public final StatementBuilder.refreshMaterializedView_return refreshMaterializedView() throws RecognitionException {
		StatementBuilder.refreshMaterializedView_return retval = new StatementBuilder.refreshMaterializedView_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree REFRESH_MATERIALIZED_VIEW321=null;
		TreeRuleReturnScope qname322 =null;

		CommonTree REFRESH_MATERIALIZED_VIEW321_tree=null;

		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:656:5: ( ^( REFRESH_MATERIALIZED_VIEW qname ) )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:656:7: ^( REFRESH_MATERIALIZED_VIEW qname )
			{
			root_0 = (CommonTree)adaptor.nil();


			_last = (CommonTree)input.LT(1);
			{
			CommonTree _save_last_1 = _last;
			CommonTree _first_1 = null;
			CommonTree root_1 = (CommonTree)adaptor.nil();
			_last = (CommonTree)input.LT(1);
			REFRESH_MATERIALIZED_VIEW321=(CommonTree)match(input,REFRESH_MATERIALIZED_VIEW,FOLLOW_REFRESH_MATERIALIZED_VIEW_in_refreshMaterializedView6338); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			REFRESH_MATERIALIZED_VIEW321_tree = (CommonTree)adaptor.dupNode(REFRESH_MATERIALIZED_VIEW321);


			root_1 = (CommonTree)adaptor.becomeRoot(REFRESH_MATERIALIZED_VIEW321_tree, root_1);
			}

			match(input, Token.DOWN, null); if (state.failed) return retval;
			_last = (CommonTree)input.LT(1);
			pushFollow(FOLLOW_qname_in_refreshMaterializedView6340);
			qname322=qname();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) 
			adaptor.addChild(root_1, qname322.getTree());

			match(input, Token.UP, null); if (state.failed) return retval;
			adaptor.addChild(root_0, root_1);
			_last = _save_last_1;
			}


			if ( state.backtracking==0 ) { retval.value = new RefreshMaterializedView((qname322!=null?((StatementBuilder.qname_return)qname322).value:null)); }
			if ( state.backtracking==0 ) {
			}

			}

			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "refreshMaterializedView"


	public static class viewRefresh_return extends TreeRuleReturnScope {
		public String value;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "viewRefresh"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:659:1: viewRefresh returns [String value] : ^( REFRESH integer ) ;
	public final StatementBuilder.viewRefresh_return viewRefresh() throws RecognitionException {
		StatementBuilder.viewRefresh_return retval = new StatementBuilder.viewRefresh_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree REFRESH323=null;
		TreeRuleReturnScope integer324 =null;

		CommonTree REFRESH323_tree=null;

		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:660:5: ( ^( REFRESH integer ) )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:660:7: ^( REFRESH integer )
			{
			root_0 = (CommonTree)adaptor.nil();


			_last = (CommonTree)input.LT(1);
			{
			CommonTree _save_last_1 = _last;
			CommonTree _first_1 = null;
			CommonTree root_1 = (CommonTree)adaptor.nil();
			_last = (CommonTree)input.LT(1);
			REFRESH323=(CommonTree)match(input,REFRESH,FOLLOW_REFRESH_in_viewRefresh6365); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			REFRESH323_tree = (CommonTree)adaptor.dupNode(REFRESH323);


			root_1 = (CommonTree)adaptor.becomeRoot(REFRESH323_tree, root_1);
			}

			match(input, Token.DOWN, null); if (state.failed) return retval;
			_last = (CommonTree)input.LT(1);
			pushFollow(FOLLOW_integer_in_viewRefresh6367);
			integer324=integer();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) 
			adaptor.addChild(root_1, integer324.getTree());

			match(input, Token.UP, null); if (state.failed) return retval;
			adaptor.addChild(root_0, root_1);
			_last = _save_last_1;
			}


			if ( state.backtracking==0 ) { retval.value = (integer324!=null?((StatementBuilder.integer_return)integer324).value:null); }
			if ( state.backtracking==0 ) {
			}

			}

			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "viewRefresh"


	public static class createAlias_return extends TreeRuleReturnScope {
		public Statement value;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "createAlias"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:663:1: createAlias returns [Statement value] : ^( CREATE_ALIAS qname remote= forRemote ) ;
	public final StatementBuilder.createAlias_return createAlias() throws RecognitionException {
		StatementBuilder.createAlias_return retval = new StatementBuilder.createAlias_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree CREATE_ALIAS325=null;
		TreeRuleReturnScope remote =null;
		TreeRuleReturnScope qname326 =null;

		CommonTree CREATE_ALIAS325_tree=null;

		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:664:5: ( ^( CREATE_ALIAS qname remote= forRemote ) )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:664:7: ^( CREATE_ALIAS qname remote= forRemote )
			{
			root_0 = (CommonTree)adaptor.nil();


			_last = (CommonTree)input.LT(1);
			{
			CommonTree _save_last_1 = _last;
			CommonTree _first_1 = null;
			CommonTree root_1 = (CommonTree)adaptor.nil();
			_last = (CommonTree)input.LT(1);
			CREATE_ALIAS325=(CommonTree)match(input,CREATE_ALIAS,FOLLOW_CREATE_ALIAS_in_createAlias6392); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			CREATE_ALIAS325_tree = (CommonTree)adaptor.dupNode(CREATE_ALIAS325);


			root_1 = (CommonTree)adaptor.becomeRoot(CREATE_ALIAS325_tree, root_1);
			}

			match(input, Token.DOWN, null); if (state.failed) return retval;
			_last = (CommonTree)input.LT(1);
			pushFollow(FOLLOW_qname_in_createAlias6394);
			qname326=qname();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) 
			adaptor.addChild(root_1, qname326.getTree());

			_last = (CommonTree)input.LT(1);
			pushFollow(FOLLOW_forRemote_in_createAlias6398);
			remote=forRemote();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) 
			adaptor.addChild(root_1, remote.getTree());

			match(input, Token.UP, null); if (state.failed) return retval;
			adaptor.addChild(root_0, root_1);
			_last = _save_last_1;
			}


			if ( state.backtracking==0 ) { retval.value = new CreateAlias((qname326!=null?((StatementBuilder.qname_return)qname326).value:null), (remote!=null?((StatementBuilder.forRemote_return)remote).value:null)); }
			if ( state.backtracking==0 ) {
			}

			}

			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "createAlias"


	public static class dropAlias_return extends TreeRuleReturnScope {
		public Statement value;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "dropAlias"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:667:1: dropAlias returns [Statement value] : ^( DROP_ALIAS qname ) ;
	public final StatementBuilder.dropAlias_return dropAlias() throws RecognitionException {
		StatementBuilder.dropAlias_return retval = new StatementBuilder.dropAlias_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree DROP_ALIAS327=null;
		TreeRuleReturnScope qname328 =null;

		CommonTree DROP_ALIAS327_tree=null;

		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:668:5: ( ^( DROP_ALIAS qname ) )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:668:7: ^( DROP_ALIAS qname )
			{
			root_0 = (CommonTree)adaptor.nil();


			_last = (CommonTree)input.LT(1);
			{
			CommonTree _save_last_1 = _last;
			CommonTree _first_1 = null;
			CommonTree root_1 = (CommonTree)adaptor.nil();
			_last = (CommonTree)input.LT(1);
			DROP_ALIAS327=(CommonTree)match(input,DROP_ALIAS,FOLLOW_DROP_ALIAS_in_dropAlias6423); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			DROP_ALIAS327_tree = (CommonTree)adaptor.dupNode(DROP_ALIAS327);


			root_1 = (CommonTree)adaptor.becomeRoot(DROP_ALIAS327_tree, root_1);
			}

			match(input, Token.DOWN, null); if (state.failed) return retval;
			_last = (CommonTree)input.LT(1);
			pushFollow(FOLLOW_qname_in_dropAlias6425);
			qname328=qname();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) 
			adaptor.addChild(root_1, qname328.getTree());

			match(input, Token.UP, null); if (state.failed) return retval;
			adaptor.addChild(root_0, root_1);
			_last = _save_last_1;
			}


			if ( state.backtracking==0 ) { retval.value = new DropAlias((qname328!=null?((StatementBuilder.qname_return)qname328).value:null)); }
			if ( state.backtracking==0 ) {
			}

			}

			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "dropAlias"


	public static class forRemote_return extends TreeRuleReturnScope {
		public QualifiedName value;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "forRemote"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:671:1: forRemote returns [QualifiedName value] : ^( FOR qname ) ;
	public final StatementBuilder.forRemote_return forRemote() throws RecognitionException {
		StatementBuilder.forRemote_return retval = new StatementBuilder.forRemote_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree FOR329=null;
		TreeRuleReturnScope qname330 =null;

		CommonTree FOR329_tree=null;

		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:672:5: ( ^( FOR qname ) )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:672:7: ^( FOR qname )
			{
			root_0 = (CommonTree)adaptor.nil();


			_last = (CommonTree)input.LT(1);
			{
			CommonTree _save_last_1 = _last;
			CommonTree _first_1 = null;
			CommonTree root_1 = (CommonTree)adaptor.nil();
			_last = (CommonTree)input.LT(1);
			FOR329=(CommonTree)match(input,FOR,FOLLOW_FOR_in_forRemote6450); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			FOR329_tree = (CommonTree)adaptor.dupNode(FOR329);


			root_1 = (CommonTree)adaptor.becomeRoot(FOR329_tree, root_1);
			}

			match(input, Token.DOWN, null); if (state.failed) return retval;
			_last = (CommonTree)input.LT(1);
			pushFollow(FOLLOW_qname_in_forRemote6452);
			qname330=qname();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) 
			adaptor.addChild(root_1, qname330.getTree());

			match(input, Token.UP, null); if (state.failed) return retval;
			adaptor.addChild(root_0, root_1);
			_last = _save_last_1;
			}


			if ( state.backtracking==0 ) { retval.value = (qname330!=null?((StatementBuilder.qname_return)qname330).value:null); }
			if ( state.backtracking==0 ) {
			}

			}

			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "forRemote"


	public static class dropBlobTable_return extends TreeRuleReturnScope {
		public Statement value;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "dropBlobTable"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:675:1: dropBlobTable returns [Statement value] : ( ^( DROP_BLOB_TABLE namedTable ) | ^( DROP_BLOB_TABLE EXISTS namedTable ) );
	public final StatementBuilder.dropBlobTable_return dropBlobTable() throws RecognitionException {
		StatementBuilder.dropBlobTable_return retval = new StatementBuilder.dropBlobTable_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree DROP_BLOB_TABLE331=null;
		CommonTree DROP_BLOB_TABLE333=null;
		CommonTree EXISTS334=null;
		TreeRuleReturnScope namedTable332 =null;
		TreeRuleReturnScope namedTable335 =null;

		CommonTree DROP_BLOB_TABLE331_tree=null;
		CommonTree DROP_BLOB_TABLE333_tree=null;
		CommonTree EXISTS334_tree=null;

		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:676:5: ( ^( DROP_BLOB_TABLE namedTable ) | ^( DROP_BLOB_TABLE EXISTS namedTable ) )
			int alt88=2;
			int LA88_0 = input.LA(1);
			if ( (LA88_0==DROP_BLOB_TABLE) ) {
				int LA88_1 = input.LA(2);
				if ( (LA88_1==DOWN) ) {
					int LA88_2 = input.LA(3);
					if ( (LA88_2==EXISTS) ) {
						alt88=2;
					}
					else if ( (LA88_2==TABLE) ) {
						alt88=1;
					}

					else {
						if (state.backtracking>0) {state.failed=true; return retval;}
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 88, 2, input);
							throw nvae;
						} finally {
							input.rewind(nvaeMark);
						}
					}

				}

				else {
					if (state.backtracking>0) {state.failed=true; return retval;}
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 88, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

			}

			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 88, 0, input);
				throw nvae;
			}

			switch (alt88) {
				case 1 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:676:7: ^( DROP_BLOB_TABLE namedTable )
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					{
					CommonTree _save_last_1 = _last;
					CommonTree _first_1 = null;
					CommonTree root_1 = (CommonTree)adaptor.nil();
					_last = (CommonTree)input.LT(1);
					DROP_BLOB_TABLE331=(CommonTree)match(input,DROP_BLOB_TABLE,FOLLOW_DROP_BLOB_TABLE_in_dropBlobTable6477); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					DROP_BLOB_TABLE331_tree = (CommonTree)adaptor.dupNode(DROP_BLOB_TABLE331);


					root_1 = (CommonTree)adaptor.becomeRoot(DROP_BLOB_TABLE331_tree, root_1);
					}

					match(input, Token.DOWN, null); if (state.failed) return retval;
					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_namedTable_in_dropBlobTable6479);
					namedTable332=namedTable();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, namedTable332.getTree());

					match(input, Token.UP, null); if (state.failed) return retval;
					adaptor.addChild(root_0, root_1);
					_last = _save_last_1;
					}


					if ( state.backtracking==0 ) { retval.value = new DropBlobTable((namedTable332!=null?((StatementBuilder.namedTable_return)namedTable332).value:null)); }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 2 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:677:7: ^( DROP_BLOB_TABLE EXISTS namedTable )
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					{
					CommonTree _save_last_1 = _last;
					CommonTree _first_1 = null;
					CommonTree root_1 = (CommonTree)adaptor.nil();
					_last = (CommonTree)input.LT(1);
					DROP_BLOB_TABLE333=(CommonTree)match(input,DROP_BLOB_TABLE,FOLLOW_DROP_BLOB_TABLE_in_dropBlobTable6491); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					DROP_BLOB_TABLE333_tree = (CommonTree)adaptor.dupNode(DROP_BLOB_TABLE333);


					root_1 = (CommonTree)adaptor.becomeRoot(DROP_BLOB_TABLE333_tree, root_1);
					}

					match(input, Token.DOWN, null); if (state.failed) return retval;
					_last = (CommonTree)input.LT(1);
					EXISTS334=(CommonTree)match(input,EXISTS,FOLLOW_EXISTS_in_dropBlobTable6493); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					EXISTS334_tree = (CommonTree)adaptor.dupNode(EXISTS334);


					adaptor.addChild(root_1, EXISTS334_tree);
					}

					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_namedTable_in_dropBlobTable6495);
					namedTable335=namedTable();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, namedTable335.getTree());

					match(input, Token.UP, null); if (state.failed) return retval;
					adaptor.addChild(root_0, root_1);
					_last = _save_last_1;
					}


					if ( state.backtracking==0 ) { retval.value = new DropBlobTable((namedTable335!=null?((StatementBuilder.namedTable_return)namedTable335).value:null), true ); }
					if ( state.backtracking==0 ) {
					}

					}
					break;

			}
			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "dropBlobTable"


	public static class dropTable_return extends TreeRuleReturnScope {
		public Statement value;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "dropTable"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:680:1: dropTable returns [Statement value] : ( ^( DROP_TABLE namedTable ) | ^( DROP_TABLE EXISTS namedTable ) );
	public final StatementBuilder.dropTable_return dropTable() throws RecognitionException {
		StatementBuilder.dropTable_return retval = new StatementBuilder.dropTable_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree DROP_TABLE336=null;
		CommonTree DROP_TABLE338=null;
		CommonTree EXISTS339=null;
		TreeRuleReturnScope namedTable337 =null;
		TreeRuleReturnScope namedTable340 =null;

		CommonTree DROP_TABLE336_tree=null;
		CommonTree DROP_TABLE338_tree=null;
		CommonTree EXISTS339_tree=null;

		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:681:5: ( ^( DROP_TABLE namedTable ) | ^( DROP_TABLE EXISTS namedTable ) )
			int alt89=2;
			int LA89_0 = input.LA(1);
			if ( (LA89_0==DROP_TABLE) ) {
				int LA89_1 = input.LA(2);
				if ( (LA89_1==DOWN) ) {
					int LA89_2 = input.LA(3);
					if ( (LA89_2==EXISTS) ) {
						alt89=2;
					}
					else if ( (LA89_2==TABLE) ) {
						alt89=1;
					}

					else {
						if (state.backtracking>0) {state.failed=true; return retval;}
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 89, 2, input);
							throw nvae;
						} finally {
							input.rewind(nvaeMark);
						}
					}

				}

				else {
					if (state.backtracking>0) {state.failed=true; return retval;}
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 89, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

			}

			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 89, 0, input);
				throw nvae;
			}

			switch (alt89) {
				case 1 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:681:7: ^( DROP_TABLE namedTable )
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					{
					CommonTree _save_last_1 = _last;
					CommonTree _first_1 = null;
					CommonTree root_1 = (CommonTree)adaptor.nil();
					_last = (CommonTree)input.LT(1);
					DROP_TABLE336=(CommonTree)match(input,DROP_TABLE,FOLLOW_DROP_TABLE_in_dropTable6520); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					DROP_TABLE336_tree = (CommonTree)adaptor.dupNode(DROP_TABLE336);


					root_1 = (CommonTree)adaptor.becomeRoot(DROP_TABLE336_tree, root_1);
					}

					match(input, Token.DOWN, null); if (state.failed) return retval;
					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_namedTable_in_dropTable6522);
					namedTable337=namedTable();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, namedTable337.getTree());

					match(input, Token.UP, null); if (state.failed) return retval;
					adaptor.addChild(root_0, root_1);
					_last = _save_last_1;
					}


					if ( state.backtracking==0 ) { retval.value = new DropTable((namedTable337!=null?((StatementBuilder.namedTable_return)namedTable337).value:null)); }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 2 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:682:7: ^( DROP_TABLE EXISTS namedTable )
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					{
					CommonTree _save_last_1 = _last;
					CommonTree _first_1 = null;
					CommonTree root_1 = (CommonTree)adaptor.nil();
					_last = (CommonTree)input.LT(1);
					DROP_TABLE338=(CommonTree)match(input,DROP_TABLE,FOLLOW_DROP_TABLE_in_dropTable6534); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					DROP_TABLE338_tree = (CommonTree)adaptor.dupNode(DROP_TABLE338);


					root_1 = (CommonTree)adaptor.becomeRoot(DROP_TABLE338_tree, root_1);
					}

					match(input, Token.DOWN, null); if (state.failed) return retval;
					_last = (CommonTree)input.LT(1);
					EXISTS339=(CommonTree)match(input,EXISTS,FOLLOW_EXISTS_in_dropTable6536); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					EXISTS339_tree = (CommonTree)adaptor.dupNode(EXISTS339);


					adaptor.addChild(root_1, EXISTS339_tree);
					}

					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_namedTable_in_dropTable6538);
					namedTable340=namedTable();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, namedTable340.getTree());

					match(input, Token.UP, null); if (state.failed) return retval;
					adaptor.addChild(root_0, root_1);
					_last = _save_last_1;
					}


					if ( state.backtracking==0 ) { retval.value = new DropTable((namedTable340!=null?((StatementBuilder.namedTable_return)namedTable340).value:null), true ); }
					if ( state.backtracking==0 ) {
					}

					}
					break;

			}
			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "dropTable"


	public static class createUser_return extends TreeRuleReturnScope {
		public Statement value;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "createUser"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:685:1: createUser returns [Statement value] : ^( CREATE_USER username IDENTIFIED_BY password ) ;
	public final StatementBuilder.createUser_return createUser() throws RecognitionException {
		StatementBuilder.createUser_return retval = new StatementBuilder.createUser_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree CREATE_USER341=null;
		CommonTree IDENTIFIED_BY343=null;
		TreeRuleReturnScope username342 =null;
		TreeRuleReturnScope password344 =null;

		CommonTree CREATE_USER341_tree=null;
		CommonTree IDENTIFIED_BY343_tree=null;

		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:686:5: ( ^( CREATE_USER username IDENTIFIED_BY password ) )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:686:7: ^( CREATE_USER username IDENTIFIED_BY password )
			{
			root_0 = (CommonTree)adaptor.nil();


			_last = (CommonTree)input.LT(1);
			{
			CommonTree _save_last_1 = _last;
			CommonTree _first_1 = null;
			CommonTree root_1 = (CommonTree)adaptor.nil();
			_last = (CommonTree)input.LT(1);
			CREATE_USER341=(CommonTree)match(input,CREATE_USER,FOLLOW_CREATE_USER_in_createUser6567); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			CREATE_USER341_tree = (CommonTree)adaptor.dupNode(CREATE_USER341);


			root_1 = (CommonTree)adaptor.becomeRoot(CREATE_USER341_tree, root_1);
			}

			match(input, Token.DOWN, null); if (state.failed) return retval;
			_last = (CommonTree)input.LT(1);
			pushFollow(FOLLOW_username_in_createUser6569);
			username342=username();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) 
			adaptor.addChild(root_1, username342.getTree());

			_last = (CommonTree)input.LT(1);
			IDENTIFIED_BY343=(CommonTree)match(input,IDENTIFIED_BY,FOLLOW_IDENTIFIED_BY_in_createUser6571); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			IDENTIFIED_BY343_tree = (CommonTree)adaptor.dupNode(IDENTIFIED_BY343);


			adaptor.addChild(root_1, IDENTIFIED_BY343_tree);
			}

			_last = (CommonTree)input.LT(1);
			pushFollow(FOLLOW_password_in_createUser6573);
			password344=password();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) 
			adaptor.addChild(root_1, password344.getTree());

			match(input, Token.UP, null); if (state.failed) return retval;
			adaptor.addChild(root_0, root_1);
			_last = _save_last_1;
			}


			if ( state.backtracking==0 ) { retval.value = new CreateUser((username342!=null?((StatementBuilder.username_return)username342).value:null), (password344!=null?((StatementBuilder.password_return)password344).value:null)); }
			if ( state.backtracking==0 ) {
			}

			}

			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "createUser"


	public static class alterUser_return extends TreeRuleReturnScope {
		public Statement value;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "alterUser"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:689:1: alterUser returns [Statement value] : ( ^( RESET_PASSWORD username password ) | ^( RESET_WHITELIST username hostWhitelist ) );
	public final StatementBuilder.alterUser_return alterUser() throws RecognitionException {
		StatementBuilder.alterUser_return retval = new StatementBuilder.alterUser_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree RESET_PASSWORD345=null;
		CommonTree RESET_WHITELIST348=null;
		TreeRuleReturnScope username346 =null;
		TreeRuleReturnScope password347 =null;
		TreeRuleReturnScope username349 =null;
		TreeRuleReturnScope hostWhitelist350 =null;

		CommonTree RESET_PASSWORD345_tree=null;
		CommonTree RESET_WHITELIST348_tree=null;

		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:690:5: ( ^( RESET_PASSWORD username password ) | ^( RESET_WHITELIST username hostWhitelist ) )
			int alt90=2;
			int LA90_0 = input.LA(1);
			if ( (LA90_0==RESET_PASSWORD) ) {
				alt90=1;
			}
			else if ( (LA90_0==RESET_WHITELIST) ) {
				alt90=2;
			}

			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 90, 0, input);
				throw nvae;
			}

			switch (alt90) {
				case 1 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:690:7: ^( RESET_PASSWORD username password )
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					{
					CommonTree _save_last_1 = _last;
					CommonTree _first_1 = null;
					CommonTree root_1 = (CommonTree)adaptor.nil();
					_last = (CommonTree)input.LT(1);
					RESET_PASSWORD345=(CommonTree)match(input,RESET_PASSWORD,FOLLOW_RESET_PASSWORD_in_alterUser6598); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					RESET_PASSWORD345_tree = (CommonTree)adaptor.dupNode(RESET_PASSWORD345);


					root_1 = (CommonTree)adaptor.becomeRoot(RESET_PASSWORD345_tree, root_1);
					}

					match(input, Token.DOWN, null); if (state.failed) return retval;
					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_username_in_alterUser6600);
					username346=username();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, username346.getTree());

					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_password_in_alterUser6602);
					password347=password();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, password347.getTree());

					match(input, Token.UP, null); if (state.failed) return retval;
					adaptor.addChild(root_0, root_1);
					_last = _save_last_1;
					}


					if ( state.backtracking==0 ) { retval.value = new ResetPassword((username346!=null?((StatementBuilder.username_return)username346).value:null), (password347!=null?((StatementBuilder.password_return)password347).value:null)); }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 2 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:691:7: ^( RESET_WHITELIST username hostWhitelist )
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					{
					CommonTree _save_last_1 = _last;
					CommonTree _first_1 = null;
					CommonTree root_1 = (CommonTree)adaptor.nil();
					_last = (CommonTree)input.LT(1);
					RESET_WHITELIST348=(CommonTree)match(input,RESET_WHITELIST,FOLLOW_RESET_WHITELIST_in_alterUser6614); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					RESET_WHITELIST348_tree = (CommonTree)adaptor.dupNode(RESET_WHITELIST348);


					root_1 = (CommonTree)adaptor.becomeRoot(RESET_WHITELIST348_tree, root_1);
					}

					match(input, Token.DOWN, null); if (state.failed) return retval;
					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_username_in_alterUser6616);
					username349=username();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, username349.getTree());

					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_hostWhitelist_in_alterUser6618);
					hostWhitelist350=hostWhitelist();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, hostWhitelist350.getTree());

					match(input, Token.UP, null); if (state.failed) return retval;
					adaptor.addChild(root_0, root_1);
					_last = _save_last_1;
					}


					if ( state.backtracking==0 ) { retval.value = new ResetWhitelist((username349!=null?((StatementBuilder.username_return)username349).value:null), (hostWhitelist350!=null?((StatementBuilder.hostWhitelist_return)hostWhitelist350).value:null)); }
					if ( state.backtracking==0 ) {
					}

					}
					break;

			}
			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "alterUser"


	public static class grantStmt_return extends TreeRuleReturnScope {
		public Statement value;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "grantStmt"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:694:1: grantStmt returns [Statement value] : ^( GRANT_PRIVILEGE privilege namedTable username ) ;
	public final StatementBuilder.grantStmt_return grantStmt() throws RecognitionException {
		StatementBuilder.grantStmt_return retval = new StatementBuilder.grantStmt_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree GRANT_PRIVILEGE351=null;
		TreeRuleReturnScope privilege352 =null;
		TreeRuleReturnScope namedTable353 =null;
		TreeRuleReturnScope username354 =null;

		CommonTree GRANT_PRIVILEGE351_tree=null;

		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:695:5: ( ^( GRANT_PRIVILEGE privilege namedTable username ) )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:695:7: ^( GRANT_PRIVILEGE privilege namedTable username )
			{
			root_0 = (CommonTree)adaptor.nil();


			_last = (CommonTree)input.LT(1);
			{
			CommonTree _save_last_1 = _last;
			CommonTree _first_1 = null;
			CommonTree root_1 = (CommonTree)adaptor.nil();
			_last = (CommonTree)input.LT(1);
			GRANT_PRIVILEGE351=(CommonTree)match(input,GRANT_PRIVILEGE,FOLLOW_GRANT_PRIVILEGE_in_grantStmt6647); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			GRANT_PRIVILEGE351_tree = (CommonTree)adaptor.dupNode(GRANT_PRIVILEGE351);


			root_1 = (CommonTree)adaptor.becomeRoot(GRANT_PRIVILEGE351_tree, root_1);
			}

			match(input, Token.DOWN, null); if (state.failed) return retval;
			_last = (CommonTree)input.LT(1);
			pushFollow(FOLLOW_privilege_in_grantStmt6649);
			privilege352=privilege();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) 
			adaptor.addChild(root_1, privilege352.getTree());

			_last = (CommonTree)input.LT(1);
			pushFollow(FOLLOW_namedTable_in_grantStmt6651);
			namedTable353=namedTable();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) 
			adaptor.addChild(root_1, namedTable353.getTree());

			_last = (CommonTree)input.LT(1);
			pushFollow(FOLLOW_username_in_grantStmt6653);
			username354=username();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) 
			adaptor.addChild(root_1, username354.getTree());

			match(input, Token.UP, null); if (state.failed) return retval;
			adaptor.addChild(root_0, root_1);
			_last = _save_last_1;
			}


			if ( state.backtracking==0 ) { retval.value = new GrantPrivilege((username354!=null?((StatementBuilder.username_return)username354).value:null), (privilege352!=null?((StatementBuilder.privilege_return)privilege352).value:null), (namedTable353!=null?((StatementBuilder.namedTable_return)namedTable353).value:null)); }
			if ( state.backtracking==0 ) {
			}

			}

			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "grantStmt"


	public static class revokeStmt_return extends TreeRuleReturnScope {
		public Statement value;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "revokeStmt"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:698:1: revokeStmt returns [Statement value] : ^( REVOKE_PRIVILEGE privilege namedTable username ) ;
	public final StatementBuilder.revokeStmt_return revokeStmt() throws RecognitionException {
		StatementBuilder.revokeStmt_return retval = new StatementBuilder.revokeStmt_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree REVOKE_PRIVILEGE355=null;
		TreeRuleReturnScope privilege356 =null;
		TreeRuleReturnScope namedTable357 =null;
		TreeRuleReturnScope username358 =null;

		CommonTree REVOKE_PRIVILEGE355_tree=null;

		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:699:5: ( ^( REVOKE_PRIVILEGE privilege namedTable username ) )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:699:7: ^( REVOKE_PRIVILEGE privilege namedTable username )
			{
			root_0 = (CommonTree)adaptor.nil();


			_last = (CommonTree)input.LT(1);
			{
			CommonTree _save_last_1 = _last;
			CommonTree _first_1 = null;
			CommonTree root_1 = (CommonTree)adaptor.nil();
			_last = (CommonTree)input.LT(1);
			REVOKE_PRIVILEGE355=(CommonTree)match(input,REVOKE_PRIVILEGE,FOLLOW_REVOKE_PRIVILEGE_in_revokeStmt6682); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			REVOKE_PRIVILEGE355_tree = (CommonTree)adaptor.dupNode(REVOKE_PRIVILEGE355);


			root_1 = (CommonTree)adaptor.becomeRoot(REVOKE_PRIVILEGE355_tree, root_1);
			}

			match(input, Token.DOWN, null); if (state.failed) return retval;
			_last = (CommonTree)input.LT(1);
			pushFollow(FOLLOW_privilege_in_revokeStmt6684);
			privilege356=privilege();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) 
			adaptor.addChild(root_1, privilege356.getTree());

			_last = (CommonTree)input.LT(1);
			pushFollow(FOLLOW_namedTable_in_revokeStmt6686);
			namedTable357=namedTable();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) 
			adaptor.addChild(root_1, namedTable357.getTree());

			_last = (CommonTree)input.LT(1);
			pushFollow(FOLLOW_username_in_revokeStmt6688);
			username358=username();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) 
			adaptor.addChild(root_1, username358.getTree());

			match(input, Token.UP, null); if (state.failed) return retval;
			adaptor.addChild(root_0, root_1);
			_last = _save_last_1;
			}


			if ( state.backtracking==0 ) { retval.value = new RevokePrivilege((username358!=null?((StatementBuilder.username_return)username358).value:null), (privilege356!=null?((StatementBuilder.privilege_return)privilege356).value:null), (namedTable357!=null?((StatementBuilder.namedTable_return)namedTable357).value:null)); }
			if ( state.backtracking==0 ) {
			}

			}

			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "revokeStmt"


	public static class showGrantsStmt_return extends TreeRuleReturnScope {
		public Statement value;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "showGrantsStmt"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:702:1: showGrantsStmt returns [Statement value] : ^( SHOW_GRANTS ( forUsername )? ) ;
	public final StatementBuilder.showGrantsStmt_return showGrantsStmt() throws RecognitionException {
		StatementBuilder.showGrantsStmt_return retval = new StatementBuilder.showGrantsStmt_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree SHOW_GRANTS359=null;
		TreeRuleReturnScope forUsername360 =null;

		CommonTree SHOW_GRANTS359_tree=null;

		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:703:5: ( ^( SHOW_GRANTS ( forUsername )? ) )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:703:7: ^( SHOW_GRANTS ( forUsername )? )
			{
			root_0 = (CommonTree)adaptor.nil();


			_last = (CommonTree)input.LT(1);
			{
			CommonTree _save_last_1 = _last;
			CommonTree _first_1 = null;
			CommonTree root_1 = (CommonTree)adaptor.nil();
			_last = (CommonTree)input.LT(1);
			SHOW_GRANTS359=(CommonTree)match(input,SHOW_GRANTS,FOLLOW_SHOW_GRANTS_in_showGrantsStmt6717); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			SHOW_GRANTS359_tree = (CommonTree)adaptor.dupNode(SHOW_GRANTS359);


			root_1 = (CommonTree)adaptor.becomeRoot(SHOW_GRANTS359_tree, root_1);
			}

			if ( input.LA(1)==Token.DOWN ) {
				match(input, Token.DOWN, null); if (state.failed) return retval;
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:703:21: ( forUsername )?
				int alt91=2;
				int LA91_0 = input.LA(1);
				if ( (LA91_0==FOR) ) {
					alt91=1;
				}
				switch (alt91) {
					case 1 :
						// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:703:21: forUsername
						{
						_last = (CommonTree)input.LT(1);
						pushFollow(FOLLOW_forUsername_in_showGrantsStmt6719);
						forUsername360=forUsername();
						state._fsp--;
						if (state.failed) return retval;
						if ( state.backtracking==0 ) 
						adaptor.addChild(root_1, forUsername360.getTree());

						if ( state.backtracking==0 ) {
						}

						}
						break;

				}

				match(input, Token.UP, null); if (state.failed) return retval;
			}
			adaptor.addChild(root_0, root_1);
			_last = _save_last_1;
			}


			if ( state.backtracking==0 ) { retval.value = new ShowGrants((forUsername360!=null?((StatementBuilder.forUsername_return)forUsername360).value:null)); }
			if ( state.backtracking==0 ) {
			}

			}

			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "showGrantsStmt"


	public static class showUsersStmt_return extends TreeRuleReturnScope {
		public Statement value;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "showUsersStmt"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:706:1: showUsersStmt returns [Statement value] : SHOW_USERS ;
	public final StatementBuilder.showUsersStmt_return showUsersStmt() throws RecognitionException {
		StatementBuilder.showUsersStmt_return retval = new StatementBuilder.showUsersStmt_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree SHOW_USERS361=null;

		CommonTree SHOW_USERS361_tree=null;

		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:707:5: ( SHOW_USERS )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:707:7: SHOW_USERS
			{
			root_0 = (CommonTree)adaptor.nil();


			_last = (CommonTree)input.LT(1);
			SHOW_USERS361=(CommonTree)match(input,SHOW_USERS,FOLLOW_SHOW_USERS_in_showUsersStmt6744); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			SHOW_USERS361_tree = (CommonTree)adaptor.dupNode(SHOW_USERS361);


			adaptor.addChild(root_0, SHOW_USERS361_tree);
			}

			if ( state.backtracking==0 ) { retval.value = new ShowGrants(null); }
			if ( state.backtracking==0 ) {
			}

			}

			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "showUsersStmt"


	public static class privilege_return extends TreeRuleReturnScope {
		public String value;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "privilege"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:710:1: privilege returns [String value] : (r= READ_ONLY |w= READ_WRITE );
	public final StatementBuilder.privilege_return privilege() throws RecognitionException {
		StatementBuilder.privilege_return retval = new StatementBuilder.privilege_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree r=null;
		CommonTree w=null;

		CommonTree r_tree=null;
		CommonTree w_tree=null;

		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:711:5: (r= READ_ONLY |w= READ_WRITE )
			int alt92=2;
			int LA92_0 = input.LA(1);
			if ( (LA92_0==READ_ONLY) ) {
				alt92=1;
			}
			else if ( (LA92_0==READ_WRITE) ) {
				alt92=2;
			}

			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 92, 0, input);
				throw nvae;
			}

			switch (alt92) {
				case 1 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:711:7: r= READ_ONLY
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					r=(CommonTree)match(input,READ_ONLY,FOLLOW_READ_ONLY_in_privilege6769); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					r_tree = (CommonTree)adaptor.dupNode(r);


					adaptor.addChild(root_0, r_tree);
					}

					if ( state.backtracking==0 ) { retval.value = (r!=null?r.getText():null).toLowerCase(Locale.ENGLISH); }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 2 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:712:7: w= READ_WRITE
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					w=(CommonTree)match(input,READ_WRITE,FOLLOW_READ_WRITE_in_privilege6781); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					w_tree = (CommonTree)adaptor.dupNode(w);


					adaptor.addChild(root_0, w_tree);
					}

					if ( state.backtracking==0 ) { retval.value = (w!=null?w.getText():null).toLowerCase(Locale.ENGLISH); }
					if ( state.backtracking==0 ) {
					}

					}
					break;

			}
			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "privilege"


	public static class forUsername_return extends TreeRuleReturnScope {
		public String value;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "forUsername"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:715:1: forUsername returns [String value] : ^( FOR username ) ;
	public final StatementBuilder.forUsername_return forUsername() throws RecognitionException {
		StatementBuilder.forUsername_return retval = new StatementBuilder.forUsername_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree FOR362=null;
		TreeRuleReturnScope username363 =null;

		CommonTree FOR362_tree=null;

		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:716:5: ( ^( FOR username ) )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:716:7: ^( FOR username )
			{
			root_0 = (CommonTree)adaptor.nil();


			_last = (CommonTree)input.LT(1);
			{
			CommonTree _save_last_1 = _last;
			CommonTree _first_1 = null;
			CommonTree root_1 = (CommonTree)adaptor.nil();
			_last = (CommonTree)input.LT(1);
			FOR362=(CommonTree)match(input,FOR,FOLLOW_FOR_in_forUsername6806); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			FOR362_tree = (CommonTree)adaptor.dupNode(FOR362);


			root_1 = (CommonTree)adaptor.becomeRoot(FOR362_tree, root_1);
			}

			match(input, Token.DOWN, null); if (state.failed) return retval;
			_last = (CommonTree)input.LT(1);
			pushFollow(FOLLOW_username_in_forUsername6808);
			username363=username();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) 
			adaptor.addChild(root_1, username363.getTree());

			match(input, Token.UP, null); if (state.failed) return retval;
			adaptor.addChild(root_0, root_1);
			_last = _save_last_1;
			}


			if ( state.backtracking==0 ) { retval.value = (username363!=null?((StatementBuilder.username_return)username363).value:null); }
			if ( state.backtracking==0 ) {
			}

			}

			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "forUsername"


	public static class username_return extends TreeRuleReturnScope {
		public String value;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "username"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:719:1: username returns [String value] : ident ;
	public final StatementBuilder.username_return username() throws RecognitionException {
		StatementBuilder.username_return retval = new StatementBuilder.username_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		TreeRuleReturnScope ident364 =null;


		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:720:5: ( ident )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:720:7: ident
			{
			root_0 = (CommonTree)adaptor.nil();


			_last = (CommonTree)input.LT(1);
			pushFollow(FOLLOW_ident_in_username6832);
			ident364=ident();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) 
			adaptor.addChild(root_0, ident364.getTree());

			if ( state.backtracking==0 ) { retval.value = (ident364!=null?((StatementBuilder.ident_return)ident364).value:null); }
			if ( state.backtracking==0 ) {
			}

			}

			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "username"


	public static class password_return extends TreeRuleReturnScope {
		public String value;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "password"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:723:1: password returns [String value] : ident ;
	public final StatementBuilder.password_return password() throws RecognitionException {
		StatementBuilder.password_return retval = new StatementBuilder.password_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		TreeRuleReturnScope ident365 =null;


		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:724:5: ( ident )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:724:7: ident
			{
			root_0 = (CommonTree)adaptor.nil();


			_last = (CommonTree)input.LT(1);
			pushFollow(FOLLOW_ident_in_password6855);
			ident365=ident();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) 
			adaptor.addChild(root_0, ident365.getTree());

			if ( state.backtracking==0 ) { retval.value = (ident365!=null?((StatementBuilder.ident_return)ident365).value:null); }
			if ( state.backtracking==0 ) {
			}

			}

			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "password"


	public static class hostWhitelist_return extends TreeRuleReturnScope {
		public String value;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "hostWhitelist"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:727:1: hostWhitelist returns [String value] : q= QUOTED_IDENT ;
	public final StatementBuilder.hostWhitelist_return hostWhitelist() throws RecognitionException {
		StatementBuilder.hostWhitelist_return retval = new StatementBuilder.hostWhitelist_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree q=null;

		CommonTree q_tree=null;

		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:728:5: (q= QUOTED_IDENT )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:728:7: q= QUOTED_IDENT
			{
			root_0 = (CommonTree)adaptor.nil();


			_last = (CommonTree)input.LT(1);
			q=(CommonTree)match(input,QUOTED_IDENT,FOLLOW_QUOTED_IDENT_in_hostWhitelist6884); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			q_tree = (CommonTree)adaptor.dupNode(q);


			adaptor.addChild(root_0, q_tree);
			}

			if ( state.backtracking==0 ) { retval.value = (q!=null?q.getText():null); }
			if ( state.backtracking==0 ) {
			}

			}

			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "hostWhitelist"


	public static class createClusterStmt_return extends TreeRuleReturnScope {
		public Statement value;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "createClusterStmt"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:731:1: createClusterStmt returns [Statement value] : ^( CREATE_CLUSTER clustername ( genericProperties )? ) ;
	public final StatementBuilder.createClusterStmt_return createClusterStmt() throws RecognitionException {
		StatementBuilder.createClusterStmt_return retval = new StatementBuilder.createClusterStmt_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree CREATE_CLUSTER366=null;
		TreeRuleReturnScope clustername367 =null;
		TreeRuleReturnScope genericProperties368 =null;

		CommonTree CREATE_CLUSTER366_tree=null;

		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:732:5: ( ^( CREATE_CLUSTER clustername ( genericProperties )? ) )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:732:8: ^( CREATE_CLUSTER clustername ( genericProperties )? )
			{
			root_0 = (CommonTree)adaptor.nil();


			_last = (CommonTree)input.LT(1);
			{
			CommonTree _save_last_1 = _last;
			CommonTree _first_1 = null;
			CommonTree root_1 = (CommonTree)adaptor.nil();
			_last = (CommonTree)input.LT(1);
			CREATE_CLUSTER366=(CommonTree)match(input,CREATE_CLUSTER,FOLLOW_CREATE_CLUSTER_in_createClusterStmt6913); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			CREATE_CLUSTER366_tree = (CommonTree)adaptor.dupNode(CREATE_CLUSTER366);


			root_1 = (CommonTree)adaptor.becomeRoot(CREATE_CLUSTER366_tree, root_1);
			}

			match(input, Token.DOWN, null); if (state.failed) return retval;
			_last = (CommonTree)input.LT(1);
			pushFollow(FOLLOW_clustername_in_createClusterStmt6915);
			clustername367=clustername();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) 
			adaptor.addChild(root_1, clustername367.getTree());

			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:732:37: ( genericProperties )?
			int alt93=2;
			int LA93_0 = input.LA(1);
			if ( (LA93_0==GENERIC_PROPERTIES) ) {
				alt93=1;
			}
			switch (alt93) {
				case 1 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:732:37: genericProperties
					{
					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_genericProperties_in_createClusterStmt6917);
					genericProperties368=genericProperties();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, genericProperties368.getTree());

					if ( state.backtracking==0 ) {
					}

					}
					break;

			}

			match(input, Token.UP, null); if (state.failed) return retval;
			adaptor.addChild(root_0, root_1);
			_last = _save_last_1;
			}


			if ( state.backtracking==0 ) { retval.value = new CreateTenant((clustername367!=null?((StatementBuilder.clustername_return)clustername367).value:null), (genericProperties368!=null?((StatementBuilder.genericProperties_return)genericProperties368).value:null));}
			if ( state.backtracking==0 ) {
			}

			}

			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "createClusterStmt"


	public static class dropClusterStmt_return extends TreeRuleReturnScope {
		public Statement value;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "dropClusterStmt"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:735:1: dropClusterStmt returns [Statement value] : ^( DROP_CLUSTER clustername ) ;
	public final StatementBuilder.dropClusterStmt_return dropClusterStmt() throws RecognitionException {
		StatementBuilder.dropClusterStmt_return retval = new StatementBuilder.dropClusterStmt_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree DROP_CLUSTER369=null;
		TreeRuleReturnScope clustername370 =null;

		CommonTree DROP_CLUSTER369_tree=null;

		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:736:5: ( ^( DROP_CLUSTER clustername ) )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:736:7: ^( DROP_CLUSTER clustername )
			{
			root_0 = (CommonTree)adaptor.nil();


			_last = (CommonTree)input.LT(1);
			{
			CommonTree _save_last_1 = _last;
			CommonTree _first_1 = null;
			CommonTree root_1 = (CommonTree)adaptor.nil();
			_last = (CommonTree)input.LT(1);
			DROP_CLUSTER369=(CommonTree)match(input,DROP_CLUSTER,FOLLOW_DROP_CLUSTER_in_dropClusterStmt6944); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			DROP_CLUSTER369_tree = (CommonTree)adaptor.dupNode(DROP_CLUSTER369);


			root_1 = (CommonTree)adaptor.becomeRoot(DROP_CLUSTER369_tree, root_1);
			}

			match(input, Token.DOWN, null); if (state.failed) return retval;
			_last = (CommonTree)input.LT(1);
			pushFollow(FOLLOW_clustername_in_dropClusterStmt6946);
			clustername370=clustername();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) 
			adaptor.addChild(root_1, clustername370.getTree());

			match(input, Token.UP, null); if (state.failed) return retval;
			adaptor.addChild(root_0, root_1);
			_last = _save_last_1;
			}


			if ( state.backtracking==0 ) { retval.value = new DropTenant((clustername370!=null?((StatementBuilder.clustername_return)clustername370).value:null)); }
			if ( state.backtracking==0 ) {
			}

			}

			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "dropClusterStmt"


	public static class showClustersStmt_return extends TreeRuleReturnScope {
		public Statement value;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "showClustersStmt"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:739:1: showClustersStmt returns [Statement value] : ^( SHOW_CLUSTERS ( clustername )? ) ;
	public final StatementBuilder.showClustersStmt_return showClustersStmt() throws RecognitionException {
		StatementBuilder.showClustersStmt_return retval = new StatementBuilder.showClustersStmt_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree SHOW_CLUSTERS371=null;
		TreeRuleReturnScope clustername372 =null;

		CommonTree SHOW_CLUSTERS371_tree=null;

		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:740:5: ( ^( SHOW_CLUSTERS ( clustername )? ) )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:740:7: ^( SHOW_CLUSTERS ( clustername )? )
			{
			root_0 = (CommonTree)adaptor.nil();


			_last = (CommonTree)input.LT(1);
			{
			CommonTree _save_last_1 = _last;
			CommonTree _first_1 = null;
			CommonTree root_1 = (CommonTree)adaptor.nil();
			_last = (CommonTree)input.LT(1);
			SHOW_CLUSTERS371=(CommonTree)match(input,SHOW_CLUSTERS,FOLLOW_SHOW_CLUSTERS_in_showClustersStmt6975); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			SHOW_CLUSTERS371_tree = (CommonTree)adaptor.dupNode(SHOW_CLUSTERS371);


			root_1 = (CommonTree)adaptor.becomeRoot(SHOW_CLUSTERS371_tree, root_1);
			}

			if ( input.LA(1)==Token.DOWN ) {
				match(input, Token.DOWN, null); if (state.failed) return retval;
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:740:23: ( clustername )?
				int alt94=2;
				int LA94_0 = input.LA(1);
				if ( (LA94_0==IDENT||LA94_0==QUOTED_IDENT) ) {
					alt94=1;
				}
				switch (alt94) {
					case 1 :
						// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:740:23: clustername
						{
						_last = (CommonTree)input.LT(1);
						pushFollow(FOLLOW_clustername_in_showClustersStmt6977);
						clustername372=clustername();
						state._fsp--;
						if (state.failed) return retval;
						if ( state.backtracking==0 ) 
						adaptor.addChild(root_1, clustername372.getTree());

						if ( state.backtracking==0 ) {
						}

						}
						break;

				}

				match(input, Token.UP, null); if (state.failed) return retval;
			}
			adaptor.addChild(root_0, root_1);
			_last = _save_last_1;
			}


			if ( state.backtracking==0 ) { retval.value = new ShowTenants((clustername372!=null?((StatementBuilder.clustername_return)clustername372).value:null)); }
			if ( state.backtracking==0 ) {
			}

			}

			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "showClustersStmt"


	public static class alterClusterStmt_return extends TreeRuleReturnScope {
		public Statement value;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "alterClusterStmt"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:743:1: alterClusterStmt returns [Statement value] : ( ^( ALTER_CLUSTER clustername genericProperties ) | ^( ALTER_CLUSTER_ADD_NODES clustername nodeList ) | ^( ALTER_CLUSTER_DROP_NODES clustername nodeList ) | ^( ALTER_CLUSTER_DECOMMISSION_NODES clustername nodeList ) );
	public final StatementBuilder.alterClusterStmt_return alterClusterStmt() throws RecognitionException {
		StatementBuilder.alterClusterStmt_return retval = new StatementBuilder.alterClusterStmt_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree ALTER_CLUSTER373=null;
		CommonTree ALTER_CLUSTER_ADD_NODES376=null;
		CommonTree ALTER_CLUSTER_DROP_NODES379=null;
		CommonTree ALTER_CLUSTER_DECOMMISSION_NODES382=null;
		TreeRuleReturnScope clustername374 =null;
		TreeRuleReturnScope genericProperties375 =null;
		TreeRuleReturnScope clustername377 =null;
		TreeRuleReturnScope nodeList378 =null;
		TreeRuleReturnScope clustername380 =null;
		TreeRuleReturnScope nodeList381 =null;
		TreeRuleReturnScope clustername383 =null;
		TreeRuleReturnScope nodeList384 =null;

		CommonTree ALTER_CLUSTER373_tree=null;
		CommonTree ALTER_CLUSTER_ADD_NODES376_tree=null;
		CommonTree ALTER_CLUSTER_DROP_NODES379_tree=null;
		CommonTree ALTER_CLUSTER_DECOMMISSION_NODES382_tree=null;

		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:744:5: ( ^( ALTER_CLUSTER clustername genericProperties ) | ^( ALTER_CLUSTER_ADD_NODES clustername nodeList ) | ^( ALTER_CLUSTER_DROP_NODES clustername nodeList ) | ^( ALTER_CLUSTER_DECOMMISSION_NODES clustername nodeList ) )
			int alt95=4;
			switch ( input.LA(1) ) {
			case ALTER_CLUSTER:
				{
				alt95=1;
				}
				break;
			case ALTER_CLUSTER_ADD_NODES:
				{
				alt95=2;
				}
				break;
			case ALTER_CLUSTER_DROP_NODES:
				{
				alt95=3;
				}
				break;
			case ALTER_CLUSTER_DECOMMISSION_NODES:
				{
				alt95=4;
				}
				break;
			default:
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 95, 0, input);
				throw nvae;
			}
			switch (alt95) {
				case 1 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:744:7: ^( ALTER_CLUSTER clustername genericProperties )
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					{
					CommonTree _save_last_1 = _last;
					CommonTree _first_1 = null;
					CommonTree root_1 = (CommonTree)adaptor.nil();
					_last = (CommonTree)input.LT(1);
					ALTER_CLUSTER373=(CommonTree)match(input,ALTER_CLUSTER,FOLLOW_ALTER_CLUSTER_in_alterClusterStmt7007); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					ALTER_CLUSTER373_tree = (CommonTree)adaptor.dupNode(ALTER_CLUSTER373);


					root_1 = (CommonTree)adaptor.becomeRoot(ALTER_CLUSTER373_tree, root_1);
					}

					match(input, Token.DOWN, null); if (state.failed) return retval;
					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_clustername_in_alterClusterStmt7009);
					clustername374=clustername();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, clustername374.getTree());

					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_genericProperties_in_alterClusterStmt7011);
					genericProperties375=genericProperties();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, genericProperties375.getTree());

					match(input, Token.UP, null); if (state.failed) return retval;
					adaptor.addChild(root_0, root_1);
					_last = _save_last_1;
					}


					if ( state.backtracking==0 ) { retval.value = new AlterTenantProperty((clustername374!=null?((StatementBuilder.clustername_return)clustername374).value:null), (genericProperties375!=null?((StatementBuilder.genericProperties_return)genericProperties375).value:null)); }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 2 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:745:7: ^( ALTER_CLUSTER_ADD_NODES clustername nodeList )
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					{
					CommonTree _save_last_1 = _last;
					CommonTree _first_1 = null;
					CommonTree root_1 = (CommonTree)adaptor.nil();
					_last = (CommonTree)input.LT(1);
					ALTER_CLUSTER_ADD_NODES376=(CommonTree)match(input,ALTER_CLUSTER_ADD_NODES,FOLLOW_ALTER_CLUSTER_ADD_NODES_in_alterClusterStmt7023); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					ALTER_CLUSTER_ADD_NODES376_tree = (CommonTree)adaptor.dupNode(ALTER_CLUSTER_ADD_NODES376);


					root_1 = (CommonTree)adaptor.becomeRoot(ALTER_CLUSTER_ADD_NODES376_tree, root_1);
					}

					match(input, Token.DOWN, null); if (state.failed) return retval;
					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_clustername_in_alterClusterStmt7025);
					clustername377=clustername();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, clustername377.getTree());

					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_nodeList_in_alterClusterStmt7027);
					nodeList378=nodeList();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, nodeList378.getTree());

					match(input, Token.UP, null); if (state.failed) return retval;
					adaptor.addChild(root_0, root_1);
					_last = _save_last_1;
					}


					if ( state.backtracking==0 ) { retval.value = new AlterTenantModifyNodes((clustername377!=null?((StatementBuilder.clustername_return)clustername377).value:null), (nodeList378!=null?((StatementBuilder.nodeList_return)nodeList378).value:null), TenantModificationOperation.ADD_NODES); }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 3 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:746:7: ^( ALTER_CLUSTER_DROP_NODES clustername nodeList )
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					{
					CommonTree _save_last_1 = _last;
					CommonTree _first_1 = null;
					CommonTree root_1 = (CommonTree)adaptor.nil();
					_last = (CommonTree)input.LT(1);
					ALTER_CLUSTER_DROP_NODES379=(CommonTree)match(input,ALTER_CLUSTER_DROP_NODES,FOLLOW_ALTER_CLUSTER_DROP_NODES_in_alterClusterStmt7039); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					ALTER_CLUSTER_DROP_NODES379_tree = (CommonTree)adaptor.dupNode(ALTER_CLUSTER_DROP_NODES379);


					root_1 = (CommonTree)adaptor.becomeRoot(ALTER_CLUSTER_DROP_NODES379_tree, root_1);
					}

					match(input, Token.DOWN, null); if (state.failed) return retval;
					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_clustername_in_alterClusterStmt7041);
					clustername380=clustername();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, clustername380.getTree());

					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_nodeList_in_alterClusterStmt7043);
					nodeList381=nodeList();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, nodeList381.getTree());

					match(input, Token.UP, null); if (state.failed) return retval;
					adaptor.addChild(root_0, root_1);
					_last = _save_last_1;
					}


					if ( state.backtracking==0 ) { retval.value = new AlterTenantModifyNodes((clustername380!=null?((StatementBuilder.clustername_return)clustername380).value:null), (nodeList381!=null?((StatementBuilder.nodeList_return)nodeList381).value:null), TenantModificationOperation.DROP_NODES); }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 4 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:747:7: ^( ALTER_CLUSTER_DECOMMISSION_NODES clustername nodeList )
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					{
					CommonTree _save_last_1 = _last;
					CommonTree _first_1 = null;
					CommonTree root_1 = (CommonTree)adaptor.nil();
					_last = (CommonTree)input.LT(1);
					ALTER_CLUSTER_DECOMMISSION_NODES382=(CommonTree)match(input,ALTER_CLUSTER_DECOMMISSION_NODES,FOLLOW_ALTER_CLUSTER_DECOMMISSION_NODES_in_alterClusterStmt7055); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					ALTER_CLUSTER_DECOMMISSION_NODES382_tree = (CommonTree)adaptor.dupNode(ALTER_CLUSTER_DECOMMISSION_NODES382);


					root_1 = (CommonTree)adaptor.becomeRoot(ALTER_CLUSTER_DECOMMISSION_NODES382_tree, root_1);
					}

					match(input, Token.DOWN, null); if (state.failed) return retval;
					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_clustername_in_alterClusterStmt7057);
					clustername383=clustername();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, clustername383.getTree());

					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_nodeList_in_alterClusterStmt7059);
					nodeList384=nodeList();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, nodeList384.getTree());

					match(input, Token.UP, null); if (state.failed) return retval;
					adaptor.addChild(root_0, root_1);
					_last = _save_last_1;
					}


					if ( state.backtracking==0 ) { retval.value = new AlterTenantModifyNodes((clustername383!=null?((StatementBuilder.clustername_return)clustername383).value:null), (nodeList384!=null?((StatementBuilder.nodeList_return)nodeList384).value:null), TenantModificationOperation.DECOMMISSION_NODES); }
					if ( state.backtracking==0 ) {
					}

					}
					break;

			}
			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "alterClusterStmt"


	public static class migrateTableStmt_return extends TreeRuleReturnScope {
		public Statement value;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "migrateTableStmt"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:750:1: migrateTableStmt returns [Statement value] : ^( MIGRATE_TABLE namedTable clustername ) ;
	public final StatementBuilder.migrateTableStmt_return migrateTableStmt() throws RecognitionException {
		StatementBuilder.migrateTableStmt_return retval = new StatementBuilder.migrateTableStmt_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree MIGRATE_TABLE385=null;
		TreeRuleReturnScope namedTable386 =null;
		TreeRuleReturnScope clustername387 =null;

		CommonTree MIGRATE_TABLE385_tree=null;

		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:751:5: ( ^( MIGRATE_TABLE namedTable clustername ) )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:751:7: ^( MIGRATE_TABLE namedTable clustername )
			{
			root_0 = (CommonTree)adaptor.nil();


			_last = (CommonTree)input.LT(1);
			{
			CommonTree _save_last_1 = _last;
			CommonTree _first_1 = null;
			CommonTree root_1 = (CommonTree)adaptor.nil();
			_last = (CommonTree)input.LT(1);
			MIGRATE_TABLE385=(CommonTree)match(input,MIGRATE_TABLE,FOLLOW_MIGRATE_TABLE_in_migrateTableStmt7088); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			MIGRATE_TABLE385_tree = (CommonTree)adaptor.dupNode(MIGRATE_TABLE385);


			root_1 = (CommonTree)adaptor.becomeRoot(MIGRATE_TABLE385_tree, root_1);
			}

			match(input, Token.DOWN, null); if (state.failed) return retval;
			_last = (CommonTree)input.LT(1);
			pushFollow(FOLLOW_namedTable_in_migrateTableStmt7090);
			namedTable386=namedTable();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) 
			adaptor.addChild(root_1, namedTable386.getTree());

			_last = (CommonTree)input.LT(1);
			pushFollow(FOLLOW_clustername_in_migrateTableStmt7092);
			clustername387=clustername();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) 
			adaptor.addChild(root_1, clustername387.getTree());

			match(input, Token.UP, null); if (state.failed) return retval;
			adaptor.addChild(root_0, root_1);
			_last = _save_last_1;
			}


			if ( state.backtracking==0 ) { retval.value = new MigrateTable((namedTable386!=null?((StatementBuilder.namedTable_return)namedTable386).value:null), (clustername387!=null?((StatementBuilder.clustername_return)clustername387).value:null)); }
			if ( state.backtracking==0 ) {
			}

			}

			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "migrateTableStmt"


	public static class clustername_return extends TreeRuleReturnScope {
		public String value;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "clustername"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:754:1: clustername returns [String value] : ident ;
	public final StatementBuilder.clustername_return clustername() throws RecognitionException {
		StatementBuilder.clustername_return retval = new StatementBuilder.clustername_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		TreeRuleReturnScope ident388 =null;


		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:755:5: ( ident )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:755:7: ident
			{
			root_0 = (CommonTree)adaptor.nil();


			_last = (CommonTree)input.LT(1);
			pushFollow(FOLLOW_ident_in_clustername7116);
			ident388=ident();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) 
			adaptor.addChild(root_0, ident388.getTree());

			if ( state.backtracking==0 ) { retval.value = (ident388!=null?((StatementBuilder.ident_return)ident388).value:null); }
			if ( state.backtracking==0 ) {
			}

			}

			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "clustername"


	public static class nodeList_return extends TreeRuleReturnScope {
		public String value;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "nodeList"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:758:1: nodeList returns [String value] : ident ;
	public final StatementBuilder.nodeList_return nodeList() throws RecognitionException {
		StatementBuilder.nodeList_return retval = new StatementBuilder.nodeList_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		TreeRuleReturnScope ident389 =null;


		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:759:5: ( ident )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:759:7: ident
			{
			root_0 = (CommonTree)adaptor.nil();


			_last = (CommonTree)input.LT(1);
			pushFollow(FOLLOW_ident_in_nodeList7143);
			ident389=ident();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) 
			adaptor.addChild(root_0, ident389.getTree());

			if ( state.backtracking==0 ) { retval.value = (ident389!=null?((StatementBuilder.ident_return)ident389).value:null); }
			if ( state.backtracking==0 ) {
			}

			}

			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "nodeList"


	public static class dropRepository_return extends TreeRuleReturnScope {
		public Statement value;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "dropRepository"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:762:1: dropRepository returns [Statement value] : ^( DROP_REPOSITORY repository ) ;
	public final StatementBuilder.dropRepository_return dropRepository() throws RecognitionException {
		StatementBuilder.dropRepository_return retval = new StatementBuilder.dropRepository_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree DROP_REPOSITORY390=null;
		TreeRuleReturnScope repository391 =null;

		CommonTree DROP_REPOSITORY390_tree=null;

		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:763:5: ( ^( DROP_REPOSITORY repository ) )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:763:7: ^( DROP_REPOSITORY repository )
			{
			root_0 = (CommonTree)adaptor.nil();


			_last = (CommonTree)input.LT(1);
			{
			CommonTree _save_last_1 = _last;
			CommonTree _first_1 = null;
			CommonTree root_1 = (CommonTree)adaptor.nil();
			_last = (CommonTree)input.LT(1);
			DROP_REPOSITORY390=(CommonTree)match(input,DROP_REPOSITORY,FOLLOW_DROP_REPOSITORY_in_dropRepository7171); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			DROP_REPOSITORY390_tree = (CommonTree)adaptor.dupNode(DROP_REPOSITORY390);


			root_1 = (CommonTree)adaptor.becomeRoot(DROP_REPOSITORY390_tree, root_1);
			}

			match(input, Token.DOWN, null); if (state.failed) return retval;
			_last = (CommonTree)input.LT(1);
			pushFollow(FOLLOW_repository_in_dropRepository7173);
			repository391=repository();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) 
			adaptor.addChild(root_1, repository391.getTree());

			match(input, Token.UP, null); if (state.failed) return retval;
			adaptor.addChild(root_0, root_1);
			_last = _save_last_1;
			}


			if ( state.backtracking==0 ) { retval.value = new DropRepository((repository391!=null?((StatementBuilder.repository_return)repository391).value:null)); }
			if ( state.backtracking==0 ) {
			}

			}

			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "dropRepository"


	public static class dropSnapshot_return extends TreeRuleReturnScope {
		public Statement value;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "dropSnapshot"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:766:1: dropSnapshot returns [Statement value] : ^( DROP_SNAPSHOT qname ) ;
	public final StatementBuilder.dropSnapshot_return dropSnapshot() throws RecognitionException {
		StatementBuilder.dropSnapshot_return retval = new StatementBuilder.dropSnapshot_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree DROP_SNAPSHOT392=null;
		TreeRuleReturnScope qname393 =null;

		CommonTree DROP_SNAPSHOT392_tree=null;

		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:767:5: ( ^( DROP_SNAPSHOT qname ) )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:767:7: ^( DROP_SNAPSHOT qname )
			{
			root_0 = (CommonTree)adaptor.nil();


			_last = (CommonTree)input.LT(1);
			{
			CommonTree _save_last_1 = _last;
			CommonTree _first_1 = null;
			CommonTree root_1 = (CommonTree)adaptor.nil();
			_last = (CommonTree)input.LT(1);
			DROP_SNAPSHOT392=(CommonTree)match(input,DROP_SNAPSHOT,FOLLOW_DROP_SNAPSHOT_in_dropSnapshot7198); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			DROP_SNAPSHOT392_tree = (CommonTree)adaptor.dupNode(DROP_SNAPSHOT392);


			root_1 = (CommonTree)adaptor.becomeRoot(DROP_SNAPSHOT392_tree, root_1);
			}

			match(input, Token.DOWN, null); if (state.failed) return retval;
			_last = (CommonTree)input.LT(1);
			pushFollow(FOLLOW_qname_in_dropSnapshot7200);
			qname393=qname();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) 
			adaptor.addChild(root_1, qname393.getTree());

			match(input, Token.UP, null); if (state.failed) return retval;
			adaptor.addChild(root_0, root_1);
			_last = _save_last_1;
			}


			if ( state.backtracking==0 ) { retval.value = new DropSnapshot((qname393!=null?((StatementBuilder.qname_return)qname393).value:null)); }
			if ( state.backtracking==0 ) {
			}

			}

			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "dropSnapshot"


	public static class dropUser_return extends TreeRuleReturnScope {
		public Statement value;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "dropUser"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:770:1: dropUser returns [Statement value] : ^( DROP_USER username ) ;
	public final StatementBuilder.dropUser_return dropUser() throws RecognitionException {
		StatementBuilder.dropUser_return retval = new StatementBuilder.dropUser_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree DROP_USER394=null;
		TreeRuleReturnScope username395 =null;

		CommonTree DROP_USER394_tree=null;

		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:771:5: ( ^( DROP_USER username ) )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:771:7: ^( DROP_USER username )
			{
			root_0 = (CommonTree)adaptor.nil();


			_last = (CommonTree)input.LT(1);
			{
			CommonTree _save_last_1 = _last;
			CommonTree _first_1 = null;
			CommonTree root_1 = (CommonTree)adaptor.nil();
			_last = (CommonTree)input.LT(1);
			DROP_USER394=(CommonTree)match(input,DROP_USER,FOLLOW_DROP_USER_in_dropUser7229); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			DROP_USER394_tree = (CommonTree)adaptor.dupNode(DROP_USER394);


			root_1 = (CommonTree)adaptor.becomeRoot(DROP_USER394_tree, root_1);
			}

			match(input, Token.DOWN, null); if (state.failed) return retval;
			_last = (CommonTree)input.LT(1);
			pushFollow(FOLLOW_username_in_dropUser7231);
			username395=username();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) 
			adaptor.addChild(root_1, username395.getTree());

			match(input, Token.UP, null); if (state.failed) return retval;
			adaptor.addChild(root_0, root_1);
			_last = _save_last_1;
			}


			if ( state.backtracking==0 ) { retval.value = new DropUser((username395!=null?((StatementBuilder.username_return)username395).value:null)); }
			if ( state.backtracking==0 ) {
			}

			}

			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "dropUser"


	public static class insert_return extends TreeRuleReturnScope {
		public Statement value;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "insert"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:774:1: insert returns [Statement value] : ( ^( INSERT values= insertValues namedTable (cols= columnIdentList )? ( onDuplicateKey )? ) | ^( INSERT subQuery= query namedTable (cols= columnIdentList )? ( onDuplicateKey )? ) );
	public final StatementBuilder.insert_return insert() throws RecognitionException {
		StatementBuilder.insert_return retval = new StatementBuilder.insert_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree INSERT396=null;
		CommonTree INSERT399=null;
		TreeRuleReturnScope values =null;
		TreeRuleReturnScope cols =null;
		TreeRuleReturnScope subQuery =null;
		TreeRuleReturnScope namedTable397 =null;
		TreeRuleReturnScope onDuplicateKey398 =null;
		TreeRuleReturnScope namedTable400 =null;
		TreeRuleReturnScope onDuplicateKey401 =null;

		CommonTree INSERT396_tree=null;
		CommonTree INSERT399_tree=null;

		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:775:5: ( ^( INSERT values= insertValues namedTable (cols= columnIdentList )? ( onDuplicateKey )? ) | ^( INSERT subQuery= query namedTable (cols= columnIdentList )? ( onDuplicateKey )? ) )
			int alt100=2;
			int LA100_0 = input.LA(1);
			if ( (LA100_0==INSERT) ) {
				int LA100_1 = input.LA(2);
				if ( (LA100_1==DOWN) ) {
					int LA100_2 = input.LA(3);
					if ( (LA100_2==INSERT_VALUES) ) {
						alt100=1;
					}
					else if ( (LA100_2==QUERY) ) {
						alt100=2;
					}

					else {
						if (state.backtracking>0) {state.failed=true; return retval;}
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 100, 2, input);
							throw nvae;
						} finally {
							input.rewind(nvaeMark);
						}
					}

				}

				else {
					if (state.backtracking>0) {state.failed=true; return retval;}
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 100, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

			}

			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 100, 0, input);
				throw nvae;
			}

			switch (alt100) {
				case 1 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:775:7: ^( INSERT values= insertValues namedTable (cols= columnIdentList )? ( onDuplicateKey )? )
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					{
					CommonTree _save_last_1 = _last;
					CommonTree _first_1 = null;
					CommonTree root_1 = (CommonTree)adaptor.nil();
					_last = (CommonTree)input.LT(1);
					INSERT396=(CommonTree)match(input,INSERT,FOLLOW_INSERT_in_insert7256); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					INSERT396_tree = (CommonTree)adaptor.dupNode(INSERT396);


					root_1 = (CommonTree)adaptor.becomeRoot(INSERT396_tree, root_1);
					}

					match(input, Token.DOWN, null); if (state.failed) return retval;
					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_insertValues_in_insert7260);
					values=insertValues();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, values.getTree());

					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_namedTable_in_insert7262);
					namedTable397=namedTable();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, namedTable397.getTree());

					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:775:51: (cols= columnIdentList )?
					int alt96=2;
					int LA96_0 = input.LA(1);
					if ( (LA96_0==IDENT_LIST) ) {
						alt96=1;
					}
					switch (alt96) {
						case 1 :
							// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:775:51: cols= columnIdentList
							{
							_last = (CommonTree)input.LT(1);
							pushFollow(FOLLOW_columnIdentList_in_insert7266);
							cols=columnIdentList();
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) 
							adaptor.addChild(root_1, cols.getTree());

							if ( state.backtracking==0 ) {
							}

							}
							break;

					}

					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:775:69: ( onDuplicateKey )?
					int alt97=2;
					int LA97_0 = input.LA(1);
					if ( (LA97_0==ON_DUP_KEY) ) {
						alt97=1;
					}
					switch (alt97) {
						case 1 :
							// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:775:69: onDuplicateKey
							{
							_last = (CommonTree)input.LT(1);
							pushFollow(FOLLOW_onDuplicateKey_in_insert7269);
							onDuplicateKey398=onDuplicateKey();
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) 
							adaptor.addChild(root_1, onDuplicateKey398.getTree());

							if ( state.backtracking==0 ) {
							}

							}
							break;

					}

					match(input, Token.UP, null); if (state.failed) return retval;
					adaptor.addChild(root_0, root_1);
					_last = _save_last_1;
					}


					if ( state.backtracking==0 ) {
					            retval.value = new InsertFromValues(
					                (namedTable397!=null?((StatementBuilder.namedTable_return)namedTable397).value:null),
					                (values!=null?((StatementBuilder.insertValues_return)values).value:null),
					                (cols!=null?((StatementBuilder.columnIdentList_return)cols).value:null),
					                (onDuplicateKey398!=null?((StatementBuilder.onDuplicateKey_return)onDuplicateKey398).value:null));
					        }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 2 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:783:7: ^( INSERT subQuery= query namedTable (cols= columnIdentList )? ( onDuplicateKey )? )
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					{
					CommonTree _save_last_1 = _last;
					CommonTree _first_1 = null;
					CommonTree root_1 = (CommonTree)adaptor.nil();
					_last = (CommonTree)input.LT(1);
					INSERT399=(CommonTree)match(input,INSERT,FOLLOW_INSERT_in_insert7290); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					INSERT399_tree = (CommonTree)adaptor.dupNode(INSERT399);


					root_1 = (CommonTree)adaptor.becomeRoot(INSERT399_tree, root_1);
					}

					match(input, Token.DOWN, null); if (state.failed) return retval;
					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_query_in_insert7294);
					subQuery=query();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, subQuery.getTree());

					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_namedTable_in_insert7296);
					namedTable400=namedTable();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, namedTable400.getTree());

					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:783:46: (cols= columnIdentList )?
					int alt98=2;
					int LA98_0 = input.LA(1);
					if ( (LA98_0==IDENT_LIST) ) {
						alt98=1;
					}
					switch (alt98) {
						case 1 :
							// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:783:46: cols= columnIdentList
							{
							_last = (CommonTree)input.LT(1);
							pushFollow(FOLLOW_columnIdentList_in_insert7300);
							cols=columnIdentList();
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) 
							adaptor.addChild(root_1, cols.getTree());

							if ( state.backtracking==0 ) {
							}

							}
							break;

					}

					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:783:64: ( onDuplicateKey )?
					int alt99=2;
					int LA99_0 = input.LA(1);
					if ( (LA99_0==ON_DUP_KEY) ) {
						alt99=1;
					}
					switch (alt99) {
						case 1 :
							// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:783:64: onDuplicateKey
							{
							_last = (CommonTree)input.LT(1);
							pushFollow(FOLLOW_onDuplicateKey_in_insert7303);
							onDuplicateKey401=onDuplicateKey();
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) 
							adaptor.addChild(root_1, onDuplicateKey401.getTree());

							if ( state.backtracking==0 ) {
							}

							}
							break;

					}

					match(input, Token.UP, null); if (state.failed) return retval;
					adaptor.addChild(root_0, root_1);
					_last = _save_last_1;
					}


					if ( state.backtracking==0 ) {
					            retval.value = new InsertFromSubquery(
					                (namedTable400!=null?((StatementBuilder.namedTable_return)namedTable400).value:null),
					                (subQuery!=null?((StatementBuilder.query_return)subQuery).value:null),
					                (cols!=null?((StatementBuilder.columnIdentList_return)cols).value:null),
					                (onDuplicateKey401!=null?((StatementBuilder.onDuplicateKey_return)onDuplicateKey401).value:null));
					        }
					if ( state.backtracking==0 ) {
					}

					}
					break;

			}
			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "insert"


	public static class onDuplicateKey_return extends TreeRuleReturnScope {
		public List<Assignment> value;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "onDuplicateKey"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:793:1: onDuplicateKey returns [List<Assignment> value] : ^( ON_DUP_KEY assignmentList ) ;
	public final StatementBuilder.onDuplicateKey_return onDuplicateKey() throws RecognitionException {
		StatementBuilder.onDuplicateKey_return retval = new StatementBuilder.onDuplicateKey_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree ON_DUP_KEY402=null;
		TreeRuleReturnScope assignmentList403 =null;

		CommonTree ON_DUP_KEY402_tree=null;

		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:794:5: ( ^( ON_DUP_KEY assignmentList ) )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:794:7: ^( ON_DUP_KEY assignmentList )
			{
			root_0 = (CommonTree)adaptor.nil();


			_last = (CommonTree)input.LT(1);
			{
			CommonTree _save_last_1 = _last;
			CommonTree _first_1 = null;
			CommonTree root_1 = (CommonTree)adaptor.nil();
			_last = (CommonTree)input.LT(1);
			ON_DUP_KEY402=(CommonTree)match(input,ON_DUP_KEY,FOLLOW_ON_DUP_KEY_in_onDuplicateKey7337); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			ON_DUP_KEY402_tree = (CommonTree)adaptor.dupNode(ON_DUP_KEY402);


			root_1 = (CommonTree)adaptor.becomeRoot(ON_DUP_KEY402_tree, root_1);
			}

			match(input, Token.DOWN, null); if (state.failed) return retval;
			_last = (CommonTree)input.LT(1);
			pushFollow(FOLLOW_assignmentList_in_onDuplicateKey7339);
			assignmentList403=assignmentList();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) 
			adaptor.addChild(root_1, assignmentList403.getTree());

			match(input, Token.UP, null); if (state.failed) return retval;
			adaptor.addChild(root_0, root_1);
			_last = _save_last_1;
			}


			if ( state.backtracking==0 ) { retval.value = (assignmentList403!=null?((StatementBuilder.assignmentList_return)assignmentList403).value:null); }
			if ( state.backtracking==0 ) {
			}

			}

			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "onDuplicateKey"


	public static class insertValues_return extends TreeRuleReturnScope {
		public List<ValuesList> value = new ArrayList<>();
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "insertValues"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:797:1: insertValues returns [List<ValuesList> value = new ArrayList<>()] : ^( INSERT_VALUES ( valuesList )+ ) ;
	public final StatementBuilder.insertValues_return insertValues() throws RecognitionException {
		StatementBuilder.insertValues_return retval = new StatementBuilder.insertValues_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree INSERT_VALUES404=null;
		TreeRuleReturnScope valuesList405 =null;

		CommonTree INSERT_VALUES404_tree=null;

		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:798:5: ( ^( INSERT_VALUES ( valuesList )+ ) )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:798:7: ^( INSERT_VALUES ( valuesList )+ )
			{
			root_0 = (CommonTree)adaptor.nil();


			_last = (CommonTree)input.LT(1);
			{
			CommonTree _save_last_1 = _last;
			CommonTree _first_1 = null;
			CommonTree root_1 = (CommonTree)adaptor.nil();
			_last = (CommonTree)input.LT(1);
			INSERT_VALUES404=(CommonTree)match(input,INSERT_VALUES,FOLLOW_INSERT_VALUES_in_insertValues7364); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			INSERT_VALUES404_tree = (CommonTree)adaptor.dupNode(INSERT_VALUES404);


			root_1 = (CommonTree)adaptor.becomeRoot(INSERT_VALUES404_tree, root_1);
			}

			match(input, Token.DOWN, null); if (state.failed) return retval;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:798:23: ( valuesList )+
			int cnt101=0;
			loop101:
			while (true) {
				int alt101=2;
				int LA101_0 = input.LA(1);
				if ( (LA101_0==VALUES_LIST) ) {
					alt101=1;
				}

				switch (alt101) {
				case 1 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:798:24: valuesList
					{
					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_valuesList_in_insertValues7367);
					valuesList405=valuesList();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, valuesList405.getTree());

					if ( state.backtracking==0 ) { retval.value.add((valuesList405!=null?((StatementBuilder.valuesList_return)valuesList405).value:null)); }
					if ( state.backtracking==0 ) {
					}

					}
					break;

				default :
					if ( cnt101 >= 1 ) break loop101;
					if (state.backtracking>0) {state.failed=true; return retval;}
					EarlyExitException eee = new EarlyExitException(101, input);
					throw eee;
				}
				cnt101++;
			}

			match(input, Token.UP, null); if (state.failed) return retval;
			adaptor.addChild(root_0, root_1);
			_last = _save_last_1;
			}


			if ( state.backtracking==0 ) {
			}

			}

			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "insertValues"


	public static class valuesList_return extends TreeRuleReturnScope {
		public ValuesList value;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "valuesList"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:801:1: valuesList returns [ValuesList value] : ^( VALUES_LIST exprList ) ;
	public final StatementBuilder.valuesList_return valuesList() throws RecognitionException {
		StatementBuilder.valuesList_return retval = new StatementBuilder.valuesList_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree VALUES_LIST406=null;
		TreeRuleReturnScope exprList407 =null;

		CommonTree VALUES_LIST406_tree=null;

		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:802:5: ( ^( VALUES_LIST exprList ) )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:802:7: ^( VALUES_LIST exprList )
			{
			root_0 = (CommonTree)adaptor.nil();


			_last = (CommonTree)input.LT(1);
			{
			CommonTree _save_last_1 = _last;
			CommonTree _first_1 = null;
			CommonTree root_1 = (CommonTree)adaptor.nil();
			_last = (CommonTree)input.LT(1);
			VALUES_LIST406=(CommonTree)match(input,VALUES_LIST,FOLLOW_VALUES_LIST_in_valuesList7394); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			VALUES_LIST406_tree = (CommonTree)adaptor.dupNode(VALUES_LIST406);


			root_1 = (CommonTree)adaptor.becomeRoot(VALUES_LIST406_tree, root_1);
			}

			if ( input.LA(1)==Token.DOWN ) {
				match(input, Token.DOWN, null); if (state.failed) return retval;
				_last = (CommonTree)input.LT(1);
				pushFollow(FOLLOW_exprList_in_valuesList7396);
				exprList407=exprList();
				state._fsp--;
				if (state.failed) return retval;
				if ( state.backtracking==0 ) 
				adaptor.addChild(root_1, exprList407.getTree());

				match(input, Token.UP, null); if (state.failed) return retval;
			}
			adaptor.addChild(root_0, root_1);
			_last = _save_last_1;
			}


			if ( state.backtracking==0 ) { retval.value = new ValuesList((exprList407!=null?((StatementBuilder.exprList_return)exprList407).value:null)); }
			if ( state.backtracking==0 ) {
			}

			}

			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "valuesList"


	public static class delete_return extends TreeRuleReturnScope {
		public Statement value;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "delete"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:805:1: delete returns [Statement value] : ^( DELETE relation (where= whereClause )? ) ;
	public final StatementBuilder.delete_return delete() throws RecognitionException {
		StatementBuilder.delete_return retval = new StatementBuilder.delete_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree DELETE408=null;
		TreeRuleReturnScope where =null;
		TreeRuleReturnScope relation409 =null;

		CommonTree DELETE408_tree=null;

		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:806:5: ( ^( DELETE relation (where= whereClause )? ) )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:806:7: ^( DELETE relation (where= whereClause )? )
			{
			root_0 = (CommonTree)adaptor.nil();


			_last = (CommonTree)input.LT(1);
			{
			CommonTree _save_last_1 = _last;
			CommonTree _first_1 = null;
			CommonTree root_1 = (CommonTree)adaptor.nil();
			_last = (CommonTree)input.LT(1);
			DELETE408=(CommonTree)match(input,DELETE,FOLLOW_DELETE_in_delete7421); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			DELETE408_tree = (CommonTree)adaptor.dupNode(DELETE408);


			root_1 = (CommonTree)adaptor.becomeRoot(DELETE408_tree, root_1);
			}

			match(input, Token.DOWN, null); if (state.failed) return retval;
			_last = (CommonTree)input.LT(1);
			pushFollow(FOLLOW_relation_in_delete7423);
			relation409=relation();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) 
			adaptor.addChild(root_1, relation409.getTree());

			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:806:30: (where= whereClause )?
			int alt102=2;
			int LA102_0 = input.LA(1);
			if ( (LA102_0==WHERE) ) {
				alt102=1;
			}
			switch (alt102) {
				case 1 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:806:30: where= whereClause
					{
					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_whereClause_in_delete7427);
					where=whereClause();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, where.getTree());

					if ( state.backtracking==0 ) {
					}

					}
					break;

			}

			match(input, Token.UP, null); if (state.failed) return retval;
			adaptor.addChild(root_0, root_1);
			_last = _save_last_1;
			}


			if ( state.backtracking==0 ) {
			            retval.value = new Delete((relation409!=null?((StatementBuilder.relation_return)relation409).value:null), (where!=null?((StatementBuilder.whereClause_return)where).value:null));
			        }
			if ( state.backtracking==0 ) {
			}

			}

			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "delete"


	public static class update_return extends TreeRuleReturnScope {
		public Statement value;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "update"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:812:1: update returns [Statement value] : ^( UPDATE relation assignments= assignmentList (where= whereClause )? ) ;
	public final StatementBuilder.update_return update() throws RecognitionException {
		StatementBuilder.update_return retval = new StatementBuilder.update_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree UPDATE410=null;
		TreeRuleReturnScope assignments =null;
		TreeRuleReturnScope where =null;
		TreeRuleReturnScope relation411 =null;

		CommonTree UPDATE410_tree=null;

		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:813:5: ( ^( UPDATE relation assignments= assignmentList (where= whereClause )? ) )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:813:7: ^( UPDATE relation assignments= assignmentList (where= whereClause )? )
			{
			root_0 = (CommonTree)adaptor.nil();


			_last = (CommonTree)input.LT(1);
			{
			CommonTree _save_last_1 = _last;
			CommonTree _first_1 = null;
			CommonTree root_1 = (CommonTree)adaptor.nil();
			_last = (CommonTree)input.LT(1);
			UPDATE410=(CommonTree)match(input,UPDATE,FOLLOW_UPDATE_in_update7461); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			UPDATE410_tree = (CommonTree)adaptor.dupNode(UPDATE410);


			root_1 = (CommonTree)adaptor.becomeRoot(UPDATE410_tree, root_1);
			}

			match(input, Token.DOWN, null); if (state.failed) return retval;
			_last = (CommonTree)input.LT(1);
			pushFollow(FOLLOW_relation_in_update7463);
			relation411=relation();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) 
			adaptor.addChild(root_1, relation411.getTree());

			_last = (CommonTree)input.LT(1);
			pushFollow(FOLLOW_assignmentList_in_update7467);
			assignments=assignmentList();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) 
			adaptor.addChild(root_1, assignments.getTree());

			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:813:57: (where= whereClause )?
			int alt103=2;
			int LA103_0 = input.LA(1);
			if ( (LA103_0==WHERE) ) {
				alt103=1;
			}
			switch (alt103) {
				case 1 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:813:57: where= whereClause
					{
					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_whereClause_in_update7471);
					where=whereClause();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, where.getTree());

					if ( state.backtracking==0 ) {
					}

					}
					break;

			}

			match(input, Token.UP, null); if (state.failed) return retval;
			adaptor.addChild(root_0, root_1);
			_last = _save_last_1;
			}


			if ( state.backtracking==0 ) {
			            retval.value = new Update((relation411!=null?((StatementBuilder.relation_return)relation411).value:null),
			                                (assignments!=null?((StatementBuilder.assignmentList_return)assignments).value:null),
			                                (where!=null?((StatementBuilder.whereClause_return)where).value:null));
			        }
			if ( state.backtracking==0 ) {
			}

			}

			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "update"


	public static class assignmentList_return extends TreeRuleReturnScope {
		public List<Assignment> value = new ArrayList<>();
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "assignmentList"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:821:1: assignmentList returns [List<Assignment> value = new ArrayList<>()] : ^( ASSIGNMENT_LIST ( assignment )+ ) ;
	public final StatementBuilder.assignmentList_return assignmentList() throws RecognitionException {
		StatementBuilder.assignmentList_return retval = new StatementBuilder.assignmentList_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree ASSIGNMENT_LIST412=null;
		TreeRuleReturnScope assignment413 =null;

		CommonTree ASSIGNMENT_LIST412_tree=null;

		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:822:5: ( ^( ASSIGNMENT_LIST ( assignment )+ ) )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:822:7: ^( ASSIGNMENT_LIST ( assignment )+ )
			{
			root_0 = (CommonTree)adaptor.nil();


			_last = (CommonTree)input.LT(1);
			{
			CommonTree _save_last_1 = _last;
			CommonTree _first_1 = null;
			CommonTree root_1 = (CommonTree)adaptor.nil();
			_last = (CommonTree)input.LT(1);
			ASSIGNMENT_LIST412=(CommonTree)match(input,ASSIGNMENT_LIST,FOLLOW_ASSIGNMENT_LIST_in_assignmentList7505); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			ASSIGNMENT_LIST412_tree = (CommonTree)adaptor.dupNode(ASSIGNMENT_LIST412);


			root_1 = (CommonTree)adaptor.becomeRoot(ASSIGNMENT_LIST412_tree, root_1);
			}

			match(input, Token.DOWN, null); if (state.failed) return retval;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:822:25: ( assignment )+
			int cnt104=0;
			loop104:
			while (true) {
				int alt104=2;
				int LA104_0 = input.LA(1);
				if ( (LA104_0==ASSIGNMENT) ) {
					alt104=1;
				}

				switch (alt104) {
				case 1 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:822:26: assignment
					{
					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_assignment_in_assignmentList7508);
					assignment413=assignment();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, assignment413.getTree());

					if ( state.backtracking==0 ) { retval.value.add((assignment413!=null?((StatementBuilder.assignment_return)assignment413).value:null)); }
					if ( state.backtracking==0 ) {
					}

					}
					break;

				default :
					if ( cnt104 >= 1 ) break loop104;
					if (state.backtracking>0) {state.failed=true; return retval;}
					EarlyExitException eee = new EarlyExitException(104, input);
					throw eee;
				}
				cnt104++;
			}

			match(input, Token.UP, null); if (state.failed) return retval;
			adaptor.addChild(root_0, root_1);
			_last = _save_last_1;
			}


			if ( state.backtracking==0 ) {
			}

			}

			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "assignmentList"


	public static class assignment_return extends TreeRuleReturnScope {
		public Assignment value;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "assignment"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:825:1: assignment returns [Assignment value] : ( ^( ASSIGNMENT subscript expr ) | ^( ASSIGNMENT qname expr ) );
	public final StatementBuilder.assignment_return assignment() throws RecognitionException {
		StatementBuilder.assignment_return retval = new StatementBuilder.assignment_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree ASSIGNMENT414=null;
		CommonTree ASSIGNMENT417=null;
		TreeRuleReturnScope subscript415 =null;
		TreeRuleReturnScope expr416 =null;
		TreeRuleReturnScope qname418 =null;
		TreeRuleReturnScope expr419 =null;

		CommonTree ASSIGNMENT414_tree=null;
		CommonTree ASSIGNMENT417_tree=null;

		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:826:5: ( ^( ASSIGNMENT subscript expr ) | ^( ASSIGNMENT qname expr ) )
			int alt105=2;
			int LA105_0 = input.LA(1);
			if ( (LA105_0==ASSIGNMENT) ) {
				int LA105_1 = input.LA(2);
				if ( (LA105_1==DOWN) ) {
					int LA105_2 = input.LA(3);
					if ( (LA105_2==341) ) {
						alt105=1;
					}
					else if ( (LA105_2==QNAME) ) {
						alt105=2;
					}

					else {
						if (state.backtracking>0) {state.failed=true; return retval;}
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 105, 2, input);
							throw nvae;
						} finally {
							input.rewind(nvaeMark);
						}
					}

				}

				else {
					if (state.backtracking>0) {state.failed=true; return retval;}
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 105, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

			}

			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 105, 0, input);
				throw nvae;
			}

			switch (alt105) {
				case 1 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:826:7: ^( ASSIGNMENT subscript expr )
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					{
					CommonTree _save_last_1 = _last;
					CommonTree _first_1 = null;
					CommonTree root_1 = (CommonTree)adaptor.nil();
					_last = (CommonTree)input.LT(1);
					ASSIGNMENT414=(CommonTree)match(input,ASSIGNMENT,FOLLOW_ASSIGNMENT_in_assignment7536); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					ASSIGNMENT414_tree = (CommonTree)adaptor.dupNode(ASSIGNMENT414);


					root_1 = (CommonTree)adaptor.becomeRoot(ASSIGNMENT414_tree, root_1);
					}

					match(input, Token.DOWN, null); if (state.failed) return retval;
					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_subscript_in_assignment7538);
					subscript415=subscript();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, subscript415.getTree());

					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_expr_in_assignment7540);
					expr416=expr();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, expr416.getTree());

					match(input, Token.UP, null); if (state.failed) return retval;
					adaptor.addChild(root_0, root_1);
					_last = _save_last_1;
					}


					if ( state.backtracking==0 ) { retval.value = new Assignment((subscript415!=null?((StatementBuilder.subscript_return)subscript415).value:null), (expr416!=null?((StatementBuilder.expr_return)expr416).value:null)); }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 2 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:827:7: ^( ASSIGNMENT qname expr )
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					{
					CommonTree _save_last_1 = _last;
					CommonTree _first_1 = null;
					CommonTree root_1 = (CommonTree)adaptor.nil();
					_last = (CommonTree)input.LT(1);
					ASSIGNMENT417=(CommonTree)match(input,ASSIGNMENT,FOLLOW_ASSIGNMENT_in_assignment7552); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					ASSIGNMENT417_tree = (CommonTree)adaptor.dupNode(ASSIGNMENT417);


					root_1 = (CommonTree)adaptor.becomeRoot(ASSIGNMENT417_tree, root_1);
					}

					match(input, Token.DOWN, null); if (state.failed) return retval;
					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_qname_in_assignment7554);
					qname418=qname();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, qname418.getTree());

					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_expr_in_assignment7556);
					expr419=expr();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, expr419.getTree());

					match(input, Token.UP, null); if (state.failed) return retval;
					adaptor.addChild(root_0, root_1);
					_last = _save_last_1;
					}


					if ( state.backtracking==0 ) { retval.value = new Assignment(new QualifiedNameReference((qname418!=null?((StatementBuilder.qname_return)qname418).value:null)), (expr419!=null?((StatementBuilder.expr_return)expr419).value:null)); }
					if ( state.backtracking==0 ) {
					}

					}
					break;

			}
			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "assignment"


	public static class copyTo_return extends TreeRuleReturnScope {
		public Statement value;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "copyTo"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:830:1: copyTo returns [Statement value] : ^( COPY_TO namedTable ( columnList )? ( whereClause )? d= copyToTargetSpec[false] expr ( genericProperties )? ) ;
	public final StatementBuilder.copyTo_return copyTo() throws RecognitionException {
		StatementBuilder.copyTo_return retval = new StatementBuilder.copyTo_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree COPY_TO420=null;
		TreeRuleReturnScope d =null;
		TreeRuleReturnScope namedTable421 =null;
		TreeRuleReturnScope columnList422 =null;
		TreeRuleReturnScope whereClause423 =null;
		TreeRuleReturnScope expr424 =null;
		TreeRuleReturnScope genericProperties425 =null;

		CommonTree COPY_TO420_tree=null;

		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:831:5: ( ^( COPY_TO namedTable ( columnList )? ( whereClause )? d= copyToTargetSpec[false] expr ( genericProperties )? ) )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:831:7: ^( COPY_TO namedTable ( columnList )? ( whereClause )? d= copyToTargetSpec[false] expr ( genericProperties )? )
			{
			root_0 = (CommonTree)adaptor.nil();


			_last = (CommonTree)input.LT(1);
			{
			CommonTree _save_last_1 = _last;
			CommonTree _first_1 = null;
			CommonTree root_1 = (CommonTree)adaptor.nil();
			_last = (CommonTree)input.LT(1);
			COPY_TO420=(CommonTree)match(input,COPY_TO,FOLLOW_COPY_TO_in_copyTo7581); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			COPY_TO420_tree = (CommonTree)adaptor.dupNode(COPY_TO420);


			root_1 = (CommonTree)adaptor.becomeRoot(COPY_TO420_tree, root_1);
			}

			match(input, Token.DOWN, null); if (state.failed) return retval;
			_last = (CommonTree)input.LT(1);
			pushFollow(FOLLOW_namedTable_in_copyTo7583);
			namedTable421=namedTable();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) 
			adaptor.addChild(root_1, namedTable421.getTree());

			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:831:28: ( columnList )?
			int alt106=2;
			int LA106_0 = input.LA(1);
			if ( (LA106_0==COLUMN_LIST) ) {
				alt106=1;
			}
			switch (alt106) {
				case 1 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:831:28: columnList
					{
					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_columnList_in_copyTo7585);
					columnList422=columnList();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, columnList422.getTree());

					if ( state.backtracking==0 ) {
					}

					}
					break;

			}

			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:831:40: ( whereClause )?
			int alt107=2;
			int LA107_0 = input.LA(1);
			if ( (LA107_0==WHERE) ) {
				alt107=1;
			}
			switch (alt107) {
				case 1 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:831:40: whereClause
					{
					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_whereClause_in_copyTo7588);
					whereClause423=whereClause();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, whereClause423.getTree());

					if ( state.backtracking==0 ) {
					}

					}
					break;

			}

			_last = (CommonTree)input.LT(1);
			pushFollow(FOLLOW_copyToTargetSpec_in_copyTo7593);
			d=copyToTargetSpec(false);
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) 
			adaptor.addChild(root_1, d.getTree());

			_last = (CommonTree)input.LT(1);
			pushFollow(FOLLOW_expr_in_copyTo7596);
			expr424=expr();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) 
			adaptor.addChild(root_1, expr424.getTree());

			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:831:84: ( genericProperties )?
			int alt108=2;
			int LA108_0 = input.LA(1);
			if ( (LA108_0==GENERIC_PROPERTIES) ) {
				alt108=1;
			}
			switch (alt108) {
				case 1 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:831:84: genericProperties
					{
					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_genericProperties_in_copyTo7598);
					genericProperties425=genericProperties();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, genericProperties425.getTree());

					if ( state.backtracking==0 ) {
					}

					}
					break;

			}

			match(input, Token.UP, null); if (state.failed) return retval;
			adaptor.addChild(root_0, root_1);
			_last = _save_last_1;
			}


			if ( state.backtracking==0 ) {
			            retval.value = new CopyTo((namedTable421!=null?((StatementBuilder.namedTable_return)namedTable421).value:null),
			                                (columnList422!=null?((StatementBuilder.columnList_return)columnList422).value:null),
			                                (whereClause423!=null?((StatementBuilder.whereClause_return)whereClause423).value:null),
			                                (d!=null?((StatementBuilder.copyToTargetSpec_return)d).value:false),
			                                (expr424!=null?((StatementBuilder.expr_return)expr424).value:null),
			                                (genericProperties425!=null?((StatementBuilder.genericProperties_return)genericProperties425).value:null));
			        }
			if ( state.backtracking==0 ) {
			}

			}

			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "copyTo"


	public static class copyFrom_return extends TreeRuleReturnScope {
		public Statement value;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "copyFrom"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:842:1: copyFrom returns [Statement value] : ^( COPY_FROM namedTable path= expr ( genericProperties )? ) ;
	public final StatementBuilder.copyFrom_return copyFrom() throws RecognitionException {
		StatementBuilder.copyFrom_return retval = new StatementBuilder.copyFrom_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree COPY_FROM426=null;
		TreeRuleReturnScope path =null;
		TreeRuleReturnScope namedTable427 =null;
		TreeRuleReturnScope genericProperties428 =null;

		CommonTree COPY_FROM426_tree=null;

		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:843:5: ( ^( COPY_FROM namedTable path= expr ( genericProperties )? ) )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:843:7: ^( COPY_FROM namedTable path= expr ( genericProperties )? )
			{
			root_0 = (CommonTree)adaptor.nil();


			_last = (CommonTree)input.LT(1);
			{
			CommonTree _save_last_1 = _last;
			CommonTree _first_1 = null;
			CommonTree root_1 = (CommonTree)adaptor.nil();
			_last = (CommonTree)input.LT(1);
			COPY_FROM426=(CommonTree)match(input,COPY_FROM,FOLLOW_COPY_FROM_in_copyFrom7632); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			COPY_FROM426_tree = (CommonTree)adaptor.dupNode(COPY_FROM426);


			root_1 = (CommonTree)adaptor.becomeRoot(COPY_FROM426_tree, root_1);
			}

			match(input, Token.DOWN, null); if (state.failed) return retval;
			_last = (CommonTree)input.LT(1);
			pushFollow(FOLLOW_namedTable_in_copyFrom7634);
			namedTable427=namedTable();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) 
			adaptor.addChild(root_1, namedTable427.getTree());

			_last = (CommonTree)input.LT(1);
			pushFollow(FOLLOW_expr_in_copyFrom7638);
			path=expr();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) 
			adaptor.addChild(root_1, path.getTree());

			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:843:40: ( genericProperties )?
			int alt109=2;
			int LA109_0 = input.LA(1);
			if ( (LA109_0==GENERIC_PROPERTIES) ) {
				alt109=1;
			}
			switch (alt109) {
				case 1 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:843:40: genericProperties
					{
					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_genericProperties_in_copyFrom7640);
					genericProperties428=genericProperties();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, genericProperties428.getTree());

					if ( state.backtracking==0 ) {
					}

					}
					break;

			}

			match(input, Token.UP, null); if (state.failed) return retval;
			adaptor.addChild(root_0, root_1);
			_last = _save_last_1;
			}


			if ( state.backtracking==0 ) {
			            retval.value = new CopyFrom((namedTable427!=null?((StatementBuilder.namedTable_return)namedTable427).value:null),
			                                  (path!=null?((StatementBuilder.expr_return)path).value:null),
			                                  (genericProperties428!=null?((StatementBuilder.genericProperties_return)genericProperties428).value:null));
			        }
			if ( state.backtracking==0 ) {
			}

			}

			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "copyFrom"


	public static class createBlobTable_return extends TreeRuleReturnScope {
		public Statement value;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "createBlobTable"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:851:1: createBlobTable returns [Statement value] : ^( CREATE_BLOB_TABLE namedTable ( clusteredBy )? ( genericProperties )? ) ;
	public final StatementBuilder.createBlobTable_return createBlobTable() throws RecognitionException {
		StatementBuilder.createBlobTable_return retval = new StatementBuilder.createBlobTable_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree CREATE_BLOB_TABLE429=null;
		TreeRuleReturnScope namedTable430 =null;
		TreeRuleReturnScope clusteredBy431 =null;
		TreeRuleReturnScope genericProperties432 =null;

		CommonTree CREATE_BLOB_TABLE429_tree=null;

		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:852:5: ( ^( CREATE_BLOB_TABLE namedTable ( clusteredBy )? ( genericProperties )? ) )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:852:7: ^( CREATE_BLOB_TABLE namedTable ( clusteredBy )? ( genericProperties )? )
			{
			root_0 = (CommonTree)adaptor.nil();


			_last = (CommonTree)input.LT(1);
			{
			CommonTree _save_last_1 = _last;
			CommonTree _first_1 = null;
			CommonTree root_1 = (CommonTree)adaptor.nil();
			_last = (CommonTree)input.LT(1);
			CREATE_BLOB_TABLE429=(CommonTree)match(input,CREATE_BLOB_TABLE,FOLLOW_CREATE_BLOB_TABLE_in_createBlobTable7674); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			CREATE_BLOB_TABLE429_tree = (CommonTree)adaptor.dupNode(CREATE_BLOB_TABLE429);


			root_1 = (CommonTree)adaptor.becomeRoot(CREATE_BLOB_TABLE429_tree, root_1);
			}

			match(input, Token.DOWN, null); if (state.failed) return retval;
			_last = (CommonTree)input.LT(1);
			pushFollow(FOLLOW_namedTable_in_createBlobTable7676);
			namedTable430=namedTable();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) 
			adaptor.addChild(root_1, namedTable430.getTree());

			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:852:38: ( clusteredBy )?
			int alt110=2;
			int LA110_0 = input.LA(1);
			if ( (LA110_0==CLUSTERED) ) {
				alt110=1;
			}
			switch (alt110) {
				case 1 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:852:38: clusteredBy
					{
					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_clusteredBy_in_createBlobTable7678);
					clusteredBy431=clusteredBy();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, clusteredBy431.getTree());

					if ( state.backtracking==0 ) {
					}

					}
					break;

			}

			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:852:51: ( genericProperties )?
			int alt111=2;
			int LA111_0 = input.LA(1);
			if ( (LA111_0==GENERIC_PROPERTIES) ) {
				alt111=1;
			}
			switch (alt111) {
				case 1 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:852:51: genericProperties
					{
					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_genericProperties_in_createBlobTable7681);
					genericProperties432=genericProperties();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, genericProperties432.getTree());

					if ( state.backtracking==0 ) {
					}

					}
					break;

			}

			match(input, Token.UP, null); if (state.failed) return retval;
			adaptor.addChild(root_0, root_1);
			_last = _save_last_1;
			}


			if ( state.backtracking==0 ) {
			            retval.value = new CreateBlobTable((namedTable430!=null?((StatementBuilder.namedTable_return)namedTable430).value:null),
			                                         (clusteredBy431!=null?((StatementBuilder.clusteredBy_return)clusteredBy431).value:null),
			                                         (genericProperties432!=null?((StatementBuilder.genericProperties_return)genericProperties432).value:null));
			        }
			if ( state.backtracking==0 ) {
			}

			}

			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "createBlobTable"


	public static class alterBlobTable_return extends TreeRuleReturnScope {
		public Statement value;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "alterBlobTable"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:860:1: alterBlobTable returns [Statement value] : ( ^( ALTER_BLOB_TABLE genericProperties namedTable ) | ^( ALTER_BLOB_TABLE columnIdentList namedTable ) );
	public final StatementBuilder.alterBlobTable_return alterBlobTable() throws RecognitionException {
		StatementBuilder.alterBlobTable_return retval = new StatementBuilder.alterBlobTable_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree ALTER_BLOB_TABLE433=null;
		CommonTree ALTER_BLOB_TABLE436=null;
		TreeRuleReturnScope genericProperties434 =null;
		TreeRuleReturnScope namedTable435 =null;
		TreeRuleReturnScope columnIdentList437 =null;
		TreeRuleReturnScope namedTable438 =null;

		CommonTree ALTER_BLOB_TABLE433_tree=null;
		CommonTree ALTER_BLOB_TABLE436_tree=null;

		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:861:5: ( ^( ALTER_BLOB_TABLE genericProperties namedTable ) | ^( ALTER_BLOB_TABLE columnIdentList namedTable ) )
			int alt112=2;
			int LA112_0 = input.LA(1);
			if ( (LA112_0==ALTER_BLOB_TABLE) ) {
				int LA112_1 = input.LA(2);
				if ( (LA112_1==DOWN) ) {
					int LA112_2 = input.LA(3);
					if ( (LA112_2==GENERIC_PROPERTIES) ) {
						alt112=1;
					}
					else if ( (LA112_2==IDENT_LIST) ) {
						alt112=2;
					}

					else {
						if (state.backtracking>0) {state.failed=true; return retval;}
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 112, 2, input);
							throw nvae;
						} finally {
							input.rewind(nvaeMark);
						}
					}

				}

				else {
					if (state.backtracking>0) {state.failed=true; return retval;}
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 112, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

			}

			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 112, 0, input);
				throw nvae;
			}

			switch (alt112) {
				case 1 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:861:7: ^( ALTER_BLOB_TABLE genericProperties namedTable )
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					{
					CommonTree _save_last_1 = _last;
					CommonTree _first_1 = null;
					CommonTree root_1 = (CommonTree)adaptor.nil();
					_last = (CommonTree)input.LT(1);
					ALTER_BLOB_TABLE433=(CommonTree)match(input,ALTER_BLOB_TABLE,FOLLOW_ALTER_BLOB_TABLE_in_alterBlobTable7715); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					ALTER_BLOB_TABLE433_tree = (CommonTree)adaptor.dupNode(ALTER_BLOB_TABLE433);


					root_1 = (CommonTree)adaptor.becomeRoot(ALTER_BLOB_TABLE433_tree, root_1);
					}

					match(input, Token.DOWN, null); if (state.failed) return retval;
					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_genericProperties_in_alterBlobTable7717);
					genericProperties434=genericProperties();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, genericProperties434.getTree());

					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_namedTable_in_alterBlobTable7719);
					namedTable435=namedTable();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, namedTable435.getTree());

					match(input, Token.UP, null); if (state.failed) return retval;
					adaptor.addChild(root_0, root_1);
					_last = _save_last_1;
					}


					if ( state.backtracking==0 ) {
					            retval.value = new AlterBlobTable((namedTable435!=null?((StatementBuilder.namedTable_return)namedTable435).value:null), (genericProperties434!=null?((StatementBuilder.genericProperties_return)genericProperties434).value:null));
					        }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 2 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:865:7: ^( ALTER_BLOB_TABLE columnIdentList namedTable )
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					{
					CommonTree _save_last_1 = _last;
					CommonTree _first_1 = null;
					CommonTree root_1 = (CommonTree)adaptor.nil();
					_last = (CommonTree)input.LT(1);
					ALTER_BLOB_TABLE436=(CommonTree)match(input,ALTER_BLOB_TABLE,FOLLOW_ALTER_BLOB_TABLE_in_alterBlobTable7739); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					ALTER_BLOB_TABLE436_tree = (CommonTree)adaptor.dupNode(ALTER_BLOB_TABLE436);


					root_1 = (CommonTree)adaptor.becomeRoot(ALTER_BLOB_TABLE436_tree, root_1);
					}

					match(input, Token.DOWN, null); if (state.failed) return retval;
					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_columnIdentList_in_alterBlobTable7741);
					columnIdentList437=columnIdentList();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, columnIdentList437.getTree());

					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_namedTable_in_alterBlobTable7743);
					namedTable438=namedTable();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, namedTable438.getTree());

					match(input, Token.UP, null); if (state.failed) return retval;
					adaptor.addChild(root_0, root_1);
					_last = _save_last_1;
					}


					if ( state.backtracking==0 ) {
					            retval.value = new AlterBlobTable((namedTable438!=null?((StatementBuilder.namedTable_return)namedTable438).value:null), (columnIdentList437!=null?((StatementBuilder.columnIdentList_return)columnIdentList437).value:null));
					        }
					if ( state.backtracking==0 ) {
					}

					}
					break;

			}
			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "alterBlobTable"


	public static class alterTable_return extends TreeRuleReturnScope {
		public Statement value;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "alterTable"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:871:1: alterTable returns [Statement value] : ( ^( ALTER_TABLE genericProperties namedTable ) | ^( ALTER_TABLE columnIdentList namedTable ) | ^( ADD_COLUMN namedTable addColumnDefinition ) );
	public final StatementBuilder.alterTable_return alterTable() throws RecognitionException {
		StatementBuilder.alterTable_return retval = new StatementBuilder.alterTable_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree ALTER_TABLE439=null;
		CommonTree ALTER_TABLE442=null;
		CommonTree ADD_COLUMN445=null;
		TreeRuleReturnScope genericProperties440 =null;
		TreeRuleReturnScope namedTable441 =null;
		TreeRuleReturnScope columnIdentList443 =null;
		TreeRuleReturnScope namedTable444 =null;
		TreeRuleReturnScope namedTable446 =null;
		TreeRuleReturnScope addColumnDefinition447 =null;

		CommonTree ALTER_TABLE439_tree=null;
		CommonTree ALTER_TABLE442_tree=null;
		CommonTree ADD_COLUMN445_tree=null;

		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:872:5: ( ^( ALTER_TABLE genericProperties namedTable ) | ^( ALTER_TABLE columnIdentList namedTable ) | ^( ADD_COLUMN namedTable addColumnDefinition ) )
			int alt113=3;
			int LA113_0 = input.LA(1);
			if ( (LA113_0==ALTER_TABLE) ) {
				int LA113_1 = input.LA(2);
				if ( (LA113_1==DOWN) ) {
					int LA113_3 = input.LA(3);
					if ( (LA113_3==GENERIC_PROPERTIES) ) {
						alt113=1;
					}
					else if ( (LA113_3==IDENT_LIST) ) {
						alt113=2;
					}

					else {
						if (state.backtracking>0) {state.failed=true; return retval;}
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 113, 3, input);
							throw nvae;
						} finally {
							input.rewind(nvaeMark);
						}
					}

				}

				else {
					if (state.backtracking>0) {state.failed=true; return retval;}
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 113, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

			}
			else if ( (LA113_0==ADD_COLUMN) ) {
				alt113=3;
			}

			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 113, 0, input);
				throw nvae;
			}

			switch (alt113) {
				case 1 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:872:7: ^( ALTER_TABLE genericProperties namedTable )
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					{
					CommonTree _save_last_1 = _last;
					CommonTree _first_1 = null;
					CommonTree root_1 = (CommonTree)adaptor.nil();
					_last = (CommonTree)input.LT(1);
					ALTER_TABLE439=(CommonTree)match(input,ALTER_TABLE,FOLLOW_ALTER_TABLE_in_alterTable7776); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					ALTER_TABLE439_tree = (CommonTree)adaptor.dupNode(ALTER_TABLE439);


					root_1 = (CommonTree)adaptor.becomeRoot(ALTER_TABLE439_tree, root_1);
					}

					match(input, Token.DOWN, null); if (state.failed) return retval;
					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_genericProperties_in_alterTable7778);
					genericProperties440=genericProperties();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, genericProperties440.getTree());

					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_namedTable_in_alterTable7780);
					namedTable441=namedTable();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, namedTable441.getTree());

					match(input, Token.UP, null); if (state.failed) return retval;
					adaptor.addChild(root_0, root_1);
					_last = _save_last_1;
					}


					if ( state.backtracking==0 ) {
					            retval.value = new AlterTable((namedTable441!=null?((StatementBuilder.namedTable_return)namedTable441).value:null), (genericProperties440!=null?((StatementBuilder.genericProperties_return)genericProperties440).value:null));
					        }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 2 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:876:7: ^( ALTER_TABLE columnIdentList namedTable )
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					{
					CommonTree _save_last_1 = _last;
					CommonTree _first_1 = null;
					CommonTree root_1 = (CommonTree)adaptor.nil();
					_last = (CommonTree)input.LT(1);
					ALTER_TABLE442=(CommonTree)match(input,ALTER_TABLE,FOLLOW_ALTER_TABLE_in_alterTable7800); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					ALTER_TABLE442_tree = (CommonTree)adaptor.dupNode(ALTER_TABLE442);


					root_1 = (CommonTree)adaptor.becomeRoot(ALTER_TABLE442_tree, root_1);
					}

					match(input, Token.DOWN, null); if (state.failed) return retval;
					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_columnIdentList_in_alterTable7802);
					columnIdentList443=columnIdentList();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, columnIdentList443.getTree());

					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_namedTable_in_alterTable7804);
					namedTable444=namedTable();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, namedTable444.getTree());

					match(input, Token.UP, null); if (state.failed) return retval;
					adaptor.addChild(root_0, root_1);
					_last = _save_last_1;
					}


					if ( state.backtracking==0 ) {
					            retval.value = new AlterTable((namedTable444!=null?((StatementBuilder.namedTable_return)namedTable444).value:null), (columnIdentList443!=null?((StatementBuilder.columnIdentList_return)columnIdentList443).value:null));
					        }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 3 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:880:7: ^( ADD_COLUMN namedTable addColumnDefinition )
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					{
					CommonTree _save_last_1 = _last;
					CommonTree _first_1 = null;
					CommonTree root_1 = (CommonTree)adaptor.nil();
					_last = (CommonTree)input.LT(1);
					ADD_COLUMN445=(CommonTree)match(input,ADD_COLUMN,FOLLOW_ADD_COLUMN_in_alterTable7824); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					ADD_COLUMN445_tree = (CommonTree)adaptor.dupNode(ADD_COLUMN445);


					root_1 = (CommonTree)adaptor.becomeRoot(ADD_COLUMN445_tree, root_1);
					}

					match(input, Token.DOWN, null); if (state.failed) return retval;
					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_namedTable_in_alterTable7826);
					namedTable446=namedTable();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, namedTable446.getTree());

					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_addColumnDefinition_in_alterTable7828);
					addColumnDefinition447=addColumnDefinition();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, addColumnDefinition447.getTree());

					match(input, Token.UP, null); if (state.failed) return retval;
					adaptor.addChild(root_0, root_1);
					_last = _save_last_1;
					}


					if ( state.backtracking==0 ) {
					            retval.value = new AlterTableAddColumn((namedTable446!=null?((StatementBuilder.namedTable_return)namedTable446).value:null), (addColumnDefinition447!=null?((StatementBuilder.addColumnDefinition_return)addColumnDefinition447).value:null));
					        }
					if ( state.backtracking==0 ) {
					}

					}
					break;

			}
			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "alterTable"


	public static class createTable_return extends TreeRuleReturnScope {
		public Statement value;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "createTable"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:887:1: createTable returns [Statement value] : ( ^( CREATE_TABLE namedTable tableElementList crateTableOptionList ( genericProperties )? ) | ^( CREATE_TABLE EXISTS namedTable tableElementList crateTableOptionList ( genericProperties )? ) );
	public final StatementBuilder.createTable_return createTable() throws RecognitionException {
		StatementBuilder.createTable_return retval = new StatementBuilder.createTable_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree CREATE_TABLE448=null;
		CommonTree CREATE_TABLE453=null;
		CommonTree EXISTS454=null;
		TreeRuleReturnScope namedTable449 =null;
		TreeRuleReturnScope tableElementList450 =null;
		TreeRuleReturnScope crateTableOptionList451 =null;
		TreeRuleReturnScope genericProperties452 =null;
		TreeRuleReturnScope namedTable455 =null;
		TreeRuleReturnScope tableElementList456 =null;
		TreeRuleReturnScope crateTableOptionList457 =null;
		TreeRuleReturnScope genericProperties458 =null;

		CommonTree CREATE_TABLE448_tree=null;
		CommonTree CREATE_TABLE453_tree=null;
		CommonTree EXISTS454_tree=null;

		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:888:5: ( ^( CREATE_TABLE namedTable tableElementList crateTableOptionList ( genericProperties )? ) | ^( CREATE_TABLE EXISTS namedTable tableElementList crateTableOptionList ( genericProperties )? ) )
			int alt116=2;
			int LA116_0 = input.LA(1);
			if ( (LA116_0==CREATE_TABLE) ) {
				int LA116_1 = input.LA(2);
				if ( (LA116_1==DOWN) ) {
					int LA116_2 = input.LA(3);
					if ( (LA116_2==EXISTS) ) {
						alt116=2;
					}
					else if ( (LA116_2==TABLE) ) {
						alt116=1;
					}

					else {
						if (state.backtracking>0) {state.failed=true; return retval;}
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 116, 2, input);
							throw nvae;
						} finally {
							input.rewind(nvaeMark);
						}
					}

				}

				else {
					if (state.backtracking>0) {state.failed=true; return retval;}
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 116, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

			}

			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 116, 0, input);
				throw nvae;
			}

			switch (alt116) {
				case 1 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:888:7: ^( CREATE_TABLE namedTable tableElementList crateTableOptionList ( genericProperties )? )
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					{
					CommonTree _save_last_1 = _last;
					CommonTree _first_1 = null;
					CommonTree root_1 = (CommonTree)adaptor.nil();
					_last = (CommonTree)input.LT(1);
					CREATE_TABLE448=(CommonTree)match(input,CREATE_TABLE,FOLLOW_CREATE_TABLE_in_createTable7862); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					CREATE_TABLE448_tree = (CommonTree)adaptor.dupNode(CREATE_TABLE448);


					root_1 = (CommonTree)adaptor.becomeRoot(CREATE_TABLE448_tree, root_1);
					}

					match(input, Token.DOWN, null); if (state.failed) return retval;
					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_namedTable_in_createTable7864);
					namedTable449=namedTable();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, namedTable449.getTree());

					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_tableElementList_in_createTable7866);
					tableElementList450=tableElementList();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, tableElementList450.getTree());

					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_crateTableOptionList_in_createTable7868);
					crateTableOptionList451=crateTableOptionList();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, crateTableOptionList451.getTree());

					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:888:71: ( genericProperties )?
					int alt114=2;
					int LA114_0 = input.LA(1);
					if ( (LA114_0==GENERIC_PROPERTIES) ) {
						alt114=1;
					}
					switch (alt114) {
						case 1 :
							// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:888:71: genericProperties
							{
							_last = (CommonTree)input.LT(1);
							pushFollow(FOLLOW_genericProperties_in_createTable7870);
							genericProperties452=genericProperties();
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) 
							adaptor.addChild(root_1, genericProperties452.getTree());

							if ( state.backtracking==0 ) {
							}

							}
							break;

					}

					match(input, Token.UP, null); if (state.failed) return retval;
					adaptor.addChild(root_0, root_1);
					_last = _save_last_1;
					}


					if ( state.backtracking==0 ) {
					            retval.value = new CreateTable((namedTable449!=null?((StatementBuilder.namedTable_return)namedTable449).value:null),
					                                     (tableElementList450!=null?((StatementBuilder.tableElementList_return)tableElementList450).value:null),
					                                     (crateTableOptionList451!=null?((StatementBuilder.crateTableOptionList_return)crateTableOptionList451).value:null),
					                                     (genericProperties452!=null?((StatementBuilder.genericProperties_return)genericProperties452).value:null),
					                                     false);
					        }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 2 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:896:7: ^( CREATE_TABLE EXISTS namedTable tableElementList crateTableOptionList ( genericProperties )? )
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					{
					CommonTree _save_last_1 = _last;
					CommonTree _first_1 = null;
					CommonTree root_1 = (CommonTree)adaptor.nil();
					_last = (CommonTree)input.LT(1);
					CREATE_TABLE453=(CommonTree)match(input,CREATE_TABLE,FOLLOW_CREATE_TABLE_in_createTable7891); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					CREATE_TABLE453_tree = (CommonTree)adaptor.dupNode(CREATE_TABLE453);


					root_1 = (CommonTree)adaptor.becomeRoot(CREATE_TABLE453_tree, root_1);
					}

					match(input, Token.DOWN, null); if (state.failed) return retval;
					_last = (CommonTree)input.LT(1);
					EXISTS454=(CommonTree)match(input,EXISTS,FOLLOW_EXISTS_in_createTable7893); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					EXISTS454_tree = (CommonTree)adaptor.dupNode(EXISTS454);


					adaptor.addChild(root_1, EXISTS454_tree);
					}

					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_namedTable_in_createTable7895);
					namedTable455=namedTable();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, namedTable455.getTree());

					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_tableElementList_in_createTable7897);
					tableElementList456=tableElementList();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, tableElementList456.getTree());

					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_crateTableOptionList_in_createTable7899);
					crateTableOptionList457=crateTableOptionList();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, crateTableOptionList457.getTree());

					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:896:78: ( genericProperties )?
					int alt115=2;
					int LA115_0 = input.LA(1);
					if ( (LA115_0==GENERIC_PROPERTIES) ) {
						alt115=1;
					}
					switch (alt115) {
						case 1 :
							// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:896:78: genericProperties
							{
							_last = (CommonTree)input.LT(1);
							pushFollow(FOLLOW_genericProperties_in_createTable7901);
							genericProperties458=genericProperties();
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) 
							adaptor.addChild(root_1, genericProperties458.getTree());

							if ( state.backtracking==0 ) {
							}

							}
							break;

					}

					match(input, Token.UP, null); if (state.failed) return retval;
					adaptor.addChild(root_0, root_1);
					_last = _save_last_1;
					}


					if ( state.backtracking==0 ) {
					            retval.value = new CreateTable((namedTable455!=null?((StatementBuilder.namedTable_return)namedTable455).value:null),
					                                     (tableElementList456!=null?((StatementBuilder.tableElementList_return)tableElementList456).value:null),
					                                     (crateTableOptionList457!=null?((StatementBuilder.crateTableOptionList_return)crateTableOptionList457).value:null),
					                                     (genericProperties458!=null?((StatementBuilder.genericProperties_return)genericProperties458).value:null),
					                                     true);
					        }
					if ( state.backtracking==0 ) {
					}

					}
					break;

			}
			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "createTable"


	public static class createRepository_return extends TreeRuleReturnScope {
		public Statement value;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "createRepository"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:906:1: createRepository returns [Statement value] : ^( CREATE_REPOSITORY repository ident ( genericProperties )? ) ;
	public final StatementBuilder.createRepository_return createRepository() throws RecognitionException {
		StatementBuilder.createRepository_return retval = new StatementBuilder.createRepository_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree CREATE_REPOSITORY459=null;
		TreeRuleReturnScope repository460 =null;
		TreeRuleReturnScope ident461 =null;
		TreeRuleReturnScope genericProperties462 =null;

		CommonTree CREATE_REPOSITORY459_tree=null;

		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:907:5: ( ^( CREATE_REPOSITORY repository ident ( genericProperties )? ) )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:907:7: ^( CREATE_REPOSITORY repository ident ( genericProperties )? )
			{
			root_0 = (CommonTree)adaptor.nil();


			_last = (CommonTree)input.LT(1);
			{
			CommonTree _save_last_1 = _last;
			CommonTree _first_1 = null;
			CommonTree root_1 = (CommonTree)adaptor.nil();
			_last = (CommonTree)input.LT(1);
			CREATE_REPOSITORY459=(CommonTree)match(input,CREATE_REPOSITORY,FOLLOW_CREATE_REPOSITORY_in_createRepository7935); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			CREATE_REPOSITORY459_tree = (CommonTree)adaptor.dupNode(CREATE_REPOSITORY459);


			root_1 = (CommonTree)adaptor.becomeRoot(CREATE_REPOSITORY459_tree, root_1);
			}

			match(input, Token.DOWN, null); if (state.failed) return retval;
			_last = (CommonTree)input.LT(1);
			pushFollow(FOLLOW_repository_in_createRepository7937);
			repository460=repository();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) 
			adaptor.addChild(root_1, repository460.getTree());

			_last = (CommonTree)input.LT(1);
			pushFollow(FOLLOW_ident_in_createRepository7939);
			ident461=ident();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) 
			adaptor.addChild(root_1, ident461.getTree());

			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:907:44: ( genericProperties )?
			int alt117=2;
			int LA117_0 = input.LA(1);
			if ( (LA117_0==GENERIC_PROPERTIES) ) {
				alt117=1;
			}
			switch (alt117) {
				case 1 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:907:44: genericProperties
					{
					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_genericProperties_in_createRepository7941);
					genericProperties462=genericProperties();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, genericProperties462.getTree());

					if ( state.backtracking==0 ) {
					}

					}
					break;

			}

			match(input, Token.UP, null); if (state.failed) return retval;
			adaptor.addChild(root_0, root_1);
			_last = _save_last_1;
			}


			if ( state.backtracking==0 ) {
			            retval.value = new CreateRepository((repository460!=null?((StatementBuilder.repository_return)repository460).value:null),
			                                          (ident461!=null?((StatementBuilder.ident_return)ident461).value:null),
			                                          (genericProperties462!=null?((StatementBuilder.genericProperties_return)genericProperties462).value:null));
			        }
			if ( state.backtracking==0 ) {
			}

			}

			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "createRepository"


	public static class createSnapshot_return extends TreeRuleReturnScope {
		public Statement value;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "createSnapshot"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:915:1: createSnapshot returns [Statement value] : ( ^( CREATE_SNAPSHOT qname ALL ( genericProperties )? ) | ^( CREATE_SNAPSHOT qname tableWithPartitionList ( genericProperties )? ) );
	public final StatementBuilder.createSnapshot_return createSnapshot() throws RecognitionException {
		StatementBuilder.createSnapshot_return retval = new StatementBuilder.createSnapshot_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree CREATE_SNAPSHOT463=null;
		CommonTree ALL465=null;
		CommonTree CREATE_SNAPSHOT467=null;
		TreeRuleReturnScope qname464 =null;
		TreeRuleReturnScope genericProperties466 =null;
		TreeRuleReturnScope qname468 =null;
		TreeRuleReturnScope tableWithPartitionList469 =null;
		TreeRuleReturnScope genericProperties470 =null;

		CommonTree CREATE_SNAPSHOT463_tree=null;
		CommonTree ALL465_tree=null;
		CommonTree CREATE_SNAPSHOT467_tree=null;

		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:916:5: ( ^( CREATE_SNAPSHOT qname ALL ( genericProperties )? ) | ^( CREATE_SNAPSHOT qname tableWithPartitionList ( genericProperties )? ) )
			int alt120=2;
			alt120 = dfa120.predict(input);
			switch (alt120) {
				case 1 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:916:7: ^( CREATE_SNAPSHOT qname ALL ( genericProperties )? )
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					{
					CommonTree _save_last_1 = _last;
					CommonTree _first_1 = null;
					CommonTree root_1 = (CommonTree)adaptor.nil();
					_last = (CommonTree)input.LT(1);
					CREATE_SNAPSHOT463=(CommonTree)match(input,CREATE_SNAPSHOT,FOLLOW_CREATE_SNAPSHOT_in_createSnapshot7975); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					CREATE_SNAPSHOT463_tree = (CommonTree)adaptor.dupNode(CREATE_SNAPSHOT463);


					root_1 = (CommonTree)adaptor.becomeRoot(CREATE_SNAPSHOT463_tree, root_1);
					}

					match(input, Token.DOWN, null); if (state.failed) return retval;
					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_qname_in_createSnapshot7977);
					qname464=qname();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, qname464.getTree());

					_last = (CommonTree)input.LT(1);
					ALL465=(CommonTree)match(input,ALL,FOLLOW_ALL_in_createSnapshot7979); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					ALL465_tree = (CommonTree)adaptor.dupNode(ALL465);


					adaptor.addChild(root_1, ALL465_tree);
					}

					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:916:35: ( genericProperties )?
					int alt118=2;
					int LA118_0 = input.LA(1);
					if ( (LA118_0==GENERIC_PROPERTIES) ) {
						alt118=1;
					}
					switch (alt118) {
						case 1 :
							// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:916:35: genericProperties
							{
							_last = (CommonTree)input.LT(1);
							pushFollow(FOLLOW_genericProperties_in_createSnapshot7981);
							genericProperties466=genericProperties();
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) 
							adaptor.addChild(root_1, genericProperties466.getTree());

							if ( state.backtracking==0 ) {
							}

							}
							break;

					}

					match(input, Token.UP, null); if (state.failed) return retval;
					adaptor.addChild(root_0, root_1);
					_last = _save_last_1;
					}


					if ( state.backtracking==0 ) {
					            retval.value = new CreateSnapshot((qname464!=null?((StatementBuilder.qname_return)qname464).value:null),
					                                        (genericProperties466!=null?((StatementBuilder.genericProperties_return)genericProperties466).value:null));
					        }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 2 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:921:7: ^( CREATE_SNAPSHOT qname tableWithPartitionList ( genericProperties )? )
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					{
					CommonTree _save_last_1 = _last;
					CommonTree _first_1 = null;
					CommonTree root_1 = (CommonTree)adaptor.nil();
					_last = (CommonTree)input.LT(1);
					CREATE_SNAPSHOT467=(CommonTree)match(input,CREATE_SNAPSHOT,FOLLOW_CREATE_SNAPSHOT_in_createSnapshot8002); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					CREATE_SNAPSHOT467_tree = (CommonTree)adaptor.dupNode(CREATE_SNAPSHOT467);


					root_1 = (CommonTree)adaptor.becomeRoot(CREATE_SNAPSHOT467_tree, root_1);
					}

					match(input, Token.DOWN, null); if (state.failed) return retval;
					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_qname_in_createSnapshot8004);
					qname468=qname();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, qname468.getTree());

					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_tableWithPartitionList_in_createSnapshot8006);
					tableWithPartitionList469=tableWithPartitionList();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, tableWithPartitionList469.getTree());

					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:921:54: ( genericProperties )?
					int alt119=2;
					int LA119_0 = input.LA(1);
					if ( (LA119_0==GENERIC_PROPERTIES) ) {
						alt119=1;
					}
					switch (alt119) {
						case 1 :
							// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:921:54: genericProperties
							{
							_last = (CommonTree)input.LT(1);
							pushFollow(FOLLOW_genericProperties_in_createSnapshot8008);
							genericProperties470=genericProperties();
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) 
							adaptor.addChild(root_1, genericProperties470.getTree());

							if ( state.backtracking==0 ) {
							}

							}
							break;

					}

					match(input, Token.UP, null); if (state.failed) return retval;
					adaptor.addChild(root_0, root_1);
					_last = _save_last_1;
					}


					if ( state.backtracking==0 ) {
					            retval.value = new CreateSnapshot((qname468!=null?((StatementBuilder.qname_return)qname468).value:null),
					                                        (tableWithPartitionList469!=null?((StatementBuilder.tableWithPartitionList_return)tableWithPartitionList469).value:null),
					                                        (genericProperties470!=null?((StatementBuilder.genericProperties_return)genericProperties470).value:null));
					        }
					if ( state.backtracking==0 ) {
					}

					}
					break;

			}
			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "createSnapshot"


	public static class restoreSnapshot_return extends TreeRuleReturnScope {
		public Statement value;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "restoreSnapshot"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:929:1: restoreSnapshot returns [Statement value] : ( ^( RESTORE_SNAPSHOT qname ALL ( genericProperties )? ) | ^( RESTORE_SNAPSHOT qname tableWithPartitionList ( genericProperties )? ) );
	public final StatementBuilder.restoreSnapshot_return restoreSnapshot() throws RecognitionException {
		StatementBuilder.restoreSnapshot_return retval = new StatementBuilder.restoreSnapshot_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree RESTORE_SNAPSHOT471=null;
		CommonTree ALL473=null;
		CommonTree RESTORE_SNAPSHOT475=null;
		TreeRuleReturnScope qname472 =null;
		TreeRuleReturnScope genericProperties474 =null;
		TreeRuleReturnScope qname476 =null;
		TreeRuleReturnScope tableWithPartitionList477 =null;
		TreeRuleReturnScope genericProperties478 =null;

		CommonTree RESTORE_SNAPSHOT471_tree=null;
		CommonTree ALL473_tree=null;
		CommonTree RESTORE_SNAPSHOT475_tree=null;

		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:930:5: ( ^( RESTORE_SNAPSHOT qname ALL ( genericProperties )? ) | ^( RESTORE_SNAPSHOT qname tableWithPartitionList ( genericProperties )? ) )
			int alt123=2;
			alt123 = dfa123.predict(input);
			switch (alt123) {
				case 1 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:930:7: ^( RESTORE_SNAPSHOT qname ALL ( genericProperties )? )
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					{
					CommonTree _save_last_1 = _last;
					CommonTree _first_1 = null;
					CommonTree root_1 = (CommonTree)adaptor.nil();
					_last = (CommonTree)input.LT(1);
					RESTORE_SNAPSHOT471=(CommonTree)match(input,RESTORE_SNAPSHOT,FOLLOW_RESTORE_SNAPSHOT_in_restoreSnapshot8042); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					RESTORE_SNAPSHOT471_tree = (CommonTree)adaptor.dupNode(RESTORE_SNAPSHOT471);


					root_1 = (CommonTree)adaptor.becomeRoot(RESTORE_SNAPSHOT471_tree, root_1);
					}

					match(input, Token.DOWN, null); if (state.failed) return retval;
					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_qname_in_restoreSnapshot8044);
					qname472=qname();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, qname472.getTree());

					_last = (CommonTree)input.LT(1);
					ALL473=(CommonTree)match(input,ALL,FOLLOW_ALL_in_restoreSnapshot8046); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					ALL473_tree = (CommonTree)adaptor.dupNode(ALL473);


					adaptor.addChild(root_1, ALL473_tree);
					}

					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:930:36: ( genericProperties )?
					int alt121=2;
					int LA121_0 = input.LA(1);
					if ( (LA121_0==GENERIC_PROPERTIES) ) {
						alt121=1;
					}
					switch (alt121) {
						case 1 :
							// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:930:36: genericProperties
							{
							_last = (CommonTree)input.LT(1);
							pushFollow(FOLLOW_genericProperties_in_restoreSnapshot8048);
							genericProperties474=genericProperties();
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) 
							adaptor.addChild(root_1, genericProperties474.getTree());

							if ( state.backtracking==0 ) {
							}

							}
							break;

					}

					match(input, Token.UP, null); if (state.failed) return retval;
					adaptor.addChild(root_0, root_1);
					_last = _save_last_1;
					}


					if ( state.backtracking==0 ) {
					            retval.value = new RestoreSnapshot((qname472!=null?((StatementBuilder.qname_return)qname472).value:null),
					                                        (genericProperties474!=null?((StatementBuilder.genericProperties_return)genericProperties474).value:null));
					        }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 2 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:935:7: ^( RESTORE_SNAPSHOT qname tableWithPartitionList ( genericProperties )? )
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					{
					CommonTree _save_last_1 = _last;
					CommonTree _first_1 = null;
					CommonTree root_1 = (CommonTree)adaptor.nil();
					_last = (CommonTree)input.LT(1);
					RESTORE_SNAPSHOT475=(CommonTree)match(input,RESTORE_SNAPSHOT,FOLLOW_RESTORE_SNAPSHOT_in_restoreSnapshot8069); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					RESTORE_SNAPSHOT475_tree = (CommonTree)adaptor.dupNode(RESTORE_SNAPSHOT475);


					root_1 = (CommonTree)adaptor.becomeRoot(RESTORE_SNAPSHOT475_tree, root_1);
					}

					match(input, Token.DOWN, null); if (state.failed) return retval;
					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_qname_in_restoreSnapshot8071);
					qname476=qname();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, qname476.getTree());

					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_tableWithPartitionList_in_restoreSnapshot8073);
					tableWithPartitionList477=tableWithPartitionList();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, tableWithPartitionList477.getTree());

					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:935:55: ( genericProperties )?
					int alt122=2;
					int LA122_0 = input.LA(1);
					if ( (LA122_0==GENERIC_PROPERTIES) ) {
						alt122=1;
					}
					switch (alt122) {
						case 1 :
							// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:935:55: genericProperties
							{
							_last = (CommonTree)input.LT(1);
							pushFollow(FOLLOW_genericProperties_in_restoreSnapshot8075);
							genericProperties478=genericProperties();
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) 
							adaptor.addChild(root_1, genericProperties478.getTree());

							if ( state.backtracking==0 ) {
							}

							}
							break;

					}

					match(input, Token.UP, null); if (state.failed) return retval;
					adaptor.addChild(root_0, root_1);
					_last = _save_last_1;
					}


					if ( state.backtracking==0 ) {
					            retval.value = new RestoreSnapshot((qname476!=null?((StatementBuilder.qname_return)qname476).value:null),
					                                        (tableWithPartitionList477!=null?((StatementBuilder.tableWithPartitionList_return)tableWithPartitionList477).value:null),
					                                        (genericProperties478!=null?((StatementBuilder.genericProperties_return)genericProperties478).value:null));
					        }
					if ( state.backtracking==0 ) {
					}

					}
					break;

			}
			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "restoreSnapshot"


	public static class tableElementList_return extends TreeRuleReturnScope {
		public List<TableElement> value = new ArrayList<>();
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "tableElementList"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:943:1: tableElementList returns [List<TableElement> value = new ArrayList<>()] : ^( TABLE_ELEMENT_LIST ( tableElement )+ ) ;
	public final StatementBuilder.tableElementList_return tableElementList() throws RecognitionException {
		StatementBuilder.tableElementList_return retval = new StatementBuilder.tableElementList_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree TABLE_ELEMENT_LIST479=null;
		TreeRuleReturnScope tableElement480 =null;

		CommonTree TABLE_ELEMENT_LIST479_tree=null;

		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:944:5: ( ^( TABLE_ELEMENT_LIST ( tableElement )+ ) )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:944:7: ^( TABLE_ELEMENT_LIST ( tableElement )+ )
			{
			root_0 = (CommonTree)adaptor.nil();


			_last = (CommonTree)input.LT(1);
			{
			CommonTree _save_last_1 = _last;
			CommonTree _first_1 = null;
			CommonTree root_1 = (CommonTree)adaptor.nil();
			_last = (CommonTree)input.LT(1);
			TABLE_ELEMENT_LIST479=(CommonTree)match(input,TABLE_ELEMENT_LIST,FOLLOW_TABLE_ELEMENT_LIST_in_tableElementList8109); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			TABLE_ELEMENT_LIST479_tree = (CommonTree)adaptor.dupNode(TABLE_ELEMENT_LIST479);


			root_1 = (CommonTree)adaptor.becomeRoot(TABLE_ELEMENT_LIST479_tree, root_1);
			}

			match(input, Token.DOWN, null); if (state.failed) return retval;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:944:28: ( tableElement )+
			int cnt124=0;
			loop124:
			while (true) {
				int alt124=2;
				int LA124_0 = input.LA(1);
				if ( (LA124_0==COLUMN_DEF||LA124_0==INDEX||LA124_0==PRIMARY_KEY) ) {
					alt124=1;
				}

				switch (alt124) {
				case 1 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:944:29: tableElement
					{
					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_tableElement_in_tableElementList8112);
					tableElement480=tableElement();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, tableElement480.getTree());

					if ( state.backtracking==0 ) { retval.value.add((tableElement480!=null?((StatementBuilder.tableElement_return)tableElement480).value:null)); }
					if ( state.backtracking==0 ) {
					}

					}
					break;

				default :
					if ( cnt124 >= 1 ) break loop124;
					if (state.backtracking>0) {state.failed=true; return retval;}
					EarlyExitException eee = new EarlyExitException(124, input);
					throw eee;
				}
				cnt124++;
			}

			match(input, Token.UP, null); if (state.failed) return retval;
			adaptor.addChild(root_0, root_1);
			_last = _save_last_1;
			}


			if ( state.backtracking==0 ) {
			}

			}

			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "tableElementList"


	public static class tableElement_return extends TreeRuleReturnScope {
		public TableElement value;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "tableElement"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:947:1: tableElement returns [TableElement value] : ( columnDefinition | indexDefinition | primaryKeyConstraint );
	public final StatementBuilder.tableElement_return tableElement() throws RecognitionException {
		StatementBuilder.tableElement_return retval = new StatementBuilder.tableElement_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		TreeRuleReturnScope columnDefinition481 =null;
		TreeRuleReturnScope indexDefinition482 =null;
		TreeRuleReturnScope primaryKeyConstraint483 =null;


		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:948:5: ( columnDefinition | indexDefinition | primaryKeyConstraint )
			int alt125=3;
			switch ( input.LA(1) ) {
			case COLUMN_DEF:
				{
				alt125=1;
				}
				break;
			case INDEX:
				{
				alt125=2;
				}
				break;
			case PRIMARY_KEY:
				{
				alt125=3;
				}
				break;
			default:
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 125, 0, input);
				throw nvae;
			}
			switch (alt125) {
				case 1 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:948:7: columnDefinition
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_columnDefinition_in_tableElement8140);
					columnDefinition481=columnDefinition();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_0, columnDefinition481.getTree());

					if ( state.backtracking==0 ) { retval.value = (columnDefinition481!=null?((StatementBuilder.columnDefinition_return)columnDefinition481).value:null); }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 2 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:949:7: indexDefinition
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_indexDefinition_in_tableElement8150);
					indexDefinition482=indexDefinition();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_0, indexDefinition482.getTree());

					if ( state.backtracking==0 ) { retval.value = (indexDefinition482!=null?((StatementBuilder.indexDefinition_return)indexDefinition482).value:null); }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 3 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:950:7: primaryKeyConstraint
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_primaryKeyConstraint_in_tableElement8161);
					primaryKeyConstraint483=primaryKeyConstraint();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_0, primaryKeyConstraint483.getTree());

					if ( state.backtracking==0 ) { retval.value = (primaryKeyConstraint483!=null?((StatementBuilder.primaryKeyConstraint_return)primaryKeyConstraint483).value:null); }
					if ( state.backtracking==0 ) {
					}

					}
					break;

			}
			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "tableElement"


	public static class addColumnDefinition_return extends TreeRuleReturnScope {
		public AddColumnDefinition value;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "addColumnDefinition"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:954:1: addColumnDefinition returns [AddColumnDefinition value] : ^( ADD_COLUMN_DEF name= expr (generated= expr )? ( dataType )? columnConstraints ) ;
	public final StatementBuilder.addColumnDefinition_return addColumnDefinition() throws RecognitionException {
		StatementBuilder.addColumnDefinition_return retval = new StatementBuilder.addColumnDefinition_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree ADD_COLUMN_DEF484=null;
		TreeRuleReturnScope name =null;
		TreeRuleReturnScope generated =null;
		TreeRuleReturnScope dataType485 =null;
		TreeRuleReturnScope columnConstraints486 =null;

		CommonTree ADD_COLUMN_DEF484_tree=null;

		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:955:5: ( ^( ADD_COLUMN_DEF name= expr (generated= expr )? ( dataType )? columnConstraints ) )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:955:7: ^( ADD_COLUMN_DEF name= expr (generated= expr )? ( dataType )? columnConstraints )
			{
			root_0 = (CommonTree)adaptor.nil();


			_last = (CommonTree)input.LT(1);
			{
			CommonTree _save_last_1 = _last;
			CommonTree _first_1 = null;
			CommonTree root_1 = (CommonTree)adaptor.nil();
			_last = (CommonTree)input.LT(1);
			ADD_COLUMN_DEF484=(CommonTree)match(input,ADD_COLUMN_DEF,FOLLOW_ADD_COLUMN_DEF_in_addColumnDefinition8186); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			ADD_COLUMN_DEF484_tree = (CommonTree)adaptor.dupNode(ADD_COLUMN_DEF484);


			root_1 = (CommonTree)adaptor.becomeRoot(ADD_COLUMN_DEF484_tree, root_1);
			}

			match(input, Token.DOWN, null); if (state.failed) return retval;
			_last = (CommonTree)input.LT(1);
			pushFollow(FOLLOW_expr_in_addColumnDefinition8190);
			name=expr();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) 
			adaptor.addChild(root_1, name.getTree());

			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:955:43: (generated= expr )?
			int alt126=2;
			int LA126_0 = input.LA(1);
			if ( (LA126_0==AND||(LA126_0 >= ARRAY_CMP && LA126_0 <= ARRAY_NOT_LIKE)||LA126_0==BETWEEN||LA126_0==CAST||LA126_0==COALESCE||LA126_0==CURRENT_DATE||(LA126_0 >= CURRENT_TIME && LA126_0 <= CURRENT_TIMESTAMP)||LA126_0==DATE||LA126_0==DECIMAL_VALUE||LA126_0==EQ||LA126_0==EXISTS||(LA126_0 >= EXTRACT && LA126_0 <= FALSE)||LA126_0==FUNCTION_CALL||(LA126_0 >= GT && LA126_0 <= GTE)||LA126_0==IDENT_EXPR||LA126_0==IF||LA126_0==IN||LA126_0==INTEGER_VALUE||LA126_0==IN_LIST||(LA126_0 >= IS_DISTINCT_FROM && LA126_0 <= IS_NULL)||LA126_0==LIKE||(LA126_0 >= LT && LA126_0 <= MATCH)||(LA126_0 >= NEGATIVE && LA126_0 <= NEQ)||LA126_0==NOT||(LA126_0 >= NULL && LA126_0 <= NULLIF)||LA126_0==OBJECT_LITERAL||LA126_0==OR||LA126_0==QNAME||LA126_0==QUERY||(LA126_0 >= REGEX_MATCH && LA126_0 <= REGEX_NO_MATCH_CI)||LA126_0==SEARCHED_CASE||LA126_0==SIMPLE_CASE||LA126_0==STRING||LA126_0==TIME||(LA126_0 >= TRUE && LA126_0 <= TRY_CAST)||(LA126_0 >= 328 && LA126_0 <= 329)||(LA126_0 >= 332 && LA126_0 <= 333)||LA126_0==335||(LA126_0 >= 337 && LA126_0 <= 338)||LA126_0==341) ) {
				alt126=1;
			}
			else if ( (LA126_0==TIMESTAMP) ) {
				int LA126_2 = input.LA(2);
				if ( (LA126_2==DOWN) ) {
					alt126=1;
				}
			}
			switch (alt126) {
				case 1 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:955:43: generated= expr
					{
					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_expr_in_addColumnDefinition8194);
					generated=expr();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, generated.getTree());

					if ( state.backtracking==0 ) {
					}

					}
					break;

			}

			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:955:50: ( dataType )?
			int alt127=2;
			int LA127_0 = input.LA(1);
			if ( (LA127_0==ARRAY||LA127_0==BOOLEAN||LA127_0==BYTE||LA127_0==DOUBLE||LA127_0==FLOAT||(LA127_0 >= GEO_POINT && LA127_0 <= GEO_SHAPE)||(LA127_0 >= INT && LA127_0 <= INTEGER)||LA127_0==IP||LA127_0==LONG||LA127_0==OBJECT||LA127_0==SET||LA127_0==SHORT||LA127_0==STRING_TYPE||LA127_0==TIMESTAMP) ) {
				alt127=1;
			}
			switch (alt127) {
				case 1 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:955:50: dataType
					{
					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_dataType_in_addColumnDefinition8197);
					dataType485=dataType();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, dataType485.getTree());

					if ( state.backtracking==0 ) {
					}

					}
					break;

			}

			_last = (CommonTree)input.LT(1);
			pushFollow(FOLLOW_columnConstraints_in_addColumnDefinition8200);
			columnConstraints486=columnConstraints();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) 
			adaptor.addChild(root_1, columnConstraints486.getTree());

			match(input, Token.UP, null); if (state.failed) return retval;
			adaptor.addChild(root_0, root_1);
			_last = _save_last_1;
			}


			if ( state.backtracking==0 ) {
			            retval.value = new AddColumnDefinition((name!=null?((StatementBuilder.expr_return)name).value:null),
			                                             (generated!=null?((StatementBuilder.expr_return)generated).value:null),
			                                             (dataType485!=null?((StatementBuilder.dataType_return)dataType485).value:null),
			                                             (columnConstraints486!=null?((StatementBuilder.columnConstraints_return)columnConstraints486).value:null));
			        }
			if ( state.backtracking==0 ) {
			}

			}

			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "addColumnDefinition"


	public static class columnDefinition_return extends TreeRuleReturnScope {
		public ColumnDefinition value;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "columnDefinition"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:964:1: columnDefinition returns [ColumnDefinition value] : ( ^( COLUMN_DEF ident expr ( dataType )? columnConstraints ) | ^( COLUMN_DEF ident dataType columnConstraints ) );
	public final StatementBuilder.columnDefinition_return columnDefinition() throws RecognitionException {
		StatementBuilder.columnDefinition_return retval = new StatementBuilder.columnDefinition_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree COLUMN_DEF487=null;
		CommonTree COLUMN_DEF492=null;
		TreeRuleReturnScope ident488 =null;
		TreeRuleReturnScope expr489 =null;
		TreeRuleReturnScope dataType490 =null;
		TreeRuleReturnScope columnConstraints491 =null;
		TreeRuleReturnScope ident493 =null;
		TreeRuleReturnScope dataType494 =null;
		TreeRuleReturnScope columnConstraints495 =null;

		CommonTree COLUMN_DEF487_tree=null;
		CommonTree COLUMN_DEF492_tree=null;

		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:965:5: ( ^( COLUMN_DEF ident expr ( dataType )? columnConstraints ) | ^( COLUMN_DEF ident dataType columnConstraints ) )
			int alt129=2;
			int LA129_0 = input.LA(1);
			if ( (LA129_0==COLUMN_DEF) ) {
				int LA129_1 = input.LA(2);
				if ( (LA129_1==DOWN) ) {
					int LA129_2 = input.LA(3);
					if ( (LA129_2==IDENT) ) {
						switch ( input.LA(4) ) {
						case AND:
						case ARRAY_CMP:
						case ARRAY_LIKE:
						case ARRAY_LITERAL:
						case ARRAY_NOT_LIKE:
						case BETWEEN:
						case CAST:
						case COALESCE:
						case CURRENT_DATE:
						case CURRENT_TIME:
						case CURRENT_TIMESTAMP:
						case DATE:
						case DECIMAL_VALUE:
						case EQ:
						case EXISTS:
						case EXTRACT:
						case FALSE:
						case FUNCTION_CALL:
						case GT:
						case GTE:
						case IDENT_EXPR:
						case IF:
						case IN:
						case INTEGER_VALUE:
						case IN_LIST:
						case IS_DISTINCT_FROM:
						case IS_NOT_NULL:
						case IS_NULL:
						case LIKE:
						case LT:
						case LTE:
						case MATCH:
						case NEGATIVE:
						case NEQ:
						case NOT:
						case NULL:
						case NULLIF:
						case OBJECT_LITERAL:
						case OR:
						case QNAME:
						case QUERY:
						case REGEX_MATCH:
						case REGEX_MATCH_CI:
						case REGEX_NO_MATCH:
						case REGEX_NO_MATCH_CI:
						case SEARCHED_CASE:
						case SIMPLE_CASE:
						case STRING:
						case TIME:
						case TRUE:
						case TRY_CAST:
						case 328:
						case 329:
						case 332:
						case 333:
						case 335:
						case 337:
						case 338:
						case 341:
							{
							alt129=1;
							}
							break;
						case TIMESTAMP:
							{
							int LA129_6 = input.LA(5);
							if ( (LA129_6==DOWN) ) {
								alt129=1;
							}
							else if ( (LA129_6==UP||LA129_6==CONSTRAINT) ) {
								alt129=2;
							}

							else {
								if (state.backtracking>0) {state.failed=true; return retval;}
								int nvaeMark = input.mark();
								try {
									for (int nvaeConsume = 0; nvaeConsume < 5 - 1; nvaeConsume++) {
										input.consume();
									}
									NoViableAltException nvae =
										new NoViableAltException("", 129, 6, input);
									throw nvae;
								} finally {
									input.rewind(nvaeMark);
								}
							}

							}
							break;
						case ARRAY:
						case BOOLEAN:
						case BYTE:
						case DOUBLE:
						case FLOAT:
						case GEO_POINT:
						case GEO_SHAPE:
						case INT:
						case INTEGER:
						case IP:
						case LONG:
						case OBJECT:
						case SET:
						case SHORT:
						case STRING_TYPE:
							{
							alt129=2;
							}
							break;
						default:
							if (state.backtracking>0) {state.failed=true; return retval;}
							int nvaeMark = input.mark();
							try {
								for (int nvaeConsume = 0; nvaeConsume < 4 - 1; nvaeConsume++) {
									input.consume();
								}
								NoViableAltException nvae =
									new NoViableAltException("", 129, 3, input);
								throw nvae;
							} finally {
								input.rewind(nvaeMark);
							}
						}
					}
					else if ( (LA129_2==QUOTED_IDENT) ) {
						switch ( input.LA(4) ) {
						case AND:
						case ARRAY_CMP:
						case ARRAY_LIKE:
						case ARRAY_LITERAL:
						case ARRAY_NOT_LIKE:
						case BETWEEN:
						case CAST:
						case COALESCE:
						case CURRENT_DATE:
						case CURRENT_TIME:
						case CURRENT_TIMESTAMP:
						case DATE:
						case DECIMAL_VALUE:
						case EQ:
						case EXISTS:
						case EXTRACT:
						case FALSE:
						case FUNCTION_CALL:
						case GT:
						case GTE:
						case IDENT_EXPR:
						case IF:
						case IN:
						case INTEGER_VALUE:
						case IN_LIST:
						case IS_DISTINCT_FROM:
						case IS_NOT_NULL:
						case IS_NULL:
						case LIKE:
						case LT:
						case LTE:
						case MATCH:
						case NEGATIVE:
						case NEQ:
						case NOT:
						case NULL:
						case NULLIF:
						case OBJECT_LITERAL:
						case OR:
						case QNAME:
						case QUERY:
						case REGEX_MATCH:
						case REGEX_MATCH_CI:
						case REGEX_NO_MATCH:
						case REGEX_NO_MATCH_CI:
						case SEARCHED_CASE:
						case SIMPLE_CASE:
						case STRING:
						case TIME:
						case TRUE:
						case TRY_CAST:
						case 328:
						case 329:
						case 332:
						case 333:
						case 335:
						case 337:
						case 338:
						case 341:
							{
							alt129=1;
							}
							break;
						case TIMESTAMP:
							{
							int LA129_6 = input.LA(5);
							if ( (LA129_6==DOWN) ) {
								alt129=1;
							}
							else if ( (LA129_6==UP||LA129_6==CONSTRAINT) ) {
								alt129=2;
							}

							else {
								if (state.backtracking>0) {state.failed=true; return retval;}
								int nvaeMark = input.mark();
								try {
									for (int nvaeConsume = 0; nvaeConsume < 5 - 1; nvaeConsume++) {
										input.consume();
									}
									NoViableAltException nvae =
										new NoViableAltException("", 129, 6, input);
									throw nvae;
								} finally {
									input.rewind(nvaeMark);
								}
							}

							}
							break;
						case ARRAY:
						case BOOLEAN:
						case BYTE:
						case DOUBLE:
						case FLOAT:
						case GEO_POINT:
						case GEO_SHAPE:
						case INT:
						case INTEGER:
						case IP:
						case LONG:
						case OBJECT:
						case SET:
						case SHORT:
						case STRING_TYPE:
							{
							alt129=2;
							}
							break;
						default:
							if (state.backtracking>0) {state.failed=true; return retval;}
							int nvaeMark = input.mark();
							try {
								for (int nvaeConsume = 0; nvaeConsume < 4 - 1; nvaeConsume++) {
									input.consume();
								}
								NoViableAltException nvae =
									new NoViableAltException("", 129, 4, input);
								throw nvae;
							} finally {
								input.rewind(nvaeMark);
							}
						}
					}

					else {
						if (state.backtracking>0) {state.failed=true; return retval;}
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 129, 2, input);
							throw nvae;
						} finally {
							input.rewind(nvaeMark);
						}
					}

				}

				else {
					if (state.backtracking>0) {state.failed=true; return retval;}
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 129, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

			}

			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 129, 0, input);
				throw nvae;
			}

			switch (alt129) {
				case 1 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:965:7: ^( COLUMN_DEF ident expr ( dataType )? columnConstraints )
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					{
					CommonTree _save_last_1 = _last;
					CommonTree _first_1 = null;
					CommonTree root_1 = (CommonTree)adaptor.nil();
					_last = (CommonTree)input.LT(1);
					COLUMN_DEF487=(CommonTree)match(input,COLUMN_DEF,FOLLOW_COLUMN_DEF_in_columnDefinition8233); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					COLUMN_DEF487_tree = (CommonTree)adaptor.dupNode(COLUMN_DEF487);


					root_1 = (CommonTree)adaptor.becomeRoot(COLUMN_DEF487_tree, root_1);
					}

					match(input, Token.DOWN, null); if (state.failed) return retval;
					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_ident_in_columnDefinition8235);
					ident488=ident();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, ident488.getTree());

					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_expr_in_columnDefinition8237);
					expr489=expr();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, expr489.getTree());

					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:965:31: ( dataType )?
					int alt128=2;
					int LA128_0 = input.LA(1);
					if ( (LA128_0==ARRAY||LA128_0==BOOLEAN||LA128_0==BYTE||LA128_0==DOUBLE||LA128_0==FLOAT||(LA128_0 >= GEO_POINT && LA128_0 <= GEO_SHAPE)||(LA128_0 >= INT && LA128_0 <= INTEGER)||LA128_0==IP||LA128_0==LONG||LA128_0==OBJECT||LA128_0==SET||LA128_0==SHORT||LA128_0==STRING_TYPE||LA128_0==TIMESTAMP) ) {
						alt128=1;
					}
					switch (alt128) {
						case 1 :
							// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:965:31: dataType
							{
							_last = (CommonTree)input.LT(1);
							pushFollow(FOLLOW_dataType_in_columnDefinition8239);
							dataType490=dataType();
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) 
							adaptor.addChild(root_1, dataType490.getTree());

							if ( state.backtracking==0 ) {
							}

							}
							break;

					}

					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_columnConstraints_in_columnDefinition8242);
					columnConstraints491=columnConstraints();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, columnConstraints491.getTree());

					match(input, Token.UP, null); if (state.failed) return retval;
					adaptor.addChild(root_0, root_1);
					_last = _save_last_1;
					}


					if ( state.backtracking==0 ) {
					            retval.value = new ColumnDefinition((ident488!=null?((StatementBuilder.ident_return)ident488).value:null), (expr489!=null?((StatementBuilder.expr_return)expr489).value:null), (dataType490!=null?((StatementBuilder.dataType_return)dataType490).value:null), (columnConstraints491!=null?((StatementBuilder.columnConstraints_return)columnConstraints491).value:null));
					        }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 2 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:969:7: ^( COLUMN_DEF ident dataType columnConstraints )
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					{
					CommonTree _save_last_1 = _last;
					CommonTree _first_1 = null;
					CommonTree root_1 = (CommonTree)adaptor.nil();
					_last = (CommonTree)input.LT(1);
					COLUMN_DEF492=(CommonTree)match(input,COLUMN_DEF,FOLLOW_COLUMN_DEF_in_columnDefinition8262); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					COLUMN_DEF492_tree = (CommonTree)adaptor.dupNode(COLUMN_DEF492);


					root_1 = (CommonTree)adaptor.becomeRoot(COLUMN_DEF492_tree, root_1);
					}

					match(input, Token.DOWN, null); if (state.failed) return retval;
					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_ident_in_columnDefinition8264);
					ident493=ident();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, ident493.getTree());

					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_dataType_in_columnDefinition8266);
					dataType494=dataType();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, dataType494.getTree());

					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_columnConstraints_in_columnDefinition8268);
					columnConstraints495=columnConstraints();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, columnConstraints495.getTree());

					match(input, Token.UP, null); if (state.failed) return retval;
					adaptor.addChild(root_0, root_1);
					_last = _save_last_1;
					}


					if ( state.backtracking==0 ) {
					            retval.value = new ColumnDefinition((ident493!=null?((StatementBuilder.ident_return)ident493).value:null), (dataType494!=null?((StatementBuilder.dataType_return)dataType494).value:null), (columnConstraints495!=null?((StatementBuilder.columnConstraints_return)columnConstraints495).value:null));
					        }
					if ( state.backtracking==0 ) {
					}

					}
					break;

			}
			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "columnDefinition"


	public static class dataType_return extends TreeRuleReturnScope {
		public ColumnType value;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "dataType"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:975:1: dataType returns [ColumnType value] : (type= ( BOOLEAN | BYTE | SHORT | INT | INTEGER | LONG | FLOAT | DOUBLE | TIMESTAMP | STRING_TYPE | IP | GEO_POINT | GEO_SHAPE ) | objectTypeDefinition | ^( ARRAY innerType= dataType ) | ^( SET innerType= dataType ) );
	public final StatementBuilder.dataType_return dataType() throws RecognitionException {
		StatementBuilder.dataType_return retval = new StatementBuilder.dataType_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree type=null;
		CommonTree ARRAY497=null;
		CommonTree SET498=null;
		TreeRuleReturnScope innerType =null;
		TreeRuleReturnScope objectTypeDefinition496 =null;

		CommonTree type_tree=null;
		CommonTree ARRAY497_tree=null;
		CommonTree SET498_tree=null;

		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:976:5: (type= ( BOOLEAN | BYTE | SHORT | INT | INTEGER | LONG | FLOAT | DOUBLE | TIMESTAMP | STRING_TYPE | IP | GEO_POINT | GEO_SHAPE ) | objectTypeDefinition | ^( ARRAY innerType= dataType ) | ^( SET innerType= dataType ) )
			int alt130=4;
			switch ( input.LA(1) ) {
			case BOOLEAN:
			case BYTE:
			case DOUBLE:
			case FLOAT:
			case GEO_POINT:
			case GEO_SHAPE:
			case INT:
			case INTEGER:
			case IP:
			case LONG:
			case SHORT:
			case STRING_TYPE:
			case TIMESTAMP:
				{
				alt130=1;
				}
				break;
			case OBJECT:
				{
				alt130=2;
				}
				break;
			case ARRAY:
				{
				alt130=3;
				}
				break;
			case SET:
				{
				alt130=4;
				}
				break;
			default:
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 130, 0, input);
				throw nvae;
			}
			switch (alt130) {
				case 1 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:976:7: type= ( BOOLEAN | BYTE | SHORT | INT | INTEGER | LONG | FLOAT | DOUBLE | TIMESTAMP | STRING_TYPE | IP | GEO_POINT | GEO_SHAPE )
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					type=(CommonTree)input.LT(1);
					if ( input.LA(1)==BOOLEAN||input.LA(1)==BYTE||input.LA(1)==DOUBLE||input.LA(1)==FLOAT||(input.LA(1) >= GEO_POINT && input.LA(1) <= GEO_SHAPE)||(input.LA(1) >= INT && input.LA(1) <= INTEGER)||input.LA(1)==IP||input.LA(1)==LONG||input.LA(1)==SHORT||input.LA(1)==STRING_TYPE||input.LA(1)==TIMESTAMP ) {
						input.consume();
						if ( state.backtracking==0 ) {
						type_tree = (CommonTree)adaptor.dupNode(type);


						adaptor.addChild(root_0, type_tree);
						}

						state.errorRecovery=false;
						state.failed=false;
					}
					else {
						if (state.backtracking>0) {state.failed=true; return retval;}
						MismatchedSetException mse = new MismatchedSetException(null,input);
						throw mse;
					}

					if ( state.backtracking==0 ) {
					        retval.value = new ColumnType((type!=null?type.getText():null).toLowerCase(Locale.ENGLISH));
					      }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 2 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:981:7: objectTypeDefinition
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_objectTypeDefinition_in_dataType8380);
					objectTypeDefinition496=objectTypeDefinition();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_0, objectTypeDefinition496.getTree());

					if ( state.backtracking==0 ) { retval.value = (objectTypeDefinition496!=null?((StatementBuilder.objectTypeDefinition_return)objectTypeDefinition496).value:null); }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 3 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:982:7: ^( ARRAY innerType= dataType )
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					{
					CommonTree _save_last_1 = _last;
					CommonTree _first_1 = null;
					CommonTree root_1 = (CommonTree)adaptor.nil();
					_last = (CommonTree)input.LT(1);
					ARRAY497=(CommonTree)match(input,ARRAY,FOLLOW_ARRAY_in_dataType8392); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					ARRAY497_tree = (CommonTree)adaptor.dupNode(ARRAY497);


					root_1 = (CommonTree)adaptor.becomeRoot(ARRAY497_tree, root_1);
					}

					match(input, Token.DOWN, null); if (state.failed) return retval;
					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_dataType_in_dataType8396);
					innerType=dataType();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, innerType.getTree());

					match(input, Token.UP, null); if (state.failed) return retval;
					adaptor.addChild(root_0, root_1);
					_last = _save_last_1;
					}


					if ( state.backtracking==0 ) { retval.value = CollectionColumnType.array((innerType!=null?((StatementBuilder.dataType_return)innerType).value:null)); }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 4 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:983:7: ^( SET innerType= dataType )
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					{
					CommonTree _save_last_1 = _last;
					CommonTree _first_1 = null;
					CommonTree root_1 = (CommonTree)adaptor.nil();
					_last = (CommonTree)input.LT(1);
					SET498=(CommonTree)match(input,SET,FOLLOW_SET_in_dataType8412); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					SET498_tree = (CommonTree)adaptor.dupNode(SET498);


					root_1 = (CommonTree)adaptor.becomeRoot(SET498_tree, root_1);
					}

					match(input, Token.DOWN, null); if (state.failed) return retval;
					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_dataType_in_dataType8416);
					innerType=dataType();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, innerType.getTree());

					match(input, Token.UP, null); if (state.failed) return retval;
					adaptor.addChild(root_0, root_1);
					_last = _save_last_1;
					}


					if ( state.backtracking==0 ) { retval.value = CollectionColumnType.set((innerType!=null?((StatementBuilder.dataType_return)innerType).value:null)); }
					if ( state.backtracking==0 ) {
					}

					}
					break;

			}
			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "dataType"


	public static class objectTypeDefinition_return extends TreeRuleReturnScope {
		public ObjectColumnType value;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "objectTypeDefinition"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:986:1: objectTypeDefinition returns [ObjectColumnType value] : ^( OBJECT (type= objectType )? ( columnDefinitionList )? ) ;
	public final StatementBuilder.objectTypeDefinition_return objectTypeDefinition() throws RecognitionException {
		StatementBuilder.objectTypeDefinition_return retval = new StatementBuilder.objectTypeDefinition_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree OBJECT499=null;
		TreeRuleReturnScope type =null;
		TreeRuleReturnScope columnDefinitionList500 =null;

		CommonTree OBJECT499_tree=null;

		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:987:5: ( ^( OBJECT (type= objectType )? ( columnDefinitionList )? ) )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:987:7: ^( OBJECT (type= objectType )? ( columnDefinitionList )? )
			{
			root_0 = (CommonTree)adaptor.nil();


			_last = (CommonTree)input.LT(1);
			{
			CommonTree _save_last_1 = _last;
			CommonTree _first_1 = null;
			CommonTree root_1 = (CommonTree)adaptor.nil();
			_last = (CommonTree)input.LT(1);
			OBJECT499=(CommonTree)match(input,OBJECT,FOLLOW_OBJECT_in_objectTypeDefinition8447); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			OBJECT499_tree = (CommonTree)adaptor.dupNode(OBJECT499);


			root_1 = (CommonTree)adaptor.becomeRoot(OBJECT499_tree, root_1);
			}

			if ( input.LA(1)==Token.DOWN ) {
				match(input, Token.DOWN, null); if (state.failed) return retval;
				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:987:20: (type= objectType )?
				int alt131=2;
				int LA131_0 = input.LA(1);
				if ( (LA131_0==DYNAMIC||LA131_0==IGNORED||LA131_0==STRICT) ) {
					alt131=1;
				}
				switch (alt131) {
					case 1 :
						// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:987:20: type= objectType
						{
						_last = (CommonTree)input.LT(1);
						pushFollow(FOLLOW_objectType_in_objectTypeDefinition8451);
						type=objectType();
						state._fsp--;
						if (state.failed) return retval;
						if ( state.backtracking==0 ) 
						adaptor.addChild(root_1, type.getTree());

						if ( state.backtracking==0 ) {
						}

						}
						break;

				}

				// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:987:33: ( columnDefinitionList )?
				int alt132=2;
				int LA132_0 = input.LA(1);
				if ( (LA132_0==OBJECT_COLUMNS) ) {
					alt132=1;
				}
				switch (alt132) {
					case 1 :
						// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:987:33: columnDefinitionList
						{
						_last = (CommonTree)input.LT(1);
						pushFollow(FOLLOW_columnDefinitionList_in_objectTypeDefinition8454);
						columnDefinitionList500=columnDefinitionList();
						state._fsp--;
						if (state.failed) return retval;
						if ( state.backtracking==0 ) 
						adaptor.addChild(root_1, columnDefinitionList500.getTree());

						if ( state.backtracking==0 ) {
						}

						}
						break;

				}

				match(input, Token.UP, null); if (state.failed) return retval;
			}
			adaptor.addChild(root_0, root_1);
			_last = _save_last_1;
			}


			if ( state.backtracking==0 ) {
			            retval.value = new ObjectColumnType((type!=null?((StatementBuilder.objectType_return)type).value:null), (columnDefinitionList500!=null?((StatementBuilder.columnDefinitionList_return)columnDefinitionList500).value:null));
			        }
			if ( state.backtracking==0 ) {
			}

			}

			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "objectTypeDefinition"


	public static class objectType_return extends TreeRuleReturnScope {
		public String value;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "objectType"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:993:1: objectType returns [String value] : type= ( DYNAMIC | STRICT | IGNORED ) ;
	public final StatementBuilder.objectType_return objectType() throws RecognitionException {
		StatementBuilder.objectType_return retval = new StatementBuilder.objectType_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree type=null;

		CommonTree type_tree=null;

		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:994:5: (type= ( DYNAMIC | STRICT | IGNORED ) )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:994:7: type= ( DYNAMIC | STRICT | IGNORED )
			{
			root_0 = (CommonTree)adaptor.nil();


			_last = (CommonTree)input.LT(1);
			type=(CommonTree)input.LT(1);
			if ( input.LA(1)==DYNAMIC||input.LA(1)==IGNORED||input.LA(1)==STRICT ) {
				input.consume();
				if ( state.backtracking==0 ) {
				type_tree = (CommonTree)adaptor.dupNode(type);


				adaptor.addChild(root_0, type_tree);
				}

				state.errorRecovery=false;
				state.failed=false;
			}
			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				MismatchedSetException mse = new MismatchedSetException(null,input);
				throw mse;
			}

			if ( state.backtracking==0 ) { retval.value = (type!=null?type.getText():null).toLowerCase(Locale.ENGLISH); }
			if ( state.backtracking==0 ) {
			}

			}

			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "objectType"


	public static class columnDefinitionList_return extends TreeRuleReturnScope {
		public List<ColumnDefinition> value = new ArrayList<>();
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "columnDefinitionList"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:997:1: columnDefinitionList returns [List<ColumnDefinition> value = new ArrayList<>()] : ^( OBJECT_COLUMNS ( columnDefinition )+ ) ;
	public final StatementBuilder.columnDefinitionList_return columnDefinitionList() throws RecognitionException {
		StatementBuilder.columnDefinitionList_return retval = new StatementBuilder.columnDefinitionList_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree OBJECT_COLUMNS501=null;
		TreeRuleReturnScope columnDefinition502 =null;

		CommonTree OBJECT_COLUMNS501_tree=null;

		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:998:5: ( ^( OBJECT_COLUMNS ( columnDefinition )+ ) )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:998:7: ^( OBJECT_COLUMNS ( columnDefinition )+ )
			{
			root_0 = (CommonTree)adaptor.nil();


			_last = (CommonTree)input.LT(1);
			{
			CommonTree _save_last_1 = _last;
			CommonTree _first_1 = null;
			CommonTree root_1 = (CommonTree)adaptor.nil();
			_last = (CommonTree)input.LT(1);
			OBJECT_COLUMNS501=(CommonTree)match(input,OBJECT_COLUMNS,FOLLOW_OBJECT_COLUMNS_in_columnDefinitionList8519); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			OBJECT_COLUMNS501_tree = (CommonTree)adaptor.dupNode(OBJECT_COLUMNS501);


			root_1 = (CommonTree)adaptor.becomeRoot(OBJECT_COLUMNS501_tree, root_1);
			}

			match(input, Token.DOWN, null); if (state.failed) return retval;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:998:24: ( columnDefinition )+
			int cnt133=0;
			loop133:
			while (true) {
				int alt133=2;
				int LA133_0 = input.LA(1);
				if ( (LA133_0==COLUMN_DEF) ) {
					alt133=1;
				}

				switch (alt133) {
				case 1 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:998:26: columnDefinition
					{
					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_columnDefinition_in_columnDefinitionList8523);
					columnDefinition502=columnDefinition();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, columnDefinition502.getTree());

					if ( state.backtracking==0 ) { retval.value.add((columnDefinition502!=null?((StatementBuilder.columnDefinition_return)columnDefinition502).value:null)); }
					if ( state.backtracking==0 ) {
					}

					}
					break;

				default :
					if ( cnt133 >= 1 ) break loop133;
					if (state.backtracking>0) {state.failed=true; return retval;}
					EarlyExitException eee = new EarlyExitException(133, input);
					throw eee;
				}
				cnt133++;
			}

			match(input, Token.UP, null); if (state.failed) return retval;
			adaptor.addChild(root_0, root_1);
			_last = _save_last_1;
			}


			if ( state.backtracking==0 ) {
			}

			}

			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "columnDefinitionList"


	public static class columnConstraints_return extends TreeRuleReturnScope {
		public List<ColumnConstraint> value = new ArrayList<>();
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "columnConstraints"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:1001:1: columnConstraints returns [List<ColumnConstraint> value = new ArrayList<>()] : ( ^( CONSTRAINT columnConstraint ) )* ;
	public final StatementBuilder.columnConstraints_return columnConstraints() throws RecognitionException {
		StatementBuilder.columnConstraints_return retval = new StatementBuilder.columnConstraints_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree CONSTRAINT503=null;
		TreeRuleReturnScope columnConstraint504 =null;

		CommonTree CONSTRAINT503_tree=null;

		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:1002:5: ( ( ^( CONSTRAINT columnConstraint ) )* )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:1002:7: ( ^( CONSTRAINT columnConstraint ) )*
			{
			root_0 = (CommonTree)adaptor.nil();


			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:1002:7: ( ^( CONSTRAINT columnConstraint ) )*
			loop134:
			while (true) {
				int alt134=2;
				int LA134_0 = input.LA(1);
				if ( (LA134_0==CONSTRAINT) ) {
					alt134=1;
				}

				switch (alt134) {
				case 1 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:1002:8: ^( CONSTRAINT columnConstraint )
					{
					_last = (CommonTree)input.LT(1);
					{
					CommonTree _save_last_1 = _last;
					CommonTree _first_1 = null;
					CommonTree root_1 = (CommonTree)adaptor.nil();
					_last = (CommonTree)input.LT(1);
					CONSTRAINT503=(CommonTree)match(input,CONSTRAINT,FOLLOW_CONSTRAINT_in_columnConstraints8553); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					CONSTRAINT503_tree = (CommonTree)adaptor.dupNode(CONSTRAINT503);


					root_1 = (CommonTree)adaptor.becomeRoot(CONSTRAINT503_tree, root_1);
					}

					match(input, Token.DOWN, null); if (state.failed) return retval;
					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_columnConstraint_in_columnConstraints8555);
					columnConstraint504=columnConstraint();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, columnConstraint504.getTree());

					match(input, Token.UP, null); if (state.failed) return retval;
					adaptor.addChild(root_0, root_1);
					_last = _save_last_1;
					}


					if ( state.backtracking==0 ) { retval.value.add((columnConstraint504!=null?((StatementBuilder.columnConstraint_return)columnConstraint504).value:null)); }
					if ( state.backtracking==0 ) {
					}

					}
					break;

				default :
					break loop134;
				}
			}

			if ( state.backtracking==0 ) {
			}

			}

			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "columnConstraints"


	public static class columnConstraint_return extends TreeRuleReturnScope {
		public ColumnConstraint value;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "columnConstraint"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:1005:1: columnConstraint returns [ColumnConstraint value] : ( PRIMARY_KEY | INDEX_OFF | ^( INDEX indexMethod= ident ( genericProperties )? ) );
	public final StatementBuilder.columnConstraint_return columnConstraint() throws RecognitionException {
		StatementBuilder.columnConstraint_return retval = new StatementBuilder.columnConstraint_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree PRIMARY_KEY505=null;
		CommonTree INDEX_OFF506=null;
		CommonTree INDEX507=null;
		TreeRuleReturnScope indexMethod =null;
		TreeRuleReturnScope genericProperties508 =null;

		CommonTree PRIMARY_KEY505_tree=null;
		CommonTree INDEX_OFF506_tree=null;
		CommonTree INDEX507_tree=null;

		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:1006:5: ( PRIMARY_KEY | INDEX_OFF | ^( INDEX indexMethod= ident ( genericProperties )? ) )
			int alt136=3;
			switch ( input.LA(1) ) {
			case PRIMARY_KEY:
				{
				alt136=1;
				}
				break;
			case INDEX_OFF:
				{
				alt136=2;
				}
				break;
			case INDEX:
				{
				alt136=3;
				}
				break;
			default:
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 136, 0, input);
				throw nvae;
			}
			switch (alt136) {
				case 1 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:1006:7: PRIMARY_KEY
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					PRIMARY_KEY505=(CommonTree)match(input,PRIMARY_KEY,FOLLOW_PRIMARY_KEY_in_columnConstraint8582); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					PRIMARY_KEY505_tree = (CommonTree)adaptor.dupNode(PRIMARY_KEY505);


					adaptor.addChild(root_0, PRIMARY_KEY505_tree);
					}

					if ( state.backtracking==0 ) { retval.value = new PrimaryKeyColumnConstraint(); }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 2 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:1007:7: INDEX_OFF
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					INDEX_OFF506=(CommonTree)match(input,INDEX_OFF,FOLLOW_INDEX_OFF_in_columnConstraint8592); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					INDEX_OFF506_tree = (CommonTree)adaptor.dupNode(INDEX_OFF506);


					adaptor.addChild(root_0, INDEX_OFF506_tree);
					}

					if ( state.backtracking==0 ) { retval.value = IndexColumnConstraint.OFF; }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 3 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:1008:7: ^( INDEX indexMethod= ident ( genericProperties )? )
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					{
					CommonTree _save_last_1 = _last;
					CommonTree _first_1 = null;
					CommonTree root_1 = (CommonTree)adaptor.nil();
					_last = (CommonTree)input.LT(1);
					INDEX507=(CommonTree)match(input,INDEX,FOLLOW_INDEX_in_columnConstraint8605); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					INDEX507_tree = (CommonTree)adaptor.dupNode(INDEX507);


					root_1 = (CommonTree)adaptor.becomeRoot(INDEX507_tree, root_1);
					}

					match(input, Token.DOWN, null); if (state.failed) return retval;
					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_ident_in_columnConstraint8609);
					indexMethod=ident();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, indexMethod.getTree());

					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:1008:33: ( genericProperties )?
					int alt135=2;
					int LA135_0 = input.LA(1);
					if ( (LA135_0==GENERIC_PROPERTIES) ) {
						alt135=1;
					}
					switch (alt135) {
						case 1 :
							// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:1008:33: genericProperties
							{
							_last = (CommonTree)input.LT(1);
							pushFollow(FOLLOW_genericProperties_in_columnConstraint8611);
							genericProperties508=genericProperties();
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) 
							adaptor.addChild(root_1, genericProperties508.getTree());

							if ( state.backtracking==0 ) {
							}

							}
							break;

					}

					match(input, Token.UP, null); if (state.failed) return retval;
					adaptor.addChild(root_0, root_1);
					_last = _save_last_1;
					}


					if ( state.backtracking==0 ) {
					            retval.value = new IndexColumnConstraint((indexMethod!=null?((StatementBuilder.ident_return)indexMethod).value:null), (genericProperties508!=null?((StatementBuilder.genericProperties_return)genericProperties508).value:null));
					        }
					if ( state.backtracking==0 ) {
					}

					}
					break;

			}
			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "columnConstraint"


	public static class genericProperties_return extends TreeRuleReturnScope {
		public GenericProperties value = new GenericProperties();
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "genericProperties"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:1014:1: genericProperties returns [GenericProperties value = new GenericProperties()] : ^( GENERIC_PROPERTIES ( genericProperty )+ ) ;
	public final StatementBuilder.genericProperties_return genericProperties() throws RecognitionException {
		StatementBuilder.genericProperties_return retval = new StatementBuilder.genericProperties_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree GENERIC_PROPERTIES509=null;
		TreeRuleReturnScope genericProperty510 =null;

		CommonTree GENERIC_PROPERTIES509_tree=null;

		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:1015:5: ( ^( GENERIC_PROPERTIES ( genericProperty )+ ) )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:1015:7: ^( GENERIC_PROPERTIES ( genericProperty )+ )
			{
			root_0 = (CommonTree)adaptor.nil();


			_last = (CommonTree)input.LT(1);
			{
			CommonTree _save_last_1 = _last;
			CommonTree _first_1 = null;
			CommonTree root_1 = (CommonTree)adaptor.nil();
			_last = (CommonTree)input.LT(1);
			GENERIC_PROPERTIES509=(CommonTree)match(input,GENERIC_PROPERTIES,FOLLOW_GENERIC_PROPERTIES_in_genericProperties8646); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			GENERIC_PROPERTIES509_tree = (CommonTree)adaptor.dupNode(GENERIC_PROPERTIES509);


			root_1 = (CommonTree)adaptor.becomeRoot(GENERIC_PROPERTIES509_tree, root_1);
			}

			match(input, Token.DOWN, null); if (state.failed) return retval;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:1015:29: ( genericProperty )+
			int cnt137=0;
			loop137:
			while (true) {
				int alt137=2;
				int LA137_0 = input.LA(1);
				if ( (LA137_0==GENERIC_PROPERTY) ) {
					alt137=1;
				}

				switch (alt137) {
				case 1 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:1015:31: genericProperty
					{
					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_genericProperty_in_genericProperties8650);
					genericProperty510=genericProperty();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, genericProperty510.getTree());

					if ( state.backtracking==0 ) { retval.value.add((genericProperty510!=null?((StatementBuilder.genericProperty_return)genericProperty510).value:null)); }
					if ( state.backtracking==0 ) {
					}

					}
					break;

				default :
					if ( cnt137 >= 1 ) break loop137;
					if (state.backtracking>0) {state.failed=true; return retval;}
					EarlyExitException eee = new EarlyExitException(137, input);
					throw eee;
				}
				cnt137++;
			}

			match(input, Token.UP, null); if (state.failed) return retval;
			adaptor.addChild(root_0, root_1);
			_last = _save_last_1;
			}


			if ( state.backtracking==0 ) {
			}

			}

			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "genericProperties"


	public static class genericProperty_return extends TreeRuleReturnScope {
		public GenericProperty value;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "genericProperty"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:1018:1: genericProperty returns [GenericProperty value] : ^( GENERIC_PROPERTY key= ident expr ) ;
	public final StatementBuilder.genericProperty_return genericProperty() throws RecognitionException {
		StatementBuilder.genericProperty_return retval = new StatementBuilder.genericProperty_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree GENERIC_PROPERTY511=null;
		TreeRuleReturnScope key =null;
		TreeRuleReturnScope expr512 =null;

		CommonTree GENERIC_PROPERTY511_tree=null;

		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:1019:5: ( ^( GENERIC_PROPERTY key= ident expr ) )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:1019:7: ^( GENERIC_PROPERTY key= ident expr )
			{
			root_0 = (CommonTree)adaptor.nil();


			_last = (CommonTree)input.LT(1);
			{
			CommonTree _save_last_1 = _last;
			CommonTree _first_1 = null;
			CommonTree root_1 = (CommonTree)adaptor.nil();
			_last = (CommonTree)input.LT(1);
			GENERIC_PROPERTY511=(CommonTree)match(input,GENERIC_PROPERTY,FOLLOW_GENERIC_PROPERTY_in_genericProperty8679); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			GENERIC_PROPERTY511_tree = (CommonTree)adaptor.dupNode(GENERIC_PROPERTY511);


			root_1 = (CommonTree)adaptor.becomeRoot(GENERIC_PROPERTY511_tree, root_1);
			}

			match(input, Token.DOWN, null); if (state.failed) return retval;
			_last = (CommonTree)input.LT(1);
			pushFollow(FOLLOW_ident_in_genericProperty8683);
			key=ident();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) 
			adaptor.addChild(root_1, key.getTree());

			_last = (CommonTree)input.LT(1);
			pushFollow(FOLLOW_expr_in_genericProperty8685);
			expr512=expr();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) 
			adaptor.addChild(root_1, expr512.getTree());

			match(input, Token.UP, null); if (state.failed) return retval;
			adaptor.addChild(root_0, root_1);
			_last = _save_last_1;
			}


			if ( state.backtracking==0 ) { retval.value = new GenericProperty((key!=null?((StatementBuilder.ident_return)key).value:null), (expr512!=null?((StatementBuilder.expr_return)expr512).value:null)); }
			if ( state.backtracking==0 ) {
			}

			}

			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "genericProperty"


	public static class arrayLiteral_return extends TreeRuleReturnScope {
		public ArrayLiteral value;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "arrayLiteral"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:1022:1: arrayLiteral returns [ArrayLiteral value] : ^( ARRAY_LITERAL exprList ) ;
	public final StatementBuilder.arrayLiteral_return arrayLiteral() throws RecognitionException {
		StatementBuilder.arrayLiteral_return retval = new StatementBuilder.arrayLiteral_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree ARRAY_LITERAL513=null;
		TreeRuleReturnScope exprList514 =null;

		CommonTree ARRAY_LITERAL513_tree=null;

		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:1023:5: ( ^( ARRAY_LITERAL exprList ) )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:1023:7: ^( ARRAY_LITERAL exprList )
			{
			root_0 = (CommonTree)adaptor.nil();


			_last = (CommonTree)input.LT(1);
			{
			CommonTree _save_last_1 = _last;
			CommonTree _first_1 = null;
			CommonTree root_1 = (CommonTree)adaptor.nil();
			_last = (CommonTree)input.LT(1);
			ARRAY_LITERAL513=(CommonTree)match(input,ARRAY_LITERAL,FOLLOW_ARRAY_LITERAL_in_arrayLiteral8710); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			ARRAY_LITERAL513_tree = (CommonTree)adaptor.dupNode(ARRAY_LITERAL513);


			root_1 = (CommonTree)adaptor.becomeRoot(ARRAY_LITERAL513_tree, root_1);
			}

			if ( input.LA(1)==Token.DOWN ) {
				match(input, Token.DOWN, null); if (state.failed) return retval;
				_last = (CommonTree)input.LT(1);
				pushFollow(FOLLOW_exprList_in_arrayLiteral8712);
				exprList514=exprList();
				state._fsp--;
				if (state.failed) return retval;
				if ( state.backtracking==0 ) 
				adaptor.addChild(root_1, exprList514.getTree());

				match(input, Token.UP, null); if (state.failed) return retval;
			}
			adaptor.addChild(root_0, root_1);
			_last = _save_last_1;
			}


			if ( state.backtracking==0 ) { retval.value =new ArrayLiteral((exprList514!=null?((StatementBuilder.exprList_return)exprList514).value:null)); }
			if ( state.backtracking==0 ) {
			}

			}

			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "arrayLiteral"


	public static class objectLiteral_return extends TreeRuleReturnScope {
		public ObjectLiteral value;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "objectLiteral"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:1026:1: objectLiteral returns [ObjectLiteral value] : ^( OBJECT_LITERAL objectAttributes ) ;
	public final StatementBuilder.objectLiteral_return objectLiteral() throws RecognitionException {
		StatementBuilder.objectLiteral_return retval = new StatementBuilder.objectLiteral_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree OBJECT_LITERAL515=null;
		TreeRuleReturnScope objectAttributes516 =null;

		CommonTree OBJECT_LITERAL515_tree=null;

		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:1027:5: ( ^( OBJECT_LITERAL objectAttributes ) )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:1027:7: ^( OBJECT_LITERAL objectAttributes )
			{
			root_0 = (CommonTree)adaptor.nil();


			_last = (CommonTree)input.LT(1);
			{
			CommonTree _save_last_1 = _last;
			CommonTree _first_1 = null;
			CommonTree root_1 = (CommonTree)adaptor.nil();
			_last = (CommonTree)input.LT(1);
			OBJECT_LITERAL515=(CommonTree)match(input,OBJECT_LITERAL,FOLLOW_OBJECT_LITERAL_in_objectLiteral8737); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			OBJECT_LITERAL515_tree = (CommonTree)adaptor.dupNode(OBJECT_LITERAL515);


			root_1 = (CommonTree)adaptor.becomeRoot(OBJECT_LITERAL515_tree, root_1);
			}

			if ( input.LA(1)==Token.DOWN ) {
				match(input, Token.DOWN, null); if (state.failed) return retval;
				_last = (CommonTree)input.LT(1);
				pushFollow(FOLLOW_objectAttributes_in_objectLiteral8739);
				objectAttributes516=objectAttributes();
				state._fsp--;
				if (state.failed) return retval;
				if ( state.backtracking==0 ) 
				adaptor.addChild(root_1, objectAttributes516.getTree());

				match(input, Token.UP, null); if (state.failed) return retval;
			}
			adaptor.addChild(root_0, root_1);
			_last = _save_last_1;
			}


			if ( state.backtracking==0 ) { retval.value = new ObjectLiteral((objectAttributes516!=null?((StatementBuilder.objectAttributes_return)objectAttributes516).value:null)); }
			if ( state.backtracking==0 ) {
			}

			}

			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "objectLiteral"


	public static class objectAttributes_return extends TreeRuleReturnScope {
		public Multimap<String, Expression> value = LinkedListMultimap.<String, Expression>create();
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "objectAttributes"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:1031:1: objectAttributes returns [Multimap<String, Expression> value = LinkedListMultimap.<String, Expression>create()] : ( ^( KEY_VALUE key= ident val= expr ) )* ;
	public final StatementBuilder.objectAttributes_return objectAttributes() throws RecognitionException {
		StatementBuilder.objectAttributes_return retval = new StatementBuilder.objectAttributes_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree KEY_VALUE517=null;
		TreeRuleReturnScope key =null;
		TreeRuleReturnScope val =null;

		CommonTree KEY_VALUE517_tree=null;

		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:1032:5: ( ( ^( KEY_VALUE key= ident val= expr ) )* )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:1032:7: ( ^( KEY_VALUE key= ident val= expr ) )*
			{
			root_0 = (CommonTree)adaptor.nil();


			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:1032:7: ( ^( KEY_VALUE key= ident val= expr ) )*
			loop138:
			while (true) {
				int alt138=2;
				int LA138_0 = input.LA(1);
				if ( (LA138_0==KEY_VALUE) ) {
					alt138=1;
				}

				switch (alt138) {
				case 1 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:1032:9: ^( KEY_VALUE key= ident val= expr )
					{
					_last = (CommonTree)input.LT(1);
					{
					CommonTree _save_last_1 = _last;
					CommonTree _first_1 = null;
					CommonTree root_1 = (CommonTree)adaptor.nil();
					_last = (CommonTree)input.LT(1);
					KEY_VALUE517=(CommonTree)match(input,KEY_VALUE,FOLLOW_KEY_VALUE_in_objectAttributes8767); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					KEY_VALUE517_tree = (CommonTree)adaptor.dupNode(KEY_VALUE517);


					root_1 = (CommonTree)adaptor.becomeRoot(KEY_VALUE517_tree, root_1);
					}

					match(input, Token.DOWN, null); if (state.failed) return retval;
					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_ident_in_objectAttributes8771);
					key=ident();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, key.getTree());

					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_expr_in_objectAttributes8775);
					val=expr();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, val.getTree());

					match(input, Token.UP, null); if (state.failed) return retval;
					adaptor.addChild(root_0, root_1);
					_last = _save_last_1;
					}


					if ( state.backtracking==0 ) { retval.value.put((key!=null?((StatementBuilder.ident_return)key).value:null), (val!=null?((StatementBuilder.expr_return)val).value:null)); }
					if ( state.backtracking==0 ) {
					}

					}
					break;

				default :
					break loop138;
				}
			}

			if ( state.backtracking==0 ) {
			}

			}

			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "objectAttributes"


	public static class indexDefinition_return extends TreeRuleReturnScope {
		public IndexDefinition value;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "indexDefinition"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:1035:1: indexDefinition returns [IndexDefinition value] : ^( INDEX indexName= ident indexMethod= ident columnList ( genericProperties )? ) ;
	public final StatementBuilder.indexDefinition_return indexDefinition() throws RecognitionException {
		StatementBuilder.indexDefinition_return retval = new StatementBuilder.indexDefinition_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree INDEX518=null;
		TreeRuleReturnScope indexName =null;
		TreeRuleReturnScope indexMethod =null;
		TreeRuleReturnScope columnList519 =null;
		TreeRuleReturnScope genericProperties520 =null;

		CommonTree INDEX518_tree=null;

		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:1036:5: ( ^( INDEX indexName= ident indexMethod= ident columnList ( genericProperties )? ) )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:1036:7: ^( INDEX indexName= ident indexMethod= ident columnList ( genericProperties )? )
			{
			root_0 = (CommonTree)adaptor.nil();


			_last = (CommonTree)input.LT(1);
			{
			CommonTree _save_last_1 = _last;
			CommonTree _first_1 = null;
			CommonTree root_1 = (CommonTree)adaptor.nil();
			_last = (CommonTree)input.LT(1);
			INDEX518=(CommonTree)match(input,INDEX,FOLLOW_INDEX_in_indexDefinition8803); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			INDEX518_tree = (CommonTree)adaptor.dupNode(INDEX518);


			root_1 = (CommonTree)adaptor.becomeRoot(INDEX518_tree, root_1);
			}

			match(input, Token.DOWN, null); if (state.failed) return retval;
			_last = (CommonTree)input.LT(1);
			pushFollow(FOLLOW_ident_in_indexDefinition8807);
			indexName=ident();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) 
			adaptor.addChild(root_1, indexName.getTree());

			_last = (CommonTree)input.LT(1);
			pushFollow(FOLLOW_ident_in_indexDefinition8811);
			indexMethod=ident();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) 
			adaptor.addChild(root_1, indexMethod.getTree());

			_last = (CommonTree)input.LT(1);
			pushFollow(FOLLOW_columnList_in_indexDefinition8813);
			columnList519=columnList();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) 
			adaptor.addChild(root_1, columnList519.getTree());

			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:1036:60: ( genericProperties )?
			int alt139=2;
			int LA139_0 = input.LA(1);
			if ( (LA139_0==GENERIC_PROPERTIES) ) {
				alt139=1;
			}
			switch (alt139) {
				case 1 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:1036:60: genericProperties
					{
					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_genericProperties_in_indexDefinition8815);
					genericProperties520=genericProperties();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, genericProperties520.getTree());

					if ( state.backtracking==0 ) {
					}

					}
					break;

			}

			match(input, Token.UP, null); if (state.failed) return retval;
			adaptor.addChild(root_0, root_1);
			_last = _save_last_1;
			}


			if ( state.backtracking==0 ) {
			            retval.value = new IndexDefinition((indexName!=null?((StatementBuilder.ident_return)indexName).value:null), (indexMethod!=null?((StatementBuilder.ident_return)indexMethod).value:null), (columnList519!=null?((StatementBuilder.columnList_return)columnList519).value:null), (genericProperties520!=null?((StatementBuilder.genericProperties_return)genericProperties520).value:null));
			        }
			if ( state.backtracking==0 ) {
			}

			}

			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "indexDefinition"


	public static class columnIdentList_return extends TreeRuleReturnScope {
		public List<String> value;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "columnIdentList"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:1042:1: columnIdentList returns [List<String> value] : ^( IDENT_LIST identList ) ;
	public final StatementBuilder.columnIdentList_return columnIdentList() throws RecognitionException {
		StatementBuilder.columnIdentList_return retval = new StatementBuilder.columnIdentList_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree IDENT_LIST521=null;
		TreeRuleReturnScope identList522 =null;

		CommonTree IDENT_LIST521_tree=null;

		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:1043:5: ( ^( IDENT_LIST identList ) )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:1043:7: ^( IDENT_LIST identList )
			{
			root_0 = (CommonTree)adaptor.nil();


			_last = (CommonTree)input.LT(1);
			{
			CommonTree _save_last_1 = _last;
			CommonTree _first_1 = null;
			CommonTree root_1 = (CommonTree)adaptor.nil();
			_last = (CommonTree)input.LT(1);
			IDENT_LIST521=(CommonTree)match(input,IDENT_LIST,FOLLOW_IDENT_LIST_in_columnIdentList8849); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			IDENT_LIST521_tree = (CommonTree)adaptor.dupNode(IDENT_LIST521);


			root_1 = (CommonTree)adaptor.becomeRoot(IDENT_LIST521_tree, root_1);
			}

			match(input, Token.DOWN, null); if (state.failed) return retval;
			_last = (CommonTree)input.LT(1);
			pushFollow(FOLLOW_identList_in_columnIdentList8851);
			identList522=identList();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) 
			adaptor.addChild(root_1, identList522.getTree());

			match(input, Token.UP, null); if (state.failed) return retval;
			adaptor.addChild(root_0, root_1);
			_last = _save_last_1;
			}


			if ( state.backtracking==0 ) { retval.value = (identList522!=null?((StatementBuilder.identList_return)identList522).value:null); }
			if ( state.backtracking==0 ) {
			}

			}

			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "columnIdentList"


	public static class columnList_return extends TreeRuleReturnScope {
		public List<Expression> value = new ArrayList<>();
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "columnList"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:1046:1: columnList returns [List<Expression> value = new ArrayList<>()] : ^( COLUMN_LIST ( columnListElement )+ ) ;
	public final StatementBuilder.columnList_return columnList() throws RecognitionException {
		StatementBuilder.columnList_return retval = new StatementBuilder.columnList_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree COLUMN_LIST523=null;
		TreeRuleReturnScope columnListElement524 =null;

		CommonTree COLUMN_LIST523_tree=null;

		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:1047:5: ( ^( COLUMN_LIST ( columnListElement )+ ) )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:1047:7: ^( COLUMN_LIST ( columnListElement )+ )
			{
			root_0 = (CommonTree)adaptor.nil();


			_last = (CommonTree)input.LT(1);
			{
			CommonTree _save_last_1 = _last;
			CommonTree _first_1 = null;
			CommonTree root_1 = (CommonTree)adaptor.nil();
			_last = (CommonTree)input.LT(1);
			COLUMN_LIST523=(CommonTree)match(input,COLUMN_LIST,FOLLOW_COLUMN_LIST_in_columnList8876); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			COLUMN_LIST523_tree = (CommonTree)adaptor.dupNode(COLUMN_LIST523);


			root_1 = (CommonTree)adaptor.becomeRoot(COLUMN_LIST523_tree, root_1);
			}

			match(input, Token.DOWN, null); if (state.failed) return retval;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:1047:22: ( columnListElement )+
			int cnt140=0;
			loop140:
			while (true) {
				int alt140=2;
				int LA140_0 = input.LA(1);
				if ( (LA140_0==QNAME||LA140_0==341) ) {
					alt140=1;
				}

				switch (alt140) {
				case 1 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:1047:23: columnListElement
					{
					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_columnListElement_in_columnList8880);
					columnListElement524=columnListElement();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, columnListElement524.getTree());

					if ( state.backtracking==0 ) { retval.value.add((columnListElement524!=null?((StatementBuilder.columnListElement_return)columnListElement524).value:null)); }
					if ( state.backtracking==0 ) {
					}

					}
					break;

				default :
					if ( cnt140 >= 1 ) break loop140;
					if (state.backtracking>0) {state.failed=true; return retval;}
					EarlyExitException eee = new EarlyExitException(140, input);
					throw eee;
				}
				cnt140++;
			}

			match(input, Token.UP, null); if (state.failed) return retval;
			adaptor.addChild(root_0, root_1);
			_last = _save_last_1;
			}


			if ( state.backtracking==0 ) {
			}

			}

			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "columnList"


	public static class columnListElement_return extends TreeRuleReturnScope {
		public Expression value;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "columnListElement"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:1050:1: columnListElement returns [Expression value] : ( subscript | qname );
	public final StatementBuilder.columnListElement_return columnListElement() throws RecognitionException {
		StatementBuilder.columnListElement_return retval = new StatementBuilder.columnListElement_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		TreeRuleReturnScope subscript525 =null;
		TreeRuleReturnScope qname526 =null;


		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:1051:5: ( subscript | qname )
			int alt141=2;
			int LA141_0 = input.LA(1);
			if ( (LA141_0==341) ) {
				alt141=1;
			}
			else if ( (LA141_0==QNAME) ) {
				alt141=2;
			}

			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 141, 0, input);
				throw nvae;
			}

			switch (alt141) {
				case 1 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:1051:7: subscript
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_subscript_in_columnListElement8908);
					subscript525=subscript();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_0, subscript525.getTree());

					if ( state.backtracking==0 ) { retval.value = (subscript525!=null?((StatementBuilder.subscript_return)subscript525).value:null); }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 2 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:1052:7: qname
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_qname_in_columnListElement8918);
					qname526=qname();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_0, qname526.getTree());

					if ( state.backtracking==0 ) { retval.value = new QualifiedNameReference((qname526!=null?((StatementBuilder.qname_return)qname526).value:null)); }
					if ( state.backtracking==0 ) {
					}

					}
					break;

			}
			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "columnListElement"


	public static class primaryKeyConstraint_return extends TreeRuleReturnScope {
		public PrimaryKeyConstraint value;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "primaryKeyConstraint"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:1055:1: primaryKeyConstraint returns [PrimaryKeyConstraint value] : ^( PRIMARY_KEY columnList ) ;
	public final StatementBuilder.primaryKeyConstraint_return primaryKeyConstraint() throws RecognitionException {
		StatementBuilder.primaryKeyConstraint_return retval = new StatementBuilder.primaryKeyConstraint_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree PRIMARY_KEY527=null;
		TreeRuleReturnScope columnList528 =null;

		CommonTree PRIMARY_KEY527_tree=null;

		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:1056:5: ( ^( PRIMARY_KEY columnList ) )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:1056:7: ^( PRIMARY_KEY columnList )
			{
			root_0 = (CommonTree)adaptor.nil();


			_last = (CommonTree)input.LT(1);
			{
			CommonTree _save_last_1 = _last;
			CommonTree _first_1 = null;
			CommonTree root_1 = (CommonTree)adaptor.nil();
			_last = (CommonTree)input.LT(1);
			PRIMARY_KEY527=(CommonTree)match(input,PRIMARY_KEY,FOLLOW_PRIMARY_KEY_in_primaryKeyConstraint8942); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			PRIMARY_KEY527_tree = (CommonTree)adaptor.dupNode(PRIMARY_KEY527);


			root_1 = (CommonTree)adaptor.becomeRoot(PRIMARY_KEY527_tree, root_1);
			}

			match(input, Token.DOWN, null); if (state.failed) return retval;
			_last = (CommonTree)input.LT(1);
			pushFollow(FOLLOW_columnList_in_primaryKeyConstraint8944);
			columnList528=columnList();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) 
			adaptor.addChild(root_1, columnList528.getTree());

			match(input, Token.UP, null); if (state.failed) return retval;
			adaptor.addChild(root_0, root_1);
			_last = _save_last_1;
			}


			if ( state.backtracking==0 ) { retval.value = new PrimaryKeyConstraint((columnList528!=null?((StatementBuilder.columnList_return)columnList528).value:null)); }
			if ( state.backtracking==0 ) {
			}

			}

			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "primaryKeyConstraint"


	public static class crateTableOptionList_return extends TreeRuleReturnScope {
		public List<CrateTableOption> value = new ArrayList();
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "crateTableOptionList"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:1059:1: crateTableOptionList returns [List<CrateTableOption> value = new ArrayList()] : ( crateTableOption )* ;
	public final StatementBuilder.crateTableOptionList_return crateTableOptionList() throws RecognitionException {
		StatementBuilder.crateTableOptionList_return retval = new StatementBuilder.crateTableOptionList_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		TreeRuleReturnScope crateTableOption529 =null;


		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:1060:5: ( ( crateTableOption )* )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:1060:7: ( crateTableOption )*
			{
			root_0 = (CommonTree)adaptor.nil();


			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:1060:7: ( crateTableOption )*
			loop142:
			while (true) {
				int alt142=2;
				int LA142_0 = input.LA(1);
				if ( (LA142_0==CLUSTERED||LA142_0==PARTITIONED) ) {
					alt142=1;
				}

				switch (alt142) {
				case 1 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:1060:9: crateTableOption
					{
					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_crateTableOption_in_crateTableOptionList8970);
					crateTableOption529=crateTableOption();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_0, crateTableOption529.getTree());

					if ( state.backtracking==0 ) { retval.value.add( (crateTableOption529!=null?((StatementBuilder.crateTableOption_return)crateTableOption529).value:null) ); }
					if ( state.backtracking==0 ) {
					}

					}
					break;

				default :
					break loop142;
				}
			}

			if ( state.backtracking==0 ) {
			}

			}

			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "crateTableOptionList"


	public static class crateTableOption_return extends TreeRuleReturnScope {
		public CrateTableOption value;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "crateTableOption"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:1063:1: crateTableOption returns [CrateTableOption value] : ( clusteredBy | partitionedBy );
	public final StatementBuilder.crateTableOption_return crateTableOption() throws RecognitionException {
		StatementBuilder.crateTableOption_return retval = new StatementBuilder.crateTableOption_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		TreeRuleReturnScope clusteredBy530 =null;
		TreeRuleReturnScope partitionedBy531 =null;


		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:1064:5: ( clusteredBy | partitionedBy )
			int alt143=2;
			int LA143_0 = input.LA(1);
			if ( (LA143_0==CLUSTERED) ) {
				alt143=1;
			}
			else if ( (LA143_0==PARTITIONED) ) {
				alt143=2;
			}

			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 143, 0, input);
				throw nvae;
			}

			switch (alt143) {
				case 1 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:1064:7: clusteredBy
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_clusteredBy_in_crateTableOption8996);
					clusteredBy530=clusteredBy();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_0, clusteredBy530.getTree());

					if ( state.backtracking==0 ) { retval.value = (clusteredBy530!=null?((StatementBuilder.clusteredBy_return)clusteredBy530).value:null); }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 2 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:1065:7: partitionedBy
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_partitionedBy_in_crateTableOption9008);
					partitionedBy531=partitionedBy();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_0, partitionedBy531.getTree());

					if ( state.backtracking==0 ) { retval.value = (partitionedBy531!=null?((StatementBuilder.partitionedBy_return)partitionedBy531).value:null); }
					if ( state.backtracking==0 ) {
					}

					}
					break;

			}
			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "crateTableOption"


	public static class clusteredBy_return extends TreeRuleReturnScope {
		public ClusteredBy value;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "clusteredBy"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:1068:1: clusteredBy returns [ClusteredBy value] : ( ^( CLUSTERED parameterOrSimpleLiteral ) | ^( CLUSTERED subscript ( parameterOrSimpleLiteral )? ) | ^( CLUSTERED qname ( parameterOrSimpleLiteral )? ) );
	public final StatementBuilder.clusteredBy_return clusteredBy() throws RecognitionException {
		StatementBuilder.clusteredBy_return retval = new StatementBuilder.clusteredBy_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree CLUSTERED532=null;
		CommonTree CLUSTERED534=null;
		CommonTree CLUSTERED537=null;
		TreeRuleReturnScope parameterOrSimpleLiteral533 =null;
		TreeRuleReturnScope subscript535 =null;
		TreeRuleReturnScope parameterOrSimpleLiteral536 =null;
		TreeRuleReturnScope qname538 =null;
		TreeRuleReturnScope parameterOrSimpleLiteral539 =null;

		CommonTree CLUSTERED532_tree=null;
		CommonTree CLUSTERED534_tree=null;
		CommonTree CLUSTERED537_tree=null;

		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:1069:5: ( ^( CLUSTERED parameterOrSimpleLiteral ) | ^( CLUSTERED subscript ( parameterOrSimpleLiteral )? ) | ^( CLUSTERED qname ( parameterOrSimpleLiteral )? ) )
			int alt146=3;
			int LA146_0 = input.LA(1);
			if ( (LA146_0==CLUSTERED) ) {
				int LA146_1 = input.LA(2);
				if ( (LA146_1==DOWN) ) {
					switch ( input.LA(3) ) {
					case DECIMAL_VALUE:
					case FALSE:
					case IDENT_EXPR:
					case INTEGER_VALUE:
					case NULL:
					case STRING:
					case TRUE:
					case 328:
					case 338:
						{
						alt146=1;
						}
						break;
					case 341:
						{
						alt146=2;
						}
						break;
					case QNAME:
						{
						alt146=3;
						}
						break;
					default:
						if (state.backtracking>0) {state.failed=true; return retval;}
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 146, 2, input);
							throw nvae;
						} finally {
							input.rewind(nvaeMark);
						}
					}
				}

				else {
					if (state.backtracking>0) {state.failed=true; return retval;}
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 146, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

			}

			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 146, 0, input);
				throw nvae;
			}

			switch (alt146) {
				case 1 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:1069:7: ^( CLUSTERED parameterOrSimpleLiteral )
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					{
					CommonTree _save_last_1 = _last;
					CommonTree _first_1 = null;
					CommonTree root_1 = (CommonTree)adaptor.nil();
					_last = (CommonTree)input.LT(1);
					CLUSTERED532=(CommonTree)match(input,CLUSTERED,FOLLOW_CLUSTERED_in_clusteredBy9032); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					CLUSTERED532_tree = (CommonTree)adaptor.dupNode(CLUSTERED532);


					root_1 = (CommonTree)adaptor.becomeRoot(CLUSTERED532_tree, root_1);
					}

					match(input, Token.DOWN, null); if (state.failed) return retval;
					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_parameterOrSimpleLiteral_in_clusteredBy9034);
					parameterOrSimpleLiteral533=parameterOrSimpleLiteral();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, parameterOrSimpleLiteral533.getTree());

					match(input, Token.UP, null); if (state.failed) return retval;
					adaptor.addChild(root_0, root_1);
					_last = _save_last_1;
					}


					if ( state.backtracking==0 ) { retval.value = new ClusteredBy(null, (parameterOrSimpleLiteral533!=null?((StatementBuilder.parameterOrSimpleLiteral_return)parameterOrSimpleLiteral533).value:null)); }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 2 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:1070:7: ^( CLUSTERED subscript ( parameterOrSimpleLiteral )? )
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					{
					CommonTree _save_last_1 = _last;
					CommonTree _first_1 = null;
					CommonTree root_1 = (CommonTree)adaptor.nil();
					_last = (CommonTree)input.LT(1);
					CLUSTERED534=(CommonTree)match(input,CLUSTERED,FOLLOW_CLUSTERED_in_clusteredBy9046); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					CLUSTERED534_tree = (CommonTree)adaptor.dupNode(CLUSTERED534);


					root_1 = (CommonTree)adaptor.becomeRoot(CLUSTERED534_tree, root_1);
					}

					match(input, Token.DOWN, null); if (state.failed) return retval;
					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_subscript_in_clusteredBy9048);
					subscript535=subscript();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, subscript535.getTree());

					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:1070:29: ( parameterOrSimpleLiteral )?
					int alt144=2;
					int LA144_0 = input.LA(1);
					if ( (LA144_0==DECIMAL_VALUE||LA144_0==FALSE||LA144_0==IDENT_EXPR||LA144_0==INTEGER_VALUE||LA144_0==NULL||LA144_0==STRING||LA144_0==TRUE||LA144_0==328||LA144_0==338) ) {
						alt144=1;
					}
					switch (alt144) {
						case 1 :
							// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:1070:29: parameterOrSimpleLiteral
							{
							_last = (CommonTree)input.LT(1);
							pushFollow(FOLLOW_parameterOrSimpleLiteral_in_clusteredBy9050);
							parameterOrSimpleLiteral536=parameterOrSimpleLiteral();
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) 
							adaptor.addChild(root_1, parameterOrSimpleLiteral536.getTree());

							if ( state.backtracking==0 ) {
							}

							}
							break;

					}

					match(input, Token.UP, null); if (state.failed) return retval;
					adaptor.addChild(root_0, root_1);
					_last = _save_last_1;
					}


					if ( state.backtracking==0 ) { retval.value = new ClusteredBy((subscript535!=null?((StatementBuilder.subscript_return)subscript535).value:null), (parameterOrSimpleLiteral536!=null?((StatementBuilder.parameterOrSimpleLiteral_return)parameterOrSimpleLiteral536).value:null)); }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 3 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:1071:7: ^( CLUSTERED qname ( parameterOrSimpleLiteral )? )
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					{
					CommonTree _save_last_1 = _last;
					CommonTree _first_1 = null;
					CommonTree root_1 = (CommonTree)adaptor.nil();
					_last = (CommonTree)input.LT(1);
					CLUSTERED537=(CommonTree)match(input,CLUSTERED,FOLLOW_CLUSTERED_in_clusteredBy9063); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					CLUSTERED537_tree = (CommonTree)adaptor.dupNode(CLUSTERED537);


					root_1 = (CommonTree)adaptor.becomeRoot(CLUSTERED537_tree, root_1);
					}

					match(input, Token.DOWN, null); if (state.failed) return retval;
					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_qname_in_clusteredBy9065);
					qname538=qname();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, qname538.getTree());

					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:1071:25: ( parameterOrSimpleLiteral )?
					int alt145=2;
					int LA145_0 = input.LA(1);
					if ( (LA145_0==DECIMAL_VALUE||LA145_0==FALSE||LA145_0==IDENT_EXPR||LA145_0==INTEGER_VALUE||LA145_0==NULL||LA145_0==STRING||LA145_0==TRUE||LA145_0==328||LA145_0==338) ) {
						alt145=1;
					}
					switch (alt145) {
						case 1 :
							// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:1071:25: parameterOrSimpleLiteral
							{
							_last = (CommonTree)input.LT(1);
							pushFollow(FOLLOW_parameterOrSimpleLiteral_in_clusteredBy9067);
							parameterOrSimpleLiteral539=parameterOrSimpleLiteral();
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) 
							adaptor.addChild(root_1, parameterOrSimpleLiteral539.getTree());

							if ( state.backtracking==0 ) {
							}

							}
							break;

					}

					match(input, Token.UP, null); if (state.failed) return retval;
					adaptor.addChild(root_0, root_1);
					_last = _save_last_1;
					}


					if ( state.backtracking==0 ) { retval.value = new ClusteredBy(new QualifiedNameReference((qname538!=null?((StatementBuilder.qname_return)qname538).value:null)), (parameterOrSimpleLiteral539!=null?((StatementBuilder.parameterOrSimpleLiteral_return)parameterOrSimpleLiteral539).value:null)); }
					if ( state.backtracking==0 ) {
					}

					}
					break;

			}
			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "clusteredBy"


	public static class partitionedBy_return extends TreeRuleReturnScope {
		public PartitionedBy value;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "partitionedBy"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:1074:1: partitionedBy returns [PartitionedBy value] : ^( PARTITIONED columnList ) ;
	public final StatementBuilder.partitionedBy_return partitionedBy() throws RecognitionException {
		StatementBuilder.partitionedBy_return retval = new StatementBuilder.partitionedBy_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree PARTITIONED540=null;
		TreeRuleReturnScope columnList541 =null;

		CommonTree PARTITIONED540_tree=null;

		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:1075:5: ( ^( PARTITIONED columnList ) )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:1075:7: ^( PARTITIONED columnList )
			{
			root_0 = (CommonTree)adaptor.nil();


			_last = (CommonTree)input.LT(1);
			{
			CommonTree _save_last_1 = _last;
			CommonTree _first_1 = null;
			CommonTree root_1 = (CommonTree)adaptor.nil();
			_last = (CommonTree)input.LT(1);
			PARTITIONED540=(CommonTree)match(input,PARTITIONED,FOLLOW_PARTITIONED_in_partitionedBy9093); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			PARTITIONED540_tree = (CommonTree)adaptor.dupNode(PARTITIONED540);


			root_1 = (CommonTree)adaptor.becomeRoot(PARTITIONED540_tree, root_1);
			}

			match(input, Token.DOWN, null); if (state.failed) return retval;
			_last = (CommonTree)input.LT(1);
			pushFollow(FOLLOW_columnList_in_partitionedBy9095);
			columnList541=columnList();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) 
			adaptor.addChild(root_1, columnList541.getTree());

			match(input, Token.UP, null); if (state.failed) return retval;
			adaptor.addChild(root_0, root_1);
			_last = _save_last_1;
			}


			if ( state.backtracking==0 ) { retval.value = new PartitionedBy((columnList541!=null?((StatementBuilder.columnList_return)columnList541).value:null)); }
			if ( state.backtracking==0 ) {
			}

			}

			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "partitionedBy"


	public static class createAnalyzer_return extends TreeRuleReturnScope {
		public CreateAnalyzer value;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "createAnalyzer"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:1079:1: createAnalyzer returns [CreateAnalyzer value] : ^( ANALYZER ident ( extendsAnalyzer )? analyzerElementList ) ;
	public final StatementBuilder.createAnalyzer_return createAnalyzer() throws RecognitionException {
		StatementBuilder.createAnalyzer_return retval = new StatementBuilder.createAnalyzer_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree ANALYZER542=null;
		TreeRuleReturnScope ident543 =null;
		TreeRuleReturnScope extendsAnalyzer544 =null;
		TreeRuleReturnScope analyzerElementList545 =null;

		CommonTree ANALYZER542_tree=null;

		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:1080:5: ( ^( ANALYZER ident ( extendsAnalyzer )? analyzerElementList ) )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:1080:7: ^( ANALYZER ident ( extendsAnalyzer )? analyzerElementList )
			{
			root_0 = (CommonTree)adaptor.nil();


			_last = (CommonTree)input.LT(1);
			{
			CommonTree _save_last_1 = _last;
			CommonTree _first_1 = null;
			CommonTree root_1 = (CommonTree)adaptor.nil();
			_last = (CommonTree)input.LT(1);
			ANALYZER542=(CommonTree)match(input,ANALYZER,FOLLOW_ANALYZER_in_createAnalyzer9121); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			ANALYZER542_tree = (CommonTree)adaptor.dupNode(ANALYZER542);


			root_1 = (CommonTree)adaptor.becomeRoot(ANALYZER542_tree, root_1);
			}

			match(input, Token.DOWN, null); if (state.failed) return retval;
			_last = (CommonTree)input.LT(1);
			pushFollow(FOLLOW_ident_in_createAnalyzer9123);
			ident543=ident();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) 
			adaptor.addChild(root_1, ident543.getTree());

			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:1080:24: ( extendsAnalyzer )?
			int alt147=2;
			int LA147_0 = input.LA(1);
			if ( (LA147_0==EXTENDS) ) {
				alt147=1;
			}
			switch (alt147) {
				case 1 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:1080:24: extendsAnalyzer
					{
					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_extendsAnalyzer_in_createAnalyzer9125);
					extendsAnalyzer544=extendsAnalyzer();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, extendsAnalyzer544.getTree());

					if ( state.backtracking==0 ) {
					}

					}
					break;

			}

			_last = (CommonTree)input.LT(1);
			pushFollow(FOLLOW_analyzerElementList_in_createAnalyzer9128);
			analyzerElementList545=analyzerElementList();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) 
			adaptor.addChild(root_1, analyzerElementList545.getTree());

			match(input, Token.UP, null); if (state.failed) return retval;
			adaptor.addChild(root_0, root_1);
			_last = _save_last_1;
			}


			if ( state.backtracking==0 ) {
			            retval.value = new CreateAnalyzer((ident543!=null?((StatementBuilder.ident_return)ident543).value:null), (extendsAnalyzer544!=null?((StatementBuilder.extendsAnalyzer_return)extendsAnalyzer544).value:null), (analyzerElementList545!=null?((StatementBuilder.analyzerElementList_return)analyzerElementList545).value:null));
			        }
			if ( state.backtracking==0 ) {
			}

			}

			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "createAnalyzer"


	public static class extendsAnalyzer_return extends TreeRuleReturnScope {
		public String value;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "extendsAnalyzer"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:1086:1: extendsAnalyzer returns [String value] : ^( EXTENDS ident ) ;
	public final StatementBuilder.extendsAnalyzer_return extendsAnalyzer() throws RecognitionException {
		StatementBuilder.extendsAnalyzer_return retval = new StatementBuilder.extendsAnalyzer_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree EXTENDS546=null;
		TreeRuleReturnScope ident547 =null;

		CommonTree EXTENDS546_tree=null;

		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:1087:5: ( ^( EXTENDS ident ) )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:1087:7: ^( EXTENDS ident )
			{
			root_0 = (CommonTree)adaptor.nil();


			_last = (CommonTree)input.LT(1);
			{
			CommonTree _save_last_1 = _last;
			CommonTree _first_1 = null;
			CommonTree root_1 = (CommonTree)adaptor.nil();
			_last = (CommonTree)input.LT(1);
			EXTENDS546=(CommonTree)match(input,EXTENDS,FOLLOW_EXTENDS_in_extendsAnalyzer9161); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			EXTENDS546_tree = (CommonTree)adaptor.dupNode(EXTENDS546);


			root_1 = (CommonTree)adaptor.becomeRoot(EXTENDS546_tree, root_1);
			}

			match(input, Token.DOWN, null); if (state.failed) return retval;
			_last = (CommonTree)input.LT(1);
			pushFollow(FOLLOW_ident_in_extendsAnalyzer9163);
			ident547=ident();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) 
			adaptor.addChild(root_1, ident547.getTree());

			match(input, Token.UP, null); if (state.failed) return retval;
			adaptor.addChild(root_0, root_1);
			_last = _save_last_1;
			}


			if ( state.backtracking==0 ) { retval.value = (ident547!=null?((StatementBuilder.ident_return)ident547).value:null); }
			if ( state.backtracking==0 ) {
			}

			}

			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "extendsAnalyzer"


	public static class analyzerElementList_return extends TreeRuleReturnScope {
		public List<AnalyzerElement> value = new ArrayList<>();
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "analyzerElementList"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:1090:1: analyzerElementList returns [List<AnalyzerElement> value = new ArrayList<>()] : ^( ANALYZER_ELEMENTS ( analyzerElement )+ ) ;
	public final StatementBuilder.analyzerElementList_return analyzerElementList() throws RecognitionException {
		StatementBuilder.analyzerElementList_return retval = new StatementBuilder.analyzerElementList_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree ANALYZER_ELEMENTS548=null;
		TreeRuleReturnScope analyzerElement549 =null;

		CommonTree ANALYZER_ELEMENTS548_tree=null;

		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:1091:5: ( ^( ANALYZER_ELEMENTS ( analyzerElement )+ ) )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:1091:7: ^( ANALYZER_ELEMENTS ( analyzerElement )+ )
			{
			root_0 = (CommonTree)adaptor.nil();


			_last = (CommonTree)input.LT(1);
			{
			CommonTree _save_last_1 = _last;
			CommonTree _first_1 = null;
			CommonTree root_1 = (CommonTree)adaptor.nil();
			_last = (CommonTree)input.LT(1);
			ANALYZER_ELEMENTS548=(CommonTree)match(input,ANALYZER_ELEMENTS,FOLLOW_ANALYZER_ELEMENTS_in_analyzerElementList9188); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			ANALYZER_ELEMENTS548_tree = (CommonTree)adaptor.dupNode(ANALYZER_ELEMENTS548);


			root_1 = (CommonTree)adaptor.becomeRoot(ANALYZER_ELEMENTS548_tree, root_1);
			}

			match(input, Token.DOWN, null); if (state.failed) return retval;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:1091:27: ( analyzerElement )+
			int cnt148=0;
			loop148:
			while (true) {
				int alt148=2;
				int LA148_0 = input.LA(1);
				if ( (LA148_0==CHAR_FILTERS||LA148_0==GENERIC_PROPERTY||(LA148_0 >= TOKENIZER && LA148_0 <= TOKEN_FILTERS)) ) {
					alt148=1;
				}

				switch (alt148) {
				case 1 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:1091:29: analyzerElement
					{
					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_analyzerElement_in_analyzerElementList9192);
					analyzerElement549=analyzerElement();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, analyzerElement549.getTree());

					if ( state.backtracking==0 ) { retval.value.add((analyzerElement549!=null?((StatementBuilder.analyzerElement_return)analyzerElement549).value:null)); }
					if ( state.backtracking==0 ) {
					}

					}
					break;

				default :
					if ( cnt148 >= 1 ) break loop148;
					if (state.backtracking>0) {state.failed=true; return retval;}
					EarlyExitException eee = new EarlyExitException(148, input);
					throw eee;
				}
				cnt148++;
			}

			match(input, Token.UP, null); if (state.failed) return retval;
			adaptor.addChild(root_0, root_1);
			_last = _save_last_1;
			}


			if ( state.backtracking==0 ) {
			}

			}

			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "analyzerElementList"


	public static class analyzerElement_return extends TreeRuleReturnScope {
		public AnalyzerElement value;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "analyzerElement"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:1094:1: analyzerElement returns [AnalyzerElement value] : ( ^( TOKENIZER namedProperties ) | tokenFilters | charFilters | genericProperty );
	public final StatementBuilder.analyzerElement_return analyzerElement() throws RecognitionException {
		StatementBuilder.analyzerElement_return retval = new StatementBuilder.analyzerElement_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree TOKENIZER550=null;
		TreeRuleReturnScope namedProperties551 =null;
		TreeRuleReturnScope tokenFilters552 =null;
		TreeRuleReturnScope charFilters553 =null;
		TreeRuleReturnScope genericProperty554 =null;

		CommonTree TOKENIZER550_tree=null;

		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:1095:5: ( ^( TOKENIZER namedProperties ) | tokenFilters | charFilters | genericProperty )
			int alt149=4;
			switch ( input.LA(1) ) {
			case TOKENIZER:
				{
				alt149=1;
				}
				break;
			case TOKEN_FILTERS:
				{
				alt149=2;
				}
				break;
			case CHAR_FILTERS:
				{
				alt149=3;
				}
				break;
			case GENERIC_PROPERTY:
				{
				alt149=4;
				}
				break;
			default:
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 149, 0, input);
				throw nvae;
			}
			switch (alt149) {
				case 1 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:1095:7: ^( TOKENIZER namedProperties )
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					{
					CommonTree _save_last_1 = _last;
					CommonTree _first_1 = null;
					CommonTree root_1 = (CommonTree)adaptor.nil();
					_last = (CommonTree)input.LT(1);
					TOKENIZER550=(CommonTree)match(input,TOKENIZER,FOLLOW_TOKENIZER_in_analyzerElement9221); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					TOKENIZER550_tree = (CommonTree)adaptor.dupNode(TOKENIZER550);


					root_1 = (CommonTree)adaptor.becomeRoot(TOKENIZER550_tree, root_1);
					}

					match(input, Token.DOWN, null); if (state.failed) return retval;
					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_namedProperties_in_analyzerElement9223);
					namedProperties551=namedProperties();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, namedProperties551.getTree());

					match(input, Token.UP, null); if (state.failed) return retval;
					adaptor.addChild(root_0, root_1);
					_last = _save_last_1;
					}


					if ( state.backtracking==0 ) { retval.value = new Tokenizer((namedProperties551!=null?((StatementBuilder.namedProperties_return)namedProperties551).value:null)); }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 2 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:1096:7: tokenFilters
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_tokenFilters_in_analyzerElement9235);
					tokenFilters552=tokenFilters();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_0, tokenFilters552.getTree());

					if ( state.backtracking==0 ) { retval.value = new TokenFilters((tokenFilters552!=null?((StatementBuilder.tokenFilters_return)tokenFilters552).value:null)); }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 3 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:1097:7: charFilters
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_charFilters_in_analyzerElement9262);
					charFilters553=charFilters();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_0, charFilters553.getTree());

					if ( state.backtracking==0 ) { retval.value = new CharFilters((charFilters553!=null?((StatementBuilder.charFilters_return)charFilters553).value:null)); }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 4 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:1098:7: genericProperty
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_genericProperty_in_analyzerElement9290);
					genericProperty554=genericProperty();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_0, genericProperty554.getTree());

					if ( state.backtracking==0 ) { retval.value = (genericProperty554!=null?((StatementBuilder.genericProperty_return)genericProperty554).value:null); }
					if ( state.backtracking==0 ) {
					}

					}
					break;

			}
			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "analyzerElement"


	public static class tokenFilters_return extends TreeRuleReturnScope {
		public List<NamedProperties> value = new ArrayList<>();
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "tokenFilters"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:1102:1: tokenFilters returns [List<NamedProperties> value = new ArrayList<>()] : ^( TOKEN_FILTERS ( namedProperties )+ ) ;
	public final StatementBuilder.tokenFilters_return tokenFilters() throws RecognitionException {
		StatementBuilder.tokenFilters_return retval = new StatementBuilder.tokenFilters_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree TOKEN_FILTERS555=null;
		TreeRuleReturnScope namedProperties556 =null;

		CommonTree TOKEN_FILTERS555_tree=null;

		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:1103:5: ( ^( TOKEN_FILTERS ( namedProperties )+ ) )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:1103:7: ^( TOKEN_FILTERS ( namedProperties )+ )
			{
			root_0 = (CommonTree)adaptor.nil();


			_last = (CommonTree)input.LT(1);
			{
			CommonTree _save_last_1 = _last;
			CommonTree _first_1 = null;
			CommonTree root_1 = (CommonTree)adaptor.nil();
			_last = (CommonTree)input.LT(1);
			TOKEN_FILTERS555=(CommonTree)match(input,TOKEN_FILTERS,FOLLOW_TOKEN_FILTERS_in_tokenFilters9329); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			TOKEN_FILTERS555_tree = (CommonTree)adaptor.dupNode(TOKEN_FILTERS555);


			root_1 = (CommonTree)adaptor.becomeRoot(TOKEN_FILTERS555_tree, root_1);
			}

			match(input, Token.DOWN, null); if (state.failed) return retval;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:1103:23: ( namedProperties )+
			int cnt150=0;
			loop150:
			while (true) {
				int alt150=2;
				int LA150_0 = input.LA(1);
				if ( (LA150_0==NAMED_PROPERTIES) ) {
					alt150=1;
				}

				switch (alt150) {
				case 1 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:1103:25: namedProperties
					{
					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_namedProperties_in_tokenFilters9333);
					namedProperties556=namedProperties();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, namedProperties556.getTree());

					if ( state.backtracking==0 ) { retval.value.add((namedProperties556!=null?((StatementBuilder.namedProperties_return)namedProperties556).value:null)); }
					if ( state.backtracking==0 ) {
					}

					}
					break;

				default :
					if ( cnt150 >= 1 ) break loop150;
					if (state.backtracking>0) {state.failed=true; return retval;}
					EarlyExitException eee = new EarlyExitException(150, input);
					throw eee;
				}
				cnt150++;
			}

			match(input, Token.UP, null); if (state.failed) return retval;
			adaptor.addChild(root_0, root_1);
			_last = _save_last_1;
			}


			if ( state.backtracking==0 ) {
			}

			}

			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "tokenFilters"


	public static class charFilters_return extends TreeRuleReturnScope {
		public List<NamedProperties> value = new ArrayList<>();
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "charFilters"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:1106:1: charFilters returns [List<NamedProperties> value = new ArrayList<>()] : ^( CHAR_FILTERS ( namedProperties )+ ) ;
	public final StatementBuilder.charFilters_return charFilters() throws RecognitionException {
		StatementBuilder.charFilters_return retval = new StatementBuilder.charFilters_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree CHAR_FILTERS557=null;
		TreeRuleReturnScope namedProperties558 =null;

		CommonTree CHAR_FILTERS557_tree=null;

		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:1107:5: ( ^( CHAR_FILTERS ( namedProperties )+ ) )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:1107:7: ^( CHAR_FILTERS ( namedProperties )+ )
			{
			root_0 = (CommonTree)adaptor.nil();


			_last = (CommonTree)input.LT(1);
			{
			CommonTree _save_last_1 = _last;
			CommonTree _first_1 = null;
			CommonTree root_1 = (CommonTree)adaptor.nil();
			_last = (CommonTree)input.LT(1);
			CHAR_FILTERS557=(CommonTree)match(input,CHAR_FILTERS,FOLLOW_CHAR_FILTERS_in_charFilters9362); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			CHAR_FILTERS557_tree = (CommonTree)adaptor.dupNode(CHAR_FILTERS557);


			root_1 = (CommonTree)adaptor.becomeRoot(CHAR_FILTERS557_tree, root_1);
			}

			match(input, Token.DOWN, null); if (state.failed) return retval;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:1107:22: ( namedProperties )+
			int cnt151=0;
			loop151:
			while (true) {
				int alt151=2;
				int LA151_0 = input.LA(1);
				if ( (LA151_0==NAMED_PROPERTIES) ) {
					alt151=1;
				}

				switch (alt151) {
				case 1 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:1107:24: namedProperties
					{
					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_namedProperties_in_charFilters9366);
					namedProperties558=namedProperties();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, namedProperties558.getTree());

					if ( state.backtracking==0 ) { retval.value.add((namedProperties558!=null?((StatementBuilder.namedProperties_return)namedProperties558).value:null)); }
					if ( state.backtracking==0 ) {
					}

					}
					break;

				default :
					if ( cnt151 >= 1 ) break loop151;
					if (state.backtracking>0) {state.failed=true; return retval;}
					EarlyExitException eee = new EarlyExitException(151, input);
					throw eee;
				}
				cnt151++;
			}

			match(input, Token.UP, null); if (state.failed) return retval;
			adaptor.addChild(root_0, root_1);
			_last = _save_last_1;
			}


			if ( state.backtracking==0 ) {
			}

			}

			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "charFilters"


	public static class namedProperties_return extends TreeRuleReturnScope {
		public NamedProperties value;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "namedProperties"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:1110:1: namedProperties returns [NamedProperties value] : ^( NAMED_PROPERTIES ident ( genericProperties )? ) ;
	public final StatementBuilder.namedProperties_return namedProperties() throws RecognitionException {
		StatementBuilder.namedProperties_return retval = new StatementBuilder.namedProperties_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree NAMED_PROPERTIES559=null;
		TreeRuleReturnScope ident560 =null;
		TreeRuleReturnScope genericProperties561 =null;

		CommonTree NAMED_PROPERTIES559_tree=null;

		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:1111:5: ( ^( NAMED_PROPERTIES ident ( genericProperties )? ) )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:1111:7: ^( NAMED_PROPERTIES ident ( genericProperties )? )
			{
			root_0 = (CommonTree)adaptor.nil();


			_last = (CommonTree)input.LT(1);
			{
			CommonTree _save_last_1 = _last;
			CommonTree _first_1 = null;
			CommonTree root_1 = (CommonTree)adaptor.nil();
			_last = (CommonTree)input.LT(1);
			NAMED_PROPERTIES559=(CommonTree)match(input,NAMED_PROPERTIES,FOLLOW_NAMED_PROPERTIES_in_namedProperties9395); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			NAMED_PROPERTIES559_tree = (CommonTree)adaptor.dupNode(NAMED_PROPERTIES559);


			root_1 = (CommonTree)adaptor.becomeRoot(NAMED_PROPERTIES559_tree, root_1);
			}

			match(input, Token.DOWN, null); if (state.failed) return retval;
			_last = (CommonTree)input.LT(1);
			pushFollow(FOLLOW_ident_in_namedProperties9397);
			ident560=ident();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) 
			adaptor.addChild(root_1, ident560.getTree());

			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:1111:32: ( genericProperties )?
			int alt152=2;
			int LA152_0 = input.LA(1);
			if ( (LA152_0==GENERIC_PROPERTIES) ) {
				alt152=1;
			}
			switch (alt152) {
				case 1 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:1111:32: genericProperties
					{
					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_genericProperties_in_namedProperties9399);
					genericProperties561=genericProperties();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, genericProperties561.getTree());

					if ( state.backtracking==0 ) {
					}

					}
					break;

			}

			match(input, Token.UP, null); if (state.failed) return retval;
			adaptor.addChild(root_0, root_1);
			_last = _save_last_1;
			}


			if ( state.backtracking==0 ) { retval.value = new NamedProperties((ident560!=null?((StatementBuilder.ident_return)ident560).value:null), (genericProperties561!=null?((StatementBuilder.genericProperties_return)genericProperties561).value:null)); }
			if ( state.backtracking==0 ) {
			}

			}

			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "namedProperties"


	public static class refresh_return extends TreeRuleReturnScope {
		public RefreshStatement value;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "refresh"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:1114:1: refresh returns [RefreshStatement value] : ^( REFRESH tableWithPartitionList ) ;
	public final StatementBuilder.refresh_return refresh() throws RecognitionException {
		StatementBuilder.refresh_return retval = new StatementBuilder.refresh_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree REFRESH562=null;
		TreeRuleReturnScope tableWithPartitionList563 =null;

		CommonTree REFRESH562_tree=null;

		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:1115:5: ( ^( REFRESH tableWithPartitionList ) )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:1115:7: ^( REFRESH tableWithPartitionList )
			{
			root_0 = (CommonTree)adaptor.nil();


			_last = (CommonTree)input.LT(1);
			{
			CommonTree _save_last_1 = _last;
			CommonTree _first_1 = null;
			CommonTree root_1 = (CommonTree)adaptor.nil();
			_last = (CommonTree)input.LT(1);
			REFRESH562=(CommonTree)match(input,REFRESH,FOLLOW_REFRESH_in_refresh9425); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			REFRESH562_tree = (CommonTree)adaptor.dupNode(REFRESH562);


			root_1 = (CommonTree)adaptor.becomeRoot(REFRESH562_tree, root_1);
			}

			match(input, Token.DOWN, null); if (state.failed) return retval;
			_last = (CommonTree)input.LT(1);
			pushFollow(FOLLOW_tableWithPartitionList_in_refresh9427);
			tableWithPartitionList563=tableWithPartitionList();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) 
			adaptor.addChild(root_1, tableWithPartitionList563.getTree());

			match(input, Token.UP, null); if (state.failed) return retval;
			adaptor.addChild(root_0, root_1);
			_last = _save_last_1;
			}


			if ( state.backtracking==0 ) { retval.value = new RefreshStatement((tableWithPartitionList563!=null?((StatementBuilder.tableWithPartitionList_return)tableWithPartitionList563).value:null)); }
			if ( state.backtracking==0 ) {
			}

			}

			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "refresh"


	public static class tableWithPartitionList_return extends TreeRuleReturnScope {
		public List<Table> value = new ArrayList<>();
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "tableWithPartitionList"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:1118:1: tableWithPartitionList returns [List<Table> value = new ArrayList<>()] : ^( TABLE_PARTITION_LIST ( namedTable )+ ) ;
	public final StatementBuilder.tableWithPartitionList_return tableWithPartitionList() throws RecognitionException {
		StatementBuilder.tableWithPartitionList_return retval = new StatementBuilder.tableWithPartitionList_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree TABLE_PARTITION_LIST564=null;
		TreeRuleReturnScope namedTable565 =null;

		CommonTree TABLE_PARTITION_LIST564_tree=null;

		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:1119:5: ( ^( TABLE_PARTITION_LIST ( namedTable )+ ) )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:1119:7: ^( TABLE_PARTITION_LIST ( namedTable )+ )
			{
			root_0 = (CommonTree)adaptor.nil();


			_last = (CommonTree)input.LT(1);
			{
			CommonTree _save_last_1 = _last;
			CommonTree _first_1 = null;
			CommonTree root_1 = (CommonTree)adaptor.nil();
			_last = (CommonTree)input.LT(1);
			TABLE_PARTITION_LIST564=(CommonTree)match(input,TABLE_PARTITION_LIST,FOLLOW_TABLE_PARTITION_LIST_in_tableWithPartitionList9453); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			TABLE_PARTITION_LIST564_tree = (CommonTree)adaptor.dupNode(TABLE_PARTITION_LIST564);


			root_1 = (CommonTree)adaptor.becomeRoot(TABLE_PARTITION_LIST564_tree, root_1);
			}

			match(input, Token.DOWN, null); if (state.failed) return retval;
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:1119:30: ( namedTable )+
			int cnt153=0;
			loop153:
			while (true) {
				int alt153=2;
				int LA153_0 = input.LA(1);
				if ( (LA153_0==TABLE) ) {
					alt153=1;
				}

				switch (alt153) {
				case 1 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:1119:32: namedTable
					{
					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_namedTable_in_tableWithPartitionList9457);
					namedTable565=namedTable();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, namedTable565.getTree());

					if ( state.backtracking==0 ) { retval.value.add((namedTable565!=null?((StatementBuilder.namedTable_return)namedTable565).value:null)); }
					if ( state.backtracking==0 ) {
					}

					}
					break;

				default :
					if ( cnt153 >= 1 ) break loop153;
					if (state.backtracking>0) {state.failed=true; return retval;}
					EarlyExitException eee = new EarlyExitException(153, input);
					throw eee;
				}
				cnt153++;
			}

			match(input, Token.UP, null); if (state.failed) return retval;
			adaptor.addChild(root_0, root_1);
			_last = _save_last_1;
			}


			if ( state.backtracking==0 ) {
			}

			}

			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "tableWithPartitionList"


	public static class set_return extends TreeRuleReturnScope {
		public SetStatement value;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "set"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:1122:1: set returns [SetStatement value] : ( ^( SET assignments= assignmentList ) | ^( SET TRANSIENT assignments= assignmentList ) | ^( SET PERSISTENT assignments= assignmentList ) );
	public final StatementBuilder.set_return set() throws RecognitionException {
		StatementBuilder.set_return retval = new StatementBuilder.set_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree SET566=null;
		CommonTree SET567=null;
		CommonTree TRANSIENT568=null;
		CommonTree SET569=null;
		CommonTree PERSISTENT570=null;
		TreeRuleReturnScope assignments =null;

		CommonTree SET566_tree=null;
		CommonTree SET567_tree=null;
		CommonTree TRANSIENT568_tree=null;
		CommonTree SET569_tree=null;
		CommonTree PERSISTENT570_tree=null;

		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:1123:5: ( ^( SET assignments= assignmentList ) | ^( SET TRANSIENT assignments= assignmentList ) | ^( SET PERSISTENT assignments= assignmentList ) )
			int alt154=3;
			int LA154_0 = input.LA(1);
			if ( (LA154_0==SET) ) {
				int LA154_1 = input.LA(2);
				if ( (LA154_1==DOWN) ) {
					switch ( input.LA(3) ) {
					case TRANSIENT:
						{
						alt154=2;
						}
						break;
					case PERSISTENT:
						{
						alt154=3;
						}
						break;
					case ASSIGNMENT_LIST:
						{
						alt154=1;
						}
						break;
					default:
						if (state.backtracking>0) {state.failed=true; return retval;}
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 154, 2, input);
							throw nvae;
						} finally {
							input.rewind(nvaeMark);
						}
					}
				}

				else {
					if (state.backtracking>0) {state.failed=true; return retval;}
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 154, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

			}

			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 154, 0, input);
				throw nvae;
			}

			switch (alt154) {
				case 1 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:1123:7: ^( SET assignments= assignmentList )
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					{
					CommonTree _save_last_1 = _last;
					CommonTree _first_1 = null;
					CommonTree root_1 = (CommonTree)adaptor.nil();
					_last = (CommonTree)input.LT(1);
					SET566=(CommonTree)match(input,SET,FOLLOW_SET_in_set9485); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					SET566_tree = (CommonTree)adaptor.dupNode(SET566);


					root_1 = (CommonTree)adaptor.becomeRoot(SET566_tree, root_1);
					}

					match(input, Token.DOWN, null); if (state.failed) return retval;
					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_assignmentList_in_set9489);
					assignments=assignmentList();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, assignments.getTree());

					match(input, Token.UP, null); if (state.failed) return retval;
					adaptor.addChild(root_0, root_1);
					_last = _save_last_1;
					}


					if ( state.backtracking==0 ) { retval.value = new SetStatement((assignments!=null?((StatementBuilder.assignmentList_return)assignments).value:null)); }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 2 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:1124:7: ^( SET TRANSIENT assignments= assignmentList )
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					{
					CommonTree _save_last_1 = _last;
					CommonTree _first_1 = null;
					CommonTree root_1 = (CommonTree)adaptor.nil();
					_last = (CommonTree)input.LT(1);
					SET567=(CommonTree)match(input,SET,FOLLOW_SET_in_set9501); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					SET567_tree = (CommonTree)adaptor.dupNode(SET567);


					root_1 = (CommonTree)adaptor.becomeRoot(SET567_tree, root_1);
					}

					match(input, Token.DOWN, null); if (state.failed) return retval;
					_last = (CommonTree)input.LT(1);
					TRANSIENT568=(CommonTree)match(input,TRANSIENT,FOLLOW_TRANSIENT_in_set9503); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					TRANSIENT568_tree = (CommonTree)adaptor.dupNode(TRANSIENT568);


					adaptor.addChild(root_1, TRANSIENT568_tree);
					}

					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_assignmentList_in_set9507);
					assignments=assignmentList();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, assignments.getTree());

					match(input, Token.UP, null); if (state.failed) return retval;
					adaptor.addChild(root_0, root_1);
					_last = _save_last_1;
					}


					if ( state.backtracking==0 ) { retval.value = new SetStatement(SetStatement.SettingType.TRANSIENT, (assignments!=null?((StatementBuilder.assignmentList_return)assignments).value:null)); }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 3 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:1125:7: ^( SET PERSISTENT assignments= assignmentList )
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					{
					CommonTree _save_last_1 = _last;
					CommonTree _first_1 = null;
					CommonTree root_1 = (CommonTree)adaptor.nil();
					_last = (CommonTree)input.LT(1);
					SET569=(CommonTree)match(input,SET,FOLLOW_SET_in_set9519); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					SET569_tree = (CommonTree)adaptor.dupNode(SET569);


					root_1 = (CommonTree)adaptor.becomeRoot(SET569_tree, root_1);
					}

					match(input, Token.DOWN, null); if (state.failed) return retval;
					_last = (CommonTree)input.LT(1);
					PERSISTENT570=(CommonTree)match(input,PERSISTENT,FOLLOW_PERSISTENT_in_set9521); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					PERSISTENT570_tree = (CommonTree)adaptor.dupNode(PERSISTENT570);


					adaptor.addChild(root_1, PERSISTENT570_tree);
					}

					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_assignmentList_in_set9525);
					assignments=assignmentList();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, assignments.getTree());

					match(input, Token.UP, null); if (state.failed) return retval;
					adaptor.addChild(root_0, root_1);
					_last = _save_last_1;
					}


					if ( state.backtracking==0 ) { retval.value = new SetStatement(SetStatement.SettingType.PERSISTENT, (assignments!=null?((StatementBuilder.assignmentList_return)assignments).value:null)); }
					if ( state.backtracking==0 ) {
					}

					}
					break;

			}
			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "set"


	public static class resetStatement_return extends TreeRuleReturnScope {
		public ResetStatement value;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "resetStatement"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:1128:1: resetStatement returns [ResetStatement value] : ^( RESET columns= columnList ) ;
	public final StatementBuilder.resetStatement_return resetStatement() throws RecognitionException {
		StatementBuilder.resetStatement_return retval = new StatementBuilder.resetStatement_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree RESET571=null;
		TreeRuleReturnScope columns =null;

		CommonTree RESET571_tree=null;

		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:1129:5: ( ^( RESET columns= columnList ) )
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:1129:7: ^( RESET columns= columnList )
			{
			root_0 = (CommonTree)adaptor.nil();


			_last = (CommonTree)input.LT(1);
			{
			CommonTree _save_last_1 = _last;
			CommonTree _first_1 = null;
			CommonTree root_1 = (CommonTree)adaptor.nil();
			_last = (CommonTree)input.LT(1);
			RESET571=(CommonTree)match(input,RESET,FOLLOW_RESET_in_resetStatement9550); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			RESET571_tree = (CommonTree)adaptor.dupNode(RESET571);


			root_1 = (CommonTree)adaptor.becomeRoot(RESET571_tree, root_1);
			}

			match(input, Token.DOWN, null); if (state.failed) return retval;
			_last = (CommonTree)input.LT(1);
			pushFollow(FOLLOW_columnList_in_resetStatement9554);
			columns=columnList();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) 
			adaptor.addChild(root_1, columns.getTree());

			match(input, Token.UP, null); if (state.failed) return retval;
			adaptor.addChild(root_0, root_1);
			_last = _save_last_1;
			}


			if ( state.backtracking==0 ) { retval.value = new ResetStatement((columns!=null?((StatementBuilder.columnList_return)columns).value:null)); }
			if ( state.backtracking==0 ) {
			}

			}

			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "resetStatement"


	public static class killStatement_return extends TreeRuleReturnScope {
		public KillStatement value;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "killStatement"
	// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:1132:1: killStatement returns [KillStatement value] : ( ^( KILL ALL ) | ^( KILL parameterOrSimpleLiteral ) );
	public final StatementBuilder.killStatement_return killStatement() throws RecognitionException {
		StatementBuilder.killStatement_return retval = new StatementBuilder.killStatement_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree KILL572=null;
		CommonTree ALL573=null;
		CommonTree KILL574=null;
		TreeRuleReturnScope parameterOrSimpleLiteral575 =null;

		CommonTree KILL572_tree=null;
		CommonTree ALL573_tree=null;
		CommonTree KILL574_tree=null;

		try {
			// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:1133:5: ( ^( KILL ALL ) | ^( KILL parameterOrSimpleLiteral ) )
			int alt155=2;
			int LA155_0 = input.LA(1);
			if ( (LA155_0==KILL) ) {
				int LA155_1 = input.LA(2);
				if ( (LA155_1==DOWN) ) {
					int LA155_2 = input.LA(3);
					if ( (LA155_2==ALL) ) {
						alt155=1;
					}
					else if ( (LA155_2==DECIMAL_VALUE||LA155_2==FALSE||LA155_2==IDENT_EXPR||LA155_2==INTEGER_VALUE||LA155_2==NULL||LA155_2==STRING||LA155_2==TRUE||LA155_2==328||LA155_2==338) ) {
						alt155=2;
					}

					else {
						if (state.backtracking>0) {state.failed=true; return retval;}
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 155, 2, input);
							throw nvae;
						} finally {
							input.rewind(nvaeMark);
						}
					}

				}

				else {
					if (state.backtracking>0) {state.failed=true; return retval;}
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 155, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

			}

			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 155, 0, input);
				throw nvae;
			}

			switch (alt155) {
				case 1 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:1133:7: ^( KILL ALL )
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					{
					CommonTree _save_last_1 = _last;
					CommonTree _first_1 = null;
					CommonTree root_1 = (CommonTree)adaptor.nil();
					_last = (CommonTree)input.LT(1);
					KILL572=(CommonTree)match(input,KILL,FOLLOW_KILL_in_killStatement9579); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					KILL572_tree = (CommonTree)adaptor.dupNode(KILL572);


					root_1 = (CommonTree)adaptor.becomeRoot(KILL572_tree, root_1);
					}

					match(input, Token.DOWN, null); if (state.failed) return retval;
					_last = (CommonTree)input.LT(1);
					ALL573=(CommonTree)match(input,ALL,FOLLOW_ALL_in_killStatement9581); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					ALL573_tree = (CommonTree)adaptor.dupNode(ALL573);


					adaptor.addChild(root_1, ALL573_tree);
					}

					match(input, Token.UP, null); if (state.failed) return retval;
					adaptor.addChild(root_0, root_1);
					_last = _save_last_1;
					}


					if ( state.backtracking==0 ) { retval.value = new KillStatement(); }
					if ( state.backtracking==0 ) {
					}

					}
					break;
				case 2 :
					// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:1134:7: ^( KILL parameterOrSimpleLiteral )
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					{
					CommonTree _save_last_1 = _last;
					CommonTree _first_1 = null;
					CommonTree root_1 = (CommonTree)adaptor.nil();
					_last = (CommonTree)input.LT(1);
					KILL574=(CommonTree)match(input,KILL,FOLLOW_KILL_in_killStatement9593); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					KILL574_tree = (CommonTree)adaptor.dupNode(KILL574);


					root_1 = (CommonTree)adaptor.becomeRoot(KILL574_tree, root_1);
					}

					match(input, Token.DOWN, null); if (state.failed) return retval;
					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_parameterOrSimpleLiteral_in_killStatement9595);
					parameterOrSimpleLiteral575=parameterOrSimpleLiteral();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) 
					adaptor.addChild(root_1, parameterOrSimpleLiteral575.getTree());

					match(input, Token.UP, null); if (state.failed) return retval;
					adaptor.addChild(root_0, root_1);
					_last = _save_last_1;
					}


					if ( state.backtracking==0 ) { retval.value = new KillStatement((parameterOrSimpleLiteral575!=null?((StatementBuilder.parameterOrSimpleLiteral_return)parameterOrSimpleLiteral575).value:null)); }
					if ( state.backtracking==0 ) {
					}

					}
					break;

			}
			if ( state.backtracking==0 ) {

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			}

		}

		    catch (RecognitionException re) {
		        throw new IllegalArgumentException("bad tree from parser: " + getErrorMessage(re, getTokenNames()), re);
		    }

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "killStatement"

	// $ANTLR start synpred1_StatementBuilder
	public final void synpred1_StatementBuilder_fragment() throws RecognitionException {
		// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:232:7: ( ^( ALL_COLUMNS qname ) )
		// D:\\git_repo\\es2\\core/src/main/crate/java/io/crate/sql/parser/StatementBuilder.g:232:8: ^( ALL_COLUMNS qname )
		{
		match(input,ALL_COLUMNS,FOLLOW_ALL_COLUMNS_in_synpred1_StatementBuilder1967); if (state.failed) return;

		match(input, Token.DOWN, null); if (state.failed) return;
		pushFollow(FOLLOW_qname_in_synpred1_StatementBuilder1969);
		qname();
		state._fsp--;
		if (state.failed) return;

		match(input, Token.UP, null); if (state.failed) return;


		}

	}
	// $ANTLR end synpred1_StatementBuilder

	// Delegated rules

	public final boolean synpred1_StatementBuilder() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred1_StatementBuilder_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: "+re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed=false;
		return success;
	}


	protected DFA33 dfa33 = new DFA33(this);
	protected DFA81 dfa81 = new DFA81(this);
	protected DFA120 dfa120 = new DFA120(this);
	protected DFA123 dfa123 = new DFA123(this);
	static final String DFA33_eotS =
		"\12\uffff";
	static final String DFA33_eofS =
		"\12\uffff";
	static final String DFA33_minS =
		"\1\u0120\1\2\1\u00e1\1\2\1\u008f\3\3\2\uffff";
	static final String DFA33_maxS =
		"\1\u0120\1\2\1\u00e1\1\2\3\u00e5\1\u00d2\2\uffff";
	static final String DFA33_acceptS =
		"\10\uffff\1\1\1\2";
	static final String DFA33_specialS =
		"\12\uffff}>";
	static final String[] DFA33_transitionS = {
			"\1\1",
			"\1\2",
			"\1\3",
			"\1\4",
			"\1\5\125\uffff\1\6",
			"\1\7\u008b\uffff\1\5\125\uffff\1\6",
			"\1\7\u008b\uffff\1\5\125\uffff\1\6",
			"\1\11\36\uffff\1\11\u00af\uffff\1\10",
			"",
			""
	};

	static final short[] DFA33_eot = DFA.unpackEncodedString(DFA33_eotS);
	static final short[] DFA33_eof = DFA.unpackEncodedString(DFA33_eofS);
	static final char[] DFA33_min = DFA.unpackEncodedStringToUnsignedChars(DFA33_minS);
	static final char[] DFA33_max = DFA.unpackEncodedStringToUnsignedChars(DFA33_maxS);
	static final short[] DFA33_accept = DFA.unpackEncodedString(DFA33_acceptS);
	static final short[] DFA33_special = DFA.unpackEncodedString(DFA33_specialS);
	static final short[][] DFA33_transition;

	static {
		int numStates = DFA33_transitionS.length;
		DFA33_transition = new short[numStates][];
		for (int i=0; i<numStates; i++) {
			DFA33_transition[i] = DFA.unpackEncodedString(DFA33_transitionS[i]);
		}
	}

	protected class DFA33 extends DFA {

		public DFA33(BaseRecognizer recognizer) {
			this.recognizer = recognizer;
			this.decisionNumber = 33;
			this.eot = DFA33_eot;
			this.eof = DFA33_eof;
			this.min = DFA33_min;
			this.max = DFA33_max;
			this.accept = DFA33_accept;
			this.special = DFA33_special;
			this.transition = DFA33_transition;
		}
		@Override
		public String getDescription() {
			return "308:1: namedTable returns [Table value] : ( ^( TABLE qname ONLY ) | ^( TABLE qname ( assignmentList )? ) );";
		}
	}

	static final String DFA81_eotS =
		"\45\uffff";
	static final String DFA81_eofS =
		"\45\uffff";
	static final String DFA81_minS =
		"\1\u010b\1\2\1\170\2\2\2\u00e1\2\2\2\u008f\10\3\2\2\2\uffff\2\u00e1\2"+
		"\2\2\u008f\10\3";
	static final String DFA81_maxS =
		"\1\u010b\1\2\1\u0096\2\2\2\u00e1\2\2\6\u00e5\2\3\2\u0140\2\2\2\uffff\2"+
		"\u00e1\2\2\6\u00e5\2\3\2\u0140";
	static final String DFA81_acceptS =
		"\25\uffff\1\1\1\2\16\uffff";
	static final String DFA81_specialS =
		"\45\uffff}>";
	static final String[] DFA81_transitionS = {
			"\1\1",
			"\1\2",
			"\1\3\35\uffff\1\4",
			"\1\5",
			"\1\6",
			"\1\7",
			"\1\10",
			"\1\11",
			"\1\12",
			"\1\13\125\uffff\1\14",
			"\1\15\125\uffff\1\16",
			"\1\17\u008b\uffff\1\13\125\uffff\1\14",
			"\1\17\u008b\uffff\1\13\125\uffff\1\14",
			"\1\20\u008b\uffff\1\15\125\uffff\1\16",
			"\1\20\u008b\uffff\1\15\125\uffff\1\16",
			"\1\21",
			"\1\22",
			"\1\25\164\uffff\1\23\35\uffff\1\24\34\uffff\1\25\u008c\uffff\1\26",
			"\1\25\164\uffff\1\23\35\uffff\1\24\34\uffff\1\25\u008c\uffff\1\26",
			"\1\27",
			"\1\30",
			"",
			"",
			"\1\31",
			"\1\32",
			"\1\33",
			"\1\34",
			"\1\35\125\uffff\1\36",
			"\1\37\125\uffff\1\40",
			"\1\41\u008b\uffff\1\35\125\uffff\1\36",
			"\1\41\u008b\uffff\1\35\125\uffff\1\36",
			"\1\42\u008b\uffff\1\37\125\uffff\1\40",
			"\1\42\u008b\uffff\1\37\125\uffff\1\40",
			"\1\43",
			"\1\44",
			"\1\25\u00af\uffff\1\25\u008c\uffff\1\26",
			"\1\25\u00af\uffff\1\25\u008c\uffff\1\26"
	};

	static final short[] DFA81_eot = DFA.unpackEncodedString(DFA81_eotS);
	static final short[] DFA81_eof = DFA.unpackEncodedString(DFA81_eofS);
	static final char[] DFA81_min = DFA.unpackEncodedStringToUnsignedChars(DFA81_minS);
	static final char[] DFA81_max = DFA.unpackEncodedStringToUnsignedChars(DFA81_maxS);
	static final short[] DFA81_accept = DFA.unpackEncodedString(DFA81_acceptS);
	static final short[] DFA81_special = DFA.unpackEncodedString(DFA81_specialS);
	static final short[][] DFA81_transition;

	static {
		int numStates = DFA81_transitionS.length;
		DFA81_transition = new short[numStates][];
		for (int i=0; i<numStates; i++) {
			DFA81_transition[i] = DFA.unpackEncodedString(DFA81_transitionS[i]);
		}
	}

	protected class DFA81 extends DFA {

		public DFA81(BaseRecognizer recognizer) {
			this.recognizer = recognizer;
			this.decisionNumber = 81;
			this.eot = DFA81_eot;
			this.eof = DFA81_eof;
			this.min = DFA81_min;
			this.max = DFA81_max;
			this.accept = DFA81_accept;
			this.special = DFA81_special;
			this.transition = DFA81_transition;
		}
		@Override
		public String getDescription() {
			return "618:1: showColumns returns [Statement value] : ( ^( SHOW_COLUMNS table= fromOrIn (schema= fromOrIn )? (like= likePattern )? ) | ^( SHOW_COLUMNS table= fromOrIn (schema= fromOrIn )? where= whereClause ) );";
		}
	}

	static final String DFA120_eotS =
		"\12\uffff";
	static final String DFA120_eofS =
		"\12\uffff";
	static final String DFA120_minS =
		"\1\103\1\2\1\u00e1\1\2\1\u008f\2\3\1\13\2\uffff";
	static final String DFA120_maxS =
		"\1\103\1\2\1\u00e1\1\2\3\u00e5\1\u0125\2\uffff";
	static final String DFA120_acceptS =
		"\10\uffff\1\1\1\2";
	static final String DFA120_specialS =
		"\12\uffff}>";
	static final String[] DFA120_transitionS = {
			"\1\1",
			"\1\2",
			"\1\3",
			"\1\4",
			"\1\5\125\uffff\1\6",
			"\1\7\u008b\uffff\1\5\125\uffff\1\6",
			"\1\7\u008b\uffff\1\5\125\uffff\1\6",
			"\1\10\u0119\uffff\1\11",
			"",
			""
	};

	static final short[] DFA120_eot = DFA.unpackEncodedString(DFA120_eotS);
	static final short[] DFA120_eof = DFA.unpackEncodedString(DFA120_eofS);
	static final char[] DFA120_min = DFA.unpackEncodedStringToUnsignedChars(DFA120_minS);
	static final char[] DFA120_max = DFA.unpackEncodedStringToUnsignedChars(DFA120_maxS);
	static final short[] DFA120_accept = DFA.unpackEncodedString(DFA120_acceptS);
	static final short[] DFA120_special = DFA.unpackEncodedString(DFA120_specialS);
	static final short[][] DFA120_transition;

	static {
		int numStates = DFA120_transitionS.length;
		DFA120_transition = new short[numStates][];
		for (int i=0; i<numStates; i++) {
			DFA120_transition[i] = DFA.unpackEncodedString(DFA120_transitionS[i]);
		}
	}

	protected class DFA120 extends DFA {

		public DFA120(BaseRecognizer recognizer) {
			this.recognizer = recognizer;
			this.decisionNumber = 120;
			this.eot = DFA120_eot;
			this.eof = DFA120_eof;
			this.min = DFA120_min;
			this.max = DFA120_max;
			this.accept = DFA120_accept;
			this.special = DFA120_special;
			this.transition = DFA120_transition;
		}
		@Override
		public String getDescription() {
			return "915:1: createSnapshot returns [Statement value] : ( ^( CREATE_SNAPSHOT qname ALL ( genericProperties )? ) | ^( CREATE_SNAPSHOT qname tableWithPartitionList ( genericProperties )? ) );";
		}
	}

	static final String DFA123_eotS =
		"\12\uffff";
	static final String DFA123_eofS =
		"\12\uffff";
	static final String DFA123_minS =
		"\1\u00f7\1\2\1\u00e1\1\2\1\u008f\2\3\1\13\2\uffff";
	static final String DFA123_maxS =
		"\1\u00f7\1\2\1\u00e1\1\2\3\u00e5\1\u0125\2\uffff";
	static final String DFA123_acceptS =
		"\10\uffff\1\1\1\2";
	static final String DFA123_specialS =
		"\12\uffff}>";
	static final String[] DFA123_transitionS = {
			"\1\1",
			"\1\2",
			"\1\3",
			"\1\4",
			"\1\5\125\uffff\1\6",
			"\1\7\u008b\uffff\1\5\125\uffff\1\6",
			"\1\7\u008b\uffff\1\5\125\uffff\1\6",
			"\1\10\u0119\uffff\1\11",
			"",
			""
	};

	static final short[] DFA123_eot = DFA.unpackEncodedString(DFA123_eotS);
	static final short[] DFA123_eof = DFA.unpackEncodedString(DFA123_eofS);
	static final char[] DFA123_min = DFA.unpackEncodedStringToUnsignedChars(DFA123_minS);
	static final char[] DFA123_max = DFA.unpackEncodedStringToUnsignedChars(DFA123_maxS);
	static final short[] DFA123_accept = DFA.unpackEncodedString(DFA123_acceptS);
	static final short[] DFA123_special = DFA.unpackEncodedString(DFA123_specialS);
	static final short[][] DFA123_transition;

	static {
		int numStates = DFA123_transitionS.length;
		DFA123_transition = new short[numStates][];
		for (int i=0; i<numStates; i++) {
			DFA123_transition[i] = DFA.unpackEncodedString(DFA123_transitionS[i]);
		}
	}

	protected class DFA123 extends DFA {

		public DFA123(BaseRecognizer recognizer) {
			this.recognizer = recognizer;
			this.decisionNumber = 123;
			this.eot = DFA123_eot;
			this.eof = DFA123_eof;
			this.min = DFA123_min;
			this.max = DFA123_max;
			this.accept = DFA123_accept;
			this.special = DFA123_special;
			this.transition = DFA123_transition;
		}
		@Override
		public String getDescription() {
			return "929:1: restoreSnapshot returns [Statement value] : ( ^( RESTORE_SNAPSHOT qname ALL ( genericProperties )? ) | ^( RESTORE_SNAPSHOT qname tableWithPartitionList ( genericProperties )? ) );";
		}
	}

	public static final BitSet FOLLOW_query_in_statement80 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_explain_in_statement110 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_showTables_in_statement138 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_showSchemas_in_statement163 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_showCatalogs_in_statement187 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_showColumns_in_statement210 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_showPartitions_in_statement234 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_showFunctions_in_statement255 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_showCreateTable_in_statement277 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_createTable_in_statement297 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_createRepository_in_statement321 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_createSnapshot_in_statement340 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_alterTable_in_statement361 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_alterBlobTable_in_statement386 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_createBlobTable_in_statement407 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_createMaterializedView_in_statement427 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_refreshMaterializedView_in_statement440 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_createAlias_in_statement452 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_dropAlias_in_statement476 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_dropTable_in_statement502 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_dropBlobTable_in_statement528 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_dropRepository_in_statement550 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_dropSnapshot_in_statement571 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_dropUser_in_statement594 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_restoreSnapshot_in_statement621 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insert_in_statement641 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_delete_in_statement670 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_update_in_statement699 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_copyFrom_in_statement728 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_copyTo_in_statement755 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_createAnalyzer_in_statement784 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_refresh_in_statement805 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_set_in_statement833 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_resetStatement_in_statement865 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_killStatement_in_statement886 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_createUser_in_statement908 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_alterUser_in_statement933 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_grantStmt_in_statement959 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_revokeStmt_in_statement985 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_showGrantsStmt_in_statement1010 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_showUsersStmt_in_statement1031 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_createClusterStmt_in_statement1058 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_dropClusterStmt_in_statement1077 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_showClustersStmt_in_statement1098 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_alterClusterStmt_in_statement1118 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_migrateTableStmt_in_statement1138 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_QUERY_in_query1171 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_queryExpr_in_query1173 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_withClause_in_queryExpr1197 = new BitSet(new long[]{0x0000000000000000L,0x0000020000000000L,0x0000000200000000L,0x0000001000000000L,0x0040004100000000L});
	public static final BitSet FOLLOW_queryBody_in_queryExpr1206 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0010000000000000L,0x0000000000410000L});
	public static final BitSet FOLLOW_orderClause_in_queryExpr1214 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0010000000000000L,0x0000000000010000L});
	public static final BitSet FOLLOW_limitClause_in_queryExpr1223 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000000000000000L,0x0000000000010000L});
	public static final BitSet FOLLOW_offsetClause_in_queryExpr1232 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_querySpec_in_queryBody1264 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_setOperation_in_queryBody1286 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_tableSubquery_in_queryBody1305 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_namedTable_in_queryBody1323 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_QUERY_SPEC_in_querySpec1358 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_selectClause_in_querySpec1368 = new BitSet(new long[]{0x0000000000000008L,0x0100000000000000L,0x0010000000002400L,0x0000000000410000L,0x0000000000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_fromClause_in_querySpec1378 = new BitSet(new long[]{0x0000000000000008L,0x0000000000000000L,0x0010000000002400L,0x0000000000410000L,0x0000000000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_whereClause_in_querySpec1389 = new BitSet(new long[]{0x0000000000000008L,0x0000000000000000L,0x0010000000002400L,0x0000000000410000L});
	public static final BitSet FOLLOW_groupClause_in_querySpec1400 = new BitSet(new long[]{0x0000000000000008L,0x0000000000000000L,0x0010000000002000L,0x0000000000410000L});
	public static final BitSet FOLLOW_havingClause_in_querySpec1411 = new BitSet(new long[]{0x0000000000000008L,0x0000000000000000L,0x0010000000000000L,0x0000000000410000L});
	public static final BitSet FOLLOW_orderClause_in_querySpec1422 = new BitSet(new long[]{0x0000000000000008L,0x0000000000000000L,0x0010000000000000L,0x0000000000010000L});
	public static final BitSet FOLLOW_limitClause_in_querySpec1433 = new BitSet(new long[]{0x0000000000000008L,0x0000000000000000L,0x0000000000000000L,0x0000000000010000L});
	public static final BitSet FOLLOW_offsetClause_in_querySpec1444 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_UNION_in_setOperation1478 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_queryBody_in_setOperation1482 = new BitSet(new long[]{0x0000000000000000L,0x0000020000000000L,0x0000000200000000L,0x0000001000000000L,0x0040004100000000L});
	public static final BitSet FOLLOW_queryBody_in_setOperation1486 = new BitSet(new long[]{0x0000000000000808L,0x0000000001000000L});
	public static final BitSet FOLLOW_distinct_in_setOperation1490 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_INTERSECT_in_setOperation1509 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_queryBody_in_setOperation1513 = new BitSet(new long[]{0x0000000000000000L,0x0000020000000000L,0x0000000200000000L,0x0000001000000000L,0x0040004100000000L});
	public static final BitSet FOLLOW_queryBody_in_setOperation1517 = new BitSet(new long[]{0x0000000000000808L,0x0000000001000000L});
	public static final BitSet FOLLOW_distinct_in_setOperation1521 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_EXCEPT_in_setOperation1536 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_queryBody_in_setOperation1540 = new BitSet(new long[]{0x0000000000000000L,0x0000020000000000L,0x0000000200000000L,0x0000001000000000L,0x0040004100000000L});
	public static final BitSet FOLLOW_queryBody_in_setOperation1544 = new BitSet(new long[]{0x0000000000000808L,0x0000000001000000L});
	public static final BitSet FOLLOW_distinct_in_setOperation1548 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_selectClause_in_restrictedSelectStmt1578 = new BitSet(new long[]{0x0000000000000000L,0x0100000000000000L});
	public static final BitSet FOLLOW_fromClause_in_restrictedSelectStmt1580 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_WITH_in_withClause1612 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_recursive_in_withClause1614 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000010L});
	public static final BitSet FOLLOW_withList_in_withClause1616 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_RECURSIVE_in_recursive1640 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_WITH_LIST_in_withList1682 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_withQuery_in_withList1686 = new BitSet(new long[]{0x0000000000000008L,0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_WITH_QUERY_in_withQuery1715 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_ident_in_withQuery1719 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000800000000L});
	public static final BitSet FOLLOW_query_in_withQuery1723 = new BitSet(new long[]{0x0000000000000208L});
	public static final BitSet FOLLOW_aliasedColumns_in_withQuery1727 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_SELECT_in_selectClause1753 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_distinct_in_selectClause1757 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000010L});
	public static final BitSet FOLLOW_selectList_in_selectClause1762 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_DIRECTORY_in_copyToTargetSpec1787 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_DISTINCT_in_distinct1829 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ALL_in_distinct1840 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_SELECT_LIST_in_selectList1888 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_selectItem_in_selectList1892 = new BitSet(new long[]{0x0000000000001008L,0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000008L});
	public static final BitSet FOLLOW_SELECT_ITEM_in_selectItem1927 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_expr_in_selectItem1929 = new BitSet(new long[]{0x0000000000000008L,0x0000000000000000L,0x0000000000008000L,0x0000002000000000L});
	public static final BitSet FOLLOW_ident_in_selectItem1931 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_ALL_COLUMNS_in_selectItem1976 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_qname_in_selectItem1978 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_ALL_COLUMNS_in_selectItem1991 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_FROM_in_fromClause2052 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_relationList_in_fromClause2056 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_WHERE_in_whereClause2081 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_expr_in_whereClause2083 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_GROUP_BY_in_groupClause2108 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_exprList_in_groupClause2110 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_HAVING_in_havingClause2135 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_expr_in_havingClause2137 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_ORDER_BY_in_orderClause2162 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_sortItem_in_orderClause2166 = new BitSet(new long[]{0x0000000000000008L,0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000800000L});
	public static final BitSet FOLLOW_SORT_ITEM_in_sortItem2195 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_expr_in_sortItem2197 = new BitSet(new long[]{0x0000000100000000L,0x0000000000080000L});
	public static final BitSet FOLLOW_ordering_in_sortItem2201 = new BitSet(new long[]{0x0000000000000008L,0x0008000000000000L,0x0000400000000000L});
	public static final BitSet FOLLOW_nullOrdering_in_sortItem2205 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_ASC_in_ordering2229 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_DESC_in_ordering2240 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_FIRST_in_nullOrdering2263 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_LAST_in_nullOrdering2273 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_LIMIT_in_limitClause2312 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_integer_in_limitClause2314 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_LIMIT_in_limitClause2326 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_parameterExpr_in_limitClause2328 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_OFFSET_in_offsetClause2353 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_integer_in_offsetClause2355 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_OFFSET_in_offsetClause2367 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_parameterExpr_in_offsetClause2369 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_BERNOULLI_in_sampleType2393 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_SYSTEM_in_sampleType2403 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_STRATIFY_ON_in_stratifyOn2430 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_exprList_in_stratifyOn2432 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_relation_in_relationList2458 = new BitSet(new long[]{0x0000000000000402L,0x0000000000000080L,0x0000040000000000L,0x4000000400000000L,0x0000005100000000L});
	public static final BitSet FOLLOW_relationType_in_relation2484 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_aliasedRelation_in_relation2499 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_sampledRelation_in_relation2511 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_namedTable_in_relationType2536 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_tableSubquery_in_relationType2552 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_joinedTable_in_relationType2565 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_joinRelation_in_relationType2580 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_tableFunction_in_relationType2594 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_TABLE_in_namedTable2621 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_qname_in_namedTable2623 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000040000L});
	public static final BitSet FOLLOW_ONLY_in_namedTable2625 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_TABLE_in_namedTable2637 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_qname_in_namedTable2639 = new BitSet(new long[]{0x0000000400000008L});
	public static final BitSet FOLLOW_assignmentList_in_namedTable2641 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_TABLE_FUNCTION_in_tableFunction2667 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_ident_in_tableFunction2669 = new BitSet(new long[]{0x0002082079000008L,0x2006048000015A00L,0x070801C900541800L,0x0000F00A001046B0L,0x00030C0010100001L,0x000000000026B300L});
	public static final BitSet FOLLOW_exprList_in_tableFunction2671 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_ident_in_repository2703 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_JOINED_TABLE_in_joinedTable2727 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_relation_in_joinedTable2729 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_CROSS_JOIN_in_joinRelation2754 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_relation_in_joinRelation2758 = new BitSet(new long[]{0x0000000000000400L,0x0000000000000080L,0x0000040000000000L,0x4000000400000000L,0x0000005100000000L});
	public static final BitSet FOLLOW_relation_in_joinRelation2762 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_QUALIFIED_JOIN_in_joinRelation2804 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_joinType_in_joinRelation2808 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000020008L,0x0400000000000000L});
	public static final BitSet FOLLOW_joinCriteria_in_joinRelation2812 = new BitSet(new long[]{0x0000000000000400L,0x0000000000000080L,0x0000040000000000L,0x4000000400000000L,0x0000005100000000L});
	public static final BitSet FOLLOW_relation_in_joinRelation2816 = new BitSet(new long[]{0x0000000000000400L,0x0000000000000080L,0x0000040000000000L,0x4000000400000000L,0x0000005100000000L});
	public static final BitSet FOLLOW_relation_in_joinRelation2820 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_ALIASED_RELATION_in_aliasedRelation2845 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_relation_in_aliasedRelation2849 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000008000L,0x0000002000000000L});
	public static final BitSet FOLLOW_ident_in_aliasedRelation2853 = new BitSet(new long[]{0x0000000000000208L});
	public static final BitSet FOLLOW_aliasedColumns_in_aliasedRelation2857 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_SAMPLED_RELATION_in_sampledRelation2883 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_relation_in_sampledRelation2887 = new BitSet(new long[]{0x0000001000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000080000000L});
	public static final BitSet FOLLOW_sampleType_in_sampledRelation2891 = new BitSet(new long[]{0x0002082079000000L,0x2006048000015A00L,0x070801C900541800L,0x0000F00A001046B0L,0x00030C0010100001L,0x000000000026B300L});
	public static final BitSet FOLLOW_expr_in_sampledRelation2895 = new BitSet(new long[]{0x0000000000000008L,0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000004000000L});
	public static final BitSet FOLLOW_stratifyOn_in_sampledRelation2899 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_ALIASED_COLUMNS_in_aliasedColumns2925 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_identList_in_aliasedColumns2927 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_INNER_JOIN_in_joinType2951 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_LEFT_JOIN_in_joinType2961 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_RIGHT_JOIN_in_joinType2972 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_FULL_JOIN_in_joinType2982 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_NATURAL_in_joinCriteria3006 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ON_in_joinCriteria3028 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_expr_in_joinCriteria3030 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_USING_in_joinCriteria3050 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_identList_in_joinCriteria3052 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_TABLE_SUBQUERY_in_tableSubquery3077 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_query_in_tableSubquery3079 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_expr_in_singleExpression3103 = new BitSet(new long[]{0x0000000000000000L});
	public static final BitSet FOLLOW_EOF_in_singleExpression3105 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_parameterOrSimpleLiteral_in_expr3128 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_qname_in_expr3138 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_subscript_in_expr3164 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_functionCall_in_expr3186 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_arithmeticExpression_in_expr3205 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_comparisonExpression_in_expr3216 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_arrayComparisonExpression_in_expr3227 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_AND_in_expr3238 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_expr_in_expr3242 = new BitSet(new long[]{0x0002082079000000L,0x2006048000015A00L,0x070801C900541800L,0x0000F00A001046B0L,0x00030C0010100001L,0x000000000026B300L});
	public static final BitSet FOLLOW_expr_in_expr3246 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_OR_in_expr3259 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_expr_in_expr3263 = new BitSet(new long[]{0x0002082079000000L,0x2006048000015A00L,0x070801C900541800L,0x0000F00A001046B0L,0x00030C0010100001L,0x000000000026B300L});
	public static final BitSet FOLLOW_expr_in_expr3267 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_NOT_in_expr3281 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_expr_in_expr3285 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_DATE_in_expr3305 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_string_in_expr3307 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_TIME_in_expr3326 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_string_in_expr3328 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_TIMESTAMP_in_expr3347 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_string_in_expr3349 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_predicate_in_expr3362 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_IN_LIST_in_expr3385 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_exprList_in_expr3387 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_NEGATIVE_in_expr3401 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_expr_in_expr3405 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_caseExpression_in_expr3419 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_query_in_expr3436 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_extract_in_expr3462 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_current_time_in_expr3486 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_cast_in_expr3505 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_tryCast_in_expr3532 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_arrayLiteral_in_expr3556 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_objectLiteral_in_expr3575 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_expr_in_exprList3608 = new BitSet(new long[]{0x0002082079000002L,0x2006048000015A00L,0x070801C900541800L,0x0000F00A001046B0L,0x00030C0010100001L,0x000000000026B300L});
	public static final BitSet FOLLOW_328_in_parameterExpr3634 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000100000000L});
	public static final BitSet FOLLOW_integer_in_parameterExpr3636 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_338_in_parameterExpr3646 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_NULL_in_parameterOrSimpleLiteral3677 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_parameterExpr_in_parameterOrSimpleLiteral3704 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_string_in_parameterOrSimpleLiteral3722 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_integer_in_parameterOrSimpleLiteral3747 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_decimal_in_parameterOrSimpleLiteral3771 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_TRUE_in_parameterOrSimpleLiteral3795 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_FALSE_in_parameterOrSimpleLiteral3822 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_IDENT_EXPR_in_parameterOrSimpleLiteral3849 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_ident_in_parameterOrSimpleLiteral3851 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_341_in_subscript3881 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_expr_in_subscript3885 = new BitSet(new long[]{0x0002082079000000L,0x2006048000015A00L,0x070801C900541800L,0x0000F00A001046B0L,0x00030C0010100001L,0x000000000026B300L});
	public static final BitSet FOLLOW_expr_in_subscript3889 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_QNAME_in_qname3914 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_identList_in_qname3918 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_qname_in_qnameList3944 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000000000000000L,0x0000000200000000L});
	public static final BitSet FOLLOW_ident_in_identList3972 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000000000008000L,0x0000002000000000L});
	public static final BitSet FOLLOW_IDENT_in_ident4002 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_QUOTED_IDENT_in_ident4021 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_STRING_in_string4046 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_integer_in_numberLiteral4069 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_decimal_in_numberLiteral4093 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INTEGER_VALUE_in_integer4132 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_DECIMAL_VALUE_in_decimal4157 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_FUNCTION_CALL_in_functionCall4181 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_qname_in_functionCall4185 = new BitSet(new long[]{0x0002082079000808L,0x2006048001015A00L,0x070801C900541800L,0x0000F00A001046B0L,0x00030C0010100001L,0x000000000026B304L});
	public static final BitSet FOLLOW_window_in_functionCall4189 = new BitSet(new long[]{0x0002082079000808L,0x2006048001015A00L,0x070801C900541800L,0x0000F00A001046B0L,0x00030C0010100001L,0x000000000026B300L});
	public static final BitSet FOLLOW_distinct_in_functionCall4194 = new BitSet(new long[]{0x0002082079000008L,0x2006048000015A00L,0x070801C900541800L,0x0000F00A001046B0L,0x00030C0010100001L,0x000000000026B300L});
	public static final BitSet FOLLOW_exprList_in_functionCall4199 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_WINDOW_in_window4224 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_windowPartition_in_window4226 = new BitSet(new long[]{0x0000000000000008L,0x0000000000000000L,0x0000000000000000L,0x2000004000400000L});
	public static final BitSet FOLLOW_orderClause_in_window4229 = new BitSet(new long[]{0x0000000000000008L,0x0000000000000000L,0x0000000000000000L,0x2000004000000000L});
	public static final BitSet FOLLOW_windowFrame_in_window4232 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_PARTITION_BY_in_windowPartition4266 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_exprList_in_windowPartition4268 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_RANGE_in_windowFrame4293 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_frameBound_in_windowFrame4297 = new BitSet(new long[]{0x0000000000000008L,0x0020000000000400L,0x0000000000000000L,0x0000000080000000L,0x0030000000000000L});
	public static final BitSet FOLLOW_frameBound_in_windowFrame4301 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_ROWS_in_windowFrame4314 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_frameBound_in_windowFrame4318 = new BitSet(new long[]{0x0000000000000008L,0x0020000000000400L,0x0000000000000000L,0x0000000080000000L,0x0030000000000000L});
	public static final BitSet FOLLOW_frameBound_in_windowFrame4322 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_UNBOUNDED_PRECEDING_in_frameBound4348 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_UNBOUNDED_FOLLOWING_in_frameBound4358 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_CURRENT_ROW_in_frameBound4368 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_PRECEDING_in_frameBound4387 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_expr_in_frameBound4389 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_FOLLOWING_in_frameBound4403 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_expr_in_frameBound4405 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_EXTRACT_in_extract4432 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_expr_in_extract4436 = new BitSet(new long[]{0x0002082079000000L,0x2006048000015A00L,0x070801C900541800L,0x0000F00A001046B0L,0x00030C0010100001L,0x000000000026B300L});
	public static final BitSet FOLLOW_expr_in_extract4440 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_CAST_in_cast4465 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_expr_in_cast4467 = new BitSet(new long[]{0x0000028004000000L,0x0010000004000000L,0x00800010C000000CL,0x0000000000001000L,0x00000800200000A0L});
	public static final BitSet FOLLOW_dataType_in_cast4469 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_TRY_CAST_in_tryCast4494 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_expr_in_tryCast4496 = new BitSet(new long[]{0x0000028004000000L,0x0010000004000000L,0x00800010C000000CL,0x0000000000001000L,0x00000800200000A0L});
	public static final BitSet FOLLOW_dataType_in_tryCast4498 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_CURRENT_DATE_in_current_time4522 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_CURRENT_TIME_in_current_time4550 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_CURRENT_TIMESTAMP_in_current_time4578 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_CURRENT_TIME_in_current_time4602 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_integer_in_current_time4604 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_CURRENT_TIMESTAMP_in_current_time4623 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_integer_in_current_time4625 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_arithmeticType_in_arithmeticExpression4654 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_expr_in_arithmeticExpression4658 = new BitSet(new long[]{0x0002082079000000L,0x2006048000015A00L,0x070801C900541800L,0x0000F00A001046B0L,0x00030C0010100001L,0x000000000026B300L});
	public static final BitSet FOLLOW_expr_in_arithmeticExpression4662 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_333_in_arithmeticType4686 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_335_in_arithmeticType4696 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_332_in_arithmeticType4706 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_337_in_arithmeticType4716 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_329_in_arithmeticType4726 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_comparisonType_in_comparisonExpression4752 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_expr_in_comparisonExpression4756 = new BitSet(new long[]{0x0002082079000000L,0x2006048000015A00L,0x070801C900541800L,0x0000F00A001046B0L,0x00030C0010100001L,0x000000000026B300L});
	public static final BitSet FOLLOW_expr_in_comparisonExpression4760 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_ARRAY_CMP_in_arrayComparisonExpression4785 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_expr_in_arrayComparisonExpression4789 = new BitSet(new long[]{0x0000000000000000L,0x0000008000000000L,0x0300004000001800L,0x0000F00000000020L});
	public static final BitSet FOLLOW_comparisonType_in_arrayComparisonExpression4793 = new BitSet(new long[]{0x0000000002000800L,0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000400000L});
	public static final BitSet FOLLOW_setCompareQuantifier_in_arrayComparisonExpression4797 = new BitSet(new long[]{0x0002082079000000L,0x2006048000015A00L,0x070801C900541800L,0x0000F00A001046B0L,0x00030C0010100001L,0x000000000026B300L});
	public static final BitSet FOLLOW_expr_in_arrayComparisonExpression4801 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_EQ_in_comparisonType4833 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_NEQ_in_comparisonType4862 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_LT_in_comparisonType4890 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_LTE_in_comparisonType4919 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_GT_in_comparisonType4947 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_GTE_in_comparisonType4976 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_IS_DISTINCT_FROM_in_comparisonType5004 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_REGEX_MATCH_in_comparisonType5019 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_REGEX_NO_MATCH_in_comparisonType5037 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_REGEX_MATCH_CI_in_comparisonType5051 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_REGEX_NO_MATCH_CI_in_comparisonType5068 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ANY_in_setCompareQuantifier5095 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_SOME_in_setCompareQuantifier5120 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ALL_in_setCompareQuantifier5144 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_BETWEEN_in_predicate5183 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_expr_in_predicate5187 = new BitSet(new long[]{0x0002082079000000L,0x2006048000015A00L,0x070801C900541800L,0x0000F00A001046B0L,0x00030C0010100001L,0x000000000026B300L});
	public static final BitSet FOLLOW_expr_in_predicate5191 = new BitSet(new long[]{0x0002082079000000L,0x2006048000015A00L,0x070801C900541800L,0x0000F00A001046B0L,0x00030C0010100001L,0x000000000026B300L});
	public static final BitSet FOLLOW_expr_in_predicate5195 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_LIKE_in_predicate5207 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_expr_in_predicate5211 = new BitSet(new long[]{0x0002082079000000L,0x2006048000015A00L,0x070801C900541800L,0x0000F00A001046B0L,0x00030C0010100001L,0x000000000026B300L});
	public static final BitSet FOLLOW_expr_in_predicate5215 = new BitSet(new long[]{0x0002082079000008L,0x2006048000015A00L,0x070801C900541800L,0x0000F00A001046B0L,0x00030C0010100001L,0x000000000026B300L});
	public static final BitSet FOLLOW_expr_in_predicate5219 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_ARRAY_LIKE_in_predicate5236 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_expr_in_predicate5240 = new BitSet(new long[]{0x0000000002000800L,0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000400000L});
	public static final BitSet FOLLOW_setCompareQuantifier_in_predicate5244 = new BitSet(new long[]{0x0002082079000000L,0x2006048000015A00L,0x070801C900541800L,0x0000F00A001046B0L,0x00030C0010100001L,0x000000000026B300L});
	public static final BitSet FOLLOW_expr_in_predicate5248 = new BitSet(new long[]{0x0002082079000008L,0x2006048000015A00L,0x070801C900541800L,0x0000F00A001046B0L,0x00030C0010100001L,0x000000000026B300L});
	public static final BitSet FOLLOW_expr_in_predicate5252 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_ARRAY_NOT_LIKE_in_predicate5307 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_expr_in_predicate5311 = new BitSet(new long[]{0x0000000002000800L,0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000400000L});
	public static final BitSet FOLLOW_setCompareQuantifier_in_predicate5315 = new BitSet(new long[]{0x0002082079000000L,0x2006048000015A00L,0x070801C900541800L,0x0000F00A001046B0L,0x00030C0010100001L,0x000000000026B300L});
	public static final BitSet FOLLOW_expr_in_predicate5319 = new BitSet(new long[]{0x0002082079000008L,0x2006048000015A00L,0x070801C900541800L,0x0000F00A001046B0L,0x00030C0010100001L,0x000000000026B300L});
	public static final BitSet FOLLOW_expr_in_predicate5323 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_IS_NULL_in_predicate5378 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_expr_in_predicate5380 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_IS_NOT_NULL_in_predicate5412 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_expr_in_predicate5414 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_IN_in_predicate5442 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_expr_in_predicate5446 = new BitSet(new long[]{0x0002082079000000L,0x2006048000015A00L,0x070801C900541800L,0x0000F00A001046B0L,0x00030C0010100001L,0x000000000026B300L});
	public static final BitSet FOLLOW_expr_in_predicate5450 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_EXISTS_in_predicate5475 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_query_in_predicate5479 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_MATCH_in_predicate5509 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_matchPredicateIdentList_in_predicate5513 = new BitSet(new long[]{0x0002082079000000L,0x2006048000015A00L,0x070801C900541800L,0x0000F00A001046B0L,0x00030C0010100001L,0x000000000026B300L});
	public static final BitSet FOLLOW_expr_in_predicate5517 = new BitSet(new long[]{0x0000000000000008L,0x0000000000000000L,0x0000000000008001L,0x0000002000000000L});
	public static final BitSet FOLLOW_ident_in_predicate5519 = new BitSet(new long[]{0x0000000000000008L,0x0000000000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_genericProperties_in_predicate5522 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_MATCH_PREDICATE_IDENT_LIST_in_matchPredicateIdentList5548 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_matchPredicateIdent_in_matchPredicateIdentList5551 = new BitSet(new long[]{0x0000000000000008L,0x0000000000000000L,0x0800000000000000L});
	public static final BitSet FOLLOW_MATCH_PREDICATE_IDENT_in_matchPredicateIdent5580 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_expr_in_matchPredicateIdent5582 = new BitSet(new long[]{0x0000000000000008L,0x0004000000010000L,0x0000000100040000L,0x0000000000000200L,0x0001000010000000L,0x0000000000040100L});
	public static final BitSet FOLLOW_parameterOrSimpleLiteral_in_matchPredicateIdent5584 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_NULLIF_in_caseExpression5618 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_expr_in_caseExpression5622 = new BitSet(new long[]{0x0002082079000000L,0x2006048000015A00L,0x070801C900541800L,0x0000F00A001046B0L,0x00030C0010100001L,0x000000000026B300L});
	public static final BitSet FOLLOW_expr_in_caseExpression5626 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_COALESCE_in_caseExpression5653 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_exprList_in_caseExpression5655 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_SIMPLE_CASE_in_caseExpression5685 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_expr_in_caseExpression5689 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x8000000000000000L});
	public static final BitSet FOLLOW_whenList_in_caseExpression5691 = new BitSet(new long[]{0x0002082079000008L,0x2006048000015A00L,0x070801C900541800L,0x0000F00A001046B0L,0x00030C0010100001L,0x000000000026B300L});
	public static final BitSet FOLLOW_expr_in_caseExpression5695 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_SEARCHED_CASE_in_caseExpression5708 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_whenList_in_caseExpression5710 = new BitSet(new long[]{0x0002082079000008L,0x2006048000015A00L,0x070801C900541800L,0x0000F00A001046B0L,0x00030C0010100001L,0x000000000026B300L});
	public static final BitSet FOLLOW_expr_in_caseExpression5714 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_IF_in_caseExpression5732 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_expr_in_caseExpression5736 = new BitSet(new long[]{0x0002082079000000L,0x2006048000015A00L,0x070801C900541800L,0x0000F00A001046B0L,0x00030C0010100001L,0x000000000026B300L});
	public static final BitSet FOLLOW_expr_in_caseExpression5740 = new BitSet(new long[]{0x0002082079000008L,0x2006048000015A00L,0x070801C900541800L,0x0000F00A001046B0L,0x00030C0010100001L,0x000000000026B300L});
	public static final BitSet FOLLOW_expr_in_caseExpression5744 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_WHEN_in_whenList5783 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_expr_in_whenList5787 = new BitSet(new long[]{0x0002082079000000L,0x2006048000015A00L,0x070801C900541800L,0x0000F00A001046B0L,0x00030C0010100001L,0x000000000026B300L});
	public static final BitSet FOLLOW_expr_in_whenList5791 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_EXPLAIN_in_explain5819 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_explainOptions_in_explain5821 = new BitSet(new long[]{0xD8000000004FC020L,0x00000807F004003FL,0x8000200010000080L,0x02B80C0800000000L,0x00800000000FDE20L});
	public static final BitSet FOLLOW_statement_in_explain5824 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_EXPLAIN_OPTIONS_in_explainOptions5849 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_explainOption_in_explainOptions5853 = new BitSet(new long[]{0x0000000000000008L,0x0000500000000000L});
	public static final BitSet FOLLOW_EXPLAIN_FORMAT_in_explainOption5882 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_TEXT_in_explainOption5884 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_EXPLAIN_FORMAT_in_explainOption5901 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_GRAPHVIZ_in_explainOption5903 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_EXPLAIN_TYPE_in_explainOption5916 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_LOGICAL_in_explainOption5918 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_EXPLAIN_TYPE_in_explainOption5934 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_DISTRIBUTED_in_explainOption5936 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_SHOW_TABLES_in_showTables5961 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_fromOrIn_in_showTables5965 = new BitSet(new long[]{0x0000000000000008L,0x0000000000000000L,0x0008000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_likePattern_in_showTables5969 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_whereClause_in_showTables5973 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_SHOW_SCHEMAS_in_showSchemas6000 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_likePattern_in_showSchemas6003 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_whereClause_in_showSchemas6007 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_LIKE_in_likePattern6034 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_string_in_likePattern6036 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_SHOW_CATALOGS_in_showCatalogs6060 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_SHOW_COLUMNS_in_showColumns6084 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_fromOrIn_in_showColumns6088 = new BitSet(new long[]{0x0000000000000008L,0x0100000000000000L,0x0008000000400000L});
	public static final BitSet FOLLOW_fromOrIn_in_showColumns6092 = new BitSet(new long[]{0x0000000000000008L,0x0000000000000000L,0x0008000000000000L});
	public static final BitSet FOLLOW_likePattern_in_showColumns6097 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_SHOW_COLUMNS_in_showColumns6110 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_fromOrIn_in_showColumns6114 = new BitSet(new long[]{0x0000000000000000L,0x0100000000000000L,0x0000000000400000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_fromOrIn_in_showColumns6118 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_whereClause_in_showColumns6123 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_FROM_in_fromOrIn6148 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_qname_in_fromOrIn6150 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_IN_in_fromOrIn6162 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_qname_in_fromOrIn6164 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_SHOW_PARTITIONS_in_showPartitions6189 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_qname_in_showPartitions6191 = new BitSet(new long[]{0x0000000000000008L,0x0000000000000000L,0x0010000000000000L,0x0000000000410000L,0x0000000000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_whereClause_in_showPartitions6193 = new BitSet(new long[]{0x0000000000000008L,0x0000000000000000L,0x0010000000000000L,0x0000000000410000L});
	public static final BitSet FOLLOW_orderClause_in_showPartitions6196 = new BitSet(new long[]{0x0000000000000008L,0x0000000000000000L,0x0010000000000000L,0x0000000000010000L});
	public static final BitSet FOLLOW_limitClause_in_showPartitions6199 = new BitSet(new long[]{0x0000000000000008L,0x0000000000000000L,0x0000000000000000L,0x0000000000010000L});
	public static final BitSet FOLLOW_offsetClause_in_showPartitions6202 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_SHOW_FUNCTIONS_in_showFunctions6235 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_SHOW_CREATE_TABLE_in_showCreateTable6259 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_namedTable_in_showCreateTable6261 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_CREATE_MATERIALIZED_VIEW_in_createMaterializedView6294 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_qname_in_createMaterializedView6296 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000040000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_viewRefresh_in_createMaterializedView6300 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_restrictedSelectStmt_in_createMaterializedView6305 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_REFRESH_MATERIALIZED_VIEW_in_refreshMaterializedView6338 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_qname_in_refreshMaterializedView6340 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_REFRESH_in_viewRefresh6365 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_integer_in_viewRefresh6367 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_CREATE_ALIAS_in_createAlias6392 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_qname_in_createAlias6394 = new BitSet(new long[]{0x0000000000000000L,0x0040000000000000L});
	public static final BitSet FOLLOW_forRemote_in_createAlias6398 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_DROP_ALIAS_in_dropAlias6423 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_qname_in_dropAlias6425 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_FOR_in_forRemote6450 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_qname_in_forRemote6452 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_DROP_BLOB_TABLE_in_dropBlobTable6477 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_namedTable_in_dropBlobTable6479 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_DROP_BLOB_TABLE_in_dropBlobTable6491 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_EXISTS_in_dropBlobTable6493 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000100000000L});
	public static final BitSet FOLLOW_namedTable_in_dropBlobTable6495 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_DROP_TABLE_in_dropTable6520 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_namedTable_in_dropTable6522 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_DROP_TABLE_in_dropTable6534 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_EXISTS_in_dropTable6536 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000100000000L});
	public static final BitSet FOLLOW_namedTable_in_dropTable6538 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_CREATE_USER_in_createUser6567 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_username_in_createUser6569 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000020000L});
	public static final BitSet FOLLOW_IDENTIFIED_BY_in_createUser6571 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000008000L,0x0000002000000000L});
	public static final BitSet FOLLOW_password_in_createUser6573 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_RESET_PASSWORD_in_alterUser6598 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_username_in_alterUser6600 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000008000L,0x0000002000000000L});
	public static final BitSet FOLLOW_password_in_alterUser6602 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_RESET_WHITELIST_in_alterUser6614 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_username_in_alterUser6616 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000002000000000L});
	public static final BitSet FOLLOW_hostWhitelist_in_alterUser6618 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_GRANT_PRIVILEGE_in_grantStmt6647 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_privilege_in_grantStmt6649 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000100000000L});
	public static final BitSet FOLLOW_namedTable_in_grantStmt6651 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000008000L,0x0000002000000000L});
	public static final BitSet FOLLOW_username_in_grantStmt6653 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_REVOKE_PRIVILEGE_in_revokeStmt6682 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_privilege_in_revokeStmt6684 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000100000000L});
	public static final BitSet FOLLOW_namedTable_in_revokeStmt6686 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000008000L,0x0000002000000000L});
	public static final BitSet FOLLOW_username_in_revokeStmt6688 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_SHOW_GRANTS_in_showGrantsStmt6717 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_forUsername_in_showGrantsStmt6719 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_SHOW_USERS_in_showUsersStmt6744 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_READ_ONLY_in_privilege6769 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_READ_WRITE_in_privilege6781 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_FOR_in_forUsername6806 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_username_in_forUsername6808 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_ident_in_username6832 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ident_in_password6855 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_QUOTED_IDENT_in_hostWhitelist6884 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_CREATE_CLUSTER_in_createClusterStmt6913 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_clustername_in_createClusterStmt6915 = new BitSet(new long[]{0x0000000000000008L,0x0000000000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_genericProperties_in_createClusterStmt6917 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_DROP_CLUSTER_in_dropClusterStmt6944 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_clustername_in_dropClusterStmt6946 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_SHOW_CLUSTERS_in_showClustersStmt6975 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_clustername_in_showClustersStmt6977 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_ALTER_CLUSTER_in_alterClusterStmt7007 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_clustername_in_alterClusterStmt7009 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_genericProperties_in_alterClusterStmt7011 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_ALTER_CLUSTER_ADD_NODES_in_alterClusterStmt7023 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_clustername_in_alterClusterStmt7025 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000008000L,0x0000002000000000L});
	public static final BitSet FOLLOW_nodeList_in_alterClusterStmt7027 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_ALTER_CLUSTER_DROP_NODES_in_alterClusterStmt7039 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_clustername_in_alterClusterStmt7041 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000008000L,0x0000002000000000L});
	public static final BitSet FOLLOW_nodeList_in_alterClusterStmt7043 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_ALTER_CLUSTER_DECOMMISSION_NODES_in_alterClusterStmt7055 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_clustername_in_alterClusterStmt7057 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000008000L,0x0000002000000000L});
	public static final BitSet FOLLOW_nodeList_in_alterClusterStmt7059 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_MIGRATE_TABLE_in_migrateTableStmt7088 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_namedTable_in_migrateTableStmt7090 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000008000L,0x0000002000000000L});
	public static final BitSet FOLLOW_clustername_in_migrateTableStmt7092 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_ident_in_clustername7116 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ident_in_nodeList7143 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_DROP_REPOSITORY_in_dropRepository7171 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_repository_in_dropRepository7173 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_DROP_SNAPSHOT_in_dropSnapshot7198 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_qname_in_dropSnapshot7200 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_DROP_USER_in_dropUser7229 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_username_in_dropUser7231 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_INSERT_in_insert7256 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_insertValues_in_insert7260 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000100000000L});
	public static final BitSet FOLLOW_namedTable_in_insert7262 = new BitSet(new long[]{0x0000000000000008L,0x0000000000000000L,0x0000000000080000L,0x0000000000080000L});
	public static final BitSet FOLLOW_columnIdentList_in_insert7266 = new BitSet(new long[]{0x0000000000000008L,0x0000000000000000L,0x0000000000000000L,0x0000000000080000L});
	public static final BitSet FOLLOW_onDuplicateKey_in_insert7269 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_INSERT_in_insert7290 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_query_in_insert7294 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000100000000L});
	public static final BitSet FOLLOW_namedTable_in_insert7296 = new BitSet(new long[]{0x0000000000000008L,0x0000000000000000L,0x0000000000080000L,0x0000000000080000L});
	public static final BitSet FOLLOW_columnIdentList_in_insert7300 = new BitSet(new long[]{0x0000000000000008L,0x0000000000000000L,0x0000000000000000L,0x0000000000080000L});
	public static final BitSet FOLLOW_onDuplicateKey_in_insert7303 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_ON_DUP_KEY_in_onDuplicateKey7337 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_assignmentList_in_onDuplicateKey7339 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_INSERT_VALUES_in_insertValues7364 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_valuesList_in_insertValues7367 = new BitSet(new long[]{0x0000000000000008L,0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x1000000000000000L});
	public static final BitSet FOLLOW_VALUES_LIST_in_valuesList7394 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_exprList_in_valuesList7396 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_DELETE_in_delete7421 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_relation_in_delete7423 = new BitSet(new long[]{0x0000000000000008L,0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_whereClause_in_delete7427 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_UPDATE_in_update7461 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_relation_in_update7463 = new BitSet(new long[]{0x0000000400000000L});
	public static final BitSet FOLLOW_assignmentList_in_update7467 = new BitSet(new long[]{0x0000000000000008L,0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_whereClause_in_update7471 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_ASSIGNMENT_LIST_in_assignmentList7505 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_assignment_in_assignmentList7508 = new BitSet(new long[]{0x0000000200000008L});
	public static final BitSet FOLLOW_ASSIGNMENT_in_assignment7536 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_subscript_in_assignment7538 = new BitSet(new long[]{0x0002082079000000L,0x2006048000015A00L,0x070801C900541800L,0x0000F00A001046B0L,0x00030C0010100001L,0x000000000026B300L});
	public static final BitSet FOLLOW_expr_in_assignment7540 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_ASSIGNMENT_in_assignment7552 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_qname_in_assignment7554 = new BitSet(new long[]{0x0002082079000000L,0x2006048000015A00L,0x070801C900541800L,0x0000F00A001046B0L,0x00030C0010100001L,0x000000000026B300L});
	public static final BitSet FOLLOW_expr_in_assignment7556 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_COPY_TO_in_copyTo7581 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_namedTable_in_copyTo7583 = new BitSet(new long[]{0x0042082079000000L,0x2006048000815A00L,0x070801C900541800L,0x0000F00A001046B0L,0x00030C0010100001L,0x000000000026B301L});
	public static final BitSet FOLLOW_columnList_in_copyTo7585 = new BitSet(new long[]{0x0002082079000000L,0x2006048000815A00L,0x070801C900541800L,0x0000F00A001046B0L,0x00030C0010100001L,0x000000000026B301L});
	public static final BitSet FOLLOW_whereClause_in_copyTo7588 = new BitSet(new long[]{0x0002082079000000L,0x2006048000815A00L,0x070801C900541800L,0x0000F00A001046B0L,0x00030C0010100001L,0x000000000026B300L});
	public static final BitSet FOLLOW_copyToTargetSpec_in_copyTo7593 = new BitSet(new long[]{0x0002082079000000L,0x2006048000015A00L,0x070801C900541800L,0x0000F00A001046B0L,0x00030C0010100001L,0x000000000026B300L});
	public static final BitSet FOLLOW_expr_in_copyTo7596 = new BitSet(new long[]{0x0000000000000008L,0x0000000000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_genericProperties_in_copyTo7598 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_COPY_FROM_in_copyFrom7632 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_namedTable_in_copyFrom7634 = new BitSet(new long[]{0x0002082079000000L,0x2006048000015A00L,0x070801C900541800L,0x0000F00A001046B0L,0x00030C0010100001L,0x000000000026B300L});
	public static final BitSet FOLLOW_expr_in_copyFrom7638 = new BitSet(new long[]{0x0000000000000008L,0x0000000000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_genericProperties_in_copyFrom7640 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_CREATE_BLOB_TABLE_in_createBlobTable7674 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_namedTable_in_createBlobTable7676 = new BitSet(new long[]{0x0000800000000008L,0x0000000000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_clusteredBy_in_createBlobTable7678 = new BitSet(new long[]{0x0000000000000008L,0x0000000000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_genericProperties_in_createBlobTable7681 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_ALTER_BLOB_TABLE_in_alterBlobTable7715 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_genericProperties_in_alterBlobTable7717 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000100000000L});
	public static final BitSet FOLLOW_namedTable_in_alterBlobTable7719 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_ALTER_BLOB_TABLE_in_alterBlobTable7739 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_columnIdentList_in_alterBlobTable7741 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000100000000L});
	public static final BitSet FOLLOW_namedTable_in_alterBlobTable7743 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_ALTER_TABLE_in_alterTable7776 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_genericProperties_in_alterTable7778 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000100000000L});
	public static final BitSet FOLLOW_namedTable_in_alterTable7780 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_ALTER_TABLE_in_alterTable7800 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_columnIdentList_in_alterTable7802 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000100000000L});
	public static final BitSet FOLLOW_namedTable_in_alterTable7804 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_ADD_COLUMN_in_alterTable7824 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_namedTable_in_alterTable7826 = new BitSet(new long[]{0x0000000000000040L});
	public static final BitSet FOLLOW_addColumnDefinition_in_alterTable7828 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_CREATE_TABLE_in_createTable7862 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_namedTable_in_createTable7864 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000800000000L});
	public static final BitSet FOLLOW_tableElementList_in_createTable7866 = new BitSet(new long[]{0x0000800000000008L,0x0000000000000000L,0x0000000000000001L,0x0000000004000000L});
	public static final BitSet FOLLOW_crateTableOptionList_in_createTable7868 = new BitSet(new long[]{0x0000000000000008L,0x0000000000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_genericProperties_in_createTable7870 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_CREATE_TABLE_in_createTable7891 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_EXISTS_in_createTable7893 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000100000000L});
	public static final BitSet FOLLOW_namedTable_in_createTable7895 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000800000000L});
	public static final BitSet FOLLOW_tableElementList_in_createTable7897 = new BitSet(new long[]{0x0000800000000008L,0x0000000000000000L,0x0000000000000001L,0x0000000004000000L});
	public static final BitSet FOLLOW_crateTableOptionList_in_createTable7899 = new BitSet(new long[]{0x0000000000000008L,0x0000000000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_genericProperties_in_createTable7901 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_CREATE_REPOSITORY_in_createRepository7935 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_repository_in_createRepository7937 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000008000L,0x0000002000000000L});
	public static final BitSet FOLLOW_ident_in_createRepository7939 = new BitSet(new long[]{0x0000000000000008L,0x0000000000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_genericProperties_in_createRepository7941 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_CREATE_SNAPSHOT_in_createSnapshot7975 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_qname_in_createSnapshot7977 = new BitSet(new long[]{0x0000000000000800L});
	public static final BitSet FOLLOW_ALL_in_createSnapshot7979 = new BitSet(new long[]{0x0000000000000008L,0x0000000000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_genericProperties_in_createSnapshot7981 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_CREATE_SNAPSHOT_in_createSnapshot8002 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_qname_in_createSnapshot8004 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000002000000000L});
	public static final BitSet FOLLOW_tableWithPartitionList_in_createSnapshot8006 = new BitSet(new long[]{0x0000000000000008L,0x0000000000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_genericProperties_in_createSnapshot8008 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_RESTORE_SNAPSHOT_in_restoreSnapshot8042 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_qname_in_restoreSnapshot8044 = new BitSet(new long[]{0x0000000000000800L});
	public static final BitSet FOLLOW_ALL_in_restoreSnapshot8046 = new BitSet(new long[]{0x0000000000000008L,0x0000000000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_genericProperties_in_restoreSnapshot8048 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_RESTORE_SNAPSHOT_in_restoreSnapshot8069 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_qname_in_restoreSnapshot8071 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000002000000000L});
	public static final BitSet FOLLOW_tableWithPartitionList_in_restoreSnapshot8073 = new BitSet(new long[]{0x0000000000000008L,0x0000000000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_genericProperties_in_restoreSnapshot8075 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_TABLE_ELEMENT_LIST_in_tableElementList8109 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_tableElement_in_tableElementList8112 = new BitSet(new long[]{0x0020000000000008L,0x0000000000000000L,0x0000000000800000L,0x0000000100000000L});
	public static final BitSet FOLLOW_columnDefinition_in_tableElement8140 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_indexDefinition_in_tableElement8150 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_primaryKeyConstraint_in_tableElement8161 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ADD_COLUMN_DEF_in_addColumnDefinition8186 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_expr_in_addColumnDefinition8190 = new BitSet(new long[]{0x02020AA07D000008L,0x2016048004015A00L,0x078801D9C054180CL,0x0000F00A001056B0L,0x00030C00301000A1L,0x000000000026B300L});
	public static final BitSet FOLLOW_expr_in_addColumnDefinition8194 = new BitSet(new long[]{0x0200028004000008L,0x0010000004000000L,0x00800010C000000CL,0x0000000000001000L,0x00000800200000A0L});
	public static final BitSet FOLLOW_dataType_in_addColumnDefinition8197 = new BitSet(new long[]{0x0200000000000008L});
	public static final BitSet FOLLOW_columnConstraints_in_addColumnDefinition8200 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_COLUMN_DEF_in_columnDefinition8233 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_ident_in_columnDefinition8235 = new BitSet(new long[]{0x0002082079000000L,0x2006048000015A00L,0x070801C900541800L,0x0000F00A001046B0L,0x00030C0010100001L,0x000000000026B300L});
	public static final BitSet FOLLOW_expr_in_columnDefinition8237 = new BitSet(new long[]{0x0200028004000008L,0x0010000004000000L,0x00800010C000000CL,0x0000000000001000L,0x00000800200000A0L});
	public static final BitSet FOLLOW_dataType_in_columnDefinition8239 = new BitSet(new long[]{0x0200000000000008L});
	public static final BitSet FOLLOW_columnConstraints_in_columnDefinition8242 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_COLUMN_DEF_in_columnDefinition8262 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_ident_in_columnDefinition8264 = new BitSet(new long[]{0x0000028004000000L,0x0010000004000000L,0x00800010C000000CL,0x0000000000001000L,0x00000800200000A0L});
	public static final BitSet FOLLOW_dataType_in_columnDefinition8266 = new BitSet(new long[]{0x0200000000000008L});
	public static final BitSet FOLLOW_columnConstraints_in_columnDefinition8268 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_set_in_dataType8302 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_objectTypeDefinition_in_dataType8380 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ARRAY_in_dataType8392 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_dataType_in_dataType8396 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_SET_in_dataType8412 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_dataType_in_dataType8416 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_OBJECT_in_objectTypeDefinition8447 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_objectType_in_objectTypeDefinition8451 = new BitSet(new long[]{0x0000000000000008L,0x0000000000000000L,0x0000000000000000L,0x0000000000002000L});
	public static final BitSet FOLLOW_columnDefinitionList_in_objectTypeDefinition8454 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_set_in_objectType8489 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_OBJECT_COLUMNS_in_columnDefinitionList8519 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_columnDefinition_in_columnDefinitionList8523 = new BitSet(new long[]{0x0020000000000008L});
	public static final BitSet FOLLOW_CONSTRAINT_in_columnConstraints8553 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_columnConstraint_in_columnConstraints8555 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_PRIMARY_KEY_in_columnConstraint8582 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INDEX_OFF_in_columnConstraint8592 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INDEX_in_columnConstraint8605 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_ident_in_columnConstraint8609 = new BitSet(new long[]{0x0000000000000008L,0x0000000000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_genericProperties_in_columnConstraint8611 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_GENERIC_PROPERTIES_in_genericProperties8646 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_genericProperty_in_genericProperties8650 = new BitSet(new long[]{0x0000000000000008L,0x0000000000000000L,0x0000000000000002L});
	public static final BitSet FOLLOW_GENERIC_PROPERTY_in_genericProperty8679 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_ident_in_genericProperty8683 = new BitSet(new long[]{0x0002082079000000L,0x2006048000015A00L,0x070801C900541800L,0x0000F00A001046B0L,0x00030C0010100001L,0x000000000026B300L});
	public static final BitSet FOLLOW_expr_in_genericProperty8685 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_ARRAY_LITERAL_in_arrayLiteral8710 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_exprList_in_arrayLiteral8712 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_OBJECT_LITERAL_in_objectLiteral8737 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_objectAttributes_in_objectLiteral8739 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_KEY_VALUE_in_objectAttributes8767 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_ident_in_objectAttributes8771 = new BitSet(new long[]{0x0002082079000000L,0x2006048000015A00L,0x070801C900541800L,0x0000F00A001046B0L,0x00030C0010100001L,0x000000000026B300L});
	public static final BitSet FOLLOW_expr_in_objectAttributes8775 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_INDEX_in_indexDefinition8803 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_ident_in_indexDefinition8807 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000008000L,0x0000002000000000L});
	public static final BitSet FOLLOW_ident_in_indexDefinition8811 = new BitSet(new long[]{0x0040000000000000L});
	public static final BitSet FOLLOW_columnList_in_indexDefinition8813 = new BitSet(new long[]{0x0000000000000008L,0x0000000000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_genericProperties_in_indexDefinition8815 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_IDENT_LIST_in_columnIdentList8849 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_identList_in_columnIdentList8851 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_COLUMN_LIST_in_columnList8876 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_columnListElement_in_columnList8880 = new BitSet(new long[]{0x0000000000000008L,0x0000000000000000L,0x0000000000000000L,0x0000000200000000L,0x0000000000000000L,0x0000000000200000L});
	public static final BitSet FOLLOW_subscript_in_columnListElement8908 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_qname_in_columnListElement8918 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_PRIMARY_KEY_in_primaryKeyConstraint8942 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_columnList_in_primaryKeyConstraint8944 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_crateTableOption_in_crateTableOptionList8970 = new BitSet(new long[]{0x0000800000000002L,0x0000000000000000L,0x0000000000000000L,0x0000000004000000L});
	public static final BitSet FOLLOW_clusteredBy_in_crateTableOption8996 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_partitionedBy_in_crateTableOption9008 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_CLUSTERED_in_clusteredBy9032 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_parameterOrSimpleLiteral_in_clusteredBy9034 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_CLUSTERED_in_clusteredBy9046 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_subscript_in_clusteredBy9048 = new BitSet(new long[]{0x0000000000000008L,0x0004000000010000L,0x0000000100040000L,0x0000000000000200L,0x0001000010000000L,0x0000000000040100L});
	public static final BitSet FOLLOW_parameterOrSimpleLiteral_in_clusteredBy9050 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_CLUSTERED_in_clusteredBy9063 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_qname_in_clusteredBy9065 = new BitSet(new long[]{0x0000000000000008L,0x0004000000010000L,0x0000000100040000L,0x0000000000000200L,0x0001000010000000L,0x0000000000040100L});
	public static final BitSet FOLLOW_parameterOrSimpleLiteral_in_clusteredBy9067 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_PARTITIONED_in_partitionedBy9093 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_columnList_in_partitionedBy9095 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_ANALYZER_in_createAnalyzer9121 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_ident_in_createAnalyzer9123 = new BitSet(new long[]{0x0000000000800000L,0x0001000000000000L});
	public static final BitSet FOLLOW_extendsAnalyzer_in_createAnalyzer9125 = new BitSet(new long[]{0x0000000000800000L});
	public static final BitSet FOLLOW_analyzerElementList_in_createAnalyzer9128 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_EXTENDS_in_extendsAnalyzer9161 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_ident_in_extendsAnalyzer9163 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_ANALYZER_ELEMENTS_in_analyzerElementList9188 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_analyzerElement_in_analyzerElementList9192 = new BitSet(new long[]{0x0000200000000008L,0x0000000000000000L,0x0000000000000002L,0x0000000000000000L,0x0000600000000000L});
	public static final BitSet FOLLOW_TOKENIZER_in_analyzerElement9221 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_namedProperties_in_analyzerElement9223 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_tokenFilters_in_analyzerElement9235 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_charFilters_in_analyzerElement9262 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_genericProperty_in_analyzerElement9290 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_TOKEN_FILTERS_in_tokenFilters9329 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_namedProperties_in_tokenFilters9333 = new BitSet(new long[]{0x0000000000000008L,0x0000000000000000L,0x0000000000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_CHAR_FILTERS_in_charFilters9362 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_namedProperties_in_charFilters9366 = new BitSet(new long[]{0x0000000000000008L,0x0000000000000000L,0x0000000000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_NAMED_PROPERTIES_in_namedProperties9395 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_ident_in_namedProperties9397 = new BitSet(new long[]{0x0000000000000008L,0x0000000000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_genericProperties_in_namedProperties9399 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_REFRESH_in_refresh9425 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_tableWithPartitionList_in_refresh9427 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_TABLE_PARTITION_LIST_in_tableWithPartitionList9453 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_namedTable_in_tableWithPartitionList9457 = new BitSet(new long[]{0x0000000000000008L,0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000100000000L});
	public static final BitSet FOLLOW_SET_in_set9485 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_assignmentList_in_set9489 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_SET_in_set9501 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_TRANSIENT_in_set9503 = new BitSet(new long[]{0x0000000400000000L});
	public static final BitSet FOLLOW_assignmentList_in_set9507 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_SET_in_set9519 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_PERSISTENT_in_set9521 = new BitSet(new long[]{0x0000000400000000L});
	public static final BitSet FOLLOW_assignmentList_in_set9525 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_RESET_in_resetStatement9550 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_columnList_in_resetStatement9554 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_KILL_in_killStatement9579 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_ALL_in_killStatement9581 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_KILL_in_killStatement9593 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_parameterOrSimpleLiteral_in_killStatement9595 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_ALL_COLUMNS_in_synpred1_StatementBuilder1967 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_qname_in_synpred1_StatementBuilder1969 = new BitSet(new long[]{0x0000000000000008L});
}
