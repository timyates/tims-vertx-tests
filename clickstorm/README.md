## clickstorm

Connecting browsers with minimal code.

Tested with Vert.x v1.3.0, and Chrome or Safari

## To run

    cd clickstorm
    vertx run App.groovy

## What it does

Each browser registers itself as a handler for `clicker` addressed events.

When user clicks on the canvas, the webpage sends a `clicker` message to the
Event Bus `{ x: xpos, y: ypos }`

This event then propagates to the server, and back out to all the connected browsers
(including the originator of the message), and when the message is recieved, draw
a blob at the `x,y` coordinate in the message.

## TODO

- Clean up code
- Handle resize of browser better
- Check browsers other than Chrome or safari
- Use %age rather than absolute x,y pos
- draw blob image centered on click
