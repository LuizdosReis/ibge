package br.com.ibge.clients;

import br.com.ibge.models.Municipio;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Set;

@FeignClient(name = "estados", url = "${ibge-client.estados}")
public interface EstadoClient {

    @GetMapping("{UF}/municipios")
    Set<Municipio> findMunicipiosByUF(@PathVariable("UF") String uf);
}
