package pro.fireup.translationdb.service;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.fireup.translationdb.domain.Phrase;
import pro.fireup.translationdb.domain.Translation;
import pro.fireup.translationdb.repository.PhraseDao;
import pro.fireup.translationdb.repository.TranslationDao;

import javax.transaction.Transactional;

@Service
@Transactional
public class Mutation implements GraphQLMutationResolver {
    @Autowired
    private TranslationDao translationDao;

    @Autowired
    private PhraseDao phraseDao;

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
            translationDao.delete(translation);
            return "Translation deleted";
        }
    }

    public String deleteLabel(String label) {
        Phrase phrase = phraseDao.findByLabel(label);
        if (phrase == null) {
            return "Label not found";
        } else {
            phraseDao.delete(phrase);
            return "Label deleted";
        }
    }
}
