import com.google.common.collect.*
import java.util.concurrent.*
import org.vertx.java.core.json.* ;

def webServerConf = [
  // Normal web server stuff
  port   : 8080,
  bridge : true,

  inbound_permitted: [
    [ address : 'update' ]
  ],
  outbound_permitted: [
    [ address : 'pointer' ]
  ]
]

ConcurrentMap<String,Map> graphs = new MapMaker().concurrencyLevel(1)
                                                 .weakValues()
                                                 .maximumSize(100)
                                                 .expireAfterWrite( 10, TimeUnit.SECONDS )
                                                 .makeMap()
boolean update = false

def updateHandler = { message ->
  if( message?.body?.uid ) {
    graphs << [ "$message.body.uid": message.body ]
    update = true
  }
}

def eb = vertx.eventBus

container.with {
  // Start the web server, with the config we defined above
  deployModule( 'vertx.web-server-v1.0', webServerConf )

  eb.registerHandler( 'update', updateHandler ) ;

  vertx.setPeriodic( 50, { id ->
    if( update ) {
      eb.publish( 'pointer', [ elements:graphs.values() ] )
    }
  } )
}
