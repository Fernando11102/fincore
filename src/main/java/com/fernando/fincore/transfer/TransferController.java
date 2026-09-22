package com.fernando.fincore.transfer;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/transfers")
public class TransferController {

    private final TransferService transferService;

    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping
    public void transfer(@Valid @RequestBody TransferRequest request) {

        try {
            boolean transferred = transferService.transfer(
                    request.fromAccountNumber(),
                    request.toAccountNumber(),
                    request.amount()
            );

            if (!transferred) {
                throw new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Account not found"
                );
            }

        } catch (IllegalArgumentException exception) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    exception.getMessage()
            );
        }
    }
}
