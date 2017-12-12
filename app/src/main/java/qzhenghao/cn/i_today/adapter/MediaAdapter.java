package qzhenghao.cn.i_today.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import qzhenghao.cn.i_today.R;
import qzhenghao.cn.i_today.beans.MediaBean;
import qzhenghao.cn.i_today.refreshview.recyclerview.BaseRecyclerAdapter;

/**
 *段子list数据适配器
 */
public class MediaAdapter extends BaseRecyclerAdapter<MediaAdapter.MainViewHolder> {

    private Context context;
    private List<MediaBean> mediaList;

    public MediaAdapter(Context context, List<MediaBean> mainItemBeanList) {
        this.context = context;
        this.mediaList = mainItemBeanList;
    }

    @Override
    public MainViewHolder getViewHolder(View view) {
        return new MainViewHolder(view);
    }

    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType, boolean isItem) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_media_layout, parent, false);
        return new MainViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MainViewHolder holder, int position, boolean isItem) {
        MainViewHolder mainViewHolder = holder;
        final MediaBean mediaItemBean = mediaList.get(position);
//        mainViewHolder.tvMainItem.setTextColor(Color.BLACK);
//        //如果是视频把内容显成红色，不是就显示内容
//        if (!StringUtil.isNullOrEmpty(mainItemBean.getMp4Url()) || !StringUtil.isNullOrEmpty(mainItemBean.getM3u8Url())) {
//            mainViewHolder.tvMainItem.setText(mainItemBean.getContent() + "\n请在详情页查看视频");
//            mainViewHolder.tvMainItem.setTextColor(Color.RED);
//        } else {
//            mainViewHolder.tvMainItem.setText(mainItemBean.getContent());
//        }
//        mainViewHolder.sdvMainItem.setVisibility(View.GONE);
//
//        //user
//        mainViewHolder.layoutUser.setVisibility(View.GONE);
//        //显示用户名
//        if (!StringUtil.isNullOrEmpty(mainItemBean.getUserName())) {
            mainViewHolder.tvUserName.setText(mediaItemBean.getName());
//            mainViewHolder.layoutUser.setVisibility(View.VISIBLE);
//        } else {
//            mainViewHolder.layoutUser.setVisibility(View.GONE);
//        }
//        //显示用户头像
//        if (!StringUtil.isNullOrEmpty(mainItemBean.getUserImg())) {
//            FrescoUtil.loadPicOnNet(mainViewHolder.sdvUserImg, mainItemBean.getUserImg());
//            mainViewHolder.sdvUserImg.setHierarchy(FrescoAttributeUtil.setCircleRingHierarchy(context, Color.BLUE, 2f));
//        }

        mainViewHolder.sdvUserImg.setImageResource(mediaItemBean.getImg());

        //显示段子图片
        mainViewHolder.sdvMainItem.setVisibility(View.GONE);
        if (mediaItemBean.getImg()!=0) {
            mainViewHolder.sdvMainItem.setVisibility(View.VISIBLE);

            mainViewHolder.sdvMainItem.setImageResource(mediaItemBean.getImg());
            //内容中图片的点击事件
            mainViewHolder.sdvMainItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    AlertDialog dialog = new AlertDialog.Builder(context)
                            .setTitle("节目单")//设置对话框的标题
                            .setMessage(mediaItemBean.getEpg())//设置对话框的内容
                            //设置对话框的按钮
                            .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            }).create();
                    dialog.show();



                }
            });
        }
        //设置图片listview点击事件
        mainViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




            }
        });
    }

    @Override
    public int getAdapterItemCount() {
        return mediaList.size();
    }

    /**
     * 视图的回收和复用
     */
    class MainViewHolder extends RecyclerView.ViewHolder {

        TextView tvMainItem;
        ImageView sdvMainItem;
        SimpleDraweeView sdvUserImg;
        TextView tvUserName;
        LinearLayout layoutUser;

        public MainViewHolder(View itemView) {
            super(itemView);
//            tvMainItem = (TextView) itemView.findViewById(R.id.tvMainItem);
            sdvMainItem = (ImageView) itemView.findViewById(R.id.sdvcctvItem);
            sdvUserImg = (SimpleDraweeView) itemView.findViewById(R.id.sdvUserImg);
            tvUserName = (TextView) itemView.findViewById(R.id.tvUserName);
//            layoutUser = (LinearLayout) itemView.findViewById(R.id.layoutUser);
        }
    }
}
