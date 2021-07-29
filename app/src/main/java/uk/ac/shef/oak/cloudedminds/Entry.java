package uk.ac.shef.oak.cloudedminds;

public class Entry {

    private String event;
    private String date;
    private String mood;
    private int mood_rating;
    private String catastrophise;
    private String generalise;
    private String ignoring;
    private String self_critical;
    private String mind_reading;
    private String changed_mood;
    private int changed_rating;

    public String getEvent(){
        return event;
    }

    public String getDate(){
        return date;
    }

    public String getMood(){
        return mood;
    }

    public int getMood_rating(){
        return mood_rating;
    }

    public String getCatastrophise(){
        return catastrophise;
    }

    public String getGeneralise(){
        return generalise;
    }

    public String getIgnoring(){
        return ignoring;
    }

    public String getSelf_critical(){
        return self_critical;
    }

    public String getMind_reading(){
        return mind_reading;
    }

    public String getChanged_mood(){
        return changed_mood;
    }

    public int getChanged_rating(){
        return changed_rating;
    }
}
