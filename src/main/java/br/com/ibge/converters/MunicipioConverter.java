package br.com.ibge.converters;

import br.com.ibge.dto.MunicipioDTO;
import br.com.ibge.models.Municipio;

public interface MunicipioConverter {
    MunicipioDTO conveter(Municipio municipio);
}
