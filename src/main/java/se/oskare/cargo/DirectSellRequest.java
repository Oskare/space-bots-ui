package se.oskare.cargo;

import com.fasterxml.jackson.annotation.JsonInclude;

public class DirectSellRequest {
    public Resources resources = new Resources();

    public static class Resources {
        @JsonInclude(JsonInclude.Include.NON_NULL)
        public Integer aluminium;
        @JsonInclude(JsonInclude.Include.NON_NULL)
        public Integer zinc;
        @JsonInclude(JsonInclude.Include.NON_NULL)
        public Integer titanium;
        @JsonInclude(JsonInclude.Include.NON_NULL)
        public Integer zirconium;
        @JsonInclude(JsonInclude.Include.NON_NULL)
        public Integer mithril;
    }
}