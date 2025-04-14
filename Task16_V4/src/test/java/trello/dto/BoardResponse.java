package trello.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BoardResponse {
    private String id;
    private String name;
    private boolean closed;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isClosed() {
        return closed;
    }
}