package jp.co.sss.lms.ct.util;

public interface TestUrlUtil {

	public static final String TOP_PAGE = "http://localhost:8080/lms";

	public static final String HELP_XPATH = "/html/body/div[1]/nav/div/div[2]/ul[1]/li[4]/ul/li[4]/a";

	public static final String SEARCH_VALUE_XPATH = "/html/body/div[1]/div/div[1]/div/div[1]/form/fieldset/div[2]/div/input[1]";

	public static final String CLEAR_BUTTON_XPATH = "/html/body/div[1]/div/div[1]/div/div[1]/form/fieldset/div[2]/div/input[2]";

	public static final String FAQ_TABLE_XPATH = "//*[@id=\"question-h[${status.index}]\"]/dt";

	public static final String CATEGORY_SEARCH_TRAINING_XPATH = "/html/body/div[1]/div/div[1]/div/div[1]/fieldset/ul[1]/li/a";

	public static final String CANCELL_FEE_AND_DROP_SCHOOL_XPATH = "//dl[contains(@id,'question-h')]";

	public static final String CANCELL_FEE_AND_DROP_SCHOOL_ANSER_XPATH = "//dd[contains(@id,'answer-h')]";

	public static final String REPORT_SUBMIT_BUTTON = "//input[@type='submit' and @value='日報【デモ】を提出する']";

	public static final String REPORT_ALREADY_SUBMITTED_BUTTON = "//input[@type='submit' and @value='提出済み日報【デモ】を確認する']";

	public static final String HEADER_USER_NAME_XPATH = "//*[@id=\"nav-content\"]/ul[2]/li[2]/a/small";
}