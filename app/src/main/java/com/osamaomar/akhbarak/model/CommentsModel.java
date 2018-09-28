package com.osamaomar.akhbarak.model;

import java.util.List;

public class CommentsModel {


    /**
     * code : 200
     * msg : success
     * Data : [{"CommentId":3,"UserId":83,"PostId":91,"Comment":"hahah","PhotoUrl":"http://tazafeed.com/uploads/comments/comment-15380732275bad228b245c6.png","Date":"2018-09-27","Time":"06:33:47 pm"},{"CommentId":4,"UserId":83,"PostId":91,"Comment":"hahah","PhotoUrl":"http://tazafeed.com/uploads/comments/comment-15380732415bad22995512d.png","Date":"2018-09-27","Time":"06:34:01 pm"},{"CommentId":5,"UserId":83,"PostId":91,"Comment":"hahah","PhotoUrl":"http://tazafeed.com/uploads/comments/comment-15380732595bad22abb0da8.png","Date":"2018-09-27","Time":"06:34:19 pm"},{"CommentId":6,"UserId":83,"PostId":91,"Comment":"hahah","PhotoUrl":"http://tazafeed.com/uploads/comments/comment-15380732745bad22ba1852d.png","Date":"2018-09-27","Time":"06:34:34 pm"},{"CommentId":7,"UserId":83,"PostId":91,"Comment":"hahah","PhotoUrl":"http://tazafeed.com/uploads/comments/comment-15380733045bad22d89ca77.png","Date":"2018-09-27","Time":"06:35:04 pm"},{"CommentId":8,"UserId":83,"PostId":91,"Comment":"hahah","PhotoUrl":"http://tazafeed.com/uploads/comments/comment-15380733785bad2322b65eb.png","Date":"2018-09-27","Time":"06:36:18 pm"},{"CommentId":9,"UserId":83,"PostId":91,"Comment":"hahah","PhotoUrl":"http://tazafeed.com/uploads/comments/comment-15380734325bad235822000.png","Date":"2018-09-27","Time":"06:37:12 pm"},{"CommentId":10,"UserId":83,"PostId":91,"Comment":"asdcsa","PhotoUrl":"http://tazafeed.com/uploads/comments/","Date":"2018-09-27","Time":"06:38:11 pm"},{"CommentId":11,"UserId":83,"PostId":91,"Comment":"asdcsa","PhotoUrl":"http://tazafeed.com/uploads/comments/","Date":"2018-09-28","Time":"02:08:48 pm"},{"CommentId":12,"UserId":83,"PostId":91,"Comment":"osama","PhotoUrl":"http://tazafeed.com/uploads/comments/","Date":"2018-09-28","Time":"02:10:24 pm"},{"CommentId":13,"UserId":83,"PostId":91,"Comment":"osama","PhotoUrl":"http://tazafeed.com/uploads/comments/","Date":"2018-09-28","Time":"02:12:14 pm"},{"CommentId":14,"UserId":83,"PostId":91,"Comment":"osama","PhotoUrl":"http://tazafeed.com/uploads/comments/","Date":"2018-09-28","Time":"02:12:54 pm"},{"CommentId":15,"UserId":83,"PostId":91,"Comment":"osama","PhotoUrl":"http://tazafeed.com/uploads/comments/","Date":"2018-09-28","Time":"02:13:23 pm"},{"CommentId":16,"UserId":83,"PostId":91,"Comment":"osama","PhotoUrl":"http://tazafeed.com/uploads/comments/","Date":"2018-09-28","Time":"02:13:55 pm"},{"CommentId":17,"UserId":83,"PostId":91,"Comment":"osama","PhotoUrl":"http://tazafeed.com/uploads/comments/","Date":"2018-09-28","Time":"02:47:51 pm"},{"CommentId":18,"UserId":83,"PostId":91,"Comment":"osama","PhotoUrl":"http://tazafeed.com/uploads/comments/comment-15381461015bae3f3540185.png","Date":"2018-09-28","Time":"02:48:21 pm"},{"CommentId":19,"UserId":83,"PostId":91,"Comment":"osama","PhotoUrl":"http://tazafeed.com/uploads/comments/comment-15381461125bae3f407fa94.png","Date":"2018-09-28","Time":"02:48:32 pm"},{"CommentId":20,"UserId":83,"PostId":91,"Comment":"osama","PhotoUrl":"http://tazafeed.com/uploads/comments/comment-15381461205bae3f48a2544.png","Date":"2018-09-28","Time":"02:48:40 pm"},{"CommentId":22,"UserId":83,"PostId":91,"Comment":"yyy","PhotoUrl":"http://tazafeed.com/uploads/comments/","Date":"2018-09-28","Time":"02:53:56 pm"},{"CommentId":23,"UserId":83,"PostId":91,"Comment":"osama","PhotoUrl":"http://tazafeed.com/uploads/comments/","Date":"2018-09-28","Time":"02:59:32 pm"},{"CommentId":24,"UserId":83,"PostId":91,"Comment":"osama","PhotoUrl":"http://tazafeed.com/uploads/comments/","Date":"2018-09-28","Time":"03:00:51 pm"}]
     */

    private int code;
    private String msg;
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

    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> Data) {
        this.Data = Data;
    }

    public static class DataBean {
        /**
         * CommentId : 3
         * UserId : 83
         * PostId : 91
         * Comment : hahah
         * PhotoUrl : http://tazafeed.com/uploads/comments/comment-15380732275bad228b245c6.png
         * Date : 2018-09-27
         * Time : 06:33:47 pm
         */

        private int CommentId;
        private int UserId;
        private int PostId;
        private String Comment;
        private String PhotoUrl;
        private String Date;
        private String Time;

        public int getCommentId() {
            return CommentId;
        }

        public void setCommentId(int CommentId) {
            this.CommentId = CommentId;
        }

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

        public String getComment() {
            return Comment;
        }

        public void setComment(String Comment) {
            this.Comment = Comment;
        }

        public String getPhotoUrl() {
            return PhotoUrl;
        }

        public void setPhotoUrl(String PhotoUrl) {
            this.PhotoUrl = PhotoUrl;
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
    }
}
