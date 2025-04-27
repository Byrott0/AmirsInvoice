package com.example.amirsinvoicer.Controller;

import com.example.amirsinvoicer.Model.Invoice;
import com.example.amirsinvoicer.Service.InvoiceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/invoices")
public class InvoiceController {
    private final InvoiceService invoiceService;
    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping
    public List<Invoice> getAllInvoices() {
        return invoiceService.getAllInvoices();
    }
    @GetMapping("/{id}")
    public Invoice getInvoiceById(UUID id) {
        return invoiceService.getinvoiceById(id);
    }

    @PostMapping
    public void addInvoice(@RequestBody Invoice invoice) {
        invoiceService.insertInvoice(invoice);
    }
    @DeleteMapping
    public void deleteInvoice(@RequestBody Invoice invoice) {
        invoiceService.deleteInvoice(invoice);
    }
    @PutMapping("/{id}")
    public void updateInvoice(@PathVariable UUID id,
                              @RequestBody Invoice invoice) {
        invoiceService.updateInvoice(id, invoice);
    }
}
