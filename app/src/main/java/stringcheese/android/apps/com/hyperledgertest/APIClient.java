package stringcheese.android.apps.com.hyperledgertest;

import android.os.Build;
import android.support.annotation.RequiresApi;

import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class APIClient implements Interceptor {

    public static void main(String[] args)
    {
        //APIClient client = new APIClient(59, "2342");
        //client.runClient();
    }


    public OkHttpClient client;

    public String postRequest;
    public APIClient(int score,String gradeid){
        postRequest = "{ "+
                "\"$class\": \"org.school.Grade\"," +
                "\"gradeId\": \""+gradeid+"\"," +
                "\"score\": "+score+"," +
                "\"status\": \"BANK\"," +
                "\"from\": \"resource:org.school.Teacher#201\"," +
                "\"owner\": \"resource:org.school.Teacher#201\"" +
                " }";
         client = new OkHttpClient();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void runClient(){
        try {
            post();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
    *
    * curl -X POST --header 'Content-Type: application/json' --header 'Accept: application/json' -d '{ \
   "$class": "org.school.Grade", \
   "gradeId": "301", \
   "score": 100, \
   "status": "BANK", \
   "from": "resource:org.school.Teacher#201", \
   "owner": "resource:org.school.Teacher#201" \
 }' 'http://localhost:3000/api/Grade%27*/

    public void postGrade(){
        ProcessBuilder pb = new ProcessBuilder("curl", "-X", "POST", "--header", "Content-Type: application/json", "--header","Accept: application/json", "-d",postRequest, "http://192.168.0.32:3000/api/Grade");
        try {
            Process p = pb.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            for (int i = 0; i < 20; i++)
                System.out.println(reader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static final MediaType JSON
            = MediaType.get("application/json; charset=utf-8");

    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        return null;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public String post() throws IOException {
        RequestBody body = RequestBody.create(JSON, postRequest);
        Request request = new Request.Builder()
                .url("http://192.168.0.32:3000/api/Grade")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }
}
