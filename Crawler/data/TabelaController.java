2
https://raw.githubusercontent.com/bonamigor/gera_notas/master/src/main/java/br/com/geranotas/controller/TabelaController.java
package br.com.geranotas.controller;

import br.com.geranotas.domain.Cliente;
import br.com.geranotas.domain.Tabela;
import br.com.geranotas.service.ClienteService;
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
@RequestMapping("tabelas")
public class TabelaController {

    @Autowired
    private TabelaService tabelaService;

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/listar")
    public ModelAndView listar(ModelMap model) {
        model.addAttribute("tabelas", tabelaService.recuperar());
        return new ModelAndView("/tabela/list", model);
    }

    @GetMapping("/cadastro")
    public String preSalvar(@ModelAttribute("tabela") Tabela tabela, @ModelAttribute("clientecontrol") Cliente cliente, ModelMap model) {
        model.addAttribute("clientes", clienteService.recuperar());
        return "/tabela/add";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid @ModelAttribute("tabela") Tabela tabela, BindingResult result, RedirectAttributes attr) {
        if (result.hasErrors()) {
            return "/tabela/add";
        }

        tabela.setCliente(clienteService.recuperarPorId(tabela.getCliente().getId()));
        tabelaService.salvar(tabela);
        attr.addFlashAttribute("mensagem", "Tabela criada com sucesso.");
        return "redirect:/tabelas/listar";
    }

    @GetMapping("/{id}/atualizar")
    public ModelAndView preAtualizar(@PathVariable("id") Integer id, ModelMap model) {
        Tabela tabela = tabelaService.recuperarPorId(id);
        model.addAttribute("clientes", clienteService.recuperar());
        model.addAttribute("tabela", tabela);
        return new ModelAndView("/tabela/att", model);
    }

    @PutMapping("/salvar")
    public ModelAndView atualizar(@Valid @ModelAttribute("tabela") Tabela tabela, BindingResult result, RedirectAttributes attr) {
        if (result.hasErrors()) {
            return new ModelAndView("/tabela/add");
        }

        tabela.setCliente(tabela.getCliente());
        tabelaService.atualizar(tabela);
        attr.addFlashAttribute("mensagem", "Tabela atualizada com sucesso.");
        return new ModelAndView("redirect:/tabelas/listar");
    }

    @GetMapping("/{id}/remover")
    public String remover(@PathVariable("id") Integer id, RedirectAttributes attr) {
        tabelaService.excluir(id);
        attr.addFlashAttribute("mensagem", "Tabela excluída com sucesso.");
        return "redirect:/tabelas/listar";
    }

}
