package br.com.ibge.clients;


import br.com.ibge.models.Municipio;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Set;

@FeignClient(name = "municipios", url = "${ibge-client.municipios}")
public interface MunicipioClient {

    @GetMapping()
    Set<Municipio> findMunicipios();
}
