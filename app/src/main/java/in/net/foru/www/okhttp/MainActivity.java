package in.net.foru.www.okhttp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private OkHttpClient okHttpClient;
    private Request request;
    TextView tv;
    static String result;
    private String url="http://34.210.79.58:3001/psr/testokhttp";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv=(TextView)findViewById(R.id.tv);
    }
    public void okrequest(View v)
    {
        //initialize okhttp client
        okHttpClient=new OkHttpClient();

        //initialize a request
        request=new Request.Builder().url(url).build();

        //making request

        //the below code amkes an error as we should not make any network calls on main thread
        /*try {
            Response response = okHttpClient.newCall(request).execute();
            b=response.body().toString();
        } catch (IOException e) {
            Toast.makeText(this,e.toString(),Toast.LENGTH_SHORT).show();
            b=e.toString();
        }*/

        //here we are going to make the make the request in a asynchronous manner
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                result=e.toString();
                tv.setText(result);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                result=response.body().string();
                tv.setText(result);
            }
        });



    }
}
