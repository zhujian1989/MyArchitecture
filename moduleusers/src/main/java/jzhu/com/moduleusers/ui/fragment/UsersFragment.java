package jzhu.com.moduleusers.ui.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import butterknife.BindView;
import jzhu.com.libbase.base.BaseMvpFragment;
import jzhu.com.moduleusers.R;
import jzhu.com.moduleusers.R2;
import jzhu.com.moduleusers.model.UserModel;
import jzhu.com.moduleusers.mvp.UsersPresenter;
import jzhu.com.moduleusers.mvp.UsersView;
import jzhu.com.moduleusers.ui.adapter.UserAdapter;

import java.util.List;

public class UsersFragment extends BaseMvpFragment<UsersPresenter> implements UsersView,SwipeRefreshLayout.OnRefreshListener {


    @BindView(R2.id.refresh)
    SwipeRefreshLayout mSwipeRefreshLayout;

    @BindView(R2.id.recycler_view)
    RecyclerView mRecyclerView;

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

    private void initViews(){
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mUserAdapter = new UserAdapter();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mUserAdapter);
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

    private void loadData(){
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
}
