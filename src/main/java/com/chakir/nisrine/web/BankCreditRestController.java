package com.chakir.nisrine.web;

import com.chakir.nisrine.dtos.*;
import com.chakir.nisrine.exceptions.ClientNotFoundException;
import com.chakir.nisrine.exceptions.CreditNotFoundException;
import com.chakir.nisrine.services.BankCreditService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/credits")
@CrossOrigin("*")
public class BankCreditRestController {
    private final BankCreditService bankCreditService;

    public BankCreditRestController(BankCreditService bankCreditService) {
        this.bankCreditService = bankCreditService;
    }

    @GetMapping("/{creditId}")
    public CreditDTO getCredit(@PathVariable Long creditId) throws CreditNotFoundException {
        return bankCreditService.getCredit(creditId);
    }

    @GetMapping
    public List<CreditDTO> listCredits() {
        return bankCreditService.listCredits();
    }

    @GetMapping("/client/{clientId}")
    public List<CreditDTO> getClientCredits(@PathVariable Long clientId) throws ClientNotFoundException {
        return bankCreditService.listCreditsByClient(clientId);
    }

    @GetMapping("/{creditId}/repayments")
    public List<RemboursementDTO> getCreditRepayments(@PathVariable Long creditId) throws CreditNotFoundException {
        return bankCreditService.getRemboursementsByCredit(creditId);
    }

    @PostMapping("/personal")
    public CreditPersonnelDTO createPersonalCredit(@RequestBody CreditPersonnelDTO creditDTO) throws ClientNotFoundException {
        return bankCreditService.saveCreditPersonnel(creditDTO);
    }

    @PostMapping("/mortgage")
    public CreditImmobilierDTO createMortgageCredit(@RequestBody CreditImmobilierDTO creditDTO) throws ClientNotFoundException {
        return bankCreditService.saveCreditImmobilier(creditDTO);
    }

    @PostMapping("/business")
    public CreditProfessionelDTO createBusinessCredit(@RequestBody CreditProfessionelDTO creditDTO) throws ClientNotFoundException {
        return bankCreditService.saveCreditProfessionel(creditDTO);
    }

    @PostMapping("/{creditId}/repayments")
    public RemboursementDTO addRepayment(@PathVariable Long creditId, @RequestBody RemboursementDTO repaymentDTO) throws CreditNotFoundException {
        return bankCreditService.addRemboursement(creditId, repaymentDTO);
    }

    @PutMapping("/{creditId}/status")
    public void updateStatus(@PathVariable Long creditId, @RequestParam String status) throws CreditNotFoundException {
        bankCreditService.updateCreditStatus(creditId, status);
    }

    @DeleteMapping("/{creditId}")
    public void deleteCredit(@PathVariable Long creditId) {
        bankCreditService.deleteCredit(creditId);
    }
}
