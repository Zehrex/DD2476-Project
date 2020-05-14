18
https://raw.githubusercontent.com/GZYangKui/openjfx-database/master/app/src/main/java/com/openjfx/database/app/controller/SQLEditController.java
package com.openjfx.database.app.controller;

import com.openjfx.database.mysql.JSqlParserHelper;
import com.openjfx.database.app.BaseController;
import com.openjfx.database.app.controls.TableDataCell;
import com.openjfx.database.app.config.Constants;
import com.openjfx.database.app.enums.NotificationType;
import com.openjfx.database.app.utils.DialogUtils;
import com.openjfx.database.app.utils.RobotUtils;
import com.openjfx.database.base.AbstractDataBasePool;
import com.openjfx.database.common.utils.StringUtils;
import io.vertx.core.json.JsonObject;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import net.sf.jsqlparser.JSQLParserException;
import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.LineNumberFactory;
import org.fxmisc.richtext.model.StyleSpans;
import org.fxmisc.richtext.model.StyleSpansBuilder;

import java.time.Duration;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.openjfx.database.app.DatabaseFX.DATABASE_SOURCE;

/**
 * sql编辑器控制器
 *
 * @author yangkui
 * @since 1.0
 */
public class SQLEditController extends BaseController<JsonObject> {
    /**
     * 大写关键字
     */
    private static final String[] UPPER_KEYWORD = new String[]{
            "ADD",
            "ALL",
            "ALTER",
            "ANALYZE",
            "AND",
            "AS",
            "ASC",
            "ASENSITIVE",
            "BEFORE",
            "BETWEEN",
            "BIGINT",
            "BINARY",
            "SELECT",
            "INSERT",
            "UPDATE",
            "DELETE",
            "SHOW",
            "DROP",
            "WHERE",
            "FROM",
            "LIMIT",
            "INNER",
            "LEFT",
            "RIGHT",
            "LIMIT"
    };
    /**
     * 小写关键字
     */
    private static final String[] LOW_KEYWORD = Arrays.stream(UPPER_KEYWORD).map(String::toLowerCase).toArray(String[]::new);

    private static final String KEYWORD_PATTERN = "\\b(" + String.join("|", UPPER_KEYWORD) + String.join("|", LOW_KEYWORD) + ")\\b";
    private static final String PAREN_PATTERN = "[()]";
    private static final String BRACE_PATTERN = "[{}]";
    private static final String BRACKET_PATTERN = "[\\[\\]]";
    private static final String SEMICOLON_PATTERN = ";";
    private static final String STRING_PATTERN = "\"([^\"\\\\]|\\\\.)*\"";
    private static final String COMMENT_PATTERN = "//[^\n]*" + "|" + "/\\*(.|\\R)*?\\*/";

    private static final Pattern PATTERN = Pattern.compile(
            "(?<KEYWORD>" + KEYWORD_PATTERN + ")"
                    + "|(?<PAREN>" + PAREN_PATTERN + ")"
                    + "|(?<BRACE>" + BRACE_PATTERN + ")"
                    + "|(?<BRACKET>" + BRACKET_PATTERN + ")"
                    + "|(?<SEMICOLON>" + SEMICOLON_PATTERN + ")"
                    + "|(?<STRING>" + STRING_PATTERN + ")"
                    + "|(?<COMMENT>" + COMMENT_PATTERN + ")"
    );

    @FXML
    private CodeArea codeArea;

    @FXML
    private TableView<ObservableList<StringProperty>> tableView;

    private String scheme;
    /**
     * 创建线程池渲染高亮
     */
    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    private AbstractDataBasePool client;

    @Override
    public void init() {
        var uuid = data.getString(Constants.UUID);

        scheme = data.getString(Constants.SCHEME);

        //加载scheme
        client = DATABASE_SOURCE.getDataBaseSource(uuid);
        var param = client.getConnectionParam();
        var title = param.getName() + "<" + param.getHost() + "/" + scheme + ">";

        stage.setTitle(title);

        //开启行显示
        codeArea.setParagraphGraphicFactory(LineNumberFactory.get(codeArea));
        codeArea.multiPlainChanges()
                .successionEnds(Duration.ofMillis(500))
                .supplyTask(this::computeHighlightingAsync)
                .awaitLatest(codeArea.multiPlainChanges())
                .filterMap(t -> {
                    if (t.isSuccess()) {
                        return Optional.of(t.get());
                    } else {
                        t.getFailure().printStackTrace();
                        return Optional.empty();
                    }
                }).subscribe(this::applyHighlighting);
        stage.setOnCloseRequest(event -> executor.shutdown());
    }

    private Task<StyleSpans<Collection<String>>> computeHighlightingAsync() {
        String text = codeArea.getText();
        Task<StyleSpans<Collection<String>>> task = new Task<>() {
            @Override
            protected StyleSpans<Collection<String>> call() {
                return computeHighlighting(text);
            }
        };
        executor.execute(task);
        return task;
    }

