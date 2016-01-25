package io.github.nuannuan.modle;

import java.util.List;

/**
 * Created by kevin.
 */
public class Images {

    @Override
    public String toString() {
        return "Images{" +
                "error=" + error +
                ", results=" + results +
                '}';
    }

    private boolean error;

    private List<ResultsEntity> results;

    public void setError(boolean error) {
        this.error = error;
    }

    public void setResults(List<ResultsEntity> results) {
        this.results = results;
    }

    public boolean isError() {
        return error;
    }

    public List<ResultsEntity> getResults() {
        return results;
    }
}
