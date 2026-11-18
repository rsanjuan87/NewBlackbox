package top.niunaijun.blackbox.app.dispatcher;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ProxyServiceForegroundTrackerTest {
    @Test
    public void stopReturnsTrueOnlyWhenRemovingLastForegroundService() {
        ProxyServiceForegroundTracker<Object> tracker = new ProxyServiceForegroundTracker<>();
        Object firstKey = new Object();
        Object secondKey = new Object();

        assertTrue(tracker.start(firstKey));
        assertFalse(tracker.start(firstKey));
        assertTrue(tracker.start(secondKey));
        assertFalse(tracker.stop(firstKey));
        assertTrue(tracker.hasForegroundServices());
        assertTrue(tracker.stop(secondKey));
        assertFalse(tracker.hasForegroundServices());
        assertFalse(tracker.stop(secondKey));
    }
}
