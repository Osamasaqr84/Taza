package com.osamaomar.akhbarak.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class PostDetailsModel implements Parcelable{

    public static final int TEXT_TYPE = 0;
    public static final int MEDIA_TYPE = 1;
    public static final int COMMENT_TYPE = 2;

    private GetPostsModel.DataBean basePost;
    private List<GetPostsModel.DataBean.PhotosBean> imageList;
    private List<String> commentList;
    private int type;


    public PostDetailsModel(GetPostsModel.DataBean basePost, List<GetPostsModel.DataBean.PhotosBean> imageList, List<String> commentList,int mType) {
        this.basePost = basePost;
        this.imageList = imageList;
        this.commentList = commentList;
        this.type = mType;
    }


    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public GetPostsModel.DataBean getBasePost() {
        return basePost;
    }

    public void setBasePost(GetPostsModel.DataBean basePost) {
        this.basePost = basePost;
    }

    public List<GetPostsModel.DataBean.PhotosBean> getImageList() {
        return imageList;
    }

    public void setImageList(List<GetPostsModel.DataBean.PhotosBean> imageList) {
        this.imageList = imageList;
    }

    public List<String> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<String> commentList) {
        this.commentList = commentList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.basePost, flags);
        dest.writeTypedList(this.imageList);
        dest.writeStringList(this.commentList);
        dest.writeInt(this.type);
    }

    public PostDetailsModel(Parcel in) {
        this.basePost = in.readParcelable(GetPostsModel.DataBean.class.getClassLoader());
        this.imageList = in.createTypedArrayList(GetPostsModel.DataBean.PhotosBean.CREATOR);
        this.commentList = in.createStringArrayList();
        this.type = in.readInt();
    }

    public static final Creator<PostDetailsModel> CREATOR = new Creator<PostDetailsModel>() {
        @Override
        public PostDetailsModel createFromParcel(Parcel source) {
            return new PostDetailsModel(source);
        }

        @Override
        public PostDetailsModel[] newArray(int size) {
            return new PostDetailsModel[size];
        }
    };
}
