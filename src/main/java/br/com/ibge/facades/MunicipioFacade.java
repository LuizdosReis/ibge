package br.com.ibge.facades;

import br.com.ibge.dto.MunicipioDTO;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

public interface MunicipioFacade {
    Set<MunicipioDTO> getByUF(String uf);

    void getCSVByUF(String uf, HttpServletResponse response) throws IOException;

    Long getIdBy(String nomeCidade);
}
