package omdvet.com;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.text.method.ScrollingMovementMethod;
import android.widget.ListView;
import android.widget.RemoteViews;
import android.widget.TextView;

import java.util.ArrayList;

import omdvet.com.WebServices.Models.Emp;
import omdvet.com.WebServices.Requests.EmpRequest;
import omdvet.com.WebServices.Responses.EmpResponse;
import omdvet.com.WebServices.RetrofitWebService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Implementation of App Widget functionality.
 */
public class NewAppWidget extends AppWidgetProvider {

    String myText = "";

    static void updateAppWidget(Context context, String text ,String text1, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        CharSequence widgetText = context.getString(R.string.appwidget_text);
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.new_app_widget);

        views.setTextViewText(R.id.appwidget_text, text);

        views.setTextViewText(R.id.appwidget_text1,text1);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);


    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
       // for (int appWidgetId : appWidgetIds) {
         //   updateAppWidget(context, appWidgetManager, appWidgetId);
        //}
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }




    public static void update_widget(Context context, AppWidgetManager appWidgetManager,
                                     int[] appWidgetIds,String text, String text1){

        for (int appWidgetId : appWidgetIds) {
               updateAppWidget(context, text ,text1,appWidgetManager, appWidgetId);
            }
    }
}

