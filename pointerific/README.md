## pointerific

Connecting browsers with minimal code.

Tested with Vert.x v1.3.0.final, and Chrome or Safari

## To run

    cd pointerific
    vertx run App.groovy -cp guava-13.0.1.jar

## What it does

Each browser registers itself as a handler for `pointer` addressed events.

Each time requestanimationframe is called, the user sends their current mouse location
to the address `update` in the following form: `{ uid:guid, x: xpos, y: ypos }`

The server gets this message, keeps it in a LRU map with a 10 second eviction policy.

Every so often, 100 ms, the server sends the current list of pointer locations to the
`pointer` address.

Browsers update cursors on screen each time this message is recieved.

# Demo

See http://vertx-pointers.herokuapp.com/