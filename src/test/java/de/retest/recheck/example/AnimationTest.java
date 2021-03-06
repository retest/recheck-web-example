package de.retest.recheck.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import de.retest.recheck.Recheck;
import de.retest.recheck.RecheckImpl;
import de.retest.recheck.RecheckOptions;

class AnimationTest {
	WebDriver driver;
	Recheck re;

	@BeforeEach
	public void setup() {
		re = new RecheckImpl( RecheckOptions.builder()
				//.reportUploadEnabled( true )
				.build() );

		//System.setProperty( "webdriver.chrome.driver", "C:\chromedriver.exe" );
		driver = new ChromeDriver( Util.chromeDriverOpts() );
		Util.forceContentSize( driver );
	}

	@Test
	public void excel() throws Exception {

		final String file = "pages/animation/animation.html";
		//final String file = "pages/animation/animation2.html";

		driver.get( Util.toClasspathUrl( file ) );
		Util.sleep( 3 );

		re.check( driver, "open" );
		re.capTest(); // Mark test as failed on differences.
	}

	@AfterEach
	public void tearDown() {
		driver.quit();
		re.cap(); // Produce the result file.
	}
}
