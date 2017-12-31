package com.developer.annant.gopaltyres.Extras_imp;

/**
 * Created by Annant on 05-01-2017.
 */

public class TyreDataVariable {

    private String mTyreSize;
    private String mTreadName;
    private String mPrice;
    //   private int mResourceImageView;


    public TyreDataVariable() {
    }

    /*
        public TyreDataVariable(String TreadName, String TyreSize, String Price, int ResourceImageView) {
            mTreadName = TreadName;
            mTyreSize = TyreSize;
            mPrice = Price;
            mResourceImageView = ResourceImageView;
        }
    */
    public TyreDataVariable(String TyreSize, String TreadName, String Price) {
        mTreadName = TreadName;
        mTyreSize = TyreSize;
        mPrice = Price;
        //mResourceImageView = ResourceImageView;
    }

    public String getTyreSize() {
        return mTyreSize;
    }

    /*
        public int getResourceImageView() {
            return mResourceImageView;
        }

        public void setResourceImageView(int setResourceImageView) {
            this.mResourceImageView = setResourceImageView;
        }
    */
    public void setTyreSize(String mTyreSize) {
        this.mTyreSize = mTyreSize;
    }

    public String getTreadName() {
        return mTreadName;
    }

    public void setTreadName(String mTreadName) {
        this.mTreadName = mTreadName;
    }

    public String getPrice() {
        return mPrice;
    }

    public void setPrice(String mPrice) {
        this.mPrice = mPrice;
    }

}
