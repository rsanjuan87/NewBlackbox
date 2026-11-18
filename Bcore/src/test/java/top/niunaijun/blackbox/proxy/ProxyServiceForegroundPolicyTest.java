package top.niunaijun.blackbox.proxy;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class ProxyServiceForegroundPolicyTest {
    @Test
    public void promotesForegroundServiceOnApi26() {
        assertTrue(ProxyServiceForegroundPolicy.shouldPromote(true, 26));
    }

    @Test
    public void promotesForegroundServiceOnApi33() {
        assertTrue(ProxyServiceForegroundPolicy.shouldPromote(true, 33));
    }

    @Test
    public void doesNotPromoteNonForegroundService() {
        assertFalse(ProxyServiceForegroundPolicy.shouldPromote(false, 33));
    }

    @Test
    public void doesNotPromoteBeforeApi26() {
        assertFalse(ProxyServiceForegroundPolicy.shouldPromote(true, 25));
    }

    @Test
    public void demotesOnlyForegroundRequestsWithoutActiveServices() {
        assertTrue(ProxyServiceForegroundPolicy.shouldDemote(true, false));
        assertFalse(ProxyServiceForegroundPolicy.shouldDemote(false, false));
        assertFalse(ProxyServiceForegroundPolicy.shouldDemote(true, true));
    }

    @Test
    public void notificationIdsAreUniqueAndAvoidCollidingDaemonId() {
        int daemonNotificationId = 10017;
        Set<Integer> notificationIds = new HashSet<>();

        for (int proxyIndex = 0; proxyIndex < 50; proxyIndex++) {
            int notificationId = ProxyServiceForegroundPolicy.notificationId(
                    proxyIndex, daemonNotificationId);

            assertNotEquals(daemonNotificationId, notificationId);
            assertTrue(notificationIds.add(notificationId));
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void rejectsNegativeProxyIndex() {
        ProxyServiceForegroundPolicy.notificationId(-1, 10017);
    }

    @Test(expected = IllegalArgumentException.class)
    public void rejectsProxyIndexAboveRange() {
        ProxyServiceForegroundPolicy.notificationId(50, 10017);
    }
}
