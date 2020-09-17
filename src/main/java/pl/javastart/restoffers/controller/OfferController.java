package pl.javastart.restoffers.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.javastart.restoffers.Filters;
import pl.javastart.restoffers.controller.dto.OfferDto;
import pl.javastart.restoffers.controller.form.OfferForm;
import pl.javastart.restoffers.service.OfferService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class OfferController {

    private OfferService offerService;

    @Autowired
    public OfferController(OfferService offerService) {
        this.offerService = offerService;
    }

    @GetMapping("/offers")
    List<OfferDto> findOffers(Filters filters) {
        return offerService.findAll(filters);
    }

    @GetMapping("/offers/count")
    Long countOffers() {
        return offerService.countAll();
    }

    @PostMapping("/offers")
    void save(@RequestBody OfferForm offerForm) {
        offerService.save(offerForm);
    }
}
