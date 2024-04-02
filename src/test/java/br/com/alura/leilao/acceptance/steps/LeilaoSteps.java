package br.com.alura.leilao.acceptance.steps;

import br.com.alura.leilao.e2e.pages.Browser;
import br.com.alura.leilao.e2e.pages.LeiloesPage;
import br.com.alura.leilao.e2e.pages.LoginPage;
import br.com.alura.leilao.e2e.pages.NovoLeilaoPage;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import org.junit.Assert;

public class LeilaoSteps {
    
    private Browser broswer;
    private LoginPage loginPage;
    private LeiloesPage leilaoPage;
    private NovoLeilaoPage novoLeilaoPage;
    
    @Dado("um usuario logado")
    public void um_usuario_logado() {
        broswer = new Browser();
        broswer.seed();
        loginPage = broswer.getLoginPage();
        leilaoPage = loginPage.realizaLoginComoFulano();
    }
    
    
    @Quando("acessa a pagina de novo leilao")
    public void acessa_a_pagina_de_novo_leilao() {
        novoLeilaoPage = leilaoPage.visitaPaginaParaCriarUmNovoLeilao();
    }
    
    @Quando("prenche o formulario com dados validos")
    public void prenche_o_formulario_com_dados_validos() {
        leilaoPage = novoLeilaoPage.preencheForm("PC Top", "1500", "01/11/2020");
    }
    
    @Entao("volta para a pagina de leiloes")
    public void volta_para_a_pagina_de_leiloes() {
        Assert.assertTrue(leilaoPage.estaNaPaginaDeLeiloes());
    }
    
    @Entao("o novo leilao aparece na tabela")
    public void o_novo_leilao_aparece_na_tabela() {
        leilaoPage.existe("PC Top", "1500", "01/11/2020", "fulano");
        broswer.clean();
    }
}