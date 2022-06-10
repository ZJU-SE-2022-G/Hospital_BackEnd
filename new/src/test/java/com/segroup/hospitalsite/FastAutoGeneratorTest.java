package com.segroup.hospitalsite;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.TemplateType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Properties;
import java.util.Scanner;
import java.util.function.Consumer;

/**
 * Description:
 *
 * @author zheng
 * @date 2022-03-30
 */
@SpringBootTest
public class FastAutoGeneratorTest {
    /**
     * 执行初始化数据库脚本
     */
    public static void before() throws SQLException {
        Connection conn = DATA_SOURCE_CONFIG.build().getConn();
        InputStream inputStream = CodeGeneratorTest.class.getResourceAsStream("/sql/data.sql");
        ScriptRunner scriptRunner = new ScriptRunner(conn);
        scriptRunner.setAutoCommit(true);
        scriptRunner.runScript(new InputStreamReader(inputStream));
        conn.close();
    }

    /**
     * 数据源配置
     */
    private static final DataSourceConfig.Builder DATA_SOURCE_CONFIG = new DataSourceConfig.Builder("jdbc:mysql://124.223.201.95:3316/hospital_info?characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useInformationSchema=true", "hospital_info", "hospital");


    /**
     * 执行 run
     */
    @Test
    public static void main(String[] args) throws SQLException {
//        目前没有初始化文件，不写了
//        before();

        FastAutoGenerator.create(DATA_SOURCE_CONFIG)
                // 注意这里所有输入都不要用单引号引起来！插件作者自己有加
                // 全局配置
                .globalConfig((scanner, builder) -> {
                    builder.author(scanner.apply("请输入开发者名称：")) // 设置作者
                            .disableOpenDir()   //禁止打开输出目录，默认打开
                            .commentDate("yyyy-MM-dd")//注释日期，默认值: yyyy-MM-dd
                            .enableSwagger()   //开启 swagger 模式
                            .outputDir(System.getProperty("user.dir") + "/src/main/java"); // 指定输出目录
                })
                // 包配置
                .packageConfig(builder -> {
                    builder.parent("com.segroup.hospitalsite") // 设置父包名
//                            .moduleName("mbg") // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, System.getProperty("user.dir") + "/src/main/resources/mapper")); // 设置mapperXml生成路径
                })
                // 策略配置
                .strategyConfig((scanner, builder) -> {
                    builder.addInclude(scanner.apply("请输入表名，多个表名用 , 隔开"))
                            .entityBuilder().fileOverride().enableLombok()// 覆盖已生成文件
                            .mapperBuilder().fileOverride();
//                            .addTablePrefix("s_", "c_");    // 设置过滤表前缀
                })
//                .templateConfig(builder -> {
//                    builder
//                            .disable(TemplateType.CONTROLLER)
//                            .disable(TemplateType.SERVICE)
//                            .disable(TemplateType.SERVICEIMPL);
//                })
                .templateEngine(new FreemarkerTemplateEngine())
                /*
                    模板引擎配置，默认 Velocity 可选模板引擎 Beetl 或 Freemarker
                   .templateEngine(new BeetlTemplateEngine())
                   .templateEngine(new FreemarkerTemplateEngine())
                 */.execute();
    }
}
