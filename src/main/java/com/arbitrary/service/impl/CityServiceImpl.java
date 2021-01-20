package com.arbitrary.service.impl;

import com.arbitrary.dao.slave.ICityDao;
import com.arbitrary.entity.City;
import com.arbitrary.service.CityService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Classname CityServiceImpl
 * @Description city的service接口的实现类
 * @Date 2020/5/27 9:37
 * @Created by gangye
 */
@Service
public class CityServiceImpl implements CityService {
    @Resource
    private ICityDao cityDao;

    @Override
    public City getCityByPrimarykey(Integer id) {
        return cityDao.findById(id);
    }

    @Override
    public List<City> showAllCitys() {
        return cityDao.showAll();
    }

    @Override
    @Transactional(transactionManager = "secondTransactionManager")
    public void insertOneCity(City city) {
        cityDao.insert(city);
    }

    @Override
    @Transactional(transactionManager = "secondTransactionManager")
    public void update(City city) {
        cityDao.update(city);
    }
}
