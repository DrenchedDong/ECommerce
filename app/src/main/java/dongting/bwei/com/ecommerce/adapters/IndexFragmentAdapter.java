package dongting.bwei.com.ecommerce.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import dongting.bwei.com.ecommerce.R;
import dongting.bwei.com.ecommerce.bean.IndexBean;

/**
 * 作者:${董婷}
 * 日期:2017/6/21
 * 描述:
 */
public class IndexFragmentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    public static final String base_image = "http://image3.suning.cn";

    private final int singleType = 0;

    private final int smallHorizontalType = 1;

    private final int smallType = 2;

    private final int middleWithType = 3;

    //private final int moreType = 4;

    private final int middleNoType = 4;

    private final int horizontalType = 5;

    private final int defaultType =10;

    private Context context;

    public List<IndexBean.DataBean> list;

    public IndexFragmentAdapter(Context context,List<IndexBean.DataBean> list) {
        this.context = context;
        this.list=list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
        RecyclerView.ViewHolder viewHolder = null;
        switch (viewType) {
//0
            case singleType:
                view = LayoutInflater.from(context).inflate(R.layout.singlepic_item, parent, false);
                viewHolder = new SinglePicViewHolder(view);

                break;
            //1
            case smallHorizontalType:
                view = LayoutInflater.from(context).inflate(R.layout.small_horizontal_item, parent, false);
                viewHolder = new SmallHorizontalType(view);
                break;
            //2
            case smallType:

                view = LayoutInflater.from(context).inflate(R.layout.small_item, parent, false);

                viewHolder = new SmallViewHolder(view);

                break;
            //3
            case middleWithType:

                view = LayoutInflater.from(context).inflate(R.layout.middle_with_item, parent, false);

                viewHolder = new MiddleWithType(view);

                break;

            //4
            case middleNoType:

                view = LayoutInflater.from(context).inflate(R.layout.middle_no_item, parent, false);

                viewHolder = new MiddleNoType(view);

                break;
            //5
            case horizontalType:

                view = LayoutInflater.from(context).inflate(R.layout.horizontalscroll_item, parent, false);
                viewHolder = new HorizontalScrollViewViewHolder(view);
                break;

            default:

                view = LayoutInflater.from(context).inflate(R.layout.index_default_item, parent, false);

                viewHolder = new DefaultViewHodler(view);

                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (list == null || list.size() == 0) {
            return;
        }

        if (holder instanceof SinglePicViewHolder) {

            SinglePicViewHolder holder1 = (SinglePicViewHolder) holder;

            String pic = list.get(position).getTag().get(0).getPicUrl();
            Glide.with(context).load(base_image + pic)
                    .into(holder1.imageView);

        }else if(holder instanceof SmallHorizontalType){
            SmallHorizontalType smallHorizontalType = (SmallHorizontalType) holder;

            String item_pic = list.get(position).getTag().get(0).getPicUrl();

            Glide.with(context).load(base_image+item_pic).error(R.drawable.error)
                    .into(smallHorizontalType.small_horizontal_iv);

            int size = list.get(position).getTag().size();

            for (int i = 1; i < size-1; i++) {

                String item_pict = list.get(position).getTag().get(i).getPicUrl();

                LinearLayout linearLayout = new LinearLayout(context);
                linearLayout.setOrientation(LinearLayout.VERTICAL);
                linearLayout.setPadding(10, 10, 10, 0);

                ImageView imageView = new ImageView(context);
                imageView.setLayoutParams(new LinearLayout.LayoutParams(230, 230));

                Glide.with(context).load(base_image + item_pict).error(R.drawable.miaoshaerror)
                        .into(imageView);

                TextView textView = new TextView(context);
                textView.setText("95");
                textView.setTextColor(Color.RED);
                textView.setTextSize(18f);
                textView.setGravity(Gravity.CENTER);

                linearLayout.addView(imageView);
                linearLayout.addView(textView);

                smallHorizontalType.small_horizontal_linearlayout.addView(linearLayout);
            }

        } else if(holder instanceof SmallViewHolder){
            SmallViewHolder smallViewHolder = (SmallViewHolder) holder;

            String item_pic1 = list.get(position).getTag().get(0).getPicUrl();
            Glide.with(context).load(base_image+item_pic1).error(R.drawable.error)
                    .into(smallViewHolder.small);

        } else if(holder instanceof MiddleWithType){
            MiddleWithType middleWithType = (MiddleWithType) holder;

            String item_pic1 = list.get(position).getTag().get(0).getPicUrl();
            Glide.with(context).load(base_image+item_pic1).error(R.drawable.paper)
                    .into(middleWithType.middle_with_iv);

            middleWithType.middle_with_title.setText(list.get(position).getTag().get(0).getElementName());
            middleWithType.middle_with__des.setText(list.get(position).getTag().get(0).getElementDesc());

        } else if(holder instanceof MiddleNoType){
            MiddleNoType middleNoType = (MiddleNoType) holder;

            String item_pic1 = list.get(position).getTag().get(0).getPicUrl();
            Glide.with(context).load(base_image+item_pic1).error(R.drawable.error)
                    .into(middleNoType.middle_no_iv);

        }else if (holder instanceof HorizontalScrollViewViewHolder) {
            HorizontalScrollViewViewHolder horizontalScrollViewViewHolder = (HorizontalScrollViewViewHolder) holder;

            int size = list.get(position).getTag().size();

            for (int i = 1; i < size; i++) {

                String item_pict = list.get(position).getTag().get(i).getPicUrl();

                LinearLayout linearLayout = new LinearLayout(context);
                linearLayout.setOrientation(LinearLayout.VERTICAL);
                linearLayout.setPadding(10, 10, 10, 0);

                ImageView imageView = new ImageView(context);
                imageView.setLayoutParams(new LinearLayout.LayoutParams(230, 230));

                Glide.with(context).load(base_image + item_pict).error(R.drawable.miaoshaerror)
                        .into(imageView);

                TextView textView = new TextView(context);
                textView.setText("95");
                textView.setTextColor(Color.RED);
                textView.setTextSize(18f);
                textView.setGravity(Gravity.CENTER);

                linearLayout.addView(imageView);
                linearLayout.addView(textView);

                horizontalScrollViewViewHolder.horizontal_linearlayout.addView(linearLayout);
            }
        }else if(holder instanceof DefaultViewHodler){
            DefaultViewHodler defaultViewHodler = (DefaultViewHodler) holder;
            defaultViewHodler.textView.setVisibility(View.GONE);

        }
    }

    @Override
    public int getItemViewType(int position) {

        int type = 0 ;
        switch (position){
            case 0:
                type = singleType ;
                break;
            case 1:
                type = smallHorizontalType ;
                break;
            case 2:
                type = defaultType;
                break;
            case 3:
                type = smallType ;
                break;
            case 4:
                type= defaultType;
                break;
            case 5:
                type= middleWithType;
                break;
            case 6:
                type= defaultType;
                break;
            case 7:
                type= defaultType;
                break;
            case 8:
                type= middleWithType;
                break;
            case 9:
                type= defaultType;
                break;
            case 10:
                type= defaultType;
                break;
            case 11:
                type= smallType;
                break;
            case 12:
                type= middleNoType;
                break;
            case 13:
                type= horizontalType;
                break;

            case 14:
                type= middleNoType;
                break;
            case 15:
                type= horizontalType;
                break;
            case 16:
                type= middleNoType;
                break;
            case 17:
                type= horizontalType;
                break;
            case 18:
                type= middleNoType;
                break;
            case 19:
                type= horizontalType;
                break;
            case 20:
                type= defaultType;
                break;
        }
        return type;
    }


    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    // 单张图片
    class SinglePicViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.singleitem_imageview)
        ImageView imageView;

        public SinglePicViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    //水平滚动的item
    class HorizontalScrollViewViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.horizontal_linearlayout)
        LinearLayout horizontal_linearlayout;

        public HorizontalScrollViewViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    //SmallViewHolder
    class SmallViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.small)
        ImageView small;

        public SmallViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
    //
    class MiddleWithType extends RecyclerView.ViewHolder {
        @BindView(R.id.middle_with_iv)
        ImageView middle_with_iv;
        @BindView(R.id.middle_with_title)
        TextView middle_with_title;
        @BindView(R.id.middle_with__des)
        TextView middle_with__des;

        public MiddleWithType(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class MiddleNoType extends RecyclerView.ViewHolder {

        @BindView(R.id.middle_no_iv)
        ImageView middle_no_iv;

        public MiddleNoType(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class SmallHorizontalType extends RecyclerView.ViewHolder{
        @BindView(R.id.small_horizontal_iv)
        ImageView small_horizontal_iv;

        @BindView(R.id.small_horizontal_linearlayout)
        LinearLayout small_horizontal_linearlayout;

        public SmallHorizontalType(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    class DefaultViewHodler extends RecyclerView.ViewHolder {

        @BindView(R.id.indexfragment_textview)
        TextView textView;

        public DefaultViewHodler(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}

