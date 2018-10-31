package company.com.dtos;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)

public class StarWarsCharactersDto {
    private String name;
    private String height;
    private String mass;
    private String hair_color;
    private String skin_color;
    private String eye_color;
    private String birth_year;
    private String gender;
    private String homeworld;
    private List<String> films;
    private List<String> species;
    private List<String> vehicles;
    private List<String> starships;
    private String created;
    private String edited;
    private String url;

    public String getName() {
        return name;
    }

    public StarWarsCharactersDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getHeight() {
        return height;
    }

    public StarWarsCharactersDto setHeight(String height) {
        this.height = height;
        return this;
    }

    public String getMass() {
        return mass;
    }

    public StarWarsCharactersDto setMass(String mass) {
        this.mass = mass;
        return this;
    }

    public String getHairColor() {
        return hair_color;
    }

    public StarWarsCharactersDto setHairColor(String hair_color) {
        this.hair_color = hair_color;
        return this;
    }

    public String getSkinColor() {
        return skin_color;
    }

    public StarWarsCharactersDto setSkinColor(String skin_color) {
        this.skin_color = skin_color;
        return this;
    }

    public String getEyeColor() {
        return eye_color;
    }

    public StarWarsCharactersDto setEyeColor(String eye_color) {
        this.eye_color = eye_color;
        return this;
    }

    public String getBirthYear() {
        return birth_year;
    }

    public StarWarsCharactersDto setBirthYear(String birth_year) {
        this.birth_year = birth_year;
        return this;
    }

    public String getGender() {
        return gender;
    }

    public StarWarsCharactersDto setGender(String gender) {
        this.gender = gender;
        return this;
    }

    public String getHomeWorld() {
        return homeworld;
    }

    public StarWarsCharactersDto setHomeWorld(String homeworld) {
        this.homeworld = homeworld;
        return this;
    }

    public List<String> getFilms() {
        return films;
    }

    public StarWarsCharactersDto setFilms(List<String> films) {
        this.films = films;
        return this;
    }

    public List<String> getSpecies() {
        return species;
    }

    public StarWarsCharactersDto setSpecies(List<String> species) {
        this.species = species;
        return this;
    }

    public List<String> getVehicles() {
        return vehicles;
    }

    public StarWarsCharactersDto setVehicles(List<String> vehicles) {
        this.vehicles = vehicles;
        return this;
    }

    public List<String> setStarships() {
        return starships;
    }

    public StarWarsCharactersDto setStarships(List<String> starships) {
        this.starships = starships;
        return this;
    }

    public String getCreated() {
        return created;
    }

    public StarWarsCharactersDto setCreated(String created) {
        this.created = created;
        return this;
    }

    public String getEdited() {
        return edited;
    }

    public StarWarsCharactersDto setEdited(String edited) {
        this.edited = edited;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public StarWarsCharactersDto setUrl(String url) {
        this.url = url;
        return this;
    }
}
