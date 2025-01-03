package ho.artisan.anno.core;

import java.util.List;

/**
 * An interface for containing entries.
 */
public interface EntryContainer {
    List<Entry> getEntries();

    String id();
}
