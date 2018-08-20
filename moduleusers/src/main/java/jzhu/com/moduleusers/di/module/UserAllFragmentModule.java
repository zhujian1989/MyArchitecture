package jzhu.com.moduleusers.di.module;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import jzhu.com.moduleusers.ui.fragment.UsersFragment;

@Module
public abstract class UserAllFragmentModule {

    @ContributesAndroidInjector(modules = {UsersModule.class})
    abstract UsersFragment contributeMainFragmentInjector();
}
