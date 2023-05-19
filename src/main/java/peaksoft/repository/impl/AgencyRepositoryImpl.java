package peaksoft.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import peaksoft.entity.Agency;
import peaksoft.repository.AgencyRepository;

import java.util.List;

@Repository
@Transactional
public class AgencyRepositoryImpl implements AgencyRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void saveAgency(Agency agency) {
        entityManager.persist(agency);
    }

    @Override
    public Agency getAgencyById(Long id) {
        return entityManager.find(Agency.class, id);
    }

    @Override
    public List<Agency> getAllAgencies() {
        return entityManager.createQuery("select a from Agency a", Agency.class).getResultList();
    }

    @Override
    public void updateAgency(Long id, Agency newAgency) {
        Agency agency = entityManager.find(Agency.class, id);
        agency.setName(newAgency.getName());
        agency.setEmail(newAgency.getEmail());
        agency.setCountry(newAgency.getCountry());
        agency.setPhoneNumber(newAgency.getPhoneNumber());
        agency.setImage(newAgency.getImage());
        entityManager.merge(agency);

    }

    @Override
    public void deleteAgencyById(Long id) {
        Agency agency = entityManager.find(Agency.class, id);
        entityManager.remove(agency);


    }

    @Override
    public List<Agency> search(String word) {
        return entityManager.createQuery("select a from Agency a where a.name ilike :word", Agency.class)
                .setParameter("word", "%" + word + "%").getResultList();
    }
}
