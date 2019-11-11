package br.com.ibge.services;

import br.com.ibge.models.Municipio;

import java.util.Set;

public interface MunicipioService {
    Set<Municipio> getByUF(String uf);
}
