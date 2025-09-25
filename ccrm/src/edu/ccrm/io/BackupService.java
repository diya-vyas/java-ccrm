package edu.ccrm.io;

import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BackupService {
    private final Path dataPath = Paths.get("data");
    private final Path backupPath = Paths.get("backup");

    public void backup() {
        try {
            if (!Files.exists(dataPath)) {
                System.out.println("⚠️ Nothing to backup.");
                return;
            }
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
            Path backupDir = backupPath.resolve(timestamp);
            Files.createDirectories(backupDir);

            Files.walk(dataPath).forEach(src -> {
                try {
                    Path dest = backupDir.resolve(dataPath.relativize(src));
                    if (Files.isDirectory(src)) {
                        Files.createDirectories(dest);
                    } else {
                        Files.copy(src, dest, StandardCopyOption.REPLACE_EXISTING);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            System.out.println("✅ Backup completed at: " + backupDir.toAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Recursive utility: compute total size of backup directory
    public long computeDirSize(Path dir) {
        if (!Files.exists(dir)) return 0;
        try {
            return Files.walk(dir)
                    .filter(Files::isRegularFile)
                    .mapToLong(p -> {
                        try {
                            return Files.size(p);
                        } catch (IOException e) {
                            return 0L;
                        }
                    }).sum();
        } catch (IOException e) {
            return 0L;
        }
    }

    // Recursive listing
    public void listFilesByDepth(Path dir, int depth) {
        if (!Files.exists(dir)) {
            System.out.println("⚠️ Directory not found: " + dir);
            return;
        }
        try {
            Files.walk(dir, depth).forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
