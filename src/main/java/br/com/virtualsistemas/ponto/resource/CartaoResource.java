package br.com.virtualsistemas.ponto.resource;

import br.com.virtualsistemas.ponto.model.Cartao;
import br.com.virtualsistemas.ponto.service.CartaoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("pontos")
public class CartaoResource {

    @Autowired
    private CartaoServiceImpl cartaoService;

    @PostMapping("valida")
    @ResponseStatus(HttpStatus.OK)
    public Cartao estrairDadosCartaoFoto(@RequestParam("files") MultipartFile file) throws IOException {
        return cartaoService.extrairDadosFoto(file.getBytes());
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Cartao salvarPonto(@RequestBody Cartao cartao) {
        return cartaoService.salvarCartao(cartao);
    }

}
