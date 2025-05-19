package com.chakir.nisrine.services;

import com.chakir.nisrine.dtos.*;
import com.chakir.nisrine.exceptions.ClientNotFoundException;
import com.chakir.nisrine.exceptions.CreditNotFoundException;

import java.util.List;

public interface BankCreditService {

    ClientDTO saveClient(ClientDTO clientDTO);
    ClientDTO getClient(Long clientId) throws ClientNotFoundException;
    List<ClientDTO> listClients();
    ClientDTO updateClient(ClientDTO clientDTO);
    void deleteClient(Long clientId);

    CreditPersonnelDTO saveCreditPersonnel(CreditPersonnelDTO dto) throws ClientNotFoundException;
    CreditImmobilierDTO saveCreditImmobilier(CreditImmobilierDTO dto) throws ClientNotFoundException;
    CreditProfessionelDTO saveCreditProfessionel(CreditProfessionelDTO dto) throws ClientNotFoundException;

    CreditDTO getCredit(Long creditId) throws CreditNotFoundException;
    List<CreditDTO> listCreditsByClient(Long clientId) throws ClientNotFoundException;

    void updateCreditStatus(Long creditId, String status) throws CreditNotFoundException;
    void deleteCredit(Long creditId);


    RemboursementDTO addRemboursement(Long creditId, RemboursementDTO remboursementDTO) throws CreditNotFoundException;
    List<RemboursementDTO> getRemboursementsByCredit(Long creditId) throws CreditNotFoundException;

    List<ClientDTO> searchClients(String keyword);

    List<CreditDTO> listCredits();
}