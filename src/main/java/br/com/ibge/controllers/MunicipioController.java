package br.com.ibge.controllers;

import br.com.ibge.dto.MunicipioDTO;
import br.com.ibge.facades.MunicipioFacade;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("municipios")
public class MunicipioController {

    private MunicipioFacade municipioFacade;

    @GetMapping("/{UF}")
    public Set<MunicipioDTO> getByUF(@PathVariable(name = "UF") String uf) {
        return municipioFacade.getByUF(uf);
    }

    @GetMapping(value = "/{UF}/csv", produces = "text/csv")
    public void getCSVByUF(@PathVariable(name = "UF") String uf, HttpServletResponse response) throws IOException {
        municipioFacade.getCSVByUF(uf, response);
    }

    @GetMapping()
    public Long getIdBy(@RequestParam String nomeCidade) {
        return municipioFacade.getIdBy(nomeCidade);
    }
}
