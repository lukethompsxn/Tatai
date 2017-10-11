package model;

import javafx.concurrent.Task;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class AudioDirector {
    private static AudioDirector _audioDirector;
    private String fileSeparator = File.separator;

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



    //Reads results of voice recognition, goes to file in documents. Need to change so it work for VM directory
    private String readResults() throws IOException {

        // reads file
        File file = new File("data" + fileSeparator + "recout.mlf");
        FileInputStream fis = new FileInputStream(file);
        byte[] bytes = new byte[(int) file.length()];
        fis.read(bytes);
        fis.close();

        String text = new String(bytes, "UTF-8");
        //Puts string from file into string array
        String[] words = text.split("\\W+");
        String newText = "";

        // Places text file back into string without the extra format
        for (int j = 0; j < words.length-1; j++) {
            if (j == words.length-2) {
                newText += words[j];
                break;
            }
            newText += words[j] + " ";
        }

        // Removes any text that is not needed
        newText = newText.replaceFirst(" ", "").replaceFirst("MLF audio rec sil ", "");
        return newText;
    }

    class VoiceRecognitionInBackground extends Task<Integer> {

        @Override
        protected Integer call() throws Exception {
            String docDirectory ="~" + fileSeparator + "Documents" + fileSeparator + "HTK" + fileSeparator + "MaoriNumbers" + fileSeparator;
            String cmd = "HVite -H " + docDirectory + "HMMs" + fileSeparator + "hmm15" +  fileSeparator + "macros -H " + docDirectory + "HMMs" +  fileSeparator +
                    "hmm15" +  fileSeparator + "hmmdefs -C " + docDirectory + "user" +  fileSeparator + "configLR  -w " + docDirectory + "user" +  fileSeparator
                    + "wordNetworkNum -o SWT -l '*' -i " + "data" + fileSeparator + "recout.mlf -p 0.0 -s 5.0  " + docDirectory + "user" +  fileSeparator +
                    "dictionaryD " + docDirectory + "user" + fileSeparator + "tiedList data" + fileSeparator + "audio.wav";
            runInBash(cmd);
            return 0;
        }
    }

    //Helper method for making the process and process builder so commands can be executed in bash
    private void runInBash(String cmd) {
        Process process;
        ProcessBuilder processBuilder = new ProcessBuilder(fileSeparator + "bin" + fileSeparator + "bash", "-c", cmd);
        try {
            process = processBuilder.start();
        } catch (IOException excep) {
            excep.printStackTrace();
            return;
        }
        try {
            process.waitFor();
        } catch (InterruptedException ie) {
            ie.printStackTrace();
            return;
        }
    }
}
