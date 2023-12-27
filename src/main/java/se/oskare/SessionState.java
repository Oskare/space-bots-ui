package se.oskare;

import jakarta.enterprise.context.ApplicationScoped;

import java.io.Serializable;

@ApplicationScoped
public class SessionState implements Serializable {
    public String currentFleet = "0a168186-a789-433a-839a-9b55acba1101";
}
