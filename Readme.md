GWT Multimodule example with tbroyer plugin
===========================================

This project is a simple example of a multimodule GWT application using the new GWT plugin (aka *tbroyer* plugin).

It  is an extension of the *modular-webapp* archetype described [here](https://github.com/tbroyer/gwt-maven-archetypes/).

The application is composed of a *main* container (the **GwtMultiModule-** submodules) and other submodules grouped by
*architectural* pertinence (clients, shared, server) following the initial example.
To match the *tbroyer* nomenclature, the following *convention* has been used:

1. shared -> api
2. client -> client
3. server -> backend

Modules belonging to the same *architectural* group have been declared as submodules of a common *bom*, so to avoid repeating declaration of the same dependencies all over the poms.

To start the application, issue the following commands in two different consoles:



    mvn clean gwt:codeserver -pl *-client -am

    mvn jetty:run -pl *-server -am -Denv=dev


and then open the browser at the following url:

    http://localhost:8080
