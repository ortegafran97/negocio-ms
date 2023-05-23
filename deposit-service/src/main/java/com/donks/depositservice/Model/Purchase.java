package com.donks.depositservice.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Table(name = "purchase")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Purchase {

    @Id
    @Column(columnDefinition = "uuid")
    @GeneratedValue
    @GenericGenerator(
            name="uuid",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;

    private LocalDateTime createdAt = LocalDateTime.now().truncatedTo(ChronoUnit.MILLIS);
    private LocalDateTime updatedAt = LocalDateTime.now().truncatedTo(ChronoUnit.MILLIS);
    private String comment;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true,targetEntity = PurchaseItem.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "purchase_id")
    private List<PurchaseItem> items = new ArrayList<>();

    @ManyToOne
    private Provider provider = null;

    /* METHODS */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Purchase)) return false;
        Purchase purchase = (Purchase) o;
        return Objects.equals(id, purchase.id)
                && Objects.equals(createdAt.truncatedTo(ChronoUnit.MILLIS), purchase.createdAt.truncatedTo(ChronoUnit.MILLIS))
                && Objects.equals(updatedAt.truncatedTo(ChronoUnit.MILLIS), purchase.updatedAt.truncatedTo(ChronoUnit.MILLIS));
    }

    @Override
    public int hashCode() {
        return Objects.hash(id,
                createdAt.truncatedTo(ChronoUnit.MILLIS),
                updatedAt.truncatedTo(ChronoUnit.MILLIS));
    }
}
