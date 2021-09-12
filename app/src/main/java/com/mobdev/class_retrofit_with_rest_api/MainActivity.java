package com.mobdev.class_retrofit_with_rest_api;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.mobdev.class_retrofit_with_rest_api.Model.Comment;
import com.mobdev.class_retrofit_with_rest_api.Model.Post;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    //List<Post> listmodel = new ArrayList<Post>();
    JsonPlaceHolderAPI jsonPlaceHolderAPI;
    RecyclerView recyclerView;
    //ItemAdapter itemAdapter;

    //private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //itemAdapter = new ItemAdapter(MainActivity.this,listmodel);
        //recyclerView.setAdapter(itemAdapter);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonPlaceHolderAPI = retrofit.create(JsonPlaceHolderAPI.class);

        getPost();
        //getComment();

    }

    private void getPost() {
        Call<List<Post>> call = jsonPlaceHolderAPI.getPosts();

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if(! response.isSuccessful()){

                   // recyclerView.setText("Code: " +response.code());
                    return;

                }List<Post> posts = response.body();
                ItemAdapter itemAdapter = new ItemAdapter(MainActivity.this,posts);
                recyclerView.setAdapter(itemAdapter);
//                for (Post post : posts){
//                    String content = "";
//                    content += "ID: " +post.getId() +"\n";
//                    content += "User ID : " +post.getUserId() +"\n";
//                    content += "Title : " +post.getTitle() +"\n";
//                    content += "Text : " +post.getText() +"\n\n\n";
//
//                    recyclerView.append(content);
//                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

//    private void getComment() {
//        Call<List<Comment>> call = jsonPlaceHolderAPI.getComments(5);
//
//        call.enqueue(new Callback<List<Comment>>() {
//            @Override
//            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
//                if (!response.isSuccessful()) {
//                    textView.setText("Code: " + response.code());
//                    return;
//                }
//                List<Comment> comments = response.body();
//                for (Comment comment : comments) {
//                    String content = "";
//                    content += "ID: " + comment.getId() + "\n";
//                    content += "Post ID : " + comment.getPostId() + "\n";
//                    content += "Name : " + comment.getName() + "\n";
//                    content += "Text : " + comment.getText() + "\n";
//                    content += "Email : " + comment.getEmail() + "\n\n\n";
//
//                    textView.append(content);
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<Comment>> call, Throwable t) {
//                textView.setText(t.getMessage());
//            }
//        });
//    }

}