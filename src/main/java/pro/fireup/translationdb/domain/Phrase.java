package pro.fireup.translationdb.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Phrase {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    private long id;
    private String label;
    @OneToMany(
            targetEntity = Translation.class,
            mappedBy = "phrase",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<Translation> translations = new ArrayList<>();

    public Phrase() {
    }

    public Phrase(String label) {
        this.label = label;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<Translation> getTranslations() {
        return translations;
    }

    public void setTranslations(List<Translation> translations) {
        this.translations = translations;
    }
}
