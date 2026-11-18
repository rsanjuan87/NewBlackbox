package top.niunaijun.blackbox.app.dispatcher;

import java.util.HashSet;
import java.util.Set;

final class ProxyServiceForegroundTracker<T> {
    private final Set<T> serviceKeys = new HashSet<>();

    synchronized boolean start(T key) {
        validateKey(key);
        return serviceKeys.add(key);
    }

    synchronized boolean stop(T key) {
        validateKey(key);
        return serviceKeys.remove(key) && serviceKeys.isEmpty();
    }

    synchronized boolean hasForegroundServices() {
        return !serviceKeys.isEmpty();
    }

    synchronized void clear() {
        serviceKeys.clear();
    }

    private static void validateKey(Object key) {
        if (key == null) {
            throw new IllegalArgumentException("Service key must not be null");
        }
    }
}
