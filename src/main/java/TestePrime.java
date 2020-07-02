import static br.ce.wcaquino.core.DriverFactory.getDriver;
import static br.ce.wcaquino.core.DriverFactory.killDriver;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import br.ce.wcaquino.core.DSL;

public class TestePrime {
	private DSL dsl;

		@Before
		public void inicializa () {
			dsl = new DSL();
		}
		
		@After
		public void finaliza() {
		killDriver();
		}
	

	@Test
	public void deveInteragirComRadioPrime() {
		getDriver().get("https://www.primefaces.org/showcase/ui/input/oneRadio.xhtml");
		//dsl.clickRadio(By.xpath("//input[@id='j_idt86:console:0']/../..//span"));
		//Assert.assertTrue(dsl.isRadioMarcado("j_idt86:console:0"));
		
		dsl.clickRadio(By.xpath("//label[.='PS4']/..//span"));
		Assert.assertTrue(dsl.isRadioMarcado("j_idt86:console:1"));
		
	}
	
	/**** mantem a combo aberta*****/
	
	@Test
	public void deveInteragirComSelectPrime() {
		getDriver().get("https://www.primefaces.org/showcase/ui/input/oneMenu.xhtml");
		dsl.selecionarComboPrime("j_idt726:console", "Xbox One");
		Assert.assertEquals("Xbox One", dsl.obterTexto("j_idt726:console_label"));
	}
	
	
	/******Fecha a combo****/
   
	@Test
	public void deveInteragirComSelectPrimeFECHA() throws InterruptedException{
		WebDriver driver = new FirefoxDriver();
		driver.get("https://www.primefaces.org/showcase/ui/input/oneMenu.xhtml");
		driver.findElement(By.xpath("//*[@id='j_idt726:console_input']/../..//span")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id='j_idt726:console_items']//li[.='PS4']")).click();
		Assert.assertEquals("PS4", driver.findElement(By.xpath("j_idt726:console_label")).getText());
	}
}
