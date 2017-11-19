![cover](./pictures/cover.png)

# Setup

```
compile 'com.jakewharton.timber:timber:4.5.1'
compile 'com.facebook.stetho:stetho:1.5.0'
// Depend on your network
compile 'com.facebook.stetho:stetho-okhttp3:1.5.0'
// or
compile 'com.facebook.stetho:stetho-okhttp:1.5.0'
// or
compile 'com.facebook.stetho:stetho-urlconnection:1.5.0'
// for use javascript console
compile 'com.facebook.stetho:stetho-js-rhino:1.5.0'
```

```java
[MainApplication.class]
public class MainApplication extends BaseApplication{

    @Override
    public void onCreate(){
        super.onCreate();
        Timber.plant( new DebugTree() );

        Stetho.initialize(
                Stetho.newInitializerBuilder( this )
                        .enableWebKitInspector(
                                () -> new Stetho.DefaultInspectorModulesBuilder( MainApplication.this )
                                        .runtimeRepl(
                                                new JsRuntimeReplFactoryBuilder( MainApplication.this )
                                                        .addVariable( "foo", "Assign to foo variable" )
                                                        .build()
                                        ).finish() )
                        .enableDumpapp(
                                () -> new Stetho.DefaultDumperPluginsBuilder( MainApplication.this )
                                        .provide( new HelloWorldDumperPlugin() )
                                        .finish() )
                        .build()
        );
    }
}
```

# 1. UI Debugging

![](./pictures/ui_debugging_1.png)

![](./pictures/ui_debugging_2.png)

# 2. Network Debugging

![](./pictures/network_debugging_1.png)

![](./pictures/network_debugging_2.png)

![](./pictures/network_debugging_3.png)

# 3. Resource Debugging

![](./pictures/resource_debugging_1.png)

![](./pictures/resource_debugging_2.png)

![](./pictures/resource_debugging_3.png)

# 4. Javascript Console

![](./pictures/javascript_console_1.png)

![](./pictures/javascript_console_2.png)

![](./pictures/javascript_console_3.png)

# 5. dumpapp

![](./pictures/dumpapp_1.png)

![](./pictures/dumpapp_2.png)

![](./pictures/dumpapp_3.png)

![](./pictures/dumpapp_4.png)


# Webview debugging

![](./pictures/webview_1.png)

![](./pictures/webview_2.png)
