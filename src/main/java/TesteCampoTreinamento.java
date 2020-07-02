import static br.ce.wcaquino.core.DriverFactory.getDriver;
import static br.ce.wcaquino.core.DriverFactory.killDriver;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import br.ce.wcaquino.core.DSL;

public class TesteCampoTreinamento {
	
	private DSL dsl;
	
	@Before
	public void inicializa () {
		getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		dsl = new DSL();
	}
	
	@After
	public void finaliza() {
	killDriver();
	}
	
	// @Test
	// public void teste() {
	//	dsl.escreve("elementosForm:nome", "Teste de Escrita");
	//	Assert.assertEquals("Teste de Escrita", dsl.obterValorCampo("elementosForm:nome"));
	// }

	// @Test
	// public void deveInteragirComTextArea() {	
	//	dsl.escreve("elementosForm:sugestoes", "teste");
	//	Assert.assertEquals("teste", dsl.obterValorCampo("elementosForm:sugestoes"));
	// }
	
	
	@Test
	public void deveInteragirComRadioButton() {
		dsl.clickRadio("elementosForm:sexo:0");
		Assert.assertTrue(dsl.isRadioMarcado("elementosForm:sexo:0"));
	}
	
	
	@Test
	public void deveInteragirComCheckBox() {
		dsl.clicarCheck("elementosForm:comidaFavorita:0");
		Assert.assertTrue(dsl.isCheckMarcado("elementosForm:comidaFavorita:0"));
	}
	
	
	@Test
	public void deveInteragirComCombo() {
		dsl.selecionarCombo("elementosForm:escolaridade", "Superior");
		Assert.assertEquals("Superior", dsl.ObterValorCombo("elementosForm:escolaridade"));
	}	
	
	

	@Test
	public void deveVerificarValoresCombo() {
		WebElement element = getDriver().findElement(By.id("elementosForm:escolaridade"));
		Select combo = new Select(element);
		//List<WebElement> options = combo.getOptions();
		//Assert.assertEquals(8, Options.size());
	
	}

	@Test
	public void deveVerificarValoresComboMultiplo() {
		dsl.selecionarCombo("elementosForm:esportes", "Natacao");
		dsl.selecionarCombo("elementosForm:esportes", "Futebol");
		dsl.selecionarCombo("elementosForm:esportes", "Corrida");
		
		List<String> opcoesMarcadas = dsl.obterValoresCombo("elementosForm:esportes");
		Assert.assertEquals(3, opcoesMarcadas.size());
		
		dsl.deselecionarCombo("elementosForm:esportes", "Corrida");
		opcoesMarcadas = dsl.obterValoresCombo("elementosForm:esportes");
		Assert.assertEquals(2, opcoesMarcadas.size());
		Assert.assertTrue(opcoesMarcadas.containsAll(Arrays.asList("Natacao","Futebol")));				

	}
	
	
	@Test
	public void deveInteragirComBotoes() {
		
		dsl.clicarbotao("buttonSimple");
		Assert.assertEquals("Obrigado!", dsl.obterValueElemento("buttonSimple"));
	}
	
	
	@Test
	public void deveInteragirComLinks() {
		dsl.clicarlink("Voltar");
		
		Assert.assertEquals("Voltou!",dsl.obterTexto("resultado"));
	}	
		
	@Test
	public void deveBuscarTextosnaPagina() {
	
	//	Assert.assertTrue(driver.findElement(By.tagName("Body"))
	//	.getText().contains("Campo de Treinamento"));
		
		//Encontrava-se num H3
		Assert.assertEquals("Campo de Treinamento", dsl.obterTexto(By.tagName("h3")));
		
		//Encontrava-se numa Span
		//Assert.assertEquals("Cuidado onde clica, muitas armadilhas...", dsl.obterTexto(By.className("Span")));
		
		//Encontrava-se numa classe
		Assert.assertEquals("Cuidado onde clica, muitas armadilhas...", dsl.obterTexto(By.className("facilAchar")));

	}
	
	
	//prencher informação sobre a pagina //
	
	@Test
	public void testeJavascript() {
		JavascriptExecutor js = (JavascriptExecutor) getDriver();
		//js.executeScript("alert('Testando js via selenium')");
		js.executeScript("document.getElementById('elementosForm:nome').value = 'Escrito via js'");
		js.executeScript("document.getElementById('elementosForm:sobrenome').type = 'radio'");
		
		WebElement element = getDriver().findElement(By.id("elementosForm:nome"));
		js.executeScript("arguments[0].style.border = arguments[1]", element, "solid 4px red");
	}
	
	
	@Test
	public void deveClicarBotaoTabela () {
	dsl.clicarBotaoTabela("Nome", "Maria", "Botao", "elementosForm:tableUsuarios");
	//dsl.clicarBotaoTabela("Escolaridade", "Mestrado", "Botao", "elementosForm:tableUsuarios");
	//dsl.clicarBotaoTabela("Escolaridade", "Mestrado", "Radio", "elementosForm:tableUsuarios");
		
	}	
}
