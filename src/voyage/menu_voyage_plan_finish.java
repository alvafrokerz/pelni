package voyage;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.main.R;
import com.example.main.variabelpublic;

import static com.example.main.R.id.tes;
import static com.example.main.variabelpublic.Data_menu_voayage_class;
import static com.example.main.variabelpublic.Data_menu_voyage_addmenu;
import static com.example.main.variabelpublic.Data_menu_voyage_addmenu_totalday;
import static com.example.main.variabelpublic.Data_menu_voyage_tipemakan_total;
import static com.example.main.variabelpublic.Data_menu_voyage_tipemakans;
import static com.example.main.variabelpublic.String_database_menu_voyage_menu;

/**
 * Created by ALVA on 10/13/2016.
 */

public class menu_voyage_plan_finish   extends  com.example.main.main_menu_template    {



@Override
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout item = (LinearLayout)findViewById(tes);
        View child = getLayoutInflater().inflate(R.layout.menu_voyage_plan_finish, null);
        item.addView(child);


    TextView destination=(TextView)findViewById(R.id.textview_menu_voyage_finish_destination);
    TextView kapal=(TextView)findViewById(R.id.textview_menu_voyage_finish_kapal);
    TextView tanggal=(TextView)findViewById(R.id.textview_menu_voyage_finish_tanggal);
    TextView kelasmenu=(TextView)findViewById(R.id.textview_menu_voyage_finish_kelasmenu);

    destination.setText(variabelpublic.Data_menu_voyage_origin+"-"+variabelpublic.Data_menu_voyage_to);
    kapal.setText(variabelpublic.Data_menu_voyage_kapal);
    tanggal.setText(variabelpublic.Data_menu_voyage_tanggal+":"+variabelpublic.Data_menu_voyage_jam);

String kelas="";
    for (int i = 0; i <Data_menu_voyage_addmenu_totalday ; i++) {
        kelas=kelas+" <center><font size='5' >day"+String.valueOf(i+1)+"</font><center><br>";
        for (int j = 0; j <Data_menu_voayage_class.size() ; j++) {
            kelas=kelas+""+Data_menu_voayage_class.get(j)+"<br>";
            for (int k = 0; k < Data_menu_voyage_tipemakan_total; k++) {
                //System.out.print(String_database_menu_voyage_menu[i][j]+",");
                kelas=kelas+" "+Data_menu_voyage_tipemakans[k]+""+Data_menu_voyage_addmenu.get(Integer.parseInt(String_database_menu_voyage_menu[i][k][j]))+"<br>";

            }
             }
        kelas=kelas+"<br>";
        System.out.println(kelas);
    }
    kelasmenu.setText(Html.fromHtml(kelas));

}
}
