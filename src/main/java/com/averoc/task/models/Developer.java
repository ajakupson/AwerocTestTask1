package com.averoc.task.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by de1mos on 7.08.16.
 */
public class Developer extends Person implements Comparable<Developer>{

    private List<Sprint> developerSprints = new ArrayList<Sprint>();
    private Float weightedAverageOfBurnedStoryPoints = 0.0F;

    public Float calculateWeightedAverageOfBurnedStoryPointsOverSeveralSprints() {
        Float dividend = 0.0F;
        Long divider = 0L;
        for(Sprint sprint : developerSprints) {
            dividend += (sprint.getTasksCount() * sprint.getArithmeticAverageOfBurnedPoints());
            divider += sprint.getTasksCount();
        }

        weightedAverageOfBurnedStoryPoints = dividend / divider;
        return weightedAverageOfBurnedStoryPoints;
    }

    public int compareTo(final Developer o) {
        return Float.compare(o.getWeightedAverageOfBurnedStoryPoints(), this.weightedAverageOfBurnedStoryPoints);
    }

    // getters / setters
    public List<Sprint> getDeveloperSprints() {
        return developerSprints;
    }
    public void setDeveloperSprints(List<Sprint> developerSprints) {
        this.developerSprints = developerSprints;
    }

    public Float getWeightedAverageOfBurnedStoryPoints() {
        return weightedAverageOfBurnedStoryPoints;
    }
    public void setWeightedAverageOfBurnedStoryPoints(Float weightedAverageOfBurnedStoryPoints) {
        this.weightedAverageOfBurnedStoryPoints = weightedAverageOfBurnedStoryPoints;
    }

    @Override
    public String toString() {
        return "Developer{" +
                "developerSprints=" + developerSprints +
                ", weightedAverageOfBurnedStoryPoints=" + weightedAverageOfBurnedStoryPoints +
                '}';
    }
}
