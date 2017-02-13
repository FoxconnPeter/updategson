package com.liqipeter.myapplication;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

//https://api.douban.com/v2/book/1220562

public class MainActivity extends AppCompatActivity {
    private BookListAdapter adapter;
    private List<GSON.ResultBean.DataBean > dataBeens;


    @Bind(R.id.listview)
    ListView lv;
    private String url = "http://v.juhe.cn/toutiao/index?type=top&key=34380c2654aa698a2572a2e71f456c3e";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        getData();

    }

    private void getData() {
        StringRequest request = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String arg0) {
                Log.i("info",arg0);
                try {
                    dealData(arg0);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });
        Volley.newRequestQueue(getApplicationContext()).add(request);
    }

    private void dealData( String arg0) throws JSONException{
        Gson gson = new Gson();
        GSON gson1 = gson.fromJson(arg0,GSON.class);
        ArrayList<GSON.ResultBean.DataBean> dataBeen = (ArrayList<GSON.ResultBean.DataBean>) gson1.getResult().getData();

//        List<GSON> gsonList = gson.fromJson(arg0,new TypeToken<List<GSON>>(){}.getType());



        System.out.println("解析的是+++++++"+gson1);






        adapter = new BookListAdapter(this,dataBeen);
        lv.setAdapter(adapter);








//        Type listType=new TypeToken<ArrayList<GSON>>(){
//
//        }.getType();
//
//
//          // JSONObject object = new JSONObject(arg0);
//
//        System.out.println("解析的是+++++++++++++"+arg0);
//
//       ArrayList<GSON> books  = gson.fromJson(arg0,listType);
//
//        adapter = new BookListAdapter(this,books);
//        lv.setAdapter(adapter);




//        JSONObject jsonObject = new JSONObject(arg0);
//        JSONArray jsonArray = jsonObject.getJSONArray("result");
//        List<GSON.ResultBean> resultBeen = new ArrayList<>();
//
//        for (int i =0;i<jsonArray.length();i++){
//            JSONObject item = jsonArray.getJSONObject(i);
//            JSONArray array = item.getJSONArray("data");
//            List<GSON.ResultBean.DataBean > dataBeens = new ArrayList<>();
//            for (int j =0;j<array.length();j++){
//                JSONObject aa=array.getJSONObject(j);
//                GSON.ResultBean.DataBean dataBean = new GSON.ResultBean.DataBean();
//                dataBean.setTitle(aa.getString("title"));
//                dataBean.setThumbnail_pic_s(aa.getString("thumbnail_pic_s"));
//                dataBeens.add(dataBean);
//                System.out.println("解析的是+++++++++++++"+dataBeens);
//
//
//            }
//
//
//        }
//        Message msg = new Message();
//        msg.what = 0;
//
//        handler.sendMessage(msg);





    }

//
//    public Handler handler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//
//            try {
//                switch (msg.what) {
//
//                    case 0:
//                        BookListAdapter lvAdapter = new BookListAdapter(MainActivity.this, dataBeens);
//                        listView.setAdapter(lvAdapter);
//                        Toast.makeText(SuccessActivity.this, "提交成功", Toast.LENGTH_SHORT).show();
//                        break;
//
//                    default:
//                        break;
//                }
//            } catch (Exception e) {
//
//                e.printStackTrace();
//            }
//        }
//    };


}
