import static br.ce.wcaquino.core.DriverFactory.getDriver;
import static br.ce.wcaquino.core.DriverFactory.killDriver;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import br.ce.wcaquino.core.DSL;

@RunWith(Parameterized.class)
public class TesteRegrasCadastro {

	private DSL dsl;

	private CampoTreinamentoPage page;
	
	@Parameter
	public String nome;
	@Parameter (value=1)
	public String sobrenome;
	@Parameter (value=2)
	public String sexo;
	@Parameter (value=3)
	public List<String> comidas;
	@Parameter (value=4)
	public String [] esportes;
	@Parameter (value=5)
	public String msg;
	
		@Before
		public void inicializa () {
			getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
			dsl = new DSL();
			page = new CampoTreinamentoPage();
		}
		
		@After
		public void finaliza() {
			killDriver();
		}
		
		
		@Parameters
		public static Collection<Object[]> getCollection(){
		return Arrays.asList(new Object[][]{	
			{"", "", "", Arrays.asList(), new String [] {}, "Nome eh obrigatorio" },
			{"Ana", "", "", Arrays.asList(), new String [] {}, "Sobrenome eh obrigatorio" },
			{"Ana", "Luisa", "", Arrays.asList(), new String [] {}, "Sexo eh obrigatorio" },
			{"Ana", "Luisa", "Feminino", Arrays.asList("Frango","Vegetariano"), new String [] {}, "Tem certeza que voce eh vegetariano?" },
			{"Ana", "Luisa", "Feminino", Arrays.asList("Frango"), new String [] {"Karate", "O que eh esporte?"}, "Voce faz esporte ou nao?" }


		});
		
		}
	
	@Test
	public void DeveValidarRegras() {		
		page.setNome(nome);
		page.setSobrenome(sobrenome);
		if(sexo.equals("Feminino")) {
			page.setSexoFeminino();
		} 
		if(sexo.equals("Masculino")) {
		page.setSexoMasculino();
		}
		if(comidas.contains("Frango")) page.setComidaFrango();
		if(comidas.contains("Vegetariano")) page.setComidaVegetariano();
		page.setComidaFrango();
		page.setEsportes(esportes);
		page.cadastrar();
		System.out.println(msg);
		Assert.assertEquals(msg , dsl.alertaObterTextoEAceita());	
	}	
	
	
	
}
