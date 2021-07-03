package movieticket;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel="cinemas", path="cinemas")
public interface CinemaRepository extends PagingAndSortingRepository<Cinema, Long>{


}
