package peaksoft.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.entity.House;
import peaksoft.repository.HouseRepository;
import peaksoft.service.HouseService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HouseServiceImpl implements HouseService {
    private final HouseRepository houseRepository;

    @Override
    public void saveHouse(Long agencyId, House house) {
        houseRepository.saveHouse(agencyId, house);
    }

    @Override
    public House getHouseById(Long id) {
        return houseRepository.getHouseById(id);
    }

    @Override
    public List<House> getAllHouses(Long id) {
        return houseRepository.getAllHouses(id);
    }

    @Override
    public void updateHouse(Long id, House newHouse) {
        houseRepository.updateHouse(id, newHouse);
    }

    @Override
    public void deleteHouseById(Long id) {
        houseRepository.deleteHouseById(id);
    }
}
