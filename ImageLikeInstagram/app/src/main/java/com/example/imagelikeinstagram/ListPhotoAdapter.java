package com.example.imagelikeinstagram;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListPhotoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    public static final int TYPE_GRID=1;
    public static final int TYPE_LARGE=2;
    private List<ListPhoto> mlistPhoto;
    public void sendData(List<ListPhoto> listPhotos)
    {
        this.mlistPhoto=listPhotos;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        ListPhoto listPhoto=mlistPhoto.get(position);
        return listPhoto.getType();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(TYPE_GRID==viewType)
        {
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_photo_grid,parent,false);
            return new PhotoGridViewHolder(view);
        }
        else if(TYPE_LARGE==viewType)
        {
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_photo_large,parent,false);
            return new PhotoLargeViewHolder();
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
    ListPhoto listPhoto=mlistPhoto.get(position);
    if(TYPE_GRID==listPhoto.getType())
    {
        PhotoGridViewHolder photoGridViewHolder= (PhotoGridViewHolder) holder;
        photoGridViewHolder.img1.setImageResource(listPhoto.getPhotos().get(0).getResoureId());
        photoGridViewHolder.img2.setImageResource(listPhoto.getPhotos().get(1).getResoureId());
        photoGridViewHolder.img3.setImageResource(listPhoto.getPhotos().get(2).getResoureId());

    }
    else if(TYPE_LARGE==listPhoto.getType())
    {
        PhotoLargeViewHolder photoLargeViewHolder= (PhotoLargeViewHolder) holder;
        photoLargeViewHolder.img1.setImageResource(listPhoto.getPhotos().get(0).getResoureId());
        photoLargeViewHolder.img2.setImageResource(listPhoto.getPhotos().get(1).getResoureId());
        photoLargeViewHolder.img3.setImageResource(listPhoto.getPhotos().get(2).getResoureId());
    }
    }

    @Override
    public int getItemCount() {
        if(mlistPhoto!=null)
        {
            return mlistPhoto.size();
        }
        return 0;
    }

    public class PhotoGridViewHolder extends RecyclerView.ViewHolder{
        private ImageView img1,img2,img3;
        public PhotoGridViewHolder(@NonNull View itemView) {
            super(itemView);
            img1=itemView.findViewById(R.id.img_1);
            img2=itemView.findViewById(R.id.img_2);
            img3=itemView.findViewById(R.id.img_3);
        }
    }
    public class PhotoLargeViewHolder extends RecyclerView.ViewHolder{
        private ImageView img1,img2,img3;
        public PhotoLargeViewHolder(@NonNull View itemView) {
            super(itemView);
            img1=itemView.findViewById(R.id.img_1);
            img2=itemView.findViewById(R.id.img_2);
            img3=itemView.findViewById(R.id.img_3);
        }
    }
}
