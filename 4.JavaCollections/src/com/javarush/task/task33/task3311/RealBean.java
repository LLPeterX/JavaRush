package com.javarush.task.task33.task3311;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.Map;

public class RealBean {
    protected final int id;
    protected final String name;

    protected Map<String, Object> additionalMap = new HashMap<>();

    @JsonCreator
    public RealBean(@JsonProperty("id") int id, @JsonProperty("name") String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    // убрать вывод "additionalMap" - вместо него сами значения
    //@JsonProperty("additionalMap") - неверно!!
    //@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_EMPTY) - не то
    // @JsonIgnore - не то
    @JsonAnyGetter
    public Map<String, Object> getAdditionalMap() {
        return additionalMap;
    }

    @JsonAnySetter
    public void setAdditionalMap(String name, Object value) {
        additionalMap.put(name, value);
    }
}