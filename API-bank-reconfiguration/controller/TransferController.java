package com.enterprise.bank_lies.controller;

import com.enterprise.bank_lies.dto.TransferDTO;
import com.enterprise.bank_lies.service.TransferService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transfers")
public class TransferController {

    final
    TransferService transferService;

    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping("/number/{id}")
    public ResponseEntity<Void> transferByNumber(
        @PathVariable Integer id,
                @RequestBody TransferDTO dto){

        transferService.transferByNumber(id,dto.getReceiver(), dto.getValue());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/email/{id}")
    public ResponseEntity<Void> transferByEmail(

            @PathVariable Integer id,
            @RequestBody TransferDTO transferDTO){
        transferService.transferByEmail(id,transferDTO.getReceiver(),transferDTO.getValue());
    return ResponseEntity.ok().build();
}
}
