package jzhu.com.moduleusers.mvp;

import jzhu.com.libbase.base.BaseView;
import jzhu.com.libprovider.model.UserModel;

import java.util.List;

public interface UsersView extends BaseView {

    void getUsersSuc(List<UserModel> list);

    void getUsersFail(Throwable throwable);

}
