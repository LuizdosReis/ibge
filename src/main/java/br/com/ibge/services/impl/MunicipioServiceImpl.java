package br.com.ibge.services.impl;

import br.com.ibge.clients.EstadoClient;
import br.com.ibge.clients.MunicipioClient;
import br.com.ibge.exception.ResourceNotFoundException;
import br.com.ibge.models.Municipio;
import br.com.ibge.services.MunicipioService;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@AllArgsConstructor
public class MunicipioServiceImpl implements MunicipioService {

    public static final String MUNICIPIO_NOT_FOUND = "Municipio not found";

    private EstadoClient estadoClient;

    private MunicipioClient municipioClient;

    @Override
    public Set<Municipio> getByUF(String uf) {
        return estadoClient.findMunicipiosByUF(uf);
    }

    @Override
    @Cacheable(value = "IdMunicipios")
    public Long getIdBy(String nomeCidade) {

        Set<Municipio> municipios = municipioClient.findMunicipios();

        return municipios
                .stream()
                .filter(municipio -> municipio.getNome().equals(nomeCidade))
                .findAny()
                .map(Municipio::getId)
                .orElseThrow(() -> new ResourceNotFoundException(MUNICIPIO_NOT_FOUND));
    }
}
