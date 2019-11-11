package br.com.ibge.models;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Municipio {

    private Long id;
    private String nome;
    private Microrregiao microrregiao;
}
