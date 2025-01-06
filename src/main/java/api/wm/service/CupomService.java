package api.wm.service;
import api.wm.domain.entity.Produto;
import api.wm.domain.entity.Venda;
import org.springframework.stereotype.Service;

import javax.print.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class CupomService {

    public void imprimirCupom(Venda venda) throws Exception {
        // Formatar o LocalDateTime
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String dataFormatada = LocalDateTime.now().format(formatter);

        // Criando a string do cupom
        StringBuilder cupom = new StringBuilder();
        cupom.append("\n");
        cupom.append("------ AVIAMENTOS COCHABAMBA -----\n");
        cupom.append("\n");
        cupom.append("Rua, Prof. Cosme Deodato Tadeu, 20\n");
        cupom.append("Guaianases, Sao Paulo - SP, 08450-380\n");
        cupom.append("CNPJ: 49.518.671/0001-38\n\n");
        cupom.append("---------- CUPOM FISCAL ----------\n");
        cupom.append("\n");
        cupom.append("Codigo da Venda: ").append(venda.getId()).append("\n");
        cupom.append("Data: ").append(dataFormatada).append("\n");

        if (venda.getCpf().length() > 10) {
            String cpfFormatado = venda.getCpf().replaceAll("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4");
            cupom.append("CPF do Consumidor: ").append(cpfFormatado).append("\n");
        }

        cupom.append("\n");

        cupom.append("PRODUTO - QUANTIDADE - VALOR UNITARIO\n");
        double total = 0;
        for (Produto produto : venda.getProdutos()) {

            cupom.append(String.format("%-9s %-12.2f %-12s\n", produto.getNome(), produto.getQuantidade(),
                    String.format("R$ %.2f", produto.getPreco())));

            total += produto.getPreco() * produto.getQuantidade();
        }

        cupom.append("\nTOTAL: R$ ").append(String.format("%.2f", total)).append("\n");
        cupom.append("\n");
        cupom.append("----------------------------------\n");
        cupom.append("\n");
        cupom.append("Obrigado pela sua compra!\n");
        cupom.append("Obs: Troca em ate 2 dias a partir\n");
        cupom.append("da data de venda com apresentacao do cupom!\n");

        // Obtenha o serviço de impressão padrão
        PrintService printService = PrintServiceLookup.lookupDefaultPrintService();

        if (printService != null) {
            DocPrintJob printJob = printService.createPrintJob();

            byte[] bytes = cupom.toString().getBytes(StandardCharsets.UTF_8);
            Doc doc = new SimpleDoc(bytes, DocFlavor.BYTE_ARRAY.AUTOSENSE, null);

            printJob.print(doc, null);

            System.out.println("Cupom enviado para a fila de impressão!");
        } else {
            System.err.println("Erro ao detectar a impressora!");
        }
    }
}