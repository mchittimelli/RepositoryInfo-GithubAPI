package com.example.finalprojectmp1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class RepositoryDescription extends AppCompatActivity {
ImageView owner_img;
TextView name,owner_name,languages,description;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repository_description);
        owner_img=findViewById(R.id.owner_image);
        name=findViewById(R.id.name);
        owner_name=findViewById(R.id.owner_name);
        languages=findViewById(R.id.languages);
        description=findViewById(R.id.description);


        Intent i = getIntent();
        Repository r= i.getParcelableExtra("data");
        Picasso.get().load(r.getOwner_avatar_url()).into(owner_img);

        owner_name.setText(r.getOwner_name());
        name.setText(r.getName());
        languages.setText(r.getLanguages_url());
        description.setText(r.getDescription());

    }
}
