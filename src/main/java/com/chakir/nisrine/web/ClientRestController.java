package com.chakir.nisrine.web;

import com.chakir.nisrine.dtos.ClientDTO;
import com.chakir.nisrine.exceptions.ClientNotFoundException;
import com.chakir.nisrine.services.BankCreditService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
@CrossOrigin("*")
public class ClientRestController {
    private final BankCreditService bankCreditService;

    @GetMapping("clients")
    public List<ClientDTO> listClients() {
        return bankCreditService.listClients();
    }

    @GetMapping("/clients/search")
    public List<ClientDTO> searchClients(@RequestParam(name = "keyword", defaultValue = "") String keyword) {
        return bankCreditService.searchClients("%" + keyword + "%");
    }

    @GetMapping("/clients/{id}")
    public ClientDTO getClient(@PathVariable Long id) throws ClientNotFoundException {
        return bankCreditService.getClient(id);
    }

    @PostMapping("/clients")
    public ClientDTO saveClient(@RequestBody ClientDTO clientDTO) {
        return bankCreditService.saveClient(clientDTO);
    }

    @PutMapping("/clients/{id}")
    public ClientDTO updateClient(@PathVariable Long id, @RequestBody ClientDTO clientDTO) {
        clientDTO.setId(id);
        return bankCreditService.updateClient(clientDTO);
    }

    @DeleteMapping("/clients/{id}")
    public void deleteClient(@PathVariable Long id) {
        bankCreditService.deleteClient(id);
    }
}
