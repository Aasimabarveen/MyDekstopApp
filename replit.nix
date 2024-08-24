{ pkgs }: {
    deps = [
      pkgs.unzip
      pkgs.mesa-demos
      pkgs.xvfb_run
      pkgs.openjfx17
      pkgs.libGL
      pkgs.xorg.xorgserver
      pkgs.xorg.xf86videodummy
      pkgs.mesa
      pkgs.tigervnc
      pkgs.maven
      pkgs.xorg.xauth
      pkgs.openjdk
      pkgs.xorg.libXrender
      pkgs.xorg.libXrandr
      pkgs.xorg.libXtst
      pkgs.xorg.libXi
      pkgs.xorg.libXcursor
      pkgs.xorg.libXcomposite
        pkgs.graalvm17-ce
        pkgs.glibc
        pkgs.xorg.libX11
        pkgs.gtk3
        pkgs.cups
        pkgs.libglvnd
        pkgs.sqlite
        pkgs.replitPackages.jdt-language-server
        pkgs.replitPackages.java-debug                
        
    ];
   env = {
       LD_LIBRARY_PATH = pkgs.lib.makeLibraryPath [
           pkgs.xorg.libX11
           pkgs.xorg.libXxf86vm
           pkgs.libGL
           pkgs.xorg.libXtst
       ];
   };
    services.vnc.enable = true;
    services.vnc.port = 5900;
    services.vnc.bind = "0.0.0.0";
    services.vnc.opengl = true;
    services.vnc.extraOptions = {
      "-depth" = "24";
      "-opengl" = "core"; 
        "-Dprism.order" = "sw";
         "-verbose" = "true";
        "-Dprism.verbose" = "true";
          "-Dprism.debug" = "true";
         run = "JVM_OPTS='-Dprism.verbose=true' mvn exec:java -Dexec.mainClass=com.StaffSubstitution.app.Main";

    };
     

}