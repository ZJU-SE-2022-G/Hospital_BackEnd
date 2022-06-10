package com.segroup.hospitalsite;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.fill.Column;
import com.baomidou.mybatisplus.generator.fill.Property;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;

@SpringBootTest
public class codeGen {
    @Test
    public void main(){
        // 自动填充配置
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/se", "root", "lyb2579363213")
                .globalConfig(builder -> {
                    builder.author("Liu Yibin") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .commentDate("2022-5-26")
                            .outputDir(System.getProperty("user.dir") + "/src/main/java"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.segroup.hospitalsite") // 设置父包名
                            .entity("pojo")
                            .mapper("mapper")
                            .controller("controller")
                            .service("service");
                })
                .strategyConfig(builder -> {
                    builder.addInclude("problem_feedback")
                    .entityBuilder().fileOverride().enableLombok().naming(NamingStrategy.underline_to_camel).columnNaming(NamingStrategy.underline_to_camel).logicDeleteColumnName("deleted").versionColumnName("version").addTableFills(new Column("ask_time", FieldFill.INSERT_UPDATE)).addTableFills(new Column("res_time", FieldFill.INSERT_UPDATE))
                    .controllerBuilder().enableRestStyle().enableHyphenStyle()
                    .mapperBuilder().fileOverride();;// 设置需要生成的表名


                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}
