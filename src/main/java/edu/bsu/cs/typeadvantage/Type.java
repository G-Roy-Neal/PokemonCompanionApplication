package edu.bsu.cs.typeadvantage;

import java.net.URL;
import java.util.Objects;

public class Type {
    String type;
    URL typeUrl;

    public static final class Builder{
        public String type;
        public URL typeURL;

        public Builder withType(String type){
            this.type = type;
            return this;
        }
        public Builder withURL(URL typeURL){
            this.typeURL = typeURL;
            return this;
        }

        public Type build(){
            return new Type(this);
        }
    }

    private Type(Builder builder){
        this.type = builder.type;
        this.typeUrl = builder.typeURL;
    }

    public String getType() {
        return type;
    }

    public URL getTypeUrl() {
        return typeUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Type type1 = (Type) o;
        return Objects.equals(type, type1.type) && Objects.equals(typeUrl, type1.typeUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, typeUrl);
    }
}
