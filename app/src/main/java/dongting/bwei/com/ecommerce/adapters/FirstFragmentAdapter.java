package dongting.bwei.com.ecommerce.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import dongting.bwei.com.ecommerce.fragments.IndexFragment;

/**
 * 作者:${董婷}
 * 日期:2017/6/20
 * 描述:
 */
public class FirstFragmentAdapter extends FragmentPagerAdapter {

    String[] titles = {"纸尿裤","奶粉","洗护喂养","玩具","服饰","图书"} ;


    public FirstFragmentAdapter(FragmentManager fragmentManager){
        super(fragmentManager);

    }

    @Override
    public Fragment getItem(int position) {
        return IndexFragment.newInstance(position);
    }

    @Override
    public int getCount() {
        return titles.length;
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
