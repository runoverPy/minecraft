package mdk.worldgen;

@Setting
public @interface IntSlider {
    int start();
    int stop();
    int step() default 1;
}
