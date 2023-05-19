package peaksoft.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import peaksoft.entity.Agency;
import peaksoft.entity.House;
import peaksoft.repository.HouseRepository;

import java.util.List;

@Repository
@Transactional
public class HouseRepositoryImpl implements HouseRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void saveHouse(Long agencyId, House house) {
        Agency agency = entityManager.find(Agency.class, agencyId);
        agency.getHouses().add(house);
        house.setAgency(agency);
        entityManager.merge(house);
    }

    @Override
    public House getHouseById(Long id) {
        return entityManager.find(House.class, id);
    }

    @Override
    public List<House> getAllHouses(Long id) {
        return entityManager.createQuery("select h from House h join h.agency a where a.id=:id", House.class)
                .setParameter("id", id).getResultList();
    }

    @Override
    public void updateHouse(Long id, House newHouse) {
        House house = entityManager.find(House.class, id);
        house.setHouseType(newHouse.getHouseType());
        house.setAddress(newHouse.getAddress());
        house.setCountry(newHouse.getCountry());
        house.setRoom(newHouse.getRoom());
        house.setPrice(newHouse.getPrice());
        house.setDescription(newHouse.getDescription());
        house.setImage(newHouse.getImage());
        house.setIsBooked(newHouse.getIsBooked());

    }

    @Override
    public void deleteHouseById(Long id) {
        House house = entityManager.find(House.class, id);

        Agency agency = house.getAgency();
        agency.getHouses().forEach(a -> a.getAgency().setHouses(null));
        entityManager.remove(house);


    }
}