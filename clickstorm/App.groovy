def webServerConf = [
  // Normal web server stuff
  port   : 8080,
  host   : 'localhost',
  bridge : true,

  inbound_permitted: [
    [ address : 'clicker' ]
  ],
  outbound_permitted: [
    [ address : 'clicker' ]
  ]
]

container.with {
  // Start the web server, with the config we defined above
  deployModule( 'vertx.web-server-v1.0', webServerConf )
}
