package ru.job4j.ood.isp;

interface Printer {

    void printDocument(String content);

    void scanDocument(String filePath);

    void faxDocument(String number);
}

class SimplePrinter implements Printer {
    @Override
    public void printDocument(String content) {
        System.out.println("Printing: " + content);
    }

    @Override
    public void scanDocument(String filePath) {
        throw new UnsupportedOperationException("Scan not supported");
    }

    @Override
    public void faxDocument(String number) {
        throw new UnsupportedOperationException("Fax not supported");
    }
}
