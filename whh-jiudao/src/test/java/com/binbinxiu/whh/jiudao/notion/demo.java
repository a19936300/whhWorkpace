package com.binbinxiu.whh.jiudao.notion;

import com._2lazy2name.notion.Notion;
import com._2lazy2name.notion.entity.Page;
import com._2lazy2name.notion.entity.common.PaginationResult;
import com._2lazy2name.notion.entity.common.filter.typeSpecific.TextFilter;
import com._2lazy2name.notion.enumeration.type.PropertyTypeEnum;

import java.io.IOException;

public class demo {
    public static void main(String[] args) throws IOException {
        Notion notion = new Notion("secret_rQWaUGUlY6aruP9ZT3IUWsSwsoWYr68d7jreI90QdWZ");
        TextFilter textFilter = new TextFilter();
        textFilter.contains("是张三啊");
        textFilter.setType(PropertyTypeEnum.RICH_TEXT);
        textFilter.setProperty("Description");
        PaginationResult<Page> be412db67889442d92ebe606a745a03d = notion.queryDatabase("be412db67889442d92ebe606a745a03d", textFilter);
        System.out.println(be412db67889442d92ebe606a745a03d);

    }
}
