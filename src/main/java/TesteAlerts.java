import static br.ce.wcaquino.core.DriverFactory.getDriver;
import static br.ce.wcaquino.core.DriverFactory.killDriver;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.ce.wcaquino.core.DSL;


public class TesteAlerts {

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
		
	//ALERTA SIMPLES - APENAS DAVA O OK

	@Test
	public void deveInteragirComAlertasSimples() {		
		dsl.clicarbotao("alert");
		String texto = dsl.alertaObterTextoEAceita();
		Assert.assertEquals("Alert Simples", texto);
		
		dsl.escreve("elementosForm:nome",texto);
	}
	
	
	//ALERTA DE CONFIRMAÇAO - PEDE PARA CONFIRMAR
		
	@Test
	public void deveInteragirComAlertasConfirmacao() {
		dsl.clicarbotao("Confirm");
		Assert.assertEquals("Confirm Simples", dsl.alertaObterTextoEAceita());
		Assert.assertEquals("Confirmado", dsl.alertaObterTextoEAceita());
		
		dsl.clicarbotao("Confirm");
		Assert.assertEquals("Confirm Simples", dsl.alertaObterTextoENega());
		Assert.assertEquals("Negado", dsl.alertaObterTextoENega());
	}
		
	//ALERTA DE PROMPT - PEDE UM NUMERO PARA CONFIRMAR
	
	
		@Test
		public void deveInteragirComAlertasPrompt() {
			dsl.clicarbotao("prompt");
			Assert.assertEquals("Digite um numero", dsl.alertaObterTexto());
			dsl.alertaEscrever("12");
			Assert.assertEquals("Era 12?", dsl.alertaObterTextoEAceita());
			Assert.assertEquals(":0", dsl.alertaObterTextoEAceita());
		}		
}
		
