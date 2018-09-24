package jzhu.com.libprovider.config;

public interface RouterPath {

    interface  MainPath{

        String MAIN_FLUTTER_CONTAINER = "/main/FlutterContainerActivity";

        String MAIN_TEST= "/main/TestActivity";

    }

     interface  ModuleSearchPath{

        String MAIN_FRAGMENT_PROVIDER= "/modulesearch/main";

    }

    interface ModuleUserPath{

        String MAIN_FRAGMENT_PROVIDER= "/moduleuser/main";

    }

    interface  ModuleFlutterPath{

        String FLUTTER_HOME= "/homepage";

        String FLUTTER_TEST= "/testpage";

    }

}
