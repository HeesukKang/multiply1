package com.example.talingpractice1.multiply;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

//주석을 추가합니다
public class MainActivity extends AppCompatActivity {

    CheckBox[] oneonecheck = new CheckBox[10];
    EditText[] oneonesubject = new EditText[10];
    Spinner[] oneonegrade = new Spinner[10];
    Spinner oneonecredit1, oneonecredit2, oneonecredit3, oneonecredit4, oneonecredit5, oneonecredit6, oneonecredit7, oneonecredit8, oneonecredit9, oneonecredit10;
    TextView credit_sum_view, upper_score_view, korea_score_view, america_score_view;
    //전공평점, 총평점, 3,4학년 평점 총 텍스트뷰 4개
    Button cal_button; //버튼을 누르면 계산이 실행된다

    int credit_sum; //이번 학기 이수 학점
    double real_credit_sum; // 패논패는 제외한 학점
    double korea_score, america_score; // 4.5만점 학점의 분자, 4.0만점 학점의 분자


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        oneonecredit1 = (Spinner)findViewById(R.id.oneonecredit1);
        oneonecredit2 = (Spinner)findViewById(R.id.oneonecredit2);
        oneonecredit3 = (Spinner)findViewById(R.id.oneonecredit3);
        oneonecredit4 = (Spinner)findViewById(R.id.oneonecredit4);
        oneonecredit5 = (Spinner)findViewById(R.id.oneonecredit5);
        oneonecredit6 = (Spinner)findViewById(R.id.oneonecredit6);
        oneonecredit7 = (Spinner)findViewById(R.id.oneonecredit7);
        oneonecredit8 = (Spinner)findViewById(R.id.oneonecredit8);
        oneonecredit9 = (Spinner)findViewById(R.id.oneonecredit9);
        oneonecredit10 = (Spinner)findViewById(R.id.oneonecredit10);
        //oneonecredit은 1학년 1학기 과목별 학점임, 3학점, 2학점, 1학점 중 하나

        oneonegrade[0] = (Spinner)findViewById(R.id.oneonegrade1);
        oneonegrade[1] = (Spinner)findViewById(R.id.oneonegrade2);
        oneonegrade[2] = (Spinner)findViewById(R.id.oneonegrade3);
        oneonegrade[3] = (Spinner)findViewById(R.id.oneonegrade4);
        oneonegrade[4] = (Spinner)findViewById(R.id.oneonegrade5);
        oneonegrade[5] = (Spinner)findViewById(R.id.oneonegrade6);
        oneonegrade[6] = (Spinner)findViewById(R.id.oneonegrade7);
        oneonegrade[7] = (Spinner)findViewById(R.id.oneonegrade8);
        oneonegrade[8] = (Spinner)findViewById(R.id.oneonegrade9);
        oneonegrade[9] = (Spinner)findViewById(R.id.oneonegrade10);
        //A+,A,B+ 등 평점

        //여기까지 spinner 선언

        Adapter();
        //Adapter는 밑에 함수 만들어둔걸로 선언


        oneonecheck[0] = (CheckBox)findViewById(R.id.oneonecheck1);
        oneonecheck[1] = (CheckBox)findViewById(R.id.oneonecheck2);
        oneonecheck[2] = (CheckBox)findViewById(R.id.oneonecheck3);
        oneonecheck[3] = (CheckBox)findViewById(R.id.oneonecheck4);
        oneonecheck[4] = (CheckBox)findViewById(R.id.oneonecheck5);
        oneonecheck[5] = (CheckBox)findViewById(R.id.oneonecheck6);
        oneonecheck[6] = (CheckBox)findViewById(R.id.oneonecheck7);
        oneonecheck[7] = (CheckBox)findViewById(R.id.oneonecheck8);
        oneonecheck[8] = (CheckBox)findViewById(R.id.oneonecheck9);
        oneonecheck[9] = (CheckBox)findViewById(R.id.oneonecheck10);
        //전공인지 여부를 체크

