package br.com.ibge.models;


import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Mesorregiao {

    private Long id;
    private String nome;

    @JsonAlias("UF")
    private UF uf;
}
