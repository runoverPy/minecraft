package game.main;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class Installer {
    public static void main(String[] args) {
        if (args.length > 0 && Objects.equals(args[0], "--desc")) {
            System.out.println("Entpackt das Spiel in das Verzeichnis, indem es sich gerade aufhält");
            System.exit(0);
        }
        Path installDir = Paths.get(System.getProperty("user.dir"));
        System.out.println(installDir);

    }
}
