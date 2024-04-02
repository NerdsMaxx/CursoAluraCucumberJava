package br.com.alura.leilao.acceptance.steps;

import br.com.alura.leilao.model.Lance;
import br.com.alura.leilao.model.Leilao;
import br.com.alura.leilao.model.Usuario;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import org.junit.Assert;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PropondoLanceSteps {
    
    private Lance lance;
    private Leilao leilao;
    private List<Lance> lanceList;

    @Before
    public void inicializar() {
        lanceList = new ArrayList<>();
        leilao = new Leilao("Tablet XPTO");
    }
    
    
    @Dado("um lance valido")
    public void dado_um_lance_valido() {
        Usuario usuario = new Usuario("fulano");
        lance = new Lance(usuario, BigDecimal.TEN);
    }
    
    @Quando("propoe ao leilao")
    public void quando_propoe_ao_leilao() {
        leilao.propoe(lance);
    }
    
    @Entao("o lance eh aceito")
    public void entao_o_lance_eh_aceito() {
        Assert.assertEquals(1, leilao.getLances().size());
        Assert.assertEquals(BigDecimal.TEN, leilao.getLances().get(0).getValor());
    }
    
    
    @Dado("um lance de {double} reais do usuario {string}")
    public void um_lance_de_reais_do_usuario_fulano(Double valor, String nome) {
        Lance lance = new Lance(new Usuario(nome), BigDecimal.valueOf(valor));
        lanceList.add(lance);
    }
    
    @Quando("propoe varios lances ao leilao")
    public void propoe_varios_lances_ao_leilao() {
        lanceList.forEach(leilao::propoe);
    }
    
    @Entao("os lances sao aceitos")
    public void os_lances_sao_aceitos() {
        Assert.assertEquals(lanceList.size(), leilao.getLances().size());
        Assert.assertEquals(lanceList.get(0).getValor(), leilao.getLances().get(0).getValor());
        Assert.assertEquals(lanceList.get(1).getValor(), leilao.getLances().get(1).getValor());
    }
    
    @Dado("um lance invalido de {double} reais do usuario {string}")
    public void um_lance_invalido_de_reais(Double valor, String nomeUsuario) {
        Assert.assertThrows(
                IllegalArgumentException.class,
                () -> new Lance(new Usuario(nomeUsuario), BigDecimal.valueOf(valor)));
    }
    
    @Entao("o lance nao eh aceito")
    public void o_lance_nao_eh_aceito() {
        Assert.assertEquals(0, leilao.getLances().size());
    }
    
    @Dado("dois lances")
    public void dois_lances_do_mesmo_usuario(DataTable dataTable) {
        final List<Map<String,String>> valores = dataTable.asMaps();
        
        for(final Map<String, String> map : valores) {
            final Usuario usuario = new Usuario(map.get("nomeUsuario"));
            final BigDecimal valor = new BigDecimal(map.get("valor"));
            final Lance lance1 = new Lance(usuario, valor);
            
            lanceList.add(lance1);
        }
    }
    
    @Entao("o segundo lance nao eh aceito")
    public void oSegundoLanceNaoEhAceito() {
        Assert.assertNotEquals(lanceList.size(), leilao.getLances().size());
        Assert.assertEquals(1, leilao.getLances().size());
        Assert.assertEquals(leilao.getLances().get(0), lanceList.get(0));
    }
}