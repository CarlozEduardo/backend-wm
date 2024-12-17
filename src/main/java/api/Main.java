package api;

import javax.print.*;
import javax.print.attribute.*;
import javax.print.attribute.standard.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) {
        try {
            // Formatar o LocalDateTime
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            String dataFormatada = LocalDateTime.now().format(formatter);

            // Criando a string do cupom
            StringBuilder cupom = new StringBuilder();
            cupom.append("------ AVIAMENTOS COCHABAMBA -----\n");
            cupom.append("\n");
            cupom.append("Rua, Prof. Cosme Deodato Tadeu, 118\n");
            cupom.append("Guaianases, Sao Paulo - SP, 08450-380\n");
            cupom.append("\n");
            cupom.append("---------- CUPOM FISCAL ----------\n");
            cupom.append("\n");
            cupom.append("Codigo da Venda: 1\n");
            cupom.append("Data: ").append(dataFormatada).append("\n\n");
            cupom.append("Produto A - Qtd: 2 - Valor: R$ 50,00\n");
            cupom.append("Produto B - Qtd: 1 - Valor: R$ 30,00\n");
            double total = (2 * 50.00) + (1 * 30.00);
            cupom.append("\nTOTAL: R$ ").append(String.format("%.2f", total)).append("\n");
            cupom.append("\n");
            cupom.append("----------------------------------\n");
            cupom.append("\n");
            cupom.append("Obrigado pela sua compra!\n\n");
            cupom.append("Obs: em caso de devolucao mostre o cupom!\n");

            // Obtenha o serviço de impressão padrão
            PrintService printService = PrintServiceLookup.lookupDefaultPrintService();
            DocPrintJob printJob = printService.createPrintJob();

            // Crie o documento a ser impresso
            byte[] bytes = cupom.toString().getBytes("UTF-8");
            Doc doc = new SimpleDoc(bytes, DocFlavor.BYTE_ARRAY.AUTOSENSE, null);

            // Envie o documento para a impressora
            printJob.print(doc, null);

            System.out.println("Cupom enviado para a fila de impressão!");
        } catch (Exception e) {
            System.err.println("Erro ao tentar gerar o cupom: " + e.getMessage());
        }
    }
}
