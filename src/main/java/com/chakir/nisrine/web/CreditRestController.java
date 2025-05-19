package com.chakir.nisrine.web;

import com.chakir.nisrine.dtos.*;
import com.chakir.nisrine.exceptions.ClientNotFoundException;
import com.chakir.nisrine.exceptions.CreditNotFoundException;
import com.chakir.nisrine.services.BankCreditService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/credits")
@CrossOrigin("*")
public class CreditRestController {
    private final BankCreditService bankCreditService;

    public CreditRestController(BankCreditService bankCreditService) {
        this.bankCreditService = bankCreditService;
    }

    @GetMapping("/{creditId}")
    @PreAuthorize("hasAuthority('SCOPER CLIENT_ROLE')")
    public CreditDTO getCredit(@PathVariable Long creditId) throws CreditNotFoundException {
        return bankCreditService.getCredit(creditId);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('SCOPER EMPLOYE_ROLE')")
    public List<CreditDTO> listCredits() {
        return bankCreditService.listCredits();
    }

    @PreAuthorize("hasAuthority('SCOPER CLIENT_ROLE')")
    @GetMapping("/client/{clientId}")
    public List<CreditDTO> getClientCredits(@PathVariable Long clientId) throws ClientNotFoundException {
        return bankCreditService.listCreditsByClient(clientId);
    }

    @PreAuthorize("hasAuthority('SCOPER CLIENT_ROLE')")
    @GetMapping("/{creditId}/repayments")
    public List<RemboursementDTO> getCreditRepayments(@PathVariable Long creditId) throws CreditNotFoundException {
        return bankCreditService.getRemboursementsByCredit(creditId);
    }

    @PostMapping("/personal")
    @PreAuthorize("hasAuthority('SCOPER CLIENT_ROLE')")
    public CreditPersonnelDTO createPersonalCredit(@RequestBody CreditPersonnelDTO creditDTO) throws ClientNotFoundException {
        return bankCreditService.saveCreditPersonnel(creditDTO);
    }

    @PostMapping("/mortgage")
    @PreAuthorize("hasAuthority('SCOPER CLIENT_ROLE')")
    public CreditImmobilierDTO createMortgageCredit(@RequestBody CreditImmobilierDTO creditDTO) throws ClientNotFoundException {
        return bankCreditService.saveCreditImmobilier(creditDTO);
    }

    @PostMapping("/business")
    @PreAuthorize("hasAuthority('SCOPER EMPLOYE_ROLE')")
    public CreditProfessionelDTO createBusinessCredit(@RequestBody CreditProfessionelDTO creditDTO) throws ClientNotFoundException {
        return bankCreditService.saveCreditProfessionel(creditDTO);
    }

    @PostMapping("/{creditId}/repayments")
    @PreAuthorize("hasAuthority('SCOPER EMPLOYE_ROLE')")
    public RemboursementDTO addRepayment(@PathVariable Long creditId, @RequestBody RemboursementDTO repaymentDTO) throws CreditNotFoundException {
        return bankCreditService.addRemboursement(creditId, repaymentDTO);
    }


    @PutMapping("/{creditId}/status")
    @PreAuthorize("hasAuthority('SCOPER EMPLOYE_ROLE')")
    public void updateStatus(@PathVariable Long creditId, @RequestParam String status) throws CreditNotFoundException {
        bankCreditService.updateCreditStatus(creditId, status);
    }

    @DeleteMapping("/{creditId}")
    @PreAuthorize("hasAuthority('SCOPER EMPLOYE_ROLE')")
    public void deleteCredit(@PathVariable Long creditId) {
        bankCreditService.deleteCredit(creditId);
    }
}
