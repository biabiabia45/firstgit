package mycode.WalletOnline.WalletApplication.controller;

import mycode.WalletOnline.WalletApplication.dto.TransferDto;
import mycode.WalletOnline.WalletApplication.model.Transaction;
import mycode.WalletOnline.WalletApplication.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/transfers")
public class TransferController {
    @Autowired
    private TransferService transferService;

    @PostMapping
    public ResponseEntity<Transaction> transfer(@RequestBody TransferDto transferDto) {
        Transaction transaction = transferService.transfer(transferDto);
        return ResponseEntity.ok(transaction);
    }

    @PostMapping("/bank")
    public ResponseEntity<Transaction> bankTransfer(@RequestBody TransferDto transferDto) {
        Transaction transaction = transferService.bankTransfer(transferDto);
        return ResponseEntity.ok(transaction);
    }
}
