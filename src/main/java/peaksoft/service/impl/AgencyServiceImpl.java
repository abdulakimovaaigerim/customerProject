package peaksoft.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.entity.Agency;
import peaksoft.repository.AgencyRepository;
import peaksoft.service.AgencyService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AgencyServiceImpl implements AgencyService {
    private final AgencyRepository agencyRepository;

    @Override
    public void saveAgency(Agency agency) {
        agencyRepository.saveAgency(agency);
    }

    @Override
    public Agency getAgencyById(Long id) {
        return agencyRepository.getAgencyById(id);
    }

    @Override
    public List<Agency> getAllAgencies() {
        return agencyRepository.getAllAgencies();
    }

    @Override
    public void updateAgency(Long id, Agency newAgency) {
        agencyRepository.updateAgency(id, newAgency);
    }

    @Override
    public void deleteAgencyById(Long id) {
        agencyRepository.deleteAgencyById(id);
    }

    @Override
    public List<Agency> search(String word) {
        return agencyRepository.search(word);
    }
}