        oneonesubject[0] = findViewById(R.id.oneonesubject1);
        oneonesubject[1] = findViewById(R.id.oneonesubject2);
        oneonesubject[2] = findViewById(R.id.oneonesubject3);
        oneonesubject[3] = findViewById(R.id.oneonesubject4);
        oneonesubject[4] = findViewById(R.id.oneonesubject5);
        oneonesubject[5] = findViewById(R.id.oneonesubject6);
        oneonesubject[6] = findViewById(R.id.oneonesubject7);
        oneonesubject[7] = findViewById(R.id.oneonesubject8);
        oneonesubject[8] = findViewById(R.id.oneonesubject9);
        oneonesubject[9] = findViewById(R.id.oneonesubject10);

    // 여기까지 하면 findviewbyid 끝




        cal_button = findViewById(R.id.cal_button);
        credit_sum_view = findViewById(R.id.credit_sum_view);

        // 원하는 목표는 버튼 하나 누르면 이수학점, 총평점, 전공평점, 3,4학년 평점 텍스트뷰에 각각 계산해서 업데이트

        cal_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int[] oneonecredit = new int[10];
                oneonecredit[0] = Integer.parseInt(oneonecredit1.getSelectedItem().toString());
                oneonecredit[1] = Integer.parseInt(oneonecredit2.getSelectedItem().toString());
                oneonecredit[2] = Integer.parseInt(oneonecredit3.getSelectedItem().toString());
                oneonecredit[3] = Integer.parseInt(oneonecredit4.getSelectedItem().toString());
                oneonecredit[4] = Integer.parseInt(oneonecredit5.getSelectedItem().toString());
                oneonecredit[5] = Integer.parseInt(oneonecredit6.getSelectedItem().toString());
                oneonecredit[6] = Integer.parseInt(oneonecredit7.getSelectedItem().toString());
                oneonecredit[7] = Integer.parseInt(oneonecredit8.getSelectedItem().toString());
                oneonecredit[8] = Integer.parseInt(oneonecredit9.getSelectedItem().toString());
                oneonecredit[9] = Integer.parseInt(oneonecredit10.getSelectedItem().toString());

                //oneonecredit의 내용을 정수로 변환해서 저장

                double[] oneonescoreKor = new double[10];
                double[] oneonescoreAmer = new double[10];
                // 나중의 이들과 oneonecredit의 곱의 합들이 korea_score와 america_score로 저장되어 학점계산의 분자로 들어간다.

                korea_score = 0;
                america_score = 0;
                //클릭할때마다 다시 계산해야하므로 초기화

                for (int i = 0; i < 10; i++) {
                    if(oneonesubject[i].getText().toString().getBytes().length <= 0){

                    }else {
                        if (oneonegrade[i].getSelectedItem().toString() == "A+") {
                            oneonescoreKor[i] = 4.5;
                            oneonescoreAmer[i] = 4;
                        }
                        if (oneonegrade[i].getSelectedItem().toString() == "A") {
                            oneonescoreKor[i] = 4;
                            oneonescoreAmer[i] = 4;
                        }
                        if (oneonegrade[i].getSelectedItem().toString() == "B+") {
                            oneonescoreKor[i] = 3.5;
                            oneonescoreAmer[i] = 3;
                        }
                        if (oneonegrade[i].getSelectedItem().toString() == "B") {
                            oneonescoreKor[i] = 3;
                            oneonescoreAmer[i] = 3;
                        }
                        if (oneonegrade[i].getSelectedItem().toString() == "C+") {
                            oneonescoreKor[i] = 2.5;
                            oneonescoreAmer[i] = 2;
                        }
                        if (oneonegrade[i].getSelectedItem().toString() == "C") {
                            oneonescoreKor[i] = 2;
                            oneonescoreAmer[i] = 2;
                        }
                        if (oneonegrade[i].getSelectedItem().toString() == "D+") {
                            oneonescoreKor[i] = 1.5;
                            oneonescoreAmer[i] = 1;
                        }
                        if (oneonegrade[i].getSelectedItem().toString() == "D") {
                            oneonescoreKor[i] = 1;
                            oneonescoreAmer[i] = 1;
                        }
                        if (oneonegrade[i].getSelectedItem().toString() == "F") {
                            oneonescoreKor[i] = 0;
                            oneonescoreAmer[i] = 0;
                        }
                        if (oneonegrade[i].getSelectedItem().toString() == "P") {
                            oneonescoreKor[i] = 0;
                            oneonescoreAmer[i] = 0;
                        }
                        if (oneonegrade[i].getSelectedItem().toString() == "NP") {
                            oneonescoreKor[i] = 0;
                            oneonescoreAmer[i] = 0;
                        }
                        //각 평점별로 학점 계산을 했음
                        korea_score = korea_score + oneonecredit[i] * oneonescoreKor[i];
                        //4.5만점 학점 계산의 분자부분
                        america_score = america_score + oneonecredit[i] * oneonescoreKor[i];
                        //4.0만점 학점 계산의 분자부분
                    }

                }

