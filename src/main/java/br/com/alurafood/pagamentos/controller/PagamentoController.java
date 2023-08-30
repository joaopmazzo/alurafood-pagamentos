package br.com.alurafood.pagamentos.controller;

import br.com.alurafood.pagamentos.dto.PagamentoDTO;
import br.com.alurafood.pagamentos.service.PagamentoService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/pagamento")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PagamentoController {

    private PagamentoService pagamentoService;

    @GetMapping
    public Page<PagamentoDTO> listar(@PageableDefault(size = 10) Pageable pageable) {
        return pagamentoService.obterTodos(pageable);
    }

    @GetMapping("/{id}")
    public PagamentoDTO detalhar(@PathVariable @NotNull Long id) {
        return pagamentoService.obterPorId(id);
    }

    @PostMapping
    public ResponseEntity<PagamentoDTO> cadastrar(@RequestBody @Valid PagamentoDTO pagamentoDTO,
                                                  UriComponentsBuilder uriComponentsBuilder) {
        PagamentoDTO pagamentoCriado = pagamentoService.criarPagamento(pagamentoDTO);
        URI uri = uriComponentsBuilder.path("/pagamentos/{id}").buildAndExpand(pagamentoCriado.getId()).toUri();

        return ResponseEntity.created(uri).body(pagamentoCriado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PagamentoDTO> atualizar(@PathVariable @NotNull Long id,
                                                  @RequestBody @Valid PagamentoDTO pagamentoDTO) {
        PagamentoDTO pagamentoAtualizado = pagamentoService.atualizarPagamento(id, pagamentoDTO);

        return ResponseEntity.ok(pagamentoAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PagamentoDTO> remover(@PathVariable @NotNull Long id) {
        pagamentoService.excluirPagamento(id);

        return ResponseEntity.noContent().build();
    }

}
