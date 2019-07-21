package stringcheese.android.apps.com.hyperledgertest;

        import android.app.FragmentManager;
        import android.app.FragmentTransaction;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements GradeFragment.OnGradeReadListener{

    public static FragmentManager fragmentManager;
    public Button addGrade;
    public Button assign;
    public EditText gradet;
    public EditText namet;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getFragmentManager();

        gradet = (EditText) findViewById(R.id.gradenum);
        namet = (EditText) findViewById(R.id.studentid);

        addGrade = (Button)findViewById(R.id.creategrade);
        addGrade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                APIClient apiClient = new APIClient(Integer.parseInt(gradet.getText().toString()),"123");
                apiClient.runClient();
            }
        });

        assign = (Button)findViewById(R.id.assign);
        assign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addGradeView(Integer.parseInt(gradet.getText().toString()),namet.getText().toString());
            }
        });


    }

    public void addGradeView(int grade, String name){
        if(findViewById(R.id.gradeframe)!=null){
            GradeFragment keyBoardOneFragment = GradeFragment.newInstance(name,grade);
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.gradeframe,keyBoardOneFragment,null);
            fragmentTransaction.commit();

        }
    }

    @Override
    public void OnGradeRead(double b) {

    }
}
