package com.example.amirsinvoicer.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Invoice {

    @Id
    @GeneratedValue
    private UUID id;

    private String invoiceNumber; // Bijvoorbeeld: INV-2024-0001

    private LocalDate issueDate;  // Datum waarop factuur gemaakt is
    private LocalDate dueDate;    // Betaaldatum (bijv. 30 dagen na issueDate)

    private BigDecimal totalAmount; // Totaalbedrag in EUR of USD
    private BigDecimal taxAmount;   // BTW bedrag
    private BigDecimal subTotal;    // Bedrag zonder belasting

    private String description;    // Algemene omschrijving (bijv. "Website Development Services")

    @Enumerated(EnumType.STRING)
    private InvoiceStatus status;  // OPEN, PAID, OVERDUE, CANCELLED

    private String s3Url;           // Link naar opgeslagen PDF in AWS S3 (optioneel)

    @ManyToOne
    private User user;              // Wie heeft deze factuur gemaakt (gebruiker)

    @ManyToOne
    private Client client;          // Voor welke klant is deze factuur
}
