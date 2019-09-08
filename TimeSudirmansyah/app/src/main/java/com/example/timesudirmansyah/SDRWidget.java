package com.example.timesudirmansyah;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.widget.RemoteViews;

/**
 * Implementation of App Widget functionality.
 */
public class SDRWidget extends AppWidgetProvider {
    public static Bitmap BuildUpdate(String txttime, int size, Context context){
        Paint paint = new Paint();
        paint.setTextSize(size);
        Typeface ourCustomTypeface = Typeface.createFromAsset(context.getAssets(),"fonts/Lato-Light.ttf");
        paint.setTypeface(ourCustomTypeface);
        paint.setColor(Color.WHITE);
        paint.setSubpixelText(true);
        paint.setAntiAlias(true);
        float baseline = -paint.ascent();
        int width=(int) (paint.measureText(txttime)+0.5f);
        int height=(int) (baseline+paint.descent()+0.5f);
        Bitmap image = Bitmap.createBitmap(width,height, Bitmap.Config.ARGB_4444);
        Canvas canvas = new Canvas(image);
        canvas.drawText(txttime,0,baseline,paint);
        return image;
    }
    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        CharSequence widgetText = context.getString(R.string.appwidget_text);
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.sdrwidget);
        views.setImageViewBitmap(R.id.imgnama, BuildUpdate("Sudirmansyah",80,context));

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

