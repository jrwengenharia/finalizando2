package com.aula.atividades.datatotable;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TimePicker;

import java.util.Calendar;

public class Add_Event extends Activity {

    private Calendar cal;
    private int day;
    private int month;
    private int year, hour,minute;
    private EditText data_evento, hora_inicio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__event);

        SQLController sqlcon;
        final Button BTnovoTipo = (Button) findViewById(R.id.BT_add_add_Tipo);
        data_evento = (EditText)findViewById(R.id.ET_add_Date);
        hora_inicio = (EditText)findViewById(R.id.ET_add_Time_Ini);
        final EditText hora_fim = (EditText)findViewById(R.id.ET_add_Time_Fim);

        sqlcon = new SQLController(this);


        cal = Calendar.getInstance();
        day = cal.get(Calendar.DAY_OF_MONTH);
        month = cal.get(Calendar.MONTH);
        year = cal.get(Calendar.YEAR);
        hour = cal.get(Calendar.HOUR);
        minute = cal.get(Calendar.MINUTE);



        data_evento.setText(day+"/"+month+"/"+"/"+year);

        //----------------------Abrir DataPicker quando clickar------------------------------
        data_evento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DateDialog();
            }
        });

        //------------------Abrir o TimePicker Quando Clickar na hora Inicio

        hora_inicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            TimeDialog();
            }
        });

        //------------------Abrir o TimePicker Quando Clickar na Hora fim

        hora_fim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimeDialog();
            }
        });

        //-----------------------Criar novo tipo de evento ---------------------------------------//


        BTnovoTipo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //------------------Novo intent para cadastrar Tipo
                Intent novotipo = new Intent(Add_Event.this,add_Tipo.class);
                startActivity(novotipo);

            }
        });
    }


    public void DateDialog(){

        DatePickerDialog.OnDateSetListener listener=new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,int dayOfMonth)
            {

                data_evento.setText(dayOfMonth + "/" + monthOfYear + "/" + year);

            }};

        DatePickerDialog dpDialog=new DatePickerDialog(this, listener, year, month, day);
        dpDialog.show();

    }

    public void TimeDialog(){
        TimePickerDialog.OnTimeSetListener listener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                hora_inicio.setText(hourOfDay+":"+minute);

            }
        };
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,listener,hour,minute,true);
        timePickerDialog.show();

    }

}
