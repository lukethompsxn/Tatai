package model;

import javafx.concurrent.Task;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class AudioDirector {
    private static AudioDirector _audioDirector;

    //Singleton Constructor
    public static AudioDirector instance() {
        if (_audioDirector == null) {
            _audioDirector = new AudioDirector();
        }
        return _audioDirector;
    }

    //Private Constructor
    private AudioDirector() {
        //anything that needs to constructed
    }




}
