package com.arbitrary.dao.slave;

import com.arbitrary.entity.City;

import java.util.List;

/**
 * @Classname CityMapper
 * @Description city的dao层
 * @Date 2020/5/26 16:41
 */
public interface ICityDao {
    City findById(Integer id);

    List<City> showAll();

    void insert(City city);

    void update(City city);
}
