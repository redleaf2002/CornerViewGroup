package com.leaf.corner;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.FrameLayout;

public class CornerFrameLayout extends FrameLayout {
    private float topLeftRadius;
    private float topRightRadius;
    private float bottomLeftRadius;
    private float bottomRightRadius;
    private int strokeWidth;
    private int strokeColor;
    private static final int STROKE_DEFAULT_COLOR = Color.TRANSPARENT;

    private Path mPath;
    private RectF mRectF;
    private Paint mPaint;
    private int width, height;

    public CornerFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (attrs != null) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomCorner);
            float radius = typedArray.getDimensionPixelSize(R.styleable.CustomCorner_radius, 0);
            topLeftRadius = typedArray.getDimensionPixelSize(R.styleable.CustomCorner_topLeftRadius, 0);
            topRightRadius = typedArray.getDimensionPixelSize(R.styleable.CustomCorner_topRightRadius, 0);
            bottomLeftRadius = typedArray.getDimensionPixelSize(R.styleable.CustomCorner_bottomLeftRadius, 0);
            bottomRightRadius = typedArray.getDimensionPixelSize(R.styleable.CustomCorner_bottomRightRadius, 0);
            strokeWidth = typedArray.getDimensionPixelSize(R.styleable.CustomCorner_strokeWidth, 0);
            strokeColor = typedArray.getColor(R.styleable.CustomCorner_strokeColor, STROKE_DEFAULT_COLOR);
            typedArray.recycle();
            radius = radius > 0 ? radius : 0;
            topLeftRadius = topLeftRadius > 0 ? topLeftRadius : radius;
            topRightRadius = topRightRadius > 0 ? topRightRadius : radius;
            bottomLeftRadius = bottomLeftRadius > 0 ? bottomLeftRadius : radius;
            bottomRightRadius = bottomRightRadius > 0 ? bottomRightRadius : radius;
        }

        mPath = new Path();
        mRectF = new RectF();

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        width = getWidth();
        height = getHeight();
        drawLayer(canvas);

        super.dispatchDraw(canvas);

        drawPath(canvas);

        if (strokeColor != 0) {
            drawStroke(canvas, strokeColor, strokeWidth);
        }
    }

    void drawLayer(Canvas canvas) {
        canvas.saveLayer(new RectF(0, 0, canvas.getWidth(), canvas.getHeight()), mPaint, Canvas.ALL_SAVE_FLAG);
    }

    void drawPath(Canvas canvas) {
        mPaint.reset();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(1);
        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        drawTopLeft(canvas);
        drawTopRight(canvas);
        drawBottomLeft(canvas);
        drawBottomRight(canvas);
        mPaint.setXfermode(null);
        canvas.restore();
    }

    public void drawStroke(Canvas canvas, int strokeColor, int strokeWidth) {
        mPaint.reset();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(strokeColor);
        mPaint.setStrokeWidth(strokeWidth);
        canvas.drawPath(getStrokePath(), mPaint);
    }

    private Path getStrokePath() {
        mPath.reset();
        mPath.moveTo(topLeftRadius, 0);
        if (topLeftRadius > 0) {
            mRectF.set(0, 0, topLeftRadius * 2, topLeftRadius * 2);
            mPath.arcTo(mRectF, -90, -90);
        }
        mPath.lineTo(0, height - bottomLeftRadius);
        if (topRightRadius > 0) {
            mRectF.set(0, height - 2 * bottomLeftRadius, bottomLeftRadius * 2, height);
            mPath.arcTo(mRectF, 180, -90);
        }
        mPath.lineTo(width - bottomRightRadius, height);
        if (bottomRightRadius > 0) {
            mRectF.set(width - 2 * bottomRightRadius, height - 2 * bottomRightRadius, width, height);
            mPath.arcTo(mRectF, 90, -90);
        }
        mPath.lineTo(width, topRightRadius);
        if (topRightRadius > 0) {
            mRectF.set(width - 2 * topRightRadius, 0, width, 2 * topRightRadius);
            mPath.arcTo(mRectF, 0, -90);
        }
        mPath.close();
        return mPath;
    }

    private void drawTopLeft(Canvas canvas) {
        if (topLeftRadius > 0) {
            mPath.reset();
            mPath.moveTo(0, topLeftRadius);
            mPath.lineTo(0, 0);
            mPath.lineTo(topLeftRadius, 0);
            mRectF.set(0, 0, topLeftRadius * 2, topLeftRadius * 2);
            mPath.arcTo(mRectF, -90, -90);
            mPath.close();
            canvas.drawPath(mPath, mPaint);
        }
    }

    private void drawTopRight(Canvas canvas) {
        if (topRightRadius > 0) {
            mPath.reset();
            mPath.moveTo(width - topRightRadius, 0);
            mPath.lineTo(width, 0);
            mPath.lineTo(width, topRightRadius);
            mRectF.set(width - 2 * topRightRadius, 0, width, topRightRadius * 2);
            mPath.arcTo(mRectF, 0, -90);
            mPath.close();
            canvas.drawPath(mPath, mPaint);
        }
    }

    private void drawBottomLeft(Canvas canvas) {
        if (bottomLeftRadius > 0) {
            mPath.reset();
            mPath.moveTo(0, height - bottomLeftRadius);
            mPath.lineTo(0, height);
            mPath.lineTo(bottomLeftRadius, height);
            mRectF.set(0, height - 2 * bottomLeftRadius, bottomLeftRadius * 2, height);
            mPath.arcTo(mRectF, 90, 90);
            mPath.close();
            canvas.drawPath(mPath, mPaint);
        }
    }

    private void drawBottomRight(Canvas canvas) {
        if (bottomRightRadius > 0) {
            mPath.reset();
            mPath.moveTo(width - bottomRightRadius, height);
            mPath.lineTo(width, height);
            mPath.lineTo(width, height - bottomRightRadius);
            mRectF.set(width - 2 * bottomRightRadius, height - 2 * bottomRightRadius, width, height);
            mPath.arcTo(mRectF, 0, 90);
            mPath.close();
            canvas.drawPath(mPath, mPaint);
        }
    }

    public void setCornderRadius(float topLeftRadius, float topRightRadius, float bottomLeftRadius, float bottomRightRadius) {
        this.topLeftRadius = topLeftRadius;
        this.topRightRadius = topRightRadius;
        this.bottomLeftRadius = bottomLeftRadius;
        this.bottomRightRadius = bottomRightRadius;
        postInvalidate();
    }
}