package br.com.fiap.conta.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TB_AGENCIA")
public class Agencia {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_AGENCIA")
    @SequenceGenerator(name = "SQ_AGENCIA", sequenceName = "SQ_AGENCIA")
    @Column(name = "ID_AGENCIA")
    private Long id;

    @Column(name = "NR_AGENCIA")
    private Integer numero;


}
