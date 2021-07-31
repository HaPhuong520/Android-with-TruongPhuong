package com.example.selectmultipleimagesfromgallery;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder>{
    private Context context;
    private List<Uri> listPhoto;

    public PhotoAdapter(Context context) {
        this.context = context;
    }
    public void setData(List<Uri> list){
        this.listPhoto = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PhotoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_photo,parent,false);
        return new PhotoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoAdapter.PhotoViewHolder holder, int position) {
        Uri uri = listPhoto.get(position);
        try {
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(),uri);
            holder.imagePhoto.setImageBitmap(bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        if(listPhoto == null)
        return 0;
        return listPhoto.size();
    }

    public class PhotoViewHolder extends RecyclerView.ViewHolder{
        private ImageView imagePhoto;

        public PhotoViewHolder(@NonNull View itemView) {
            super(itemView);
            imagePhoto = itemView.findViewById(R.id.img_photo);
        }
    }
}
