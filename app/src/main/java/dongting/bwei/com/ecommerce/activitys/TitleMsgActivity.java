package dongting.bwei.com.ecommerce.activitys;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import dongting.bwei.com.ecommerce.R;

public class TitleMsgActivity extends Activity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title_msg);

        ImageView titlesearch_back =(ImageView) findViewById(R.id.titlemsg_back);
        titlesearch_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.titlemsg_back:

                startActivity(new Intent(TitleMsgActivity.this,TabActivity.class));
                finish();
                overridePendingTransition(R.anim.anim_translate_back_in,R.anim.anim_translate_back);
                break;
        }
    }
}
