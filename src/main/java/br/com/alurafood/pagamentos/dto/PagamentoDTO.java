package br.com.alurafood.pagamentos.dto;

import br.com.alurafood.pagamentos.model.Status;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public class PagamentoDTO {

    private Long id;
    private BigDecimal valor;
    private String nome;
    private String numero;
    private String expiracao;
    private String codigo;
    private Status status;
    private Long pedidoId;
    private Long formaDePagamentoId;

}
