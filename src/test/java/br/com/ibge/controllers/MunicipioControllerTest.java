package br.com.ibge.controllers;

import br.com.ibge.dto.MunicipioDTO;
import br.com.ibge.facades.MunicipioFacade;
import org.assertj.core.util.Sets;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(MunicipioController.class)
public class MunicipioControllerTest {

    @MockBean
    private MunicipioFacade municipioFacade;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnOneMunicipioWhenFacadeReturnOne() throws Exception {
        MunicipioDTO municipio = MunicipioDTO
                .builder()
                .nomeFormatado("Palhoça/SC")
                .siglaEstado("SC")
                .regiaoNome("Sul")
                .nomeMesorregiao("Grande Florianopolis")
                .nomeCidade("Palhoça")
                .idEstado(42L)
                .build();

        when(municipioFacade.getByUF("SC")).thenReturn(Sets.newLinkedHashSet(municipio));

        mockMvc.perform(get("/municipios/SC").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].nomeFormatado", is(municipio.getNomeFormatado())))
                .andExpect(jsonPath("$[0].siglaEstado", is(municipio.getSiglaEstado())))
                .andExpect(jsonPath("$[0].regiaoNome", is(municipio.getRegiaoNome())))
                .andExpect(jsonPath("$[0].nomeMesorregiao", is(municipio.getNomeMesorregiao())))
                .andExpect(jsonPath("$[0].nomeCidade", is(municipio.getNomeCidade())))
                .andExpect(jsonPath("$[0].idEstado", is(municipio.getIdEstado().intValue())));
    }

    @Test
    public void shouldReturnCsvWhenFacadeReturnOne() throws Exception {
        mockMvc.perform(get("/municipios/SC/csv"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnIdWhenFacadeReturnIt() throws Exception {
        Long idCidade = 324242L;
        String nomeCidade = "Palhoça";

        when(municipioFacade.getIdBy(nomeCidade)).thenReturn(idCidade);

        mockMvc.perform(get("/municipios?nomeCidade=" + nomeCidade))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(idCidade.toString()));
    }
}