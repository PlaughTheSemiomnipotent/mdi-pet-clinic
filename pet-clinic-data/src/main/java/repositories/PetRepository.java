package repositories;

import org.springframework.data.repository.CrudRepository;

import com.MDI747.mdipetclinic.model.Pet;

public interface PetRepository extends CrudRepository<Pet, Long> {

}
