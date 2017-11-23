package fr.univtln.serverclient.utilities;

import org.androidannotations.rest.spring.annotations.Get;
import org.androidannotations.rest.spring.annotations.Rest;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.util.List;

import fr.univtln.serverclient.model.Profile;

/**
 * Created by damienlemenager on 23/11/2017.
 */

// On Emulator
//@Rest(rootUrl = "http://10.0.2.2:8080/server/api", converters = {MappingJackson2HttpMessageConverter.class, StringHttpMessageConverter.class})
// On device connect via USB for my wireless connection
@Rest(rootUrl = "http://192.168.43.114:8080/server/api", converters = {MappingJackson2HttpMessageConverter.class, StringHttpMessageConverter.class})
public interface IProfileAPI {

    @Get("/profiles")
    List<Profile> getProfiles();

}
