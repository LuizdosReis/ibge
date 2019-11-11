package br.com.ibge.models;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Regiao {

    private Long id;
    private String sigla;
    private String nome;
}
