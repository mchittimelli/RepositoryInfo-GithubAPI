package com.example.finalprojectmp1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class HomeScreen extends AppCompatActivity {

    ArrayList<Repository> repositoryArrayList;
    ListView listView;
    ListAdapt listAdapt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        repositoryArrayList=new ArrayList<>();
        listView=findViewById(R.id.list_repo);

        String link = getResources().getString(R.string.link);
        try {
            String myjson = new syncdata().execute(link).get();
            System.out.println("MainActivity :"+myjson);
            JSONArray repositories= new JSONArray(myjson);

           /* JSONObject mainObj = new JSONObject(myjson);

            JSONArray repositories = mainObj.getJSONArray("Pokemon");*/


            for (int i=0;i<30;i++){

                JSONObject childObj = repositories.getJSONObject(i);

                String name = childObj.getString("name");
                String owner=childObj.getJSONObject("owner").getString("login");
                String ownerAvatar=childObj.getJSONObject("owner").getString("avatar_url");
                String languages_url = childObj.getString("languages_url");
                String description = childObj.getString("description");
                repositoryArrayList.add(new Repository(name,owner,ownerAvatar,languages_url,description));


            }
            listAdapt = new ListAdapt(getApplicationContext(),repositoryArrayList);

            listView.setAdapter(listAdapt);
           // System.out.println("idi naa array"+repositoryArrayList.get(1).toString());

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    Intent i = new Intent(HomeScreen.this,RepositoryDescription.class);

            i.putExtra("data",repositoryArrayList.get(position));
            startActivity(i);
                }
            });
        } catch (ExecutionException e) {
            System.out.println("Execution Exception Home screen");
        }  catch (InterruptedException e) {
            System.out.println("InterruptedException Home screen");
        } catch (JSONException e) {
            System.out.println("JSONException Home screen");
        }

    }
}
