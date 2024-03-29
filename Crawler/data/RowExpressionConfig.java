12
https://raw.githubusercontent.com/Pingvin235/bgerp/master/src/ru/bgcrm/model/process/config/RowExpressionConfig.java
package ru.bgcrm.model.process.config;

import java.util.HashMap;
import java.util.Map;

import ru.bgcrm.dao.expression.Expression;
import ru.bgcrm.util.Config;
import ru.bgcrm.util.ParameterMap;
import ru.bgcrm.util.Utils;

public class RowExpressionConfig extends Config {
    private Map<String, Expressions> mediaExpressions = new HashMap<String, Expressions>();

    public RowExpressionConfig(ParameterMap setup) {
        super(setup);

        for (ParameterMap conf : setup.subIndexed("rowConfig.").values()) {
            String media = conf.get("media");
            Expressions expressions = new Expressions(conf.get(Expression.STRING_MAKE_EXPRESSION_CONFIG_KEY + "Head"),
                    conf.get(Expression.STRING_MAKE_EXPRESSION_CONFIG_KEY + "Row"));

            if (Utils.isBlankString(media) || Utils.isBlankString(expressions.headRowExpression) || Utils.isBlankString(expressions.rowExpression))
                continue;

            mediaExpressions.put(media, expressions);
        }
    }

    public String getHead(String media, Map<String, Object> data) {
        String result = "";

        Expressions expressions = mediaExpressions.get(media);
        if (expressions != null)
            result = new Expression(data).getString(expressions.headRowExpression);

        return result;
    }

    public String getRow(String media, Map<String, Object> data) {
        String result = "";

        Expressions expressions = mediaExpressions.get(media);
        if (expressions != null)
            result = new Expression(data).getString(expressions.rowExpression);

        return result;
    }

    private static class Expressions {
        private String headRowExpression;
        private String rowExpression;

        private Expressions(String headRowExprssion, String rowExpression) {
            this.headRowExpression = headRowExprssion;
            this.rowExpression = rowExpression;
        }
    }
}
