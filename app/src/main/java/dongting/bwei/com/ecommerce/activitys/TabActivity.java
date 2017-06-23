package dongting.bwei.com.ecommerce.activitys;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import dongting.bwei.com.ecommerce.R;
import dongting.bwei.com.ecommerce.fragments.FirstFragment;
import dongting.bwei.com.ecommerce.fragments.FourthFragment;
import dongting.bwei.com.ecommerce.fragments.SecondFragment;
import dongting.bwei.com.ecommerce.fragments.ThirdFragment;
import dongting.bwei.com.ecommerce.widget.ButtomLayout;
import dongting.bwei.com.ecommerce.widget.ButtomLayout.OnSelectListener;

/**
 * 作者:${董婷}
 * 日期:2017/6/20
 * 描述:
 */

public class TabActivity extends IActivity /*implements OnSelectListener*/ {

    private List<Fragment> fragments = new ArrayList<Fragment>();
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);

        fragmentManager = getSupportFragmentManager();

        createFragment(savedInstanceState);

        ButtomLayout buttomLayout = (ButtomLayout) findViewById(R.id.buttom_layout);

        buttomLayout.setOnSelectListener(new OnSelectListener() {
            @Override
            public void onSelect(int index) {
                switchFragment(index);
            }
        });

        switchFragment(0);
    }

    public void createFragment(Bundle savedInstanceState) {

        FirstFragment firstFragment = (FirstFragment) fragmentManager.findFragmentByTag("FirstFragment");
        SecondFragment secondFragment = (SecondFragment) fragmentManager.findFragmentByTag("SecondFragment");
        ThirdFragment thirdFragment = (ThirdFragment) fragmentManager.findFragmentByTag("ThirdFragment");
        FourthFragment fourthFragment = (FourthFragment) fragmentManager.findFragmentByTag("FourthFragment");

        if (firstFragment == null) {
            firstFragment = new FirstFragment();
        }

        if (secondFragment == null) {
            secondFragment = new SecondFragment();
        }
        if (thirdFragment == null) {
            thirdFragment = new ThirdFragment();
        }
        if (fourthFragment == null) {
            fourthFragment = new FourthFragment();
        }


        fragments.add(firstFragment);
        fragments.add(secondFragment);
        fragments.add(thirdFragment);
        fragments.add(fourthFragment);

    }

    public void switchFragment(int pos) {
        FragmentTransaction beginTransaction =
                fragmentManager.beginTransaction();

        if (!fragments.get(pos).isAdded()) {
            beginTransaction.add(R.id.container, fragments.get(pos), fragments.get(pos).getClass().getSimpleName());
        }

        for (int i = 0; i < fragments.size(); i++) {
            if (i == pos) {
                beginTransaction.show(fragments.get(i));
            } else {
                beginTransaction.hide(fragments.get(i));
            }
        }

        beginTransaction.commit();
    }
    private long exitTime = 0;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){
            if((System.currentTimeMillis()-exitTime) > 2000){
                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
