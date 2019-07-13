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
    TextView ownerName,followers,following,organization,repos,followersCount,organizationsCount,followingCount;
    String followersUrl,followingUrl,organizationsUrl,reposUrl;

public String[] nameList(String jsonURl,String property){
    String names="";

    String[] res=new String[2];
    try {
        String  myjson = new syncdata().execute(jsonURl).get();
        JSONArray myJsonArray= new JSONArray(myjson);

        for(int x=0;x<myJsonArray.length();x++){
            JSONObject childObj = myJsonArray.getJSONObject(x);
            names=names+childObj.getString(property)+"\n";
        }
       res[0]=names;
        res[1]=String.valueOf(myJsonArray.length());
    } catch (ExecutionException e) {
        e.printStackTrace();
    } catch (InterruptedException e) {
        e.printStackTrace();
    } catch (JSONException e) {
        e.printStackTrace();
    }
    return res;
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
       followersCount=findViewById(R.id.followers_count);
       organizationsCount=findViewById(R.id.Organizations_count);
       followingCount=findViewById(R.id.following_count);

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
        String followersDetails[]=ownerInfo.nameList(followersUrl,"login");
       followers.setText(followersDetails[0]);
      followersCount.setText(followersDetails[1]);
       //for following
        String followingDetails[]=ownerInfo.nameList(followingUrl,"login");
        following.setText(followingDetails[0]);
        followingCount.setText(followingDetails[1]);
         //for organizations
        String organizationDetails[]=ownerInfo.nameList(organizationsUrl,"login");
        organization.setText(organizationDetails[0]);
       organizationsCount.setText(organizationDetails[1]);

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
