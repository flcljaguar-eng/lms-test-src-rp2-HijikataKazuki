package jp.co.sss.lms.ct.f01_login1;

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
 * 結合テスト ログイン機能①
 * ケース02
 * @author holy
 */
@TestMethodOrder(OrderAnnotation.class)
@DisplayName("ケース02 受講生 ログイン 認証失敗")
public class Case02 {

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
	@DisplayName("テスト02 DBに登録されていないユーザーでログイン")
	void test02() {
		// WebElementを利用してログイン処理を行う
		final WebElement userId = webDriver.findElement(By.id("loginId"));
		final WebElement password = webDriver.findElement(By.id("password"));
		final WebElement loginButton = webDriver.findElement(By.className("btn-primary"));

		userId.clear();
		userId.sendKeys("NotStudent00");
		password.clear();
		password.sendKeys("password");
		loginButton.click();

		// ヴァリデーションメッセージが正しいことを確認する
		final WebElement validation = webDriver.findElement(By.className("error"));
		String validText = validation.getText();

		assertEquals("* ログインに失敗しました。", validText);

		getEvidence(new Object() {
		});

	}

}
