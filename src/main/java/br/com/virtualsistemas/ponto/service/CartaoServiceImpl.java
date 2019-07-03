package br.com.virtualsistemas.ponto.service;

import br.com.virtualsistemas.ponto.model.Cartao;
import br.com.virtualsistemas.ponto.repository.CartaoRepository;
import br.com.virtualsistemas.ponto.repository.filter.CartaoFilter;
import com.google.cloud.vision.v1.*;
import com.google.protobuf.ByteString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CartaoServiceImpl implements CartaoService {

    @Autowired
    private CartaoRepository cartaoRepository;

    @Override
    public Cartao extrairDadosFotoCartao(byte[] fotoCartao) {
        try {
            ImageAnnotatorClient vision = ImageAnnotatorClient.create();

            byte[] data = fotoCartao;
            ByteString imgBytes = ByteString.copyFrom(data);

            List<AnnotateImageRequest> requests = new ArrayList<>();
            Image img = Image.newBuilder().setContent(imgBytes).build();
            Feature feat = Feature.newBuilder().setType(Feature.Type.TEXT_DETECTION).build();
            AnnotateImageRequest request = AnnotateImageRequest.newBuilder()
                    .addFeatures(feat)
                    .setImage(img)
                    .build();
            requests.add(request);

            BatchAnnotateImagesResponse response = vision.batchAnnotateImages(requests);

            String[] textoSeparado = response.getResponses(0).getFullTextAnnotation().getText().split("\\r?\\n");

            Cartao dadosCartao = new Cartao();
            dadosCartao.setTitulo(textoSeparado[0].concat(" ").concat(textoSeparado[1]));
            dadosCartao.setAparelho(textoSeparado[2]);
            dadosCartao.setNumeroFab(textoSeparado[3].substring(11, textoSeparado[3].length()));
            dadosCartao.setEmpresa(textoSeparado[4]);
            dadosCartao.setCnpj(textoSeparado[5].substring(5, textoSeparado[5].length()));
            dadosCartao.setLocal(textoSeparado[6].substring(6, textoSeparado[6].length()));
            dadosCartao.setNome(textoSeparado[7].substring(5, textoSeparado[7].length()));


            if (textoSeparado[8].contains("PIS:")) {
                String[] pisDataHora = textoSeparado[8].split(" ");
                dadosCartao.setPis(pisDataHora[0].substring(4, pisDataHora[0].length()));

                SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                Date dataTemp = null;
                try {
                    dataTemp = formato.parse(pisDataHora[1]);
                } catch (ParseException e) {
                    e.printStackTrace();
                }


                dadosCartao.setDataPonto(dataTemp);
                dadosCartao.setHoraPonto(pisDataHora[2]);
            }

            dadosCartao.setAssinatura1(textoSeparado[9]);
            dadosCartao.setAssinatura2(textoSeparado[10]);
            dadosCartao.setAssinatura3(textoSeparado[11]);
            dadosCartao.setNsr(textoSeparado[12].substring(4, textoSeparado[12].length()));

            return dadosCartao;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Cartao();
    }

    @Override
    public Cartao salvarCartao(Cartao cartao) {
        return cartaoRepository.save(cartao);
    }

    @Override
    public List<Cartao> filtrar(CartaoFilter cartaoFilter) {
        return cartaoRepository.filtrar(cartaoFilter);
    }
}
