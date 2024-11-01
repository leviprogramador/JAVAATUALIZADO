package application.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashSet;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import application.model.Livro;
import application.model.Autor; 
import application.repository.EditoraRepository;
import application.repository.GeneroRepository;
import application.repository.LivroRepository;
import application.repository.AutorRepository; 

@Controller
@RequestMapping(value = {"/livros", "/"})
public class LivroController {
    
    @Autowired
    private LivroRepository livroRepo;

    @Autowired
    private GeneroRepository generoRepo;

    @Autowired
    private EditoraRepository editoraRepo;

    @Autowired
    private AutorRepository autorRepo; 

    @RequestMapping("/insert")
    public String insert(Model ui) {
        ui.addAttribute("generos", generoRepo.findAll());
        ui.addAttribute("editoras", editoraRepo.findAll());
        ui.addAttribute("autores", autorRepo.findAll()); 
        return "livros/insert";
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public String insert(
        @RequestParam("titulo") String titulo,
        @RequestParam("genero") long idGenero,
        @RequestParam("editora") long idEditora,
        @RequestParam("autores") long[] idsAutores
    ) {
        Livro livro = new Livro();
        livro.setTitulo(titulo);
        livro.setGenero(generoRepo.findById(idGenero).orElse(null));
        livro.setEditora(editoraRepo.findById(idEditora).orElse(null));

        for (long idAutor : idsAutores) {
            Autor autor = autorRepo.findById(idAutor).orElse(null);
            if (autor != null) {
                livro.getAutores().add(autor); 
            }
        }

        livroRepo.save(livro);
        return "redirect:/livros/list"; 
    }

    @RequestMapping(value = {"/list", ""})
    public String list(Model ui) {
        ui.addAttribute("livros", livroRepo.findAll());
        return "livros/list";
    }

    @RequestMapping("/delete")
    public String delete(@RequestParam("id") long id, Model ui) {
        Optional<Livro> resultado = livroRepo.findById(id);
        if (resultado.isPresent()) {
            ui.addAttribute("livro", resultado.get());
            return "livros/delete";
        }
        return "redirect:/livros/list"; // Mudado para redirect
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String deletePost(@RequestParam("id") long id) {
        livroRepo.deleteById(id);
        return "redirect:/livros/list"; // Use redirect para evitar duplicação de envio
    }

    @RequestMapping("/update")
    public String update(Model ui, @RequestParam("id") long id) {
        Optional<Livro> resultado = livroRepo.findById(id);
        if (resultado.isPresent()) {
            ui.addAttribute("livro", resultado.get());
            ui.addAttribute("generos", generoRepo.findAll());
            ui.addAttribute("editoras", editoraRepo.findAll());
            ui.addAttribute("autores", autorRepo.findAll()); // Adicionei os autores
            return "livros/update";
        }
        return "redirect:/livros/list"; // Mudado para redirect
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updatePost(
        @RequestParam("id") long id,
        @RequestParam("titulo") String titulo,
        @RequestParam("genero") long idGenero,
        @RequestParam("editora") long idEditora,
        @RequestParam("autores") long[] idsAutores // Corrigi o nome do parâmetro
    ) {
        Optional<Livro> resultado = livroRepo.findById(id);
        if (resultado.isPresent()) {
            Livro livro = resultado.get();
            livro.setTitulo(titulo);
            livro.setGenero(generoRepo.findById(idGenero).orElse(null));
            livro.setEditora(editoraRepo.findById(idEditora).orElse(null));

            livro.setAutores(new HashSet<Autor>());
            for (long idAutor : idsAutores) {
                Autor autor = autorRepo.findById(idAutor).orElse(null);
                if (autor != null) {
                    livro.getAutores().add(autor); 
                }
            }

            livroRepo.save(livro);
        }
        return "redirect:/livros/list"; 
    }
}
