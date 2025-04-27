package com.example.amirsinvoicer.Repository;

import com.example.amirsinvoicer.Model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, UUID> {
    // Custom query methods can be defined here if needed
    // For example, you can add methods to find invoices by user, date, etc.
}
