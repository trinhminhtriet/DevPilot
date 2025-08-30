package com.trinhminhtriet.app.cli.skel.command;

import com.trinhminhtriet.app.cli.skel.service.TemplateRenderService;
import java.io.File;
import java.io.IOException;
import java.util.Map;

public abstract class AbstractRustCommand {

  protected void renderTemplate(TemplateRenderService templateService, Map<String, Object> objectMapping, File dir) throws IOException {

    templateService.renderCommonTemplates(objectMapping, dir);

    templateService.renderTemplate("rust/gitignore.ftl", objectMapping, new File(dir, ".gitignore"));
    templateService.renderTemplate("rust/gitattributes.ftl", objectMapping, new File(dir, ".gitattributes"));
    templateService.renderTemplate("rust/editorconfig.ftl", objectMapping, new File(dir, ".editorconfig"));
    templateService.renderTemplate("rust/Makefile.ftl", objectMapping, new File(dir, "Makefile"));
    templateService.renderTemplate("rust/rustfmt.toml.ftl", objectMapping, new File(dir, "rustfmt.toml"));
    templateService.renderTemplate("rust/clippy.toml.ftl", objectMapping, new File(dir, "clippy.toml"));
  }
}
