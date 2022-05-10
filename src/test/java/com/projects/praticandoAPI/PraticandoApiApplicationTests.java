package com.projects.praticandoAPI;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.projects.praticandoAPI.controller.HelloWorld;
import com.projects.praticandoAPI.modelo.Livro;



@RunWith(SpringRunner.class)
//@SpringBootTest 
@WebMvcTest(controllers = HelloWorld.class) 

public class PraticandoApiApplicationTests {

	@Autowired
    private MockMvc mockMvc;

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
    public void testeNomeIgual() throws Exception {

        Livro livro = new Livro();
        
        livro.setAutorLivro("Fabio");
        livro.setEditora("editoraFabio");
        livro.setQtdPaginas(100);
        livro.setTitulo("Teste1");


        ObjectMapper objectMapper = new ObjectMapper();

        mockMvc.perform((MockMvcRequestBuilders.post("/livros")
                .contentType(MediaType.APPLICATION_JSON))
                .content(objectMapper.writeValueAsString(livro))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}

