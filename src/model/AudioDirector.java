package model;

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
