package com.chakir.nisrine.web;

import com.chakir.nisrine.dtos.ClientDTO;
import com.chakir.nisrine.exceptions.ClientNotFoundException;
import com.chakir.nisrine.services.BankCreditService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
@CrossOrigin("*")
public class ClientRestController {
    private final BankCreditService bankCreditService;

    @GetMapping("clients")
    @PreAuthorize("hasAuthority('SCOPER EMPLOYE_ROLE')")
    public List<ClientDTO> listClients() {
        return bankCreditService.listClients();
    }

    @GetMapping("/clients/search")
    @PreAuthorize("hasAuthority('SCOPER EMPLOYE_ROLE')")
    public List<ClientDTO> searchClients(@RequestParam(name = "keyword", defaultValue = "") String keyword) {
        return bankCreditService.searchClients("%" + keyword + "%");
    }

    @GetMapping("/clients/{id}")
    @PreAuthorize("hasAuthority('SCOPER CLIENT_ROLE')")
    public ClientDTO getClient(@PathVariable Long id) throws ClientNotFoundException {
        return bankCreditService.getClient(id);
    }

    @PostMapping("/clients")
    @PreAuthorize("hasAuthority('SCOPER ADMIN_ROLE')")
    public ClientDTO saveClient(@RequestBody ClientDTO clientDTO) {
        return bankCreditService.saveClient(clientDTO);
    }

    @PutMapping("/clients/{id}")
    @PreAuthorize("hasAuthority('SCOPER ADMIN_ROLE')")
    public ClientDTO updateClient(@PathVariable Long id, @RequestBody ClientDTO clientDTO) {
        clientDTO.setId(id);
        return bankCreditService.updateClient(clientDTO);
    }

    @DeleteMapping("/clients/{id}")
    @PreAuthorize("hasAuthority('SCOPER ADMIN_ROLE')")
    public void deleteClient(@PathVariable Long id) {
        bankCreditService.deleteClient(id);
    }
}
