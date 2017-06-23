package dongting.bwei.com.ecommerce.fragments;


import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import dongting.bwei.com.ecommerce.R;
import dongting.bwei.com.ecommerce.adapters.ThirdFragmentAdapter;
import dongting.bwei.com.ecommerce.bean.ShopBean;
import dongting.bwei.com.ecommerce.utils.StringUtils;


/**
 * A simple {@link Fragment} subclass.
 */
public class ThirdFragment extends Fragment {


    @BindView(R.id.third_recyclerview)
    RecyclerView thirdRecyclerview;
    @BindView(R.id.third_allselect)
    TextView thirdAllselect;
    @BindView(R.id.third_totalprice)
    TextView thirdTotalprice;
    @BindView(R.id.third_totalnum)
    TextView thirdTotalnum;
    @BindView(R.id.third_submit)
    TextView thirdSubmit;
    Unbinder unbinder;
    private ThirdFragmentAdapter adapter;

    public ThirdFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_third, container, false);

        unbinder = ButterKnife.bind(this, view);

        try {
            showData();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        LinearLayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);

        thirdRecyclerview.setLayoutManager(manager);

    }

    List<ShopBean.OrderDataBean.CartlistBean> mAllOrderList = new ArrayList<>();

    public void showData() throws IOException {

        adapter = new ThirdFragmentAdapter(getActivity());
        thirdRecyclerview.setAdapter(adapter);

        InputStream inputStream = getActivity().getAssets().open("shop.json");

        String string = StringUtils.convertStreamToString(inputStream);

        Gson gson = new Gson();
        ShopBean shopBean = gson.fromJson(string, ShopBean.class);

        List<ShopBean.OrderDataBean> orderDataBeanList = shopBean.getOrderData();

        for (int i = 0; i < orderDataBeanList.size(); i++) {
            List<ShopBean.OrderDataBean.CartlistBean> cartlist = orderDataBeanList.get(i).getCartlist();

            for (int j = 0; j < cartlist.size(); j++) {
                ShopBean.OrderDataBean.CartlistBean cartlistBean = orderDataBeanList.get(i).getCartlist().get(j);
                mAllOrderList.add(cartlistBean);
            }
        }
        setFirstState(mAllOrderList);

        adapter.setData(mAllOrderList);

        //
        adapter.setOnRefershListener(new ThirdFragmentAdapter.OnRefershListener() {
            @Override
            public void onRefersh(boolean isSelect,List<ShopBean.OrderDataBean.CartlistBean> list) {

                //标记底部 全选按钮
                if(isSelect){
                    Drawable left = getResources().getDrawable(R.drawable.shopcart_selected);
                    thirdAllselect.setCompoundDrawablesWithIntrinsicBounds(left,null,null,null);
                }else {
                    Drawable left = getResources().getDrawable(R.drawable.shopcart_unselected);
                    thirdAllselect.setCompoundDrawablesWithIntrinsicBounds(left,null,null,null);
                }

                //总价
                float mTotlaPrice = 0f;
                int mTotalNum = 0;
                for(int i=0;i<list.size();i++){
                    if(list.get(i).isSelect()){
                        mTotlaPrice += list.get(i).getPrice() * list.get(i).getCount() ;
                        mTotalNum += list.get(i).getCount();
                    }
                }

                thirdTotalprice.setText("总价 : " + mTotlaPrice  );

                thirdTotalnum.setText("共" + mTotalNum + "件商品");
            }
        });
    }

    /**
     * 标记第一条数据 isfirst 1 显示商户名称 2 隐藏
     *
     * @param list
     */
    public static void setFirstState(List<ShopBean.OrderDataBean.CartlistBean> list) {

        if (list.size() > 0) {
            list.get(0).setIsFirst(1);
            for (int i = 1; i < list.size(); i++) {

                if (list.get(i).getShopId() == list.get(i - 1).getShopId()) {
                    list.get(i).setIsFirst(2);
                } else {
                    list.get(i).setIsFirst(1);
                }
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.third_allselect, R.id.third_submit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.third_allselect:

                for(int i=0;i<mAllOrderList.size();i++){
                    ShopBean.OrderDataBean.CartlistBean cartlistBean = mAllOrderList.get(i);

                        cartlistBean.setSelect(true);
                    cartlistBean.setShopSelect(true);
                    adapter.notifyDataSetChanged();
                }

                break;
            case R.id.third_submit:

                Toast.makeText(getActivity(), "结算成功", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
