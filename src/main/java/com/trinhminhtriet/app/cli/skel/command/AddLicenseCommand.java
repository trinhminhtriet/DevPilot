package com.trinhminhtriet.app.cli.skel.command;

import com.trinhminhtriet.app.cli.skel.service.TemplateRenderService;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Slf4j
@Component
@Command(name = "license", description = "Add LICENSE to current directory", mixinStandardHelpOptions = true)
@RequiredArgsConstructor
public class AddLicenseCommand implements Runnable {
    private final TemplateRenderService templateService;

    @Option(names = {"--dir"}, description = "Target directory", defaultValue = ".")
    private File dir;

    @Override
    public void run() {
        Map<String, Object> objectMapping = new HashMap<>();
        objectMapping.put("authorName", "Trinh Minh Triet");
        objectMapping.put("authorEmail", "contact@trinhminhtriet.com");
        try {
            templateService.renderTemplate("common/LICENSE.ftl", objectMapping, new File(dir, "LICENSE"));
            log.info("LICENSE added to {}", dir.getAbsolutePath());
        } catch (Exception e) {
            log.error("Failed to add LICENSE", e);
        }
    }
}
