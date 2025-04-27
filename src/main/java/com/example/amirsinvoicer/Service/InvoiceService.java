package com.example.amirsinvoicer.Service;

import com.example.amirsinvoicer.Model.Invoice;
import com.example.amirsinvoicer.Repository.InvoiceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class InvoiceService {
    private final InvoiceRepository invoiceRepository;

    public InvoiceService(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }
    // methodes
    public List<Invoice> getAllInvoices() {
        return invoiceRepository.findAll();
    }
    public Invoice getinvoiceById(UUID id) {
       return invoiceRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException(id + " not found"));
    }
    public void insertInvoice(Invoice invoice) {
        invoice.setId(null);
        invoiceRepository.save(new Invoice());
    }

    public void deleteInvoice(Invoice invoice) {
        invoiceRepository.delete(invoice);
    }

    public void updateInvoice(UUID id, Invoice update) {
        Invoice invoice = invoiceRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException(id + " not found"));

        invoice.setInvoiceNumber(update.getInvoiceNumber());
        invoice.setDueDate(update.getDueDate());
        invoice.setTotalAmount(update.getTotalAmount());
        invoice.setDescription(update.getDescription());
        invoiceRepository.save(invoice);
    }
}
