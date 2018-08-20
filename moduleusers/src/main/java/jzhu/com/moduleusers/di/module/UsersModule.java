package jzhu.com.moduleusers.di.module;

import dagger.Module;
import dagger.Provides;
import jzhu.com.moduleusers.Repository.UsersRepository;
import jzhu.com.moduleusers.Repository.UsersRepositoryImpl;
import jzhu.com.moduleusers.ui.adapter.UserAdapter;

import javax.inject.Named;

@Module
public class UsersModule {

    @Provides
    UsersRepository provideUsersRepository(){
        return  new UsersRepositoryImpl();
    }

    @Named("users")
    @Provides
    UserAdapter provideUsersAdapter(){
        return  new UserAdapter();
    }


    @Named("followers")
    @Provides
    UserAdapter provideFollowersAdapter(){
        return  new UserAdapter();
    }

}
