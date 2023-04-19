package kodlama.io.rentacar.business.rules;

import org.springframework.stereotype.Service;

import kodlama.io.rentacar.core.exceptions.BusinessException;
import kodlama.io.rentacar.repository.InvoiceRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class InvoiceBusinessRules {

    private final InvoiceRepository repository;

    public void checkIfInvoiceExists(int id) {
        if (!repository.existsById(id)) {
            throw new BusinessException("Fatura bilgisi bulunamadı.");
        }
    }

}
