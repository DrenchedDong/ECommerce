package dongting.bwei.com.ecommerce.fragments;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aspsine.irecyclerview.IRecyclerView;
import com.aspsine.irecyclerview.OnLoadMoreListener;
import com.aspsine.irecyclerview.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import dongting.bwei.com.ecommerce.R;
import dongting.bwei.com.ecommerce.adapters.IndexFragmentAdapter;
import dongting.bwei.com.ecommerce.base.BaseMvpFragment;
import dongting.bwei.com.ecommerce.bean.IndexBean;
import dongting.bwei.com.ecommerce.footer.LoadMoreFooterView;
import dongting.bwei.com.ecommerce.presenter.IndexFragmentPresenter;
import dongting.bwei.com.ecommerce.view.IndexFragmentView;
import dongting.bwei.com.ecommerce.view.IndexView;

public class IndexFragment extends BaseMvpFragment<IndexFragmentView, IndexFragmentPresenter> implements IndexView {

    private static final String ARG_PARAM1 = "param1";

    int mParam1;
    @BindView(R.id.recycleview_id)
    IRecyclerView recyclerView;
    Unbinder unbinder;
    private LoadMoreFooterView loadMoreFooterView;
    boolean refresh = true;

    List<IndexBean.DataBean> listAll = new ArrayList<>();
    IndexFragmentAdapter indexFragmentAdapter;

    Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    refresh = false;
                    //获取数据
                    presenter.getData(mParam1);
                    indexFragmentAdapter.notifyDataSetChanged();
                    recyclerView.setRefreshing(false);

                    break;
                case 2:
                    refresh = false;
                    //获取数据
                    presenter.getData(mParam1);

                    indexFragmentAdapter.notifyDataSetChanged();
                    loadMoreFooterView.setStatus(LoadMoreFooterView.Status.GONE);

                    break;
            }
        }
    };

    @Override
    public IndexFragmentPresenter initPresenter() {
        return new IndexFragmentPresenter(this);
    }

    public static IndexFragment newInstance(int param1) {
        IndexFragment fragment = new IndexFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getInt(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_index, container, false);

        unbinder = ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        loadMoreFooterView = (LoadMoreFooterView) recyclerView.getLoadMoreFooterView();

        LinearLayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);

        recyclerView.setLayoutManager(manager);

        presenter.getData(mParam1);

        //上拉加载更多
        recyclerView.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                loadMoreFooterView.setStatus(LoadMoreFooterView.Status.LOADING);
                handler.sendEmptyMessageDelayed(2, 2000);
            }
        });

//下拉刷新
        recyclerView.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {

                recyclerView.setRefreshing(true);
                listAll.clear();
                handler.sendEmptyMessageDelayed(1, 2000);

            }
        });
    }

    @Override
    public void onsuccess(IndexBean indexBean) {

       /* indexBean.getData().remove(2);
        indexBean.getData().remove(4);
        indexBean.getData().remove(6);
        indexBean.getData().remove(7);
        indexBean.getData().remove(9);
        indexBean.getData().remove(10);
        indexBean.getData().remove(20);*/

        listAll.addAll(indexBean.getData());

        System.out.println("开始 list.size() = " + listAll.size());

        indexFragmentAdapter = new IndexFragmentAdapter(getActivity(), listAll);
        recyclerView.setIAdapter(indexFragmentAdapter);

        indexFragmentAdapter.notifyDataSetChanged();

        // System.out.println("fragment数据返回成功：indexBean = " + indexBean.toString());
    }

    @Override
    public void onFail() {
        //System.out.println("fragment数据返回失败" );
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}