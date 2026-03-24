package jp.co.sss.lms.ct.f02_faq;

import static jp.co.sss.lms.ct.util.WebDriverUtils.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import jp.co.sss.lms.ct.util.TestUrlUtil;

/**
 * 結合テスト よくある質問機能
 * ケース05
 * @author holy
 */
@TestMethodOrder(OrderAnnotation.class)
@DisplayName("ケース05 キーワード検索 正常系")
public class Case05 {

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
		userId.sendKeys("StudentAA01");
		password.clear();
		password.sendKeys("StudentAA01");
		loginButton.click();

		// タイトルがコース詳細画面になっていることを確認する
		final String title = webDriver.getTitle();

		assertEquals("コース詳細 | LMS", title);

		getEvidence(new Object() {
		});
	}

	@Test
	@Order(3)
	@DisplayName("テスト03 上部メニューの「ヘルプ」リンクからヘルプ画面に遷移")
	void test03() {
		// WebElementを利用してログイン処理を行う
		final WebElement userId = webDriver.findElement(By.id("loginId"));
		final WebElement password = webDriver.findElement(By.id("password"));
		final WebElement loginButton = webDriver.findElement(By.className("btn-primary"));

		userId.clear();
		userId.sendKeys("StudentAA01");
		password.clear();
		password.sendKeys("StudentAA01");
		loginButton.click();

		// タイトルがコース詳細画面になっていることを確認する
		final String title = webDriver.getTitle();

		assertEquals("コース詳細 | LMS", title);

		getEvidence(new Object() {
		});
	}

	@Test
	@Order(4)
	@DisplayName("テスト04 「よくある質問」リンクからよくある質問画面を別タブに開く")
	void test04() {
		final WebElement faq = webDriver.findElement(By.linkText("よくある質問"));
		faq.click();

		// 新規タブが生成されていることの確認と操作タブの切り替え
		Object[] allTab = webDriver.getWindowHandles().toArray();
		assertEquals(allTab.length, 2);
		webDriver.switchTo().window((String) allTab[1]);

		final WebElement faqPage = webDriver.findElement(By.tagName("h2"));
		assertEquals("よくある質問", faqPage.getText());

		getEvidence(new Object() {
		});
	}

	@Test
	@Order(5)
	@DisplayName("テスト05 キーワード検索で該当キーワードを含む検索結果だけ表示")
	void test05() {
		// WebElementを利用し、テキストフォームから検索を行う
		final WebElement searchForm = webDriver.findElement(By.id("form"));
		final WebElement searchButton = webDriver.findElement(By.xpath(TestUrlUtil.SEARCH_VALUE));

		searchForm.clear();
		searchForm.sendKeys("IT");
		searchButton.click();

		final WebElement grant = webDriver.findElement(By.className(TestUrlUtil.GRANT_VALUE));
	}

	@Test
	@Order(6)
	@DisplayName("テスト06 「クリア」ボタン押下で入力したキーワードを消去")
	void test06() {
		// TODO ここに追加
	}

}
