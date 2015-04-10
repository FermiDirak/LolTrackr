package manuele.bryan.lolwinrate.Helpers;

import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ProgressBar;

public class ProgressBarAnimation extends Animation {
    private ProgressBar progressBar;
    private float from;
    private float to;

    public ProgressBarAnimation(ProgressBar progressBar, float from, float to) {
        super();
        this.progressBar = progressBar;
        this.from = from;
        this.to = to;
    }

    @Override
    protected void applyTransformation(float animationTime, Transformation transformation) {
        super.applyTransformation(animationTime, transformation);
        float progressValue = from + (to - from) * animationTime;
        progressBar.setProgress((int) progressValue);
    }

}
