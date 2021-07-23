package com.example.covidtracker.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.covidtracker.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class Symptoms extends Fragment {


    private TextView topMsg,nose,diarrhea,dryCough,achPain,fever;
//    private Button hindiBtn,englishBtn;

    private View view;

    private ImageButton btn1;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_symptoms, container, false);



        viewInit();

//        hindiTranslation();
//        enflishTranslation();

        listeners();

     //   loadVideos();


        buttonControls();

        return view;

    }

    private void buttonControls() {

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }

 /*   @SuppressLint("SetJavaScriptEnabled")
    private void loadVideos() {



         v = (VideoView) view.findViewById(R.id.YoutubeVideoView);

        v.setVideoURI(Uri.parse("https://youtu.be/oBSkHZPu2xU"));

        v.setMediaController(new MediaController(getActivity())); //sets MediaController in the video view



        v.requestFocus();//give focus to a specific view

        v.start();

    }
*/
    private void listeners() {


//        englishBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                enflishTranslation();
////                Toast.makeText(getActivity(),"Translation Successful",Toast.LENGTH_LONG).show();
//            }
//        });

//        hindiBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                hindiTranslation();
//                Toast.makeText(getActivity(),"अनुवाद हो चुका है",Toast.LENGTH_LONG).show();
//            }
//        });
    }

//    @SuppressLint("SetTextI18n")
//    private void enflishTranslation() {
//
//
//        topMsg.setText("The most common symptoms of COVID-19 \"");
//        dryCough.setText("Dry cough");
//        diarrhea.setText("Sore throat or diarrhea");
//        achPain.setText("Aches and pains");
//
//        fever.setText("fever,tiredness");
//        nose.setText("Runny nose");
//
//    }

//    @SuppressLint("SetTextI18n")
//    private void hindiTranslation() {
//
//
//        topMsg.setText("COVID-19 के सबसे सामान्य लक्षण");
//        dryCough.setText("सूखी खांसी");
//        diarrhea.setText("गले में खराश या दस्त");
//        achPain.setText("दर्द एवं पीड़ा");
//
//        fever.setText("बुखार,थकान");
//        nose.setText("बहती नाक");
//    }

    private void viewInit() {

         topMsg=view.findViewById(R.id.topMsg);
         nose=view.findViewById(R.id.nose);
         diarrhea=view.findViewById(R.id.diarrhea);
         dryCough=view.findViewById(R.id.dryCough);
         achPain=view.findViewById(R.id.achesPain);

         fever=view.findViewById(R.id.fever);
//         hindiBtn=view.findViewById(R.id.hindi);
//         englishBtn=view.findViewById(R.id.english);




        btn1=view.findViewById(R.id.btn1);
/*

        ImageButton btn2 = view.findViewById(R.id.btn2);

        ImageButton btn3 = view.findViewById(R.id.btn3);

        ImageButton btn4 = view.findViewById(R.id.btn4);

        ImageButton btn5 = view.findViewById(R.id.btn5);

*/



    }





}
