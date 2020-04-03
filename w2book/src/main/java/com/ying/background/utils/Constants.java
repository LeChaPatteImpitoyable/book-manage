package com.ying.background.utils;

public class Constants {

	public static final int DEFAULT_INDEX = 0;
	public static final int DEFAULT_SIZE = 9;

	public static final String PATH_SIGN = "/";
	public static final String QUERY_SIGN = "?";
	public static final String SEPARATE_SIGN = "&";
	public static final String KEY_VALUE_SIGN = ":";
	public static final String ELEMENT_REGEXP_SIGN = "\\|";
	public static final String POINT_REGEXP_SIGN = "\\.";
	public static final String POINT_SIGN = ".";
	public static final String SEMICOLON_SIGN = ";";
	public static final String TILDE_SIGN = "~";
	public static final String COMMA_SIGN = ",";
	public static final String RENMINBI_SIGN = "￥";
    public static final String ELLIPSIS_SIGN = "...";
	public static final String UNDERLINE = "_";
	public static final String LINE_SEPARATOR = "\r\n";
	
	public static final String TRUE = "1";
	public static final String FALSE = "0";
	public static final int MAX_SIZE = 100;
	public static final int MIN_SIZE = 10;
	
	public static final String SUCCESS = "1";
	public static final String FAIL = "0";

	public static final long DAY = 24 * 60 * 60 * 1000;
	
	public static final String RANDOM_BASE = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	public static class TIME {
		
		public static final long ONE_DAY_MILLS = 24l * 60l * 60l * 1000l;
		public static final long NINETY_DAY_MILLS = 24l * 60l * 60l * 1000l * 90l;
	}

	public static class IS_DELETED{
		public static final int NO = 0;
		public static final int YES = 1;
	}
	
	public static class NUMBER {
		
		public static final int ZERO = 0;
		public static final int ONE = 1;
		public static final int TWO = 2;
		public static final int THREE = 3;
		public static final int FOUR = 4;
		public static final int FIVE = 5;
		public static final int SIX = 6;
		public static final int SEVEN = 7;
		public static final int EIGHT = 8;
		public static final int NINE = 9;
		public static final int TEN = 10;
		public static final int ELEVEN = 11;
		public static final int TWELVE = 12;
		public static final int THIRTEEN = 13;
		public static final int FOURTEEN = 14;
		public static final int TWENTY = 20;
		public static final int FIFTY = 50;
	}
	
	public static class REGEXP {
		
		public static final String NUMBER_REGEXP = "(\\+|-)?[0-9]+";
		public static final String DOUBLE_REGEXP = "(\\+|-)?[0-9]+(\\.[0-9]*)?";
		public static final String PAY_NUMBER_REGEXP = "^[+]?(([1-9]\\d*[.]?)|(0.))(\\d{0,2})?$"; // copy from xmen
		
	}
	
	public static final String UTF8 = "UTF-8";
	
	public static class HTTP_CONTENT_TYPE {
		
		public static final String HEAD_KEY = "Content-type";
		public static final String JSON = "application/json";
		public static final String BIN = "application/octet-stream";
		public static final String FORM = "application/x-www-form-urlencoded";
		public static final String XML = "text/xml";
		public static final String IMAGE = "image/png";
		public static final String HTML = "text/html";
		public static final String CHARSET = "charset";
	}
	
	public static class HTTP_METHOD {
		
		public static final String POST = "POST";
		public static final String GET = "GET";
	}

	public static class JSP_VIEW {
		public static final String REDIRECT = "redirect:/";
		public static final String ADMIN_BOOKS = "admin_books";
		public static final String ADMIN_BOOK_LIST = "admin_book_list";
		public static final String ALLBOOKS = "allbooks.html";
		public static final String READER_QUERYBOOK = "reader_querybook.html";
		public static final String ADMIN_BOOK_DETAIL = "admin_book_detail";
		public static final String READER_BOOK_DETAIL = "reader_book_detail";
		public static final String READER_BOOK_QUERY = "reader_book_query";
		public static final String ADMIN_BOOK_ADD = "admin_book_add";
		public static final String ADMIN_BOOK_EDIT = "admin_book_edit";
	}

	public static final String TOTALCOUNT = "totalCount";
	public static final String BOOKS = "books";
	public static final String DETAIL = "detail";
	public static final String BOOKID = "bookId";
	public static final String ERROR = "error";

	public static  class BOOK_STATE{
		public static final short HAS_BORROWED = 0;//也借出
		public static final short NOT_LEND = 1;//未借出
	}
}
