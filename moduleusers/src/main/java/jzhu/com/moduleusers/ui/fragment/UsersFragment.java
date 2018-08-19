package jzhu.com.moduleusers.ui.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import butterknife.BindView;
import com.alibaba.android.arouter.facade.annotation.Autowired;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import jzhu.com.libbase.base.BaseMvpFragment;
import jzhu.com.libprovider.model.UserModel;
import jzhu.com.libprovider.providers.ModuleSearchService;
import jzhu.com.moduleusers.R;
import jzhu.com.moduleusers.R2;
import jzhu.com.moduleusers.mvp.UsersPresenter;
import jzhu.com.moduleusers.mvp.UsersView;
import jzhu.com.moduleusers.ui.adapter.UserAdapter;

import java.util.List;

public class UsersFragment extends BaseMvpFragment<UsersPresenter> implements UsersView, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R2.id.refresh)
    SwipeRefreshLayout mSwipeRefreshLayout;

    @BindView(R2.id.recycler_view)
    RecyclerView mRecyclerView;

    @Autowired
    ModuleSearchService mSearchService;

    private UserAdapter mUserAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.module_user_fragment_users;
    }

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        initViews();
        mPresenter.onAttachView(this);
        loadData();
    }

    @Override
    protected boolean injectRouter() {
        return true;
    }

    private void initViews() {
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mUserAdapter = new UserAdapter();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mUserAdapter);
        mUserAdapter.setOnItemClickListener(new UserAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v) {
                UserModel userModel = (UserModel) v.getTag();
                handleData(mSearchService.searchFollowersByName(userModel.getLogin()));
            }
        });
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

    @Override
    public void onDestroy() {
        mPresenter.onDetachView();
        super.onDestroy();
    }

    private void handleData(Observable<List<UserModel>> observable) {
        Disposable disposable = observable.subscribeOn(Schedulers.io())
                                          .observeOn(AndroidSchedulers.mainThread())
                                          .subscribe(new Consumer<List<UserModel>>() {
                                              @Override
                                              public void accept(List<UserModel> userModels) throws Exception {

                                                  Log.i("zj", "size:" + userModels.size());
                                              }
                                          }, new Consumer<Throwable>() {
                                              @Override
                                              public void accept(Throwable throwable) throws Exception {
                                              }
                                          });
        disposable.isDisposed();
    }

}
