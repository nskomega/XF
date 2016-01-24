package ru.om.model.pojo;

public class Training {

    public String mUrl, mText, mPhoto, mTitle;

    private Training(Builder builder) {
        mUrl = builder.mUrl;
        mText = builder.mText;
        mTitle = builder.mTitle;
        mPhoto = builder.mPhoto;
    }

    public static class Builder {

        private String mUrl, mText, mPhoto, mTitle;

        public Builder setText(String text) {
            mText = text;
            return Builder.this;
        }

        public Builder setUrl(String url) {
            mUrl = url;
            return Builder.this;
        }

        public Builder setPhoto(String photo) {
            mPhoto = photo;
            return Builder.this;
        }

        public Builder setTitle(String title) {
            mTitle = title;
            return Builder.this;
        }

        public Training build() {
            return new Training(Builder.this);
        }
    }
}
