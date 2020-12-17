package com.example.calculadoradivises;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Buttons
        Button btn0 = findViewById(R.id.button0);
        Button btn1 = findViewById(R.id.button1);
        Button btn2 = findViewById(R.id.button2);
        Button btn3 = findViewById(R.id.button3);
        Button btn4 = findViewById(R.id.button4);
        Button btn5 = findViewById(R.id.button5);
        Button btn6 = findViewById(R.id.button6);
        Button btn7 = findViewById(R.id.button7);
        Button btn8 = findViewById(R.id.button8);
        Button btn9 = findViewById(R.id.button9);

        Button btnCE = findViewById(R.id.ce);
        Button btnDelete = findViewById(R.id.borrar);
        final Button btnComa = findViewById(R.id.buttonComa);

        Button btnEqual = findViewById(R.id.buttonEqual);

        final Button btnLibra = findViewById(R.id.buttonLibra);
        final Button btnDolar = findViewById(R.id.buttonDolar);

        final String buttonColorSelect = "#699ff5";

        final Button btnYen = findViewById(R.id.buttonYen);
        final Button btnPes = findViewById(R.id.buttonPes);

        //Text
        final TextView numero = findViewById(R.id.entrada);
        final TextView numeroSortida = findViewById(R.id.sortida);
        final TextView monedaText = findViewById(R.id.monedaText);

        //trigger que escolta quan s'apreta el boto
        btnLibra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Global.getInstance().valorConversor = 0.91;
                monedaText.setText("£");
                btnLibra.setBackgroundColor(Color.parseColor(buttonColorSelect));
                btnDolar.setBackgroundColor(Color.parseColor("#424242"));
                btnYen.setBackgroundColor(Color.parseColor("#424242"));
                btnPes.setBackgroundColor(Color.parseColor("#424242"));
            }
        });

        btnDolar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Global.getInstance().valorConversor = 1.18;
                monedaText.setText("$");
                btnLibra.setBackgroundColor(Color.parseColor("#424242"));
                btnDolar.setBackgroundColor(Color.parseColor(buttonColorSelect));
                btnYen.setBackgroundColor(Color.parseColor("#424242"));
                btnPes.setBackgroundColor(Color.parseColor("#424242"));
            }
        });

        btnYen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Global.getInstance().valorConversor = 124.71;
                monedaText.setText("¥");
                btnLibra.setBackgroundColor(Color.parseColor("#424242"));
                btnDolar.setBackgroundColor(Color.parseColor("#424242"));
                btnYen.setBackgroundColor(Color.parseColor(buttonColorSelect));
                btnPes.setBackgroundColor(Color.parseColor("#424242"));
            }
        });

        btnPes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Global.getInstance().valorConversor = 24.95;
                monedaText.setText("₱");
                btnLibra.setBackgroundColor(Color.parseColor("#424242"));
                btnDolar.setBackgroundColor(Color.parseColor("#424242"));
                btnYen.setBackgroundColor(Color.parseColor("#424242"));
                btnPes.setBackgroundColor(Color.parseColor(buttonColorSelect));
            }
        });


        btnCE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numero.setText("0");
                numeroSortida.setText("0");
            }
        });

        btnComa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String botoNum = ",";
                String cadena = numero.getText().toString();
                int count = 0;

                for(int i = 0; i < cadena.length(); i++){
                    if(cadena.charAt(i) == ','){
                        count++;
                    }
                }
                if(count == 0){
                    numPressed(botoNum, numero);
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cadena = numero.getText().toString();

                if(cadena.length() > 1){
                    numero.setText(cadena.substring(0, cadena.length() -1));

                } else if(cadena.length() == 1){
                    numero.setText("0");
                }
            }
        });

        btnEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Mètode transformar numero a string amb decimal .
                String cadena = numero.getText().toString();

                if(cadena.charAt(0) == ','){
                    cadena = "0" + cadena;
                }

                char[] cadenaChar = cadena.toCharArray();

                for(int i = 0; i < cadenaChar.length; i++){
                    if(cadenaChar[i] == ','){
                        cadenaChar[i] = '.';
                    }
                }

                cadena = String.valueOf(cadenaChar);

                double valor = Double.parseDouble(cadena);
                int indexComa = 1;
                boolean comaTrobada = false;
                boolean surtBucle = false;
                int count = 1;

                valor = valor * Global.getInstance().valorConversor;

                DecimalFormat decimalFormat = new DecimalFormat("#.00");
                String resultat = decimalFormat.format(valor);

                //String resultat = String.valueOf(valor);
                String index1 = String.valueOf(resultat.charAt(1));
                String index2 = String.valueOf(resultat.charAt(2));

                if(resultat.equals(","+index1+index2)){
                    resultat = "0,"+index1+index2;
                }

                while(!comaTrobada && !surtBucle){

                    if(resultat.charAt(count) == ','){
                        indexComa = count;
                        comaTrobada = true;
                    }

                    if(count >= resultat.length()-1){
                        surtBucle = true;
                    }

                    count++;
                }

                resultat = resultat.substring(0, indexComa + 3);

                numeroSortida.setText(resultat);
            }
        });

        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String botoNum = "0";
                numPressed(botoNum, numero);
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String botoNum = "1";
                numPressed(botoNum, numero);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String botoNum = "2";
                numPressed(botoNum, numero);
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String botoNum = "3";
                numPressed(botoNum, numero);
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String botoNum = "4";
                numPressed(botoNum, numero);
            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String botoNum = "5";
                numPressed(botoNum, numero);
            }
        });

        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String botoNum = "6";
                numPressed(botoNum, numero);
            }
        });

        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String botoNum = "7";
                numPressed(botoNum, numero);
            }
        });

        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String botoNum = "8";
                numPressed(botoNum, numero);
            }
        });

        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String botoNum = "9";
                numPressed(botoNum, numero);
            }
        });
    }

    private void numPressed(String numeroBoto, TextView numeroText){

        String cadena = numeroText.getText().toString();
        boolean comaExistent = false;
        int counTDecimals = 0;

        for (int i = 0; i < cadena.length(); i++){
            if(cadena.charAt(i) == ','){
                comaExistent = true;
            }

            if(comaExistent){
                counTDecimals++;
            }
        }


        if(counTDecimals <= 2){
            if(numeroText.getText().equals("0")){
                numeroText.setText(numeroBoto);
            } else{
                numeroText.setText(numeroText.getText() + numeroBoto);
            }
        }
    }

}