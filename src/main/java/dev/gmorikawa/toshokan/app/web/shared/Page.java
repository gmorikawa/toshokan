package dev.gmorikawa.toshokan.app.web.shared;

public class Page {
    private final String title;
    private final String subtitle;

    public String getTitle() {
        return title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public Page(String title, String subtitle) {
        this.title = title;
        this.subtitle = subtitle;
    }
}
