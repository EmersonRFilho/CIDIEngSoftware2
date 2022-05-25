package com.projects.praticandoAPI;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.assertFalse;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.projects.praticandoAPI.controller.HelloWorld;
import com.projects.praticandoAPI.controller.LivroController;
import com.projects.praticandoAPI.modelo.Livro;
import com.projects.praticandoAPI.repository.LivroRepository;
import com.projects.praticandoAPI.services.LivroServices;



@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PraticandoApiApplicationTests {

	@Autowired
    private MockMvc mockMvc;
    
    @Autowired
    LivroRepository livroRepository;

    @Autowired
    LivroServices livroService;

    @Autowired
    private WebApplicationContext webApplicationContext;
		
	@Test
    public void testHelloWorld() throws Exception {
        
        mockMvc
                .perform(MockMvcRequestBuilders.get("/hello"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Hello World!"));
    }

    @Test
    public void testeInsereLivro() throws Exception {
        
        ObjectMapper objectMapper = new ObjectMapper();

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

        mockMvc.perform((MockMvcRequestBuilders.post("/livros")
                .contentType(MediaType.APPLICATION_JSON))
                .content(objectMapper.writeValueAsString(livro))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        mockMvc.perform((MockMvcRequestBuilders.post("/livros")
                .contentType(MediaType.APPLICATION_JSON))
                .content(objectMapper.writeValueAsString(livro2))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

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

