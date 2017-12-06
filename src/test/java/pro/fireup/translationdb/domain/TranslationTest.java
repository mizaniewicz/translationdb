package pro.fireup.translationdb.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pro.fireup.translationdb.repository.PhraseDao;
import pro.fireup.translationdb.repository.TranslationDao;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TranslationTest {
    @Autowired
    private TranslationDao translationDao;

    @Autowired
    private PhraseDao phraseDao;


    @Test
    public void testTranslationDaoSave() {
        //Given
        Translation translation1 = new Translation("pl", "tekst po polsku");
        Translation translation2 = new Translation("en", "this is english");
        Phrase phrase = new Phrase("test");
        phrase.getTranslations().add(translation1);
        phrase.getTranslations().add(translation2);
        translation1.setPhraseList(phrase);
        translation2.setPhraseList(phrase);

        //When
        phraseDao.save(phrase);

        //Then
        assertEquals("pl", translation1.getLanguage());
        assertEquals("test", phrase.getLabel());

        //CleanUp
//        translationDao.deleteAll();

    }

}