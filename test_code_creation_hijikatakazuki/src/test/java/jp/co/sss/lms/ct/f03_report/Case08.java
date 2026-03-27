package jp.co.sss.lms.ct.f03_report;

import static jp.co.sss.lms.ct.util.WebDriverUtils.*;
import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import jp.co.sss.lms.ct.util.TestStringUtil;
import jp.co.sss.lms.ct.util.TestUrlUtil;

/**
 * 結合テスト レポート機能
 * ケース08
 * @author holy
 */
@TestMethodOrder(OrderAnnotation.class)
@DisplayName("ケース08 受講生 レポート修正(週報) 正常系")
public class Case08 {

	/** 前処理 */
	@BeforeAll
	static void before() {
		createDriver();
	}

	/** 後処理 */
	@AfterAll
	static void after() {
		closeDriver();
	}

	@Test
	@Order(1)
	@DisplayName("テスト01 トップページURLでアクセス")
	void test01() {
		goTo(TestUrlUtil.TOP_PAGE);

		final String title = webDriver.getTitle();
		final WebElement loginButton = webDriver.findElement(By.className("btn-primary"));
		String button = loginButton.getAttribute("value");

		assertEquals("ログイン | LMS", title);
		assertEquals("ログイン", button);

		getEvidence(new Object() {
		});
	}

	@Test
	@Order(2)
	@DisplayName("テスト02 初回ログイン済みの受講生ユーザーでログイン")
	void test02() {
		// WebElementを利用してログイン処理を行う
		final WebElement userId = webDriver.findElement(By.id("loginId"));
		final WebElement password = webDriver.findElement(By.id("password"));
		final WebElement loginButton = webDriver.findElement(By.className("btn-primary"));

		userId.clear();
		userId.sendKeys(TestStringUtil.STUDENT_PASSWORD);
		password.clear();
		password.sendKeys(TestStringUtil.STUDENT_PASSWORD);
		loginButton.click();

		// タイトルがコース詳細画面になっていることを確認する
		final WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.titleContains("コース詳細 | LMS"));
		final String title = webDriver.getTitle();

		assertEquals("コース詳細 | LMS", title);

		getEvidence(new Object() {
		});
	}

	@Test
	@Order(3)
	@DisplayName("テスト03 提出済の研修日の「詳細」ボタンを押下しセクション詳細画面に遷移")
	void test03() {
		// スケジュールをリストとして取得し未提出ステータスの日付を抽出
		final List<WebElement> dateTable = webDriver.findElements(By.cssSelector("table.sctionList tr"));
		WebElement submitted = null;
		for (WebElement dateElement : dateTable) {
			if (dateElement.getText().contains("提出済")) {
				submitted = dateElement;
				break;
			}
		}
		((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView(false);", submitted);
		((JavascriptExecutor) webDriver).executeScript("window.scrollBy(0, 200);");
		WebElement detailButton = submitted.findElement(By.className("btn"));
		detailButton.click();

		final WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.className("panel")));

		final String sectionDetail = webDriver.getTitle();
		assertEquals(TestStringUtil.SECTION_DETAIL_PAGE, sectionDetail);

		getEvidence(new Object() {
		});
	}

	@Test
	@Order(4)
	@DisplayName("テスト04 「確認する」ボタンを押下しレポート登録画面に遷移")
	void test04() {
		final WebElement comfirmReport = webDriver
				.findElement(By.xpath(TestUrlUtil.REPORT_ALREADY_SUBMITTED_BUTTON));
		((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView(false);", comfirmReport);
		final WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.className("panel")));
		comfirmReport.click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("form-group")));
		final String submitPage = webDriver.getTitle();
		assertEquals(TestStringUtil.SUBMIT_REPORT_PAGE, submitPage);

		getEvidence(new Object() {
		});
	}

	@Test
	@Order(5)
	@DisplayName("テスト05 報告内容を修正して「提出する」ボタンを押下しセクション詳細画面に遷移")
	void test05() {
		final WebElement reportForm = webDriver.findElement(By.className("form-control"));
		final WebElement submitBtn = webDriver.findElement(By.className("btn-primary"));

		reportForm.clear();
		reportForm.sendKeys(TestStringUtil.UPDATE_TEST_TEXT);
		getEvidence(new Object() {
		}, "1");

		submitBtn.click();

		getEvidence(new Object() {
		}, "2");
	}

	@Test
	@Order(6)
	@DisplayName("テスト06 上部メニューの「ようこそ○○さん」リンクからユーザー詳細画面に遷移")
	void test06() {
		final WebElement userName = webDriver.findElement(By.xpath(TestUrlUtil.HEADER_USER_NAME_XPATH));
		userName.click();

		final String title = webDriver.getTitle();

		assertEquals(TestStringUtil.USER_DETAIL_PAGE, title);

		getEvidence(new Object() {
		});
	}

	@Test
	@Order(7)
	@DisplayName("テスト07 該当レポートの「詳細」ボタンを押下しレポート詳細画面で修正内容が反映される")
	void test07() {
		final List<WebElement> dateTable = webDriver
				.findElements(By.xpath("//table[@class='table table-hover'][3]//tr"));
		final int submitListLength = dateTable.size() - 1;
		final WebElement submitted = dateTable.get(submitListLength);

		((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView(true);", submitted);

		final WebElement submitButton = submitted.findElement(By.xpath(".//input[@type='submit' and @value='詳細']"));
		submitButton.click();

		final String title = webDriver.getTitle();
		final WebElement submitedReport = webDriver
				.findElement(By.xpath("//*[@id=\"main\"]/div[1]/table/tbody/tr/td"));
		assertEquals(TestStringUtil.REPORT_DETAIL_PAGE, title);
		assertEquals(TestStringUtil.UPDATE_TEST_TEXT, submitedReport.getText());

		getEvidence(new Object() {
		});
	}

}
