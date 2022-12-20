package ba.sum.fpmoz.filmoteka.model;

public class Movie {
    private String name;
    private String genere;
    private Double score;
    private Long year;
    private String image;

    public Movie() {}

    public Movie(String name, String genere, Double score, Long year, String image) {
        this.name = name;
        this.genere = genere;
        this.score = score;
        this.year = year;
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenere() {
        return genere;
    }

    public void setGenere(String genere) {
        this.genere = genere;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Long getYear() {
        return year;
    }

    public void setYear(Long year) {
        this.year = year;
    }
}
