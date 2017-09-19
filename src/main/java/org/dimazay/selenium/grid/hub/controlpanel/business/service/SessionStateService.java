package org.dimazay.selenium.grid.hub.controlpanel.business.service;

import org.openqa.grid.internal.ExternalSessionKey;
import org.openqa.grid.internal.Registry;
import org.openqa.grid.internal.TestSession;
import org.openqa.selenium.remote.server.Session;

/**
 * Created by Dmytro_Zaitsev on 8/10/2017.
 */
public class SessionStateService {
    private final Registry registry;

    public SessionStateService(Registry registry) {
        this.registry = registry;
    }

    public void killTestSession(String sessionKey){
        ExternalSessionKey key = ExternalSessionKey.fromString(sessionKey);
        TestSession session = registry.getExistingSession(key);
        session.sendDeleteSessionRequest();
        session.getSlot().doFinishRelease();
    }

    public void getInformationForSpecificSession(String sessionKey){
        ExternalSessionKey key = ExternalSessionKey.fromString(sessionKey);
        TestSession session = registry.getExistingSession(key);
    }

}
