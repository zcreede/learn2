package com.example.textdifftool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.github.difflib.DiffUtils;
import com.github.difflib.patch.AbstractDelta;
import com.github.difflib.patch.Patch;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class TextDiffToolApplication {

    public static void main(String[] args) {
        SpringApplication.run(TextDiffToolApplication.class, args);
    }
}

@Controller
class TextDiffController {

    private String originalText = "";
    private String newText = "";
    private boolean hasAccepted = false;
    private String originalTextBackup = "";

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/compare")
    @ResponseBody
    public Map<String, Object> compareTexts(@RequestBody Map<String, String> request) {
        originalText = request.get("original");
        newText = request.get("new");
        originalTextBackup = originalText;
        hasAccepted = false;

        String diffHtml = generateDiff(originalText, newText);
        
        Map<String, Object> response = new HashMap<>();
        response.put("original_text", originalText);
        response.put("diff_html", diffHtml);
        response.put("has_accepted", hasAccepted);
        return response;
    }

    @PostMapping("/accept")
    @ResponseBody
    public Map<String, Object> acceptChanges() {
        originalText = newText;
        hasAccepted = true;

        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("original_text", originalText);
        response.put("has_accepted", hasAccepted);
        return response;
    }

    @PostMapping("/cancel")
    @ResponseBody
    public Map<String, Object> cancelChanges() {
        if (hasAccepted) {
            originalText = originalTextBackup;
            hasAccepted = false;
        }

        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("original_text", originalText);
        response.put("has_accepted", hasAccepted);
        return response;
    }

    private String generateDiff(String oldText, String newText) {
        List<String> oldLines = Arrays.asList(oldText.split("\n"));
        List<String> newLines = Arrays.asList(newText.split("\n"));

        // 计算差异
        Patch<String> patch = DiffUtils.diff(oldLines, newLines);

        // 生成HTML差异表格
        StringBuilder diffHtml = new StringBuilder();
        diffHtml.append("<table class='diff'>\n");
        diffHtml.append("<tr><th colspan='2'>原始文本</th><th colspan='2'>新文本</th></tr>\n");

        int oldPos = 0;
        int newPos = 0;

        for (AbstractDelta<String> delta : patch.getDeltas()) {
            // 添加未改变的行
            for (int i = oldPos; i < delta.getSource().getPosition(); i++) {
                diffHtml.append("<tr>\n");
                diffHtml.append("<td class='line-number'>").append(i + 1).append("</td>\n");
                diffHtml.append("<td class='diff_equal'>").append(escapeHtml(oldLines.get(i))).append("</td>\n");
                diffHtml.append("<td class='line-number'>").append(i + 1).append("</td>\n");
                diffHtml.append("<td class='diff_equal'>").append(escapeHtml(newLines.get(i))).append("</td>\n");
                diffHtml.append("</tr>\n");
            }

            // 处理差异
            switch (delta.getType()) {
                case CHANGE:
                    // 展示修改的行
                    List<String> oldChangeLines = delta.getSource().getLines();
                    List<String> newChangeLines = delta.getTarget().getLines();
                    int maxRows = Math.max(oldChangeLines.size(), newChangeLines.size());

                    for (int i = 0; i < maxRows; i++) {
                        diffHtml.append("<tr>\n");
                        if (i < oldChangeLines.size()) {
                            diffHtml.append("<td class='line-number'>").append(delta.getSource().getPosition() + i + 1).append("</td>\n");
                            diffHtml.append("<td class='diff_sub'>").append(escapeHtml(oldChangeLines.get(i))).append("</td>\n");
                        } else {
                            diffHtml.append("<td class='line-number'></td><td></td>\n");
                        }

                        if (i < newChangeLines.size()) {
                            diffHtml.append("<td class='line-number'>").append(delta.getTarget().getPosition() + i + 1).append("</td>\n");
                            diffHtml.append("<td class='diff_add'>").append(escapeHtml(newChangeLines.get(i))).append("</td>\n");
                        } else {
                            diffHtml.append("<td class='line-number'></td><td></td>\n");
                        }
                        diffHtml.append("</tr>\n");
                    }
                    break;
                case DELETE:
                    // 展示删除的行
                    for (int i = 0; i < delta.getSource().getLines().size(); i++) {
                        diffHtml.append("<tr>\n");
                        diffHtml.append("<td class='line-number'>").append(delta.getSource().getPosition() + i + 1).append("</td>\n");
                        diffHtml.append("<td class='diff_sub'>").append(escapeHtml(delta.getSource().getLines().get(i))).append("</td>\n");
                        diffHtml.append("<td class='line-number'></td><td></td>\n");
                        diffHtml.append("</tr>\n");
                    }
                    break;
                case INSERT:
                    // 展示插入的行
                    for (int i = 0; i < delta.getTarget().getLines().size(); i++) {
                        diffHtml.append("<tr>\n");
                        diffHtml.append("<td class='line-number'></td><td></td>\n");
                        diffHtml.append("<td class='line-number'>").append(delta.getTarget().getPosition() + i + 1).append("</td>\n");
                        diffHtml.append("<td class='diff_add'>").append(escapeHtml(delta.getTarget().getLines().get(i))).append("</td>\n");
                        diffHtml.append("</tr>\n");
                    }
                    break;
            }

            oldPos = delta.getSource().getPosition() + delta.getSource().getLines().size();
            newPos = delta.getTarget().getPosition() + delta.getTarget().getLines().size();
        }

        // 添加剩余未改变的行
        for (int i = oldPos; i < oldLines.size() && i < newLines.size(); i++) {
            diffHtml.append("<tr>\n");
            diffHtml.append("<td class='line-number'>").append(i + 1).append("</td>\n");
            diffHtml.append("<td class='diff_equal'>").append(escapeHtml(oldLines.get(i))).append("</td>\n");
            diffHtml.append("<td class='line-number'>").append(i + 1).append("</td>\n");
            diffHtml.append("<td class='diff_equal'>").append(escapeHtml(newLines.get(i))).append("</td>\n");
            diffHtml.append("</tr>\n");
        }

        diffHtml.append("</table>");
        return diffHtml.toString();
    }

    private String escapeHtml(String text) {
        return text.replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "&#39;")
                .replace(" ", "&nbsp;");
    }
}