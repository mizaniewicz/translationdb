package pro.fireup.translationdb.repository;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pro.fireup.translationdb.domain.Phrase;
import pro.fireup.translationdb.domain.Translation;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class Query implements GraphQLQueryResolver {
    @Autowired
    PhraseDao phraseDao;

    @Autowired
    TranslationDao translationDao;

    public List<Phrase> getPhrases() {
        return phraseDao.findAll();
    }

    public List<Translation> getTranslations() {
        return translationDao.findAll();
    }

    public Translation getTranslationById(long id) {
        return translationDao.findById(id);
    }

    public List<Translation> getTranslationByLanguage(String language) {
        return translationDao.findByLanguage(language);
    }

    public Translation getTranslationByLanguageAndPhraseLabel(String language, String label) {
        return translationDao.findByLanguageAndAndPhraseLabel(language, label);
    }

    public String checkTranslation(String language, String label) {
        if (getTranslationByLanguageAndPhraseLabel(language, label) == null) {
            return language + " translation for " + label + " doesn't exists";
        } else {
            return language + " translation for " + label + " exists";
        }
    }

    public List<String> getEmptyTranslations(String language) {
        List<Phrase> phrases = phraseDao.findAll();
        List<String> labels = phrases.stream().map(p -> p.getLabel()).collect(Collectors.toList());
        List<String> emptyTranslations = new ArrayList<>();
        for (String label : labels) {
            if (getTranslationByLanguageAndPhraseLabel(language, label) == null) {
                emptyTranslations.add(label);
            }
        }
        return emptyTranslations;
    }
}
