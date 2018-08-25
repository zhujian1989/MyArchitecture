package jzhu.com.moduleusers.ui.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.BindView;
import com.alibaba.android.arouter.facade.annotation.Autowired;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import jzhu.com.libbase.base.BaseMvpFragment;
import jzhu.com.libprovider.model.UserModel;
import jzhu.com.libprovider.providers.ModuleSearchService;
import jzhu.com.moduleusers.R;
import jzhu.com.moduleusers.R2;
import jzhu.com.moduleusers.mvp.Presenter.UsersPresenter;
import jzhu.com.moduleusers.mvp.view.UsersView;
import jzhu.com.moduleusers.ui.adapter.UserAdapter;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

public class UsersFragment extends BaseMvpFragment<UsersPresenter> implements UsersView, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R2.id.refresh)
    SwipeRefreshLayout mSwipeRefreshLayout;

    @BindView(R2.id.recycler_view_users)
    RecyclerView mUsersRecyclerView;

    @BindView(R2.id.recycler_view_followers)
    RecyclerView mFollowersRecyclerView;

    @Autowired
    ModuleSearchService mSearchService;

    @Named("users")
    @Inject
    UserAdapter mUserAdapter;

    @Named("followers")
    @Inject
    UserAdapter mFollowersAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.module_user_fragment_users;
    }

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        initViews();
        loadData(); }

    @Override
    protected boolean injectRouter() {
        return true;
    }

    private void initViews() {
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mUsersRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mUsersRecyclerView.setAdapter(mUserAdapter);
        mUserAdapter.setOnItemClickListener(new UserAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v) {
                UserModel userModel = (UserModel) v.getTag();
                handleData(mSearchService.searchFollowersByName(userModel.getLogin()));
            }
        });


        mFollowersRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mFollowersRecyclerView.setAdapter(mFollowersAdapter);

    }

    @Override
    public void getUsersSuc(List<UserModel> list) {
        mUserAdapter.setData(list);
    }

    @Override
    public void getUsersFail(Throwable throwable) {

    }

    @Override
    public void showLoading() {
        mSwipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideLoading() {
        mSwipeRefreshLayout.setRefreshing(false);
    }

    private void loadData() {
        mPresenter.getUsers();
    }

    @Override
    public void onRefresh() {
        loadData();
    }

    private void handleData(Observable<List<UserModel>> observable) {
        Disposable disposable = observable.subscribeOn(Schedulers.io())
                                          .observeOn(AndroidSchedulers.mainThread())
                                          .subscribe(userModels -> mFollowersAdapter.setData(userModels));
        disposable.isDisposed();
    }

}
