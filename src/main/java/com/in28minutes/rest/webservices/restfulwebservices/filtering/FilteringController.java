package com.in28minutes.rest.webservices.restfulwebservices.filtering;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class FilteringController {
    @GetMapping("/filtering")
    public MappingJacksonValue filtering() {
        SomeBean someBean = new SomeBean("value1", "value2", "value3");
        //dynamic filtering
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(someBean);
        //show field1 and field3
        SimpleBeanPropertyFilter filter =
                SimpleBeanPropertyFilter.filterOutAllExcept("field1","field3");
        FilterProvider filters =
                new SimpleFilterProvider().addFilter("SomeBeanFilter", filter );
        //add "SomeBeanFilter" in Bean
        mappingJacksonValue.setFilters(filters);
        return mappingJacksonValue;


    }

    @GetMapping("/filtering-list")
    public MappingJacksonValue filteringList() {//show field2 and field3
        List<SomeBean> list = Arrays.asList(new SomeBean("value1", "value2", "value3"),
                new SomeBean("value4", "value5", "value6"));
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(list);
        SimpleBeanPropertyFilter filter =
                SimpleBeanPropertyFilter.filterOutAllExcept("field2","field3");
        FilterProvider filters =
                new SimpleFilterProvider().addFilter("SomeBeanFilter", filter );
        mappingJacksonValue.setFilters(filters);

        return mappingJacksonValue;
    }

}