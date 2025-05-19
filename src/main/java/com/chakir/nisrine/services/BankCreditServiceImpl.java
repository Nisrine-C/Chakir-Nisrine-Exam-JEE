package com.chakir.nisrine.services;

import com.chakir.nisrine.dtos.*;
import com.chakir.nisrine.entities.*;
import com.chakir.nisrine.enums.StatutType;
import com.chakir.nisrine.exceptions.ClientNotFoundException;
import com.chakir.nisrine.exceptions.CreditNotFoundException;
import com.chakir.nisrine.mappers.CreditMapperImpl;
import com.chakir.nisrine.repositories.*;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class
BankCreditServiceImpl implements BankCreditService {

    private ClientRepository clientRepository;
    private CreditRepository creditRepository;
    private RemboursementRepository remboursementRepository;
    private CreditMapperImpl dtoMapper;

    @Override
    public ClientDTO saveClient(ClientDTO clientDTO) {
        Client client = dtoMapper.fromClientDTO(clientDTO);
        Client savedClient = clientRepository.save(client);
        return dtoMapper.fromClient(savedClient);
    }

    @Override
    public ClientDTO getClient(Long clientId) throws ClientNotFoundException {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ClientNotFoundException("Client not found"));
        return dtoMapper.fromClient(client);
    }

    @Override
    public List<ClientDTO> listClients() {
        return clientRepository.findAll()
                .stream()
                .map(dtoMapper::fromClient)
                .collect(Collectors.toList());
    }

    @Override
    public ClientDTO updateClient(ClientDTO clientDTO) {
        Client client = dtoMapper.fromClientDTO(clientDTO);
        Client saved = clientRepository.save(client);
        return dtoMapper.fromClient(saved);
    }

    @Override
    public void deleteClient(Long clientId) {
        clientRepository.deleteById(clientId);
    }

    @Override
    public CreditPersonnelDTO saveCreditPersonnel(CreditPersonnelDTO dto) throws ClientNotFoundException {
        Client client = clientRepository.findById(dto.getClient().getId())
                .orElseThrow(() -> new ClientNotFoundException("Client not found"));
        CreditPersonnel credit = dtoMapper.fromCreditPersonnnelDTO(dto);
        credit.setClient(client);
        credit.setDateDemande(new Date());
        CreditPersonnel saved = creditRepository.save(credit);
        return dtoMapper.fromCreditPersonnel(saved);
    }

    @Override
    public CreditImmobilierDTO saveCreditImmobilier(CreditImmobilierDTO dto) throws ClientNotFoundException {
        Client client = clientRepository.findById(dto.getClient().getId())
                .orElseThrow(() -> new ClientNotFoundException("Client not found"));
        CreditImmobilier credit = dtoMapper.fromCreditImmobilierDTO(dto);
        credit.setClient(client);
        credit.setDateDemande(new Date());
        CreditImmobilier saved = creditRepository.save(credit);
        return dtoMapper.fromCreditImmobilier(saved);
    }

    @Override
    public CreditProfessionelDTO saveCreditProfessionel(CreditProfessionelDTO dto) throws ClientNotFoundException {
        Client client = clientRepository.findById(dto.getClient().getId())
                .orElseThrow(() -> new ClientNotFoundException("Client not found"));
        CreditProfessionel credit = dtoMapper.fromCreditProfessionnelDTO(dto);
        credit.setClient(client);
        credit.setDateDemande(new Date());
        CreditProfessionel saved = creditRepository.save(credit);
        return dtoMapper.fromCreditProfessionel(saved);
    }

    @Override
    public CreditDTO getCredit(Long creditId) throws CreditNotFoundException {
        Credit credit = creditRepository.findById(creditId)
                .orElseThrow(() -> new CreditNotFoundException("Credit not found"));
        if (credit instanceof CreditPersonnel) {
            return dtoMapper.fromCreditPersonnel((CreditPersonnel) credit);
        } else if (credit instanceof CreditImmobilier) {
            return dtoMapper.fromCreditImmobilier((CreditImmobilier) credit);
        } else {
            return dtoMapper.fromCreditProfessionel((CreditProfessionel) credit);
        }
    }

    @Override
    public List<CreditDTO> listCreditsByClient(Long clientId) throws ClientNotFoundException {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ClientNotFoundException("Client not found"));
        List<Credit> credits = creditRepository.findByClient(client);
        return credits.stream().map(credit -> {
            if (credit instanceof CreditPersonnel) {
                return dtoMapper.fromCreditPersonnel((CreditPersonnel) credit);
            } else if (credit instanceof CreditImmobilier) {
                return dtoMapper.fromCreditImmobilier((CreditImmobilier) credit);
            } else {
                return dtoMapper.fromCreditProfessionel((CreditProfessionel) credit);
            }
        }).collect(Collectors.toList());
    }

    @Override
    public void updateCreditStatus(Long creditId, String status) throws CreditNotFoundException {
        Credit credit = creditRepository.findById(creditId)
                .orElseThrow(() -> new CreditNotFoundException("Credit not found"));
        credit.setStatut(StatutType.valueOf(status));
        if ("Accepté".equalsIgnoreCase(status)) {
            credit.setDateAcception(new Date());
        }
        creditRepository.save(credit);
    }

    @Override
    public void deleteCredit(Long creditId) {
        creditRepository.deleteById(creditId);
    }

    @Override
    public RemboursementDTO addRemboursement(Long creditId, RemboursementDTO remboursementDTO) throws CreditNotFoundException {
        Credit credit = creditRepository.findById(creditId)
                .orElseThrow(() -> new CreditNotFoundException("Credit not found"));
        Remboursement remboursement = new Remboursement();
        remboursement.setCredit(credit);
        remboursement.setDate(remboursementDTO.getDate());
        remboursement.setMontant(remboursementDTO.getMontant());
        remboursement.setType(remboursementDTO.getType());
        Remboursement saved = remboursementRepository.save(remboursement);
        return dtoMapper.fromRemboursement(saved);
    }

    @Override
    public List<RemboursementDTO> getRemboursementsByCredit(Long creditId) throws CreditNotFoundException {
        Credit credit = creditRepository.findById(creditId)
                .orElseThrow(() -> new CreditNotFoundException("Credit not found"));
        return remboursementRepository.findByCredit(credit)
                .stream()
                .map(dtoMapper::fromRemboursement)
                .collect(Collectors.toList());
    }

    @Override
    public List<ClientDTO> searchClients(String keyword) {
        return clientRepository.findByNomContainsIgnoreCase(keyword)
                .stream()
                .map(dtoMapper::fromClient)
                .collect(Collectors.toList());
    }

    @Override
    public List<CreditDTO> listCredits() {
        List<Credit> credits = creditRepository.findAll();
        List<CreditDTO> creditDTOS = credits.stream().map(credit -> {
            if (credit instanceof CreditPersonnel) {
                CreditPersonnel creditPersonnel = (CreditPersonnel) credit;
                return dtoMapper.fromCreditPersonnel(creditPersonnel);
            }else if (credit instanceof CreditImmobilier) {
                CreditImmobilier creditImmobilier = (CreditImmobilier) credit;
                return dtoMapper.fromCreditImmobilier(creditImmobilier);
            } else if (credit instanceof CreditProfessionel) {
                CreditProfessionel creditProfessionel = (CreditProfessionel) credit;
                return dtoMapper.fromCreditProfessionel(creditProfessionel);
            }
            return null;
        }).collect(Collectors.toList());
        return creditDTOS;
    }
}
