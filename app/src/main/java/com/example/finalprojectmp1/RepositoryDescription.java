package com.example.finalprojectmp1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class RepositoryDescription extends AppCompatActivity {
    ImageView owner_img;
    TextView name,owner_name,languages,description;
    String lan="",lang="";
    String owner,ownerAvatar,followers,following,organizations,repos;
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

        description.setText(r.getDescription());

        try {
            String myjson = new syncdata().execute(r.getLanguages_url()).get();
            JSONObject language=new JSONObject(myjson);
            String[] l=language.toString().split(",",0);
            for(int x=0;x<l.length;x++){
                lan=lan+l[x]+" ";

            }
            String[] la=lan.split("\"",0);
            for(int x=1;x<la.length;x=x+2){
                lang=lang+la[x]+",";

            }
            languages.setText(lang.substring(0,lang.length()-1)+".");
        } catch (ExecutionException | JSONException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        owner=r.getOwner_name();
        ownerAvatar=r.getOwner_avatar_url();
        followers=r.getFollowers_url();
        following=r.getFollowing_url();
        organizations=r.getOrganization_url();
        repos=r.getRepos_url();

        owner_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ownerIntent=new Intent(RepositoryDescription.this,OwnerInfo.class);
                ownerIntent.putExtra("ownerName",owner);
                ownerIntent.putExtra("owner_img",ownerAvatar);
                ownerIntent.putExtra("followers",followers);
                ownerIntent.putExtra("following",following);
                ownerIntent.putExtra("organization",organizations);
                ownerIntent.putExtra("repos",repos);
                startActivity(ownerIntent);
            }
        });
    }
}
