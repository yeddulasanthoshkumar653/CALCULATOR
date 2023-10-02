package com.example.calci;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.udojava.evalex.Expression;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView result,input;

    MaterialButton one,two,three,four,five,six,seven,eight,nine,zero,clear,allclear,subtract,add,multiply,divison,dot,equal,open_bracket,close_bracket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        result=findViewById(R.id.result);
        input= findViewById(R.id.input);
        initview(one,R.id.one);
        initview(two,R.id.two);
        initview(three,R.id.three);
        initview(four,R.id.four);
        initview(five,R.id.five);
        initview(six,R.id.six);
        initview(seven,R.id.seven);
        initview(eight,R.id.eight);
        initview(nine,R.id.nine);
        initview(zero,R.id.zero);
        initview(multiply,R.id.multiply);
        initview(divison,R.id.division);
        initview(add,R.id.add);
        initview(subtract,R.id.subtract);
        initview(open_bracket,R.id.ob);
        initview(close_bracket,R.id.cb);
        initview(clear,R.id.clear);
        initview(allclear,R.id.allclear);
        initview(equal,R.id.equal);




    }

    private void initview(MaterialButton btn, int one1) {
        btn= findViewById(one1);
        btn.setOnClickListener(this);

    }
    @Override
    public void onClick(View view){
        MaterialButton button =(MaterialButton) view;
        String btn = button.getText().toString();
        String data = input.getText().toString();

        if(btn.equalsIgnoreCase("AC")){
            input.setText("");
            result.setText("");
            return;
        }
        if(btn.equalsIgnoreCase("=")){
         if(data.length()==1){

             if(!Character.isDigit(data.charAt(0))){
                 input.setText("");
                 result.setText("");
                 return;
             }
            else return;
         }
         else{
             input.setText(result.getText().toString());
             result.setText("");
             return;
         }
        }
        if(btn.equalsIgnoreCase("C")){
            if(data.length()==0){
                return;
            }
            if(data.length()==1){
                input.setText("");
                result.setText("");
                return;
            }else
                data = data.substring(0,data.length()-1);

        }else{
            data= data+btn;
        }
        input.setText(data);
        if(Character.isDigit(data.charAt(data.length()-1))) {
            String fres = getData(data);
            if(!(fres.equals("err"))){
                result.setText(fres);
            }
        }else{
           String fres = getData(data.substring(0,data.length()-1));
            if(!(fres.equals("err"))){
                result.setText(fres);
            }
        }

    }

    public String getData(String data){
//        Expression expression = new Expression(data);
//        return expression.eval().toString();
       try {
           Context context = Context.enter();
           context.setOptimizationLevel(-1);
           Scriptable scriptable = context.initSafeStandardObjects();
           String f =context.evaluateString(scriptable, data, "Javascript", 1, null).toString();
           if (f.endsWith(".0")) {
               f = f.replace(".0", "");
           }
           return f;
       }catch (Exception e){
           return "err";
       }
    }

}