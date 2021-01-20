package com.arbitrary.controller;

import com.arbitrary.entity.City;
import com.arbitrary.service.CityService;
import com.arbitrary.tools.Response;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
//http://localhost:8089/cityController/showAllCitys
@RestController
@RequestMapping(value = "/cityController")
public class CityController {
    @Resource
    private CityService cityService;

    //展示所有用户
    @RequestMapping("/showAllCitys")
    public Response showAllCitys(){
        List<City> citys = cityService.showAllCitys();
        Response response = Response.newResponse();
        return response.setData(citys);
    }

    //根据id查询用户信息
    @RequestMapping("/showOneCityInfo")
    public Response showOneCityInfo(Integer id){
        Response response = Response.newResponse();
        City cityInfo = cityService.getCityByPrimarykey(id);
        return response.setData(cityInfo);
    }

    //更改用户信息
    @RequestMapping("/updateCityInfo")
    public Response updateCityInfo(@RequestBody City city){
        Response response = Response.newResponse();
        if (city.getId()!=null && ! "".equals(city.getId())){
            City tempCity = cityService.getCityByPrimarykey(city.getId());
            if (tempCity==null){
                return response.setCodeAndMessage(9999,"要更新的城市信息不存在！");
            }
            cityService.update(city);
            return response.OK();
        }
        return response.setCodeAndMessage(9999,"更改信息失败！");
    }

    //新增城市信息
    @RequestMapping("/addCity")
    public Response addCity(@RequestBody City cityInfo){
        Response response = Response.newResponse();
        cityService.insertOneCity(cityInfo);
        return response.OK();
    }
}
