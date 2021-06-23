package com.miro.data;

public enum HowDidYouHear {
    SOCIAL_MEDIA("Social Media"),
    PODCAST("Podcast"),
    FRIEND_COLLEAGUE("Friend / Colleague");

    private String option;

    HowDidYouHear(String option) {
        this.option = option;
    }

    @Override
    public String toString() {
        return option;
    }
}
