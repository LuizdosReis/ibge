package br.com.ibge.converters.impl;

import br.com.ibge.converters.MunicipioConverter;
import br.com.ibge.dto.MunicipioDTO;
import br.com.ibge.models.Municipio;
import org.springframework.stereotype.Component;

@Component
public class MunicipioConverterImpl implements MunicipioConverter {

    @Override
    public MunicipioDTO conveter(Municipio municipio) {
        return MunicipioDTO.builder()
                .idEstado(municipio.getMicrorregiao().getMesorregiao().getUf().getId())
                .nomeCidade(municipio.getNome())
                .nomeMesorregiao(municipio.getMicrorregiao().getMesorregiao().getNome())
                .regiaoNome(municipio.getMicrorregiao().getMesorregiao().getUf().getRegiao().getNome())
                .siglaEstado(municipio.getMicrorregiao().getMesorregiao().getUf().getSigla())
                .nomeFormatado(municipio.getNome() + "/" + municipio.getMicrorregiao().getMesorregiao().getUf().getNome())
                .build();
    }
}
