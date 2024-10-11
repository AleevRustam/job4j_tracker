package ru.job4j.ood.isp;

interface Device {

    void playAudio(String audioFile);

    void displayVideo(String videoFile);

    void printText(String text);
}

class AudioPlayer implements Device {
    @Override
    public void playAudio(String audioFile) {
        System.out.println("Playing audio: " + audioFile);
    }

    @Override
    public void displayVideo(String videoFile) {
        throw new UnsupportedOperationException("Video not supported");
    }

    @Override
    public void printText(String text) {
        throw new UnsupportedOperationException("Printing not supported");
    }
}
