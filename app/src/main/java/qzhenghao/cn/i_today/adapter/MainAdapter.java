package qzhenghao.cn.i_today.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import qzhenghao.cn.i_today.R;
import qzhenghao.cn.i_today.activity.MainDetailActivity;
import qzhenghao.cn.i_today.activity.PreGifviewActivity;
import qzhenghao.cn.i_today.activity.PreviewActivity;
import qzhenghao.cn.i_today.beans.MainDataItemBean;
import qzhenghao.cn.i_today.refreshview.recyclerview.BaseRecyclerAdapter;
import qzhenghao.cn.i_today.utils.ControllerListenerUtil;
import qzhenghao.cn.i_today.utils.FrescoAttributeUtil;
import qzhenghao.cn.i_today.utils.FrescoUtil;
import qzhenghao.cn.i_today.utils.MyUtils;
import qzhenghao.cn.i_today.utils.StringUtil;

/**
 *段子list数据适配器
 */
public class MainAdapter extends BaseRecyclerAdapter<MainAdapter.MainViewHolder> {

    private Context context;
    private List<MainDataItemBean> mainItemBeanList;

    public MainAdapter(Context context, List<MainDataItemBean> mainItemBeanList) {
        this.context = context;
        this.mainItemBeanList = mainItemBeanList;
    }

    @Override
    public MainViewHolder getViewHolder(View view) {
        return new MainViewHolder(view);
    }

    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType, boolean isItem) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_main_layout, parent, false);
        return new MainViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MainViewHolder holder, int position, boolean isItem) {
        MainViewHolder mainViewHolder = holder;
        final MainDataItemBean mainItemBean = (MainDataItemBean) mainItemBeanList.get(position);
        mainViewHolder.tvMainItem.setTextColor(Color.BLACK);
        //如果是视频把内容显成红色，不是就显示内容
        if (!StringUtil.isNullOrEmpty(mainItemBean.getMp4Url()) || !StringUtil.isNullOrEmpty(mainItemBean.getM3u8Url())) {
            mainViewHolder.tvMainItem.setText(mainItemBean.getContent() + "\n请在详情页查看视频");
            mainViewHolder.tvMainItem.setTextColor(Color.RED);
        } else {
            mainViewHolder.tvMainItem.setText(mainItemBean.getContent());
        }
        mainViewHolder.sdvMainItem.setVisibility(View.GONE);

        //user
        mainViewHolder.layoutUser.setVisibility(View.GONE);
        //显示用户名
        if (!StringUtil.isNullOrEmpty(mainItemBean.getUserName())) {
            mainViewHolder.tvUserName.setText(mainItemBean.getUserName());
            mainViewHolder.layoutUser.setVisibility(View.VISIBLE);
        } else {
            mainViewHolder.layoutUser.setVisibility(View.GONE);
        }
        //显示用户头像
        if (!StringUtil.isNullOrEmpty(mainItemBean.getUserImg())) {
            FrescoUtil.loadPicOnNet(mainViewHolder.sdvUserImg, mainItemBean.getUserImg());
            mainViewHolder.sdvUserImg.setHierarchy(FrescoAttributeUtil.setCircleRingHierarchy(context, Color.BLUE, 2f));
        }
        //显示段子图片
        mainViewHolder.sdvMainItem.setVisibility(View.GONE);
        if (!StringUtil.isNullOrEmpty(mainItemBean.getImage())) {
            mainViewHolder.sdvMainItem.setVisibility(View.VISIBLE);
            final String img = mainItemBean.getImage();
            ControllerListenerUtil.setControllerListener(mainViewHolder.sdvMainItem, img, MyUtils.getScreenWidth(context));
            //内容中图片的点击事件
            mainViewHolder.sdvMainItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mainItemBean.getIsGif() != 1) {
                        ArrayList<String> list = new ArrayList<String>();
                        list.add(img);
                        Intent intent = new Intent(context, PreviewActivity.class);
                        intent.putStringArrayListExtra("list", list);
                        context.startActivity(intent);
                    } else {
                        Intent intent = new Intent(context, PreGifviewActivity.class);
                        intent.putExtra("url", img);
                        context.startActivity(intent);
                    }
                }
            });
        } else {
            mainViewHolder.sdvMainItem.setVisibility(View.GONE);
        }
        //设置图片listview点击事件
        mainViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MainDetailActivity.class);
                intent.putExtra("mainItemBean", (Parcelable) mainItemBean);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getAdapterItemCount() {
        return mainItemBeanList.size();
    }

    /**
     * 视图的回收和复用
     */
    class MainViewHolder extends RecyclerView.ViewHolder {

        TextView tvMainItem;
        SimpleDraweeView sdvMainItem;
        SimpleDraweeView sdvUserImg;
        TextView tvUserName;
        LinearLayout layoutUser;

        public MainViewHolder(View itemView) {
            super(itemView);
            tvMainItem = (TextView) itemView.findViewById(R.id.tvMainItem);
            sdvMainItem = (SimpleDraweeView) itemView.findViewById(R.id.sdvMainItem);
            sdvUserImg = (SimpleDraweeView) itemView.findViewById(R.id.sdvUserImg);
            tvUserName = (TextView) itemView.findViewById(R.id.tvUserName);
            layoutUser = (LinearLayout) itemView.findViewById(R.id.layoutUser);
        }
    }
}
