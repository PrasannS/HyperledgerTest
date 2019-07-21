package stringcheese.android.apps.com.hyperledgertest;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link GradeFragment.OnGradeReadListener} interface
 * to handle interaction events.
 * Use the {@link GradeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GradeFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String STRING_REP = "param1";
    private static final String SOLUTION = "param2";
    OnGradeReadListener calcDisplayReadListener;
    // TODO: Rename and change types of parameters
    private String StringRep = "";
    private double Solution = 0;
    public TextView gradetext;
    public TextView nametext;

    public GradeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GradeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GradeFragment newInstance(String param1, double param2) {
        GradeFragment fragment = new GradeFragment();
        Bundle args = new Bundle();
        args.putString(STRING_REP,param1);
        args.putDouble(SOLUTION, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            StringRep = getArguments().getString(STRING_REP);
            Solution = getArguments().getDouble(SOLUTION);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_grade, container, false);
        gradetext = (TextView)view.findViewById(R.id.grade);
        nametext = (TextView)view.findViewById(R.id.name);
        gradetext.setText(StringRep);
        nametext.setText(Solution+"");
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity = (Activity)context;
        try{
            calcDisplayReadListener = (GradeFragment.OnGradeReadListener)activity;
        }catch(ClassCastException e){
            throw new ClassCastException(activity.toString()+"must override onKeyboardOneRead");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnGradeReadListener {
        // TODO: Update argument type and name
        void OnGradeRead(double b);
    }
}

