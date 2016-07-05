package com.stagnationlab.c8y.example;

import c8y.IsDevice;
import com.cumulocity.model.authentication.CumulocityCredentials;
import com.cumulocity.rest.representation.inventory.ManagedObjectRepresentation;
import com.cumulocity.sdk.client.Platform;
import com.cumulocity.sdk.client.PlatformImpl;
import com.cumulocity.sdk.client.inventory.InventoryApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class App
{
    private static final Logger log = LoggerFactory.getLogger(App.class);

    private App() throws IOException {
        Config config = Config.loadOrCreateConfig();

        log.info("starting application, tenant: " + config.tenant + ", username: " + config.username);

        Platform platform = new PlatformImpl(
                "http://" + config.tenant + ".cumulocity.com",
                new CumulocityCredentials(
                        config.username,
                        config.password
                )
        );

        InventoryApi inventoryApi = platform.getInventoryApi();

        ManagedObjectRepresentation managedObjectRepresentation = new ManagedObjectRepresentation();
        managedObjectRepresentation.setName("Hello World!");
        managedObjectRepresentation.set(new IsDevice());
        managedObjectRepresentation = inventoryApi.create(managedObjectRepresentation);

        log.info("Created managed object URL: " + managedObjectRepresentation.getSelf());

        System.exit(0);
    }

    public static void main( String[] args ) throws IOException {
        new App();
    }
}
