package com.example.finalprojectmp1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.concurrent.ExecutionException;

public class OwnerInfo extends AppCompatActivity {
    ImageView owner_img;
    TextView ownerName,followers,following,organization,repos;
    String followersUrl,followingUrl,organizationsUrl,reposUrl;

public String nameList(String jsonURl,String property){
    String result="";
    try {
        String  myjson = new syncdata().execute(jsonURl).get();
        JSONArray myJsonArray= new JSONArray(myjson);
        for(int x=0;x<myJsonArray.length();x++){
            JSONObject childObj = myJsonArray.getJSONObject(x);
            result=result+childObj.getString(property)+"\n";
        }
    } catch (ExecutionException e) {
        e.printStackTrace();
    } catch (InterruptedException e) {
        e.printStackTrace();
    } catch (JSONException e) {
        e.printStackTrace();
    }
    return result;
}
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
        final OwnerInfo ownerInfo=new OwnerInfo();
        Bundle bundle = getIntent().getExtras();
        String imgurl = bundle.getString("owner_img");

        ownerName.setText(bundle.getString("ownerName"));

        followersUrl=bundle.getString("followers");
        followingUrl=bundle.getString("following");
        organizationsUrl=bundle.getString("organization");

        repos.setText(bundle.getString("repos"));
        reposUrl=bundle.getString("repos");

        Picasso.get().load(imgurl).into(owner_img);


        //for followers
       followers.setText(ownerInfo.nameList(followersUrl,"login"));
       //for following
        following.setText("Data not found");
         //for organizations
        organization.setText(ownerInfo.nameList(organizationsUrl,"login"));

       // String result="";
        try {
            String  myjson = new syncdata().execute(reposUrl).get();
            JSONArray repositories= new JSONArray(myjson);


            repos.setText(String.valueOf(repositories.length()));

            repos.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent myIntent=new Intent(OwnerInfo.this,HomeScreen.class);
                    myIntent.putExtra("url",reposUrl);
                    startActivity(myIntent);
                }
            });
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        } catch (ExecutionException e1) {
            e1.printStackTrace();
        } catch (JSONException e1) {
            e1.printStackTrace();
        }


    }


    }
