package pro.fireup.translationdb.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pro.fireup.translationdb.domain.Phrase;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface PhraseDao extends CrudRepository<Phrase, Long> {
    @Override
    List<Phrase> findAll();

    Phrase findByLabel(String label);

    void deleteByLabel(String label);
}
