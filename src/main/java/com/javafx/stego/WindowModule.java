package com.javafx.stego;

import com.google.inject.AbstractModule;
import com.javafx.stego.model.WindowData;


public class WindowModule extends AbstractModule {
    @Override
    protected void configure() {
        WindowData model = new WindowData();
        bind(WindowData.class).toInstance(model);
    }
}
