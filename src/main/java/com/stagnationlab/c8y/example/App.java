package com.stagnationlab.c8y.example;

import c8y.IsDevice;
import com.cumulocity.model.authentication.CumulocityCredentials;
import com.cumulocity.rest.representation.inventory.ManagedObjectRepresentation;
import com.cumulocity.sdk.client.Platform;
import com.cumulocity.sdk.client.PlatformImpl;
import com.cumulocity.sdk.client.inventory.InventoryApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App
{
    public static final Logger log = LoggerFactory.getLogger(App.class);

    public static void main( String[] args )
    {
        log.info("starting application");

        Platform platform = new PlatformImpl(
                "http://telia.cumulocity.com",
                new CumulocityCredentials(
                        "priit.kallas@telia.ee",
                        "purgisupp"
                )
        );

        InventoryApi inventoryApi = platform.getInventoryApi();

        ManagedObjectRepresentation managedObjectRepresentation = new ManagedObjectRepresentation();
        managedObjectRepresentation.setName("Hello World!");
        managedObjectRepresentation.set(new IsDevice());
        managedObjectRepresentation = inventoryApi.create(managedObjectRepresentation);

        log.info("URL: " + managedObjectRepresentation.getSelf());
    }
}
