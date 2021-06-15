package com.example.miwok;

public class word {
    private String mDefaultTranslation;
    private String mMiwokTranslation;
    private int mImageResourceId = -1;
    private int mAudioResourceId;

    public word(String DefaultTranslation, String MiwokTranslation, int AudioResourceId){
        mDefaultTranslation=DefaultTranslation;
        mMiwokTranslation=MiwokTranslation;
        mAudioResourceId = AudioResourceId;
    }

    public word(String DefaultTranslation, String MiwokTranslation, int ImageResourceId, int AudioResourceId){
        mDefaultTranslation=DefaultTranslation;
        mMiwokTranslation=MiwokTranslation;
        mImageResourceId=ImageResourceId;
        mAudioResourceId = AudioResourceId;
    }
    public String getDefaultTrasnlation(){
        return mDefaultTranslation;
    }
    public String getmMiwokTranslation(){
        return mMiwokTranslation;
    }

    public int getmImageId() {
        return mImageResourceId;
    }

    public boolean hasImage(){
        return mImageResourceId != -1;
    }

    public int getmAudioResourceId(){
        return mAudioResourceId;
    }
}