                credit_sum = 0;
                //클릭할때마다 다시 계산해야하므로 초기화
                for(int i=0; i<10; i++){
                    if(oneonesubject[i].getText().toString().getBytes().length <= 0){

                    }else{
                        credit_sum += oneonecredit[i];
                    }
                    //과목명이 비어있지 않으면 학점으로 인정
                }

                real_credit_sum = 0;
                //클릭할때마다 다시 계산해야하므로 초기화
                //real_credit_sum은 학점 계산 시 분모에 해당한다.
                for(int i=0; i<10; i++){
                    if(oneonesubject[i].getText().toString().getBytes().length <= 0){

                    } else if (oneonegrade[i].getSelectedItem().toString() == "P") {

                    } else if (oneonegrade[i].getSelectedItem().toString() == "NP") {

                    } else {
                        real_credit_sum += oneonecredit[i];
                    }
                    //과목명이 비어있지 않고 패논패가 아니라면 유효학점으로 인정
                }
                credit_sum_view.setText(Integer.toString(credit_sum));
                //이번학기 이수학점을 나타냄
                korea_score_view.setText(Double.toString(korea_score / real_credit_sum));
                //4.5만점 학점의 분자부분이 korea_score 여기에 유효수강학점인 real_credit_sum을 나눔
                america_score_view.setText(Double.toString(america_score / real_credit_sum));
                //4.0만점 학점의 분자부분이 america_score 여기에 유효수강학점인 real_credit_sum을 나눔
            }
        });





    }

    void Adapter(){
        ArrayAdapter Adapter1 = ArrayAdapter.createFromResource(this, R.array.credit, android.R.layout.simple_spinner_item);
        oneonecredit1.setAdapter(Adapter1);
        oneonecredit2.setAdapter(Adapter1);
        oneonecredit3.setAdapter(Adapter1);
        oneonecredit4.setAdapter(Adapter1);
        oneonecredit5.setAdapter(Adapter1);
        oneonecredit6.setAdapter(Adapter1);
        oneonecredit7.setAdapter(Adapter1);
        oneonecredit8.setAdapter(Adapter1);
        oneonecredit9.setAdapter(Adapter1);
        oneonecredit10.setAdapter(Adapter1);

        ArrayAdapter Adapter2 = ArrayAdapter.createFromResource(this, R.array.grade, android.R.layout.simple_spinner_item);
        oneonegrade[0].setAdapter(Adapter2);
        oneonegrade[1].setAdapter(Adapter2);
        oneonegrade[2].setAdapter(Adapter2);
        oneonegrade[3].setAdapter(Adapter2);
        oneonegrade[4].setAdapter(Adapter2);
        oneonegrade[5].setAdapter(Adapter2);
        oneonegrade[6].setAdapter(Adapter2);
        oneonegrade[7].setAdapter(Adapter2);
        oneonegrade[8].setAdapter(Adapter2);
        oneonegrade[9].setAdapter(Adapter2);
//spinner에 대한 어댑터
    }





}
