package jzhu.com.moduleusers.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import jzhu.com.moduleusers.R;
import jzhu.com.moduleusers.R2;
import jzhu.com.moduleusers.model.UserModel;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserHolder> {

    private List<UserModel> list;

    @NonNull
    @Override
    public UserAdapter.UserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.module_user_item_name, parent, false);
        UserHolder userHolder = new UserHolder(view);
        return userHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.UserHolder holder, int position) {
        UserModel userModel = list.get(position);
        holder.textView.setText(userModel.getLogin());
    }

    @Override
    public int getItemCount() {
        return null != list ? list.size() : 0;
    }

    public void setData(List<UserModel> list){
        this.list = list;
        notifyDataSetChanged();
    }

    class UserHolder extends RecyclerView.ViewHolder {

        @BindView(R2.id.username)
        TextView textView;

        public UserHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
