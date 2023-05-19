package peaksoft.service;

import peaksoft.entity.Agency;
import peaksoft.entity.House;

import java.util.List;

public interface HouseService {

    void saveHouse(Long agencyId, House house);

    House getHouseById(Long id);

    List<House> getAllHouses(Long id);

    void updateHouse(Long id, House newHouse);

    void deleteHouseById(Long id);
}
