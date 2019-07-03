package br.com.virtualsistemas.ponto.resource;

import br.com.virtualsistemas.ponto.model.Cartao;
import br.com.virtualsistemas.ponto.repository.filter.CartaoFilter;
import br.com.virtualsistemas.ponto.service.CartaoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("pontos")
public class CartaoResource {

    @Autowired
    private CartaoServiceImpl cartaoService;


    @GetMapping
    public List<Cartao> pesquisar(CartaoFilter cartaoFilter) {
        return cartaoService.filtrar(cartaoFilter);
    }

    @PostMapping("valida")
    @ResponseStatus(HttpStatus.OK)
    public Cartao extrairDadosFotoCartao(@RequestParam("files") MultipartFile file) throws IOException {
        return cartaoService.extrairDadosFotoCartao(file.getBytes());
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Cartao salvarPonto(@RequestBody Cartao cartao) {
        return cartaoService.salvarCartao(cartao);
    }

}
