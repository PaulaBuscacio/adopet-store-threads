package br.com.alura.adopetstore.email;

import br.com.alura.adopetstore.dto.RelatorioEstoque;
import br.com.alura.adopetstore.dto.RelatorioFaturamento;
import br.com.alura.adopetstore.service.RelatorioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmailRelatorio {
    @Autowired
    private EnviadorEmail enviador;
    @Autowired
    private RelatorioService relatorioService;


    public void enviar(RelatorioEstoque estoque, RelatorioFaturamento faturamento){
        enviador.enviarEmail("Relatório de Estoque e Faturamento",
                "admin@admin.com",
                """
                        Olá!
                        Segue o relatório de estoque e faturamento da Adopet Store:
                        %s
                        %s
                        """.formatted(estoque, faturamento));


    }
}
