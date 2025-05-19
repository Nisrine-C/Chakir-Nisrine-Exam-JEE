package com.chakir.nisrine;

import com.chakir.nisrine.entities.*;
import com.chakir.nisrine.enums.*;
import com.chakir.nisrine.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.stream.Stream;

@SpringBootApplication
public class ChakirNisrineExamJeeApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChakirNisrineExamJeeApplication.class, args);
    }

    @Bean
    CommandLineRunner initData(
            ClientRepository clientRepository,
            CreditRepository creditRepository,
            RemboursementRepository remboursementRepository) {
        return args -> {
            // Create and save clients
            Client client1 = new Client();
            client1.setId(1L);
            client1.setNom("Nisrine Chakir");
            client1.setEmail("nisrine@example.com");
            clientRepository.save(client1);

            Client client2 = new Client();
            client2.setId(2L);
            client2.setNom("Ahmed Zaid");
            client2.setEmail("ahmed@example.com");
            clientRepository.save(client2);

            // Create and save different types of credits
            CreditPersonnel creditPersonnel = new CreditPersonnel();
            creditPersonnel.setId(1L);
            creditPersonnel.setDateDemande(new Date());
            creditPersonnel.setStatut(StatutType.EN_COURS);
            creditPersonnel.setMontant(50000.0);
            creditPersonnel.setDureeRemboursement(60);
            creditPersonnel.setTauxInteret(4.5f);
            creditPersonnel.setMotif("Achat voiture");
            creditPersonnel.setClient(client1);
            creditRepository.save(creditPersonnel);

            CreditImmobilier creditImmobilier = new CreditImmobilier();
            creditImmobilier.setId(2L);
            creditImmobilier.setDateDemande(new Date());
            creditImmobilier.setStatut(StatutType.ACCEPTE);
            creditImmobilier.setMontant(2000000.0);
            creditImmobilier.setDureeRemboursement(240);
            creditImmobilier.setTauxInteret(3.2f);
            creditImmobilier.setBienType(BienType.APPARTEMENT);
            creditImmobilier.setClient(client1);
            creditRepository.save(creditImmobilier);

            CreditProfessionel creditProfessionel = new CreditProfessionel();
            creditProfessionel.setId(3L);
            creditProfessionel.setDateDemande(new Date());
            creditProfessionel.setStatut(StatutType.ACCEPTE);
            creditProfessionel.setMontant(1000000.0);
            creditProfessionel.setDureeRemboursement(120);
            creditProfessionel.setTauxInteret(3.8f);
            creditProfessionel.setMotif("Expansion entreprise");
            creditProfessionel.setRaisonSociale("SARL TechSolutions");
            creditProfessionel.setClient(client2);
            creditRepository.save(creditProfessionel);

            // Create and save repayments
            Remboursement remboursement1 = new Remboursement();
            remboursement1.setId(1L);
            remboursement1.setDate(new Date());
            remboursement1.setMontant(10000.0);
            remboursement1.setType(RemboursementType.MENSUALITE);
            remboursement1.setCredit(creditPersonnel);
            remboursementRepository.save(remboursement1);

            Remboursement remboursement2 = new Remboursement();
            remboursement2.setId(2L);
            remboursement2.setDate(new Date());
            remboursement2.setMontant(50000.0);
            remboursement2.setType(RemboursementType.REMBOURSEMENT_ANTICIPE);
            remboursement2.setCredit(creditImmobilier);
            remboursementRepository.save(remboursement2);

            // Verify data
            System.out.println("=== Clients ===");
            clientRepository.findAll().forEach(client -> {
                System.out.println(client.getNom() + " - " + client.getEmail());
                System.out.println("Credits:");
                client.getCredits().forEach(credit -> {
                    System.out.println("- " + credit.getClass().getSimpleName() +
                            " (" + credit.getMontant() + " DH)");
                });
            });

            System.out.println("\n=== Credits with repayments ===");
            creditRepository.findAll().forEach(credit -> {
                System.out.println(credit.getClass().getSimpleName() +
                        " - " + credit.getStatut());
                System.out.println("Repayments:");
                credit.getRemboursements().forEach(r -> {
                    System.out.println("- " + r.getMontant() + " DH (" + r.getType() + ")");
                });
            });
        };
    }

}
