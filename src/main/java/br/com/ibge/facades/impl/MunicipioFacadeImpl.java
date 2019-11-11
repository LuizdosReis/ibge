package br.com.ibge.facades.impl;

import br.com.ibge.converters.MunicipioConverter;
import br.com.ibge.dto.MunicipioDTO;
import br.com.ibge.facades.MunicipioFacade;
import br.com.ibge.services.MunicipioService;
import br.com.ibge.util.WriteCsvToResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Component
@AllArgsConstructor
public class MunicipioFacadeImpl implements MunicipioFacade {

    private MunicipioService municipioService;

    private MunicipioConverter municipioConverter;

    @Override
    public Set<MunicipioDTO> getByUF(String uf) {
        return municipioService.getByUF(uf)
                .stream()
                .map(municipioConverter::conveter)
                .collect(Collectors.toSet());
    }


    @Override
    public void getCSVByUF(String uf, HttpServletResponse response) throws IOException {
        Set<MunicipioDTO> municipios = getByUF(uf);
        WriteCsvToResponse.writeMunicipios(response.getWriter(), new ArrayList<>(municipios));
    }
}
