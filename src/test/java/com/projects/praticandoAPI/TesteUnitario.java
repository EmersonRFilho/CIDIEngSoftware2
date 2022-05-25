package com.projects.praticandoAPI;

import static org.junit.Assert.assertFalse;

import com.projects.praticandoAPI.modelo.Livro;
import com.projects.praticandoAPI.repository.LivroRepository;
import com.projects.praticandoAPI.services.LivroServices;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TesteUnitario {
    
    @Autowired
    LivroRepository livroRepository;

    @Autowired
    LivroServices livroService;

    @Test
    public void testaMetodoDeNomeIguai() throws Exception{
        Livro livro = new Livro();
        Livro livro2 = new Livro();

        livro.setAutorLivro("Fabio");
        livro.setEditora("editoraFabio");
        livro.setQtdPaginas(100);
        livro.setTitulo("Teste1");

        livro2.setAutorLivro("Fabio");
        livro2.setEditora("editoraFabio");
        livro2.setQtdPaginas(100);
        livro2.setTitulo("Teste1");

        livroRepository.save(livro);

        assertFalse("Livro já cadastrado", livroService.VerificaLivro(livro2));
    }
}

