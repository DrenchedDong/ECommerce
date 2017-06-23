package dongting.bwei.com.ecommerce.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.xys.libzxing.zxing.activity.CaptureActivity;

import dongting.bwei.com.ecommerce.R;
import dongting.bwei.com.ecommerce.activitys.TitleMsgActivity;
import dongting.bwei.com.ecommerce.activitys.TitleSearchActivity;
import dongting.bwei.com.ecommerce.adapters.FirstFragmentAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class FirstFragment extends Fragment implements View.OnClickListener{
    private ImageView titleScan;
    private Button titleSearch;
    private ImageView titleMsg;

  /*  @BindView(R.id.title_scan)
    ImageView titleScan;
    @BindView(R.id.title_search)
    Button titleSearch;
    @BindView(R.id.title_msg)
    ImageView titleMsg;*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_first, container, false);

        //ButterKnife.bind(getActivity());
        initView(view);

        titleScan = (ImageView) view.findViewById(R.id.title_scan);
        titleSearch = (Button) view.findViewById(R.id.title_search);
        titleMsg = (ImageView) view.findViewById(R.id.title_msg);

        titleScan.setOnClickListener(this);
        titleSearch.setOnClickListener(this);
        titleMsg .setOnClickListener(this);

        return view ;
    }

    private void initView(View view) {

       TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tablayout_id);
        ViewPager viewPager = (ViewPager) view.findViewById(R.id.first_viewpager);

        FirstFragmentAdapter adapters = new FirstFragmentAdapter(getChildFragmentManager());

        viewPager.setAdapter(adapters);

        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
    }

    private void Scann() {
        Intent openCameraIntent = new Intent(getActivity(), CaptureActivity.class);
        startActivityForResult(openCameraIntent, 0);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_scan:
                Scann();
                break;
            case R.id.title_search:
                startActivity(new Intent(getActivity(), TitleSearchActivity.class));
                break;
            case R.id.title_msg:
                startActivity(new Intent(getActivity(), TitleMsgActivity.class));
                getActivity().overridePendingTransition(R.anim.anim_translate_in, R.anim.anim_translate);
                break;
        }
    /*@OnClick({R.id.title_scan, R.id.title_search, R.id.title_msg})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.title_scan:
                Toast.makeText(getActivity(), "二维码", Toast.LENGTH_SHORT).show();
                Scann();
                break;
            case R.id.title_search:
                startActivity(new Intent(getActivity(),TitleSearchActivity.class));
                break;
            case R.id.title_msg:
                startActivity(new Intent(getActivity(),TitleMsgActivity.class));
                getActivity().overridePendingTransition(R.anim.anim_translate_in,R.anim.anim_translate);
                break;
        }
    }*/
        }
    }