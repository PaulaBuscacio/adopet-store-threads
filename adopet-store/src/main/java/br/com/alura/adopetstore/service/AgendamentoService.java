package br.com.alura.adopetstore.service;

import br.com.alura.adopetstore.dto.RelatorioEstoque;
import br.com.alura.adopetstore.dto.RelatorioFaturamento;
import br.com.alura.adopetstore.email.EmailRelatorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
public class AgendamentoService {

    @Autowired
    private RelatorioService relatorioService;
    @Autowired
    private EmailRelatorio emailRelatorio;

    @Scheduled(cron = "0 52 10 * * *")
    public void agendarEnvioRelatorio() {

        var estoque = relatorioService.infoEstoque();
        var faturamento = relatorioService.faturamentoObtido();

        CompletableFuture.allOf(estoque, faturamento).join();

        try {
            emailRelatorio.enviar(estoque.get(),
                    faturamento.get());
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }

    }
}
