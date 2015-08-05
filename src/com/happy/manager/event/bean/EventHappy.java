package com.happy.manager.event.bean;

/**
 * Created by Jorge Olvera on 04/08/2015.
 */
public class EventHappy {

    private final int id_e;
    private final String type_e;
    private final String comment_e;
    private final String image_e;

    public final int getId_e() {
        return id_e;
    }

    public final String getType_e() {
        return type_e;
    }

    public final String getComment_e() {
        return comment_e;
    }

    public final String getImage_e() {
        return image_e;
    }

    public static class Builder {
        // Required parameters
        private final int  id_e;
        private final String type_e;
        private final String comment_e;

        // Optional parameters - initialized to default values
        private String image_e = "empty";

        public Builder(final int id_e,final String type_e, final String comment_e) {
            this.id_e = id_e;
            this.type_e = type_e;
            this.comment_e = comment_e;
        }

        public Builder image_e(final String val) {
            image_e = val;
            return this;
        }

        public EventHappy build() {
            return new EventHappy(this);
        }
    }
    private EventHappy(final Builder builder) {
        id_e = builder.id_e;
        type_e = builder.type_e;
        comment_e = builder.comment_e;
        image_e = builder.image_e;
    }
}
