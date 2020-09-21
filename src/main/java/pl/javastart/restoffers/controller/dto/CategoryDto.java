package pl.javastart.restoffers.controller.dto;

public class CategoryDto {

    private String name;
    private String description;
    private Integer offers;

    public CategoryDto() {
    }

    public CategoryDto(String name, String description, Integer offers) {
        this.name = name;
        this.description = description;
        this.offers = offers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getOffers() {
        return offers;
    }

    public void setOffers(Integer offers) {
        this.offers = offers;
    }
}
