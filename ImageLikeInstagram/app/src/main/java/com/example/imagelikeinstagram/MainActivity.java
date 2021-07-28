package com.example.imagelikeinstagram;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rcvPhoto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rcvPhoto=findViewById(R.id.rcv_photo);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        rcvPhoto.setLayoutManager(linearLayoutManager);

        ListPhotoAdapter listPhotoAdapter=new ListPhotoAdapter();
        listPhotoAdapter.setData(getListPhoto());
        rcvPhoto.setAdapter(listPhotoAdapter);
    }

    private List<ListPhoto> getListPhoto() {
        List<ListPhoto> listPhotos=new ArrayList<>();
        List<PhoTo>list=new ArrayList<>();
        list.add(new PhoTo(R.drawable.img_1));
        list.add(new PhoTo(R.drawable.img_2));
        list.add(new PhoTo(R.drawable.img_3));

        List<PhoTo>list2=new ArrayList<>();
        list2.add(new PhoTo(R.drawable.img_3));
        list2.add(new PhoTo(R.drawable.img_2));
        list2.add(new PhoTo(R.drawable.img_1));

        listPhotos.add(new ListPhoto(list,ListPhotoAdapter.TYPE_GRID));
        listPhotos.add(new ListPhoto(list2,ListPhotoAdapter.TYPE_GRID));
        listPhotos.add(new ListPhoto(list,ListPhotoAdapter.TYPE_LARGE));

        listPhotos.add(new ListPhoto(list,ListPhotoAdapter.TYPE_GRID));
        listPhotos.add(new ListPhoto(list2,ListPhotoAdapter.TYPE_GRID));
        listPhotos.add(new ListPhoto(list,ListPhotoAdapter.TYPE_LARGE));

        listPhotos.add(new ListPhoto(list,ListPhotoAdapter.TYPE_GRID));
        listPhotos.add(new ListPhoto(list2,ListPhotoAdapter.TYPE_GRID));
        listPhotos.add(new ListPhoto(list,ListPhotoAdapter.TYPE_LARGE));

        listPhotos.add(new ListPhoto(list,ListPhotoAdapter.TYPE_GRID));
        listPhotos.add(new ListPhoto(list2,ListPhotoAdapter.TYPE_GRID));
        listPhotos.add(new ListPhoto(list,ListPhotoAdapter.TYPE_LARGE));

        return  listPhotos;

    }
}