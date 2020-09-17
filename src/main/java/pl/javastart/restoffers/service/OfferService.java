package pl.javastart.restoffers.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.javastart.restoffers.Filters;
import pl.javastart.restoffers.controller.dto.OfferDto;
import pl.javastart.restoffers.controller.form.OfferForm;
import pl.javastart.restoffers.model.Category;
import pl.javastart.restoffers.model.Offer;
import pl.javastart.restoffers.repository.CategoryRepository;
import pl.javastart.restoffers.repository.OfferRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OfferService {

    private OfferRepository offerRepository;
    private CategoryRepository categoryRepository;

    @Autowired
    public OfferService(OfferRepository offerRepository, CategoryRepository categoryRepository) {
        this.offerRepository = offerRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<OfferDto> findAll(Filters filters) {
        String titleFilter = Optional.ofNullable(filters.getTitle())
                .orElse("");

        return offerRepository.findAllByTitleContains(titleFilter).stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    private OfferDto toDto(Offer offer) {
        //Może to być zrobione za pomocą np Mapstruct'a
        return new OfferDto(offer.getId(), offer.getTitle(), offer.getDescription(), offer.getImgUrl(), offer.getPrice(), offer.getCategory().getName());
    }

    public Long countAll() {
        return offerRepository.count();
    }

    public void save(OfferForm offerForm) {
        String categoryName = offerForm.getCategory();
        Category category = categoryRepository.findByName(categoryName)
                .orElseThrow(IllegalArgumentException::new);

        Offer offer = new Offer(offerForm.getTitle(), offerForm.getDescription(), offerForm.getImgUrl(), offerForm.getPrice(), category);
        offerRepository.save(offer);
    }
}
