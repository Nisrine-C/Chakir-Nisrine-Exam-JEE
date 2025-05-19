package com.chakir.nisrine.mappers;

import com.chakir.nisrine.dtos.*;
import com.chakir.nisrine.entities.*;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class CreditMapperImpl {
    public ClientDTO fromClient(Client client){
        ClientDTO clientDTO=new ClientDTO();
        BeanUtils.copyProperties(client,clientDTO);
        return  clientDTO;
    }
    public Client fromClientDTO(ClientDTO clientDTO){
        Client client=new Client();
        BeanUtils.copyProperties(clientDTO,client);
        return  client;
    }

    public CreditPersonnelDTO fromCreditPersonnel(CreditPersonnel creditPersonnel){
        CreditPersonnelDTO creditPersonnelDTO=new CreditPersonnelDTO();
        BeanUtils.copyProperties(creditPersonnel,creditPersonnelDTO);
        creditPersonnelDTO.setClient(fromClient(creditPersonnel.getClient()));
        creditPersonnelDTO.setType(creditPersonnel.getClass().getSimpleName());
        return creditPersonnelDTO;
    }

    public CreditPersonnel fromCreditPersonnnelDTO(CreditPersonnelDTO creditPersonnelDTO){
        CreditPersonnel creditPersonnel=new CreditPersonnel();
        BeanUtils.copyProperties(creditPersonnelDTO,creditPersonnel);
        creditPersonnel.setClient(fromClientDTO(creditPersonnelDTO.getClient()));
        return creditPersonnel;
    }

    public CreditImmobilier fromCreditImmobilierDTO(CreditImmobilierDTO creditImmobilierDTO){
        CreditImmobilier creditImmobilier=new CreditImmobilier();
        BeanUtils.copyProperties(creditImmobilierDTO,creditImmobilier);
        creditImmobilier.setClient(fromClientDTO(creditImmobilierDTO.getClient()));
        return creditImmobilier;
    }

    public CreditImmobilierDTO fromCreditImmobilier(CreditImmobilier creditImmobilier){
        CreditImmobilierDTO creditImmobilierDTO=new CreditImmobilierDTO();
        BeanUtils.copyProperties(creditImmobilier,creditImmobilierDTO);
        creditImmobilierDTO.setClient(fromClient(creditImmobilier.getClient()));
        creditImmobilierDTO.setType(creditImmobilier.getClass().getSimpleName());
        return creditImmobilierDTO;
    }

    public CreditProfessionel fromCreditProfessionnelDTO(CreditProfessionelDTO creditProfessionelDTO){
        CreditProfessionel creditProfessionel=new CreditProfessionel();
        BeanUtils.copyProperties(creditProfessionelDTO,creditProfessionel);
        creditProfessionel.setClient(fromClientDTO(creditProfessionelDTO.getClient()));
        return creditProfessionel;
    }

    public CreditProfessionelDTO fromCreditProfessionel(CreditProfessionel creditProfessionel){
        CreditProfessionelDTO creditProfessionelDTO=new CreditProfessionelDTO();
        BeanUtils.copyProperties(creditProfessionel,creditProfessionelDTO);
        creditProfessionelDTO.setClient(fromClient(creditProfessionel.getClient()));
        creditProfessionelDTO.setType(creditProfessionel.getClass().getSimpleName());
        return creditProfessionelDTO;
    }



    public RemboursementDTO fromRemboursement(Remboursement remboursement){
        RemboursementDTO remboursementDTO=new RemboursementDTO();
        BeanUtils.copyProperties(remboursement,remboursementDTO);
        return remboursementDTO;
    }
    public Remboursement fromRemboursementDTO(RemboursementDTO remboursementDTO){
        Remboursement remboursement = new Remboursement();
        BeanUtils.copyProperties(remboursementDTO, remboursement);
        return remboursement;
    }
}