    private void applyHighlighting(StyleSpans<Collection<String>> highlighting) {
        codeArea.setStyleSpans(0, highlighting);
    }


    private static StyleSpans<Collection<String>> computeHighlighting(String text) {
        Matcher matcher = PATTERN.matcher(text);
        int lastKwEnd = 0;
        StyleSpansBuilder<Collection<String>> spansBuilder
                = new StyleSpansBuilder<>();
        while (matcher.find()) {
            String styleClass =
                    matcher.group("KEYWORD") != null ? "keyword" :
                            matcher.group("PAREN") != null ? "paren" :
                                    matcher.group("BRACE") != null ? "brace" :
                                            matcher.group("BRACKET") != null ? "bracket" :
                                                    matcher.group("SEMICOLON") != null ? "semicolon" :
                                                            matcher.group("STRING") != null ? "string" :
                                                                    matcher.group("COMMENT") != null ? "comment" :
                                                                            null; /* never happens */
            assert styleClass != null;
            spansBuilder.add(Collections.emptyList(), matcher.start() - lastKwEnd);
            spansBuilder.add(Collections.singleton(styleClass), matcher.end() - matcher.start());
            lastKwEnd = matcher.end();
        }
        spansBuilder.add(Collections.emptyList(), text.length() - lastKwEnd);
        return spansBuilder.create();
    }

    @FXML
    public void executeSql(ActionEvent event) {
        var str = codeArea.getText();
        if (StringUtils.isEmpty(str)) {
            DialogUtils.showNotification("sql语句不能为空", Pos.TOP_CENTER, NotificationType.WARNING);
            return;
        }
        String sql;
        try {
            sql = JSqlParserHelper.transform(str, scheme);
        } catch (JSQLParserException e) {
            DialogUtils.showErrorDialog(e, "sql转换异常");
            return;
        }
        var b = sql.toLowerCase().trim();
        var a = b.startsWith("select") | b.startsWith("show");
        if (a) {
            executeSqlQuery(sql);
        } else {
            executeSqlUpdate(sql);
        }
    }

    @FXML
    public void copySql(ActionEvent event) {
        var sql = codeArea.getText();
        if (StringUtils.isEmpty(sql)) {
            DialogUtils.showNotification("sql不能为空", Pos.TOP_CENTER, NotificationType.INFORMATION);
            return;
        }
        RobotUtils.addStrClipboard(sql);
    }

    @FXML
    public void clearSql(ActionEvent event) {
        codeArea.deleteText(0, codeArea.getText().length());
    }

    public void executeSqlQuery(String sql) {
        var future = client.getDql().executeSql(sql);
        future.onSuccess(rs -> {
            for (Map.Entry<List<String>, List<String[]>> entry : rs.entrySet()) {
                var columns = entry.getKey();
                var data = entry.getValue();
                //创建列
                createColumn(columns);
                createData(data);
            }
        });
        future.onFailure(t -> DialogUtils.showErrorDialog(t, "执行查询失败"));
    }

    public void executeSqlUpdate(String sql) {
        var future = client.getDml().executeSqlUpdate(sql);
        future.onSuccess(rs -> {
            //创建列
            var columnName = "affected rows";
            createColumn(Collections.singletonList(columnName));
            //新增列数据
            createData(Collections.singletonList(new String[]{rs.toString()}));
        });
        future.onFailure(t -> DialogUtils.showErrorDialog(t, "更新失败"));
    }

    private void createColumn(List<String> columns) {
        //clear exist table column
        Platform.runLater(() -> tableView.getColumns().clear());
        for (int i = 0; i < columns.size(); i++) {
            var title = columns.get(i);
            TableColumn<ObservableList<StringProperty>, String> column = new TableColumn<>();
            column.setText(title);
            final var j = i;
            column.setCellValueFactory(cellDataFeatures -> {
                ObservableList<StringProperty> values = cellDataFeatures.getValue();
                if (j >= values.size()) {
                    return new SimpleStringProperty("");
                } else {
                    return cellDataFeatures.getValue().get(j);
                }
            });
            column.setCellFactory(TableDataCell.forTableColumn());
            Platform.runLater(() -> tableView.getColumns().add(column));
        }
    }

    private void createData(List<String[]> data) {
        var list = FXCollections.<ObservableList<StringProperty>>observableArrayList();
        for (var row : data) {
            var item = FXCollections.<StringProperty>observableArrayList();
            for (var val : row) {
                item.add(new SimpleStringProperty(val));
            }
            list.add(item);
        }
        Platform.runLater(() -> {
            tableView.getItems().clear();
            tableView.getItems().addAll(list);
            tableView.refresh();
        });
    }
}
