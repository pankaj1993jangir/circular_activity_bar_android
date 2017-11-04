package pankaj.com.circularview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by pankaj on 11/4/17.
 */

public class CircularProgressBar extends View {
    private Context mContext;
    private int angle = 0;
    private int startAngle = 270;
    private int barWidth = 15;
    private float innerRadius;
    private float outerRadius;
    private float cx;
    private float cy;
    private Bitmap progressMark;
    private float markPointX;
    private float markPointY;

    private RectF rect = new RectF();


    public CircularProgressBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mContext = context;
    }

    public CircularProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
    }

    public CircularProgressBar(Context context) {
        super(context);
        mContext = context;
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = getWidth();
        int height = getHeight();

        int size = (width > height) ? height : width;


        cx = width / 2;
        cy = height / 2;
        outerRadius = size / 2;

        innerRadius = outerRadius - barWidth;

        float left = cx - outerRadius;
        float right = cx + outerRadius;
        float top = cy - outerRadius;
        float bottom = cy + outerRadius;

        rect.set(left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        Paint circleColor = new Paint();
        Paint innerColor = new Paint();
        Paint circleRing = new Paint();

        circleColor.setColor(Color.BLACK);
        innerColor.setColor(Color.WHITE);
        circleRing.setColor(Color.RED);

        circleColor.setAntiAlias(true);
        innerColor.setAntiAlias(true);
        circleRing.setAntiAlias(true);

        circleColor.setStrokeWidth(5);
        innerColor.setStrokeWidth(5);
        circleRing.setStrokeWidth(5);

        circleColor.setStyle(Paint.Style.FILL);


        canvas.drawCircle(cx, cy, outerRadius, circleColor);
        canvas.drawArc(rect, startAngle, angle, true, circleRing);
        canvas.drawCircle(cx, cy, innerRadius, innerColor);

        drawMarkerAtProgress(canvas);

        super.onDraw(canvas);
    }

    public void setAngle(int angle) {
        this.angle = angle % 360;
    }

    private void drawMarkerAtProgress(Canvas canvas) {
        progressMark = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.clock_on_progress);

        markPointX = (float) (innerRadius * Math.cos((angle + startAngle) * Math.PI / 180F)) + cx;
        markPointY = (float) (innerRadius * Math.sin((angle + startAngle) * Math.PI / 180F)) + cy;

        canvas.drawBitmap(progressMark, getXFromAngle(), getYFromAngle(), null);
    }

    private float getXFromAngle() {
        int width = progressMark.getWidth();
        float x = markPointX - (width / 2);
        return x;
    }

    private float getYFromAngle() {
        int height = progressMark.getHeight();
        float y = markPointY - (height / 2);
        return y;
    }
}
