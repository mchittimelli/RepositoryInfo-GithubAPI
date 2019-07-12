package com.example.finalprojectmp1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class OwnerInfo extends AppCompatActivity {
    ImageView owner_img;
    TextView ownerName,followers,following,organization,repos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_info);
        owner_img=findViewById(R.id.image);
        ownerName=findViewById(R.id.login_name);
        followers=findViewById(R.id.followers);
        following=findViewById(R.id.following);
        organization=findViewById(R.id.organization);
        repos=findViewById(R.id.repos);
        Bundle bundle = getIntent().getExtras();
        String imgurl = bundle.getString("owner_img");
        ownerName.setText(bundle.getString("ownerName"));
        followers.setText(bundle.getString("followers"));
        following.setText(bundle.getString("following"));
        organization.setText(bundle.getString("organization"));
        repos.setText(bundle.getString("repos"));
        Picasso.get().load(imgurl).into(owner_img);

    }
}
