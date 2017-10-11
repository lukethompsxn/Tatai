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



    //Reads results of voice recognition, goes to file in documents. Need to change so it work for VM directory
    private String readResults() throws IOException {

        // reads file
        File file = new File("data" + fileSeperator + "recout.mlf");
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
            String docDirectory ="~" + fileSeperator + "Documents" + fileSeperator + "HTK" + fileSeperator + "MaoriNumbers" + fileSeperator;
            String cmd = "HVite -H " + docDirectory + "HMMs" + fileSeperator + "hmm15" +  fileSeperator + "macros -H " + docDirectory + "HMMs" +  fileSeperator +
                    "hmm15" +  fileSeperator + "hmmdefs -C " + docDirectory + "user" +  fileSeperator + "configLR  -w " + docDirectory + "user" +  fileSeperator
                    + "wordNetworkNum -o SWT -l '*' -i " + "data" + fileSeperator + "recout.mlf -p 0.0 -s 5.0  " + docDirectory + "user" +  fileSeperator +
                    "dictionaryD " + docDirectory + "user" + fileSeperator + "tiedList data" + fileSeperator + "audio.wav";
            runInBash(cmd);
            return 0;
        }
    }
}
