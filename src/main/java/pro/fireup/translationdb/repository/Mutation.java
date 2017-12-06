package pro.fireup.translationdb.repository;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pro.fireup.translationdb.domain.Phrase;
import pro.fireup.translationdb.domain.Translation;

@Component
public class Mutation implements GraphQLMutationResolver {
    @Autowired
    TranslationDao translationDao;

    @Autowired
    PhraseDao phraseDao;

    public Translation saveTranslation(String language, String text, String label) {
        Phrase phrase = phraseDao.findByLabel(label);
        if (phrase == null) {
            Phrase newPhrase = new Phrase(label);
            phraseDao.save(newPhrase);
            phrase = newPhrase;
        }
        Translation translation = translationDao.findByLanguageAndAndPhraseLabel(language, label);
        if (translation == null) {
            translation = new Translation(language, text, phrase);
        } else {
            translation.setText(text);
        }
        return translationDao.save(translation);
    }

    public String deleteTranslationByLabel(String language, String label) {
        Translation translation = translationDao.findByLanguageAndAndPhraseLabel(language, label);
        if (translation == null) {
            return "Translation not found";
        } else {
            translationDao.delete(translation.getId());
            return "Translation deleted";
        }
    }

    public String deleteLabel(String label) {
        Phrase phrase = phraseDao.findByLabel(label);
        if (phrase == null) {
            return "Label not found";
        } else {
            phraseDao.deleteByLabel(label);
            return "Label deleted";
        }
    }
}
