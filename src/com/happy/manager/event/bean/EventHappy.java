package com.happy.manager.event.bean;

/**
 * Created by Jorge Olvera on 04/08/2015.
 */
public class EventHappy {

    private final String type;
    private final String comment;
    private final String image;

    public final String getType() {
        return type;
    }

    public final String getComment() {
        return comment;
    }

    public final String getImage() {
        return image;
    }

    public static class Builder {
        // Required parameters
        private final String type;
        private final String comment;

        // Optional parameters - initialized to default values
        private String image = "empty";

        public Builder(final String type, final String comment) {
            this.type = type;
            this.comment = comment;
        }

        public Builder image(final String val) {
            image = val;
            return this;
        }

        public EventHappy build() {
            return new EventHappy(this);
        }
    }
    private EventHappy(final Builder builder) {
        type = builder.type;
        comment = builder.comment;
        image = builder.image;
    }
}
