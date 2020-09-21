package pl.javastart.restoffers.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.javastart.restoffers.controller.dto.CategoryDto;
import pl.javastart.restoffers.model.Category;
import pl.javastart.restoffers.repository.CategoryRepository;
import pl.javastart.restoffers.repository.OfferRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    private CategoryRepository categoryRepository;
    private OfferRepository offerRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository, OfferRepository offerRepository) {
        this.categoryRepository = categoryRepository;
        this.offerRepository = offerRepository;
    }

    public List<String> findNames() {
        return categoryRepository.findAll().stream()
                .map(Category::getName)
                .collect(Collectors.toList());
    }

    public List<CategoryDto> findAll() {
        List<Category> all = categoryRepository.findAll();

        List<CategoryDto> result = new ArrayList<>();

        for (Category category : all) {
            Integer offers = offerRepository.countAllByCategoryName(category.getName());
            CategoryDto categoryDto = new CategoryDto(category.getName(), category.getDescription(), offers);
            result.add(categoryDto);
        }

//        Alternatywne podejście za pomocą streamów
//        categoryRepository.findAll().stream()
//                .map(this::toDto)
//                .collect(Collectors.toList());

        return result;
    }

//    private CategoryDto toDto(Category category) {
//        Integer offers = offerRepository.countAllByCategoryName(category.getName());
//        return new CategoryDto(category.getName(), category.getDescription(), offers);
//    }
}
