9
https://raw.githubusercontent.com/idanapp/IdanPlusPlus/master/app/src/main/java/com/example/idan/plusplus/V2/Services/Retrofit2Services/IRetrofitServices.java
package com.example.idan.plusplus.V2.Services.Retrofit2Services;



import com.example.idan.plusplus.V2.App.AppModule;
import com.example.idan.plusplus.V2.Services.Retrofit2Services.Epgs.Israel.Live.Channell12Service.IChannell12Service;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Component;

@Component(modules = AppModule.class)
@Singleton
public interface IRetrofitServices {

    @Named(AppModule.PROVIDER_GENERAL_SERVICE)
    IBaseService getGeneralService();

    @Named(AppModule.PROVIDER_CHANNELL_12_SERVICE)
    IChannell12Service getChannell12Service();

}
