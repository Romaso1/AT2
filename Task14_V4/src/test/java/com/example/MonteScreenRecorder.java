package com.example;

import ru.sbtqa.monte.media.Format;
import ru.sbtqa.monte.media.Registry;
import ru.sbtqa.monte.screenrecorder.ScreenRecorder;

import java.awt.AWTException;
import java.awt.GraphicsConfiguration;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;

import static ru.sbtqa.monte.media.FormatKeys.*;
import static ru.sbtqa.monte.media.VideoFormatKeys.*;

public class MonteScreenRecorder extends ScreenRecorder {

    private String testName;

    public MonteScreenRecorder(GraphicsConfiguration cfg, Rectangle captureArea,
                               Format fileFormat, Format screenFormat,
                               Format mouseFormat, Format audioFormat,
                               File movieFolder, String testName)
            throws IOException, AWTException {
        super(cfg, captureArea, fileFormat, screenFormat, mouseFormat, audioFormat, movieFolder);
        this.testName = testName;
    }

    @Override
    protected File createMovieFile(Format fileFormat) throws IOException {
        if (!this.movieFolder.exists()) {
            this.movieFolder.mkdirs();
        } else if (!this.movieFolder.isDirectory()) {
            throw new IOException("\"" + this.movieFolder + "\" є файлом, а не директорією.");
        }
        // Используем Registry для получения расширения файла
        return new File(this.movieFolder, testName + "." + Registry.getInstance().getExtension(fileFormat));
    }
}
