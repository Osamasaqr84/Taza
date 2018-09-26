package com.osamaomar.akhbarak.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class ItemList implements Parcelable {


	private List<ItemImage> Images;

	public ItemList(List<ItemImage> itemImages) {
		super();
		Images = itemImages;

	}


	public List<ItemImage> getImages() {
		return Images;
	}

	public void setImages(List<ItemImage> images) {
		Images = images;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected ItemList(Parcel in) {
		Images = (List) in.readValue(List.class.getClassLoader());

	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeValue(Images);

	}

	public static final Creator<ItemList> CREATOR = new Creator<ItemList>() {
		@Override
		public ItemList createFromParcel(Parcel in) {
			return new ItemList(in);
		}

		@Override
		public ItemList[] newArray(int size) {
			return new ItemList[size];
		}
	};

	@Override
	public String toString() {
		return "ItemList{"+
				" Images=" + Images +
				'}';
	}
}