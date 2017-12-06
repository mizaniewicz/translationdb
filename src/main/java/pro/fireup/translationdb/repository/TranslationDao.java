package pro.fireup.translationdb.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pro.fireup.translationdb.domain.Translation;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface TranslationDao extends CrudRepository<Translation, Long> {
    @Override
    List<Translation> findAll();

    Translation findById(long id);

    List<Translation> findByLanguage(String language);

    Translation findByLanguageAndAndPhraseLabel(String language, String label);

    void deleteById(long id);
}
