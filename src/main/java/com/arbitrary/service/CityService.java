package com.arbitrary.service;

import com.arbitrary.entity.City;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CityService {
    City getCityByPrimarykey(Integer id);

    List<City> showAllCitys();

    void insertOneCity(City city);

    void update(City city);
}
