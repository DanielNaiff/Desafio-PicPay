package com.danielnaiff.backend.service;

import com.danielnaiff.backend.Exception.TranferNotValidated;
import com.danielnaiff.backend.entity.DTO.TranferRequestDTO;
import com.danielnaiff.backend.entity.Transfer;
import com.danielnaiff.backend.entity.User;
import com.danielnaiff.backend.repository.TransferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TransferService {

    private final TransferRepository transferRepository;
    private final UserService userService;
    private final RestTemplate restTemplate;

    public void createTransfer(TranferRequestDTO transfer) throws Exception{
        User payer = userService.findById(transfer.payerId());
        User payee = userService.findById(transfer.payeeId());

        userService.validateUserAndAmount(payer, transfer.value());

        if(!isTransactionAuthorized(payer, transfer.value())){
            throw new TranferNotValidated("Transacao nao autorizada");
        }

        Transfer newTransfer = new Transfer();
        newTransfer.setValue(transfer.value());
        newTransfer.setPayer(payer);
        newTransfer.setPayee(payee);

        payer.setBalance(payer.getBalance().subtract(transfer.value()));
        payee.setBalance(payee.getBalance().add(transfer.value()));

        transferRepository.save(newTransfer);
        userService.saveUser(payer);
        userService.saveUser(payee);
    }

    public boolean isTransactionAuthorized(User payee, BigDecimal value) {
        ResponseEntity<Map> authorization = restTemplate.getForEntity(
                "https://util.devi.tools/api/v2/authorize", Map.class);

        if (authorization.getStatusCode() == HttpStatus.OK) {
            Map<String, Object> body = authorization.getBody();

            if (body != null) {
                // verifica se "status" é "success"
                String status = (String) body.get("status");
                if ("success".equalsIgnoreCase(status)) {
                    Map<String, Object> data = (Map<String, Object>) body.get("data");
                    if (data != null) {
                        Boolean authorized = (Boolean) data.get("authorization");
                        return Boolean.TRUE.equals(authorized);
                    }
                }
            }
        }
        return false;
    }

}
