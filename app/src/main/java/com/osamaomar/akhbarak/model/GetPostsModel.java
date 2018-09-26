package com.osamaomar.akhbarak.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class GetPostsModel {


    /**
     * code : 200
     * msg : success
     * total_items : 5
     * count_items : 5
     * previousPageUrl : null
     * nextPageUrl : null
     * Data : [{"UserId":80,"PostId":80,"Title":"اسامه الصقر هنا","Photo_Url":"http://tazafeed.com/uploads/posts/","Video_Url":null,"Place_name":null,"Latitude":null,"Longitude":null,"Date":"2018-09-21","Time":"10:43:28 pm","Tags":[{"TagId":146,"Name":""},{"TagId":147,"Name":""}],"Photos":[{"Photo_url":"http://tazafeed.com/uploads/posts/post-15375698085ba574108b527.jpg"},{"Photo_url":"http://tazafeed.com/uploads/posts/post-15375698085ba574108d33b.png"}],"UserData":{"USER_ID":80,"User_Name":"@gmail.com","User_Photo":"http://tazafeed.com/uploads/users/"}},{"UserId":45,"PostId":81,"Title":"اسامه الصقر من جديد","Photo_Url":"http://tazafeed.com/uploads/posts/post-15376538675ba6bc6bb5bb0.jpg","Video_Url":"http://tazafeed.com/uploads/posts/video-15376538675ba6bc6bb5d4a.mp4","Place_name":null,"Latitude":null,"Longitude":null,"Date":"2018-09-22","Time":"10:04:27 pm","Tags":[],"Photos":[{"Photo_url":"http://tazafeed.com/uploads/posts/post-15376538675ba6bc6bbe16d.png"},{"Photo_url":"http://tazafeed.com/uploads/posts/post-15376538675ba6bc6bbf2c4.png"}],"UserData":{"USER_ID":45,"User_Name":"dvbahaa2","User_Photo":"http://tazafeed.com/uploads/users/section-15361906105b906892b3d22.png"}},{"UserId":60,"PostId":82,"Title":"بوست بصورة وفيديو","Photo_Url":"http://tazafeed.com/uploads/posts/post-15377031655ba77cfd2e58b.jpg","Video_Url":"http://tazafeed.com/uploads/posts/video-15377031655ba77cfd2e715.mp4","Place_name":null,"Latitude":null,"Longitude":null,"Date":"2018-09-23","Time":"11:46:05 am","Tags":[],"Photos":[{"Photo_url":"http://tazafeed.com/uploads/posts/post-15377031655ba77cfd37031.png"}],"UserData":{"USER_ID":60,"User_Name":"ttt","User_Photo":"http://tazafeed.com/uploads/users/"}},{"UserId":81,"PostId":83,"Title":"لقشيسابىل","Photo_Url":"http://tazafeed.com/uploads/posts/post-15377555655ba849ad3dfeb.jpg","Video_Url":null,"Place_name":"شلسقاى","Latitude":null,"Longitude":null,"Date":"2018-09-24","Time":"02:19:25 am","Tags":[],"Photos":[],"UserData":{"USER_ID":81,"User_Name":"ad1","User_Photo":"http://tazafeed.com/uploads/users/user-15377511645ba8387c24bf1.jpg"}},{"UserId":2,"PostId":84,"Title":"تطبيق طازة","Photo_Url":"http://tazafeed.com/uploads/posts/","Video_Url":null,"Place_name":null,"Latitude":null,"Longitude":null,"Date":"2018-09-24","Time":"03:33:51 pm","Tags":[],"Photos":[],"UserData":[]}]
     */

    private int code;
    private String msg;
    private int total_items;
    private int count_items;
    private Object previousPageUrl;
    private Object nextPageUrl;
    private List<DataBean> Data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getTotal_items() {
        return total_items;
    }

    public void setTotal_items(int total_items) {
        this.total_items = total_items;
    }

    public int getCount_items() {
        return count_items;
    }

    public void setCount_items(int count_items) {
        this.count_items = count_items;
    }

    public Object getPreviousPageUrl() {
        return previousPageUrl;
    }

    public void setPreviousPageUrl(Object previousPageUrl) {
        this.previousPageUrl = previousPageUrl;
    }

    public Object getNextPageUrl() {
        return nextPageUrl;
    }

    public void setNextPageUrl(Object nextPageUrl) {
        this.nextPageUrl = nextPageUrl;
    }

    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> Data) {
        this.Data = Data;
    }

    public static class DataBean implements Parcelable {
        /**
         * UserId : 80
         * PostId : 80
         * Title : اسامه الصقر هنا
         * Photo_Url : http://tazafeed.com/uploads/posts/
         * Video_Url : null
         * Place_name : null
         * Latitude : null
         * Longitude : null
         * Date : 2018-09-21
         * Time : 10:43:28 pm
         * Tags : [{"TagId":146,"Name":""},{"TagId":147,"Name":""}]
         * Photos : [{"Photo_url":"http://tazafeed.com/uploads/posts/post-15375698085ba574108b527.jpg"},{"Photo_url":"http://tazafeed.com/uploads/posts/post-15375698085ba574108d33b.png"}]
         * UserData : {"USER_ID":80,"User_Name":"@gmail.com","User_Photo":"http://tazafeed.com/uploads/users/"}
         */

        private int UserId;
        private int PostId;
        private String Title;
        private String Photo_Url;
        private String Video_Url;
        private String Place_name;
        private String Latitude;
        private String Longitude;
        private String Date;
        private String Time;
        private UserDataBean UserData;
       // private List<TagsBean> Tags;
        private List<PhotosBean> Photos;

        public int getUserId() {
            return UserId;
        }

        public void setUserId(int UserId) {
            this.UserId = UserId;
        }

        public int getPostId() {
            return PostId;
        }

        public void setPostId(int PostId) {
            this.PostId = PostId;
        }

        public String getTitle() {
            return Title;
        }

        public void setTitle(String Title) {
            this.Title = Title;
        }

        public String getPhoto_Url() {
            return Photo_Url;
        }

        public void setPhoto_Url(String Photo_Url) {
            this.Photo_Url = Photo_Url;
        }

        public Object getVideo_Url() {
            return Video_Url;
        }

        public void setVideo_Url(String Video_Url) {
            this.Video_Url = Video_Url;
        }

        public String getPlace_name() {
            return Place_name;
        }

        public void setPlace_name(String Place_name) {
            this.Place_name = Place_name;
        }

        public String getLatitude() {
            return Latitude;
        }

        public void setLatitude(String Latitude) {
            this.Latitude = Latitude;
        }

        public String getLongitude() {
            return Longitude;
        }

        public void setLongitude(String Longitude) {
            this.Longitude = Longitude;
        }

        public String getDate() {
            return Date;
        }

        public void setDate(String Date) {
            this.Date = Date;
        }

        public String getTime() {
            return Time;
        }

        public void setTime(String Time) {
            this.Time = Time;
        }

        public UserDataBean getUserData() {
            return UserData;
        }

        public void setUserData(UserDataBean UserData) {
            this.UserData = UserData;
        }

//        public List<TagsBean> getTags() {
//            return Tags;
//        }

        public List<PhotosBean> getPhotos() {
            return Photos;
        }

        public void setPhotos(List<PhotosBean> Photos) {
            this.Photos = Photos;
        }



        public static class UserDataBean implements Parcelable {
            /**
             * USER_ID : 80
             * User_Name : @gmail.com
             * User_Photo : http://tazafeed.com/uploads/users/
             */

            private int USER_ID;
            private String User_Name;
            private String User_Photo;

            public int getUSER_ID() {
                return USER_ID;
            }

            public void setUSER_ID(int USER_ID) {
                this.USER_ID = USER_ID;
            }

            public String getUser_Name() {
                return User_Name;
            }

            public void setUser_Name(String User_Name) {
                this.User_Name = User_Name;
            }

            public String getUser_Photo() {
                return User_Photo;
            }

            public void setUser_Photo(String User_Photo) {
                this.User_Photo = User_Photo;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeInt(this.USER_ID);
                dest.writeString(this.User_Name);
                dest.writeString(this.User_Photo);
            }

            public UserDataBean() {
            }

            protected UserDataBean(Parcel in) {
                this.USER_ID = in.readInt();
                this.User_Name = in.readString();
                this.User_Photo = in.readString();
            }

            public static final Creator<UserDataBean> CREATOR = new Creator<UserDataBean>() {
                @Override
                public UserDataBean createFromParcel(Parcel source) {
                    return new UserDataBean(source);
                }

                @Override
                public UserDataBean[] newArray(int size) {
                    return new UserDataBean[size];
                }
            };
        }

        public static class TagsBean {
            /**
             * TagId : 146
             * Name :
             */

            private int TagId;
            private String Name;

            public int getTagId() {
                return TagId;
            }

            public void setTagId(int TagId) {
                this.TagId = TagId;
            }

            public String getName() {
                return Name;
            }

            public void setName(String Name) {
                this.Name = Name;
            }
        }

        public static class PhotosBean implements Parcelable {
            /**
             * Photo_url : http://tazafeed.com/uploads/posts/post-15375698085ba574108b527.jpg
             */

            private String Photo_url;

            public String getPhoto_url() {
                return Photo_url;
            }

            public void setPhoto_url(String Photo_url) {
                this.Photo_url = Photo_url;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.Photo_url);
            }

            public PhotosBean() {
            }

            protected PhotosBean(Parcel in) {
                this.Photo_url = in.readString();
            }

            public static final Creator<PhotosBean> CREATOR = new Creator<PhotosBean>() {
                @Override
                public PhotosBean createFromParcel(Parcel source) {
                    return new PhotosBean(source);
                }

                @Override
                public PhotosBean[] newArray(int size) {
                    return new PhotosBean[size];
                }
            };
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.UserId);
            dest.writeInt(this.PostId);
            dest.writeString(this.Title);
            dest.writeString(this.Photo_Url);
            dest.writeString(this.Video_Url);
            dest.writeString(this.Place_name);
            dest.writeString(this.Latitude);
            dest.writeString(this.Longitude);
            dest.writeString(this.Date);
            dest.writeString(this.Time);
            dest.writeParcelable(this.UserData, flags);
            dest.writeList(this.Photos);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.UserId = in.readInt();
            this.PostId = in.readInt();
            this.Title = in.readString();
            this.Photo_Url = in.readString();
            this.Video_Url = in.readString();
            this.Place_name = in.readString();
            this.Latitude = in.readString();
            this.Longitude = in.readString();
            this.Date = in.readString();
            this.Time = in.readString();
            this.UserData = in.readParcelable(UserDataBean.class.getClassLoader());
            this.Photos = new ArrayList<PhotosBean>();
            in.readList(this.Photos, PhotosBean.class.getClassLoader());
        }

        public static final Parcelable.Creator<DataBean> CREATOR = new Parcelable.Creator<DataBean>() {
            @Override
            public DataBean createFromParcel(Parcel source) {
                return new DataBean(source);
            }

            @Override
            public DataBean[] newArray(int size) {
                return new DataBean[size];
            }
        };
    }
}
