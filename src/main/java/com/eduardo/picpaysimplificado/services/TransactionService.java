package com.eduardo.picpaysimplificado.services;

import com.eduardo.picpaysimplificado.domain.transaction.Transaction;
import com.eduardo.picpaysimplificado.domain.transaction.TransactionDTO;
import com.eduardo.picpaysimplificado.repositories.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class TransactionService {

    private final UserService userService;
    private final RestTemplate restTemplate;
    private final TransactionRepository repository;
    private final NotificationService notificationService;

    public Transaction createTransaction(TransactionDTO transaction) throws Exception {
        var payer = this.userService.findUserById(transaction.payerId());
        var payee = this.userService.findUserById(transaction.payeeId());

        userService.validateUser(payer, transaction.amount());

        boolean isAuthorize = authorizeTransaction();

        if (!isAuthorize){
            throw new Exception("Transação não autorizada.");
        }

        Transaction newTransaction = new Transaction();
        newTransaction.setAmount(transaction.amount());
        newTransaction.setPayer(payer);
        newTransaction.setPayee(payee);
        newTransaction.setTransactionTime(LocalDateTime.now());

        payer.setBalance(payer.getBalance().subtract(transaction.amount()));
        payee.setBalance(payee.getBalance().add(transaction.amount()));

        this.repository.save(newTransaction);
        this.userService.saveUser(payer);
        this.userService.saveUser(payee);

        notificationService.sendNotification(payer,"transação realizada com sucesso");
        notificationService.sendNotification(payee,"transação recebica com sucesso.");

        return newTransaction;
    }

    public boolean authorizeTransaction(){
        var response = restTemplate.getForEntity("https://run.mocky.io/v3/8fafdd68-a090-496f-8c9a-3442cf30dae6", Map.class);

        if (response.getStatusCode() == HttpStatus.OK){
            String message = (String) response.getBody().get("message");
            return "Autorizado".equalsIgnoreCase(message);
        } else return false;
    }
}
