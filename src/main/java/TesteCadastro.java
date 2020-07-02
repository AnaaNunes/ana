import static br.ce.wcaquino.core.DriverFactory.getDriver;
import static br.ce.wcaquino.core.DriverFactory.killDriver;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import br.ce.wcaquino.core.DriverFactory;


public class TesteCadastro {
	
private CampoTreinamentoPage page;

	@Before
	public void inicializa () {
		getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		page = new CampoTreinamentoPage();
	}
	
	@After
	public void finaliza() {
		killDriver();
	}
	
	
	@Test
	public void Cadastrar() {		
		page.setNome("Ana");
		page.setSobrenome("Luisa");
		page.setSexoFeminino();
		page.setComidaFrango();
		page.setEscolaridade("Superior");
		page.setEsportes("Corrida");
		page.cadastrar();
		
		
		Assert.assertEquals("Cadastrado!", page.obterResultadoCadastro());
		Assert.assertEquals("Ana", page.obterNomeCadastro());
		Assert.assertEquals("Luisa", page.obterSobrenomeCadastro());
		Assert.assertEquals("Feminino", page.obterSexoCadastro());
		Assert.assertEquals("Frango", page.obterComidaCadastro());
		Assert.assertEquals("superior", page.obterEscolaridadeCadastro());
		Assert.assertEquals("Corrida", page.obterEsporteCadastro());
	}

	@Test
	public void ValidarNomeObrigatorio() {
		page.cadastrar();
		Assert.assertEquals("Nome eh obrigatorio", dsl.alertaObterTextoEAceita());
	}
	
	@Test
	public void ValidarSobreNomeObrigatorio() {		
		page.setNome("Nome qualquer");
		page.cadastrar();
		Assert.assertEquals("Sobrenome eh obrigatorio", dsl.alertaObterTextoEAceita());
	}
	

	@Test
	public void ValidarSexo() {
		page.setNome("Nome qualquer");
		page.setSobrenome("Sobrenome qualquer");
		page.cadastrar();
		Assert.assertEquals("Sexo eh obrigatorio", dsl.alertaObterTextoEAceita());			
	}
	
	
	@Test
	public void ValidarComidaVegetariana() {		
		page.setNome("Nome qualquer");
		page.setSobrenome("Sobrenome qualquer");
		page.setSexoMasculino();
		page.setComidaFrango();
		page.setComidaVegetariano();
		page.cadastrar();
		Assert.assertEquals("Tem certeza que voce eh vegetariano?", dsl.alertaObterTextoEAceita());	
	}
	
	
	@Test
	public void ValidarEsportistaIndeciso() {		
		page.setNome("Nome qualquer");
		page.setSobrenome("Sobrenome qualquer");
		page.setSexoMasculino();
		page.setComidaFrango();
		page.setEsportes("Karate", "O que eh esporte?");
		page.cadastrar();
		Assert.assertEquals("Voce faz esporte ou nao?", dsl.alertaObterTextoEAceita());	
	}
	
}







