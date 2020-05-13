2
https://raw.githubusercontent.com/bonamigor/gera_notas/master/src/main/java/br/com/geranotas/controller/ClienteController.java
package br.com.geranotas.controller;

import br.com.geranotas.domain.Cliente;
import br.com.geranotas.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/listar")
    public ModelAndView listar(ModelMap model) {
        model.addAttribute("clientes", clienteService.recuperar());
        return new ModelAndView("/cliente/list", model);
    }

    @GetMapping("/cadastro")
    public String preSalvar(@ModelAttribute("cliente") Cliente cliente) {
        return "/cliente/add";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid @ModelAttribute("cliente") Cliente cliente, BindingResult result, RedirectAttributes attr) {
        if (result.hasErrors()) {
            return "/cliente/add";
        }

        clienteService.salvar(cliente);
        attr.addFlashAttribute("mensagem", "Cliente salvo com sucesso.");
        return "redirect:/clientes/listar";
    }

    @GetMapping("/{id}/atualizar")
    public ModelAndView preAtualizar(@PathVariable("id") Integer id, ModelMap model) {
        Cliente cliente = clienteService.recuperarPorId(id);
        model.addAttribute("cliente", cliente);
        return new ModelAndView("/cliente/add", model);
    }

    @PutMapping("/salvar")
    public ModelAndView atualizar(@Valid @ModelAttribute("cliente") Cliente cliente, BindingResult result, RedirectAttributes attr) {
        if (result.hasErrors()) {
            return new ModelAndView("/cliente/add");
        }

        clienteService.atualizar(cliente);
        attr.addFlashAttribute("mensagem", "Cliente atualizado com sucesso.");
        return new ModelAndView("redirect:/clientes/listar");
    }

    @GetMapping("/{id}/remover")
    public String remover(@PathVariable("id") Integer id, RedirectAttributes attr) {
        clienteService.excluir(id);
        attr.addFlashAttribute("mensagem", "Cliente excluído com sucesso.");
        return "redirect:/clientes/listar";
    }

}
