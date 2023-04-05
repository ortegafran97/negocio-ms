package com.donks.depositservice.Model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Provider {
    @Id
    @Column(columnDefinition = "uuid")
    @GeneratedValue
    @GenericGenerator(
            name="uuid",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;

    @Column(nullable = false)
    private String name;
    private String telephone;
    private String email;
    private String cuit;
    private String cuil;

    public Provider(String name){
        this.name = name;
    }
}
