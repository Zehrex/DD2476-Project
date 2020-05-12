2
https://raw.githubusercontent.com/bonamigor/gera_notas/master/src/main/java/br/com/geranotas/controller/ProdutoNaTabelaController.java
package br.com.geranotas.controller;

import br.com.geranotas.domain.Produto;
import br.com.geranotas.domain.Tabela;
import br.com.geranotas.service.ProdutoService;
import br.com.geranotas.service.TabelaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("tabelas/{tabelaId}/produtos")
public class ProdutoNaTabelaController {

    @Autowired
    private TabelaService tabelaService;

    @Autowired
    private ProdutoService produtoService;

    @GetMapping("/listar")
    public ModelAndView listar(@PathVariable("tabelaId") Integer tabelaId, ModelMap model) {
        model.addAttribute("produtos", produtoService.recuperarPorTabela(tabelaId));
        model.addAttribute("tabela", tabelaService.recuperarPorId(tabelaId));
        model.addAttribute("tabelaId", tabelaId);
        return new ModelAndView("/produto/list", model);
    }

    @GetMapping("/cadastro_produto")
    public String cadastrarNaTabela(@ModelAttribute("produto") Produto produto, @ModelAttribute("tabela") Tabela tabela, ModelMap model) {
        model.addAttribute("produtos", produtoService.recuperar());
        return "/tabela/add_produto";
    }

    @GetMapping("/cadastro")
    public String preSalvar(@ModelAttribute("produto") Produto produto, @PathVariable("tabelaId") long tabelaId) {
        return "/produto/add";
    }

    @PostMapping("/salvar")
    public String salvar(@PathVariable("tabelaId") Integer tabelaId, @Valid @ModelAttribute("produto")
            Produto produto, BindingResult result, RedirectAttributes attr) {
        if (result.hasErrors()) {
            return "/musica/add";
        }

        produtoService.salvarNaTabela(produto, tabelaId);
        attr.addFlashAttribute("mensagem", "Produto salvo com sucesso.");
        return "redirect:/tabelas/" + tabelaId + "/produtos/listar";
    }

    @GetMapping("/{produtoId}/atualizar")
    public ModelAndView preAtualizar(@PathVariable("tabelaId") Integer tabelaId, @PathVariable("produtoId")
            Integer produtoId, ModelMap model) {
        Produto produto = produtoService.recuperarPorTabelaIdEProdutoId(tabelaId, produtoId);
        model.addAttribute("produto", produto);
        model.addAttribute("tabelaId", tabelaId);
        return new ModelAndView("/produto/add", model);
    }

    @PutMapping("/salvar")
    public ModelAndView atualizar(@PathVariable("tabelaId") Integer tabelaId, @Valid @ModelAttribute("produto")
            Produto produto, BindingResult result, RedirectAttributes attr) {
        if (result.hasErrors()) {
            return new ModelAndView("/produto/add");
        }

        produtoService.atualizarNaTabela(produto, tabelaId);
        attr.addFlashAttribute("mensagem", "Produto atualizado com sucesso.");
        return new ModelAndView("redirect:/tabelas/" + tabelaId + "/produtos/listar");
    }

    @GetMapping("/{produtoId}/remover")
    public String remover(@PathVariable("tabelaId") Integer tabelaId, @PathVariable("produtoId")
            Integer produtoId, RedirectAttributes attr) {
        produtoService.excluirNaTabela(tabelaId, produtoId);
        attr.addFlashAttribute("mensagem", "Produto excluído com sucesso.");
        return "redirect:/tabelas/" + tabelaId + "/produtos/listar";
    }


}
