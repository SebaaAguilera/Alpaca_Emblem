package model.map;

import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;


/**
 *
 * @author Ignacio slater Muñoz (mailto:ignacio.slater@ug.uchile.cl)
 * @version 3.0b8
 * @since 3.0
 */
public class Path implements Comparable<Path> {
    private final Location last;
    private final int length;
    private final List<Location> locations;

    /**
     * Path constructor
     * @param prevPath the path is building
     * @param nextLocation the next cell
     */
    public Path(Path prevPath, Location nextLocation) {
        locations = new LinkedList<>(
                prevPath != null ? prevPath.locations : Collections.emptyList());
        locations.add(nextLocation);
        last = nextLocation;
        length = locations.size() - 1;
    }

    /**
     *
     * @param another a path
     * @return the length (priority)
     */
    @Override
    public int compareTo(@NotNull Path another) {
        return length;
    }

    /**
     * @param location is going to compare
     * @return a boolean depending if the location is the final or not
     */
    public boolean endsIn(final Location location) {
        return last == location;
    }

    /**
     * @return a list of reachable locations
     */
    public List<Location> reachableLocations() {
        return last.getNeighbours().stream()
                .filter(location -> !locations.contains(location))
                .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * @return returns the length of the path
     */
    public int getLength() {
        return length;
    }
}
