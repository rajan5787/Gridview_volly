package com.example.rajan.gridview_volly;

import org.json.JSONObject;
        import android.app.ProgressDialog;
        import android.os.Bundle;
        import android.support.v7.app.AppCompatActivity;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
        import android.widget.Toast;

        import com.android.volley.RequestQueue;
        import com.android.volley.Response;
        import com.android.volley.VolleyError;
        import com.android.volley.toolbox.StringRequest;
        import com.android.volley.toolbox.Volley;

        import org.json.JSONArray;
        import org.json.JSONException;
        import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextId;
    private Button buttonGet;
    private TextView textViewResult;
    private ListView listView;
    private ProgressDialog loading;
    private ArrayList<item> arrayList;
    CustomAdapter mCustomAdapter;
    public static final String JSON_URL = "http://192.168.137.1/gridview_volly/getData.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextId = (EditText) findViewById(R.id.editTextId);
        buttonGet = (Button) findViewById(R.id.buttonGet);
        listView=(ListView)findViewById(R.id.list);
        arrayList=new ArrayList<>();
        getData();

        mCustomAdapter=new CustomAdapter(this,arrayList);
        listView.setAdapter(mCustomAdapter);
    }

    private void getData() {

        loading = ProgressDialog.show(this,"Please wait...","Fetching...",false,false);

        StringRequest stringRequest = new StringRequest(JSON_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                loading.dismiss();
                showJSON(response);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        loading.dismiss();
                       // Toast.makeText(MainActivity.this,error.getMessage().toString(),Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void showJSON(String response){

        item item=new item();
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray(Config.JSON_ARRAY);

            for(int i=0;i<result.length();i++) {
                JSONObject collegeData = result.getJSONObject(i);
                item.setName(collegeData.getString(Config.KEY_NAME));
                item.setAddress(collegeData.getString(Config.KEY_ADDRESS));
                item.setVc(collegeData.getString(Config.KEY_VC));
                item.setImage(collegeData.getString(Config.KEY_IMAGE));
                arrayList.add(item);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //textViewResult.setText("Name:\t" + name + "\nAddress:\t" + address + "\nVice Chancellor:\t" + vc);
    }

    @Override
    public void onClick(View v) {
        getData();
    }
}