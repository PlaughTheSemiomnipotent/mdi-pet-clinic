package repositories;

import org.springframework.data.repository.CrudRepository;

import com.MDI747.mdipetclinic.model.PetType;

public interface PetTypeRepository extends CrudRepository<PetType, Long> {

}
