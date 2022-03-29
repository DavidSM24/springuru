package dsm.example.springuru.repositories;

import dsm.example.springuru.domain.Publisher;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by jt on 12/23/19.
 */
public interface PublisherRepository extends CrudRepository<Publisher, Long> {
}
