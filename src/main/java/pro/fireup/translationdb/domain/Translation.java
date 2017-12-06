package pro.fireup.translationdb.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Translation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    private long id;
    private String language;
    private String text;
    @ManyToOne
    @JoinColumn(name = "phraseId")
    private Phrase phrase;


    public Translation() {
    }

    public Translation(String language, String text, Phrase phrase) {
        this.language = language;
        this.text = text;
        this.phrase = phrase;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Phrase getPhraseList() {
        return phrase;
    }

    public void setPhraseList(Phrase phrase) {
        this.phrase = phrase;
    }
}
