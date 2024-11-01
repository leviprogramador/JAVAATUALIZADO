package application.controller;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;
import application.repository.AutorRepository;
import application.model.Autor; // Import Autor model

@Controller
@RequestMapping("/autores")
public class AutorController {
    
    @Autowired 
    private AutorRepository autorRepo;

    @RequestMapping("/list")
    public String list(Model ui) {
        ui.addAttribute("autores", autorRepo.findAll()); 
        return "/autores/list"; 
    }

    @RequestMapping("/insert")
    public String insert() {
        return "autores/insert"; 
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public String insert(@RequestParam("nome") String nome) {
        Autor autor = new Autor();
        autor.setNome(nome);
        autorRepo.save(autor);

        return "redirect:/autores"; 
    }
}
