package ru.job4j.ood.isp;

interface SocialMedia {

    void postMessage(String message);

    void uploadPhoto(String filePath);

    void streamVideo(String url);
}

class Twitter implements SocialMedia {
    @Override
    public void postMessage(String message) {
        System.out.println("Posting message to Twitter: " + message);
    }

    @Override
    public void uploadPhoto(String filePath) {
        System.out.println("Uploading photo to Twitter: " + filePath);
    }

    @Override
    public void streamVideo(String url) {
        throw new UnsupportedOperationException("Video streaming not supported on Twitter");
    }
}
