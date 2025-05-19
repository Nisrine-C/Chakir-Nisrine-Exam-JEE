package com.chakir.nisrine.entities;
import com.chakir.nisrine.enums.StatutType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Client {
    @Id
    private Long id;

    private String nom;
    private String email;

    @OneToMany(mappedBy = "client",fetch=FetchType.EAGER)
    private List<Credit> credits;

}
