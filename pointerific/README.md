## clickstorm

Connecting browsers with minimal code.

Tested with Vert.x v1.3.0.final, and Chrome or Safari

## To run

    cd pointerific
    vertx run App.groovy -cp guava-13.0.1.jar

## What it does

Each browser registers itself as a handler for `pointer` addressed events.

Each time requestanimationframe is called, the user sends their current mouse location
to the address `update` in the following form: `{ id:guid, x: xpos, y: ypos }`

The server gets this message, appends the current time as a long to it, and stores it
in an internal `Map<GUID,pos>`.

Every 100 ms, the server filters the map, (removing entries over 10s old), and sends this
resultant map to the `pointer` address.

Browsers update cursors on screen each time this message is recieved.

# Demo

See http://vertx-pointers.herokuapp.com/