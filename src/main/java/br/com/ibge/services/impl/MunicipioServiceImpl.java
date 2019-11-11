package br.com.ibge.services.impl;

import br.com.ibge.clients.EstadoClient;
import br.com.ibge.models.Municipio;
import br.com.ibge.services.MunicipioService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@AllArgsConstructor
public class MunicipioServiceImpl implements MunicipioService {

    private EstadoClient estadoClient;

    @Override
    public Set<Municipio> getByUF(String uf) {
        return estadoClient.findMunicipiosByUF(uf);
    }
}
