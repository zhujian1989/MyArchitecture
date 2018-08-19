package jzhu.com.moduleusers.di.module;

import dagger.Module;
import dagger.Provides;
import jzhu.com.libbase.di.PerScoped;
import jzhu.com.moduleusers.Repository.UsersRepository;
import jzhu.com.moduleusers.Repository.UsersRepositoryImpl;

@Module
public class UsersModule {

    @PerScoped
    @Provides
    UsersRepository provideUsersRepository(){
        return  new UsersRepositoryImpl();
    }

}
