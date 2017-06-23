package dongting.bwei.com.ecommerce.activitys;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import dongting.bwei.com.ecommerce.R;

/**
 * 作者:${董婷}
 * 日期:2017/6/22
 * 描述:
 */

public class TitleSearchActivity extends Activity implements View.OnClickListener{

    private List<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_title_search);

        ImageView titlesearch_back =(ImageView) findViewById(R.id.titlesearch_back);
        titlesearch_back.setOnClickListener(this);

        GridView gv=(GridView) findViewById(R.id.titlesearch_gv);

        list = new ArrayList<>();
        list.add("育儿图书");
        list.add("防辐射服");
        list.add("儿童车");
        list.add("奶瓶");
        list.add("自行车");
        list.add("纸尿裤");
        list.add("奶粉");
        list.add("驱蚊液");
        list.add("推车");
        list.add("男童夏装");
        list.add("女童夏装");
        list.add("儿童玩具");

        gv.setAdapter(new MyAdapter());

    }

    class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView==null){
                convertView = View.inflate(TitleSearchActivity.this,R.layout.activity_title_search_item,null);
            }

            Button bt =(Button) convertView.findViewById(R.id.search_item);
            bt.setText(list.get(position));
            return convertView;
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.titlesearch_back:
                startActivity(new Intent(TitleSearchActivity.this,TabActivity.class));
                finish();
                break;
        }
    }
}
