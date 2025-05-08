package com.example.amirsinvoicer.Repository;

import com.example.amirsinvoicer.Model.Client;
import com.example.amirsinvoicer.Model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, UUID> {

    List<Invoice> findAllByClient(Client client);

}
