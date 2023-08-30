package br.com.alurafood.pagamentos.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "pagamentos")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Positive
    private BigDecimal valor;

    @Size(max = 100)
    private String nome;

    @Size(max = 19)
    private String numero;

    @Size(max = 7)
    private String expiracao;

    @Size(min = 3, max = 3)
    private String codigo;

    @Enumerated(EnumType.STRING)
    private Status status;

    private Long pedidoId;

    private Long formaDePagamentoId;

}
